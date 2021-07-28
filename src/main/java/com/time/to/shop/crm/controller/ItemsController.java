package com.time.to.shop.crm.controller;

import com.time.to.shop.crm.model.db.Item;
import com.time.to.shop.crm.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemsController {

    private final ItemsService itemsService;

    @GetMapping("/all")
    public String displayAllItems(Model model) {
        model.addAttribute("items", itemsService.findAllItems());
        return "items/all";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("item", new Item());
        return "items/add";
    }

    @PostMapping("/add")
    public String addNewItem(@ModelAttribute("item") Item item) {
        return Objects.nonNull(itemsService.save(item)) ? "redirect:all" : "redirect:add";
    }
}

