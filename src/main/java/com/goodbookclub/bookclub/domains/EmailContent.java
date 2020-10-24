package com.goodbookclub.bookclub.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailContent {
	private String firstname;
	private String lastname;
	private String email;
	private String Content;
}
