/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-13 ����01:34:36 
 */  
package xgxt.szdw.bjlh;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import xgxt.DAO.DAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼������
 * @�๦������: ˼�����鿼�˲������ó�ʼ��
 * @���ߣ� cmj
 * @ʱ�䣺 2013-12-13 ����01:34:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzkhCsszDao {
	public HashMap<String,String> getSzkhCssz(){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map=new HashMap<String, String>();
		String sql="select csmc,csz from xg_szdw_szkh_csszb";
		List<String[]> list=dao.rsToVator(sql, new String[]{}, new String[]{"csmc","csz"});
		if(list!=null&&list.size()>0){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] strs = (String[]) iterator.next();
				map.put(strs[0], strs[1]);
			}
		}
		
		
		return map;
	}

}
