/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����09:20:12 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ��Ϣά�� 
 * @���ߣ�dlq [���ţ�995]
 * @ʱ�䣺 2013-8-2 ����09:20:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RcxwxxwhDao extends SuperDAOImpl<RcxwxxwhForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("xg_rcsw_rcxwxxwh");
		super.setClass(RcxwxxwhForm.class);
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwxxwhForm t)
			throws Exception {
		return null;
	}

	/**
	 * ȡmodel
	 * @throws Exception 
	 */
	@Override
	public RcxwxxwhForm getModel(RcxwxxwhForm model) throws Exception{		
		StringBuffer sql = new StringBuffer();		
		sql.append(" select a.*,b.rcxwlbdldm ");
		sql.append(" from XG_RCSW_RCXWXXWH a ");
		sql.append(" left join XG_RCSW_RCXWLBDMB b   ");
		sql.append(" on a.rcxwlbdm = b.rcxwlbdm ");
		sql.append(" left join XG_RCSW_RCXWDBDLB c   ");
		sql.append(" on b.rcxwlbdldm = c.rcxwlbdldm ");		
		sql.append(" where a.id = ? ");
		return getModel(sql.toString(),new String[]{model.getId()});
	}
		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RcxwxxwhForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t5", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		/*sql.append("select  t5.id,t5.xh,t5.xm,t5.xb,t5.bjmc,t5.nj,t5.bjdm,t5.xydm,t5.zydm,t5.rcxwlbdlmc,rcxwlbmc,t5.rcxwjlsj,t5.shztmc, t5.rcxwlbdldm,t5.xn,");
		sql.append(" (select xqmc from xqdzb b where t5.xq=b.xqdm) xq,t5.bz,t5.rcxwlbdm from (");
		sql.append(" select t1.id,t1.xh,t2.xm,t2.xb,t1.bz,t2.bjmc,");
		sql.append("t2.lxdh,t1.xn,t2.xydm,t2.zydm,t2.bjdm,t2.nj,");
		//sql.append("decode(t1.xq,'01','��','02','��',t1.xq ) xq, ");
		sql.append("t1.rcxwjlsj,t3.rcxwlbmc,t3.rcxwlbdlmc,t1.shzt,t3.rcxwlbdldm,t3.rcxwlbdm,");
		sql.append("decode(t1.shzt,'0','δ���','1','ͨ��','2','��ͨ��',");
		sql.append("'3','�˻�','4','������','5','�����','','�������',t1.shzt) shztmc, t4.xqdm xq ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join (select *  from (select a.*,b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a left join ");
		sql.append(" xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm )) t3  on t1.rcxwlbdm =t3.rcxwlbdm ");
		sql.append(" left join  xqdzb t4 on t1.xq = t4.xqdm ) t5 where 1=1 ");*/
		
		sql.append("select * from VIEW_NEW_DC_RCSW_RCSWXXWH t5 where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:��ȡ��Ϊ��𼯺�
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:22:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwlbList(String xwdldm,
			HttpServletRequest request) {
		String sql = "select rcxwlbdm,rcxwlbmc from xg_rcsw_rcxwlbdmb where rcxwlbdldm=? and sfqy='1' order by rcxwlbdm ";
		return dao.getList(sql, new String[] { xwdldm }, new String[] {
				"rcxwlbdm", "rcxwlbmc" });
	}
	/**
	 * 
	 * @����:��ȡ��Ϊ���༯��
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:21:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.rcxwlbdldm,b.rcxwlbdlmc, ");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from xg_rcsw_rcxwdbdlb b  ");
		sql.append(" ) where isopen='true' order by rcxwlbdldm ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] { "rcxwlbdldm",
				"rcxwlbdlmc" });
	}
	
	/**
	 * 
	 * @����:����ѧ�ţ�ѧ�꣬ѧ�ڲ�ѯ���ճ���Ϊ��Ϣ �ڱ����м�������
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:20:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(RcxwxxwhForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_rcsw_rcxwxxwh where xh=? and xn=? and xq=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getXn(), model.getXq() }, "num");
		return num;

	}
	
	/**
	 * 
	 * @����:��ȡ���״̬
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:19:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(RcxwxxwhForm model) {
		String sql = "select shzt from xg_rcsw_rcxwxxwh where id=? ";
		String shzt = dao.getOneRs(sql.toString(), new String[] { model.getId()}, "shzt");
		return shzt;

	}

	/**
	 * 
	 * ��ȡ��������ID
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-5 ����03:09:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getShlcID(String Rcxwlbdldm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_rcxwdbdlb  where rcxwlbdldm = ? ");

		return dao
				.getOneRs(sql.toString(), new String[] { Rcxwlbdldm }, "splc");
	}
	
	/**
	 * 
	 * @����:��ɾ���ճ���Ϊ���ͬʱɾ���ճ���Ϊά��
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:19:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delRcxwwhFromRcxwjg(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_rcsw_rcxwxxwh t1 where  ");
		sql.append(" (");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append("id=?");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 
	 * @����:�жϸ���Ϊ�����Ƿ����������
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:18:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcxwlbdldm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkForSplc(String rcxwlbdldm)throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_rcxwdbdlb  where rcxwlbdldm = ? ");
		return dao.getOneRs(sql.toString(), new String[] { rcxwlbdldm }, "splc");
		
	}
	
	/**
	 * 
	 * @����:ɾ���ճ���Ϊ��Ϣ
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:17:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delXwxx(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_rcxwxxwh ");
		sql.append(" where (shzt is null or shzt='0' or shzt='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 
	 * �鿴�����ճ���Ϊ��Ϣ
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����02:38:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneXwxxList(String  xwjgId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,xn, xqmc , rcxwlbmc,rcxwlbdlmc,fjlj,fjmc, ");
		sql.append(" rcxwjlsj,gfly,bz,(case when rcxwfzlx='01' then '+'||fz when rcxwfzlx='02' then '-'||fz else 'δ֪����' end) fz, decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','4','������','5','�����','','�������',a.shzt) shztmc,fssj,c.rcxwlbbz,jlr,(select xm from yhb y where y.yhm=a.jlr) jlrxm ");
		sql.append(" from xg_rcsw_rcxwxxwh a left join (select *  from (select a.*,b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm )) c ");
		sql.append(" on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xqdzb d on a.xq = d.xqdm where a.id = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xwjgId});
	}
	

	public boolean updateRcxwxxwh(String id,String splc,String shzt) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_rcxwxxwh ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where id = ?");
		inputV[0] = shzt;
		inputV[1] = splc;
		inputV[2] = id;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/** 
	 * @����: ��ѯ��ȡ��Ϊ�����Ϣ
	 * @���ߣ�HongLin [���ţ�707]
	 * @���ڣ�2014-2-21 ����10:51:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXwlbxx(HttpServletRequest request,String lbdm) {
		String sql = "select a.rcxwlbdm,a.rcxwlbmc,a.rcxwlbdldm,a.rcxwfzlx,(case when a.rcxwfzlx='01' then '�ӷ�' when a.rcxwfzlx='02' then '����' else 'δ֪����' end) rcxwfzlxmc,"
					+"(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then a.rcxwlbzdfz||'-'||a.rcxwlbzgfz when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is null then a.rcxwlbzdfz when a.rcxwlbzdfz is null and a.rcxwlbzgfz is not null then a.rcxwlbzgfz else '' end) fzqj,"
					+"a.rcxwlbbz,(case when a.rcxwlbbz is null then '' when length(a.rcxwlbbz) <=10 then a.rcxwlbbz else substr(a.rcxwlbbz,0,10)||'......' end) rcxwlbbzsj,a.rcxwlbfz,(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then 'zdy' else 'gd' end) fzsfgd,a.rcxwlbzgfz,a.rcxwlbzdfz "
					+" from xg_rcsw_rcxwlbdmb a where rcxwlbdm=? and rownum=1";
		return dao.getList(sql, new String[] {lbdm}, new String[] { "rcxwlbdm","rcxwlbmc","rcxwlbdldm","rcxwfzlx","rcxwfzlxmc","fzqj","rcxwlbbz","rcxwlbfz","fzsfgd","rcxwlbzgfz","rcxwlbzdfz","rcxwlbbzsj" });
	}
	
	/** 
	 * @����: �ж���Ϣ�Ƿ��ظ�(ѧ�š�ѧ�ڡ�ѧ�ꡢ��Ϊ�б�����ʱ��)
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-24 ����05:47:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String[] xwlbStr,String[] fssjStr){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbmc from XG_RCSW_RCXWXXWH a left join XG_RCSW_RCXWLBDMB b on a.rcxwlbdm=b.rcxwlbdm where 1=1 ");
		if(xwlbStr!=null && xwlbStr.length>0){
			sql.append(" and ");
			for (int i=0;i<xwlbStr.length;i++){
				if(i==(xwlbStr.length-1)){
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbdm='"+xwlbStr[i]+"' and a.fssj='"+fssjStr[i]+"')");
				}else{
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbdm='"+xwlbStr[i]+"' and a.fssj='"+fssjStr[i]+"') or ");
				}
			}
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xh","xn","xq","rcxwlbmc","fssj"});
	}

	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:25:35
	 * @param jddm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String checkSfktj(String rcxwlbdm) {
		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from XG_XLJK_CDGL_CDXXB t2 ");
		sql.append("  where t2.sfkfsq = '1'       ");
		sql.append("    and t2.cdid = ? ");
		
		num = dao.getOneRs(sql.toString(), new String[] { rcxwlbdm }, "num");
		return num;
	}
}
