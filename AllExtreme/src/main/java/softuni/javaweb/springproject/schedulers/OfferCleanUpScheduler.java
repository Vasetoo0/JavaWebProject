package softuni.javaweb.springproject.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.javaweb.springproject.offer.service.OfferService;

@Component
public class OfferCleanUpScheduler {

    private final OfferService offerService;

    @Autowired
    public OfferCleanUpScheduler(OfferService offerService) {
        this.offerService = offerService;
    }

    //cleans up old announcements.
    @Scheduled(cron = "${allExtreme.clean-up}")
    public void cleanUpOldAnnouncements() {
        this.offerService.cleanUpOldOffer();
    }
}
