package com.timbercld.ws.order.controller;

import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.core.utils.Result;
import com.timbercld.ws.order.dto.OrderMainDTO;
import com.timbercld.ws.order.entity.OrderMainEntity;
import com.timbercld.ws.order.service.OrderMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order/main")
public class OrderMainController {

    @Autowired
    private OrderMainService orderMainService;

    @PostMapping
    public Result save(@RequestBody OrderMainDTO orderMainDTO){
        orderMainService.save(orderMainDTO);
        return new Result();
    }
    @PutMapping
    public Result update(@RequestBody OrderMainDTO orderMainDTO){
        this.orderMainService.updateById(ConvertUtils.sourceToTarget(orderMainDTO, OrderMainEntity.class));
        return new Result();
    }
}
