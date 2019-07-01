package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

public class PfbzService extends SuperServiceImpl<PfbzForm, PfbzDao> {
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
	public boolean savePfbz(PfbzForm t) throws Exception{
		//���ж����ֱ�׼�Ƿ��ظ�
		boolean rs = dao.checkRepeat(t);
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_REPEAT);
		}
		rs = dao.checkRepeatXh(t);
		if(!rs){
			throw new SystemException(MessageKey.XG_GYJC_PFBZ_XH_REPEAT);
		}
		if(StringUtils.isNotNull(t.getGuid())){
			return dao.runUpdate(t);
		}else{
			return dao.runInsert(t);
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ�����ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����06:53:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean delRs(String[] guids, String[] fjids) throws Exception{
		//�ȶ����ݽ��з���
		List<String> fjList = new ArrayList<String>();
		List<String> zjList = new ArrayList<String>();
		for (int i = 0; i < fjids.length; i++) {
			if("top".equals(fjids[i])){
				fjList.add(guids[i]);
			}else{
				zjList.add(guids[i]);
			}
		}
		
		boolean rs = true;
		if(!zjList.isEmpty() && zjList.size() > 0){
			//���ж�����Ŀ�Ƿ��д����ѱ�ʹ��
			if(!dao.checkIsUserd(zjList.toArray(new String[]{}))){
				throw new SystemException(MessageKey.XG_GYJC_PFBZ_USED);
			}
			rs = dao.delPfbz(zjList.toArray(new String[]{}));
		}
		if(!rs){
			throw new SystemException("");
		}
		
		if(!fjList.isEmpty() && fjList.size() > 0){
			//�ж��Ƿ��������Ŀ
			if(!dao.checkIsCzZjxm((fjList.toArray(new String[]{})))){
				throw new SystemException(MessageKey.XG_GYJC_PFBZ_CZZXM);
			}
			rs = dao.delPfbz(fjList.toArray(new String[]{}));
		}
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return rs;
	}
	
	/**
	 * 
	 * @����:��ȡ���ֱ�׼������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����09:05:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmSelectList(PfbzForm t){
		return dao.getXmSelectList(t);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�޸�model
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����11:55:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * PfbzForm �������� 
	 * @throws
	 */
	public PfbzForm getPfbzModel(String guid) throws Exception{
		return dao.getPfbzModel(guid);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ���ֱ�׼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-18 ����05:32:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfbzListAjax(String fjid){
		return dao.getPfbzListAjax(fjid);
		
	}
}
