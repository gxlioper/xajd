package xgxt.qgzx.zgmsxy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块中国美术学院DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-04</p>
 */
public class QgzxZgmsxyDAO extends DAO{
	
	public HashMap<String, String> getGwInfo(String pk,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String gwxz = "";
		String yrdwdm = "";
		String sql = "select xn,nd,xq,yrdwmc,gznr,gwxzmc,gwxz,spbcbz,decode(jcfs,'h','元/小时','d','元/天','w','元/周','m','元/月'," +
				"'n','元/志愿服务') jcfs,(select distinct xymc from view_njxyzybj b where " +
				"xydm=(select xydm from yrdwdmb c where c.yrdwdm=a.sqdw)) bmmc,sqdw from view_gwxx a where " + pk + "=?";
		//岗位基本信息
		map = getMap(sql, new String[]{pkValue}, new String[]{"yrdwmc","gznr","gwszmc","spbcbz","jcfs","gwxz","bmmc","sqdw"});
		
		//经费信息
		gwxz = map.get("gwxz");
		yrdwdm = map.get("sqdw");
		sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and gwxzdm=? and yrdwdm=?),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and gwxzmc=? and yrdwmc=?),0) syjf from dual";
		String syjf = getOneRs(sql, new String[]{map.get("nd"),gwxz,yrdwdm,map.get("nd"),yrdwdm,map.get("gwxzmc"),map.get("yrdwmc")}, "syjf");
		map.put("syjf", syjf);		
		return map;
	}
	
	public List getStuPayInfo(String pk,String pkValue){
		
//		String sql = "select * from xscjffb a,xsgwxxb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and b.gwdm||b.gwsbsj='"
//			+ pkValue
//			+ "' and a.nd='"
//			+ map.get("nd")
//			+ "' and a.yf='" + map.get("yf") + "'";
//		sql = "select * from xskhyb a ,xsgwxxb b where a.xh=b.xh and b.gwdm||b.gwsbsj=";
		return null;
	}
}
