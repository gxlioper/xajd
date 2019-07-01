package xgxt.xsgygl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;
/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��Ԣ����������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-06</p>
 */

public class GyglShareDAO {	

	/** ����У���б�*/
	public static List getXaioQList(){ 
		DAO dao        = DAO.getInstance();
		String sql     = "select dm,xqmc mc from dm_zju_xq order by dm";
		List xiaoQList = dao.getList(sql, new String[]{},new String[]{"dm","mc"});
		return xiaoQList;
	}
	/** ����ָ��У����Ԣ¥���б� ������*/
	public static List getLdList(String xiaoqu){
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xiaoqu)?" and 1=2 ":" and xqdm='"+xiaoqu+"' ");
		String sql  = "select lddm dm,ldmc mc from sslddmb "+querry+" order by lddm";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(dao.getXxdm())){//�й����ʴ�ѧ
			sql = "select lddm dm,(select yqmc from yqdmb b where a.yqdm=b.yqdm)||ldmc mc from sslddmb a "+querry+" order by lddm";
		}
		List ldList = dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
		return ldList;
	}
	/** ����ָ��У����Ԣ¥���б� ��������*/
	public static List getLdList(){
		DAO dao             = DAO.getInstance();
		String sql  = "select lddm dm,ldmc mc from sslddmb order by lddm";		
		List ldList = dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
		return ldList;
	}
	/** ���ع�Ԣ¥���б�*/
	public static List getLcList(String lddm){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		DAO  dao            = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
		String sql = " select cs from sslddmb "+querry;
		String cs  = dao.getOneRs(sql,new String[]{},"cs");
		if(!Base.isNull(cs)){
			for (int i = 0; i <= Integer.parseInt(cs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				if(i==0){
					map.put("dm","all");
					map.put("mc", "��¥");
				}else{
					map.put("dm",String.valueOf(i));
					map.put("mc","��"+String.valueOf(i)+"��");
				}
				result.add(map);
				map = null;
			}
		}
		return result;
	}	

	/** ���ع�Ԣ¥���б�*/
	public static List getLcList2(String lddm){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		DAO  dao            = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
		String sql = " select cs from sslddmb "+querry;
		String cs  = dao.getOneRs(sql,new String[]{},"cs");
		if(!Base.isNull(cs)){
			HashMap<String, String> mymap = new HashMap<String, String>();
			mymap.put("dm","");
			mymap.put("mc","--��ѡ��--");
			result.add(mymap);
			for (int i = 0; i <= Integer.parseInt(cs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();													
				map.put("dm",String.valueOf(i));
				map.put("mc",String.valueOf(i));				
				result.add(map);
				map = null;
			}
		}		
		return result;
	}	

	public static List getDistXyList(){
		DAO dao    = DAO.getInstance();
		String sql = "select distinct xydm dm,xymc mc from view_njxyzybj order by xydm";
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}

	public static List<HashMap<String, String>> getXiaoQuList(String userName) {
		DAO dao    = DAO.getInstance();
		// ��ȡУ���б�
		boolean is = isSssAdmin(userName);
		String sql = "";
		if(is){
			sql = "select distinct xqdm dm,xqmc mc from view_ssxx where (fpbj ='˶ʿ' or fpbj='��ʿ') and xqdm is not null order by to_number(dm)";
		}else{
			sql = "select dm,xqmc mc from dm_zju_xq";
		}
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	} 
	public static List<HashMap<String, String>> getXbXdList(String userName) {
		// ��ȡ�Ա��б�
		DAO dao    = DAO.getInstance();
		boolean is = isSssAdmin(userName);
		String sql = "";
		if(is){
			sql = "select'' dm,'--��ѡ��--'mc from dual union select distinct xbxd dm,xbxd mc from view_ssxx where (fpbj ='˶ʿ' or fpbj='��ʿ') and  xbxd is not null order by dm desc";
		}else{
			sql = "select'' dm,'--��ѡ��--'mc from dual union select distinct xbxd dm,xbxd mc from view_ssxx where fpbj='һ��' and  xbxd is not null order by dm desc";
		}
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});			 
	}
	public static List<HashMap<String,String>> getSsHfLdList(String xiaoq,String xbxd,String userName){
		//��ȡ���Ữ��¥���б�
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");		 
		querry.append(Base.isNull(xiaoq)?" and 1=2 ":"  and xqdm = '"+xiaoq+"' ");
		querry.append(Base.isNull(xbxd)?" and 1=2  ":" and xbxd like '%" + xbxd +"%' ");
		boolean is = isSssAdmin(userName);
		String sql = "";
		if(is){
			sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,yqmc||ldmc mc from "
				+ " view_ssxx  "+querry+" and (fpbj ='˶ʿ' or fpbj='��ʿ') order by dm desc ";
		}else{
			sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,yqmc||ldmc mc from "
				+ " view_ssxx  "+querry+" and fpbj='һ��' order by dm desc ";
		}
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });		
	}

