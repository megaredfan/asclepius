package buaa.bp.asclepius.util;

import org.junit.Test;

import buaa.bp.asclepius.utils.UUID11;

public class UUIDTest {

	@Test
	public void test() {
		for(int i=0; i<100;i++)
		{
			System.out.println(UUID11.getRandomId());
		}
	}
}
