/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����09:12:58 
 */  
package com.zfsoft.xgxt.zdxljk.tbxs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: ���������--�ر����ѧ�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-2-11 ����09:11:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class TbxsService extends SuperServiceImpl<TbxsModel, TbxsDao> {


	/**����̸����¼**/
	public boolean save(TbxsModel model){
		try {
			dao.updateGzlx(model);
			dao.delThjl(model.getXh());
			List<String[]> params = new ArrayList<String[]>();
			
			String[] thsj = model.getThsjArr();
			String[] canUpdate = model.getCanUpdateArr();
			if (thsj == null || thsj.length == 0){
				return false;
			}
			
			for (int i = 0, j = thsj.length ; i < j ; i++){
				if (StringUtil.isNull(thsj[i])||"N".equals(canUpdate[i])){
					continue;
				}
				params.add(new String[]{model.getXh(),thsj[i],model.getGxlxArr()[i],
						model.getGzlx(),model.getQxyy(),model.getThnrArr()[i],model.getCljgArr()[i],model.getFtrArr()[i]});
			}
			
			return dao.saveThjl(model,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**��ѧ�Ų�ѯ̸����¼**/
	public List<HashMap<String,String>> getThjlByXh(String xh){
		
		return dao.getThjlByXh(xh);
	}
	
	
	/**������ѯ***/
	public List<HashMap<String, String>> getExportData(TbxsModel t, User user)
		throws Exception {
		
		return dao.getExportData(t, user);
	}
	
	
	/**�������ù�ע*/
	public boolean saveSzgz(TbxsModel t,String ids) throws Exception{
		
		return dao.saveSzgz(t, ids.split(","));
	}
	
	public List<HashMap<String,String>> getQxgzList(String xh){
		
		return dao.getQxgzList(xh);
	}
	
	
}