//	public static List<HashMap<String,String>> getSsHfLdList(String xiaoq,String userName){
//	//��ȡ���Ữ��¥���б�
//	DAO dao             = DAO.getInstance();
//	StringBuffer querry = new StringBuffer(" where 1=1 ");		 
//	querry.append(Base.isNull(xiaoq)?" and 1=2 ":"  and xqdm = '"+xiaoq+"' ");
//	//querry.append(Base.isNull(xbxd)?" and 1=2  ":" and xbxd like '%" + xbxd +"%' ");
//	boolean is = isSssAdmin(userName);
//	String sql = "";
//	if(is){
//	sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,ldmc mc from "
//	+ " view_ssxx  "+querry+" and fpbj='˶ʿ' order by dm desc ";
//	}else{
//	sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,ldmc mc from "
//	+ " view_ssxx  "+querry+" and fpbj='һ��' order by dm desc ";
//	}
//	return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });		
//	}

	public static List<HashMap<String,String>> getSsHfLcList(String lddm,String userName){
		//��ȡ¥���б�
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");		
		querry.append(Base.isNull(lddm)?" and 1=2 ":"  and lddm = '"+lddm+"' ");
		boolean is = isSssAdmin(userName);
		String sql = "";
		if(is){
			sql = " select dm,mc from (select '' dm,'--��ѡ��--' mc from dual union select distinct cs dm,cs mc from view_ssxx "+querry+" and (fpbj ='˶ʿ' or fpbj='��ʿ')) order by to_number(dm) nulls first";
		}else{
			sql = " select dm,mc from (select '' dm,'--��ѡ��--' mc from dual union select distinct cs dm,cs mc from view_ssxx "+querry+" and fpbj='һ��') order by to_number(dm) nulls first";
		}
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});	
	}

	public static List<HashMap<String, String>> getSsHfSsXxList(String lddm,String cs,String userName) {
		DAO dao             = DAO.getInstance();		
		StringBuffer sql    = new StringBuffer();
		StringBuffer querry = new StringBuffer("where 1=1 ");
		//boolean is = isSssAdmin(userName);
		querry.append(Base.isNull(lddm)?" and 1=2 ":"  and lddm = '"+lddm+"' ");
		querry.append(Base.isNull(cs)||cs.equalsIgnoreCase("null")?" and 1=1 ":" and cs='"+cs+"' ");
		String querry2 = "";
		if(isSssAdmin(userName)){
			querry2 = " (fpbj ='˶ʿ' or fpbj='��ʿ') ";
		}else{
			querry2 = " fpbj='һ��' ";
		}
		sql.append("select ssbh||'/'||cws||'/'||cs dm, yqmc||ldmc||'/'||cs||'��'||'/'||qsh||'/'||cws mc from ( select yqmc,ssbh, cws,lddm,ldmc,cs,qsh from ( ");
		sql.append("select yqmc,ssbh, cws,lddm,ldmc,cs,qsh from view_ssxx a where  not exists(select ssbh from ssfpb b where a.ssbh=b.ssbh) and"+querry2 );
		sql.append(") a where not exists(select ssbh from (select ssbh,cwh from xszsxxb union select ssbh,cwh from wxs_xszsxxb ) b where a.ssbh=b.ssbh ) order by  qsh)"+querry);
		List<HashMap<String, String>> ssList = dao.getList(sql.toString(), new String[]{}, new String[] { "dm", "mc" });   
		return ssList;
	}


	/** ���ز�ʿУ���б�*/
	public static List getBsxqList(){
		DAO dao        = DAO.getInstance();
		String sql     = "select distinct xqdm dm,xqmc mc from view_ssxx where fpbj='��ʿ'";
		List xiaoQList = dao.getList(sql, new String[]{},new String[]{"dm","mc"});
		return xiaoQList;
	}
	/** ���ز�ʿ��Ԣ¥���б�*/
	public static List getBsldList(String xiaoqu,String xb){
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append((Base.isNull(xiaoqu)?" ":" and xqdm='"+xiaoqu+"' ")+(Base.isNull(xb)?" ":" and (xbxd='"+xb+"' or SFFQFJ='��')"));
		String sql  = "select distinct lddm dm,ldmc mc from view_ssxx a "+querry+"  and fpbj='��ʿ'  order by dm";
		List ldList = dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
		return ldList;
	}
	/** ���ز�ʿ��Ԣ¥���б�*/
	public static List getBscsList(String lddm,String xb){
		DAO  dao            = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append((Base.isNull(lddm)?" ":" and lddm='"+lddm+"' ")+(Base.isNull(xb)?" ":" and (xbxd='"+xb+"' or SFFQFJ='��')"));
		String sql = " select distinct cs dm,'��'||cs||'��' mc from view_ssxx "+querry+" and  fpbj='��ʿ' order by to_number(cs)";
		List<HashMap<String,String>> cslist  = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		return cslist;
	}	
	public static List getSsYHfList(String nj,String xydm,String lddm,String cs,String userName){
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1");
		querry.append(Base.isNull(nj)?"and 1=2 ":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"and 1=2 ":" and xydm='"+xydm+"' ");
		querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=1 ":" and lddm='"+lddm+"' ");
		querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":" and cs='"+cs+"' ");
		boolean is = isSssAdmin(userName);
		StringBuffer sql = new StringBuffer();
		if(is){
			querry.append(" and (fpbj ='˶ʿ' or fpbj='��ʿ') ");
			sql.append("select nj||'/'||xydm||'/'||ssbh||'/'||cws||'/'||cs dm,nj||'/'||xymc||'/'||yqmc||ldmc||'/'||cs||'��'||'/'||qsh||'/'||cws mc from ");
			sql.append("(select b.ssbh,b.nj,b.xydm,(select distinct xymc from sss_xxb c where c.xydm=b.xydm)xymc,a.qsh,a.cws,a.lddm, ");
			sql.append(" (select yqmc from sslddmb c,yqdmb d where c.yqdm=d.yqdm and c.lddm = a.lddm) yqmc,");
			sql.append("(select ldmc from sslddmb c where c.lddm=a.lddm)ldmc,a.cs,a.fpbj from ssfpb b,ssxxb a where b.ssbh=a.ssbh ) ");
			sql.append(querry+" order by nj, lddm ,to_number(cs),to_number(qsh)");
		}else{
			querry.append(" and fpbj ='һ��' ");
			sql.append("select nj||'/'||xydm||'/'||ssbh||'/'||cws||'/'||cs dm,nj||'/'||xymc||'/'||yqmc||ldmc||'/'||cs||'��'||'/'||qsh||'/'||cws mc from ");
			sql.append("(select b.ssbh,b.nj,b.xydm,(select distinct bmmc from zxbz_xxbmdm c where c.bmdm=b.xydm)xymc,a.qsh,a.cws,a.lddm, ");
			sql.append(" (select yqmc from sslddmb c,yqdmb d where c.yqdm=d.yqdm and c.lddm = a.lddm) yqmc,");
			sql.append("(select ldmc from sslddmb c where c.lddm=a.lddm)ldmc,a.cs,a.fpbj from ssfpb b,ssxxb a where b.ssbh=a.ssbh ) ");
			sql.append(querry+" order by nj, lddm ,to_number(cs),to_number(qsh)");
		}
		List a = dao.getList(sql.toString(),new String[]{},new String[]{"dm","mc"});
		return a;
	}
	//ͨ��¥���Ͳ�õ���Ӧ������list(����)
	public List getAcs_AldList(String ssbh,String type,String nj,String xydm,String userName){
		DAO dao    = DAO.getInstance();
		boolean is = isSssAdmin(userName);
		String sql = "";		
		String query = " where 1=1 ";
		if(nj != null && !nj.equals("")){
			query += " and (nj<>'"+nj+"' and xydm<>'"+xydm+"') ";
		}else{
			query += " and xydm<>'"+xydm+"' ";
		}
		String querr2 = "";
		String lddm = ssbh.substring(0,ssbh.indexOf("-"));
		if(is){
			sql = "select distinct xymc from sss_xxb where xydm=?";
			querr2 = "(fpbj ='˶ʿ' or fpbj='��ʿ') ";
		}else{
			sql = "select distinct xymc from view_njxyzybj where xydm=?"; 
			querr2 = "fpbj ='һ��' ";
		}		 
		String xymc = dao.getOneRs(sql, new String[]{xydm}, "xymc");
		if(type.equals("acs")){
			sql = " select cs  from view_ssxx where ssbh = ? ";
			String cs = dao.getOneRs(sql, new String[]{ssbh}, "cs");
			sql = "select '"+nj+"'||'/'||'"+xydm+"'||'/'||ssbh||'/'||cws||'/'||cs dm, '"+nj+"'||'/'||'"+xymc+"'||" 
			+"'/'||ldmc||'/'||cs||'��'||'/'||qsh||'/'||cws mc from view_ssxx where lddm=? and cs=? and "+querr2+" and ssbh not in (select distinct ssbh from ssfpb "+query+") order by dm";
			return dao.getList(sql, new String[]{lddm,cs}, new String[]{"dm","mc"});
		}else{
			sql = "select '"+nj+"'||'/'||'"+xydm+"'||'/'||ssbh||'/'||cws||'/'||cs dm, '"+nj+"'||'/'||'"+xymc+"'||" 
			+"'/'||ldmc||'/'||cs||'��'||'/'||qsh||'/'||cws mc from view_ssxx where lddm=? and "+querr2+" and ssbh not in (select distinct ssbh from ssfpb "+query+" ) order by dm";
			return dao.getList(sql, new String[]{lddm}, new String[]{"dm","mc"});
		}		 

	}
	//ͨ��¥���Ͳ�õ���Ӧ������list(�ͷ�)
	public List getAcs_Ald_delList(String ssbh,String type,String nj,String xydm,String userName){
		DAO dao    = DAO.getInstance();
		String sql = "";
		String querr2 = "";
		String lddm = ssbh.substring(0,ssbh.indexOf("-"));
		boolean is = isSssAdmin(userName);
		if(is){
			querr2 = "(fpbj ='˶ʿ' or fpbj='��ʿ') ";
		}else{

			querr2 = "fpbj ='һ��' ";
		}		
		String query = " where 1=1 ";
		if(nj != null && !nj.equals("")){
			query += " and nj='"+nj+"' ";
		}
		if(xydm != null && !xydm.equals("")){
			query += " and xydm='"+xydm+"' ";
		}
		if(type.equals("acs")){
			sql = " select cs  from view_ssxx where ssbh = ? ";
			String cs = dao.getOneRs(sql, new String[]{ssbh}, "cs");
			sql = "select ssbh||'/'||cws||'/'||cs dm,ldmc||'/'||cs||'��'||"
				+"'/'||qsh||'/'||cws mc from view_ssxx where lddm=? and cs=? and "+querr2+" and ssbh in (select distinct ssbh from ssfpb "+query+") order by dm";
			return dao.getList(sql, new String[]{lddm,cs}, new String[]{"dm","mc"});
		}else{
			sql = "select ssbh||'/'||cws||'/'||cs dm,ldmc||'/'||cs||'��'||" 
				+"'/'||qsh||'/'||cws mc from view_ssxx where lddm=? and "+querr2+" and ssbh in (select distinct ssbh from ssfpb "+query+") order by dm";
			return dao.getList(sql, new String[]{lddm}, new String[]{"dm","mc"});
		}		 

	}
	//���δ��������
	public static String[] getWhfrs(String nj,String xydm,String userName){
		String query = "";
		String tablename = "";
		if(nj != null && !nj.equals("")){
			query += " and nj='"+nj+"' ";
		}
		if(xydm != null && !xydm.equals("")){
			query += " and xydm='"+xydm+"' ";
		}
		DAO dao    = DAO.getInstance();
		boolean is = isSssAdmin(userName);
		if(is){
			tablename = "sss_xxb";
		}else{
			tablename = "view_xsjbxx";
		}
		String sql = "select boys+girls total,boys,girls from (select (select count(xh) from "+tablename+" where xb='��' "+query+" and xh not in (select xh from " 
		+"xszsxxb)) boys,(select count(xh) from "+tablename+" where xb='Ů' "+query+" and xh not in (select xh from xszsxxb)) girls from dual)";
		return dao.getOneRs(sql, new String[]{}, new String[]{"total","boys","girls"});
	}
	//����ѻ��ִ�λ��
	public static String[] getYhfcws(String nj,String xydm,String userName){
		String query = "";
		if(nj != null && !nj.equals("")){
			query += " and a.nj='"+nj+"' ";
		}
		if(xydm != null && !xydm.equals("")){
			query += " and a.xydm='"+xydm+"' ";
		}
		DAO dao    = DAO.getInstance();
		boolean is = isSssAdmin(userName);
		String querr2="";
		if(is){
			querr2 = "(b.fpbj ='˶ʿ' or b.fpbj='��ʿ') ";
		}else{

			querr2 = " b.fpbj ='һ��' ";
		}	



		String sql = "select boycws+girlcws+bgcws total,boycws,girlcws,bgcws from (select (select nvl(sum(a.cws),0) boycws "
			+"from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='��' "+query+") boycws,(select nvl(sum(a.cws),0)" 
			+" girlcws from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='Ů' "+query+") girlcws,(select nvl(sum(a.cws),0)"
			+" bgcws from ssfpb a,view_ssxx b where a.ssbh=b.ssbh and "+querr2+" and b.xbxd='���' "+query+") bgcws from dual)";
		return dao.getOneRs(sql, new String[]{}, new String[]{"total","boycws","girlcws","bgcws"});
	}
	public static List<HashMap<String,String>> getSsldList(String xiaoq,String yqdm,String userName){
		//��ȡ¥���б�
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");		 
		querry.append(Base.isNull(xiaoq)?" and 1=1 ":"  and xqdm = '"+xiaoq+"' ");
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(dao.getXxdm())){
			querry.append(Base.isNull(yqdm)?" and 1=1  ":" and yqdm = '" +yqdm+"' ");
		}
