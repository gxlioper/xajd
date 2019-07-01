
package xgxt.xsgygl.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.action.XsgyglForm;

import common.Globals;

public class gyglDao {
	ArrayList<String> values = new ArrayList<String>();
	
	public StringBuffer getWhereSql(XsgyglForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String xn = model.getXn();
		String xq = model.getXq();
		String yf = model.getYf();
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		String fdysh = model.getFdysh();
		String xysh = model.getXysh();
		String xxsh = model.getXxsh();
		String sqsjks  = model.getSqsjks();
		String sqsjjs = model.getSqsjjs();
		String sqr = model.getSqr();
		
		if (!StringUtils.isNull(xn)) {
			sb.append(" and a.xn = ?");
			values.add(xn);
		}		
		if (!StringUtils.isNull(xq)) {
			sb.append(" and a.xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(yf)) {
			sb.append(" and a.yf = ?");
			values.add(yf);
		}
		if (!StringUtils.isNull(lddm)) {
			sb.append(" and a.lddm = ?");
			values.add(lddm);
		}
		if (!StringUtils.isNull(qsh)) {
			sb.append(" and a.qsh = ?");
			values.add(qsh);
		}
		if (!StringUtils.isNull(fdysh)) {
			sb.append(" and a.fdysh = ?");
			values.add(fdysh);
		}
		if (!StringUtils.isNull(xysh)) {
			sb.append(" and a.xysh = ?");
			values.add(xysh);
		}
		if (!StringUtils.isNull(xxsh)) {
			sb.append(" and a.xxsh = ?");
			values.add(xxsh);
		}
		if (!StringUtils.isNull(sqsjks)) {
			sb.append(" and to_number(substr(a.sqsj,0,4)||substr(a.sqsj,6,2)||substr(a.sqsj,9,2)) >= to_number(?)");
			values.add(sqsjks);
		}
		if (!StringUtils.isNull(sqsjjs)) {
			sb.append(" and to_number(substr(a.sqsj,0,4)||substr(a.sqsj,6,2)||substr(a.sqsj,9,2)) <= to_number(?)");
			values.add(sqsjjs);
		}
		if (!StringUtils.isNull(sqr)) {
			sb.append(" and sqr=?");
			values.add(sqr);
		}
		
		return sb;
	}
	
	/**�ж��Ƿ��ǹ�Ԣ����Ա*/
	public static boolean isGyFdy(String userName){
		DAO dao = DAO.getInstance();
		boolean isNo = false;
		String cout = "";
		String sql = "select count(*)cout from gygl_lzxxb  where yhm=? and xn=? and xq=?";
		cout = dao.getOneRs(sql,new String[]{userName,Base.currXn,Base.currXq}, "cout");
		if(Integer.parseInt(cout)>0){
			isNo = true;
		}
		return isNo;
	}
	
	/**
	 * @descrip  ���ݵ�¼�����¥������
	 */
	public static List<HashMap<String, String>> getLddmxXx(String userName,String xqdm,String yqdm) {
		DAO dao = DAO.getInstance();
		String querry  = "";
		querry = Base.isNull(xqdm)?"":" and b.xqdm='"+xqdm+"' ";
		querry += Base.isNull(yqdm)?"":" and b.yqdm='"+yqdm+"' ";
		//���ҵ�ǰ�û��ڵ�ǰѧ�ꡢѧ�ڵ���¥��¥������			
		return dao.getList("select a.lddm dm,a.lddm,(select yqmc from yqdmb where yqdm=b.yqdm)||b.ldmc ldmc,(select yqmc from yqdmb where yqdm=b.yqdm)||b.ldmc mc  from gygl_lzxxb a,sslddmb b  where a.lddm=b.lddm and yhm=? and xn=? and xq=?   "+querry+" order by  lddm   ",
				new String[]{userName,Base.currXn,Base.currXq}, new String[]{"lddm","ldmc","dm","mc"});
	}

	public void getOrthList(DAO dao,HttpServletRequest request ){//��ȡ��ѧԺ���༶������List����
		String sql = "";
		sql = "select mzdm,mzmc from mzdmb ";
		List mzList = dao.getList(sql,new String[]{},new String[]{"mzdm","mzmc"});
		List zzmmList = dao.getList("select zzmmdm,zzmmmc from zzmmdmb ",new String[]{},new String[]{"zzmmdm","zzmmmc"});
		List sydList = dao.getList("select * from syddmb", new String[]{}, new String[]{"syddm","sydmc"});
		request.setAttribute("zzmmList",zzmmList);//������òList
		request.setAttribute("sydList",sydList);//������òList
		request.setAttribute("mzList",mzList);//����List
	}
	/**
	 * @param request
	 * @param myDao
	 *            ����ѧ��ѧ������б� dia
	 */
	@SuppressWarnings("unchecked")
	public static void getLdLcQshList(HttpServletRequest request) {	
		DAO dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		String sql = "";
		String lc = request.getParameter("lc");
		StringBuffer cxtj = new StringBuffer();
		String xqdm = request.getParameter("xqdm");
		String yqdm = request.getParameter("yqdm");
		String lddm = request.getParameter("lddm");
//		String xxdm  = dao.getXxdm(); 
		cxtj.append(" where 1=1");
		if ("".equalsIgnoreCase(lddm) || lddm == null) {
			cxtj.append(" and 1=2");
		} else {
			if (Check_Input_Value.check2(lddm)) {
				cxtj.append(" and lddm='");
				cxtj.append(lddm);
				cxtj.append("'");
			}
		}

		sql = "select distinct qsh from ssxxb " + cxtj + " order by qsh";
		List ssList = dao.getList(sql, new String[] {}, new String[] { "qsh" });
		request.setAttribute("ssList", ssList);

//		sql = "select lc dm,lc mc from (select distinct substr(qsh,1,1) lc from ssxxb) order by lc";
//		List lcList = dao.getList(sql, new String[] {}, new String[] { "dm",
//		"mc" });
//		request.setAttribute("lcList", lcList);
//		��ʼ¥�������ᣬ��λList
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)){
			sql = "select * from (select '' lddm,'--��ѡ��--' ldmc from dual  union  select distinct lddm ,yqmc||ldmc ldmc from "
				+"view_ssxx where (fpbj='һ��') and ldmc is not null) order by lddm nulls first ";	
		}else{
			sql = "select * from (select '' lddm,'--��ѡ��--' ldmc from dual  union  select distinct lddm ,ldmc  from "
				+"view_ssxx where (fpbj='һ��') and ldmc is not null) order by lddm nulls first ";
		}
		List ldList = dao.getList(sql,new String[] {}, new String[] { "lddm", "ldmc" });
//		sql = "select qsh dm ,qsh mc from (select distinct qsh from ssxxb )order by qsh";
//		List qshList = dao.getList(sql, new String[] {}, new String[] { "dm","mc" });			
		List lcList=getList.GetLcList(lddm,"");
		List qshList=getList.GetQshList2(lddm,lc,"");
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "cwh";
		}else{
			sb = "to_number(cwh)";
		}
		List cwhList = dao.getList("select distinct(cwh) dm,cwh mc from xszsxxb  order by "+sb+" ",
				new String[]{},new String[] {"dm","mc"});
		List bmList = dao.getList("select bmdm,bmmc from gywsjcbmb",
				new String[] {}, new String[] { "bmdm", "bmmc" });
		
		getXnxqList(request);				
