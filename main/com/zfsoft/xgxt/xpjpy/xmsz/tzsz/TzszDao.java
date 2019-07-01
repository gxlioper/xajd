/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tzsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-��˵�����������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class TzszDao extends SuperDAOImpl<TzszModel> {

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(TzszModel model)
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
	public List<HashMap<String, String>> getPageList(TzszModel model,
			User user) throws Exception {
		return null;
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
		sql.append("select a.*,c.xmdm fztzjxdm from xg_pjpy_new_jxtzsz a  left join xg_pjpy_new_pjxmb b");
		sql.append(" on a.tzjxdm=b.xmdm left join(select * from xg_pjpy_new_pjxmb where xn=? and xq=?)c on b.xmmc=c.xmmc where a.xmdm=? ");
		String[] input = {currXn,currXq,xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), input);
		return result;
	}	
	
	/**
	 * 
	 * @����:����xmdm������Ŀ���롢����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKshxm(String xmdm) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("��ѯ����Ϊ�գ�");
		}
		String sql = " select a.tzjxdm dm,b.xmmc mc,b.sqxn sqxn,b.sqxq sqxq  from  xg_pjpy_new_jxtzsz a,xg_pjpy_new_pjxmb b" ;
		sql += 	" where a.xmdm=? and a.tzjxdm=b.xmdm ";
		String[] input = {xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}
	
	/**
	 * 
	 * @����:����
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
	public boolean runShsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;
		
		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_pjpy_new_jxtzsz where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_pjpy_new_jxtzsz(xmdm,tzjxdm) values('"+xmdm+"','"+arr[i]+"')";
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
		super.setTableName("xg_pjpy_new_jxtzsz");
		super.setKey("xmdm");
	}

	
	/**
	 * 
	 * @����: ����Ŀ�����ѯ�ɵ�����������Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����02:23:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getKtzPjxmList(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.tzjxdm xmdm,t2.xmmc from xg_pjpy_new_jxtzsz t1");
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t1.tzjxdm=t2.xmdm");
		sql.append(" where t1.xmdm=?");
		sql.append(" and t2.sfqy = '1' and t2.shkg = '1' ");
		sql.append(" and (sysdate between to_date(nvl(t2.shkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t2.shjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}

	/**
	 * @throws Exception  
	 * @����:������Ŀ����ɾ����¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-23 ����05:25:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		
			String sql = "delete from xg_pjpy_new_jxtzsz where xmdm=? ";
			String[] inputValue = { xmdm };
			int result = dao.runDelete(sql, inputValue);
			return result > 0;
	}
}
