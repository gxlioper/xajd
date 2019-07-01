package com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * ��������
 */
public class XmxxJcszService extends SuperServiceImpl<XmxxJcszForm, XmxxJcszDao>{
	
	private XmxxJcszDao dao = new XmxxJcszDao();
	
	public XmxxJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public XmxxJcszForm getModel(XmxxJcszForm t)throws Exception{
		return dao.getModel();
	}
	
	/**
	 * ��ѯ����������Ϣ(�޲���)
	 */
    public XmxxJcszForm getModel()throws Exception{
		return getModel(new XmxxJcszForm());
	}
	
    /**
     * �������������Ϣ
     */
	public boolean saveJcsz(XmxxJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
	}
}
