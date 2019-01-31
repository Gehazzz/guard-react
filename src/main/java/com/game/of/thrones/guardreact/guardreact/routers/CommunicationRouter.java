package com.game.of.thrones.guardreact.guardreact.routers;

import com.game.of.thrones.guardreact.guardreact.decider.GuardDecider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class CommunicationRouter {

    @Bean
    public RouterFunction<ServerResponse> updateLetterStatus(GuardDecider decider){
        return RouterFunctions.route(POST("/guard").and(contentType(APPLICATION_JSON)), decider::decide);
    }
}
