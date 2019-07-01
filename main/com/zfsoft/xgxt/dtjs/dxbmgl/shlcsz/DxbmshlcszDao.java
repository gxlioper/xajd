package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @������������У���������������dao
 * @author������ ��1346��
 * @date��2017-11-1 ����09:16:53 
 */
public class DxbmshlcszDao extends SuperDAOImpl<DxbmshlcszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxbmshlcszForm t) throws Exception {
		
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DxbmshlcszForm t, User user) throws Exception {
		
		return null;
	}
	
	/** 
	 * @������������ȡ��ǰ�����
	 * @author������ ��1346��
	 * @date��2017-11-3 ����10:17:12 
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String dxbmshlcszCx() throws Exception{
		String sql=" select shl from xg_dtjs_dxbmshlb";
		return dao.getOneRs(sql, new String[]{}, "shl");
	}
	
	/** 
	 * @�������������������
	 * @author������ ��1346��
	 * @date��2017-11-3 ����09:59:50 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean dxbmshlcszBc(DxbmshlcszForm model) throws Exception{
		String sql="insert into xg_dtjs_dxbmshlb(shl) values(?)";
		return dao.runUpdate(sql,new String[]{model.getShl()});
	}
	
	/** 
	 * @����������ɾ���������
	 * @author������ ��1346��
	 * @date��2017-11-3 ����09:59:38 
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean dxbmshlcszSc() throws Exception{
		String sql = " delete from xg_dtjs_dxbmshlb ";
		return dao.runUpdate(sql, new String[]{});
	}

	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_dtjs_dxbmshlb");
		this.setClass(DxbmshlcszForm.class);
	}
}
