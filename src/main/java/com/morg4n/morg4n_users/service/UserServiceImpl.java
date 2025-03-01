package com.morg4n.morg4n_users.service;

import com.morg4n.morg4n_users.model.User;
import com.morg4n.morg4n_users.persistence.entities.PossessionEntity;
import com.morg4n.morg4n_users.persistence.entities.UserEntity;
import com.morg4n.morg4n_users.persistence.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo (Morg4n) {@literal <jdacamachoe@gmail.com>}
 */
@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository repository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAll() {
        Iterable<UserEntity> data = repository.findAll();
        return mapper.map(data, new TypeToken<List<User>>(){}.getType());
    }

    @Override
    public User findById(String id) throws Exception {
        UserEntity data = repository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Entity with ID %s does not exist.", id)));
        return mapper.map(data, User.class);
    }

    @Override
    public User save(User user) {
        UserEntity data = mapper.map(user, UserEntity.class);
        data.setId(UUID.randomUUID().toString());
        if(data.getPossessions() != null && !data.getPossessions().isEmpty())
            data.getPossessions().forEach(possession ->  {
                possession.setId(UUID.randomUUID().toString());
                possession.setObjUser(data);
            }
        );

        return mapper.map(repository.save(data), User.class);
    }

    @Override
    public User update(String id, User user) throws Exception {
        final UserEntity data = repository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Entity with ID %s does not exist.", id)));

        data.setName(user.getName());
        data.setLastName(user.getLastName());
        data.setPhoneNumber(user.getPhoneNumber());

        if (user.getPossessions() != null && !user.getPossessions().isEmpty()) {
            data.getPossessions().clear();
            List<PossessionEntity> newPossessions = mapper.map(user.getPossessions(), new TypeToken<List<PossessionEntity>>() {}.getType());

            newPossessions.forEach(possession -> {
                possession.setId(UUID.randomUUID().toString());
                possession.setObjUser(data);
            });

            data.getPossessions().addAll(newPossessions);
        }

        return mapper.map(repository.save(data), User.class);
    }

    @Override
    public boolean delete(String id) throws Exception {
        UserEntity data = repository.findById(id)
                .orElseThrow(() -> new Exception(String.format("Entity with ID %s does not exist.", id)));
        repository.delete(data);
        return true;
    }
}
