package cn.happycoding.www.menuenty;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @motto 我坚信，优质代码才能创造好的程序
 * @description 点击型菜单
 * @className ClickButton
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/5/26
 */
public class ClickButton {

    private Integer menuId;

    private Integer parentMenuId;//父类编号（菜单的编号前端生成随机数，与相关菜单绑定）

    private String type;

    private String name;

    private String key;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
