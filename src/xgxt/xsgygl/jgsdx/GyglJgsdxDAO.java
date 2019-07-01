/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 下午02:15:50</p>
 */
package xgxt.xsgygl.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;


public class GyglJgsdxDAO {
	/**文明寝室申报信息保存*/
	public boolean sbXxSave(GyglJgsdxWmqssbModel Model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xn     = Model.getXn();
		String xq     = Model.getXq();
		String lddm   = Model.getLddm();
		String qsh    = Model.getQsh();
		String sj     = Model.getPysj();
		String bz     = Model.getBz();
		String sbr    = Model.getXm();
		String ssbh   = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
		String sql    = " delete from wmqssbb where xn||xq||ssbh=? ";
		done          = dao.runUpdate(sql,new String[]{xn+xq+ssbh});
		if(done){
			sql  = "insert into wmqssbb(xn,xq,pysj,ssbh,bz,xm)values(?,?,?,?,?,?)";
			done = dao.runUpdate(sql,new String[]{xn,xq,sj,ssbh,bz,sbr});
		}
		return done;	   
	}
	/**返回文明寝室申报信息内容*/ 
	public HashMap<String,String> getWmQsSbXx(String pkValue){   
		DAO dao    = DAO.getInstance();
		String sql = " select xn,xq,pysj,ssbh,bz,xysh,xxsh,xm from wmqssbb where xn||xq||ssbh=? ";
		return dao.getMap(sql, new String[]{pkValue}, new String[]{"xn","xq","pysj","ssbh","bz","xysh","xxsh","xm"});	   
	}
	/**返回辅导员用户文明寝室查询结果*/
	public ArrayList<String[]> getwmQsSbFdyshRes(GyglJgsdxWmqssbModel Model){
		DAO dao                = DAO.getInstance();		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String querry          =  whereSql(Model);
		String yesNo           = Model.getYesNo();//审核标志
		querry           +=Base.isNull(yesNo)?"":" and fdysh='"+yesNo+"' ";
		querry           += " order by pysj asc  ";
		String[] colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh"};//查询显示字段
		String sql       = " select xn||xq||ssbh prkey,(case when fdysh='通过' then '#FFFFFF' else '#CCCCCC' end)bgcolor ," 
			+"xn,xq,ldmc,qsh,pysj,fdysh from view_wmqssbxx ";
//		执行查询
		rs = dao.rsToVator(sql + querry, new String[]{},colList);
		return rs;
	}
	/**返回院系用户文明寝室查询结果*/
	public ArrayList<String[]> getwmQsSbXyshRes(GyglJgsdxWmqssbModel Model){
		DAO dao                = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String querry          = whereSql(Model);
		String yesNo           = Model.getYesNo();//审核标志
		querry            +=Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' ";
		querry            += " and fdysh='通过' ";
		querry            += " order by pysj asc  ";
		String[] colList  = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh"};//查询显示字段
		String sql        = " select xn||xq||ssbh prkey,(case when xysh='通过' then '#FFFFFF' else '#CCCCCC' end)bgcolor," 
			+" xn,xq,ldmc,qsh,pysj,fdysh,xysh from view_wmqssbxx ";
//		执行查询
		rs = dao.rsToVator(sql + querry, new String[]{},colList);		
		return rs;
	}
	/**返回学校用户文明寝室查询结果*/
	public ArrayList<String[]> getwmQsSbXxshRes(GyglJgsdxWmqssbModel Model){
		DAO dao                = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String querry          = whereSql(Model);
		String yesNo           = Model.getYesNo();//审核标志
		querry            +=Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ";
		querry            += " and xysh='通过' ";
		querry            += " order by pysj asc  ";
		String[] colList  = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh","xxsh"};//查询显示字段
		String sql        = " select xn||xq||ssbh prkey,(case when xxsh='通过' then '#FFFFFF' else '#CCCCCC' end)bgcolor," 
			+" xn,xq,ldmc,qsh,pysj,fdysh,xysh,xxsh from view_wmqssbxx ";
//		执行查询
		rs = dao.rsToVator(sql + querry, new String[]{},colList);		
		return rs;
	}
	/**查询条件*/
	public String whereSql (GyglJgsdxWmqssbModel Model){		
		StringBuffer querry = new StringBuffer();
		String xn     = Model.getXn();//学年
		String xq     = Model.getXq();//学期
		String lddm   = Model.getLddm();//楼栋代码
		String qsh    = Model.getQsh();//寝室号
		String pysj   = Model.getPysj();//评优时间
		
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");		
		return querry.toString();
	}
	/**
	 *  @return 文明寝室审核辅导员查询的表头
	 */
	public ArrayList<HashMap<String, String>> getwmQsShFdyTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// 必须与方法getwmQsSbXyShSearch中的输出表列一致
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh"};//查询显示字段
		colListCN = new String[]{ "主键", "背景色", "学年", "学期", "楼栋", "宿舍号", "评优时间","辅导员审核"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**
	 * @return 文明寝室审核学院查询的表头
	 */
	public ArrayList<HashMap<String, String>> getwmQsShXyTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// 必须与方法getwmQsSbXyShSearch中的输出表列一致
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh"};//查询显示字段
		colListCN = new String[]{ "主键", "背景色", "学年", "学期", "楼栋", "宿舍号", "评优时间","辅导员审核","院系审核"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**
	 * @return 文明寝室审核学校查询的表头
	 */
	public ArrayList<HashMap<String, String>> getwmQsShXxTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// 必须与方法getwmQsSbXyShSearch中的输出表列一致
		colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","fdysh","xysh","xxsh"};//查询显示字段
		colListCN = new String[]{ "主键", "背景色", "学年", "学期", "楼栋", "宿舍号", "评优时间","辅导员审核","院系审核","学校审核"};
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	public HashMap<String,String> wmSbOneInfo(String pkValue,String shType){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk                  = "xn||xq||ssbh";
		String[] colList           = {"xn","xq","pysj","ssbh","bz","xm","yesNo","lddm","ldmc","qsh"};
		String sql                 = "";		
		sql = "select xn,xq,pysj,ssbh,bz,xm,"+shType+" yesNo,lddm,ldmc,qsh from view_wmqssbxx where "+pk+"=? ";		              
	    map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	
	/**文明寝室申报辅导员审核*/
	public boolean wmqsFdyShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set fdysh=? where "+pk+"=? ";
		return dao.runUpdate(sql,new String[]{yesNo,pkValue});
	}
	/**文明寝室申报院系审核*/
	public boolean wmqsXyShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set xysh=? where "+pk+"=? ";
		return dao.runUpdate(sql,new String[]{yesNo,pkValue});
	}
	/**文明寝室申报学校审核*/
	public boolean wmqsXxShSave(String pkValue,String yesNo) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String pk     = "xn||xq||ssbh";
		String sql    = " update wmqssbb set xxsh=? where "+pk+"=? ";
		done =  dao.runUpdate(sql,new String[]{yesNo,pkValue});
		if(done){//审核通过时将信息保存到文明寝室信息维护中
			if(yesNo.equalsIgnoreCase("通过")){
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
				if(done){
					sql = "insert into wmqspbb(xn,xq,pysj,ssbh,bz) select xn,xq,pysj,ssbh,bz from wmqssbb where "+pk+"=?";
					done = dao.runUpdate(sql, new String[]{pkValue});
				}
			}else if(yesNo.equalsIgnoreCase("不通过")){//
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
			}
		}
		return done;
	}
	public String isUserReq(String pkValue, String userName) {//判断该条记录是否是当前用户申报
		String str = "";
		String pk  = "xn||xq||ssbh";
		DAO dao    = DAO.getInstance();
		String sql = "select count(*) ct from wmqssbb where "+pk+" =?  and xm=? ";
		str = dao.getOneRs(sql, new String[]{pkValue,userName},"ct");
		str = (str=="0"||str.equalsIgnoreCase("0"))?"no":"yes";
		return str;
	}
}
