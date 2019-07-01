/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����08:49:23 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��--��ͥ������� ��������
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-25 ����08:49:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtqkdcJcszService extends
		SuperServiceImpl<JtqkdcJcszForm, JtqkdcJcszDao> {

	private JtqkdcJcszDao dao = new JtqkdcJcszDao();
	private static final String CLOSE = "0";
	
	public JtqkdcJcszService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����:��ѯ��ͥ��������������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-25 ����09:03:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * JtqkdcJcszForm �������� 
	 * @throws
	 */
	public JtqkdcJcszForm getModel(){
		
		try {
			JtqkdcJcszForm model = dao.getModel();
			
			if (CLOSE.equals(model.getSqkg())){
				//false
				model.setIsOpen(String.valueOf(false));
			} else if (!StringUtil.isNull(model.getSqjssj())){
				
				Date startTime = new Date();
				Date endTime = new Date();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
				if(!StringUtils.isBlank(model.getSqkssj())){
					startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
				}
				if(!StringUtils.isBlank(model.getSqjssj())){
					endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
				}
				
				String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
				Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
				
				if ((startTime != null&&now.before(startTime)) ||(endTime!=null&& now.after(endTime))){
					//false
					model.setIsOpen(String.valueOf(false));
				} else {
					//true
					model.setIsOpen(String.valueOf(true));
				}
			} else {
				//true
				model.setIsOpen(String.valueOf(true));
			}
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean runInsert(JtqkdcJcszForm t) throws Exception {
		
		//��ɾ ����
		boolean flg = dao.delJcsz();
		
		if (flg){
			return super.runInsert(t);
		}
		
		return flg;
	}
	
	
	
}
