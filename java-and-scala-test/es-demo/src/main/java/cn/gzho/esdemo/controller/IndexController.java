package cn.gzho.esdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-06 9:35 AM
 */
@Controller
public class IndexController {

    @GetMapping({"/", "index"})
    public String index() {
        return "index";
    }
}
