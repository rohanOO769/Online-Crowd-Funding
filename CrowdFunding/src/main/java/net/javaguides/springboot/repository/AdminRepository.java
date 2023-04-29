package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import net.javaguides.springboot.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
    @Query("SELECT g FROM Admin g WHERE g.email = ?1 AND g.password = ?2")
	public Admin validate(String email, String password);

}