package xgxt.dtjs.gdby.tygl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.basic.BasicService;
import com.zfsoft.database.model.TableModel;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.utils.CommonQueryDAO;

/**
 * Title: ѧ����������ϵͳ Description:������service,����ͨ�÷����е��ж��ֶ� Copyright: Copyright (c)
 * 2010 Company: zfsoft Author: sjf Version: 1.0 Time: 2010-8-17
 */
public class BasicExtendService extends BasicService{
	public static Map<String, String> xqMap = new HashMap<String, String>();

	/**
	 * ��ʼ��xqMap
	 */
	static {
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"xqdzb", "", new String[] {}, new String[] { "xqdm", "xqmc" },
				"");
		for (HashMap<String, String> map : list) {
			xqMap.put(map.get("xqdm"), map.get("xqmc"));
		}
	}

	/**
	 * ����ָ����������ĳѧ����Ϣ
	 * 
	 * @param xh
	 * @param output
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh, String[] output) {
		return CommonQueryDAO.commonQueryOne("view_xsxxb", output, "xh", xh);
	}
	
	/**
	 * ����ָ���������ý�ʦ��Ϣ
	 * 
	 * @param xh
	 * @param output
	 * @return
	 */
	public Map<String, String> getTeaInfo(String zgh, String[] output) {
		if(output != null){
			return CommonQueryDAO.commonQueryOne("view_fdyxx", output, "zgh", zgh);
		}else {
			String[] colList = new String[]{"zgh", "xm", "zw", "xb","zwmc", "bmdm", "bmmc", "xl",
					"zwmc","mzmc","csrq","zzmm"};
			return CommonQueryDAO.commonQueryOne("view_fdyxx", colList, "zgh", zgh);
		}
	}

	/**
	 * �ж�ĳ����Ա�Ƿ��ǹ�Ԣ����Ա������Ƿ���������¥����,���Ƿ���null
	 * 
	 * @param yhm
	 * @return
	 */
	public List<HashMap<String, String>> getXqld(String yhm) {
		String[] output = new String[] { "yhm", "xqdm", "xqmc", "lddm", "ldmc" };
		String query = " where yhm=?";

		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				"view_gyfdyxx", query, new String[] { yhm }, output, "");
		
		return list != null && list.size()>0 ? list : null;
	}
	
	public List<HashMap<String, String>> getXqList(String yhm){
		String[] output = new String[]{"xqdm","xqmc"};
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct(xqdm),xqmc from view_gyfdyxx where yhm=?");
		return dao.getList(sql.toString(), new String[]{yhm}, output);
	}
	
	/**
	 * ���������ж������Ƿ�����ڱ���
	 * @param tableName
	 * @param pkValue
	 * @return
	 */
	public boolean checkExists(String tableName, String pkValue){
		boolean flag = false;
		
		TableModel tModel = getTable(tableName);
		if(tModel != null){
			String pk = tModel.getPrimaryKey();
			pk = pk.replace(",", "||");
			BaseDAO dao = new BaseDAO();
			flag = dao.checkExists(tableName, pk, pkValue);
		}
		
		return flag;
	}
}
