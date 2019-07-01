package xgxt.pjpy.ghxy.ryjf.bjbzf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

/**
 * Title: 学生工作管理系统 Description:硅湖学院班级表彰分Service Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-08-06
 */
public class BjbzfGhxyService {
	
	private final String viewName = "view_ghxy_bjbzf";

	private final String tableName = "ghxy_bjbzfb";

	/**
	 * 根据辅导员职工号获得所带的班级信息
	 * 
	 * @param zgh
	 * @return
	 */
	public List<String[]> getFdyBjInfo(String zgh, String[] output) {
		String query = " where zgh=?";
		return CommonQueryDAO.commonQueryNotFy("view_fdybbj", query,
				new String[] { zgh }, output, "");
	}

	/**
	 * 批量保存录入ghxy_bjbzfb的信息
	 * @param model
	 * @return
	 */
	public String insertRecord(BjbzfModel model) {
		String str = "操作失败";
		String[] xh = model.getXh();
		
		int num = model.getCounts().length;
		String[] bzdj = model.getBzdj();
		
		// 获得所有维护的表彰等级
		List<HashMap<String, String>> djList = getBzdj();
		
		// 两成循环，外层控制表彰等级，内层控制申请的表彰等级
		for(int i=0;i<djList.size();i++){
			Map<String, String> djMap = djList.get(i);
			String dj = djMap.get("dj");
			double bl = Double.valueOf(djMap.get("bl"));
			double count = 0;
			
			for(int j=0;j<bzdj.length;j++){
				if(dj.equalsIgnoreCase(bzdj[j])){
					count++;
				}
			}
			
			if((count/num)*100 > bl){
				return "获得" + dj + "人数超标";
			}
			
		}
	
		
		DAO dao = DAO.getInstance();
		SaveForm saveForm = new SaveForm();
		String pk = "xh||xn||xq||yd";
		String temp = model.getXn() + model.getXq() + model.getYd();

		

		String[] pkValues = new String[xh.length];
		for (int i = 0; i < xh.length; i++) {
			pkValues[i] = xh[i] + temp;
		}
		String[] onezd = new String[] { "xn", "xq", "yd" };
		String[] arrzd = new String[] { "xh", "bzdj", "bzf"};

		saveForm.setPk(pk);
		saveForm.setPkValue(pkValues);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setTableName(tableName);

		try {
			if(dao.saveData(saveForm, model)){
				str = "操作成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 更新单条ghxy_bjbzfb中的记录
	 * @return
	 */
	public boolean updateRecord(BjbzfModel model){
		boolean flag = false;
		String sql = "update ghxy_bjbzfb set njbzrsh=?,xxsh=? where " +
				"xh=? and xn=? and xq=? and yd=?";
		
		DAO dao = DAO.getInstance();
		String[] input = new String[]{model.getNjbzrsh(),model.getXxsh(),
				model.getXh()[0],model.getXn(),model.getXq(),model.getYd()};
		
		try {
			dao.runUpdate(sql, input, tableName);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获得录入信息
	 * @param bjdm
	 * @param xn
	 * @param xq
	 * @param yd
	 * @return
	 */
	public List<HashMap<String, String>> getLrInfo(String bjdm, String xn, String xq, String yd) {

		// 获得该班的所有学生
		String[] output = new String[] { "xh", "xm", "bjmc" };
		String query = " where bjdm=?";
		List<HashMap<String, String>> xsList = CommonQueryDAO.commonQueryforList("view_xsjbxx",
				query, new String[] { bjdm }, output, "");
		
		//获取某学年，学期，月度时 该班的审核信息
		String[] output1 = new String[]{"xh","bzdj","bzf","njbzrsh"};
		String query1 = " where bjdm=? and xn=? and xq=? and yd=?";
		List<HashMap<String, String>> xsList1 = CommonQueryDAO.commonQueryforList(viewName,
				query1, new String[] { bjdm,xn,xq,yd }, output1, "");
		
		// 比较 如果该班某学生已经审核，则辅导员不能再录入
		for(int i=0;i<xsList1.size();i++){
			HashMap<String, String> map1 = xsList1.get(i);
			
			for(int j=0;j<xsList.size();j++){
				HashMap<String, String> map = xsList.get(j);
				
				if(map1.get("xh").equalsIgnoreCase(map.get("xh"))){
					map.put("bzdj", map1.get("bzdj"));
					map.put("bzf", map1.get("bzf"));
					if("通过".equalsIgnoreCase(map1.get("njbzrsh"))){
						map.put("sfsh", "yes");
					}else{
						map.put("sfsh", "no");
					}
				}
			}
		}
		
		return xsList; 
	}

	/**
	 * 获取所有表彰等级
	 * @return
	 */
	public List<HashMap<String, String>> getBzdj(){
		String[] colList = new String[]{"dj", "bl", "jf"};
		return CommonQueryDAO.commonQueryforList("ghxy_bjbzf_csb", "", new String[]{}, colList, "");
	}
	
	/**
	 * 根据主键获得单个学生表彰信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getBjbzOne(String pkValue){
		String[] colList = new String[]{"xh","xm","xb","bjmc","xn","xq","yd","nj","njbzrsh",
				"xxsh","bzdj","bzf"};
		return CommonQueryDAO.commonQueryOne(viewName, colList, "xh||xn||xq||yd", pkValue);
	}
	
	/**
	 * 保存设置参数
	 * @param model
	 * @return
	 */
	public boolean saveCs(BjbzfModel model){
		boolean flag = false;
		SaveForm saveForm = new SaveForm();
		
		String pk = "1";
		String[] pkValue = new String[]{"1"};
		String[] arrzd = new String[]{"dj", "bl", "jf"};
		String[] onezd = new String[]{};
		
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("ghxy_bjbzf_csb");
		
		DAO dao = DAO.getInstance();
		
		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
