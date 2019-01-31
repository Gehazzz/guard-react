package com.game.of.thrones.guardreact.guardreact.decider;

import com.game.of.thrones.guardreact.guardreact.model.Notification;
import com.game.of.thrones.guardreact.guardreact.notifier.Notifier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class GuardDecider {

    private Notifier notifier;

    public Mono<ServerResponse> decide(ServerRequest serverRequest) {
        Mono<Void> result = serverRequest.bodyToMono(Notification.class)
                .doOnNext(notification -> {
                    log.info("Received from decoder: " + notification);
                    String message = "Author of the letter with id:" + notification.getLetterId() + " is " + getDecision();
                    notification.setMessage(message);
                    notifier.sendNotification(notification);
                }).then();
        return ServerResponse.ok().build(result);
    }

    private String getDecision(){
        int decision = (int) ((Math.random() * (2)) + 1);
        log.info(String.valueOf(decision));
        if( decision == 1){
            return "dangerous, We send a squad of guards to you......hold him!!!";
        }else {
            return "not dangerous. But you can kill him just because it is Game of Thrones!";
        }
    }
}
