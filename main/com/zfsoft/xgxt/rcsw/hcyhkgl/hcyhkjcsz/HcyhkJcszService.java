package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Żݿ������������ģ��
 * @�๦������: TODO(���Żݿ������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����02:55:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcyhkJcszService extends SuperServiceImpl<HcyhkJcszForm, HcyhkJcszDao>{
	
	private HcyhkJcszDao dao = new HcyhkJcszDao();
	
	public HcyhkJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public HcyhkJcszForm getModel(HcyhkJcszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 
	 * @����:TODO(��ѯ����������Ϣ(�޲���))
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:46:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HcyhkJcszForm �������� 
	 * @throws
	 */
    public HcyhkJcszForm getModel()throws Exception{
		
		return getModel(new HcyhkJcszForm());
	}
	
    /**
     * 
     * @����:TODO(�������������Ϣ)
     * @���ߣ�Dlq[���ţ�995]
     * @���ڣ�2013-12-26 ����09:47:07
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean saveJcsz(HcyhkJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
			
		}
		return flag;
		
	}
}
