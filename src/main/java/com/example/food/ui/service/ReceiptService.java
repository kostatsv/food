package com.example.food.ui.service;

import com.example.food.backend.domain.Receipt;
import com.example.food.backend.domain.User;
import com.example.food.backend.repositories.ReceiptRepository;
import com.example.food.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService implements FilterableCrudService<Receipt> {

	public static final String MODIFY_LOCKED_USER_NOT_PERMITTED = "User has been locked and cannot be modified or deleted";
	private static final String DELETING_SELF_NOT_PERMITTED = "You cannot delete your own account";
	private final ReceiptRepository receiptRepo;

	@Autowired
	public ReceiptService(ReceiptRepository receiptRepo) {
		this.receiptRepo = receiptRepo;
	}

	public Page<Receipt> findAnyMatching(Optional<String> filter, Pageable pageable) {
//		if (filter.isPresent()) {
//			String repositoryFilter = "%" + filter.get() + "%";
//			return getRepository()
//					.findByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrRoleLikeIgnoreCase(
//							repositoryFilter, repositoryFilter, repositoryFilter, repositoryFilter, pageable);
//		} else {
//			return find(pageable);
//		}
		return null;
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
//		if (filter.isPresent()) {
//			String repositoryFilter = "%" + filter.get() + "%";
//			return receiptRepo.countByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrRoleLikeIgnoreCase(
//					repositoryFilter, repositoryFilter, repositoryFilter, repositoryFilter);
//		} else {
//			return count();
//		}
		return 0;
	}

	@Override
	public ReceiptRepository getRepository() {
		return receiptRepo;
	}

	// public Page<Receipt> find(Pageable pageable) {
	//	return getRepository().findBy(pageable);
	// }

//	@Override
	public Receipt save(Receipt entity) {
		return getRepository().saveAndFlush(entity);
	}

//	@Override
	@Transactional
	public void delete(Receipt receipt) {
		getRepository().delete(receipt);
	}

	@Override
	public Receipt createNew(User currentUser) {
		return new Receipt();
	}

	public List<Receipt> getAllReceipts() {
		return receiptRepo.findAll();
	}

	public List<Receipt> getReceipts(LocalDate filterDate) {
		return receiptRepo.findAllByReceiptDateBetween(filterDate.withDayOfMonth(1),
							filterDate.withDayOfMonth(filterDate.lengthOfMonth()));
	}
}
