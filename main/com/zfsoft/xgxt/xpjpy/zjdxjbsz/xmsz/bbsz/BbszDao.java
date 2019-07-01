/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-4-20 ����09:16:01 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-��������-��Ŀ����-��������
 * @�๦������: �ǼǱ��ϱ�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-20 ����09:16:35 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbszDao extends SuperDAOImpl<BbszForm> {
	protected void setTableInfo() {
		super.setTableName("XG_PJPY_NEW_XMSZBBDMB");
		super.setKey("bbdm");
		super.setClass(BbszForm.class);
	}
	
	/**
	 * ��ͨ��ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BbszForm t)
			throws Exception {
		return null;
	}
	
	/**
	 * �߼���ѯ
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BbszForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * @����: ��ѯ�ǼǱ������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����10:30:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bblx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.bbdm,a.bbmc,a.mblj,a.mbmc,a.bblx,b.bblj,b.dyym from xg_pjpy_new_xmszbbdmb a ");
		sql.append("left join xg_pjpy_new_bbdytpb b on a.bbdm = b.bbdm ");
		sql.append("where b.dyym ='1' and a.bblx = ? ");
		sql.append("order by a.mbmc ");
		String[] input = {bblx};
		return dao.getListNotOut(sql.toString(), input);
	}
	
	/**
	 * @����: ����Ԥ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����03:39:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBbxxYlList(String bbdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from xg_pjpy_new_xmszbbdmb t1 ");
		sql.append("left join xg_pjpy_new_bbdytpb t2 on t1.bbdm = t2.bbdm where t1.bbdm = ? order by t2.dyym ");
		String[] input = new String[]{bbdm};
		return dao.getListNotOut(sql.toString(), input);
	}
}
