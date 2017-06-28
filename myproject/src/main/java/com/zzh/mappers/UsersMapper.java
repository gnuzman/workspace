package com.zzh.mappers;

import com.zzh.entities.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by house on 2017/6/28.
 */
@Mapper
public interface UsersMapper {
    List<UsersEntity> getUsers(Map<String, Object> params);

    UsersEntity getUser(Integer id);

    Long addUser(UsersEntity usersEntity);
}
