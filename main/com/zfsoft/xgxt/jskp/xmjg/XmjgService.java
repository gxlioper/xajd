package com.zfsoft.xgxt.jskp.xmjg;

import java.util.HashMap;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;

public class XmjgService extends SuperServiceImpl<XmjgForm, XmjgDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����: ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-17 ����11:27:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxjg
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveForLxjg(XmjgForm lxjg) throws Exception{
		lxjg.setXmdl("gdx");
		lxjg.setSjly("0");
		boolean rs = true;
		//��Ŀ�����ظ�����֤
		LxsqForm lxsq = new LxsqForm();
		lxsq.setSqid(lxjg.getXmid());
		lxsq.setLxsj(lxsq.getLxsj());
		lxsq.setXmmc(lxjg.getXmmc());
		if(StringUtils.isNull(lxjg.getXmid())){
			if(!new LxsqService().checkIsNotRepeat(lxsq)){
				throw new SystemException(MessageKey.XG_ZJDX_JSKP_REPEAT);
			}
			rs = dao.runInsert(lxjg);
		}else{
			rs = dao.runUpdate(lxjg);
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ��֤��Ŀ�Ƿ��ѱ�ʹ�ù�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-24 ����08:55:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXmIsNotUserd(String[] xmids){
		return dao.checkXmIsNotUserd(xmids);
	}

	public HashMap<String,String> getXmxx(String xmid){
		return dao.getXmxx(xmid);
	}
}
