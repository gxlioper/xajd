/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-15 ����11:39:31 
 */
package com.zfsoft.xgxt.ystgl.jtgl.ystwh;


import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����Ź���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2016-1-15 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YstwhDao extends SuperDAOImpl<YstwhForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YstwhForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YstwhForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchSztzShTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmlbmc,t3.ystlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm fzrxm,t6.xm zdlsxm,t7.gkdwmc,t9.lxdh zdlslxfs,t7.gkdwdm xydm,t10.zcmc ");
		sql.append("from XG_YSTGL_YSTJGB t1 left join XG_YSTGL_XMLB t2 on t1.xmlbdm = t2.xmlbdm left join XG_YSTGL_YSTLB t3 on t1.ystlbdm=t3.ystlbdm ");
		sql.append(" left join  ");
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t4  ");
			sql.append(" on t1.jtr=t4.yhm");
		}else{
			sql.append("  xsxxb t4");
			sql.append(" on t1.jtr=t4.xh");
		}
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t5");
		sql.append(" on t1.fzr = t5.yhm left join yhb t6 on t1.zdls = t6.yhm left join XG_YSTGL_GKDW t7 on t1.gkdwdm=t7.gkdwdm left join fdyxxb t9 on t1.zdls=t9.zgh");
		sql.append(" left join zcb t10 on t1.zdlszc=t10.zcdm) t where 1=1 ");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and t.jtr = '"+user.getUserName()+"' ");
		}
		if(!user.getUserType().equalsIgnoreCase("stu")){
			sql.append(searchTjByUser);
		}
		sql.append(searchTj);
		sql.append(" order by sqsj desc ");
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * @throws Exceptionzil
	 * 
	 * @����:��ȡ�����Ž��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-15 ����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getYstwh(YstwhForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.ystlbmc,t3.xmlbmc,t4.xm jtrxm,t5.xm fzrxm,t6.xm zdlsxm,t7.bmmc ssbmmc,t8.zcmc ");
		sql.append(",t9.bmdm ssbm,t9.lxdh zdlslxfs,t10.gkdwmc ");
		sql.append(" from XG_YSTGL_YSTJGB t1  left join XG_YSTGL_YSTLB t2 on t1.ystlbdm=t2.ystlbdm");
		sql.append(" left join XG_YSTGL_XMLB t3 on t1.xmlbdm=t3.xmlbdm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm ");
		if(t.getFzrlb().equals("��ʦ")){
			sql.append(" left join yhb t5 on t1.fzr = t5.yhm");
		}else{
			sql.append(" left join xsxxb t5 on t1.fzr = t5.xh ");
		}
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm ");
		sql.append(" left join zxbz_xxbmdm t7 on  t5.szbm = t7.bmdm");
		sql.append(" left join zcb t8 on t1.zdlszc = t8.zcdm");
		sql.append(" left join fdyxxb t9 on t1.zdls=t9.zgh left join XG_YSTGL_GKDW t10 on t1.gkdwdm=t10.gkdwdm");
		sql.append(" where t1.ystid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getYstid() });
	}


	/**
	 *�ж������Ž���Ƿ��Ѵ���, ���ڵ�����ͬѧ�����������Ŀ�����ظ�
	 */
	public boolean isHaveYstxx(YstwhForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_YSTGL_YSTJGB where  xn>=?  and ystxmmc=?");
		if(null!=model.getYstid()){
			sql.append(" and ystid<>'"+model.getYstid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getYstxmmc()}, "num");
		return Integer.parseInt(num)>0;
	}


	/**
	 * 
	 * ɾ�������Ž��
	 */
	public boolean delYstwh(String id) throws Exception {
		String sql = "delete from XG_YSTGL_YSTJGB where ystid=?";
		return dao.runUpdate(sql, new String[] { id });
	}
	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from( select ystid from xg_ystgl_rtsqb where ystid in("+values+")");
		sql.append(" union all select ystid from xg_ystgl_rtjgb where ystid in("+values+"))");
		int result = dao.getOneRsint(sql.toString());
		return result > 0;
	}

	
	@Override
	protected void setTableInfo() {
		super.setClass(YstwhForm.class);
		super.setKey("ystid");
		super.setTableName("XG_YSTGL_YSTJGB");

	}
	
	//�������ۺ�ά��ά������״̬ʱ����cysl
	public boolean update_stcysl(String cysl,String ystid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_YSTGL_YSTJGB set cysl = ? where ystid = ?");
		return dao.runUpdate(sql.toString(), new String[]{cysl,ystid});
	}
	
}
