package xyz.frt.basesdk2.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.frt.basesdk2.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}