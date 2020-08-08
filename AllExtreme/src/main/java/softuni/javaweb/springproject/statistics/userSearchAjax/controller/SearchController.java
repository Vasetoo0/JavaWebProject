package softuni.javaweb.springproject.statistics.userSearchAjax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softuni.javaweb.springproject.statistics.userSearchAjax.model.AjaxResponseBody;
import softuni.javaweb.springproject.statistics.userSearchAjax.model.SearchCriteria;
import softuni.javaweb.springproject.user.model.view.UserViewModel;
import softuni.javaweb.springproject.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/statistics/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, BindingResult errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        List<UserViewModel> users = userService.findByUserName(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);

    }

}
