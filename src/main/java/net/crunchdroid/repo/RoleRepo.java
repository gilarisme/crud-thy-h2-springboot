package net.crunchdroid.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.crunchdroid.entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {

	Role findRoleByidRole(UUID fromString);

	
}
