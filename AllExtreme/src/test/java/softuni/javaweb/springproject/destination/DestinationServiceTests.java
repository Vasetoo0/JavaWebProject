package softuni.javaweb.springproject.destination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import softuni.javaweb.springproject.destination.model.binding.DestinationAddBindingModel;
import softuni.javaweb.springproject.destination.model.entity.Destination;
import softuni.javaweb.springproject.destination.model.service.DestinationServiceModel;
import softuni.javaweb.springproject.destination.model.view.DestinationViewModel;
import softuni.javaweb.springproject.destination.repository.DestinationRepository;
import softuni.javaweb.springproject.destination.service.DestinationService;
import softuni.javaweb.springproject.destination.service.impl.DestinationServiceImpl;
import softuni.javaweb.springproject.enums.Sport;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DestinationServiceTests {

    private DestinationService serviceToTest;

    @Mock
    DestinationRepository mockDestinationRepository;

    @BeforeEach
    public void setUp() {
        serviceToTest = new DestinationServiceImpl(new ModelMapper(),mockDestinationRepository);
    }

    @Test
    public void testGetDestinationsCount(){
        when(mockDestinationRepository.count()).thenReturn(10L);

        Assertions.assertEquals(10,serviceToTest.getDestinationsCount());
    }

    @Test
    public void testGetAllBySport() {
        Destination destination = new Destination();
        destination.setTitle("Test");
        destination.setSport(Sport.CLIMBING);

        Destination destination1 = new Destination();
        destination1.setTitle("Test1");
        destination1.setSport(Sport.SNOWBOARDING);

        when(mockDestinationRepository.findAll()).thenReturn(List.of(destination1,destination));

        List<DestinationViewModel> returned = serviceToTest.getAllBySport("CLIMBING");

        Assertions.assertEquals(1,returned.size());
        Assertions.assertEquals(destination.getTitle(),returned.get(0).getTitle());
    }

    @Test
    public void testAddDestination(){
        DestinationAddBindingModel destinationAddBindingModel = new DestinationAddBindingModel();
        destinationAddBindingModel.setTitle("Test");

        Destination savedDest = new Destination();
        savedDest.setTitle("Test");

        when(mockDestinationRepository.save(any(Destination.class))).thenReturn(savedDest);

        DestinationServiceModel returned = serviceToTest.addDestination(destinationAddBindingModel);

        Assertions.assertSame(DestinationServiceModel.class,returned.getClass());
        Assertions.assertEquals(savedDest.getTitle(),returned.getTitle());
    }
}
