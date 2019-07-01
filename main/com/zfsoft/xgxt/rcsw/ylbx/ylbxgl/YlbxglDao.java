package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ҽ�Ʊ��չ���
 */
public class YlbxglDao extends SuperDAOImpl<YlbxglForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxglForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String ylbxzt = t.getYlbxzt();
		if (ylbxzt.equals("djz")) {
			sql.append(" select distinct a.* from (select case ");
			sql.append(" when (to_number(nvl(a.nj, 0)) + ");
			sql.append(" to_number(nvl(a.xz, 0)) - ");
			sql.append(" greatest(to_number(nvl(bynd,0)), ");
			sql.append(" to_number(to_char(sysdate, 'yyyy')))) < 0 then 0 ");
			sql.append(" else ");
			sql.append(" to_number(nvl(a.nj, 0)) + ");
			sql.append(" to_number(nvl(a.xz, 0)) - ");
			sql.append(" greatest(to_number(nvl(bynd,0)), ");
			sql.append(" to_number(to_char(sysdate, 'yyyy'))) ");
			sql.append(" end xjnx, ");
			sql.append(" a.*, b.*, nvl2(c.rddc, '��', '��') sfkns ");
			sql.append(" from (select a.xh, a.xm, a.xb, a.sfzh, a.csrq, a.bjmc, a.xydm, a.xymc, a.zymc, a.zydm, a.bjdm, a.nj, a.xz ");																														// bynd
			sql.append(" from view_xsjbxx a) a ");
			sql.append(" left join (select a.zd8, a.zd21,a.zd22,substr(to_char(a.zd22), 0, 4) bynd, a.zd23, a.jgid, a.xh bxh,   ");
			sql.append(" count(1) over(partition by a.xh order by a.sqsj desc) rs ");
			sql.append(" from xg_rcsw_ylbx_jgb a) b ");
			sql.append(" on a.xh = b.bxh ");
			sql.append(" and b.rs = 1 "); 
			//�㽭��ѧ�������������Ի�
			if("10335".equals(Base.xxdm)){
				sql.append(" left join (select a.xh,a.rddc from view_knsjgb_fqxrd a) c ");
			}else{
				sql.append(" left join (select a.xh,a.rddc from xg_xszz_new_knsjgb a) c ");
			}
			sql.append(" on a.xh = c.xh) a ");
			sql.append(" where xjnx > 0 ");
		} else {
			sql.append(" select distinct a.* from ( ");
			sql.append(" select a.xh, b.xm, b.xb, b.nj,b.SFZH,b.CSRQ, b.xymc, b.zymc, b.bjmc, b.xz, b.xydm, b.zydm, b.bjdm, a.zd8, a.zd21, a.zd22, a.zd23, a.jgid, a.sjly, nvl2(c.rddc, '��', '��') sfkns ");
			sql.append(" from xg_rcsw_ylbx_jgb a ");
			sql.append(" left join view_xsbfxx b ");
			sql.append(" on a.xh = b.xh ");
			//�㽭��ѧ�������������Ի�
			if("10335".equals(Base.xxdm)){
				sql.append(" left join (select a.xh,a.rddc from view_knsjgb_fqxrd a) c ");
			}else{
				sql.append(" left join (select a.xh,a.rddc from xg_xszz_new_knsjgb a) c ");
			}
			sql.append(" on a.xh = c.xh) a ");
			sql.append(" where 1=1 ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_ylbx_jgb");
		super.setKey("jgid");
	}

	/**
	 * Ψһ���ж�
	 */
	public boolean isExist(YlbxglForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		// �� ���տ�ʼѧ�꡿+Ͷ������ �ڼ��Ƿ����ظ�
		sql.append("select count(1) num from xg_rcsw_ylbx_jgb ");
		sql.append("where to_number(substr(xn,0,4)) >= to_number(?) ");
		sql.append("and to_number(substr(xn,0,4)) <= to_number(?) ");
		String bxksnd = model.getXn().substring(0, 4); // ���տ�ʼ���
		String tbnx = model.getZd2(); // Ͷ������
		String bxjsnd = String.valueOf(Integer.parseInt(bxksnd)
				+ Integer.parseInt(tbnx)); // ���ս������
		params.add(bxksnd);
		params.add(bxjsnd);
		// ============ ѧ�� begin =============
		sql.append("and ( ");
		String[] arr = model.getXh().split(",");
		for (int i = 0; i < arr.length; i++) {
			sql.append(" xh = ? ");
			params.add(arr[i]);
			if (i < arr.length - 1) {
				sql.append(" or ");
			}
		}
		sql.append(") ");
		// ============ ѧ�� end =============
		if (!StringUtils.isBlank(model.getJgid())) {
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}

		boolean flag = false;
		String num = dao.getOneRs(sql.toString(), params
				.toArray(new String[params.size()]), "num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Ψһ���жϡ����ݲ�ѯ���������������ר�á�
	 */
	public boolean isExistPl(YlbxglForm model,
			List<HashMap<String, String>> resultList) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		// �� ���տ�ʼѧ�꡿+Ͷ������ �ڼ��Ƿ����ظ�
		sql.append("select count(1) num from xg_rcsw_ylbx_jgb ");
		sql.append("where to_number(substr(xn,0,4)) >= to_number(?) ");
		sql.append("and to_number(substr(xn,0,4)) <= to_number(?) ");
		String bxksnd = model.getXn().substring(0, 4); // ���տ�ʼ���
		String tbnx = model.getZd2(); // Ͷ������
		String bxjsnd = String.valueOf(Integer.parseInt(bxksnd)
				+ Integer.parseInt(tbnx)); // ���ս������
		params.add(bxksnd);
		params.add(bxjsnd);
		// ============ ѧ�� begin =============
		sql.append("and ( ");
		for (int i = 0; i < resultList.size(); i++) {
			sql.append(" xh = ? ");
			params.add(resultList.get(i).get("xh"));
			if (i < resultList.size() - 1) {
				sql.append(" or ");
			}
		}
		sql.append(") ");
		// ============ ѧ�� end =============
		if (!StringUtils.isBlank(model.getJgid())) {
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}

		boolean flag = false;
		String num = dao.getOneRs(sql.toString(), params
				.toArray(new String[params.size()]), "num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * ��ѯ
	 */
	public Map<String, String> viewOneYlbxglList(String xh, String jgid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select  a.* from (select case ");
		sql.append(" when (to_number(nvl(a.nj, 0)) + ");
		sql.append(" to_number(nvl(a.xz, 0)) - ");
		sql.append(" greatest(to_number(bynd), ");
		sql.append(" to_number(to_char(sysdate, 'yyyy')))) < 0 then 0 ");
		sql.append(" else ");
		sql.append(" to_number(nvl(a.nj, 0)) + ");
		sql.append(" to_number(nvl(a.xz, 0)) - ");
		sql.append(" greatest(to_number(bynd), ");
		sql.append(" to_number(to_char(sysdate, 'yyyy'))) ");
		sql.append(" end xjnx, ");
		sql.append(" a.*, b.* ");
		sql.append(" from (select a.xh, a.xm, a.xb, a.sfzh, a.csrq, a.bjmc, a.xydm, a.xymc, a.zymc, a.zydm, a.bjdm, a.nj, a.xz ");
		sql.append(" from view_xsjbxx a where a.xh = ? ) a ");
		sql.append(" left join (select a.zd8, a.zd21,a.zd22,nvl(substr(to_char(a.zd22), 0, 4), 0) bynd, a.zd23, a.jgid, a.xh bxh, ");
		sql.append(" count(1) over(partition by a.xh order by a.sqsj desc) rs ");
		sql.append(" from xg_rcsw_ylbx_jgb a where a.jgid = ? ) b ");
		sql.append(" on a.xh = b.bxh ");
		sql.append(" and b.rs = 1) a ");
		return dao.getMapNotOut(sql.toString(), new String[] { xh, jgid});
	}

	/**
	 * ���޸�ҳ��ר�á��ų���ǰ��¼����ѯӦ������
	 */
	public String viewYjnum(String xh, String jgid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" case when a.anum > a.bnum then a.bnum else a.anum end yjnum ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" case when a.bynd <= to_char(sysdate,'yyyy') then 0 else (a.bynd - to_char(sysdate,'yyyy')) end anum, ");
		sql.append(" (a.bynd - b.jgnd - b.tbnx) bnum ");
		sql.append(" from ( ");
		sql.append(" select a.xh, ");
		sql.append(" xzxm bynd from view_xsxxb a where (a.sfzx='��У' or a.sfzx is null) and a.xh=? ");
		sql.append(" ) a left join ( ");
		sql.append(" select ");
		sql.append(" a.xh bxh, ");
		sql.append(" count(1) over (partition by a.xh order by a.xn desc) rs, ");
		sql.append(" substr(nvl(a.xn,0),0,4) jgnd, ");
		sql.append(" nvl(a.zd2,0) tbnx ");
		sql.append(" from xg_rcsw_ylbx_jgb a ");
		sql.append(" where a.jgid <> ? ");
		sql.append(" ) b on a.xh=b.bxh ");
		sql.append(" where (b.rs= 1 or b.rs is null) ");
		sql.append(" ) a ");
		return dao.getOneRs(sql.toString(), new String[] { xh, jgid }, "yjnum");
	}

	/**
	 * ���鿴ҳ��ר�á���ѯ��ʷ����
	 */
	public List<HashMap<String, String>> viewLsList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" a.*, ");
		sql.append(" case when length(a.zd8) > 10 then substr(a.zd8, 0, 10)||'...' else a.zd8 end str ");
		sql.append(" from xg_rcsw_ylbx_jgb a ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by a.zd22 desc ");
		return dao.getListNotOut(sql.toString(), new String[] { xh});
	}
	/**
	 * ���鿴ҳ��ר�á���ѯ�������ռ�¼
	 */
	public List<HashMap<String, String>> viewOneList(String xh,String jgid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" a.*, ");
		sql.append(" substr(a.zd8,0,10) str ");
		sql.append(" from xg_rcsw_ylbx_jgb a ");
		sql.append(" where a.xh = ? and a.jgid = ?");
		return dao.getListNotOut(sql.toString(), new String[] { xh ,jgid});
	}

}
