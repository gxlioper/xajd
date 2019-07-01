package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ܱ���������
 */
public class XsgzzbJcszService extends SuperServiceImpl<XsgzzbJcszForm, XsgzzbJcszDao>{
	
	private XsgzzbJcszDao dao = new XsgzzbJcszDao();
	
	public XsgzzbJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public XsgzzbJcszForm getModel(String gzzblx)throws Exception{
		return dao.getModel(gzzblx);
	}
	
    /**
     * �������������Ϣ
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean saveJcsz(XsgzzbJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			dao.setTableInfo(model);// ��̬ȡ��
			flag=dao.runInsert(model);
		}
		return flag;
	}
}
