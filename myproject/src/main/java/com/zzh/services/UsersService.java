package com.zzh.services;

import com.zzh.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by house on 2017/6/28.
 */
@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;
}
