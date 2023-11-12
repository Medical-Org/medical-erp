package com.example.platformerp;

import com.example.platformerp.enums.Permissions;
import com.example.platformerp.model.UserRole;
import com.example.platformerp.repository.UserRoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PlatformErpApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run( PlatformErpApplication.class, args );
    }
    @Bean
    ApplicationRunner runner(UserRoleRepository userRoleRepository){
      return args -> {
          UserRole admin = new UserRole(
                  "ADMIN",
                  List.of(
                        Permissions.TEACHER_CREATE,
                          Permissions.TEACHER_GET,
                          Permissions.STUDENT_DELETE,
                          Permissions.STUDENT_CREATE,
                          Permissions.GROUP_GET,
                          Permissions.GROUP_CREATE,
                          Permissions.GROUP_UPDATE,
                          Permissions.SUBJECT_CREATE,
                          Permissions.SUBJECT_GET,
                          Permissions.STUDENT_DELETE,
                          Permissions.SUBJECT_UPDATE
                  )
          );
          userRoleRepository.save(admin);
          UserRole superAdmin = new UserRole(
                  "SUPER_ADMIN",
                  List.of(
                     Permissions.GROUP_GET,
                          Permissions.STUDENT_GET,
                          Permissions.ADMIN_CREATE,
                          Permissions.MANAGER_CREATE,
                          Permissions.ADMIN_BLOCK,
                          Permissions.MANAGER_BLOCK,
                          Permissions.ADMIN_DELETE,
                          Permissions.MANAGER_DELETE,
                          Permissions.ADMIN_GET_ALL
                  )
          );
          userRoleRepository.save(superAdmin);
      };

        }

    }

