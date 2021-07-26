package com.time.to.shop.crm.service;

import com.time.to.shop.crm.model.db.Item;
import com.time.to.shop.crm.repository.ItemsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsService {

    @NonNull
    private final ItemsRepository itemsRepository;

    public List<Item> findAllItems() {
        return itemsRepository.findAll();
    }
}
