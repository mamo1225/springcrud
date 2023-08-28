package com.example.springcrud.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springcrud.dto.OrderDto;
import com.example.springcrud.entity.OrderEntity;
import com.example.springcrud.form.OrderForm;
import com.example.springcrud.form.OrderListSearchCondForm;
import com.example.springcrud.service.OrderService;
import com.example.springcrud.symbol.MessageSymbols;
import com.example.springcrud.util.MessageUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class OrderController {

    /** サンプル注文サービス */
    private OrderService service;

    /**
     * [検索画面]検索ボタン押下時
     * @param form 検索条件フォーム
     * @return 検索画面
     */
    @GetMapping(value = "/viewList")
    public String viewList(@ModelAttribute("orderListSearchCondForm") OrderListSearchCondForm form) {
        return "order/list";
    }

    /**
     * [検索画面]検索ボタン押下時
     * @param form 検索条件フォーム
     * @param model 画面モデル
     * @return 検索画面
     */
    @PostMapping(value = "/search")
    public String search(@Validated @ModelAttribute("orderListSearchCondForm") OrderListSearchCondForm form,
            Model model) {
        log.info("show start");
        List<OrderDto> dtoList = service.findByCond(form);
        model.addAttribute("orders", dtoList);
        model.addAttribute("msg", "select result");
        log.info("show end");
        return "order/list";
    }
    /**
     * [新規登録入力画面]初期表示時
     * @param model
     * @return
     */
    @GetMapping(value = "/viewEntry")
    public String viewEntry(Model model, @ModelAttribute("msg") String msg) {
        OrderForm form = new OrderForm();
        model.addAttribute("orderForm", form);
        model.addAttribute("isConfirm", false);
        if (msg != null && !msg.equals("")) {
            model.addAttribute("msg", msg);
        }
        return "order/entry";
    }

    /**
     * [新規登録入力画面]確認ボタン押下時
     * @param form
     * @param model
     * @return
     * @throws ApplicationException
     */
    @PostMapping(value = "/confirmEntry")
    public String confirmEntry(@Validated @ModelAttribute("orderForm") OrderForm form, BindingResult br, Model model) {
        log.info("confirm start");

        if (br.hasErrors()) {
            log.info("confirm end");
            model.addAttribute("orderForm", form);
            model.addAttribute("isConfirm", false);
            return "order/entry";
        }

        model.addAttribute("isConfirm", true);
        log.info("confirm end");
        return "order/entry";
    }

    /**
     * [新規登録確認画面]戻るボタン押下時
     * @param form
     * @param model
     * @return
     */
    @PostMapping(value = "/entry", params = "backEntry")
    public String backEntry(@ModelAttribute("orderForm") OrderForm form, Model model) {
        model.addAttribute("sorderForm", form);
        model.addAttribute("isConfirm", false);
        return "order/entry";
    }

    /**
     * [新規登録確認画面]新規登録ボタン押下時
     * @param form
     * @param model
     * @return
     * @throws ApplicationException
     */
    @PostMapping(value = "/entry", params = "insert")
//    public String entry(@Validated @ModelAttribute("orderForm") OrderForm form, BindingResult br, Model model)
    public String entry(@Validated @ModelAttribute("orderForm") OrderForm form, RedirectAttributes redirectAttributes, BindingResult br, Model model) {
        log.info("entry start");
        setAuditInfo();
        if (service.create(form) > 0) {
            model.addAttribute("msg", MessageUtils.getMesasge(MessageSymbols.XXXMSG001, "登録"));
            redirectAttributes.addFlashAttribute("msg", MessageUtils.getMesasge(MessageSymbols.XXXMSG001, "登録"));
        } else {
//            model.addAttribute("msg", MessageUtils.getMesasge(MessageSymbols.XXXMSG002, "登録"));
            redirectAttributes.addFlashAttribute("msg", MessageUtils.getMesasge(MessageSymbols.XXXMSG002, "登録"));
        }
        //        model.addAttribute("isConfirm", true);
        log.info("entry end");
//        return "order/complete";
        model.addAttribute("isConfirm", false);
        return "redirect:/viewEntry";
    }

    /**
     * [変更入力画面]初期表示時
     * @param model
     * @return
     */
    @GetMapping(value = "/viewEdit/{id}")
    public String viewEdit(@PathVariable String id, Model model) {
        // データ取得
        OrderEntity entity = service.findById(id);
        // Formにセット
        OrderForm form = new OrderForm();
        BeanUtils.copyProperties(entity, form);
        model.addAttribute("orderForm", form);
        model.addAttribute("isConfirm", false);
        return "order/edit";
    }

    /**
     * [変更入力画面]確認ボタン押下時
     * @param form
     * @param model
     * @return
     * @throws ApplicationException
     */
    @PostMapping(value = "/confirmEdit")
    public String confirmEdit(@Validated @ModelAttribute("orderForm") OrderForm form, Model model) {
        log.info("confirm start");
        model.addAttribute("isConfirm", true);
        log.info("confirm end");
        return "order/edit";
    }

    /**
     * [変更確認画面]戻るボタン押下時
     * @param form
     * @param model
     * @return
     */
    @PostMapping(value = "/edit", params = "backEdit")
    public String backEdit(@ModelAttribute("orderForm") OrderForm form, Model model) {
        model.addAttribute("orderForm", form);
        model.addAttribute("isConfirm", false);
        return "order/edit";
    }

    /**
     * [変更確認画面]更新ボタン押下時
     * @param form
     * @param model
     * @return
     * @throws ApplicationException
     */
    @PostMapping(value = "/edit", params = "update")
    public String edit(@Validated @ModelAttribute("orderForm") OrderForm form, Model model) {
        System.out.println("★★★★★★");
        log.info("update start");
        setAuditInfo();
        int result = service.update(form);
        if (result > 0) {
            model.addAttribute("msg", MessageUtils.getMesasge(MessageSymbols.XXXMSG001, "更新"));
        } else {
            model.addAttribute("msg", MessageUtils.getMesasge(MessageSymbols.XXXMSG002, "更新"));
        }
        log.info("update end");
        return "order/complete";
    }

    /**
     * [検索結果画面]削除ボタン押下時
     *
     * @param id 注文ID
     * @param model 画面モデル
     * @return 完了画面
     */
    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        log.info("delete start");
        setAuditInfo();
        //service.logicalRemove(id);
        service.pysicalRemove(id);
        log.info("delete end");
        model.addAttribute("msg", "deleted!");
        return "order/complete";
    }

    /**
     * 管理情報設定
     */
    private void setAuditInfo() {
        // String userId = "";
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // if (auth != null) {
        //     LoginSession user = (LoginSession) auth.getPrincipal();
        //     userId = user.getUserid();
        // }
        // AuditInfoHolder.clear();
        // AuditInfoHolder.set(new Date(), userId, "AAA");
    }

}
