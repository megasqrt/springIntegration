package ru.kan.otus.factory.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.kan.otus.factory.service.ManufactureService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommand {

    private final ManufactureService orderService;

    @ShellMethod(value = "Запуск сборки", key = {"dash", "d", "s", "start"})
    public void order() throws InterruptedException {
        orderService.startFactory();
    }

    @ShellMethod(value = "Запуск ручного заказа", key = {"hand", "h"})
    public void handOrder() {
        orderService.processOrder();
    }
}
