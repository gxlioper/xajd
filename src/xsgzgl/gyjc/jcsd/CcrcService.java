/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����5:58:11 
 */  
package xsgzgl.gyjc.jcsd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gyjc.jcrc.JcrcForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��22�� ����5:58:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CcrcService extends SuperServiceImpl<CcrcForm, CcrcDao> {

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��23�� ����2:28:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcrq
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkIfRqIntersection(String jcrq) {
		// TODO �Զ����ɷ������
		return dao.checkIfRqIntersection(jcrq);
	}
	
	
	@TransactionControl
	public boolean  saveCcRc(CcrcForm t) throws Exception {
		return dao.runInsert(t);
	}
	
	public List<HashMap<String, String>> getList(CcrcForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return dao.getList(t,user);
	}
	
	
	public List<HashMap<String, String>> getRyfpList(CcrcForm t, User user) throws  Exception{
		return dao.getRyfpList(t, user);
	}
	
	@TransactionControl
	public boolean saveRyFp(CcrcForm t, String[] lddms, String[] qshs) throws Exception{
		/**
		 * �������
		 */
		List<String[]> paraList = new ArrayList<String[]>();
		String zgh = t.getZgh();
		String ccid = t.getCcid();
		/**
		 * �������
		 */
		for (int i = 0; i < lddms.length; i++) {
			paraList.add(new String[]{ccid,lddms[i],qshs[i],zgh});
		}
		boolean rs = dao.saveFpry(paraList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}


	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��24�� ����5:22:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> gettodayCcList(CcrcForm model, User user) throws Exception {
		// TODO �Զ����ɷ������
		return dao.gettodayCcList(model,user);
	}


	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��25�� ����2:21:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param lddms
	 * @param qshs
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateRyFp(CcrcForm model) throws Exception {
		// TODO �Զ����ɷ������
		return dao.updateRyfp(model);
	}


	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��25�� ����4:34:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getqsfpList(CcrcForm model, User user) throws Exception {
		// TODO �Զ����ɷ������
		return dao.getqsfpList(model,user);
	}


	

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��25�� ����6:41:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertQs(CcrcForm model, String[] lddm, String[] qsh) throws Exception {
		/**
		 * �������
		 */
		List<String[]> paraList = new ArrayList<String[]>();
		String zgh = model.getZgh();
		String ccid = model.getCcid();
		/**
		 * �������
		 */
		for (int i = 0; i < lddm.length; i++) {
			paraList.add(new String[]{ccid,lddm[i],qsh[i],zgh});
		}
		boolean rs = dao.saveFpry(paraList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}


	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��25�� ����7:14:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param userStatus
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	@TransactionControl
	public boolean cancelRyfp(CcrcForm model, String userStatus) throws Exception {
		// TODO �Զ����ɷ������
		return dao.cancelFp(model,userStatus);
	}


	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��28�� ����9:49:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkIfExistTj(String rcid) {
		// TODO �Զ����ɷ������
		return dao.checkIfExistTj(rcid);
	}


	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018��5��28�� ����9:55:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcid
	 * @param b
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delCcrcTran(String rcid, boolean b) throws Exception {
		// TODO �Զ����ɷ������
		return dao.delCcrc(new String[]{rcid});
	}


	

}
