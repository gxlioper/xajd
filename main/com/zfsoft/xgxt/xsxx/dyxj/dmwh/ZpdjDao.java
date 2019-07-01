/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-18 ����11:11:55 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������:�����ȼ�ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-18 ����11:11:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZpdjDao extends SuperDAOImpl<ZpdjModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dyxj_djdmb");
		super.setKey("djdm");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZpdjModel t)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_dyxj_djdmb where 1=1 ");
		
		if (!StringUtil.isNull(t.getDjdm())){
			sql.append(" and djdm = ? ");
			params.add(t.getDjdm());
		}
		
		if (!StringUtil.isNull(t.getDjmc())){
			sql.append(" and djmc like '%'||?||'%' ");
			params.add(t.getDjmc());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	
	public String getCountByDjmc(String djmc){
		String sql = "select count(1) c from xg_xsxx_dyxj_djdmb where djmc=?";
		return dao.getOneRs(sql, new String[]{djmc}, "c");
	}
	
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZpdjModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xsxx_dyxj_djdmb t1");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("djdm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_xsxx_dyxj_dypysqb t2 where t1.djdm=t2.djdm)");
		sql.append("  and not exists (select 1 from xg_xsxx_dyxj_dypyjg t3 where t1.djdm=t3.djdm)");
		
		return dao.runDelete(sql.toString(), values);
	}
	
    public HashMap<String, String> getDjmc(String djdm){
    	StringBuilder sql = new StringBuilder();
    	sql.append("select * from xg_xsxx_dyxj_djdmb where djdm = ?");
    	return dao.getMapNotOut(sql.toString(), new String[]{djdm});
    }

}
