/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-31 ����09:36:13 
 */  
package com.zfsoft.xgxt.xsxx.yrgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: ѧ����Ϣģ��
	 * @�๦������: TODO(������һ�仰��������������) 
	 * @���ߣ� ����[����:1186]
	 * @ʱ�䣺 2016-1-5 ����09:49:10 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	public class YrglDao extends SuperDAOImpl<YrglForm> {
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YrglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YrglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from (select t1.guid,t1.xn,t1.xh, t2.xm, t2.nj, t2.xymc, t2.zymc,t2.xydm,t2.bjdm,t2.bjmc ");
		sql.append(" from xg_yrgl_jg t1 ");
		sql.append(" left join view_xsbfxx t2 ");
		sql.append(" on t1.xh = t2.xh) a ");
		sql.append(" where 1 = 1 ");
	    sql.append(searchTjByUser);
	    sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
		/*
		      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
		 */	
		@Override
		protected void setTableInfo() {
			super.setTableName("xg_yrgl_jg");
			super.setKey("guid");
			super.setClass(YrglForm.class);
		}
		//������ѯ
		public HashMap<String, String> getOneInfo(YrglForm t) throws Exception {
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select a.guid, a.xh, b.xm, b.nj, b.xymc, b.zymc, b.bjmc, a.xn ");
			sql.append(" from xg_yrgl_jg a ");
			sql.append(" left join view_xsbfxx b ");
			sql.append(" on a.xh = b.xh ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = ? ");
			return dao.getMapNotOut(sql.toString(), new String[]{});
		}
		public String checkExistForSave(YrglForm form){
				StringBuilder sql = new StringBuilder();
				List<String> params = new ArrayList<String>();
				String guid =  form.getGuid();
				sql.append(" select count(1) num from xg_yrgl_jg ");
				sql.append(" where xh = ? ");
				params.add(form.getXh());
				if(!StringUtils.isEmpty(guid)){
					sql.append(" and guid <> ? ");
					params.add(guid);
				}
				String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
				return num;
			}
		/**
		 * @����: ��������
		 * @���ߣ�����[���ţ�1186]
		 * @���ڣ�2015-10-14 ����08:30:10
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param t
		 * @param user
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getXshjgldcList(YrglForm t, User user)
		throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.guid, a.xh, b.xm, b.nj, b.xymc, b.zymc, b.bjmc, a.xn,a.sqly,a.sqsj ");
		sql.append(" from xg_yrgl_jg a ");
		sql.append(" left join view_xsbfxx b ");
		sql.append(" on a.xh = b.xh ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
		}
		public HashMap<String, String> getXn(String xh) throws Exception {

			StringBuffer sql = new StringBuffer();
			sql.append(" select xn from xg_yrgl_jg where xh = ? ");
			return dao.getMapNotOut(sql.toString(), new String[]{xh});
		}
	}
