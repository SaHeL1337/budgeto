package com.sahsec.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sahsec.validator.PasswordMatches;
import com.sahsec.validator.ValidEmail;

public class UserDto {
	
	@NotNull
    @NotEmpty
    private String name;
     
    @PasswordMatches
    private String password;
    
    @NotNull
    @NotEmpty
    private String matchingPassword;
     
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
     
    // standard getters and setters
}
