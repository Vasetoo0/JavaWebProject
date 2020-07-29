package softuni.javaweb.springproject.offer.service;

import softuni.javaweb.springproject.offer.model.binding.OfferAddBindingModel;
import softuni.javaweb.springproject.offer.model.service.OfferServiceModel;
import softuni.javaweb.springproject.offer.model.view.AllOfferViewModel;
import softuni.javaweb.springproject.offer.model.view.UnApprovedOfferViewModel;

import java.util.List;

public interface OfferService {

    OfferServiceModel addOffer(OfferAddBindingModel offerAddBindingModel);

    List<AllOfferViewModel> getByCreator(String name);

    List<UnApprovedOfferViewModel> getUnApproved();

    void approveOffer(String id);
}
