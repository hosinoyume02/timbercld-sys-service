package com.timbercld.ws.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.timbercld.core.entity.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 订单详情
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("order_main")
public class OrderMainEntity{

    @TableId
    private long id;
    private int quantity;
    private double amount;
    private Long productId;
    private Long buyerId;
    private Long sellerId;
//    private Long addressId;
    private  String address;
    private String phoneNum;
    private String recvName;
}
