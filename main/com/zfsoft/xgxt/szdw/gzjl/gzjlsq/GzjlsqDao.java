/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:39:32 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:39:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlsqDao extends SuperDAOImpl<GzjlsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjlsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjlsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc, ");
		sql.append("(case when length(t1.bz)>10 then substr(t1.bz,0,10)||'...' else t1.bz end)bzstr,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ");
		sql.append("from xg_gzjlgl_gzjlsqb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh ");
		sql.append(" left join XG_SZDWX_LKSDMB t4 on t1.lks = t4.lksdm");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:16:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_gzjlgl_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	@Override
	public GzjlsqForm getModel(GzjlsqForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc from xg_gzjlgl_gzjlsqb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh left join xg_szdwx_lksdmb t4 on t1.lks=t4.lksdm where t1.sqid=?");
		return getModel(sql.toString(), new String[]{myForm.getSqid()});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(GzjlsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_gzjlgl_gzjlsqb");

	}
	
	//������
	public List<HashMap<String, String>> getLksList() {
		String sql = "select * from xg_szdwx_lksdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	//�õ�ѧ����Ϣ
	public List<HashMap<String, String>> getXsxxList(GzjlsqForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhArr();
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a");
		if(xhs.length>0){
		sql.append(" where a.xh not in(");
		for (int i = 0; i < xhs.length; i++) {
			if(i!=0){
				sql.append(", ");
			}
			sql.append("'"+xhs[i]+"' ");
		}
		sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/** 
	 * @����:���̸��������Ϣ(�㽭����)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-18 ����10:45:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getThdxList(String[] xhArr) {
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a");
		if(xhArr.length>0) {
			sql.append(" where a.xh in(");
			for (int i = 0; i < xhArr.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhArr[i]+"' ");
			}
			sql.append("))a");
		}
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	

}
