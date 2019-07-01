/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����04:04:40 
 */
package com.zfsoft.xgxt.zxdk.rwfbybc.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����04:04:40
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RwfbyCsszService extends SuperServiceImpl<RwfbyCssz, RwfbyCsszDao>
		implements Constants {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_END_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String endTime = " 23:59:59";

	public RwfbyCssz getModel() throws Exception {
		RwfbyCssz model = dao.getModel();
		if (model == null) {
			model = new RwfbyCssz();
			model.setSqkg(CLOSE);
		} else {
			String nowTime = GetTime.getTimeByFormat(DATE_FORMAT);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			SimpleDateFormat endF = new SimpleDateFormat(DATE_END_FORMAT);
			Date now = sdf.parse(nowTime);
			if (CLOSE.equals(model.getSqkg())) {
				model.setSqkg(CLOSE);
			} else if (!StringUtil.isNull(model.getSqkssj())
					&& StringUtil.isNull(model.getSqjssj())) {
				Date start = sdf.parse(model.getSqkssj());
				model.setSqkg(now.after(start) ? OPEN : CLOSE);
			} else if (StringUtil.isNull(model.getSqkssj())
					&& !StringUtil.isNull(model.getSqjssj())) {
				Date end = endF.parse(model.getSqjssj() + endTime);
				model.setSqkg(now.before(end) ? OPEN : CLOSE);
			} else if (!StringUtil.isNull(model.getSqkssj())
					&& !StringUtil.isNull(model.getSqjssj())) {
				Date start = sdf.parse(model.getSqkssj());
				Date end = endF.parse(model.getSqjssj() + endTime);
				model.setSqkg(now.after(start) && now.before(end) ? OPEN
						: CLOSE);
			} else {
				model.setSqkg(OPEN);
			}
		}
		return model;
	}
}
