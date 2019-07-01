/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.jdsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-�������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class JdszDao extends SuperDAOImpl<JdszModel> {

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(JdszModel model)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @����:�߼���ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(JdszModel model,
			User user) throws Exception {
		return null;
	}
	/**
	 * 
	 * @����:��ȡ�Ǽ��Ǽ�
	 * @���ߣ�caopei[���ţ�1352]
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXj() {
		String sql = "select dj from xg_gygl_new_wsjc_djfsb where lx='1' order by dj ";
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @����:����xmdm��ѯ���õļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn, String currXq) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,c.xmdm fzbjdxmdm from xg_pjpy_new_jdszb a  left join xg_pjpy_new_pjxmb b");
		sql.append(" on a.bjdxmdm=b.xmdm left join(select * from xg_pjpy_new_pjxmb where xn=? and xq=?)c on b.xmmc=c.xmmc where a.xmdm=? ");
		String[] input = {currXn,currXq,xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), input);
		return result;
	}
	
	/**
	 * 
	 * @����:����xmdm���ز��ɼ����Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjdxm(String xmdm) throws Exception{
		if(StringUtil.isNull(xmdm)){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xmdm,t2.xmmc,t2.xmje from (");
		sql.append(" select bjdxmdm xmdm from xg_pjpy_new_jdszb where xmdm=?");
		sql.append(" union ");
		sql.append(" select xmdm from xg_pjpy_new_jdszb where bjdxmdm=?");
		sql.append(" ) t1 left join xg_pjpy_new_pjxmb t2 on t1.xmdm=t2.xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}	
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runJdsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;

		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_pjpy_new_jdszb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		sql = "delete from xg_pjpy_new_jdszb where bjdxmdm='"+xmdm+"'";
		sqlList.add(sql);		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_pjpy_new_jdszb(xmdm,bjdxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
					sql = "insert into xg_pjpy_new_jdszb(xmdm,bjdxmdm) values('"+arr[i]+"','"+xmdm+"')";
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
	
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_jdszb");
		super.setKey("xmdm");
	}

	/** 
	 * @����:������Ŀ���뷵�ز��ɼ����������Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-3 ����10:37:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJdysq(User user, String xmdm) throws Exception{
		
		if(StringUtil.isNull(xmdm)){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select b.xmmc from xg_pjpy_new_jdszb a ");
		sql.append(" left join xg_pjpy_new_pjxmb b on a.bjdxmdm = b.xmdm ");
		sql.append(" where exists (select 1 from xg_pjpy_new_xmsq c where a.bjdxmdm = c.xmdm and c.xh = ?) ");
		sql.append(" and a.xmdm = ? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{user.getUserName(),xmdm});
	}





}