//		��Ԣ����Ա�жϼ��ظ���¥���б�begin
		if(gyglDao.isGyFdy(request.getSession().getAttribute("userName").toString())){
			List listTem = gyglDao.getLddmxXx(request.getSession().getAttribute("userName").toString(),xqdm,yqdm);
			ldList = listTem;
			HashMap<String,String> hmap = new HashMap<String,String>();
			String ldtem =""; 
			if (listTem.size()>0){
			   hmap = (HashMap<String,String>)listTem.get(0);
			   ldtem = hmap.get("lddm");
		    }
			lcList = GyglShareDAO.getLcList2(ldtem);			
			qshList = dao.getList(" select '' dm,'--��ѡ��--'mc from dual union all select distinct qsh dm,qsh mc from ssxxb where lddm=? order by dm nulls first ",
					new String[] {ldtem}, new String[] { "dm","mc" });
		}
//		��Ԣ����Ա�ж�end
		request.setAttribute("ldList", ldList);
		request.setAttribute("qshList", qshList);		
		request.setAttribute("cwhList", cwhList);
		request.setAttribute("lcList", lcList);			
		request.setAttribute("bmList", bmList);
	}
	public static void reLoadLb(HttpServletRequest request){//��¥������Ա��¼ʱ���¼�������б�
		DAO dao = DAO.getInstance();
		String xqdm = request.getParameter("xqdm");
		String yqdm = request.getParameter("yqdm");
		//	��Ԣ����Ա�жϼ��ظ���¥���б�
		getXnxqList(request);
		List<HashMap<String, String>> ldList = new ArrayList<HashMap<String,String>>();	
		List qshList = new ArrayList<HashMap<String,String>>();
		List lcList = new ArrayList<HashMap<String,String>>();
		if(gyglDao.isGyFdy(request.getSession().getAttribute("userName").toString())){
			List<HashMap<String, String>> listTem = gyglDao.getLddmxXx(request.getSession().getAttribute("userName").toString(),xqdm,yqdm);
			ldList = listTem;
			HashMap<String,String> hmap = new HashMap<String,String>();
			String ldtem =""; 
			if (listTem.size()>0){
			   hmap = (HashMap<String,String>)listTem.get(0);
			   ldtem = hmap.get("lddm");
		    }
			qshList = dao.getList("select distinct qsh dm,qsh mc from ssxxb where lddm=? order by dm ",new String[] {ldtem}, new String[] { "dm","mc"});			
			lcList = GyglShareDAO.getLcList2(ldtem);
			qshList = dao.getList(" select '' dm,'--��ѡ��--'mc from dual union all select distinct qsh dm,qsh mc from ssxxb where lddm=? order by dm nulls first ",
			new String[] {ldtem}, new String[] { "dm","mc" });
		}
		request.setAttribute("ldList", ldList);
		request.setAttribute("qshList",qshList);
		request.setAttribute("ssList",qshList);
		request.setAttribute("lcList",lcList);
		request.setAttribute("csList",lcList);		
	}
	/**
	 * @param request
	 * @param myDao
	 *            ����ѧ��ѧ������б�
	 */
	public static void getXnxqList(HttpServletRequest request){
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
	}
	/**
	 * @param request
	 * @param myDao
	 *            ����ѧԺרҵ�༶��Ϣ
	 */
	public static void getXyZyBjxx(HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
		}
		String userName = request.getSession().getAttribute("userName").toString();
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
//		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ���Ͱ༶�б�
		if(Fdypd.isFdy(userName)){
			//����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList (userName));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList (userName));// ���Ͱ༶�б�					
		}
	}
	/** ���ع�Ԣ¥���б�*/
	public List getGyLdList(){
		DAO dao     = DAO.getInstance();
		String sql  = "select lddm,ldmc from sslddmb  order by  lddm ";
		List ldList = dao.getList(sql, new String[] {}, new String[] {
				"lddm", "ldmc" });
		return ldList;
	}
	/** �������Һ��б�*/
	public List getQshList(){
		DAO dao     = DAO.getInstance();
		String sql = "select qsh dm ,qsh mc from (select distinct qsh from ssxxb where fpbj='һ��' )order by qsh";			
		List qshList = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		return qshList;
	}
	/** ���ظ���Ա�б�*/
	public List<HashMap<String, String>> getFdyList(){
		DAO dao = DAO.getInstance();
		return dao.getList("select zgh,xm from fdyxxb", new String[]{},new String[]{"zgh","xm"});
	}
	/**������ס�����б�*/
	public static List<HashMap<String,String>> getWzlxList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct wzlxdm,wzlxmc from wzlxdmb";
		return dao.getList(sql, new String[] {}, new String[] { "wzlxdm","wzlxmc" });
	}
	/**��ɳ������Ԣά������*/
	public  List<HashMap<String,String>> getGyWxNrList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct nrdm,nrmc from csmz_gywxnrdmb order by nrdm ";
		return dao.getList(sql, new String[] {}, new String[] {"nrdm","nrmc"});
	}
	/**��ɳ������Ԣά�޸�����*/
	public  List<HashMap<String,String>> getGyWxFzBmList(){
		DAO dao = DAO.getInstance();
		String sql = "select distinct fzbmdm,fzbmmc from csmz_gywxfzbmdmb order by fzbmdm";
		return dao.getList(sql, new String[] {}, new String[] {"fzbmdm","fzbmmc"});
	}
	public List<HashMap<String,String>> getXsYxList( String nj){
		DAO dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append( Base.isNull(nj)?"":" where nj='"+nj+"' ");
		String sql = "select distinct xydm,xymc from view_newstuinfo "+querry+" order by xydm ";
		return dao.getList(sql, new String[]{},new String[]{"xydm","xymc"});	
	}
	public List<HashMap<String,String>> getXiaoQuList(){
		DAO dao = DAO.getInstance();
		String sql = " select dm,xqmc from dm_zju_xq ";//"select distinct b.xqdm dm,b.xqmc from ssfpb a,view_ssxx b where a.ssbh=b.ssbh";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "xqmc" });
	}
