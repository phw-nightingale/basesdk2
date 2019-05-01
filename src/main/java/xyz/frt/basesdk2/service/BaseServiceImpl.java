package xyz.frt.basesdk2.service;

import xyz.frt.basesdk2.common.AppContext;
import xyz.frt.basesdk2.entity.BaseEntity;
import xyz.frt.basesdk2.entity.User;
import xyz.frt.basesdk2.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {


    public abstract BaseMapper<T> getMapper();

    @Override
    public Integer insert(T item) {
        if (item != null) {
            setOptUser(item, OPT_INSERT);
            return getMapper().insertSelective(item);
        }
        return 0;
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        if (id != null && id != 0) {
            T item = selectByPrimaryKey(id);
            setOptUser(item, OPT_DELETE);
            return getMapper().deleteByPrimaryKey(id);
        }
        return id;
    }

    @Override
    public Integer updateByPrimaryKey(T item) {
        if (item != null && item.getId() != null) {
            setOptUser(item, OPT_UPDATE);
            return getMapper().updateByPrimaryKeySelective(item);
        }
        return 0;
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        if (id != 0) {
            return getMapper().selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public List<T> selectByExample(T exam) {
        if (exam != null) {
            return getMapper().selectByExample(exam);
        }
        return null;
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    private void setOptUser(T item, int opt) {
        User user = AppContext.getCurrentUser();
        if (user != null) {
            switch (opt) {
                case OPT_INSERT:
                    if (item.getCreateUser() == null) {
                        item.setCreateUser(user.getUsername());
                    }
                    if (item.getCreateUserId() == null) {
                        item.setCreateUserId(user.getId());
                    }
                    if (item.getCreateTime() == null) {
                        item.setCreateTime(new Date());
                    }
                    break;
                case OPT_UPDATE:
                    item.setUpdateUser(user.getUsername());
                    item.setUpdateUserId(user.getId());
                    item.setUpdateTime(new Date());
                    break;
                case OPT_DELETE:
                    item.setDeleteUser(user.getUsername());
                    item.setDeleteUserId(user.getId());
                    item.setDeleteTime(new Date());
                    break;
                default:
                    break;
            }
        }
    }
}
