/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����06:11:22 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�����������) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����06:11:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxdjDao extends SuperDAOImpl<CxdjForm> {

	

	@Override
	protected void setTableInfo() {
		super.setTableName("xsxx_cxdjdmb");
		super.setKey("cxdjdm");

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxdjForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from (select cxdjdm,cxdjmc from xsxx_cxdjdmb order by cxdjdm) where 1=1 ");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxdjForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/** 
	 * @����:TODO(���еȼ������Ƿ����)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-30 ����06:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(CxdjForm model) {
		// TODO �Զ����ɷ������
		String sql="select * from xsxx_cxdjdmb where cxdjdm='"+model.getCxdjdm()+"'";
		DAO dao=DAO.getInstance();
		List<HashMap<String, String>> list=dao.getList(sql, new String[]{}, new String[]{"cxdjdm"});
		return list.size()!=0;
	}
	
	@Override
	public int runDelete(String[] values) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("delete from xsxx_cxdjdmb a");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cxdjdm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		sql.append(" and not exists(select 1 from xsxx_cxpyb b where a.cxdjdm=b.cxdj)");
		return dao.runDelete(sql.toString(), values);
	}
	//�㽭���θ��Ի��ж�
	public boolean isNowUsing(String[] pj){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ");
		sql.append(" (select pj from xg_xsxx_cxpy_pysb_xs ");
		sql.append(" union all ");
		sql.append(" select pj from xg_xsxx_cxpy_pysb_jg");
		sql.append(" ) t");
		sql.append(" where t.pj in ");
		sql.append(" (");
		for (int i = 0; i < pj.length; i++) {
			sql.append("?");
			if(i!=pj.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		
		String num = dao.getOneRs(sql.toString(), pj, "num");
		if("0".equalsIgnoreCase(num)){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkSameNameIsNotExists(String cxdjdm,String cxdjmc){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select count(1) num");
		sql.append(" from xsxx_cxdjdmb");
		sql.append(" where cxdjmc = ?");
		paralist.add(cxdjmc);
		if(StringUtils.isNotNull(cxdjdm)){
			sql.append("  and cxdjdm != ?");
			paralist.add(cxdjdm);
		}
	
		sql.append(" ");
		String num = dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "num");
		if(num.equals("0")){
			return true;
		}else{
			return false;
		}
	}
}