//		boolean is = isSssAdmin(userName);
		String sql = "";
//		if(is){
//			sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,yqmc||ldmc mc from "
//				+ " view_ssxx  "+querry+" and (fpbj='˶ʿ' or fpbj='��ʿ') and ldmc is not null order by dm nulls first ";
//		}else{
//			sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,yqmc||ldmc mc from "
//				+ " view_ssxx  "+querry+" and fpbj='һ��' and ldmc is not null order by dm nulls first ";
//		}
		sql = "select '' dm,'--��ѡ��--' mc from dual  union  select distinct lddm dm,(select yqmc from yqdmb where yqdm=a.yqdm )||ldmc mc from "
			+ " sslddmb a "+querry+"  order by dm nulls first ";
		List<HashMap<String,String>> ldList = dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });	
//		��Ԣ����Ա�жϼ��ظ���¥���б�begin
		//List<HashMap<String,String>> listTem = gyglDao.getLddmxXx(userName,xiaoq,yqdm);
		if(gyglDao.isGyFdy(userName)){
			ldList = gyglDao.getLddmxXx(userName,xiaoq,yqdm);
		}
//		��Ԣ����Ա�ж�end		
		return 	ldList;
	}
//	//��ȡ¥�������б�
	public static List<HashMap<String,String>>GetQshList(String lddm){
		DAO dao             = DAO.getInstance();
		String sql = "select '' dm,'--��ѡ��--' mc from dual" +
		" union all select qsh dm,qsh mc  from (select distinct qsh from ssxxb where lddm = ? and fpbj='һ��'  order by qsh)";
		return  dao.getList(sql, new String []{lddm}, new String []{"dm","mc"});
	}

	//��ȡ¥�������б�
	public static List<HashMap<String,String>>GetQshList(String lddm,String  userName){
		DAO dao     = DAO.getInstance();
		boolean is = isSssAdmin(userName);
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String sql  = "";
		String querry =" where 1=1 ";
		querry += Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ";
		if(is){
			querry +=" and (fpbj='˶ʿ' or fpbj='��ʿ') ";
			sql = "select '' dm,'--��ѡ��--' mc from dual" +
			" union all select qsh dm,qsh mc  from (select distinct qsh from ssxxb "+querry+" order by qsh)";
			list = dao.getList(sql, new String []{}, new String []{"dm","mc"});
		}else{
			list = GetQshList(lddm);
		}
		return list;
	}
	public static boolean isSssAdmin(String userName){//�Ƿ����о�������Ա
		DAO   dao  = DAO.getInstance();
		boolean   isNO = false;
		String sql = "select zdm from yhb where yhm=?";
		String yhzdm = dao.getOneRs(sql,new String[]{userName},"zdm");			
		if(!Base.isNull(yhzdm)&&yhzdm.equalsIgnoreCase("9999")){//"9999"�о����޹ܿ�����루�й����ʴ�ѧ��
			isNO = true;
		}	
		return isNO;
	}
	//�о�����������ѧԺ�б�
	public static List<HashMap<String,String>> getSssXyList(String userName){
		DAO dao = DAO.getInstance();
		String sql = "";
		List<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		if(isSssAdmin(userName)){
			sql = "select distinct xydm dm,xymc mc from sss_xxb order by xydm";        		
		}else{
			sql = "select distinct xydm dm,xymc mc from view_njxyzybj order by xydm";
		}
		arrayList = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		return arrayList;
	}

