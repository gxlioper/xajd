package xgxt.xsxx.comm.jbxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;

public class XsxxJbxxDAO {
	
	/**
	 * 获取下拉列表信息
	 * @param XsxxJbxxForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXlkxx(XsxxJbxxForm model){
		
		DAO dao=DAO.getInstance();
		//表名
		String tableName=model.getTableName();
		//字段代码
		String zddm=model.getZddm();
		//字段名称
		String zdmc=model.getZdmc();
		
		String sql=" select distinct("+zddm+") dm, "+zdmc+" mc from "+tableName;
		
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		
	}
	
	/**
	 * 获取下拉列表字段
	 * @param XsxxJbxxForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXllbList(XsxxJbxxForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select b.zd,b.lyb,b.mc,b.dm,lydmwh from ");
		sql.append(" (select * from xg_xsxx_zdszb where (lrxs='下拉列表' or lrxs='单选框') and sfqy='是') ");
		sql.append(" a ,xg_xsxx_zdlyb b where a.zd=b.zd and a.lyb=b.lyb ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"zd", "lyb", "dm", "mc","lydmwh"});
	}
	
	/**
	 * 获取学生相关信息
	 * @param model
	 * @return HashMap<String,String>
	 * @author qlj
	 */
	public HashMap<String,String>getXsxgxx(XsxxJbxxForm model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		//需显示字段
		StringBuilder zd=new StringBuilder();
		List<HashMap<String,String>>xszdList=getXszdxx(model);
		//判断需显示的字段是否大于0
		if(xszdList.size()==0){
			return null;
		}
		
		List<String>outPut=new ArrayList<String>();
		for(int i=0;i<xszdList.size();i++){
			HashMap<String,String>xszdMap=xszdList.get(i);
			if(i==0){
				zd.append("a."+xszdMap.get("zd"));
			}else{
				zd.append(",a."+xszdMap.get("zd"));
			}
			outPut.add(xszdMap.get("zd"));
			if("jg".equalsIgnoreCase(xszdMap.get("zd"))
					&& !Base.isNull(xszdMap.get("lyb"))){
				outPut.add("jgmc");
				zd.append(",a.jgmc ");
			}else if("hkszd".equalsIgnoreCase(xszdMap.get("zd"))
					&& !Base.isNull(xszdMap.get("lyb"))){
				outPut.add("hkszdmc");
				zd.append(",a.hkszdmc ");
			}else if("syd".equalsIgnoreCase(xszdMap.get("zd"))
					&& !Base.isNull(xszdMap.get("lyb"))){
				outPut.add("sydmc");
				zd.append(",a.sydmc ");
			}
		}
		sql.append(" select "+zd+" from view_xsxxb a  where a.xh=? ");

		return dao.getMap(sql.toString(), new String[]{model.getXh()}, outPut.toArray(new String[]{}));
	}
	
	public List<HashMap<String,String>>getXszdxx(XsxxJbxxForm model){
		DAO dao=DAO.getInstance();
		
		String sql=" select a.zd,b.lyb from xg_xsxx_xsqwzb a,xg_xsxx_zdszb b where a.zd=b.zd  ";
		
		return dao.getList(sql, new String[]{}, new String[]{"zd","lyb"});
	}
	
	/**
	 * 获取学生详细页信息
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXxyxx(XsxxJbszForm model) {
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		StringBuilder query = new StringBuilder();
		
		sql.append(" select xsqdm,zd,zdszh,zdszl,zdm,lrxz,wkxz,lrxs,xgwz,zszl, ");
		sql.append(" zszh,sfqy,sfzk,sfzd,lyb,lydmwh,case when lydmwh ='yes'  ");
		sql.append(" or lyb is null  then zd else  mc end mc from(  ");
		sql.append(" select a.xsqdm,a.zd,a.zdszh,a.zdszl,a.zdm, ");
		sql.append(" a.lrxz,a.wkxz,a.lrxs,a.xgwz,a.zszl,a.zszh,a.sfqy,a.sfzk,a.sfzd,b.mc, b.lyb,b.lydmwh ");
		sql.append(" from xg_view_xsxx_xsqzd a ");
		sql.append(" left join xg_xsxx_zdlyb b on a.zd = b.zd) where 1=1 ");
		
		if (!Base.isNull(model.getSearch_sfqy())) {
			query.append(" and  sfqy='" + model.getSearch_sfqy() + "' ");
		}

		query.append(" order by xsqdm,zdszh,zdszl ");

		ArrayList<String> inPutList = new ArrayList<String>();

		String[] colList = new String[] { "xsqdm", "zd", "zdm", "zdszh",
				"zdszl", "lrxz", "wkxz", "lrxs", "sfqy", "sfzd", "sfzk","mc" };
		

		return dao.getList(sql.toString(), new String[]{}, colList);
	}
}
