/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.twgl.tgb;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	�� TgbService
 * @description	�� TODO(��������������)
 * @author 		��lj��1282��
 * @date		�� 2018-5-15 ����03:33:11
 * @version 	V1.0 
 */

public class TgbService extends SuperServiceImpl<TgbModel, TgbDao>{
	/**
	 * @description	�� ��ȡְ�����
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-15 ����05:26:55
	 * @return
	 */
	public List<HashMap<String,String>> getDmList(){
		return dao.getDmList();
	}
	
	/**
	 * @description	�� ��ȡ��Ϣ
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-16 ����11:44:55
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getInfo(TgbModel t){
		return dao.getInfo(t);
	}
	
	/**
	 * @description	�� �Ƿ�����Ÿɲ�
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-16 ����05:52:22
	 * @param zzids
	 * @return
	 */
	public boolean isExistTgb(String[] zzids){
		return dao.countTgb(zzids)>0;
	}
}
