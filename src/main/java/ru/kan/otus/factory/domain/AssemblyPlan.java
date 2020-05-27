package ru.kan.otus.factory.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AssemblyPlan {

    private Integer id;
    private String name;
}
