
package xgxt.qgzx.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import common.Globals;

public class QgzxDao extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 组合查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getWhereSql(CommanForm model){
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = DealString.toGBK(model.getXh());
		String xm = DealString.toGBK(model.getXm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String yrdwdm = model.getYrdwdm();
		String gwxz = model.getGwxz();
		String gwdm = model.getGwdm();
		String sfyx = model.getSfyx();
		String gwflag = model.getGwflag();
		
		model.setXh(xh);
		model.setXm(xm);
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(zydm !=null && !zydm.equalsIgnoreCase("")){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(bjdm !=null && !bjdm.equalsIgnoreCase("")){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xh !=null && !xh.equalsIgnoreCase("")){
			sb.append( " and xh=?");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm=?");
			value.add(xm);
		}
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(xq !=null && !xq.equalsIgnoreCase("")){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(yrdwdm !=null && !yrdwdm.equalsIgnoreCase("")){
			sb.append( " and yrdwdm=?");
			value.add(yrdwdm);
		}
		if(StringUtils.isNotNull(gwxz)){
			sb.append( " and gwxz=?");
			value.add(gwxz);
		}
		if(StringUtils.isNotNull(gwflag)){
			sb.append( " and gwflag=?");
			value.add(gwflag);
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm like '%'||?||'%'");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(sfyx)){
			sb.append( " and sfyx=?");
			value.add(sfyx);
		}
		return sb;
	}
	
	/**
	 * 组合岗位列表查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	public StringBuffer getConditionOfGwmc(CommanForm model,String isLsgw){
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String yrdwdm = model.getYrdwdm();
		String gwdm = model.getGwdm();
		String xueqi = model.getXueqi();
		String gwfbr = model.getGwfbr();
		String gwxz = model.getGwxz();
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(!StringUtils.isNull(xn)){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(xueqi)){
			sb.append( " and xueqi=?");
			value.add(xueqi);
		}
		if(!StringUtils.isNull(yrdwdm)){
			sb.append( " and sqdw=?");
			value.add(yrdwdm);
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm=?");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(gwxz)){
			sb.append( " and gwxz=?");
			value.add(gwxz);
		}
		if(!StringUtils.isNull(gwfbr)){
			sb.append( " and gwfbr=?");
			value.add(gwfbr);
		}
		
		if (Globals.XXDM_GZDX.equals(Base.xxdm) && "yes".equals(isLsgw)) {
			sb.append( " and gwxz<>(select gwxzdm from gwxzdmb where gwxzmc='临时岗位')");
		}
		
		return sb;
	}
	
	/**
	 * 组合岗位列表查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getConditionOfStuGw(CommanForm model){		
		String xh = DealString.toGBK(model.getXh());
		String xm = DealString.toGBK(model.getXm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String yrdwdm = model.getYrdwdm();
		String gwdm = model.getGwdm();
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh=?");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm=?");
			value.add(xm);
		}
		if(!StringUtils.isNull(xn)){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(xq)){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(!StringUtils.isNull(yrdwdm)){
			sb.append( " and yrdwdm=?");
			value.add(yrdwdm);
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm=?");
			value.add(gwdm);
		}
		return sb;
	}
	
	
	/**
	 * 根据申请单位获取岗位列表
	 * @param sqdwdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwmcList(String sqdwdm){
		List<HashMap<String, String>> gwmcList = null;
		String sql = "select distinct gwdm||'-'||gwsbsj gwdm, a.gwdm gwmc from view_gwxx a where a.shjg='通过' and a.sqdw=?";
		gwmcList = getList(sql, new String[]{sqdwdm}, new String[]{"gwdm","gwmc"});
		return gwmcList;
	}
	
	/**
	 * 根据申请单位获取岗位列表
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwGwList(String userName,String sqdwdm, String gwxzdm, boolean shFlag){
		String[] inputList = {};
		String sql = "select distinct gwdm||'-'||gwsbsj gwdm, a.gwdm gwmc from view_gwxx a where 1=1";
		
		if(shFlag){//查询审核通过的岗位
			sql += " and a.shjg='通过'";
		}
		if(!Base.isNull(sqdwdm)){
			sql += " and a.sqdw=?";
			inputList = new String[]{sqdwdm};
		}
		if(StringUtils.isNotNull(gwxzdm)){
			sql += " and gwxz='" + gwxzdm + "'";			
		}
		if(isYrdwUser(userName)){
			sql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
		}
		return getList(sql, inputList, new String[]{"gwdm","gwmc"});
	}
	
	/**
	 * 获取非用户发布的岗位
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List
	 * */
	public List<HashMap<String, String>> getNotUserGwList(String userName,String sqdwdm, String gwxzdm, boolean shFlag){
		String[] inputList = {};
		String sql = "select distinct gwdm||'-'||gwsbsj gwdm, a.gwdm gwmc from view_gwxx a where 1=1";
		
		if(shFlag){//查询审核通过的岗位
			sql += " and a.shjg='通过'";
		}
		if(!Base.isNull(sqdwdm)){
			sql += " and a.sqdw=?";
			inputList = new String[]{sqdwdm};
		}
		if(StringUtils.isNotNull(gwxzdm)){
			sql += " and gwxz='" + gwxzdm + "'";			
		}
		if(!Base.isNull(userName)){
			sql += " and (gwfbr <>'" + userName + "' or gwfbr is null)";
		}
		return getList(sql, inputList, new String[]{"gwdm","gwmc"});
	}
	
	public HashMap<String, String> getGwrsxx(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select count(*) count from view_xsgwxx where xxyj='通过' and gwdm||'-'||gwsbsj=?";		
		map.put("tgrs", getOneRs(sql, new String[]{pkValue}, "count"));
		sql = "select count(*)count from view_xsgwxx where gwdm||'-'||gwsbsj=?";
		map.put("sqrs", getOneRs(sql, new String[]{pkValue},"count"));
		return map;
	}
	
	
	/**
	 * 根据岗位名称查询负责人和联系电话
	 * @param pkValue
	 * @return HashMap
	 * */
	public HashMap<String, String> getPrincipalInfo(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select lxr fzr,lxdh from yrdwdmb where yrdwdm=?";
		map = getMap(sql,new String[]{pkValue}, new String[]{"fzr","lxdh"});
		return map;
	}
	
	/***************************************************************************
	 * 获取岗位调整前的岗位信息
	 * 
	 * @param pkValue
	 * @return ArrayList
	 **************************************************************************/
	public ArrayList<HashMap<String, String>> getTzqgw(String pkValue) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "select rownum ,a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,"
				+ "(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,"
				+ "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,"
				+ "(select v.yhmc from view_xsxxb v where a.xh = v.xh) yhmc,"
				+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) yhkh,"
				+ "a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.nd,a.yf,a.dqn,a.dqy,a.cjje,a.bz ,a.gzsj,a.khqk,a.kh from ("
				+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh"
				+ " from ("
				+ "select b.xh,substr(b.tzsj,0,4) tzn,substr(b.tzsj,6,2)tzy,b.tzqgw ,b.tzqgwsbsj,"
				+ "(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)dqn,"
				+ "(select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) from dual)dqy"
				+ " from qgzx_gwtzb b where not exists(select a.xh from xsgwxxb a where a.xh=b.xh and a.gwdm=b.tzqgw and a.gwsbsj=b.tzqgwsbsj)"
				+ " and tzqgw||tzqgwsbsj='"
				+ pkValue
				+ "'"
				+ ")a left join xscjffb b on a.xh=b.xh and a.tzqgwsbsj=b.sqsj and a.tzqgw=b.gwdm and b.nd=a.dqn and b.yf=a.dqy left join khxgsqb c on c.xh=b.xh"
				+ ") a where dqn=tzn and dqy=tzy";
		list = getArrayList(sql, new String[] {}, new String[] { "rownum",
				"xh", "xm", "bjdm", "cjje", "gzsj", "khqk", "kh", "bz", "yhmc",
				"yhkh" });
		return list;
	}	
	
