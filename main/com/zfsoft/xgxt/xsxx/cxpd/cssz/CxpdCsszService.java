/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:49:10 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import common.newp.StringUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��������--�������� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2015-1-14 ����01:52:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CxpdCsszService extends SuperServiceImpl<CxpdCssz, CxpdCsszDao> implements Constants{

	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String startTime = " 00:00:00";
	private static final String endTime = " 23:59:59";
	
	public CxpdCssz getModel() throws Exception{
		
		CxpdCssz model = dao.getModel();
		
		if (model == null){
			model = new CxpdCssz();
			model.setSqkg(CLOSE);
		} else {
			
			String nowTime = GetTime.getTimeByFormat(DATE_FORMAT);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Date now = sdf.parse(nowTime);
			
			if (CLOSE.equals(model.getSqkg())){
				model.setSqkg(CLOSE);
			} else if (!StringUtil.isNull(model.getSqkssj()) && StringUtil.isNull(model.getSqjssj())){
				Date start = sdf.parse(model.getSqkssj()+startTime);
				model.setSqkg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getSqkssj()) && !StringUtil.isNull(model.getSqjssj())){
				Date end = sdf.parse(model.getSqjssj()+endTime);
				model.setSqkg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getSqkssj()) && !StringUtil.isNull(model.getSqjssj())){
				Date start = sdf.parse(model.getSqkssj()+startTime);
				Date end = sdf.parse(model.getSqjssj()+endTime);
				model.setSqkg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setSqkg(OPEN);
			}
		}
		
		return model;
	}
}
