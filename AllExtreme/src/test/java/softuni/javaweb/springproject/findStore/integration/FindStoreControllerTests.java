package softuni.javaweb.springproject.findStore.integration;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.javaweb.springproject.findStore.model.view.StoreViewModel;
import softuni.javaweb.springproject.findStore.service.FindStoreService;
import softuni.javaweb.springproject.findStore.web.FindStoreController;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FindStoreControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FindStoreService mockFindStoreService;

    @Test
    @WithMockUser
    public void testFindStore() throws Exception {
//        StoreViewModel storeViewModel = new StoreViewModel();

        when(mockFindStoreService.getStoresBySport("CLIMBING")).thenReturn(List.of(new StoreViewModel()));

        mockMvc.perform(get("/CLIMBING/findStore"))
                .andExpect(view().name("stores/find-store"))
                .andExpect(model().attributeExists("stores"))
                .andExpect(status().isOk());
    }
}
