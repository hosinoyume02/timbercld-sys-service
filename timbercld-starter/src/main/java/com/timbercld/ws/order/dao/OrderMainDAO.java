package com.timbercld.ws.order.dao;
import com.timbercld.core.dao.BasicDao;
import com.timbercld.ws.order.entity.OrderMainEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMainDAO  extends BasicDao<OrderMainEntity>{
}
