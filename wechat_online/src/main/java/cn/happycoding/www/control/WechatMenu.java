package cn.happycoding.www.control;

import cn.happycoding.www.service.WechatMenuService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @ClassName: WechatMenu
 * @Description: 微信菜单
 * @author: Mr.xu
 * @date: 2017/9/19
 */
@RequestMapping("/wechat_menu")
@Controller
public class WechatMenu {

    public static final Logger log = LoggerFactory.getLogger(WechatMenu.class);

    @Autowired
    private WechatMenuService wechatMenuService;


    @RequestMapping(value = "/set_wechat_menu")
    @ResponseBody
    public JSONObject setWechatMenu(){
        return wechatMenuService.setWechatMenu();
    }



}
