

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dowman.spring.test.XdpUser;
import com.dowman.spring.test.XdpUserDao;

public class XdpUserHandler {

	@Test
	public void testXdpUserCRUD() throws SQLException, ClassNotFoundException{
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/dowman/spring/test/bean.xml");
		
		XdpUser xdpUser = (XdpUser) context.getBean("xdpUser");
		xdpUser.setUser_id("foo");
		xdpUser.setUser_nm("foo bear");
		xdpUser.setCmpy_cd("MEH");
		
		XdpUserDao xdpUserDao = (XdpUserDao) context.getBean("xdpUserDao");

		//insert
		int count = xdpUserDao.insert(xdpUser);
		assertEquals(count, 1);
		
		//select
		String cmpy_cd = xdpUserDao.getUser("foo");
		assertEquals(cmpy_cd, "MEH");
		
		//delete
		assertEquals(xdpUserDao.delete("foo"), 1);
		
		
	}
}
