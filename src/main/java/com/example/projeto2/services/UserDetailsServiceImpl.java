package com.example.projeto2.services;

import com.example.projeto2.models.UserModel;
import com.example.projeto2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }

    public UserModel getUserById(Integer id_user) throws UserNotFoundException {
        Optional<UserModel> result = userRepository.findById(id_user);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Não se encontra nenhum user com esse id " + id_user);
    }

    public void deleteUser(Integer id_user){
        userRepository.deleteById(id_user);
    }

}