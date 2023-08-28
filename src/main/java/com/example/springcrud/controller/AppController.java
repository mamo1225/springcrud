package com.example.springcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    /**
     * 初期表示時
     * @return TOP画面
     */
    @GetMapping(value = "/")
    public String index(Model model) {
//        model.addAttribute("msg", "INDEX PAGE"); // 表示メッセージ
        return "index";
    }

    /**
     * [CRUDサンプルメニュー画面]初期表示時
     * @return CRUDサンプルメニュー画面
     * @throws Exception
     */
//    @GetMapping(value = "/ordermenu")
//    public String samplemenu() throws Exception {
//        return "order/menu";
//    }

    /**
     * [CRUDサンプルメニュー画面]初期表示時
     * @return CRUDサンプルメニュー画面
     * @throws Exception
     */
    @GetMapping(value = "/columnresize")
    public String columnresize() throws Exception {
        return "sample/columnresize";
    }

}
