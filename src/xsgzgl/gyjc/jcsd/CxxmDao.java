/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����10:25:00 
 */  
package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��22�� ����10:25:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxxmDao extends SuperDAOImpl<CxxmForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(CxxmForm.class);
		this.setKey("dm");
		this.setTableName("xg_gygl_wsjc_qsztwspjdmb");
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxxmForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CxxmForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select row_number() over(ORDER BY t.dm) as xh, t.dm,t.mc,t.jbz from xg_gygl_wsjc_qsztwspjdmb t");
			
			return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��22�� ����4:00:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkRepeatDM(CxxmForm t) {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_gygl_wsjc_qsztwspjdmb");
		sql.append(" where dm = ?");
		paraList.add(t.getDm());
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��22�� ����4:00:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkRepeatMC(CxxmForm t) {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_gygl_wsjc_qsztwspjdmb");
		sql.append(" where mc = ?");
		paraList.add(t.getMc());
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	

}
