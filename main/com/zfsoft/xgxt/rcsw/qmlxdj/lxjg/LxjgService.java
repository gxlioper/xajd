/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����09:07:29 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxjg;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����09:07:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxjgService extends SuperServiceImpl<LxjgForm, LxjgDao> {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";

	
	/**
	 * 
	 * @����: ��ȡ�鿴��Ϣmap
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����03:45:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		return dao.getCkxx(id);
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdjform
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJg(LxjgForm lxjgform,User user) throws Exception {
		lxjgform.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		String jgid = lxjgform.getJgid();
		boolean flag = true;
		if(StringUtils.isNotNull(jgid)){
			flag = dao.runUpdate(lxjgform);
			
		}else{
			flag = dao.runInsert(lxjgform);
		}
		 
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���ͨ��֮��ɾ�������֮��ԭ�к����ͨ����¼�ظ��ļ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:33:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgbyShTg(String xh,String xn,String xq) throws Exception{
		return dao.delJgbyShTg(xh, xn, xq);
	}

}
