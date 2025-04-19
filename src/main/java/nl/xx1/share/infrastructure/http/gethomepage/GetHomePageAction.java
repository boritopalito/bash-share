package nl.xx1.share.infrastructure.http.gethomepage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class GetHomePageAction {
    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }
}
