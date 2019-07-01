/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-31 ����09:10:02 
 */
package com.zfsoft.xgxt.base.export.business.imp.qgzx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.jfgl.QgzxJfglService;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��𷽷�������չ
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-31 ����09:10:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CjffRule extends BusinessExtend {
	private final static String _YWKZ_JEYZ_KEY = "YWKZ_QGZX";

	public CjffRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		String message = this._YZTG;
		if (StringUtils.isNull(param)) {
			return message;
		}
		Map<String, String> map = getParamExcleValue(data);
		// XN-YRBM
		String xn = map.get("XN").substring(0,4);
		String yrdwdm = map.get("YRBM");
		String je = map.get("JE");
		StringBuffer sb = new StringBuffer();
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			xn = map.get("FFNY");
			sb.append("select syje from view_xg_qgzx_jfhbb_yf where yf=? and yrdwdm=?");
		}else{
			sb.append("select syje from view_xg_qgzx_jfhbb_nd where nd=? and yrdwdm=?");
		}
		String syje = DAO.getInstance().getOneRs(sb.toString(),
				new String[] { xn, yrdwdm }, "syje");
		boolean isKzhbje = new QgzxJfglService().isKzHbjs();
		if (isKzhbje && StringUtils.isNull(syje)) {
			message = "��Ӧ���˵�λ���λ�����ڣ����ܷ��ţ�";
		}else if(this.isSuccess(message)) {
			message=checkXsCjff(map.get("XH"), map.get("FFNY"),map.get("GWDM"));
		} else if(isKzhbje) {
			Integer syjeI = StringUtils.paseStringToInteger(syje);
			Integer jeI = StringUtils.paseStringToInteger(je);
			if (jeI > syjeI) {
				message = compileMessage(map, param);
			}
		}
		return message;
	}
	private String checkXsCjff(String xh,String ffny,String gwdm){
		String message = this._YZTG;
		StringBuffer sb=new StringBuffer();
		sb.append("select * from  XG_QGZX_CJFF where xh=? and ffny=? and gwdm=?");
		List<HashMap<String, String>> list=DAO.getInstance().getListNotOut(sb.toString(), new String[]{xh,ffny,gwdm});
		if(null!=list&&list.size()>0){
			message="�˸�λѧ��["+xh+"]����"+ffny+"���µķ�����Ϣ���ܷ��ţ�";
		}
		return message;
	}
}
