/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.ybgzz.cywh;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @�๦������: �װ��Աά�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-1-30 ����01:56:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YbcyService extends SuperServiceImpl<YbcyModel, YbcyDao> {

	
	/**�����˳� **/
	public boolean batchExit(String[] idArr,YbcyModel model){
		
		try {
			return dao.batchExit(idArr, model);
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	public YbcyModel getExitInfo(String id) throws Exception{
		
		return dao.getExitInfo(id);
	}
	
	/**��ѧ�Ų�ѯ�Ƿ�����װ๤��վ*/
	public boolean isExists(String xh) throws Exception{
		
		String count = dao.getExistsByXh(xh);
		
		return Integer.valueOf(count) > 0;
	}
	
	/**��ѧ�Ų�ѯ�Ƿ��������װ๤��վ��Ա*/
	public boolean isSqExists(String xh) throws Exception{
		
		String count = dao.getSqExistsByXh(xh);
		
		return Integer.valueOf(count) > 0;
	}
	
}
