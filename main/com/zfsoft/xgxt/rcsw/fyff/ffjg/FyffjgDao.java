/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-10 ����10:17:21 
 */  
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡����÷��š������Ž��  ����ģ��
 * @�๦������: ���Ž��ά��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-10 ����10:17:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffjgDao extends SuperDAOImpl<FyffjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FyffjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ���÷��Ž����ѯ
	 */
	public List<HashMap<String, String>> getPageList(FyffjgForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.* from( ");
		sql.append("select t.*,to_char(decode(fffs,'����',to_date(ffsj,'yyyy-mm-dd hh24:mi:ss'),'����',to_date(ffsj,'yyyy-mm')) ,'mm')  yf, decode(fffs,'����',substr(ffsj,1,4),'����',substr(ffsj,1,4),ffsj) nd from view_new_dc_rcsw_fyffjg t  ");
		sql.append(")t1 where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	  
	
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_fyff_ffjgb");
		super.setKey("guid");
	}
	
	
	/**
	 * 
	 * @����:���������ظ����жϣ�ѧ�ţ�����ʱ�䣬������Ŀ������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����01:38:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(FyffjgForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from view_new_dc_rcsw_fyffjg ");
		sql.append(" where xh = ? and ffsj = ? and ffxmdm = ? and fftjdm = ?");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getFfsj(),form.getFfxmdm(),form.getFftjdm()}, "num");
		
		return num;
	}
	
	
	/**
	 * 
	 * @����:�޸ı����ظ����жϣ�ѧ�ţ�����ʱ�䣬������Ŀ������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����01:43:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(FyffjgForm form){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from view_new_dc_rcsw_fyffjg ");
		sql.append(" where xh = ? and ffsj = ? and ffxmdm = ? and fftjdm = ? and guid <> ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),
			form.getFfsj(),form.getFfxmdm(),form.getFftjdm(),form.getGuid()}, "num");
		
		return num;
		
	}
	
	
	/**
	 * 
	 * @����: ���÷��Ž�������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����01:53:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneFyffjgList(String guid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_new_dc_rcsw_fyffjg where guid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
		
	}
	
	/**
	 * 
	 * @����: ������Ŀ�����ȡ���ŷ�ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-29 ����08:05:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffxmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	
	public String getFffs(String ffxmdm){
		
		StringBuffer sb = new StringBuffer("select fffs from xg_rcsw_fyff_ffxmdmb where ffxmdm = ?");
		
		return dao.getOneRs(sb.toString(), new String[]{ffxmdm}, "fffs");
	}
	
	
	

}
