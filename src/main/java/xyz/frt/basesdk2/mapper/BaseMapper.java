package xyz.frt.basesdk2.mapper;

import xyz.frt.basesdk2.entity.BaseEntity;

import java.util.List;

public interface BaseMapper<T extends BaseEntity> {

    Integer updateByPrimaryKeySelective(T item);

    Integer insertSelective(T item);

    Integer deleteByPrimaryKey(Integer id);

    T selectByPrimaryKey(Integer id);

    List<T> selectByExample(T exam);

    List<T> selectAll();

}
