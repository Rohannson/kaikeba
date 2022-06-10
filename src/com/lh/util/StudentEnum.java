package com.lh.util;

public enum StudentEnum {

    READING(1, "在读"),
    SUSPENSION(2, "休学"),
    DROPOUT(3, "退学"),
    DELETE(4, "删除");

    public final Integer type;
    public final String value;
    StudentEnum(Integer type, String value){
        this.type = type;
        this.value = value;
    }

}
