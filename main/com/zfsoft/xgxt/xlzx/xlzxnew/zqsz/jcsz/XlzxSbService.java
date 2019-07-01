package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XlzxSbService extends SuperServiceImpl<XlzxSbJcszForm,XlzxSbDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ñ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����05:25:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJcsz(String[]splcs,String[]lxs) throws Exception{
		dao.delJcsz();
		List<String[]> paraList = new ArrayList<String[]>();
		if(splcs != null && lxs != null){
			for (int i = 0; i < splcs.length; i++) {
				paraList.add(new String[]{splcs[i],lxs[i]});
			}
		}
		return dao.saveJcsz(paraList);
	}
	
	/**
	 * getModel��д
	 * @throws Exception 
	 */
	public XlzxSbJcszForm getModel(String lx) throws Exception{
		return dao.getModel(lx);
	}
}
