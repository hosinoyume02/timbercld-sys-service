package com.timbercld.ws.order.service;

import cn.hutool.db.sql.Order;
import com.timbercld.core.service.BasicService;
import com.timbercld.ws.order.dto.OrderMainDTO;
import com.timbercld.ws.order.entity.OrderMainEntity;

public interface OrderMainService extends BasicService<OrderMainEntity> {

    void save(OrderMainDTO orderMainDTO);
}
