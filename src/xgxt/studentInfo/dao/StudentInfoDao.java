
package xgxt.studentInfo.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentStatusModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

import common.Globals;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生信息模块DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class StudentInfoDao {
	DAO dao = DAO.getInstance();
	
	/**
	 * 检测记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String result = dao.getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result) >0 ? true : false;
	}
	
	/**
	 * 查询学生的基本信息是否在xsxxb中存在
	 * @param String xh
	 * @return boolean flag
	 * */
	public boolean isExistInXsxx(String xh){
		boolean flag = false;
		String sql = "select xh from xsxxb where xh=? ";
		xh = dao.getOneRs(sql, new String[]{xh}, "xh");
		flag = xh!=null && !"".equalsIgnoreCase(xh) ? true : false;
		return flag;
	}
	
	/**
	 * Method 将学生信息视图里的信息插入到xsxxb中
	 * @param String xh
	 * @param HttpServletRequest request
	 * @return boolean flag
	 * **/
	public boolean addStuInfoFromView(String xh,HttpServletRequest request) throws Exception{		
		boolean flag = false;
		String[] input = new String[]{xh};
		String sql = "insert into xsxxb(xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjztm,csrq,syd,sfzh,mz,zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny)" +
				     "(select xh,xm,xb,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjlb,csrq,syd,sfzh,mz,zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny from view_xsxxb where xh=?)";
		flag = dao.runUpdate(sql, input);
		
//		//写日志
//		if(flag){
//			DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),)
//			dmlLogger.setOldData("xsxxb","xh", xh,"update :");
//			new Thread(dmlLogger).start();
//				}
		
		return flag; 
	}
	
	/**
	 * Method 将学生信息视图里的信息插入到xsxxb中
	 * @param String xh
	 * @return boolean flag
	 * **/
	public boolean addStuInfoFromView(String xh) throws Exception{		
		boolean flag = false;
		String[] input = new String[]{xh};
		String sql = "insert into xsxxb(xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjztm,csrq,syd,sfzh,mz,zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny)" +
				     "(select xh,xm,xb,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjlb,csrq,syd,sfzh,mz,zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny from view_xsxxb where xh=?)";
		flag = dao.runUpdate(sql, input);
		
//		//写日志
//		if(flag){
//			DMLLogger dmlLogger = new DMLLogger(dao,sql.toString(),)
//			dmlLogger.setOldData("xsxxb","xh", xh,"update :");
//			new Thread(dmlLogger).start();
//				}
		
		return flag; 
	}
	
	/**
	 * Method modBaseXsxx 修改xsxxb中的基本数据
	 * @param String xh
	 * @param StudentStatusModel model
	 * @param HttpServletRequest reqeust
	 * @return boolean flag
	 * */
	public boolean modBaseXsxx(String xh,StudentStatusModel model,HttpServletRequest request){
		boolean flag = true;
		String xxdm = StandardOperation.getXxdm();
		String primaryKey = "xh";
		String pkValue = xh;
		String tableName = "xsxxb";
		String[] input = {"xydm","zydm","bjdm","nj","xz","xjztm"};
		String[] values = null; 
		
		String xydm = model.getYdhxydm();
		String zydm = model.getYdhzydm();
		String bjdm = model.getYdhbjdm();
		String nj = model.getYdhnj();
		String xz = model.getYdhxz();
		String xjztm = model.getYdhxjztm();
		@SuppressWarnings("unused")
		String sfzx = model.getSfzx();
		
		values = new String[]{xydm,zydm,bjdm,nj,xz,xjztm};
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			input = new String[]{"xydm","zydm","bjdm","nj","xz","xjztm","sfzx"};
			values = new String[]{xydm,zydm,bjdm,nj,xz,xjztm,sfzx};
		}
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZDX)){
//			//中州大学
//			input = new String[]{"xydm","zydm","bjdm","nj","xz","xjztm","sfzx"};
//			values = new String[]{xydm,zydm,bjdm,nj,xz,xjztm,sfzx};
//		}
		
		try {
			flag = StandardOperation.update(tableName, input, values, primaryKey, pkValue, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
		
	/**
	 * Method 查询单条记录的异动信息
	 * @param String pkValue0
	 * @return HashMap<String, String> map
	 * */
	public HashMap<String, String> getOneStatuDiffInfo(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xjydjbxx where xh||ydxh=?";
		String[] outputValue = { "ydxh", "xh", "clwh", "ydlbdm", "ydyy","ydsm", 
				                 "ydqxydm", "ydqxymc", "ydqzydm", "ydqzymc","xszt",
				                 "ydqbjdm", "ydqbjmc", "ydqnj", "ydqxz", "ydqxjztm", 
				                 "ydrq", "xm", "ydhxydm", "ydhxymc", "ydhzydm", 
				                 "ydhzymc", "ydhbjdm", "ydhbjmc", "ydhxz", "ydhxjztm",
				                 "ydhnj", "ydjzrq"};

		map = dao.getMap(sql, new String[]{pkValue}, outputValue);
		
		return map;
	}
	
	/**
	 * StudentStatusModel Method 增加学籍异动信息
	 * @param StudentStatusModel model
	 * @param HttpServletRequest request
	 * @return boolean flag
	 * */
	public boolean addStatuInfo(StudentStatusModel model,HttpServletRequest request){
		boolean flag = false;
		String xh = model.getXh();
		String ydlbm = model.getYdlbdm();
		String ydyy = model.getYdyy();
		String ydsm = model.getYdsm();
		String ydsj = model.getYdrq();
		String ydxh = model.getYdxh();
		String clwh = model.getClwh();
		
		String ydqxydm = model.getYdqxydm();
		String ydqzydm = model.getYdqzydm();
		String ydqbdm = model.getYdqbjdm();
		String ydqnj = model.getYdqnj();
		String ydqxz = model.getYdqxz();
		String ydqxjztm = model.getYdqxjztm();
		
		String ydhxydm = model.getYdhxydm();
		String ydhzydm = model.getYdhzydm();
		String ydhbdm = model.getYdhbjdm();
		String ydhnj = model.getYdhnj();
		String ydhxz = model.getYdhxz();
		String ydhxjztm = model.getYdhxjztm();
		String ydjzrq = model.getYdjzrq();
		
		
		String xszt = model.getXszt();
		
		String tableName = "bks_xjydxx";
		String[] columns = {"xh", "ydxh", "ydlbm", "clwh", "ydsm", "ydyy", "ydrq",
				            "ydqxydm", "ydqzydm", "ydqbdm", "ydqnj", "ydqxz", 
				            "ydqxjztm", "ydhxydm", "ydhzydm", "ydhbdm", "ydhnj",
				            "ydhxz", "ydhxjztm", "xszt", "ydjzrq"};
		String[] values = {xh, ydxh, ydlbm, clwh, ydsm, ydyy, ydsj, ydqxydm,
				           ydqzydm, ydqbdm, ydqnj, ydqxz, ydqxjztm, ydhxydm,
				           ydhzydm, ydhbdm, ydhnj, ydhxz, ydhxjztm, xszt, ydjzrq};
		flag = StandardOperation.insert(tableName, columns, values, request);
		
		return flag;		
	}
	
	/**
	 * Method addStatuDiffInfo 向用于异动提醒的表中插入记录
	 * @param StudentStatusModel model
	 * @param HttpServletRequest request
	 * @return boolean flag
	 * */
	public boolean addStatuDiffInfo(StudentStatusModel model,HttpServletRequest request){
		boolean flag = false;
		String sql = "";
		String tableName = "bks_stu_info_xjydb";
		
		String ydlbmc = "";
		String xh = model.getXh();
		String ydlbdm = model.getYdlbdm();
		String ydsj = model.getYdrq();
		
		//根据代码查询名称
		sql = "select ydlbmc from dm_ydlb where ydlbm=?";
		ydlbmc = dao.getOneRs(sql, new String[]{ydlbdm}, "ydlbmc");
		
		String[] columns = {"xh","ydlb","ydsj"};
		String[] values = {xh,ydlbmc,ydsj};
		
		flag = StandardOperation.insert(tableName, columns, values,request);		
		return flag;
	}
	
	/**
	 * Method modStatuInfo 修改学籍异动信息
	 * @param StudentStatusModel model
	 * @param HttpServletRequest request
	 * @return boolean flag
	 * */
	public boolean modStatuInfo(StudentStatusModel model,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "bks_xjydxx";
		String primaryKey = "xh||ydxh";
		String pkValue = "";
		
		String xh = model.getXh();
		String ydlbm = model.getYdlbdm();
		String ydyy = model.getYdyy();
		String ydsm = model.getYdsm();
		String ydsj = model.getYdrq();
		String ydxh = model.getYdxh();
		String clwh = model.getClwh();
		
		String ydqxydm = model.getYdqxydm();
		String ydqzydm = model.getYdqzydm();
		String ydqbdm = model.getYdqbjdm();
		String ydqnj = model.getYdqnj();
		String ydqxz = model.getYdqxz();
		String ydqxjztm = model.getYdqxjztm();
		
		String ydhxydm = model.getYdhxydm();
		String ydhzydm = model.getYdhzydm();
		String ydhbdm = model.getYdhbjdm();
		String ydhnj = model.getYdhnj();
		String ydhxz = model.getYdhxz();
		String ydhxjztm = model.getYdhxjztm();
		String ydjzrq = model.getYdjzrq();
		
		String xszt = model.getXszt();
		pkValue = xh+ydxh;
		String[] columns = {"xh", "ydxh", "ydlbm", "clwh", "ydsm", "ydyy", "ydrq",
				            "ydqxydm", "ydqzydm", "ydqbdm", "ydqnj", "ydqxz", 
				            "ydqxjztm", "ydhxydm", "ydhzydm", "ydhbdm", "ydhnj",
				            "ydhxz", "ydhxjztm", "xszt", "ydjzrq"};
		String[] values = {xh, ydxh, ydlbm, clwh, ydsm, ydyy, ydsj, ydqxydm, 
				           ydqzydm, ydqbdm, ydqnj, ydqxz, ydqxjztm, ydhxydm,
				           ydhzydm, ydhbdm, ydhnj, ydhxz, ydhxjztm, xszt, ydjzrq};		
		
		flag = StandardOperation.update(tableName, columns, values, primaryKey, pkValue,request);		
		return flag;
	}
	
	/**
	 * Method modStatuDiffInfo 修改学籍异动提醒信息
	 * @param StudentStatusModel model
	 * @param HttpServletRequest request
	 * @return boolean flag
	 * */
	public boolean modStatuDiffInfo(StudentStatusModel model,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String sql = "";
		String tableName = "bks_stu_info_xjydb";
		String primaryKey = "xh";
		String pkValue = "";
		
		String xh = model.getXh();
		String ydlbm = model.getYdlbdm();
		String ydsj = model.getYdrq();
		
		pkValue = xh;
		//根据代码查询名称
		sql = "select ydlbmc from dm_ydlb where ydlbm=?";
		String ydlbmc = dao.getOneRs(sql, new String[]{ydlbm}, "ydlbmc");
		
		String[] columns = {"xh","ydlb","ydsj"};
		String[] values = {xh,ydlbmc,ydsj};		
		
		flag = StandardOperation.update(tableName, columns, values, primaryKey, pkValue,request);		
		return flag;
	}
	
	/**
	 * Method getGraduateInfo 获取毕业信息
	 * @return List
	 * */
	public List getGraduateInfo(){
		List list = null;
		String sql = "select count(*)count,bjdm from view_xsxxb where sfyby='是' and not exists(select bjdm from bks_bybjdmb) group by bjdm";
		list = dao.getList(sql, new String[]{}, new String[]{"count","bjdm"});
		return list;
	}
	
	/**
	 * Method getCountByBjdm 根据班级代码获取总人数
	 * @param bjdm
	 * @return int
	 * */
	public int getCountByBjdm(String bjdm){
		int iCount = 0;
		String sql = "select count(*) count from view_xsjbxx where bjdm=?";
		iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{bjdm},"count"));
		return iCount;
	}
	
	/**
	 * Method delAndAdd 班级插入到毕业班级中
	 * @param bjdm
	 * @return boolean
	 * */
	public boolean addBybjInfo(String bjdm,HttpServletRequest request){
		boolean flag  = false;
		String sql= "insert into bks_bybjdmb(bjdm,bjmc,zydm,bmdm,nj)(select bjdm,bjmc,zydm,xydm,nj from view_njxyzybj where bjdm='" + bjdm + "')";
		try {
			//将班级信息插入到毕业班级信息表中
			flag = StandardOperation.update("bks_bybjdmb", sql, request);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * Method getBybjList 获取毕业班级信息
	 * @param condition
	 * @return List
	 * */
	public List getBybjList(String condition,String type){
		List bjList = null;
		StringBuffer sb = new StringBuffer();
		String zydm = "";
		String bmdm = "";
		String nj = "";
		String[] info = condition.split("!!");
		if(info!=null && info.length == 3){
			bmdm = info[0];
			zydm = info[1];
			nj = info[2];
		}
		
		sb.append(bmdm==null || "".equalsIgnoreCase(bmdm)? "" : " and xydm='" + bmdm + "'");
		sb.append(zydm==null || "".equalsIgnoreCase(zydm) ? "" : " and zydm ='" + zydm + "'");
		sb.append(nj==null || "".equalsIgnoreCase(nj) ? "" : " and nj='" + nj + "'");
		if(type!=null && type.equalsIgnoreCase("zx")){
			sb.append(" and exists(select 1 from view_xsxxb b where a.bjdm=b.bjdm and (b.sfyby<>'是' or b.sfyby is null))");
		}
		if(type!=null && type.equalsIgnoreCase("by")){
			sb.append(" and exists(select 1 from view_xsxxb b where a.bjdm=b.bjdm and b.sfyby='是')");
		}
		bjList = dao.getList("select distinct bjdm,bjmc from view_njxyzybj a where 1=1 " + sb.toString() + " order by bjdm", new String[]{}, new String[]{"bjdm","bjmc"});
		return bjList;
	}
	
	/**
	 * 获取最大异动序号
	 * @param xh
	 * @return String
	 * */
	public String getMaxYdxh(){
		String _sql = "select max(ydxh)+1 ydxh from bks_xjydxx";
		String ydxh = dao.getOneRs(_sql, new String[]{}, "ydxh");
		ydxh = (ydxh==null || "".equalsIgnoreCase(ydxh)) ? "1" : ydxh;
		return ydxh;
	}
	
	/**
	 * 获取学生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh, a.xm, b.cym, a.xb, b.csrq, a.xymc, a.bjmc, a.sfzh,"
				+ " b.zkzh, b.mzmc, b.yzbm, b.jtdz, b.jtdh, a.lxdh from view_xsjbxx a,"
				+ " view_xsxxb b where a.xh = b.xh and a.xh = ?";
		String[] colList = new String[] { "xh", "xm", "cym", "xb", "csrq",
				"xymc", "bjmc", "sfzh", "zkzh", "mzmc", "yzbm", "jtdz", "jtdh",
				"lxdh" };
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	/**
	 * 获取入学日期
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String getRxrq(String pkVal) throws Exception {
		String sql = "select t.rxrq from view_xsxxb t where t.xh = ?";
		String rxrq = dao.getOneRs(sql, new String[] { pkVal }, "rxrq");
		return rxrq;
	}
	
	/**
	 * 是否提交新生登记
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String getNewXs(String pkVal) throws Exception {
		String sql = "select count(t.xh) count from jsxx_xsqkdjb t where t.xh = ?";
		String rxrq = dao.getOneRs(sql, new String[] { pkVal }, "count");
		return rxrq;
	}
	
	/**
	 * 获取新生信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getNewStu(String pkVal) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select a.xh, a.xm, b.cym, a.xb, b.csrq, a.xymc, a.bjmc, a.sfzh,"
				+ " b.zkzh, b.mzmc, b.yzbm, b.jtdz, b.jtdh, a.lxdh ,c.rxcj, c.rdsj, c.rddd,"
				+ " c.rdjsr, c.grahtc, c.grjlkssj1, c.grjlkssj2, c.grjlkssj3, c.grjljssj1, c.grjljssj2, c.grjljssj3,"
				+ " c.grjlnr1, c.grjlnr2, c.grjlnr3, c.grjlzw1, c.grjlzw2, c.grjlzw3, c.jtcych1, c.jtcych2, c.jtcych3,"
				+ " c.jtcych4, c.jtcyxm1, c.jtcyxm2, c.jtcyxm3, c.jtcyxm4, c.jtcynl1, c.jtcynl2, c.jtcynl3, c.jtcynl4,"
				+ " c.jtcyzzmm1, c.jtcyzzmm2, c.jtcyzzmm3, c.jtcyzzmm4, c.jtcydw1, c.jtcydw2, c.jtcydw3, c.jtcydw4,"
				+ " c.jtcysr1, c.jtcysr2, c.jtcysr3,c.jtcysr4,c.jtcydh1,c.jtcydh2,c.jtcydh3,c.jtcydh4,c.shgxch1,c.shgxch2,"
				+ " c.shgxch3,c.shgxch4,c.shgxxm1,c.shgxxm2,c.shgxxm3,c.shgxxm4,c.shgxnl1,c.shgxnl2,c.shgxnl3,c.shgxnl4,"
				+ " c.shgxzzmm1,c.shgxzzmm2,c.shgxzzmm3,c.shgxzzmm4,c.shgxdw1,c.shgxdw2,c.shgxdw3,c.shgxdw4,c.shgxsr1,"
				+ " c.shgxsr2,c.shgxsr3,c.shgxsr4,c.shgxdh1,c.shgxdh2,c.shgxdh3,c.shgxdh4,c.bz,c.txrq from view_xsjbxx a,"
				+ " view_xsxxb b,jsxx_xsqkdjb c where a.xh = b.xh and b.xh=c.xh and a.xh = ?";
		String[] colList = new String[] { "txrq","xh", "xm", "cym", "xb", "csrq",
				"xymc", "bjmc", "sfzh", "zkzh", "mzmc", "yzbm", "jtdz", "jtdh",
				"lxdh", "xh", "rxcj", "rdsj", "rddd", "rdjsr", "grahtc",
				"grjlkssj1", "grjlkssj2", "grjlkssj3", "grjljssj1",
				"grjljssj2", "grjljssj3", "grjlnr1", "grjlnr2", "grjlnr3",
				"grjlzw1", "grjlzw2", "grjlzw3", "jtcych1", "jtcych2",
				"jtcych3", "jtcych4", "jtcyxm1", "jtcyxm2", "jtcyxm3",
				"jtcyxm4", "jtcynl1", "jtcynl2", "jtcynl3", "jtcynl4",
				"jtcyzzmm1", "jtcyzzmm2", "jtcyzzmm3", "jtcyzzmm4", "jtcydw1",
				"jtcydw2", "jtcydw3", "jtcydw4", "jtcysr1", "jtcysr2",
				"jtcysr3", "jtcysr4", "jtcydh1", "jtcydh2", "jtcydh3",
				"jtcydh4", "shgxch1", "shgxch2", "shgxch3", "shgxch4",
				"shgxxm1", "shgxxm2", "shgxxm3", "shgxxm4", "shgxnl1",
				"shgxnl2", "shgxnl3", "shgxnl4", "shgxzzmm1", "shgxzzmm2",
				"shgxzzmm3", "shgxzzmm4", "shgxdw1", "shgxdw2", "shgxdw3",
				"shgxdw4", "shgxsr1", "shgxsr2", "shgxsr3", "shgxsr4",
				"shgxdh1", "shgxdh2", "shgxdh3", "shgxdh4", "bz"};
		String[] sLen = dao.getOneRs(sql, new String[] { pkVal }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	/**
	 * 保存新生情况登记
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean jsxxXsdjbSave(String pkVal, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jsxx_xsqkdjb", "xh", pkVal,
				request);
		String rxcj = DealString.toGBK(request.getParameter("rxcj"));
		String rdsj = DealString.toGBK(request.getParameter("rdsj"));
		String rddd = DealString.toGBK(request.getParameter("rddd"));
		String rdjsr = DealString.toGBK(request.getParameter("rdjsr"));
		String grahtc = DealString.toGBK(request.getParameter("grahtc"));
		String grjlkssj1 = DealString.toGBK(request.getParameter("grjlkssj1"));
		String grjlkssj2 = DealString.toGBK(request.getParameter("grjlkssj2"));
		String grjlkssj3 = DealString.toGBK(request.getParameter("grjlkssj3"));
		String grjljssj1 = DealString.toGBK(request.getParameter("grjljssj1"));
		String grjljssj2 = DealString.toGBK(request.getParameter("grjljssj2"));
		String grjljssj3 = DealString.toGBK(request.getParameter("grjljssj3"));
		String grjlnr1 = DealString.toGBK(request.getParameter("grjlnr1"));
		String grjlnr2 = DealString.toGBK(request.getParameter("grjlnr2"));
		String grjlnr3 = DealString.toGBK(request.getParameter("grjlnr3"));
		String grjlzw1 = DealString.toGBK(request.getParameter("grjlzw1"));
		String grjlzw2 = DealString.toGBK(request.getParameter("grjlzw2"));
		String grjlzw3 = DealString.toGBK(request.getParameter("grjlzw3"));
		String jtcych1 = DealString.toGBK(request.getParameter("jtcych1"));
		String jtcych2 = DealString.toGBK(request.getParameter("jtcych2"));
		String jtcych3 = DealString.toGBK(request.getParameter("jtcych3"));
		String jtcych4 = DealString.toGBK(request.getParameter("jtcych4"));
		String jtcyxm1 = DealString.toGBK(request.getParameter("jtcyxm1"));
		String jtcyxm2 = DealString.toGBK(request.getParameter("jtcyxm2"));
		String jtcyxm3 = DealString.toGBK(request.getParameter("jtcyxm3"));
		String jtcyxm4 = DealString.toGBK(request.getParameter("jtcyxm4"));
		String jtcynl1 = DealString.toGBK(request.getParameter("jtcynl1"));
		String jtcynl2 = DealString.toGBK(request.getParameter("jtcynl2"));
		String jtcynl3 = DealString.toGBK(request.getParameter("jtcynl3"));
		String jtcynl4 = DealString.toGBK(request.getParameter("jtcynl4"));
		String jtcyzzmm1 = DealString.toGBK(request.getParameter("jtcyzzmm1"));
		String jtcyzzmm2 = DealString.toGBK(request.getParameter("jtcyzzmm2"));
		String jtcyzzmm3 = DealString.toGBK(request.getParameter("jtcyzzmm3"));
		String jtcyzzmm4 = DealString.toGBK(request.getParameter("jtcyzzmm4"));
		String jtcydw1 = DealString.toGBK(request.getParameter("jtcydw1"));
		String jtcydw2 = DealString.toGBK(request.getParameter("jtcydw2"));
		String jtcydw3 = DealString.toGBK(request.getParameter("jtcydw3"));
		String jtcydw4 = DealString.toGBK(request.getParameter("jtcydw4"));
		String jtcysr1 = DealString.toGBK(request.getParameter("jtcysr1"));
		String jtcysr2 = DealString.toGBK(request.getParameter("jtcysr2"));
		String jtcysr3 = DealString.toGBK(request.getParameter("jtcysr3"));
		String jtcysr4 = DealString.toGBK(request.getParameter("jtcysr4"));
		String jtcydh1 = DealString.toGBK(request.getParameter("jtcydh1"));
		String jtcydh2 = DealString.toGBK(request.getParameter("jtcydh2"));
		String jtcydh3 = DealString.toGBK(request.getParameter("jtcydh3"));
		String jtcydh4 = DealString.toGBK(request.getParameter("jtcydh4"));
		String shgxch1 = DealString.toGBK(request.getParameter("shgxch1"));
		String shgxch2 = DealString.toGBK(request.getParameter("shgxch2"));
		String shgxch3 = DealString.toGBK(request.getParameter("shgxch3"));
		String shgxch4 = DealString.toGBK(request.getParameter("shgxch4"));
		String shgxxm1 = DealString.toGBK(request.getParameter("shgxxm1"));
		String shgxxm2 = DealString.toGBK(request.getParameter("shgxxm2"));
		String shgxxm3 = DealString.toGBK(request.getParameter("shgxxm3"));
		String shgxxm4 = DealString.toGBK(request.getParameter("shgxxm4"));
		String shgxnl1 = DealString.toGBK(request.getParameter("shgxnl1"));
		String shgxnl2 = DealString.toGBK(request.getParameter("shgxnl2"));
		String shgxnl3 = DealString.toGBK(request.getParameter("shgxnl3"));
		String shgxnl4 = DealString.toGBK(request.getParameter("shgxnl4"));
		String shgxzzmm1 = DealString.toGBK(request.getParameter("shgxzzmm1"));
		String shgxzzmm2 = DealString.toGBK(request.getParameter("shgxzzmm2"));
		String shgxzzmm3 = DealString.toGBK(request.getParameter("shgxzzmm3"));
		String shgxzzmm4 = DealString.toGBK(request.getParameter("shgxzzmm4"));
		String shgxdw1 = DealString.toGBK(request.getParameter("shgxdw1"));
		String shgxdw2 = DealString.toGBK(request.getParameter("shgxdw2"));
		String shgxdw3 = DealString.toGBK(request.getParameter("shgxdw3"));
		String shgxdw4 = DealString.toGBK(request.getParameter("shgxdw4"));
		String shgxsr1 = DealString.toGBK(request.getParameter("shgxsr1"));
		String shgxsr2 = DealString.toGBK(request.getParameter("shgxsr2"));
		String shgxsr3 = DealString.toGBK(request.getParameter("shgxsr3"));
		String shgxsr4 = DealString.toGBK(request.getParameter("shgxsr4"));
		String shgxdh1 = DealString.toGBK(request.getParameter("shgxdh1"));
		String shgxdh2 = DealString.toGBK(request.getParameter("shgxdh2"));
		String shgxdh3 = DealString.toGBK(request.getParameter("shgxdh3"));
		String shgxdh4 = DealString.toGBK(request.getParameter("shgxdh4"));
		String bz = DealString.toGBK(request.getParameter("bz"));

		String[] columns = { "xh", "rxcj", "rdsj", "rddd", "rdjsr", "grahtc",
				"grjlkssj1", "grjlkssj2", "grjlkssj3", "grjljssj1",
				"grjljssj2", "grjljssj3", "grjlnr1", "grjlnr2", "grjlnr3",
				"grjlzw1", "grjlzw2", "grjlzw3", "jtcych1", "jtcych2",
				"jtcych3", "jtcych4", "jtcyxm1", "jtcyxm2", "jtcyxm3",
				"jtcyxm4", "jtcynl1", "jtcynl2", "jtcynl3", "jtcynl4",
				"jtcyzzmm1", "jtcyzzmm2", "jtcyzzmm3", "jtcyzzmm4", "jtcydw1",
				"jtcydw2", "jtcydw3", "jtcydw4", "jtcysr1", "jtcysr2",
				"jtcysr3", "jtcysr4", "jtcydh1", "jtcydh2", "jtcydh3",
				"jtcydh4", "shgxch1", "shgxch2", "shgxch3", "shgxch4",
				"shgxxm1", "shgxxm2", "shgxxm3", "shgxxm4", "shgxnl1",
				"shgxnl2", "shgxnl3", "shgxnl4", "shgxzzmm1", "shgxzzmm2",
				"shgxzzmm3", "shgxzzmm4", "shgxdw1", "shgxdw2", "shgxdw3",
				"shgxdw4", "shgxsr1", "shgxsr2", "shgxsr3", "shgxsr4",
				"shgxdh1", "shgxdh2", "shgxdh3", "shgxdh4", "bz" };

		String[] values = { pkVal, rxcj, rdsj, rddd, rdjsr, grahtc, grjlkssj1,
				grjlkssj2, grjlkssj3, grjljssj1, grjljssj2, grjljssj3, grjlnr1,
				grjlnr2, grjlnr3, grjlzw1, grjlzw2, grjlzw3, jtcych1, jtcych2,
				jtcych3, jtcych4, jtcyxm1, jtcyxm2, jtcyxm3, jtcyxm4, jtcynl1,
				jtcynl2, jtcynl3, jtcynl4, jtcyzzmm1, jtcyzzmm2, jtcyzzmm3,
				jtcyzzmm4, jtcydw1, jtcydw2, jtcydw3, jtcydw4, jtcysr1,
				jtcysr2, jtcysr3, jtcysr4, jtcydh1, jtcydh2, jtcydh3, jtcydh4,
				shgxch1, shgxch2, shgxch3, shgxch4, shgxxm1, shgxxm2, shgxxm3,
				shgxxm4, shgxnl1, shgxnl2, shgxnl3, shgxnl4, shgxzzmm1,
				shgxzzmm2, shgxzzmm3, shgxzzmm4, shgxdw1, shgxdw2, shgxdw3,
				shgxdw4, shgxsr1, shgxsr2, shgxsr3, shgxsr4, shgxdh1, shgxdh2,
				shgxdh3, shgxdh4, bz };
		if (flg) {
			flg = StandardOperation.insert("jsxx_xsqkdjb", columns, values,
					request);
		}

		return true;
	}
	
	/**
	 * 获取新生登记列表
	 * @author luojw
	 */

	public ArrayList<String[]> getNewStuList(String tableName, CommanForm form,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, form);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", form);
	}
	
	/**
	 * 获得新生综合素质记载
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getNewStuInfo(String xh) {
		String[] colList = new String[] { "xh", "rxnj", "yydcj", "yyzcj",
				"yytcj", "yykpf", "yybjpm", "yrdcj", "yrzcj", "yrtcj", "yrkpf",
				"yrbjpm", "rydcj", "ryzcj", "rytcj", "rykpf", "rybjpm",
				"rrdcj", "rrzcj", "rrtcj", "rrkpf", "rrbjpm", "sydcj", "syzcj",
				"sytcj", "sykpf", "sybjpm", "srdcj", "srzcj", "srtcj", "srkpf",
				"srbjpm", "xydcj", "xyzcj", "xytcj", "xykpf", "xybjpm",
				"xrdcj", "xrzcj", "xrtcj", "xrkpf", "xrbjpm", "fydcj", "fyzcj",
				"fytcj", "fykpf", "fybjpm", "frdcj", "frzcj", "frtcj", "frkpf",
				"frbjpm", "yqk", "rqk", "sqk", "xqk", "fqk" };
		return CommonQueryDAO.commonQueryOne("view_jsxx_xslnqkdj", colList,
				"xh", xh);
	}
	
	/**
	 * 获得新生综合素质记载
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public boolean saveNewStuInfo(CommanForm myForm) throws SQLException {
		
		boolean flg = false;
		
		String xh = myForm.getXh();
		String yqk = myForm.getYqk();
		String rqk = myForm.getRqk();
		String sqk = myForm.getSqk();
		String xqk = myForm.getXqk();
		String fqk = myForm.getFqk();
		yqk = !Base.isNull(yqk) ? yqk.trim() : yqk;
		rqk = !Base.isNull(rqk) ? rqk.trim() : rqk;
		sqk = !Base.isNull(sqk) ? sqk.trim() : sqk;
		xqk = !Base.isNull(xqk) ? xqk.trim() : xqk;
		fqk = !Base.isNull(fqk) ? fqk.trim() : fqk;
		String rxnj = myForm.getRxnj();
		String yxn = rxnj + "-" + (Integer.parseInt(rxnj) + 1);
		String rxn = (Integer.parseInt(rxnj) + 1) + "-" + (Integer.parseInt(rxnj) + 2);
		String sxn = (Integer.parseInt(rxnj) + 2) + "-" + (Integer.parseInt(rxnj) + 3);
		String xxn = (Integer.parseInt(rxnj) + 3) + "-" + (Integer.parseInt(rxnj) + 4);
		String fxn = (Integer.parseInt(rxnj) + 4) + "-" + (Integer.parseInt(rxnj) + 5);
		
		StringBuffer sql = new StringBuffer();

		sql.append("delete from  jsxx_xsqkjzb where xh||xn = '" + xh + yxn + "'");
		sql.append("!!#!!");
		sql.append("insert into jsxx_xsqkjzb(xh,xn,qk) values('" + xh + "','" + yxn + "','" + yqk + "')");
		sql.append("!!#!!");

		sql.append("delete from  jsxx_xsqkjzb where xh||xn = '" + xh + rxn + "'");
		sql.append("!!#!!");
		sql.append("insert into jsxx_xsqkjzb(xh,xn,qk) values('" + xh + "','" + rxn + "','" + rqk + "')");
		sql.append("!!#!!");

		sql.append("delete from  jsxx_xsqkjzb where xh||xn = '" + xh + sxn + "'");
		sql.append("!!#!!");
		sql.append("insert into jsxx_xsqkjzb(xh,xn,qk) values('" + xh + "','" + sxn + "','" + sqk + "')");
		sql.append("!!#!!");

		sql.append("delete from  jsxx_xsqkjzb where xh||xn = '" + xh + xxn + "'");
		sql.append("!!#!!");
		sql.append("insert into jsxx_xsqkjzb(xh,xn,qk) values('" + xh + "','" + xxn + "','" + xqk + "')");
		sql.append("!!#!!");

		sql.append("delete from  jsxx_xsqkjzb where xh||xn = '" + xh + fxn + "'");
		sql.append("!!#!!");
		sql.append("insert into jsxx_xsqkjzb(xh,xn,qk) values('" + xh + "','" + fxn + "','" + fqk + "')");
		sql.append("!!#!!");
		
		
		String[] actsql = sql.toString().split("!!#!!");
		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/**
	 * 删除新生情况登记
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean delNewStuInfo(String[] key)
			throws Exception {

		boolean flg = false;
		if(key!=null&&key.length>0){
			String[] sql = new String[key.length * 2];
			int n=0;
			for(int i=0;i<key.length;i++){
				sql[n] = "delete from jsxx_xsqkdjb where xh= '" + key[i] + "'";
				n++;
				sql[n] = "delete from jsxx_xsqkjzb where xh= '" + key[i] + "'";
				n++;
			}
			int[] res = dao.runBatch(sql);
			for (int i = 0; i < res.length; i++) {
				flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flg)
					break;
			}
		}
		return flg;
	}
	
	public List<Object> getGdzlData(String xydm,String nj){
		DAO dao = DAO.getInstance();
		String sql = "select gdzldm,zlbmc from stu_gdzlb";
		List<HashMap<String , String >> rs = dao.getList(sql, new String[]{}, new String[]{"gdzldm","zlbmc"});
		List<String> top = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		List<String> output = new ArrayList<String>();
		sb.append("select  a.xh,b.xm,");
		output.add("xh");
		output.add("xm");
		for(Map map : rs){
			sb.append("CASE WHEN a.a");
			sb.append(map.get("gdzldm"));
			sb.append(" = 1 then '√' else '○' end  a");
			sb.append(map.get("gdzldm"));
			sb.append(",");
			top.add(map.get("zlbmc").toString());
			output.add("a"+map.get("gdzldm"));
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ,case  when sftj is null then '未提交' ");
		sb.append(" else  sftj  end sftj,");
		sb.append(" case  when dazxqk is null then  '未设置' ");
		sb.append(" else dazxqk  end dazxqk ");
		sb.append(" from (select  xh,");
		for(Map map : rs){
			sb.append("sum(a");
			sb.append(map.get("gdzldm"));
			sb.append(")  a");
			sb.append(map.get("gdzldm"));
			sb.append(" ,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ,sftj,dazxqk ");
		sb.append("  from ( select xh ,");
		for(Map map : rs){
			sb.append(" case when  a.gdzldm = '");
			sb.append(map.get("gdzldm"));
			
			if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
				sb.append("' and a.sftj = '已提交' then '1' else '0' end  a");
			}else{
				sb.append("' and a.sftj = '已经提交' then '1' else '0' end  a");
			}
			sb.append(map.get("gdzldm"));
			sb.append(" ,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ,sftj,dazxqk ");
		sb.append("  FROM (select xh,gdzldm,sftj,dazxqk from gdzltjb union select xh,'' gdzldm, '' sftj,''dazxqk from "+Base.xsxxb+" a where not exists  (select 1 from gdzltjb b where a.xh=b.xh) ) A  ) group by xh,sftj,dazxqk ) a left join "+Base.xsxxb+" b on a.xh=b.xh");
		if(null != xydm && !"".equals(xydm)){
			sb.append(" where b.xydm ='"+xydm+"'");
			sb.append(" and b.nj='"+nj+"'");
		}
		
		if(Globals.XXDM_HBJJXY.equalsIgnoreCase(Base.xxdm)){
			output.add("sftj");
			output.add("dazxqk");
		}
		
		List<String[]> rs2 = dao.rsToVator(sb.toString(), new String[]{}, output.toArray(new String[]{}));
		
		String xymc = dao.getList("select xymc from view_njxyzybj where xydm = ?", new String[]{xydm}, new String[]{"xymc"}).get(0).get("xymc");
		List<Object> o = new ArrayList<Object>();
		o.add(top);
		o.add(rs2);
		o.add(xymc);
		
		return o;
	}
	
	public List<String[]> getDrzdList(String tableName){
		String sql = "select zdmc from dcb where zdssb||xxdm=?";
		return dao.rsToVator(sql, new String[]{tableName+Base.xxdm}, new String[]{"zdmc"});
	}
	
	/**
	 * 获取身份证号校验信息
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> getSfzhJyInfoList(CommanForm model) throws Exception{
		//高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		
		String tableName="select  rownum r,a.* from ( " +
							" select a.*, " +
							" (case when xx_jg=sfzh_jg then '否' else '是' end) yczt_jg, " +
							" (case when xx_csrq=sfzh_csrq then '否' else '是' end) yczt_csrq, " +
							" (case when xx_xb=sfzh_xb then '否' else '是' end) yczt_xb, " +
							" (select sydq from dmk_sydq x where x.sydqdm=(substr(a.xx_jg,1,2)||'0000'))|| " +
							" (select qxmc from dmk_qx x where x.qxdm=(substr(a.xx_jg,1,4)||'00'))|| " +
							" (select qxmc from dmk_qx x where x.qxdm=a.xx_jg) xx_jgmc, " +
							" (select sydq from dmk_sydq x where x.sydqdm=(substr(a.sfzh_jg,1,2)||'0000'))|| " +
							" (select qxmc from dmk_qx x where x.qxdm=(substr(a.sfzh_jg,1,4)||'00'))|| " +
							" (select qxmc from dmk_qx x where x.qxdm=a.sfzh_jg) sfzh_jgmc " +
							" from ( " +
							
							" select xh,xm,nj,xydm,zydm,bjdm,sfzh,jg,csrq,xb, " +
							" substr(jg,15,6) xx_jg, " +
							" replace(replace(csrq,'-',''),'/','') xx_csrq, " +
							" (case when xb='1' then '男' when xb='2' then '女' else xb end) xx_xb, " +
							" substr(sfzh,1,6) sfzh_jg, " +
							" (case  " +
							" when length(sfzh)=15 then '19'||substr(sfzh,7,6) " + 
							" when length(sfzh)=18 then  substr(sfzh,7,8) " +
							" end) sfzh_csrq, " +
							" (case  " +
							" when length(sfzh)=15 then " + 
							" case when mod(substr(sfzh,15,1),2)=1 then '男' else '女' end " +  
							" when length(sfzh)=18 then  " +
							" case when mod(substr(sfzh,17,1),2)=1 then '男' else '女' end " +  
							" end) sfzh_xb " +
							" from view_xsxxb where sfzh is not null " +
//							" and nvl(substr(sfzh,15,1),'0')>='0' and nvl(substr(sfzh,15,1),'0')<='9' " +
//							" and nvl(substr(sfzh,17,1),'0')>='0' and nvl(substr(sfzh,17,1),'0')<='9' " +
							"and REGEXP_LIKE(nvl(substr(sfzh, 15, 1), '0'),'^[:punct:]|[0-9]+$') "+ 
							"and REGEXP_LIKE(nvl(substr(sfzh, 17, 1), '0'),'^[:punct:]|[0-9]+$') "+
							
							" ) a where nvl(xx_jg,'x')<>nvl(sfzh_jg,'s') or " +
							" nvl(xx_csrq,'x')<>nvl(sfzh_csrq,'s') or  " +
							" nvl(xx_xb,'x')<>nvl(sfzh_xb,'s')" +
							" ) a ";
		String[] outPutV = new String[]{"xh","xm","nj","sfzh","xx_xb","sfzh_xb","xx_csrq","sfzh_csrq","xx_jgmc","sfzh_jgmc",
				"yczt_xb","yczt_csrq","yczt_jg"};
		return CommonQueryDAO.commonQuery(tableName," where 1=1 "+searchTj,inputV,outPutV,model);
	}
	
	/**
	 * 更新身份证号校验信息
	 * @param updateType
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateSfzhJyInfo(String[] updateType,CommanForm model,HttpServletRequest request) throws Exception{
		boolean b=true;
		String[] xh=model.getCheckVal();
		if(updateType==null||updateType.length==0||xh==null||xh.length==0){
			return b;
		}
		//判断bks_xsjbxx是否是表,只有是表的情况是才进行该表的更新
		String bks_xsjbxx_isTable_sql="select count(1) num from user_tables where table_name='BKS_XSJBXX'";
		String num=new DAO().getOneRs(bks_xsjbxx_isTable_sql, new String[]{}, "num");
		boolean bks_xsjbxx_isTable="0".equals(num)?false:true;
		
		ArrayList<String> sql=new ArrayList<String>();
		String xhStr=getPkStr(xh);
		
		Bks_xsjbxxToXsxxb(xhStr,null);
		
		for(int i=0;i<updateType.length;i++){
			if("xb".equals(updateType[i])){//更新性别
				sql.add("update xsxxb a set xb=(select (case when  mod(substr(sfzh,15,1),2)=1 then '男' else '女' end) " +
						"from view_xsxxb b where a.xh=b.xh) where xh in("+xhStr+") and length(sfzh)=15");
				sql.add("update xsxxb a set xb=(select (case when  mod(substr(sfzh,17,1),2)=1 then '男' else '女' end) " +
						"from view_xsxxb b where a.xh=b.xh) where xh in("+xhStr+") and length(sfzh)=18");
				if(bks_xsjbxx_isTable){
					sql.add("update bks_xsjbxx a set xb=(select (case when  mod(substr(sfzh,15,1),2)=1 then '男' else '女' end) " +
							"from view_xsxxb b where a.xh=b.xh) where xh in("+xhStr+") and length(sfzh)=15 and " +
							"not exists (select 1 from xsxxb c where a.xh=c.xh) ");
					sql.add("update bks_xsjbxx a set xb=(select (case when  mod(substr(sfzh,17,1),2)=1 then '男' else '女' end) " +
							"from view_xsxxb b where a.xh=b.xh) where xh in("+xhStr+") and length(sfzh)=18 and " +
							"not exists (select 1 from xsxxb c where a.xh=c.xh)");
				}
			}else if("csrq".equals(updateType[i])){//更新出生日期
				sql.add("update xsxxb a set csrq=(select '19'||substr(sfzh,7,6) from view_xsxxb b where a.xh=b.xh) " +
						" where xh in ("+xhStr+") and length(sfzh)=15");
				sql.add("update xsxxb a set csrq=(select substr(sfzh,7,8) from view_xsxxb b where a.xh=b.xh) " +
						" where xh in ("+xhStr+") and length(sfzh)=18");
				if(bks_xsjbxx_isTable){
					sql.add("update bks_xsjbxx a set csrq=(select '19'||substr(sfzh,7,6) from view_xsxxb b where a.xh=b.xh) " +
							" where xh in ("+xhStr+") and length(sfzh)=15 and " +
							"not exists (select 1 from xsxxb c where a.xh=c.xh)");
					sql.add("update bks_xsjbxx a set csrq=(select substr(sfzh,7,8) from view_xsxxb b where a.xh=b.xh) " +
							" where xh in ("+xhStr+") and length(sfzh)=18 and " +
							"not exists (select 1 from xsxxb c where a.xh=c.xh)");
				}
			}else if("jg".equals(updateType[i])){//更新籍贯
				sql.add("update xsxxb a set jg=(select substr(sfzh,1,2)||'0000/'||substr(sfzh,1,4)||'00/'||substr(sfzh,1,6) " +
						"from view_xsxxb b where a.xh=b.xh) where xh in("+xhStr+") and (length(sfzh)=15 or length(sfzh)=18)");
				if(bks_xsjbxx_isTable){
					sql.add("update bks_xsjbxx a set jg=(select substr(sfzh,1,2)||'0000/'||substr(sfzh,1,4)||'00/'||substr(sfzh,1,6) " +
							"from view_xsxxb b where a.xh=b.xh) where xh in("+xhStr+") and (length(sfzh)=15 or length(sfzh)=18) and " +
							"not exists (select 1 from xsxxb c where a.xh=c.xh)");
				}
			}
		}
		if(sql.size()>0){
			CommDAO dao=new CommDAO();
			b=dao.saveArrDate(sql.toArray(new String[]{}));
		}
		return b;
	}
	
	public String getPkStr(String[] xh){
		if(xh==null||xh.length==0){
			return null;
		}
		StringBuffer xhstr=new StringBuffer();
		for(int i=0;i<xh.length;i++){
			xhstr.append("'");
			xhstr.append(xh[i]);
			xhstr.append("',");
		}
		return xhstr.substring(0,xhstr.length()-1);
	}
	
	/**
	 * 将本科生学生信息表中数据更新到学生信息表（南通航运）
	 * @param xhs 
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean Bks_xsjbxxToXsxxb(String xhs,String type) throws Exception{
		boolean b=true;
		if(Globals.XXDM_NTHYZYJSXY.equals(Base.xxdm)){//南通航运
			b=Bks_xsjbxxToXsxxb_nthy(xhs, type);
		}
		return b;
	}
	
	/**
	 * 将本科生学生信息表中数据更新到学生信息表（南通航运）
	 * @param xhs 
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean Bks_xsjbxxToXsxxb_nthy(String xhs,String type) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xsxxb ");
		sql.append("(xh,xm,xb,sfzh,nj,xjztm,xz,xydm,zydm,bjdm");
		sql.append(",ksh)");

		sql.append("select xh,xm,xbm xb,sfzh,to_char(nj) nj,xjztm,substr(xz, 0, 1) xz,bmdm xydm,zydm,bjdm");
		sql.append(",ksh ");
		sql.append("from bks_xsjbxx a ");
		sql.append("where a.xh in( ");
		sql.append(xhs);
		sql.append(") and not exists (select 1 from xsxxb x where x.xh=a.xh)");
		
		DAO dao=new DAO();
		boolean b=dao.runUpdate(sql.toString(), new String[]{});
		return b;
	}
	
	/**
	 * 昆明学院个性化，住宿费查询（查询从教务推送过来的数据）
	 * @param model
	 * @return
	 * @throws Exception 
	 * @author honglin
	 * @date 2012-10-22
	 */
	public List<String[]> getZsfInfoList(CommanForm model,User user)  throws Exception{
		model.setXydm(model.getXy());
		model.setZydm(model.getZy());
		model.setBjdm(model.getBj());
		String[] queryArr = new String[]{"xn","nj","xydm","zydm","bjdm","sfjq"};
		String[] queryLikeArr = new String[]{"xh","xm"};
		Map<String, Object> map = CommonQueryDAO.getQuerySQL(model, queryArr, queryLikeArr);
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pk","xn","xh","xm","nj","xymc","zymc","bjmc","ldmc","qsh","yjje","sjje","sfjq"};
		sql.append(" select rownum r,pk,xn,xh,xm,nj,xymc,zymc,bjmc,ldmc,qsh,yjje,sjje,sfjq from view_xg_kmxy_zsfxxb a where 1=1 ");
		sql.append(map.get("sql"));
		if (Boolean.valueOf(user.getFdyQx()) && Boolean.valueOf(user.getBzrQx())){
			sql.append(" and exists (select 1 from (select bjdm from fdybjb a where ");
			sql.append(" a.zgh = '" + user.getUserName() + "' ");
			sql.append(" union all select bjdm from bzrbbb b where ");
			sql.append(" b.zgh = '" + user.getUserName() + "') c where a.bjdm = c.bjdm ) ");
		}else if (Boolean.valueOf(user.getFdyQx())){
			sql.append(" and exists (select 1 from fdybjb c where a.bjdm = c.bjdm ");
			sql.append(" and c.zgh = '" + user.getUserName() + "')");
		}else if (Boolean.valueOf(user.getBzrQx())){
			sql.append(" and exists (select 1 from bzrbbb c where a.bjdm = c.bjdm ");
			sql.append(" and c.zgh = '" + user.getUserName() + "')");
		}else if ("xy".equals(user.getUserType())){
			sql.append(" and a.xydm='" + user.getUserDep() + "' ");
		}
		return CommonQueryDAO.commonPageQuery(model.getPages(), sql.toString(),(String[]) map.get("input"), colList);
	}
	
}
