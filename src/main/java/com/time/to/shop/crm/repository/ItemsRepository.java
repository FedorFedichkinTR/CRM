package com.time.to.shop.crm.repository;

import com.time.to.shop.crm.model.db.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends CrudRepository<Item, Long> {

    List<Item> findAll();

}
