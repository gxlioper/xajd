package com.zfsoft.xgxt.pjpy.xzhcp.jg;

import net.sf.json.JSONObject;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class ZhcpJgService extends SuperServiceImpl<ZhcpJgForm,ZhcpJgDao> {
	private static final String MESSAGE_REPEAT = "��ѧ��ѧ�����м�¼�������ظ���д��";
	/**
	 * 
	 * @����: �����ۺϲ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-7 ����02:54:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zhcpForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean save(ZhcpJgForm zhcpForm) throws Exception{
		boolean rs = true;
		if(StringUtils.isNotNull(zhcpForm.getSqid())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setSjly("0");
			if(!dao.checkNotRepeat(zhcpForm)){
				JSONObject json = new JSONObject();
				json.put("message", MESSAGE_REPEAT);
				throw new SystemException(json);
			}
			zhcpForm.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		return rs;
	}
}
