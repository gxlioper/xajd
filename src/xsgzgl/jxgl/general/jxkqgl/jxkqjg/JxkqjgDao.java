/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-7 ����07:29:03 
 */
package xsgzgl.jxgl.general.jxkqgl.jxkqjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-7 ����07:29:03
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JxkqjgDao extends SuperDAOImpl<JxkqjgForm> {
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JxkqjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JxkqjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*from(select a.*,b.lbmc kqlbmc,c.lbmc kqlxmc,d.xm,d.xb,d.nj,d.xydm,d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,e.jxmc from xg_jxgl_jxkqjgb a left join  xg_jxgl_kqlbb b on a.kqlb=b.lbdm ");
		sql.append("left join xg_jxgl_kqlxb c on a.kqlx=c.lbdm left join view_xsbfxx d on a.xh=d.xh left join xg_jxgl_jxxxwhb e on a.jxid=e.jxid)t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��ѵѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8 ����04:47:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxxsList(JxkqjgForm t,User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,rownum r from (select a.mdid,a.jxid,d.xh,d.xm,d.xb,d.nj,d.xydm,d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,case when a.cxqk='cx' then '��ѵ' when a.cxqk='hx' then '��ѵ' when a.cxqk='mx' then '��ѵ' end as cxqk");
		sql.append(" from XG_JXGL_JXCXMDB a left join xg_jxgl_jxxxwhb b on a.jxid=b.jxid left join view_xsjbxx d on a.xh = d.xh where b.jxzt='start' and a.cxqk='cx' order by d.bjdm,d.xh )a where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8����10:25:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_jxgl_kqlxb order by lbdm desc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8����10:25:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlb() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_jxgl_kqlbb order by lbdm desc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	public HashMap<String, String> getKqxx(String kqid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.lbmc kqlbmc,c.lbmc kqlxmc from xg_jxgl_jxkqjgb a left join  xg_jxgl_kqlbb b on a.kqlb=b.lbdm ");
		sql.append("left join xg_jxgl_kqlxb c on a.kqlx=c.lbdm where a.kqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { kqid });
	}

	@Override
	public JxkqjgForm getModel(JxkqjgForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.lbmc kqlbmc,c.lbmc kqlxmc,e.jxmc from xg_jxgl_jxkqjgb a left join  xg_jxgl_kqlbb b on a.kqlb=b.lbdm ");
		sql.append("left join xg_jxgl_kqlxb c on a.kqlx=c.lbdm left join xg_jxgl_jxxxwhb e on a.jxid=e.jxid where a.kqid=? ");
		return getModel(sql.toString(), new String[] { myForm.getKqid() });

	}

	@Override
	protected void setTableInfo() {
		this.setKey("kqid");
		this.setTableName("xg_jxgl_jxkqjgb");
		this.setClass(JxkqjgForm.class);

	}

}
