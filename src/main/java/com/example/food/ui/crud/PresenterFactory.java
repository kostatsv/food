/**
 *
 */
package com.example.food.ui.crud;

import com.example.food.backend.domain.Receipt;
import com.example.food.backend.domain.User;
import com.example.food.ui.service.ReceiptService;
import com.example.food.ui.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PresenterFactory {

//	@Bean
//	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	public CrudEntityPresenter<Product> productPresenter(ProductService crudService, User currentUser) {
//		return new CrudEntityPresenter<>(crudService, currentUser);
//	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CrudEntityPresenter<User> userPresenter(UserService crudService, User currentUser) {
		return new CrudEntityPresenter<>(crudService, currentUser);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CrudEntityPresenter<Receipt> receiptPresenter(ReceiptService crudService, User currentUser) {
		return new CrudEntityPresenter<>(crudService, currentUser);
	}

//	@Bean
//	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	public EntityPresenter<Order, StorefrontView> orderEntityPresenter(OrderService crudService, User currentUser) {
//		return new EntityPresenter<>(crudService, currentUser);
//	}

}
