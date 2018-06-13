package com.ddm.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yunpeng.song on 6/13/2018.
 */

@Controller
@RequestMapping("/page")
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping("/")
    public String page() {
        logger.info("thymeleaf.page/");
        return "system/th_index";
    }


    @RequestMapping("/redirect")
    public String page2(){
        logger.info("thymeleaf.page2/");
        return "redirect/th_redirect";
    }


    @RequestMapping("/model")
    public String page3(Model model){
        logger.info("thymeleaf.page3/");
        model.addAttribute("name","seawater");
        return "th_hello";
    }
}
