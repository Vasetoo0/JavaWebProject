package softuni.javaweb.springproject.statistics.userSearchAjax.model;

import softuni.javaweb.springproject.user.model.view.UserViewModel;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<UserViewModel> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserViewModel> getResult() {
        return result;
    }

    public void setResult(List<UserViewModel> result) {
        this.result = result;
    }

}
