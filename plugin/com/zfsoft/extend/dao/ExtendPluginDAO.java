/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-23 ����11:56:16 
 */  
package com.zfsoft.extend.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-23 ����11:56:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendPluginDAO {

	private static String QUERY_SH = "select distinct sydqdm dm,sydq mc from dmk_sydq ORDER BY sydqdm";
	
	private static String QUERY_QX = "select distinct qxdm dm,qxmc mc from dmk_qx order by qxdm";
	
	private static DAO dao = DAO.getInstance();
	
	public static List<HashMap<String, String>> SH = dao.getListNotOut(QUERY_SH, new String[]{});
	
	public static List<HashMap<String, String>> QX = dao.getListNotOut(QUERY_QX, new String[]{});
}
