package xgxt.yxgl;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import common.Globals;

import xgxt.DAO.DAO;
//import xgxt.daoActionLogic.StandardOperation;
//import xgxt.utils.CustomException;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.GetTime;
//import xgxt.utils.String.StringUtils;

public class YxglDAO  {
//	enum Mode{
//		UPDATE,DELETE,INSERT
//	}
	public static final int INSERT =  0;
	public static final int UPDATE =  1;
	public static final int DELETE =  2;
	DAO dao = DAO.getInstance();

//	private static YxglDAO yxglDao = null;
//	
//	public static YxglDAO getInstance(){
//		if(yxglDao == null){
//			yxglDao = new YxglDAO();
//		}
//		return yxglDao;
//	}
	
	
	public List<HashMap<String, String>>  getNewXyList(){
		String sql = "select xydm,xymc from newxydmb order by xydm";
		return dao.getList(sql,new String[]{},new String[]{"xydm","xymc"});
	}

	public List<HashMap<String, String>>  getNewZyList(){
		String sql = "select zydm,zymc from newzydmb order by zydm";
		return dao.getList(sql,new String[]{},new String[]{"zydm","zymc"});
	} 

	public List<HashMap<String, String>>  getNewZyList(String xydm){
		String sql = "select zydm,zymc from newzydmb where 1=1 ";
		if(xydm != null && !("".equalsIgnoreCase(xydm.trim()))){
			sql += " and ssxydm='"+xydm+"' ";
		}
		sql += " order by zydm";
		return dao.getList(sql,new String[]{},new String[]{"zydm","zymc"});
	}

	public List<HashMap<String, String>>  getNewBjList(){
		String sql = "select bjdm,bjmc from newbjdmb order by bjdm";
		return dao.getList(sql, new String[]{}, new String[]{"bjdm","bjmc"});
	}

