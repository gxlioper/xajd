/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������Ŀά��
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-2 ����04:31:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XsxwJcxmwhService extends SuperServiceImpl<XsxwJcxmwhForm, XsxwJcxmwhDao>  {

	private XsxwJcxmwhDao dao = new XsxwJcxmwhDao();
	/**
	 * 
	 * @����:������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-8-2 ����05:01:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCfxmPageList(XsxwJcxmwhForm model) throws Exception{
		return dao.getCfxmPageList(model);
	}
	

}
