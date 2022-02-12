package com.secondhand.secondhand.init;

import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBinit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DBinit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        initUserRoles();
    }

    private void initUserRoles() {
        List<RoleEntity> roleEntities = roleRepository
                .findAll();

        if (roleEntities.size() == 0) {

            RoleEntity userRole = new RoleEntity();
            userRole.setRole(RoleEnum.USER);

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole(RoleEnum.ADMINISTRATOR);

            roleRepository.saveAll(List.of(userRole,adminRole));
        }
    }
}
