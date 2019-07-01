/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-1 ����01:27:02 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.gygl.zzdgl.sh.ZzdshForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-1 ����01:27:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdjgService extends SuperServiceImpl<ZzdjgForm, ZzdjgDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ZzdjgDao dao = new ZzdjgDao();
	/** 
	 * @����:�ж��Ƿ��м�¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-1 ����01:30:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecordForjg(ZzdjgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	/** 
	 * @����:������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-2 ����10:08:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveZzdjg(ZzdjgForm form) throws Exception{
		boolean result = false;
		if ("save".equals(form.getType())) {
			result = dao.runInsert(form);
		} else {
			result = dao.runUpdate(form);
		}
		return result;
	}
	
	/** 
	 * @����:�õ���Ԣ����Աȷ���б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����09:05:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getPageListForQr(ZzdjgForm t, User user) throws Exception {
		return dao.getPageListForQr(t, user);
	}
	
	/** 
	 * @����:����ȷ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����02:55:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean qxqr(ZzdjgForm t,User user) throws Exception{
		t.setQrzt("");
		t.setQryj("");
		t.setCzy(user.getUserName());
		t.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		return dao.runUpdate(t);
	}
	
	/** 
	 * @����:����ȷ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����02:51:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String savePlqr(ZzdjgForm t, User user) throws Exception{
		String[] ids = t.getQrids();
		if("yes".equalsIgnoreCase(t.getQrzt())){
			t.setQrzt("1");
		}else {
			t.setQrzt("2");
		}
		ZzdjgForm model = new ZzdjgForm();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			ZzdjgForm form = dao.getModel(ids[i]);			
			BeanUtils.copyProperties(model, StringUtils.formatData(form));
			model.setQrzt(t.getQrzt());
			model.setQryj(t.getQryj());
			boolean isSuccess = dao.runUpdate(model);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL, json.toString());
		}
	}
	
	
	
	
}
