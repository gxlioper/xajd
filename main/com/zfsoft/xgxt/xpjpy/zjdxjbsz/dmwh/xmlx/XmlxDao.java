/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:40:47 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ά��_��Ŀ����
 * @���ߣ�Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:39:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmlxDao extends SuperDAOImpl<XmlxForm>{
	
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XmlxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_xmlx");
		super.setKey("lxdm");
	}

	/**
	 * ��ѯ
	 */
	public List<HashMap<String, String>> getPageList(XmlxForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		/*����ҳ*/
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder(" select lxdm,lxmc from xg_zjdx_pjpy_xmlx where 1 = 1 ");
		
		if(!StringUtil.isNull(t.getLxmc())){
			params.add(t.getLxmc());
			sql.append(" and lxmc like '%'||?||'%'");
		}
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}
	
	/**
	 * @����: ����_��ѯ���������Ƿ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����07:49:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExistLxmc(XmlxForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_zjdx_pjpy_xmlx where lxmc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getLxmc()}, "num");
		return num;
	}
	
	/**
	 * @����: �鿴��Ŀ����������������Ƿ��ѱ�ʹ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-10 ����09:53:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] lxCheckExistForPjjg( String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.lxmc ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a,xg_zjdx_pjpy_xmlx b ");
		sql.append(" where a.lxdm = to_char(b.lxdm) and a.lxdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
				sql.append(" ? ");
				params.add(tableArr[i]);
				if(i!= tableArr.length -1){
					sql.append(" , ");
				}
		}
		sql.append(" ) ");
		String[] lxmc = dao.getRs(sql.toString(), params.toArray(new String[]{}), "lxmc");
		return lxmc;
	}
	
	/**
	 * @����: �鿴��Ŀ������������Ŀ���Ƿ��ѱ�ʹ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-10 ����09:53:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] lxCheckExistForPjxm(String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.lxmc ");
		sql.append(" from xg_zjdx_pjpy_pjxmb a,xg_zjdx_pjpy_xmlx b ");
		sql.append(" where a.lxdm = to_char(b.lxdm) and a.lxdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
			sql.append(" ? ");
			params.add(tableArr[i]);
			if(i!= tableArr.length -1){
				sql.append(" , ");
			}
		}
		sql.append(" ) ");
		String[] lxmc = dao.getRs(sql.toString(),params.toArray(new String[]{}), "lxmc");
		return lxmc;
	}
	
	/**
	 * @����: ��ȡ��Ŀ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-2-10 ����11:32:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmlx() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select lxdm dm,lxmc mc");
		sql.append(" from xg_zjdx_pjpy_xmlx order by lxdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
