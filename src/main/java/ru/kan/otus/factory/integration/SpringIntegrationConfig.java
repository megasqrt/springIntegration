package ru.kan.otus.factory.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandlingException;

import java.util.concurrent.Executors;

@Configuration
public class SpringIntegrationConfig {

    @Bean
    public MessageChannel ordersChannel() {
        return MessageChannels.queue(100).get();
    }

    @Bean
    public PublishSubscribeChannel partsChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel aggregatorChannel() {
        return MessageChannels.publishSubscribe().get();
    }


    @Bean
    public IntegrationFlow errorFlow() {
        return IntegrationFlows
                .from("errorChannel")
                .route(exceptionTypeRouter())
                .get();
    }

    private ErrorMessageExceptionTypeRouter exceptionTypeRouter() {
        ErrorMessageExceptionTypeRouter router = new ErrorMessageExceptionTypeRouter();
        router.setChannelMapping(RuntimeException.class.getName(), "runtimeExceptionChannel");
        router.setChannelMapping(MessageHandlingException.class.getName(), "messageHandlingExceptionChannel");
        return router;
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public IntegrationFlow factoryFlow() {
        return IntegrationFlows.from("ordersChannel")

                .split()
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .gateway(g -> g.channel(c -> c.executor(Executors.newCachedThreadPool()))
                        .handle("assemblyLine", "process")
                )
                .aggregate()
                .channel("partsChannel")
                .get();
    }
}
