package ru.kan.otus.factory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    String name;
    int orderNumber;
}
