package ar.edu.utn.frc.tup.lciii.videos.services.impl;

import ar.edu.utn.frc.tup.lciii.videos.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.videos.models.Player;
import ar.edu.utn.frc.tup.lciii.videos.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.videos.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*
    @Override
    public Player getPlayerById(Long id) {
        //Objeto a mapear (de capa inferior)
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);
        //Mapeo

        //     objeto player sera igual a el objeto que se pasa por parametro con la clase especificada

        //     Objeto return = mapeo -> ( Objeto Entity, Clase tipo del return )
        Player player = modelMapper.map(playerEntity, Player.class);
        return player;
    }*/
    @Override
    public Player getPlayerById(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);

        if (Objects.isNull(playerEntity.getUserName())) {
            throw new EntityNotFoundException();
        }
        Player player = modelMapper.map(playerEntity, Player.class);
        return player;
    }

    /*
    Lo que estamos haciendo es guardar, convertir el player como entity, lo guardamos en la bd
    y convertimos el objeto guardado (entity) en objeto (model) que es lo que pide la firma
     */
    @Override
    public Player savePlayer(Player player) {
        // (video 14) creamos el opcional para llamar la validacion de mail y el usuario no existan
        // es opcional porque puede devolver algo vacio, osea en otros terminos,
        // que el usuario en realidad no existe por lo tanto si puede guardarse
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmail(
                player.getUserName(),
                player.getEmail());
        if (playerEntityOptional.isEmpty()){

            //(video 14) en el caso de que el player entity este vacio, significaria que
            //podemos guardar el usuario en la base de datos

            //mapear al reves la conversion para un POST
            PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);

            PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity);

            //retornar finalmente la conversion del objeto GUARDADO entity a el objeto final
            return modelMapper.map(playerEntitySaved, Player.class);
        }
        else {
            // (video 14) En el caso de que el usuario si exista se devuelve un Null
            return null;
        }
    }

    @Override
    public Player getPlayerByUserNameAndPassword(String userName, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameAndPassword(userName, password);
        if (playerEntityOptional.isPresent()) {
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("invalid username or password!");
        }
    }

    @Override
    public Player getPlayerByEmailAndPassword(String email, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByEmailAndPassword(email, password);
        if (playerEntityOptional.isPresent()) {
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("invalid email or password!");
        }
    }

    @Override
    public Player getPlayerByUserNameOrEmailAndPassword(String identity, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmailAndPassword(identity, password);
        if (playerEntityOptional.isPresent()) {
            return modelMapper.map(playerEntityOptional.get(), Player.class);
        } else {
            throw new EntityNotFoundException("Some parameters are incorrect!");
        }
    }

    @Override
    @Transactional
    public void updateLastLogin(Long id, LocalDateTime lastLogin) {
        playerJpaRepository.updateLastLogin(id, lastLogin);
    }
}
