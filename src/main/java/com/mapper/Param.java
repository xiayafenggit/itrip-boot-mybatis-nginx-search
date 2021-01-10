package com.mapper;

import java.io.Serializable;

/**
 * Created by zezhong.shang on 17-5-11.
 */
public class Param implements Serializable{

    private String key;

    private Object value;

    private String operator;

    public static class OPERATOR{
        public static String OR="OR";
        public static String AND="AND";
        public static String IN="IN";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Param(String key, Object value) {
        this.key = key;
        this.value = value;
        this.operator=OPERATOR.AND;
    }

    public Param(String key, Object value, String operator) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }
}
