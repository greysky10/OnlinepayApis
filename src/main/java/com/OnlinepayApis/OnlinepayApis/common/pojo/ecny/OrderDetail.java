package com.OnlinepayApis.OnlinepayApis.common.pojo.ecny;


import lombok.Data;

@Data
public class OrderDetail {
    private String subMercCode;

    private String subMercName;

    private String totalAmt;

    private String totalNum;

    private String merUnitDetail;
}
