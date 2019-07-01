/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-6 ����09:53:06 
 */  
package xsgzgl.jxgl.general.jxqjgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-6 ����09:53:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxqjjgDao extends SuperDAOImpl<JxqjjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JxqjjgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JxqjjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.* from view_new_jxgl_jxqjjg a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-6 ����10:25:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx order by qjlxid asc");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	public HashMap<String, String> getQjjgForPrint(String qjjgid) {
		String sql = "select * from view_new_jxgl_jxqjjg where qjid=?";
		return dao.getMapNotOut(sql.toString(), new String[] { qjjgid });
	}
	@Override
	public JxqjjgForm getModel(JxqjjgForm myForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,(select xqmc from xqdzb where xqdm = a.xq) xqmc from xg_jxgl_jxqjjgb a  where a.qjid=? ");
		return getModel(sql.toString(), new String[]{myForm.getQjid()});

	}

	@Override
	protected void setTableInfo() {
		this.setKey("qjid");
		this.setTableName("xg_jxgl_jxqjjgb");
		this.setClass(JxqjjgForm.class);

	}
	
	/*
	 * ��߼�����ID
	 */
	public String getJzid(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select jxid from xg_jxgl_jxxxwhb where jxzt = 'start' ");
		return dao.getOneRs(sql.toString(), new String[]{}, "jxid");
	}
	
	/*
	 * ��������
	 */
	public String getJzmc(String jxid,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.jzmc001 || a.jzmc002 || a.jzmc003 || a.jzmc004 jzmc from (select a.*, b.xh ");
        sql.append(" from (select jxjz001.jzid jzid001, jxjz001.jzmc jzmc001, jxjz002.jzid jzid002, jxjz002.jzmc jzmc002, jxjz003.jzid jzid003, ");
        sql.append(" jxjz003.jzmc jzmc003, jxjz004.jzid jzid004, jxjz004.jzmc jzmc004 from xg_jxgl_jxjzwhb a left join xg_jxgl_jxjzwhb jxjz001 ");
        sql.append(" on a.jzid = jxjz001.sjid left join xg_jxgl_jxjzwhb jxjz002 on jxjz001.jzid = jxjz002.sjid left join xg_jxgl_jxjzwhb jxjz003 ");
        sql.append(" on jxjz002.jzid = jxjz003.sjid left join xg_jxgl_jxjzwhb jxjz004 on jxjz003.jzid = jxjz004.sjid where a.jzid = ?) a ");
        sql.append(" left join xg_jxgl_jxjzmdb b on a.jzid004 = b.jzid left join (select a.xh from (select xh from bks_xsjbxx a where not exists ");
        sql.append(" (select 1 from xsxxb b where a.xh = b.xh) union all select a.xh from xsxxb a where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null)) a) c ");
        sql.append(" on b.xh = c.xh) a where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{jxid,xh}, "jzmc");
	}


	public String getJzmc1(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.jzmc002 || a.jzmc003 || a.jzmc006 jzmc from (select a.*, b.xh ");
        sql.append(" from (select jxjz001.jzid jzid001, jxjz001.jzmc jzmc001, jxjz002.jzid jzid002, jxjz002.jzmc jzmc002, jxjz003.jzid jzid003, ");
        sql.append(" jxjz003.jzmc jzmc003, jxjz004.jzid jzid004, jxjz004.jzmc jzmc004,jxjz005.jzid jzid005,jxjz005.jzmc jzmc005,jxjz006.jzid jzid006,jxjz006.jzmc jzmc006 from xg_jxgl_jxjzwhb a left join xg_jxgl_jxjzwhb jxjz001 ");
        sql.append(" on a.jzid = jxjz001.sjid left join xg_jxgl_jxjzwhb jxjz002 on jxjz001.jzid = jxjz002.sjid left join xg_jxgl_jxjzwhb jxjz003 ");
        sql.append(" on jxjz002.jzid = jxjz003.sjid left join xg_jxgl_jxjzwhb jxjz004 on jxjz003.jzid = jxjz004.sjid  left join xg_jxgl_jxjzwhb jxjz005 on jxjz004.jzid = jxjz005.sjid left join xg_jxgl_jxjzwhb jxjz006 on jxjz005.jzid = jxjz006.sjid) a ");
        sql.append(" left join xg_jxgl_jxjzmdb b on a.jzid006 = b.jzid left join (select a.xh from (select xh from bks_xsjbxx a where not exists ");
        sql.append(" (select 1 from xsxxb b where a.xh = b.xh) union all select a.xh from xsxxb a where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null)) a) c ");
        sql.append(" on b.xh = c.xh) a where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "jzmc");
	}
	

}
