package com.zfsoft.xgxt.pjpy.hjsq.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class HjjgService extends SuperServiceImpl<HjjgForm, HjjgDao> {
	private static final String MESSAGE_REPEAT = "ͬһѧ��ѧ�ڽ������Ʋ����ظ���";
	public boolean save(HjjgForm zhcpForm) throws Exception{
		boolean rs = true;
		if(dao.checkExistForSave(zhcpForm)){
			JSONObject json = new JSONObject();
			json.put("message", MESSAGE_REPEAT);
			throw new SystemException(json);
		}
		if(StringUtils.isNotNull(zhcpForm.getId())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setId(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ��ȡ����ϢList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-22 ����02:12:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjxxPageList(HjjgForm t, User user)
	throws Exception {
		return  dao.getHjxxPageList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-22 ����05:16:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws
	 */
	public boolean  delHjxx(String[] ids) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < ids.length; i++) {
			params.add(new String[]{ids[i]});
		}
		return dao.delHjxx(params);
	}
}
