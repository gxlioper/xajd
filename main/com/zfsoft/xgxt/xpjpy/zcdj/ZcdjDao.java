/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-25 ����02:38:27 
 */  
package com.zfsoft.xgxt.xpjpy.zcdj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy[����:754]
 * @ʱ�䣺 2013-9-25 ����02:38:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcdjDao {
	protected DAO dao = DAO.getInstance();
	
	/**
	 * 
	 * @����:TODO��ȡ�۲�ȼ�����ֵ�б�
	 * @���ߣ�xucy[���ţ�754]
	 * @���ڣ�2013-9-25 ����05:02:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcdjList() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct zcdj ");
		sb.append(" from xg_zhcp_zcfsb ");
		sb.append(" where 1=1 and zcdj is not null order by zcdj");
			
		return dao.getListNotOut(sb.toString(), null);
	}
	
	//��ȡ���е��꼶
	public List<HashMap<String, String>> getViewNjList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct nj dm ,nj mc from view_njxyzybj order by  nj");
	    return dao.getListNotOut(sql.toString(), new String[]{});
	}
}