/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����05:00:16 
 */  
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-31 ����05:00:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszDao extends SuperDAOImpl<TjszModel> {
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
	}

	/** 
	 * @����: ����Ŀ�����ѯ�����õ�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����05:02:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getSztjList(String xmdm) {
		return null;
	}	
	
	private static String CHAR_DH = ",";

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(TjszModel model)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @����:�߼���ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(TjszModel model,
			User user) throws Exception {
		return null;
	}

	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTj() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjkb a where a.tjdm not in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? ) order by tjmc ");
		return dao.getListNotOut(sql.toString(), new String[]{"002"});
	}

	/**
	 * 
	 * @����:��ѯ���й�ϵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxb a  ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @����:��ѯ����������ϵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTjGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxdzb a  where a.tjdm  not in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? )");
		return dao.getListNotOut(sql.toString(), new String[]{"002"});
	}

	/**
	 * 
	 * @����:��ѯ����������ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a  ");
		sql.append(" where a.xmdm=? ");
		sql.append(" and a.tjdm not in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? ) ");
		String[] inputValue = { xmdm,"002" };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @����:��ѯ����������ֵ,������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm)
			throws Exception {
		return getTjszGl(xmdm,null);
	}
	
	/**
	 * 
	 * @����:��ѯ����������ֵ,������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm,String xh)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a,xg_pjpy_new_tjkb b,xg_pjpy_new_tjgxb c,xg_pjpy_new_tjgxdzb d  ");
		sql.append("  where a.tjdm=b.tjdm and a.gxdm=c.gxdm and b.tjdm=d.tjdm and c.gxdm=d.gxdm ");
		sql.append(" and a.xmdm=?");
		if(xh != null && !xh.trim().equals("")){
			CsszModel csszModel = new CsszDao().getModel();

			sql.append(" and (a.yyfw='all' or a.yyfw in (select lxdm from xg_pjpy_new_tsxsb where xh='");
			sql.append(xh);
			sql.append("' and xn='");
			sql.append(csszModel.getXn());
			sql.append("' and xq='");
			sql.append(csszModel.getXq());
			sql.append("')");
			sql.append("  or a.yyfw in (");
			sql.append(" select distinct a.lbdm from xg_xtwh_bjdlb a,xg_xtwh_bjlbb b,xg_pjpy_new_cpmdb c ");
			sql.append(" where a.lbdm=b.lbdm and b.bjdm=c.bjdm and c.xh='");
			sql.append(xh);
			sql.append("' and c.xn='");
			sql.append(csszModel.getXn());
			sql.append("' and c.xq='");
			sql.append(csszModel.getXq());
			sql.append("')");
			sql.append(")");
		}
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean runDeal(String xmdm, List<TjszModel> list)
			throws Exception {
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("delete from xg_pjpy_new_xmtjb ");
		sb.append(" where xmdm='");
		sb.append(xmdm);
		sb.append("'");
		sqlList.add(sb.toString());
		for (TjszModel form : list) {
			sb = new StringBuffer();
			String tjz = form.getTjz();
			if (tjz != null) {
				tjz = tjz.trim();
			} else {
				tjz = "";
			}
			sb.append("insert into xg_pjpy_new_xmtjb");
			sb.append("(xmdm,tjdm,yyfw,gxdm,tjz,ylzq)");
			sb.append(" values('");
			sb.append(xmdm);
			sb.append("','");
			sb.append(URLDecoder.decode(URLDecoder.decode(form.getTjdm(),"UTF-8"),"UTF-8"));
			sb.append("','");
			sb.append(form.getYyfw());
			sb.append("','");
			sb.append(form.getGxdm());
			sb.append("','");
			sb.append(URLDecoder.decode(URLDecoder.decode(form.getTjz(),"UTF-8"),"UTF-8"));
			sb.append("','");
			sb.append(form.getYlzq());
			sb.append("')");
						
			sqlList.add(sb.toString());
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean delDeal(String xmdm, String tjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_new_xmtjb a  ");
		sql.append(" where a.xmdm=? and a.tjdm=? ");
		String[] input = { xmdm, tjdm };
		return dao.runUpdate(sql.toString(), input);
	}

	/**
	 * 
	 * @����:��ȡ���롢�����б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: 
	 * @param sql
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmMc(String bm,String dm,String mc) throws Exception {
		String sql = "select distinct " + dm + "," + mc + " from " + bm + " order by " + dm;
		return dao.getListNotOut(sql, null);
	}
	
	/**
	 * 
	 * @����:��ѯ��������(��������)
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTj(String lbdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjkb a where a.tjdm in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? ) order by tjmc ");
		return dao.getListNotOut(sql.toString(), new String[]{lbdm});
	}
	
	/**
	 * 
	 * @����:��ѯ����������ϵ(��������)
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTjGx(String lbdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxdzb a where a.tjdm in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? ) ");
		return dao.getListNotOut(sql.toString(), new String[]{lbdm});
	}
	
	/**
	 * 
	 * @����:��ѯ����������ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm,String lbdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a  ");
		sql.append(" where a.xmdm=? ");
		sql.append(" and a.tjdm in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? ) ");
		String[] inputValue = { xmdm,lbdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @����:��ѯ����������ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszJtpj(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a  ");
		sql.append(" where a.xmdm=? ");
		sql.append(" and a.tjdm  in (select tjdm from xg_xspjzz_new_tjdmb where lbdm = ? ) ");
		String[] inputValue = { xmdm,"002" };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @��������ȡ������������ѧ���б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��16�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getbfhList(TjszModel model,String xmdm,List<String> xhList) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select t1.sqid, t1.splc, t4.gwid, t1.xh, decode(t2.xb,'1','��','2','Ů') xb, t2.xm,t3.bjdm, t3.bjmc,t3.zydm, t3.zymc, t3.xymc, t3.nj ");
		sql.append("  from xg_pjpy_new_xmsq t1 ");
		sql.append("  left join xsxxb t2 ");
		sql.append("    on t1.xh = t2.xh ");
		sql.append("  left join view_njxyzybj_all t3 ");
		sql.append("    on t2.bjdm = t3.bjdm ");
		sql.append("  left join XG_XTWH_SHZTB t4 ");
		sql.append("  on t1.sqid = t4.ywid ");
		sql.append(" where t1.xmdm=? ");
		sql.append("   and t4.shzt in ('0', '4') ");
		//����xhList����ҳ��ȡ��������ע����գ�����ѡ��ֱ��ƴ��sql�ķ�ʽ
		sql.append("   and t1.xh in ('");
		for(String xh:xhList){
			sql.append(xh);
			sql.append("','");
		}
		sql.append("') )");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), StringUtils.joinStrArr(new String[]{xmdm},inputV));
	}
}
