package com.p2.regex;

import java.util.regex.*;

public class RegexMain 
{
    public boolean valid( String regex,String input )
    {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.matches();
    }
}
