/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-20 ����03:25:03 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-20 ����03:25:03
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RwdjService extends SuperServiceImplExtend<RwdjForm, RwdjDao> {
	private RwdjDao rd = new RwdjDao();

	public RwdjService() {
		setDao(rd);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-4 ����05:33:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		return dao.checkIsNotExist(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����ѧ��ɾ�������¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-5 ����10:30:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgbyXh(String xh) throws Exception{
		return dao.delJgbyXh(xh);
	}
}
