package xgxt.studentInfo.zgkd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国矿业大学学生信息Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-03</p>
 */
public class XsxxZgkdService {
	XsxxZgkdDAO dao = new XsxxZgkdDAO();
	
	/**
	 * 获取学生信息的所有字段 
	 * @return List
	 * */
	public List<HashMap<String, String>> getXsxxzdList(){	
		String xxdm = StandardOperation.getXxdm();
		return dao.getXsxxzdList(xxdm);
	}
	
	/**
	 * 获取学生家庭信息的所有字段 
	 * @return List
	 * */
	public List<HashMap<String, String>> getJtxxzdList(){
		String xxdm = StandardOperation.getXxdm();
		return dao.getJtxxzdList(xxdm);
	}
	
	/**
	 * 根据用户类型获取可维护的字段列表
	 * @param yhjs
	 * @param tableName
	 * @return List
	 * */
	public List getZdByYhList(String yhjs,String tableName){
		return dao.getWhzdByYh(yhjs,tableName);
	}
	
	/**
	 * 获取用户列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getYhList(){
		String xxdm = StandardOperation.getXxdm();
		return dao.getYhList(xxdm);
	}
	
	/**
	 * 保存学生信息修改权限信息
	 * @param model
	 * @return boolean
	 * */
	public boolean savePower(XsxxZgkdForm model,HttpServletRequest request){
		return dao.savePower(model,request);
	}
	
	/**
	 * 获取民族列表
	 * */
	public List getMzList(){
		return dao.getMzList();
	}
	
	/**
	 * 获取政治面貌列表
	 * */
	public List getZzmmList(){
		return dao.getZzmmList();
	}
	
	/**
	 * 查询除学号外学生可维护的字段
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs,String tableName){
		return dao.getzdNoXh(yhjs,tableName);
	}
	
	/**
	 * 查询除学号外学生可维护的字段
	 * @param yhjs
	 * @return String
	 * */
	public String getzdNoXh(String yhjs){
		return dao.getzdNoXh(yhjs);
	}
	
	/**
	 * 查询学生基本信息和家庭信息
	 * @param xh
	 * @return List
	 * */
	public HashMap<String, String> getStuAndFamily(String xh,String yhjs){
		String xxdm = StandardOperation.getXxdm();
		return dao.getStuAndFamily(xh,yhjs,xxdm);
	}
	
	/**
	 * 判断是否在修改学生信息的时间段内
	 * @return String
	 * */
	public String isSqqjFlag(){
		return dao.isSqqjFlag();
	}
	
