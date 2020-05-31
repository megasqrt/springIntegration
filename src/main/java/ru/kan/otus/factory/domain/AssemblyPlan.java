package ru.kan.otus.factory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class AssemblyPlan {

    private int id;
    private String name;
    private List<String> itemList = new ArrayList<>();

    public void addItem(String name) {
        this.itemList.add(name);
    }


    public AssemblyPlan(int i) {
        this.id = i;
    }
}
