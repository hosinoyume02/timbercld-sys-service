package com.timbercld.ws.order.service.impl;

import com.timbercld.core.service.impl.BasicServiceImpl;
import com.timbercld.core.utils.ConvertUtils;
import com.timbercld.ws.demo.dao.ProductDAO;
import com.timbercld.ws.demo.service.ProductService;
import com.timbercld.ws.order.dao.OrderMainDAO;
import com.timbercld.ws.order.dto.OrderMainDTO;
import com.timbercld.ws.order.entity.OrderMainEntity;
import com.timbercld.ws.order.service.OrderMainService;
import org.springframework.stereotype.Service;

@Service
public class OrderMainServiceImpl extends BasicServiceImpl<OrderMainDAO, OrderMainEntity> implements OrderMainService {
//    private ProductDAO productDAO;
    @Override
    public void save(OrderMainDTO orderMainDTO) {
        this.basicDao.insert(ConvertUtils.sourceToTarget(orderMainDTO, OrderMainEntity.class));
    }
}