//	�о������������༶�б�
	public static List<HashMap<String,String>> getSssBjList(String nj,String xydm,String zydm,String userName){
		DAO dao = DAO.getInstance();
		String sql = "";
		StringBuffer querry = new StringBuffer(" where 1=1 ");        	
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"and 1=2":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");       	        	
		if(isSssAdmin(userName)){
			sql = "select '' dm, '--��ѡ��--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from sss_xxb "+querry+" order by dm ) ";        		
		}else{
			sql = "select '' dm, '--��ѡ��--' mc from dual" +
			" union all select * from (select distinct" +
			" bjdm dm,bjmc mc from view_njxyzybj a " + querry + "order by dm)";
		}
		List<HashMap<String,String>> arrayList = dao.getList(sql,new String[]{},new String[]{"dm","mc"});
		return arrayList;
	}    
//	�о�����������(��λ����)У���б�   
	public static List<HashMap<String,String>> getSssXqList(String nj,String xydm,String userName){
		DAO dao = DAO.getInstance();
		String sql = "";
		StringBuffer querry = new StringBuffer("");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?" and 1=2":" and xydm='"+xydm+"' ");
		if(isSssAdmin(userName)){
			querry.append(" and (fpbj='˶ʿ' or fpbj='��ʿ') ");
		}else{//����
			querry.append(" and fpbj='һ��' ");
		}
		sql = "select ''dm,'--��ѡ��--'mc from dual union all select * from(select distinct b.xqdm dm,b.xqmc mc from ssfpb a,view_ssxx b where  a.ssbh=b.ssbh"+querry+" order by dm) ";
		List<HashMap<String,String>> xiaoqquList = dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
		return xiaoqquList;
	}
