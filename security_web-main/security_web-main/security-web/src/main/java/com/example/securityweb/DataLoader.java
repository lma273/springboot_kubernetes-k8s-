package com.example.securityweb;
import com.example.securityweb.model.Role;
import com.example.securityweb.model.User;
import com.example.securityweb.repository.RoleRepository;
import com.example.securityweb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            // --- 1. Tạo role nếu chưa có ---
            Role rUser = roleRepo.findByName("USER")
                    .orElseGet(() -> roleRepo.saveAndFlush(new Role("USER")));
            Role rAdmin = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.saveAndFlush(new Role("ADMIN")));

            // --- 2. Tạo user admin nếu chưa có ---
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setEnabled(true);
                admin.getRoles().add(rAdmin);
                admin.getRoles().add(rUser);
                userRepo.saveAndFlush(admin); // saveAndFlush để chắc chắn ghi vào DB
            }

            // --- 3. Tạo user thường ---
            if (userRepo.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("password"));
                user.setEnabled(true);
                user.getRoles().add(rUser);
                userRepo.saveAndFlush(user);
            }

            System.out.println("DataLoader: Roles và Users đã được tạo.");
        };
    }
}
