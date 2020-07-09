package net.crunchdroid.entity;

import java.util.List;

import lombok.Data;


@Data
public class UserDTO{

  	private String userName;
    private String userPassword;
    private String email;
    private String namaLengkap;
    private String role;
}
