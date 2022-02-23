package com.lessonsbyleo.practicerepo.webclient.inf;

import com.lessonsbyleo.practicerepo.data.Item;

import java.util.List;
import java.util.Map;

public interface InventoryWebclient {
    Map<String,Item> getInventory(List<String> skus);
}
