package xgxt.qtsj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qtsj.dao.InsureDAO;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 保险管理模块Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-15</p>
 */
public class InsureService {
	InsureDAO dao = new InsureDAO();//数据库操作类
	
	/**
	 * 学生投保申请批量审核
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean batchAuditing(CommanForm model, HttpServletRequest request){
		boolean flag = false;
		String yesNo = model.getYesNo();
		String pkValue = model.getPkValue();//主键值字符串
		String[] pkV = Base.isNull(pkValue) ? model.getPkV() : pkValue
				.split("!!"); 
		String pkString = "xh||nd||tbgsdm||tbxzdm";// 主键字段
		String userType = model.getUserType();//用户类型
		String[] columns = {"xxsh"};
		String[] values = null;
		String mes = "";//提示信息
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			columns = new String[]{"xysh"};
		}
		
		//审核结果
		if(yesNo != null && "pass".equalsIgnoreCase(yesNo)){
			values = new String[]{"通过"};
		}else{
			values = new String[]{"不通过"};
		}
		
		for(int i=0; i<pkV.length; i++){
			try {
				flag = StandardOperation.update("xsbxb", columns, values, pkString, pkV[i], request);
			} catch (Exception e) {//操作失败
				flag = false;
				mes += "第" + (i+1) + "条数据操作失败！\n" ;
				e.printStackTrace();
			}
			
		}		
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * 获取投保险种列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getTbxzList(){
		return dao.getTbxzdmList();
	}
	
	/**
	 * 获取保险公司代码表
	 * @return List
	 * */
	public List<HashMap<String, String>> getBxgsList(){
		return dao.getBxgsList();
	}
	
	/**
	 * 获取保险档次代码表
	 * @return List
	 * */
	public List<HashMap<String, String>> getBxdcList(){
		return dao.getBxdcList();
	}
	
	
	
	/**
	 * 保存发布信息
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean savePubData(InsureForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "bxglxxb";
		String pk = "title";
		String keys = model.getTitle();
		if(dao.checkExists(tableName, pk, keys)){
			//update
			flag = StandardOperation.update(tableName, new String[]{"content"}, new String[]{model.getContent()}, "title", model.getTitle(), request);
			
		}else{
			//insert
			flag = StandardOperation.insert(tableName, new String[]{"title", "content"}, new String[]{model.getTitle(), model.getContent()}, request);
		}
		return flag;
	}
	
	/**
	 * 发布信息查询
	 * @param title
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getDataPubInfo(String title){
		HashMap<String, String> map = dao.getDataPubInfo(title);
		return map;
	}
	
	
	
	/**
	 * 理赔项目列表 
	 * @return
	 */
	public List<HashMap<String, String>> getLpxmList() {
		
		return dao.getWhList("bxxx_lpxm", "dm", "mc", "", "", "");
	}
	
	
	
	/**
	 * 下拉列表
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getList(int type) {
		return dao.getChkList(type);
	}
	
	
	
	/**
	 * 学生信息
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
}
