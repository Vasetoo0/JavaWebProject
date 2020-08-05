package softuni.javaweb.springproject.findStore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.findStore.model.binding.StoreAddBindingModel;
import softuni.javaweb.springproject.findStore.model.entity.Store;
import softuni.javaweb.springproject.findStore.model.service.StoreServiceModel;
import softuni.javaweb.springproject.findStore.model.view.StoreViewModel;
import softuni.javaweb.springproject.findStore.repository.FindStoreRepository;
import softuni.javaweb.springproject.findStore.service.FindStoreService;
import softuni.javaweb.springproject.findStore.service.impl.FindStoreServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindStoreServiceTests {

    private FindStoreService serviceToTest;

    @Mock
    FindStoreRepository mockFindStoreRepository;

    @BeforeEach
    public void setUp(){
        serviceToTest = new FindStoreServiceImpl(mockFindStoreRepository,new ModelMapper());
    }

    @Test
    public void testGetStoresCount(){
        when(mockFindStoreRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10,serviceToTest.getStoresCount());
    }

    @Test
    public void testGetStoreBySport() {
        Store store = new Store();
        store.setName("Test");
        store.setSport(Sport.CLIMBING);
        Store store1 = new Store();
        store1.setName("Test1");
        store1.setSport(Sport.SNOWBOARDING);

        when(mockFindStoreRepository.findAll()).thenReturn(List.of(store,store1));

        List<StoreViewModel> returnedStores = serviceToTest.getStoresBySport("CLIMBING");

        Assertions.assertEquals(1,returnedStores.size());
        Assertions.assertEquals(store.getName(),returnedStores.get(0).getName());

    }

    @Test
    public void testAddStore(){
        StoreAddBindingModel storeAddBindingModel = new StoreAddBindingModel();
        storeAddBindingModel.setName("Test");

        Store savedStore = new Store();
        savedStore.setName("Test");

        when(mockFindStoreRepository.save(any(Store.class))).thenReturn(savedStore);

        StoreServiceModel storeServiceModel = serviceToTest.addStore(storeAddBindingModel);

        Assertions.assertSame(storeServiceModel.getClass(),StoreServiceModel.class);
        Assertions.assertEquals(storeServiceModel.getName(),savedStore.getName());
    }
}
