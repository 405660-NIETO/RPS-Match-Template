package ar.edu.utn.frc.tup.lciii.videos.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.videos.entities.PlayerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Long> {
    /*
    el uso de la nomenclatura "findBy" nos permite crear una consulta sin escribir el select gracias a
    la convencion de nombres que nos propone JPA Repository, donde le decimos "FindBy" y los concatenamos con
    por ejemplo "AND" "OR" y nos quedaria "findByUsernameOrEmail"

    por lo que con solamente con esta linea de codigo el JPA Repository sabe que tiene que hacer una consulta,
    sobre la tabla Players porque estamos pidiendo un PlayerEntity, y sabe que lo tiene que buscar por los campos
    Username y Email de esa tabla porque estos son los campos que le estamos pidiendo en el nombre del metodo

    En este caso es Opcional el Player Entity, lo que esto quiere decir para el service es que
    como es Optional puede venir null cuando lo vaya a buscar a la base de datos, osea que cuando vuelva puede
    que este vacio
     */
    Optional<PlayerEntity> findByUserNameOrEmail(String userName, String email);
    Optional<PlayerEntity> findByUserNameAndPassword(String userName, String password);
    Optional<PlayerEntity> findByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query("UPDATE PlayerEntity p SET p.lastLogin = :lastLogin WHERE p.id = :id")
    int updateLastLogin(@Param("id") Long id, @Param("lastLogin") LocalDateTime lastLogin);

    @Query("SELECT p FROM PlayerEntity p " +
            "WHERE (p.userName LIKE :identity OR p.email LIKE :identity) AND p.password LIKE :password")
    Optional<PlayerEntity> findByUserNameOrEmailAndPassword(@Param("identity") String identity, @Param("password") String password);
}
