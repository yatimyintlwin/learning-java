package com.platform.onlinecourse.service;

import com.platform.onlinecourse.model.User;
import com.platform.onlinecourse.repository.UserRepository;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import static com.platform.onlinecourse.mapper.UserDetailMapper.mapToUserDetail;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found or not enabled: " + username);
        }

        return mapToUserDetail(user.getUsername(), user.getPassword(), user.getRole());
    }
}
