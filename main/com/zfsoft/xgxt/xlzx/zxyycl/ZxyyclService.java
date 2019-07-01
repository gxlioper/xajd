/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-22 ����08:55:19 
 */  
package com.zfsoft.xgxt.xlzx.zxyycl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xlzx.yysq.YysqDao;
import com.zfsoft.xgxt.xlzx.yysq.YysqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ��ѯԤԼ���� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-8-22 ����08:55:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxyyclService extends SuperServiceImpl<ZxyyclForm, ZxyyclDao> {
	
	private ZxyyclDao dao = new ZxyyclDao();

	public ZxyyclService() {
		super.setDao(dao);
	}
	
	/** 
	 * @����:��ѯ��ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXlzxInfoByYyId(String id)
	throws Exception {
		
		return dao.getXlzxInfoByYyId(id);
	}
	
	/** 
	 * @����:ѡ����ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public boolean saveXlzxInfo(ZxyyclForm model)
	throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setId(guid);
		return dao.saveXlzxInfo(model);
	}
	/**
	 * 
	 * @����:ɾ����ѯ��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-26 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delZxInfoByYyid(String[] yyid) throws Exception {
		return dao.delZxInfoByYyid(yyid);
	}
	
	/** 
	 * @����:��ѯʦ�����Ա����ԤԼ��ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public boolean saveYyzxInfo(ZxyyclForm model) throws Exception {
		//�����ɹ���ԤԼ��Ϣ
		YysqDao yysqDao = new YysqDao();
		YysqForm yysqModel = new YysqForm();
		
		String guid = UniqID.getInstance().getUniqIDHash();
		yysqModel.setId(guid);
		yysqModel.setYyzxrq(model.getZxrq());
		yysqModel.setStatus("2");
		yysqModel.setXh(model.getXh());
		yysqModel.setZgh(model.getZgh());
		yysqModel.setXstell(model.getXstell());
		Boolean yyflag = yysqDao.saveYysqInfo(yysqModel);
		//������ѯ��Ϣ
		String newGuid = UniqID.getInstance().getUniqIDHash();
		model.setId(newGuid);
		model.setYyid(guid);
		Boolean zxflag = dao.saveXlzxInfo(model);
		return yyflag && zxflag;
	}
	/** 
	 * @����:�޸���ѯ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-9-13 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public boolean updateXlzxInfo(ZxyyclForm model)
	throws Exception {
		return dao.updateXlzxInfo(model);
	}
	
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-22 ����08:55:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɷ������

	}

}
