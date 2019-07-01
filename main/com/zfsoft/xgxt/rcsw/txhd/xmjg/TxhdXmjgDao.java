/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-24 ����09:39:48 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-6-24 ����09:39:48
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmjgDao extends SuperDAOImpl<TxhdXmjgForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( ");
		sql.append("select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc, ");
		sql.append("(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm=b.lbdm)lbmc, ");
		sql.append("hdkssj||' �� '||hdjssj hdsj, ");
		sql.append("b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from XG_RCSW_TXHD_JGB a ");
		sql.append("left join view_xsbfxx b on b.xh=a.xh ) where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:������ж�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-09-25 ����09:12:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isAdd(TxhdXmjgForm myForm) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from xg_rcsw_txhd_jgb where xh=? and xn=? and xq=? and xmmc=?");
		Map<String, String> map = dao.getMapNotOut(sb.toString(), new String[] {
				myForm.getXh(), myForm.getXn(), myForm.getXq(),
				myForm.getXmmc() });
		String xh = map.get("xh");
		if (xh == null) {
			return true;
		}
		return xh.equals(myForm.getXh()) ? false : true;
	}
	/**
	 * ɾ����Ӧ������Ϣ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-6-26 ����04:27:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * int ��������
	 */
	public int deleteForSh(String sqid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_rcsw_txhd_jgb where sqid=?");
		return dao.runDelete(sb.toString(), new String[] { sqid });
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		this.setKey("guid");
		this.setTableName("xg_rcsw_txhd_jgb");
		this.setClass(TxhdXmjgForm.class);

	}
	
	/**
	 * 
	 * @����:�����鿴����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-29 ����05:56:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getOneHdjgList(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,c.xm,(case when a.xckssj is null then a.xckssj else substr(a.xckssj,length(a.xckssj)-2,length(a.xckssj)-1)||'ʱ' end)kshour,");
		sql.append("(case when a.xcjssj is null then a.xcjssj else substr(a.xcjssj,length(a.xcjssj)-2,length(a.xcjssj)-1)||'ʱ' end)jshour,");
		sql.append("(select lbmc from xg_rcsw_txhd_lbdm b where a.lbdm = b.lbdm) lbmc,");
		sql.append("(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc ");
		sql.append("from xg_rcsw_txhd_jgb a left join xsxxb c on a.xh=c.xh where guid= ?  ");
		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}

}
