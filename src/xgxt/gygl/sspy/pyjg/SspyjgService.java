/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-28 ����09:24:52 
 */  
package xgxt.gygl.sspy.pyjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ�������ģ��
 * @�๦������: �������Ž��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-28 ����09:24:38 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SspyjgService extends SuperServiceImpl<SspyjgForm,SspyjgDao>{
	private SspyjgDao dao = new SspyjgDao();
	
	/**
	 * @����: ��ȡ¥����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����01:40:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdxxList() throws Exception{
		return dao.getLdxxList();
	}
	
	/**
	 * @����: ������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����05:53:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsxxForParam(String lddm,String ch,String qsh) throws Exception{
		return dao.getQsxxForParam(lddm,ch,qsh);
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����03:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveFormSspyjg(SspyjgForm model,User user)throws Exception{
		
		boolean rs = true;
		
		/*����Ψһ��ʶ��*/
		String guid = UniqID.getInstance().getUniqIDHash();
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*��ǰ������Ա�û�������*/
		model.setSjlrr(user.getUserName());
		
		/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*�жϸ������Ƿ�Ϊ�޸�����*/
		if(StringUtils.isNotNull(model.getGuid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*����ΨһID*/
			model.setGuid(guid);
			/*1:������ˡ�2:�������*/
			model.setSjly("2");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @����: ��֤Ψһ��:ѧ�ꡢѧ�ڡ�¥�������ҡ�������Ŀ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-28 ����04:01:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(SspyjgForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @����: ����ID��ȡ�����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-3 ����09:01:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid) throws Exception{
		return dao.getInfoByGuid(guid);
	}
}
