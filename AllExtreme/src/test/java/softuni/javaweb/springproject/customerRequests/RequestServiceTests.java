package softuni.javaweb.springproject.customerRequests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import softuni.javaweb.springproject.help.model.binding.RequestAddBindingModel;
import softuni.javaweb.springproject.help.model.entity.Request;
import softuni.javaweb.springproject.help.model.service.RequestServiceModel;
import softuni.javaweb.springproject.help.model.view.RequestViewModel;
import softuni.javaweb.springproject.help.repository.RequestRepository;
import softuni.javaweb.springproject.help.service.RequestService;
import softuni.javaweb.springproject.help.service.impl.RequestServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RequestServiceTests {

    private RequestService serviceToTest;

    @Mock
    RequestRepository mockRequestRepository;

    @BeforeEach
    public void setUp(){
        serviceToTest = new RequestServiceImpl(new ModelMapper(),mockRequestRepository);
    }

    @Test
    public void testGetAll(){

        when(mockRequestRepository.findAll()).thenReturn(List.of(new Request()));

        List<RequestViewModel> returned = serviceToTest.getRequests();

        Assertions.assertEquals(1,returned.size());

    }

    @Test
    public void testAddRequest() {
        RequestAddBindingModel requestAddBindingModel = new RequestAddBindingModel();
        requestAddBindingModel.setName("Test");

        Request savedRequest = new Request();
        savedRequest.setName("Test");

        when(mockRequestRepository.save(any(Request.class))).thenReturn(savedRequest);

        RequestServiceModel returned = serviceToTest.addRequest(requestAddBindingModel);

        Assertions.assertSame(returned.getClass(),RequestServiceModel.class);
        Assertions.assertEquals(returned.getName(),savedRequest.getName());
    }
}
