package softuni.javaweb.springproject.findStore.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.findStore.model.binding.StoreAddBindingModel;
import softuni.javaweb.springproject.findStore.model.entity.Store;
import softuni.javaweb.springproject.findStore.model.service.StoreServiceModel;
import softuni.javaweb.springproject.findStore.model.view.StoreViewModel;
import softuni.javaweb.springproject.findStore.repository.FindStoreRepository;
import softuni.javaweb.springproject.findStore.service.FindStoreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindStoreServiceImpl implements FindStoreService {

    private final FindStoreRepository findStoreRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FindStoreServiceImpl(FindStoreRepository findStoreRepository, ModelMapper modelMapper) {
        this.findStoreRepository = findStoreRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public StoreServiceModel addStore(StoreAddBindingModel storeAddBindingModel) {


        Store savedStore = this.findStoreRepository.save(
                this.modelMapper.map(storeAddBindingModel,Store.class)
        );

        return this.modelMapper.map(savedStore,StoreServiceModel.class);
    }

    @Override
    public List<StoreViewModel> getStoresBySport(String sport) {

        return this.findStoreRepository.findAll()
                .stream()
                .filter(s -> s.getSport().name().equals(sport))
                .map(s -> this.modelMapper.map(s,StoreViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long getStoresCount() {
        return this.findStoreRepository.count();
    }
}