	/***************************************************************************
	 * 获取岗位调整前的岗位信息（浙江科技--只显示有工作考核的学生）
	 * 
	 * @param pkValue
	 * @return ArrayList
	 **************************************************************************/
	public ArrayList<HashMap<String, String>> queryTzqxsgwForZjkj(String pkValue) {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//		String sql = "select rownum,a.xh,a.xm,a.bjmc,a.bjdm,a.yhmc,a.yhkh,a.tzqgw,a.tzqgwsbsj,a.tzn,a.tzy,a.dqn,a.dqy,a.nd,a.yf,(case when a.cjje is null and a.gzsj is null then to_char(b.ygzsj) else to_char(a.gzsj) end)gzsj,(case when a.cjje is null and a.gzsj is null then to_char(b.ffcjje) else to_char(a.cjje) end)cjje,a.bz,a.khqk,a.kh,b.ygzsj,b.ffcjje from(select rownum ,a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,"
//				+ "(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,"
//				+ "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,"
//				+ "(select v.yhmc from view_xsxxb v where a.xh = v.xh) yhmc,"
//				+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) yhkh,"
//				+ "a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.nd,a.yf,a.dqn,a.dqy,a.cjje,a.bz ,a.gzsj,a.khqk,a.kh from ("
//				+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh"
//				+ " from ("
//				+ "select b.xh,substr(b.tzsj,0,4) tzn,substr(b.tzsj,6,2)tzy,b.tzqgw ,b.tzqgwsbsj,"
//				+ "(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)dqn,"
//				+ "(select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) from dual)dqy"
//				+ " from qgzx_gwtzb b where not exists(select a.xh from xsgwxxb a where a.xh=b.xh and a.gwdm=b.tzqgw and a.gwsbsj=b.tzqgwsbsj)"
//				+ " and tzqgw||tzqgwsbsj='"
//				+ pkValue
//				+ "'"
//				+ ")a left join xscjffb b on a.xh=b.xh and a.tzqgwsbsj=b.sqsj and a.tzqgw=b.gwdm and b.nd=a.dqn and b.yf=a.dqy left join khxgsqb c on c.xh=b.xh"
//				+ ") a where dqn=tzn and dqy=tzy) a,qgzx_xsgzkhb b where a.tzqgw=b.gwdm and a.tzqgwsbsj=b.gwsbsj and a.xh=b.xh and b.nd=a.tzn and b.yf=a.tzy";
		String sql = "select rownum,a.xh,a.xm,a.bjmc,a.bjdm,a.yhmc,a.yhkh,a.tzqgw,a.tzqgwsbsj,a.tzn,a.tzy,a.dqn,a.dqy,a.nd,a.yf,a.gzsj,a.cjje,a.bz,a.khqk,a.kh,b.ygzsj,b.ffcjje from(select rownum ,a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,"
			+ "(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,"
			+ "(select b.bjdm from view_xsjbxx b where a.xh=b.xh)bjdm,"
			+ "(select v.yhmc from view_xsxxb v where a.xh = v.xh) yhmc,"
			+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) yhkh,"
			+ "a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.nd,a.yf,a.dqn,a.dqy,a.cjje,a.bz ,a.gzsj,a.khqk,a.kh from ("
			+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh"
			+ " from ("
			+ "select b.xh,substr(b.tzsj,0,4) tzn,substr(b.tzsj,6,2)tzy,b.tzqgw ,b.tzqgwsbsj,"
			+ "(select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) from dual)dqn,"
			+ "(select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) from dual)dqy"
			+ " from qgzx_gwtzb b where not exists(select a.xh from xsgwxxb a where a.xh=b.xh and a.gwdm=b.tzqgw and a.gwsbsj=b.tzqgwsbsj)"
			+ " and tzqgw||tzqgwsbsj='"
			+ pkValue
			+ "'"
			+ ")a left join xscjffb b on a.xh=b.xh and a.tzqgwsbsj=b.sqsj and a.tzqgw=b.gwdm and b.nd=a.dqn and b.yf=a.dqy left join khxgsqb c on c.xh=b.xh"
			+ ") a where dqn=tzn and dqy=tzy) a,qgzx_xsgzkhb b where a.tzqgw=b.gwdm and a.tzqgwsbsj=b.gwsbsj and a.xh=b.xh and b.nd=a.tzn and b.yf=a.tzy";
		list = getArrayList(sql, new String[] {}, new String[] { "rownum",
				"xh", "xm", "bjdm", "cjje", "gzsj", "khqk", "kh", "bz", "yhmc",
				"yhkh","ygzsj","ffcjje" });
		return list;
	}
	
	
	/**
	 * 获取岗位调整前的岗位信息
	 * @param pkValue
	 * @return ArrayList
	 * **/
	public ArrayList<HashMap<String, String>> getTzqgw(String pkValue,String nd,String yf){
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();		
		String sql = "select rownum ,a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.nd,a.yf,a.dqn,a.dqy,a.cjje,a.bz ,a.gzsj,a.khqk,a.kh from (" + 
			        "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,decode(b.gzsj,'',(select ygzsj from view_qgzx_xsgzkhb c where a.tzqgw=c.gwdm and a.tzqgwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"+nd+"' and c.yf='"+yf+"'),b.gzsj)gzsj,decode(b.cjje,'',(select ffcjje from view_qgzx_xsgzkhb c where a.tzqgw=c.gwdm and a.tzqgwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"+nd+"' and c.yf='"+yf+"'),b.cjje)cjje,b.bz,b.khqk ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh" +   
			        " from (" + 
			        "select b.xh,substr(b.tzsj,0,4) tzn,substr(b.tzsj,6,2)tzy,b.tzqgw ,b.tzqgwsbsj," + 
			        nd + " dqn," + 
			        yf+ " dqy" +  
			        " from qgzx_gwtzb b where not exists(select a.xh from xsgwxxb a where a.xh=b.xh and a.gwdm=b.tzqgw and a.gwsbsj=b.tzqgwsbsj)" +  
			        " and tzqgw||tzqgwsbsj='" + pkValue + "'" +
			        ")a left join xscjffb b on a.xh=b.xh and a.tzqgwsbsj=b.sqsj and a.tzqgw=b.gwdm and b.nd=a.dqn and b.yf=a.dqy left join khxgsqb c on c.xh=b.xh" +  
			        ") a where dqn=tzn and dqy=tzy";
		list = getArrayList(sql, new String[]{}, new String[]{"rownum","xh","xm","bjdm","cjje","gzsj","khqk","kh","bz"});
		return list;
	}
	
	/**
	 * 按某个表的主键查询记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean isExist(String tableName, String pk, String pkValue){
		String sql = "select count(*) cnum from " + tableName + " where " + pk + "='" + pkValue + "'";
		int iCount = Integer.parseInt(getOneRs(sql, new String[]{}, "cnum"));		
		
		return iCount>0 ? true : false ;
	}
	
	//井冈山大学 liang（根据岗位返回相应的要求信息列表）
	public String[] getSqtjString(String pk,String pkValue) {
		String[] returnStr = new String[2];
		String sql = "select * from jgszdshb where 1=2";
		String[] columnsNameString = getColumnNameCN(getColumnName(sql), "jgszdshb");
		String judgeExists = "select count(*) num from jgszdshb where " + pk + "='" + pkValue + "'";
		String[] num = getOneRs(judgeExists, new String[] {}, new String[] {"num"});
		if(num[0].equalsIgnoreCase("0")) {
			returnStr[0] = "没有条件限制";
			returnStr[1] = "0";
			return returnStr;
		}
		String jgsTempSQLString = "select * from jgszdshb where " + pk + "='" + pkValue + "'";
		String[] outputValue = {"WSCF","CXKM","QKS","DY","TY","DJSJ","NNS","NS"};
		String[] jgsTempSQL =  getOneRs(jgsTempSQLString, new String[] {}, outputValue);
		int count = 0;
		StringBuffer returnString = new StringBuffer();
		for(int i=0;i<columnsNameString.length-3;i++) {
			if(jgsTempSQL[i].equalsIgnoreCase("1")) {
				returnString.append((++count) + ", " + columnsNameString[i] + "   ");
			}			
		}
		returnStr[0] = returnString.toString();
		//判断是否是有“懂计算机”的条件,如果是一，那么将数组returnStr的【1】设置为1，作为标志
		if(jgsTempSQL[5].equalsIgnoreCase("1")) {
			returnStr[1] = "1";
		}else {
			returnStr[1] = "0";
		}
		return returnStr;
	}
	
	//井冈山大学 liang 将审核信息全部返回，供检验学生的申请条件判断
	public List<HashMap<String,String>> getSqtjList(String pkValue) {
		List<HashMap<String,String>> returnValue = new ArrayList<HashMap<String,String>>();
		String sql = "select * from jgszdshb where 1=2";
		String[] columnsNameString = getColumnNameCN(getColumnName(sql), "jgszdshb");
		//String judgeExists = "select count(*) num from jgszdshb where GWDM||'-'||GWSBSJ='" + pkValue + "'";
		String jgsTempSQLString = "select * from jgszdshb where GWDM||'-'||GWSBSJ='" + pkValue + "'";
		String[] outputValue = {"WSCF","CXKM","QKS","DY","TY","DJSJ","NNS","NS"};
		String[] jgsTempSQL =  getOneRs(jgsTempSQLString, new String[] {}, outputValue);
		if(jgsTempSQL!=null && jgsTempSQL.length>0){
		for(int i=0;i<columnsNameString.length-3;i++) {
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("tjxx",jgsTempSQL[i]==null ? "" : jgsTempSQL[i]);
			map.put("columnName", columnsNameString[i]==null ? "" : columnsNameString[i]);
			returnValue.add(map);
		}
		}
		return returnValue;
	}
	//井冈山大学 liang 检验学生是否符合申请条件
	public String isStudentCondOK(String xh,String pkValue,String djsj) {
		int count = 0;
		StringBuffer sb = new StringBuffer();
//		sb.append("同学不符合的标准有： \n");
		String[] outputValue = new String[]{"XH","ZZMM","XB"};
		String jgsSql = "select * from view_jgszdsh where xh=?";
		String[] studentInfo = getOneRs(jgsSql, new String[] {xh}, outputValue);
		List<HashMap<String,String>> mapList = getSqtjList(pkValue);
		// 判断1：近一学年来未受处分 
		if(mapList!=null && mapList.size()>0){
		if(mapList.get(0).get("tjxx").equalsIgnoreCase("1")) {
			//表明学生没有在一年内被处分
			if(studentInfo != null) {
				sb.append(++count + ": " + mapList.get(0).get("columnName") +"\n");
			}else {
				//再判断学生有没有在视图[view_wjcf]里
				String wjcfSQL = "select * from view_wjcf where xh=?";
				studentInfo = getOneRs(wjcfSQL, new String[] {xh}, new String[] {"XH"});
				//如果在这个视图里，则不符合这个标准，【否则通过】
				if(studentInfo != null) {
					sb.append(++count + ": " + mapList.get(0).get("columnName") +"\n");
				}
			}
		}		
		//判断2：没有重修课目
		if(mapList.get(1).get("tjxx").equalsIgnoreCase("1")) {
			String cxcj = getOneRs("select count(cxcj) count from cjb where xh=? and cxcj is not null", new String[]{xh}, "count");
			if(cxcj!=null && !"".equalsIgnoreCase(cxcj)){
				sb.append(++count + ": " + mapList.get(1).get("columnName") +"\n");
			}
		}
		//判断3：贫困或特困生
		if(mapList.get(2).get("tjxx").equalsIgnoreCase("1")) {
			if(!isKns(xh)) {
				//如果不是贫困生，不符合
				sb.append(++count + ": " + mapList.get(2).get("columnName") +"\n");
			}
		}
		String xsxxSQL = "select * from view_xsxxb where xh=?";
		studentInfo = getOneRs(xsxxSQL, new String[] {xh}, outputValue);
		//判断4：党员
		if(mapList.get(3).get("tjxx").equalsIgnoreCase("1")) {
			if(studentInfo[1]==null || !studentInfo[1].equalsIgnoreCase("01")) {
				sb.append(++count + ": " + mapList.get(3).get("columnName") +"\n");
			}
		}
		//判断5：团员
		if(mapList.get(4).get("tjxx").equalsIgnoreCase("1")) {
			if(studentInfo[1]!=null && !studentInfo[1].equalsIgnoreCase("02")) {
				sb.append(++count + ": " + mapList.get(4).get("columnName") +"\n");
			}
		}
		//判断6：懂计算机
		if(mapList.get(5).get("tjxx").equalsIgnoreCase("1")) {
			if(djsj!=null && djsj.equals("0")) {
				sb.append(++count + ": " + mapList.get(5).get("columnName") +"\n");
			}
		}
		//判断7：男生
		if(mapList.get(6).get("tjxx").equalsIgnoreCase("1")) {
			if(!studentInfo[2].equalsIgnoreCase("男")) {
				sb.append(++count + ": " + mapList.get(6).get("columnName") +"\n");
			}
		}
		//判断8：女生
		if(mapList.get(7).get("tjxx").equalsIgnoreCase("1")) {
			if(!studentInfo[2].equalsIgnoreCase("女")) {
				sb.append(++count + ": " + mapList.get(7).get("columnName") +"\n");
			}
		}
		}
		if(count == 0) {
			//表示合格
			return "0";
		}else {
			return sb.toString() ;//+ "\n很有可能不会通过，是否继续申请!";
		}
	
	}
	//井冈山大学 liang 判断在该岗位上有没有学生已经在工作【工作：即这些学生在该岗位上是审核通过的】
	public boolean isHaveStuOkByJob(String pk) {
		////////////////////////////////////////
		return false;
	}
	//井冈山大学 liang 判断岗位是否已经通过
	public boolean isJobOk(String pk) {
		String sql = "select * from gwxxb where gwdm||'-'||gwsbsj=? and SHJG='通过'";
		String[] oneRes = getOneRs(sql, new String[] {pk}, new String[] {"gwdm"});
		return (oneRes != null) ? true : false;
	}
	//井冈山大学 liang 自动审核通过的学生的审核状态的人数是否已经超过了岗位的额定人数
	public boolean isStuOkNumBeyondStandard(String pk) {
		String sql = "select * from  gwxxb where gwdm||'-'||gwsbsj=?";
		String[] oneRes = getOneRs(sql, new String[] {pk}, new String[] {"xyrs"});
		//System.out.println(oneRes[0]);
		//System.out.println(getStuOkNumByJob(pk));
		return (getStuOkNumByJob(pk) > Integer.parseInt(oneRes[0])) ? true : false;
	}
	//井冈山大学 liang 返回某岗位学生满足要求的人数
	public int getStuOkNumByJob(String pk) {
		int stuOK = 0;
		String sql = "select xh,SFDJSJ from xsgwxxb where gwdm||'-'||gwsbsj=?";
		List<HashMap<String,String>> stuList = getList(sql, new String[] {pk}, new String[] {"XH","SFDJSJ"});
		for(HashMap<String,String> map : stuList){
			if(map.get("SFDJSJ") != null && map.get("SFDJSJ").equalsIgnoreCase("on")){
				stuOK = (isStudentCondOK(map.get("XH"),pk,"1").equalsIgnoreCase("0")) ? (++stuOK) : stuOK;
			}else{
				stuOK = (isStudentCondOK(map.get("XH"),pk,"0").equalsIgnoreCase("0")) ? (++stuOK) : stuOK;
			}
		}
		return stuOK;
	}
	/**
	 * 用人单位(学校)审核学生
	 * @author liang
	 * @param jobPk
	 * @param sb
	 * @param request
	 * @throws Exception
	 */
	public void updateCheckStu(String jobPk,StringBuffer sb,HttpServletRequest request,String shenfen,String leibie)
		throws Exception{
		if(sb != null){
			String sfStr = " and 1=1";
			if(shenfen.equalsIgnoreCase("XXYJ") && leibie.equalsIgnoreCase("3")){
				//学校
				sfStr = " and xyyj='通过'";
			}
			String tempSql = "update xsgwxxb  set " + shenfen + " ='通过' where gwdm||gwsbsj='" +  jobPk + "'"
			+ " and xh in (" + sb.toString() + ")" + sfStr;	
			StandardOperation.update("xsgwxxb", tempSql, request);
			tempSql = "update xsgwxxb set " + shenfen + " ='不通过' where gwdm||gwsbsj='" +  jobPk + "'"
			+ " and " + shenfen + " ='未审核'";
			StandardOperation.update("xsgwxxb", tempSql, request);
		}	
	}
	/**
	 * 提取满足岗位要求的学生的学号列表
	 * @author liang
	 * @param pk
	 * @return
	 */
	public List<String> getOKStuid(String pk){
		List<String> retuList = new ArrayList<String>();
		String sql = "select xh,SFDJSJ from xsgwxxb where gwdm||'-'||gwsbsj=? order by sqsj";
		List<HashMap<String,String>> stuList = getList(sql, new String[] {pk}, new String[] {"XH","SFDJSJ"});
		for(HashMap<String,String> map : stuList){
			if(map.get("SFDJSJ") != null && map.get("SFDJSJ").equalsIgnoreCase("on")){
				if(isStudentCondOK(map.get("XH"),pk,"1").equalsIgnoreCase("0"))
					retuList.add(map.get("XH"));
			}else{
				if(isStudentCondOK(map.get("XH"),pk,"0").equalsIgnoreCase("0"))
					retuList.add(map.get("XH"));
			}
		}
		return retuList;
	}
	/**
	 * 长沙民政学院 检验学生学号的存在,如果存在，就返回学生的信息
	 * @author liang
	 * @param studentid
	 * @param gwdm
	 * @param gwsbsj
	 * @return String[]
	 */
	public String[] isStudentExists(String studentid,String gwdm,String gwsbsj) {
		String[] outpuValue = {"XH","XM","NJ","XYMC","ZYMC","BJMC"};
		String sql = "select * from view_xsjbxx where XH=?" ;
		String[] isExistsList = getOneRs(sql, new String[] {studentid}, outpuValue);
		if(isExistsList == null) {
			return new String[] {"0"};
		}
		sql = "select count(*) count from view_xsgwxx where xh=? and gwdm=? and gwsbsj=?";
		int iCount = Integer.parseInt(getOneRs(sql, new String[]{studentid,gwdm,gwsbsj}, "count"));
		if(iCount>0){
			return new String[]{"1"};
		}		
		return isExistsList;
	}
	/**
	 * 长沙民政学院  判断岗位是否是"志愿服务的"，如果岗位的报酬是0，那么就认为该岗位是"志愿服务"的
	 * @author liang
	 * @param pkValue
	 * @return
	 */
	public String isGwZyfw(String pkValue) {
		String jgsTempSQLString = "select * from gwxxb where GWDM||'-'||GWSBSJ='" + pkValue + "'";
		String[] bc = getOneRs(jgsTempSQLString, new String[] {}, new String[] {"SPBCBZ"});
		if(bc[0].equalsIgnoreCase("0")) {
			return "1";
		}
		return "0";
	}
	/**
	 * 获取查询岗位信息的主键 gwdm||gwsbsj 
	 * @param pk
	 * @param pkValue
	 * @return String
	 * */
	public String getGwPk(String pk,String pkValue){
		String sVal = "";
		String sql = "select gwdm||gwsbsj str from view_gwxx where gwdm=(select gwdm from view_xsgwxx where "+pk+"='"+pkValue+"')";
		sVal = getOneRs(sql, new String[]{}, "str");
		return sVal;
	}
	
	/**
	 * 获取查询岗位信息的主键 gwdm||gwsbsj 
	 * @param pk
	 * @param pkValue
	 * @return String
	 * */
	public String getGwPk(String pk,String pkValue,String symbol){
		String sVal = "";
		String sql = "select gwdm||'" + symbol + "'||gwsbsj str from view_gwxx where gwdm=(select gwdm from view_xsgwxx where "+pk+"='"+pkValue+"')";
		sVal = getOneRs(sql, new String[]{}, "str");
		return sVal;
	}
	
	/**
	 * 批量审核判断是否可以通过
	 * @param pkValue
	 * @return String[]
	 * **/
	public String[] checkPostStuAudi(String pkValue,String userName){	
		String[] pkVal = pkValue.split("!!SplitOneSplit!!");
		String[] value = new String[pkVal.length+1];		
		String sMess = "";
		String xxdm = StandardOperation.getXxdm();
		String xh = "";
		boolean chkFlag = false;
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//中国地质大学
			xh = checkXhRep(pkVal);//检测重复的学号
			chkFlag = Base.isNull(xh) ? false : true;			
		}
		
		for(int i=0;i<pkVal.length; i++){			
			if(Globals.XXDM_HNDX.equalsIgnoreCase(xxdm)){
				//河南大学
				//判断在审核的岗位申请年度、学年、学期是否有岗位已经审核通过
				if(checkPassByXq(pkVal[i],userName)){
					sMess = "一学期只能审核通过一个岗位！";
				}else{
					sMess = checkGwrs(pkVal[i]);	//检测人数是否超限
				}
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//浙江科技学院
				if(checkYjbgwlq(pkVal[i])){
					sMess = "学生已经有岗位审核通过了！";
				}
			}else{				
				if(!chkFlag){//学号无重复
					sMess = checkGwrs(pkVal[i]); //checkGwrsByUser(pkVal[i],shzd);	//检测人数是否超限
				}
			}
			if(sMess!=null && !"".equalsIgnoreCase(sMess)){
				value[i] = sMess + "\n";
			}	
		}
		
		if(chkFlag){//学号有重复
			value[0] = "学号为：" + xh + "的学生不能同时通过一个以上的岗位！";
		}
		return value;
	}
	
	/**
	 * 检测重复的学号
	 * @param String[] pkVal
	 * @return String
	 */	
	public String checkXhRep(String[] pkVal){
		String flag = "";
		String[] xhArr = new String[pkVal.length];
		String sql = "select xh from view_xsgwxx where xh||gwdm||sqsj=?";
		for(int i=0; i<pkVal.length; i++){
			xhArr[i] = getOneRs(sql, new String[]{pkVal[i]}, "xh");
		}
		
		for(int i=0; i<xhArr.length; i++){
			for(int j=i+1; j<xhArr.length-i; j++){
				if(xhArr[i].equalsIgnoreCase(xhArr[j])){
					flag = xhArr[i];
					break;
				}
			}
		}
		return flag;		
	}
	
	/**
	 * 检测学生岗位申请情况
	 * @param pkValue
	 * @return
	 */
	public String checkXsgwsqqk(String pkValue){
		String[] pkVal = pkValue.split("!!SplitOneSplit!!");
		String sql = "select xh from view_xsgwxx where xh||gwdm||sqsj=?";
		String xhString = "";
		for(int i=0; i<pkVal.length; i++){
			String xh = getOneRs(sql, new String[]{pkVal[i]}, "xh");
			String cont = getOneRs("select count(1) cont from view_xsgwxx where xh=? and fdyyj='通过' and xyyj='通过' and xxyj='通过' and xh||gwdm||sqsj<>? ", new String[]{xh,pkVal[i]}, "cont");
			if(!"0".equals(cont)){
				xhString += xh + ",";
			}
		}
		return StringUtil.isNull(xhString) ? "" : ("学生" + xhString.substring(0,xhString.length()-1) + "已经有岗位通过审核");
	}
	
	
	/**
	 * /**
	 * 宁波天一学院检测审核通过人数是否超过分配人数
	 * @param String pkValue
	 * @return String[]
	 * */
	public String[] checkFprs(String pkValue,String userType){
		String[] pkVal = pkValue.split("!!SplitOneSplit!!");
		String[] value = new String[pkVal.length+1];		
		String sMess = "";
		if("xy".equalsIgnoreCase(userType)){
			for(int i=0;i<pkVal.length; i++){
				sMess = checkFprsOfNbtyxy(pkVal[i]);//检测人数是否超限
				if(sMess!=null && !"".equalsIgnoreCase(sMess)){
					value[i] = sMess + "\n";
				}	
			}		
		}
		return value;
	}
	
	/**
	 * 宁波天一学院检测审核通过人数是否超过分配人数
	 * @param String pkValue
	 * @return String
	 * */
	public String checkFprsOfNbtyxy(String pkValue){
		String result = "";
		String sql = "select count(*)num from  view_xsgwxx a where xyyj='通过' and xh||gwdm||sqsj<>? and exists(select 1 from view_xsgwxx b where a.nd=b.nd and a.xn=b.xn and a.xq=b.xq and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.xydm=b.xydm and b.xh||b.gwdm||b.sqsj=?)";
		String num = getOneRs(sql, new String[]{pkValue,pkValue}, "num");
		
		sql = "select xymc,gwdm  from view_xsgwxx where xh||gwdm||sqsj=?";
		HashMap<String,String> map = getMap(sql, new String[]{pkValue}, new String[]{"xymc","gwdm"});
		
		
		sql = "select fprs from qgzx_xyrsb a where exists(select 1 from view_xsgwxx b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and b.xh||b.gwdm||b.sqsj=?)";
		String fprs = getOneRs(sql, new String[]{pkValue}, "fprs");
		
		if(Integer.parseInt(StringUtils.isNull(fprs) ? "0" : fprs) <= Integer.parseInt(StringUtils.isNull(num) ? "0" : num)){
			result = "岗位‘"+map.get("gwdm") + "’" + map.get("xymc") + Base.YXPZXY_KEY+"人数已经满额！";
		}
		return result;
	}
	
	/**
	 * 检测同一学期是否已经有岗位审核通过
	 * @param String pkValue
	 * @return boolean 
	 * */
	public boolean checkPassByXq(String pkValue,String userName){
		if(!checkExists("yrdwdmb", "dlm", userName)){//学校用户审核不判断该条件
			return false;
		}
		String sql  = "select count(*)num from view_xsgwxx a where exists(select 1 from (select xh,xn,nd,xq,xyyj from view_xsgwxx where xh||gwdm||sqsj=?) b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.xyyj='通过' and a.xh||a.gwdm||a.sqsj<>?)";
		String num = getOneRs(sql, new String[]{pkValue,pkValue}, "num");
		return Integer.parseInt(StringUtils.isNull(num) ? "0" : num) >0 ? true : false;
	}
	
	/**
	 * 检测岗位人数是否超限
	 * @param pkValue
	 * @return String
	 * */
	public String checkGwrs(String pkValue){
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		List xhValue = null;
		int iKnsCount = 0;
		String sMess = "";
		
		String sql = "select sqsyrs,sqsyknss,gwdm from view_gwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=?)";
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"sqsyrs","sqsyknss","gwdm"});
		
		//查询学号
		sql = "select xh from xsgwxxb where xh||gwdm||sqsj=?";
		String xh = getOneRs(sql, new String[]{pkValue}, "xh");
		
		//学校审核通过总人数
		sql = "select count(*)count from xsgwxxb where xn||nd||xq||gwdm||gwsbsj=(select xn||nd||xq||gwdm||gwsbsj from xsgwxxb where xh||gwdm||sqsj=?) and xh||gwdm||sqsj<>? and xxyj='通过'";
		map.putAll(dao.getMap(sql, new String[]{pkValue,pkValue}, new String[]{"count"}));
		
		//学校审核通过困难生数
		sql = "select xh from xsgwxxb where xn||nd||xq||gwdm||gwsbsj=(select xn||nd||xq||gwdm||gwsbsj from xsgwxxb where xh||gwdm||sqsj=?) and xh||gwdm||sqsj<>? and xxyj='通过'";
		xhValue = dao.getList(sql, new String[]{pkValue,pkValue}, new String[]{"xh"});
		for(int j=0 ; j<xhValue.size(); j++){
			HashMap<String, String> tmpMap = (HashMap<String, String>)xhValue.get(j);			
			if(dao.isKns(tmpMap.get("xh"))){
				iKnsCount += 1;
			}
		}
		String sqsyrs = map.get("sqsyrs");
		String count = map.get("count");
		String sqsyknss = map.get("sqsyknss");
		
		sqsyrs = sqsyrs == null || "".equalsIgnoreCase(sqsyrs) ? "0" : sqsyrs;
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		sqsyknss = sqsyknss == null || "".equalsIgnoreCase(sqsyknss) ? "0" : sqsyknss;
		//中国地质大学
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			HashMap<String, String> xsgwxx = selectXsgwxxbByPk(pkValue);
			
			//判断是否有岗位审核通过
			sql = "select count(*)num from view_xsgwxx where xh=? and xxyj='通过' and xn=? and nd=? and xq=? and xh||gwdm||sqsj<>?";
		    String result = dao.getOneRs(sql, new String[]{xh,xsgwxx.get("xn"),xsgwxx.get("nd"),xsgwxx.get("xq"),pkValue}, "num");
		    result = StringUtils.isNull(result) ? "0" : result;
			if(Integer.parseInt(result)>0){
				sMess += xh + "审核失败：已经有岗位审核通过！";
			}
		}
		if(Integer.parseInt(sqsyrs)<= Integer.parseInt(count)){
			sMess += xh + "审核失败:岗位" + map.get("gwdm") + "使用人数已满\n";
		}
		if((Integer.parseInt(sqsyrs)-Integer.parseInt(sqsyknss)<= Integer.parseInt(count) - iKnsCount) && Integer.parseInt(sqsyrs) > Integer.parseInt(count)){
			if(!dao.isKns(xh)){
				sMess += xh + "审核失败:岗位" + map.get("gwdm") + ",只能是困难生才可通过!\n";
			}
		}
		
		return sMess;
	}
	
	
	/**
	 * 武汉理工大学获取岗位任职要求信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRzyqInfo(HashMap<String, String> map){
		String PK = "xh||gwdm||sqsj";
		String pkValue = map.get(PK);
		String[] outputValue = {"rzyq_nj","rzyq_xb","rzyq_zy","rzyq_wyyq","rzyq_gzjn","rzyq_qt"};
		String sql = "select rzyq_nj,rzyq_xb,rzyq_zymc rzyq_zy,rzyq_wyyq,rzyq_gzjn,rzyq_qt from view_gwxx a where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=?)";
		
		map.putAll(getMap(sql, new String[]{pkValue}, outputValue));
		return map;
	}
	
	/**
	 * 获取所有的校区列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getXiaoquList(){
		return getList("select distinct dm xqdm,xqmc from dm_zju_xq order by xqdm", new String[]{}, new String[]{"xqdm","xqmc"});
	}                     
	
	/**
	 * 获取所有的岗位性质列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzList() {
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb order by gwxzdm";
		// ---------2010/5/17 edit by luojw ----------
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			// 广州大学
			sql = "select distinct gwxzdm,gwxzmc from gwxzdmb where gwxzmc <> '临时岗位' order by gwxzdm";
		}
		// ---------end----------
		return getList(sql, new String[] {},
				new String[] { "gwxzdm", "gwxzmc" });
	}
	
	/**
	 * 获取非临时岗位的岗位性质列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzCjList() {
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb order by gwxzdm";
		return getList(sql, new String[] {},
				new String[] { "gwxzdm", "gwxzmc" });
		
	}
	
	/**
	 * 获取所有的用人单位列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(){
		return getList("select distinct yrdwdm,yrdwmc from yrdwdmb order by yrdwdm", new String[]{}, new String[]{"yrdwdm","yrdwmc"});
	}
	
	/**
	 * 获取所有的用人单位列表
	 * @param String userName
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(String userName){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		if(checkExists("yrdwdmb", "dlm", userName)){
			list = getList("select distinct yrdwdm,yrdwmc from yrdwdmb where dlm=? order by yrdwdm", new String[]{userName}, new String[]{"yrdwdm","yrdwmc"});
		}else{
			list = getList("select distinct yrdwdm,yrdwmc from yrdwdmb order by yrdwdm", new String[]{}, new String[]{"yrdwdm","yrdwmc"});
		}
		return list;
	}
	
	/**
	 * 获取所有的信用度列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getXydList(){
		return getList("select distinct xyddm,xydmc from xyddmb order by xyddm", new String[]{}, new String[]{"xyddm","xydmc"});
	}	
	
	/**
	 * 获取参数设定信息 
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSqsjInfo(){
		String sql = "select nd,xn,xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,kssj,jssj,knsbl,mxsbc,mtzgxs,myzgxs," 
			         + "myzgbc,xwkssj,xwjssj,myzgsjfs,shkssj,shjssj,ffsjjg," 
			         + "nvl(cjffsj,to_char(sysdate,'yyyymm'))cjffsj,sbsj,sbts," 
			         + "(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,jfglkg," 
			         + "bkkmsxz from gwsqsjb a";
		return getMap(sql, 
				      new String[]{}, 
				      new String[]{"nd", "xn", "xq","xqmc", "kssj", "jssj" ,"knsbl", "mxsbc",
				                   "mtzgxs", "myzgxs", "myzgbc", "xwkssj", "xwjssj", 
				                   "myzgsjfs", "shkssj", "shjssj", "ffsjjg",
				                   "cjffsj", "sbsj", "sbts", "xqmc", "jfglkg",
				                   "bkkmsxz"});
	}
	
	/**
	 * 根据学生岗位信息主键获取岗位的报酬标准
	 * @param pkValue
	 * @return String
	 * */
	public String getBcbzByStuPk(String pkValue){
		String sql = "select spbcbz from view_gwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where xh||gwdm||sqsj=?)";
		String spbcbz = getOneRs(sql, new String[]{pkValue}, "spbcbz");
		spbcbz =  spbcbz == null || "".equalsIgnoreCase(spbcbz) ? "0" : spbcbz;
		return spbcbz;		
	}
	
	/**
	 * 计算总报酬金额
	 * @param spbcbz
	 * @param zsj
	 * @return String
	 * */
	public String getZbcje(String spbcbz,double zsj){
		String sql = "select trunc("+zsj*Integer.parseInt(spbcbz)+",1) je from dual";
		return getOneRs(sql, new String[]{}, "je");
	}
	
	/**
	 * 查询当前学年年度学期月份的酬金发放信息
	 * @return List
	 * */
	public List<HashMap<String, String>> getPayInfo(String nd, String yf) {
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,gwdm,gzsj,cjje,"
				+ "(select kh from view_xsxxb b where a.xh=b.xh)kh,"
				+ "(select v.yhmc from view_xsxxb v where a.xh = v.xh) yhmc,"
				+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) yhkh,"
				+ "(select v.yhkh from view_xsxxb v where a.xh = v.xh) sfzh,"
				+ "gzpj from view_xscjff a where  nd=? and yf=?";
		return getList(sql, new String[] { nd, yf }, new String[] { "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc", "gwdm", "gzsj", "cjje",
				"kh", "gzpj", "yhmc", "yhkh","sfzh" });
	}
	
	/**
	 * 查询当前学年年度学期月份的酬金发放信息
	 * @return List
	 * */
	public List<HashMap<String, String>> getPayInfoOfNbty(String nd, String yf,String userName){
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,gwdm,gzsj,cjje,(select xsgzqk from xsgwxxb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.gwsbsj)gznr,(select gzbx from xsgwxxb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.gwsbsj)gzbx from view_xscjff a where  nd=? and yf=?";
		if(checkExists("yrdwdmb", "dlm", userName)){
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,gwdm,gzsj,cjje,(select xsgzqk from xsgwxxb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.gwsbsj)gznr,(select gzbx from xsgwxxb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.gwsbsj)gzbx from view_xscjff a where  nd=? and yf=? and exists(select 1 from yrdwdmb c where a.sqdw=c.yrdwdm and c.dlm='"+userName+"')";
		}
		return getList(sql, new String[]{nd,yf}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","gwdm","gzsj","cjje","gznr","gzbx"});
	}
	
	/**
	 * 查询当前学年年度学期月份的酬金发放信息
	 * @return List
	 * */
	public List<HashMap<String, String>> getPayInfoByGzdx(String nd, 
			                                              String yf,
			                                              String userName, 
			                                              String gwxzmc){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] inputValue = new String[]{nd,yf};
		String sql = "select xh,xm ,gwdm, gzsj,cjje,yrdwmc,(select kh from view_xsxxb b where a.xh=b.xh)kh,gzpj from view_xscjff a where  nd=? and yf=?";
		if(checkExists("yrdwdmb", "dlm", userName)){
			//用人单位用户
			sql = "select xh,xm ,gwdm, gzsj,cjje,yrdwmc,(select kh from view_xsxxb b where a.xh=b.xh)kh,gzpj from view_xscjff a where  nd=? and yf=? and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm)";
		}
		if(StringUtils.isNotNull(gwxzmc)){
			sql += " and gwxzmc=?";
			inputValue = new String[]{nd, yf, gwxzmc};
		}
		list = getList(sql, inputValue, new String[]{"xh","xm","gwdm","gzsj","cjje","kh","gzpj","yrdwmc"});
		return list;
	}
	
	/**
	 * 查询当前学年年度学期月份的酬金发放信息
	 * @return List
	 * */
	public List<HashMap<String, String>> getPayInfoByZjcmxy(String nd, String yf){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "select xh,xm ,gwdm, gzsj,cjje,yhkh kh,yhmc,yrdwmc,xymc from view_xscjff a where  ffsj like ?||'%'";
		list = getList(sql, new String[]{nd+yf}, new String[]{"xh","xm","gwdm","gzsj","cjje","kh","yhmc","yrdwmc","xymc"});
//		if(checkExists("yrdwdmb", "dlm", userName)){
//			//用人单位用户
//			sql = "select xh,xm ,gwdm, gzsj,cjje,yhkh kh,yhmc from view_xscjff a where  ffsj like ?||'%' and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm)";
//		}		
		return list;
	}
	
	
	/**
	 * 计算总酬金
	 * @return String
	 * */
	public String getTotalMoney(String nd, String yf){
		String sql = "select sum(cjje) totalMoney from view_xscjff a where nd=? and yf=?";
		
		String result = getOneRs(sql, new String[]{nd,yf}, "totalMoney");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return result;
	}
	
	/**
	 * 计算总酬金
	 * @return String
	 * */
	public String getTotalMoney(String nd, String yf, String gwxzmc){
		
		
		String sql = "select sum(cjje) totalMoney from view_xscjff a where nd=? and yf=?";
		String []inputVal=new String[]{nd,yf};
		if(!Base.isNull(gwxzmc) && ! "".equalsIgnoreCase(gwxzmc)){//2010.9.19 qlj
			sql+=" and gwxzmc=?";
			inputVal=new String[]{nd,yf,gwxzmc};
		}
		String result = getOneRs(sql, inputVal, "totalMoney");
		//System.out.println(sql);
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return result;
	}
	
	/**
	 * 计算总酬金
	 * @return String
	 * */
	public String getTotalMoneyForZjcmxy(String nd, String yf){
		String sql = "select sum(cjje) totalMoney from view_xscjff a where ffsj like ?||'%'";
		
		String result = getOneRs(sql, new String[]{nd+yf}, "totalMoney");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return result;
	}
	
	/**
	 * 获取当前月份
	 * @return String
	 * */
	public String getCurrentYf(){
		return getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) yf from dual", new String[]{}, "yf");
	}
	
	/**
	 * 判断用户是否属于用人单位用户
	 * @param userName
	 * @return boolean
	 * */
	public boolean isYrdwUser(String userName){
		String sql = "select count(*)num from yrdwdmb where dlm=?";
		String result = getOneRs(sql, new String[]{userName}, "num");
		result = result== null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result) >0 ? true : false;
	}
	
	
	/**
	 * 判断是否在勤工助学申请时间内
	 * @return String
	 * @throws Exception
	 * */
	public String getSqsjFlag() throws Exception{
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String currentTime = date.toString().substring(0, 19).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		
		String result = "0";
		String sql = "select * from gwsqsjb where kssj<" + currentTime + " and jssj>" + currentTime;
		String tag = returntag(sql, new String[] {});
		if(tag != null && "empty".equalsIgnoreCase(tag)){
			result = "1";
		}
		return result;
	}
	
	/**
	 * 勤工助学申请结果查询
	 * @param model
	 * @return List
	 * */
	public List<String[]> querryXsgwxx(CommanForm model){
		List<String[]> rs = null;
		List<String[]> tmpRs = null;
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String toolSql = "select a.*, a.xh||a.gwdm||a.sqsj 主键, rownum r from view_xsgwxx a ";
		String[] colList = {};
		StringBuffer whereSql = getWhereSql(model);
		int start = model.getPages().getStart();
		int pageSize = model.getPages().getPageSize();
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			//上海工程技术大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj", "kh" };
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			// 云南艺术
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					                 "bjmc", "sfpks", "gwdm", "sqsj", "xyyj", 
					                 "xxyj", "kh", "bh", "gh" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电职业技术学院
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
			//中国美术学院
			colList = new String[]{"主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
			//湖南工业大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xxyj" , "xyyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//中国地质大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "lxdh", "xxyj" };
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "sfyx", "xyyj", "xxyj" };
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
			//浙江科技
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc","sfpks", "yrdwmc", "gwdm", "sqsj", "lxdh", "xyyj", "xxyj" ,"已录取岗位"};			
			toolSql = StringUtils.joinStr("select a.*, a.xh||a.gwdm||a.sqsj 主键, rownum r,",
			 		"(select zd from (select xh,xn,xq,max(ltrim(sys_connect_by_path(gwdm,','),','))zd from (",
					"select xh,xn,xq,gwdm,row_number() over (partition by xh,xn,xq order by gwdm) pno,",
					"row_number() over (partition by xh,xn,xq order by gwdm)-1 sno ",
					"from xsgwxxb b where xxyj='通过' order by xh,xn,xq) ",
					"connect by prior xh = xh and prior xn = xn and prior xq=xq and prior sno = pno ",
					"group by xh,xn,xq) b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)已录取岗位  ",
			 		"from view_xsgwxx a ");
		}else if(Globals.XXDM_HBJTZYJSXY.equals(xxdm)){
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(Globals.XXDM_CDTYXY.equals(xxdm)){
			toolSql = StringUtils.joinStr("select a.*, a.xh||a.gwdm||a.sqsj 主键, rownum r from",
			   " (select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,",
		       " a.zymc,a.bjdm,a.bjmc,a.xzb,a.zzmmm,a.zzmm,a.zwxzdm,",
		       " a.zwxzmc,a.xn,a.nd,a.xq, a.xqmc,a.yrdwdm, a.gwdm,a.sqsj,",
		       " a.xssq,a.fdyyj,a.gwxz,case when xyyrdwsh='否' then '无需审核' else  a.xyyj end xyyj,",
		       " a.xxyj,a.gwsbsj,a.sfpks ",
		       " from (select a.*,case when b.xyyrdwsh is null then '是' else  b.xyyrdwsh end xyyrdwsh ",
		       " from view_xsgwxx a left join gwxxb b on a.gwdm = b.gwdm and a.gwsbsj = b.gwsbsj) a)a ");
		  colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			//武汉商业
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){
			//浙江建设
			colList = new String[] { "主键","xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
			toolSql = "select a.*, a.xh||a.gwdm||a.sqsj 主键,(case when a.fdyyj<>'未审核' or a.xyyj<>'未审核' or a.xxyj<>'未审核'  then 'disabled' else '' end) 标记, rownum r from view_xsgwxx a ";
			//浙江交通职业技术学院
			colList = new String[] { "主键","标记", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","fdyyj","xyyj", "xxyj" };
		}else {
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xyyj", "xxyj" };
		}
		//武汉商业(辅导员用户查询数据范围控制)
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			if("true".equals(model.getIsFdy())){
				whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='")
						.append(model.getUserName())
						.append("') ");
			}
		}
		//浙江交通职业技术学院(辅导员用户查询数据范围控制)
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
			if(model.getBjdm().equals("")){
				if("true".equals(model.getIsFdy()) && "true".equals(model.getBzr())){
					whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='")
							.append(model.getUserName())
							.append("' union select 1 from bzrbbb b where b.zgh='"+model.getUserName()+"' and a.bjdm=b.bjdm) ");
				}
				else if("true".equals(model.getBzr())){
					whereSql.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='")
							.append(model.getUserName())
							.append("') ");
				}
				else if("true".equals(model.getIsFdy())){
						whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='")
								.append(model.getUserName())
								.append("') ");
				}
			}
		}
		sql = "select * from (" + toolSql + whereSql.toString() + " ) where r<=" + (start+pageSize) + " and r>" +start+ "";
		rs = rsToVator(sql,value != null ? value.toArray(new String[0]) : new String[]{}, colList);
				
		tmpRs = rsToVator(toolSql + whereSql.toString(), value != null ? value.toArray(new String[0]) : new String[]{}, colList);
		model.getPages().setMaxRecord(tmpRs.size());	//最大记录数	
		
		//判断是否是困难生
		List<String[]> tempRs = new ArrayList<String[]>();
		for(int i=0; i<rs.size();i++){
			String[] values = rs.get(i);
			String xh = values.length >4 ? values[4]: "";
			//浙江交通职业技术学院
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
				values[8] = isKns(xh) == true ? "是" : "否";
				tempRs.add(values);
			}else{
				values[7] = isKns(xh) == true ? "是" : "否";
				tempRs.add(values);
			}
		}
		
		return tempRs;
	}
	
	/**
	 * 判断学生是否已经申请过岗位
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkStuHavePost(String xh, String gwdm, String doType){
		boolean flag = false;
		HashMap<String, String> timeMap = new HashMap<String, String>();
		String count = "0";
		String sql = "select xn,nd,xq from gwsqsjb where rownum=1";
		
		timeMap = getMap(sql, new String[]{}, new String[]{"xn", "nd", "xq"});
		if(doType != null && "modi".equalsIgnoreCase(doType)){//修改操作
			//当修改的岗位在数据库中没有原始记录时返回true
			sql = "select count(*) num from xsgwxxb where xh=? and xn=? and nd=? and xq=? and gwdm=?";
			count = getOneRs(sql, new String[]{xh,timeMap.get("xn"),timeMap.get("nd"),timeMap.get("xq"),gwdm}, "num");
			flag = Integer.parseInt(count)>0 ? false : true;
		}else{
			//学生已经在本学年年度学期申请了岗位返回true
			sql = "select count(*) num from xsgwxxb where xh=? and xn=? and nd=? and xq=?";
			count = getOneRs(sql, new String[]{xh,timeMap.get("xn"), timeMap.get("nd"), timeMap.get("xq")}, "num");
			flag = Integer.parseInt(count)>0 ? true : false;
		}
		
		return flag;
	}
	
	/**
	 * 获取设定的岗位困难生使用比例
	 * @return Double
	 * */
	public Double getKnsbl(){
		String sql = "select knsbl from gwsqsjb";
		String result = getOneRs(sql, new String[]{}, "knsbl");
		
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Double.parseDouble(result);
	}
	
	/**
	 * 查询岗位详细信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPostDetail(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		//岗位基本信息
		String[] outputValue = {"xq","gwdm","gwxzmc","yrdwmc","xn","nd","gzksrq","gzjsrq","xyrs","syknss","sqsyrs","sqsyknss","jcfsmc","spbcbz","mssjd","msdd",
				"fzr","lxdh","gzdd","gzsj","xydmc","rzyq_nj","rzyq_xb","rzyq_zymc","rzyq_wyyq","rzyq_gzjn","rzyq_qt","gzmd","gznd","gzyd","gzjj","mtbzgz","dqbzgz",
				"bz","gznr"};
		String sql = "select * from view_gwxx where gwdm||gwsbsj=?";
		map = dao.getMap(sql, new String[]{pkValue}, outputValue);
		//有多少人申请该岗位
		sql = "select count(*) count from view_xsgwxx where gwdm||gwsbsj=?";
		map.put("sqrs", dao.getOneRs(sql, new String[]{pkValue}, "count"));
		//有多少人申请通过
		sql = "select count(*) count from view_xsgwxx where gwdm||gwsbsj=? and xxyj='通过'";
		map.put("tgrs", dao.getOneRs(sql, new String[]{pkValue},"count"));
		return map;
	}
	
	/**
	 * 获取岗位的空闲时间
	 * @param pkVal
	 * @return List
	 * */
	public List<HashMap<String, String>> getGzsjKxbz(String pkVal){
		List<HashMap<String, String>> kxbz = null;
		if (pkVal != null && !pkVal.equalsIgnoreCase("")) {		
			String sql = "select to_number(xq-1) xq, sj, kxbz from gwgzsjb where gwdm||gwsbsj = ? and kxbz=1 order by xq,sj ";
			kxbz= getList(sql, new String[]{pkVal}, new String[]{"xq","sj","kxbz"});
		}	
		return kxbz;
	}
	
	/**
	 * 获取岗位的相关信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getWorkInfo(CommanForm model){
		String pkValue = model.getPkValue();
		String[] outputValue = {"xn", "nd", "xueqi", "xueqimc", "xq", "xqmc", "gwdm", "gwsbsj","gwxz", "gwxzmc", "sqdw", "yrdwmc", "jcfs", "xyrs" ,
		        "syknss", "jybcbz", "spbcbz", "sqsyrs", "sqsyknss", "gzsjdw"};
		String sql = "select xn, nd, xueqi, xueqimc, xq, xqmc, gwdm,gwsbsj, gwxz, gwxzmc, sqdw, yrdwmc," +
				"decode(jcfs,'h','元/小时','d','元/天','w','元/周','m','元/月','n','元/志愿服务') jcfs, xyrs," +
				"decode(jcfs,'h','小时','d','天','w','周','m','月') gzsjdw," +
				"syknss, jybcbz, spbcbz, sqsyrs, sqsyknss from view_gwxx where gwdm||gwsbsj = ?";
		
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 获取岗位的剩余经费
	 * @param model
	 * @return String 
	 * */
	public String getSyjf(CommanForm model){	
		HashMap<String, String> map = getWorkInfo(model);
		String nd = model.getNd();
		String yrdwdm = map.get("sqdw");
		String gwxzdm = map.get("gwxz");
		String gwxzmc = map.get("gwxzmc");
		
		String sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and " +
			  "(gwxzdm=? or gwxzdm is null)),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and (gwxzmc=? or gwxzmc is null)),0) " +
			  "syjf from dual";
		
		return getOneRs(sql, new String[]{nd,yrdwdm,gwxzdm,nd,yrdwdm,gwxzmc}, "syjf");
	}
	
	/**
	 * 获取某岗位下的学生
	 * @param model
	 * @return List
	 * */
	public List<HashMap<String, String>> getStuByPost(CommanForm model){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		String nd = model.getNd();
		String yf = model.getYf();
		String pkValue = model.getPkValue();
		yf = yf== null || "".equalsIgnoreCase(yf) ? "0" : yf;
		yf = String.valueOf(Integer.parseInt(yf));
		
		String[] outputValue = {"xh","xm","bjmc","kh"};
		//学生岗位中除去在本月之后调整到本岗位的学生
		String sql = "select a.xh,a.xm,a.bjmc,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh "
                     + " from ( " 
                     + "select a.xh,a.xm,a.bjmc from view_xsgwxx a where a.gwdm||a.gwsbsj=? and a.xxyj='通过' " 
                     + "and not exists( " 
                     + "select 1 from qgzx_gwtzb b where a.xh=b.xh and a.gwdm=b.tzhgw and a.gwsbsj=b.tzhgwsbsj and "
                     + "(to_number(substr(b.tzsj,0,4))> "
                     + nd 
                     +" or (to_number(substr(b.tzsj,0,4))= "
                     + nd 
                     +" and to_number(substr(b.tzsj,6,2))>"
                     + yf
                     +")) " 
                     + ")"
                     + ")a left join khxgsqb c on c.xh=a.xh";	
		rs = getList(sql, new String[]{pkValue}, outputValue);	
		
		//在本月调整到其他岗位的学生
		sql = "select a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,"
              + "a.tzn,a.tzy,a.kh from "
              + "(" 
              + "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,"
              + "b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh "
              + "from (" 
              + "select b.xh,substr(b.tzsj,0,4) tzn,substr(b.tzsj,6,2)tzy,b.tzqgw ,b.tzqgwsbsj "                      
              + "from qgzx_gwtzb b" 
              + " where not exists(select a.xh from xsgwxxb a where a.xh=b.xh and a.gwdm=b.tzqgw and a.gwsbsj=b.tzqgwsbsj)" 
              + " and tzqgw||tzqgwsbsj= ? "
              + ")a left join xscjffb b on a.xh=b.xh and a.tzqgwsbsj=b.sqsj and a.tzqgw=b.gwdm and to_number(b.nd)="
              + nd
              +" and to_number(b.yf)="
              + yf
              +" left join khxgsqb c on c.xh=b.xh"
              + ") a where to_number(tzn)="+nd+" and to_number(tzy)="
              + yf;
		rs.addAll(getList(sql, new String[]{pkValue}, outputValue));
		
		return rs;
	}
	
	/**
	 * 判断记录是否存在
	 * @param tableName 
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + " = ?";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}
	
	/**
	 * 获取学生的基本信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,zzmmmc,lxdh,jg,mzmc,kh,(select ldmc from view_xszsxx b where a.xh=b.xh)ldmc,(select qsh from view_xszsxx b where a.xh=b.xh)qsh,ssbh from view_xsxxb a where xh=?";
		String[] columns = {"xh","xm","xb","nj","xymc","zymc","bjmc","zzmmmc","lxdh","jg","mzmc","kh","ldmc","qsh","ssbh"};		
		return getMap(sql, new String[]{xh}, columns);
	}
	
	/**
	 * 检测岗位是否能够申请
	 * @param pkValue
	 * @return boolean
	 * */ 
	public boolean checkGwsj(String pkValue){	
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String currentTime = date.toString().substring(0, 19).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		
		String sql = "select count(*)count from view_gwxx where shjg='通过' and gwdm||gwsbsj=? and gzjsrq>?";
		String result = getOneRs(sql, new String[]{pkValue,currentTime}, "count");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result; 
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * 查询发放了酬金的用人单位
	 * @param nd
	 * @param yf
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(String nd, String yf){
		String sql = "select distinct sqdw yrdwdm,yrdwmc from view_xscjff where ffsj like '" + nd + yf + "%' ";                                                                                                      
		String[] outputValue = {"yrdwmc", "yrdwdm"};
		return getList(sql, new String[]{}, outputValue);
	}
	
	/**
	 * 查询发放酬金单位下的岗位信息
	 * @param yrdwdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwList(String nd, String yf, String yrdwdm){
		String sql = "select distinct gwdm,sqsj gwsbsj from  view_xscjff where ffsj like '" + nd + yf + "%' and sqdw=?";
		String[] outputValue = {"gwdm", "gwsbsj"};
		return getList(sql, new String[]{yrdwdm}, outputValue);
	}
	
	/**
	 * 查询岗位下的学生发放酬金信息
	 * @param yrdwdm
	 * @param gwdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getStuOfPayinfo(String nd, String yf, String gwdm,String gwsbsj){
		String sql = "select distinct xh,xm,bjmc from view_xscjff where ffsj like '" + nd + yf + "%' and gwdm=? and sqsj=?";
		String[] outputValue = {"xh", "xm", "bjmc"};
		return getList(sql, new String[]{gwdm, gwsbsj}, outputValue);
	}
	
	/**
	 * 查询某一学生的酬金发放信息
	 * @param xh
	 * @return List
	 * */
	public List<HashMap<String, String>> getStuList(String nd, String yf, String gwdm, String gwsbsj,String xh){
		String sql = "select xh,xm,bjmc,cjje,(case yf when ? then '' else '补发' ||yf || '月份工资' end) jebz from view_xscjff where ffsj like '" + nd + yf + "%' and gwdm=? and sqsj=? and xh=?";
		String[] outputValue = {"cjje", "jebz"};
		return getList(sql, new String[]{yf, gwdm, gwsbsj,xh}, outputValue);
	}
	
	/**
	 * 获取数据记录数
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return int
	 * */
	public int getDataCount(String tableName,String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where 1=1 and " + pk + "=?";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result);
	}
	
	/**
	 * 获取学生的空闲时间
	 * @param xh
	 * @return String[]
	 * @throws Exception
	 * */
	public String[] getFreeTimeArray(String xh) throws Exception{
		String sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj";
		String[] kxbz = getRs(sql, new String[] { xh }, "kxbz");		
		return kxbz;
	}
	
	/**
	 * 判断学生所申请岗位的剩余岗位人数
	 * @param pk
	 * @param pkValue
	 * @return int
	 * */
	public int getSygwrs(String pk, String pkValue){
		String xxdm = getXxdm();
		String sql = "select count(*) num from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where " + pk + "=? ) and xyyj='通过'";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){//中国地质大学
			sql = "select count(*) num from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where " + pk + "=? ) and xxyj='通过'"; 
		}
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		
		sql = "select sqsyrs spsyrs from view_gwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where " +pk+ "=?)";
		String spsyrs = getOneRs(sql, new String[]{pkValue}, "spsyrs");
		spsyrs = spsyrs == null || "".equalsIgnoreCase(spsyrs) ? "0" : spsyrs;
		
		return Integer.parseInt(spsyrs)-Integer.parseInt(num);
	}
	
	/**
	 * 根据主键查询岗位
	 * @param pk
	 * @param pkValue
	 * @return String
	 * */
	public String getGwmc(String pk, String pkValue){
		String sql = "select gwdm||gwsbsj gw from view_xsgwxx where " + pk + "=?";
		return getOneRs(sql, new String[]{pkValue}, "gw");		
	}
	
	/**
	 * 查询岗位剩余人数
	 * @param pkValue
	 * @return int
	 * */
	public int getGwtgrs(String pkValue){
		String xxdm = getXxdm();
		String sql  = "select count(*) num from view_xsgwxx where xyyj='通过' and gwdm||gwsbsj=?";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){//中国地质大学
			sql = "select count(*) num from view_xsgwxx where xxyj='通过' and gwdm||gwsbsj=?";
		}
		String shtgrs = getOneRs(sql, new String[]{pkValue}, "num");
		shtgrs = shtgrs == null || "".equalsIgnoreCase(shtgrs) ? "0" : shtgrs;
		
		sql = "select sqsyrs from view_gwxx where gwdm||gwsbsj=?";
		String spsyrs = getOneRs(sql, new String[]{pkValue}, "sqsyrs");
		spsyrs = spsyrs == null || "".equalsIgnoreCase(spsyrs) ? "0" : spsyrs;
		
		return Integer.parseInt(spsyrs) - Integer.parseInt(shtgrs);
	}
	
	/**
	 * 根据主键查询岗位名称
	 * @param pkValue
	 * @return String
	 * */
	public String getGwdm(String pkValue){
		String sql = "select gwdm from view_gwxx where gwdm||gwsbsj=?";
		return getOneRs(sql, new String[]{pkValue}, "gwdm");
	}
	
	/**
	 * 根据主键获取岗位信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk, String pkValue){
		String sql = "select gwdm,gwsbsj from view_gwxx where " + pk + " = ?";
		String[] output = {"gwdm", "gwsbsj"};
		
		return getMap(sql, new String[]{pkValue}, output);
	}
	
	/**
	 * 判断是否在审核上报时间之内
	 * @param yrdwdm
	 * @return boolean
	 * */
	public boolean checkShsbsj(String yrdwdm){
		boolean flag = false;
		String sql = "select count(*)num from qgzx_yrdwkhsbsjb where yrdwdm=? and (kssj<=to_char(sysdate,'yyyymmddHHMISS') and jssj>=to_char(sysdate,'yyyymmddHHMISS')) or (kssj is null and jssj is null)";
		String result = getOneRs(sql, new String[]{yrdwdm}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		flag = Integer.parseInt(result) > 0 ? true : false;		
		return flag;
	}
	
	/**
	 * 根据用人单位获取考核上报时间信息 
	 * @param yrdwdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getKhsbsjByYrdw(String yrdwdm){
		String sql = "select kssj, jssj from qgzx_yrdwkhsbsjb where yrdwdm=? ";
		String[] value = rsToVator(sql, new String[]{yrdwdm}, new String[]{"kssj", "jssj"}).get(0);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("kssj", value[0]);
		map.put("jssj", value[1]);
		return map;
	}
	
	/**
	 * 获取浙江机电酬金发放时间
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZjjdCjffsj(){
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = "";
		String yf = "";
			
		String cjffsj = getOneRs("select cjffsj from gwsqsjb",new String[]{},"cjffsj");
		if(cjffsj != null && cjffsj.length()==6){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}else{
			nd = Base.currNd;
			yf = GetTime.getSystemTime().substring(5,7);
		}
		map.put("nd", nd);
		map.put("yf", yf);
		
		return map;
	}
	
	/**
	 * 查询勤工助学参数设置信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConf(){
		String sql = "select * from gwsqsjb";
		String[] outputValue = {"nd","xn","xq","kssj","jssj","knsbl","mxsbc","mtzgxs","myzgxs","myzgbc","xwkssj","xwjssj","myzgsjfs","shkssj","shjssj","ffsjjg","cjffsj"};
		
		return getMap(sql, new String[]{}, outputValue);
	}
	
	/**
	 * 获取要导出的字段列
	 * @param tableName
	 * @param xxdm
	 * @return String
	 * */
	public String getExpColumn(String xxdm , String tableName){
		String zd = "";
		String sql = "select zd from (select XXDM,ZDSSB,max(ltrim(sys_connect_by_path(zdmc,','),','))zd from (" +
					 "select XXDM,ZDSSB,ZDMC,row_number() over (partition by XXDM,ZDSSB order by ZDMC) pno, " + 
					 "row_number() over (partition by XXDM,ZDSSB order by ZDMC)-1 sno " + 
					 " from dcb order by xxdm,ZDSSB)" + 
					 "connect by prior xxdm = xxdm and prior ZDSSB = ZDSSB and prior sno = pno " + 
					 "group by XXDM,ZDSSB) where xxdm=? and zdssb=?";
		zd = getOneRs(sql, new String[]{xxdm, tableName},"zd");
		return zd;
	}
	
	/**
	 * 获取要导入的字段列
	 * @param tableName
	 * @param xxdm
	 * @return String
	 * */
	public String getImpColumn(String xxdm , String tableName){
		String zd = "";
		String sql = "select zd from (select xxdm,zdssb,max(ltrim(sys_connect_by_path(zdmc,','),','))zd from (" +
					 "select xxdm,zdssb,xsxh,zdmc,row_number() over (partition by XXDM,ZDSSB order by ZDMC) pno, " + 
					 "row_number() over (partition by XXDM,ZDSSB order by ZDMC)-1 sno " + 
					 " from drb order by xsxh)" + 
					 "connect by prior xxdm = xxdm and prior ZDSSB = ZDSSB and prior sno = pno " + 
					 "group by XXDM,ZDSSB) where xxdm=? and zdssb=? ";
		zd = getOneRs(sql, new String[]{xxdm, tableName},"zd");
		return zd;
	}
	
	/**
	 * 获取辅导员下的专业
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getZyByFdy(String zgh){
		String[] outputSQL = new String[] { "zydm", "zymc" };
		String sql="select distinct(b.zydm), b.zymc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm and a.zgh = ? order by b.zydm";
		List<HashMap<String, String>> zyList = getList(sql, new String[] { zgh }, outputSQL);
		return zyList;
	}
	
	/**
	 * 获取辅导员下的班级
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getBjByFdy(String zgh){
		String[] outputSQL = new String[] { "bjdm", "bjmc" };
		String sql = "select distinct b.bjdm, b.bjmc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm and a.zgh = ? order by b.bjdm";
		List<HashMap<String, String>> bjList = getList(sql, new String[] { zgh },outputSQL);
		return bjList;
	}
	
	/**
	 * 查询审核通过的岗位名称列表
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwmcList(String userName){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "";
		if(checkExists("yrdwdmb", "dlm", userName)){
			sql = "select distinct gwdm from view_gwxx a where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?) and shjg='通过'";
			list = getList(sql, new String[]{userName}, new String[]{"gwdm"});
		}else{
			sql = "select distinct gwdm from view_gwxx where shjg='通过' ";
			list = getList(sql, new String[]{}, new String[]{"gwdm"});
		}		
		return list;
	}
	
	/**
	 * 查询所有岗位名称列表
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwmcList(String userName, boolean isPass){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "";
		String query = "";		
		if(checkExists("yrdwdmb", "dlm", userName)){
			if(isPass){
				query  += " and shjg = '通过'";
			}
			sql = "select distinct gwdm from view_gwxx a where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?)" + query;
			list = getList(sql, new String[]{userName}, new String[]{"gwdm"});
		}else{
			if(isPass){
				query  += " where shjg = '通过'";
			}
			sql = "select distinct gwdm from view_gwxx " + query;
			list = getList(sql, new String[]{}, new String[]{"gwdm"});
		}		
		return list;
	}	
	
	
	/**
	 * 根据用人单位查询岗位列表
	 * @param yrdwdm
	 * @param gwxzdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcByYrdw(String yrdwdm, String gwxzdm){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] inputValue = new String[]{};
		String sql = "select gwdm from view_gwxx where shjg='通过'";
		if(StringUtils.isNotNull(yrdwdm)){
			sql += " and  sqdw=?";
			inputValue = new String[]{yrdwdm};
		}
		if(StringUtils.isNotNull(gwxzdm)){
			sql += " and  gwxz='" + gwxzdm + "'";
			
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gwdm","");
		list.add(map);
		list.addAll(getList(sql, inputValue, new String[]{"gwdm"}));
		return list;
	}
	
	/**
	 * 查询岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectGwxx(String pk, String pkValue){
		String sql = "select * from view_gwxx a where " + pk + "=?";
		String[] cols = new String[]{"bz","dqbzgz","dwdz","dwzp","fzr","gwdm","gwsbsj","gwsl","gwsxbj",
						 "gwtsyq","gwxz","gwxzmc","gzdd","gzjj","gzjsrq","gzksrq","gzmd",
						 "gznd","gznr","gzsj","gzyd","jcfs","jcfsmc","jybcbz","lxdh","msdd",
						 "mssj","mssjd","mtbzgz","myqgzxsj","nd","qgbyj","rylsqk","ryyq","rzyq_gzjn",
						 "rzyq_nj","rzyq_qt","rzyq_wyyq","rzyq_xb","rzyq_zy","rzyq_zymc","sfyx","shjg",
						 "spbcbz","sqbg","sqdw","sqdwyj","sqsj","sqsyknss","sqsyrs","syknss","tmpsqsyknss",
						 "tmpsqsyrs","tsyq","xgbyj","xn","xq","xqmc","xscshjg","xueqi","xueqimc","xyboy",
						 "xyddm","xydm","xydmc","xygirl","xymc","xyrs","yrdwlxdh","yrdwmc","yrdwsh",
						 "zcjf","zjf"};
		//浙江工业大学之江学院新增两个字段（申请开始时间、申请结束时间）
		//if(Globals.XXDM_ZJXY.equalsIgnoreCase(Base.xxdm)){
			cols = new String[]{"bz","dqbzgz","dwdz","dwzp","fzr","gwdm","gwsbsj","gwsl","gwsxbj",
					 "gwtsyq","gwxz","gwxzmc","gzdd","gzjj","gzjsrq","gzksrq","gzmd",
					 "gznd","gznr","gzsj","gzyd","jcfs","jcfsmc","jybcbz","lxdh","msdd",
					 "mssj","mssjd","mtbzgz","myqgzxsj","nd","qgbyj","rylsqk","ryyq","rzyq_gzjn",
					 "rzyq_nj","rzyq_qt","rzyq_wyyq","rzyq_xb","rzyq_zy","rzyq_zymc","sfyx","shjg",
					 "spbcbz","sqbg","sqdw","sqdwyj","sqsj","sqsyknss","sqsyrs","syknss","tmpsqsyknss",
					 "tmpsqsyrs","tsyq","xgbyj","xn","xq","xqmc","xscshjg","xueqi","xueqimc","xyboy",
					 "xyddm","xydm","xydmc","xygirl","xymc","xyrs","yrdwlxdh","yrdwmc","yrdwsh",
					 "zcjf","zjf","sqkssj","sqjssj"};
		//}
		return getMap(sql, new String[]{pkValue}, cols);
	}
	
	/**
	 * 查询岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectGwxx(String pk, String pkValue,String userDep){
		String sql = "select   a.gwdm || a.gwsbsj 主键,a.xq,a.gwdm, a.gwxz,a.sqdw, a.sqsj,a.gznr,a.gzsj,a.gzksrq,a.gzjsrq,a.xyrs,a.syknss,"
                       +"a.jybcbz,a.jcfsmc,a.zjf,a.sqbg, a.fzr, a.lxdh, a.sqdwyj,a.qgbyj,a.xgbyj,a.shjg,a.spbcbz,b.fprs sqsyrs,a.sqsyknss,a.gwsxbj,"
                       +"a.bz, a.xn,a.nd, a.xueqi,a.gwsbsj, a.ryyq,a.tmpsqsyrs,a.tmpsqsyknss,a.dwdz,a.gwsl,a.xygirl,a.xyboy,a.gwtsyq,"
                       +"a.xscshjg,a.zcjf,a.sfyx, a.gzdd, a.msdd,a.mssjd,a.rzyq_nj,a.rzyq_xb,a.rzyq_zy,a.rzyq_zymc,a.rzyq_wyyq,a.rzyq_gzjn,"
                       +"a.rzyq_qt,a.gzmd,a.gzyd,a.gznd,a.gzjj,a.mtbzgz,a.dqbzgz,a.xyddm,a.xydmc,a.xymc,a.yrdwlxdh,"
                       +"a.gwxzmc, a.xqmc,a.yrdwmc,a.xydm,a.xueqimc,a.yrdwsh,a.myqgzxsj,a.mssj,a.tsyq,a.rylsqk,"
                       +"a.dwzp from view_gwxx a,qgzx_xyrsb b where  a.gwdm||a.gwsbsj=? and  a.gwdm=b.gwdm and b.xydm= ?";
		String[] cols = {"bz","dqbzgz","dwdz","dwzp","fzr","gwdm","gwsbsj","gwsl","gwsxbj","gwtsyq","gwxz",
						 "gwxzmc","gzdd","gzjj","gzjsrq","gzksrq","gzmd","gznd","gznr","gzsj","gzyd","jcfs",
						 "jcfsmc","jybcbz","lxdh","msdd","mssj","mssjd","mtbzgz","myqgzxsj","nd","qgbyj",
						 "rylsqk","ryyq","rzyq_gzjn","rzyq_nj","rzyq_qt","rzyq_wyyq","rzyq_xb","rzyq_zy",
						 "rzyq_zymc","sfyx","shjg","spbcbz","sqbg","sqdw","sqdwyj","sqsj","sqsyknss",
						 "sqsyrs","syknss","tmpsqsyknss","tmpsqsyrs","tsyq","xgbyj","xn","xq","xqmc",
						 "xscshjg","xueqi","xueqimc","xyboy","xyddm","xydm","xydmc","xygirl","xymc","xyrs",
						 "yrdwlxdh","yrdwmc","yrdwsh","zcjf","zjf"};
		return getMap(sql, new String[]{pkValue,userDep}, cols);
	}
	
	
	/**
	 * 查询参加岗位的学生列表
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>> selectCjgwxs(String pk, String pkValue){
		String sql = "select * from view_xsgwxx where  "+pk+"=?  and xxyj='通过'";
		String[] cols = {"bh","bhgkm","bjdm","bjmc","bz","fdyyj","gh","gjzxdk","gwdm","gwsbsj","gwzydj","gzbx","gzjl","jsjsp","jtcy","jtcy1_cw","jtcy1_gzdwjzw","jtcy1_nsr","jtcy1_xm","jtcy2_cw","jtcy2_gzdwjzw","jtcy2_nsr","jtcy2_xm","jtcy3_cw","jtcy3_gzdwjzw","jtcy3_nsr","jtcy3_xm","jtnsr","jtysr","jtzyjjly","kcjqgzxsj","kh","ldyx","lxdh","mqqgzxqk","nd","nj","pkdj","sfdjsj","sffcfp","sfpks","sfzdtg","sqsj","ssbh","wjcf","xb","xg","xh","xm","xn","xq","xqmc","xscyj","xsgzqk","xssq","xxshyj","xxyj","xydm","xymc","xyyj","xzb","yhtc","yrdwdm","zydm","zymc","zzmm","zzmmm","zzqk"};
		return getList(sql, new String[]{pkValue}, cols);
	}
	
	/**
	 * 根据用户名获取用人单位名称
	 * @param String userName
	 * @return String
	 * */
	public String getYrdwmcByUser(String userName){
		String bmmc = "";
		if(checkExists("yrdwdmb", "dlm", userName)){
			//用人单位用户
			String sql = "select yrdwmc from yrdwdmb where dlm=?";
			bmmc = getOneRs(sql, new String[]{userName}, "yrdwmc");
		}
		return bmmc;
	}
	
	/**
	 * 获取大写金额
	 * @param String xxje
	 * @return String 
	 * @throws Exception
	 * */
	public String getDxje(String xxje) throws Exception{
		String sql = "{call pro_Disp_dxje(?,?)}";
		String[] tmp = getProVal(sql, new String[]{xxje},new int[]{2});
		return tmp==null ? "" : tmp[0];
	}
	
	/**
	 * 根据主键查询学生岗位调整信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectGwxzxxOne(String pk, String pkValue){
		String[] outputValue = {"bjdm","bjmc","czsj","gwxz","nj","shjg","tzhgw","tzhgwsbsj","tzhgznd","tzhgzxn","tzhgzxq","tzhkcjqgzxsj","tzqgw","tzqgwsbsj","tzqgznd","tzqgzxn","tzqgzxq","tzqkcjqgzxsj","tzsj","tzyy","xb","xh","xm","xn","xq","xydm","xymc","zydm","zymc"};
		String sql = "select * from view_qgzx_gwtz where " + pk + "=?";
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 查询岗位列表
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwList(CommanForm model){
		String sql = "select gwdm, gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a ";
		sql = sql + getConditionOfGwmc(model,"yes") + " and shjg = '通过' and gzjsrq>=to_char(sysdate,'yyyymmdd')";
		if(!Base.isNull(model.getUserName())){
			if(checkExists("yrdwdmb", "dlm", model.getUserName())){//用人单位用户
				sql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + model.getUserName() + "')";
			}
		}
		String[] outputValue = {"gwdm", "gwdmgwsbsj"};
		return getList(sql,  value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询岗位列表
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectStuGwList(CommanForm model){
		String sql = "select gwdm, gwdm||'-'||gwsbsj gwdmgwsbsj from view_xsgwxx ";
		sql = sql + getConditionOfStuGw(model) + " and xxyj='通过'";
		String[] outputValue = {"gwdm", "gwdmgwsbsj"};
		return getList(sql,  value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 检测勤工助学申请审核时指定的岗位是否有效
	 * @param String xh
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> checkZdgwExists(String xh,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String[] xmdm = pkValue.split("!!");
		String gwdm = "";
		String gwsbsj = "";
		String sql = "!!";
		for(int i=0; i<xmdm.length;i++){
			sql = "select gwdm,gwsbsj from xsgwxxb where xh=? and gwdm||'-'||gwsbsj = ? and sfyx='1'";
			map = getMap(sql, new String[]{xh,xmdm[i]},new String[]{"gwdm","gwsbsj"});
			if(StringUtils.isNotNull(map.get("gwdm"))){
				gwdm += map.get("gwdm") + ",";
				gwsbsj += map.get("gwdm") + "-" + map.get("gwsbsj")+"!!";
			}
		}
		if(StringUtils.isNotNull(gwdm)){
			gwdm = gwdm.substring(0, gwdm.length()-1);
		}
		if(StringUtils.isNotNull(gwsbsj)){
			gwsbsj = gwsbsj.substring(0, gwsbsj.length()-2);
		}
		map.put("gwdm", gwdm);
		map.put("gwsbsj", gwsbsj);
		return map;
	}
	
	public HashMap<String, String> selectXsgwxxbByPk(String pkValue){
		String sql = "select * from view_xsgwxx where xh||gwdm||sqsj=?";
		String[] output = {"bdsj","bh","bhgkm","bjdm","bjmc","bz","fdyyj","fjwjmc","gh","gjzxdk","gwdm","gwsbsj","gwzydj","gzbx","gzjl","jsjsp","jtcy","jtcy1_cw","jtcy1_gzdwjzw","jtcy1_nsr","jtcy1_xm","jtcy2_cw","jtcy2_gzdwjzw","jtcy2_nsr","jtcy2_xm","jtcy3_cw","jtcy3_gzdwjzw","jtcy3_nsr","jtcy3_xm","jtnsr","jtysr","jtzyjjly","kcjqgzxsj","kh","ldyx","lxdh","lzsj","mqqgzxqk","nd","nj","pkdj","sfdjsj","sffcfp","sfpks","sfyx","sfzdtg","sqsj","ssbh","wjcf","xb","xg","xh","xm","xn","xq","xqmc","xscyj","xsgzqk","xssq","xxshyj","xxyj","xydm","xymc","xyyj","xzb","yhtc","yrdwdm","zydm","zymc","zzmm","zzmmm","zzqk"};
		return getMap(sql, new String[]{pkValue}, output);
	}
	
	/**
	 * 查询计酬方式代码列表
	 * @param boolean oneNull
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectJcfsdmbList(boolean oneNull){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "select distinct jcfsdm dm, jcfsmc mc from qgzx_jcfsdmb order by jcfsdm";
		if(oneNull){//需要一条空数据
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "--请选择--");
			list.add(map);
		}
		list.addAll(getList(sql, new String[]{}, new String[]{"dm","mc"}));
		return list;
	}
	
	/**
	 * 查询计酬方式对应的计酬标准
	 * @param String jcfsdm
	 * @return String
	 * */
	public String loadJcbz(String jcfsdm){
		String sql = "select jcfsdm, jcfsmc, jybcbz from qgzx_jcfsdmb where jcfsdm=?";		
		return getOneRs(sql, new String[]{jcfsdm}, "jybcbz");
	}
	
	/**
	 * 海南大学判断上学期补考科目条件是否符合
	 * @param map
	 * @param boolean
	 * */
	public boolean checkBktj(HashMap<String, String> map){
		boolean result= true;
		String bkkmxz = getSqsjInfo().get("bkkmsxz");
		
		if(StringUtils.isNotNull(bkkmxz)){
			PjpyCommonInterface pInterface = new PjpyCommonInterface();
			//获取上一学年学期
			String befXq = getBeforeXq(map).get("xq");
			String befXn = getBeforeXq(map).get("xn");
			map.put("xq", befXq);
			map.put("xn", befXn);
			
			//从接口获取补考科目数
			int mkkms = Integer.parseInt(pInterface.queryStuCjbkms(map)) ;
			result = mkkms <= Integer.parseInt(bkkmxz);
		}
		return result;
	}
	
	/**
	 * 获取可申请的岗位列表
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> getKsqgwList(){
		String sql = sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='通过' and gzjsrq>=to_char(sysdate,'yyyymmdd')";
		return getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
	}
	
	/**
	 * 岗位名称信息查询
	 * @param paramter
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcxxList(HashMap<String, String> paramter,String isLsgw){
		value = new ArrayList<String>();
		CommanForm model = new CommanForm();
		model.setXn(paramter.get("xn"));
		model.setNd(paramter.get("nd"));
		model.setXueqi(paramter.get("xq"));
		model.setGwfbr(paramter.get("gwfbr"));
		model.setYrdwdm(paramter.get("yrdwdm"));
		model.setGwxz(paramter.get("gwxzdm"));
		
		boolean shtg = true;
		if(StringUtils.isNotNull(paramter.get("shFlag"))){
			shtg = false;
		}
		
		String whereSql = getConditionOfGwmc(model,isLsgw).toString();
		String userName = paramter.get("userName");
		if(isYrdwUser(userName)){
			whereSql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
		}
		if(shtg){
			whereSql += " and shjg = '通过'";
		}
		
		String sql = "select distinct gwdm gwdm, gwdm gwmc from view_gwxx a " + whereSql;
		
		return getList(sql, value != null ? value.toArray(new String[0]) : new String[]{}, new String[]{"gwdm", "gwmc"});
	}
	
	/**
	 * 岗位名称信息查询
	 * @param paramter
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getFgwfbrGwmcxxList(HashMap<String, String> paramter,String isLsgw){
		value = new ArrayList<String>();
		CommanForm model = new CommanForm();
		model.setXn(paramter.get("xn"));
		model.setNd(paramter.get("nd"));
		model.setXueqi(paramter.get("xq"));		
		model.setYrdwdm(paramter.get("yrdwdm"));
		model.setGwxz(paramter.get("gwxzdm"));
		
		boolean shtg = true;
		if(StringUtils.isNotNull(paramter.get("shFlag"))){
			shtg = false;
		}
		
		String whereSql = getConditionOfGwmc(model,isLsgw).toString();
		String userName = paramter.get("userName");
		if(isYrdwUser(userName)){
			whereSql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
		}
		if(shtg){
			whereSql += " and shjg = '通过'";
		}
		if(StringUtils.isNotNull(paramter.get("gwfbr"))){
			whereSql += " and (gwfbr<>'" + userName + "' or gwfbr is null)";
		}
		String sql = "select distinct gwdm gwdm, gwdm gwmc from view_gwxx a " + whereSql;
		
		return getList(sql, value != null ? value.toArray(new String[0]) : new String[]{}, new String[]{"gwdm", "gwmc"});
	}
	
	/**
	 * 获取职位性质代码列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZwxzdmList(){
		String sql = "select distinct zwxzdm dm, zwxzmc mc from qgzx_zwxzdmb order by zwxzdm";
		return getList(sql, new String[]{}, new String[]{"dm", "mc"});
	}
	
	/**
	 * 检测学生是否已经有岗位
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkYjbgwlq(String pkValue){
		String sql = StringUtils.joinStr("select count(*)num from xsgwxxb a where xxyj='通过' ",
				     "and exists(select 1 from (select * from xsgwxxb b where b.xh||b.gwdm||b.sqsj=?) b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.gwdm<>b.gwdm and a.gwsbsj<>b.gwsbsj and a.xq=b.xq) ");
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		
		return Integer.parseInt(num)> 0 ? true : false;
	}
	
	/**
	 * 获取岗位信息
	 */
	public HashMap<String,String>getGwxx(String pkValue){
		String sql="select * from view_gwxx where gwdm || gwsbsj = ? ";
		DAO dao =new DAO();
		return dao.getMap(sql, new String[]{pkValue}, new String []{"yrdwsh"});
	}
	
	/**
	 * 检测学生是否已经有岗位审核通过
	 * @param pkString
	 * @param zjkjPk
	 * */
	public String checkZjkjXsgw(String pkString, String zjkjPk){
		String msg = "";
		String[] pkV = pkString.split("!!SplitOneSplit!!");
		String[] zjkjV = zjkjPk.split("!!SplitOneSplit!!");
		for(int i=0; i<pkV.length; i++){
			String num = getOneRs("select count(*)num from xsgwxxb a where exists(select 1 from (select * from xsgwxxb b where xh||gwdm||sqsj=? and xh||xn||nd=? ) b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.gwdm<>b.gwdm and a.gwsbsj<>b.gwsbsj) and xxyj='通过'", new String[]{pkV[i],zjkjV[i]}, "num");
			num = StringUtils.isNull(num) ? "0" : num;
			if(Integer.parseInt(num)>0){
				msg += "第" + (i+1) + "条记录：该学生已经有岗位审核通过了！\n" ;
			}
		}		
		return msg;
	}
	
	/**
	 * 根据岗位名称获取最近的上报时间
	 * @param gwdm
	 * @return String
	 * */
	public String queryZjgwsbsj(String gwdm){
		String sql = "select max(gwsbsj) gwsbsj from gwxxb where gwdm=?";
		return getOneRs(sql, new String[]{gwdm}, "gwsbsj");
	}
	
	/**
	 * 判断是否存在相同的岗位信息(DWR)
	 * @param pkValue
	 * @return String
	 * author qlj
	 */
	public String checkGwExists(String pkValue){
		DAO dao =DAO.getInstance();
		String sql=" select count(1)num from gwxxb where gwdm||gwsbsj=? ";
		String[]gwMap=dao.getOneRs(sql, new String[]{pkValue}, new String[]{"num"});
		if(!"0".equalsIgnoreCase(gwMap[0])){
			return "true";
		}else{
			return "false";
		}
	}
	
	/**
	 * 根据路径获取TITLE
	 * @param path
	 * @return	String
	 */
	public String getTitles(String path){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("select  (select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm, 0, 3)) || '-' || ");
		sql.append("(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm, 0, 5)) || '-' ||");
		sql.append("a.gnmkmc title  from gnmkdmb a where dyym = ? ");
		String[] titleName=dao.getOneRs(sql.toString(), new String[]{path}, new String[]{"title"});
		if(titleName.length>0){
			return titleName[0];
		}else{
			return "";
		}
	}
	
	/**
	 * 检测岗位是否在申请时间范围内
	 * @param pkValue
	 * @return
	 */
	public boolean checkGwsqsj(String pkValue){
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String currentTime = date.toString().substring(0, 19).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		
		String sql = "select count(*)count from view_gwxx where gwdm||gwsbsj=? and sqkssj<=? and sqjssj>=?";
		String result = getOneRs(sql, new String[]{pkValue,currentTime,currentTime}, "count");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result; 
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * 查询申请岗位的学生列表
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>> selectSqgwxs(String pk, String pkValue){
		String sql = "select * from view_xsgwxx where  "+pk+"=? ";
		String[] cols = {"bh","bhgkm","bjdm","bjmc","bz","fdyyj","gh","gjzxdk","gwdm","gwsbsj","gwzydj","gzbx","gzjl","jsjsp","jtcy","jtcy1_cw","jtcy1_gzdwjzw","jtcy1_nsr","jtcy1_xm","jtcy2_cw","jtcy2_gzdwjzw","jtcy2_nsr","jtcy2_xm","jtcy3_cw","jtcy3_gzdwjzw","jtcy3_nsr","jtcy3_xm","jtnsr","jtysr","jtzyjjly","kcjqgzxsj","kh","ldyx","lxdh","mqqgzxqk","nd","nj","pkdj","sfdjsj","sffcfp","sfpks","sfzdtg","sqsj","ssbh","wjcf","xb","xg","xh","xm","xn","xq","xqmc","xscyj","xsgzqk","xssq","xxshyj","xxyj","xydm","xymc","xyyj","xzb","yhtc","yrdwdm","zydm","zymc","zzmm","zzmmm","zzqk"};
		return getList(sql, new String[]{pkValue}, cols);
	}
	
	
	public boolean saveYrdwshkz(CommanForm model) throws Exception {

		CommDAO dao = new CommDAO();
		String[] pk = model.getPkV();
		String[] yrdwsh = model.getXyyrdwsh_hid();
		String[] sql = new String[pk.length];
		for (int i = 0; i < pk.length; i++) {
			sql[i] = " update gwxxb set xyyrdwsh='" + yrdwsh[i]
					+ "' where gwdm||'-'||gwsbsj='" + pk[i] + "' ";

		}
		return dao.saveArrDate(sql);
	}
}
