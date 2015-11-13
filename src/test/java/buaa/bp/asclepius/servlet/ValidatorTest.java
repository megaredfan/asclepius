package buaa.bp.asclepius.servlet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import buaa.bp.asclepius.model.User;

public class ValidatorTest {

	private static Validator validator;
	
	@BeforeClass
	public void setUp(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void test() throws IOException{
		User user = new User();
		Set<ConstraintViolation<User>>  constraintViolation= validator.validate(user);
		System.out.println(constraintViolation.size());
		for(ConstraintViolation<User> c : constraintViolation){
			System.out.println(c.getMessage());
		}
	}
}
