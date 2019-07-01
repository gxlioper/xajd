package xgxt.qgzx.shgc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.form.QgzxForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-03-16</p>
 */
public class QgzxShgcService {
	QgzxShgcDAO dao = new QgzxShgcDAO();
	
	/**
	 * 获取用人单位列表
	 * @return List
	 * */
	public List getYrdwList(){
		return dao.getYrdwList();
	}
	
	/**
	 * 获取用人单位考核上报时间设置信息
	 * @param model
	 * @return List
	 * */
	public List getYrdwkhsbsj(QgzxForm model){
		return dao.getYrdwkhsbsj(model);
	}
	
	/**
	 * 获取考核上报时间表头
	 * @return List
	 * */
	public List getKhsbsjToptr(){
		String[] column =  {"yrdwdm", "yrdwmc", "kssj", "jssj", "dlm"};
		String[] colCN = dao.getColumnNameCN(column, "view_yrdwkhsbsj");
		return dao.arrayToList(column, colCN);
	}
	
	/**
	 * 根据用人单位查询考核上报时间信息
	 * @param pk
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getKhsbsjOfYrdw(String pk){
		return dao.getKhsbsjOfYrdw(pk);
	}
	
	/**
	 * 保存考核上报时间设置信息
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveKhsbsjInfo(QgzxForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String pk = model.getPk();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String message = "";
		String tableName = "qgzx_yrdwkhsbsjb";
		
		String[] pkValue = pk.split("!!");
		for(int i=0; i<pkValue.length; i++){
			if(pkValue[i] != null && !"".equalsIgnoreCase(pkValue[i])){
				if(dao.checkExists(tableName, "yrdwdm", pkValue[i])){//记录存在
					//update
					flag = StandardOperation.update(tableName, new String[]{"kssj","jssj"}, new String[]{kssj,jssj}, "yrdwdm", pkValue[i], request);
				}else{//记录不存在 
					//insert
					flag = StandardOperation.insert(tableName, new String[]{"yrdwdm","kssj","jssj"}, new String[]{pkValue[i],kssj,jssj}, request);
				}		
				if(!flag){//操作失败
					message += "第" + (i+1) + "条数据操作失败！\n";
				}
			}
		}
		request.setAttribute("mes", message);//操做失败的记录信息
		return flag;
	}
	
	/**
	 * 查询学生工作时间考核情况信息
	 * @param model
	 * @return List
	 * */
	public List getXskhInfo(QgzxForm model){
		return dao.getXskhInfo(model);
	}
	
	/**
	 * 根据代码查询名称
	 * @param tableName
	 * @param pk
	 * @param value
	 * @param str
	 * @return String
	 * */
	public String getXmmc(String tableName, String pk,String str, String value){
		return dao.getXmmc(tableName,pk,str,value);
	}
	
	/**
	 * 获取打印考核时间信息表头
	 * @return List
	 * */
	public List getPrintXskhTortr(){
		String[] column = {"xn","nd","yf","gwdm","xh","xm","bjmc","sqsj","gzsj","gzbx","gzqk"};
		String[] colCN ={"学年","年度","月份","岗位名称","学号","姓名","班级","申请时间","工作时间","工作表现","工作情况"};
		return dao.arrayToList(column, colCN);
	}
}
