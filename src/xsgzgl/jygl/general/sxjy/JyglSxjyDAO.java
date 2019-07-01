package xsgzgl.jygl.general.sxjy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ҵ����_ʵϰ��ҵ_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class JyglSxjyDAO extends CommDAO {

	// ==================ִ�в�ѯ���� begin==============================

	/**
	 * ��ý��������ʷ������
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getSxjyList(JyglGeneralForm myForm,
			JyglSxjyModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// ====================��ʼ������begin===============================
		// ��ѯ��
		String tableName = model.getSearch_table();
		// ���ֵ
		String[] colList = model.getSearch_zd();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯȨ��
		String searchQx = SearchService.getSearchTjByUser(user, "a", "xydm",
				"bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		// ====================��ʼ������ end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append(" select rownum r,a.* from(");
		tableSql.append(" select a.*  ");
		tableSql.append(" from ");
		tableSql.append(tableName);
		tableSql.append(" a where 1=1 ");
		tableSql.append(searchTj);
		tableSql.append(searchQx);
		tableSql.append(" ) a ");
		tableSql.append(" where 1=1 ");

		// ====================SQLƴװ end================================

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	// ==================ִ�в�ѯ���� end==============================
}
