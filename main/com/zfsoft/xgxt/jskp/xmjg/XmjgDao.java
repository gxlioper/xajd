package com.zfsoft.xgxt.jskp.xmjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XmjgDao extends SuperDAOImpl<XmjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XmjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XmjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (select t.*,t2.xm fzrxm,t3.bmmc,t4.xmlbmc,t.zxf || '-' || t.zdf pfqj,decode(t.xmdl,'gdx','�̶���','������') xmdlmc");
		sql.append(" from xg_jskp_xmjgb t");
		sql.append(" left join (select yhm,xm from yhb union select xh,xm from xsxxb) t2");
		sql.append(" on t.fzr = t2.yhm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.zdbm = t3.bmdm");
		sql.append(" left join xg_jskp_xmlbb t4");
		sql.append(" on t.xmlb = t4.xmlbdm)a");
		sql.append(" where fzr = '"+user.getUserName()+"'");
		sql.append(searchTj);
		sql.append(" order by lxsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	public HashMap<String,String> getXmxx(String xmid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,decode(t.xmdl,'gdx','�̶���','������') xmdlmc,t2.xm fzrxm,t3.bmmc zddwmc,t4.xmlbmc from xg_jskp_xmjgb t left join yhb t2 on t.fzr = t2.yhm");
		sql.append(" left join zxbz_xxbmdm t3 on t.zdbm = t3.bmdm left join xg_jskp_xmlbb t4 ");
		sql.append(" on t.xmlb = t4.xmlbdm where t.xmid=?");
		return dao.getMapNotOut(sql.toString(),new String[]{xmid});
	}
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(XmjgForm.class);
		this.setKey("xmid");
		this.setTableName("xg_jskp_xmjgb");
	}
	
	/**
	 * 
	 * @����: ��֤��Ŀ�Ƿ��ѱ�ʹ�ù�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-24 ����08:55:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXmIsNotUserd(String[] xmids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (");
		sql.append(" select xmid from xg_jskp_xmsbb");
		sql.append(" union ");
		sql.append(" select xmid from xg_jskp_xmsbjgb) where xmid in");
		sql.append(" (");
		for (int i = 0; i < xmids.length; i++) {
			sql.append("?");
			if(i != xmids.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		String rs = dao.getOneRs(sql.toString(), xmids, "cnt");
		return "0".equals(rs) ? true :false;
	}
	
}
