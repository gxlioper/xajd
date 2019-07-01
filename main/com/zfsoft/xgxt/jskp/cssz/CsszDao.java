package com.zfsoft.xgxt.jskp.cssz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CsszDao extends SuperDAOImpl<CsszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(CsszForm.class);
		this.setKey("id");
		this.setTableName("xg_jskp_csszb");
	}
	
	/**
	 * 
	 * @������ ��ȡ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����04:26:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSplc(String lx){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_csszb where lx = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{lx});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����04:47:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cssz
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveData(CsszForm cssz) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String[]> paraList = new ArrayList<String[]>();
		if(cssz.getIds() == null || cssz.getIds().length == 0 ||StringUtils.isNull(cssz.getIds()[0]) ){
			sql.append(" insert into xg_jskp_csszb(splc,lx,sfsh)values(?,?,?)");
			paraList.add(new String[]{cssz.getSplcs()[0],cssz.getLxs()[0],cssz.getSfsh()});
			paraList.add(new String[]{cssz.getSplcs()[1],cssz.getLxs()[1],cssz.getSfsh()});
		}else{
			sql.append(" update xg_jskp_csszb set splc = ?,sfsh = ? where id = ?");
			paraList.add(new String[]{cssz.getSplcs()[0],cssz.getSfsh(),cssz.getIds()[0]});
			paraList.add(new String[]{cssz.getSplcs()[1],cssz.getSfsh(),cssz.getIds()[1]});
		}
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @����: ȡ�������ñ��е��Ƿ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-20 ����08:04:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSfsh(){
		String sql = " select distinct sfsh from xg_jskp_csszb ";
		return dao.getOneRs(sql, new String[]{}, "sfsh");
	}
}
