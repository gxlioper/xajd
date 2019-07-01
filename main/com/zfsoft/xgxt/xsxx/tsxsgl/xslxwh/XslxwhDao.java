/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:48:39 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:48:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XslxwhDao extends SuperDAOImpl<XslxwhForm>{

	@Override
	public List<HashMap<String, String>> getPageList(XslxwhForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_xslxb a where 1=1");
		if (!StringUtil.isNull(model.getXslxmc())) {
			params.add(model.getXslxmc());
			sql.append(" and a.xslxmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	/**
	 * ��ȡѧ������б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XslxwhForm model, User user) throws Exception {
		return null;
	}

    /**
     * 
     * @����:����ѧ�����
     * @���ߣ�����[���ţ�1104]
     * @���ڣ�2015-5-15 ����01:17:57
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean addXslx(XslxwhForm model) throws Exception {
		boolean flag = false;
		String sql;
		sql = "select count(1) num from xg_xsxx_xslxb where xslxdm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getXslxdm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into xg_xsxx_xslxb(XSLXDM,XSLXMC) values(?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getXslxdm(), model.getXslxmc() });
		} else {
			throw new SystemException(MessageKey.SYS_SAVE_DM_REPEAT);
		}

		return flag;

	}

	/**
	 *ɾ��
	 */
	public int deleteXslx(String values) throws Exception {
		String sql = "delete from xg_xsxx_xslxb where xslxdm =?";
		return dao.runDelete(sql, new String[] {values});

	}
	

	/**
	 *��ȡѧ�����
	 */
	public String getXslxmc(String lbdm) throws Exception {
		String sql = "select mc Xslxmc from xg_xsxx_xslxb where xslxdm =?";
		return dao.getOneRs(sql, new String[]{lbdm}, "Xslxmc");

	}
	
	public List<String> getTsxslxData() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct xslxdm from (select substr(t.xslxdm,instr(t.xslxdm, ',', 1, c.lv) + 1,");
	    sql.append("instr(t.xslxdm, ',', 1, c.lv + 1) -(instr(t.xslxdm, ',', 1, c.lv) + 1)) AS xslxdm from (select ");
	    sql.append("',' || xslxdm || ',' AS xslxdm,length(xslxdm || ',') - nvl(length(REPLACE(xslxdm, ',')), 0) AS cnt");
	    sql.append(" FROM (select xslxdm from xg_xsxx_tsxsb)) t,(select LEVEL lv from dual CONNECT BY LEVEL <= 4000) c");
	    sql.append(" where c.lv <= t.cnt) order by  xslxdm");
		return dao.getList(sql.toString(), new String[]{}, "xslxdm");
		
	}

	public List<HashMap<String, String>> getXslxList(){
		
		String sql = "select xslxdm,xslxmc from xg_xsxx_xslxb";
		
		return dao.getListNotOut(sql, new String[]{});
		
	}
	public List<HashMap<String, String>> getXslxList(String[] xslxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xslxdm,xslxmc from xg_xsxx_xslxb where ");
		
		for (int i = 0 , n = xslxdm.length ; i < n ; i++){
			sql.append("  xslxdm =? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), xslxdm);
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(XslxwhForm.class);
		super.setKey("xslxdm");
		super.setTableName("xg_xsxx_xslxb");
	}

	

}
