/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.twgl.tzz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	�� TzzService
 * @description	�� TODO(��������������)
 * @author 		��lj��1282��
 * @date		�� 2018-5-14 ����02:45:44
 * @version 	V1.0 
 */

public class TzzService extends SuperServiceImpl<TzzModel, TzzDao>{
	TzzDao dao = new TzzDao();
	
	/**
	 * @description	�� �Ƿ����ͬ��
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-15 ����10:12:09
	 * @param model
	 * @return
	 */
	public boolean isMcExist(TzzModel model){
		return dao.countMc(model) > 0;
	}
}
