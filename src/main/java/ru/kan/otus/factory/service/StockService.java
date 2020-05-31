package ru.kan.otus.factory.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.kan.otus.factory.domain.AssemblyPlan;

import java.util.Arrays;
import java.util.List;

@Service
public class StockService {

    private static final List<AssemblyPlan> STOCK_ITEMS = Arrays.asList(
            AssemblyPlan.builder().id(1).name("Дверь левая").build(),
            AssemblyPlan.builder().id(2).name("Мотор").build(),
            AssemblyPlan.builder().id(3).name("Стекло лобовое").build());

    public List<AssemblyPlan> getDetailsInStock() {
        return STOCK_ITEMS;
    }

    public AssemblyPlan findById(Integer id) throws Exception {
        return STOCK_ITEMS.stream().filter(item -> item.getId() == id).findFirst().orElseThrow(() -> new Exception("Itme not found"));
    }

    public AssemblyPlan generateAssemblyPlan() {
        return STOCK_ITEMS.get(RandomUtils.nextInt(0, STOCK_ITEMS.size()));
    }


}
