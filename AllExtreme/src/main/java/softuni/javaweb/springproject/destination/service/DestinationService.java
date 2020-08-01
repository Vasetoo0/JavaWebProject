package softuni.javaweb.springproject.destination.service;

import softuni.javaweb.springproject.destination.model.binding.DestinationAddBindingModel;
import softuni.javaweb.springproject.destination.model.service.DestinationServiceModel;
import softuni.javaweb.springproject.destination.model.view.DestinationViewModel;

import java.util.List;

public interface DestinationService {

    DestinationServiceModel addDestination(DestinationAddBindingModel destinationAddBindingModel);

    List<DestinationViewModel> getAllBySport(String sport);

    Long getDestinationsCount();
}
