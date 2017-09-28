package cn.happycoding.www.util;

import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Copyright©2017-2018 中卡科技 版权所有. All rights reserved.
 * @description Properties文件工具
 * @version V1.0.0
 * @motto
 * 人生三大境界：
 * 昨夜西风凋碧树，独上高楼，望尽天涯路
 * 衣带渐宽终不悔，为伊消得人憔悴
 * 众里寻他千百度。蓦然回首，那人却在灯火阑珊处
 * @className PropertiesTools
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/9/18
 */
public class PropertiesTools {

    //配置文件地址
    private static final String PATH = "wechat.properties";
    private static InputStream ins = null;
    private static Properties p = null;
    /**
     * 采用静态方法
     */
    private static Properties props = new Properties();

    static {
        try {
            //获取配置文件的路径
            String configPath = Thread.currentThread().getContextClassLoader().getResource(PATH).getPath();
            //转换成文件流
            ins = new BufferedInputStream(new FileInputStream(configPath));
            p = new Properties();
            p.load(ins);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Map<String, Object> map = new ConcurrentReaderHashMap();

    /**
     * 根据key查询value,返回string类型的数据
     * @param key
     * @return
     */
    public static String getConfig(String key){
        if (!map.containsKey(key)){
            map.put(key, p.getProperty(key));
        }
        return (String) map.get(key);
    }

    /**
     * 根据key查询value,返回Int类型的数据
     * @param key
     * @return
     */
    public static int getConfigForInt(String key){
        if (!map.containsKey(key)){
            map.put(key, p.getProperty(key));
        }
        return Integer.parseInt(String.valueOf(map.get(key)));
    }



    /**
     * 更新（或插入）一对properties信息(主键及其键值)
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     * @param keyname 键名
     * @param keyvalue 键值
     */
    public static void writeProperties(String keyname,String keyvalue) {
        try {
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(PATH);
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }


}
