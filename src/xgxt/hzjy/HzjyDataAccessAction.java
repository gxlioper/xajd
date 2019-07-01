package xgxt.hzjy;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class HzjyDataAccessAction {
	
	//��ȡ�༶����
	public static HashMap<String, String> getBjrs(DAO dao, String xydm, String bjdm) {
		String sql;
		HashMap<String, String> map;
		sql = "select bjmc,count(*) bjrs from view_xsjbxx where bjdm=? and xydm=? group by bjdm,bjmc";
		map = dao.getMap(sql, new String[]{bjdm,xydm}, new String[]{"bjmc","bjrs"});
		return map;
	}
	
	//��ȡ��������������Ϣ
	public static List getHzjyazxx(DAO dao, String bjdm) {
		String sql;
		List rs;
		sql = "select rownum bh,xm,xh,jtdh,sjh,gzdwqc,gzdwdz,qy,yzbm,lxr,bm,lxdh,lxrsjh,xty,xtysjh from view_hzjyazb where bjdm=?";
		rs = dao.getList(sql, new String[]{bjdm}, new String[]{"bh","xm","xh","jtdh","sjh","gzdwqc","gzdwdz","qy",
				"yzbm","lxr","bm","lxdh","lxrsjh","xty","xtysjh"});
		return rs;
	}
	
	//��ȡЭ��Ա��Ϣ
	public static HashMap<String, String> getCoorDetailXxMap(DAO dao, String realTable, String pkValue, String pk) {
		String sql;
		HashMap<String, String> map;
		sql = "select xtydm,xtyxm,xtylxdh,xtysjh,xtyxb from " + realTable + " where " + pk + "=?";
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"xtydm","xtyxm","xtylxdh","xtysjh","xtyxb"});
		return map;
	}
	
	//��ȡ��ǰϵͳʱ��
	public static String getCurrSysTime(DAO dao) {
		String currTime = dao.getOneRs("select to_char(sysdate,'yyyymmdd') currtime from dual", new String[]{}, "currtime");
		return currTime;
	}
}
