/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-22 ����10:09:25 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-רҵ������-����רҵ��ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2015-12-22 ����10:09:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GrzyjwhService extends SuperServiceImpl<GrzyjwhForm, GrzyjwhDao> {
	private GrzyjwhDao dao = new GrzyjwhDao();
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public GrzyjwhService() {
		super.setDao(dao);
	}
	
	//���ӱ���
	public boolean saveGrzyj(GrzyjwhForm model) throws Exception {
		boolean insertResult = dao.runInsert(model);
		return insertResult;
	}
	
	//�޸ı���
	public boolean updateGrzyj(GrzyjwhForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	//ɾ��
	public int delGrzyj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	//�õ������ȼ�
	public List<HashMap<String, String>>getRddjList() {
		return dao.getRddjList();
	}
	
}
