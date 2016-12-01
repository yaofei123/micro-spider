package com.yaofei.framework.mvc;

import java.util.HashMap;

/**
 * 返回 值
 *
 * @author an hao 2016-7-9 下午5:45:22
 */
class CoreBaseConstant {

    static final Integer SUCCESS = 0; // 成功

    static final Integer FAILURE = -1; // 异常

    /**
     * 非法参数（参数为空或参数格式、类型等不符）
     */
    final static int INVALID_PARAMETER = 110000;

    /**
     * 会员积分账户余额不足,不能消费
     */
    final static int POINTS_NOT_ENOUGH = 110001;

    /**
     * 根据所传参数未查找到结果
     */
    final static int RESOURCE_NOT_EXIST = 110002;




    /**
     * 删除失败
     */
    final static int DELETE_FAILURE = 110003;

    /**
     * 数据重复
     */
    final static int DATA_REPEAT = 110004;

    /**
     * 数据添加失败
     */
    final static int DATA_ADD_FAILURE = 110005;


    private static HashMap<Integer, String> map = new HashMap<>();


    static {
        map.put(SUCCESS, "接口调用成功");
        map.put(FAILURE, "接口调用失败");
        map.put(POINTS_NOT_ENOUGH, "会员积分账户余额不足,不能消费");
        map.put(INVALID_PARAMETER, "非法参数");
        map.put(RESOURCE_NOT_EXIST, "资源不存在");
        map.put(DELETE_FAILURE, "删除失败");
        map.put(DATA_REPEAT, "数据重复");
        map.put(DATA_ADD_FAILURE, "数据添加失败");
    }


    static String getMsgByCode(Integer code) {
        return map.get(code);
    }

}
