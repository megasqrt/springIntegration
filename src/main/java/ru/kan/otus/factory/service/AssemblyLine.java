package ru.kan.otus.factory.service;

import org.springframework.stereotype.Service;
import ru.kan.otus.factory.domain.AssemblyPlan;
import ru.kan.otus.factory.domain.Car;

@Service
public class AssemblyLine {

    public Car process(AssemblyPlan stockItem) {
        return Car.builder().name(stockItem.getName()).build();
    }

}
