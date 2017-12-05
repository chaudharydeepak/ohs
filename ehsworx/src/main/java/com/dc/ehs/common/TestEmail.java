package com.dc.ehs.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEmail
{
public static void main (String args[])
{
	String password = "Jan2010$";
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String hashedPassword = passwordEncoder.encode(password);
	System.out.println( hashedPassword );
}
}
