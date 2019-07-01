package com.zfsoft.xgxt.qgzx.jfcjgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import java.util.HashMap;
import java.util.List;
/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ���ѳ�����  ����ά��dao
 * @���ߣ� zhangjw 
 * @ʱ�䣺2013-04-15 ����09:46:37 
 * @�汾�� V5.1.75
 * @�޸ļ�¼: 
 */
public class CjffSjwhDAO  extends SuperDAOImpl<CjffwhForm>{

	@Override
	public List<HashMap<String, String>> getPageList(CjffwhForm t)
			throws Exception {
		
		return null;
	}
	/**
	 * 
	 * @����: ��ȡ��λ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����09:49:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param bmdm
	 * @param gwdm
	 * @return
	 * HashMap<String,String> �������� 
	 */
	public HashMap<String, String>  getGwxx(String xn,String bmdm,String gwdm){
		StringBuffer sb=new StringBuffer();
		sb.append("select t.* ,t2.sx from xg_qgzx_gwxxb t ");
		sb.append(" left join xg_qgzx_yrdwdmb t1 on t.yrdwid = t1.id ");
		sb.append(" left join XG_QGZX_GWXZDMB t2 on t.GWlb = t2.GWXZDM ");
		sb.append(" where xn=? and t1.xydm=? and gwdm=?");
		return dao.getMapNotOut(sb.toString(), new String[]{xn,bmdm,gwdm});
		
	}
	/**
	 * 
	 * @����: ���¾��ѷ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����04:15:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cf
	 * @return
	 * boolean �������� 
	 */
	public boolean updataFfxx(CjffwhForm cf){
		StringBuffer sb=new StringBuffer();
		sb.append("update XG_QGZX_JCFFB t set t.gs=?,t.je=?,t.bz=? where t.xh=? and t.gwdm=? and yf=?");
		try {
			return dao.runUpdate(sb.toString(), new String[]{cf.getGs(),cf.getJe(),cf.getBz(),cf.getXh(),cf.getGwdm(),cf.getFfsj()});
		} catch (Exception e) {
			logger.error("���������¾��ѷ�����Ϣʧ��");
			throw new RuntimeException("���������¾��ѷ�����Ϣʧ��");
		}
	}
	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageList(CjffwhForm model,User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		//Ȩ�޿��� 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			//searchTjQx+=" and yrbm = '"+user.getUserDep()+"' ";
			searchTjQx+=SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		}
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM (");
		sql.append("SELECT t1.*,t2.RDDC,t3.DCMC ");
		sql.append("FROM VIEW_NEW_DC_QGZX_CIFFSJWH t1 ");
		sql.append("LEFT JOIN xg_xszz_new_knsjgb t2 ON t1.XH = t2.XH AND t1.XN = t2.XN ");
		sql.append(" AND t1.xq = decode(t2.xq,'on',t1.xq,t2.xq) ");
		sql.append("LEFT JOIN xg_xszz_new_kndcdmb t3 ON t2.RDDC = t3.DCDM ");
		sql.append(") a WHERE 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(searchTjQx);
		sql.append("order by ffsj,yrdw,xh desc");
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	protected CjffwhForm getModel(CjffwhForm t, String sql, String[] input)
			throws Exception {
		// TODO �Զ����ɷ������
		return super.getModel(t, sql, input);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_qgzx_cjff");
		super.setKey("wbh");
	}
	/**
	 *  �Ƿ���ڷ�����Ϣ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����02:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean �������� 
	 */
	public boolean isHaveFfxx(String wbh,String xh,String xn,String yrdwdm,String gwdm,String ffsj){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_qgzx_cjff t where t.xh=? and t.xn=? and t.yrbm=? and t.gwdm=? and ffsj=? and wbh<> nvl(?,'-1')");
		List<HashMap<String, String>> list=dao.getListNotOut(sb.toString(), new String[]{xh,xn,yrdwdm,gwdm,ffsj,wbh});
		return null==list||list.size()<=0?false:true;
	}
	
	/**
	 * 
	 *
	 */
	public List<HashMap<String, String>> getCjffSbbDclist(CjffwhForm t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append(" select rownum r, a.*");
		sql.append(" from (select");
		sql.append(" decode(sjhm, null, qsdh,sjhm) qsdhsjhm,");
		sql.append(" a.*");
		sql.append(" FROM view_xg_qgzx_cjffb a");
		sql.append(" order by yrdwdm, ffny desc, gwmc) a");
		sql.append("  where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		List<HashMap<String, String>> list =  dao.getListNotOut(sql.toString(), inputV);
		
		// TODO �Զ����ɷ������
		return list;
	}
	
	public List<HashMap<String, String>> getFzlist(CjffwhForm t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append("select distinct yrdwdm dm,yrdwmc mc from (");
		sql.append(" select  a.*");
		sql.append(" from (select");
		sql.append(" decode(sjhm, null, qsdh,sjhm) qsdhsjhm,");
		sql.append(" a.*");
		sql.append(" FROM view_xg_qgzx_cjffb a");
		sql.append(" order by yrdwdm, ffny desc, gwmc) a");
		sql.append("  where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" )");
		
		List<HashMap<String, String>> list =  dao.getListNotOut(sql.toString(), inputV);
		
		// TODO �Զ����ɷ������
		return list;
	}
	
	/** 
	 * @����:�ж��Ƿ���ƶ������������ж����𷢷Ŵ�һ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��3�� ����11:50:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isPks(String xh) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("select xh from XG_XSZZ_NEW_KNSJGB where (xn,xq) =  (select rdxn,nvl(rdxq, 'on') "); 
//		sql.append("from xg_xszz_new_knsjbszb where rownum = 1) ");	
//		sql.append("and xh = ?");
//		String result = dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		return dao.isKns(xh);
	}
}
