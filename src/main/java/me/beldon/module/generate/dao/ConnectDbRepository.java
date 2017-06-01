package me.beldon.module.generate.dao;

import me.beldon.module.generate.domain.ConnectDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Beldon.
 * Copyright (c)  2017/5/21, All Rights Reserved.
 * http://beldon.me
 */
@Repository
public interface ConnectDbRepository extends JpaRepository<ConnectDb,String> {
}