//	����ѧ��������Ϣ
	public HashMap<String,String> getStuInfo(String xh){
		HashMap<String,String> map = new HashMap<String,String>();
		DAO dao    = DAO.getInstance();
		String sql = "select xh,xm,xb,bjmc,zymc,xymc,nj,zzmmmc,lxdh,jg,mzmc,kh,ssbh,(select ldmc||qsh from view_ssxx b where a.ssbh=b.ssbh )ssmc,qsdh from view_xsxxb a where xh=?";
		String[] colList = dao.getColumnName("select xh,xm,xb,bjmc,zymc,xymc,zzmmmc,lxdh,jg,mzmc,nj,kh,ssbh,'������ע��'ssmc,qsdh from view_xsxxb where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		return map;
	}
	public static List<HashMap<String,String>> dao_zsList(){
		DAO dao    = DAO.getInstance();
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String sql ="select xqzs from xtszb";
		String zs = dao.getOneRs(sql, new String[]{}, "xqzs");
		if(zs !=null&&!"".equalsIgnoreCase(zs)){
		// ������dao_alreadyCTLCQrr�����е��������һ��
		for (int i = 1; i <= Integer.parseInt(zs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("en", ((String.valueOf(i).length() == 1) ? "0" + i : i)
						.toString());
				map.put("cn", "��" + i + "��");
				result.add(map);
				map = null;
			}
		}
		return result;	  
	}
	/**
	 * ��������ģʽ�Ƿ�ѧУ�����ж�
	 * @param xxdm
	 * @return
	 */
	public static boolean xsfpMsXxPd(String xxdm){
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)//��ɳ����ְҵ����ѧԺ
//		||xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)//���Ϲ�ҵ��ѧ
//		||Globals.XXDM_CSDLZYJSXY.equalsIgnoreCase(xxdm)
//		||Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){//��ɳ����ְҵ����ѧԺ,���Ϲ�ҵ��ѧ,������Ϣְҵ����ѧԺ,��ɳ����ְҵ����ѧԺ
//			return true;
//		}else{
//			return false;
//		}
		return true;
	}
	/**
	 * ��ȡ��������(�����־)
	 * @return
	 */
	public static List<HashMap<String, String>> getQsLxList(int num) {
		DAO dao = DAO.getInstance();
		// ��ȡ�Ա��б�
		String[] mc = null;
		if(num==1){
			mc = new String[] { "һ��", "����"};
		}if(num==2){
			mc = new String[] { "һ��", "˶ʿ","��ʿ","����"};
		}
		return dao.arrayToList(mc, mc);
	}
	/**
	 * ��ְҵ��Ԣ����Ա�ж�
	 */
	 public static boolean gyFdyPd(String userName,String xn,String xq){
		 DAO dao = DAO.getInstance();
		 boolean flag = false;
		 String sql = "select count(*)cout from jhzy_gyfdyb where yhm=? and xn=? and xq=? ";
		 String cout = dao.getOneRs(sql,new String[]{userName,xn,xq},"cout");
		 if(Integer.parseInt(cout)>0){
			 flag = true;
		 }
		 return flag;
	 }
	/**
	 * ��ְҵ��Ԣ����Ա������¥��
	 */
	 public static List<HashMap<String, String>> getGyFdyLdList(String userName,String xn,String xq) {
		 DAO dao = DAO.getInstance(); 
		 String sql = "select ''dm,'--��ѡ��--'mc,''lddm,'--��ѡ��--'ldmc from dual union all";
		        sql+=" select dm,mc,lddm,ldmc from (select distinct b.lddm,b.lddm dm,b.ldmc,b.ldmc mc from jhzy_gyfdyb a,view_ssxx b ";
		        sql+=" where a.ssbh=b.ssbh and yhm=? and xn=? and xq=? order by to_number(dm))";
		 return dao.getList(sql,new String[]{userName,xn,xq},new String[]{"dm","mc","lddm","ldmc"});
		 
	 }
	/**
	 * ��ְҵ��Ԣ����Ա������¥��
	 */
	 public static List<HashMap<String, String>> getGyFdyLcList(String userName,String xn,String xq,String lddm) {
		 DAO dao = DAO.getInstance(); 
		 String sql = "select ''dm,'--��ѡ��--'mc from dual union all";
		        sql+=" select dm,mc from (select distinct b.cs dm,b.cs mc from jhzy_gyfdyb a,view_ssxx b  ";
		        sql+=" where a.ssbh=b.ssbh and yhm=? and xn=? and xq=? and b.lddm=?  order by to_number(dm))";
		 return dao.getList(sql,new String[]{userName,xn,xq,lddm},new String[]{"dm","mc"});		 
	 }
	/**
	 * ��ְҵ��Ԣ����Ա����������
	 */
	 public static List<HashMap<String, String>> getGyFdyQshList(String userName,String xn,String xq,String lddm,String lc) {
		 DAO dao = DAO.getInstance(); 
		 String sql = "select ''dm,'--��ѡ��--'mc from dual union all";
		        sql+=" select dm,mc from (select distinct b.qsh dm,b.qsh mc from jhzy_gyfdyb a,view_ssxx b   ";
		        sql+=" where a.ssbh=b.ssbh and yhm=? and xn=? and xq=? and b.lddm=? and b.cs=? order by to_number(dm))";
		 return dao.getList(sql,new String[]{userName,xn,xq,lddm,lc},new String[]{"dm","mc"});		 
	 }
	 /**
      * ��ְҵ��Ԣ����Ա������
      */
	 public static void setGyfdy(HttpServletRequest request,String userName,String lddm,String lc,String xn,String xq){
		 if(gyFdyPd(userName, xn, xq)){			 
			 request.setAttribute("ldList", getGyFdyLdList(userName, xn, xq));
			 request.setAttribute("lcList", getGyFdyLcList(userName, xn, xq, lddm));
			 request.setAttribute("qshList", getGyFdyQshList(userName, xn, xq, lddm, lc));	
			 request.setAttribute("userType","isGyFdy");	
		 }
	 }
	 /**
	  * ��ְҵ��Ԣ����Ա��Ϣ
	  */
	 public static HashMap<String, String>gyGyFdyXx(String xn,String xq,String ssbh){
		 DAO dao = DAO.getInstance();
		 String sql = "select yhm,xm,(select szbm yhb b where a.yhm=b.yhm )xydm,bmmc,lxdh from jhzy_gyfdyb a where ssbh=? and xn=? and xq=? ";
		 return dao.getMap(sql,new String[]{ssbh,xn,xq},new String[]{"yhm","xm","xydm","lxdh","bmmc"});
	 }
	 /**
	  * ��ְҵ��Ԣ����Ա��Ϣ�б�
	  */
	 public static List<HashMap<String, String>>gyGyFdyXxLlist(String xn,String xq){
		 DAO dao = DAO.getInstance();
		 String sql = "select distinct yhm,xm from jhzy_gyfdyb where  xn=? and xq=? ";
		 return dao.getList(sql,new String[]{xn,xq},new String[]{"yhm","xm"});
	 }
	 /**
	  * ��ְҵ��Ԣ����Ա��Ϣ
	  */
	 public static HashMap<String, String>gyGyFdyInfo(String userName,String xn,String xq){
		 DAO dao = DAO.getInstance();
		 String sql = "select distinct yhm,xm,(select szbm from yhb b where a.yhm=b.yhm )xydm,bmmc,lxdh from jhzy_gyfdyb a where yhm=? and xn=? and xq=? ";
		 return dao.getMap(sql,new String[]{userName,xn,xq},new String[]{"yhm","xm","xydm","lxdh","bmmc"});
	 }
	 
	 /**
	  * ����¼�Ƿ��Ѿ�����
	  * @param String tableName
	  * @param String pk
	  * @param String pkValue
	  * @return boolean
	  * */
	 public boolean checkIsExists(String tableName,String pk,String pkValue){
		 DAO dao = DAO.getInstance();
		 String sql = StringUtils.joinStr("select count(*) num from ",tableName,
				 			" where ",pk,"=?") ;
		 String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		 
		 return Integer.parseInt(Base.isNull(num) ? "0" : num)>0 ? true : false;
	 }
	 
	 /**
	 * ����������ѯ�¶����������������Ϣ
	 * @param XsgyglForm model
	 * @param String yhType
	 * @return HashMap<String, String> 
	 * */
	 public HashMap<String, String> selectYdwmqssqbByPk(XsgyglForm model, 
			 String yhType){
		 DAO dao = DAO.getInstance();
		 String disabled = " '' disabled ";
		 
		 if("fdy".equalsIgnoreCase(yhType)){//����Ա
			 disabled = "(case when xysh='δ���' then '' else 'disabled' end) disabled";
		 }else if("xy".equalsIgnoreCase(yhType)){//ѧԺ
			 disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
		 }
		 String sql = StringUtils.joinStr("select pk,", disabled, ",xn,xq,xqmc,", 
		 			  "yf,ssbh,sqsj,sqr,", 
		 		      "bz,fdysh,fdyshyj,",
		 		      "fdyshsj,xysh,xyshyj,xyshsj,xxsh,xxshyj,xxshsj,lddm,",
		 		      "ldmc,cs,qsh from view_gygl_ydwmqssqb ",
		 		      "where xn||xq||yf||ssbh=?");

		 return dao.getMap(sql, new String[]{model.getCbv()[0]}, 
				 			new String[]{"pk", "xn","xq","xqmc","yf","ssbh","sqsj",
				 						 "sqr","bz","fdysh","fdyshyj","fdyshsj",
				 						 "xysh","xyshyj","xyshsj","xxsh","xxshyj",
				 						 "xxshsj","lddm","ldmc","cs","qsh"});
	 }
	 
	 /**
	 * ��ѯ�¶����������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectYdwmqssqxx(XsgyglForm model) throws Exception{
		String tableName = "view_gygl_ydwmqssqb";
		String[] colList = {"pk", "xn", "xqmc", "yf", "ldmc", "qsh", "sqsj", "sqr", "xxsh"};
		
		return CommonQueryDAO.commonQuery(tableName, getWhereSql(model).toString(), 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				colList, "", model);
	 }
	 
	 /**
	 * ��ѯ�¶������������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @param userType
	 * @param userName
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectYdwmqssqshxx(XsgyglForm model,
			 String yhType,String userName) throws Exception{
		String tableName = "view_gygl_ydwmqssqb";
		String[] colList = {"pk", "disabled", "bgcolor","xn", "xqmc", "yf", 
							"ldmc", "qsh", "sqsj", "sqr", "fdysh", "xysh", 
							"xxsh"};		
		Pages pages = model.getPages();
		String disabled = "";
		String bgcolor = "";
		String conSql = "";
		String shjb = model.getShjb();
		
		if("fdy".equalsIgnoreCase(yhType)){//��Ԣ����Ա
			disabled = "(case when xysh='δ���' then '' else 'disabled' end) disabled";
			bgcolor = "(case when fdysh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";		
			conSql = StringUtils.joinStr(" and exists(select 1 from gygl_lzxxb b ",
					                     "where a.lddm=b.lddm and b.yhm='", 
					                     userName, "' and b.xn='", Base.currXn, 
					                     "' and b.xq='", Base.currXq, "')");
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "xqmc", "yf", 
					   "ldmc", "qsh", "sqsj", "sqr", "fdysh", "xysh", 
					   "xxsh"};
		}else if("xy".equalsIgnoreCase(yhType)){//ѧԺ
			disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
			bgcolor = "(case when xysh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";
			conSql = StringUtils.joinStr(" and exists(select 1 from view_xszsxx b ",
					 "where a.lddm=b.lddm and a.qsh=b.qsh and b.xydm='", 
					 model.getXydm(), "')");
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "xqmc", "yf", 
					   "ldmc", "qsh", "sqsj", "sqr", "xysh", "xxsh"};
			if("3".equalsIgnoreCase(shjb)){//3�����
				model.setFdysh("ͨ��");				
			}
		}else{//ѧУ
			disabled = " '' disabled ";
			bgcolor = "(case when xxsh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "xqmc", "yf", 
					   "ldmc", "qsh", "sqsj", "sqr", "xxsh"};
			if(!"1".equalsIgnoreCase(shjb)){//��1�����
				model.setXysh("ͨ��");
			}
		}
		
		String whereSql = getWhereSql(model).toString() + conSql;
		String sql = StringUtils.joinStr("select * from (", "select rownum r,pk,", 
				                         disabled, ",", bgcolor, ",xn,xqmc,yf,",
				                         "ldmc,qsh,sqsj,sqr,fdysh,xysh,xxsh ",
				                         "from ", tableName, " a ", whereSql, 
				                         ") where r>", pages.getStart()+"", 
				                         " and r<=", 
				                         (pages.getStart() + pages.getPageSize())+"");		
		return CommonQueryDAO.commonQuery(tableName, whereSql, 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				colList, sql, model);
	 }
	 
	 /**
	  * �¶��������������������
	  * @param XsgyglForm model
	  * @param String shjg
	  * @param String yhType
	  * @return boolean
	  * */
	 public boolean audiYdwmqssqbBatch(XsgyglForm model, 
			 String shjg, String yhType){
		 DAO dao = DAO.getInstance();
		 boolean result = false;
		 String[] pkArr = model.getCbv();
		 String[] sqlArr = new String[pkArr.length];
		 String shzd = "xxsh";
		 String shsj = GetTime.getSystemTime();
		 
		 if("fdy".equalsIgnoreCase(yhType)){
			 shzd = "fdysh";
		 }else if("xy".equalsIgnoreCase(yhType)){
			 shzd = "xysh";
		 }
		 
		 for(int i=0; i<pkArr.length; i++){
			 sqlArr[i] = "update gygl_ydwmqssqb set " + shzd + "='" + shjg 
			             + "'," + shzd + "sj='" + shsj + "' where xn||xq||yf||ssbh='" + pkArr[i] + "'";
		 }
		 try{
			 dao.runBatch(sqlArr);
			 result = true;
		 }catch(SQLException e){
			 result = false;
		 }
		 return result;
	 }
	 
	 /**
	 * ��ѯ�¶����������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectYdwmqssqxxAll(XsgyglForm model, 
			 String[] outputList) throws Exception{
		String tableName = "view_gygl_ydwmqssqb";
		String whereSql = getWhereSql(model).toString();
		
		return CommonQueryDAO.commonQuery(tableName, whereSql, 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				outputList, "select * from " + tableName + " a " + whereSql, model);
	 }
	 
	 /**
	 * ɾ���¶����������������Ϣ
	 * @param XsgyglForm model
	 * @return boolean
	 * */
	 public boolean deleteYdwmqssqb(XsgyglForm model){
		 DAO dao = DAO.getInstance();
		 boolean result = true;
		 String[] value = model.getCbv();
		 String[] sqlArr = new String[value.length];
		 for(int i=0; i<value.length; i++){
			 sqlArr[i] = "delete from gygl_ydwmqssqb where xn||xq||yf||ssbh ='" + value[i] + "'";
		 }
		 try{
			 dao.runBatch(sqlArr);
		 }catch(Exception e){
			 e.printStackTrace();
			 result = false;
		 }
		 return result;
	 }
	 
	 /**
	 * ����������ѯѧ�����������������Ϣ
	 * @param XsgyglForm model
	 * @param String yhType
	 * @return HashMap<String, String> 
	 * */
	 public HashMap<String, String> selectXqwmqssqbByPk(XsgyglForm model, 
			 String yhType){
		 DAO dao = DAO.getInstance();
		 String disabled = " '' disabled ";
		 
		 if("fdy".equalsIgnoreCase(yhType)){//����Ա
			 disabled = "(case when xysh='δ���' then '' else 'disabled' end) disabled";
		 }else if("xy".equalsIgnoreCase(yhType)){//ѧԺ
			 disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
		 }
		 
		 String sql = StringUtils.joinStr("select pk,",disabled,",xn,xq,xqmc,",
		 		      "ssbh,sqsj,sqr,bz,fdysh,fdyshyj,",
		 		      "fdyshsj,xysh,xyshyj,xyshsj,xxsh,xxshyj,xxshsj,lddm,",
		 		      "ldmc,cs,qsh from view_gygl_xqwmqssqb ",
		 		      "where xn||xq||ssbh=?");

		 return dao.getMap(sql, new String[]{model.getCbv()[0]}, 
				 			new String[]{"pk", "disabled","xn", "xq", "xqmc", 
				                         "ssbh", "sqsj", "sqr", "bz", "fdysh", 
				                         "fdyshyj", "fdyshsj", "xysh", "xyshyj", 
				                         "xyshsj", "xxsh", "xxshyj", "xxshsj", 
				                         "lddm", "ldmc", "cs", "qsh"});
	 }
		 
	 /**
	 * ��ѯѧ�����������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectXqwmqssqxx(XsgyglForm model) 
	     throws Exception{
		String tableName = "view_gygl_xqwmqssqb";
		String[] colList = {"pk", "xn", "xqmc", "ldmc", "qsh", "sqsj", "sqr", 
				            "fdysh", "xxsh"};
		
		return CommonQueryDAO.commonQuery(tableName, getWhereSql(model).toString(), 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				colList, "", model);
	 }
	 
	 /**
	 * ��ѯѧ�����������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectXqwmqssqxxAll(XsgyglForm model, 
			 String[] outputList) throws Exception{
		String tableName = "view_gygl_xqwmqssqb";
		String whereSql = getWhereSql(model).toString();
		
		return CommonQueryDAO.commonQuery(tableName, whereSql, 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				outputList, "select * from " + tableName + " a " + whereSql, model);
	 }
	 
	 /**
	 * ɾ��ѧ�����������������Ϣ
	 * @param XsgyglForm model
	 * @return boolean
	 * */
	 public boolean deleteXqwmqssqb(XsgyglForm model){
		 DAO dao = DAO.getInstance();
		 boolean result = true;
		 String[] value = model.getCbv();
		 String[] sqlArr = new String[value.length];
		 for(int i=0; i<value.length; i++){
			 sqlArr[i] = "delete from gygl_xqwmqssqb where xn||xq||ssbh ='" + value[i] + "'";
		 }
		 try{
			 dao.runBatch(sqlArr);
		 }catch(Exception e){
			 e.printStackTrace();
			 result = false;
		 }
		 return result;
	 }
	 
	 /**
	 * ��ѯѧ�������������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @param String yhType
	 * @param String userName
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectXqwmqssqshxx(XsgyglForm model,
			 String yhType, String userName) throws Exception{
		String tableName = "view_gygl_xqwmqssqb";
		String[] colList = {"pk", "disabled", "bgcolor","xn", "xqmc", "ldmc", 
				            "qsh", "sqsj", "sqr", "xxsh"};		
		Pages pages = model.getPages();
		String disabled = "";
		String bgcolor = "";
		String conSql = "";
		String shjb = model.getShjb();
		
		if("fdy".equalsIgnoreCase(yhType)){//��Ԣ����Ա
			disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
			if("3".equalsIgnoreCase(shjb)){
				disabled = "(case when xysh='δ���' then '' else 'disabled' end) disabled";
			}
			bgcolor = "(case when fdysh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";		
			conSql = StringUtils.joinStr(" and exists(select 1 from gygl_lzxxb b ",
					                     "where a.lddm=b.lddm and b.yhm='", 
					                     userName, "' and b.xn='", Base.currXn, 
					                     "' and b.xq='", Base.currXq, "')");
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "xqmc", 
					               "ldmc", "qsh", "sqsj", "sqr", "fdysh", "xxsh"};	
		}else if("xy".equalsIgnoreCase(yhType)){//ѧԺ
			disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
			bgcolor = "(case when xysh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";
			conSql = StringUtils.joinStr(" and exists(select 1 from view_xszsxx b ",
					 "where a.lddm=b.lddm and a.qsh=b.qsh and b.xydm='", 
					 model.getXydm(), "')");
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "xqmc", 
					               "ldmc", "qsh", "sqsj", "sqr", "xysh", "xxsh"};	
			if("3".equalsIgnoreCase(shjb)){//3�����
				model.setFdysh("ͨ��");
			}
		}else{//ѧУ
			disabled = " '' disabled";
			bgcolor = "(case when xxsh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";
			if("2".equalsIgnoreCase(shjb)){//��1�����
				model.setFdysh("ͨ��");
			}
		}
		
		String whereSql = getWhereSql(model).toString() + conSql;
		String sql = StringUtils.joinStr("select * from (", "select rownum r,pk,", 
				                         disabled, ",", bgcolor, ",xn,xqmc,",
				                         "ldmc,qsh,sqsj,sqr,fdysh,xysh,xxsh ",
				                         "from ", tableName, " a ", whereSql, 
				                         ") where r>", pages.getStart()+"", 
				                         " and r<=", 
				                         (pages.getStart() + pages.getPageSize())+"");		
		return CommonQueryDAO.commonQuery(tableName, whereSql, 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				colList, sql, model);
	 }
	 
	 /**
	  * ѧ���������������������
	  * @param XsgyglForm model
	  * @param String shjg
	  * @param String yhType
	  * @return boolean
	  * */
	 public boolean audiXqwmqssqbBatch(XsgyglForm model, 
			 String shjg, String yhType){
		 DAO dao = DAO.getInstance();
		 boolean result = false;
		 String[] pkArr = model.getCbv();
		 String[] sqlArr = new String[pkArr.length];
		 String shzd = "xxsh";
		 String shsj = GetTime.getSystemTime();
		 
		 if("fdy".equalsIgnoreCase(yhType)){
			 shzd = "fdysh";
		 }else if("xy".equalsIgnoreCase(yhType)){
			 shzd = "xysh";
		 }
		 
		 for(int i=0; i<pkArr.length; i++){
			 sqlArr[i] = "update gygl_xqwmqssqb set " + shzd + "='" + shjg 
			             + "'," + shzd + "sj='" + shsj + "' where xn||xq||ssbh='" + pkArr[i] + "'";
		 }
		 try{
			 dao.runBatch(sqlArr);
			 result = true;
		 }catch(SQLException e){
			 result = false;
		 }
		 return result;
	 }
	 
	 /**
	 * ����������ѯѧ�����������������Ϣ
	 * @param XsgyglForm model
	 * @param String yhType
	 * @return HashMap<String, String> 
	 * */
	 public HashMap<String, String> selectXnwmqssqbByPk(XsgyglForm model,
			 String yhType){
		 DAO dao = DAO.getInstance();
		 String disabled = " '' disabled ";
		 
		 if("fdy".equalsIgnoreCase(yhType)){//����Ա
			 disabled = "(case when xysh='δ���' then '' else 'disabled' end) disabled";
		 }else if("xy".equalsIgnoreCase(yhType)){//ѧԺ
			 disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
		 }
		 
		 String sql = StringUtils.joinStr("select pk,",disabled,",xn,ssbh,sqsj,",
		 		      "sqr,bz,fdysh,fdyshyj,fdyshsj,xysh,xyshyj,xyshsj,xxsh,",
		 		      "xxshyj,xxshsj,lddm,ldmc,cs,qsh from view_gygl_xnwmqssqb ",
		 		      "where xn||ssbh=?");

		 return dao.getMap(sql, new String[]{model.getCbv()[0]}, 
				 			new String[]{"pk", "disabled", "xn", "ssbh", 
				 	                     "sqsj", "sqr", "bz", "fdysh", "fdyshyj", 
				 	                     "fdyshsj", "xysh", "xyshyj", "xyshsj", 
				 	                     "xxsh", "xxshyj", "xxshsj", "lddm", 
				 	                     "ldmc", "cs", "qsh"});
	 }
	 
	 /**
	 * ��ѯѧ�����������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectXnwmqssqxx(XsgyglForm model) 
	     throws Exception{
		String tableName = "view_gygl_xnwmqssqb";
		String[] colList = {"pk", "xn", "ldmc", "qsh", "sqsj", "sqr", 
				            "fdysh", "xysh", "xxsh"};
		
		return CommonQueryDAO.commonQuery(tableName, getWhereSql(model).toString(), 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				colList, "", model);
	 }
	 
	 /**
	 * ��ѯѧ�����������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectXnwmqssqxxAll(XsgyglForm model, 
			 String[] outputList) throws Exception{
		String tableName = "view_gygl_xnwmqssqb";
		String whereSql = getWhereSql(model).toString();
		
		return CommonQueryDAO.commonQuery(tableName, whereSql, 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				outputList, "select * from " + tableName + " a " + whereSql, model);
	 }
	 
	 /**
	 * ɾ��ѧ�����������������Ϣ
	 * @param XsgyglForm model
	 * @return boolean
	 * */
	 public boolean deleteXnwmqssqb(XsgyglForm model){
		 DAO dao = DAO.getInstance();
		 boolean result = true;
		 String[] value = model.getCbv();
		 String[] sqlArr = new String[value.length];
		 for(int i=0; i<value.length; i++){
			 sqlArr[i] = "delete from gygl_xnwmqssqb where xn||ssbh ='" + value[i] + "'";
		 }
		 try{
			 dao.runBatch(sqlArr);
		 }catch(Exception e){
			 e.printStackTrace();
			 result = false;
		 }
		 return result;
	 }
	 
	 /**
	 * ��ѯѧ�������������������Ϣ������ҳ��
	 * @param XsgyglForm model
	 * @param String yhType
	 * @param String userName
	 * @return List<String[]>
	 * @throws Exception
	 * */
	 public ArrayList<String[]> selectXnwmqssqshxx(XsgyglForm model,
			 String yhType, String userName) throws Exception{
		String tableName = "view_gygl_xnwmqssqb";
		String[] colList = {"pk", "disabled", "bgcolor","xn", "ldmc", 
				            "qsh", "sqsj", "sqr", "xxsh"};		
		Pages pages = model.getPages();
		String disabled = "";
		String bgcolor = "";
		String conSql = "";
		String shjb = model.getShjb();
		
		if("fdy".equalsIgnoreCase(yhType)){//��Ԣ����Ա
			disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
			if("3".equalsIgnoreCase(shjb)){
				disabled = "(case when xysh='δ���' then '' else 'disabled' end) disabled";
			}
			bgcolor = "(case when fdysh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";		
			conSql = StringUtils.joinStr(" and exists(select 1 from gygl_lzxxb b ",
					                     "where a.lddm=b.lddm and b.yhm='", 
					                     userName, "' and b.xn='", Base.currXn, 
					                     "' and b.xq='", Base.currXq, "')");
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "ldmc", 
		            "qsh", "sqsj", "sqr", "fdysh", "xxsh"};
		}else if("xy".equalsIgnoreCase(yhType)){//ѧԺ
			disabled = "(case when xxsh='δ���' then '' else 'disabled' end) disabled";
			bgcolor = "(case when xysh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";
			conSql = StringUtils.joinStr(" and exists(select 1 from view_xszsxx b ",
					 "where a.lddm=b.lddm and a.qsh=b.qsh and b.xydm='", 
					 model.getXydm(), "')");
			colList = new String[]{"pk", "disabled", "bgcolor","xn", "ldmc", 
		            "qsh", "sqsj", "sqr", "xysh", "xxsh"};
			if("3".equalsIgnoreCase(shjb)){//3�����
				model.setFdysh("ͨ��");
			}
		}else{//ѧУ
			disabled = " '' disabled";
			bgcolor = "(case when xxsh='δ���' then '#CCCCCC' else '#FFFFFF' end) bgcolor";			
			if("2".equalsIgnoreCase(shjb)){//2�����(����Ա--->ѧУ)
				model.setFdysh("ͨ��");
			}
		}
		
		String whereSql = getWhereSql(model).toString() + conSql;
		String sql = StringUtils.joinStr("select * from (", "select rownum r,pk,", 
				                         disabled, ",", bgcolor, ",xn,",
				                         "ldmc,qsh,sqsj,sqr,fdysh,xysh,xxsh ",
				                         "from ", tableName, " a ", whereSql, 
				                         ") where r>", pages.getStart()+"", 
				                         " and r<=", 
				                         (pages.getStart() + pages.getPageSize())+"");		
		return CommonQueryDAO.commonQuery(tableName, whereSql, 
				values != null ? values.toArray(new String[0]) : new String[]{}, 
				colList, sql, model);
	 }
	 
	 /**
	  * ѧ���������������������
	  * @param XsgyglForm model
	  * @param String shjg
	  * @param String yhType
	  * @return boolean
	  * */
	 public boolean audiXnwmqssqbBatch(XsgyglForm model, 
			 String shjg, String yhType){
		 DAO dao = DAO.getInstance();
		 boolean result = false;
		 String[] pkArr = model.getCbv();
		 String[] sqlArr = new String[pkArr.length];
		 String shzd = "xxsh";
		 String shsj = GetTime.getSystemTime();
		 
		 if("fdy".equalsIgnoreCase(yhType)){
			 shzd = "fdysh";
		 }else if("xy".equalsIgnoreCase(yhType)){
			 shzd = "xysh";
		 }
		 
		 for(int i=0; i<pkArr.length; i++){
			 sqlArr[i] = "update gygl_xnwmqssqb set " + shzd + "='" + shjg 
			             + "'," + shzd + "sj='" + shsj + "' where xn||ssbh='" + pkArr[i] + "'";
		 }
		 try{
			 dao.runBatch(sqlArr);
			 result = true;
		 }catch(SQLException e){
			 result = false;
		 }
		 return result;
	 }
	 
	 /**
	 * �ж����ͨ���������Ƿ񳬹����õı���
	 * @param XsgyglForm model
	 * @return String
	 * */
	 public String checkWmqsbl(XsgyglForm model){
		 DAO dao = DAO.getInstance();
		 String message = "";
		 
		 String sql = "select nvl(wmqsbz,0) wmqsbz from gygl_csszb where xn=?";
		 //�������ұ���
		 String wmqsbz = dao.getOneRs(sql, new String[]{model.getXn()}, "wmqsbz");
		 //�����������ұ���������������ͨ����������
		 sql = "select round(count(*)*?/100) bzwmqss from view_ssxx";
		 //��׼����������
		 String bzwmqss = dao.getOneRs(sql, new String[]{wmqsbz}, "bzwmqss");
		 //��ȡѧ���Ѿ����ͨ��������
		 sql = "select count(*) num from gygl_xnwmqssqb a where xn=? and xysh='ͨ��'";
		 String num = dao.getOneRs(sql, new String[]{Base.currXn}, "num");
		 //Ԥ�������
		 int yshrs = model.getCbv().length; 
		 if(Integer.parseInt(Base.isNull(num) ? "0" : num) + yshrs > Integer.parseInt(bzwmqss)){
			 message = "�����ͨ�����������Ѿ����������õı�������������ˣ�";
		 }
		 
		 return message;
	 }
}
