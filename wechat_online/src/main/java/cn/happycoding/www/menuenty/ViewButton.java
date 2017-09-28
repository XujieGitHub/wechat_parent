package cn.happycoding.www.menuenty;

/**
 * ©2017-2018 中卡科技 版权所有
 * @motto 我坚信，优质代码才能创造好的程序
 * @description 视图型菜单
 * @className ViewButton
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/5/26
 */
public class ViewButton {

    private String type;

    private String name;

    private String url;

    private Integer menuLV;//菜单等级

    public Integer getMenuLV() {
        return menuLV;
    }

    public void setMenuLV(Integer menuLV) {
        this.menuLV = menuLV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
