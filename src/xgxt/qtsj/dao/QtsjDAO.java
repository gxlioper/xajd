package xgxt.qtsj.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
/**
 * ��������
 * */
public class QtsjDAO  {
	 private static QtsjDAO qtsj=new QtsjDAO();
	
	 public QtsjDAO() {
		
	 }
	 
	 public static QtsjDAO getInstance() {
			if (qtsj != null) {
				return new QtsjDAO(); 
			} else {
				return qtsj;
			}
		}
	 DAO dao = DAO.getInstance(); 
	 
	 public String getOneRs(String sql,String[] inputValue,String outputValue){
		 return dao.getOneRs(sql, inputValue, outputValue);
	 }
	 
	 /**
	  * dwr�ͻ����жϱ�����Ϣ�Ƿ���������Ϣ�д���
	  * @param tabName ��ǰҳ��������
	  * @param isSchool���Ƿ���У԰��������
	  * @param xh����Ҫ�жϵ�ѧ��ѧ��
	  * @param nd ���
	  * */
	 public static String InsureInfoisExist(String xh,String nd){
		 String result="";
		 String _bx_xh="";
		 _bx_xh=getInstance().getOneRs("select xh from xsbxb where xh=? and nd=?", new String[]{xh,nd}, "xh");
		 if(!"".equalsIgnoreCase(_bx_xh)){
			 result="��ѧ����"+nd+"��ȴ��ڱ��ջ�����Ϣ��";
		 }		 
		 return result;		 
	 }
	 
