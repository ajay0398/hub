package com.hub.Service;

import com.hub.dto.AuthenticationResponse;
import com.hub.dto.LoginDto;
import com.hub.dto.SignUpDto;
import com.hub.model.User;

public interface UserService {


User signup(SignUpDto dto);
    AuthenticationResponse login(LoginDto dto);




}
