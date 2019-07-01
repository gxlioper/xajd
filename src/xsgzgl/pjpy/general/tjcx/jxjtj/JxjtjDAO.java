package xsgzgl.pjpy.general.tjcx.jxjtj;

import java.util.ArrayList;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 ����14:19:22
 * </p>
 */

public class JxjtjDAO extends CommDAO {

	/**
	 * ��ѧ��ͳ����ҳ���ݲ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjtjCx(JxjtjForm myForm) throws Exception {
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		// �û�����
		String[] colList = new String[] { "xn", "xymc", "xmmc", "rs", "je" };
		// �û�����
		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from view_xg_pjpy_jxjtj_gd a ");
		sql.append(query);
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		return list;
	}
}