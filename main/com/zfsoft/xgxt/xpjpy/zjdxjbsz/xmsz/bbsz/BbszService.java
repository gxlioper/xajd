/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-4-20 ����09:15:38 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-��������-��Ŀ����-��������
 * @�๦������: �ǼǱ��ϱ�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-20 ����09:16:35 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbszService extends SuperServiceImpl<BbszForm,BbszDao>{
	private BbszDao dao = new BbszDao();
	
	public BbszService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: ��ѯ�ǼǱ������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����10:27:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bblx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		return dao.getBbxxList(bblx);
	}
	
	/**
	 * @����: ����Ԥ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����03:38:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxYlList(String bbdm){
		return dao.getBbxxYlList(bbdm);
	}
}
