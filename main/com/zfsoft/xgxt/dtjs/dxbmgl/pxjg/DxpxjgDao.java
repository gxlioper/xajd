package com.zfsoft.xgxt.dtjs.dxbmgl.pxjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @������������У��ѵ���dao
 * @author������ ��1346��
 * @date��2017-11-1 ����03:48:19 
 */
public class DxpxjgDao extends SuperDAOImpl<DxpxjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxpxjgForm t) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DxpxjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer("select * from (select a.*,b.XM,b.XYDM,b.XYMC,b.ZYDM,b.ZYMC,b.BJDM,b.BJMC,b.NJ,c.pxqc,c.pxsj ");
		sql.append(" from XG_DTJS_DXBMJGB a left join view_xsxxb b on a.xh=b.XH left join xg_dtjs_dxpxb c on a.pxid=c.id) where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @��������������ѧ�Ż�ȡ��ѵ�б�
	 * @author������ ��1346��
	 * @date��2017-11-11 ����11:52:11 
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListOfPx(DxpxjgForm t) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer("select * from (select a.xh,a.pxid,b.pxqc,b.pxsj,b.pxnr,b.kqcjbfb,b.sjcjbfb,b.bjcjbfb,b.kscjbfb,b.bmkssj,c.jgid from xg_dtjs_dxbmsqb a ");
		sql.append(" left join xg_dtjs_dxpxb b on a.pxid=b.id left join xg_dtjs_dxbmjgb c on a.xh=c.xh and a.pxid=c.pxid where c.jgid is null and a.xh='"+t.getXh()+"') where 1=1");
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @����������������ѵ���
	 * @author������ ��1346��
	 * @date��2017-11-10 ����05:45:36 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean save(DxpxjgForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("insert into xg_dtjs_dxbmjgb(pxid,xh,sjly,sqid,sqsj,kqcj,sjcj,bjcj,kscj,zpcj,pjjg)values(?,?,'0',?,to_char(sysdate,'yyyy-mm-dd'),?,?,?,?,?,?)");
		return dao.insert(sb.toString(), new String[]{t.getPxid(),t.getXh(),t.getSqid(),t.getKqcj(),t.getSjcj(),t.getBjcj(),t.getKscj(),t.getZpcj(),t.getPjjg()});
	}
	/** 
	 * @������������������
	 * @author������ ��1346��
	 * @date��2017-11-11 ����04:47:49 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updatePf(DxpxjgForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update xg_dtjs_dxbmjgb set kqcj=?,SJCJ=?,BJCJ=?,KSCJ=?,ZPCJ=?,PJJG=? where jgid=?");
		return dao.insert(sb.toString(), new String[]{t.getKqcj(),t.getSjcj(),t.getBjcj(),t.getKscj(),t.getZpcj(),t.getPjjg(),t.getJgid()});
	}
	/** 
	 * @������������ȡѧ����ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-11 ����09:57:55 
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXspx(String jgid) throws Exception {
		StringBuffer sql = new StringBuffer("select * from ( select a.*,b.XM,b.XYDM,b.XYMC,b.ZYDM,b.ZYMC,b.XB,b.SFZH, ");
		sql.append("b.BJDM,b.BJMC,b.NJ,c.pxqc,c.pxsj,c.kqcjbfb,c.sjcjbfb,c.bjcjbfb,c.kscjbfb");
		sql.append(" from XG_DTJS_DXBMJGB a left join view_xsxxb b on a.xh = b.XH left join xg_dtjs_dxpxb c on a.pxid = c.id) where jgid = ?");
		String[] outV=new String[]{"jgid","pxid","xh","sjly","sqid","sqsj","kqcj","sjcj","bjcj","kscj","zpcj","pjjg","xm","xymc","zymc","bjmc","nj","sfzh","xb","pxqc","pxsj","kqcjbfb","sjcjbfb","bjcjbfb","kscjbfb"};
		return dao.getMap(sql.toString(), new String[]{jgid}, outV);
	}
	/** 
	 * @��������������ѧ�ź���ѵid��ȡ��ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-11 ����03:20:29 
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXspxByxhpxid(String xh,String pxid) throws Exception {
		StringBuffer sql = new StringBuffer("select * from (select a.xh,a.pxid,b.pxqc,b.pxsj,b.pxnr,b.kqcjbfb,b.sjcjbfb,b.bjcjbfb,b.kscjbfb,b.bmkssj,c.jgid");
		sql.append(" from xg_dtjs_dxbmsqb a left join xg_dtjs_dxpxb b on a.pxid = b.id left join xg_dtjs_dxbmjgb c on a.xh = c.xh and a.pxid = c.pxid) where xh=? and pxid=?");
		String[] outV=new String[]{"xh","pxid","pxqc","pxsj","pxnr","kqcjbfb","sjcjbfb","bjcjbfb","kscjbfb","bmkssj","jgid"};
		return dao.getMap(sql.toString(), new String[]{xh,pxid}, outV);
	}
	
	/** 
	 * @����������ɾ������
	 * @author������ ��1346��
	 * @date��2017-11-11 ����04:58:22 
	 * @param sql
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws 
	 */
	public int del(String sql,String[] values) throws Exception {
		return dao.runDelete(sql, values);
	}
	
	@Override
	protected void setTableInfo() {
		this.setKey("jgid");
		this.setTableName("xg_dtjs_dxbmjgb");
		this.setClass(DxpxjgForm.class);
	}
}
