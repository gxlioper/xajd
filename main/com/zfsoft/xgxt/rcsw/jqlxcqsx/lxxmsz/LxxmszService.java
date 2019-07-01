/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:22:25 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

import java.util.ArrayList;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:��У��Ŀ����
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:22:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxxmszService extends SuperServiceImpl<LxxmszForm, LxxmszDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����: ������Ŀ���ý��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:16:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSzjg(LxxmszForm model) throws Exception{
		boolean rs = true;
		if("save".equals(model.getType())){
			rs = dao.runInsert(model);
		}else if("update".equals(model.getType())){
			rs = dao.runUpdate(model);
		}else{
			return false;
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ��Ŀ�����Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:36:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String xmid,String xmmc){
	    return dao.checkIsNotExist(xmid, xmmc);
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ��ɾ�����ж����ݣ�xg_cqsx_jqlx_mdwh���Ƿ����õ���ɾ������Ŀ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean ifCanDel(String[] xmids){
		return dao.ifCanDel(xmids);
	}
}
