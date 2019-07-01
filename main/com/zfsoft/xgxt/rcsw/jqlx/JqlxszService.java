/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����03:55:28 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ������У����
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-30 ����03:55:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JqlxszService extends SuperServiceImpl<JqlxszModel, JqlxszDao> {

	private JqlxszDao dao = new JqlxszDao();

	public JqlxszService() {
		super.setDao(dao);
	}

	/**
	 * ��ѯ����������Ϣ
	 */
	public JqlxszModel getModel(JqlxszModel t) throws Exception {

		return dao.getModel();
	}

	/**
	 * 
	 * @����:�õ�ʵ��
	 * @���ߣ�945
	 * @���ڣ�2013-12-26 ����09:20:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             XszbbJcszForm ��������
	 * @throws
	 */
	public JqlxszModel getModel() throws Exception {
		return getModel(new JqlxszModel());
	}
	
	/**
	 * ��ѯ����������Ϣ�����롢�����ר�á���
	 */
	public JqlxszModel getJqlxszModelSqJg() throws Exception {
		JqlxszModel jqlxszModel = dao.getModel();
		if(jqlxszModel == null){
			jqlxszModel = new JqlxszModel();
		}
		String lxkssj = jqlxszModel.getLxkssj();
		String lxjssj = jqlxszModel.getLxjssj();
		if(!StringUtil.isNull(lxkssj) && !lxkssj.contains("-")){
			lxkssj = lxkssj.substring(0, 4) + "-" + lxkssj.substring(4, 6) + "-" + lxkssj.substring(6, 8);
		}
		if(!StringUtil.isNull(lxjssj) && !lxjssj.contains("-")){
			lxjssj = lxjssj.substring(0, 4) + "-" + lxjssj.substring(4, 6) + "-" + lxjssj.substring(6, 8);
		}
		jqlxszModel.setLxkssj(lxkssj);
		jqlxszModel.setLxjssj(lxjssj);
		return jqlxszModel;
	}

	/**
	 * 
	 * @����:�������������Ϣ
	 * @���ߣ�945
	 * @���ڣ�2013-12-26 ����09:21:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveJcsz(JqlxszModel model) throws Exception {
		boolean flag = false;
		flag = dao.deleteJqlxsz(model);
		if (flag) {
			flag = dao.runInsert(model);
		}
		return flag;
	}

}
