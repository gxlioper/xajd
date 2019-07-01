/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:安徽建筑工业学院公寓管理DAO </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-1 下午01:54:37</p>
 */
package xgxt.xsgygl.ahjzgyxy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GyglAhjgDAO{
   /**文明寝室申报信息保存*/
   public boolean sbXxSave(GyglAhjgWmqssbModel mgawModel) throws Exception{
	   DAO dao       = DAO.getInstance();
	   boolean done = false;
	   String xn   = mgawModel.getXn();
	   String xq   = mgawModel.getXq();
	   String lddm = mgawModel.getLddm();
	   String qsh  = mgawModel.getQsh();
	   String sj   = mgawModel.getPysj();
	   String bz   = mgawModel.getBz();
	   String sbr  = mgawModel.getXm();
	   String qslb = mgawModel.getQslb();
	   String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
	   String sql  = " delete from wmqssbb where xn||xq||ssbh=? ";
	   done = dao.runUpdate(sql,new String[]{xn+xq+ssbh});
	   if(done){
		   sql = "insert into wmqssbb(xn,xq,pysj,ssbh,bz,xm,qslb)values(?,?,?,?,?,?,?)";
		   done = dao.runUpdate(sql,new String[]{xn,xq,sj,ssbh,bz,sbr,qslb});
	   }
	   return done;	   
   }
   /**返回文明寝室申报信息内容*/ 
   public HashMap<String,String> getWmQsSbXx(String pkValue){   
	   DAO dao = DAO.getInstance();
	   String sql = " select xn,xq,pysj,ssbh,bz,xysh,xxsh,xm,qslb from wmqssbb where xn||xq||ssbh=? ";
	   return dao.getMap(sql, new String[]{pkValue}, 
	   new String[]{"xn","xq","pysj","ssbh","bz","xysh","xxsh","xm","qslb"});	   
   }
   /**返回学院审核查询结果*/
   public ArrayList<String[]> getwmQsSbXyShSearch(GyglAhjgWmqssbModel mgawModel) {
	   DAO dao = DAO.getInstance();
	   ArrayList<String[]> rs = new ArrayList<String[]>();
	   String xn     = mgawModel.getXn();//学年
	   String xq     = mgawModel.getXq();//学期
	   String lddm   = mgawModel.getLddm();//楼栋代码
	   String qsh    = mgawModel.getQsh();//寝室号
	   String pysj   = mgawModel.getPysj();//评优时间
	   String yesNo  = mgawModel.getYesNo();//审核标志
	   String sql    =  "";
	   String[] colList = null;
	   StringBuffer querry = new StringBuffer();
	   //查询条件
	   querry.append(" where 1=1 ");
	   querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
	   querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
	   querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
	   querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
	   querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");
	   querry.append(Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' ");
	   querry.append(" order by pysj asc ");
	   colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh"};//查询显示字段 
	   sql     = " select xn||xq||ssbh prkey,(case when xysh='通过' then '#FFFFFF' else '#CCCCCC' end)bgcolor," 
		       + " xn,xq,ldmc,qsh,pysj,xysh from view_wmqssbxx ";
	   //执行查询
	   rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	   return rs;
   }
   public ArrayList<String[]> getwmqsXXshSearch(GyglAhjgWmqssbModel mgawModel) {
	   DAO dao = DAO.getInstance();
	   ArrayList<String[]> rs = new ArrayList<String[]>();
	   String xn     = mgawModel.getXn();//学年
	   String xq     = mgawModel.getXq();//学期
	   String lddm   = mgawModel.getLddm();//楼栋代码
	   String qsh    = mgawModel.getQsh();//寝室号
	   String pysj   = mgawModel.getPysj();//评优时间
	   String yesNo  = mgawModel.getYesNo();//审核标志
	   String sql    =  "";
	   String[] colList = null;
	   StringBuffer querry = new StringBuffer();
	   //查询条件
	   querry.append(" where 1=1 ");
	   querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
	   querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
	   querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
	   querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
	   querry.append(Base.isNull(pysj)?"":" and pysj='"+pysj+"' ");
	   querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ");
	   querry.append(" and xysh='通过'");
	   querry.append(" order by pysj asc ");
	   colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh","xxsh"};//查询显示字段 
	   sql     = " select xn||xq||ssbh prkey,(case when xxsh='通过' then '#FFFFFF' else '#CCCCCC' end )bgcolor,"
		       +"  xn,xq,ldmc,qsh,pysj,xysh,xxsh from view_wmqssbxx ";  
//	   执行查询
	   rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
	   return rs;

   }
   /**
	 * @return 文明寝室审核查询的表头
	 */
	public ArrayList<HashMap<String, String>> getwmQsShTitle(String userType) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		// 必须与方法getwmQsSbXyShSearch中的输出表列一致
		if(userType.equalsIgnoreCase("xy")){//学院用户
			colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh"};//查询显示字段 
			colListCN = new String[]{ "主键", "背景色", "学年", "学期", "楼栋", "宿舍号", "评优时间","院系审核"};
		}else if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){ //学校或管理员用户
			colList = new String[]{"prkey", "bgcolor","xn","xq","ldmc","qsh","pysj","xysh","xxsh"};//查询显示字段 
			colListCN = new String[]{ "主键", "背景色", "学年", "学期", "楼栋", "宿舍号",  "评优时间","院系审核","学校审核"};

		}
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	public HashMap<String,String> wmSbOneInfo(String pkValue,String userType){
		DAO dao                    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk                  = "xn||xq||ssbh";
		String[] colList           = {"xn","xq","pysj","ssbh","bz","xm","yesNo","lddm","ldmc","qsh"};
		String sql                 = "";
		if(userType.equalsIgnoreCase("xy")){//不同级别用户返回的不同的审核状态
			sql = "select xn,xq,pysj,ssbh,bz,xm,xysh yesNo,lddm,ldmc,qsh from view_wmqssbxx where "+pk+"=? ";
		}else{
			sql = "select xn,xq,pysj,ssbh,bz,xm,xxsh yesNo,lddm,ldmc,qsh from view_wmqssbxx where "+pk+"=? ";
		}		              
	    map = dao.getMap(sql,new String[]{pkValue},colList);
		return map;	
	}
	/**返回该宿舍学生违纪处分信息*/
	public HashMap<String,String>wmQsWjInfo(String pkValue){
		DAO dao       = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pk     = "xn||xq||ssbh";
		String sql    = "select xn,xq,ssbh from wmqssbb where "+pk+" =?";
		String[] temA = dao.getOneRs(sql,new String[]{pkValue},new String[]{"xn","xq","ssbh"});
		if(temA != null){
			//该学年学期该宿舍有违纪记录的总人数
			sql = " select count(a.xh) wjcfrs from( select distinct(a.xh),b.ssbh from xszsxxb a,wmqssbb b where a.ssbh=b.ssbh  "
				+ " ) a,view_wjcf b where a.xh=b.xh and b.xn||b.xq||a.ssbh=?  ";
			String wjrs = dao.getOneRs(sql,new String[]{pkValue},"wjcfrs");
		    map.put("wjrs",wjrs);
		    //该宿舍目前入住人数
		    sql = " select count(*) rzrs from xszsxxb where ssbh=? ";
		    String rzrs = dao.getOneRs(sql,new String[]{temA[2]},"rzrs");
		    map.put("rzrs",rzrs);
		}
		return map;
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
			}else {//
				done = dao.runUpdate("delete from wmqspbb where "+pk+"=?",new String[]{pkValue});
			}
		}	
		return done;
	}
	/**返回本学年内文明寝室比例数及相关信息*/
	public HashMap<String,String> wmQsBlInf(){
		DAO dao    = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
	    String sql = "select (select count(*) from view_wmqspbxx d where d.xn=b.xn)ybqss,qszs ,wmqsbz||'%'  bz ,xn,"
				   +" to_char(qszs*wmqsbz/100,'99999999999999') yxqss from (select count(*) qszs from ssxxb ) a,gygl_csszb b where xn =? and xq=?";
		map = dao.getMap(sql, new String[]{Base.currXn,Base.currXq},new String[]{"xn","ybqss","qszs","bz","yxqss"});
		
		if(map.size()!=0){
			if(Integer.parseInt(map.get("ybqss").trim())-Integer.parseInt(map.get("yxqss").trim())>=0){
				map.put("sxbz","1");//"上限标志",文明寝室人数已达到或超过上限
			}else{
				map.put("sxbz","0");//文明寝室人数未达到上限
			}
			map.put("Set","yes");
		}else{
			map.put("Set","no");
		}
		return map;
	}
	
	
}
