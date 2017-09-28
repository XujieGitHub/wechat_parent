package cn.happycoding.www.util;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @ClassName: GetTokenTool
 * @Description: 获取token方法
 * @author: Mr.xu
 * @date: 2017/9/28
 */
public class GetTokenTool {

    public static String getToken(){
        String jstoken = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "client_credential");
            params.put("appid", PropertiesTools.getConfig(Constant.APPID));
            params.put("secret", PropertiesTools.getConfig(Constant.SECRET));
            jstoken = HttpTools.sendGet(
                    PropertiesTools.getConfig(Constant.GET_TOKEN_URL), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.parseObject(jstoken).getString("access_token");
    }


}
