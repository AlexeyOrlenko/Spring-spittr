package spittr.onfig;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import spittr.Spitter;
import spittr.Spittle;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

/** @author yvkuzmen */
@Configuration
@ComponentScan(basePackages = "spittr", 
        excludeFilters = {
            @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
        }
)
public class RootConfig {
    
    @Bean
    public SpittleRepository spittleRepository(){
        return new SpittleRepository() {
            @Override
            public List<Spittle> findSpittles(long max, int count) {
                return Stream.iterate(1, i->i+1).limit(5).map(i-> new Spittle("Spittle " + i, new Date())).collect(Collectors.toList());
            }

            @Override
            public Spittle findOne(long id) {
                return new Spittle("Hello " + id, new Date());
            }
        };
    }
    
    @Bean
    public SpitterRepository spitterRepository(){
        return new SpitterRepository() {
            @Override
            public Spitter save(Spitter spitter) {
                return spitter;
            }

            @Override
            public Spitter findByUsername(String username) {
                return new Spitter(username, null, null, null, null);
            }
        };
    }
}
