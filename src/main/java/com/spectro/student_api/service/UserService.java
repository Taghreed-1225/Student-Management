package com.spectro.student_api.service;

import com.spectro.student_api.dto.RegisterRequest;
import com.spectro.student_api.entity.User;

public interface UserService {
    User createUser(RegisterRequest registerRequest);
    boolean existsByUsername(String username);
}
