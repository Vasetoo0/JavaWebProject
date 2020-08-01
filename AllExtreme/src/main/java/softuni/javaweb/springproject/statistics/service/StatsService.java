package softuni.javaweb.springproject.statistics.service;

import java.time.Instant;

public interface StatsService {

    void incOfferAddAttemptCount();

    void incStoriesReadCount();

    int getOfferAddAttemptCount();

    int getStoryReadAttemptCount();

    Instant offerAddStartedOn();

    Instant storyReadStartedOn();

    void setStoryReadStartedOn(Instant startedOn);

    void setOfferAddStartedOn(Instant instant);
}
