package spittr.web;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        model.addAttribute("registerForm", new RegisterForm());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, 
                                      @Valid RegisterForm form, 
                                      Errors errors,
                                      RedirectAttributes model) throws IOException {

        log.info("profilePicture: \n{}\n{}", profilePicture.getOriginalFilename(), profilePicture.getSize());
        if(!profilePicture.isEmpty()){
            profilePicture.transferTo(new File(profilePicture.getOriginalFilename()));
        }
        
        if (errors.hasErrors()) {
            return "registerForm";
        } else {
            Spitter spitter = form.toSpitter();
            spitterRepository.save(spitter);
            model.addAttribute("username", form.getUsername());
            model.addFlashAttribute("spitter", spitter);
            return "redirect:/spitter/{username}";
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showProfilePage(@PathVariable String username, Model model) {
        
        if(!model.containsAttribute("spitter")){
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute("spitter", spitter);
        }

        return "profile";
    }
}
