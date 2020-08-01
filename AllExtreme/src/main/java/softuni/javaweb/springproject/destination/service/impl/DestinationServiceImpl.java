package softuni.javaweb.springproject.destination.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.destination.model.binding.DestinationAddBindingModel;
import softuni.javaweb.springproject.destination.model.entity.Destination;
import softuni.javaweb.springproject.destination.model.service.DestinationServiceModel;
import softuni.javaweb.springproject.destination.model.view.DestinationViewModel;
import softuni.javaweb.springproject.destination.repository.DestinationRepository;
import softuni.javaweb.springproject.destination.service.DestinationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final ModelMapper modelMapper;
    private final DestinationRepository destinationRepository;


    @Autowired
    public DestinationServiceImpl(ModelMapper modelMapper, DestinationRepository destinationRepository) {
        this.modelMapper = modelMapper;
        this.destinationRepository = destinationRepository;
    }


    @Override
    public DestinationServiceModel addDestination(DestinationAddBindingModel destinationAddBindingModel) {

        DestinationServiceModel destinationServiceModel = this.modelMapper.map(
                destinationAddBindingModel,DestinationServiceModel.class
        );

        Destination savedDestination = this.destinationRepository.save(
                this.modelMapper.map(destinationServiceModel,Destination.class)
        );


        return this.modelMapper.map(savedDestination,DestinationServiceModel.class);
    }

    @Override
    public List<DestinationViewModel> getAllBySport(String sport) {

        return this.destinationRepository.findAll()
                .stream()
                .filter(d -> d.getSport().name().equals(sport))
                .map(d -> this.modelMapper.map(d,DestinationViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long getDestinationsCount() {

        return this.destinationRepository.count();
    }
}
