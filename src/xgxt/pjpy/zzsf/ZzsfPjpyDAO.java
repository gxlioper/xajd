package xgxt.pjpy.zzsf;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;

public class ZzsfPjpyDAO {
	public boolean checkExistJxjxx(String xh){
		boolean res = false;
		DAO dao = DAO.getInstance();
		String sql = "select count(*) cont from xsjxjxxb where xh=? ";
		String result = dao.getOneRs(sql, new String[]{xh}, "cont");
		if(Integer.parseInt(result)>0){
			res = true;
		}
		return res;
	}
	
	public boolean autoCalcZhcp(HttpServletRequest request){
		boolean result = false;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm="";
		String sql = "{call zzsf_zhszcp_calculate(?)}";
		try{
			if("xy".equalsIgnoreCase(userType)){
				bmdm = session.getAttribute("userDep").toString();
				result = dao.runProcuder(sql, new String[]{bmdm});
			}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
				result = dao.runProcuder(sql, new String[]{bmdm});
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<HashMap<String, String>> getZyList(String yhm,DAO dao){
		String sql = "select distinct a.zydm,a.zymc from view_njxyzybj a,yhb d,fdybjb b,fdyxxb c where a.bjdm=b.bjdm and b.zgh=c.zgh and d.xm=c.xm and d.yhm=? order by zydm";
		return (ArrayList<HashMap<String, String>>)dao.getList(sql, new String[]{yhm}, new String[]{"zydm","zymc"});
	}
	
	public ArrayList<HashMap<String, String>> getBjList(String yhm,DAO dao,String zydm){
		String sql = "";
		if("".equalsIgnoreCase(zydm) || zydm == null)
			sql = "select a.bjdm,a.bjmc from view_njxyzybj a,yhb d,fdybjb b,fdyxxb c where a.bjdm=b.bjdm and b.zgh=c.zgh and d.xm=c.xm and d.yhm='"+yhm+"' order by bjdm";
		else 
			sql = "select a.bjdm,a.bjmc from view_njxyzybj a,yhb d,fdybjb b,fdyxxb c where a.bjdm=b.bjdm and b.zgh=c.zgh and d.xm=c.xm and d.yhm='"+yhm+"' and a.zydm='"+zydm+"' order by bjdm";
		return (ArrayList<HashMap<String, String>>)dao.getList(sql, new String[]{}, new String[]{"bjdm","bjmc"});
	}
	
}
