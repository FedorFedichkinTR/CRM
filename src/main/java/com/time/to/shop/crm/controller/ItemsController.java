package com.time.to.shop.crm.controller;

import com.time.to.shop.crm.model.db.Item;
import com.time.to.shop.crm.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemsController {

    private final ItemsService itemsService;

    @GetMapping("/all")
    public String getAllItems(Model model) {
        model.addAttribute("items", itemsService.findAllItems());
        return "items/all";
    }
}

