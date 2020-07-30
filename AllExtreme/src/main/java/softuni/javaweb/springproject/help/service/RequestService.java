package softuni.javaweb.springproject.help.service;

import softuni.javaweb.springproject.help.model.binding.RequestAddBindingModel;
import softuni.javaweb.springproject.help.model.service.RequestServiceModel;
import softuni.javaweb.springproject.help.model.view.RequestViewModel;

import java.util.List;

public interface RequestService {

    RequestServiceModel addRequest(RequestAddBindingModel requestAddBindingModel);

    List<RequestViewModel> getRequests();

    void deleteRequest(String requestId);
}
