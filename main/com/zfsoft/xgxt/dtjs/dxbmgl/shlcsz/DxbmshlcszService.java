package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @������������У���������������service
 * @author������ ��1346��
 * @date��2017-11-1 ����09:25:37 
 */
public class DxbmshlcszService extends SuperServiceImpl<DxbmshlcszForm, DxbmshlcszDao> {
	/** 
	 * @������������ȡ��ǰ�����
	 * @author������ ��1346��
	 * @date��2017-11-3 ����10:17:53 
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String dxbmshlcszCx() throws Exception{
		return dao.dxbmshlcszCx();
	}
	/** 
	 * @��������������������   ��ɾ���
	 * @author������ ��1346��
	 * @date��2017-11-3 ����09:55:32 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean dxbmshlcszBc(DxbmshlcszForm model) throws Exception{
		if(dao.dxbmshlcszSc()){
			if(model==null){
				return false;
			}
			return dao.dxbmshlcszBc(model);
		}
		return false;
	}
}
