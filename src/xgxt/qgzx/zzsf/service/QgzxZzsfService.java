package xgxt.qgzx.zzsf.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.zzsf.dao.QgzxZzsfDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 漳州师范学院勤工助学Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-06-24</p>
 */
public class QgzxZzsfService {
	QgzxZzsfDAO dao = new QgzxZzsfDAO();
	
	/**
	 * 查询学生基本信息和当前时间
	 * */
	public HashMap<String, String> getBaseInfo(String xh){
		return dao.getBaseInfo(xh);
	}
	
	/**
	 * 查询岗位列表
	 * */
	public List getGwList(){
		return dao.getGwList();
	}
	
	/**
	 * 查询学生岗位信息
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwxx(String pkV){
		return dao.getXsgwxx(pkV);
	}
	
	/**
	 * 保存岗位信息
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXsgwxx(QgzxForm model, HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "xsgwxxb";
		String[] columns = {};
		String[] values = {};
		String primaryKey = "xh||gwdm||'-'||gwsbsj";
		String pkValue = "";
				
		model.setXmdm(DealString.toGBK(model.getXmdm()));
		model.setLxdh(DealString.toGBK(model.getLxdh()));
		model.setXssq(DealString.toGBK(model.getXssq()));
		model.setGzjl(DealString.toGBK(model.getGzjl()));
		//获取岗位信息
		HashMap<String, String> map = dao.getGwxx(model.getXmdm());
		boolean isKns = dao.isKns(model.getXh());
		String kns = isKns == true ? "是" : "否";
		
		if(dao.checkXsgwExists(model.getXh(), model.getXmdm())){//申请记录已经存在进行修改操作
			columns = new String[]{"lxdh","gzjl","xssq","bz","sfpks"};
			values = new String[]{model.getLxdh(), model.getGzjl(), model.getXssq(), model.getBz(),kns};
			pkValue = model.getXh() + model.getXmdm();
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{//记录不存在进行增加操作
			HashMap<String, String> tmpMap = dao.getQgzxcs();
			
			columns = new String[]{"xn","nd","xq","xh", "gwdm" , "gwsbsj", "lxdh", "gzjl", "xssq", "bz", "fdyyj", "xyyj", "xxyj","sfpks"};
			values = new String[]{tmpMap.get("xn"),tmpMap.get("nd"),tmpMap.get("xq"),model.getXh(), map.get("gwdm"), map.get("gwsbsj"),model.getLxdh(),model.getGzjl(),model.getXssq(),model.getBz(),"通过","通过","通过",kns};
			result = StandardOperation.insert(tableName, columns, values, request);
		}
		
		return result;
	}
}
