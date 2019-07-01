/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.bbwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-6-3 ����09:40:35
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class BbwhDao extends SuperDAOImpl<BbwhForm> {

	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAll() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_xszz_new_zzxmbbdmb ");
		return dao.getListNotOut(sb.toString(), null);
	}

	/**
	 * 
	 * @����:��ȡ��������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_xszz_new_zzxmbbdmb ");
		sb.append(" where bbdm=?");
		String[] inputValue = { bbdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmbbdmb");
		super.setKey("bbdm");
		super.setClass(BbwhForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @����: ��ѯ�ǼǱ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-5 ����02:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxLists(String bblx){
		
		String[] input = {bblx};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from xg_xszz_new_zzxmbbdmb t1 ");
		sql.append("left join xg_xszz_new_bbdytpb t2 on t1.bbdm=t2.bbdm and t2.dyym='1' where t1.bblx=? ");
		
		return dao.getListNotOut(sql.toString(), input);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-5 ����03:52:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBbxxList(String bbdm) {
		String[] input = new String[]{bbdm};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from xg_xszz_new_zzxmbbdmb t1 ");
		sql.append("left join xg_xszz_new_bbdytpb t2 on t1.bbdm=t2.bbdm where t1.bbdm=? order by t2.dyym");
		return dao.getListNotOut(sql.toString(), input);
	}
	
	
	
}
