package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class YbjgService extends SuperServiceImpl<YbjgForm, YbjgDao> {
	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:59:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡѧ����ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����01:45:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuCx(YbjgForm t, User user,String xhs) throws Exception{
		return dao.getStuCx(t, user, xhs);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����02:55:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList(String xymc) throws Exception{
		return dao.getXyList(xymc);
	}
	
	public boolean saveYbjg(YbjgForm ybjg) throws Exception{
		boolean rs = true;
		String[] xhArray = ybjg.getXhArray();
		String[] ybwtmsArray = ybjg.getYbwtmsArray();
		String[] ybgycsArray = ybjg.getYbgycsArray();
		String[] ybgyhjgArray = ybjg.getYbgyhjgArray();
		String[] wtfsrqArray = ybjg.getWtfsrqArray();
		ybjg.setTxrq(GetTime.getTimeByFormat("yyyy-mm-dd"));
		if(StringUtils.isNotNull(ybjg.getJgid())){
			rs = dao.delWtb(ybjg.getJgid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdate(ybjg);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!dao.checkIsNotExist(ybjg.getXydm(),ybjg.getXn(),ybjg.getYf())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			ybjg.setJgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			ybjg.setSjly("0");
			rs = dao.runInsert(ybjg);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < ybwtmsArray.length; i++) {
				//�����Ƽ���ѧ���Ի�
				if("10704".equals(Base.xxdm)){
					paraList.add(new String[]{"yb",ybjg.getJgid(),xhArray[i],ybwtmsArray[i],null,ybgyhjgArray[i],wtfsrqArray[i]});
				}else{
					paraList.add(new String[]{"yb",ybjg.getJgid(),xhArray[i],ybwtmsArray[i],ybgycsArray[i],ybgyhjgArray[i],null});
				}
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
	 * @����: ��ȡ�ܱ�������Ա��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:26:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYbWtryInfo(String ybjgid){
		return dao.getYbWtryInfo(ybjgid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���±����
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

	public boolean update(YbjgForm myForm) throws Exception {
		boolean rs = true;
		rs = dao.update(myForm);
		if(rs){
			rs =  dao.delWtb(myForm.getJgid());
		}
		return rs;
	}

	public List<HashMap<String, String>> getYbhzList(YbjgForm model, User user) throws Exception{
		return dao.getYbhzList(model,user);
	}

	public List<HashMap<String, String>> getAllHzList(YbjgForm t, User user)  throws Exception{
		return dao.getAllHzList(t, user);
	}

}
