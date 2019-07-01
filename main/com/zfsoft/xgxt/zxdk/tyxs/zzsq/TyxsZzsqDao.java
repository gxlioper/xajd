/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-8 ����11:53:38 
 */
package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ѧ����������ģ��
 * @�๦������: ��������
 * @���ߣ� ����Ӣ[����:1177]
 * @ʱ�䣺 2015-4-8 ����11:53:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TyxsZzsqDao extends SuperDAOImpl<TyxsZzsq> {

	private static final String YSH = "ysh";

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		setClass(TyxsZzsq.class);
		setKey("id");
		setTableName("xg_tyxsrx_xfzzsqb");

	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TyxsZzsq t)
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
	public List<HashMap<String, String>> getPageList(TyxsZzsq t, User user)
			throws Exception {
		// TODO �Զ����ɷ������

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");

		StringBuilder sql = new StringBuilder();
		if(!"10511".equals(Base.xxdm)){
			sql.append("select * from (");
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xb,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t2.lxdh, t2.mz, t3.csrq, t3.xxszd,t3.jtdz, t3.jtyb,t4.*,");
			sql.append("t2.zybj,t2.zybjmc,x1.sydm,x1.symc,");
			sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc ");
			sql.append("from xg_tyxsrx_xfzzsqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
			sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
			sql.append(" left join  xsxxb t3 on t1.xh =t3.xh  left join zzmmdmb t4 on t3.zzmm = t4.zzmmdm ");
			sql.append(") t where 1=1 ");
		}else{
			sql.append(" select *  from (");
			sql.append(" select t1.id,");
			sql.append(" t1.xh,");
			sql.append(" t1.xn,");
			sql.append(" t1.lsgx,");
			sql.append(" t1.fxjdxlcc,");
			sql.append(" t1.rwqrxsj,");
			sql.append(" t1.rwsj,");
			sql.append(" t1.tysj,");
			sql.append(" t1.fxsj,");
			sql.append(" t1.fxjdnx,");
			sql.append(" t1.sqxfzj,");
			sql.append(" t5.je dyzzxf,");
			sql.append(" t6.je dezzxf,");
			sql.append(" t7.je dszzxf,");
			sql.append(" t8.je dsizzxf,");
			sql.append(" t1.sqsj,");
			sql.append(" t1.shzt,");
			sql.append(" t1.splcid,");
			sql.append(" t1.sqly,");
			sql.append(" t1.dkbj,");
			sql.append(" t1.yhdm,");
			sql.append(" t1.dkhth,");
			sql.append(" t1.dkkssj,");
			sql.append(" t1.dkjssj,");
			sql.append(" t1.dklx,");
			sql.append(" t2.xm,");
			sql.append(" t2.xydm,");
			sql.append(" t2.xymc,");
			sql.append(" t2.zydm,");
			sql.append(" t2.zymc,");
			sql.append(" t2.bjdm,");
			sql.append(" t2.bjmc,");
			sql.append(" t2.nj,");
			sql.append(" t2.xb,");
			sql.append(" t2.sfzh,");
			sql.append(" t2.xz,");
			sql.append(" t2.sjhm,");
			sql.append(" t2.xmsj sjdh,");
			sql.append(" t2.dzyx,");
			sql.append(" t2.lxdh,");
			sql.append(" t2.mz,");
			sql.append(" t3.csrq,");
			sql.append(" t3.xxszd,");
			sql.append(" t3.jtdz,");
			sql.append(" t3.jtyb,");
			sql.append(" t4.*,");
			sql.append(" decode(t1.shzt,");
			sql.append("  '0',");
			sql.append("  'δ�ύ',");
			sql.append("  '1',");
			sql.append("  'ͨ��',");
			sql.append("  '2',");
			sql.append("  '��ͨ��',");
			sql.append("  '3',");
			sql.append("  '�˻�',");
			sql.append("  '5',");
			sql.append("  '�����',");
			sql.append(" t1.shzt) shztmc");
			sql.append(" from xg_tyxsrx_xfzzsqb t1");
			sql.append(" left join view_xsbfxx t2");
			sql.append(" on t1.xh = t2.xh");
			sql.append(" left join xsxxb t3");
			sql.append(" on t1.xh = t3.xh");
			sql.append(" left join zzmmdmb t4");
			sql.append(" on t3.zzmm = t4.zzmmdm");
			sql.append(" left join");
			sql.append(" (select *");
			sql.append(" from (select w.*,");
			sql.append(" row_number() over(partition by w.id order by w.nd) rn1");
			sql.append(" from XG_ZXDK_TYFBY_FDNYB w)");
			sql.append(" where rn1 = 1");
			sql.append(" ) t5  on t1.id = t5.id");
			sql.append(" left join");
			sql.append(" (select *");
			sql.append(" from (select y.*,");
			sql.append(" row_number() over(partition by y.id order by y.nd) rn2");
			sql.append(" from XG_ZXDK_TYFBY_FDNYB y)");
			sql.append(" where rn2 = 2");
			sql.append(" ) t6  on t1.id = t6.id");
			sql.append(" left join");
			sql.append(" (select *");
			sql.append(" from (select x.*,");
			sql.append(" row_number() over(partition by x.id order by x.nd) rn3");
			sql.append(" from XG_ZXDK_TYFBY_FDNYB x)");
			sql.append(" where rn3 = 3");
			sql.append(" ) t7  on t1.id = t7.id");
			sql.append(" left join");
			sql.append(" (select *");
			sql.append(" from (select z.*,");
			sql.append(" row_number() over(partition by z.id order by z.nd) rn4");
			sql.append(" from XG_ZXDK_TYFBY_FDNYB z)");
			sql.append(" where rn4 = 4");
			sql.append(" ) t8  on t1.id = t8.id");
			sql.append("  ) t where 1 = 1");
			
		}


		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

	}

	/**
	 * 
	 * @����:����id ��ѯ������Ϣ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-9 ����04:09:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 *            void ��������
	 * @throws
	 */
	public HashMap<String, String> getSqxxByID(String id) {
		String sql = "select * from  xg_tyxsrx_xfzzsqb where id = ?";

		return dao.getMapNotOut(sql, new String[] { id });
	}

	/**
	 * 
	 * @����:��˲�ѯ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:58:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getShList(TyxsZzsq t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm",
				"bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,");
		sql.append("t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xb,t2.sfzh,t2.xy,t2.lxdh, t2.mz,");
		sql.append("t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,");
		sql.append(" '[' || c.mc || ':' || ");
		sql
				.append("decode(b.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.shzt) || ']' shztmc,");
		sql.append("c.gwz, t3.csrq, t3.xxszd,t3.jtdz, t3.jtyb,t4.*,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid, ");
		sql
				.append("row_number() over(partition by t1.id order by b.shsj desc) rn");
		sql.append(" from xg_tyxsrx_xfzzsqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql
		.append(" left join  xsxxb t3 on t1.xh =t3.xh  left join zzmmdmb t4 on t3.zzmm = t4.zzmmdm ");

		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append("left join xg_xtwh_spgw c on b.gwid = c.id ");

		if (YSH.equals(t.getShzt())) {
			sql.append("where b.shzt not in ('0', '4') ");
		} else {
			sql.append(" where b.shzt in ('0', '4') ");
		}
		sql
				.append(" and b.gwid in(select spgw from xg_xtwh_spgwyh where spyh = ?))t where (1=1) and rn=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);

		return super.getPageList(t, sql.toString(), StringUtils.joinStrArr(
				new String[] { user.getUserName() }, inputV));
	}

	/**
	 * 
	 * @����:��ѯѧ���������ѧ�ź�����ѧ��
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-14 ����01:33:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getZzsqByXh(TyxsZzsq t) {

		String sql = "select * from xg_tyxsrx_xfzzsqb where  xh = ? and xn = ?";
		return dao.getMapNotOut(sql, new String[] { t.getXh(), t.getXn() });
	}

	/**
	 * 
	 * @����:����ѧ�ź�ѧ���ѯ����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-19-����03:57:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzsq t) {

		StringBuilder sql = new StringBuilder(
				"select count(1) c from xg_tyxsrx_xfzzsqb where xh = ? and xn = ?");
		String[] params = null;
		
		if (!StringUtil.isNull(t.getId())) {
			sql.append(" and id <> ?");
			params = new String[] { t.getXh(), t.getXn(), t.getId() };
		} else {
			params = new String[] { t.getXh(), t.getXn() };
		}
		
		return dao.getOneRs(sql.toString(), params, "c");
	}
	
	/**
	 * 
	 * @����:��ȡ����List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhdm dm,yhmc mc from dmk_yh order by yhdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*��ʦ�󱣴���Ƚ��*/
	public boolean saveNdJeHsd(String[] nds,String[] jes,String id) throws Exception{
		
		ArrayList<String> paralist = new ArrayList<String>();
		for(int i=0;i<nds.length;i++){
			StringBuilder sql = new StringBuilder();
			sql.append("insert into XG_ZXDK_TYFBY_FDNYB values(");
			sql.append("'"+id+"',");
			sql.append("'"+nds[i]+"',");
			sql.append("'"+jes[i]+"'");
			sql.append(")");
			paralist.add(sql.toString());
		}
		int flag = dao.runBatch(paralist.toArray(new String[]{})).length;
		if(flag == nds.length){
			return true;
		}else{
			return false;
		}
	}
	
	/*��ʦ����Ƚ��ɾ��*/
	public boolean delNdJe(String id) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_ZXDK_TYFBY_FDNYB where id = ?");
		return dao.runUpdate(sql.toString(), new String[]{id});
	}
	
	/*��Ƚ��list*/
	public List<HashMap<String, String>> getNdJe(String id){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_ZXDK_TYFBY_FDNYB where id = ?");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @����:��ȡ����map
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getYhListByYhdm(String yhdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhmc  from dmk_yh where yhdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{yhdm}, "yhmc");
	}
	
	public boolean afterLastAudit(String key,String xn) throws Exception {
		// TODO �Զ����ɷ������

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_tyxsrx_xfzzjgb where xh = ? and xn = ?");
		return dao.runUpdate(sql.toString(), new String[]{key,xn});
	}
}
