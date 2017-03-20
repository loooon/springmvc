package com.credit.service;

import com.credit.entity.UserEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Michael Chan on 3/20/2017.
 */
public interface UserService<T>
{
    UserEntity save(UserEntity userEntity);
}
