package com.credit.service.impl;

import com.credit.entity.UserEntity;
import com.credit.repository.UserRepository;
import com.credit.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Michael Chan on 3/20/2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService<UserEntity>
{
    @Resource
    UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity userEntity)
    {
        return userRepository.save(userEntity);
    }
}
