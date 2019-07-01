/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-14 ����02:13:35 
 */  
package com.zfsoft.xgxt.base.export.business.imp.system;


import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������һ������֤������չ
 * @�๦������: ѧ������һ������֤
 * @���ߣ�tgj[����:1075]
 * @ʱ�䣺 2017-7-14 ����03:34:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XhXmRule extends BusinessExtend {
	private final static String _YWKZ_JEYZ_KEY = "xhxm_yzxyz";
	private final String defaultMessage = "������ѧ�Ų�ƥ�䣡";

	public XhXmRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		String message = this._YZTG;
		if (StringUtils.isNull(param)) {
			return message;
		}
		Map<String, String> map = getParamExcleValue(data);

		String xh = map.get("XH");
		String xm = map.get("XSXM");
		
		message = checkXhxm(xh, xm);
		
		return message;
	}
	
	private String checkXhxm(String xh,String xm){
		String message = this._YZTG;
		StringBuffer sb=new StringBuffer();
		sb.append("select count(1) num from xsxxb where xh=? and xm=? ");
		String num = DAO.getInstance().getOneRs(sb.toString(), new String[] { xh, xm }, "num");
		if("0".equals(num)){
			message=defaultMessage;
		}
		return message;
	}
}
