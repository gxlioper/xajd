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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��������_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyLcszService extends PjpyCommService {

	PjpyLcszDAO dao = new PjpyLcszDAO();

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLcszList() {

		// �����
		String tableName = "xg_pjpy_lcszb";
		// ����
		String query = " order by lcdj";
		// ��һ�ֶ�
		String[] colList = new String[] { "lcdj" };

		List<HashMap<String, String>> list = getRsList(tableName, query,
				new String[] {}, colList, "");

		return list;
	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @author ΰ�����
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
	 * ������������������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjpyLcsz(PjpyLcszForm model, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_pjpy_lcszb";
		// ����
		String pk = "lcdj";
		// ����ֵ
		String[] pkValue = null;
		String pkTemp = request.getParameter("pkValue");
		
		if(StringUtils.isNotNull(pkTemp)){
			pkValue = new String[]{pkTemp};
		}else{
			pkValue = new String[] { model.getLcdj() };
		}
		// ��һ�ֶ�
		String[] onezd = new String[] { "lcdj", "lcmc", "kssj", "jssj", "lcsm" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);
	}
	
	/**
	 * ��ȡ���̵ȼ�
	 * 
	 * @return list
	 * @author sjf
	 */
	public List<Integer> getLcdj(){
		Map<Integer, String> lcdjMap = new TreeMap<Integer, String>();
		
		// Ĭ��1��10�����̵ȼ�
		for(int i=1; i<=9; i++ ){
			lcdjMap.put(i, String.valueOf(i));
		}
		
		// ɾ���Ѿ���ʹ�õ����̵ȼ�
		String query = " order by lcdj";
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList("XG_PJPY_LCSZB", query, new String[]{}, new String[]{"lcdj"}, "");
	
		if(list != null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				int key = Integer.parseInt(list.get(i).get("lcdj"));
				lcdjMap.remove(key);
			}
		}
		
//		 ���1~10�ȼ�����ʹ���ˣ���������
//		if(lcdjMap.isEmpty()){
//			int lcdjMax = Integer.parseInt(list.get(list.size()-1).get("lcdj"))+1;
//			lcdjMap.put(lcdjMax, String.valueOf(lcdjMax));
//		}
		
		Set<Integer> set = lcdjMap.keySet();
		
		return new ArrayList<Integer>(set);
	}
	
	/**
	 * ��list����һ���������ͣ�������������
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
	 * ��ȡ����������Ϣ
	 * @return List
	 * @author sjf
	 */
	public List<String[]> getLcsz(){
		String query = " order by lcdj";
		return CommonQueryDAO.commonQueryNotFy("XG_PJPY_LCSZB", query, new String[]{}, 
													new String[]{"lcdj", "lcmc", "kssj", "jssj"}, "");
	}

	/**
	 * ͨ��������ȡĳ������
	 * @param pkValue
	 * @return
	 * @author sjf
	 */
	public Map<String, String> getLcForPk(String pkValue){
		String[] colList = new String[]{"lcdj", "lcmc", "kssj", "jssj", "lcsm"};
		return CommonQueryDAO.commonQueryOne("XG_PJPY_LCSZB", colList, "lcdj", pkValue);
	}
}