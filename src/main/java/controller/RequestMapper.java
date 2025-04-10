package controller;

import controller.controllers.*;

import java.util.HashMap;
import java.util.Map;

import static controller.enums.URL.*;

public class RequestMapper {
    private static final Map<String, Controller> CONTROLLER_MAP = new HashMap<>();

    static {
        CONTROLLER_MAP.put(HOME.getUrl(), new HomeController());
        CONTROLLER_MAP.put(REGISTER.getUrl(), new CreateUserController());
        CONTROLLER_MAP.put(LOGIN.getUrl(), new LoginController());
        CONTROLLER_MAP.put(LOGOUT.getUrl(), new LogoutController());
        CONTROLLER_MAP.put(USER_LIST.getUrl(), new ListUserController());
        CONTROLLER_MAP.put(UPDATE.getUrl(), new UpdateUserFormController());
    }

    public Controller getController(String path){
        return CONTROLLER_MAP.get(path);
    }

}
