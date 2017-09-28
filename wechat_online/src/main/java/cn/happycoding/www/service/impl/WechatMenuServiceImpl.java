package cn.happycoding.www.service.impl;

import cn.happycoding.www.control.WechatSecurity;
import cn.happycoding.www.menuenty.ClickButton;
import cn.happycoding.www.menuenty.ViewButton;
import cn.happycoding.www.service.WechatMenuService;
import cn.happycoding.www.util.*;
import cn.happycoding.www.vo.MenuKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @ClassName: WechatMenuServiceImpl
 * @Description: TODO
 * @author: Mr.xu
 * @date: 2017/9/28
 */
@Service
public class WechatMenuServiceImpl implements WechatMenuService {

    private Map<String,String> map = GlobalPar.getToken();

    public static final Logger log = LoggerFactory.getLogger(WechatMenuServiceImpl.class);

    @Override
    public JSONObject setWechatMenu() {
        JSONObject json = new JSONObject();
        String token_acconut = map.get("access_token");
        if(StringUtils.isBlank(token_acconut)){
           token_acconut = GetTokenTool.getToken();
        }
        log.debug("设置菜单的token_acconut:{}",token_acconut);
        //设置菜单实体
        /*ViewButton xs_menu = new ViewButton();
        xs_menu.setName("进入主页");
        xs_menu.setType("view");
        xs_menu.setUrl("http://1733kd5863.iok.la/wechat_online/index/to_index");*/

        //自助服务（一级菜单）
        JSONObject parent_zz = new JSONObject();
        JSONArray sub_button = new JSONArray();
        //自助服务（二级菜单）
        ClickButton bd_studentID = new ClickButton();
        ClickButton sm_device = new ClickButton();
        ClickButton help_info = new ClickButton();
        bd_studentID.setType("click");
        bd_studentID.setName("关于我们");
        bd_studentID.setKey(MenuKey.BIND_STUDENT_ID);
        sm_device.setType("click");
        sm_device.setName("测试菜单");
        sm_device.setKey(MenuKey.USE_BLUETOOTH_WATER);
        help_info.setType("click");
        help_info.setName("帮助文档");
        help_info.setKey(MenuKey.HELP_DOC);

        sub_button.add(bd_studentID);
        sub_button.add(sm_device);
        sub_button.add(help_info);

        parent_zz.put("name","自助服务");
        parent_zz.put("sub_button",sub_button);

        JSONArray button = new JSONArray();
       /* button.add(xs_menu);*/
        button.add(parent_zz);
        JSONObject menujson = new JSONObject();
        menujson.put("button", button);
        StringBuffer buildMenu_api = new StringBuffer();
        buildMenu_api.append(PropertiesTools.getConfig(Constant.BUILD_MENU_URL));
        buildMenu_api.append(token_acconut);
        System.out.println("菜单参数："+menujson);
        String rs = HttpTools.sendPostBuffer(buildMenu_api.toString(), menujson.toJSONString());
        System.out.println(rs);
        return JSONObject.parseObject(rs);
    }
}
