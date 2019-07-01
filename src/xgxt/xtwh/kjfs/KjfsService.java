package xgxt.xtwh.kjfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.xtwh.CommXtwhForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ݷ�ʽ_Service��
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

public class KjfsService extends CommService {

	KjfsDAO dao = new KjfsDAO();

	/**
	 * �����û���ݷ�ʽ�б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> setKjfsList(KjfsForm model, User user) {

		// �û��ѷ��书���б�
		List<HashMap<String, String>> yhList = getYhkjfsList(model, user);
		// �û���Ȩ�޹����б�
		List<HashMap<String, String>> picList = getYhAllkjfsList(model, user);
		// ���չ����б�
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (picList != null && picList.size() > 0) {

			int n = 0;

			for (int i = 0; i < picList.size(); i++) {

				HashMap<String, String> picMap = picList.get(i);
				HashMap<String, String> map = new HashMap<String, String>();
				// ͼƬ·��
				String picPath = picMap.get("picpath");

				if (yhList != null && yhList.size() > 0) {

					for (int j = 0; j < yhList.size(); j++) {

						HashMap<String, String> yhMap = yhList.get(j);
						// �û�����·��
						String yhPath = yhMap.get("picpath");

						if (yhPath.equalsIgnoreCase(picPath)) {
							map.put("iskjfs", "yes");
							map.put("yhnum", String.valueOf(n));
							n++;
							break;
						}
					}
				}

				map.putAll(picMap);
				list.add(map);
			}
		}
		
		int len=0;
		if(list!=null){
			len=list.size();
		}
		for(int i=0;i<18-len;i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			hashMap.put("picpath", "");
			hashMap.put("showmk", "δ����");
			hashMap.put("iskjfs", "none");
			list.add(hashMap);
		}

		return list;
	}

	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhkjfsList(KjfsForm model, User user) {

		// ��
		String tableName = "xg_view_xtwh_yhkjfs";
		// �û���
		String yhm = user.getUserName();
		// ��ѯ����
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// �����ֶ�
		String[] inPutList = new String[] { yhm };
		// ����ֶ�
		String[] colList = new String[] { "picpath" };
		// ��ݷ�ʽ�б�
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");

		return list;
	}

	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhAllkjfsList(KjfsForm model,
			User user) {
		String newQxwh = user.getUserRolesApply();
		List<HashMap<String, String>> picList;
		if(newQxwh.equalsIgnoreCase("yes")){
			picList = dao.getYhAllkjfsListNew(model,
				user);
		}else{
			picList = dao.getYhAllkjfsList(model,
					user);
		}
		// List<HashMap<String, String>> picList = KjfsForm.getPicList();
		// // ����ͼƬ�б�
		// if (picList == null || picList.size() == 0) {
		// // ��
		// String tableName = "xg_view_xtwh_kjfs";
		// // ����ֶ�
		// String[] colList = new String[] { "picpath", "gnmk", "mkms", "bz",
		// "showmk", "showbz" };
		// // ��ݷ�ʽ�б�
		// picList = CommonQueryDAO.commonQueryforList(tableName, "",
		// new String[] {}, colList, "");
		//
		// KjfsForm.setPicList(picList);
		//		}

		return picList;
	}

	/**
	 * �����û���ݷ�ʽ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveYhKjfs(KjfsForm model, User user) throws Exception {

		// �����
		String tableName = "xtwh_kjfsszb";
		// ����
		String pk = "yhm";
		// ����ֵ
		String[] pkValue = new String[] { user.getUserName() };
		// ��һ�ֶ�
		String[] onezd = new String[] { "yhm" };
		// �����ֶ�
		String[] arrzd = new String[] { "pic", "xssx" };

		// ͼƬ��ַ
		String[] pic = model.getPic();
		// ��ʾ˳��
		String[] xssx = null;
		if (pic != null && pic.length > 0) {
			xssx = new String[pic.length];
			for (int i = 0; i < xssx.length; i++) {
				xssx[i] = String.valueOf(i);
			}
		}

		model.setYhm(user.getUserName());
		model.setXssx(xssx);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);

		return saveInfoToDb(saveForm, model, user);

	}

	/**
	 * ����û���ݷ�ʽ�б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsList(User user) {

		return dao.getKjfsList(user);
	}
	
	/**
	 * �Ƿ�ӵ��Ȩ��
	 * 
	 * @author ΰ�����
	 */
	public Boolean hadQx(User user, String path) {
		return dao.hadQx(user, path);
	}
	
	/**
	 * ��ȡ�����б�
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getFwlb(KjfsForm model, User user){
		
		return dao.getFwlb(model, user);
	}
}