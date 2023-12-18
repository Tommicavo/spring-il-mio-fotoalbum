package org.java.spring.springilmiofotoalbum.auth.db.serv;

import org.java.spring.springilmiofotoalbum.auth.db.pojo.Role;
import org.java.spring.springilmiofotoalbum.auth.db.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public Role findById(int id) {
        return roleRepo.findById(id).get();
    }

    public void save(Role role) {
        roleRepo.save(role);
    }
}
