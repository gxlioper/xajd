/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����02:38:21 
 */
package com.zfsoft.xgxt.xpjpy.xmsz.xmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����02:38:21
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmwhDao extends SuperDAOImpl<XmwhModel> {

	@Override
	protected void setTableInfo() {
		super.setClass(XmwhModel.class);
		super.setTableName("xg_pjpy_new_pjxmb");
		super.setKey("xmdm");
	}

	/**
	 * 
	 * @����:�߼���ѯ����
	 * @���ߣ�ligl  TODO
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhModel model, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder(
				"select a.xmdm,a.lxdm,a.xzdm,a.xmmc,a.sfqy,a.xmje,a.shlc,a.rskzjb,a.jdkzjb,a.rsfpfs,a.rsfpz,a.rsfpnj,a.zcfpm,");		
		sql.append("a.xmsm,a.sqkssj,a.sqjssj,a.sqkg,a.shkssj,a.shjssj,a.shkg,a.kgbz,a.xn,a.xq,a.dybb,to_number(a.xsxh) xsxh,a.xmywmc,");
		sql.append("b.xmlxmc,b.xmlxdm,c.xmxzdm,c.xmxzmc");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_pjpy_new_jdszb e where a.xmdm=e.xmdm) jdsz");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_pjpy_new_xmtjb f where a.xmdm=f.xmdm) tjsz ");
		sql.append(",(select case when count(1)>0 then sum(zzme) else 0 end  from xg_pjpy_new_rsszb g where a.xmdm=g.xmdm and g.zzme is not null ");
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)&& "xy".equalsIgnoreCase(user.getUserStatus())){  //�㽭��ѧ���Ի��޸�
			sql.append("and bmdm = '" + user.getUserDep() + "'");
		}
		if(null!=model.getCzfs()&&model.getCzfs().length()!=0&&model.getCzfs().equals("xyrssz")&& Globals.XXDM_ZJDX.equals(Base.xxdm)){//�㽭��ѧ���Ի��޸�
			sql.append("and zd1 is not null ");
		}
		sql.append(") rssz ");
		sql.append(",(select case when count(*)>0 then '1' else '0' end  from xg_pjpy_new_jxtzsz h where a.xmdm=h.xmdm) shsz ");
		sql.append(" from xg_pjpy_new_pjxmb a, ");
		if("2".equals(model.getXmxz()))
		{//���ý���
			sql.append("xg_pjpy_new_xmlx b, ");
		}
		else{//��ѧ��
			sql.append("xg_pjpy_new_jxjxmlx b, ");
		}

		sql.append("  xg_pjpy_new_xmxz c where b.xmlxdm=a.lxdm and c.xmxzdm=a.xzdm ");
		if("2".equals(model.getXmxz()))
		{//���ý���
			sql.append(" and a.xzdm = '2' ");
		}
		else{//��ѧ��
			sql.append(" and a.xzdm = '1' ");
		}
		String currXn = CsszService.getPjzq().get("xn");// ��ǰѧ��
		String currXq = CsszService.getPjzq().get("xq");// ����ѧ��
		sql.append(" and a.xn='");
		sql.append(currXn);
		sql.append("'");
		sql.append(" and a.xq='");
		sql.append(currXq);
		sql.append("'");
		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and xmmc like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getLxdm())) {
			params.add(model.getLxdm());
			sql.append(" and lxdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXzdm())) {
			params.add(model.getXzdm());
			sql.append(" and xzdm like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getSqkg())) {
			String sqkg = model.getSqkg();
			if(sqkg.equals("1")){
				params.add(model.getSqkg());
				sql.append(" and sqkg like '%'||?||'%' and");
				sql.append(" (sysdate between to_date(nvl(a.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(a.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");	
			}else{
				params.add(model.getSqkg());
				sql.append(" and (sqkg like '%'||?||'%' or");
				sql.append(" (sysdate not between to_date(nvl(a.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(a.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')))");	
			}
		}
		if(!StringUtil.isNull(model.getCzfs())&&model.getCzfs().equals("xyrssz")){
			sql.append(" and rsfpfs<>'xx'");
			sql.append(" and a.xmdm in (select xmdm from xg_pjpy_new_rsszb where fpbl is not null )");
			if ("xy".equalsIgnoreCase(user.getUserStatus())){
			  sql.append(" and '"+user.getUserDep()+"' in(select bmdm from xg_pjpy_new_rsszb where fpbl is not null)");	
			}
		}
		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @����: ��ѯ�������������Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����08:41:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKfsqPjxm() {

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select * from xg_pjpy_new_pjxmb t where t.sfqy='1' and sqkg='1' and");
		sql
				.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql
				.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		sql
				.append(" and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1)");

		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_pjpy_new_pjxmb t where t.sfqy='1' and sqkg='1' and");
		sql.append(" (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		sql.append(" and xn||xq=? ");

		return dao.getListNotOut(sql.toString(), new String[] {xnxq});
	}
	
	public List<HashMap<String, String>> getKfsqPjxm(String xnxq,String bjdms) {

		StringBuilder sql = new StringBuilder();
		MessageResources message = MessageResources
						.getMessageResources("config.ApplicationResources");
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		sql.append(" select t1.*,decode(t1.rsfpfs,'xx','ѧУ','xy','"+Base.YXPZXY_KEY+"','njxy','�꼶+"+Base.YXPZXY_KEY+"','njzy','�꼶+רҵ','bj','�༶','cpz','������') as rsksfsmc, ");
		sql.append(" decode(decode(t1.rsfpfs,'xx',t7.zzme,'xy',t2.xyzzme,'njxy',t3.njxyzzme,'njzy',t4.njzyzzme,'bj',t5.bjzzme,'cpz',t6.cpzzzme),null,'����������',decode(t1.rsfpfs,'xx',t7.zzme,'xy',t2.xyzzme,'njxy',t3.njxyzzme,'njzy',t4.njzyzzme,'bj',t5.bjzzme,'cpz',t6.cpzzzme)) as zzme ");
		sql.append(" from xg_pjpy_new_pjxmb t1 left join xg_pjpy_new_rsszb t7 on t1.xmdm=t7.xmdm and t7.bmdm='xx' left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as xyzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where a.bmdm in (select xydm from view_njxyzybj_all where bjdm in ("+bjdms+")) group by a.xmdm ");
		sql.append(" ) t2 on t1.xmdm=t2.xmdm left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as njxyzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where (a.nj,a.bmdm) in (select nj,xydm from view_njxyzybj_all where bjdm in ("+bjdms+")) ");
		sql.append(" group by a.xmdm ) t3 on t1.xmdm=t3.xmdm left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as njzyzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where (a.nj,a.bmdm) in (select nj,zydm from view_njxyzybj_all where bjdm in ("+bjdms+")) ");
		sql.append(" group by a.xmdm  ) t4 on t1.xmdm=t4.xmdm  left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as bjzzme from xg_pjpy_new_rsszb a ");
		sql.append(" where a.bmdm in ("+bjdms+") group by a.xmdm ) t5 on t1.xmdm=t5.xmdm left join ( ");
		sql.append(" select a.xmdm,sum(a.zzme) as cpzzzme from xg_pjpy_new_rsszb a "); 
		sql.append(" left join xg_pjpy_new_pjxmb b on a.xmdm=b.xmdm  where a.bmdm in "); 
		sql.append(" (select c.cpzdm from XG_ZHCP_FSTJJLB c where b.xn=c.xn and b.xq=c.xq and c.bjdm in ("+bjdms+") ) ");
		sql.append(" group by a.xmdm  ) t6 on t1.xmdm=t6.xmdm "); 
		sql.append(" where t1.sfqy='1' and t1.sqkg='1' and (sysdate between to_date(nvl(t1.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') "); 
		sql.append(" and to_date(nvl(t1.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and t1.xn||t1.xq=? order by to_number(xsxh) asc  ");

		return dao.getListNotOut(sql.toString(), new String[] {xnxq});
	}

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhModel model)
			throws Exception {
		return null;
		
	}

	/**
	 * 
	 * @����: ����ǰ��Ŀ�⣬�������Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm,String currXn,String currXq)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmdm,xmmc ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where ");
		if(!StringUtil.isNull(xmdm)){
			sb.append("xmdm!=? and ");
		}
		sb.append(" xn='");
		sb.append(currXn);
		sb.append("' and xq='");
		sb.append(currXq);
		sb.append("'");
		List<String> input=new ArrayList<String>();
		
		if(!StringUtil.isNull(xmdm)){
			sb.append(" and shlc = (select shlc from xg_pjpy_new_pjxmb where xmdm=?) ");
			input.add(xmdm);
			input.add(xmdm);
		}
		
		sb.append(" order by to_number(nvl(xsxh,0))");
		
		
		return dao.getListNotOut(sb.toString(), input.toArray(new String[]{}));
	}

	public List<HashMap<String, String>> getOthers(String xmdm,String currXn,String currXq,String xzdm)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmdm,xmmc ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where ");
		if(!StringUtil.isNull(xmdm)){
			sb.append("xmdm!=? and ");
		}
		sb.append(" xn='");
		sb.append(currXn);
		sb.append("' and xq='");
		sb.append(currXq);
		sb.append("'");
		List<String> input=new ArrayList<String>();

		if(!StringUtil.isNull(xmdm)){
			sb.append(" and shlc = (select shlc from xg_pjpy_new_pjxmb where xmdm=?) ");
			input.add(xmdm);
			input.add(xmdm);
		}
		if("2".equals(xzdm)){
			sb.append(" and xzdm='2' ");
		}else{
			sb.append(" and xzdm <> '2' ");
		}

		sb.append(" order by to_number(nvl(xsxh,0))");


		return dao.getListNotOut(sb.toString(), input.toArray(new String[]{}));
	}

	/**
	 * 
	 * @����:�ж��ظ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRepeat(XmwhModel model) throws Exception {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) count from xg_pjpy_new_pjxmb t ");
		sql.append(" where t.xmmc=?");
		if (model.getXmdm() != null && !model.getXmdm().trim().equals("")) {
			sql.append(" and t.xmdm!='" + model.getXmdm() + "'");
		}
		String currXn = CsszService.getPjzq().get("xn");// ��ǰѧ��
		String currXq = CsszService.getPjzq().get("xq");// ����ѧ��
		sql.append(" and t.xn='");
		sql.append(currXn);
		sql.append("'");
		sql.append(" and t.xq='");
		sql.append(currXq);
		sql.append("'");
		String xmmc = model.getXmmc();
		if (xmmc != null) {
			xmmc = xmmc.trim();
		}
		String[] input = { xmmc };
		String[] output = { "count" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);// ///�˷����쳣�ѱ���������������޷�����
		// ��������������////������������////
		if (oneRs != null && oneRs.length > 0) {
			if (!oneRs[0].equals("0")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @����:ɾ������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean delRalate(String keys) throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		if (keys != null) {
			keys = StringUtils.replace(keys, ",", "','");
			keys = "'" + keys + "'";
		} else {
			return true;
		}
		String sql = "delete from  xg_pjpy_new_rsszb ";// �������ñ�
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_xmtjb ";// ���� ���ñ�
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_jdszb ";// ������ñ�
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_jdszb ";// ������ñ�
		sql += " where bjdxmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_jxtzsz ";// ��˵�������Ŀ����
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xg_pjpy_new_pjxmb ";// ��Ŀά����
		sql += " where xmdm in(" + keys + ")";
		sqlList.add(sql);
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @����:������Ŀ�����ѯ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String getNameById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmmc xmmc ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		String[] outputValue = { "xmmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (oneRs != null && oneRs.length > 0) {
			xmmc = oneRs[0];
		}
		return xmmc;
	}

	/**
	 * 
	 * @����:������Ŀ�����ѯ��¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataById(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where xmdm=?");
		String[] inputValue = { xmdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:������Ŀ���Ʋ�ѯ������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn, String xq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		sb.append(" where xmmc=?");

		sb.append(" and xn='");
		sb.append(xn);
		sb.append("'");
		sb.append(" and xq='");
		sb.append(StringUtil.isNull(xq) ? "on" : xq);
		sb.append("'");

		String[] inputValue = { xmmc };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @����:����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param xmdm
	 * @param zme
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean setZme(String xmdm, String zme, String rsfpnj)
			throws Exception {
		String sql = "update xg_pjpy_new_pjxmb set rsfpz=?,rsfpnj=? where xmdm=? ";
		String[] input = { zme, rsfpnj, xmdm };
		return dao.runUpdate(sql, input);
	}

	/**
	 * 
	 * @����:ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getRsfpnj(String xmdm) throws Exception {
		String rsfpnj = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select rsfpnj from xg_pjpy_new_pjxmb where xmdm=? ");
		String[] input = { xmdm };
		String[] output = { "rsfpnj" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				rsfpnj = oneRs[0];
			}
		}
		return rsfpnj;
	}

	/**
	 * 
	 * @����:ͨ����Ŀ�����ȡ�������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String getPycc(String xmdm) throws Exception {
		String pycc = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select pycc from xg_pjpy_new_pjxmb where xmdm=? ");
		String[] input = { xmdm };
		String[] output = { "pycc" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				pycc = oneRs[0];
			}
		}
		return pycc;
	}

	/**
	 * 
	 * @����:��ȡ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-7 ����11:24:49
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmxzdm dm,xmxzmc mc ");
		sb.append(" from  xg_pjpy_new_xmxz ");
		sb.append(" order by xmxzdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @����:��ȡ��Ŀ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-7 ����11:24:49
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx(String xzdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmlxdm dm,xmlxmc mc from ");
		if("2".equals(xzdm)){
			sb.append(" xg_pjpy_new_xmlx ");
		}else{
			sb.append(" xg_pjpy_new_jxjxmlx ");
		}

		sb.append(" order by xmlxdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @����:��ѯ���Ը��ƵĽ�����Դ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����11:01:07
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfzXnXq(String csz) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct xn,xq ");
		sb.append(" from  xg_pjpy_new_pjxmb ");
		if(CsszService.PJFS_XN.equals(csz)){
			sb.append(" where xq = 'on' ");
		}else{
			sb.append(" where xq <> 'on' ");
		}
		sb.append("order by xn desc,xq desc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @����:��ѯ���Ը��ƵĽ�����Դ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-14 ����11:01:07
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getJxfz(String fzXn, String fzXq,
			String currXn, String currXq) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_pjpy_new_pjxmb  ");
		sb.append("  where xn='");
		sb.append(fzXn);
		sb.append("'");
		sb.append("  and xq='");
		sb.append(fzXq);
		sb.append("'");
		sb
				.append("  and xmmc not in(select xmmc from xg_pjpy_new_pjxmb where xn='");
		sb.append(currXn);
		sb.append("'");
		sb.append("  and xq='");
		sb.append(currXq);
		sb.append("')");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

	public boolean saveData(List<HashMap<String, String>> list, String currXn,
			String currXq) throws Exception {

		int[] result = null;
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_pjpy_new_pjxmb ");
		sb
				.append("(xmdm,jdkzjb,kgbz,lxdm,rsfpfs,rsfpnj,rsfpz,rskzjb,sfqy,shjssj,shkg,shkssj,shlc,sqjssj,sqkg,sqkssj,xmje,xmmc,xmsm,xzdm,zcfpm,xsxh,xn,xq)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'");
		sb.append(currXn);
		sb.append("','");
		sb.append(currXq);
		sb.append("')");

		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : list) {
			param = new String[22];
			param[0] = map.get("id");
			param[1] = map.get("jdkzjb");
			param[2] = map.get("kgbz");
			param[3] = map.get("lxdm");
			param[4] = map.get("rsfpfs");
			param[5] = map.get("rsfpnj");
			param[6] = map.get("rsfpz");
			param[7] = map.get("rskzjb");
			param[8] = map.get("sfqy");
			param[9] = map.get("shjssj");
			param[10] = map.get("shkg");
			param[11] = map.get("shkssj");
			param[12] = map.get("shlc");
			param[13] = map.get("sqjssj");
			param[14] = map.get("sqkg");
			param[15] = map.get("sqkssj");
			param[16] = map.get("xmje");
			param[17] = map.get("xmmc");
			param[18] = map.get("xmsm");
			param[19] = map.get("xzdm");
			param[20] = map.get("zcfpm");
			param[21] = map.get("xsxh");
			params.add(param);
		}
		result = dao.runBatch(sb.toString(), params);
		return true;
	}
	
	/**
	 * 
	 * @����:��ѯ�ܹ�������Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-26 ����11:48:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPjxm() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct trim(xmmc) xmmc from (select * from xg_pjpy_new_pjxmb order by xn,xq,to_number(xsxh)) ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	public List<HashMap<String, String>> getPjjgxmmc() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct trim(xmmc) xmmc from xg_pjpy_new_pjjgb order by xmmc ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
		
	
	/**
	 * 
	 * @����:��������ȡ��Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-19 ����12:09:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCountXmlx(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct a.xmlxdm||','||b.lv xmlxdm, b.lv||'�Ρ�'||a.xmlxmc||'��������' xmlxmc from ");
		sql.append("xg_pjpy_new_xmlx a,(select LEVEL lv from dual CONNECT BY LEVEL <= 5) b order by a.xmlxdm||','||b.lv");

		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������Ŀ����ظ���֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-11-17 ����02:40:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistXssx(XmwhModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select count(1) num from xg_pjpy_new_pjxmb ");
		sql.append(" where xn = ? and  xq = ? and xsxh = ? and xzdm=? ");
		params.add(CsszService.getPjzq().get("xn"));
		params.add(CsszService.getPjzq().get("xq"));
		params.add(model.getXsxh());
		params.add(model.getXmxz());
		String xmdm = model.getXmdm();
		if(!StringUtils.isEmpty(xmdm)){
			sql.append(" and xmdm <> ? ");
			params.add(xmdm);
		}
		
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	/**
	 * @����: �㽭��ýѧԺ����Ŀ���ʡ����ˡ����ҽ�ѧ�𡱲�ѡ�У�����ȫ��Ĭ��ѡ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ� 2015-12-31 ����01:04:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getXmxzmw() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmxzdm,xmxzmc ");
		sb.append(" from  xg_pjpy_new_xmxz ");
		sb.append(" where xmxzmc not like '���ҽ�ѧ��' ");
		sb.append(" order by xmxzdm");
		String[] input = {};
		return dao.getList(sb.toString(), input,"xmxzdm");
	}
	
	//��������������
	public String getNameByIdJtpj(String xmdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select jxmc xmmc ");
		sb.append(" from  XG_PJPY_JTPJ_JTJXSZ ");
		sb.append(" where jxid=?");
		String[] inputValue = { xmdm };
		String outputValue = "xmmc";
		String oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xmmc = "";
		if (xgxt.utils.String.StringUtils.isNotNull(oneRs)) {
			xmmc = oneRs;
		}
		return xmmc;
	}
	
	/** 
	 * @����:��ȡ�����ǼǴ�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-19 ����02:35:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTydj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select pjdjdm dm,pjdjmc mc from xg_pjpy_new_pjdj where pjxmmc = '���ܲ��Գɼ�' ");

		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @description	�� �Ƿ�����ɲ��������Ƽ���ѧ��
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-28 ����02:49:58
	 * @param xmdm
	 * @return
	 */
	public String getSfyxgb(String xmdm){
		String sql = "select nvl(sfyxgb,'0') sfyxgb from xg_pjpy_new_pjxmb where xmdm = ?";
		return dao.getOneRs(sql, new String[]{xmdm}, "sfyxgb");
	}
	
	/**
	 * @description	�� �����Ƿ�����ɲ��������Ƽ���ѧ��
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-28 ����03:47:01
	 * @param xmdm
	 * @return
	 * @throws Exception 
	 */
	public boolean updateSfYxgb(String xmdm,String sfyxgb) throws Exception{
		String sql = "update xg_pjpy_new_pjxmb set sfyxgb = ? where xmdm = ?";
		return dao.runUpdate(sql, new String[]{sfyxgb,xmdm});
	}

	public List<HashMap<String,String>> getnewXmlx(String xmxz) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xmlxdm dm,xmlxmc mc ");
		if("2".equals(xmxz))
		{
			sb.append(" from  xg_pjpy_new_xmlx ");
		}else{
			sb.append(" from  xg_pjpy_new_jxjxmlx ");
		}

		sb.append(" order by xmlxdm");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}

    public String getXsxh(String xzdm) {

		String sql = "SELECT nvl(max(XSXH),0)+1 xsxh FROM xg_pjpy_new_pjxmb WHERE XZDM = ? ";
		return dao.getOneRs(sql,new String [] {xzdm},"xsxh");
    }
}
