package xsgzgl.xsxx.general.zxxs;

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
import xsgzgl.xsxx.general.XsxxGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_ͨ��_DAO��
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

public class XsxxZxxsDAO extends CommDAO {

	// ==================ִ�в�ѯ���� begin========================
	/**
	 * ��ý����(��Уѧ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// �û�����
		String yhlx = model.getYhlx();

		// ====================��������===================================
		user.setUserStatus(yhlx);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// query += searchTjByUser;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.* ");
		tableSql.append("from (");
		tableSql.append("select a.xh, a.xm, a.xb, a.nj, a.bjmc, a.xjztm ");
		tableSql.append("from view_xsbfxx a ");
		tableSql.append("where (sfyby = '��' or sfyby is null) ");
		tableSql.append("and (sfzx = '��У' or sfzx is null) ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.nj,a.bjdm,a.xh ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}

	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @date 2013-01-06
	 * @author ΰ�����
	 */
	public HashMap<String, String> getZxxsInfo(String xh, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,a.xm,a.xb,a.csrq,a.mz,a.mzmc,a.zzmm,a.zzmmmc, ");
		sql.append("a.sfzh,a.jg,a.syd,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm, ");
		sql.append("a.bjmc,a.xz,a.sfzx,a.sfyby,a.sjhm,a.qqhm,a.dzyx,a.yhdm,a.yhkh, ");
		sql.append("(select yhmc from dmk_yh where yhdm=a.yhdm) yhmc,");
		sql.append("b.jtszd,b.yb,lxdh1, ");
		sql.append("b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_nl,b.jtcy1_sfzh,b.jtcy1_mz,");
		sql.append("(select mzmc from mzdmb where mzdm=b.jtcy1_mz) jtcy1_mzmc,");
		sql.append("(select zzmmmc from zzmmdmb where zzmmdm=b.jtcy1_zzmm) jtcy1_zzmmmc,");
		sql.append("b.jtcy1_zzmm,b.jtcy1_zy,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy1_gzdz, ");
		sql.append("b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_nl,b.jtcy2_sfzh,b.jtcy2_mz,b.jtcy2_zzmm,");
		sql.append("(select mzmc from mzdmb where mzdm=b.jtcy2_mz) jtcy2_mzmc,");
		sql.append("(select zzmmmc from zzmmdmb where zzmmdm=b.jtcy2_zzmm) jtcy2_zzmmmc,");
		sql.append("b.jtcy2_zy,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy2_gzdz, ");
		sql.append("a.jgmc,a.sydqmc ");
		sql.append("from view_xsxxb a ");
		sql.append("left join xsfzxxb b on a.xh = b.xh ");
		sql.append("where a.xh=? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { xh });
		return map;
	}
	// ==================ִ���������� end==============================
}
