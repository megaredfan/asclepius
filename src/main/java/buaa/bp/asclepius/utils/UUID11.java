package buaa.bp.asclepius.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public class UUID11 {
	public static long getRandomId(){
		String uuid = UUID.randomUUID().toString();
		ByteArrayInputStream bis = new ByteArrayInputStream(uuid.getBytes());
		DataInputStream dis = new DataInputStream(bis);
		
		try {
			return (dis.readLong()/100000000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
