package com.zfsoft.xgxt.comm.bdpz.service;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.comm.bdpz.dao.BdpzDao;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013�棬��̬����ز���
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-18 ����04:39:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BdpzService {

	
	private BdpzDao dao = new BdpzDao();
	
	
	/**
	 * ������Ŀѧ��������Ϣ����
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String,String>> getJbxxpz(String gnmk){
		
		if (StringUtil.isNull(gnmk)){
			throw new NullPointerException();
		}
		
		return dao.getJbxxpz(gnmk);
	}
	
	
	
}
