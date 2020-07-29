package softuni.javaweb.springproject.offer.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.javaweb.springproject.offer.model.binding.OfferAddBindingModel;
import softuni.javaweb.springproject.offer.model.entity.Offer;
import softuni.javaweb.springproject.offer.model.service.OfferServiceModel;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.model.view.UnApprovedOfferViewModel;
import softuni.javaweb.springproject.offer.repository.OfferRepository;
import softuni.javaweb.springproject.offer.service.OfferService;
import softuni.javaweb.springproject.user.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final UserService userService;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(UserService userService, OfferRepository offerRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public OfferServiceModel addOffer(OfferAddBindingModel offerAddBindingModel) {
        OfferServiceModel offerServiceModel = this.modelMapper.map(
                offerAddBindingModel, OfferServiceModel.class
        );

        Offer newOffer = this.modelMapper.map(
                offerServiceModel, Offer.class
        );

        newOffer.setCreator(this.userService.getByUsername(offerServiceModel.getCreator()));


        return this.modelMapper.map(this.offerRepository.saveAndFlush(newOffer), OfferServiceModel.class);
    }

    @Override
    public List<AllOfferViewModel> getByCreator(String name) {

        return this.offerRepository.findAll()
                .stream()
                .filter(Offer::isEnabled)
                .filter(o -> o.getCreator().getUsername().equals(name))
                .map(o -> this.modelMapper.map(o,AllOfferViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UnApprovedOfferViewModel> getUnApproved() {

        return this.offerRepository.findAll()
                .stream()
                .filter(o -> !o.isEnabled())
                .map(o -> this.modelMapper.map(o, UnApprovedOfferViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void approveOffer(String id) {

        Offer offerToApprove = this.offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such offer to approve!"));

        offerToApprove.setEnabled(true);

        this.offerRepository.saveAndFlush(offerToApprove);
    }
}
