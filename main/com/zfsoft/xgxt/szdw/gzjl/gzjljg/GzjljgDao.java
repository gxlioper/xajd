/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:32:03 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjljg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:32:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjljgDao extends SuperDAOImpl<GzjljgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjljgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjljgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc, ");
		sql.append("(case when length(t1.bz)>10 then substr(t1.bz,0,10)||'...' else t1.bz end)bzstr ");
		sql.append("from xg_gzjlgl_gzjljgb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh ");
		sql.append(" left join XG_SZDWX_LKSDMB t4 on t1.lks = t4.lksdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:������¼ͳ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-2 ����03:23:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGzjltjList(GzjljgForm model,List<HashMap<String, String>> lblist,
			User user) throws Exception {
		// ====================��������===================================
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "b",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sb = new StringBuilder();
		
		
		sb.append("select a.* from(select ");
		for (int i = 0 , j = lblist.size() ; i < j ; i++){
			sb.append("sum(t.cs").append(i).append(") gzlb").append(i).append(",");
		}
		sb.append("t.zgh,xm,xymc from  (select");
		for (int i = 0 , j = lblist.size() ; i < j ; i++){
			sb.append(" case when t.lbdm='").append(lblist.get(i).get("gzlbdm"))
			.append("' then 1 else 0 end cs")
			.append(i).append(",");
		}
		sb.append("t.zgh, b.xm,b.xymc  from xg_gzjlgl_gzjljgb t left join view_jsxx b on t.zgh=b.zgh where 1=1  ");
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" ) t where 1=1 ");
		sb.append("group by t.zgh ,t.xm,t.xymc)a ");
		return getPageList(model, sb.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getJsxxList(GzjljgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.zgh,a.xm,a.lxdh,decode(a.xb, '1', '��', '2', 'Ů', a.xb) xb, ");
		sql.append("b.bmdm xydm,b.bmmc xymc,c.zzmmmc from fdyxxb a  left join zxbz_xxbmdm b on a.bmdm = b.bmdm ");
		sql.append("left join zzmmdmb c on a.zzmm = c.zzmmdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public HashMap<String, String> getJsjbxx(String zgh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.zgh,a.xm,a.lxdh,decode(a.xb, '1', '��', '2', 'Ů', a.xb) xb, ");
		sql.append("b.bmmc xymc,c.zzmmmc from fdyxxb a  left join zxbz_xxbmdm b on a.bmdm = b.bmdm ");
		sql.append("left join zzmmdmb c on a.zzmm = c.zzmmdm where a.zgh=?");
		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}
	
	/**
	 * �жϵ��Ƿ�����д��¼
	 */
	public boolean checkExistForSave(GzjljgForm model) {
		String id = model.getJgid() == null ? "-1" : model.getJgid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_gzjlgl_gzjljgb where zgh=? and lbdm=? and gzsj=? and id<>? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getZgh(),model.getLbdm(),model.getGzsj(),id}, "num");
		return Integer.valueOf(num) > 0;
	}
	/**
	 * 
	 * @����:��������idɾ���������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-2 ����08:58:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgById(String id)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_gzjlgl_gzjljgb where sqid=? ");
		return dao.runUpdate(sql.toString(),new String[]{id});
	}
	/**
	 * 
	 * @����:��ȡ�û�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-3 ����08:54:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public String getXymc(String xydm)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select bmmc from zxbz_xxbmdm where bmdm=? ");
		return dao.getOneRs(sql.toString(), new String[]{xydm}, "bmmc");
	}
	@Override
	public GzjljgForm getModel(GzjljgForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.lbmc gzlbmc,t3.xm,t3.xydm,t3.xymc,t4.lksmc from xg_gzjlgl_gzjljgb t1 left join xg_gzjlgl_gzlbb t2 on t1.lbdm=t2.lbdm ");
		sql.append(" left join view_jsxx t3 on t1.zgh=t3.zgh left join xg_szdwx_lksdmb t4 on t1.lks=t4.lksdm where t1.jgid=?");
		return getModel(sql.toString(), new String[]{myForm.getJgid()});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(GzjljgForm.class);
		super.setTableName("xg_gzjlgl_gzjljgb");
		super.setKey("jgid");
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
	public List<HashMap<String, String>> getZghList(GzjljgForm model,User user)throws Exception{
		// ====================��������===================================
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t3",
				"xydm", "");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm,b.xymc from ( ");
		sql.append("select zgh from ( select t1.zgh,t.xm,t.xymc,t.gzsj,t.xydm,t.gzlbmc from  XG_GZJLGL_GZJLJGB t1 " + 
                   " left join  (select a.zgh,a.xm,a.xymc,a.xydm,b.gzsj,c.lbmc gzlbmc from view_jsxx a " + 
                   " left join XG_GZJLGL_GZJLJGB b on a.zgh=b.zgh " +
                   " left join xg_gzjlgl_gzlbb c on b.lbdm=c.lbdm ) t  on t.zgh = t1.zgh) t3");
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append("group by zgh ) a");
		sql.append(" left join  view_jsxx b on a.zgh = b.zgh  ");
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-4-19 ����02:15:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param searchModel
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGzjlListByZgh(String zgh,String lbmc,
			SearchModel searchModel, User user) throws Exception{
		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = null;
		if(StringUtil.isNull(lbmc)){
			inputV = new String[]{zgh};
		}else{
			inputV = new String[]{zgh,lbmc};
		}
		String[] s = SearchService.getTjInput(searchModel);
		int strLen1 = inputV.length;// �����һ�����鳤��
        int strLen2 = s.length;// ����ڶ������鳤��
        inputV = Arrays.copyOf(inputV, strLen1 + strLen2);// ����
        System.arraycopy(s, 0, inputV, strLen1, strLen2);// ���ڶ����������һ������ϲ�
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.lbmc gzlbmc ,t1.xydm,t1.xm,t1.xymc  from XG_GZJLGL_GZJLJGB a left join xg_gzjlgl_gzlbb b on a.lbdm = b.lbdm " +
				" left join view_jsxx t1 on t1.zgh = a.zgh " +
				" where a.zgh=? ");
		if(StringUtil.isNull(lbmc)){
			sql.append(" and b.lbmc <> 'ѧ����̸' ");
			sql.append(" and b.lbmc <> '��ϵ�ҳ�' ");
			sql.append(" and b.lbmc <> '�����߷�' ");
			sql.append(" and b.lbmc <> '�������' ");
		}else{
			sql.append("and b.lbmc=?  ");
		}

		

		
		
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}

}
