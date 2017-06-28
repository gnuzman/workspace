package com.zzh.mappers;

import com.zzh.entitys.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by house on 2017/6/28.
 */
@Mapper
public interface UsersMapper {
    List<UsersEntity> getUsers();
}
