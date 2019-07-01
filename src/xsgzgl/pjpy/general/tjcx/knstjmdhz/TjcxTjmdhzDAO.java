package xsgzgl.pjpy.general.tjcx.knstjmdhz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;
import xsgzgl.pjpy.general.tjcx.tjmdhz.TjcxTjmdhzModel;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_�񽱽�����_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class TjcxTjmdhzDAO extends PjpyTjcxDAO {

	/**
	 * ��ȡ�ҵ���������������Ϣ
	 * 
	 * @author qlj
	 */
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = searchModel.getSearch_tj_xn();
//		if (xn == null || xn.length == 0) {
//			xn = new String[] { jbszForm.getPjxn() };
//		}
//		searchModel.setSearch_tj_xn(xn);
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

		sql.append(" select rownum r,a.* from(select substr(a.xmmc,11,3)pjdj,a.xn,b.xm,b.xh, ");
		sql.append(" b.nj,b.xydm,b.zydm,b.bjdm,b.bjmc||'('||c.bjrs||'��)' bjrs,nvl(zd2,0) dycj, nvl(zd1,0) zcf,zcfbjpm ,zdcj ");
		sql.append(" from (select a.xn,a.xh,a.xmmc from  xg_pjpy_pjlsxxb a  ");
		sql.append(" union (select a.pjxn,a.xh,b.xmmc from xg_pjpy_pjxmsqb a, ");
		sql.append(" xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd and ( a.sqjg='tg' or a.sqjg='wxsh')))a ");
		sql.append(" left join xg_view_pjpy_pjryk b on a.xh = b.xh ");
		sql.append(" left join (select count(1)bjrs,bjdm from xg_view_pjpy_pjryk group by bjdm)c on b.bjdm=c.bjdm ");
		sql.append(" left join xg_pjpy_zhcpb d on a.xh=d.xh and a.xn=d.xn  ");
		sql.append(" left join (select xh,xn,min(cj)zdcj from view_zhhcjb where (kcxz like '%���޿�%' or kcxz like '%ѡ�޿�%') ");
		sql.append(" and regexp_like(cj,'^\\d{1,10}\\.*\\d{0,10}$') ");
		sql.append("  group by xh,xn) e on a.xh=e.xh and a.xn=e.xn  ");
		
		sql.append(" where xmmc like '%ѧԺ��ͥ��������ѧ��һ�Ƚ�%' ");
		sql.append(" or xmmc like '%ѧԺ��ͥ��������ѧ�����Ƚ�%' ");
		sql.append(" or xmmc like '%ѧԺ��ͥ��������ѧ�����Ƚ�%'  ");
		sql.append(" )a ");

		sql.append(query);

		String[] colList = { "r","pjdj", "xm", "xh",  "bjrs", "dycj", "zcf",
				"zcfbjpm",  "zdcj" };
		// ====================SQLƴװ end================================
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("exp".equalsIgnoreCase(model.getType())){
			list=(ArrayList<String[]>) CommonQueryDAO
			.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		}else{
			list=(ArrayList<String[]>) CommonQueryDAO
					.commonQuery(sql.toString(), "", inputV, colList, myForm);
		}

		return list;
	}

}
