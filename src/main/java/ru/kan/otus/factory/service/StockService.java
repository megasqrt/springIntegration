package ru.kan.otus.factory.service;

import org.springframework.stereotype.Service;
import ru.kan.otus.factory.domain.AssemblyPlan;

import java.util.Arrays;
import java.util.List;

@Service
public class StockService {

    private static final List<AssemblyPlan> STOCK_ITEMS = Arrays.asList(
            AssemblyPlan.builder().id(1).name("Дверь левая").build(),
            AssemblyPlan.builder().id(2).name("Мотор").build(),
            AssemblyPlan.builder().id(3).name("Стекло лобовое").build(),
            AssemblyPlan.builder().id(4).name("Глушитель").build(),
            AssemblyPlan.builder().id(5).name("Сиденье вадителя").build(),
            AssemblyPlan.builder().id(6).name("Бензобак").build(),
            AssemblyPlan.builder().id(7).name("Зеркало заднего вида").build(),
            AssemblyPlan.builder().id(8).name("Руль").build(),
            AssemblyPlan.builder().id(9).name("Капот").build(),
            AssemblyPlan.builder().id(10).name("Педаль тормоза").build());

    public List<AssemblyPlan> getDetailsInStock() {
        return STOCK_ITEMS;
    }

    public AssemblyPlan findById(Integer id) throws Exception {
        return STOCK_ITEMS.stream().filter(item -> item.getId().equals(id)).findFirst().orElseThrow(() -> new Exception("Itme not found"));
    }
}
