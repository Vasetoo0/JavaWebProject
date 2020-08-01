package softuni.javaweb.springproject.statistics.service.impl;

import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.statistics.service.StatsService;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatsServiceImpl implements StatsService {


    private AtomicInteger offerAddAttemptCount = new AtomicInteger(0);
    private AtomicInteger storiesReadCount = new AtomicInteger(0);
    private Instant offerAddStartedOn;
    private Instant storyReadStartedOn;


    @Override
    public void incOfferAddAttemptCount() {
        this.offerAddAttemptCount.incrementAndGet();
    }

    @Override
    public void incStoriesReadCount() {
        this.storiesReadCount.incrementAndGet();
    }

    @Override
    public int getOfferAddAttemptCount() {
        return this.offerAddAttemptCount.get();
    }

    @Override
    public int getStoryReadAttemptCount() {
        return this.storiesReadCount.get();
    }

    @Override
    public Instant offerAddStartedOn() {
        return this.offerAddStartedOn;
    }

    @Override
    public Instant storyReadStartedOn() {
        return this.storyReadStartedOn;
    }

    @Override
    public void setOfferAddStartedOn(Instant startedOn) {
        this.offerAddStartedOn = startedOn;
    }

    @Override
    public void setStoryReadStartedOn(Instant startedOn) {
        this.storyReadStartedOn = startedOn;
    }
}
