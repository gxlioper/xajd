package xgxt.pjpy.comm.pjpy.lcsz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_基本设置_流程设置_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyLcszService extends PjpyCommService {

	PjpyLcszDAO dao = new PjpyLcszDAO();

	/**
	 * 查询基本设置信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getLcszList() {

		// 保存表
		String tableName = "xg_pjpy_lcszb";
		// 条件
		String query = " order by lcdj";
		// 单一字段
		String[] colList = new String[] { "lcdj" };

		List<HashMap<String, String>> list = getRsList(tableName, query,
				new String[] {}, colList, "");

		return list;
	}

	/**
	 * 查询基本设置信息
	 * 
	 * @author 伟大的骆
	 */
	public List<Map<String, String>> setLcszList() {

		List<HashMap<String, String>> lcdjList = getLcszList();

		if (lcdjList != null && lcdjList.size() > 0) {
//			String oldMaxNum = lcdjList.get(0).get("lcdj");
//			String newMaxNum = String.valueOf(Integer.parseInt(oldMaxNum) + 1);
		}
		
		return null;
	}

	/**
	 * 保存评奖流程设置信息
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjpyLcsz(PjpyLcszForm model, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		// 保存表
		String tableName = "xg_pjpy_lcszb";
		// 主键
		String pk = "lcdj";
		// 主键值
		String[] pkValue = null;
		String pkTemp = request.getParameter("pkValue");
		
		if(StringUtils.isNotNull(pkTemp)){
			pkValue = new String[]{pkTemp};
		}else{
			pkValue = new String[] { model.getLcdj() };
		}
		// 单一字段
		String[] onezd = new String[] { "lcdj", "lcmc", "kssj", "jssj", "lcsm" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);
	}
	
	/**
	 * 获取流程等级
	 * 
	 * @return list
	 * @author sjf
	 */
	public List<Integer> getLcdj(){
		Map<Integer, String> lcdjMap = new TreeMap<Integer, String>();
		
		// 默认1到10的流程等级
		for(int i=1; i<=9; i++ ){
			lcdjMap.put(i, String.valueOf(i));
		}
		
		// 删除已经被使用的流程等级
		String query = " order by lcdj";
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList("XG_PJPY_LCSZB", query, new String[]{}, new String[]{"lcdj"}, "");
	
		if(list != null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				int key = Integer.parseInt(list.get(i).get("lcdj"));
				lcdjMap.remove(key);
			}
		}
		
//		 如果1~10等级都被使用了，往后增加
//		if(lcdjMap.isEmpty()){
//			int lcdjMax = Integer.parseInt(list.get(list.size()-1).get("lcdj"))+1;
//			lcdjMap.put(lcdjMax, String.valueOf(lcdjMax));
//		}
		
		Set<Integer> set = lcdjMap.keySet();
		
		return new ArrayList<Integer>(set);
	}
	
	/**
	 * 对list增加一个数（整型），并进行排序
	 * @param list
	 * @param element
	 * @return list
	 * @author sjf
	 */
	public static List<Integer> sort(List<Integer> list, Integer element){
		list.add(element);
		Collections.sort(list);
		
		return list;
	}
	
	/**
	 * 获取所有流程信息
	 * @return List
	 * @author sjf
	 */
	public List<String[]> getLcsz(){
		String query = " order by lcdj";
		return CommonQueryDAO.commonQueryNotFy("XG_PJPY_LCSZB", query, new String[]{}, 
													new String[]{"lcdj", "lcmc", "kssj", "jssj"}, "");
	}

	/**
	 * 通过主键获取某个流程
	 * @param pkValue
	 * @return
	 * @author sjf
	 */
	public Map<String, String> getLcForPk(String pkValue){
		String[] colList = new String[]{"lcdj", "lcmc", "kssj", "jssj", "lcsm"};
		return CommonQueryDAO.commonQueryOne("XG_PJPY_LCSZB", colList, "lcdj", pkValue);
	}
}