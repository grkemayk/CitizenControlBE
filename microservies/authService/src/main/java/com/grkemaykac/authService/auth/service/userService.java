package com.grkemaykac.authService.auth.service;

import com.grkemaykac.authService.auth.dto.userDto;
import com.grkemaykac.authService.auth.entity.userEntity;
import com.grkemaykac.authService.auth.repository.userRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
@Slf4j
public class userService implements UserDetailsService {

    @Autowired
    userRepository UserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public userEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        userEntity UserEntity = UserRepository.findByUsername(username);
        if(UserEntity == null)
            throw new UsernameNotFoundException("User Not Found! Account: "+username);
        return UserEntity;
    }
    public userDto saveUser(userDto newUser)
    {
        userEntity newUserEntity = new userEntity();
        BeanUtils.copyProperties(newUser, newUserEntity);//It can copy same properties.
        newUserEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setId(UserRepository.save(newUserEntity).getId());
        return newUser;
    }
    public userDto editUser(userDto editedUserDto)
    {
        userEntity editedUser = getUserById(editedUserDto.getId());
        userDto checkUserDto = new userDto();
        BeanUtils.copyProperties(editedUser, checkUserDto);
        String decodedPassword = editedUserDto.getPassword();
        if(passwordEncoder.matches(editedUserDto.getPassword(), checkUserDto.getPassword()))
        {
            editedUserDto.setPassword(null);
            checkUserDto.setPassword(null);
            if(checkUserDto.equals(editedUserDto))
            {
                log.info("Update Warning! Same account's information! Account: "+editedUser.getEmail());
                editedUserDto.setPassword(decodedPassword);
                return editedUserDto;
            }
        }
        editedUserDto.setPassword(decodedPassword);
        BeanUtils.copyProperties(editedUserDto, editedUser);
        editedUser.setPassword(passwordEncoder.encode(editedUserDto.getPassword()));
        editedUser.setUpdatedTime(System.currentTimeMillis());
        UserRepository.save(editedUser);
        log.info("Update is succesful! Account: "+editedUser.getEmail());
        return editedUserDto;
    }

    public userDto deleteUser(userDto deletedUserDto)
    {
        userEntity deletedUser = getUserById(deletedUserDto.getId());
        deletedUser.setActive(false);
        deletedUser.setUpdatedTime(System.currentTimeMillis());
        UserRepository.save(deletedUser);
        log.info("Delete is succesful! Account: "+deletedUser.getEmail());
        return deletedUserDto;
    }

    public userDto activeUser(userDto activeUserDto)
    {
        userEntity activeUser = getUserById(activeUserDto.getId());
        activeUser.setActive(true);
        activeUser.setUpdatedTime(System.currentTimeMillis());
        UserRepository.save(activeUser);
        log.info("Activation is succesful! Account: "+activeUser.getEmail());
        return activeUserDto;
    }

    public userDto getFullUser(userDto fullUserDto)
    {
        String userPassword = fullUserDto.getPassword();
        if(fullUserDto.getEmail() != null ||
        fullUserDto.getUsername() != null)
        {
            if(fullUserDto.getUsername() == null)
            {
                BeanUtils.copyProperties(UserRepository.findByEmail(fullUserDto.getEmail()), fullUserDto);
                fullUserDto.setPassword(userPassword);
                return fullUserDto;
            }
            else
            {
                BeanUtils.copyProperties(UserRepository.findByUsername(fullUserDto.getUsername()), fullUserDto);
                fullUserDto.setPassword(userPassword);
                return fullUserDto;
            }
        }
        else
        {
            throw new UsernameNotFoundException("User Not Found!");
        }

    }

    public userEntity getUserById(Long id)
    {
        Optional<userEntity> newUserEntity = UserRepository.findById(id);
        return newUserEntity.get();
    }
}
