package nz.clem.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRespository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String role);
}
