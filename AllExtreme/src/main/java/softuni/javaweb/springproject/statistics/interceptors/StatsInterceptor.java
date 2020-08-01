package softuni.javaweb.springproject.statistics.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import softuni.javaweb.springproject.statistics.service.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
public class StatsInterceptor implements HandlerInterceptor {

    private final StatsService statsService;

    public StatsInterceptor(StatsService statsService) {
        this.statsService = statsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(request.getRequestURL().toString().contains("read")) {
            this.statsService.incStoriesReadCount();
            this.statsService.setStoryReadStartedOn(Instant.now());

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(request.getRequestURL().toString().contains("addOffer")) {
            this.statsService.incOfferAddAttemptCount();
            this.statsService.setOfferAddStartedOn(Instant.now());
        }


    }
}
