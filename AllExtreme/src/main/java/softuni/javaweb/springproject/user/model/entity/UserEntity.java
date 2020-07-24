package softuni.javaweb.springproject.user.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.offer.model.Offer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Set<Role> authorities;
    private Set<Offer> myOffers;
    private Set<String> wishList = new HashSet<>();
    private Set<Comment> comments;

    public UserEntity() {
    }

    @Column(nullable = false,unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @OneToMany(mappedBy = "userEntity")
    public Set<Offer> getMyOffers() {
        return myOffers;
    }

    public void setMyOffers(Set<Offer> myOffers) {
        this.myOffers = myOffers;
    }

    @ElementCollection
    public Set<String> getWishList() {
        return wishList;
    }

    public void setWishList(Set<String> wishList) {
        this.wishList = wishList;
    }

    @OneToMany(mappedBy = "userEntity")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


}