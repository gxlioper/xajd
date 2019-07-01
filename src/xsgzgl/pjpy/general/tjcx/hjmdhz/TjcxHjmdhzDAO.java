package xsgzgl.pjpy.general.tjcx.hjmdhz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_����������_ͨ��_DAO��
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

public class TjcxHjmdhzDAO extends PjpyTjcxDAO {

	/**
	 * ��ȡ�ҵ���������������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getHjmdhzList(PjpyGeneralForm myForm,
			TjcxHjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = searchModel.getSearch_tj_xn();
		if (xn == null || xn.length == 0) {
			xn = new String[] { jbszForm.getPjxn() };
		}
		searchModel.setSearch_tj_xn(xn);
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,a.* from ( ");
		sql.append(" select a.xn||a.xq||a.xmlx||a.xmmc||a.xh pk, ");
		sql.append(" a.xn,a.xh,b.xm,b.nj,b.xydm,b.zydm, ");
		sql.append(" b.bjdm,b.xymc,a.xmmc,a.hdsj,a.xmje,a.xmlx, ");
		sql.append(" decode(a.xmlx,'01','��ѧ��','�����ƺ�') xmlxmc ");
		sql.append(" from xg_pjpy_pjlsxxb a ");
		sql.append(" left join xg_view_pjpy_pjryk b ");
		sql.append(" on a.xh=b.xh ");
		sql.append("order by a.xn,a.xmmc,a.xh ");
		sql.append(" )a ");

		sql.append(query);

		String[] colList = { "pk", "xn", "xh", "xm", "xymc", "xmlxmc", "xmmc",
				"hdsj" };
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonQuery(sql.toString(), "", inputV, colList, myForm);

		return list;
	}

	/**
	 * ��ѯ��ѧԺѧ�ڽ�ѧ�������
	 * 
	 * @author ΰ�����
	 */
	public List<String[]> getXyList(String xn, String xmlx, String[] xydm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xmlx);
		params.add(xn);
		params.add(xmlx);
		
		sql.append("select xydm, xymc, xmmc jxjdm, rs ");
		sql.append("from (select a.xydm, a.xymc, a.xmmc, nvl(b.rs, 0) rs ");
		sql.append("from (select distinct a.xydm, a.xymc, b.xmmc ");
		sql.append("from view_njxyzybj_all a, ");
		sql.append("(select distinct xmmc ");
		sql.append("from xg_pjpy_pjlsxxb ");
		sql.append("where xn = ? ");
		sql.append("and xmlx = ?) b) a ");
		sql.append("left join (select c.xmmc, d.xydm, count(1) rs ");
		sql.append("from xg_pjpy_pjlsxxb c, xg_view_pjpy_pjryk d ");
		sql.append("where c.xh = d.xh ");
		sql.append("and c.xn = ? ");
		sql.append("and c.xmlx = ? ");
		if (xydm != null && xydm.length > 0) {
			sql.append("and d.xydm in ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		sql.append("group by c.xmmc, d.xydm) b ");
		sql.append("on a.xmmc = b.xmmc ");
		sql.append("and a.xydm = b.xydm) ");
		sql.append("where rs is not null ");
		sql.append("and rs <> '0' ");

		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "xydm", "xymc", "jxjdm", "rs" });
	}
	
	/**
	 * ��ѯ����ѧ�������
	 * 
	 * @author ΰ�����
	 */
	public List<String[]> getXmrsList(String xn, String xmlx) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xmlx);

		sql.append("select xmmc jxjdm, xmmc jxjmc, rs ");
		sql.append("from (select xmmc, count(1) rs ");
		sql.append("from xg_pjpy_pjlsxxb ");
		sql.append("where xn = ? ");
		sql.append("and xmlx = ? ");
		sql.append("group by xmmc) ");

		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "jxjdm", "jxjmc", "rs" });
	}
	
	/**
	 * ��ѯ����ѧ�������
	 * 
	 * @author ΰ�����
	 */
	public List<String[]> getHjmdList(String xn, String xmlx, String[] xydm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		params.add(xmlx);

		sql.append("select a.xmmc jxjdm, b.xydm, b.xm ");
		sql.append("from xg_pjpy_pjlsxxb a, xg_view_pjpy_pjryk b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xn = ? ");
		sql.append("and a.xmlx = ? ");
		if (xydm != null && xydm.length > 0) {
			sql.append("and b.xydm in ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "jxjdm", "xydm", "xm" });
	}
}
