/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����10:25:55 
 */  
package xsgzgl.gyjc.jcsd;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��22�� ����10:25:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxxmService extends SuperServiceImpl<CxxmForm, CxxmDao> {
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����06:04:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCxxm(CxxmForm t) throws Exception{
		//���ж����ֱ�׼�Ƿ��ظ�
		boolean rs = dao.checkRepeatDM(t);//�����Ƿ��ظ�
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_REPEAT);
		}
		rs = dao.checkRepeatMC(t);//�����Ƿ��ظ�
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_XH_REPEAT);
		}
		
			return dao.runInsert(t);
		}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��22�� ����4:46:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dms
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delRs(String[] dms) {
		// TODO �Զ����ɷ������
		//�ж��Ƿ���ʹ��
		return false;
	}
	

}
