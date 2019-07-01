/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-9 ����02:26:39 
 */  
package xsgzgl.gygl.sdftj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-9 ����02:26:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class sdfTjDao extends SuperDAOImpl<sdfTjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(sdfTjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(sdfTjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*, t1.ldmc");
		sql.append(" from xg_gygl_new_sdftjb t");
		sql.append(" left join (select distinct lddm, ldmc from XG_GYGL_NEW_LDXXB) t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(sdfTjForm.class);
		this.setKey("id");
		this.setTableName("xg_gygl_new_sdftjb");
	}
	
	/**
	 * 
	 * @����: ��ȡ¥������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:02:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm, ldmc from XG_GYGL_NEW_LDXXB ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ��ȡ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:04:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm,qsh from XG_GYGL_NEW_QSXXB where lddm = ? and ch = ?");
		return dao.getListNotOut(sql.toString(), new String[]{lddm,ch});
	}
	
	/**
	 * 
	 * @����: ��ȡ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm,ch from XG_GYGL_NEW_QSXXB where lddm = ?");
		return dao.getListNotOut(sql.toString(),new String[]{lddm});
	}
	
	
	/**
	 * 
	 * @����: ��ȡ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getCh(String qsh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ch from XG_GYGL_NEW_QSXXB where qsh = ?");
		return dao.getOneRs(sql.toString(),new String[]{qsh},"ch");
	}
	
	/**
	 * 
	 * @����: ��ȡ¥��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getLdmc(String lddm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ldmc from XG_GYGL_NEW_LDXXB where lddm = ?");
		return dao.getOneRs(sql.toString(),new String[]{lddm},"ldmc");
	}
	
	/**
	 * 
	 * @����:����Ƿ��Ѵ�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����05:41:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @param jd
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String nd,String jd,String lddm,String qsh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num from xg_gygl_new_sdftjb where nd = ?");
		sql.append(" and jd = ? ");
		sql.append(" and lddm = ? ");
		sql.append(" and qsh = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{nd,jd,lddm,qsh},"num" );
		return ("0").equals(num) ? true : false;
	}

}
