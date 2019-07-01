/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-21 下午07:10:54</p>
 */
package xgxt.xsgygl.hngydx;

import java.util.HashMap;
import java.util.List;

public class GyglHngydxService {
    public List<HashMap<String,String>> serv_HmcTj(String xb){
    	GyglHngydxDao myDao = new GyglHngydxDao(); 	
    	return myDao.dao_HmcTj(xb);
    }
}
