/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-8 ����02:38:50 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-8 ����02:38:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XshdglDao extends SuperDAOImpl<XshdglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XshdglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XshdglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,decode(t.hdlx,0,'У��',1,'У��') hdlxmc from xg_rcsw_xshdgl t");
		sql.append(" where 1= 1  ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(XshdglForm.class);
		super.setKey("sqid");
		super.setTableName("xg_rcsw_xshdgl");
	}
	
	/**
	 * 
	 * @����:��ȡ���Ŵ���list
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:08:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBmdmList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmdm dm,bmmc mc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:��ȡѧ�����¼map,���ڲ鿴����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:09:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXshdMap(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,decode(t.hdlx,0,'У��',1,'У��') hdlxmc from xg_rcsw_xshdgl t");
        sql.append(" where t.sqid = ?");
        return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @����:�Ƿ����ͬһ���쵥λͬһʱ�������������������ϵĻ���ƣ�����ǣ�����false,����񣬷���true
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-6-12 ����10:15:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsExistNotSame(XshdglForm t){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select count(1) num");
		sql.append(" from xg_rcsw_xshdgl");
		sql.append(" where zbdwdm = ?");
		sql.append(" and hdsj = ?");
		sql.append(" and hdmc = ?");
		paralist.add(t.getZbdwdm());
		paralist.add(t.getHdsj());
		paralist.add(t.getHdmc().trim());
		if("edit".equals(t.getType())){
			sql.append(" and sqid != ?");
			paralist.add(t.getSqid());
		}
		String numStr = dao.getOneRs(sql.toString(),paralist.toArray(new String[]{}) , "num");
		if(StringUtils.isNotNull(numStr)){
			if("0".equals(numStr.trim())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
