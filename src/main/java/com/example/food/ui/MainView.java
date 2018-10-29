package com.example.food.ui;

import com.example.food.ui.components.AppNavigation;
import com.example.food.ui.components.PageInfo;
import com.example.food.ui.views.admin.users.UsersView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.food.app.security.SecurityUtils;
import com.example.food.ui.exceptions.AccessDeniedException;
import com.example.food.ui.utils.BakeryConst;
import com.example.food.ui.views.HasConfirmation;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.food.ui.utils.BakeryConst.*;

@Tag("main-view")
@HtmlImport("src/main-view.html")

@PageTitle("Food App")
@Viewport(BakeryConst.VIEWPORT)
public class MainView extends PolymerTemplate<TemplateModel>
		implements RouterLayout, BeforeEnterObserver {

	@Id("appNavigation")
	private AppNavigation appNavigation;

	private static final long serialVersionUID = 1L;
  	private final ConfirmDialog confirmDialog;

	@Autowired
	public MainView() {
		this.confirmDialog = new ConfirmDialog();
		confirmDialog.setCancelable(true);
		confirmDialog.setConfirmButtonTheme("raised tertiary error");
		confirmDialog.setCancelButtonTheme("raised tertiary");

		List<PageInfo> pages = new ArrayList<>();

		pages.add(new PageInfo(PAGE_ROOT, ICON_DASHBOARD, TITLE_STOREFRONT));
		//pages.add(new PageInfo(PAGE_DASHBOARD, ICON_DASHBOARD, TITLE_DASHBOARD));
		pages.add(new PageInfo(PAGE_RECEIPTS, ICON_RECEIPTS, TITLE_RECEIPTS));
		if (SecurityUtils.isAccessGranted(UsersView.class)) {
			pages.add(new PageInfo(PAGE_USERS, ICON_USERS, TITLE_USERS));
		}
//		if (SecurityUtils.isAccessGranted(ProductsView.class)) {
//			pages.add(new PageInfo(PAGE_PRODUCTS, ICON_PRODUCTS, TITLE_PRODUCTS));
//		}
		pages.add(new PageInfo(PAGE_LOGOUT, ICON_LOGOUT, TITLE_LOGOUT));

		appNavigation.init(pages, PAGE_DEFAULT, PAGE_LOGOUT);

		getElement().appendChild(confirmDialog.getElement());
		//getElement().appendChild(new BakeryCookieConsent().getElement());
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (!SecurityUtils.isAccessGranted(event.getNavigationTarget())) {
			event.rerouteToError(AccessDeniedException.class);
		}
	}

	@Override
	public void showRouterLayoutContent(HasElement content) {
		if (content != null) {
			getElement().appendChild(content.getElement());
		}

		this.confirmDialog.setOpened(false);
		if (content instanceof HasConfirmation) {
			((HasConfirmation) content).setConfirmDialog(this.confirmDialog);
		}
	}
}
