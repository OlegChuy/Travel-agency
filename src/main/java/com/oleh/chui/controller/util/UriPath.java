package com.oleh.chui.controller.util;

public class UriPath {

    private UriPath() {}

    public static final String REDIRECT = "redirect:";

    public static final String LOGIN = "/login";
    public static final String REGISTRATION = "/registration";
    public static final String LOGOUT = "/logout";
    public static final String CATALOG = "/catalog";
    public static final String TOUR_DETAILS = "/tour/details";

    public static final String USER_ACCOUNT = "/account";
    public static final String USER_BUY_TOUR = "/tour/buy";

    public static final String MANAGER_USER_PAGE = "/user";
    public static final String MANAGER_CHANGE_ORDER_STATUS = "/order/changeStatus";
    public static final String MANAGER_CHANGE_TOUR_BURNING_STATE = "/tour/changeBurningState";
    public static final String MANAGER_CHANGE_DISCOUNT = "/tour/changeDiscount";

    public static final String ADMIN_CREATE_TOUR = "/tour/create";
    public static final String ADMIN_UPDATE_TOUR = "/tour/update";
    public static final String ADMIN_DELETE_TOUR = "/tour/delete";
    public static final String ADMIN_USERS = "/users";

}
