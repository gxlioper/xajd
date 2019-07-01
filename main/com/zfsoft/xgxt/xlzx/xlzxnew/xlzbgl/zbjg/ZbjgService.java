package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

public class ZbjgService extends SuperServiceImpl<ZbjgForm,ZbjgDao> {
	/**
	 * 
	 * @����: ��ȡ�ܱ�������Ա��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:26:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZbWtryInfo(String zbjgid){
		return dao.getZbWtryInfo(zbjgid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����05:13:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsb
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveZbsb(ZbjgForm zbsb) throws Exception{
		boolean rs = true;
		String[] xhArray = zbsb.getXhArray();
		String[] zbwtmsArray = zbsb.getZbwtmsArray();
		zbsb.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		if(StringUtils.isNotNull(zbsb.getSbjgid())){
			rs = dao.delWtb(zbsb.getSbjgid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdateNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!this.checkIsNotExist(zbsb.getBjdm(), zbsb.getSbzbid())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			zbsb.setSbjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			zbsb.setSjly("0");
			rs = dao.runInsertNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < zbwtmsArray.length; i++) {
				paraList.add(new String[]{"zb",zbsb.getSbjgid(),xhArray[i],zbwtmsArray[i]});
			}
			rs = dao.saveDataIntoWtb(paraList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	
	/**
	 * 
	 * @����: ��֤�Ƿ�����д�ܱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-1 ����11:33:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String bjdm,String sbzbid){
		return dao.checkIsNotExist(bjdm, sbzbid);
	}
	
	/**
	 * 
	 * @����: ���ά���鿴�༶��Ϣ
	 * @���ڣ�2017-9-2 ����10:36:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getTeaBjxx(String bjdm){
		return dao.getTeaBjxx(bjdm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���ϱ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����03:07:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delSbjg(String[] sbjgids) throws Exception{
		return dao.delSbjg(sbjgids);
	}
	
	/**
	 *
	 * @����: ��ѯ������Ա���д�����Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-3 ����02:47:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjList(String zgh){
		return dao.getBjList(zgh);
	}
	
	/**
	 * 
	 * @����: �Ƿ񸨵�Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-3 ����04:41:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fdy
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsFdy(String zgh){
		return dao.checkIsFdy(zgh);
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܴ�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����08:47:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param sblx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcList(String xn, String xq) {
		return dao.getZcList(xn, xq);
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܱ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����08:50:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param zbid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQzrq(String zbid) {
		return dao.getQzrq(zbid);
	}
}
