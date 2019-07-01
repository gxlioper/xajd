package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48 
 * @�汾�� V5.12.20
 */
public class XjydsqDao extends SuperDAOImpl<XjydsqForm> {

	@Override
	protected void setTableInfo() {
		this.setTableName("xg_xsxx_xjydsqb");
		this.setKey("xjydsqid");
		this.setClass(XjydsqForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjydsqForm t)
			throws Exception {
		return null;
	}
	
	/**
	 * @throws Exception  
	 * @����:ѧ���춯�����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-28 ����10:10:38
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XjydsqForm model, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from  ");
		sql.append("  VIEW_NEW_DC_XJYD_XJYDSQ ");
		sql.append("  a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(),inputV);
	}

	/**
	 * @throws Exception  
	 * @����: �����ύ/ȡ���ύѧ���춯����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-30 ����11:56:48
	 * @param values
	 * @param tjzt
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean pltjXjydsq(String values, String tjzt) throws Exception {
		
		if (StringUtil.isNull(values)) {
			logger.error("�����������ܽ���!");
			throw new NullPointerException();
		}
		
		boolean isok=true;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_xsxx_xjydsqb set shzt = ? where xjydsqid = ?");
		
		String[] v = values.split(",");
		
		int i=0;
		for(String str:v){
			
			List<String> param=new ArrayList<String>();
			if(Integer.valueOf(getShzt(str))>0){
				param.add(Constants.YW_YTH);
			}else{
				param.add(Constants.YW_WTJ);
			}
			param.add(str);
			isok=isok&&dao.runUpdate(sql.toString(),param.toArray(new String[]{}));
			i++;
		}
		
		return isok;
	}
	
	
	/**
	 * 
	 * @����:�����ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-4 ����04:13:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param tjzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pltjXjydsq(String values, String tjzt, String shlcidnew) throws Exception {
		
		if (StringUtil.isNull(values)) {
			logger.error("�����������ܽ���!");
			throw new NullPointerException();
		}
		boolean isok=true;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update xg_xsxx_xjydsqb set shzt = ? ,splcid=?");
		sql.append(" where xjydsqid =?");
		
		String[] v = values.split(",");
		String[] p = shlcidnew.split(",");
		
		int i=0;
		for(String str:v){
			
			List<String> param=new ArrayList<String>();
			param.add(Constants.YW_SHZ);
			param.add(p[i]);
			param.add(str);
			isok=isok&&dao.runUpdate(sql.toString(),param.toArray(new String[]{}));
			i++;
		}
		return isok;
		
	}

	/**
	 * ȡ��������Ϣ
	 */
	@Override
	public XjydsqForm getModel(String keyValue) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xjydsqid, ");
		sql.append("       t1.sqsj, ");
		sql.append("       t1.shzt, ");
		sql.append("       t1.splcid, ");
		sql.append("       t1.xn, ");
		sql.append("       t1.xq, ");
		sql.append("       (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("       t1.ydlbdm, ");
		sql.append("       (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfyxj = '1' then ");
		sql.append("          '��ѧ��' ");
		sql.append("         else ");
		sql.append("          '��ѧ��' ");
		sql.append("       end ydhsfyxjmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfzx = '1' then ");
		sql.append("          '����У' ");
		sql.append("         else ");
		sql.append("          '��У' ");
		sql.append("       end ydhsfzxmc, ");
		sql.append("       t1.sqr, ");
		sql.append("       t1.sqly, ");
		sql.append("       t1.ydqnj, ");
		sql.append("       t1.ydqxydm, ");
		sql.append("       t1.ydqzydm, ");
		sql.append("       t1.ydqbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydqbjdm ");
		sql.append("           and nj = t1.ydqnj) ydqbjmc, ");

		sql.append("       t1.ydqxjlb, ");// �춯ǰ  ѧ�����
		sql.append("       t1.ydqxjlbmc, ");// �춯ǰ ѧ���������
		sql.append("       t1.ydqsfyxjmc, ");// �춯ǰ �Ƿ���ѧ������
		sql.append("       t1.ydqsfzxmc, ");//�춯ǰ�Ƿ���У����
		
		sql.append("       t1.ydhnj, ");
		sql.append("       t1.ydhxydm, ");
		sql.append("       t1.ydhzydm, ");
		sql.append("       t1.ydhbjdm, ");
		sql.append("       t1.ydhxjlb, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydhbjdm ");
		sql.append("           and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("       t3.xjlbmc ydqxjlbmc, ");
		sql.append("       t1.xh, ");
		sql.append("       t1.sqkssj, ");
		sql.append("       t1.sqjssj, ");
		sql.append("       t1.filepath ");
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(", t1.ydyydm, t1.lyqxxxdm, t1.xz, t1.sfsfs, t2.ydyymc, t4.xxmc ");
		}
		
		sql.append("  from xg_xsxx_xjydsqb t1 ");
		sql.append("  left join dm_xjlb t3 ");
		sql.append("    on t1.ydlbdm = t3.xjlbdm ");
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			sql.append(" left join xg_xsxx_xjydyydm t2 ");
			sql.append(" on t1.ydyydm = t2.ydyydm ");
			sql.append(" left join xg_xsxx_lyqxxx t4 ");
			sql.append(" on t1.lyqxxxdm = t4.xxdm ");			
		}
		
		sql.append(" where ");
		sql.append(" t1.xjydsqid ");
		sql.append("=?");
		logger.debug(sql);
		logger.debug("keyValue:"+keyValue);
		return getModel(sql.toString(), new String[]{keyValue});
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-31 ����02:07:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String keyValue)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xjydsqid, ");
		sql.append("       t1.sqsj, ");
		sql.append("       t1.shzt, ");
		sql.append("       t1.splcid, ");
		sql.append("       t1.xn, ");
		sql.append("       t1.xq, ");
		sql.append("       (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("       t1.ydlbdm, ");
		sql.append("       (select xjlbmc from dm_xjlb where xjlbdm = t1.ydlbdm) ydlbmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfyxj = '1' then ");
		sql.append("          '��ѧ��' ");
		sql.append("         else ");
		sql.append("          '��ѧ��' ");
		sql.append("       end ydhsfyxjmc, ");
		sql.append("       case ");
		sql.append("         when t3.sfzx = '1' then ");
		sql.append("          '����У' ");
		sql.append("         else ");
		sql.append("          '��У' ");
		sql.append("       end ydhsfzxmc, ");
		sql.append("       t1.sqr, ");
		sql.append("       t1.sqly, ");
		sql.append("       t1.ydqnj, ");
		sql.append("       t1.ydqxydm, ");
		sql.append("       t1.ydqzydm, ");
		sql.append("       t1.ydqbjdm, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydqxydm) ydqxymc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydqzydm) ydqzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydqbjdm ");
		sql.append("           and nj = t1.ydqnj) ydqbjmc, ");

		sql.append("       t1.ydqxjlb, ");// �춯ǰ  ѧ�����
		sql.append("       t1.ydqxjlbmc, ");// �춯ǰ ѧ���������
		sql.append("       t1.ydqsfyxjmc, ");// �춯ǰ �Ƿ���ѧ������
		sql.append("       t1.ydqsfzxmc, ");//�춯ǰ�Ƿ���У����
		
		sql.append("       t1.ydhnj, ");
		sql.append("       t1.ydhxydm, ");
		sql.append("       t1.ydhzydm, ");
		sql.append("       t1.ydhbjdm, ");
		sql.append("       t1.ydhxjlb, ");
		sql.append("       (select distinct xymc from view_njxyzybj_all where xydm = t1.ydhxydm) ydhxymc, ");
		sql.append("       (select distinct pyccmc from xg_xsxx_pyccdmb where pyccdm = t5.pycc) pyccmc, ");
		sql.append("       (select distinct zymc from view_njxyzybj_all where zydm = t1.ydhzydm) ydhzymc, ");
		sql.append("       (select distinct bjmc ");
		sql.append("          from view_njxyzybj_all ");
		sql.append("         where bjdm = t1.ydhbjdm ");
		sql.append("           and nj = t1.ydhnj) ydhbjmc, ");
		sql.append("       t3.xjlbmc ydqxjlbmc, ");
		sql.append("       t1.xh , t4.dybb ");
		sql.append("  from xg_xsxx_xjydsqb t1 ");
		sql.append("  left join dm_xjlb t3 ");
		sql.append("    on t1.ydlbdm = t3.xjlbdm    left join xg_xsxx_xjyd_xjydlbb t4 on t3.xjlbdm = t4.xjlbdm ");
		sql.append("    left join xsxxb t5 on t1.xh = t5.xh ");
		sql.append(" where ");
		sql.append(" t1.xjydsqid ");
		sql.append("=?");
		logger.debug(sql);
		logger.debug("keyValue:"+keyValue);
		return dao.getMapNotOut(sql.toString(), new String[]{keyValue});
	}
	
