/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-27 ����02:23:57 
 */  
package com.zfsoft.xgxt.base.export.business.imp.rcsw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-11-27 ����02:23:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcswxwjgxwlbAndXfRule extends BusinessExtend{
	private final static String _YWKZ_JEYZ_KEY ="rcsw_xwlb_xfyz";

	public RcswxwjgxwlbAndXfRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		String message = this._YZTG;
		if (StringUtils.isNull(param)) {
			return message;
		}
		Map<String, String> map = getParamExcleValue(data);
		// XN-
		String pdfz = map.get("FZ");
		String rcxwlbdm = map.get("RCXWLBDM");
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.rcxwlbzgfz maxfz,t.rcxwlbzdfz  minfz from xg_rcsw_rcxwlbdmb t where t.rcxwlbdm = ?");
		/*��ȡ��С����ֵ*/
		HashMap<String, String> min_maxfz = DAO.getInstance().getMapNotOut(sb.toString(), new String[]{rcxwlbdm});
		String minfz = min_maxfz.get("minfz");
		String maxfz = min_maxfz.get("maxfz");
	    if(this.isSuccess(message)){
			if (StringUtils.isNull(maxfz) || StringUtils.isNull(minfz)) {
				if(StringUtils.isNotNull(maxfz) && !maxfz.trim().equals(pdfz.trim())){
					message = "��ֵӦ���ڹ̶�ֵ"+maxfz;
				}else if(StringUtils.isNotNull(minfz) && !minfz.trim().equals(pdfz.trim())){
					message = "��ֵӦ���ڹ̶�ֵ"+minfz;
				}				
			}else{
				double szgsminfz = Double.parseDouble(minfz);
				double szgsmaxfz = Double.parseDouble(maxfz);
				double fz = Double.parseDouble(pdfz);
				if(!(fz >= szgsminfz && fz <= szgsmaxfz)){
					message = "��ֵӦ��"+minfz+"-"+maxfz+"֮��";
				}
				
			}
		} 
		return message;
	}
}
