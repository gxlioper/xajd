package xgxt.qgzx.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.GetTime;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-03-16</p>
 */
public class QgzxShgcDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	
	private StringBuffer getWhereSql(QgzxForm model){
		String yrdwdm = model.getYrdwdm();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String xn = model.getXn();
		String nd = model.getNd();
		String nj = model.getNj();
		String xmdm = model.getXmdm();
		String gwxz = model.getGwxz();
		String xydm = model.getXydm();
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(yrdwdm != null && !"".equalsIgnoreCase(yrdwdm)){
			sb.append(" and yrdwdm=? ");
			value.add(yrdwdm);
		}
		if(kssj != null && !"".equalsIgnoreCase(kssj)){//模糊查询
			sb.append(" and kssj like ?||'%' ");
			value.add(kssj.replaceAll("-", ""));
		}
		if(jssj != null && !"".equalsIgnoreCase(jssj)){//模糊查询
			sb.append(" and jssj like chr(39)||?||chr(39)||'%' ");
			value.add(jssj.replaceAll("-", ""));
		}	
		if(xn != null && !"".equalsIgnoreCase(xn)){
			sb.append(" and xn =? ");
			value.add(xn);
		}
		if(nd != null && !"".equalsIgnoreCase(nd)){
			sb.append(" and nd =? ");
			value.add(nd);
		}
		if(nj != null && !"".equalsIgnoreCase(nj)){
			sb.append(" and nj =? ");
			value.add(nj);
		}
		if(xydm != null && !"".equalsIgnoreCase(xydm)){
			sb.append(" and xydm =? ");
			value.add(xydm);
		}
		if(xmdm != null && !"".equalsIgnoreCase(xmdm)){
			sb.append(" and gwdm =? ");
			value.add(xmdm);
		}
		if(gwxz != null && !"".equalsIgnoreCase(gwxz)){
			sb.append(" and gwxz =? ");
			value.add(gwxz);
		}
		
		return sb;
	}
			
	/**
	 * 检测记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result)>0 ? true : false;
	}
	/**
	 * 获取所有的用人单位列表
	 * @return List
	 * */
	public List getYrdwList(){
		return getList("select distinct yrdwdm,yrdwmc from yrdwdmb order by yrdwdm", new String[]{}, new String[]{"yrdwdm","yrdwmc"});
	}
	
	/**
	 * 获取用人单位考核上报时间设置信息
	 * @param model
	 * @return List
	 * */
	public List getYrdwkhsbsj(QgzxForm model){
		String sql = "select yrdwdm, yrdwmc, kssj, jssj,dlm from view_yrdwkhsbsj ";
		String[] output = {"yrdwdm", "yrdwmc", "kssj", "jssj", "dlm"};
		
		sql += getWhereSql(model).toString();//查询条件
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, output);	
	}
	
	/**
	 * 根据用人单位查询考核上报时间信息
	 * @param pk
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getKhsbsjOfYrdw(String pk){
		String[] outputValue = {"kssj1","kssj2","kssj3","kssj4","jssj1","jssj2","jssj3","jssj4"};
		String sql = "select strtodatetime(substr(kssj,1,8)) kssj1,"
			+ "substr(kssj,9,2) kssj2," + "substr(kssj,11,2) kssj3,"
			+ "substr(kssj,13,2) kssj4,"
			+ "strtodatetime(substr(jssj,1,8)) jssj1,"
			+ "substr(jssj,9,2) jssj2," + "substr(jssj,11,2) jssj3,"
			+ "substr(jssj,13,2) jssj4 from  view_yrdwkhsbsj "
			+ "where yrdwdm=?";
		pk = pk.split("!!")[1];
		return  getMap(sql, new String[]{pk}, outputValue);
	}
	
	/**
	 * 查询学生工作时间考核情况信息
	 * @param model
	 * @return List
	 * */
	public List getXskhInfo(QgzxForm model){
		String yf = GetTime.getNowMonth();
		if(yf != null && yf.length()==1){
			yf = "0"+yf;
		}
		String sql = "select * from (select a.xn, a.nd, a.gwdm,a.gzbx,a.yrdwdm,a.xydm,a.nj,c.gwxz,(select distinct xqmc from xqdzb b where a.xq=b.xqdm)xqmc,(select gzsj from xsgzkhb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.sbsj and b.yf='" + yf + "')gzsj," +
				"a.xh,a.xm,a.bjmc,a.sqsj,(select gzqk from xsgzkhb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.sbsj and b.yf='" + yf + "')gzqk," +
				"" + yf + " yf from view_xsgwxx a, view_gwxx c where a.xyyj='通过' and a.xxyj='通过' and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj)";
		
		String[] outputValue = {"xn","nd","yf","gwdm","xh","xm","bjmc","sqsj","gzsj","gzbx","gzqk"};
		return rsToVator(sql+getWhereSql(model).toString(), value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 根据代码查询名称
	 * @param tableName
	 * @param pk
	 * @param value
	 * @param str
	 * @return String
	 * */
	public String getXmmc(String tableName, String pk, String str, String value){
		String sql = "select distinct " + str + " from " + tableName + " where " + pk + "=?"  ;
		return getOneRs(sql, new String[]{value}, str);
	}
}
