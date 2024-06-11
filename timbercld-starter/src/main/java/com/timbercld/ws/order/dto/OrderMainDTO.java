package com.timbercld.ws.order.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderMainDTO {
    @TableId
    private long id;
    private int quantity;
    private double amount;
    private Long productId;
    private Long buyerId;
    private Long sellerId;
    /**    private Long addressId;**/
    private  String address;
    private String phoneNum;
    private String recvName;
}
