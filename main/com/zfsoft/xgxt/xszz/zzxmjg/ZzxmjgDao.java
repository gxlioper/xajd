/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-24 ����01:41:30 
 */
package com.zfsoft.xgxt.xszz.zzxmjg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: DAO
 * @���ߣ� maxh
 * @ʱ�䣺 2013-4-24 ����01:41:30
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZzxmjgDao extends SuperDAOImpl<ZzxmjgForm> {

	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzxmjgForm t)
			throws Exception {
		
		return null;
		// TODO �Զ����ɷ������
	}

	/**
	 * ʹ�ø߼���ѯ
	 * 
	 * @param
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZzxmjgForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		//������ͼ
		sql.append(" select * from ( ");

		sql.append(" select m.*,t2.sydm,t3.symc ");
		sql.append(" from VIEW_NEW_DC_XSZZ_ZZMXJG m ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=m.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" ) a  where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by xn desc,xq,sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * ѧԺ������
	 */
	public List<String[]> getXyList(String xn, String xq ,String[] xmlb, String[] xmmc, User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if(!StringUtil.isNull(xq)){
			params.add(xq);
		}
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.rs rs");
		sql.append(" from (select a.xmmc, a.xymc, count(1) rs");
		sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,(select bjdm from xsxxb t2 where a.xh=t2.xh)");
		sql.append(" bjdm from xg_xszz_new_zzxmjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_xszz_new_zzxmlbb b");
		sql.append(" on a.lbdm = b.lbdm");
		
		sql.append(" where a.xn = ? ");
		if(!StringUtil.isNull(xq)){
			sql.append(" and a.xq = ? ");
		}
		
		sql.append("and  b.lbdm in ( ");
		for (int i = 0; i < xmlb.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlb[i]);
		}
		sql.append(")");
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by a.xymc, a.xmmc) b").append(" where rs is not null and rs<>0 ");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] { "xymc" , "xmmc", "rs"});
	}

	/**
	 * ��Ŀ��������
	 */
	public List<String[]> getXmleList(String xn , String xq , String[] xmlb , String[] xmmc, User user){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if(!StringUtil.isNull(xq)){
			params.add(xq);
		}
		sql.append("select b.xmmc xmmc , b.rs rs from  (");
		sql.append("select a.xmmc ,a.lbdm,");
		sql.append(" count(1) rs ");
	 	sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,(select bjdm from xsxxb t2 where a.xh=t2.xh)");
		sql.append(" bjdm from xg_xszz_new_zzxmjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join  xg_xszz_new_zzxmlbb b on a.lbdm = b.lbdm");
		sql.append(" where a.xn = ? ");
		if(!StringUtil.isNull(xq)){
			sql.append(" and a.xq = ? ");
		}
		sql.append("and  b.lbdm in( ");
		for (int i = 0; i < xmlb.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlb[i]);
		}
		sql.append(")");
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append("group by a.xmmc,a.lbdm) b order by lbdm");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] { "xmmc", "rs"});
	}
	
	/**
	 * ����Ա����
	 */
	public List<String[]> getHjmdList(String xn , String xq , String[] xmlb , String[] xmmc){
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if(!StringUtil.isNull(xq)){
			params.add(xq);
		}
		
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.xm xm");
		sql.append(" from (select a.xmmc, a.xymc, c.xm");
		
		sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,(select bjdm from xsxxb t2 where a.xh=t2.xh)");
		sql.append(" bjdm from xg_xszz_new_zzxmjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
//		sql.append(searchTjByUser);
		sql.append(" )a");
		
		sql.append(" inner join xg_xszz_new_zzxmlbb b");
		sql.append(" on a.lbdm = b.lbdm");
		sql.append("  inner join xsxxb c on a.xh=c.xh ");
		sql.append(" where a.xn = ? ");
		if(!StringUtil.isNull(xq)){
			sql.append(" and a.xq = ? ");
		}
		sql.append("and  b.lbdm in( ");
		for (int i = 0; i < xmlb.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlb[i]);
		}
		sql.append(")");
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" ) b order by length(xm) ");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}), new String[] {"xymc" , "xmmc", "xm"});
	}
	
	/**
	 * ���ݴ���������
	 */
	public List<String> getXmlbmc(String[] xmlbdm) throws SQLException{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();	
		
		sql.append("select lbmc from xg_xszz_new_zzxmlbb where lbdm in (");
		
		for (int i = 0; i < xmlbdm.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(xmlbdm[i]);
		}
		sql.append(")");
		
		return dao.getList(sql.toString(), params.toArray(new String[]{}), "lbmc");
		
	}

	/**
	 * ��ҳ������ѯ������Ŀ������Ϣ
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> zzxmhzView(ZzxmjgForm t, User user,Boolean fyFlag)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		
		String searchTj = " and lbdm=? and xn=? and xq=? and xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};

		StringBuilder sql = new StringBuilder();

		//������ͼ
		sql.append("select * from VIEW_NEW_DC_XSZZ_ZZMXJG a where 1=1 ");

		sql.append(searchTj);
		if(StringUtils.equals("12036", Base.xxdm) && StringUtils.equals("excelByXy", t.getType())){
			sql.append(" and xydm='"+t.getXydm()+"'");
		}
		sql.append(searchTjByUser);
		if(fyFlag){
			return getPageList(t, sql.toString(), inputV);
		}else{
			return dao.getListNotOut(sql.toString(), inputV);
		}
	}
	/**
	 * ��ҳ������ѯ������Ŀ������Ϣ������ʦ��ѧԺ���Ի���
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> zzxmhzView_10402(ZzxmjgForm t, User user)
	throws Exception {
		// ============== ����� ��ת�� begin ======================
		StringBuilder hjqkSql = new StringBuilder(); 
		StringBuilder concatSql = new StringBuilder(); 
		StringBuilder caseSql = new StringBuilder(); 
		for (int i = 1; i < 5; i++) {
			hjqkSql.append(" g.hjsj"+i+", ");
			hjqkSql.append(" g.hjmc"+i+", ");
			hjqkSql.append(" g.fjdw"+i+" ");
			
			concatSql.append(" wm_concat(a.hjsj"+i+") hjsj"+i+", ");
			concatSql.append(" wm_concat(a.hjmc"+i+") hjmc"+i+", ");
			concatSql.append(" wm_concat(a.fjdw"+i+") fjdw"+i+" ");
			
			caseSql.append(" case when a.hjnum='"+i+"' then a.hjsj else '' end hjsj"+i+", ");
			caseSql.append(" case when a.hjnum='"+i+"' then a.hjmc else '' end hjmc"+i+", ");
			caseSql.append(" case when a.hjnum='"+i+"' then a.fjdw else '' end fjdw"+i+" ");
			if(i < 4){
				hjqkSql.append(", ");
				concatSql.append(", ");
				caseSql.append(", ");
			}
		}
		// ============== ����� ��ת�� end ======================
		// ���ɸ߼���ѯ�������������ֵ
		
		String searchTj = " and a.lbdm=? and a.xn=? and a.xq=? and a.xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.*, ");
		sql.append(" b.xm,b.sfzh,b.mzmc,a.xh,b.xymc,b.zymc,b.bjmc,b.xz,substr(b.rxrq,0,4) rxrq,b.zzmmmc,b.sjhm, ");
		sql.append(" c.bxkcs,d.jgkcs,e.cpzpm,f.cpzrs, ");
		sql.append(hjqkSql.toString());
		sql.append(" from xg_xszz_new_zzxmjgb a ");
		sql.append(" left join view_xsxxb b on a.xh=b.xh ");
		sql.append(" left join ( ");
		// ============== ���޿����� begin ======================
		sql.append(" select xn,xq,xh,count(1) bxkcs from cjb ");
		sql.append(" where kclx='����' ");
		sql.append(" group by xn,xq,xh ");
		// ============== ���޿����� end ======================
		sql.append(" ) c on (a.xn=c.xn and a.xq=c.xq and a.xh=c.xh) ");
		sql.append(" left join ( ");
		// ============== ���޿μ������� begin ======================
		sql.append(" select xn,xq,xh,count(1) jgkcs from cjb ");
		sql.append(" where cj >= 60 and kclx='����' ");
		sql.append(" group by xn,xq,xh ");
		// ============== ���޿μ������� end ======================
		sql.append(" ) d on (a.xn=d.xn and a.xq=d.xq and a.xh=d.xh) ");
		sql.append(" left join ( ");
		// ============== �ۺϿ������� begin ======================
		sql.append(" select a.cpzpm,a.xh,a.xn,a.xq from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b ");
		sql.append(" on (a.xn=b.xn and a.xq=b.xq and a.xmdm=b.xmdm) ");
		sql.append(" where b.fjdm='N' ");
		// ============== �ۺϿ������� end ======================
		sql.append(" ) e on (a.xn=e.xn and a.xq=e.xq and a.xh=e.xh) ");
		sql.append(" left join ( ");
		// ============== �ۺϿ������������� begin ======================
		sql.append(" select count(1) cpzrs,a.xn,a.xq from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b ");
		sql.append(" on (a.xn=b.xn and a.xq=b.xq and a.xmdm=b.xmdm) ");
		sql.append(" where b.fjdm='N' group by a.xn,a.xq ");
		// ============== �ۺϿ������������� end ======================
		sql.append(" ) f on (a.xn=f.xn and a.xq=f.xq) ");
		sql.append(" left join ( ");
		// ============== ����� begin ======================
		sql.append(" select a.xh, ");
		sql.append(concatSql.toString());
		sql.append(" from ( ");
		sql.append(" select a.xh, ");
		sql.append(caseSql.toString());
		sql.append(" from ( ");
		sql.append(" select a.xh,a.hjsj,a.hjmc,a.fjdw,(rank() over(partition by a.xh order by a.hjsj desc nulls last)) hjnum ");
		sql.append(" from xg_xsxx_new_hjqkb a ");
		sql.append(" ) a ) a group by a.xh ");
		// ============== ����� end ======================
		sql.append(" ) g on a.xh=g.xh ");
		sql.append(" where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:���Ӳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ����,����ʱ�䣩
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����04:55:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return boolean ��������
	 * @throws
	 */
	public String checkExistForSave(ZzxmjgForm model) {
		String num = "";
		
		if(StringUtils.isBlank(model.getSqsj())){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
		}
		
		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_xszz_new_zzxmjgb where xh=? and pdxn=? and xmmc=? ");
		sql.append("and to_char(to_date(sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') = to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') ");
		
		if(model.getPdxq()!=null && !"".equals(model.getPdxq())){
			sql.append(" and pdxq=? ");
			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(),model.getSqsj(), model.getPdxq() }, "num");
		}else{
			sql.append(" and pdxq is null ");
			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(),model.getSqsj() }, "num");
		}
		return num;
	}

	/**
	 * 
	 * @����:�޸Ĳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ��,��Ŀ���ƣ�
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-24 ����04:55:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return boolean ��������
	 * @throws
	 */
	public String checkExistForUpdate(ZzxmjgForm model) {

		String num = "";
		
		if(StringUtils.isBlank(model.getSqsj())){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(df.format(new Date()));
		}
		
		StringBuilder sql = new StringBuilder("select count(1) num from xg_xszz_new_zzxmjgb where xh=? and pdxn=?  and xmmc=?  and guid<>?");
		sql.append(" and to_char(to_date(sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') = to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') ");
		if(model.getPdxq()!=null && !"".equals(model.getPdxq())){
			sql.append(" and pdxq=? ");
			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(), model.getGuid(),model.getSqsj(), model.getPdxq() }, "num");
		}else{

			num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getPdxn(), model.getXmmc(), model.getGuid(),model.getSqsj() }, "num");
		}
		return num;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmjgb");
		super.setKey("guid");
		super.setClass(ZzxmjgForm.class);
	}

	/**
	 * 
	 * @����:�鿴����������Ŀ�����Ϣ
	 * @���ߣ�Administrator
	 * @���ڣ�2013-4-25 ����06:39:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return Map<String,String> ��������
	 * @throws
	 */
	public Map<String, String> getOneZzxmjgList(String guid) {
		StringBuilder sql = new StringBuilder(); 
		 sql.append(" select ylzd1,ylzd2,ylzd3,ylzd4,ylzd5,lbmc,sqly,sqsj,xqmc,xn,pdxn,(select xqmc from xqdzb b where a.pdxq=b.xqdm) pdxqmc, xmmc,je ");
		 if("11799".equals(Base.xxdm)){
			sql.append(",(select shsj from (SELECT shsj, ywid FROM (SELECT ROW_NUMBER() OVER(PARTITION BY ywid ORDER BY shsj desc) r,a.* FROM xg_xtwh_shztb a)WHERE r = 1)");
			sql.append("  where ywid = a.LYLCYWID) lastshsj ");
		 }
		 sql.append(" from xg_view_xszz_new_zzxmjgb a where guid=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { guid });
	}

	/**
	 * ͨ��ѧ�Ų�ѯ������Ŀ���
	 * 
	 * @param xh
	 * @return
	 */
	public List<String[]> getZzxmjgList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.xn,b.xqmc,a.xmmc,a.je,a.sqsj from xg_xszz_new_zzxmjgb a   ");
		sql.append("  left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xh=? ");
		sql.append(" order by xn desc,xq");
		String[] input = new String[] { xh };
		String[] output = new String[] { "xn", "xqmc", "xmmc", "je", "sqsj" };
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), input, output);
		return rs;
	}

	/**
	 * 
	 * @����:������Ŀ���ƣ���ѧ�ꡢѧ��ͳ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-28 ����02:48:35
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select xydm,xn,xq,count(*) rs from (  ");
		sql
				.append(" select x.*,y.xydm from xg_xszz_new_zzxmjgb x,view_xsjbxx y where  x.xh=y.xh and x.xmmc=? ");
		sql.append(" ) group by xydm,xn,xq order by xn desc,xq desc");
		String[] inputValue = { xmmc };
		List<HashMap<String, String>> result = dao.getListNotOut(
				sql.toString(), inputValue);
		return result;
	}
	
	/**
	 * 
	 * @����:ͨ��ѧ�Ų�ѯ������Ŀ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgInfoList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc ");
		if ("12867".equals(Base.xxdm)) {
			sql.append(",to_char(to_date(a.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy\"��\"mm\"��\"') zzsj");
		}else {
			sql.append(",substr(a.sqsj,0,10) zzsj");
		}
		sql.append(" from xg_xszz_new_zzxmjgb a left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xh=? ");
		sql.append(" order by xn desc,xq desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	/** 
	 * @����:����ҵ��idɾ��������¼
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-5 ����11:20:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * void �������� 
	 * @throws 
	 */
	public int delByYwid(String guid) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("delete from xg_xszz_new_zzxmjgb where sjly='1' and lylcywid=?");
		return dao.runDelete(sql.toString(), new String[]{guid});
		
	}
	/**
	 * 
	 * @����:��ѯ����ʦ�� ��ѧ���Ի�֤������¼
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-4����11:57:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZsbm(ZzxmjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select substr(zsbm,length(zsbm)-3,4) zsbm from VIEW_NEW_DC_XSZZ_ZZMXJG")
		.append(" where nj=? and xydm=? and pdxn=? and zsbm is not null order by zsbm desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getNj(),model.getXydm(),model.getPdxn()});
	}
	/**
	 * 
	 * @����:������Ŀ���ƻ�ȡ��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-4 ����11:23:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXmdm(ZzxmjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select xmdm from xg_xszz_new_zzxmdmb where xmmc=?");
		return dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "xmdm");
	}
	
	/** 
	 * @����:������Ŀ������Ϣ�б�
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * void �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZzxmhzList(ZzxmjgForm model, User user)	throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		StringBuilder sql = new StringBuilder();
		SearchModel searchModel = model.getSearchModel();
		String[] xydm = searchModel.getSearch_tj_xy();
		searchModel.setSearch_tj_xy(new String[]{});
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		//�㽭��ѧ���Ի�
		if("10335".equals(Base.xxdm)){
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
			sql.append(" select * from (select t.xmmc,t.lbdm,(select lbmc from xg_xszz_new_zzxmlbb where lbdm = t.lbdm) lbmc,t.xn,t.xq,(select xqmc from xqdzb where xqdm = t.xq) xqmc,count(*) hjrs ");
			sql.append(" from (select * from (select t1.xmmc, t1.lbdm, t1.xn, t1.xq, t2.xydm from xg_xszz_new_zzxmjgb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh) a  where 1 = 1 ");
			sql.append(searchTjByUser);
			sql.append(" ) t ");
			sql.append(" group by (t.xmmc, t.lbdm, t.xn, t.xq)) t3 where 1 = 1 ");	
		}else if("12036".equals(Base.xxdm)){
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
			sql.append(" select * from (select t.xmmc,t.lbdm,(select lbmc from xg_xszz_new_zzxmlbb where lbdm = t.lbdm) lbmc,t.xn,t.xq,(select xqmc from xqdzb where xqdm = t.xq) xqmc,count(*) hjrs ");
			sql.append(" from (select * from (select t1.xmmc, t1.lbdm, t1.xn, t1.xq, t2.xydm from xg_xszz_new_zzxmjgb t1 ");
			sql.append(" left join view_xsjbxx t2 on t1.xh = t2.xh) a  where 1 = 1 ");
			if(xydm !=null && xydm.length> 0){
				StringBuffer xySql = new StringBuffer();
				int length = xydm.length;
				xySql.append(" and a.xydm in ( ");
				for(int i=0;i<length-1;i++ ){
					xySql.append("'"+xydm[i]+"',");
				}
				xySql.append("'"+xydm[length-1]+"')");
				sql.append(xySql);
			}
			sql.append(searchTjByUser);
			sql.append(" ) t ");
			sql.append(" group by (t.xmmc, t.lbdm, t.xn, t.xq)) t3 where 1 = 1 ");
		}else{
			sql.append("select * from (select t.xmmc,t.lbdm,(select lbmc from xg_xszz_new_zzxmlbb where lbdm = t.lbdm) lbmc,t.xn,t.xq,(select xqmc from xqdzb where xqdm=t.xq) xqmc,count(*) hjrs");
			sql.append(" from xg_xszz_new_zzxmjgb t group by (t.xmmc,t.lbdm,t.xn,t.xq)) a where 1=1");
		}
			sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//��ѯѧ��ְ��
	public String getZwForXh(String xh){
		String sql = "select a.xh,wm_concat(zwmc) zwmc from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt='1' and xh = ? group by a.xh";
		return dao.getOneRs(sql, new String[]{xh}, "zwmc");
	}
	/**
	 * 
	 * @����:��ѯ������������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-18 ����03:18:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getzzjgZqList() {

		StringBuilder sb = new StringBuilder();
		sb.append(" select xn ");
		sb.append(" from (select  distinct a.xn from xg_xszz_new_zzxmjgb a order by a.xn) a ");
		return dao.getListNotOut(sb.toString(), new String[]{});		
	}
	/**
	 * 
	 * @����:��ѯָ��ѧ��ѧ��������Ŀ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-18 ����03:43:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgOfXnList(String lyxn,String mbxn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc from xg_xszz_new_zzxmjgb a   ");
		sql.append("  left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xn=? ");
		sql.append(" and   not exists (select 1 from xg_xszz_new_zzxmjgb c");
		sql.append(" where c.xn=? and a.xh=c.xh and a.xq=c.xq and a.xmmc=c.xmmc ) ");
		sql.append(" order by xn desc,xq");
		return dao.getListNotOut(sql.toString(), new String[] { lyxn,mbxn });
	}
	
	public ZzxmjgForm getModel(String guid) throws Exception {
		ZzxmjgForm zzxmjgForm = new ZzxmjgForm();
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.xqmc,c.lbmc ");
		// =============== �༶���� < =============
		if(SFBJPY_Y){
			sql.append(" ,decode(t3.shzt,'1', 'ͬ������', '0', '��ͬ������','') bjpyjgshztmc,t3.pyhsj bjpyjgpyhsj,t3.pyhdd bjpyjgpyhdd,t3.pyyj bjpyjgpyyj, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd1 end) bjpyxzcyxms, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd2 end) bjpyxzdbxms ");			
		}
		// =============== �༶���� > =============		
		sql.append("  from xg_xszz_new_zzxmjgb a   ");
		sql.append("  left join xqdzb b on a.xq=b.xqdm left join xg_xszz_new_zzxmlbb c on a.lbdm=c.lbdm ");

		// =============== �༶���� < =============
		if(SFBJPY_Y){
			sql.append(" left join (select * from xg_xszz_new_xszz_bjpyxzpyjg where tjzt='1') t3 on a.lylcywid=t3.sqid ");
		}
		// =============== �༶���� > =============
		sql.append(" where a.guid=? ");
		sql.append(" order by a.xn desc,a.xq");
		return getModel(zzxmjgForm, sql.toString(), new String[]{guid});
	}
	
	/**
	 * ��ѯ��ְ����Ϣ
	 */
	public Map<String, String> getJzgInfo(User user){
		String sql = "select a.*,(select bmmc from zxbz_xxbmdm_ls b where a.szbm=b.bmdm) bmmc from yhb a where yhm = ? ";
		return dao.getMapNotOut(sql, new String[]{user.getUserName()});
	}
	
	public List<HashMap<String,String>> getZzxmjgHzList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xh, t2.xm, t1.xmmc,t1.zje, t2.nj,t2.xymc,   ");
		sql.append("  t2.zymc, t2.bjmc,t2.sfzh,t2.MZ,t2.sjhm, yhkh  ");
		sql.append("   from (select xh, replace(wm_concat(a.pdxn || 'ѧ��' ||  ");
		sql.append("  b.xqmc || '���' || a.xmmc), ';', ',') xmmc, sum(nvl(je, 0)) zje ");
		sql.append("  from xg_xszz_new_zzxmjgb a left join xqdzb b on a.pdxq = b.xqdm ");
		sql.append("  group by xh) t1 inner join view_xsjbxx t2 on t1.xh = t2.xh  ");
		sql.append(" order by xh ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	//ɽ��������ҽ(�������W��ȣ����ܻ�ȡѧ����Ϣ�Լ���Ŀ���list
	public List<HashMap<String, String>> getShzxjHzbxxList(String values,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select rownum bh,t1.xm,decode(t1.xb, '1', '��', '2', 'Ů', '��', '��', 'Ů', 'Ů') xb,");
		sql.append(" t.je xmje,t3.bjmc, t3.xymc xy,(select xxmc from xtszb) xxmc,");
		sql.append(" decode(t1.hkxz, 1, '����', 2, 'ũ��') hk, decode(substr(t.xmmc, 0, 2), 'һ��', '1', '') szdc1, ");
		sql.append(" decode(substr(t.xmmc, 0, 2), '����', '1', '') szdc2, decode(substr(t.xmmc, 0, 2), '����', '1', '') szdc3,");
		sql.append(" t1.sfzh,t3.zymc,t1.xh, t1.rxrq,(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_xszz_new_zzxmjgb t,xsxxb t1 , view_njxyzybj t3");
		sql.append(" where  t.xh = t1.xh and t1.bjdm = t3.bjdm  and t.xmmc = ? and t.xn = ? and t.xq = ?");	
		return dao.getListNotOut(sql.toString(), new String[]{values});
	}
	
	//ɽ��������ҽ�����ѧ���ȡ���������ܽ�� map
	public HashMap<String, String> getshzxjTotal(String values){
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select  count(*) zrs,sum(t1.je) xmze  from xg_xszz_new_zzxmlbb t,xg_xszz_new_zzxmjgb t1 ");
		sql.append(" where t.lbdm = t1.lbdm and t.lbmc = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{values});
	}
	
	//ɽ��������ҽ������־��ѧ���ȡ��ϵ�绰 map
	public HashMap<String, String> getlxfs(String value){
		StringBuffer sql = new StringBuffer();
		sql.append("select lxdh from fdyxxb where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{value});
	}
	
	//������־��ѧ����������ģ��
	public List<HashMap<String, String>> getLzjHzXsxx(String[] values){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t1.hjqk from");
		sql.append("(select t1.xh,t1.xm, decode(t1.xb, '1', '��', '2', 'Ů', '��', '��', 'Ů', 'Ů') xb,t1.csrq," +
				"(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc,");
		sql.append("t1.rxrq, t1.sfzh, t1.lxdh,(select t3.yhmc from DMK_YH t3 where t3.yhdm = t1.yhdm) khyh, " +
				"t1.yhkh,t1.xymc xy,t1.zymc,t1.bjmc,t1.zzmmmc" +
				",t5.jthk,");
		sql.append(" t5.jtrs,(t5.jtrjysr) * (t5.jtrs) jtyzsr,t5.jtrjysr, t5.jtsrly,t5.jtdz,t1.yzbm, t.sqly,");
		sql.append("t1.xm sqr, to_char(to_date(t.sqsj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') sqsj");
		sql.append(" from xg_xszz_new_zzxmjgb t, view_xsjbxx t1, XG_XSZZ_NEW_JTQKDCB t5 ");
		sql.append("where t.xh = t1.xh  and t1.xh = t5.xh  and t.guid in (");
		for(int i = 0;i<values.length;i++){
			if(i != values.length-1){
				sql.append("?,");
			}else{
				sql.append("?");
			}
		}
		sql.append("))t left join ");
		sql.append("(select WM_CONCAT(xmmcs) hjqk, xh from (select a.xqmc || '��' || replace(a.xmmcsTemp, ';', '��') xmmcs,xh");
		sql.append(" from (select xqmc,xh,WM_CONCAT(xmmc) over(partition by xqmc order by xqmc) xmmcsTemp,"
                            + " row_number() over(partition by xqmc order by xqmc desc) rn");
		sql.append(" from (select t1.xn || t2.xqmc xqmc, t1.xmmc, t1.xh from xg_pjpy_new_pjjgb t1 left join xqdzb t2 "
                                 +  "on t1.xq = t2.xqdm order by t1.xn asc, sqsj asc) a) a  where rn = 1)");
		sql.append(" group by xh) t1");
		sql.append("   on t.xh = t1.xh ");
		return dao.getListNotOut(sql.toString(), values);
	}
	
	//ɽ��������ҽ(������W��ȣ����ܻ�ȡѧ����Ϣ�Լ���Ŀ���list
	public HashMap<String, String> getShzxjhz(String values,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		String[]inputvalues=new String[]{values};
		sql.append("select rownum bh, t1.xm,decode(t1.xb, '1', '��', '2', 'Ů', '��', '��', 'Ů', 'Ů') xb,");
		sql.append(" t.je xmje,t3.bjmc, t3.xymc xy,(select xxmc from xtszb) xxmc,");
		sql.append(" decode(t1.hkxz, 1, '����', 2, 'ũ��') hk, decode(substr(t.xmmc, 0, 2), 'һ��', '1', '') szdc1, ");
		sql.append(" decode(substr(t.xmmc, 0, 2), '����', '1', '') szdc2, decode(substr(t.xmmc, 0, 2), '����', '1', '') szdc3,");
		sql.append(" t1.sfzh,t3.zymc,t1.xh, t1.rxrq,(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_xszz_new_zzxmjgb t,xsxxb t1 , view_njxyzybj t3,xg_xszz_new_zzxmlbb t4");
		sql.append(" where  t.xh = t1.xh and t1.bjdm = t3.bjdm  and  t4.lbdm = t.lbdm  and t4.lbmc  = ?  ");	
		if(!xn.trim().equals("")){
			sql.append("and t.xn = ?");
		}
		
		if(!xq.trim().equals("")){
			sql.append("and t.xq = ?");
		}
		if(xn.trim().equals("")&&!xq.trim().equals("")){
			return dao.getMapNotOut(sql.toString(), new String[]{values,xq});
		}else if(!xn.trim().equals("")&& xq.trim().equals("")){
			return dao.getMapNotOut(sql.toString(), new String[]{values,xn});
		}else if(xn.trim().equals("")&& xq.trim().equals("")){
			return dao.getMapNotOut(sql.toString(), new String[]{values});
		}
		return dao.getMapNotOut(sql.toString(), new String[]{values,xn,xq});
	}
	
	//�����ѧ����ܵ���
	public List<HashMap<String, String>> getDcList(ZzxmjgForm t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum bh, t1.xm,decode(t1.xb, '1', '��', '2', 'Ů', '��', '��', 'Ů', 'Ů') xb,");
		sql.append(" t.je xmje,t3.bjmc, t3.xymc xy,(select xxmc from xtszb) xxmc,");
		sql.append(" decode(t1.hkxz, 1, '����', 2, 'ũ��') hk, decode(substr(t.xmmc, 0, 2), 'һ��', '1', '') szdc1, ");
		sql.append(" decode(substr(t.xmmc, 0, 2), '����', '1', '') szdc2, decode(substr(t.xmmc, 0, 2), '����', '1', '') szdc3,");
		sql.append(" t1.sfzh,t3.zymc,t1.xh, t1.rxrq,(select mzmc from mzdmb t2 where t2.mzdm = t1.mz) mzmc  ");
		sql.append(" from xg_xszz_new_zzxmjgb t,xsxxb t1 , view_njxyzybj t3,xg_xszz_new_zzxmlbb t4");
		sql.append(" where  t.xh = t1.xh and t1.bjdm = t3.bjdm  and  t4.lbdm = t.lbdm   ");	
		sql.append(searchTjByUser);
		sql.append("");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	// TODO �Զ����ɷ������
   }
	
	/**
	 * 
	 * @����:��ȡ�ڹ���ѧlist
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-6 ����11:53:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.gwmc, t2.yrdwmc,t1.xn ");
		sql.append("from xg_qgzx_xsgwxxb t, xg_qgzx_gwxxb t1, xg_qgzx_yrdwdmb t2 ");
		sql.append(" where t.gwdm = t1.gwdm ");
		sql.append(" and t.xh = ?  and t1.yrdwdm = t2.yrdwdm order by t2.yrdwmc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:��ȡ��Ŀ����list
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-6 ����03:11:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getMjxList(String xh,String xmmc){
		StringBuilder sql = new StringBuilder();
		sql.append("select xn,xmmc from xg_xszz_new_zzxmjgb where xmmc like '%' || ? || '%' ");
		sql.append("and xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xmmc,xh});
	}
	
	/**
	 * 
	 * @����:�Ƿ������ѧ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-7 ����02:15:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSfzxDk(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) cs from xg_zxdk_xydkjgb where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	public HashMap<String, String> getSfkns(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.dcmc  from xg_xszz_new_knsjgb t,xg_xszz_new_kndcdmb t1 where t.rddc = t1.dcdm and t.xh = ? and t.xn = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	public HashMap<String, String>  getSfxs(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dqnd from xtszb");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:��ȡƴ����
	 * @���ߣ��Ų�·[���ţ�1206]
	 * @���ڣ�2015-7-8 ����09:53:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPinyin(String str){
		StringBuilder sql = new StringBuilder();
		sql.append("select  GETHZPY.GETHZFULLPY(?) py FROM DUAL");
		return  dao.getOneRs(sql.toString(), new String[]{str}, "py");
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר������Ŀ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-27 ����04:40:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDxscbInfoList(ZzxmjgForm t, User user) throws Exception {
		
		String searchTj = " and a.lbdm=? and a.xn=? and a.xq=? and a.xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*,t3.xm,t3.xb,t3.sfzh,t3.xymc,t3.bjmc,t3.xydm,t3.bjdm,t3.zd5,t2.mc jfdcmc,t2.grsjje,t2.mzzzje, ");
		sql.append(" (select mc from XG_RCSW_YLBX_CBLXB where dm = t1.zd12) cblxmc, ");
		sql.append(" (select mc from xg_rcsw_ylbx_bsddyljgb where dm = t1.zd2) zzlxmc ");
		sql.append(" from xg_xszz_new_zzxmjgb a ");
		sql.append(" left join xg_rcsw_ylbx_jgb t1 ");
		sql.append(" on a.xh = t1.xh ");
		sql.append(" left join xg_rcsw_ylbx_ylczbzbsb t2 ");
		sql.append(" on t1.zd1 = t2.dm ");
		sql.append(" left join view_xsxxb t3 ");
		sql.append(" on t1.xh = t3.xh )a");
		sql.append(" where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	public HashMap<String, String> getZzxmNumTotalMoney(String xh){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paramater = new ArrayList<String>();
		sql.append("select sum(nvl(t.je,0)) zje,count(1) sl from  xg_xszz_new_zzxmjgb t where t.xh = ?");
		paramater.add(xh);
		return dao.getMapNotOut(sql.toString(), paramater.toArray(new String[]{}));
	}
	/**
	 * @����:TODO(��������ְҵ����ѧԺ���Ի�)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-8-5 ����03:32:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getLnjdzyjsxyList(ZzxmjgForm t, User user) throws Exception {
		String searchTj = " and a.lbdm=? and a.xn=? and a.xq=? and a.xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.xmmc,t1.je,t1.sqsj,t1.sqly,t1.lbdm,t1.pdxn,t2.xm,t2.SFZH,t2.xydm,t2.bjdm,t2.xymc,t2.zymc,t2.bjmc,t2.xb,t2.nj,t2.mzmc,t2.RXRQ,t2.jtdzxx jtdz,t2.SJHM,t2.yhkh, ");
		sql.append(" case when xmmc like '%һ��%' then '1'  when xmmc like '%����%' then '2' else '' end zzdj ");
		sql.append(" from xg_xszz_new_zzxmjgb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh )a where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����: רҵ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-14 ����09:11:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sxn
	 * @param zydm
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXsZyBxPm(String sxn, String zydm, String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select a.*, rank() over(order by to_number(nvl(a.fs, 0)) desc) pm ");
        sql.append(" from (select a.* from (select 0 fs, a.zydm, a.xh from xsxxb a ");
        sql.append(" where not exists (select 1 from cjb b where a.xh = b.xh and b.xn = ?) union all select sum(nvl(cj, 0)) fs, d.zydm, c.xh ");
        sql.append(" from cjb c left join xsxxb d on c.xh = d.xh where c.xn = ? and c.kcxz like '%����%' group by c.xh, d.zydm) a where a.zydm = ?) a) a ");
        sql.append(" where a.xh = ? ");
		return  dao.getOneRs(sql.toString(), new String[]{sxn,sxn,zydm,xh}, "pm");
	}
	
	/**
	 * 
	 * @����: רҵ������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-14 ����09:16:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zydm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZyrs(String zydm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) n from xsxxb where zydm = ? ");
		return  dao.getOneRs(sql.toString(), new String[]{zydm}, "n");
	}
	
	/**
	 * 
	 * @����: ���޿�ƽ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-14 ����09:27:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param sxn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBxPjcj(String xh, String sxn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(avg(cj),0) pjcj from cjb where xh = ? and xn = ? and kcxz like '%����%' ");
		return  dao.getOneRs(sql.toString(), new String[]{xh,sxn}, "pjcj");
	}
	
	/**
	 * 
	 * @����: ��ͷ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-14 ����09:38:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param sxn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZdf(String xh, String sxn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select min(to_number(cj)) minCj from cjb where xh = ? and xn = ? ");
		return  dao.getOneRs(sql.toString(), new String[]{xh,sxn}, "minCj");
	}
	
	/**
	 * 
	 * @����: ��ͷֿγ�����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-14 ����10:00:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param sxn
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZdfkmmc(String xh, String sxn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select kcmc from cjb where xh = ? and xn = ? and cj = (select min(to_number(cj)) minCj from cjb where xh = ? and xn = ?) ");
		return  dao.getOneRs(sql.toString(), new String[]{xh,sxn,xh,sxn}, "kcmc");
	}
	
	/**
	 * 
	 * @����: �Ϻ�����������Ŀ���ܱ��ܽ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-14 ����05:14:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String[] getZje_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append("  select to_char(nvl(sum(je),0),'9999999999990.99') zje,count(1) zrs from xg_xszz_new_zzxmjgb  where regexp_replace(je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null");
		sql.append(" and xn||xq||xmmc||lbdm in(");
		for (int i = 0; i < param.length; i++) {
			paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), new String[]{"zje","zrs"});
	}
	
	/**
	 * 
	 * @����:�Ϻ�����������Ŀ���ܱ�רҵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-14 ����05:34:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getzyhz_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select a.zydm,nvl(b.zyje,'0.00' ) zyje,nvl(c.zyrs,0) zyrs from ");
		sql.append(" (select distinct zydm from view_njxyzybj )a");
		sql.append(" left join");
		sql.append(" (select to_char(sum(nvl(a.je,0)),'9999999999990.99')  zyje,a.zydm from ");
		sql.append(" (select t1.zydm,t.je from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh where regexp_replace(t.je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null");
		
	    sql.append(" and t.xn||t.xq||t.xmmc||t.lbdm in(");
	    for (int i = 0; i < param.length; i++) {
	    	paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
	    sql.append(")");
		sql.append(" ) a  group by a.zydm) b");
		sql.append(" on a.zydm = b.zydm");
		sql.append(" left join");
		sql.append(" (select a.zydm,count(1) zyrs from ");
		sql.append(" (select  t1.zydm from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" where t.xn||t.xq||t.xmmc||t.lbdm in(");
		for (int i = 0; i < param.length; i++) {
		    	paraList.add(param[i]);
				sql.append("?");
				if(i != param.length-1){
					sql.append(",");
				}
		}
		sql.append(")");
		sql.append(" ) a  group by a.zydm)c");
		sql.append(" on a.zydm = c.zydm");
		sql.append(" order by a.zydm");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����:�Ϻ�����������Ŀ���ܱ��꼶���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����09:25:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjhz_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select a.nj,nvl(b.njje,'0.00' ) njje,nvl(c.njrs,0) njrs from ");
		sql.append(" (select distinct nj from view_njxyzybj )a");
		sql.append(" left join");
		sql.append(" (select to_char(sum(nvl(a.je,0)),'9999999999990.99')  njje,a.nj from ");
		sql.append(" (select t1.nj,t.je from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh where regexp_replace(t.je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null ");
	    sql.append(" and t.xn||t.xq||t.xmmc||t.lbdm in(");
	    for (int i = 0; i < param.length; i++) {
	    	paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
	    sql.append(" )");
		sql.append(" ) a  group by a.nj) b");
		sql.append(" on a.nj = b.nj");
		sql.append(" left join");
		sql.append(" (select a.nj,count(1) njrs from ");
		sql.append(" (select t1.nj from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh ");
		sql.append(" where t.xn||t.xq||t.xmmc||t.lbdm in(");
	    for (int i = 0; i < param.length; i++) {
	    	paraList.add(param[i]);
			sql.append("?");
			if(i != param.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		sql.append(" ) a  group by a.nj)c");
		sql.append(" on a.nj = c.nj");
		sql.append(" order by a.nj");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @����: �Ϻ�����ѧԺרҵ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����10:13:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getxyzyList_shty(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.zygs from ");
		sql.append(" (");
		sql.append(" select distinct t.xymc,t.xydm,t.zydm,t.zymc  from view_njxyzybj t  order by t.xydm,t.zydm)a");
		sql.append(" left join");
		sql.append(" (");
		sql.append(" select xymc,xydm,count(1) zygs from (select distinct t.xymc,t.xydm,t.zydm,t.zymc  from view_njxyzybj t  order by t.xydm,t.zydm) t group by xymc,xydm)b");
		sql.append(" on a.xydm = b.xydm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:�Ϻ�����ѧԺ�꼶�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����10:18:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct nj from view_njxyzybj order by nj");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: �Ϻ������꼶ѧԺ������Ŀ����������ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����10:21:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjxyzy_shty(String[] param){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select a.*,nvl(b.mhje,'0.00' ) mhje,nvl(c.mhrs,0) mhrs from ");
		sql.append(" (select distinct t.xymc,t.xydm,t.nj,t.zydm,t.zymc  from view_njxyzybj t)a");
		sql.append(" left join ");
		sql.append(" (select to_char(sum(nvl(a.je,0)),'9999999999990.99')  mhje,a.xydm,a.nj,a.zydm from ");
		sql.append(" (select t1.xymc,t1.xydm,t1.nj,t1.zymc,t1.zydm,t.je from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh where regexp_replace(t.je,'^[-\\+]?\\d+(\\.\\d+)?$','') is  null ");
		sql.append(" and t.xn||t.xq||t.xmmc||t.lbdm in(");
		 for (int i = 0; i < param.length; i++) {
		    	paraList.add(param[i]);
				sql.append("?");
				if(i != param.length-1){
					sql.append(",");
				}
		}
		sql.append(" )");
		
		sql.append(" ) a  group by a.xymc,a.xydm,a.nj,a.zymc,a.zydm )b");
		sql.append(" on a.nj = b.nj and a.zydm = b.zydm");
		sql.append(" left join");
		sql.append(" (select a.nj,a.xydm,a.zydm,count(1) mhrs from ");
		sql.append(" (select t1.nj,t1.xydm,t1.zydm from xg_xszz_new_zzxmjgb t");
		sql.append(" left join  view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh ");
		sql.append(" where t.xn||t.xq||t.xmmc||t.lbdm in(");
		 for (int i = 0; i < param.length; i++) {
		    	paraList.add(param[i]);
				sql.append("?");
				if(i != param.length-1){
					sql.append(",");
				}
		}
		sql.append(" )");
		sql.append(" ) a  group by a.nj,a.xydm,a.zydm )c");
		sql.append(" on a.nj = c.nj and a.zydm =c.zydm");
		sql.append(" order by a.nj,a.xydm,a.zydm");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}) );
	}
	
	/**
	 * 
	 * @����: �Ϻ�����ѧУ��ȡѧԺ�б�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-15 ����02:20:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct a.xymc, a.xydm, a.zygs");
		sql.append(" from (select a.*, b.zygs");
		sql.append(" from (select distinct t.xymc, t.xydm, t.zydm, t.zymc");
		sql.append(" from view_njxyzybj t");
		sql.append(" order by t.xydm, t.zydm) a");
		sql.append(" left join (select xymc, xydm, count(1) zygs");
		sql.append(" from (select distinct t.xymc, t.xydm, t.zydm, t.zymc");
		sql.append(" from view_njxyzybj t");
		sql.append(" order by t.xydm, t.zydm) t");
		sql.append(" group by xymc, xydm) b");
		sql.append(" on a.xydm = b.xydm) a where a.zygs >0");
		sql.append(" order by xydm");
	    return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ������Ŀ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-17 ����04:19:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZzxmMc(String paras){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmmc from xg_xszz_new_zzxmjgb t where  t.xn || t.xq || t.xmmc || t.lbdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{paras}, "xmmc");
	}
	
	/** 
	 * @����:����������ݿ�(�ൺ�Ƶ�ְҵ������ѧԺ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����05:53:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * int[] �������� 
	 * @throws 
	 */
	public int[] DrForInsert(List<String[]> params) throws SQLException{
		String sql = "insert into xg_xszz_new_zzxmjgb(xh,xn,xq,pdxn,pdxq,lbdm,xmmc,je) values(?,?,?,?,?,?,?,?)";
		return dao.runBatch(sql, params);
	}
	
	/** 
	 * @����:��֤ѧ���Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-2 ����05:54:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkXhExist(ZzxmjgForm form) {
		String sql = "select count(xh) num from view_xsbfxx where xh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{form.getXh()}, "num"))>0;
	}
	
	/** 
	 * @����:��֤���֤��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����08:36:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkSfz(ZzxmjgForm form){
		boolean result = true;
		String sql = "selecct count(sfzh) num from view_xsbfxx where sfzh = ?";
		result = Integer.valueOf(dao.getOneRs(sql, new String[]{form.getSfzh()}, "num"))>0;
		if(result){
			String sqll = "select xh from view_xsbfxx where sfzh = ?";
			String xh = dao.getOneRs(sqll, new String[]{form.getSfzh()}, "xh");
			form.setXh(xh);
			return true;
		}else{
			return false;
		}
	}
	
	/** 
	 * @����:��֤��Ŀ�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����08:44:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkXmlb(ZzxmjgForm form){
		String sql = "select count(1)num from xg_xszz_new_zzxmlbb where lbmc = ?";
		boolean result = Integer.valueOf(dao.getOneRs(sql, new String[]{form.getLbmc()}, "num"))>0;
		if(result){
			String sqll = "select lbdm from xg_xszz_new_zzxmlbb where lbmc = ?";
			String lbdm = dao.getOneRs(sqll, new String[]{form.getLbmc()}, "lbdm");
			form.setLbdm(lbdm);
			return true;
		} else{
		  return false;
		}
	}
	
	/** 
	 * @����:��֤ѧ�ź����֤�Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����10:34:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkXhAndSfzh(ZzxmjgForm form){
		String sql = "select count(1) num from view_xsbfxx where xh = ? and sfzh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{form.getXh(),form.getSfzh()}, "num"))>0;
	}
	
	/** 
	 * @����:��֤ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-3 ����10:55:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkXq(ZzxmjgForm form,String xqType){
		String sql = "select count(1) num from xqdzb where xqmc = ?";
		String mc;
		if(xqType == "xq"){
			 mc = form.getXqmc();
		}else{
			 mc = form.getPdxqmc();
		}
		boolean result = Integer.valueOf(dao.getOneRs(sql, new String[]{mc}, "num"))>0;
		if(result) {
			String sqll = "select xqdm from xqdzb where xqmc = ?";
			String xqdm = dao.getOneRs(sqll, new String[]{mc}, "xqdm");
			if(xqType == "xq"){
				form.setXq(xqdm);
			}else{
				form.setPdxq(xqdm);
			}
			return true;
		}else {
			return false;
		}		
	}
	
	public List<HashMap<String,String>> getZzjgList(String xh,String n){
		String sql = "select * from (select * from xg_xszz_new_zzxmjgb where xh=? order by xn desc,xq desc,sqsj desc) where rownum <=? ";
		return dao.getListNotOut(sql, new String[]{xh,n});
	}

	/** 
	 * �㽭���ΰ��Ļ���
	 */
	public HashMap<String, String> getZjlyAxjjMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_axjjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭����-�¶����Ĳ���
	 */
	public HashMap<String, String> getZjlyGeaxMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_geaxbzjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭��������У�����ü���
	 */
	public HashMap<String, String> getZjlyXfjmMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_xffyjmjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭��������������Ʒ���ü���
	 */
	public HashMap<String, String> getZjlyShfjmMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_shybjmjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭����-��ҵ���ղ���
	 */
	public HashMap<String, String> getZjlySybxbzMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_sybxbzjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭���ν�ͨ����
	 */
	public HashMap<String, String> getZjlyJtbzMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_xszz_jtbzjgb where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭����-���ܿ�֤�Ѳ���
	 */
	public HashMap<String, String> getZjlyJnkzfbzmapMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,to_char(a.f_fzsj1,'yyyy-mm-dd') fzsj1,to_char(a.f_fzsj2,'yyyy-mm-dd') fzsj2,to_char(a.f_fzsj3,'yyyy-mm-dd') fzsj3 from zfsoft_bpmx.view_xszz_jnkzfybzjgb a where a.f_xh=? and a.f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}

	/** 
	 * �㽭����-ѧ�Ѽ���
	 */
	public HashMap<String, String> getZjlyXfjmmapMap(String xh, String xn) {	
			StringBuilder sql = new StringBuilder();
			sql.append("select * from zfsoft_bpmx.view_xszz_xfjmjgb where f_xh=? and f_xn=?");
			return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
		}
	/**
	 *�㽭����--���һ�ѧԺ��ѧ��
	 */
	public HashMap<String, String> getZjlyZxjmapMap(String xh, String xn) {	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from zfsoft_bpmx.view_knzz_zxjpdjg where f_xh=? and f_xn=?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//���ݴ�ѧ����γ�
	public HashMap<String,String> getWzdxbxkms(String xh,String xn ){
		StringBuilder sql =  new StringBuilder();
		sql.append("select nvl(round(sum(case when cj >=60  then '1' end),0),0)bxkjgms ,count(xh) as bxkms from cjb  where " +
				" kcxz like '%����%'and xh = ? and xn = ?   group by xh");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	/**
	 * @description	�� �����մɸ��Ի�
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-5 ����04:43:41
	 * @param t
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getJxtczzxmList(ZzxmjgForm t,
			User user) {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = " and lbdm=? and xn=? and xq=? and xmmc=?";
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = {t.getLbdm(),t.getXn(),t.getXq(),t.getXmmc()};
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.fdyxmdh,decode(c.ylzd8,1,'��',0,'��') sfjdlk from VIEW_NEW_DC_XSZZ_ZZMXJG a  ");
		sql.append(" left join (select bjdm,wm_concat(fdyxmdh) fdyxmdh from (select a.zgh, b.xm|| b.lxdh fdyxmdh, a.bjdm from fdybjb a left join fdyxxb b on a.zgh = b.zgh) group by bjdm) b on a.bjdm=b.bjdm ");
		sql.append(" left join xg_xszz_new_jtqkdcb c on a.xh=c.xh");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	/**
	 * @description	�� ���ݴ�ѧ���Ի�
	 * @author 		�� JZ��1529��
	 * @date 		��2017-12-5 ����04:43:41
	 * @param t
	 * @param user
	 * @return
	 */
		public List<HashMap<String, String>> getWzdxzzxmList(ZzxmjgForm t,
				User user) {
			// ���ɸ߼���ѯ�������������ֵ
			String searchTj = "  and xn=?  and xmmc=? ";
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
					"xydm", "bjdm");
			String[] inputV = {t.getXn(),t.getXmmc()};
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (select a.*,c.bxkcs,d.jgkcs,f.xnzypm,f.xnzfpm,f.zrs from VIEW_NEW_DC_XSZZ_ZZMXJG a  ");
			sql.append(" left join ( ");
			// ============== ���޿����� begin ======================
			sql.append(" select xn,xh,count(1) bxkcs from cjb ");
			sql.append(" where kclx='����' or kcxz like '%����%' ");
			sql.append(" group by xn,xh ");
			// ============== ���޿����� end ======================
			sql.append(" ) c on (a.xn=c.xn and a.xh=c.xh) ");
			sql.append(" left join ( ");
			// ============== ���޿μ������� begin ======================
			sql.append(" select xn,xh,count(1) jgkcs from cjb ");
			sql.append(" where cj >= 60 and (kclx='����' or kcxz like '%����%') ");
			sql.append(" group by xn,xh ");
			// ============== ���޿μ������� end ======================
			sql.append(" ) d on (a.xn=d.xn  and a.xh=d.xh) ");
			sql.append(" left join ( ");
			// ============== �ۺϿ������������� brgin ======================
			sql.append("select a.xn,a.xh,a.bjdm,");
			sql.append("  nvl(min(to_number(a.xnzfpm)), '') xnzfpm,");
			sql.append(" nvl(min(to_number(a.xnzypm)), '') xnzypm,");
			sql.append(" nvl(max(to_number(b.zrs)), '') zrs");
			sql.append("  from (select a.xn,");
			sql.append("               a.xq,");
			sql.append("               b.xh,");
			sql.append("               c.bjdm,");
			sql.append("               min(case");
			sql.append("                     when a.xmmc = '�۲��ܷ�' then");
			sql.append("                      b.bjpm");
			sql.append("                     else");
			sql.append("                      null");
			sql.append("                   end) xnzfpm,");
			sql.append("               min(case");
			sql.append("                     when a.xmmc = '������' then"); 
			sql.append("                      b.bjpm");
			sql.append("                     else");
			sql.append("                      null");
			sql.append("                   end) xnzypm");
			sql.append("          from xg_zhcp_zcxmb a");
			sql.append("          left join xg_zhcp_zcfsb b");
			sql.append("            on a.xmdm = b.xmdm");
			sql.append("          left join xsxxb c");
			sql.append("            on b.xh = c.xh");
			sql.append("         where  a.xq <> 'on'");
			sql.append("           and (a.xmmc = '�۲��ܷ�' or a.xmmc = '������')");
			sql.append("         group by a.xn, a.xq,b.xh,c.bjdm) a");
			sql.append("  left join (select xn, xq ,bjdm,count(1) zrs");
			sql.append("               from xg_pjpy_new_cpmdb");
			sql.append("              where xn || xq || bjdm in");
			sql.append("                    (select xn || xq || bjdm");
			sql.append("                       from xg_pjpy_new_cpmdb )");
			sql.append("              group by xn, xq,bjdm) b");
			sql.append("    on b.xn = a.xn");
			sql.append("   and b.xq = a.xq");
			sql.append("   and a.bjdm = b.bjdm");
			sql.append(" group by a.xn,a.xh,a.bjdm");
			sql.append("");
			sql.append("");
			sql.append("");
			// ============== �ۺϿ������������� end ======================
			sql.append(" ) f on (a.xn=f.xn and a.xh=f.xh) ");
			sql.append(" ) t where 1=1 ");
			sql.append(searchTj);
			sql.append(searchTjByUser);
			return dao.getListNotOut(sql.toString(), inputV);
		}
	/**
	 * @description	�� ���칤��,ȡ�������������Ի���
	 * @author 		�� JZ��1529��
	 * @date 		��2017-12-13 ����05:13:41
	 * @param t
	 * @param user
	 * @return
	 */
	public HashMap<String,String> getQtzzje(String xh,String xn ){
		StringBuilder sql =  new StringBuilder();
		sql.append("select sum(je) as qtzzje from XG_XSZZ_NEW_ZZXMJGB where xh = ? and xn = ?  and  " +
				"(xmmc = '���ҽ�ѧ��'  or xmmc = '������־��ѧ��' or xmmc ='������ѧ��һ�ȣ�'or xmmc = '������ѧ�𣨶��ȣ�' or xmmc = '������ѧ�����ȣ�')");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	public HashMap<String,String> getKncsAndJe(String xh,String xn ){
		StringBuilder sql =  new StringBuilder();
		sql.append("select count(xmmc)as knbzcs,sum(je)as knbzje from XG_XSZZ_NEW_ZZXMJGB where xmmc  = '��ʱ���Ѳ���' and xh = ?and xn =?");
		return dao.getMapNotOut(sql.toString(), new String[] {xh,xn});
	}
	//�����մɵ�������
	public List<HashMap<String, String>> getShyjList(String xh, String xn, String xq, String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select shyj, xm, shsj, mc from (select t1.xh,t1.xn, t1.xq, t1.xmmc, t2.shyj, t2.shsj, t2.xm, " +
				"t2.shr, t3.xmdm, t2.mc, row_number() over(partition by t1.xh, t1.xn, t1.xq, t3.xmdm, t1.xmmc," +
				" t2.mc order by t2.shsj desc) rn  from xg_xszz_new_zzxmjgb t1 left join (select t2.*, t5.xm, t3.* " +
				"from xg_xtwh_shztb t2");
		sql.append(" left join fdyxxb t5 on t2.shr = t5.zgh   " +
				"left join xg_xtwh_spgw t3  on t2.gwid = t3.id) t2 on t2.ywid = t1.lylcywid " +
				" left join xg_xszz_new_zzxmsqb t3  on t3.guid = t1.lylcywid  where t1.xh = ?" +
				" and t1.xn = ?  and t1.xq = ? and t3.xmdm = ?) where rn = 1 order by shsj");
		String[] inputValue = { xh,xn,xq,xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * @description	�� TODO
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-18 ����03:55:28
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getGjjxjdc(ZzxmjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'�������֤' sfzlxmc,d.rs cjrs,b.pm cjpm,d.rs zhrs,c.pm zhpm,'' cjbz,'' bxkjgs,'' bxkms,'' sfzhkp,'' ffrq from view_new_dc_xszz_zzmxjg a");
		sql.append(" left join view_grzyfpm b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join view_grzhfpm c on a.xh = c.xh and a.xn = c.xn");
		sql.append(" left join (select bjdm,count(1) rs from view_xsbfxx group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" where a.xmmc like '%���ҽ�ѧ��%'");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	�� ������ѧ�𵼳�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-19 ����10:42:07
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjzxjdc(ZzxmjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'�������֤' sfzlxmc,'' zzbz,'' yfje,'' ffrq,a.sqly sqlv,'' bzsm from view_new_dc_xszz_zzmxjg a");
		sql.append(" where a.xmmc like '%������ѧ��%'");
		sql.append(" ) a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	�� ������־��ѧ�𵼳�
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-24 ����04:04:33
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjlzjdc(ZzxmjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select a.*,'�������֤' sfzlxmc,d.rs cjrs,b.pm cjpm,d.rs zhrs,c.pm zhpm,'' cjbz,'' bxkjgs,'' bxkms,'' sfzhkp,'' ffrq from view_new_dc_xszz_zzmxjg a");
		sql.append(" left join view_grzyfpm b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join view_grzhfpm c on a.xh = c.xh and a.xn = c.xn");
		sql.append(" left join (select bjdm,count(1) rs from view_xsbfxx group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" where a.xmmc like '%������־��ѧ��%'");
		sql.append(" ) a where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}


	public List<HashMap<String,String>> getXmzzqkhz(ZzxmjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String sxxm = t.getSxxm();//��ѡ��Ŀ
		String[] sxxms =sxxm.split(",");
		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (select a.*,'�������֤' sfzlxmc,d.rs cjrs,b.pm cjpm,d.rs zhrs,c.pm zhpm,'' cjbz,'' bxkjgs,'' bxkms,'' sfzhkp,'' ffrq from view_new_dc_xszz_zzmxjg a");
		sql.append(" left join view_grzyfpm b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join view_grzhfpm c on a.xh = c.xh and a.xn = c.xn");
		sql.append(" left join (select bjdm,count(1) rs from view_xsbfxx group by bjdm) d on a.bjdm = d.bjdm");
		sql.append(" where 1=1 ");
		for (int i = 0; i < sxxms.length; i++) {
			if(i==0)
			{
				sql.append(" and a.xmmc like '%"+sxxms[i]+"%' ");
			}
			else {
				sql.append(" or a.xmmc like '%" + sxxms[i] + "%' ");
			}
		}
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by a.xn,a.xq,a.sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}


	/**
	 *
	 * @����:ͨ��ѧ��ѧ��ѧ���ѯ��ѧ���ѻ��������Ŀ
	 * @���ߣ�lyx
	 * @���ڣ�2018-07-24
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgByXhXnList(String xh,String xn,String xq) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xqmc ");
		sql.append(",substr(a.sqsj,0,10) zzsj");
		sql.append(" from xg_xszz_new_zzxmjgb a left join xqdzb b on a.xq=b.xqdm  ");
		sql.append(" where a.xh=? and a.xq = ? and xn =?");
		sql.append(" order by xn desc,xq desc");
		return dao.getListNotOut(sql.toString(), new String[] { xh ,xq, xn });
	}
	/**
	 * 
	 * @����:TODO(����guid��xg_xszz_new_zzxmjgbȡҵ������id,��Ӧ�����xg_xszz_new_zzxmsqb���guid)
	 * @���ߣ��´���[���ţ�1620]
	 * @���ڣ�2018-9-5 ����03:48:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getSqbIdByYwid(String ywid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select lylcywid from xg_xszz_new_zzxmjgb where guid = ?");
		return dao.getOneRs(sql.toString(), new String[]{ywid},"lylcywid");
	}

    public List<HashMap<String,String>> getJgExportList(ZzxmjgForm t, User user) throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum rn,t.* from (");

		//������ͼ
		sql.append(" select * from ( ");

		sql.append(" select m.*,t2.sydm,t3.symc ");
		sql.append(" from VIEW_NEW_DC_XSZZ_ZZMXJG m ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=m.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" ) a  where 1=1 ");

		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(" order by symc,xmmc,xh");

		sql.append(" ) t ");
		return getPageList(t, sql.toString(), inputV);
    }
}
