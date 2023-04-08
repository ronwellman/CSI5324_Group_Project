package baylor.csi5324.group_project.Repository;

import baylor.csi5324.group_project.Domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);

//    Optional<Token> findByEmail(String email);
}
