package com.time.to.shop.crm.service;

import com.time.to.shop.crm.model.db.Item;
import com.time.to.shop.crm.repository.ItemsRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;

    public List<Item> findAllItems() {
        return itemsRepository.findAll();
    }

    public Item save(Item item) {
        if (Objects.isNull(item.getDateOfPurchase())) {
            item.setDateOfPurchase(LocalDateTime.now());
        }
        return itemsRepository.save(item);
    }
}
