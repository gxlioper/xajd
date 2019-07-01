/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:34:45 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-12 ����09:34:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DkbcDao extends SuperDAOImpl<DkbcModel> {

	private static final String YSH = "ysh";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_dkbcsqb");
		super.setClass(DkbcModel.class);
		super.setKey("id");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DkbcModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DkbcModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj ");
		sql.append("from xg_zxdk_dkbcsqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	
	public List<HashMap<String, String>> getAudingList(DkbcModel t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2","xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm","bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from xg_zxdk_dkbcsqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
		sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '")
		   .append(user.getUserName()).append("')  ");

		if (YSH.equals(t.getShzt())) {
			sql.append("and b.lczt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		} else {
			sql.append(" and b.lczt in ('0', '4')) t1) t2 where rn = 1 ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);

		return super.getPageList(t, sql.toString(), inputV);
	}
	
    

	@Override
	public DkbcModel getModel(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t3.mc yjxfmc,t4.mc dclbmc,t10.yhmc");
		sql.append(" from xg_zxdk_dkbcsqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
		sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ");
		sql.append(" left join xg_zxdk_dclbdmb t4 on t1.dclb=t4.dm ) t where id=?");
		return getModel(sql.toString(),new String[]{id});
	}

	/**��ѧ��ѧ���ѯ�Ƿ������� */
	
	public String getCountByXhAndXn(DkbcModel djjdForm) {
		
		String sql = "select count(1) count from xg_zxdk_dkbcsqb where xh = ?";
		
		return dao.getOneRs(sql, new String[]{djjdForm.getXh()}, "count");
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
	
	/**
	 * 
	 * @����:��ȡ�������List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDclbList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dm,mc from xg_zxdk_dclbdmb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����:��ȡѧ��List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXfList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dm,mc from xg_zxdk_xfdmb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//����ʦ����ѧ��������������ѯ��ѧ�������������Դ�ش������������
	public HashMap<String, String> getHzsfDxDklxChange(String xh,String dklx,String xn){
		StringBuilder sql = new StringBuilder();
		if(dklx.equals("��Դ�ش���")){
			sql.append(" select t.dkje, t.dkyh, t.htbh, t.dkkssj,t.dkqx,");
			sql.append(" to_char(to_date(t.dkkssj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy') n,");
			sql.append(" to_char(to_date(t.dkkssj, 'yyyy-MM-dd hh24:mi:ss'), 'mm') y,");
			sql.append("  to_char(to_date(t.dkkssj, 'yyyy-MM-dd hh24:mi:ss'), 'dd') d");
			sql.append(" from xg_zxdk_syddk t");
			sql.append(" where t.xn = ?");
			sql.append(" and t.xh = ?");
			sql.append(" ");
		}else if(dklx.equals("������ѧ����")){
			sql.append(" select t.dkje,");
			sql.append(" t.yhmc dkyh,");
			sql.append(" t.htbh,");
			sql.append(" to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy-mm-dd') dkkssj,");
			sql.append(" to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy') n,");
			sql.append(" to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'mm') y,");
			sql.append("  to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'dd') d,");
			sql.append(" t.dkqx");
			sql.append(" from xg_zxdk_xydkjgb t");
			sql.append(" where t.xn = ?");
			sql.append(" and t.xh = ?");
		}else{
			return null;
		}
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	public String produceDkjssj(String dkkssj,String dkqx,String dklx){
		if(dklx.equals("������ѧ����")){
			dkqx = Integer.parseInt(dkqx)*12+"";
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(add_months(to_date(?,'yyyy-MM-dd hh24:mi:ss'),?),'yyyy-mm-dd') dkjssj from dual ");
		return dao.getOneRs(sql.toString(), new String[]{dkkssj,dkqx}, "dkjssj");
	}
 	
}
