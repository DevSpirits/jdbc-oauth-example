package kr.co.woosuk.oauth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.woosuk.oauth.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

	List<Role> findByUsername(String username);

}
