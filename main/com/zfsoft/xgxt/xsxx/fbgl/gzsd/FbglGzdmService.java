/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:24:54 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:24:54
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzdmService extends
		SuperServiceImpl<FbglGzdmForm, FbglGzdmDao> {
	FbglGzdmDao dao = new FbglGzdmDao();

	public FbglGzdmService() {
		this.setDao(dao);
	}

	public List<HashMap<String, String>> getGzList() {
		return dao.getGzList();
	}
	/**
	 * 
	 * @����: ͨ����������ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����02:50:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzIds
	 * @return
	 * List<FbglTjgzForm> �������� 
	 */
	public List<HashMap<String, String>> getTjGzForGzdm(String gzdm){
		try {
			FbglGzdmForm fgf=dao.getModel(gzdm);
			FbglTjgzService fts=new FbglTjgzService();
			return fts.getTjlx(fgf.getTjgzid());
		} catch (Exception e) {
			throw new RuntimeException("��ȡ��Ӧ��������ʧ��!"+e.getMessage());
		}
	}
	public String getGzmc(String gzdm){
		return dao.getGzmc(gzdm);
	}
}
