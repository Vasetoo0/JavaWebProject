package softuni.javaweb.springproject.findStore.service;

import softuni.javaweb.springproject.findStore.model.binding.StoreAddBindingModel;
import softuni.javaweb.springproject.findStore.model.service.StoreServiceModel;
import softuni.javaweb.springproject.findStore.model.view.StoreViewModel;

import java.util.List;

public interface FindStoreService {

    StoreServiceModel addStore(StoreAddBindingModel storeAddBindingModel);

    List<StoreViewModel> getStoresBySport(String sport);

    Long getStoresCount();
}
