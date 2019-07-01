/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-26 ����03:33:00 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import com.zfsoft.utils.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: TODO(�㽭��ѧ���Ի���_�������϶�) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-26 ����03:33:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZJDXKnsrdCondition {
	/**
	 * @����:TODO(�����������е��������ж�)
	 * @���ߣ�MengWei[���ţ�1186]
	 * @���ڣ�2016-7-26 ����03:49:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZzKnsrdInfo(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) rdcs from view_knsjgb_fqxrd where xh = ? ");
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and rddc in (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append("?");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rdcs");
	}
	
	/**
	 * @����: �㽭��ѧ���Ի���������Ŀ�����������Ͻ�ֽ�ʲ��ϵ������������������κο��ƣ�ֻ����ѧ��������Ҫ�Ͻ����ϵ���Ŀ������ʾ����
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-11-17 ����11:12:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZzZzclInfo(String xh,HashMap<String,String> condition){
		return "1";
	}
	
	/**
	 * @����: ������־��ѧ����ѧ��ƽ���������3.5�֣��Ǻ���ѧ��ƽ���������2.5
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-27 ����11:38:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getGjlzjxjTjhzxs(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*)hzxsjd from ( ");
		sql.append(" select zjdx.func_zgqbkcpjjd(?,?)jd,mz from xsxxb where xh = ?) where (jd > 3.5 and mz = '01') or (jd > 2.5 and mz <> '01') ");
		String xn1 = condition.get("xn");
		if (StringUtil.isNull(xn1)){
			xn1 = Base.currXn;
		}
		params.add(xh);
		params.add(xn1);
		params.add(xh);
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "hzxsjd");
	}
	
	/**
	 * @����: ˼���������ʴﵽ�ϸ�����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-3 ����10:34:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSxzzsz(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)sxzzszCs from ( ");
		sql.append("select a.*, b.xmmc, c.pjdjmc from xg_zjdx_pjpy_zcfsb a ");
		sql.append("left join view_xg_zjdx_pjpy_cssz b on a.xmdm = b.xmdm ");
		sql.append("left join xg_pjpy_new_pjdj c on b.xmmc = c.pjxmmc and a.fs = c.pjdjdm  where b.xmmc = '˼���������ʵȼ�����') ");
		sql.append("where xh = ? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and fs in (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				sql.append("?");
				params.add(values[i]);
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "sxzzszCs");
	}
	
	/**
	 * @����: ���ʽ����ﵽ�ϸ�����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-3 ����04:51:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTzjk(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)tzjkCs from ( ");
		sql.append("select a.*, b.xmmc, c.pjdjmc from xg_zjdx_pjpy_zcfsb a ");
		sql.append("left join view_xg_zjdx_pjpy_cssz b on a.xmdm = b.xmdm ");
		sql.append("left join xg_pjpy_new_pjdj c on b.xmmc = c.pjxmmc and a.fs = c.pjdjdm  where b.xmmc = '���ʽ����ȼ�����') ");
		sql.append("where xh = ? ");
		params.add(xh);
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and fs in (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				sql.append("?");
				params.add(values[i]);
				if(i != n-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "tzjkCs");
	}
	
	/**
	 * @����: �Ƿ�Υ��У��У��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-3 ����05:45:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXjxg(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		/*Υ�ͱ��ز��Ա�*/
		sql.append("select count(1) wjcs from zjdx.xscfb where xh = ? ");
		/*Υ����ʽ���ñ�*/
		//sql.append("select count(1) wjcs from xg_wjcf_wjcfb where xh = ? ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wjcs");
	}
	
	/**
	 * @����: �����ƺŻ�ô���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-3 ����08:17:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getRyhdcs(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)ryhdcs from ( ");
		sql.append("select a.xh,a.xn,a.xmmc from xg_zjdx_pjpy_pjjgb a ");
		sql.append("left join xg_zjdx_pjpy_xmlx b on a.lxdm = b.lxdm ");
		sql.append("where b.lxmc = '�����ƺ�' ");
		sql.append(") where 1= 1 ");
		
		List<String> params = new ArrayList<String>();
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
		
		sql.append(" and xh = ? ");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "ryhdcs");
	}
	
	/**
	 * @����: �Ƿ��ѻ������ѧ�������ƺ�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-6 ����04:10:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYxxs(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append("select count(*)yxxsCs from ( ");
		sql.append("select * from xg_zjdx_pjpy_pjjgb where xmmc = '����ѧ��' and xh = ? ");
		params.add(xh);
		sql.append(") where 1 = 1 ");
		
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "yxxsCs");
	}
	
	/**
	 * @����: ѧҵ����ǰ�ٷ�֮����(ǰ������ʦ��������������������������ܲ���)
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-6 ����05:59:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXypm(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String xn = condition.get("xn");
		sql.append("select count(*) xybfb ");
		sql.append("from (select * ");
		sql.append("from (select a.xh, a.xn, a.fs, a.xmdm, b.xmmc, d.xymc ");
		sql.append("from xg_zjdx_pjpy_zcfsb a ");
		sql.append("left join view_xg_zjdx_pjpy_cssz b on a.xmdm = b.xmdm and a.xn = b.xn ");
		sql.append("left join xg_zjdx_pjpy_cpmdb c on a.xh = c.xh and a.xn = b.xn ");
		sql.append("left join view_njxyzybj_all d on c.bjdm = d.bjdm  ");
		sql.append("where b.xmmc = 'ѧҵ����' order by d.xymc, to_number(a.fs)) e ");
		sql.append("where e.xymc = (select g.xymc from xg_zjdx_pjpy_cpmdb f ");
		sql.append("left join view_njxyzybj_all g on f.bjdm = g.bjdm ");
		sql.append("where f.xh = ? ");
		params.add(xh);
		sql.append("and f.xn = ? ");
		params.add(xn);
		sql.append(" ) ");
		sql.append("and rownum <= (select count(*) zrs from xg_zjdx_pjpy_zcfsb t1  ");
		sql.append("left join view_xg_zjdx_pjpy_cssz t2 on t1.xmdm = t2.xmdm and t1.xn = t2.xn  ");
		sql.append("left join xg_zjdx_pjpy_cpmdb t3 on t1.xh = t3.xh and t1.xn = t3.xn ");
		sql.append("left join view_njxyzybj_all t4 on t3.bjdm = t4.bjdm  ");
		sql.append("where t2.xmmc = 'ѧҵ����' and t4.xymc = (select t6.xymc from xg_zjdx_pjpy_cpmdb t5 ");
		sql.append("left join view_njxyzybj_all t6 on t5.bjdm = t6.bjdm ");
		sql.append("where t5.xh = ? ");
		params.add(xh);
		sql.append("and t5.xn = ? ");
		params.add(xn);
		sql.append(" ) ) * ");
		String tjz = condition.get("tjz");
		sql.append(" (select  ");
		sql.append(tjz);
		sql.append(" / 100 from dual)) ");
		sql.append("where 1 = 1 and xh = ? ");
		params.add(xh);
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "xybfb");
	}
	
	/**
	 * @����: �Ƿ��ù��㽭��ѧһ���������Ƚ�ѧ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-7 ����11:38:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYesdjxj(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)yesdjxjCs from ( ");
		sql.append("select * from xg_zjdx_pjpy_pjjgb where xmmc in ('�㽭��ѧһ�Ƚ�ѧ��','�㽭��ѧ���Ƚ�ѧ��','�㽭��ѧ���Ƚ�ѧ��') ");
		sql.append(") where 1= 1 ");
		
		List<String> params = new ArrayList<String>();
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
		
		sql.append(" and xh = ? ");
		params.add(xh); 
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "yesdjxjCs");
	}
	
	/**
	 * @����: δ���רҵ��ѧ�����㽱��ѧ�����Ի��רҵ��ѧ����ͨ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-7 ����03:43:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZyjxjptj(String xh,HashMap<String,String> condition){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)zyjxjptj from ( ");
		sql.append("select * from xg_zjdx_pjpy_pjjgb where xmmc = 'רҵ��ѧ�����㽱' ");
		sql.append(") where 1= 1 ");
		
		List<String> params = new ArrayList<String>();
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
		
		sql.append(" and xh = ? ");
		params.add(xh); 
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "zyjxjptj");
	}
}
