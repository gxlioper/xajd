/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-6 ����04:50:36 
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
 * @ģ������: XXXX����ģ��
 * @�๦������: ������֤������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-5-6 ����04:50:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KnsrdCondition {

	
	/**
	 * 
	 * @����:�������϶���Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����06:44:45
	 * @�޸ļ�¼: ����-2016-11-08-����ڹ���λ��Աά���鿴��ϸ���ô˷��������������������Ի���
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdInfo(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		if("10335".equals(Base.xxdm)){
			sql.append("select count(1) rdcs from view_knsjgb_fqxrd where xh = ? ");
		}else{
			sql.append("select count(1) rdcs from xg_xszz_new_knsjgb where xh = ? ");
		}
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
		if(!"10335".equals(Base.xxdm)){
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
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rdcs");
	}
	
	
	/**
	 * 
	 * @����:�����������е��������ж�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-25 ����10:11:19
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
		sql.append("select count(1) rdcs from xg_xszz_new_knsjgb where xh = ? ");
		
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "rdcs");
	}
	
	
	/**
	 * 
	 * @����: ��ѧ���α�����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-23 ����01:45:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXscbqkInfo(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_rcsw_ylbx_jgb where xh = ? ");
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}

}
