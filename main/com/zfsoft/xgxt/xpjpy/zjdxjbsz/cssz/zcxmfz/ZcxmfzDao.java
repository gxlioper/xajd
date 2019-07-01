/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-29 ����09:41:34 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmfz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-3-29 ����09:41:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcxmfzDao  extends SuperDAOImpl<CsszForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_zcxmb");
		super.setKey("xmdm");
		super.setClass(CsszForm.class);
	}
	
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		return null;
	}
	
	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		return null;
	}
	
	/*
	 *  ����û����
	 */
	public boolean updateZcxmFz(CsszForm model) throws Exception{
		String sql = " update xg_zjdx_pjpy_zcxmb set zxfz = ?,zdfz = ?, px = ? where xmdm = ? )";
		return dao.runUpdate(sql, new String[]{model.getZxfz(),model.getZdfz(),model.getPx(),model.getXmdm()});
	}
	
	/*
	    �޸ı����������ɾ��ͬһ������������Ϣ
	 */
	public boolean delZcxmfz(String xmdm) throws Exception {
		String sql = " delete from xg_zjdx_pjpy_zcxmb where xmdm = ? ";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
	
	/*
              ���Ӳ����������Լ���Ŀlist(�Ѿ������Ķ�����Ŀ)
	 */
	public List<HashMap<String, String>> getYjxmlist(String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm dm,xmmc mc from xg_zjdx_pjpy_zcxmb where fjdm = 'top' and xn = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
}
