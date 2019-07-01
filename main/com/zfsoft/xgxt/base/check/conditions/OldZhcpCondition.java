/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-11 ����02:52:03 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.newp.StringUtil;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: N35��������Ӧ���ۺϲ����������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-11 ����02:52:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class OldZhcpCondition {

	private static String PJXN = null;
	private static String PJXQ = null;
	private static String PJZQ = null;
	
	public OldZhcpCondition() {
		DAO dao = DAO.getInstance();
		
		String sql = "select * from xg_pjpy_xtszb";
		
		HashMap<String,String> pjsz = dao.getMapNotOut(sql, new String[]{});
		
		PJXN = pjsz.get("pjxn");
		PJXQ = pjsz.get("pjxq");
		PJZQ = pjsz.get("pjzq");
	}
	
	
	
	
	/**
	 * 
	 * @����: �۲⣨�༶������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-11 ����02:54:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getZcpm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		if (PJZQ.equals("xn")){
			
			if (StringUtil.isNull(xn)){
				sql.append("select zcfbjpm from xg_pjpy_zhcpb where xh=? and xn=? ");
				params.add(PJXN);
			} else {
				sql.append("select max(zcfbjpm) zcfbjpm from xg_pjpy_zhcpb where xh=? and xn in (");
				
				String[] zqArray = xn.split(",");
				
				for (int i = 0 , n = zqArray.length; i < n; i++){
					sql.append("?");
					params.add(zqArray[i].split(";")[0]);
					if (i+1 != n){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			
			
		} else {
			
			if (StringUtil.isNull(xn)){
				sql.append("select zcfbjpm from xg_pjpy_zhcpb where xh=? and xn=? and xq=? ");
				params.add(PJXN);
				params.add(PJXQ);
			} else {
				sql.append("select  max(zcfbjpm) zcfbjpm from xg_pjpy_zhcpb where xh=? and xn||';'||xq in (");
				
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
			
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "zcfbjpm");
	}
	
	
	/**
	 * 
	 * @����: �������༶������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-11 ����02:54:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getDypm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		if (PJZQ.equals("xn")){
			
			if (StringUtil.isNull(xn)){
				sql.append("select dyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? ");
				params.add(PJXN);
			} else {
				sql.append("select max(dyfbjpm) dyfbjpm from xg_pjpy_zhcpb where xh=? and xn in (");
				
				String[] zqArray = xn.split(",");
				
				for (int i = 0 , n = zqArray.length; i < n; i++){
					sql.append("?");
					params.add(zqArray[i].split(";")[0]);
					if (i+1 != n){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			
			
		} else {
			
			if (StringUtil.isNull(xn)){
				sql.append("select dyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? and xq=? ");
				params.add(PJXN);
				params.add(PJXQ);
			} else {
				sql.append("select  max(dyfbjpm) dyfbjpm from xg_pjpy_zhcpb where xh=? and xn||';'||xq in (");
				
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
			
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "dyfbjpm");
	}
	
	
	
	/**
	 * 
	 * @����: �������༶������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-11 ����02:54:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getZypm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xn = condition.get("xn");
		List<String> params = new ArrayList<String>();
		params.add(xh);
		
		if (PJZQ.equals("xn")){
			
			if (StringUtil.isNull(xn)){
				sql.append("select zyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? ");
				params.add(PJXN);
			} else {
				sql.append("select max(zyfbjpm) zyfbjpm from xg_pjpy_zhcpb where xh=? and xn in (");
				
				String[] zqArray = xn.split(",");
				
				for (int i = 0 , n = zqArray.length; i < n; i++){
					sql.append("?");
					params.add(zqArray[i].split(";")[0]);
					if (i+1 != n){
						sql.append(",");
					}
				}
				sql.append(")");
			}
			
			
		} else {
			
			if (StringUtil.isNull(xn)){
				sql.append("select zyfbjpm from xg_pjpy_zhcpb where xh=? and xn=? and xq=? ");
				params.add(PJXN);
				params.add(PJXQ);
			} else {
				sql.append("select  max(zyfbjpm) zyfbjpm from xg_pjpy_zhcpb where xh=? and xn||';'||xq in (");
				
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
			
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "zyfbjpm");
	}
	
	
	/**
	 * 
	 * @����: ������Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-26 ����02:59:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjxm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_pjpy_new_pjjgb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" trim(xmmc) = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	/**
	 * @������������Ŀ�����ϵ��ͬʱ��ö�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��5�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String ��������
	 */
	public String getPjxmAnd(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
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
				//params.add(zqArray[i]);
				if (i+1 != n){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		String xnxq=sql.toString();
		
		sql = new StringBuilder();
		sql.append("select 1 ");
		
		String tjz = condition.get("tjz");
		if (StringUtils.isNotNull(tjz)){
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				sql.append(" *( select count(1) num from xg_pjpy_new_pjjgb where xh = ? ");
				params.add(xh);
				sql.append(" and trim(xmmc) = ? ");
				params.add(values[i]);
				sql.append(xnxq);
				sql.append(" ) ");
				if (!StringUtil.isNull(xn)){
					String[] zqArray = xn.split(",");
					for (int j = 0 , m = zqArray.length; j < m; j++){
						params.add(zqArray[j]);
					}
				}
			}
		}
		sql.append(" num from dual ");
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
	
	/*
	 * ������Ŀ
	 */
	public String getZzxm(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_xszz_new_zzxmjgb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			sql.append(" and (");
			String[] values = tjz.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				
				sql.append(" trim(xmmc) = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
}
