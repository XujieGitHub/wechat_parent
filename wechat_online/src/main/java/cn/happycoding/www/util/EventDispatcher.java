package cn.happycoding.www.util;

import cn.happycoding.www.vo.MenuKey;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
public class EventDispatcher {

    public static String processEvent(Map<String, String> map) {
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            System.out.println("==============这是关注事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            System.out.println("==============这是扫描二维码事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            System.out.println("==============这是位置上报事件！");
        }

        /*if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");
            System.out.println("map:"+map);
        }*/

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
            System.out.println("==============这是自定义菜单 View 事件！");
        }
        if(map.get("EventKey").equals(MenuKey.BIND_STUDENT_ID)){
            System.out.println(map);
            System.out.println("==============这是点击绑定学生账号事件！");
        }
        if(map.get("EventKey").equals(MenuKey.USE_BLUETOOTH_WATER)){
            System.out.println("==============这是点击使用蓝牙水控事件！");
        }
        if(map.get("EventKey").equals(MenuKey.HELP_DOC)){
            System.out.println("==============这是点击帮助文档事件！");
        }
        return null;
    }


}