	 //�㽭��ҵְҵ������Ϣ����ƱԤ����
	 public static String AddYdkInfo(String cc,String xh,String zdz,String pj,String type){
		 boolean flag=false;
		 DAO dao= DAO.getInstance(); 
		 cc = cc==null || cc.equalsIgnoreCase("") ? "" :cc.trim();
		 xh = xh==null || xh.equalsIgnoreCase("") ? "" : xh.trim();
		 type = type==null || type.equalsIgnoreCase("") ? "" : type.trim();
		 zdz = zdz==null || "".equalsIgnoreCase(zdz) ? "" : zdz.trim();
		 pj = pj==null || "".equalsIgnoreCase(pj) ? "" : pj.trim();		
		 String sql="delete from hccpydkb where xh=? and cc=?";
		 try {
			 flag=dao.runUpdate(sql,new String[]{xh,cc});
		 if("add".equalsIgnoreCase(type) && flag){			
			 sql="insert into hccpydkb(xh,cc,zdz,pj) values(?,?,?,?)";
			 flag=dao.runUpdate(sql, new String[]{xh,cc,zdz,pj});
		 } 
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 return flag?"true":"false";
		 
	 }
	 
	 //�㽭��ҵְҵ���ݳ��β�ѯ��վ����վ
	 public static String getTrainInfoByCc(String cc){
		 DAO dao=DAO.getInstance();
		 String qdz="";
		 String zdz="";
		 qdz=dao.getOneRs("select qdz from hcccb where cc=?", new String[]{cc}, "qdz");
		 zdz=dao.getOneRs("select zdz from hcccb where cc=?", new String[]{cc}, "zdz");
		 return qdz+","+zdz;		 
	 }
	 
	 //�㽭��ҵְҵ���ݳ��β�ѯѧ������Ԥ����ĳ�����Ϣ
	 public static String getZdzInfo(String xh,String cc){
		 HashMap<String, String> map = new HashMap<String, String>();
		 DAO dao = DAO.getInstance();
		 
		 xh = xh==null || xh.equalsIgnoreCase("") ? "" : xh.trim();
		 cc = cc==null || cc.equalsIgnoreCase("") ? "" : cc.trim();
		 
		 map = dao.getMap("select zdz,pj from hccpydkb where cc=? and xh=?",new String[]{cc,xh}, new String[]{"zdz","pj"});
		 return map.get("zdz") + "," + map.get("pj");
	 }
	 
	 //�㽭��ҵְҵ�ж��Ƿ�������ɹ��ļ�¼
	 public static String checkCpydInfo(String xh,String nd,String xn,String xq){
		 DAO dao = DAO.getInstance();
		 xh = xh==null || "".equalsIgnoreCase(xh)? "" : xh.trim();
		 nd = nd==null || "".equalsIgnoreCase(nd)? "" : nd.trim();
		 xn = xn==null || "".equalsIgnoreCase(xn)? "" : xn.trim();
		 String sql = "select count(*)num from view_cpyd where xh=? and nd=? and xn=? and xq=? and ydjg='�ɹ�'";
		 int num = Integer.parseInt(dao.getOneRs(sql, new String[]{xh,nd,xn,xq}, "num"));
		 return num>0 ? "true" : "false";
	 }	 
	 
	 //�ж��ֶ��Ƿ����������д���
	 public static String CheckExist(String str,String val,String xh,String nd){
		 DAO dao=DAO.getInstance();
		 String tableName = "xsbxb";
		 String result="";
		 String rs="";
		 String strV="";
		 String sql="select "+str+" from "+tableName+" where xh||nd=?";		
		 rs=dao.getOneRs(sql,new String[]{xh.trim()+nd.trim()},str);		 
		 if((rs!=null &&!rs.equalsIgnoreCase(val)) || rs==null){
			 if("bxnx".equalsIgnoreCase(str));
			 	strV="��������";
			 if("bxje".equalsIgnoreCase(str))
				 strV="���ս��";
			 if("bxdc".equalsIgnoreCase(str))
				 strV="���յ���";
			 result = strV+"���ݺͱ��ջ�����Ϣ��д�Ĳ�һ��!";
		 }
		 return result;		 
	 }
	 
	 //�жϱ�����ͬһ������������Ƿ�һ��
	 public static String CheckOrtherExist(String table1,String table2,String str,String value,String xh,String nd,String bxgsdm,String flag1,String flag2){
		 String result="";
		 String values="";
		 DAO dao=DAO.getInstance();
		 String sql="";
		 if(!"".equalsIgnoreCase(table1)){
			 sql="select "+str+" from "+table1+" where xh=? and nd=? and bxgsdm=?";
			 if(!"".equalsIgnoreCase(flag1)){
				 sql+=" and sfxfzrx="+flag1;
			 }		 
			 values=dao.getOneRs(sql,new String[] {xh,nd,bxgsdm}, str);
		}
		 if(!value.equalsIgnoreCase(values)){
			 result+=table1;
		 } 
		 result+=",";
		 if(!"".equalsIgnoreCase(table2)){
			 sql="select "+str+" from "+table2+" where xh=? and nd=?";
			 if(!"".equalsIgnoreCase(flag2)){
				 sql+=" and sfxfzrx="+flag2;
			 }
			 values=dao.getOneRs(sql, new String[] {xh,nd}, str);
		 }
		 if(!value.equalsIgnoreCase(values)){
			 result+=table2;
		 }
		 result+=",";
		 return result;
	 }
	 
	 //����dwr�ͻ��˻�ȡ����
	 public static String getBf(String bxxzdm,String bxnx,String xh){
		 HashMap< String, String> map = new HashMap<String, String>();
		 DAO dao=DAO.getInstance();
		 int iBf = 0;
		 bxnx = bxnx==null ||"".equalsIgnoreCase(bxnx) ? "0" : bxnx;
		 xh = xh==null ? "" : xh;
		 //���������ֲ�ѯ�������ͱ��ս��
		 String sql = "select qxlb,bxje from bxxzb where bxxzdm=?";
		 map = dao.getMap(sql, new String[]{bxxzdm}, new String[]{"qxlb","bxje"});
		 
		 //��ѯѧ��ѧ��
		 sql = "select xz from view_xsjbxx where xh=?";
		 String xz = dao.getOneRs(sql, new String[]{xh}, "xz");
		 xz = xz==null || "".equalsIgnoreCase(xz) ? "0" : xz;
		 
		 if(map.get("qxlb")!=null && "�Զ���".equalsIgnoreCase(map.get("qxlb"))){
			 iBf = Integer.parseInt(map.get("bxje")) * Integer.parseInt(bxnx);
		 }
		 if(map.get("qxlb")!=null && "��ѧ��".equalsIgnoreCase(map.get("qxlb"))){
			 iBf = Integer.parseInt(map.get("bxje")) * Integer.parseInt(xz);
			 bxnx=xz;
		 }
		 return String.valueOf(iBf)+"!!" + bxnx;
	 }
	 
	 /**
	  * ���ݱ��չ�˾�����ȡ���������б�
	  * @param bxgsdm
	  * @return List
	  * */
	 public static List getBxxzByBxgs(String bxgsdm){
		 List list = null;
		 DAO dao=DAO.getInstance();
		 String sql = "select distinct bxxzdm,bxxzmc from bxxzb where 1=1";
		 if(bxgsdm!=null && !bxgsdm.equals("")){
			 sql += " and bxgsdm='" + bxgsdm + "'";
		 }
		 sql += " order by bxxzdm";
		 list = dao.getList(sql, new String[]{}, new String[]{"bxxzdm","bxxzmc"});		 
		 return list;
	 }
	
	 
	 /**
	  * ���ݱ������ִ���ȡ���չ�˾����
	  * @param tbxzdm
	  * @return
	  */
	 public String getBxgsdm(String tbxzdm) {	
		 String sql = "select * from bxxzb where bxxzdm=?";
		 
		 return dao.getOneRs(sql, new String[] {tbxzdm}, "bxgsdm");
		 
	 }
	 
}