	/** 
	 * @����: �Ƿ�������жϣ���ѧ����δ������ѧ���춯�����������룩
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-12 ����02:57:38
	 * @param xh
	 * @return
	 * boolean ��������  true:�м�¼
	 * @throws 
	 */
	public boolean sfKsq(String xh) {
		boolean returnFlg = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) counts ");
		sql.append(" from xg_xsxx_xjydsqb t1 ");
		sql.append(" where t1.xh = ? ");
		sql.append(" and t1.shzt <> '1' and t1.shzt <> '2' ");
		List<HashMap<String, String>> lst = dao.getListNotOut(sql.toString(), new String[]{xh});
		if(lst !=null){
			returnFlg = Integer.parseInt(lst.get(0).get("counts")) > 0 ? true: false;
		}
		return returnFlg;
	}
	
	
	/**
	 * 
	 * @����:��ѯҵ��ID�Ƿ��й��˻ؼ�¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-5 ����02:57:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShzt(String ywid){
		
		StringBuffer sql = new StringBuffer(" select count(1) num from xg_xtwh_shztb where shzt = '3' and  ywid = ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{ywid}, "num");
		
		return num;
	}

	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:25:35
	 * @param xjlbdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkSfktj(String xjlbdm) {
		

		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from XG_XSXX_XJYD_XJYDLBB t2 ");
		sql.append("  where t2.sfksq = '0'       ");
		sql.append("    and ((t2.SQKSSJ is not null and ");
		sql.append("        t2.SQKSSJ <= to_char(sysdate, 'yyyy-mm-dd')) or t2.SQKSSJ is null) ");		
		sql.append("    and ((t2.SQJSSJ is not null and ");
		sql.append("        t2.SQJSSJ >= to_char(sysdate, 'yyyy-mm-dd')) or t2.SQJSSJ is null) ");
		sql.append("    and t2.xjlbdm = ? ");
		num = dao.getOneRs(sql.toString(), new String[] { xjlbdm }, "num");
	
		return num;
	}
	
	/**
	 * 
	 * @����:��ȡ�춯ԭ���б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-12 ����11:00:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdyyList() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select ydyydm,ydyymc from xg_xsxx_xjydyydm order by ydyydm asc ");	
		
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	/**
	 * 
	 * @����:��ȡ��Դȥ��ѧУ�б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-12 ����11:00:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLyqxxxList() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select xxdm lyqxxxdm,xxmc lyqxxxmc from xg_xsxx_lyqxxx order by xxdm asc ");	
		
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	/**
	 * 
	 * @����: ��ǰ״̬�б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-22 ����02:29:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDqztList() {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select dqztdm,dqztmc from zxbz_dqztm order by dqztdm asc ");	
		
		return dao.getListNotOut(sql.toString(), new String[] {});
	} 

}
