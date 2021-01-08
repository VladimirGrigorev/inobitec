package com.inobitec.project.data.repository;

import com.inobitec.project.data.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findByName(String name);
}
