package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_������Ŀ_����_DAO��
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

public class DshdDAO extends CommDAO {

	/**
	 * ɾ�����������
	 * 
	 * @author ΰ�����
	 */
	public Boolean delDshdSqf(PjpyStuForm model, String[] pkValue, User user) {

		boolean flag = true;

		// ����
		String pk = "xn||xq||xh||dsmc||dsrq";
		// ������
		String tableName = "szyq_dshdjzb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(tableName);
		sql.append(" where bzrsh = 'δ���' ");

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(pk + "=? ");
			}
			sql.append(")");
		}

		try {

			DAO dao = DAO.getInstance();
			if (pkValue != null && pkValue.length > 0) {
				flag = dao.runUpdate(sql.toString(), pkValue);
			}

		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	/**
	 * ������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveDshdSqf(PjpyStuForm model, List<String[]> params,
			User user) {

		boolean flag = false;

		// �������ر�
		String tableName = "szyq_dshdjzb";
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(tableName);
		sql.append("(xn,xq,xh,dsmc,dsrq,dsxd,sfhj,sqf)");
		sql.append("values(?,?,?,?,?,?,?,?)");

		try {
			flag = saveArrDate(sql.toString(), params, tableName, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ���������Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getDshdList(PjpyStuForm model) {

		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ѧ��
		String xh = model.getXh();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,case when length(a.dsmc) > 6 then ");
		sql.append(" substr(a.dsmc, 0, 6) || '...' ");
		sql.append(" else a.dsmc end dsmc, a.dsmc alldsmc, a.dsrq, case ");
		sql.append(" when length(a.dsxd) > 6 then substr(a.dsxd, 0, 6) || '...' ");
		sql.append(" else a.dsxd end dsxd, a.dsxd alldsxd, a.sfhj, ");
		sql.append(" a.sqf, case when bzrsh = 'ͨ��' then bzrshf ");
		sql.append(" else '0' end bzrshf, case when xysh = 'ͨ��' then ");
		sql.append(" xyshf else '0' end xyshf, case when xxsh = 'ͨ��' then ");
		sql.append(" xxshf else '0' end xxshf, case ");
		sql.append(" when clr is not null then '�Ѵ���' ");
		sql.append(" when b.xh is not null then '������' ");
		sql.append(" else '����' end cz,b.ssnr,b.clyj,b.clr, ");
		sql.append(" (select xm from yhb c where c.yhm = b.clr) clrxm, ");
		sql.append(" bzrsh,xysh,xxsh,bzrshf bzrlrf,xyshf xylrf,xxshf xxlrf ");
		sql.append(" from (select id,xn,xq,xh,dsmc,dsrq,dsxd,sfhj,sqf, ");
		sql.append(" nvl(bzrshf, 0) bzrshf,bzrsh,nvl(xyshf, 0) xyshf, ");
		sql.append(" xysh,nvl(xxshf, 0) xxshf,xxsh from szyq_dshdjzb a ");
		sql.append(" where xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and xh = ?) a ");
		sql.append(" left join xg_pjpy_szgyyq_xsssb b on ");
		sql.append(" a.xh = b.xh and a.id = b.xmid ");
		sql.append(" order by dsrq ");
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "id", "dsmc",
						"alldsmc", "dsrq", "dsxd", "alldsxd", "sfhj", "sqf",
						"bzrshf", "xyshf", "xxshf", "cz", "ssnr", "clyj",
						"clrxm", "bzrsh", "xysh", "xxsh","bzrlrf","xylrf","xxlrf" });

		return list;
	}
	
	/**
	 * ��ö�����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// ѧ��
		String xh = model.getXh();
		// �༶
		String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);
		// ѧ��
		String xn = searchModel.getSearch_tj_xn()[0];
		// ѧ��
		String xq = searchModel.getSearch_tj_xq()[0];
		// ����
		String[] cz = searchModel.getSearch_tj_cz();
		// ģ����ѯ
		String input_mhcx = searchModel.getInput_mhcx();
		// ��ѯ����
		String mhcx_lx = searchModel.getMhcx_lx();
		// �û�����
		String yhlx = model.getYhlx();
		
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("( select a.xh,a.xm,a.sqf,a.zzf,a.cz,a.clr,");
		tableSql.append("a.clyj,a.clsj,a.tsnr,a.clrxm,a.nj,a.xydm,a.zydm,a.bjdm ");	
		tableSql.append("from ( ");
		tableSql.append("select a.xh, a.xm, nvl(b.sqf, 0) sqf, nvl(b.zzf, 0) zzf, ");
		tableSql.append("'"+xn+"' xn,'"+xq+"' xq, ");
		tableSql.append("case when a.xh = '" + xh + "' then '�鿴' ");
		tableSql.append("when c.clr is not null then '�Ѵ���' ");
		tableSql.append("when c.xh is not null then '��Ͷ��' else '��Ͷ��' end cz, ");
		tableSql.append("c.clr,c.clyj,c.clsj,c.tsnr,a.nj,a.xydm,a.zydm,a.bjdm,");
		tableSql.append("(select xm from yhb d where d.yhm = c.clr) clrxm ");
		tableSql.append("from (select a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
		tableSql.append("bz".equalsIgnoreCase(yhlx)||"stu".equalsIgnoreCase(yhlx)  ? " and a.bjdm = '" + bjdm + "'" : "");
		tableSql.append(") a ");
		tableSql.append("left join (select b.xh, sum(b.sqf) sqf, sum(b.pf) zzf ");
		tableSql.append("from szyq_dshdjzb b ");
		tableSql.append("where b.xn = '"+xn+"' ");
		tableSql.append("and b.xq = '"+xq+"' ");
		tableSql.append("group by b.xh) b on a.xh = b.xh ");
		tableSql.append("left join (select c.xh, c.btsr, c.tsnr, c.tssj, c.clr, c.clyj, c.clsj ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb c ");
		tableSql.append("where c.xmlx = 'szyq_dshdjzb' ");
		
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
			tableSql.append("and c.xh = '" + xh + "' ");
		} else {
			tableSql.append("and exists (select 1 ");
			tableSql.append("from (select btsr, max(tssj) tssj ");
			tableSql.append("from xg_pjpy_szgyyq_xstsb ");
			tableSql.append("where xmlx = 'szyq_dshdjzb' ");
			tableSql.append("and xn = '" + xn + "' ");
			tableSql.append("and xq = '" + xq + "' ");
			tableSql.append("group by btsr) d ");
			tableSql.append("where c.btsr = d.btsr ");
			tableSql.append("and c.tssj = d.tssj) ");
		}
		
		tableSql.append("and c.xn = '"+xn+"' ");
		tableSql.append("and c.xn = '"+xn+"' ");
		tableSql.append("and c.xq = '"+xq+"') c on a.xh = c.btsr ");
		tableSql.append("order by zzf,xh ");
		tableSql.append(") a ");
		tableSql.append("where 1 = 1 ");

		String[] inputV = new String[]{};
		
		if ("stu".equalsIgnoreCase(yhlx) ||"bz".equalsIgnoreCase(yhlx)) {//�೤
			// ����
			if (cz != null && cz.length > 0) {
				tableSql.append("and ( ");
				for (int i = 0; i < cz.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("a.cz = '" + commService.unicode2Gbk(cz[i])
							+ "'");
				}
				tableSql.append(") ");
			}

			// ģ����ѯ
			if (!Base.isNull(input_mhcx.trim())) {

				String[] mhcx = input_mhcx.split(" ");

				if (!"all".equalsIgnoreCase(mhcx_lx)) {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("a." + mhcx_lx + " like '%" + mhcx[i]
								+ "%'");
					}
					tableSql.append(") ");

				} else {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("(a.xh like '%" + mhcx[i] + "%' ");
						tableSql.append("or ");
						tableSql.append("a.xm like '%" + mhcx[i] + "%') ");
					}
					tableSql.append(") ");
				}
			}
		} else {

			user.setUserStatus(yhlx);
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");

			tableSql.append(searchTj);
			tableSql.append(searchTjByUser);

			inputV = SearchService.getTjInput(model.getSearchModel());
		}

		tableSql.append(")");

		String query = " order by xh ";
		
		return getRsArrList(tableSql.toString(), query, inputV, new String[] {
				"xh", "xm", "sqf", "zzf", "clr", "clsj", "clrxm", "clyj",
				"tsnr", "cz" }, "", model);

	}

	/**
	 * ��ö��������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getDshdShList(
			ArrayList<String[]> dshdList, PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchModel searchModel = model.getSearchModel();

		// �û�����
		String yhlx = model.getYhlx();
		// ѧ��
		String xn = searchModel.getSearch_tj_xn()[0];
		// ѧ��
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder sql = new StringBuilder();

		sql.append("select xh, bzrsh, xysh, xxsh from szyq_dshdjzb ");
		sql.append("where xn = '" + xn + "' ");
		sql.append("and xq = '" + xq + "' ");
		if (dshdList != null && dshdList.size() > 0) {
			sql.append("and ( ");
			for (int i = 0; i < dshdList.size(); i++) {
				String xh = dshdList.get(i)[0];
				if (i != 0) {
					sql.append(" or");
				}
				sql.append(" xh = '" + xh + "' ");
			}
			sql.append(") ");
		}

		StringBuilder shztTj = new StringBuilder();

		// �߼���ѯ����
		String[] shztlx = searchModel.getSearch_tj_shztlx();
		
		if (shztlx != null && shztlx.length > 0) {

			for (int i = 0; i < shztlx.length; i++) {
				if (i == 0) {
					shztTj.append("and( ");
				} else {
					shztTj.append(" or ");
				}
				if ("ȫ�����ͨ��".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// �೤
						// shztTj.append(" bzrsh='ͨ��' ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='ͨ��' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='ͨ��' ");
					}

				} else if ("����δ���".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// �೤
						// shztTj.append(" bzrsh='δ���' or bzrsh='������' ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" (xysh='δ���' or xysh='������') ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj
								.append(" ((xysh='ͨ��' and xxsh='δ���') or (xysh='ͨ��' and xxsh='������'))  ");
					}

				} else if ("����������".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// �೤
						// shztTj.append(" bzrsh='������' ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='������' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='������'  ");
					}
				} else if ("�������".equalsIgnoreCase(shztlx[i])) {
					if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" bzrsh<>'ͨ��' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh<>'ͨ��' ");
					}
				}
			}
			
			shztTj.append(" ) ");
		}
		sql.append(shztTj);
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao
				.getList(sql.toString(), new String[] {}, new String[] { "xh",
						"bzrsh", "xysh", "xxsh" });

		return list;

	}
	
	/**
	 * ��ö��������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdShList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchModel searchModel = model.getSearchModel();

		// Ȩ�޿���
		
		
		// �û�����
		String yhlx = model.getYhlx();
		user.setUserStatus(yhlx);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// ѧ��
		String xn = searchModel.getSearch_tj_xn()[0];
		// ѧ��
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder tableSql = new StringBuilder();

//		�߼���ѯ����
		String[] shztlx=searchModel.getSearch_tj_shztlx();
		searchModel.setSearch_tj_shztlx(null);
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
//		�߼���ѯ����ֵ
		String[]  inputV = SearchService.getTjInput(searchModel);
		searchModel.setSearch_tj_shztlx(shztlx);
		
		StringBuilder shztTj=new StringBuilder();
		String userName=user.getUserName();
		if(shztlx!=null && shztlx.length>0){
			
			for(int i=0;i<shztlx.length;i++){
				if(i==0){
					shztTj.append("and( ");
				}else{
					shztTj.append(" or ");
				}
		
				if("ȫ�����ͨ��".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//�೤
						shztTj.append(" bzrsh='ͨ��' ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh='ͨ��' ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xxsh='ͨ��' ");
					}	
				}else if("����δ���".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//�೤
						shztTj.append(" bzrsh='δ���' or bzrsh='������' ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" (bzrsh='ͨ��' and xysh='δ���') or (bzrsh='ͨ��' and xysh='������') ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" (xysh='ͨ��' and xxsh='δ���') or (xysh='ͨ��' and xxsh='������')  ");
					}
				}else if("����������".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//�೤
						shztTj.append(" bzrsh='������' ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh='������' ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xxsh='������'  ");
					}
				}else if("�������".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//�೤
						shztTj.append(" (xysh<>'δ���' and xysh<>'�˻�') ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" bzrsh<>'ͨ��' ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh<>'ͨ��' ");
					}	
				}
			}
			shztTj.append(" ) ");
		}
		
		tableSql.append("(");
		tableSql.append("select * from (");
		tableSql.append("select a.xn,a.xq,a.xh,a.xm,a.bjmc,sum(a.sqf) sqf, ");
		tableSql.append("a.bjdm,a.xydm,a.zydm,a.nj, ");
		tableSql.append("sum(a.bzrshf) bzrshf,sum(a.xyshf) xyshf, ");
		tableSql.append("sum(a.xxshf) xxshf,a.ss,a.ts from (select a.xh,a.xn,a.xq, ");
		tableSql.append("b.xm,b.bjdm,b.bjmc,a.sqf,b.xydm,b.zydm,b.nj, ");
		tableSql.append("case when a.bzrsh = 'ͨ��' then nvl(a.bzrshf, '0') else '0' end bzrshf, ");
		tableSql.append("case when a.xysh = 'ͨ��' then nvl(a.xyshf, '0') else '0' end xyshf, ");
		tableSql.append("case when a.xxsh = 'ͨ��' then nvl(a.xxshf, '0') else '0' end xxshf, ");
		
		tableSql.append("case when (c.ssrs = '0' or c.ssrs is null) then '��' else '��' end ss, ");
		tableSql.append("case when (d.tsrs = '0' or d.tsrs is null) then '��' else '��' end ts ");
		
		tableSql.append("from (select * from szyq_dshdjzb where 1=1 "+shztTj+" ) a ");
		tableSql.append("left join view_xsjbxx b on a.xh = b.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) ssrs ");
		tableSql.append("from (select distinct xn, xq, xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xsssb a ");
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) c on c.xmlx = 'szyq_dshdjzb' ");
		tableSql.append("and a.xh = c.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) tsrs ");
		tableSql.append("from (select distinct xn, xq,btsr xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb a ");
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) d on d.xmlx = 'szyq_dshdjzb' ");
		tableSql.append("and a.xh = d.xh ");
	                                  
		tableSql.append("where a.xn = '" + xn + "' ");
		tableSql.append("and a.xq = '" + xq + "') a ");
		tableSql.append("group by a.xn,a.xq,a.xh,a.xm,a.bjdm,a.bjmc,a.xydm,a.zydm,a.nj,a.ss,a.ts ");
		tableSql.append(") a ");
		
		tableSql.append(" where 1=1 ");
		
		if("bz".equalsIgnoreCase(yhlx)){//�೤
			
			// ѧ��
			String xh = user.getUserName();
			// �༶
			String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);
				
			tableSql.append(" and bjdm = '"+bjdm+"' and xh<>'"+userName+"' ");
			
		} else {//�ǰ೤�û�
			
			
			tableSql.append(searchTjByUser);
		}
		
		tableSql.append(searchTj);
		tableSql.append(")");
		
		return getRsArrList(tableSql.toString(), "", inputV, new String[] {
				"xh", "xm", "bjmc", "sqf", "bzrshf", "xyshf", "xxshf", "ss",
				"ts" }, "", model);

	}

	/**
	 * �޸Ķ�����Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean editDshdInfo(PjpyStuForm model, User user) throws Exception {

		DshdModel dshdModel = model.getDshdModel();
		// ����ID
		String id = dshdModel.getId();
		// ��������
		String dsmc = dshdModel.getDsmc()[0];
		// ��������
		String dsrq = dshdModel.getDsrq()[0];
		// �����ĵ�
		String dsxd = dshdModel.getDsxd()[0];
		// �Ƿ��
		String sfhj = dshdModel.getSfhj()[0];
		// �����
		String sqf = dshdModel.getSqf()[0];

		StringBuilder sql = new StringBuilder();
		sql.append("update szyq_dshdjzb set ");
		sql.append("dsmc = ? ");
		sql.append(",dsrq = ? ");
		sql.append(",dsxd = ? ");
		sql.append(",sfhj = ? ");
		sql.append(",sqf = ? ");
		sql.append("where id = ? ");

		DAO dao = DAO.getInstance();

		boolean flag = dao.runUpdate(sql.toString(), new String[] { dsmc, dsrq,
				dsxd, sfhj, sqf, id });

		return flag;
	}
}
