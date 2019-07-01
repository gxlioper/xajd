package xgxt.xszz.nbty.jtjjknsbz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.GetTime;

public class XszzDao {
	public List<HashMap<String, String>> getXszzList() {
		/**
		 * 获取荣誉称号列表
		 */
		DAO dao = DAO.getInstance();
		String sql = "select bzdm  dm, bzmc mc,bzje je from NBTY_XSZZ_SQBZB";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc","je"});
	}
	
	public List<HashMap<String, String>> getJtjjknbz(String bzdm){
		DAO dao = DAO.getInstance();
		String sql = "select bzje je from NBTY_XSZZ_SQBZB where bzdm= ?";
		return dao.getList(sql, new String[]{bzdm}, new String[]{"je"});
	}
	
	public List<HashMap<String, String>> getShjb(String viewName){
		DAO dao = DAO.getInstance();
		String sql = "select shjb jb from xszz_n05_shjb where xmb= ?";
		return dao.getList(sql, new String[]{viewName}, new String[]{"jb"});
	}
	
	/**
	 * 判断是否困难
	 * @param viewName
	 * @return
	 */
	public boolean judgeKn(String viewName){
		
		DAO dao = DAO.getInstance();
		
		String num="";
		String sql="select count(*) num from xszz_n05_shjb where XMB= ? and sfkns=?";
		num = dao.getOneRs(sql, new String[] {viewName,"1"}, new String("num"));
		if(!"0".equalsIgnoreCase(num)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * 判断补助是否在申请时间内
	 */
	public boolean jtjjknsSqSj(String xydm,String viewName){
		DAO dao = DAO.getInstance(); 
		
		String num="";
		String nowTime=GetTime.getSystemTime();
		//根据学院代码，获取申请的开始时间和结束时间
		String sql="select count(*) num from nblg_sjb where xydm=?  and xmmc='家庭经济困难学生困难补助'  and ? between kssj and jssj ";
		num = dao.getOneRs(sql, new String[]{xydm,nowTime}, new String("num"));
		if(!"0".equalsIgnoreCase(num)){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public String getXqMc(String xq){
		DAO dao = DAO.getInstance(); 
		List<String> xqmc=new ArrayList<String>();
		String nowTime=GetTime.getSystemTime();
		//根据学院代码，获取申请的开始时间和结束时间
		String sql="select xqmc  from xqdzb where xqdm = ? ";
		try {
			xqmc = dao.getList(sql, new String[]{xq}, new String("xqmc"));
		} catch (SQLException e) {
			// TODO 获取学期名称失败
			e.printStackTrace();
		}
		return xqmc.get(0);
		
	}
}
