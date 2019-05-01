package xyz.frt.basesdk2.service;

import xyz.frt.basesdk2.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    int OPT_INSERT = 0;
    int OPT_UPDATE = 1;
    int OPT_DELETE = 2;

    public List<T> selectByExample(T exam);

    public T selectByPrimaryKey(Integer id);

    public Integer updateByPrimaryKey(T item);

    public Integer insert(T item);

    public Integer deleteByPrimaryKey(Integer id);

    public List<T> selectAll();

}
