package cn.happycoding.www.quartz;

import cn.happycoding.www.util.*;
import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright©2017-2018 中卡科技 版权所有. All rights reserved.
 * @description 定时获取access_token
 * @version V1.0.0
 * @motto
 * 人生三大境界：
 * 昨夜西风凋碧树，独上高楼，望尽天涯路
 * 衣带渐宽终不悔，为伊消得人憔悴
 * 众里寻他千百度。蓦然回首，那人却在灯火阑珊处
 * @className WeChatTask
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/9/18
 */
public class WeChatTask {

    private Map<String,String> tokenMap = GlobalPar.getToken();

    /**
     * @description 获取token（主要解决token过期，微信获取的token有效时间是2个小时）
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName getToken_getTicket
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/25
     */
    public void getToken(){
        try {
           /* Map<String, String> params = new HashMap<>();
            params.put("grant_type", "client_credential");
            params.put("appid", PropertiesTools.getConfig(Constant.APPID));
            params.put("secret", PropertiesTools.getConfig(Constant.SECRET));
            String jstoken = HttpTools.sendGet(
                    PropertiesTools.getConfig(Constant.GET_TOKEN_URL), params);
            String access_token = JSON.parseObject(jstoken).getString("access_token");*/
            String access_token = GetTokenTool.getToken();
            //将获取的token写入wechat.properties文件，然后定时器扫描（每两小时更新值）
//            PropertiesTools.writeProperties("access_token", access_token);
            tokenMap.put("access_token",access_token);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"token 为=============================="+access_token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
