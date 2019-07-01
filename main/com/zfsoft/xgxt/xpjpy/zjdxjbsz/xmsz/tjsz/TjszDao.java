/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-4-18 ����08:17:35 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������-��Ŀ����-��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-18 ����08:17:53 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszDao extends SuperDAOImpl<TjszForm>{
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
	}
	
	private static String CHAR_DH = ",";
	
	/**
	 * ��ͨ��ѯ����
	 * Meng.Wei[����:1186]
	 */
	public List<HashMap<String, String>> getPageList(TjszForm model)
			throws Exception {
		return null;
	}

	/**
	 * �߼���ѯ����
	 * Meng.Wei[����:1186]
	 */
	public List<HashMap<String, String>> getPageList(TjszForm model,
			User user) throws Exception {
		return null;
	}
	
	/**
	 * @����: ����Ŀ��ѯ��˱��еļ�¼��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����09:26:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFlowData(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_zjdx_pjpy_xmsq where xmdm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmdm}, "count");
	}
	
	/**
	 * @����: ��ѯ ������� ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����11:25:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTj() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjkb order by tjmc ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��ѯ ������ϵ�� ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����11:25:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxb ");
		return dao.getListNotOut(sql.toString(), null);
	}
	
	/**
	 * @����: ��ѯ ������ϵ���ձ� ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����11:25:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTjGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_tjgxdzb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: ��ѯ ��Ŀ������ ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����11:25:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a ");
		sql.append("where a.xmdm = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * @����: ��ȡ���롢�����б�
	 * @���ߣ� Meng.Wei[����:1186]
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
	 * @����: ���±��棬��ɾ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����02:54:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runDeal(String xmdm, List<TjszForm> list)
		throws Exception {
		int[] result = null;
		
		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("delete from xg_pjpy_new_xmtjb ");
		sb.append(" where xmdm='");
		sb.append(xmdm);
		sb.append("'");
		sqlList.add(sb.toString());
		for (TjszForm form : list) {
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
	 * @����: ɾ��
	 * @���ߣ�Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����02:53:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param tjdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
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
	 * @����: ��ѯ����������ֵ,������������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����10:10:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm,String xh)
		throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_pjpy_new_xmtjb a,xg_pjpy_new_tjkb b,xg_pjpy_new_tjgxb c,xg_pjpy_new_tjgxdzb d  ");
		sql.append("  where a.tjdm=b.tjdm and a.gxdm=c.gxdm and b.tjdm=d.tjdm and c.gxdm=d.gxdm ");
		sql.append(" and a.xmdm=?");
		if(xh != null && !xh.trim().equals("")){
			CsszForm csszModel = new CsszDao().getModel();
			sql.append(" and (a.yyfw='all' or a.yyfw in(select lxdm from xg_pjpy_new_tsxsb where xh='");
			sql.append(xh);
			sql.append("')");			
			sql.append("  or a.yyfw in(");
			sql.append(" select distinct a.lbdm from xg_xtwh_bjdlb a,xg_xtwh_bjlbb b,xg_zjdx_pjpy_cpmdb c ");
			sql.append(" where a.lbdm=b.lbdm and b.bjdm=c.bjdm and xh='");
			sql.append(xh);
			sql.append("' and xn='");
			sql.append(csszModel.getXn());
			sql.append("')");
			sql.append(")");
		}
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @����: �۲�������Ŀ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����10:32:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZhcpTjxm(){
		StringBuilder sql = new StringBuilder();
		sql.append("select xmdm dm, xn, xn || xmmc mc ");
		sql.append("from (select a.xmdm,a.xn,a.xmmc,a.fjdm,px, ");
		sql.append("case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, ");
		sql.append("decode(a.xmlx, '0', '�ȼ�', '1', '��ֵ', a.xmlx) xmlx from xg_zjdx_pjpy_zcxmb a ");
		sql.append("left join (select xmdm, max(result) result from (select xmdm,mc,wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append("from xg_zjdx_pjpy_zcxmxxb t) s group by xmdm) b on a.xmdm = b.xmdm ");
		sql.append("START WITH a.fjdm = 'top' CONNECT BY PRIOR a.xmdm = a.fjdm ORDER SIBLINGS BY to_number(px)) t ");
		sql.append("where (t.fjdm = 'top' or exists (select 1 ");
		sql.append("from xg_zjdx_pjpy_zcxmb t2 where t.fjdm = t2.xmdm and t2.fjdm = 'top')) ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: �������   ˼���������ʵȼ�����{���㡢���á��ϸ񡢲��ϸ�}
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-3 ����04:46:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSxzzszDjList()throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select pjdjdm dm,pjdjmc mc from xg_pjpy_new_pjdj where pjxmmc = '˼���������ʵȼ�����' order by to_number(pjdjdm) desc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @����: �������   ˼���������ʵȼ�����{���㡢���á���⡢���񡢲�����}
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-3 ����04:47:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTzjkDjList()throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select pjdjdm dm,pjdjmc mc from xg_pjpy_new_pjdj where pjxmmc = '���ʽ����ȼ�����' order by to_number(pjdjdm) desc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
}
