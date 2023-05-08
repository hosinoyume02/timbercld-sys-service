/**
 * Copyright (C) 2022-2023 Timber Chain Cloud (TimberCLD). All Rights Reserved.
 *
 * @author TimberCLD
 * @email account@timbercld.com
 * @site https://www.timbercld.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
* Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.timbercld.core.utils;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * excel工具类
 *
 * @author fly2leo zouzhiyuan@emulian.com
 */
public class ExcelUtils {

    public static  List<String> getExcelSheet(File file){
        boolean isExcel2003 = WDWUtil.isExcel2003(file.getName());
        List<String> stringList = new ArrayList<>();
        FileInputStream is = null;
        try{
            is = new FileInputStream(file);
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(is);
            } else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面的信息
            int number = wb.getNumberOfSheets();
            if(number > 0){
                for(int i = 0;i<number;i++){
                    stringList.add(wb.getSheetName(i));
                }
            }
            is.close();
        } catch (IOException e)  {
            e.printStackTrace();
        }finally {
            if(is !=null) {
                try{
                    is.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return stringList;
    }
    public static Workbook writeExcel(File file){
        boolean isExcel2003 = WDWUtil.isExcel2003(file.getName());


        Workbook wb = null;
        try{

            /** 根据版本选择创建Workbook的方式 */

            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook();

            } else{//当excel是2007时
                wb = new XSSFWorkbook();

            }
            //读取Excel里面的信息

        } catch (Exception e)  {
            e.printStackTrace();
        }
        return  wb;
    }
    public static Map<String,Object> getExcelInfo(File file, int sheetNo){
        Map<String,Object> pds = null;
        boolean isExcel2003 = WDWUtil.isExcel2003(file.getName());
        FileInputStream is = null;
        try{
             is = new FileInputStream(file);
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(is);
            } else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            //读取Excel里面的信息
            pds = readExcelValue(wb,sheetNo);
            is.close();
        } catch (IOException e)  {
            e.printStackTrace();
        }finally {
            if(is !=null) {
                try{
                    is.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return pds;
    }
    private static int totalRows = 0;
    //总条数
    private static int totalCells = 0;
    //错误信息接收器
    private static String errorMsg;

    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }
    private static Object getValueFromCell(Cell cell) {
        Object cellValue = null;
        if(null == cell) {
            return null;
        }else if(cell.getCellType() == CellType.STRING) {
            cellValue = cell.getStringCellValue();
        }else if(cell.getCellType() == CellType.NUMERIC) {
            if(DateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                cellValue = DateUtil.getJavaDate(d);
            }else {
                double d = cell.getNumericCellValue();
                double _1 = Math.floor(d);
                if (_1<d) { //有小數部分
                    cellValue = d;
                }else { //無小數部分
                    long _2 = Math.round(_1);
                    if (_2>Integer.MAX_VALUE || _2<Integer.MIN_VALUE) {
                        cellValue = _2;
                    }else {
                        cellValue = (int)_2;
                    }
                }
            }
        }else if(cell.getCellType() == CellType.BLANK) {
            cellValue = "";
        }else if(cell.getCellType() == CellType.BOOLEAN) {
            cellValue = cell.getBooleanCellValue();
        }else if(cell.getCellType() == CellType.ERROR) {
            cellValue = "";
        }else if(cell.getCellType() == CellType.FORMULA) {
            cellValue = cell.getCellFormula();
        }

        return cellValue;
    }


    private static Map<String, Object> readExcelValue(Workbook wb, int sheetNo){
        //得到第几个shell
        Sheet sheet = wb.getSheetAt(sheetNo);

        //得到Excel的行数
        totalRows = sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if(totalRows >= 1 && sheet.getRow(0) != null){
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        //第一行的数据
        ArrayList<String> keys = new ArrayList<>();
        //获取第一行的数据
        Row row1 = sheet.getRow(0);
        for (int i = 0; i < totalCells; i++) {
            Cell cell = row1.getCell(i);
            String value = String.valueOf(getValueFromCell(cell));
            keys.add(value);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("title",keys);

        List<LinkedHashMap<String,Object>> pds = new ArrayList<>();
        LinkedHashMap<String,Object> pd;
        //循环Excel行数,从第二行开始。标题不入库
        for(int r = 1; r < totalRows; r++){
            Row row = sheet.getRow(r);
            if (null == row) continue;
            pd = new LinkedHashMap<>();

            //循环Excel的列
            for(int c = 0; c <totalCells; c++){

                Cell cell = row.getCell(c);
                //将列的信息添加到Map
                pd.put(keys.get(c),getValueFromCell(cell));
            }
            //添加客户
            pds.add(pd);
        }
        map.put("data",pds);
        return map;
    }
    /**
     * Excel导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list,
                                   Class<?> pojoClass) throws IOException {
        if(StringUtils.isBlank(fileName)){
            //当前日期
            fileName = DateUtils.format(new Date());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param sourceList    原数据List
     * @param targetClass   目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
                                           Class<?> targetClass) throws Exception {
        List<Object> targetList = new ArrayList<>(sourceList.size());
        for(Object source : sourceList){
            Object target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, targetList, targetClass);
    }
}

class WDWUtil {

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}