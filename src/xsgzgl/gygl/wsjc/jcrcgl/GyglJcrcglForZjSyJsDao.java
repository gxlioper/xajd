/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-5-31 ����11:00:59 
 */  
package xsgzgl.gygl.wsjc.jcrcgl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �㽭��ҵ��ʦר��dao(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-5-31 ����11:00:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyglJcrcglForZjSyJsDao extends SuperDAOImpl<GyglJcrcglForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyglJcrcglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyglJcrcglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(GyglJcrcglForm.class);
		super.setKey("zzdid");
		super.setTableName("xg_gygl_new_wsjc_jcrcb");
		
	}
	
	public boolean plInsertJc(GyglJcrcglForm model,String[] strs) throws SQLException{
		boolean flg = true;
		String[] sqlStrs = new String[strs.length];
		for(int i = 0;i<strs.length;i++){
			StringBuilder sb = new StringBuilder();
			sb.append("insert into xg_gygl_new_wsjc_jcrcb(xn,xq,mc,kssj,jssj,bz,jclx) values ('"+model.getXn()+"','"+model.getXq()+"','"+model.getMc()+"("+ strs[i] +")','"+strs[i]+"','"+strs[i]+"','"+model.getBz()+"','"+model.getJclx()+"')");
			sqlStrs[i] = sb.toString();
		}
		int[] res = dao.runBatch(sqlStrs);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
}
