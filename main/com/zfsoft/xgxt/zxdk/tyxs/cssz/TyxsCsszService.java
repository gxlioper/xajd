/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-8 ����10:23:06 
 */  
package com.zfsoft.xgxt.zxdk.tyxs.cssz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ѧ����������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Ӣ[���ţ�1177]
 * @ʱ�䣺 2015-4-8 ����10:23:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TyxsCsszService extends  SuperServiceImpl<TyxsCssz, TyxsCsszDao> implements Constants {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String startTime = " 00:00:00";
	private static final String endTime = " 23:59:59";
	
	public TyxsCssz getModel() throws Exception{
		
		TyxsCssz model = dao.getModel();
		
		if (model == null){
			model = new TyxsCssz();
			model.setXfzzshkg(CLOSE);
			model.setXfzzsqkg(CLOSE);
			
		} else {
			
			String nowTime = GetTime.getTimeByFormat(DATE_FORMAT);
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Date now = sdf.parse(nowTime);
			//���뿪��
			if (CLOSE.equals(model.getXfzzsqkg())){
				model.setXfzzsqkg(CLOSE);
			} else if (!StringUtil.isNull(model.getXfzzsqkssj()) && StringUtil.isNull(model.getXfzzsqjssj())){
				Date start = sdf.parse(model.getXfzzsqkssj()+startTime);
				model.setXfzzsqkg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getXfzzsqkssj()) && !StringUtil.isNull(model.getXfzzsqjssj())){
				Date end = sdf.parse(model.getXfzzsqjssj()+endTime);
				model.setXfzzsqkg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getXfzzsqkssj()) && !StringUtil.isNull(model.getXfzzsqjssj())){
				Date start = sdf.parse(model.getXfzzsqkssj()+startTime);
				Date end = sdf.parse(model.getXfzzsqjssj()+endTime);
				model.setXfzzsqkg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setXfzzsqkg(OPEN);
			}
			//��˿���
			
			if (CLOSE.equals(model.getXfzzshkg())){
				model.setXfzzshkg(CLOSE);
			} else if (!StringUtil.isNull(model.getXfzzshkssj()) && StringUtil.isNull(model.getXfzzshjssj())){
				Date start = sdf.parse(model.getXfzzshkssj()+startTime);
				model.setXfzzshkg(now.after(start) ? OPEN :CLOSE);
			} else if (StringUtil.isNull(model.getXfzzshkssj()) && !StringUtil.isNull(model.getXfzzshjssj())){
				Date end = sdf.parse(model.getXfzzshjssj()+endTime);
				model.setXfzzshkg(now.before(end) ? OPEN :CLOSE);
			} else if (!StringUtil.isNull(model.getXfzzshkssj()) && !StringUtil.isNull(model.getXfzzshjssj())){
				Date start = sdf.parse(model.getXfzzshkssj()+startTime);
				Date end = sdf.parse(model.getXfzzshjssj()+endTime);
				model.setXfzzshkg(now.after(start) && now.before(end) ? OPEN :CLOSE);
			} else {
				model.setXfzzshkg(OPEN);
			}
			
		
		}
		
		return model;
	}
	public List<HashMap<String, String>> getXlccList(){
		return dao.getXlccList();
	}
	
}
