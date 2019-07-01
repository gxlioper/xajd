/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-2 ����10:36:59 
 */
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

import com.zfsoft.utils.StringUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��֤ѧ����Ϣ���ҵ��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-9-25 ����08:50:20
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsxxCondition {

	/**
	 * 
	 * @����:�ж����п����Ƿ�Ϊ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-25 ����02:37:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return String ��������
	 * @throws
	 */
	public String getYhkh(String xh, HashMap<String, String> condition) {

		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();

		sql
				.append("select count(1) yhkh from xsxxb where yhkh is null and xh = ? ");

		params.add(xh);

		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"yhkh");
	}

	/**
	 * 
	 * @����:��ȡ�������ڵ����һ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-25 ����09:16:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return String ��������
	 * @throws
	 */
	public String getSydx(String xh, HashMap<String, String> condition) {

		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xsxxb where xh = ? ");

		params.add(xh);

		String tjz = condition.get("tjz");
		// ���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)) {
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0, n = values.length; i < n; i++) {

				sql.append(" hkszd=?");
				params.add(values[i]);

				if (i != n - 1) {
					sql.append(" or ");
				}
			}
			sql.append(")");
		}

		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	public String getZyzfwxss(String xh, HashMap<String, String> condition) {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		sql
				.append("select sum(a.fwxss) fwxss from xg_sthd_hdjg a left join view_stxm_zyzhd  b on a.stid=b.stid where xh=?  ");
		params.add(xh);

		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)) {
			if (xn.contains(";")) {
				sql.append("and a.xn||';'||a.xq in ( ");
			} else {
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0, n = zqArray.length; i < n; i++) {
				sql.append("?");
				params.add(zqArray[i]);
				if (i + 1 != n) {
					sql.append(",");
				}
			}
			sql.append(")");
		}

		sql.append(" group by a.xh ");

		return DAO.getInstance().getOneRs(sql.toString(),
				params.toArray(new String[] {}), "fwxss");
	}

	/**
	 * @�������Ƿ�Ӧ���ҵ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��18��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� String ��������
	 */
	public String sfyjbys(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select (nj+xz)njxz from view_xsxxb where xh = ? ");
		params.add(xh);
		if ((Base.currXn.substring(5, 9)).equals(dao.getOneRs(sql.toString(),
				params.toArray(new String[] {}), "njxz"))) {
			return "1";
		}
		return "0";
	}

	/**
	 * @�������Ƿ�²�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��18��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� String ��������
	 */
	public String sfgc(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb t1 left join xg_xszz_new_jtqkdcb t2 on t1.xh=t2.xh where t1.xh = ? and t2.sfgc=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		params.add(tjz);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @�������Ƿ���ʿ��Ů
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��18��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� String ��������
	 */
	public String sflszn(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb t1 left join xg_xszz_new_jtqkdcb t2 on t1.xh=t2.xh where t1.xh = ? and t2.lszn=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		params.add(tjz);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @�������Ƿ��Ÿ���ͥ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��18��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� String ��������
	 */
	public String sfyfjt(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb t1 left join xg_xszz_new_jtqkdcb t2 on t1.xh=t2.xh where t1.xh = ? and t2.ylzd11=? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		params.add(tjz);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @�������Ƿ���������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��18��
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� String ��������
	 */
	public String sfssmz(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(1) num from xsxxb  where xh = ? and mz<>'01' ");
		params.add(xh);
		return dao.getOneRs(sql.toString(), params.toArray(new String[] {}),
				"num");
	}

	/**
	 * @description �� �Ƿ�೤
	 * @author �� lj��1282��
	 * @date ��2018-3-21 ����02:14:22
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String sfbz(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_szdw_xsgb_dwb a left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid where a.zzzt = '1' and b.zwmc = '�೤' and a.xh = ? ");
		params.add(xh);
		String oneRs = dao.getOneRs(sql.toString(), params.toArray(new String[] {}), "num");
		return Integer.valueOf(oneRs) > 0 ? "1" : "0";
	}
	/**
	 * @description	�� �Ƿ����ҳ�
	 * @author 		�� CP��1352��
	 * @date 		��2018-5-4 ����04:10:32
	 * @param xh
	 * @param condition
	 * @return
	 */
	public String sfqsz(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_view_gygl_new_gyglryb where gllx ='���ҳ�' and xh=?");
		params.add(xh);
		String oneRs = dao.getOneRs(sql.toString(), params.toArray(new String[] {}), "num");
		return Integer.valueOf(oneRs) > 0 ? "1" : "0";
	}
	
	/**
	 * @description	�� ����������  ����¥�������
	 * @author 		�� CP��1352��
	 * @date 		��2018-5-7 ����11:37:41
	 * @param param
	 * @param condition
	 * @return
	 */
	public String sfQsz(String param, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_view_gygl_new_gyglryb where gllx ='���ҳ�' and lddm=? and qsh=? and xh=? ");
		String [] params = param.split("@@");
		if (isExist(params[2])) {
			String oneRs = dao.getOneRs(sql.toString(),new String[] {params[0],params[1],params[2] }, "num");
			return Integer.valueOf(oneRs) > 0 ? "1" : "0";
		}else {
			return "1";
		}
	}
	
	public boolean isExist(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(1) num from xsxxb where xh = ?" ;
		String num = dao.getOneRs(sql, new String[]{xh}, "num");
		return Integer.valueOf(num)>0;
	}
	//�Ƿ��������ң�����������
	public String sfWmqs(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select count(1) wmqs from ( ");
		sql.append(" select * from xg_gygl_new_wmqsxsmdb where  xh = ? ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wmqs");
		return "0".equals(rs)?"0":"1";
	}
	//ѧ�����еȵأ�����������
	public String xscxdd(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		/*String tjz = condition.get("tjz");
		if("A".equals(tjz)) tjz = "90";
		if("B".equals(tjz)) tjz = "80";
		if("C".equals(tjz)) tjz = "70";
		if("D".equals(tjz)) tjz = "60";*/
		sql.append("select  CASE b.cxdjmc  WHEN 'A' THEN '90'  WHEN 'B' THEN '80' " +
				"  WHEN 'C' THEN '70'  WHEN 'D' THEN '60'" +
				"END cxf from xg_xsxx_cxpy_pysb_jg a left join xsxx_cxdjdmb b on a.pj=b.cxdjdm  where a.xh=? ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "cxf");
	}
	//�Ƿ��ý�ѧ������������
	public String sfHdjxj(String xh, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(0) sum from xg_pjpy_new_pjjgb a where xh=?  " );
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.xn||';'||a.xq in ( ");
			}else{
				sql.append("and a.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sum");
		return Integer.valueOf(rs) > 0 ? "1" : "0";
	}
	//�Ƿ�����༶������������
	public String sfSxbj(String pjjtid, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(0) sum from xg_pjpy_jtpj_jtpjjgb a left join XG_PJPY_JTPJ_JTJXSZ b on a.jxid=b.jxid where b.jxmc = '����༶' and a.pjjtid=?  " );
		params.add(pjjtid);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.pdxn||';'||a.pdxq in ( ");
			}else{
				sql.append("and a.pdxn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sum");
		return Integer.valueOf(rs) > 0 ? "1" : "0";
	}


	//�������ᶼ���������ң�����������
	public String sfDswmqs(String pjjtid, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(0) sum from (select xh from VIEW_XSJBXX t left join xg_pjpy_jtpj_jtpjjgb a  on a.pjjtid=t.bjdm where t.bjdm=?) a "+
					" where not EXISTS(select 1 from xg_gygl_new_wmqsxsmdb where xh= a.xh " );
		params.add(pjjtid);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq in ( ");
			}else{
				sql.append("and xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(")");
		String rs = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sum");
		return Integer.valueOf(rs) > 0 ? "0" : "1";
	}
	
	//�������ᶼ���������ң�����������
	public String bjcjnj(String pjjtid, HashMap<String, String> condition) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select pm from (select bjdm,nj,pjf,RANK() OVER(PARTITION BY nj ORDER BY pjf DESC) pm from (select b.bjdm,b.nj, round(avg(t.cj),2) pjf ");
		sql.append(" from CJB t left join view_xsjbxx a on a.xh = t.xh ");
		sql.append(" left join VIEW_NJXYZYBJ b on a.bjdm = b.bjdm where 1=1  " );
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and t.xn||';'||t.xq in ( ");
			}else{
				sql.append("and t.xn in ( ");
			}
			String[] zqArray = xn.split(",");
			for (int i = 0 , n = zqArray.length; i < n; i++){
				sql.append("?");
				params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append("  group by b.bjdm,b.nj)) where bjdm=?" );
		params.add(pjjtid);
		String pmString = dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "pm");
		String bjsSql = "select count(0) bjs from VIEW_NJXYZYBJ a where nj||zydm=(select nj||zydm from VIEW_NJXYZYBJ where bjdm = ?)";
		String bjs = dao.getOneRs(bjsSql,  new String[]{pjjtid}, "bjs");
		double doublebjs=0;
		if(!StringUtil.isNull(bjs))
		doublebjs = Integer.parseInt(bjs)/2.0;
		double doublepm=1;
		if(!StringUtil.isNull(pmString)){
			doublepm = Double.parseDouble(pmString);
			return (doublepm-doublebjs) <= 0 ? "1" : "0";
		}else{
			return "0";
		}
	}
}
