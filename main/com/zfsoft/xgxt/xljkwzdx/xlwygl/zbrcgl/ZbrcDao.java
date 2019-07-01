/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����11:57:23 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.jcsjwh.JcsjForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����11:57:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZbrcDao extends SuperDAOImpl<ZbrcForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZbrcForm t)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*, a.xqmc, " +
				" (select count(1) from ((select sbzbid from XG_XLJK_XLWYGL_XSSBSQB) union all (select sbzbid from XG_XLJK_XLWYGL_XLSBJGB)) where sbzbid = t.zbid) sqcount " +
				"from XG_XLJK_XLWYGL_ZBRCXXB t left join xqdzb a on t.xq = a.xqdm where 1=1 ");
		String lx = t.getZblx();
		if("0".equals(lx)){
			sql.append(" and t.zblx = '0' ");
		}else if("1".equals(lx)){
			sql.append(" and t.zblx = '1' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	//
	public HashMap<String, String> getZbrcxx(String zbid){
		String sql = "select t.*, a.xqmc, '1' ysbfs from XG_XLJK_XLWYGL_ZBRCXXB t left join xqdzb a on t.xq = a.xqdm where t.zbid = ? ";
		return dao.getMapNotOut(sql, new String[]{zbid});
	}
	
	/**
	 * 
	 * @����:
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-9 ����03:47:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zblx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZbrcListByLx(String zblx){
		String sql = "select t.zbid,t.zblx,t.zbzc,t.zbksrq,t.zbjsrq,t.zbksrq||' �� '||t.zbjsrq zbqzrq from XG_XLJK_XLWYGL_ZBRCXXB t where t.zblx = ? order by t.zbksrq asc ";
		return dao.getListNotOut(sql, new String[]{zblx});
	}
	
	/**
	 * @����: δ/���ϱ��༶
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-24 ����11:17:38
	 * @param model
	 * @param user
	 * @param sbbjlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String, String>> ��������
	 */
	public List<HashMap<String, String>> cxSbbj(ZbrcForm model, User user, String sbbjlx)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();
		
		sql.append(" select a.* from VIEW_NEW_DC_XSXX_JCSJWH_BJ a " );
		sql.append(" where ");		
		if("ysb".equals(sbbjlx)){
			sql.append(" exists ");
		}else if("wsb".equals(sbbjlx)){
			sql.append(" not exists ");
		}
		sql.append(" (select 1 from ( ");
		sql.append(" select bjdm from view_xsxxb b where b.xh in ( ");
		sql.append(" (select xh from XG_XLJK_XLWYGL_XSSBSQB where sbzbid=? and shzt not in ('0','3')) ");
		sql.append(" union all (select xh from XG_XLJK_XLWYGL_XLSBJGB where sbzbid=?) ");
		sql.append(" ) ) b where b.bjdm=a.bjdm) ");

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		params.add(model.getZbid());
		params.add(model.getZbid());
		params.addAll(Arrays.asList(inputV));
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZbrcForm t, User user)
			throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZbrcForm.class);
		super.setKey("zbid");
		super.setTableName("XG_XLJK_XLWYGL_ZBRCXXB");
	}
	
	/**
	 * 
	 * @����: Ψһ���жϣ����ӣ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-19 ����04:56:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ZbrcForm form) {
		String sql = "select count(1) num from XG_XLJK_XLWYGL_ZBRCXXB where xn = ? and xq = ? and zblx = ? and zbzc = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXn(),form.getXq(),form.getZblx(),form.getZbzc()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	/**
	 * 
	 * @����: Ψһ���жϣ��޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-20 ����02:14:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(ZbrcForm form) {
		String sql = "select count(1) num from XG_XLJK_XLWYGL_ZBRCXXB where xn = ? and xq = ? and zblx = ? and zbzc = ? and zbid <> ? ";
		String num = dao.getOneRs(sql, new String[]{form.getXn(),form.getXq(),form.getZblx(),form.getZbzc(),form.getZbid()}, "num");
		return Integer.valueOf(num)>0;
	}
	
}
