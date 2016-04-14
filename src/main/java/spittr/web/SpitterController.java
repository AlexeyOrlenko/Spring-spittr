package spittr.web;

import javax.servlet.http.Part;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import spittr.Spitter;
import spittr.data.SpitterRepository;

/**
 * @author Yuriy
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private static Logger log = LoggerFactory.getLogger(SpittleController.class);
    
    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("spitter", new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart("profilePicture") Part profilePicture, 
                                      @Valid Spitter spitter, 
                                      Errors errors) {

        log.info("profilePicture: \n{}\n{}", profilePicture.getSubmittedFileName(), profilePicture.getSize());
        if (errors.hasErrors()) {
            return "registerForm";
        } else {
            spitterRepository.save(spitter);
            return "redirect:/spitter/" + spitter.getUsername();
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showProfilePage(@PathVariable String username, Model model) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute("spitter", spitter);

        return "profile";
    }
}
