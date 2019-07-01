package xgxt.qtsj.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
/**
 * 其它数据
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
	  * dwr客户端判断保险信息是否在其它信息中存在
	  * @param tabName 当前页面所属表
	  * @param isSchool　是否是校园方责任险
	  * @param xh　需要判断的学生学号
	  * @param nd 年度
	  * */
	 public static String InsureInfoisExist(String xh,String nd){
		 String result="";
		 String _bx_xh="";
		 _bx_xh=getInstance().getOneRs("select xh from xsbxb where xh=? and nd=?", new String[]{xh,nd}, "xh");
		 if(!"".equalsIgnoreCase(_bx_xh)){
			 result="此学生在"+nd+"年度存在保险基本信息！";
		 }		 
		 return result;		 
	 }
	 
	 //浙江商业职业增加信息到火车票预定库
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
	 
	 //浙江商业职业根据车次查询到站和起站
	 public static String getTrainInfoByCc(String cc){
		 DAO dao=DAO.getInstance();
		 String qdz="";
		 String zdz="";
		 qdz=dao.getOneRs("select qdz from hcccb where cc=?", new String[]{cc}, "qdz");
		 zdz=dao.getOneRs("select zdz from hcccb where cc=?", new String[]{cc}, "zdz");
		 return qdz+","+zdz;		 
	 }
	 
	 //浙江商业职业根据车次查询学生加入预定库的车次信息
	 public static String getZdzInfo(String xh,String cc){
		 HashMap<String, String> map = new HashMap<String, String>();
		 DAO dao = DAO.getInstance();
		 
		 xh = xh==null || xh.equalsIgnoreCase("") ? "" : xh.trim();
		 cc = cc==null || cc.equalsIgnoreCase("") ? "" : cc.trim();
		 
		 map = dao.getMap("select zdz,pj from hccpydkb where cc=? and xh=?",new String[]{cc,xh}, new String[]{"zdz","pj"});
		 return map.get("zdz") + "," + map.get("pj");
	 }
	 
	 //浙江商业职业判断是否有申请成功的记录
	 public static String checkCpydInfo(String xh,String nd,String xn,String xq){
		 DAO dao = DAO.getInstance();
		 xh = xh==null || "".equalsIgnoreCase(xh)? "" : xh.trim();
		 nd = nd==null || "".equalsIgnoreCase(nd)? "" : nd.trim();
		 xn = xn==null || "".equalsIgnoreCase(xn)? "" : xn.trim();
		 String sql = "select count(*)num from view_cpyd where xh=? and nd=? and xn=? and xq=? and ydjg='成功'";
		 int num = Integer.parseInt(dao.getOneRs(sql, new String[]{xh,nd,xn,xq}, "num"));
		 return num>0 ? "true" : "false";
	 }	 
	 
	 //判断字段是否在其它表中存在
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
			 	strV="保险期限";
			 if("bxje".equalsIgnoreCase(str))
				 strV="保险金额";
			 if("bxdc".equalsIgnoreCase(str))
				 strV="保险档次";
			 result = strV+"内容和保险基本信息填写的不一致!";
		 }
		 return result;		 
	 }
	 
	 //判断保险中同一项和其他表中是否一致
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
	 
	 //用于dwr客户端获取保费
	 public static String getBf(String bxxzdm,String bxnx,String xh){
		 HashMap< String, String> map = new HashMap<String, String>();
		 DAO dao=DAO.getInstance();
		 int iBf = 0;
		 bxnx = bxnx==null ||"".equalsIgnoreCase(bxnx) ? "0" : bxnx;
		 xh = xh==null ? "" : xh;
		 //按保险险种查询期限类别和保险金额
		 String sql = "select qxlb,bxje from bxxzb where bxxzdm=?";
		 map = dao.getMap(sql, new String[]{bxxzdm}, new String[]{"qxlb","bxje"});
		 
		 //查询学生学制
		 sql = "select xz from view_xsjbxx where xh=?";
		 String xz = dao.getOneRs(sql, new String[]{xh}, "xz");
		 xz = xz==null || "".equalsIgnoreCase(xz) ? "0" : xz;
		 
		 if(map.get("qxlb")!=null && "自定义".equalsIgnoreCase(map.get("qxlb"))){
			 iBf = Integer.parseInt(map.get("bxje")) * Integer.parseInt(bxnx);
		 }
		 if(map.get("qxlb")!=null && "按学制".equalsIgnoreCase(map.get("qxlb"))){
			 iBf = Integer.parseInt(map.get("bxje")) * Integer.parseInt(xz);
			 bxnx=xz;
		 }
		 return String.valueOf(iBf)+"!!" + bxnx;
	 }
	 
	 /**
	  * 根据保险公司代码获取保险险种列表
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
	  * 根据保险险种代码取保险公司代码
	  * @param tbxzdm
	  * @return
	  */
	 public String getBxgsdm(String tbxzdm) {	
		 String sql = "select * from bxxzb where bxxzdm=?";
		 
		 return dao.getOneRs(sql, new String[] {tbxzdm}, "bxgsdm");
		 
	 }
	 
}
