package xgxt.dwjl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.GetTime;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 对外交流模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class DwjlService {
	
	DwjlDAO dao = new DwjlDAO();
	
	/**
	 * 获取学生对外交流申请信息
	 * @param model
	 * @return HashMap< String, String>
	 * */
	public HashMap< String, String> getDwjlsqInfo(DwjlForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		
		map = dao.getStuInfo(model.getXh());
		map.putAll(dao.getDwjlxmInfo(model.getXmdm()));
		map.put("bjdypm", model.getBjdypm());
		map.put("njzypm", model.getNjzypm());
		map.put("tcah", model.getTcah());
		map.put("shgzqk", model.getShgzqk());
		map.put("sqly", model.getSqly());
		return map;
	}
	
	/**
	 * 获取学生申请对外交流奖学金信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDwjljxjsqInfo(DwjlForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuInfo(model.getXh());
		map.putAll(dao.getDwjlxmInfo(model.getXmdm()));
		map.put("sqje", model.getSqje());
		map.put("jlbx", model.getJlbx());
		return map;
	}
	
	/**
	 * 获取学生申请对外交流奖学金信息
	 * @param xh
	 * @param xmdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDwjljxjsqInfo(String xh, String xmdm){
		return dao.selectDwjljxjsq(xh,xmdm);
	}
	
	/**
	 * 查询对外交流信息
	 * @param xmdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryDwjlxx(String xmdm){
		return dao.selectDwjlxx(xmdm);
	}
		
	/**
	 * 获取学生申请的单位记录项目
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryXsdwjlxm(String xh){
		return dao.selectXsdwjlxmList(xh);
	}
	/**
	 * 获取学生基本信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 获取学生申请出国留学信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuCglxsq(DwjlForm model){
		return dao.getStuCglxsq(model);
	}
	
	/**
	 * 保存生申请出国留学信息
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveCglxsq(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "xscgsqb";
		String pk = "xh||sqrq";
		String xh = DealString.toGBK(model.getXh());
		String qqh = DealString.toGBK(model.getQqh());
		String xl = DealString.toGBK(model.getXl());
		String jdxx = DealString.toGBK(model.getJdxx());
		String dzyx = DealString.toGBK(model.getDzyx());
		String cet = DealString.toGBK(model.getCet());
		String tem = DealString.toGBK(model.getTem());
		String toeft = DealString.toGBK(model.getToeft());
		String jzxm = DealString.toGBK(model.getJzxm());
		String jzgzdw = DealString.toGBK(model.getJzgzdw());
		String jtdz = DealString.toGBK(model.getJtdz());
		String xx = DealString.toGBK(model.getXx());
		String gj = DealString.toGBK(model.getGj());
		String sfzzlxxx = DealString.toGBK(model.getSfzzlxxx());
		String lxdh = DealString.toGBK(model.getLxdh());
		String sqrq = DealString.toGBK(model.getSqrq());
		sqrq = sqrq == null || "".equalsIgnoreCase(sqrq) ? GetTime.getSystemTime() : sqrq;
		
		String pkValue = xh+sqrq;
		String[] columns = {"xh", "sqrq", "xl", "jdxx", "cet", "tem", "toeft", "jzxm", "jzgzdw", "jtdz", "xx", "gj", "qqh", "dzyx", "lxdh", "sfzzlxxx"};
		String[] values = {xh,sqrq,xl,jdxx,cet,tem,toeft,jzxm,jzgzdw,jtdz,xx,gj,qqh,dzyx,lxdh,sfzzlxxx};
		
		if(dao.checkExists(tableName, pk, pkValue)){
			//update			
			columns = new String[]{ "xl", "jdxx", "cet", "tem", "toeft", "jzxm", "jzgzdw", "jtdz", "xx", "gj", "qqh", "dzyx", "lxdh", "sfzzlxxx"};
			values = new String[]{xl,jdxx,cet,tem,toeft,jzxm,jzgzdw,jtdz,xx,gj,qqh,dzyx,lxdh,sfzzlxxx};
			flag = StandardOperation.update(tableName, columns, values, pk, pkValue, request);
		}else{
			//insert
			flag = StandardOperation.insert(tableName, columns, values, request);
		}		
		return flag;
	}
	
	/**
	 * 出国留学申请查询表头
	 * @return List
	 * */
	public List getCglxsqToptr(){
		String[] column = {"主键","xh","xm", "xb","xymc","bjmc","gj","xx","sqrq","xysh","xxsh"};
		String[] colCN = dao.getColumnNameCN(column, "view_cgsqxx");		
		return dao.arrayToList(column, colCN);
	}
	
	/**
	 * 出国留学申请信息查询
	 * @param model
	 * @return List
	 * */
	public List selectCglxsq(DwjlForm model){
		return dao.selectCglxsq(model);
	}
	
	/**
	 * 删除学生出国留学申请审核
	 * @param pk
	 * @param request
	 * @return boolean
	 * @throws Excepton
	 * */
	public boolean cglxsqDel(String pk, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String message = "";
		for(int i=0; i<pkValues.length; i++){
			if(pkValues[i] != null && !"".equalsIgnoreCase(pkValues[i])){
				flag = StandardOperation.delete("xscgsqb", "xh||sqrq", pkValues[i], request);
				if(!flag){			
					message += "第" + i + "条记录删除失败！\n";
				}
			}
		}		
		request.setAttribute("mes", message);
		return flag;
	}
	
	/**
	 * 查询出国留学申请审核信息
	 * @param model
	 * @return List
	 * */
	public List selectCglxsqsh(DwjlForm model){
		return dao.selectCglxsqsh(model);
	}
	
	/**
	 * 获取审核列表
	 * @param type
	 * @return List
	 * */
	public List getChkList(int type){
		return dao.getChkList(type);
	}
	
	/**
	 * 出国留学审核结果保存
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveCglxsh(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String xh = DealString.toGBK(model.getXh());
		String sqrq = DealString.toGBK(model.getSqrq());		
		String yesNo = DealString.toGBK(model.getYesNo());
		String userType = model.getUserType();
		String[] column = {};
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			column = new String[]{"xysh"};
		}else if(userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
			column = new String[]{"xxsh"};
		}
		
		flag = StandardOperation.update("xscgsqb", column, new String[]{yesNo}, "xh||sqrq", xh+sqrq, request);
		return flag;
	}
	
	/**
	 * 出国留学申请批量审核
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean cglxsqplsh(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
	    String pk = model.getPk();
	    String tableName = "xscgsqb";
	    String userType = model.getUserType();
	    String message = "";
	    String yesNo = model.getYesNo();
		String[] values = pk.split("!!");
		
		String[] columns = {};
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			columns = new String[]{"xysh"};
		}else if(userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
			columns = new String[]{"xxsh"};
		}
		
		for(int i=0; i<values.length; i++){
			if(values[i] != null && !"".equalsIgnoreCase(values[i])){
				flag = StandardOperation.update(tableName, columns, new String[]{yesNo}, "xh||sqrq", values[i], request);
				if(!flag){
					message += "第" + (i+1) + "条记录审核失败！";
				}
			}
		}		
		return flag;
	}	
	
	/**
	 * 对外交流申请批量审核
	 * @param model
	 * @param request
	 * @throws Exception
	 * */
	public boolean dwjlsqsh(DwjlForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String yesNo = model.getYesNo();
		String[] columns = {"xxsh","xxzs"};
		String tableName = "dwjlsqb";
		String[] pkValue = model.getPk().split("!!");
		yesNo = yesNo.equalsIgnoreCase("1") ? "通过" : "不通过";
		String message = "";
		String userType=model.getUserType();
		
		if ("xy".equalsIgnoreCase(userType)) {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName,
							new String[] { "xysh" }, new String[] { yesNo },
							"xn||nd||xh||jlxmdm", pkValue[i], request);
					if (!flag) {
						message += "第" + (i + 1) + "条记录操作失败!\n";
					}
				}
			}
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName, columns,
							new String[] { yesNo, yesNo },
							"xn||nd||xh||jlxmdm", pkValue[i], request);
					if (!flag) {
						message += "第" + (i + 1) + "条记录操作失败!\n";
					}
				}
			}
		}
		request.setAttribute("mesage", message);
		return flag;
	}
	
	/**
	 * 对外交流奖学金申请批量审核
	 * @param model
	 * @param request
	 * @throws Exception
	 * */
	public boolean dwjljxjsqsh(DwjlForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = false;
		String yesNo = model.getYesNo();
		String[] columns = { "xxsh" };
		String tableName = "dwjljxjsqb";
		String[] pkValue = model.getPk().split("!!");
		yesNo = yesNo.equalsIgnoreCase("1") ? "通过" : "不通过";
		String message = "";
		String userType = model.getUserType();

		if ("xy".equalsIgnoreCase(userType)) {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName,
							new String[] { "xysh" }, new String[] { yesNo },
							"xn||nd||xh||jlxmdm", pkValue[i], request);
					if (!flag) {
						message += "第" + (i + 1) + "条记录操作失败!\n";
					}
				}
			}
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])) {
					flag = StandardOperation.update(tableName, columns,
							new String[] { yesNo }, "xn||nd||xh||jlxmdm",
							pkValue[i], request);
					if (!flag) {
						message += "第" + (i + 1) + "条记录操作失败!\n";
					}
				}
			}
		}
		request.setAttribute("mesage", message);
		return flag;
	}
	
	/**
	 * 获取对外交流项目列表
	 * @return List
	 * */
	public List getDwjlxmList(){
		return dao.getDwjlxmList();
	}
	
	/**
	 * 获取对外记录项目信息
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryDwjlxmdmList(String xn, 
			                                               String nd, 
			                                               String xq){
		return dao.queryDwjlxmList(xn, nd, xq);
	}
}
