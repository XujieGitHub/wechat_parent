package cn.happycoding.www.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @description 全局变量
 * @version V1.0.0
 * @motto
 * 人生三大境界：
 * 昨夜西风凋碧树，独上高楼，望尽天涯路
 * 衣带渐宽终不悔，为伊消得人憔悴
 * 众里寻他千百度。蓦然回首，那人却在灯火阑珊处
 * @className GlobalPar
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/9/18
 */
public class GlobalPar {


    private static Map<String,String> token =new HashMap<>();

    public static Map<String, String> getToken() {
        return token;
    }

    public static void setToken(Map<String, String> token) {
        GlobalPar.token = token;
    }
}
