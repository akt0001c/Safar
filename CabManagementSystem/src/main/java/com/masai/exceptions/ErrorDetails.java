package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String description;
}
