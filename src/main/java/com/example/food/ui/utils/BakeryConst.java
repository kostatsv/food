package com.example.food.ui.utils;

import java.util.Locale;

import org.springframework.data.domain.Sort;

public class BakeryConst {

	public static final Locale APP_LOCALE = Locale.US;

	public static final String PAGE_ROOT = "";
	public static final String PAGE_RECEIPTS = "receipts";
	public static final String PAGE_STOREFRONT_EDIT = "storefront/edit";
	public static final String PAGE_DASHBOARD = "dashboard";
	public static final String PAGE_USERS = "users";
	public static final String PAGE_PRODUCTS = "products";
	public static final String PAGE_LOGOUT = "logout";
	public static final String PAGE_LOGIN = "login";
	public static final String PAGE_DEFAULT = PAGE_RECEIPTS;

	public static final String ICON_STOREFRONT = "edit";
	public static final String ICON_DASHBOARD = "clock";
	public static final String ICON_USERS = "user";
	public static final String ICON_PRODUCTS = "calendar";
	public static final String ICON_LOGOUT = "arrow-right";
	public static final String ICON_RECEIPTS = "calendar";

	public static final String TITLE_STOREFRONT = "Food App";
	public static final String TITLE_DASHBOARD = "Dashboard";
	public static final String TITLE_USERS = "Users";
	public static final String TITLE_PRODUCTS = "Products";
	public static final String TITLE_LOGOUT = "Logout";
	public static final String TITLE_NOT_FOUND = "Page was not found";
	public static final String TITLE_ACCESS_DENIED = "Access denied";
	public static final String TITLE_RECEIPTS = "Receipts";

	public static final String[] ORDER_SORT_FIELDS = {"dueDate", "dueTime", "id"};
	public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;

	public static final String VIEWPORT = "width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes";

	// Mutable for testing.
	public static int NOTIFICATION_DURATION = 4000;

}
