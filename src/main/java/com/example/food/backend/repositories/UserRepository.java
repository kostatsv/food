package com.example.food.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmailIgnoreCase(String email);

  Page<User> findBy(Pageable pageable);

  Page<User> findByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrRoleLikeIgnoreCase(
      String emailLike, String firstNameLike, String lastNameLike, String roleLike, Pageable pageable);

  long countByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrRoleLikeIgnoreCase(
      String emailLike, String firstNameLike, String lastNameLike, String roleLike);
  
}
