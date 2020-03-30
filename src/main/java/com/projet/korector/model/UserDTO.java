package com.projet.korector.model;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User Model")




public class UserDTO implements Comparable<UserDTO> {


    @ApiModelProperty(value = "User id")
    private Long id;

    @ApiModelProperty(value = "User email")

    private String email;

    @ApiModelProperty(value = "User username")

    private String username;

    @ApiModelProperty(value = "User password")
    private String password;

    @ApiModelProperty(value = "User githubAccount")
    private String githubAccount;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getGithubAccount() {
        return githubAccount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setGithubAccount(String githubAccount) {
        this.githubAccount = githubAccount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo(UserDTO o) {
        return 0;
    }
}




