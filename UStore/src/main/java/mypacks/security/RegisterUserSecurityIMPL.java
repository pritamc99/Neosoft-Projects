package mypacks.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserSecurityIMPL implements RegisterUserSecurity 
{

	@Override
	public String hashUserPassword(String password) throws NoSuchAlgorithmException 
	{
		/*MessageDigest md=MessageDigest.getInstance("SHA-1");
		md.update(password.getBytes());
		byte[] b=md.digest();
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<b.length;i++)
		{
			sb.append(Integer.toHexString(b[i] & 0xff).toString());
		}
		return sb.toString();*/
		String cryptPassword=new BCryptPasswordEncoder().encode(password);
		return cryptPassword;
	}

	@Override
	public String getSalt() throws NoSuchAlgorithmException 
	{
		SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
		byte[] b=new byte[10];
		sr.nextBytes(b);
		return b.toString();
	}

}
