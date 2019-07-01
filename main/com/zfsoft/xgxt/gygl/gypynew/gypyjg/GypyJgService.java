package com.zfsoft.xgxt.gygl.gypynew.gypyjg;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqDao;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqForm;

public class GypyJgService extends SuperServiceImpl<GypyJgForm,GypyJgDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-27 ����03:39:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSq(GypyJgForm model,User user) throws Exception{
		boolean rs = true;
		//�ж��ظ���
		GypySqForm sqForm = new GypySqForm();
		BeanUtils.copyProperties(sqForm, model);
		sqForm.setSqid(model.getJgid());
		if(!new GypySqDao().checkIsNotRepeat(sqForm)){
			throw new SystemException(MessageKey.XG_GYPY_SQ_REPEAT);
		}
		model.setCxzt("0");
		if(StringUtils.isNotNull(model.getJgid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			model.setJgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			model.setSjly("0");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-28 ����04:42:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCx(GypyJgForm model) throws Exception{
		model.setCxzt("1");
		return dao.runUpdate(model);
	}
	
}
