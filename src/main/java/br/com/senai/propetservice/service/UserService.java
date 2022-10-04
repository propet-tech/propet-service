package br.com.senai.propetservice.service;

import br.com.senai.propetservice.converters.ModelToDto;
import br.com.senai.propetservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.propetservice.data.UserDto;
import br.com.senai.propetservice.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repository;

    public void create(UserDto user) {
        repository.save(
                ModelToDto.parseObject(user, User.class)
        );
    }

    public UserDto getUserById(long id) {
        var user = repository.findById(id).orElseThrow(
                () -> new RuntimeException("User Not Found")
        );

        return ModelToDto.parseObject(user, UserDto.class);
    }
}
