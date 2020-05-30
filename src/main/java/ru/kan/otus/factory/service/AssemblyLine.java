package ru.kan.otus.factory.service;

import org.springframework.stereotype.Service;
import ru.kan.otus.factory.domain.Car;

import java.util.Objects;

@Service
public class AssemblyLine {

    public Car process(String item) {
        if (Objects.nonNull(item))
            return Car.builder().name(item).build();
        else
            return null;
    }

}
