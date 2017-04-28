package com.example.jezuz1n.hairly.register;

import com.example.jezuz1n.hairly.models.dto.UserDTO;

/**
 * Created by jsalas on 28/4/17.
 */

public interface RegisterPresenter {
    void validateAccount(UserDTO user);
    void createSession(UserDTO user);
}
