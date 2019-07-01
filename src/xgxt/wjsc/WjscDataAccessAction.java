package xgxt.wjsc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import oracle.sql.CLOB;
import xgxt.DAO.DAO;
import xgxt.utils.Check_Input_Value;

//import javax.servlet.http.HttpSession;

public class WjscDataAccessAction {
	
	//获取系统时间 
	public static String GetSysTime(){
		DAO dao = DAO.getInstance();
		String systime = "";
		systime=dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') wjffsj from dual", new String[]{}, "wjffsj");
		return systime;
	}
	
	//获取文件上传列表
	public static List GetFileList(String yhm){
		DAO dao = DAO.getInstance();
		String query ="";
		
		if("zf01".equalsIgnoreCase(yhm)||"admin".equalsIgnoreCase(yhm)){
			query = " 1=1 ";
		}else{
			query = " ffr like '%" + yhm + "%' ";
		}
		String sql = "select wjh,wjm,bmmc,ffr,wjffsj from view_scwjxx where "+ query +"  order by wjffsj desc";
		List rs = dao.getList(sql, new String[]{}, new String []{"wjh","wjm","bmmc","ffr","wjffsj"});
		return rs;
	}
	//获取文件详细信息
	public static HashMap<String, String> GetWjscInfo(String wjh) throws SQLException{
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select wjh,bmmc,wjffsj,wjscsm,ffr,wjsclj,wjm,substr(wjsclj,23,length(wjsclj)-22) filename from view_scwjxx where wjh = ? ";
		map = dao.getMap(sql, new String[]{wjh}, new String[]{"wjh","bmmc","wjffsj","wjscsm","ffr","wjsclj","wjm","filename"});
		sql = "select jsr,ydrxm,wdrxm,jsrxm from view_scwjxx where wjh = ?";
		CLOB clob1 = dao.getOneClob(sql, new String[]{wjh}, "ydrxm");
		map.put("ydrxm", clob1 == null ? "":clob1.getSubString(1L, (int) clob1.length()));
		CLOB clob2 = dao.getOneClob(sql, new String[]{wjh}, "jsrxm");
		map.put("jsrxm", clob2 == null ? "":clob2.getSubString(1L, (int) clob2.length()));
		CLOB clob3 = dao.getOneClob(sql, new String[]{wjh}, "wdrxm");
		map.put("wdrxm", clob3 == null ? "":clob3.getSubString(1L, (int) clob3.length()));
		CLOB clob4 = dao.getOneClob(sql, new String[]{wjh}, "jsr");
		map.put("jsr", clob4 == null ? "":clob4.getSubString(1L, (int) clob4.length()));
		return map;
	}
	
	//获取组列表
	public static List getYhzList(){
		DAO dao = DAO.getInstance();
		List yhzList = null;
		String sql = "select zdm,zmc from yhzb a where a.zdm<>'6727' order by zdm";
		yhzList = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc"});
		return yhzList;
	}
	
	//获取用户列表
	public static List getYhList(String zdm){
		DAO dao = DAO.getInstance();
		List yhList = null;
		String sql = "select yhm,yhm bmmc from view_yhxx where zdm like ?";
		yhList = dao.getList(sql, new String[]{zdm}, new String[]{"yhm","bmmc"});
		return yhList;
	}
	
