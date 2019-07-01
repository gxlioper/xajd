/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����10:54:25 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-28 ����10:54:25
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjSzDao extends SuperDAOImplExtend<JtpjSzForm> {
	@Override
	public List<HashMap<String, String>> getPageList(JtpjSzForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();

		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JtpjSzForm t, User user)
			throws Exception {
		ArrayList<String> inputV = new ArrayList<String>();
		inputV.add(JtpjSzAction.JTPJ_SHLC);

		StringBuffer sql = new StringBuffer();
		sql
				.append("select d.* ,(select xqmc from xqdzb where xqdm=d.pdxq) pdxqmc,");

		// ������ֹʱ��
		sql.append(" 		case ");
		sql.append("   			when d.sfkfsq = '0' then '' ");
		sql
				.append("   			when d.sqkfjssj is not null then d.sqkfkssj || ' �� '|| d.sqkfjssj ");
		sql
				.append("   			when d.sqkfkssj is not null then d.sqkfkssj||  ' �� ' ");
		sql.append("   			else '' end sqqzsj, ");

		// ��ѯ�������
		sql
				.append(" decode(e.lcxx, '', '', mc || '��' || replace(lcxx, ';', '->')) lcxx, ");
		sql.append(" (select case");
		sql.append(" when count(*) > 0 then");
		sql.append(" '1'");
		sql.append(" else");
		sql.append(" '0'");
		sql.append(" end");
		sql.append(" from xg_pjpy_new_xmtjb f");
		sql.append("  where d.jxid = f.xmdm) tjsz");
		sql
				.append(" from XG_PJPY_JTPJ_JTJXSZ d left join (select splc, mc, lcxx ");
		sql
				.append(" from (select splc,a.mc,to_char(WM_CONCAT(c.mc) over (partition by splc order by xh)) lcxx,  ");
		sql.append(" row_number() over(partition by splc order by xh desc) as ddd ");
		sql
				.append(" from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c where djlx = ? and a.id = b.splc ");
		sql
				.append(" and b.spgw = c.id) b where ddd = 1 ) e on d.splcid = e.splc where 1 = 1 ");

		if (StringUtils.isNotNull(t.getJxmc())) {
			sql.append(" and jxmc like '%'||?||'%'");
			inputV.add(t.getJxmc());
		}
		if (StringUtils.isNotNull(t.getSqxn())) {
			sql.append(" and sqxn =?");
			inputV.add(t.getSqxn());
		}
		if (StringUtils.isNotNull(t.getSqxq())) {
			sql.append(" and sqxq =?");
			inputV.add(t.getSqxq());
		}
		return this.getPageList(t, sql.toString(), inputV
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @����: �����������ڻ�ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-7 ����03:36:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqxn
	 * @param sqxq
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJxxxForSqXnXq(String sqxn,
			String sqxq) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_PJPY_JTPJ_JTJXSZ where sqxn=? and sqxq=?");
		return dao.getListNotOut(sb.toString(), new String[] { sqxn, sqxq });
	}

	/**
	 * 
	 * @����: ��ȡְ���б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����09:29:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getZwList(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zwid,t.zwmc from VIEW_NEW_DC_SZDW_XSDWWH t where t.xh = ? and t.zzzt = '1' ");
		return dao.getArrayList(sql.toString(), new String[] { xh },
				new String[] { "zwid", "zwmc" });
	}

	public List<HashMap<String, String>> getZwList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zwid,t.zwmc from xg_szdw_xsgb_zwb t");
		return dao.getArrayList(sql.toString(), new String[] {}, new String[] {
				"zwid", "zwmc" });
	}

	/**
	 * 
	 * @����: ��ȡְ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����09:29:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zwid
	 * @return String ��������
	 */
	public String getZwMc(String zwid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zwmc from xg_szdw_xsgb_zwb t where zwid=?");
		return dao.getOneRs(sql.toString(), new String[] { zwid }, "zwmc");
	}

	/**
	 * 
	 * @����: ��ȡ����list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @param flg
	 *            : 0:ȫ���б�1����������б�
	 * @���ڣ�2014-4-30 ����09:31:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJxList(String xn, String xq,
			String flg, String jxid,User user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.jxid,t.jxmc,t.ksqxslx from xg_pjpy_jtpj_jtjxsz t where 1=1 ");

		// �������б�
		if ("1".equals(flg)) {
			sql.append("    and ((sqkfkssj is not null and sqkfkssj <= to_char(sysdate,'yyyy-mm-dd')) or sqkfkssj is null) ");
			sql.append("    and ((sqkfjssj is not null and sqkfjssj >= to_char(sysdate,'yyyy-mm-dd')) or sqkfjssj is null) ");
			sql.append("    and SFKFSQ = '1'  ");
			if (user.getUserType().equals("stu")&&Globals.XXDM_WZDX.equals(Base.xxdm)) {
				sql.append(" and ksqxslx is not null");
			}
		}

		if (StringUtils.isNotNull(xn)) {
			sql.append(" and sqxn = '" + xn + "'");
		}
		if (StringUtils.isNotNull(xq)) {
			sql.append(" and sqxq = '" + xq + "'");
		}
		
		if(StringUtils.isNotNull(jxid)){
			sql.append(" union select t.jxid, t.jxmc,t.ksqxslx ");
			sql.append("   from xg_pjpy_jtpj_jtjxsz t ");
			sql.append("  where t.jxid= '" + jxid +"' ");
			
		}
		return dao.getArrayList(sql.toString(), new String[] {}, new String[] {
				"jxid", "jxmc","ksqxslx" });
	}

	/**
	 * 
	 * 
	 * @����: ��ȡ������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-6 ����09:30:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getSqZqList() {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select t.sqxn||','||t.sqxq dm,t.sqxn||' '||t1.xqmc mc from  xg_pjpy_jtpj_jtjxsz t ");
		sql.append(" left join xqdzb t1 on t.sqxq=t1.xqdm ");
		sql.append(" group by t.sqxn,t.sqxq,t1.xqmc ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	protected void setTableInfo() {
		setKey("jxid");
		setTableName("XG_PJPY_JTPJ_JTJXSZ");
		setClass(JtpjSzForm.class);
	}
	
	/**
	 * 
	 * @����:����������Ŀ�ж��Ƿ��ѱ�ʹ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-16 ����10:53:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String jxid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(num) num from");
		sql.append(" (");
		sql.append(" select count(1) num from xg_pjpy_jtpj_jtjxsqb t where jxid = ? ");
		sql.append(" union all");
		sql.append(" select count(1) num from xg_pjpy_jtpj_jtpjjgb t where jxid = ?");
		sql.append(" ) t");
		String numstr = dao.getOneRs(sql.toString(), new String[]{jxid,jxid}, "num");
		if("0".equals(numstr)){
			return true;
		}else{
			return false;
		}	
	}
	
	/**
	 * 
	 * @����:����������Ŀ�ж��Ƿ��ѱ�ʹ��(ɾ��)
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2016-6-20 ����02:26:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] isCheckExistForDel(String value) throws Exception{
		StringBuilder sql = new StringBuilder();
		String jxidArr = value.replaceAll(",", "','");
		sql.append(" select t2.jxmc from ");
		sql.append(" (" );
		sql.append(" select jxid from xg_pjpy_jtpj_jtjxsqb t where jxid in ('" + jxidArr + "') ");
		sql.append(" union ");
		sql.append(" select jxid from xg_pjpy_jtpj_jtpjjgb t where jxid in ('" + jxidArr + "') ");
		sql.append(" ) t ");
		sql.append(" left join ");
		sql.append(" XG_PJPY_JTPJ_JTJXSZ t2 on t.jxid = t2.jxid ");
		String[] jxmcArr = dao.getRs(sql.toString(), new String[]{ }, "jxmc");
		return jxmcArr;
	}
	
	/**
	 * 
	 * @����:����������Ŀ�����ظ����ж�
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2016-6-20 ����11:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isExistByJxmc(JtpjSzForm model) {
		
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paramValue = new ArrayList<String>();
		sql.append("select count(1) num from XG_PJPY_JTPJ_JTJXSZ where sqxn=? and sqxq=? and jxmc=? ");
		paramValue.add(model.getSqxn());
		paramValue.add(model.getSqxq());
		paramValue.add(model.getJxmc());
		
		if(StringUtils.isNotNull(model.getJxid())){
			sql.append(" and jxid<>? ");
			paramValue.add(model.getJxid());
		}
		String num=dao.getOneRs(sql.toString(), paramValue.toArray(new String[] {}), "num");
		
		return num;
	}
	
	/**
	 * @������������Ϣ�б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��2�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBbxxList(){
		String[] input = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_JTPJ_JTJXBBDMB t1 ");
		sql.append("left join XG_PJPY_JTPJ_BBDYTPB t2 on  t1.bbdm=t2.bbdm and t2.dyym='1' ");
		return dao.getListNotOut(sql.toString(), input);
	}
	
public List<HashMap<String,String>> getBbxxList(String bbdm){
		
		String[] input = new String[]{bbdm};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_JTPJ_JTJXBBDMB t1 ");
		sql.append("left join XG_PJPY_JTPJ_BBDYTPB t2 on t1.bbdm=t2.bbdm where t1.bbdm = ? order by t2.dyym ");
		
		return dao.getListNotOut(sql.toString(), input);
		
	}
	
}
