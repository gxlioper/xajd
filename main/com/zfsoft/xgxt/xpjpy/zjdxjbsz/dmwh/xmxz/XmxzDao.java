/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:43:34 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

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
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:43:34 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmxzDao extends SuperDAOImpl<XmxzForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XmxzForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_xmxz");
		super.setKey("xzdm");
	}

	/**
	 * @����: ��ͨ��ѯ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-10 ����11:47:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmxzForm t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		/*����ҳ*/
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder(" select xzdm,xzmc from xg_zjdx_pjpy_xmxz where 1=1 ");
		
		if(!StringUtil.isNull(t.getXzmc())){
			params.add(t.getXzmc());
			sql.append(" and xzmc like '%'||?||'%'");
		}
		return getPageList(t,sql.toString(),params.toArray(new String[]{}));
	}

	/**
	 * 
	 * @����: ����_��ѯ���������Ƿ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-3-10 ����11:47:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExistXzmc(XmxzForm model) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_zjdx_pjpy_xmxz where xzmc = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXzmc()}, "num");
		return num;
	}

	/**
	 * @����: �鿴��Ŀ����������������Ƿ��ѱ�ʹ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-3-10 ����09:53:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] xzCheckExistForPjjg( String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.xzmc ");
		sql.append(" from xg_zjdx_pjpy_pjjgb a,xg_zjdx_pjpy_xmxz b ");
		sql.append(" where a.xzdm = to_char(b.xzdm) and a.xzdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
			sql.append(" ? ");
			params.add(tableArr[i]);
			if(i!= tableArr.length -1){
				sql.append(" , ");
			}
		}
		sql.append(" ) ");
		String[] xzmc = dao.getRs(sql.toString(), params.toArray(new String[]{}), "xzmc");
		return xzmc;
}

	/**
	 * @����: �鿴��Ŀ������������Ŀ���Ƿ��ѱ�ʹ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-3-10 ����09:53:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] xzCheckExistForPjxm(String value) throws Exception{
		StringBuffer sql = new StringBuffer();
		ArrayList<String> params = new ArrayList<String>();
		String[] tableArr = value.split(",");
		sql.append(" select distinct b.xzmc ");
		sql.append(" from xg_zjdx_pjpy_pjxmb a,xg_zjdx_pjpy_xmxz b ");
		sql.append(" where a.xzdm = to_char(b.xzdm) and a.xzdm in ( ");
		
		for (int i = 0; i < tableArr.length; i++) {
			sql.append(" ? ");
			params.add(tableArr[i]);
			if(i!= tableArr.length -1){
				sql.append(" , ");
			}
		}
		sql.append(" ) ");
		String[] xzmc = dao.getRs(sql.toString(), params.toArray(new String[]{}), "xzmc");
		return xzmc;
		}
	}
