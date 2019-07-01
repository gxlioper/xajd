package xgxt.xsgygl.comm.tj;

import java.util.HashMap;
import java.util.List;
/**
 * author lyl
 * Title 数据统计分析的Service实现
 */
public class GyglCommTjServiceImpl implements GyglCommTjService{
	GyglCommTjDaoImpl gcDAO = null;
	/**
	 * author lyl
	 * Title:学生入住情况的统计分析
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> service_FpDeTailView(String userDep,String nj,String xydm,String zydm,String bjdm){
		gcDAO = new GyglCommTjDaoImpl();
		return gcDAO.dao_FpDeTailView(userDep,nj,xydm,zydm,bjdm);
	}
}