	/**
	 * 
	 * @param xydm
	 * @param zydm
	 * @return
	 */
	public List<HashMap<String, String>>  getNewBjList(String xydm,String zydm){
		StringBuffer sql = new StringBuffer("select bjdm,bjmc from newbjdmb where 1=1 ");
		if((xydm!=null) && !("".equalsIgnoreCase(xydm.trim()))){
			sql.append(" and ssxydm='");
			sql.append(xydm);
			sql.append("' ");
		}
		if((zydm != null) && !("".equalsIgnoreCase(zydm.trim()))){
			sql.append(" and sszydm='");
			sql.append(zydm);
			sql.append("' ");
		}
		sql.append(" order by bjdm");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"bjdm","bjmc"});
	}
	
	/**
	 * @param ksh
	 * @param cols
	 * @return ���ѧԺ����ѧ����Ϣ
	 */
	
	public String[] getNewStuGlinfo(String ksh,String[] cols){
		String sql = "select ksh,xh,xm,xydm,xb,sfzh,zydm,bjdm,sfdm,csrq,jg,nj from newstusinfo where ksh=?";
		return dao.getOneRs(sql, new String[]{ksh},cols); 	  
	}
	
	public String[] getNewStuInfo(String ksh,String[] cols){
		String sql = "select ksh,xh,xm,xydm,zydm,bjdm,sfjy,sflsj,sfmc,ldh,qsh,xybd,stbd,yybd,ssbd,ysqsf,ysxf,sjqsf,sjxf,sffs,sftj,sfzsym,sfzh,cwh from view_newstureportinfo where ksh=?";
		return dao.getOneRs(sql, new String[]{ksh},cols); 	  
	}
	
	public String[] getNewStuInfoByXydm(String ksh,String xydm,String[] cols){
		String sql = "select ksh,xh,xm,xydm,zydm,bjdm,sfjy,sflsj,sfmc,ldh,qsh,xybd,stbd,yybd,ssbd,ysqsf,ysxf,sjqsf,sjxf,sffs,sftj,sfzsym,sfzh,cwh from view_newstureportinfo where ksh=? and xydm = ?";
		return dao.getOneRs(sql, new String[]{ksh,xydm},cols); 	  
	}
	
	public String[] getNewStulstdInfo(String ksh,String[] cols){
		String sql = "select ksh,xh,cjrq,jtrs,pjsr,kndj,bz,hyjqsf,hyjxf from view_newstulstdinfo where ksh=?";
		return dao.getOneRs(sql, new String[]{ksh},cols); 	  
	}
	
	public HashMap<String, String> getNewStuDetails(String ksh){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.ksh,a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj,a.sfjy,b.ldh,b.qsh from view_newstuinfo a,view_newstudormitoryinfo b where a.ksh=b.ksh and a.ksh=?";
		String[] cols = {"ksh","xh","xm","xymc","zymc","bjmc","nj","sfjy","ldh","qsh"};
		String[] vals = dao.getOneRs(sql, new String[]{ksh}, cols);
		for(int i=0;i<cols.length;i++){
			map.put(cols[i], (vals != null)?vals[i]:"");
		}
		return map;
	}
	
	/***
	 * Ϊ���Ϲ�ҵ��ѧ��������ӡ����������
	 * @param ksh
	 * @return
	 */
	public HashMap<String, String> getNewStuDetails_Print(String ksh){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select ksh,xh,xb,xm,xymc,zymc,bjmc,nj,sfjy,ldh,qsh from view_newstureportinfo where ksh=?";
		String[] cols = {"ksh","xh","xb","xm","xymc","zymc","bjmc","nj","sfjy","ldh","qsh"};
		String[] vals = dao.getOneRs(sql, new String[]{ksh}, cols);
		for(int i=0;i<cols.length;i++){
			map.put(cols[i], (vals != null)?vals[i]:"");
		}
		return map;
	}
	
	//ɾ��ѧԺ�������е���Ӧѧ������ 
	public boolean doDelete(String ksh){
		String sql = "delete from xybdzcb where ksh=?";
		boolean rs = false;
		try{
			rs = dao.runUpdate(sql, new String[]{ksh});
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	//УҽԺ�趨����ѧ����Ϣ

	/**
	 * @param result  ֻ��������ֵ:yes/no
	 * @param doType
	 * @param sfdm
	 * @return
	 */
	public boolean setMedicineArea(String result,String doType,String [] sfdm){
		StringBuffer sb = new StringBuffer("update newstusinfo set sfjy='yes' where ");
		sb.append("sfdm=? ");
		for(int i =1;i<sfdm.length;i++){
			sb.append("or sfdm=? ");		
		}
		try{
			return dao.runUpdate(sb.toString(), sfdm);
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return  �����Ѿ�����Ҫ���м��ߵ�ʡ��
	 */
	public String[] getMedicineArea(){
		String sql = "select sfmc from sfdmdzb a where exists(select sfmc from newstusinfo b where a.sfdm=b.sfdm and sfjy='yes')";
		try {
			return dao.getArray(sql, new String[]{}, "sfmc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return  ����ʡ���б� 
	 */
	public List<HashMap<String, String>> getProvinceList(){
		String sql = "select sfdm,sfmc from sfdmdzb order by sfdm";
		return dao.getList(sql, new String[]{}, new String[]{"sfdm","sfmc"});
	}
	/**
	 * @param ksh
	 * @param cols
	 * @return ���УҽԺԺ����ѧ����Ϣ
	 */
	public String[] getNewStuHospitalInfo(String ksh,String[] cols){
		String sql = "select a.ksh,a.xh,a.xm,a.xydm,a.zydm,a.bjdm,b.sfbd,b.sftj,b.sfzsym from newstusinfo a left join xyybdzcb b on a.ksh=b.ksh where  a.ksh=?";
		return dao.getOneRs(sql, new String[]{ksh},cols); 	  
	}
	/**
	 * @param ksh
	 * @param cols
	 * @return ���ʳ�ñ���ѧ����Ϣ
	 */
	public String[] getNewStuEateryInfo(String ksh,String[] cols){
		String sql = "select a.ksh,a.xh,a.xm,a.xydm,a.zydm,a.bjdm from newstusinfo a left join stbdzcb b on a.ksh=b.ksh where  a.ksh=?";
		return dao.getOneRs(sql, new String[]{ksh},cols); 	  
	}
	/**
	 * @param ksh
	 * @param cols
	 * @return ������ᱨ��ѧ����Ϣ
	 */
	public String[] getNewStuDomInfo(String ksh,String[] cols){
		String sql = "select a.ksh,a.xh,a.xm,a.xydm,a.zydm,a.bjdm from newstusinfo a left join ssbdzcb b on a.ksh=b.ksh where  a.ksh=?";
		return dao.getOneRs(sql, new String[]{ksh},cols); 	  
	}
	
	//УҽԺ����ɾ��
	public boolean doXyyBdDelete(String ksh){
		String sql = "delete from xyybdzcb where ksh=?";
		boolean rs = false;
		try{
			rs = dao.runUpdate(sql, new String[]{ksh});
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	//	ʳ�ñ���ɾ��
	public boolean doStBdDelete(String ksh){
		String sql = "delete from stbdzcb where ksh=?";
		boolean rs = false;
		try{
			rs = dao.runUpdate(sql, new String[]{ksh});
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	//���ᱨ��ɾ��
	public boolean doSsBdDelete(String ksh){
		String sql = "delete from ssbdzcb where ksh=?";
		boolean rs = false;
		try{
			rs = dao.runUpdate(sql, new String[]{ksh});
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	public Object getNewSfList() {
		String sql = "select sfdm,sfmc from sfdmdzb order by sfdm";
		return dao.getList(sql,new String[]{},new String[]{"sfdm","sfmc"});
	}

	public Object getSffsList() {
		String[] fsdm = new String[] { "1", "2","3" };
		String[] fsmc = new String[] { "���", "�ֽ�","ˢ��" };
		return dao.arrayToList(fsdm, fsmc);
	}
	
	public Object getKndjList() {
		String[] Kndjdm = new String[] { "a", "b","c","d","e" };
		String[] Kndjmc = new String[] { "a", "b","c","d","e" };
		return dao.arrayToList(Kndjdm, Kndjmc);
	}
	
	public Object getBgList() {
		String[] bgdm = new String[] { "��", "��" };
		String[] bgmc = new String[] { "����", "δ����" };
		return dao.arrayToList(bgdm, bgmc);
	}
	
	public Object getSfzsymList() {
		String[] tmpdm = new String[] { "on", "off" };
		String[] tmpmc = new String[] { "��ע������", "δע������" };
		return dao.arrayToList(tmpdm, tmpmc);
	}
	
	public Object getSfblLstdList(){
		String[] tmpdm = new String[] { "on", "off" };
		String[] tmpmc = new String[] { "�Ѱ���", "δ����" };
		return dao.arrayToList(tmpdm, tmpmc);
	}
	
	public Object getSftjList() {
		String[] tmpdm = new String[] { "on", "off" };
		String[] tmpmc = new String[] { "�����", "δ���" };
		return dao.arrayToList(tmpdm, tmpmc);
	}

	public Object getBgzlList() {
		String[] bgzldm = new String[] { "sfbd","xybd", "yybd","stbd","ssbd" };
		String[] bgzlmc = new String[] { "��У����",Base.YXPZXY_KEY+"����", "ҽԺ����","ʳ�ñ���","���ᱨ��"};
		return dao.arrayToList(bgzldm, bgzlmc);
	}
	
	public Object getBgzlList_HENAN() { //���Ϲ�ҵ��ѧ
		String[] bgzldm = new String[] { "sfbd","xybd", "yybd","stbd","ssbd" };
		String[] bgzlmc = new String[] { "��У����",Base.YXPZXY_KEY+"����", "ҽԺ����","��������","���ᱨ��"};
		return dao.arrayToList(bgzldm, bgzlmc);
	}
	
	public Object getSfList() {
		String sql = "select sfdm,sfmc from sfdmdzb ";
		return dao.getList(sql,new String[]{},new String[]{"sfdm","sfmc"});
	}

	public HashMap<String, String> getNewStuLstdReport(String ksh) {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.lsh,a.ksh,a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.cjrq,a.hyjqsf,a.hyjxf,b.lsxm,b.lxdh,b.bgdd from view_newstulstdexpinfo a left join xyfzjjknlsb b on a.xydm=b.xydm where a.ksh=? order by ksh";
		String[] cols = {"lsh","ksh","xh","xm","xymc","zymc","bjmc","cjrq","hyjqsf","hyjxf","lsxm","lxdh","bgdd"};
		String[] vals = dao.getOneRs(sql, new String[]{ksh}, cols);
		for(int i=0;i<cols.length;i++){
			map.put(cols[i], (vals != null)?vals[i]:"");
		}
		map.put("dqrq", GetTime.getNowTime());
		return map;
	}

	public void insertlstd(String[] values)  {
		String sql  = "insert into lstdxxb(lsh,ksh,xh,cjrq,jtrs,pjsr,kndj,bz,hyjqsf,hyjxf)"
			+ "values (S_YXGLLSTD_ID.nextval,?,?,?,?,?,?,?,?,?)";
		try {
			dao.runUpdate(sql, values);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	public String getNextYear(){
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyy') nowYear from dual";
		String nowYear = dao.getOneRs(sql, new String[]{}, new String[]{"nowYear"})[0];
		Integer Year = Integer.parseInt(nowYear);
		String nextYear =((Integer)(Year+1)).toString();
		return nextYear;
	}
/*	
	//���Ϲ�ҵ��ѧ  ��������������������Ϣ
	public List<HashMap<String,String>> getNewSsList(){
		String sql = "select distinct ldh||'-'||qsh ssbh from  view_newstureportinfo where ldh||qsh is not null order by ssbh";
		List<HashMap<String,String>> ssList = dao.getList(sql, new String[] {},new String[] {"ssbh"});
		return ssList;
	}
*/	
	//�������ҷ���ѧ��������δ����������
	private String[] getNewStuSsbdList(String ssbh){
		String[] outputValue = new String[] {"yescount","nocount","cws"};
 		StringBuffer bf = new StringBuffer();
 		bf.append("select nvl(b.yescount,'0') yescount,"); 
 		bf.append("	to_char(to_number(nvl(a.cws,'0'))-to_number(nvl(b.yescount,'0'))) nocount,"); 
 		bf.append(" a.cws FROM");
 		bf.append(" (SELECT cws,ssbh  FROM ssxxb) a");
 		bf.append(" left join");
 		bf.append(" (SELECT lddm||'-'||qsh ssbh,COUNT(*) yescount FROM view_newstureportinfo");
 		bf.append(" WHERE ssbd = '��' and lddm||qsh is not null group by lddm||'-'||qsh) b");
 		bf.append(" on a.ssbh = b.ssbh and a.ssbh=?");
		return dao.getOneRs(bf.toString(),new String[] {ssbh}, outputValue);
	}
	//����ѧ���ж��Ƿ�ѧԺ������ ���Ϲ�ҵ��ѧ
	public boolean isXybd(String ksh) {
		System.out.println(ksh);	
		String sql = "select xybd from view_newstureportinfo where ksh=?";
		String[] xybd = dao.getOneRs(sql, new String[] {ksh}, new String[] {"xybd"});
		if(xybd != null){
			return xybd[0].equalsIgnoreCase("��") ? true : false;
		}
		return false;
	}
	
	/**
	 * <font color='red'>������౨����Ϣ ���Ϲ�ҵ��ѧ</font>
	 * @param ksh <font color='red'></font>
	 * @param xh
	 * @param doType
	 * @param array 
	 * @return
	 */
	public boolean xsbdOneByOne(String ksh,String xh,String doType,String array){
		try{
			DAO dao = DAO.getInstance();
			String tempSql = "";
			String condi = " where ksh||xh='" + ksh + xh + "'"; 
			String tableName = "";
			String bdlx = "";
			String[] sArray = array.split("-");
			String[] tablecol = null;
			String[] values = null;
			boolean update = false;
			if(doType.equalsIgnoreCase("xy")){ //ѧԺ����
				tableName = "xybdzcb";
				bdlx = "xybd";
				if(array.length() > 0){
					tempSql = ",sflsj='" + array + "'";
				}	
			}else if(doType.equalsIgnoreCase("yy")){ //ҽԺ����
				tablecol = new String[]{"ksh","xh","Sfzsym","sftj","sfbd"};//ҽԺ�������ֶ�
				values = new String[]{ksh,xh,sArray[0],sArray[1],"��"};
				tableName = "xyybdzcb"; 
//				bdlx = "sfbd";  //УҽԺ�����ֶ�[��/��]
//				if(sArray[0] != "1"){
//					tempSql = ",sfzsym='" + sArray[0] + "'";
//				}
//				if(sArray[1] != "1"){
//					tempSql += ",sftj='" + sArray[1] + "'";
//				}
			}else if(doType.equalsIgnoreCase("st")){ //ʳ�ñ���
				tableName = "stbdzcb"; 
				tablecol = new String[]{"ksh","xh","stbd"};
				values = new String[]{ksh,xh,"��"};
			}else if(doType.equalsIgnoreCase("ss")){
				tableName = "ssbdzcb"; //���ᱨ�� 
				tablecol = new String[]{"ksh","xh","ssbd"};
				values = new String[]{ksh,xh,"��"};
			}	
			
			//System.out.println(sql);
			if(doType.equalsIgnoreCase("xy")){
				String sql = "update " + tableName + " set "	+ bdlx + " ='��' " + tempSql + condi;
				//System.out.println(sql); 
				update = dao.runUpdate(sql, new String[]{});
			}else{
				String delSql = "delete from "+tableName+" where ksh=?";
				boolean del = dao.runUpdate(delSql, new String []{ksh});
				if(del){
					update = StandardOperation.insertNoLog(tableName,tablecol,values);
				}
			}
			return update;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ���Ϲ�ҵ��ѧ ��������ʱ�����
	 * @param fromDate  <font color='blue'>��ʼʱ��</font> 
	 * @param toDate <font color='blue'>����ʱ��</font> 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveInitTime(String fromDate,String toDate) throws Exception{
		String sql = "select count(*) num from henan_inittimeb";
		String count = dao.getOneRs(sql, new String[]{}, new String[]{"num"})[0];
		if(count.equalsIgnoreCase("0")){
			sql = "insert into henan_inittimeb values(?,?)";
			return dao.runUpdate(sql, new String[]{fromDate,toDate});
		}else{
			sql = "update henan_inittimeb set FROMDATE=?,TODATE=?";
			return dao.runUpdate(sql, new String[]{fromDate,toDate});
		}
	}
	
	/**
	 * �õ����õ�ʱ��
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> getInitTime(){
		List<HashMap<String,String>> myList = null;
		HashMap<String,String> myHMap       = new HashMap<String,String>();
		String sql = "select fromDate,toDate from henan_inittimeb";
		myList     = dao.getList(sql, new String[]{}, new String[]{"fromDate","toDate"});
		if(myList!=null&&myList.size()!=0){
			myHMap = myList.get(0);
		}else{
			myHMap.put("fromDate","1900-00-00") ;
			myHMap.put("toDate","1900-00-00") ;
		}
		return myHMap; 
	}
	
	/**
	 * �ж��Ƿ���ӭ�µ�ʱ����
	 * @param xxdm
	 * @return String
	 * @throws Exception
	 */
	public String isOkTime(String xxdm) throws Exception{
		String tag = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)){ //���Ϲ�ҵ��ѧ
			String sql = "select * from henan_inittimeb where "
						+ "fromdate <= to_char(sysdate,'yyyy-mm-dd')"
						+ " and todate >= to_char(sysdate,'yyyy-mm-dd')";
			tag =  dao.returntag(sql, new String[] {});
			return (tag.equalsIgnoreCase("notempty") ? "ok" : "no");
		}
		return "ok";
	}
	
	/**
	 * �������е�¥���ż���
	 * @return
	 */
	public List<HashMap<String,String>> getLdmcList(){
		String sql = "select distinct ldh,lddm from view_newstureportinfo where ldh is not null and lddm is not null";
		return  dao.getList(sql, new String[]{},new String[]{"lddm","ldh"});
	}
	
	/**
	 * <font color='red'>����¥�����뷵����Ӧ�����Ҽ���</font>
	 * @param lddm <font color='blue'>¥������</font>
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getQsListByLddm(String lddm){
		//String sql = "select qsh qsdm,qsh qsmc from view_newstureportinfo where lddm = ?";
		String sql = "select '' dm,'--��ѡ��--' mc from view_newstureportinfo where rownum=1 union all select * from (select distinct qsh dm,qsh mc from view_newstureportinfo where lddm = ? order by dm) order by dm nulls first";
		//return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
		List<HashMap<String,String>> qshList = dao.getList(sql, new String[]{lddm},new String[]{"dm","mc"});
		return qshList;
	}
	
	/**
	 * ֻ��¥����������
	 * @param sql
	 * @param yxform
	 * @return 
	 */
	public Vector dealHENAN_Lddm(YxglActionForm yxform){
		String lddm = yxform.getLddm(); //�õ�¥������
		StringBuffer sb = new StringBuffer();
		Vector<Object> rs = new Vector<Object>(); 
		String[] outputValues = new String[]{"qsh","bdzl","yescount","nocount","cws"};
//		sb.append("select qsh,'��У����' bdzl,cws, to_char(to_number(cws) - to_number(nvl(sum,'0'))) nocount,"); 
//		sb.append(" nvl(sum,'0') yescount from ( select a.cws,b.sum,a.qsh from ");
//		sb.append(" (select a.qsh,b.cws from(select distinct qsh from view_newstureportinfo where lddm = ?) a");
//		sb.append(" left join (select qsh,cws from ssxxb where lddm = ?) b on a.qsh = b.qsh) a"); 
//		sb.append(" left join");
//		sb.append(" (select count(*) sum,qsh from view_newstureportinfo where lddm=? and ssbd = '��' group by qsh) b");
//		sb.append(" on a.qsh = b.qsh )");
		sb.append("select qsh,'��У����' bdzl,cws, to_char(to_number(cws) - to_number(nvl(sum,'0'))) nocount,"); 
		sb.append(" nvl(sum,'0') yescount from ( select a.cws,b.sum,a.qsh from ");
		sb.append(" (select a.qsh,b.cws from(select distinct qsh from view_newstureportinfo where lddm = ?) a");
		sb.append(" left join (select qsh,cws from ssxxb where lddm = ?) b on a.qsh = b.qsh) a"); 
		sb.append(" left join");
		sb.append(" (select count(*) sum,qsh from view_newstureportinfo where lddm=? and ssbd = '��' group by qsh) b");
		sb.append(" on a.qsh = b.qsh )");
		rs.addAll(dao.rsToVator(sb.toString(), new String[] {lddm,lddm,lddm},outputValues));
		return rs;
	}
	
	 /**
	  * ��ѯ����ĳ�����ҵı������
	  * @param yxform
	  * @return
	  */
	public HashMap<String,String> dealHENAN_Qsh(YxglActionForm yxform){
		String qsh = yxform.getQsh(); //�õ����Һ�
		String lddm = yxform.getLddm(); //�õ�¥������
		
		HashMap<String, String> map = new HashMap<String, String>();
		String[] newStuSsbdList = getNewStuSsbdList(dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh"));
		map.put("moduleName", "ȫУ����");
		map.put("yesCount", newStuSsbdList[0]);
		map.put("noCount", newStuSsbdList[1]); 
		map.put("maxSum", newStuSsbdList[2]);
		map.put("chickModule", "���ᱨ��");
		return map;
	}
	
	/**
	 * ���ݷ��ص������б�����ܵ�¥��������
	 * @param henanSsList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String,String> getSsbdNumByLddm(Vector henanSsList){
		HashMap<String,String> result = new HashMap<String,String>();
		result.put("moduleName", "ȫУ����");
		result.put("chickModule", "���ᱨ��");
		int yesCount = 0;
		int noCount  = 0;
		int maxSum   = 0;
		for(String[] s : (Vector<String[]>)henanSsList){
			yesCount += Integer.parseInt(s[2]);
			noCount  += Integer.parseInt(s[3]);
			maxSum   += Integer.parseInt(s[4]);
		}
		result.put("yesCount", String.valueOf(yesCount));
		result.put("noCount",  String.valueOf(noCount)); 
		result.put("maxSum",   String.valueOf(maxSum));
		return result;
	}
	
	/**
	 * <font color='blue'>������ѧ������Ϣ  </font>
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return ����ɹ�����true ���򷵻�false
	 * @throws Exception
	 */
	public boolean saveNewStuInfo(String tableName,String[] columns,String[] values) throws Exception{
		return dao.runUpdate(getInsertSql(tableName,columns),values); //��newstusinfo
	}
	
	/**
	 * <font color='blue'>������ת����String���ͣ���","�ָ�<br>���ҶԲ�������String��ʼ��</font>
	 * @param columns
	 * @return 
	 */
	private String arrayToStrForInsert(String[] columns){
		StringBuffer sb = new StringBuffer("(");
		StringBuffer noteSb = new StringBuffer("(");
		int length = columns.length-1;
		for(int i=0; i<columns.length; i++){
			sb.append(columns[i]);
			sb.append((i==length) ? "": ",");
			//noteSb
			noteSb.append((i==length) ? "?": "?,");
		}
		sb.append(")");
		noteSb.append(")");
		StringBuffer resultSql = new StringBuffer();
		resultSql.append(sb.toString());
		resultSql.append(" values ");
		resultSql.append(noteSb.toString());
		return resultSql.toString();
	}
	
	/**
	 * @param tableName
	 * @param columns
	 * @return  ����insert�﷨��sql���
	 */
	private String getInsertSql(String tableName,String[] columns){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(tableName);
		sb.append(arrayToStrForInsert(columns));
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
//	/**
//	 * 
//	 * @param tableName
//	 * @param columns
//	 * @return ����update�﷨��sql���
//	 */
//	private String getUpdateSql(String tableName,String[] columns){
//		StringBuffer sb = new StringBuffer();
//		sb.append("update ");
//		sb.append(tableName);
//		sb.append(arrayToStrForInsert(columns));
//		//System.out.println(sb.toString());
//		return sb.toString();
//	}
	
	/**
	 * �жϸÿ��������Ƿ����(DWR)
	 * @param ksh
	 * @return ���ڷ���ture,���򷵻�false
	 */
	public boolean isKshExists(String ksh){
		String sql ="select ksh from newstusinfo where ksh=?";
		String[] rs = dao.getOneRs(sql, new String[]{ksh}, new String[]{"ksh"});
		return (rs == null ? false : true);
	}
	
	/**
	 * �жϸ�ѧ���Ƿ����(DWR)
	 * @param xh
	 * @return
	 */
	public boolean isXhExists(String xh){
		String sql ="select xh from newstusinfo where xh=? and xh <> '����'";
		String[] rs = dao.getOneRs(sql, new String[]{xh}, new String[]{"xh"});
		return (rs == null ? false : true);
	}
	
	/**
	 * 
	 * @param ksh
	 * @param type  <font>��������[ѧԺ/УҽԺ/��ʳ�û򻧼���/����]</font>
	 * @return
	 */
	public boolean cancleBd(String ksh,String type) {
	try{	
		String table = "";
		String sql = "";
		if(type.equalsIgnoreCase("xy")){
			table = "xybdzcb";   //ѧԺ
			sql = "update " + table + " set XYBD='��' where ksh=?";
		}else if(type.equalsIgnoreCase("xyy")){
			table = "xyybdzcb";  //УҽԺ
			sql = "update " + table + " set SFBD='��' where ksh=?";
		}else if(type.equalsIgnoreCase("st")){
			table = "stbdzcb";  //����
			sql = "update " + table + " set STBD='��' where ksh=?";
		}else if(type.equalsIgnoreCase("ss")){
			table = "ssbdzcb";  //����
			sql = "update " + table + " set SSBD='��' where ksh=?";
		}else{
			return false;
		}
		return dao.runUpdate(sql,new String[]{ksh});
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}		
	}
	
	/**
	 * ����ȫУ��������/ʵ�ʱ�������/������
	 * <font color='blue'>��Ժ��Ϲ�ҵ��ѧ</font>
	 * @return
	 */
	public HashMap<String,String> getSchoolBdSearch(){
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select totalNum,OkNum,");
		sb.append(" (case when to_number(a.percentBd)<1 and to_number(a.percentBd) >0 then '0'||a.percentBd");  
		sb.append(" when to_number(a.percentBd) = 0 then '0.00' when to_number(a.percentBd) >= 1  then a.percentBd end)||'%' percentBd from"); 
		sb.append(" (select a.totalNum totalNum,b.OkNum OkNum,");
		sb.append(" nvl(to_char(round(to_number(b.OkNum)/to_number(a.totalNum),4) * 100),'0.00') percentBd"); 
		sb.append(" from(select count(*) totalNum from view_newstureportinfo) a, (select nvl(count(*),'0') OkNum"); 
		sb.append(" from view_newstureportinfo where xybd='��') b)a");
		String[] ouput = new String[]{"totalNum","OkNum","percentBd"};
		String[] resArray = dao.getOneRs(sb.toString(),new String[]{}, ouput);
		for(int i=0;i<resArray.length;i++){
			map.put(ouput[i],resArray[i]);
		}
		return map;
	}
	
	/**
	 * ���ظ���ѧԺ�ı�����Ϣ/ѧԺ����/������/ʵ�ʱ�������/������
	 * <font color='blue'>��Ժ��Ϲ�ҵ��ѧ</font>
	 * @return
	 */
	public List<HashMap<String,String>> getXyBdSearch(){
		StringBuffer sb = new StringBuffer();
		sb.append("select xydm,xymc,totalNum,OkNum,");
		sb.append(" (case when to_number(a.percentBd)<1 and to_number(a.percentBd) >0 then '0'||a.percentBd");  
		sb.append("	when to_number(a.percentBd) = 0 then '0.00' when to_number(a.percentBd) >= 1  then a.percentBd end)||'%' percentBd from"); 
		sb.append(" (select a.xydm xydm,a.xymc xymc,a.totalNum totalNum,nvl(b.OkNum,'0') OkNum,");
		sb.append(" nvl(to_char(round(to_number(b.OkNum)/to_number(a.totalNum),4) * 100),'0.00') percentBd"); 
		sb.append(" from(select count(*) totalNum,xymc,xydm from view_newstureportinfo group by xymc,xydm) a"); 
		sb.append(" left join(select nvl(count(*),'0') OkNum,xymc from view_newstureportinfo"); 
		sb.append(" where xybd='��' group by xymc) b on a.xymc = b.xymc)a");	
		String[] output = new String[]{"xydm","xymc","totalNum","OkNum","percentBd"};
		return dao.getList(sb.toString(), new String[]{},output);
	}
	
	/**
	 * * ����ѧԺ�Ĵ��뷵���Ѿ�������δ������ѧ������Ϣ
	 * <font color='blue'>��Ժ��Ϲ�ҵ��ѧ</font>
	 * @param xydm
	 * @param tag
	 * @return
	 */
	public List<HashMap<String,String>> getStuDataByXydm(String xydm,String tag){	
		String sql = "select * from VIEW_NEWSTUREPORTINFO where xydm=?";
		if(tag.equalsIgnoreCase("ybd")){ //�Ѿ�����������
			sql += " and xybd='��'";
		}else if(tag.equalsIgnoreCase("wbd")){  //δ����������
			sql += " and xybd='��'";
		}
		String[] output = new String[]{"ksh","xm","xb","nj","xymc","zymc","bjmc"}; 
		return dao.getList(sql, new String[]{xydm}, output);
	}
	
	/**
	 * ����ѧ��������comment
	 * @return
	 */
	public List<HashMap<String,String>>  getStuTopTr(){
		String[] columns = new String[]{"ksh","xm","xb","nj","xymc","zymc","bjmc"}; 
		String[] cnArray = dao.getColumnNameCN(columns, "VIEW_NEWSTUREPORTINFO");
		return dao.arrayToList(columns, cnArray);
	}
	
	/**
	 * ����ȫУ��ѧ��������δ��������Ϣ
	 * @param tag
	 * @return
	 */
	public List<HashMap<String,String>> getTotalStuData(String tag){
		String sql = "select * from VIEW_NEWSTUREPORTINFO ";
		if(tag.equalsIgnoreCase("ybd")){ //�Ѿ�����������
			sql += " where xybd='��'";
		}else if (tag.equalsIgnoreCase("wbd")){ //�Ѿ�����������
			sql += " where xybd='��'";
		}
		String[] output = new String[]{"ksh","xm","xb","nj","xymc","zymc","bjmc"};
		return dao.getList(sql, new String[]{}, output);
	}
	
	/**
	 * ����ȫУ��ѧ��������δ��������Ϣ
	 * @param tag
	 * @return
	 */
	public List<String[]> getTotalDataVec(String tag,String[] output){
		String sql = "select * from VIEW_NEWSTUREPORTINFO";
		if(tag.equalsIgnoreCase("ybd")){ //�Ѿ�����������
			sql += " where xybd='��'";
		}else if (tag.equalsIgnoreCase("wbd")){ //�Ѿ�����������
			sql += " where xybd='��'";
		}		
		return dao.rsToVator3(sql, new String[]{}, output);
	}
	
	/**
	 * ����ѧԺ�Ĵ��뷵���Ѿ�������δ������ѧ������Ϣ
	 * @param tag
	 * @return
	 */
	public List<String[]> getXyDataVec(String xydm,String tag,String[] output){
		String sql = "select * from VIEW_NEWSTUREPORTINFO where xydm=?";
		if(tag.equalsIgnoreCase("ybd")){ //�Ѿ�����������
			sql += " and xybd='��'";
		}else if(tag.equalsIgnoreCase("wbd")){  //δ����������
			sql += " and xybd='��'";
		}
		return dao.rsToVator3(sql, new String[]{xydm}, output);
	}
	
	/**
	 * ����ѧԺ�Ĵ��뷵��ѧԺ�����ƺ��꼶
	 * @param xydm
	 * @return
	 */
	public String getXymcByXydm(String xydm){
		String sql = "select xymc from newxydmb where xydm=?";
		String[] xymcArray = dao.getOneRs(sql, new String[]{xydm},new String[]{"xymc"});
		return xymcArray== null ? "" : getDqnd() + "���" + xymcArray[0];
	}
	
	/**
	 * ���ص�ǰ���
	 * @return
	 */
	public String getDqnd(){
		String sql = "select dqnd from xtszb";
		String[] xymcArray = dao.getOneRs(sql, new String[]{},new String[]{"dqnd"});
		return xymcArray== null ? "" : xymcArray[0];
	}
	/**
	 * ����ѧУ�����ƺ��꼶
	 * @param xxdm
	 * @return
	 */
	public String getXxmc(){
		String sql = "select dqnd,xxmc from xtszb";
		String[] xxmcArray = dao.getOneRs(sql, new String[]{},new String[]{"dqnd","xxmc"});
		return xxmcArray== null ? "" : xxmcArray[0] + "���" + xxmcArray[1];
	}
	/**
	 * ͨ�������˵�
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String,String>> getselectInfo(String tabName){
		String sql = "select dm,mc from "+tabName;
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});	
	}
	
	/**
	 * ��ѯ���������Ϣ�б�
	 * */
	public List<String> getPyccList(){
		String sql = "select distinct pycc from view_newstureportinfo";
		List<String> list = new ArrayList<String>();
		try {
			list = dao.getList(sql, new String[]{}, "pycc");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * ��ѯ�ض�������εĵ���ʵ�������б�
	 * */
	public List<String> getSdrsList(String pycc,String nj){
		String sql = "select distinct xymc,xydm,("
			+"select sdrs from ("
			+"select sdrs,xymc from (select count(*)sdrs,xymc from view_newstureportinfo where xybd='��' and pycc=? and nj=? group by xymc,xydm order by xydm)"
			+"b where a.xymc=b.xymc))sdrs from view_newstureportinfo a order by xydm";
//		String sql = "";
		List<String> list = new ArrayList<String>();
		try {
			list = dao.getList(sql, new String[]{pycc,nj}, "sdrs");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * ��ѯ�ض�������εĵ���Ӧ�������б�
	 * */
	public List<HashMap<String, String>> getYdrsList(String pycc,String nj){
		String sql = "select distinct xymc,xydm,("
						+"select ydrs from ("
						+"select ydrs,xymc from (select count(*)ydrs,xymc from view_newstureportinfo where pycc=? and nj=? group by xydm,xymc order by xydm)"
						+"b where a.xymc=b.xymc))ydrs from view_newstureportinfo a order by xydm";
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			list = dao.getArrayList(sql, new String[]{pycc,nj}, new String[]{"ydrs","xymc"});
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * ѧԺ�ܵ�Ӧ�������б�
	 * */
	public List<String> getYdrsTotalList(String nj){
		String sql = "select distinct xymc,xydm,("
			+"select ydrs from ("
			+"select ydrs,xymc from (" 
			+"select count(*)ydrs,xymc from view_newstureportinfo where nj=? group by xydm,xymc order by xydm)"
			+"b where a.xymc=b.xymc))ydrs from view_newstureportinfo a order by xydm";
		List<String> list = new ArrayList<String>();
		try {
			list = dao.getList(sql, new String[]{nj}, "ydrs");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}

	/**
	 * ѧԺ�ܵ�ʵ�������б�
	 * */
	public List<String> getSdrsTotalList(String nj){
		String sql = "select distinct xymc,xydm,("
			+"select sdrs from ("
			+"select sdrs,xymc from (" 
			+"select count(*)sdrs,xymc from view_newstureportinfo where xybd='��' and nj=? group by xymc,xydm order by xydm)"
			+"b where a.xymc=b.xymc))sdrs from view_newstureportinfo a order by xydm";
		
		//String sql = "select count(*)sdrs,xymc from view_newstureportinfo where xybd='��' and nj=? group by xymc,xydm order by xydm";
		List<String> list = new ArrayList<String>();
		try {
			list = dao.getList(sql, new String[]{nj}, "sdrs");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * ��ѯѧԺ�б�
	 * */
	public List<String> getXyList(){
		String sql = "select distinct xydm,xymc from view_newstureportinfo order by xydm";
		List<String> list = new ArrayList<String>();
		try {
			list = dao.getList(sql, new String[]{}, "xymc");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	/**
	 * ��ò�ѯ����
	 * @param xxdm
	 * @return
	 */
	public String getselectInfo(YxglActionForm form){
		StringBuffer sb = new StringBuffer();
		form.setSfbd(DealString.toGBK(form.getSfbd()));
		form.setSfzc(DealString.toGBK(form.getSfzc()));
		sb.append(" ");
		if(!Base.isNull(form.getNj())){
			sb.append("and nj='");
			sb.append(form.getNj());
			sb.append("' ");
		}
		if(!Base.isNull(form.getXn())){
			sb.append("and xn='");
			sb.append(form.getXn());
			sb.append("' ");
		}
		if(!Base.isNull(form.getXq())){
			sb.append("and xq='");
			sb.append(form.getXq());
			sb.append("' ");
		}
		if(!Base.isNull(form.getXydm())){
			sb.append("and xydm='");
			sb.append(form.getXydm());
			sb.append("' ");
		}
		if(!Base.isNull(form.getZydm())){
			sb.append("and zydm='");
			sb.append(form.getZydm());
			sb.append("' ");
		}
		if(!Base.isNull(form.getBjdm())){
			sb.append("and bjdm='");
			sb.append(form.getBjdm());
			sb.append("' ");
		}
		if(!Base.isNull(form.getSfbd())){
			sb.append("and sfbd='");
			sb.append(form.getSfbd());
			sb.append("' ");
		}
		if(!Base.isNull(form.getSfzc())){
			sb.append("and sfzc='");
			sb.append(form.getSfzc());
			sb.append("' ");
		}
		if(!Base.isNull(form.getBddm())){
			sb.append("and bddm='");
			sb.append(form.getBddm());
			sb.append("' ");
		}
		if(!Base.isNull(form.getZcdm())){
			sb.append("and zcdm='");
			sb.append(form.getZcdm());
			sb.append("' ");
		}
		if(!Base.isNull(form.getXh())){
			sb.append("and xh='");
			sb.append(form.getXh());
			sb.append("' ");
		}
		return sb.toString();	
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param xxdm
	 * @return
	 */
	public List<String[]> queryXsBdzcInfo(YxglActionForm form,String[] cols){
		String query = getselectInfo(form);
		String xn = form.getXn();
		String xq = form.getXq();
		String sql ="select * from (select a.xh ||'!@!'|| nvl(b.xn,?) ||'!@!'|| nvl(b.xq,?) pk,nvl(b.xn,?) xn,nvl(b.xq,?) xq,(select xqmc from xqdzb where xqdm=nvl(b.xq,?)) xqmc,a.xh," +
				"a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,nvl(b.sfbd,'δ����') sfbd,b.bdqk,nvl(b.sfzc,'δע��') sfzc,b.zcqk from " +
				"view_xsjbxx a left join view_yxgl_xsbdzcxx b on a.xh=b.xh and xn=? and xq = ?) where 1=1 "+ query+" order by xydm,zydm,bjdm,xh ";
		return dao.rsToVator2(sql, new String[]{xn,xq,xn,xq,xq,xn,xq},cols);	
	}
	
	/**
	 * ����ѧ���Ƿ����
	 * @param xxdm
	 * @return
	 */
	public String checkXh(YxglActionForm form){
		String sql = "select xh from view_xsjbxx where xh=? ";
		String xh = dao.getOneRs(sql, new String[]{form.getXh()}, "xh");
		return 	Base.isNull(xh) == true ? "no" : "yes"; 
	}
	
	/**
	 * ѧ��ע��ͱ���
	 * @param xxdm
	 * @return
	 */
	public String xsBdzc(YxglActionForm form){
		String sql ="";
		try{
			if("save".equals(form.getDoType())){
				sql = "select xh from xsbdczqkb where xh=? and xn=? and xq=?";
				String xh = dao.getOneRs(sql, new String[]{form.getXh(),form.getXn(),form.getXq()}, "xh");
				if(Base.isNull(xh)){
					sql = "insert into xsbdczqkb(xh,xn,xq,sfbd,sfzc) values(?,?,?,?,?)";
					dao.runUpdate(sql, new String[]{form.getXh(),form.getXn(),form.getXq(),"�ѱ���","��ע��"});
				}else{
					sql = "update xsbdczqkb set sfbd=?,sfzc=? where xh=? and xn=? and xq=?";
					dao.runUpdate(sql, new String[]{"�ѱ���","��ע��",form.getXh(),form.getXn(),form.getXq()});
				}
				
			}else{
				sql ="delete from xsbdczqkb where xh=? and xn=? and xq=?";
				dao.runUpdate(sql, new String[]{form.getXh(),form.getXn(),form.getXq()});
			}
			return "yes";
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
	}
	
	/**
	 * ��ɽʦ������ѧ������ע����Ϣ
	 * @param xxdm
	 * @return
	 */
	public HashMap<String,String> getOneBdzcInfo(YxglActionForm form){
		String sql = "select * from (select a.xh,nvl(b.xn,?) xn,nvl(b.xq,?) xq,(select xqmc from xqdzb where xqdm=nvl(b.xq,?)) xqmc,a.xm,a.xb,a.nj,xymc,zymc,bjmc,sfzh,nvl(b.sfbd,'δ����') sfbd," +
				"nvl(b.sfzc,'δע��') sfzc,b.bddm,b.zcdm,b.bz from view_xsjbxx a left join xsbdczqkb b on " +
				"a.xh=b.xh ) where xh||xn||xq=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{form.getXn(),form.getXq(),form.getXq(),form.getPk()});
		HashMap<String,String> map = new HashMap<String,String>();
		if(list != null && list.size() > 0){
			map = list.get(0);
		}
		return map;
	}
	
	/**
	 * ��ɽʦ������ѧ������ע����Ϣ����
	 * @param xxdm
	 * @return
	 */
	public String saveOneBdzcInfo(YxglActionForm form){
		String sql = "select xh from xsbdczqkb where xh=? and xn=? and xq=?";
		String xh = dao.getOneRs(sql, new String[] { form.getXh(),form.getXn(),form.getXq() }, "xh");
		form.setSfbd(DealString.toGBK(form.getSfbd()));
		form.setSfzc(DealString.toGBK(form.getSfzc()));
		form.setBz(DealString.toGBK(form.getBz()));
		try {
			if (Base.isNull(xh)) {
				sql = "insert into xsbdczqkb(xh,xn,xq,sfbd,sfzc,bddm,zcdm,bz) values(?,?,?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { form.getXh(),form.getXn(),form.getXq(),form.getSfbd(),
						form.getSfzc(), form.getBddm(), form.getZcdm(),
						form.getBz() });
			} else {
				sql = "update xsbdczqkb set sfbd=?,sfzc=?,bddm=?,zcdm=?,bz=? where xh=? and xn=? and xq=?";
				dao.runUpdate(sql, new String[] { form.getSfbd(),form.getSfzc(), form.getBddm(), form.getZcdm(),
						form.getBz(), form.getXh(),form.getXn(),form.getXq() });
			}
			return "yes";
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
	//���ز�ѯʱʹ�õ�ѧ�ꡢ����б�
	public List<HashMap<String, String>> getNoNullXnndList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		// int currentNd = (new Date()).getYear() + 1900;
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			nd = String.valueOf(i);
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("nd", nd);
			map.put("xn", xn);
			aList.add(map);
		}
		return aList;
	}
	
	//	���sql
	public String getSql(YxglActionForm form) {
		String query = getselectInfo(form);
		
		String xn = form.getXn();
		String xq = form.getXq();
		String sql ="select xh,xn,xq,xm,nj,xymc,zymc,bjmc,sfbd,bdqk,sfzc,zcqk,bz from (select nvl(b.xn,'"+xn+"') xn,nvl(b.xq,'"+xq+"') xq,(select xqmc from xqdzb " +
				"where xqdm=nvl(b.xq,'"+xq+"')) xqmc,a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,nvl(b.sfbd,'δ����') sfbd,b.bdqk,nvl(b.sfzc,'δע��') sfzc,b.zcqk,b.bz from " +
				"view_xsjbxx a left join view_yxgl_xsbdzcxx b on a.xh=b.xh and xn='"+xn+"' and xq = '"+xq+"') where 1=1 "+ query+" order by xydm,zydm,bjdm,xh ";
		return sql;
	}
}