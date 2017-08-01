package com.flywithus.user.dto;

public class UserDTO {

    private final String id;
    private final String username;
    private final char[] password;

    public UserDTO(String id, String username, char[] password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

}
