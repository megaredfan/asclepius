package buaa.bp.asclepius.util;

import org.testng.annotations.Test;

import buaa.bp.asclepius.utils.UUID11;

public class UUIDTest {

	@Test
	public void test() {
		for(int i=0; i<100000;i++)
		{
			System.out.println(UUID11.getRandomId());
		}
	}
}
