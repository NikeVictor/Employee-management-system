package net.javaguide.usermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguide.usermanagementsystem.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
