/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:49:10 
 */  
package com.zfsoft.xgxt.zxdk.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-��������  
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-24 ����09:49:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxdkCsszService extends SuperServiceImpl<ZxdkCssz, ZxdkCsszDao> implements Constants{

	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String startTime = " 00:00:00";
	private static final String endTime = " 23:59:59";
	
	public ZxdkCssz getModel() throws Exception{
		
		ZxdkCssz model = dao.getModel();
		
		if (model == null){
			model = new ZxdkCssz();
			model.setXydKg(CLOSE);
			model.setSydKg(CLOSE);
			model.setXdKg(CLOSE);
		} else {
			
			String nowTime = GetTime.getTimeByFormat(DATE_FORMAT);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Date now = sdf.parse(nowTime);
			
			if (CLOSE.equals(model.getXydkg())){
				model.setXydKg(CLOSE);
			} else if (!StringUtil.isNull(model.getXydkssj()) && StringUtil.isNull(model.getXydjssj())){
				Date start = sdf.parse(model.getXydkssj()+startTime);
				model.setXydKg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getXydkssj()) && !StringUtil.isNull(model.getXydjssj())){
				Date end = sdf.parse(model.getXydjssj()+endTime);
				model.setXydKg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getXydkssj()) && !StringUtil.isNull(model.getXydjssj())){
				Date start = sdf.parse(model.getXydkssj()+startTime);
				Date end = sdf.parse(model.getXydjssj()+endTime);
				model.setXydKg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setXydKg(OPEN);
			}
			
			if (CLOSE.equals(model.getSydkg())){
				model.setSydKg(CLOSE);
			} else if (!StringUtil.isNull(model.getSydkssj()) && StringUtil.isNull(model.getSydjssj())){
				Date start = sdf.parse(model.getSydkssj()+startTime);
				model.setSydKg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getSydkssj()) && !StringUtil.isNull(model.getSydjssj())){
				Date end = sdf.parse(model.getSydjssj()+endTime);
				model.setSydKg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getSydkssj()) && !StringUtil.isNull(model.getSydjssj())){
				Date start = sdf.parse(model.getSydkssj()+startTime);
				Date end = sdf.parse(model.getSydjssj()+endTime);
				model.setSydKg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setSydKg(OPEN);
			}
			
			if (CLOSE.equals(model.getXdkg())){
				model.setXdKg(CLOSE);
			}else if (!StringUtil.isNull(model.getXdkssj()) && StringUtil.isNull(model.getXdjssj())){
				Date start = sdf.parse(model.getXdkssj()+startTime);
				model.setXdKg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getXdkssj()) && !StringUtil.isNull(model.getXdjssj())){
				Date end = sdf.parse(model.getXdjssj()+endTime);
				model.setXdKg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getXdkssj()) && !StringUtil.isNull(model.getXdjssj())){
				Date start = sdf.parse(model.getXdkssj()+startTime);
				Date end = sdf.parse(model.getXdjssj()+endTime);
				model.setXdKg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setXdKg(OPEN);
			}
			
			
			if (CLOSE.equals(model.getTqhkkg())){
				model.setTqhkKg(CLOSE);
			}else if (!StringUtil.isNull(model.getTqhkkssj()) && StringUtil.isNull(model.getTqhkjssj())){
				Date start = sdf.parse(model.getTqhkkssj()+startTime);
				model.setTqhkKg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getTqhkkssj()) && !StringUtil.isNull(model.getTqhkjssj())){
				Date end = sdf.parse(model.getTqhkjssj()+endTime);
				model.setTqhkKg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getTqhkkssj()) && !StringUtil.isNull(model.getTqhkjssj())){
				Date start = sdf.parse(model.getTqhkkssj()+startTime);
				Date end = sdf.parse(model.getTqhkjssj()+endTime);
				model.setTqhkKg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setTqhkKg(OPEN);
			}
		}
		
		return model;
	}
}