	//获取文件信息
	public static List<String[]> getFileInfo(String tableName, String realTable, String [] colList, String querry,String userName) throws Exception{
		WjscDao dao = new WjscDao();
		List<String[]>wjxxlist=new ArrayList<String[]>();
		String yhxm=WjscDataAccessAction.getYhXm(userName);
		String sql = "select wjh,wjm,wjjszmc,ffr,wjffsj from " + tableName + querry+" order by wjffsj desc";
		List<HashMap<String,String>> list = dao.getList(sql, new String[]{}, colList);
		String jsrSql = "select jsrxm from " + tableName + querry+" order by wjffsj desc";
		List<CLOB> cloblsit = dao.getClobList(jsrSql, new String[]{}, "jsrxm");
		for(int i=0;i<list.size();i++){
			String jsrxm=cloblsit.get(i) == null ? "" : cloblsit.get(i).getSubString(1L, (int) cloblsit.get(i).length());
			HashMap<String,String> map =(HashMap<String,String>)list.get(i);	
			if(jsrxm.startsWith(yhxm) || yhxm.equalsIgnoreCase(map.get("ffr"))){
				String[]wj=new String[6];
				wj[0]=map.get("wjh");
				wj[1]=map.get("wjm");
				wj[2]=map.get("wjjszmc");
				wj[3]=jsrxm;
				wj[4]=map.get("ffr");
				wj[5]=map.get("wjffsj");
				wjxxlist.add(wj);
			}
		}
		return wjxxlist;
	}
	
//	获取文件信息
	public static List<String[]> getWjxxList(String [] colList,String[] inputList, String querry,String userName) throws Exception{
		WjscDao dao = new WjscDao();
		List<String[]>wjxxlist=new ArrayList<String[]>();
		String yhxm=WjscDataAccessAction.getYhXm(userName);
		String sql = "select wjh,wjm,wjjszmc,ffr,wjffsj from view_scwjxx where 1=1 "+ querry+" order by wjffsj desc";
		List<HashMap<String,String>> list = dao.getList(sql, inputList, colList);
		String jsrSql = "select jsrxm from view_scwjxx where 1=1 "+ querry+" order by wjffsj desc";
		List<CLOB> cloblsit = dao.getClobList(jsrSql, inputList, "jsrxm");
		for(int i=0;i<list.size();i++){
			String jsrxm=cloblsit.get(i) == null ? "" : cloblsit.get(i).getSubString(1L, (int) cloblsit.get(i).length());
			HashMap<String,String> map =(HashMap<String,String>)list.get(i);	
				
				String[]wj=new String[18];
				wj[0]=map.get("wjh");
				wj[1]=map.get("wjm");
				wj[2]=map.get("wjffbm");
				wj[3]=map.get("wjjsbm");
				wj[4]=jsrxm;
				wj[5]=map.get("bcks");
				wj[6]=map.get("glr");
				wj[7]=map.get("yjr");
				wj[8]=map.get("wjffsj");
				wj[9]=map.get("wjjssj");
				wj[10]=map.get("wjsclj");
				wj[11]=map.get("wjscsm");
				wj[12]=map.get("ffr");
				wj[13]=map.get("wjjszdm");
				wj[14]=map.get("bmmc");
				wj[15]=map.get("spr");
				wj[16]=jsrxm;
				wj[17]=map.get("wjjszmc");
				wjxxlist.add(wj);
		}
		return wjxxlist;
	}
	
//	获取table表头
	public static List getTheadList(String [] colList, String realName){
		DAO dao = DAO.getInstance();
		String [] colListCN = dao.getColumnNameCN(colList,realName);
		return dao.arrayToList(colList, colListCN);
	}
	
//	获取查询条件
	public static StringBuffer getQuerry(String wjm, String jsr){
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		if("".equalsIgnoreCase(wjm) || wjm== null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(wjm)){
				querry.append(" and wjm like '%");
				querry.append(wjm);
				querry.append("%'");
			}else{
				querry.append(" and 1=2");
			}
		}
		if("".equalsIgnoreCase(jsr) || jsr == null){
			querry.append(" and 1=1");
		}else{
			if(Check_Input_Value.check2(jsr)){
				querry.append(" and jsrxm like '%");
				querry.append(jsr);
				querry.append("%'");
			}else{
				querry.append(" and 1=2");
			}
		}
		return querry;
	}
	
	//获取文件存放路径
	public static String getFilePath(String wjh){
		DAO dao = DAO.getInstance();
		String filepath = dao.getOneRs("select wjsclj from wjsc_scwjxxb where wjh =?", new String[]{wjh}, "wjsclj");
		return filepath;
	}
	
	//根据用户名获取姓名
	public static String getYhXm(String yhm){
		DAO dao = DAO.getInstance();
		HashMap<String,String>map = dao.getMap("select xm from yhb where yhm =?", new String[]{yhm},new String[]{"xm"});
		return map.get("xm");
	}
	
	public static String getYhxm(String[]yhArr){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select yhm,xm from  yhb where 1=1 and (");
		int i=0;
		for(String ydr:yhArr){
			if(i==0){
				sql.append(" yhm='"+ydr+"' ");
			}else{
				sql.append(" or yhm='"+ydr+"' ");
			}
			i++;
		}
		sql.append(")");
		List<HashMap<String,String>>list=dao.getList(sql.toString(), new String[]{}, new String[]{"yhm","xm"});
		
		String xmStr="";
		int j=0;
		for(HashMap<String,String>xmMap:list){
			if(j==0){
				xmStr+=xmMap.get("yhm")+"("+xmMap.get("xm")+")";
			}else{
				xmStr+=","+xmMap.get("yhm")+"("+xmMap.get("xm")+")";
			}
			j++;
		}
		return xmStr;
	}
	
	
}
