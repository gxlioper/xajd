/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.bbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class BbwhService extends SuperServiceImpl<BbwhForm, BbwhDao> {

	private BbwhDao dao = new BbwhDao();

	public BbwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��ȡ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBb() throws Exception {
		List<HashMap<String, String>> list = dao.getAll();
		return list;
	}

	/**
	 * 
	 * @����:���ݴ����ѯ������¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm) throws Exception {
		if (StringUtil.isNull(bbdm)) {
			logger.error("�������Ϊ�գ�");
			return null;
		}
		return dao.getDataById(bbdm);
	}

	
	/**
	 * 
	 * @����: ��ѯ�ǼǱ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-5 ����02:11:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxLists(String bblx){
		
		return dao.getBbxxLists(bblx);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-5 ����03:51:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBbxxList(String bbdm) {
		return dao.getBbxxList(bbdm);
	}
}
