package com.dc.ehs.common;

public class TestEmail
{
public static void main (String args[])
{
	String email = "Deepak Chaudhary(chaudharydeepak08@gmail.com)";
	String respManager = (email.split("\\(")[1]).replace(")", "");
	System.out.println(respManager);
}
}
