package xsgzgl.xtwh.general.homepage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵ�y�S�o_���_ͨ��_DAO��
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

public class HomePageDAO extends CommDAO {
	
	// ==================ִ�в�ѯ���� begin==============================
	/**
	 * ��ԃ����Ñ��ҵĹ����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getWdgzList(String userName,
			String userType) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.gnmklx,a.gznr,a.gnmkpath ");
		sql.append("from xg_xtwh_wdgz a ");
		sql.append("where username=? ");
		sql.append("and usertype=? ");
		sql.append("order by a.gzsj ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { userName, userType }, new String[] { "gnmklx",
						"gznr", "gnmkpath" });

		return list;
	}
	// ==================ִ�в�ѯ���� end==============================
}
