package cn.gzho.esdemo.controller;

import cn.gzho.esdemo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-06 10:55 AM
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/parse/{keyword}")
    public String parse(@PathVariable("keyword") String keyword) {
        return goodsService.putContentIntoES(keyword).toString();
    }

    @GetMapping("search/{title}")
    public List<Map<String, Object>> search(@PathVariable("title") String title,
                                            @RequestParam(name = "pageNum") Integer pageNum,
                                            @RequestParam(name = "pageSize") Integer pageSize) {
        return goodsService.searchPage(title, pageNum, pageSize);
    }

}
