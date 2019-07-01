/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-8 ����02:18:44 
 */
package xsgzgl.gygl.rcjc.xswsf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�������-ѧ�������֣���������ְҵ����ѧԺ��
 * @�๦������: (������һ�仰��������������)
 * @���ߣ�lgx[����:1553]
 * @ʱ�䣺 2018-5-8 ����02:18:44
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XswsfDao extends SuperDAOImpl<XswsfForm> {
	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_wsjc_12688_xsfsb");
		super.setKey("guid");
		super.setClass(XswsfForm.class);

	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XswsfForm t)
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
	public List<HashMap<String, String>> getPageList(XswsfForm model, User user)
			throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append("select a.*,b.xn,b.xq,b.mc,decode(b.pfjb,'xj','У��','yj','Ժ��') pfjbmc, ");
		sql.append(" b.xn || nvl(d.xqmc,b.xq) || ' ' ||  b.mc || '��' || decode(b.pfjb,'xj','У��','yj','Ժ��') || '��'  jcrcxx,");
		sql.append(" c.xydm,c.xymc,c.nj,c.zydm,c.zymc,c.xm ,c.bjmc,c.bjdm");
		sql.append(" from xg_gygl_new_wsjc_12688_xsfsb  a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid=b.guid "); 
		sql.append(" left join view_xsjbxx c on a.xh=c.xh ");
		sql.append(" left join xqdzb d on b.xq=d.xqdm ) t where 1=1 "); 
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-5-9 ����08:55:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * XswsfForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXswsfById(XswsfForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xn,nvl(d.xqmc,b.xq) xqmc,b.mc,");
		sql.append(" b.xn || nvl(d.xqmc,b.xq) || ' ' ||  b.mc || '��' || decode(b.pfjb,'xj','У��','yj','Ժ��') || '��'  jcrcxx ");
		sql.append(" from xg_gygl_new_wsjc_12688_xsfsb  a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid=b.guid "); 
		sql.append(" left join xqdzb d on b.xq=d.xqdm ");
		sql.append(" where a.guid=? "); 
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getGuid()});
		
	}


	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-9 ����04:41:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRcXnList() {
		String sql = "select xn from XG_GYGL_NEW_WSJC_JCRCB group by xn  order by  xn";
		return dao.getListNotOut(sql, new String[]{});
	}

	/** 
	 * @����:����ѧ��ѧ�ڻ�ȡ��������ѧ����������������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-10 ����11:43:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWmqsxsMd(String xn, String xq) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t.*,");
		sql.append(" ( select round(avg(a.fs),2)  from xg_gygl_new_wsjc_12688_xsfsb a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid = b.guid ");
		sql.append(" where xn = ? and xq = ? and b.pfjb='yj'  and a.xh=t.xh) yjpjf,");
		sql.append(" ( select round(avg(a.fs),2)  from xg_gygl_new_wsjc_12688_xsfsb a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid = b.guid ");
		sql.append(" where xn = ? and xq = ?  " +
				" and b.pfjb='xj' and a.xh=t.xh) xjpjf, "); 
		sql.append(" (select count(0) from  XG_WJCF_WJCFB a where a.xh=t.xh) wjcfcs "); 
		sql.append(" from  ( select xh,avg(fs) pjfs,xn,xq from ( select xh, sum(fs) fs, mc, xn, xq  from "); 
		sql.append(" (select a.xh, a.fs, b.mc, b.xn, b.xq from xg_gygl_new_wsjc_12688_xsfsb a "); 
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid = b.guid" +
				" where xn = ? and xq = ? ) "); 
		sql.append(" group by xh, mc, xn, xq ) a group by xh,xn,xq) t ");
		sql.append("  where t.pjfs >= 90) where wjcfcs=0 ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xn,xq,xn,xq});
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-10 ����01:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsmcList
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertXsmc(List<HashMap<String, String>> xsmcList, User user) throws Exception {
		List<String> sqlArr =null;
		DAO dao = DAO.getInstance();
		boolean flag = false;
		sqlArr = new ArrayList<String>();
		for (HashMap<String, String> map : xsmcList) {
			String sql = "delete from  xg_gygl_new_wmqsxsmdb where  ";
			//sql += " xh='" + map.get("xh") + "'";
			sql += " xn='" + map.get("xn") + "'";
			sql += " and xq='" + map.get("xq") + "'";
			sqlArr.add(sql);
			break;
		}
		flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrsj = df.format(new Date());
		if( flag){
			sqlArr = new ArrayList<String>();
			for (HashMap<String, String> map : xsmcList) {
				String sql = "insert into  xg_gygl_new_wmqsxsmdb (xh,xn,xq,sjly,xjfs,yjfs,lrr,lrsj) values ( ";
				sql += " '" + map.get("xh") + "'";
				sql += " , '" + map.get("xn") + "'";
				sql += " , '" + map.get("xq") + "'";
				sql += " , '" + "2" + "'";
				sql += " , '" + map.get("xjpjf") + "'";
				sql += " , '" + map.get("yjpjf") + "'";
				sql += " , '" + user.getUserName() + "'";
				sql += " , '" + lrsj + "'";
				sql += ")";
				sqlArr.add(sql);
			}
			flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		}
		
		return flag;
	}

}
