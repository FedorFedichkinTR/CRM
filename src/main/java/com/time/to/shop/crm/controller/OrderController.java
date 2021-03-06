package com.time.to.shop.crm.controller;

import com.time.to.shop.crm.model.db.Customer;
import com.time.to.shop.crm.model.db.Order;
import com.time.to.shop.crm.service.CustomerService;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public String displayAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders/all";
    }

    @GetMapping("/add")
    public String addOrders(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customer", new Customer());
        return "orders/add";
    }

    @PostMapping("/add")
    public String addNewOrder(@ModelAttribute("order") Order order,
                              @ModelAttribute("customer") Customer customer) {
        return Objects.nonNull(orderService.saveOrderWithCustomer(order, customer)) ? "redirect:all" : "redirect:add";
    }
}
