package com.example.projeto2.services;

import com.example.projeto2.models.User;
import com.example.projeto2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

@Service("userService")
public class users implements UserDetailsService {
    private final UserRepository userRepository;

    public users(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserById(Integer id_user) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id_user);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Não se encontra nenhum user com esse id " + id_user);
    }

    public void deleteUser(Integer id_user){
        userRepository.deleteById(id_user);
    }

}