/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����09:35:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgForm;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-19 ����09:35:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglJgService extends
		SuperServiceImpl<XsxxKzxxglJgForm, XsxxKzxxglJgDAO> {
	
	public boolean deleteJgBySqid(String sqid)throws Exception{
		if(StringUtils.isBlank(sqid)){
			return true;
		}
		return dao.deleteBySqid(sqid);
	}
	
	public XsxxKzxxglJgForm getModelBySqid(String sqid) throws Exception{
		if(StringUtils.isBlank(sqid)){
			return null;
		}
		return dao.getModelBySqid(sqid);
	}
	
	public XsxxKzxxglJgForm getModelByXh(String xh) throws Exception{
		if(StringUtils.isBlank(xh)){
			return null;
		}
		return dao.getModelByXh(xh);
	}
	
	public boolean deleteByXh(String xh) throws Exception{
		if(StringUtils.isNotBlank(xh)){
			return dao.deleteByXh(xh);
		}
		return true;
	}
	

}
