/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-12 ����10:02:35 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-12 ����10:02:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglService extends  SuperServiceImpl<XsxxKzxxglForm, XsxxKzxxglDAO>{

	
	/**
	 * 
	 * @����: ��ȡѧ����չ��Ϣ,�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-19 ����08:51:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm �������� 
	 * @throws
	 */
	public XsxxKzxxglForm getJgxxByXh(String xh) throws Exception{
		if(StringUtils.isNotBlank(xh)){
			return dao.getXskzxxByXh(xh);
		}
		return null;
	}
	/**
	 * 
	 * @����: ����ѧ�Ż�ȡ��չ��Ϣ�����¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-17 ����11:10:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<XsxxKzxxglForm> �������� 
	 * @throws
	 */
	public List<XsxxKzxxglForm> getSqListByXh(String xh) throws Exception{
		if(StringUtils.isNotBlank(xh)){
			return dao.getModleList(xh);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ����ѧ�Ż�ȡ��������ļ�¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-18 ����06:35:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm �������� 
	 * @throws
	 */
	public XsxxKzxxglForm getLatestSqByXh(String xh) throws Exception{
		if(StringUtils.isBlank(xh)){
			return null;
		}
		return dao.getLatestModel(xh);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ��ǰѧ����Ч�������¼
	 *  //1.�����ѧ��û���������ݣ�ѧ���������������� ���� NULL
		//2.�����ѧ���Ѿ���������������� ѧ�������ٴ�����������,�������δ��һ����ˣ����Գ��� ���� ���������¼
		//3.�����ѧ���������Ѿ������� ѧ���������������� ���ظ��������¼
		//4.���ѧ�������ݻ�δ�ύ��ѧ�������޸�֮ǰ������ ���ظ�����¼
		//5.���û�������ݲ�ͨ����ˣ�ѧ����Ҫ�������룬�����ظ�����ͨ���ļ�¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-17 ����03:15:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * XsxxKzxxglForm �������� 
	 * @throws
	 */
	public XsxxKzxxglForm getOneSqListByXh(String xh) throws Exception{
		return getLatestSqByXh(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����ѧ�Ż�ȡ��ѧ���������¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-12 ����10:27:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<XsxxKzxxglForm> �������� 
	 * @throws
	 */
	public List<XsxxKzxxglForm> getModleListByXh(String xh) throws Exception{
		if(StringUtils.isBlank(xh)){
			return null;
		}
		List<XsxxKzxxglForm> modleList = dao.getModleList(xh);
		return modleList;
	}
	
	/**
	 * 
	 * @����: ����ѧ���Ƿ���������е������¼����
	 *        ����з��ظ�����ͣ�û�л������������������NULL
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-12 ����10:37:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm �������� 
	 * @throws
	 */
	public XsxxKzxxglForm checkSQ(String xh) throws Exception{
		List<XsxxKzxxglForm> modleListByXh = getModleListByXh(xh);
		if(modleListByXh == null || modleListByXh.size() == 0){
			return null;
		}
		for (XsxxKzxxglForm xsxxKzxxglForm : modleListByXh) {
			String shzt = xsxxKzxxglForm.getShzt();
			if(StringUtils.equals(shzt, Constants.YW_SHZ) || 
					StringUtils.equals(shzt, Constants.YW_WTJ) || 
					StringUtils.equals(shzt, Constants.YW_YTH)){
				return xsxxKzxxglForm;
			}
		}
		return null;
	}
	
}
