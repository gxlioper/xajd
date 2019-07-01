package xsgzgl.gyjc.jcrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

public class JcrcService extends SuperServiceImpl<JcrcForm, JcrcDao> {
	/**
	 * 
	 * @����: ����ʱ�����ϸ�б�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-14 ����11:58:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJcrcAddList(JcrcForm t){
		return dao.getJcrcAddList(t);
	}
	
	/**
	 * 
	 * @����: �����ȡ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����08:53:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> produceSjqsList(JcrcForm t){
		return dao.produceSjqsList(t);
	}
	
	/**
	 * 
	 * @����: �������ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����09:53:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean  saveJcRc(JcrcForm t) throws Exception {
		/**
		 * ���guid��Ϊ�գ���Ϊ�޸����ͣ���Ҫ��ɾ��ԭ��������
		 */
		String[] xydms = t.getXydms();
		String[] jcbls = t.getJcbls();
		//�������
		String rcid = t.getGuid();
		 t.setType("update");
		if(StringUtils.isNull(rcid)){
			 rcid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			 t.setType("save");
		}
		List<String[]> jcblList = new ArrayList<String[]>();
		List<String[]> jcmxList = new ArrayList<String[]>();
		List<String> jjlxList = new ArrayList<String>();
		if(StringUtils.isNotNull(t.getWsjc())){
			jjlxList.add(t.getWsjc());
		}
		if(StringUtils.isNotNull(t.getAqjc())){
			jjlxList.add(t.getAqjc());
		}
		if(StringUtils.isNotNull(t.getJljc())){
			jjlxList.add(t.getJljc());
		}
		for (int i = 0; i < xydms.length; i++) {
			if(StringUtils.isNotNull(jcbls[i])){
				jcblList.add(new String[]{rcid,xydms[i],jcbls[i]});
			}
		}
		List<HashMap<String, String>> sjsList = dao.produceSjqsList(t);
		//���û�в����κγ�����ݣ�������ʾ��
		if( sjsList == null || sjsList.size() == 0){
			throw new SystemException(MessageKey.XG_GYJC_JCRC_NO_DATA);
		}
		for (int i = 0; i < sjsList.size(); i++) {
			for (int j = 0; j < jjlxList.size(); j++) {
				jcmxList.add(new String[]{rcid,sjsList.get(i).get("xydm"),sjsList.get(i).get("lddm"),sjsList.get(i).get("qsh"),jjlxList.get(j)});
			}
		}
		t.setGuid(rcid);
		return this.saveJcrcInDb(jcblList, jcmxList, t);
	}
	
	/**
	 * 
	 * @����: �������������ݿ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����03:27:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcblList
	 * @param jcmxList
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJcrcInDb(List<String[]> jcblList,List<String[]> jcmxList,JcrcForm t) throws Exception{
		boolean rs = true;
		rs = dao.saveJcrc(t);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		rs = dao.saveJcbl(jcblList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		rs = dao.saveJcmx(jcmxList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: �޸�ʱ��ȡjcbl��ϸ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����03:41:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getUpdatejcrcmx(String rcid) throws Exception{
		return dao.getUpdatejcrcmx(rcid);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ����ύ����,Ϊ�˺������ܴ�������ɾ�������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����03:53:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfExistTj(String rcid ){
		return dao.checkIfExistTj(rcid);
	}
	
	/**
	 * 
	 * @����: ��֤��ǰ�Ƿ�Ϊ���¼���ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����04:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfLastNew(String rcid,String js,String xydm){
		return dao.checkIfLastNew(rcid, js,xydm);
	}
	
	/**
	 * 
	 * @����: ��֤�����Ƿ��н���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����04:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccrq
	 * @param jzrq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIfRqIntersection(String ccrq,String jzrq,String rcid,String js,String xydm){
		return dao.checkIfRqIntersection(ccrq, jzrq, rcid, js, xydm);
	}
	
	/**
	 * 
	 * @����: ɾ������ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����05:54:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJcrc(String rcid,boolean flag) throws Exception{
		boolean rs = true;
		if(flag){
			 rs = dao.delJcrcB(new String[]{rcid});
			if(!rs){
				throw new SystemException(MessageKey.SYS_DEL_FAIL);
			}
		}
		rs = dao.delJcblB(new String[]{rcid});
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		rs = dao.delJcmxBz(rcid);
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		
		rs = dao.delJcmxB(new String[]{rcid});
		if(!rs){
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return rs;
	
    }

	public boolean delJcrcTran(String rcid,boolean flag) throws Exception{
		return this.delJcrc(rcid,flag);
	}
	
	/**
	 * 
	 * @����: ��ȡ���List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-19 ����09:21:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcmxList
	 * @param jcblList
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
    public List<HashMap<String, Object>> getZhList(List<HashMap<String, String>> jcmxList,List<HashMap<String, String>> jcblList,List<HashMap<String, String>> ldxxList){
    	List<HashMap<String, Object>> zhList = new ArrayList<HashMap<String,Object>>();
		for (HashMap<String, String> jcmx : jcmxList) {
			String jcblTemp = "";
			for (HashMap<String, String> jcbl : jcblList) {
				if(jcmx.get("xydm").equals(jcbl.get("xydm"))){
					jcblTemp = jcbl.get("jcbl");
					break;
				}
			}
			List<HashMap<String, String>> tempLdList = new ArrayList<HashMap<String,String>>();
			for (HashMap<String, String> ld : ldxxList) {
				if(jcmx.get("xydm").equals(ld.get("xydm"))){
					tempLdList.add(ld);
				}
			}
			HashMap<String,Object> tempMap = new HashMap<String, Object>();
			tempMap.putAll(jcmx);
			tempMap.put("jcblinput", jcblTemp);
			tempMap.put("ld",tempLdList);
			zhList.add(tempMap);
		}
		return zhList;
	}
    
    /**
	 * 
	 * @����: ��ȡѡ��¥����ϢList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-23 ����05:37:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSelLdList(JcrcForm t){
		return dao.getSelLdList(t);
	}
	
	/**
	 * 
	 * @����: �޸�ʱ��ȡ�����ճ̳��˶��ٸ�¥�������ٸ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-24 ����11:22:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdxxList(String rcid){
		return dao.getLdxxList(rcid);
	}
    
}
