package spittr.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.Spittle;
import spittr.data.SpittleRepository;

/**
 * @author Yuriy
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private final SpittleRepository spittleRepository;

    public static final String MAX_LONG_AS_STRING = "9223372036854775807";

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    //@RequestMapping(method=RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(
            Model model,
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {

        model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    @RequestMapping(value="/{spittleId}", method = RequestMethod.GET)
    public String spittle(Model model, @PathVariable long spittleId) {
        model.addAttribute("spittle", spittleRepository.findOne(spittleId));
        return "spittle";
    }

}