	/**
	 * 保存学生修改的信息
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoModi(String yhjs,String xh, HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsxx_xsxgxxb";
		String[] zdCol = dao.getZdinfo(yhjs);
		String[] zdVal = new String[zdCol.length];
		String[] zd = new String[zdCol.length+1];
		String[] val = new String[zdVal.length+1];
		
		for(int i=0; i<zdCol.length; i++){
			zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
			zd[i] = zdCol[i];
			val[i] = zdVal[i];
		}
		try {			
			zd[zd.length-1] = "xh";
			val[val.length-1] = xh;
			flag = StandardOperation.delete(tableName, "xh", xh, request);
			flag = StandardOperation.insert(tableName, zd, val, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 保存学院修改的信息
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String yhjs,String xh, HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsxxb";
		String[] columns = dao.getZdInfoByTab(yhjs, tableName);
		String[] zdCol = null;
		boolean hasXh = false;
		for(int i=0; i<columns.length ; i++){
			if(columns[i].equalsIgnoreCase("xh")){
				hasXh = true;
			}
		}
		if(!hasXh){
			zdCol = new String[columns.length+1];
		}else{
			zdCol = new String[columns.length];
		}
		for(int i=0; i<columns.length ; i++){
			zdCol[i] = columns[i];
		}
		
		if(!hasXh){
			zdCol[columns.length] = "xh";
		}
		String[] zdVal = new String[zdCol.length];
		
		for(int i=0; i<zdCol.length; i++){
			zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
		}
		try {	
			//保存学生个人信息
			if(!dao.isExists(xh, tableName)){
				if(!dao.isExists(xh, "view_xsjbxx")){
					flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
					
				}else{
					flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc from view_xsjbxx where xh='"+xh+"')", request);
				}
			}
			flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			//if(!"xx".equalsIgnoreCase(yhjs)){
				//保存学生家庭信息
				tableName = "xsfzxxb";
				String[] result = dao.getZdInfoByTab(yhjs, tableName);//不包括学号字段 
				zdCol = new String[result.length+1];
				for(int i=0; i<result.length; i++){
					zdCol[i] = result[i];
				}
				
				zdCol[result.length] = "xh";
				zdVal = new String[zdCol.length];
				String[] zd = new String[zdCol.length];
				String[] val = new String[zdCol.length];
				for(int i=0; i<zdCol.length; i++){//获取页面字段的值
					zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
					zd[i] = zdCol[i];
					val[i] = zdVal[i];
				}
				
				if(!dao.isExists(xh, tableName)){//学生家庭信息中不存在该学生的信息
					flag = StandardOperation.insert(tableName, zd, val, request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("student".equalsIgnoreCase(yhjs) && flag){//学生确认信息
			try{
				flag = StandardOperation.update("xsxxb", new String[]{"xsqrxxbz"},new String[]{"是"}, "xh", xh, request);
			}catch(Exception e){
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 保存学院修改的信息
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String xh, HttpServletRequest request){
		XsxxglService xsxxglService = new XsxxglService();
		boolean dzxxqdm = "yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"));
		boolean flag = false;
		String tableName = "xsxxb";
		String[] columns = dao.getZdInfoByTab("student", tableName);
		String[] zdCol = null;
		boolean hasXh = false;
		for(int i=0; i<columns.length ; i++){
			if(columns[i].equalsIgnoreCase("xh")){
				hasXh = true;
			}
		}
		if(!hasXh){
			zdCol = new String[columns.length+1];
		}else{
			zdCol = new String[columns.length];
		}
		for(int i=0; i<columns.length ; i++){
			zdCol[i] = columns[i];
		}
		
		if(!hasXh){
			zdCol[columns.length] = "xh";
		}
		String[] zdVal = new String[zdCol.length];
		if(dzxxqdm){//地址信息取代码
			for(int i=0; i<zdCol.length; i++){
				if("syd".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("syshen"),
							   "/" ,
							   request.getParameter("syshi"),
							   "/", 
							   request.getParameter("syxian"));
				}else if("jg".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("jgshen"),
							   "/" ,
							   request.getParameter("jgshi"),
							   "/", 
							   request.getParameter("jgxian"));
				}else if("hkszd".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("hkshen"),
							   "/" ,
							   request.getParameter("hkshi"),
							   "/", 
							   request.getParameter("hkxian"));
				}else{
					zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				}
			}
		}else{
			for(int i=0; i<zdCol.length; i++){
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
			}
		}
		
		try {	
			if(zdCol != null && zdCol.length>0){
				//保存学生个人信息
				if(!dao.isExists(xh, tableName)){
					if(!dao.isExists(xh, "view_xsjbxx")){
						flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
						
					}else{
						flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc,xjztm)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc,xjztm from view_xsjbxx where xh='"+xh+"')", request);
					}
				}
				flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			}
			//保存学生家庭信息
			tableName = "xsfzxxb";
			String[] result = dao.getZdInfoByTab("student", tableName);//不包括学号字段 
			zdCol = new String[result.length];
			for(int i=0; i<result.length; i++){
				zdCol[i] = result[i];
			}
			zdVal = new String[zdCol.length];
			String[] zd = new String[zdCol.length];
			String[] val = new String[zdCol.length];
			for(int i=0; i<zdCol.length; i++){//获取页面字段的值
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				zd[i] = zdCol[i];
				val[i] = zdVal[i];
			}
			if(zd != null && zd.length>0){
				if(!dao.isExists(xh, tableName)){//学生家庭信息中不存在该学生的信息
					//=============2011.7.4 edit by luojw===========================
					List<String> zdList = new ArrayList<String>();
					for(int i=0;i<zd.length;i++){
						zdList.add(zd[i]);
					}
					zdList.add("xh");
					
					List<String> valList = new ArrayList<String>();
					for (int i = 0; i < val.length; i++) {
						valList.add(val[i]);
					}
					valList.add(xh);
					
					flag = StandardOperation.insert(tableName, zdList.toArray(new String[]{}), valList.toArray(new String[]{}), request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 保存学生信息
	 * @param yhjs
	 * @param xh
	 * @param request
	 * @return boolean
	 * */
	public boolean saveStuinfoByXy(String yhjs,String xh,String tableName, HttpServletRequest request){
		XsxxglService xsxxglService = new XsxxglService();
		boolean dzxxqdm = false;//"yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"));
		boolean flag = false;
		String[] zdCol = dao.getZdInfoByTab(yhjs, tableName);
		String[] zdVal = new String[zdCol.length+1];
		
		zdCol = StringUtils.joinStrArr(zdCol, new String[]{"xh"});
		
		if(dzxxqdm){//地址信息取代码
			for(int i=0; i<zdCol.length; i++){
				if("syd".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("syshen"),
							   "/" ,
							   request.getParameter("syshi"),
							   "/", 
							   request.getParameter("syxian"));
				}else if("jg".equalsIgnoreCase(zdCol[i])){
					zdVal[i] = StringUtils.joinStr(request.getParameter("jgshen"),
							   "/" ,
							   request.getParameter("jgshi"),
							   "/", 
							   request.getParameter("jgxian"));
				}else{
					zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				}
			}
		}else{
			for(int i=0; i<zdCol.length; i++){
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
			}
		}
		try {	
			//保存学生个人信息
			if(tableName != null && tableName.equalsIgnoreCase("xsxxb")){
				if(!dao.isExists(xh, tableName)){
					if(!dao.isExists(xh, "view_xsjbxx")){
						flag = StandardOperation.insert(tableName, zdCol, zdVal, request);
					}else{
						flag = StandardOperation.update(tableName, "insert into xsxxb(xh,xm,xb,nj,xydm,zydm,bjdm,xz,xy,zymc,bjmc)(select xh,xm,xb,nj,xydm,zydm,bjdm,xz,xymc,zymc,bjmc from view_xsjbxx where xh='"+xh+"')", request);
					}
				}
				flag = StandardOperation.update(tableName, zdCol, zdVal, "xh", xh, request);
			}
			//保存学生家庭信息
			zdCol = dao.getZdInfoByTab(yhjs, tableName);//不包括学号字段
			zdVal = new String[zdCol.length];
			String[] zd = new String[zdCol.length+1];
			String[] val = new String[zdCol.length+1];
			for(int i=0; i<zdCol.length; i++){//获取页面字段的值
				zdVal[i] = DealString.toGBK(request.getParameter(zdCol[i]));
				zd[i] = zdCol[i];
				val[i] = zdVal[i];
			}
			zd[zd.length-1] = "xh";
			val[zd.length-1] = xh;
			
			if(tableName !=null && tableName.equalsIgnoreCase("xsfzxxb")){
				if(!dao.isExists(xh, tableName)){//学生家庭信息中不存在该学生的信息
					flag = StandardOperation.insert(tableName, zd, val, request);
				}else{
					flag = StandardOperation.update(tableName, zd, val, "xh", xh, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 保存学生学习简历信息
	 * @param xh
	 * @param request
	 * */
	public boolean saveXsxxjlxx(String xh, HttpServletRequest request){
		String tableName = "xsxx_xsxxjlb";
		boolean flag = false;
		String[] input = {"xh","jl1_qzny","jl1_xxjgzdw","jl1_xxhrzw","jl2_qzny",
				          "jl2_xxjgzdw","jl2_xxhrzw","jl3_qzny","jl3_xxjgzdw",
				          "jl3_xxhrzw","jl4_qzny","jl4_xxjgzdw","jl4_xxhrzw",
				          "jl5_qzny","jl5_xxjgzdw","jl5_xxhrzw","jl6_qzny",
				          "jl6_xxjgzdw","jl6_xxhrzw"};
		String[] value = new String[input.length];
		for(int i=0; i<value.length; i++){
			value[i] = request.getParameter(input[i]);
		}
		value[0] = xh;
		if(!dao.isExists(xh, tableName)){//学生家庭信息中不存在该学生的信息
			flag = StandardOperation.insert(tableName, input, value, request);
		}else{
			try {
				flag = StandardOperation.update(tableName, input, value, "xh", xh, request);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 查询学生修改的信息
	 * 
	 * @param model
	 * @return List
	 * @throws Exception
	 */
	public List<String[]> getStuInfoAudi(XsxxZgkdForm model,
			HttpServletRequest request) throws Exception {
		return dao.selectModiStuinfo(model, request);
	}
	
	/**
	 * 获取查询结果表头
	 * @return List 
	 * */
	public List getTopTr(){
		return dao.getTopTr();
	}
	
	/**
	 * 获取审核列表
	 * @param int
	 * @return List  
	 * */
	public List getChkList(int type){
		return dao.getChkList(type);
	}
	
	/**
	 * 获取学生修改前的信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNewValue(String xh){
		HashMap<String, String> map = dao.getNewValue(xh);
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		if ("new".equalsIgnoreCase(Base.getEdition())){
			//新版
			//将代码转换成名称
			map.put("pycc", xsxxglDao.dmToMc("dtjs_xsccb", "xsccdm", "xsccmc", map.get("pycc")));
			
			//将籍贯、生源地、户口所在地拆分为省、市、县
			String[] arrDq = {};
			if(StringUtils.isNotNull(map.get("jg"))){
				arrDq = map.get("jg").split("/");
			}
			map.put("jgshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
			map.put("jgshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
			map.put("jgxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			
			//生源地
			if(StringUtils.isNotNull(map.get("syd"))){
				arrDq = map.get("syd").split("/");
			}else{
				arrDq = new String[]{};
			}
			map.put("syshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
			map.put("syshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
			map.put("syxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			//户口所在地		
			if(StringUtils.isNotNull(map.get("hkszd"))){
				arrDq = map.get("hkszd").split("/");
			}
			map.put("hkshen",  (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
			map.put("hkshi",  (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
			map.put("hkxian",  (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			//代码转换成名称
			map.put("syd", xsxxglDao.dzxxdmToMc(map.get("syd")));
			map.put("jg", xsxxglDao.dzxxdmToMc(map.get("jg")));
			map.put("hkszd", xsxxglDao.dzxxdmToMc(map.get("hkszd")));
		}
		return map;
	}
	
	/**
	 * 获取学生修改前的信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNewValue(String xh,boolean dmtomc){
		HashMap<String, String> map = dao.getNewValue(xh);
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		if ("new".equalsIgnoreCase(Base.getEdition())){
			//新版
			if(dmtomc){
				//将代码转换成名称
				map.put("pycc", xsxxglDao.dmToMc("dtjs_xsccb", "xsccdm", "xsccmc", map.get("pycc")));
				map.put("syd", xsxxglDao.dzxxdmToMc(map.get("syd")));
				map.put("jg", xsxxglDao.dzxxdmToMc(map.get("jg")));
				map.put("hkszd", xsxxglDao.dzxxdmToMc(map.get("hkszd")));
			}else{			
				//将籍贯、生源地、户口所在地拆分为省、市、县
				String[] arrDq = {};
				if(StringUtils.isNotNull(map.get("jg"))){
					arrDq = map.get("jg").split("/");
				}
				map.put("jgshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
				map.put("jgshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
				map.put("jgxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
				
				//生源地
				if(StringUtils.isNotNull(map.get("syd"))){
					arrDq = map.get("syd").split("/");
				}else{
					arrDq = new String[]{};
				}
				map.put("syshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
				map.put("syshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
				map.put("syxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
				//户口所在地		
				if(StringUtils.isNotNull(map.get("hkszd"))){
					arrDq = map.get("hkszd").split("/");
				}
				map.put("hkshen",  (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
				map.put("hkshi",  (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
				map.put("hkxian",  (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
			}
		}
		return map;
	}
	
	/**
	 * 获取学生修改后的信息 
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getOldValue(String xh){
		HashMap<String, String> map = dao.getOldValue(xh);
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		if ("new".equalsIgnoreCase(Base.getEdition())){
			//新版
			map.put("syd", xsxxglDao.dzxxdmToMc(map.get("syd")));
			map.put("jg", xsxxglDao.dzxxdmToMc(map.get("jg")));
			map.put("hkszd", xsxxglDao.dzxxdmToMc(map.get("hkszd")));
			//将代码转换成名称
			map.put("pycc", xsxxglDao.dmToMc("dtjs_xsccb", "xsccdm", "xsccmc", map.get("pycc")));
		}
		return map;
	}
	
	/**
	 * 保存审核结果
	 * @param xh
	 * @param yesNo
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveAuditing(String xh, String yesNo,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String yhjs = "student";
		String tabName = "xsxxb";
		if("true".equalsIgnoreCase(request.getSession().getAttribute("fdyQx").toString()) 
				&& Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			//修改审核结果
			flag = StandardOperation.update("xsxx_xsxgxxb", new String[]{"fdysh"}, new String[]{yesNo}, "xh", xh, request);
		}else if("xy".equalsIgnoreCase(request.getSession().getAttribute("userType").toString())
				&& Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
			//学院用户不审核
			return true;
		}else{
			if(yesNo.equalsIgnoreCase("通过")){
				//修改学生个人信息
				String[] cols = dao.getZdInfoByTab(yhjs,tabName);
				String[] value = dao.getOneRs("select * from xsxx_xsxgxxb where xh=?", new String[]{xh}, cols);
				if(!dao.isExists(xh, tabName)){
					//如果学生在xsxxb中没有记录，先插入 
					StandardOperation.update(tabName, "insert into xsxxb(xh,xm,xb,nj,xz,xjztm,xydm,zydm,bjdm)(select xh,xm,xb,nj,xz,xjztm,xydm,zydm,bjdm from view_xsjbxx where xh='"+xh+"')", request);
				}
				flag = StandardOperation.update(tabName, cols, value, "xh", xh, request);
				
				//修改学生家庭信息
				tabName = "xsfzxxb";
				cols = dao.getZdInfoByTab(yhjs, tabName);
				value = dao.getOneRs("select * from xsxx_xsxgxxb where xh=?", new String[]{xh}, cols);
				if(!dao.isExists(xh, tabName)){
					//如果学生在xsfzxxb中没有记录，先插入 
					StandardOperation.update(tabName, "insert into xsfzxxb(xh) values '"+xh+"'", request);
				}
				flag = StandardOperation.update(tabName, cols, value, "xh", xh, request);
				
				if(!Globals.XXDM_BJQNZZXY.equalsIgnoreCase(Base.xxdm)){
					//删除修改的信息
					flag = StandardOperation.delete("xsxx_xsxgxxb", "xh", xh, request);
				}else{
					//学生信息修改审核通过后就不可再修改了
					//修改审核结果
					flag = StandardOperation.update("xsxx_xsxgxxb", new String[]{"shjg"}, new String[]{yesNo}, "xh", xh, request);
				}
			}else{
				//修改审核结果
				flag = StandardOperation.update("xsxx_xsxgxxb", new String[]{"shjg"}, new String[]{yesNo}, "xh", xh, request);
			}
		}
		return flag;
	}
	
	/**
	 * 批量审核
	 * @param PkValue
	 * @param yesNo
	 * @param request
	 * @return boolean
	 * */
	public boolean saveAutiBatch(String[] PkValue, String yesNo, HttpServletRequest request) throws Exception{
		boolean flag = false;
		for(int i=0; i<PkValue.length; i++){
			flag = saveAuditing(PkValue[i],yesNo,request);
		}
		return flag;
	}
	
	/**
	 * 获取贫困生等级
	 * */
	public String getPksdj(String xh){
		return dao.getPksdj(xh);
	}
	
	/**
	 * 判断学校是否审核通过
	 * @param xh
	 * @return String
	 * */
	public String getXxshjg(String xh){
		return dao.getXxshjg(xh);
	}
	
	/**
	 * 保存修改的字段
	 * */
	public boolean saveXgzdxx(String xh, String xgzd, User user){
		boolean flag = dao.saveXgzdxx(xh,xgzd,user);
		return flag;
	}
	
	/**
	 * 获取修改的字段信息
	 * */
	public HashMap<String, String> getXgzdxx(String xh){		
		return dao.selectXgzdxx(xh);
	}
}
