package spittr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.servlet.view.InternalResourceView;


/** @author Yuriy */
public class SpittleControllerTest {

    @Test
    public void shoudShowRecentSpittles() throws Exception{
        List<Spittle> expectedSpittles = createSpittleList(20);
        
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);        
        Mockito.when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
        
        SpittleController controller = new SpittleController(mockRepository);
        
        MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
        mockMvc.perform(get("/spittles"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", CoreMatchers.hasItems(expectedSpittles.toArray())));
    }
    
    @Test
    public void shouldShowPagedSpittles() throws Exception{
        List<Spittle> expectedSpittles = createSpittleList(50);
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        
        Mockito.when(mockRepository.findSpittles(238900, 50))
                .thenReturn(expectedSpittles);
        
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();
        mockMvc.perform(get("/spittles?max=238900&count=50"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", CoreMatchers.hasItems(expectedSpittles.toArray())));
    }
    
    @Test
    public void testSpittle() throws Exception{
        Spittle expectedSpittle = new Spittle("Hello", new Date());
        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
        
        Mockito.when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
        
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/spittles/12345"))
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", expectedSpittle));
    }
    
    private List<Spittle> createSpittleList(int count){
        return Stream.iterate(1, i -> i + 1).limit(count).map(i -> new Spittle("Spittle "+ i, new Date())).collect(Collectors.toList());
    }
}
