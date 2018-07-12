package com.swist.m.krzysztof.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.swist.m.krzysztof.app.dao.model.AppUser;

public interface UserRepository extends CrudRepository<AppUser, String> {

}
