package xgxt.pjpy.comm.pjpy.sjcl.sjdr;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpySjdrService {
	
	/**
	 * 获取需要导入的数据表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getSjdrlx(){
		
		DAO dao=DAO.getInstance();
		
		String[]en={"xg_pjpy_cxfpdb","xg_pjpy_cqlb","xg_pjpy_zhcpcjb"};
		String[]cn={"操行分评定表","出勤率表","评奖综测信息表"};
		
		return dao.arrayToList(en, cn);
	}
}
