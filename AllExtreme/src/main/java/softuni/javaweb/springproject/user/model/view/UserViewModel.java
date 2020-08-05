package softuni.javaweb.springproject.user.model.view;

import softuni.javaweb.springproject.offer.model.entity.Offer;
import softuni.javaweb.springproject.user.model.entity.Role;

import java.util.Set;

public class UserViewModel {
    private String username;
    private String email;
//    private Set<Role> authorities;
//    private Set<Offer> myOffers;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<Role> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Role> authorities) {
//        this.authorities = authorities;
//    }
//
//    public Set<Offer> getMyOffers() {
//        return myOffers;
//    }
//
//    public void setMyOffers(Set<Offer> myOffers) {
//        this.myOffers = myOffers;
//    }
}
