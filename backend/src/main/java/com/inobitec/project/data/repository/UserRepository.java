package com.inobitec.project.data.repository;

import com.inobitec.project.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
