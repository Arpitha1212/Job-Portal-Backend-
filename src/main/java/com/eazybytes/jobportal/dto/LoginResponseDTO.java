package com.eazybytes.jobportal.dto;

public record LoginResponseDTO(String message, UserDTO user, String jwtToken) {

}
