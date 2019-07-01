/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-10 ����04:57:04 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-10 ����04:57:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszDao extends SuperDAOImpl<CsszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_XSGZZB_CSSZ a where 1=1");
		if (!StringUtil.isNull(t.getWjlxmc())) {
			params.add(t.getWjlxmc());
			sql.append(" and a.wjlxmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(CsszForm.class);
		super.setKey("wjlxdm");
		super.setTableName("XG_XSGZZB_CSSZ");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-11 ����09:34:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveWjlx(CsszForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_XSGZZB_CSSZ values(SEQ_XSGZZB_CSSZ.nextval,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getWjlxmc()});
	}
	
	//��֤���ݿ����Ƿ���ͬ�����ļ���������
	public boolean isExistsSameWjlxmc(String wjlxmc,String wjlxdm){
		StringBuilder sql = new StringBuilder();
		String[] inputvalue = new String[]{wjlxmc};
		sql.append(" select count(1) flag from  XG_XSGZZB_CSSZ  where wjlxmc = ?");
		//�޸�ʱ����wjlxdm�����жϣ�����ʱ����null�ж�
		if(wjlxdm != null && !wjlxdm.equals("")){
			sql.append(" and wjlxdm != ?");
			inputvalue = new String[]{wjlxmc,wjlxdm};
		}
		String flag = dao.getOneRs(sql.toString(), inputvalue, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//��ȡ�ļ���������
	public String getWjlxmc(String wjlxdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select wjlxmc from XG_XSGZZB_CSSZ where wjlxdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{wjlxdm}, "wjlxmc");
	}
	
	//ɾ���ļ�����ʱ�ж���ҵ������Ƿ�����
	public boolean isExistsWjlxmc_user(String wjlxdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) flag");
		sql.append(" from XG_RCSW_XSGZZB_XSGZZB_FJB");
		sql.append(" where wjlxdm = ?");
		String flag = dao.getOneRs(sql.toString(), new String[]{wjlxdm}, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//��ȡ�ļ���������list
	public List<HashMap<String,String>> getWjlxList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_XSGZZB_CSSZ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//���ϴ�������Ϣlist
	public List<HashMap<String, String>> getYscfjList(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.wjlxmc");
		sql.append(" from XG_RCSW_XSGZZB_XSGZZB_FJB t");
		sql.append(" left join XG_XSGZZB_CSSZ t1");
		sql.append(" on t.wjlxdm = t1.wjlxdm");
		sql.append(" where t.filegid in");
		sql.append(" (select t.filegid");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t");
		sql.append(" where t.sqid = ?)");
		return dao.getListNotOut(sql.toString(), new String[]{sqid});
	}
}
