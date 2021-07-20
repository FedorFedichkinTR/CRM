package com.time.to.shop.crm.controller;

import com.time.to.shop.crm.entity.Item;
import com.time.to.shop.crm.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemsController {

    private final ItemsService itemsService;

    @RequestMapping("/all")
    public List<Item> getAllItems() {
        return itemsService.findAllItems();
    }
}
