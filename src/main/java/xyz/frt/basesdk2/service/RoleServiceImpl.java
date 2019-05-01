package xyz.frt.basesdk2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.basesdk2.entity.Role;
import xyz.frt.basesdk2.mapper.BaseMapper;
import xyz.frt.basesdk2.mapper.RoleMapper;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
