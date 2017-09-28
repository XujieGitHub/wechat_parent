package cn.happycoding.www.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @ClassName: ${CLASS_NAME}
 * @Description: TODO
 * @author: Mr.xu
 * @date: 2017/9/28
 */
@RequestMapping("/index")
@Controller
public class IndexControl {


    @RequestMapping(value = "/to_index")
    public String toIndex(){
        //dsdsdsdsdsds
        return "public/index";
    }


}
