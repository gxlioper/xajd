package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ��ҽ�Ʊ��ղ�������ģ��
 * @�๦������: TODO(��ѧ��ҽ�Ʊ��ջ�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-6 ����10:18:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxJcszService extends SuperServiceImpl<YlbxJcszForm, YlbxJcszDao>{
	
	private YlbxJcszDao dao = new YlbxJcszDao();
	
	public YlbxJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public YlbxJcszForm getModel(YlbxJcszForm t)throws Exception{
		
		return dao.getModel();
		
	}
	
	/**
	 * 
	 * @����:TODO(��ѯ����������Ϣ(�޲���))
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����10:18:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * YlbxJcszForm �������� 
	 * @throws
	 */
    public YlbxJcszForm getModel()throws Exception{
		
		return getModel(new YlbxJcszForm());
	}
	
    /**
     * 
     * @����:TODO(�������������Ϣ)
     * @���ߣ�Dlq[���ţ�995]
     * @���ڣ�2014-1-6 ����10:18:14
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean saveJcsz(YlbxJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
		
	}
}
