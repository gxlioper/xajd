
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
	 * ��ϲ�ѯ����
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
	 * ��ϸ�λ�б��ѯ����
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
			sb.append( " and gwxz<>(select gwxzdm from gwxzdmb where gwxzmc='��ʱ��λ')");
		}
		
		return sb;
	}
	
	/**
	 * ��ϸ�λ�б��ѯ����
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
	 * �������뵥λ��ȡ��λ�б�
	 * @param sqdwdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwmcList(String sqdwdm){
		List<HashMap<String, String>> gwmcList = null;
		String sql = "select distinct gwdm||'-'||gwsbsj gwdm, a.gwdm gwmc from view_gwxx a where a.shjg='ͨ��' and a.sqdw=?";
		gwmcList = getList(sql, new String[]{sqdwdm}, new String[]{"gwdm","gwmc"});
		return gwmcList;
	}
	
	/**
	 * �������뵥λ��ȡ��λ�б�
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwGwList(String userName,String sqdwdm, String gwxzdm, boolean shFlag){
		String[] inputList = {};
		String sql = "select distinct gwdm||'-'||gwsbsj gwdm, a.gwdm gwmc from view_gwxx a where 1=1";
		
		if(shFlag){//��ѯ���ͨ���ĸ�λ
			sql += " and a.shjg='ͨ��'";
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
	 * ��ȡ���û������ĸ�λ
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List
	 * */
	public List<HashMap<String, String>> getNotUserGwList(String userName,String sqdwdm, String gwxzdm, boolean shFlag){
		String[] inputList = {};
		String sql = "select distinct gwdm||'-'||gwsbsj gwdm, a.gwdm gwmc from view_gwxx a where 1=1";
		
		if(shFlag){//��ѯ���ͨ���ĸ�λ
			sql += " and a.shjg='ͨ��'";
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
		String sql = "select count(*) count from view_xsgwxx where xxyj='ͨ��' and gwdm||'-'||gwsbsj=?";		
		map.put("tgrs", getOneRs(sql, new String[]{pkValue}, "count"));
		sql = "select count(*)count from view_xsgwxx where gwdm||'-'||gwsbsj=?";
		map.put("sqrs", getOneRs(sql, new String[]{pkValue},"count"));
		return map;
	}
	
	
	/**
	 * ���ݸ�λ���Ʋ�ѯ�����˺���ϵ�绰
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
	 * ��ȡ��λ����ǰ�ĸ�λ��Ϣ
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
				+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh"
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
	 * ��ȡ��λ����ǰ�ĸ�λ��Ϣ���㽭�Ƽ�--ֻ��ʾ�й������˵�ѧ����
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
//				+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh"
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
			+ "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh"
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
	 * ��ȡ��λ����ǰ�ĸ�λ��Ϣ
	 * @param pkValue
	 * @return ArrayList
	 * **/
	public ArrayList<HashMap<String, String>> getTzqgw(String pkValue,String nd,String yf){
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();		
		String sql = "select rownum ,a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.nd,a.yf,a.dqn,a.dqy,a.cjje,a.bz ,a.gzsj,a.khqk,a.kh from (" + 
			        "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,a.dqn,a.dqy,b.nd,b.yf,decode(b.gzsj,'',(select ygzsj from view_qgzx_xsgzkhb c where a.tzqgw=c.gwdm and a.tzqgwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"+nd+"' and c.yf='"+yf+"'),b.gzsj)gzsj,decode(b.cjje,'',(select ffcjje from view_qgzx_xsgzkhb c where a.tzqgw=c.gwdm and a.tzqgwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"+nd+"' and c.yf='"+yf+"'),b.cjje)cjje,b.bz,b.khqk ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh" +   
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
	 * ��ĳ�����������ѯ��¼�Ƿ����
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
	
	//����ɽ��ѧ liang�����ݸ�λ������Ӧ��Ҫ����Ϣ�б�
	public String[] getSqtjString(String pk,String pkValue) {
		String[] returnStr = new String[2];
		String sql = "select * from jgszdshb where 1=2";
		String[] columnsNameString = getColumnNameCN(getColumnName(sql), "jgszdshb");
		String judgeExists = "select count(*) num from jgszdshb where " + pk + "='" + pkValue + "'";
		String[] num = getOneRs(judgeExists, new String[] {}, new String[] {"num"});
		if(num[0].equalsIgnoreCase("0")) {
			returnStr[0] = "û����������";
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
		//�ж��Ƿ����С����������������,�����һ����ô������returnStr�ġ�1������Ϊ1����Ϊ��־
		if(jgsTempSQL[5].equalsIgnoreCase("1")) {
			returnStr[1] = "1";
		}else {
			returnStr[1] = "0";
		}
		return returnStr;
	}
	
	//����ɽ��ѧ liang �������Ϣȫ�����أ�������ѧ�������������ж�
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
	//����ɽ��ѧ liang ����ѧ���Ƿ������������
	public String isStudentCondOK(String xh,String pkValue,String djsj) {
		int count = 0;
		StringBuffer sb = new StringBuffer();
//		sb.append("ͬѧ�����ϵı�׼�У� \n");
		String[] outputValue = new String[]{"XH","ZZMM","XB"};
		String jgsSql = "select * from view_jgszdsh where xh=?";
		String[] studentInfo = getOneRs(jgsSql, new String[] {xh}, outputValue);
		List<HashMap<String,String>> mapList = getSqtjList(pkValue);
		// �ж�1����һѧ����δ�ܴ��� 
		if(mapList!=null && mapList.size()>0){
		if(mapList.get(0).get("tjxx").equalsIgnoreCase("1")) {
			//����ѧ��û����һ���ڱ�����
			if(studentInfo != null) {
				sb.append(++count + ": " + mapList.get(0).get("columnName") +"\n");
			}else {
				//���ж�ѧ����û������ͼ[view_wjcf]��
				String wjcfSQL = "select * from view_wjcf where xh=?";
				studentInfo = getOneRs(wjcfSQL, new String[] {xh}, new String[] {"XH"});
				//����������ͼ��򲻷��������׼��������ͨ����
				if(studentInfo != null) {
					sb.append(++count + ": " + mapList.get(0).get("columnName") +"\n");
				}
			}
		}		
		//�ж�2��û�����޿�Ŀ
		if(mapList.get(1).get("tjxx").equalsIgnoreCase("1")) {
			String cxcj = getOneRs("select count(cxcj) count from cjb where xh=? and cxcj is not null", new String[]{xh}, "count");
			if(cxcj!=null && !"".equalsIgnoreCase(cxcj)){
				sb.append(++count + ": " + mapList.get(1).get("columnName") +"\n");
			}
		}
		//�ж�3��ƶ����������
		if(mapList.get(2).get("tjxx").equalsIgnoreCase("1")) {
			if(!isKns(xh)) {
				//�������ƶ������������
				sb.append(++count + ": " + mapList.get(2).get("columnName") +"\n");
			}
		}
		String xsxxSQL = "select * from view_xsxxb where xh=?";
		studentInfo = getOneRs(xsxxSQL, new String[] {xh}, outputValue);
		//�ж�4����Ա
		if(mapList.get(3).get("tjxx").equalsIgnoreCase("1")) {
			if(studentInfo[1]==null || !studentInfo[1].equalsIgnoreCase("01")) {
				sb.append(++count + ": " + mapList.get(3).get("columnName") +"\n");
			}
		}
		//�ж�5����Ա
		if(mapList.get(4).get("tjxx").equalsIgnoreCase("1")) {
			if(studentInfo[1]!=null && !studentInfo[1].equalsIgnoreCase("02")) {
				sb.append(++count + ": " + mapList.get(4).get("columnName") +"\n");
			}
		}
		//�ж�6���������
		if(mapList.get(5).get("tjxx").equalsIgnoreCase("1")) {
			if(djsj!=null && djsj.equals("0")) {
				sb.append(++count + ": " + mapList.get(5).get("columnName") +"\n");
			}
		}
		//�ж�7������
		if(mapList.get(6).get("tjxx").equalsIgnoreCase("1")) {
			if(!studentInfo[2].equalsIgnoreCase("��")) {
				sb.append(++count + ": " + mapList.get(6).get("columnName") +"\n");
			}
		}
		//�ж�8��Ů��
		if(mapList.get(7).get("tjxx").equalsIgnoreCase("1")) {
			if(!studentInfo[2].equalsIgnoreCase("Ů")) {
				sb.append(++count + ": " + mapList.get(7).get("columnName") +"\n");
			}
		}
		}
		if(count == 0) {
			//��ʾ�ϸ�
			return "0";
		}else {
			return sb.toString() ;//+ "\n���п��ܲ���ͨ�����Ƿ��������!";
		}
	
	}
	//����ɽ��ѧ liang �ж��ڸø�λ����û��ѧ���Ѿ��ڹ���������������Щѧ���ڸø�λ�������ͨ���ġ�
	public boolean isHaveStuOkByJob(String pk) {
		////////////////////////////////////////
		return false;
	}
	//����ɽ��ѧ liang �жϸ�λ�Ƿ��Ѿ�ͨ��
	public boolean isJobOk(String pk) {
		String sql = "select * from gwxxb where gwdm||'-'||gwsbsj=? and SHJG='ͨ��'";
		String[] oneRes = getOneRs(sql, new String[] {pk}, new String[] {"gwdm"});
		return (oneRes != null) ? true : false;
	}
	//����ɽ��ѧ liang �Զ����ͨ����ѧ�������״̬�������Ƿ��Ѿ������˸�λ�Ķ����
	public boolean isStuOkNumBeyondStandard(String pk) {
		String sql = "select * from  gwxxb where gwdm||'-'||gwsbsj=?";
		String[] oneRes = getOneRs(sql, new String[] {pk}, new String[] {"xyrs"});
		//System.out.println(oneRes[0]);
		//System.out.println(getStuOkNumByJob(pk));
		return (getStuOkNumByJob(pk) > Integer.parseInt(oneRes[0])) ? true : false;
	}
	//����ɽ��ѧ liang ����ĳ��λѧ������Ҫ�������
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
	 * ���˵�λ(ѧУ)���ѧ��
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
				//ѧУ
				sfStr = " and xyyj='ͨ��'";
			}
			String tempSql = "update xsgwxxb  set " + shenfen + " ='ͨ��' where gwdm||gwsbsj='" +  jobPk + "'"
			+ " and xh in (" + sb.toString() + ")" + sfStr;	
			StandardOperation.update("xsgwxxb", tempSql, request);
			tempSql = "update xsgwxxb set " + shenfen + " ='��ͨ��' where gwdm||gwsbsj='" +  jobPk + "'"
			+ " and " + shenfen + " ='δ���'";
			StandardOperation.update("xsgwxxb", tempSql, request);
		}	
	}
	/**
	 * ��ȡ�����λҪ���ѧ����ѧ���б�
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
	 * ��ɳ����ѧԺ ����ѧ��ѧ�ŵĴ���,������ڣ��ͷ���ѧ������Ϣ
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
	 * ��ɳ����ѧԺ  �жϸ�λ�Ƿ���"־Ը�����"�������λ�ı�����0����ô����Ϊ�ø�λ��"־Ը����"��
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
	 * ��ȡ��ѯ��λ��Ϣ������ gwdm||gwsbsj 
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
	 * ��ȡ��ѯ��λ��Ϣ������ gwdm||gwsbsj 
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
	 * ��������ж��Ƿ����ͨ��
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
			//�й����ʴ�ѧ
			xh = checkXhRep(pkVal);//����ظ���ѧ��
			chkFlag = Base.isNull(xh) ? false : true;			
		}
		
		for(int i=0;i<pkVal.length; i++){			
			if(Globals.XXDM_HNDX.equalsIgnoreCase(xxdm)){
				//���ϴ�ѧ
				//�ж�����˵ĸ�λ������ȡ�ѧ�ꡢѧ���Ƿ��и�λ�Ѿ����ͨ��
				if(checkPassByXq(pkVal[i],userName)){
					sMess = "һѧ��ֻ�����ͨ��һ����λ��";
				}else{
					sMess = checkGwrs(pkVal[i]);	//��������Ƿ���
				}
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//�㽭�Ƽ�ѧԺ
				if(checkYjbgwlq(pkVal[i])){
					sMess = "ѧ���Ѿ��и�λ���ͨ���ˣ�";
				}
			}else{				
				if(!chkFlag){//ѧ�����ظ�
					sMess = checkGwrs(pkVal[i]); //checkGwrsByUser(pkVal[i],shzd);	//��������Ƿ���
				}
			}
			if(sMess!=null && !"".equalsIgnoreCase(sMess)){
				value[i] = sMess + "\n";
			}	
		}
		
		if(chkFlag){//ѧ�����ظ�
			value[0] = "ѧ��Ϊ��" + xh + "��ѧ������ͬʱͨ��һ�����ϵĸ�λ��";
		}
		return value;
	}
	
	/**
	 * ����ظ���ѧ��
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
	 * ���ѧ����λ�������
	 * @param pkValue
	 * @return
	 */
	public String checkXsgwsqqk(String pkValue){
		String[] pkVal = pkValue.split("!!SplitOneSplit!!");
		String sql = "select xh from view_xsgwxx where xh||gwdm||sqsj=?";
		String xhString = "";
		for(int i=0; i<pkVal.length; i++){
			String xh = getOneRs(sql, new String[]{pkVal[i]}, "xh");
			String cont = getOneRs("select count(1) cont from view_xsgwxx where xh=? and fdyyj='ͨ��' and xyyj='ͨ��' and xxyj='ͨ��' and xh||gwdm||sqsj<>? ", new String[]{xh,pkVal[i]}, "cont");
			if(!"0".equals(cont)){
				xhString += xh + ",";
			}
		}
		return StringUtil.isNull(xhString) ? "" : ("ѧ��" + xhString.substring(0,xhString.length()-1) + "�Ѿ��и�λͨ�����");
	}
	
	
	/**
	 * /**
	 * ������һѧԺ������ͨ�������Ƿ񳬹���������
	 * @param String pkValue
	 * @return String[]
	 * */
	public String[] checkFprs(String pkValue,String userType){
		String[] pkVal = pkValue.split("!!SplitOneSplit!!");
		String[] value = new String[pkVal.length+1];		
		String sMess = "";
		if("xy".equalsIgnoreCase(userType)){
			for(int i=0;i<pkVal.length; i++){
				sMess = checkFprsOfNbtyxy(pkVal[i]);//��������Ƿ���
				if(sMess!=null && !"".equalsIgnoreCase(sMess)){
					value[i] = sMess + "\n";
				}	
			}		
		}
		return value;
	}
	
	/**
	 * ������һѧԺ������ͨ�������Ƿ񳬹���������
	 * @param String pkValue
	 * @return String
	 * */
	public String checkFprsOfNbtyxy(String pkValue){
		String result = "";
		String sql = "select count(*)num from  view_xsgwxx a where xyyj='ͨ��' and xh||gwdm||sqsj<>? and exists(select 1 from view_xsgwxx b where a.nd=b.nd and a.xn=b.xn and a.xq=b.xq and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.xydm=b.xydm and b.xh||b.gwdm||b.sqsj=?)";
		String num = getOneRs(sql, new String[]{pkValue,pkValue}, "num");
		
		sql = "select xymc,gwdm  from view_xsgwxx where xh||gwdm||sqsj=?";
		HashMap<String,String> map = getMap(sql, new String[]{pkValue}, new String[]{"xymc","gwdm"});
		
		
		sql = "select fprs from qgzx_xyrsb a where exists(select 1 from view_xsgwxx b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and b.xh||b.gwdm||b.sqsj=?)";
		String fprs = getOneRs(sql, new String[]{pkValue}, "fprs");
		
		if(Integer.parseInt(StringUtils.isNull(fprs) ? "0" : fprs) <= Integer.parseInt(StringUtils.isNull(num) ? "0" : num)){
			result = "��λ��"+map.get("gwdm") + "��" + map.get("xymc") + Base.YXPZXY_KEY+"�����Ѿ����";
		}
		return result;
	}
	
	/**
	 * ���ͬһѧ���Ƿ��Ѿ��и�λ���ͨ��
	 * @param String pkValue
	 * @return boolean 
	 * */
	public boolean checkPassByXq(String pkValue,String userName){
		if(!checkExists("yrdwdmb", "dlm", userName)){//ѧУ�û���˲��жϸ�����
			return false;
		}
		String sql  = "select count(*)num from view_xsgwxx a where exists(select 1 from (select xh,xn,nd,xq,xyyj from view_xsgwxx where xh||gwdm||sqsj=?) b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.xyyj='ͨ��' and a.xh||a.gwdm||a.sqsj<>?)";
		String num = getOneRs(sql, new String[]{pkValue,pkValue}, "num");
		return Integer.parseInt(StringUtils.isNull(num) ? "0" : num) >0 ? true : false;
	}
	
	/**
	 * ����λ�����Ƿ���
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
		
		//��ѯѧ��
		sql = "select xh from xsgwxxb where xh||gwdm||sqsj=?";
		String xh = getOneRs(sql, new String[]{pkValue}, "xh");
		
		//ѧУ���ͨ��������
		sql = "select count(*)count from xsgwxxb where xn||nd||xq||gwdm||gwsbsj=(select xn||nd||xq||gwdm||gwsbsj from xsgwxxb where xh||gwdm||sqsj=?) and xh||gwdm||sqsj<>? and xxyj='ͨ��'";
		map.putAll(dao.getMap(sql, new String[]{pkValue,pkValue}, new String[]{"count"}));
		
		//ѧУ���ͨ����������
		sql = "select xh from xsgwxxb where xn||nd||xq||gwdm||gwsbsj=(select xn||nd||xq||gwdm||gwsbsj from xsgwxxb where xh||gwdm||sqsj=?) and xh||gwdm||sqsj<>? and xxyj='ͨ��'";
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
		//�й����ʴ�ѧ
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			HashMap<String, String> xsgwxx = selectXsgwxxbByPk(pkValue);
			
			//�ж��Ƿ��и�λ���ͨ��
			sql = "select count(*)num from view_xsgwxx where xh=? and xxyj='ͨ��' and xn=? and nd=? and xq=? and xh||gwdm||sqsj<>?";
		    String result = dao.getOneRs(sql, new String[]{xh,xsgwxx.get("xn"),xsgwxx.get("nd"),xsgwxx.get("xq"),pkValue}, "num");
		    result = StringUtils.isNull(result) ? "0" : result;
			if(Integer.parseInt(result)>0){
				sMess += xh + "���ʧ�ܣ��Ѿ��и�λ���ͨ����";
			}
		}
		if(Integer.parseInt(sqsyrs)<= Integer.parseInt(count)){
			sMess += xh + "���ʧ��:��λ" + map.get("gwdm") + "ʹ����������\n";
		}
		if((Integer.parseInt(sqsyrs)-Integer.parseInt(sqsyknss)<= Integer.parseInt(count) - iKnsCount) && Integer.parseInt(sqsyrs) > Integer.parseInt(count)){
			if(!dao.isKns(xh)){
				sMess += xh + "���ʧ��:��λ" + map.get("gwdm") + ",ֻ�����������ſ�ͨ��!\n";
			}
		}
		
		return sMess;
	}
	
	
	/**
	 * �人����ѧ��ȡ��λ��ְҪ����Ϣ
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
	 * ��ȡ���е�У���б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getXiaoquList(){
		return getList("select distinct dm xqdm,xqmc from dm_zju_xq order by xqdm", new String[]{}, new String[]{"xqdm","xqmc"});
	}                     
	
	/**
	 * ��ȡ���еĸ�λ�����б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzList() {
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb order by gwxzdm";
		// ---------2010/5/17 edit by luojw ----------
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			// ���ݴ�ѧ
			sql = "select distinct gwxzdm,gwxzmc from gwxzdmb where gwxzmc <> '��ʱ��λ' order by gwxzdm";
		}
		// ---------end----------
		return getList(sql, new String[] {},
				new String[] { "gwxzdm", "gwxzmc" });
	}
	
	/**
	 * ��ȡ����ʱ��λ�ĸ�λ�����б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzCjList() {
		String sql = "select distinct gwxzdm,gwxzmc from gwxzdmb order by gwxzdm";
		return getList(sql, new String[] {},
				new String[] { "gwxzdm", "gwxzmc" });
		
	}
	
	/**
	 * ��ȡ���е����˵�λ�б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(){
		return getList("select distinct yrdwdm,yrdwmc from yrdwdmb order by yrdwdm", new String[]{}, new String[]{"yrdwdm","yrdwmc"});
	}
	
	/**
	 * ��ȡ���е����˵�λ�б�
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
	 * ��ȡ���е����ö��б�
	 * @return List
	 * */
	public List<HashMap<String, String>> getXydList(){
		return getList("select distinct xyddm,xydmc from xyddmb order by xyddm", new String[]{}, new String[]{"xyddm","xydmc"});
	}	
	
	/**
	 * ��ȡ�����趨��Ϣ 
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
	 * ����ѧ����λ��Ϣ������ȡ��λ�ı����׼
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
	 * �����ܱ�����
	 * @param spbcbz
	 * @param zsj
	 * @return String
	 * */
	public String getZbcje(String spbcbz,double zsj){
		String sql = "select trunc("+zsj*Integer.parseInt(spbcbz)+",1) je from dual";
		return getOneRs(sql, new String[]{}, "je");
	}
	
	/**
	 * ��ѯ��ǰѧ�����ѧ���·ݵĳ�𷢷���Ϣ
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
	 * ��ѯ��ǰѧ�����ѧ���·ݵĳ�𷢷���Ϣ
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
	 * ��ѯ��ǰѧ�����ѧ���·ݵĳ�𷢷���Ϣ
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
			//���˵�λ�û�
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
	 * ��ѯ��ǰѧ�����ѧ���·ݵĳ�𷢷���Ϣ
	 * @return List
	 * */
	public List<HashMap<String, String>> getPayInfoByZjcmxy(String nd, String yf){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "select xh,xm ,gwdm, gzsj,cjje,yhkh kh,yhmc,yrdwmc,xymc from view_xscjff a where  ffsj like ?||'%'";
		list = getList(sql, new String[]{nd+yf}, new String[]{"xh","xm","gwdm","gzsj","cjje","kh","yhmc","yrdwmc","xymc"});
//		if(checkExists("yrdwdmb", "dlm", userName)){
//			//���˵�λ�û�
//			sql = "select xh,xm ,gwdm, gzsj,cjje,yhkh kh,yhmc from view_xscjff a where  ffsj like ?||'%' and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm)";
//		}		
		return list;
	}
	
	
	/**
	 * �����ܳ��
	 * @return String
	 * */
	public String getTotalMoney(String nd, String yf){
		String sql = "select sum(cjje) totalMoney from view_xscjff a where nd=? and yf=?";
		
		String result = getOneRs(sql, new String[]{nd,yf}, "totalMoney");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return result;
	}
	
	/**
	 * �����ܳ��
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
	 * �����ܳ��
	 * @return String
	 * */
	public String getTotalMoneyForZjcmxy(String nd, String yf){
		String sql = "select sum(cjje) totalMoney from view_xscjff a where ffsj like ?||'%'";
		
		String result = getOneRs(sql, new String[]{nd+yf}, "totalMoney");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return result;
	}
	
	/**
	 * ��ȡ��ǰ�·�
	 * @return String
	 * */
	public String getCurrentYf(){
		return getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) yf from dual", new String[]{}, "yf");
	}
	
	/**
	 * �ж��û��Ƿ��������˵�λ�û�
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
	 * �ж��Ƿ����ڹ���ѧ����ʱ����
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
	 * �ڹ���ѧ��������ѯ
	 * @param model
	 * @return List
	 * */
	public List<String[]> querryXsgwxx(CommanForm model){
		List<String[]> rs = null;
		List<String[]> tmpRs = null;
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String toolSql = "select a.*, a.xh||a.gwdm||a.sqsj ����, rownum r from view_xsgwxx a ";
		String[] colList = {};
		StringBuffer whereSql = getWhereSql(model);
		int start = model.getPages().getStart();
		int pageSize = model.getPages().getPageSize();
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			//�Ϻ����̼�����ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj", "kh" };
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			// ��������
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					                 "bjmc", "sfpks", "gwdm", "sqsj", "xyyj", 
					                 "xxyj", "kh", "bh", "gh" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//�㽭����ְҵ����ѧԺ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
			//�й�����ѧԺ
			colList = new String[]{"����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
			//���Ϲ�ҵ��ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xxyj" , "xyyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//�й����ʴ�ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "lxdh", "xxyj" };
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "sfyx", "xyyj", "xxyj" };
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
			//�㽭�Ƽ�
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc","sfpks", "yrdwmc", "gwdm", "sqsj", "lxdh", "xyyj", "xxyj" ,"��¼ȡ��λ"};			
			toolSql = StringUtils.joinStr("select a.*, a.xh||a.gwdm||a.sqsj ����, rownum r,",
			 		"(select zd from (select xh,xn,xq,max(ltrim(sys_connect_by_path(gwdm,','),','))zd from (",
					"select xh,xn,xq,gwdm,row_number() over (partition by xh,xn,xq order by gwdm) pno,",
					"row_number() over (partition by xh,xn,xq order by gwdm)-1 sno ",
					"from xsgwxxb b where xxyj='ͨ��' order by xh,xn,xq) ",
					"connect by prior xh = xh and prior xn = xn and prior xq=xq and prior sno = pno ",
					"group by xh,xn,xq) b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)��¼ȡ��λ  ",
			 		"from view_xsgwxx a ");
		}else if(Globals.XXDM_HBJTZYJSXY.equals(xxdm)){
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(Globals.XXDM_CDTYXY.equals(xxdm)){
			toolSql = StringUtils.joinStr("select a.*, a.xh||a.gwdm||a.sqsj ����, rownum r from",
			   " (select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,",
		       " a.zymc,a.bjdm,a.bjmc,a.xzb,a.zzmmm,a.zzmm,a.zwxzdm,",
		       " a.zwxzmc,a.xn,a.nd,a.xq, a.xqmc,a.yrdwdm, a.gwdm,a.sqsj,",
		       " a.xssq,a.fdyyj,a.gwxz,case when xyyrdwsh='��' then '�������' else  a.xyyj end xyyj,",
		       " a.xxyj,a.gwsbsj,a.sfpks ",
		       " from (select a.*,case when b.xyyrdwsh is null then '��' else  b.xyyrdwsh end xyyrdwsh ",
		       " from view_xsgwxx a left join gwxxb b on a.gwdm = b.gwdm and a.gwsbsj = b.gwsbsj) a)a ");
		  colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			//�人��ҵ
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){
			//�㽭����
			colList = new String[] { "����","xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
			toolSql = "select a.*, a.xh||a.gwdm||a.sqsj ����,(case when a.fdyyj<>'δ���' or a.xyyj<>'δ���' or a.xxyj<>'δ���'  then 'disabled' else '' end) ���, rownum r from view_xsgwxx a ";
			//�㽭��ְͨҵ����ѧԺ
			colList = new String[] { "����","���", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","fdyyj","xyyj", "xxyj" };
		}else {
			colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xyyj", "xxyj" };
		}
		//�人��ҵ(����Ա�û���ѯ���ݷ�Χ����)
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			if("true".equals(model.getIsFdy())){
				whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='")
						.append(model.getUserName())
						.append("') ");
			}
		}
		//�㽭��ְͨҵ����ѧԺ(����Ա�û���ѯ���ݷ�Χ����)
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
		model.getPages().setMaxRecord(tmpRs.size());	//����¼��	
		
		//�ж��Ƿ���������
		List<String[]> tempRs = new ArrayList<String[]>();
		for(int i=0; i<rs.size();i++){
			String[] values = rs.get(i);
			String xh = values.length >4 ? values[4]: "";
			//�㽭��ְͨҵ����ѧԺ
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
				values[8] = isKns(xh) == true ? "��" : "��";
				tempRs.add(values);
			}else{
				values[7] = isKns(xh) == true ? "��" : "��";
				tempRs.add(values);
			}
		}
		
		return tempRs;
	}
	
	/**
	 * �ж�ѧ���Ƿ��Ѿ��������λ
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkStuHavePost(String xh, String gwdm, String doType){
		boolean flag = false;
		HashMap<String, String> timeMap = new HashMap<String, String>();
		String count = "0";
		String sql = "select xn,nd,xq from gwsqsjb where rownum=1";
		
		timeMap = getMap(sql, new String[]{}, new String[]{"xn", "nd", "xq"});
		if(doType != null && "modi".equalsIgnoreCase(doType)){//�޸Ĳ���
			//���޸ĵĸ�λ�����ݿ���û��ԭʼ��¼ʱ����true
			sql = "select count(*) num from xsgwxxb where xh=? and xn=? and nd=? and xq=? and gwdm=?";
			count = getOneRs(sql, new String[]{xh,timeMap.get("xn"),timeMap.get("nd"),timeMap.get("xq"),gwdm}, "num");
			flag = Integer.parseInt(count)>0 ? false : true;
		}else{
			//ѧ���Ѿ��ڱ�ѧ�����ѧ�������˸�λ����true
			sql = "select count(*) num from xsgwxxb where xh=? and xn=? and nd=? and xq=?";
			count = getOneRs(sql, new String[]{xh,timeMap.get("xn"), timeMap.get("nd"), timeMap.get("xq")}, "num");
			flag = Integer.parseInt(count)>0 ? true : false;
		}
		
		return flag;
	}
	
	/**
	 * ��ȡ�趨�ĸ�λ������ʹ�ñ���
	 * @return Double
	 * */
	public Double getKnsbl(){
		String sql = "select knsbl from gwsqsjb";
		String result = getOneRs(sql, new String[]{}, "knsbl");
		
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Double.parseDouble(result);
	}
	
	/**
	 * ��ѯ��λ��ϸ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPostDetail(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		//��λ������Ϣ
		String[] outputValue = {"xq","gwdm","gwxzmc","yrdwmc","xn","nd","gzksrq","gzjsrq","xyrs","syknss","sqsyrs","sqsyknss","jcfsmc","spbcbz","mssjd","msdd",
				"fzr","lxdh","gzdd","gzsj","xydmc","rzyq_nj","rzyq_xb","rzyq_zymc","rzyq_wyyq","rzyq_gzjn","rzyq_qt","gzmd","gznd","gzyd","gzjj","mtbzgz","dqbzgz",
				"bz","gznr"};
		String sql = "select * from view_gwxx where gwdm||gwsbsj=?";
		map = dao.getMap(sql, new String[]{pkValue}, outputValue);
		//�ж���������ø�λ
		sql = "select count(*) count from view_xsgwxx where gwdm||gwsbsj=?";
		map.put("sqrs", dao.getOneRs(sql, new String[]{pkValue}, "count"));
		//�ж���������ͨ��
		sql = "select count(*) count from view_xsgwxx where gwdm||gwsbsj=? and xxyj='ͨ��'";
		map.put("tgrs", dao.getOneRs(sql, new String[]{pkValue},"count"));
		return map;
	}
	
	/**
	 * ��ȡ��λ�Ŀ���ʱ��
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
	 * ��ȡ��λ�������Ϣ
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getWorkInfo(CommanForm model){
		String pkValue = model.getPkValue();
		String[] outputValue = {"xn", "nd", "xueqi", "xueqimc", "xq", "xqmc", "gwdm", "gwsbsj","gwxz", "gwxzmc", "sqdw", "yrdwmc", "jcfs", "xyrs" ,
		        "syknss", "jybcbz", "spbcbz", "sqsyrs", "sqsyknss", "gzsjdw"};
		String sql = "select xn, nd, xueqi, xueqimc, xq, xqmc, gwdm,gwsbsj, gwxz, gwxzmc, sqdw, yrdwmc," +
				"decode(jcfs,'h','Ԫ/Сʱ','d','Ԫ/��','w','Ԫ/��','m','Ԫ/��','n','Ԫ/־Ը����') jcfs, xyrs," +
				"decode(jcfs,'h','Сʱ','d','��','w','��','m','��') gzsjdw," +
				"syknss, jybcbz, spbcbz, sqsyrs, sqsyknss from view_gwxx where gwdm||gwsbsj = ?";
		
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * ��ȡ��λ��ʣ�ྭ��
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
	 * ��ȡĳ��λ�µ�ѧ��
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
		//ѧ����λ�г�ȥ�ڱ���֮�����������λ��ѧ��
		String sql = "select a.xh,a.xm,a.bjmc,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh "
                     + " from ( " 
                     + "select a.xh,a.xm,a.bjmc from view_xsgwxx a where a.gwdm||a.gwsbsj=? and a.xxyj='ͨ��' " 
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
		
		//�ڱ��µ�����������λ��ѧ��
		sql = "select a.xh,(select b.xm from view_xsjbxx b where a.xh=b.xh)xm,(select b.bjmc from view_xsjbxx b where a.xh=b.xh)bjmc,"
              + "a.tzn,a.tzy,a.kh from "
              + "(" 
              + "select a.xh,a.tzn,a.tzy,a.tzqgw,a.tzqgwsbsj,"
              + "b.nd,b.yf,b.gzsj,b.cjje,b.bz,b.khqk ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh "
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
	 * �жϼ�¼�Ƿ����
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
	 * ��ȡѧ���Ļ�����Ϣ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,zzmmmc,lxdh,jg,mzmc,kh,(select ldmc from view_xszsxx b where a.xh=b.xh)ldmc,(select qsh from view_xszsxx b where a.xh=b.xh)qsh,ssbh from view_xsxxb a where xh=?";
		String[] columns = {"xh","xm","xb","nj","xymc","zymc","bjmc","zzmmmc","lxdh","jg","mzmc","kh","ldmc","qsh","ssbh"};		
		return getMap(sql, new String[]{xh}, columns);
	}
	
	/**
	 * ����λ�Ƿ��ܹ�����
	 * @param pkValue
	 * @return boolean
	 * */ 
	public boolean checkGwsj(String pkValue){	
		Timestamp date = new Timestamp(System.currentTimeMillis());
		String currentTime = date.toString().substring(0, 19).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		
		String sql = "select count(*)count from view_gwxx where shjg='ͨ��' and gwdm||gwsbsj=? and gzjsrq>?";
		String result = getOneRs(sql, new String[]{pkValue,currentTime}, "count");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result; 
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * ��ѯ�����˳������˵�λ
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
	 * ��ѯ���ų��λ�µĸ�λ��Ϣ
	 * @param yrdwdm
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwList(String nd, String yf, String yrdwdm){
		String sql = "select distinct gwdm,sqsj gwsbsj from  view_xscjff where ffsj like '" + nd + yf + "%' and sqdw=?";
		String[] outputValue = {"gwdm", "gwsbsj"};
		return getList(sql, new String[]{yrdwdm}, outputValue);
	}
	
	/**
	 * ��ѯ��λ�µ�ѧ�����ų����Ϣ
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
	 * ��ѯĳһѧ���ĳ�𷢷���Ϣ
	 * @param xh
	 * @return List
	 * */
	public List<HashMap<String, String>> getStuList(String nd, String yf, String gwdm, String gwsbsj,String xh){
		String sql = "select xh,xm,bjmc,cjje,(case yf when ? then '' else '����' ||yf || '�·ݹ���' end) jebz from view_xscjff where ffsj like '" + nd + yf + "%' and gwdm=? and sqsj=? and xh=?";
		String[] outputValue = {"cjje", "jebz"};
		return getList(sql, new String[]{yf, gwdm, gwsbsj,xh}, outputValue);
	}
	
	/**
	 * ��ȡ���ݼ�¼��
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
	 * ��ȡѧ���Ŀ���ʱ��
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
	 * �ж�ѧ���������λ��ʣ���λ����
	 * @param pk
	 * @param pkValue
	 * @return int
	 * */
	public int getSygwrs(String pk, String pkValue){
		String xxdm = getXxdm();
		String sql = "select count(*) num from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where " + pk + "=? ) and xyyj='ͨ��'";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){//�й����ʴ�ѧ
			sql = "select count(*) num from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where " + pk + "=? ) and xxyj='ͨ��'"; 
		}
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		
		sql = "select sqsyrs spsyrs from view_gwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_xsgwxx where " +pk+ "=?)";
		String spsyrs = getOneRs(sql, new String[]{pkValue}, "spsyrs");
		spsyrs = spsyrs == null || "".equalsIgnoreCase(spsyrs) ? "0" : spsyrs;
		
		return Integer.parseInt(spsyrs)-Integer.parseInt(num);
	}
	
	/**
	 * ����������ѯ��λ
	 * @param pk
	 * @param pkValue
	 * @return String
	 * */
	public String getGwmc(String pk, String pkValue){
		String sql = "select gwdm||gwsbsj gw from view_xsgwxx where " + pk + "=?";
		return getOneRs(sql, new String[]{pkValue}, "gw");		
	}
	
	/**
	 * ��ѯ��λʣ������
	 * @param pkValue
	 * @return int
	 * */
	public int getGwtgrs(String pkValue){
		String xxdm = getXxdm();
		String sql  = "select count(*) num from view_xsgwxx where xyyj='ͨ��' and gwdm||gwsbsj=?";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){//�й����ʴ�ѧ
			sql = "select count(*) num from view_xsgwxx where xxyj='ͨ��' and gwdm||gwsbsj=?";
		}
		String shtgrs = getOneRs(sql, new String[]{pkValue}, "num");
		shtgrs = shtgrs == null || "".equalsIgnoreCase(shtgrs) ? "0" : shtgrs;
		
		sql = "select sqsyrs from view_gwxx where gwdm||gwsbsj=?";
		String spsyrs = getOneRs(sql, new String[]{pkValue}, "sqsyrs");
		spsyrs = spsyrs == null || "".equalsIgnoreCase(spsyrs) ? "0" : spsyrs;
		
		return Integer.parseInt(spsyrs) - Integer.parseInt(shtgrs);
	}
	
	/**
	 * ����������ѯ��λ����
	 * @param pkValue
	 * @return String
	 * */
	public String getGwdm(String pkValue){
		String sql = "select gwdm from view_gwxx where gwdm||gwsbsj=?";
		return getOneRs(sql, new String[]{pkValue}, "gwdm");
	}
	
	/**
	 * ����������ȡ��λ��Ϣ
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
	 * �ж��Ƿ�������ϱ�ʱ��֮��
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
	 * �������˵�λ��ȡ�����ϱ�ʱ����Ϣ 
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
	 * ��ȡ�㽭�����𷢷�ʱ��
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
	 * ��ѯ�ڹ���ѧ����������Ϣ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConf(){
		String sql = "select * from gwsqsjb";
		String[] outputValue = {"nd","xn","xq","kssj","jssj","knsbl","mxsbc","mtzgxs","myzgxs","myzgbc","xwkssj","xwjssj","myzgsjfs","shkssj","shjssj","ffsjjg","cjffsj"};
		
		return getMap(sql, new String[]{}, outputValue);
	}
	
	/**
	 * ��ȡҪ�������ֶ���
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
	 * ��ȡҪ������ֶ���
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
	 * ��ȡ����Ա�µ�רҵ
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getZyByFdy(String zgh){
		String[] outputSQL = new String[] { "zydm", "zymc" };
		String sql="select distinct(b.zydm), b.zymc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm and a.zgh = ? order by b.zydm";
		List<HashMap<String, String>> zyList = getList(sql, new String[] { zgh }, outputSQL);
		return zyList;
	}
	
	/**
	 * ��ȡ����Ա�µİ༶
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getBjByFdy(String zgh){
		String[] outputSQL = new String[] { "bjdm", "bjmc" };
		String sql = "select distinct b.bjdm, b.bjmc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm and a.zgh = ? order by b.bjdm";
		List<HashMap<String, String>> bjList = getList(sql, new String[] { zgh },outputSQL);
		return bjList;
	}
	
	/**
	 * ��ѯ���ͨ���ĸ�λ�����б�
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwmcList(String userName){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "";
		if(checkExists("yrdwdmb", "dlm", userName)){
			sql = "select distinct gwdm from view_gwxx a where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?) and shjg='ͨ��'";
			list = getList(sql, new String[]{userName}, new String[]{"gwdm"});
		}else{
			sql = "select distinct gwdm from view_gwxx where shjg='ͨ��' ";
			list = getList(sql, new String[]{}, new String[]{"gwdm"});
		}		
		return list;
	}
	
	/**
	 * ��ѯ���и�λ�����б�
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwmcList(String userName, boolean isPass){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "";
		String query = "";		
		if(checkExists("yrdwdmb", "dlm", userName)){
			if(isPass){
				query  += " and shjg = 'ͨ��'";
			}
			sql = "select distinct gwdm from view_gwxx a where exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?)" + query;
			list = getList(sql, new String[]{userName}, new String[]{"gwdm"});
		}else{
			if(isPass){
				query  += " where shjg = 'ͨ��'";
			}
			sql = "select distinct gwdm from view_gwxx " + query;
			list = getList(sql, new String[]{}, new String[]{"gwdm"});
		}		
		return list;
	}	
	
	
	/**
	 * �������˵�λ��ѯ��λ�б�
	 * @param yrdwdm
	 * @param gwxzdm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcByYrdw(String yrdwdm, String gwxzdm){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] inputValue = new String[]{};
		String sql = "select gwdm from view_gwxx where shjg='ͨ��'";
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
	 * ��ѯ��λ��ϸ��Ϣ
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
		//�㽭��ҵ��ѧ֮��ѧԺ���������ֶΣ����뿪ʼʱ�䡢�������ʱ�䣩
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
	 * ��ѯ��λ��ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectGwxx(String pk, String pkValue,String userDep){
		String sql = "select   a.gwdm || a.gwsbsj ����,a.xq,a.gwdm, a.gwxz,a.sqdw, a.sqsj,a.gznr,a.gzsj,a.gzksrq,a.gzjsrq,a.xyrs,a.syknss,"
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
	 * ��ѯ�μӸ�λ��ѧ���б�
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>> selectCjgwxs(String pk, String pkValue){
		String sql = "select * from view_xsgwxx where  "+pk+"=?  and xxyj='ͨ��'";
		String[] cols = {"bh","bhgkm","bjdm","bjmc","bz","fdyyj","gh","gjzxdk","gwdm","gwsbsj","gwzydj","gzbx","gzjl","jsjsp","jtcy","jtcy1_cw","jtcy1_gzdwjzw","jtcy1_nsr","jtcy1_xm","jtcy2_cw","jtcy2_gzdwjzw","jtcy2_nsr","jtcy2_xm","jtcy3_cw","jtcy3_gzdwjzw","jtcy3_nsr","jtcy3_xm","jtnsr","jtysr","jtzyjjly","kcjqgzxsj","kh","ldyx","lxdh","mqqgzxqk","nd","nj","pkdj","sfdjsj","sffcfp","sfpks","sfzdtg","sqsj","ssbh","wjcf","xb","xg","xh","xm","xn","xq","xqmc","xscyj","xsgzqk","xssq","xxshyj","xxyj","xydm","xymc","xyyj","xzb","yhtc","yrdwdm","zydm","zymc","zzmm","zzmmm","zzqk"};
		return getList(sql, new String[]{pkValue}, cols);
	}
	
	/**
	 * �����û�����ȡ���˵�λ����
	 * @param String userName
	 * @return String
	 * */
	public String getYrdwmcByUser(String userName){
		String bmmc = "";
		if(checkExists("yrdwdmb", "dlm", userName)){
			//���˵�λ�û�
			String sql = "select yrdwmc from yrdwdmb where dlm=?";
			bmmc = getOneRs(sql, new String[]{userName}, "yrdwmc");
		}
		return bmmc;
	}
	
	/**
	 * ��ȡ��д���
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
	 * ����������ѯѧ����λ������Ϣ
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
	 * ��ѯ��λ�б�
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwList(CommanForm model){
		String sql = "select gwdm, gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a ";
		sql = sql + getConditionOfGwmc(model,"yes") + " and shjg = 'ͨ��' and gzjsrq>=to_char(sysdate,'yyyymmdd')";
		if(!Base.isNull(model.getUserName())){
			if(checkExists("yrdwdmb", "dlm", model.getUserName())){//���˵�λ�û�
				sql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + model.getUserName() + "')";
			}
		}
		String[] outputValue = {"gwdm", "gwdmgwsbsj"};
		return getList(sql,  value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ��ѯ��λ�б�
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectStuGwList(CommanForm model){
		String sql = "select gwdm, gwdm||'-'||gwsbsj gwdmgwsbsj from view_xsgwxx ";
		sql = sql + getConditionOfStuGw(model) + " and xxyj='ͨ��'";
		String[] outputValue = {"gwdm", "gwdmgwsbsj"};
		return getList(sql,  value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * ����ڹ���ѧ�������ʱָ���ĸ�λ�Ƿ���Ч
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
	 * ��ѯ�Ƴ귽ʽ�����б�
	 * @param boolean oneNull
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectJcfsdmbList(boolean oneNull){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "select distinct jcfsdm dm, jcfsmc mc from qgzx_jcfsdmb order by jcfsdm";
		if(oneNull){//��Ҫһ��������
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "--��ѡ��--");
			list.add(map);
		}
		list.addAll(getList(sql, new String[]{}, new String[]{"dm","mc"}));
		return list;
	}
	
	/**
	 * ��ѯ�Ƴ귽ʽ��Ӧ�ļƳ��׼
	 * @param String jcfsdm
	 * @return String
	 * */
	public String loadJcbz(String jcfsdm){
		String sql = "select jcfsdm, jcfsmc, jybcbz from qgzx_jcfsdmb where jcfsdm=?";		
		return getOneRs(sql, new String[]{jcfsdm}, "jybcbz");
	}
	
	/**
	 * ���ϴ�ѧ�ж���ѧ�ڲ�����Ŀ�����Ƿ����
	 * @param map
	 * @param boolean
	 * */
	public boolean checkBktj(HashMap<String, String> map){
		boolean result= true;
		String bkkmxz = getSqsjInfo().get("bkkmsxz");
		
		if(StringUtils.isNotNull(bkkmxz)){
			PjpyCommonInterface pInterface = new PjpyCommonInterface();
			//��ȡ��һѧ��ѧ��
			String befXq = getBeforeXq(map).get("xq");
			String befXn = getBeforeXq(map).get("xn");
			map.put("xq", befXq);
			map.put("xn", befXn);
			
			//�ӽӿڻ�ȡ������Ŀ��
			int mkkms = Integer.parseInt(pInterface.queryStuCjbkms(map)) ;
			result = mkkms <= Integer.parseInt(bkkmxz);
		}
		return result;
	}
	
	/**
	 * ��ȡ������ĸ�λ�б�
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> getKsqgwList(){
		String sql = sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='ͨ��' and gzjsrq>=to_char(sysdate,'yyyymmdd')";
		return getList(sql, new String[] {}, new String[] { "gwdm", "gwdmgwsbsj" });
	}
	
	/**
	 * ��λ������Ϣ��ѯ
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
			whereSql += " and shjg = 'ͨ��'";
		}
		
		String sql = "select distinct gwdm gwdm, gwdm gwmc from view_gwxx a " + whereSql;
		
		return getList(sql, value != null ? value.toArray(new String[0]) : new String[]{}, new String[]{"gwdm", "gwmc"});
	}
	
	/**
	 * ��λ������Ϣ��ѯ
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
			whereSql += " and shjg = 'ͨ��'";
		}
		if(StringUtils.isNotNull(paramter.get("gwfbr"))){
			whereSql += " and (gwfbr<>'" + userName + "' or gwfbr is null)";
		}
		String sql = "select distinct gwdm gwdm, gwdm gwmc from view_gwxx a " + whereSql;
		
		return getList(sql, value != null ? value.toArray(new String[0]) : new String[]{}, new String[]{"gwdm", "gwmc"});
	}
	
	/**
	 * ��ȡְλ���ʴ����б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZwxzdmList(){
		String sql = "select distinct zwxzdm dm, zwxzmc mc from qgzx_zwxzdmb order by zwxzdm";
		return getList(sql, new String[]{}, new String[]{"dm", "mc"});
	}
	
	/**
	 * ���ѧ���Ƿ��Ѿ��и�λ
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkYjbgwlq(String pkValue){
		String sql = StringUtils.joinStr("select count(*)num from xsgwxxb a where xxyj='ͨ��' ",
				     "and exists(select 1 from (select * from xsgwxxb b where b.xh||b.gwdm||b.sqsj=?) b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.gwdm<>b.gwdm and a.gwsbsj<>b.gwsbsj and a.xq=b.xq) ");
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		
		return Integer.parseInt(num)> 0 ? true : false;
	}
	
	/**
	 * ��ȡ��λ��Ϣ
	 */
	public HashMap<String,String>getGwxx(String pkValue){
		String sql="select * from view_gwxx where gwdm || gwsbsj = ? ";
		DAO dao =new DAO();
		return dao.getMap(sql, new String[]{pkValue}, new String []{"yrdwsh"});
	}
	
	/**
	 * ���ѧ���Ƿ��Ѿ��и�λ���ͨ��
	 * @param pkString
	 * @param zjkjPk
	 * */
	public String checkZjkjXsgw(String pkString, String zjkjPk){
		String msg = "";
		String[] pkV = pkString.split("!!SplitOneSplit!!");
		String[] zjkjV = zjkjPk.split("!!SplitOneSplit!!");
		for(int i=0; i<pkV.length; i++){
			String num = getOneRs("select count(*)num from xsgwxxb a where exists(select 1 from (select * from xsgwxxb b where xh||gwdm||sqsj=? and xh||xn||nd=? ) b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.gwdm<>b.gwdm and a.gwsbsj<>b.gwsbsj) and xxyj='ͨ��'", new String[]{pkV[i],zjkjV[i]}, "num");
			num = StringUtils.isNull(num) ? "0" : num;
			if(Integer.parseInt(num)>0){
				msg += "��" + (i+1) + "����¼����ѧ���Ѿ��и�λ���ͨ���ˣ�\n" ;
			}
		}		
		return msg;
	}
	
	/**
	 * ���ݸ�λ���ƻ�ȡ������ϱ�ʱ��
	 * @param gwdm
	 * @return String
	 * */
	public String queryZjgwsbsj(String gwdm){
		String sql = "select max(gwsbsj) gwsbsj from gwxxb where gwdm=?";
		return getOneRs(sql, new String[]{gwdm}, "gwsbsj");
	}
	
	/**
	 * �ж��Ƿ������ͬ�ĸ�λ��Ϣ(DWR)
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
	 * ����·����ȡTITLE
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
	 * ����λ�Ƿ�������ʱ�䷶Χ��
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
	 * ��ѯ�����λ��ѧ���б�
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
