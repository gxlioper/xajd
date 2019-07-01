package xgxt.xsgygl.bjlh.ssfp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

public class SsfpService {
	SsfpDAO dao = new SsfpDAO();
	
	/**
	 * 分配标记列表
	 * @return
	 */
	public List<HashMap<String, String>> getFpbjList() {
		return dao.getSelectList("fpbj");
	}
	
	/**
	 * 查询学院列表,将管理,信息,自动化,机电学院排在最前面
	 * @return
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}
	
	/**
	 * 在现有学院列表的基础上增加"团委", "体育教学部", "科研处", "成人教育处"
	 * @param xyList
	 * @return
	 */
	public List<HashMap<String, String>> appendXyList(List<HashMap<String, String>> xyList) {
		String[] en = {"0202", "0405", "0110", "0117"};
		String[] cn = {"团委", "体育教学部", "科研处", "成人教育处"};
		for (int i=0;i<en.length;i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xydm", en[i]);
			map.put("xymc", cn[i]);
			xyList.add(map);
		}
		return xyList;
	}
	
	/**
	 * 已划分床位总数（这里针对的是床位总数）
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处
	 * @return
	 */
	 public  String[] getSsFpYhfcws(String xydm, String fpbj){
		 return dao.getSsFpYhfcws(xydm,fpbj);
	 }
	 
	/**
	 * 未分配学生总人数（这里针对的是学生人数）
	 * @param xydm
	 * @param fpbj 全日制，团委，体育教学部，科研处，成人教育处
	 * @return
	 */
	public String[] getCwFpZsData(String xydm, String fpbj){
			return dao.getCwFpZsData(xydm,fpbj);
		}
	
	 /**
	  * 未划分宿舍列表
	  * @param xqdm
	  * @param xb
	  * @param lddm
	  * @param cs
	  * @return
	  */
	 public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,String xb,String lddm,String cs) {
		 return dao.getSsFpSsXxList(xqdm, xb, lddm, cs);
	 }
	 
	 /**
		 * 返回已划分宿舍信息列表
		 * @param lddm 楼栋代码
		 * @param xqdm 校区代码
		 * @param xydm 学院代码
		 * @param bjdm 班级代码
		 * @return 
		 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,String cs,String xydm, String fpbj) {
			return dao.getSsFpFpSsXxList(lddm, cs, xydm, fpbj);
		}
	
	/**
	 * 保存宿舍分配信息
	 * @param oldMappingItems
	 * @param newMappingItems
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsfpxx(String oldMappingItems, String newMappingItems) throws Exception{
		return dao.saveSsfpxx(oldMappingItems, newMappingItems);
	}
	
	/**
	 * 查询宿舍划分表头
	 * @return
	 */
	public List<HashMap<String, String>> querySshfTitle() {
		String[] en = new String[]{ "pk", "r", "xqmc", "ldmc", "xbxd","cs", "qsh", "xymc"};
		String[] cn = new String[]{ "pk", "行号", "校区名称", "楼栋名称","性别限定", "所属层数", "寝室号", "分配"+Base.YXPZXY_KEY+"(部门)"};
		return dao.getList(en, cn);
	}
	
	/**
	 * 查询宿舍划分结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> querySsfpResult(SsfpModel model) throws Exception{
		return dao.querySsfpResult(model);
	}
	
	/**
	 * 通过主键(ssbh)删除宿舍分配信息
	 * @param pkList
	 * @return
	 */
	public boolean deleteSsfpxx(String[] pkList) throws Exception{
		return dao.deleteSsfpxx(pkList);
	}
	
	/**
	 * 获得公寓管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, SsfpModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getGyglList(tableName, model, colList);
	}
}
