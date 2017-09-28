package cn.happycoding.www.control;

import cn.happycoding.www.util.EventDispatcher;
import cn.happycoding.www.util.MessageUtil;
import cn.happycoding.www.util.MsgDispatcher;
import cn.happycoding.www.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Copyright©2017-2018 中卡科技 版权所有. All rights reserved.
 * @description 微信接入验证
 * @version V1.0.0
 * @motto
 * 人生三大境界：
 * 昨夜西风凋碧树，独上高楼，望尽天涯路
 * 衣带渐宽终不悔，为伊消得人憔悴
 * 众里寻他千百度。蓦然回首，那人却在灯火阑珊处
 * @className WechatSecurity
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/9/18
 */
@Controller
@RequestMapping("/wechat")
public class WechatSecurity {

    public static final Logger log = LoggerFactory.getLogger(WechatSecurity.class);

    /**
     * @description get方法用于微信消息验证
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName doGet
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/24
     * @param signature  微信加密签名
     * @param timestamp  时间戳
     * @param nonce      随机数
     * @param echostr    随机字符串
     * @return
     */
    @RequestMapping(value = "/wx_security",method = RequestMethod.GET)
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestParam(value = "signature", required = true) String signature,
                      @RequestParam(value = "timestamp", required = true) String timestamp,
                      @RequestParam(value = "nonce", required = true) String nonce,
                      @RequestParam(value = "echostr", required = true) String echostr){
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                log.info("非法请求！");
            }
        }catch (Exception e){
            log.error("get请求错误！");
            e.printStackTrace();
        }
    }

    /**
     * @description  post 方法用于接收微信服务端消息
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName DoPost
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/24
     * @param
     * @return
     */
    @RequestMapping(value = "wx_security", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request,HttpServletResponse response) {
        PrintWriter out = null;
        try {
            Map<String,String> map = MessageUtil.parseXml(request);
            String msgtype=map.get("MsgType");
            log.debug("消息类型：{}",msgtype);
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                EventDispatcher.processEvent(map); //进入事件处理
            }else{
                MsgDispatcher.processMessage(map); //进入消息处理
            }
            response.setCharacterEncoding("utf-8");
            String respXML = MsgDispatcher.processMessage(map); //
            out = response.getWriter();
            out.print(respXML);

        }catch (Exception e){
            log.error("post方法访问出错！");
            e.printStackTrace();
        }finally {
            out.flush();
            out.close();
        }
    }

}
