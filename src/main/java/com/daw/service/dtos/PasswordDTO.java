package com.daw.service.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {

	private String antiguaPassword;
    private String nuevaPassword;
	
}
