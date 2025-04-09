package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> CONTROLLER_MAP = new HashMap<>();

    static {
        CONTROLLER_MAP.put("/", new HomeController());
        CONTROLLER_MAP.put("/user/signup", new CreateUserController());
        CONTROLLER_MAP.put("/user/login", new LoginController());
        CONTROLLER_MAP.put("/user/logout", new LogoutController());
        CONTROLLER_MAP.put("/user/userList", new ListUserController());
        CONTROLLER_MAP.put("/user/update", new UpdateUserFormController());
    }

    public Controller getController(String path){
        return CONTROLLER_MAP.get(path);
    }

}
