package com.yaofei.framework.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fei.yao on 16/8/3.
 * 返回实体封装
 */
public class CoreBaseResult {
    private static final String RESP_STATUS = "status";
    private static final String RESP_MSG = "msg";
    private static final String RESP_DATA = "data";

    public Map<String, Object> success() {
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.SUCCESS);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.SUCCESS));
        return model;
    }

    protected Map<String, Object> success(Object object) {
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.SUCCESS);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.SUCCESS));
        model.put(RESP_DATA, object);
        return model;
    }

    public Map<String, Object> error() {
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.FAILURE);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.FAILURE));
        return model;
    }

    public Map<String, Object> error(Object object) {
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.FAILURE);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.FAILURE));
        model.put(RESP_DATA, object);
        return model;
    }

    protected Map<String, Object> invalidParameter() {
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.INVALID_PARAMETER);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.INVALID_PARAMETER));
        return model;
    }

    protected Map<String,Object> resourceNotExist(){
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.RESOURCE_NOT_EXIST);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.RESOURCE_NOT_EXIST));
        return model;
    }

    protected Map<String,Object> pointsNotEnough(){
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.POINTS_NOT_ENOUGH);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.POINTS_NOT_ENOUGH));
        return model;

    }

    protected Map<String,Object> repeat(Object object){
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.DATA_REPEAT);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.DATA_REPEAT));
        model.put(RESP_DATA, object);
        return model;
    }

    protected Map<String,Object> addListError(Object object){
        Map<String, Object> model = new HashMap<>();
        model.put(RESP_STATUS, CoreBaseConstant.DATA_ADD_FAILURE);
        model.put(RESP_MSG, CoreBaseConstant.getMsgByCode(CoreBaseConstant.DATA_ADD_FAILURE));
        model.put(RESP_DATA, object);
        return model;
    }



    public Map<String, Object> setResult(String code, String msg, Object object) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(RESP_STATUS, code);
        model.put(RESP_MSG, msg);
        model.put(RESP_DATA, object);
        return model;
    }
}
