package cn.happycoding.www.util;

import cn.happycoding.www.message.response.TextMessage;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
public class MsgDispatcher {

    public static String processMessage(Map<String, String> map) {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            StringBuffer msg = new StringBuffer();
            if("1".equals(map.get("Content"))){
                msg.append("非常抱歉，工程师正在研发中！");
            }else if("2".equals(map.get("Content"))){
                msg.append("打开手机蓝牙！").append("\n");
                msg.append("搜索蓝牙设备！").append("\n");
                msg.append("连接蓝牙设备！").append("\n");
                msg.append("连接成功！").append("\n");
            }else if("3".equals(map.get("Content"))){
                msg.append("非常抱歉，工程师正在研发中！").append("\n");
            }else{
                msg.append("你好，这里是乐校通公众号！").append("\n");
                msg.append("现支持如下操作：").append("\n");
                msg.append("1：查询账户余额").append("\n");
                msg.append("2：蓝牙水控使用教程").append("\n");
                msg.append("3：声波充值教程");
            }
            txtmsg.setContent(msg.toString());
            return MessageUtil.textMessageToXml(txtmsg);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }
        return null;
    }


}
