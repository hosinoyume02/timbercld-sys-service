package com.timbercld.ws.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SystemDictTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String dictType;


    private String dictName;


    private String comment;


    private Integer order;


    private Date createDate;


    private Date updateDate;
}

