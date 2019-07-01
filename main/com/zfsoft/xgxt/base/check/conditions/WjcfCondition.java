/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-2 ����10:36:59 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��֤Υ�����ҵ��
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-2 ����10:36:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WjcfCondition {

	/**
	 * 
	 * @����: ��Υ�����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-2 ����10:42:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getWjqk(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) wjcs from xg_wjcf_wjcfb where nvl(ssjg,0) <> '��������' and nvl(ssjg,0) <> '��������' ");
		sql.append("and jcwh is null and xh = ? ");
		
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
	 * 
	 * @����:��ԢΥ������������Ƽ�ʦ����ѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-12 ����10:44:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getGywjqk(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		String xn = condition.get("xn");
		String tjdm = condition.get("tjdm");
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) gywjqk from xg_gygl_new_gyjlb a left join XG_GYGL_NEW_GYJLLBDLB b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm = c.gyjllbdm where xh = ? and shzt = 'tg' ");
		params.add(xh);
		//����ְҵ���Ƿ���"���Ҳ�"��ԢΥ�����
		if(tjdm.equals("pjtj_gygl_12874_zlcwjqk")){ 
			sql.append(" and c.gyjllbmc like '%���Ҳ�%' ");
		}
		
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "gywjqk");
	} 
	/**
	 * �Ƿ���Υ�������������ũ��ְҵѧԺ��
	 */
	public String getWjqkHljnk(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		// ============== 2013-2014��Υ�� begin =============
		sql.append(" select count(1) wjcs from ( ");
		sql.append(" select * from xg_wjcf_wjcfb where nvl(ssjg,0) <> '��������' and nvl(ssjg,0) <> '��������' and jcwh is null and xh = ? ");
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
		// ============== 2013-2014��Υ�� end =============
		sql.append(" union all ");
		// ============== 2013��9��1����2014��7��30���ڽ�� begin =============
		sql.append(" select * from xg_wjcf_wjcfb where (nvl(ssjg,0) = '��������' or nvl(ssjg,0) = '��������' or jcwh is not null) and xh = ? ");
		params.add(xh);
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and xn||';'||xq = ? ");
			}else{
				sql.append("and xn = ? ");
			}
			params.add(xn);
			
			String[] xnArr = xn.split(";")[0].split("-");
			String kssj = xnArr[0] + "-09-01";
			String jssj = xnArr[1] + "-07-30";
			sql.append(" and jcsj >= ? and jcsj <= ? ");
			params.add(kssj);
			params.add(jssj);
		}
		// ============== 2013��9��1����2014��7��30���ڽ�� end =============
		sql.append(" ) ");
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "wjcs");
	} 
	
public String getGyWsf(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjg from xg_gygl_new_wsjc_qsfsb where (to_number(fs)<60  ");
		sql.append(" or dj='������') and xh = ? ");
		
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjg");
	}


	/**
	 * 
	 * @����: Υ���¼�������ѧ���пƼ�ѧԺ���Ի���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-21 ����02:37:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getWjqkCqcskjxy(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) wjcs from xg_wjcf_wjcfb where ");
		sql.append(" xh = ? ");
		
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
	 * @������"���Ҳ�"��ԢΥ�ʹ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��8�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param wjsj
	 * @return
	 * String ��������
	 */
	public String getZlcwjqk(String xh,HashMap<String,String> condition) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_new_gyjlb a left join XG_GYGL_NEW_GYJLLBDLB b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm = c.gyjllbdm where xh = ? and shzt = 'tg' and c.gyjllbmc like '%���Ҳ�%'");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @��������У�鿴���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getLxckqk(String xh,HashMap<String,String> condition) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_wjcf_wjcfb where cflbmc like '%��У�쿴%' and nvl(ssjg, 0) <> '��������' and nvl(ssjg, 0) <> '��������' ");
		sql.append(" and sysdate >= to_date(cfsj, 'yyyy-MM-dd') and sysdate <= to_date(nvl(jcsj,'2099-01-01'), 'yyyy-MM-dd') and xh = ?");
		params.add(xh);
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @��������ԢΥ�Ϳ۷� �����ϳ��и��Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String gywjkf_11527(String xh,HashMap<String,String> condition){
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(sum(gyjllbkf),0) kf from XG_GYGL_NEW_GYJLB t1 left join XG_GYGL_NEW_GYJLLBDMB t2 on t1.gyjllbdm=t2.gyjllbdm where xh=? and shzt = 'tg' ");
		params.add(xh);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "kf");
	}
	
	/**
	 * @����������Υ�ʹ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��1�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * String ��������
	 */
	public String qswjcs(String pjjtid,HashMap<String,String> condition){
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select count(1) num from  xg_gygl_new_gyjlb where lddm = ? and qsh=? and shzt = 'tg' ");
    	String []pjjtids =pjjtid.split("@@");
    	params.add(pjjtids[0]);
    	params.add(pjjtids[1]);
		String xn = condition.get("xn");
		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and wjxn||';'||wjxq in ( ");
			}else{
				sql.append("and wjxn in ( ");
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
    	return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}) ,"num");
    }
	/**
	 * 
	 * @����:���������༶��ԢΥ������������Ƽ�ʦ����ѧ��
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-9-6
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjGywjqk(String bjdm,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		String xn = condition.get("xn");
    	List<String> params = new ArrayList<String>();
    	StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) bjgywjqk from xg_gygl_new_gyjlb a ");
		sql.append(" left join XG_GYGL_NEW_GYJLLBDLB b on a.gyjllbdldm = b.gyjllbdldm ");
		sql.append(" left join xg_gygl_new_gyjllbdmb c on a.gyjllbdm = c.gyjllbdm ");
		sql.append(" where a.shzt = 'tg' and a.xh in (select xh from view_xsjbxx where bjdm = ?) ");
		params.add(bjdm);

		if (!StringUtil.isNull(xn)){
			if(xn.contains(";")){
				sql.append("and a.wjxn||';'||a.wjxq in ( ");
			}else{
				sql.append("and a.wjxn in ( ");
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgywjqk");
	} 
	
	/**
	 * @description	������ѡ���Υ�����ͳ�ư༶���Ƿ���ѧ��Υ��
	 * @author 		�� ������1282��
	 * @date 		��2017-12-1 ����09:57:48
	 * @param bjdm
	 * @param condition
	 * @return
	 */
	public String getWjcsByXmmc(String bjdm,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) bjwjcs from xg_view_wjcf_wjcfb where (ssjg <> '��������' or ssjg is null)  and xh in (select xh from view_xsjbxx where bjdm = ?) ");
		
		params.add(bjdm);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" trim(cflbmc) = ? ");
				params.add(values[i]);
					
				if(i != n-1){
					sql.append(" or ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjwjcs");
	}
}
