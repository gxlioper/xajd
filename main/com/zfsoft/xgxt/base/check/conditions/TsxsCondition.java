/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-19 ����11:32:33 
 */  
package com.zfsoft.xgxt.base.check.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

import com.zfsoft.utils.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��֤����ѧ��Ⱥ��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-8-19 ����11:32:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsCondition {
	
	
	/**
	 * 
	 * @����: ����ѧ��Ⱥ�� 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-19 ����11:35:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getTsxsInfo(String xh,HashMap<String,String> condition){
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_pjpy_new_tsxsb where xh = ? ");
		
		params.add(xh);
		
		String tjz = condition.get("tjz");
		//���� ֵ ��ʽ 0,1
		if (!StringUtil.isNull(tjz)){
			sql.append(" and lxdm in (");
			
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
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "count");
	}
	
	
	
	/**
	 * 
	 * @����: Ƿ��ѧ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-10-15 ����10:02:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQfxsInfo(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_view_xszz_qfxs where xh = ? ");
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
	
	/**
	 * 
	 * @����: Ƿ��ѧ������For����ְҵѧԺ
	 * @���ߣ��´���[���ţ�1620]
	 * @���ڣ�2013-10-15 ����10:02:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQfxsInfoForGxzyxy(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_xszz_new_xsqfb where sfqf = '1' and xh = ? ");
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
	
	/**
	 * 
	 * @����: ��Դ�طſ��¼
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-8 ����09:11:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSydfkjl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_zxdk_syddk where xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
	/**
	 * 
	 * @����: У԰�طſ��¼
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-8 ����09:11:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXydfkjl(String xh,HashMap<String,String> condition){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_zxdk_xydkjgb where xh = ? ");
		
		List<String> params = new ArrayList<String>();
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
		
		return DAO.getInstance().getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
		
	}
	
}