//	�о�����������(��λ����)¥���б�        
	public static List<HashMap<String,String>>getSssLdList(String xqdm,String xydm,String xb,String nj,String userName){
		DAO dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		String sql = "";
		querry.append(" where a.ssbh=b.ssbh ");	
		querry.append(Base.isNull(xb)?" and 1=2 ":" and b.xbxd='" + xb + "' ");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and a.xydm='" + xydm + "' ");
		querry.append(Base.isNull(xqdm)?" and 1=2 ":"  and b.xqdm='" + xqdm + "' ");
		querry.append(Base.isNull(nj)?" and 1=2 ":"  and a.nj='" + nj + "' ");

		if(isSssAdmin(userName)){
			querry.append(" and (fpbj='˶ʿ' or fpbj='��ʿ') ");
		}else{
			querry.append(" and fpbj='һ��' ");
		}
		sql = "select ''dm,'--��ѡ��--'mc from dual union all select * from (select distinct b.lddm dm,b.yqmc||b.ldmc mc from ssfpb a,view_ssxx b "+querry+" )";  	 	 
		return dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
	}

//	�о�����������(��λ����)¥���б�   
	public static List getSssCsList(String lddm,String nj,String xydm,String userName){
		String sql = "";
		DAO dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();	
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(nj)?" and 1=2 ":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?" and 1=2 ":" and xydm='"+xydm+"' ");
		if(isSssAdmin(userName)){
			querry.append(" and (fpbj='˶ʿ' or fpbj='��ʿ') ");
		}else{	
			querry.append(" and fpbj='һ��' ");
		}
		sql = " select ''dm,'--��ѡ��--'mc  from dual union select distinct cs dm,'��'||cs||'��' mc from ssfpb a,ssxxb b where a.ssbh=b.ssbh "+querry+"  order by dm desc";

		List<HashMap<String, String>> csList = dao.getList(sql, new String[] {},
				new String[] { "dm","mc" });
		return csList;
	}

	//�о�����������(��λ����)��ȡ��λ���������ᴲλ��Ϣ�б�
	public static List<HashMap<String,String>>getSssCwList(String xqdm,String xydm,String lddm,String xb,String cs,String nj,String userName){
		String sql = "";
		DAO dao = DAO.getInstance();    		
		StringBuffer querry = new StringBuffer();   		
		querry.append(Base.isNull(xydm)?" and 1=2 ":"  and a.xydm='" + xydm + "'");
		querry.append((Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null"))?" and 1=2 ":" and b.xqdm='" + xqdm + "'");
		querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=2 ":"  and b.lddm='" + lddm + "'");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=2 ":"  and b.xbxd='" + xb + "' ");
		querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":"  and b.cs='" + cs + "' ");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and a.nj='"+nj+"' ");
		if(isSssAdmin(userName)){
			querry.append(" and (fpbj='˶ʿ' or fpbj='��ʿ') ");
		}else{	
			querry.append(" and fpbj='һ��' ");
		}
		querry.append(" order by dm");
		sql = "  select distinct (a.ssbh||'/'||c.cwh) dm,b.yqmc||b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc from ssfpb a,view_ssxx b,"
			+ "cwxxb c where a.ssbh=b.ssbh and a.ssbh=c.ssbh and a.ssbh||'-'||c.cwh not in "
			+ "(select ssbh||'-'||cwh from xszsxxb)"+querry;			
		return dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });
	}


	//�о�����������(��λ����)��ȡ��λ������ѧ���б�  	
	public static List<HashMap<String,String>> getSssXsList(String xydm,String nj,String bjdm,String xb,String userName){
		StringBuffer querry = new StringBuffer();
		String sql = "";
		DAO dao = DAO.getInstance(); 
		querry.append(Base.isNull(xydm)?"  and 1=2 ":" and xydm='" + xydm + "' ");
		querry.append(Base.isNull(nj)?"  and 1=1 ":" and nj='" + nj + "' ");
		querry.append(Base.isNull(bjdm)?" and 1=1 ":" and bjdm='" + bjdm + "' ");
		querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("���"))?" and 1=1 ":" and xb='" + xb + "' ");
		if(isSssAdmin(userName)){
			sql = " select xh dm,xh||'/'||xm||'/'||xb mc from sss_xxb where xh not in (select xh from xszsxxb) "
				+ querry+" order by xb desc,xh ";
		}else{	
			sql = " select xh dm,xh||'/'||xm||'/'||xb mc from view_xsjbxx where xh not in (select xh from xszsxxb) "
				+ querry+" order by xb desc,xh ";
		} 	        
		List<HashMap<String,String>> xsList = dao.getList(sql, new String[] {}, new String[] {
				"dm", "mc" });
		return xsList;
	}


	// �о�����������(��λ����)��ȡ��λ�������ѷ��䴲λ��Ϣ�б�   	 
	public static List<HashMap<String,String>> getSssFpCwList(String xydm,String nj,String bjdm,String xb,String userName){
		StringBuffer querry = new StringBuffer();
		DAO dao = DAO.getInstance(); 
		String sql = "";
		querry.append("where 1=1 ");
		querry.append(Base.isNull(xydm)?" and 1=1 ":" and xydm = '"+xydm+"'");
		querry.append(Base.isNull(nj)?" and 1=1 ":" and nj = '"+nj+"' ");
		querry.append(Base.isNull(bjdm)?" and 1=1 ":" and bjdm = '"+bjdm+"' ");
		querry.append((Base.isNull(xb)||"���".equalsIgnoreCase(xb))?" ":" and xb = '"+xb+"' ");
		if(isSssAdmin(userName)){
			sql = "select distinct (xh||'/'||ssbh||'/'||cwh||'/'||rzrq) dm , xh||'/'||xm||'/'||xb||'/'||yqmc||ldmc||qsh||'/'||cws||'/'||cwh||'/'||rzrq mc from view_ssszsxx  "+querry;
		}else{
			sql = "select distinct (xh||'/'||ssbh||'/'||cwh||'/'||rzrq) dm , xh||'/'||xm||'/'||xb||'/'||yqmc||ldmc||qsh||'/'||cws||'/'||cwh||'/'||rzrq mc from view_xszsxx "+querry;
		}
		List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	// �о�����������(��λ����)��ȡ��λ�������ѷ��䴲λ��Ϣ�б�   	 
	public static List<HashMap<String,String>> getXhSssFpCwList(String xh,String userName){
		StringBuffer querry = new StringBuffer();
		DAO dao = DAO.getInstance(); 
		String sql = "";
		querry.append("where 1=1 ");
		querry.append(Base.isNull(xh)?" and 1=1 ":" and xh = '"+xh+"'");
		if(isSssAdmin(userName)){
			sql = "select distinct (xh||'/'||ssbh||'/'||cwh) dm , xh||'/'||xm||'/'||xb||'/'||yqmc||ldmc||qsh||'/'||cws||'/'||cwh mc from view_ssszsxx  "+querry;
		}else{
			sql = "select distinct (xh||'/'||ssbh||'/'||cwh) dm , xh||'/'||xm||'/'||xb||'/'||yqmc||ldmc||qsh||'/'||cws||'/'||cwh mc from view_xszsxx "+querry;
		}
		List<HashMap<String,String>> fpCwList = dao.getList(sql, new String[] {}, new String[] {	"dm", "mc" });
		return fpCwList;
	}
	public  static String[] getSssCwYfpZsData(String xydm,String nj,String bjdm,String userName){
		String[] dataTem = null;
		DAO dao = DAO.getInstance(); 
		StringBuffer querry = new StringBuffer();
		StringBuffer querry1 = new StringBuffer();
		StringBuffer querry2 = new StringBuffer();
		//StringBuffer querry3 = new StringBuffer();
		querry.append(Base.isNull(xydm)?" 1=2 ":" xydm='" + xydm + "' ");
		querry1.append(Base.isNull(nj)?" 1=1 ":" nj='" + nj + "' ");
		querry2.append(Base.isNull(bjdm)?" 1=1 ":" bjdm='" + bjdm + "' ");
		//querry3.append(Base.isNull(zydm)?" 1=1 ":" zydm='" + zydm + "' ");
		String tableName = "view_xszsxx";
		if(isSssAdmin(userName)){
			tableName = "view_ssszsxx"; 
		}
		String sql = "select a.boy+a.girl total,a.boy,a.girl from (select (select count(*) from "+tableName+" where "
		+ querry
		+ " and "
		+ querry1
		+ " and "
		+ querry2   			
		+ " and xb='��' ) boy,(select count(*) from "+tableName+" where "
		+ querry
		+ " and "
		+ querry1
		+ " and "
		+ querry2    			
		+ " and xb='Ů' ) girl from dual) a";
		dataTem = dao.getOneRs(sql, new String[] {}, new String[] {"total", "boy", "girl" });
		return dataTem;
	}
	public static String[] getSssCwWfpZsData(String xydm,String nj,String bjdm,String userName){
		String[] dataTem = null;
		DAO dao = DAO.getInstance(); 
		StringBuffer querry = new StringBuffer();
		StringBuffer querry1 = new StringBuffer();
		StringBuffer querry2 = new StringBuffer();
		//StringBuffer querry3 = new StringBuffer();
		querry.append(Base.isNull(xydm)?" 1=1 ":" xydm='" + xydm + "' ");
		querry1.append(Base.isNull(nj)?" 1=1 ":" nj='" + nj + "' ");
		querry2.append(Base.isNull(bjdm)?" 1=1 ":" bjdm='" + bjdm + "' ");
		//querry3.append(Base.isNull(zydm)?" 1=1 ":" zydm='" + zydm + "' ");
		String tableName = "view_xsjbxx";
		if(isSssAdmin(userName)){
			tableName = "sss_xxb"; 
		}
		String sql = "select a.noboy+a.nogirl nototal,a.noboy,a.nogirl from (select (select count(*) from "+tableName+" where "
		+ querry
		+ " and "
		+ querry1
		+ " and "
		+ querry2   			
		+ " and xb='��' and xh not in (select xh from xszsxxb ) ) noboy,(select count(*) from "+tableName+" where "
		+ querry
		+ " and "
		+ querry1
		+ " and "
		+ querry2    			
		+ " and xb='Ů' and xh not in (select xh from xszsxxb ) ) nogirl from dual) a";
		dataTem = dao.getOneRs(sql, new String[] {}, new String[] {"nototal", "noboy", "nogirl" });
		return dataTem;
	}
	//��ȡס�޷��б�
	public static List<HashMap<String,String>>GetSfbzList(String lddm,String xb){
		DAO dao             = DAO.getInstance();
		String sql = "select dm,mc from (select distinct sfbz dm,sfbz||'Ԫ' mc  from view_ssxx where fpbj='��ʿ' and sfbz is not null "+(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ")
		+(Base.isNull(xb)?" )":" and (xbxd='"+xb+"' or SFFQFJ='��'))")+"order by to_number(dm)";
		return dao.getList(sql, new String []{}, new String []{"dm","mc"});
	}

	/**��֤Ҫ���ӵ��������Ƿ��Ѿ�����*/
	public boolean isSsbhExists(String ssbh){
		DAO dao = DAO.getInstance();
		String sql = "select ssbh from ssxxb where ssbh =?";
		String ssbhStr = dao.getOneRs(sql, new String[]{ssbh}, "ssbh");
		return StringUtils.isNull(ssbhStr) ? false : true;
	}
//	��ȡ¥�����ұ���б�
	public static List<HashMap<String,String>>GetSsbhList(String lddm){
		DAO dao             = DAO.getInstance();
		String sql = "select '' dm,'--��ѡ��--' mc from dual" +
		" union all select ssbh dm,qsh mc  from (select ssbh,qsh  from ssxxb where lddm = ? order by qsh)";
		return dao.getList(sql, new String []{lddm}, new String []{"dm","mc"});
	}
	/** ����԰���б�*/
	public static List getYqList(String xqdm){
		DAO dao  = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xqdm)?"":" and yqdm='"+xqdm+"' ");
		String sql = "select '' dm,'--��ѡ��--' mc from dual union all select  dm, mc  from (select yqdm dm,yqmc mc from yqdmb "+querry+" order by to_number(dm)) ";
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	/** ����԰��¥���б�*/
	public static List getYqLdList(String yqdm){			
		DAO dao             = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(yqdm)?" and 1=2 ":" and yqdm='"+yqdm+"' ");		
		String sql  = " select '' dm,'--��ѡ��--' mc from dual union all select  dm, mc  from (select lddm dm,ldmc mc from sslddmb "+querry+" order by  lddm )";			
		List ldList = dao.getList(sql, new String[] {}, new String[] {"dm", "mc" });

		return ldList;
	}
	public static HashMap<String,String>GetYqMInfo(String yqdm){
		DAO dao             = DAO.getInstance();
		String sql = "select * from yqfzrb where yqdm=? and sfzz='��' and rownum=1 ";
		return dao.getMap(sql, new String []{yqdm}, new String []{"xm","lxdh","dzyx","bz","rzrq","lzrq"});
	}
	public static HashMap<String,String>GetLzMInfo(String lddm){
		DAO dao             = DAO.getInstance();			
		String sql = "select * from zgdd_lzxxb where lddm=? and sfzz='��' and rownum=1 ";		
		return dao.getMap(sql, new String []{lddm}, new String []{"lddm","lz","rzrq","lzrq","lxdh","dzyx","bz","sfzz"});
	}
	@SuppressWarnings("unchecked")
	public static List<HashMap<String, String>>  getqslbList() {
		DAO dao       = DAO.getInstance();
		String sql    = "select lbdm,lbmc from qslbdmb";
		List qslbList = dao.getList(sql, new String[] {}, new String[] {
				"lbdm", "lbmc" });
		return qslbList;
	}
	//��ȡ��Ԣ�����涨��
	public String getGyDyXf(String table,String colName,String pk,String pkValue){
		DAO dao = DAO.getInstance();
		String result = "";		
		String sql = "select "+colName+" num from " + table + " where "+ pk + "=? and rownum=1";
		result = dao.getOneRs(sql,new String[] { pkValue }, "num");		
		return Base.isNull(result)?"":result;
	}
	
	//��ȡ¥�������б�
	public static List<HashMap<String, String>> GetLcQshList(String lddm,
			String lc) {
		DAO dao = DAO.getInstance();
		String querry = "where 1=1 ";
		querry += Base.isNull(lddm) ? " and 1=1 " : " and lddm='" + lddm + "'";
		querry += Base.isNull(lc) ? " and 1=1 " : "and cs='" + lc + "'";
		String sql = "select '' dm,'--��ѡ��--' mc from dual"
				+ " union all select distinct qsh dm,qsh mc  from (select distinct qsh from ssxxb  "
				+ querry + " and fpbj='һ��'  )order by dm  nulls first";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
    
    public boolean getLcqszZzpd(String tableName,String pko,String pkoV,String pkt,String pktV){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		String counto = dao.getOneRs("select count(*)cout from "+tableName+" where "+pko+"=? and sfzz='��'",new String[]{pkoV}, "cout");
		String countt = dao.getOneRs("select count(*)cout from "+tableName+" where "+pkt+"=? and sfzz='��'", new String[]{pktV},"cout");
		if(Integer.parseInt(counto)>0&&Integer.parseInt(countt)==0){
			flag = false;
		}
		return flag;
	}
}