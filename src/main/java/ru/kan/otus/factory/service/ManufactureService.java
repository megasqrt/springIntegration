package ru.kan.otus.factory.service;

import org.jline.reader.LineReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.kan.otus.factory.domain.AssemblyPlan;
import ru.kan.otus.factory.domain.Car;
import ru.kan.otus.factory.integration.RobotPicker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ManufactureService {

    private final StockService stockService;
    private final RobotPicker gateway;
    private LineReader reader;

    public ManufactureService(StockService stockService, RobotPicker gateway, @Lazy LineReader reader) {
        this.stockService = stockService;
        this.gateway = gateway;
        this.reader = reader;
    }

    public List<Car> processOrder() {
        System.out.println("Закажите запчасти со склада: ");
        stockService.getDetailsInStock()
                .stream()
                .forEach(item -> System.out.println(new StringBuilder().append("| ")
                        .append(item.getId())
                        .append(" | ")
                        .append(item.getName())
                ));
        String orderedParts = reader
                .readLine("Введите номер необходимых деталей, через запятую: ");

        List<AssemblyPlan> items = Arrays.asList(orderedParts.split(","))
                .stream()
                .map(s -> stockService.findById(Integer.valueOf(s)))
                .filter(s -> s != null)
                .collect(Collectors.toList());

        List<Car> part = gateway.processOrder(items);

        System.out.println(part.size() > 1 ? "Необходимые детали доставлены:" : "Необходимая деталь доставлена:");
        part.forEach(System.out::println);
        System.out.println("И установлены на автомобиль");
        return part;
    }
}
