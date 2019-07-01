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
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��֤ѧ�ֽ������ҵ�� 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-10-9 ����04:32:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XfjsCondition {

	/**
	 * 
	 * @����:�㽭��ý��������-ѧ�ֽ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-9 ����05:32:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param condition
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZjcmXfjs(String xh,HashMap<String,String> condition){
		
		DAO dao = DAO.getInstance();
		List<String> params = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) xfjs from view_pjpy_xfjs_xsjljcb where wjlxdm in  ");
		sql.append(" (select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc = '����' or wjlxmc = '�ٵ�' or wjlxmc = '����') ");
		sql.append(" and qjlxdm is null and xh = ? ");
		
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
		return dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "xfjs");
	} 
	
}
