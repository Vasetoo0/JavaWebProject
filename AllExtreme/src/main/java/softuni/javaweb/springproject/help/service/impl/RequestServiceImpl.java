package softuni.javaweb.springproject.help.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.help.model.binding.RequestAddBindingModel;
import softuni.javaweb.springproject.help.model.entity.Request;
import softuni.javaweb.springproject.help.model.service.RequestServiceModel;
import softuni.javaweb.springproject.help.model.view.RequestViewModel;
import softuni.javaweb.springproject.help.repository.RequestRepository;
import softuni.javaweb.springproject.help.service.RequestService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private final ModelMapper modelMapper;
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(ModelMapper modelMapper, RequestRepository requestRepository) {
        this.modelMapper = modelMapper;
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestServiceModel addRequest(RequestAddBindingModel requestAddBindingModel) {

        Request savedRequest = this.requestRepository
                .save(this.modelMapper.map(requestAddBindingModel, Request.class));

        return this.modelMapper.map(savedRequest,RequestServiceModel.class);
    }

    @Override
    public List<RequestViewModel> getRequests() {

        return this.requestRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r,RequestViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRequest(String requestId) {
        this.requestRepository.deleteById(requestId);
    }
}
