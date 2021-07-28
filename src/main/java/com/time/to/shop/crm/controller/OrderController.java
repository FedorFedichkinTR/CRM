package com.time.to.shop.crm.controller;

import com.time.to.shop.crm.model.db.Item;
import com.time.to.shop.crm.model.db.Order;
import com.time.to.shop.crm.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private OrderService orderService;

    @GetMapping("/all")
    public String displayAllItems(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders/all";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("order", new Order());
        return "orders/add";
    }

    @PostMapping("/add")
    public String addNewOrder(@ModelAttribute("order") Order order) {
        return Objects.nonNull(orderService.save(order)) ? "redirect:all" : "redirect:add";
    }
}
