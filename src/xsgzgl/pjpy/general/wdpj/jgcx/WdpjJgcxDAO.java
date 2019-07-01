package xsgzgl.pjpy.general.wdpj.jgcx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����__�����ѯ_ͨ��_DAO��
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

public class WdpjJgcxDAO extends CommDAO {
	
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
	public ArrayList<String[]> getLspjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
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
				.commonPageByPjQuery(myForm.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/** 
	 * ��ȡ�ҵ���������������Ϣ
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getWdpjBcpjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		PjpyGeneralForm jbszForm=PjpyGeneralForm.getJbszModel();
		//�߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		
		// �û�����
		String userType=user.getUserType();
		
		//====================��������===================================
		//user.setUserStatus(userType);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);

		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(searchModel);
		
		String pjxn=jbszForm.getPjxn();
		
		String pjxq=jbszForm.getPjxq();
		
		String pjnd=jbszForm.getPjnd();

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================�������� end================================
		
		// ====================SQLƴװ================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select a.*,rownum r,a.xmdm||'!!@@!!'||a.xh pkValue from ( ");
		sql.append(" select a.xmmc,a.xmdm,a.pjxn, a.pjxq, ");
		sql.append(" a.pjnd,a.xh,a.xm, a.nj, a.xydm,a.xymc, ");
		sql.append(" a.zydm,a.zymc,a.sqsj,a.bjdm,a.bjmc,b.yhkh, a.sqjg, ");
		sql.append(" case when a.sqjg is null then 'δ���' ");
		sql.append(" when a.sqjg='wsh' then 'δ���' ");
		sql.append(" when a.sqjg='tg' then 'ͨ��' ");
		sql.append(" when a.sqjg='btg' then '��ͨ��' ");
		sql.append(" when a.sqjg='shz' then '�����'  ");
		sql.append(" when a.sqjg='wxsh' then '�������' end shzt ");
		sql.append(" from (select a.sqsj,c.xmmc,a.xmdm, a.pjxn, a.pjxq, a.pjnd, a.sqjg, b.* ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_view_pjpy_pjryk b,xg_pjpy_pjxmwhb c ");
		sql.append(" where a.xh = b.xh and a.xmdm=c.xmdm and a.pjxn = '"+pjxn+"' ");
		sql.append(" and a.pjxq = '"+pjxq+"' and a.pjnd =  '"+pjnd+"' ) a ");
		sql.append(" left join xsxxb b on a.xh = b.xh order by a.xmdm,a.xh ");
		sql.append(" )a ");

		sql.append(query);
		
		String[]colList={"pkValue","xh","xm","nj","bjmc","yhkh","sqsj","xmmc","shzt"};
		// ====================SQLƴװ end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.
			commonQuery(sql.toString(),"", inputV, colList, myForm);
		
		return list;
	}
	
	/**
	 * ���������ʷ��Ϣ
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLspjList(String xh) {
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select xmmc,hdsj,xmje,");
		sql.append("decode(xmlx,'01','��ѧ��','�����ƺ�') xmlx ");
		sql.append("from xg_pjpy_pjlsxxb ");
		sql.append("where xh = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh }, new String[] { "xmmc", "xmlx", "hdsj","xmje" });
		
		return list;
	}
	
	// ==================ִ�в�ѯ���� end==============================
}
