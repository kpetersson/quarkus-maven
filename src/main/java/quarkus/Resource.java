package quarkus;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.Objects;

@Slf4j
@Path("")
public class Resource {

    @GET
    @Path("/nodelay")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> noDelay() {
        return Flux.range(1, 10)
            .map(Objects::toString)
            .doOnSubscribe(subscription -> log.info("Got request on /nodelay"))
            .doOnComplete(() -> log.info("Completed /nodelay"))
            .doOnNext(s -> log.info("Sending {}", s))
            .doOnError(throwable -> log.error("Error /nodelay", throwable));
    }

    @GET
    @Path("/delay")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> delay() {
        return Flux.range(0, 10)
            .map(Objects::toString)
            .delayElements(Duration.ofSeconds(1))
            .doOnSubscribe(subscription -> log.info("Got request on /delay"))
            .doOnComplete(() -> log.info("Completed /delay"))
            .doOnNext(s -> log.info("Sending {}", s))
            .doOnError(throwable -> log.error("Error /delay", throwable));
    }
}