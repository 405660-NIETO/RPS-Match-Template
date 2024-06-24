package ar.edu.utn.frc.tup.lciii.videos.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.videos.entities.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerJpaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Test
    public void findByUserNameOrEmailTest() {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setEmail("email@email.com");
        playerEntity.setUserName("agusnieto");
        playerEntity.setPassword("Password07$");

        entityManager.persist(playerEntity);
        entityManager.flush();

        Optional<PlayerEntity> result = playerJpaRepository.findByUserNameOrEmail("agusnieto", "Password07$");
        assertEquals("agusnieto", result.get().getUserName());
    }
}