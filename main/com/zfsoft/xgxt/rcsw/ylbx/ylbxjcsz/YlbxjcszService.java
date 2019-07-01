package com.zfsoft.xgxt.rcsw.ylbx.ylbxjcsz;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ��ջ�������
 */
public class YlbxjcszService extends SuperServiceImpl<YlbxjcszForm, YlbxjcszDao>{
	
	private YlbxjcszDao dao = new YlbxjcszDao();
	
	public YlbxjcszService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public YlbxjcszForm getModel(YlbxjcszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * ��ѯ����������Ϣ(�޲���)
	 */
    public YlbxjcszForm getModel()throws Exception{
		return getModel(new YlbxjcszForm());
	}
	
    /**
     * �������������Ϣ
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean saveJcsz(YlbxjcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
}
