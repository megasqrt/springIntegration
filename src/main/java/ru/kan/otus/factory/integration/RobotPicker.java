package ru.kan.otus.factory.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.kan.otus.factory.domain.AssemblyPlan;
import ru.kan.otus.factory.domain.Car;

import java.util.List;

@MessagingGateway
public interface RobotPicker {

    @Gateway(requestChannel = "ordersChannel", replyChannel = "partsChannel")
    List<Car> processOrder(List<AssemblyPlan> items);
}
