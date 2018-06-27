package my.study.test.deault;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.infinova.pss.dao.VehicleSMDao;
//,file:E:\\hc\\Workspaces2\\V2232DH_dms\\dbs\\src\\main\\webapp\\WEB-INF\\dataSource.xml}
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:E:\\hc\\Workspaces2\\V2232DH_dms\\dbs\\src\\main\\webapp\\WEB-INF\\applicationContext.xml",
		"file:E:\\hc\\Workspaces2\\V2232DH_dms\\dbs\\src\\main\\webapp\\WEB-INF\\dataSource.xml"})
public class SpringTest {
	@Autowired
	private VehicleSMDao vehicleSMDao;
	@TestJNA
	public void test(){
		System.out.println(vehicleSMDao);
	}
}
