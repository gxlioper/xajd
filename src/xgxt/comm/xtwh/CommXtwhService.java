package xgxt.comm.xtwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class CommXtwhService extends CommService {

	CommXtwhDAO dao = new CommXtwhDAO();

	//ϵͳ��ݷ�ʽͼƬ�б�
	public static List<HashMap<String, String>> picList = null;
	
	/**
	 * ������ҳ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveSysz(CommXtwhForm model, HttpServletRequest request)
			throws Exception {
		return dao.saveSysz(model, request);
	}
	
	/**
	 * �����ҳ���������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getSyInfo(CommXtwhForm model) {

		String tableName = "xtwh_syszb";
		// ����
		String pk = "rownum";
		// ����ֵ��
		String pkValue = "1";
		// ����ֶ�
		String[] colList = new String[] { "tsyj" };

		// ��ҳ��Ϣ
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				colList, pk, pkValue);

		return map;
	}
	
	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhkjfsList(CommXtwhForm model,
			User user) {

		// ��
		String tableName = "xtwh_kjfsszb";
		// �û���
		String yhm = user.getUserName();
		// ��ѯ����
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// �����ֶ�
		String[] inPutList = new String[] { yhm };
		// ����ֶ�
		String[] colList = new String[] { "pic" };
		// ��ݷ�ʽ�б�
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");
		
		return list;
	}
	
	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllKjfsList(CommXtwhForm model,
			User user) {

		// ����ͼƬ�б�
		if (picList == null || picList.size() == 0) {
			// ��
			String tableName = "xtwh_kjfs_picb";
			// ����ֶ�
			String[] colList = new String[] { "picpath", "gnmk", "mkms" };
			// ��ݷ�ʽ�б�
			picList = CommonQueryDAO.commonQueryforList(tableName, "",
					new String[] {}, colList, "");
		}

		return picList;
	}
	
	/**
	 * �����û���ݷ�ʽ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> setKjfsList(CommXtwhForm model,
			User user) {
		// �û������б�
		List<HashMap<String, String>> yhList = getYhkjfsList(model, user);
		// �û������б�
		List<HashMap<String, String>> picList = getAllKjfsList(model, user);
		// �û������б�
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (picList != null && picList.size() > 0) {

			for (int i = 0; i < picList.size(); i++) {

				HashMap<String, String> picMap = picList.get(i);
				HashMap<String, String> map = new HashMap<String, String>();
				// ͼƬ·��
				String picPath = picMap.get("picpath");

				if (yhList != null && yhList.size() > 0) {

					for (int j = 0; j < yhList.size(); j++) {

						HashMap<String, String> yhMap = yhList.get(j);
						// �û�����·��
						String yhPath = yhMap.get("pic");

						if (yhPath.equalsIgnoreCase(picPath)) {
							map.put("iskjfs", "yes");
							break;
						}
					}
				}

				map.putAll(picMap);
				list.add(map);
			}
		}

		return list;
	}
	
	/**
	 * �����ݷ�ʽ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveKjfssz(CommXtwhForm model, User user) throws Exception {

		// ��ݷ�ʽ���ñ�
		String tableName = "xtwh_kjfsszb";
		String[] onezd = new String[] { "yhm" };
		String[] arrzd = new String[] { "pic" };
		String pk = "yhm";
		String userName = user.getUserName();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { userName });

		model.setYhm(userName);
		
		return dao.saveData(saveForm, model, user);

	}
	
	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsMenuList(String userName) {

		// ��
		String tableName = "xg_view_xtwh_yhkjfs";
		// �û���
		String yhm = userName;
		// ��ѯ����
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// �����ֶ�
		String[] inPutList = new String[] { yhm };
		// ����ֶ�
		String[] colList = new String[] { "picpath", "gnmk", "showmk","mkms" };
		// ��ݷ�ʽ�б�
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");

		return list;
	}
	
	/**
	 * �Ƿ�ӵ��Ȩ��
	 * 
	 * @author luojw
	 */
	public Boolean hadQx(User user, String path) {
		return dao.hadQx(user, path);
	}
}
