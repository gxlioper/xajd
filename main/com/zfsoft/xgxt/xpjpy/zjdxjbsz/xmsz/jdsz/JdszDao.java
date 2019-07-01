/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-13 ����10:29:21 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.jdsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ���ɼ������
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2018-7-13 ����10:24:14 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdszDao extends SuperDAOImpl<JdszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JdszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_jdszb");
		super.setKey("xmdm");
		super.setClass(JdszForm.class);
	}
	
	/**
	 * @����: ������Ŀ��������Ŀ����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����02:25:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXmmcByXmdm(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select xmmc from xg_zjdx_pjpy_pjxmb where xmdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xmmc");
	}
	
	/**
	 * @����: ����xmdm��ѯ���õļ�¼
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����03:13:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm, String xn) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,c.xmdm fzbjdxmdm from xg_zjdx_pjpy_jdszb a ");
		sql.append("left join xg_pjpy_new_pjxmb b on a.bjdxmdm = b.xmdm ");
		sql.append("left join (select * from xg_zjdx_pjpy_pjxmb where xn = ?) c on b.xmmc = c.xmmc ");
		sql.append("where a.xmdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xmdm});
	}
	
	/**
	 * @����: ����xmdm��ѯ����Ŀ�Ƿ�����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����03:19:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) count from xg_zjdx_pjpy_xmsq where xmdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}
	
	/**
	 * @����: ��ȡ��ѡ��Ŀ�����������Ŀ(��ͬ�����ڵ�)
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����05:37:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm,String xn) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm,xmmc from xg_zjdx_pjpy_pjxmb where ");
		
		if(StringUtils.isNotNull(xmdm)){
			sql.append("xmdm != ? and ");
		}
		sql.append("xn = '");
		sql.append(xn);
		sql.append("' ");
		
		if(StringUtils.isNotNull(xmdm)){
			sql.append("and shlc = (select shlc from xg_zjdx_pjpy_pjxmb where xmdm = ?) ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}
	
	/**
	 * @����: ������ñ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-14 ����11:39:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xmdms
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runJdsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;

		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_zjdx_pjpy_jdszb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		sql = "delete from xg_zjdx_pjpy_jdszb where bjdxmdm='"+xmdm+"'";
		sqlList.add(sql);		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
					sql = "insert into xg_zjdx_pjpy_jdszb(xmdm,bjdxmdm) values('"+arr[i]+"','"+xmdm+"')";
					sqlList.add(sql);
				}
			}
		}

		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
}
