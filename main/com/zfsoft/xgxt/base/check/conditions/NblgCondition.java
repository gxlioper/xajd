/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-19 ����09:24:57 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ��������������
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-9-19 ����09:24:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class NblgCondition {
	
	/**
	 * 
	 * @����:��У�ڼ俼�Գɼ��޲������Ŀ��������ѡ�Σ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-19 ����10:02:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjgs(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bjgs from view_zhhcjb where cj < 60 and xh = ? and kcmc not like '%(ѡ��)%' ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "bjgs");
	}
	
	
	/**
	 * 
	 * @����:���������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-9-19 ����12:16:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPjxmlx(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from (select xh,lxdm,count(1) count from xg_pjpy_new_pjjgb where 1=1 ");

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
		
		sql.append(" group by lxdm,xh) where xh=? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");

		if (!StringUtil.isNull(tjz)){
			String[] values = tjz.split(",");
			
			sql.append(" and lxdm = ? ");
			params.add(values[0]);
			
			sql.append(" and count >= ? ");
			params.add(values[1]);

		}

		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
	}
	
}
