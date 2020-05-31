package ru.kan.otus.factory.service;

import org.springframework.stereotype.Service;
import ru.kan.otus.factory.domain.AssemblyPlan;
import ru.kan.otus.factory.domain.Car;

@Service
public class AssemblyLine {

    public Car process(AssemblyPlan item) throws InterruptedException {
        Thread.sleep(2000);

        if (item.getId() == 2)
            throw new NullPointerException("поломка");

        return Car.builder().name(item.getName()).build();
    }
}
