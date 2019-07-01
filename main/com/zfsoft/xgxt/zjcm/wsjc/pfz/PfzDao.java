/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-26 ����04:57:37 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ý������_������
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-2-26 ����04:57:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PfzDao extends SuperDAOImpl<PfzForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PfzForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PfzForm t, User user)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,decode(ssxq,'001','��ɳ','002','ͩ��') ssxqmc, nvl(b.yhCount,0) yhCount ");
		sql.append("from xg_zjcm_gygl_wsjc_pfz a left join ");
		sql.append("(select pfzid,count(1) yhCount from xg_zjcm_gygl_wsjc_pfz_pfcy group by pfzid) b ");
		sql.append("on a.pfzid=b.pfzid where 1=1 ");
		
		if(!StringUtils.isBlank(t.getPfzmc())){
			params.add(t.getPfzmc());
			sql.append(" and a.pfzmc like '%'||?||'%'");
		}
		
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(PfzForm.class);
		super.setKey("pfzid");
		super.setTableName("xg_zjcm_gygl_wsjc_pfz");

	}
	
	
	
	/**
	 * 
	 * @����:���ֳ�Ա�б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-25 ����07:43:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfcyList(PfzForm model, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.zgh,a.xm,decode(a.xb,'1','��','2','Ů',a.xb) xbmc, ");
		sql.append("decode(a.xb,'��','1','Ů','2',a.xb) xbdm,a.bmdm, ");
		sql.append("(select b.bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc from fdyxxb a) where 1=1 ");
		
		if("1".equals(model.getFpzt())){
			sql.append("and zgh in (select cyzgh from xg_zjcm_gygl_wsjc_pfz_pfcy where pfzid = ? )");
		}else{
			sql.append("and zgh not in (select cyzgh from xg_zjcm_gygl_wsjc_pfz_pfcy where pfzid = ? )");
		}
		sql.append(searchTj);
		
		//���Լ��Ĳ����͸߼���ѯ�����ϲ�
		String[] both = (String[]) ArrayUtils.addAll(new String[]{model.getPfzid()}, inputV);
		
		return getPageList(model, sql.toString(), both);
		
	}
	
	
	/**
	 * 
	 * @����:�鿴�����Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-26 ����08:58:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pfzmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String ChkmcExist(PfzForm model){
		
		List<String> params = new ArrayList<String>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from xg_zjcm_gygl_wsjc_pfz where pfzmc = ? ");
		params.add(model.getPfzmc());
		
		if(null!=model.getPfzid()){
			sql.append(" and pfzid <> ? ");
			params.add(model.getPfzid());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	/**
	 * 
	 * @����:�ж��������Ƿ��б�ʹ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����09:14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isUsed(String value) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from ( ");
		sql.append("select pfzid from xg_zjcm_gygl_wsjc_dfsz_dfz where pfzid = ? ");
		sql.append("union all select pfzid from xg_zjcm_gygl_wsjc_wsf where pfzid = ? )");
 		String num = dao.getOneRs(sql.toString(), new String[]{value,value}, "count");
		return Integer.parseInt(num)>0;
	}

	/**
	 * @throws Exception  
	 * @����:ɾ����������س�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:00:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delPfcy(String[] ids) throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_ZJCM_GYGL_WSJC_PFZ_PFCY where pfzid in (");
		for (int i = 0; i < ids.length; i++) {
			if(i==0){
				sql.append(" ? ");
			}else{
				sql.append(" , ? ");
			}
			params.add(ids[i]);
		}
		
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * @throws Exception  
	 * @����:�������ֳ�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:41:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param value
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean savePfcy(PfzForm model, String value) throws Exception {
		
		List<String> params = new ArrayList<String>();
		params.add(model.getPfzid());
		String[] values = value.split(",");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into XG_ZJCM_GYGL_WSJC_PFZ_PFCY(cyzgh,Pfzid) select zgh,? from fdyxxb where zgh in ( ");
		for (int i = 0; i < values.length; i++) {
			if(i==0){
				sql.append(" ? ");
			}else{
				sql.append(" ,? ");
			}
			params.add(values[i]);
		}
		
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @����:ȡ�����ֳ�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����11:08:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param value
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean savePfcyQx(PfzForm model, String value) throws Exception {
		String[] values = value.split(",");
		List<String> params = new ArrayList<String>();
		params.add(model.getPfzid());
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_ZJCM_GYGL_WSJC_PFZ_PFCY where pfzid = ? and cyzgh in ( ");
		for (int i = 0; i < values.length; i++) {
			if(i==0){
				sql.append(" ? ");
			}else{
				sql.append(" ,? ");
			}
			params.add(values[i]);
		}
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @����:�û��鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����03:22:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfzList(PfzForm model, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.zgh,a.xm,decode(a.xb,'1','��','2','Ů',a.xb) xbmc, ");
		sql.append("(select b.bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc from fdyxxb a ");
		sql.append("where zgh in (select cyzgh from XG_ZJCM_GYGL_WSJC_PFZ_PFCY where pfzid = ? )) where 1=1 ");
		sql.append(searchTj);
		
		//���Լ��Ĳ����͸߼���ѯ�����ϲ�
		String[] both = (String[]) ArrayUtils.addAll(new String[]{model.getPfzid()}, inputV);
		
		return getPageList(model, sql.toString(), both);
	}
	
	/**
	 * 
	 * @����:��ѯ����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����07:28:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pfzid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZxx(String pfzid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,decode(ssxq,'001','��ɳ','002','ͩ��') ssxqmc, nvl(b.yhCount,0) yhCount ");
		sql.append("from xg_zjcm_gygl_wsjc_pfz a left join ");
		sql.append("(select pfzid,count(1) yhCount from xg_zjcm_gygl_wsjc_pfz_pfcy group by pfzid) b ");
		sql.append("on a.pfzid=b.pfzid where a.pfzid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{pfzid});
		
	}

}
