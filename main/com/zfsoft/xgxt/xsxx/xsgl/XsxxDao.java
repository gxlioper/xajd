package com.zfsoft.xgxt.xsxx.xsgl;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.newp.StringUtil;

public class XsxxDao extends SuperDAOImpl<XsxxForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XsxxForm model)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * ͨ��ѧ�Ų�ѯѧ��
	 * @param model
	 * @param xhs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageList(XsxxForm model,String xhs[])
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("select * from (select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,a.bjdm,zybj,zybjmc,t2.sydm,t2.symc,b.fdyxm from view_xsjbxx a ");
		sql.append(" left join XG_XTWH_SYBJGLB t1 on a.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
		sql.append(" left join ");
		sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
		sql.append(" on a.bjdm = b.bjdm ");
		sql.append(" where 1=1  ");
		if(xhs.length>0){
			sql.append(" and (  ");
			for(int i=0;i<xhs.length;i++){
				if(i==0){
					sql.append(" xh = '" + xhs[i] + "' ");
				}else {
					sql.append(" or xh = '" + xhs[i] + "' ");
				}
			}
			sql.append(" )");
		}else {
			sql.append(" and 1 = 0 ");
		}
		sql.append(") where 1=1 ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}


	public List<HashMap<String, String>> getPageList(XsxxForm model, User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = "";
		//���ݴ�ѧ˼������-��¼ά��-̸����¼����ʦ��ϵͳ�����̸����ѧ��������Ϊ����ѧԺ��ѧ�����������ڰ�����������ѧ��
		if("10351".equals(Base.xxdm) && "szdw_thjl.do?method=thjlDetail".equals(model.getGotoPath())){
			searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "a", "xydm");
		}else{
			if("szdw_jfxx.do?method=add".equals(model.getGotoPath())){
				user.setUserStatus("sy");
			}
			searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");
		}

		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,a.bjdm,zybj,zybjmc,t2.sydm,t2.symc,b.fdyxm from view_xsjbxx a ");
		sql.append(" left join XG_XTWH_SYBJGLB t1 on a.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
		sql.append(" left join ");
		sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
		sql.append(" on a.bjdm = b.bjdm ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * ѧ�������������룬������������Ա��
	 */
	public List<HashMap<String, String>> showStudentsKnsrdsqBjpy(XsxxForm model, User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsjbxx a where ");
		sql.append(" not exists (select 1 from xg_xszz_new_knsrd_bjpyxz b where a.xh=b.xh and b.tjzt='1') ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:������¼���� ���ݴ�ѧ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����04:30:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXlfdlr(XsxxForm model,
			User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql
				.append("bjmc,xydm,zydm,bjdm from (select b.* from XG_XLJK_XLWJYJ_XLWJYJK a left join view_xsjbxx b on a.xh = b.xh ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:������¼���� ���ݴ�ѧ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����04:30:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXxsbjggl(XsxxForm model,
			User user, String sblx) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if (StringUtils.equals("0", sblx) || StringUtils.equals("2", sblx)) {
			sql.append("select xh,xm,xb,xymc,zymc,");
			sql
					.append("bjmc,xydm,zydm,bjdm from (select b.* from XG_XLJK_XLWYGL_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh where a.lx = '"
							+ (sblx.equals("0") ? "0" : "1")
							+ "') t where 1=1 ");
		} else if (StringUtils.equals("1", sblx)) {
			sql.append("select xh,xm,xb,xymc,zymc,");
			sql
					.append("bjmc,xydm,zydm,bjdm from (select b.* from XG_GYGL_NEW_GYGLRYB a left join view_xsjbxx b on a.xh = b.xh ) t where 1=1 ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @����:�ҽ���ʦ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����04:30:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForJjlsk(XsxxForm model,
			User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*, t6.xm , t6.xydm , t6.xymc, t6.bjdm , t6.bjmc , t6.zymc , t6.zydm, t6.nj , t6.xb  from ");
		sql.append("XG_JJGL_JJLSJGB t1 ,");
		sql.append("view_xsjbxx t6 where  ");
		sql.append("t1.xh = t6.xh ");

		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/**
	 * @����:���Ź���ģ��ѧ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����05:14:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated ���� ��ʹ��getPageList����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForDtgl(XsxxForm model,
			User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ȡ��Ԣ�������ѧ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-9 ����01:55:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForGygl(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* ,a.ldmc || '_' || a.qsh || '_' || a.cwh cwxx from xg_view_gygl_new_xszsgl a where sfzs='��' ");
		if(StringUtils.isNotNull(model.getSsld())){
			sql.append("and a.lddm = '"+model.getSsld()+"' ");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByGyfdy);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ԣ�������ѧ���������춯��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-14 ����02:34:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForGyglSsyd(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql
		.append("select a.* ,case when a.ldmc is not null and a.qsh is not null and a.cwh is not null then a.ldmc || '_' || a.qsh || '_' || a.cwh else '' end as cwxx from xg_view_gygl_new_xszsgl a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByGyfdy);
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		this.setKey("xh");
		this.setTableName("xsxxb");
		this.setClass(XsxxForm.class);
	}

	/**
	 * ��ѧ�Ų�ѯѧ��������Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxx(String xh) {

		String sql = "select * from view_xsjbxx where xh = ?";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * ��ѧ�Ų�ѯѧ����ϸ������Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMore(String xh) throws Exception {
		// zjuxqmc У��
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc, b.pyccmc pyccmc ,");
		sql.append(" to_char(sysdate,'yyyy') - to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyy') nl,case when c.cwh is not null then c.ldmc||'��'||c.qsh||'��'||c.cwh||'�Ŵ�' else '' end zsqsmc,c.qsdh qslxdh,c.xqdm,d.zwmc ");
		//������ͨ��ѧ
		if("10698".equals(Base.xxdm)){
			sql.append(" ,j.sydm,j.symc,t1.fdyxm fydmc,case when h.sfgmbx = '0' then '��' when h.sfgmbx = '1' then '��' else 'δά��' end sfgmbx");
		}
		sql.append(" from view_xsxxb a");
		sql.append(" left join (select xh,zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid) d on a.xh = d.xh");
		//������ͨ��ѧ
		if("10698".equals(Base.xxdm)){
			sql.append(" left join xg_xtwh_sybjglb k  on a.bjdm = k.bjdm");
			sql.append(" left join xg_xtwh_sydmb j  on k.sydm = j.sydm");
			sql.append(" left join XG_QGZX_QGZXXSB h  on h.xh = a.xh");
		}
		sql.append(" left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm left join view_xg_gygl_new_cwxx c on a.xh = c.xh ");
		sql.append(" left join ");
		sql.append(" (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm) fdyxm from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) t1 ");
		sql.append(" on a.bjdm = t1.bjdm ");
		sql.append(" where a.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}

	/**
	 * ��ѧ�Ų�ѯѧ����ϸ������Ϣ����У����
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxZjsMore(String xh) throws Exception {
		// zjuxqmc У��
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,(select xqmc from dm_zju_xq c where a.yxdm = c.dm) zjuxqmc, b.pyccmc pyccmc ,");
		sql.append(" to_char(sysdate,'yyyy') - to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyy') nl,case when c.cwh is not null then c.ldmc||'��'||c.qsh||'��'||c.cwh||'�Ŵ�' else '' end zsqsmc,c.qsdh qslxdh ");
		// ����ʯ�ʹ�ѧ
		if("10220".equals(Base.xxdm)) {
			sql.append(" ,substr(a.rxrq,'0','4') ry, substr(a.rxrq,'6','2') rm, substr(a.rxrq,'9','2') rd, ");
			sql.append(" (to_char(to_date(a.rxrq, 'yyyy-mm-dd'), 'yyyy') + 4) yxnf ");
		}
		sql.append(" from view_xsjbxx a");	
		sql.append(" left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm left join view_xg_gygl_new_cwxx c on a.xh = c.xh where a.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}

	/**
	 * ��ѧ�Ų�ѯѧ����ϸ������Ϣ-��ס����Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMoreWithZSXX(String xh) {

		String sql = "select c.ldmc , c.qsh , c.cwh ,  c.ldmc||' '||c.qsh as zsxx, a.* , b.pyccmc pyccmc , to_char(sysdate,'yyyy') - to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyy') nl from view_xsxxb a left join xg_xsxx_pyccdmb b on a.pycc = b.pyccdm left join  "
				+ "(select aa.xh, aa.lddm,aa.qsh,aa.cwh,bb.ch , cc.ldmc from xg_gygl_new_cwxxb aa left join xg_gygl_new_qsxxb bb on aa.lddm = bb.lddm and aa.qsh = bb.qsh left join xg_gygl_new_ldxxb cc  on aa.lddm = cc.lddm) c on c.xh = a.xh "
				+ "where a.xh = ?";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * @����:��ȡ���Ź���ѧ����ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����05:19:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated ��ʹ��ͨ��ѧ����Ϣ
	 * @param xh
	 * @return HashMap<String,String> ��������
	 */
	public HashMap<String, String> getXsjbxxMoreForDtgl(String xh) {
		// ������Ϣ��ͼ����������⣨�������ø��ĺ�ش��ڲ������ݻ�ȡ���������Ļ�ʹ�ù�����ͼ
		String sql = "select * from view_xsxxb where xh = ?";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * 
	 * @����: ��ѯ��Ƭ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-20 ����10:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return InputStream ��������
	 * @throws
	 */
	public InputStream getPhotoStream(String xh) {

		String sql = "select zp from xszpb where xh = ?";

		try {
			return dao.getInputStream(sql, new String[] { xh });
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @����:��ѯ�߿���Ƭ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-2 ����10:52:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * InputStream �������� 
	 * @throws
	 */
	public InputStream getGkPhotoStream(String xh) {

		String sql = "select xszp from xszpb where xh = ?";

		try {
			return dao.getInputStream(sql, new String[] { xh });
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @����: ѧ����Ϣѧ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-12 ����09:45:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<String> ��������
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getAllXzList() {

		String sql = "select distinct xz , xz||'����' xzmc from xsxxb where xz is not null ";

		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @����: ѧ�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-12 ����10:42:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXlccList() {
		String sql = "select distinct t1.xldm,t2.xlmc from xsxxb t1 left join xldmb t2 on t2.xldm=t1.xldm where t1.xldm is not null";

		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @����: �������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-12 ����10:42:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPyccList() {
		String sql = "select distinct t1.pycc pyccdm,t2.pyccmc from xsxxb t1 left join xg_xsxx_pyccdmb t2 on t2.pyccdm=t1.pycc where t1.pycc is not null";

		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @����:����������������
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-4 ����02:20:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getMzList() {
		String sql = "select mzdm,mzmc from mzdmb";
		return dao.getListNotOut(sql, new String[] {});

	}

	/**
	 * 
	 * @����:����������Դ��ʡ������
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-4 ����02:20:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSfList() {
		String sql = "select syddm,sydmc from syddmb where syddm like '%0000'";
		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * @����:�ж�ѧ���Ƿ����(��������У��)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-30 ����05:17:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public boolean getCheckStuExists(String xh) {

		String sql = " select count(1) num from xsxxb where xh = ? ";

		return Integer.valueOf(dao.getOneRs(sql, new String[] { xh }, "num")) > 0;
	}

	/**
	 * @����: ����ѧ��,ѧ�����ѧ���ɼ�list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-30 ����02:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getCjlist(String xh, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append(" select * from view_zhhcjb where xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}
	
	/**
	 * @����:����ѧ�ź�ѧ�����ѧ���ɼ�����߷֡���ͷ֡�ƽ���֡�����������ѧϰ�ɼ��� �㽭����ְҵѧԺ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-13 ����01:22:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXxcj(String xh, String xn, String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select ");
		sql.append(" max(to_number(floatcj)) maxcj, ");
		sql.append(" min(to_number(floatcj)) mincj, ");
		sql.append(" cast(avg(to_number(floatcj)) as decimal(10,2)) avgcj, ");
		sql.append(" sum(bkcjnum) bkcjnum, ");
		sql.append(" replace(wm_concat(xxcj),';','��') xxcjstr ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(" kcmc||'��'||cj xxcj, ");
		sql.append(" to_number(cj) floatcj, ");
		sql.append(" decode(bkcj,null,0,1) bkcjnum ");
		sql.append(" from cjb where xh = ? ");
		params.add(xh);
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
		sql.append(" order by xkkh ");
		sql.append(" ) a ");
		
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}

	/**
	 * @����:������-����W����Ϣ����ע��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����09:06:14
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTsxs(XsxxForm model,
			User user) throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from view_xlzx_tsxsxx a where 1=1 ");
		if(!"10704".equals(Base.xxdm)){
			sql.append(" and a.gzzt='1' ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @����:ȡ��ѡ���ѧ��ѧ���б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����02:41:14
	 * @param xzxsKey
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectedStudents(String xzxsKey) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select xh ");
		sql.append(" from XG_XSXX_XZXSLSB ");
		sql.append(" where key = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { xzxsKey });
	}

	/**
	 * @����:ȡ��ѡ���ѧ��ѧ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����02:41:14
	 * @param xzxsKey
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public int getSelectedCount(String xzxsKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(xh) counts ");
		sql.append(" from XG_XSXX_XZXSLSB ");
		sql.append(" where key = '" + xzxsKey + "'");
		return dao.getOneRsint(sql.toString());
	}

	/**
	 * @����: ѡ��ѧ��(��ҳ)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����03:26:34
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSelectStudentsList(XsxxForm model,
			User user) throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select * ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where ");

		// ѡ���ѧ����𣨴�ѡ��/��ѡ��
		String xzxs = model.getXzxs();
		if ("yxzxs".equals(xzxs)) {
			sql.append(" exists ");
		} else {
			sql.append(" not exists ");
		}
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB a ");
		sql.append(" where a.key = '" + model.getXzxsKey() + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) )a ");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @����: ѡ��ѧ��(ȫ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����03:26:34
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSelectAllStudentsList(
			XsxxForm model, User user) throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select xh ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where  ");

		// ѡ���ѧ����𣨴�ѡ��/��ѡ��
		String xzxs = model.getXzxs();
		if ("yxzxs".equals(xzxs)) {
			sql.append(" exists ");
		} else {
			sql.append(" not exists ");
		}
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB a ");
		sql.append(" where key = '" + model.getXzxsKey() + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) ");
		sql.append(" and 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * @throws Exception
	 * @����: ����ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����05:17:08
	 * @param values
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean insertSelect(String values, String xzxsKey) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append("  XG_XSXX_XZXSLSB (key,xh) ");
		sql.append(" select '" + xzxsKey
				+ "' as key, a.xh from xsxxb a where a.xh in( ");
		String[] xhs = values.split(",");
		for (int i = 0; i < xhs.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("'" + xhs[i] + "'");
		}
		sql.append(" )   ");
		sql.append(" and not exists ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB b ");
		sql.append(" where key = '" + xzxsKey + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * @����: ����ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����10:30:00
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean insertSelect(XsxxForm model, User user, String xzxsKey)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append("  XG_XSXX_XZXSLSB (key,xh) ");
		sql.append(" select '" + xzxsKey
				+ "' as key, a.xh from xsxxb a where a.xh in( ");

		sql.append(" select b.xh ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where  ");
		sql.append(" not exists ");
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB c ");
		sql.append(" where c.key = '" + xzxsKey + "'");
		sql.append("  and c.xh = b.xh ");
		sql.append(" ) ");
		sql.append(" and 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" ) ");

		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * @����: ����ɾ��ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����11:13:20
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean delSelect(XsxxForm model, User user, String xzxsKey)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" delete from  ");
		sql.append("  XG_XSXX_XZXSLSB ");
		sql.append("  where xh in( ");

		sql.append(" select b.xh ");
		sql.append(" from view_xsbfxx b ");
		sql.append(" where  ");
		sql.append("  exists ");
		sql.append(" ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB c ");
		sql.append(" where c.key = '" + xzxsKey + "'");
		sql.append("  and c.xh = b.xh ");
		sql.append(" ) ");
		sql.append(" and 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" ) ");

		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * @����: ����ɾ��ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����11:14:57
	 * @param values
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean delSelect(String values, String xzxsKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from ");
		sql.append("  XG_XSXX_XZXSLSB a ");
		sql.append(" where a.xh in( ");
		String[] xhs = values.split(",");
		for (int i = 0; i < xhs.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("'" + xhs[i] + "'");
		}
		sql.append(" )   ");
		sql.append(" and exists ( select 1 ");
		sql.append(" from XG_XSXX_XZXSLSB b ");
		sql.append(" where key = '" + xzxsKey + "'");
		sql.append("  and a.xh = b.xh ");
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * @����: ɾ��ѡ��(���У�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����02:15:55
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean delSelectAll(String xzxsKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from ");
		sql.append("  XG_XSXX_XZXSLSB ");
		sql.append(" where key = ? ");
		return dao.runUpdate(sql.toString(), new String[] { xzxsKey });
	}

	/**
	 * @����: �߼���ѯģʽ(ȫ��ѧ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-13 ����11:46:32
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListAll(XsxxForm model,
			User user) throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsbfxx a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:�������ѧԺ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-26 ����03:02:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList() {
		String sql = " select distinct xydm,xymc from view_njxyzybj  order by xymc ";
		return dao.getListNotOut(sql, new String[] {});
	}

	/**
	 * 
	 * @����:����ѧ�Ż�ȡ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-16 ����07:49:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByXh(String xh) {

		String sql = "select t.* from (select a.bjdm , to_char(WM_CONCAT(b.xm) over (partition by a.bjdm )) bzrxx , row_number() over(partition by a.bjdm order by a.bjdm) as dd from bzrbbb a left join view_fdyxx b on a.zgh = b.zgh) t where t.dd = 1 and t.bjdm = (select bjdm from xsxxb c where c.xh = ?) ";

		return dao.getMapNotOut(sql, new String[] { xh });
	}

	/**
	 * 
	 * @����:���ݰ༶�����ȡ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-16 ����07:49:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByBjdm(String bjdm) {
		String sql = "select t.* from (select a.bjdm , to_char(WM_CONCAT(b.xm) over (partition by a.bjdm )) bzrxx , row_number() over(partition by a.bjdm order by a.bjdm) as dd from bzrbbb a left join view_fdyxx b on a.zgh = b.zgh) t where t.dd = 1 and t.bjdm = ? ";

		return dao.getMapNotOut(sql, new String[] { bjdm });
	}
	
	
	/**
	 * 
	 * @����: ѧ������
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-12-3 ����03:59:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXslxList() {
		String sql = "select distinct t1.xslx xslxdm,t2.mc xslxmc from xsxxb t1 left join stu_lbdmb t2 on t2.dm=t1.xslx where t1.xslx is not null";

		return dao.getListNotOut(sql, new String[] {});
	}
	/**
	 * 
	 * @����:��ȡ�������ڵ����һ�����б�������Ͽ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-25 ����09:30:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSydxList() {
		String sql = "select * from dmk_qx where qxdm like '50%'and qxdm not like '%00'order by qxdm asc";

		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * @����: �㽭�������ѧ��,ѧ�����ѧ��ƽ���ɼ�
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����02:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String  getZjjcPjcj(String xh, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append(" select nvl(avg(cj),0) pjcj from cjb where xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
        sql.append(" and kcxz like '%����%' group by xh ");
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "pjcj");
	}
	
	/**
	 * @����: �㽭�������ѧ��,ѧ�����ѧ�������������ͳɼ�
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����02:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String  getZjjcZdcj(String xh, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		sql.append(" select nvl(min(cj),0) cj from cjb where xh = ? ");
		
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ? ");
			params.add(xq);
		}
        sql.append(" and kcxz not like '%����%' group by xh ");
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "cj");
	}
	/**
	 * 
	 * @����:����ũҵ������ѧ�Ż�ȡƽ���ɼ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-11 ����10:26:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public HashMap<String,String>  getHznyPjcjWithPm(String xh, String xn,String xq) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select * from (select xh,pjcj,rank() over(partition by bjdm order ");
		sql.append("  by pjcj asc) pjcjpm from ( ");
		sql.append("select a.xh,b.bjdm, substr(avg(cj), 1, 4) pjcj  ");
		sql.append(" from view_zhhcjb a ");
		sql.append(" left join xsxxb b on a.xh = b.xh where 1=1 ");
		
		if(!StringUtil.isNull(xn)){
			sql.append(" and xn = ? ");
			params.add(xn);
		}
		if(!StringUtil.isNull(xq)){
			sql.append("and  xq = ? ");
			params.add(xq);
		}
		
		sql.append("  and b.bjdm in (select bjdm from xsxxb ");
		sql.append(" where xh = ?)  group by a.xh,b.bjdm ))");
		sql.append(" where xh = ? ");
		params.add(xh);
		params.add(xh);
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[params.size()]));
	}
	
	/**
	 * 
	 * @����:����ʯ��ѧ��֤��ӡʡ���ؽ�ȡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-9 ����02:24:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsjtSsx(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select (select c.qxmc from dmk_qx c ");
        sql.append(" where c.qxdm = substr(a.hkszd, 0, 2) || '0000') || ");
        sql.append(" (select d.qxmc from dmk_qx d "); 
        sql.append(" where d.qxdm = substr(a.hkszd, 0, 4) || '00' ");
        sql.append(" and a.hkszd <> substr(a.hkszd, 0, 2) || '0000') || ");
        sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = a.hkszd ");
        sql.append(" and a.hkszd <> substr(a.hkszd, 0, 2) || '0000' ");
        sql.append(" and a.hkszd <> substr(a.hkszd, 0, 4) || '00') hkszd from xsxxb a ");
        sql.append(" where xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}
	
	/**
	 * 
	 * @����:����ʯ��ѧ��֤��ӡ���»������ȡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-9 ����02:39:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsHcxx(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.xh,t1.txsj,t1.xn,t1.xq,t1.ccqdz,t1.cczdz,t1.bz,(t1.ccqdz || '-' || t1.cczdz) hcccqjmc,t2.xqmc,t3.lxmc hcyhklxmc ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjjgb t1 ");
		sql.append(" left join xqdzb t2 ");
		sql.append(" on t1.xq = t2.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t3 ");
		sql.append(" on t1.hcyhklx = t3.lxdm ");
		sql.append(" where t1.xh = ? and rownum < 2 ");
		sql.append(" order by txsj desc ");
       
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}

	/** 
	 * @����:����Ƽ��õ�ס����Ա
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����04:17:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForZzdgl(XsxxForm model,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from view_xg_gygl_new_cwxx b where a.xh=b.xh and b.xh is not null) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @����:�õ�����Ϣ���ѧ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-2 ����11:06:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXnwxjkhk(XsxxForm model,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select xh,xm,xb,xymc,zymc,");
		sql.append("bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zdgxh_wxjk_jgb b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����: ��ƽ������ѧ���Ѵ���Ա����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����04:56:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForHkxx(XsxxForm model,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,");
		sql.append(" bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zdgxh_ypzl_jgb b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����: ������ѧ���У԰�ش���Ѵ�����Ա����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-8-22 ����01:49:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForXydHkwh(XsxxForm model,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,");
		sql.append(" bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zxdk_xydkjgb b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����: ��Դ���Ѵ���Ա����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-8-22 ����02:00:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForSydHkwh(XsxxForm model,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select xh,xm,xb,xymc,zymc,");
		sql.append(" bjmc,xydm,zydm,bjdm from view_xsbfxx a where ");
		sql.append(" exists (select 1 from xg_zxdk_syddk b where a.xh=b.xh) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����: ��ҵ������Ա��Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����04:56:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForByHkxx(XsxxForm model,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm,a.nj,a.xz,t2.hkbj from view_xsbfxx a ");
		sql.append(" left join xg_zxdk_tqhkjgb t2 on a.xh = t2.xh ");
		sql.append(" where exists (select 1 from xg_zxdk_xydkjgb b where a.xh = b.xh) and ");
        sql.append(Base.currNd);
        sql.append(" - nvl(a.nj, 0) >= nvl(a.xz, 0) ");
        sql.append(" and (t2.hkbj <> 'ȫ������' or t2.hkbj is null) ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:ѧ��������ò���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-12 ����01:52:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXszzmmList() {
		String sql = "select distinct t1.zzmm zzmmdm,t2.zzmmmc from xsxxb t1 left join zzmmdmb t2 on t2.zzmmdm=t1.zzmm where t1.zzmm is not null";

		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * 
	 * @����:ѧ��ְ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-12 ����01:52:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXszwList() {
		String sql = "select distinct t1.zwid,t2.zwmc from xg_szdw_xsgb_dwb t1 left join xg_szdw_xsgb_zwb t2 on t2.zwid=t1.zwid where t1.zwid is not null";

		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * 
	 * @����:רҵ������Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-30 ����04:06:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyjList() {
		
		String sql = "select distinct t1.rddjdm,t2.rddjmc from xg_pjpy_grzyjxx t1 left join xg_pjpy_zyjrddjdmb t2 on t2.rddjdm=t1.rddjdm where t1.rddjdm is not null";
		return dao.getListNotOut(sql, new String[] {});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����10:25:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> transformYear_Month(String csrq){
		StringBuilder sql = new StringBuilder();
		sql.append("  select to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy') csn,");
		sql.append("  to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'mm') csy");
		sql.append(" from dual");
		return dao.getMapNotOut(sql.toString(), new String[]{csrq,csrq});
	}
	
	/**
	 * 
	 * @����:��ȡ��ͥ��Ա��Ϣ(����)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:36:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.mc cygxmc");
		sql.append(" from XG_XSZZ_NEW_JTCYB t");
		sql.append(" left join XSZZ_JTCYGXB t1");
		sql.append(" on t.cygx = t1.dm");
		sql.append(" where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getFather(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.mc cygxmc");
		sql.append(" from XG_XSZZ_NEW_JTCYB t");
		sql.append(" left join XSZZ_JTCYGXB t1");
		sql.append(" on t.cygx = t1.dm");
		sql.append(" where t.xh = ? and t1.mc like '%����%'");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ȡĸ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getMother(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.mc cygxmc");
		sql.append(" from XG_XSZZ_NEW_JTCYB t");
		sql.append(" left join XSZZ_JTCYGXB t1");
		sql.append(" on t.cygx = t1.dm");
		sql.append(" where t.xh = ? and  t1.mc like '%ĸ��%'");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/** 
	 * @����:�Ϻ�Ϸ��ѧԺ����ѧ���б�ѡ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-23 ����05:24:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForTsxsByTy(XsxxForm model,String xn,String xq,String lxdm,
			User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('��ѧӢ��һ��','��ѧӢ������') and a.xh = b.xh and b.xn='");
			sql.append(xn);
			sql.append( "') kc1, ");
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('��ѧӢ�����','��ѧӢ���ļ�') and a.xh = b.xh and b.xn='");
			sql.append(xn);
			sql.append( "') kc2, ");
			sql.append(" (select max(cj) from xsdjksb b where a.xh = b.xh and b.djksmc in ('CET4','CET-4') ");
			sql.append(" ) cet4,");
		}
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm,a.nj,a.xz from view_xsbfxx a ");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" where not exists (select 1 from xg_pjpy_new_tsxsb b where a.xh = b.xh and xn ='"+xn+"' and lxdm = '"+lxdm+"')) a ");
		}else{
			sql.append(" where not exists (select 1 from xg_pjpy_new_tsxsb b where a.xh = b.xh and xn ='"+xn+"' and xq = '"+xq+"' and lxdm = '"+lxdm+"')) a ");
		}		
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����: �㽭��ѧ�³�𷢷Ų�ѯ����ѧ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-20 ����05:39:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsCjffAdd(XsxxForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm from view_xsjbxx a ) where 1= 1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����: ����֯��ϵת��ѧ��ҳ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����08:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsFordzzgxzc(XsxxForm model, User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("  select *");
		sql.append("  from (select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm, b.jc");
		sql.append("  from view_xsjbxx a");
		sql.append("  left join zzmmdmb b");
		sql.append("  on a.zzmmmc = b.zzmmmc) t");
		sql.append("  where t.jc in ('Ԥ����Ա', '��ʽ��Ա')");
		sql.append("  and not exists");
		sql.append("  (select 1 from xg_dtjs_zzgxzc_zzgxzcsqb sq where sq.xh = t.xh)");
		sql.append("  and not exists");
		sql.append("  (select 1 from xg_dtjs_zzgxzc_zzgxzcjgb jg where jg.xh = t.xh)");
		sql.append("  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @��������ȡDMK_QX ʡ���������б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��13�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getQxSList() {
		String sql = "select * from DMK_QX where qxdm like '__0000' order by qxdm asc";
		return dao.getListNotOut(sql, new String[] {});
	}
	
	/** 
	 * @����:�����Ƽ���ѧ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-20 ����09:44:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXiAnKjGwSq(XsxxForm model, User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm");
		sql.append(" from view_xsjbxx a");
//		sql.append(" where not exists (select 1 from (select xh from xg_qgzx_new_xsgwxxb where zgzt = 'tg' and to_date(to_char(add_months(to_date(tgsj,'yyyy-MM-dd'),12),'yyyy-MM-dd'),'yyyy-MM-dd') > to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')) d where a.xh = d.xh))t");
		sql.append(" ) t");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @����:��ȡ����Ա��Ϣ����ȡһ������Ա��Ϣ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-17 ����11:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getFdyxxByXh(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.*,rownum rn");
		sql.append(" from (select a.zgh, c.xm fdyxm, c.lxdh fdylxdh from fdybjb a");
		sql.append(" left join fdyxxb c on a.zgh = c.zgh");
		sql.append(" where exists (select 1 from xsxxb b where a.bjdm = b.bjdm  and b.xh = ?)");
		sql.append(" order by xm asc) a)");
		sql.append(" where rn = 1");
		return dao.getMapNotOut(sql.toString(), new String[] { xh });
	}
	
	/** 
	 * @����:��ȡȫ��ѧ���б�(���һ���ר�ã����칤�̴�ѧ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-23 ����10:54:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getStudentsForQshr(XsxxForm model,User user)
	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select a.xh, a.xm, a.xb, a.nj, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, c.ldmc, b.qsh, b.cwh");
		sql.append(" from view_xsjbxx a");
		sql.append(" left join xg_gygl_new_cwxxb b on a.xh = b.xh");
		sql.append(" left join xg_gygl_new_ldxxb c on b.lddm = c.lddm) t");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����: ѡ��ѧ���б��Ÿɲ���
	 * @���ߣ� ����[���ţ�1282]
	 * @���ڣ�2018-5-19 ����10:35:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForTgb(XsxxForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select xh, xm, xb, xymc, zymc, bjmc, xydm, zydm, bjdm");
		sql.append(" from view_xsjbxx a");
		sql.append(" where not exists (select 1 from xg_tgbgl_tgbjgb b where a.xh = b.xh)");
		sql.append(" ) t");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
}
