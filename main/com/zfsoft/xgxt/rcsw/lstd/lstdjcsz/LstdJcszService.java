/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-21 ����05:31:58 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ɫͨ����������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-21 ����05:31:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdJcszService extends SuperServiceImpl<LstdJcszForm, LstdJcszDao> {
	
	private LstdJcszDao dao = new LstdJcszDao();
	
	public LstdJcszService() {
		super.setDao(dao);
	}
	
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public LstdJcszForm getModel(LstdJcszForm t)throws Exception{
		
		LstdJcszForm  model= dao.getModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (model != null) {
			
			if (Constants.CLOSE.equals(model.getSqkg())){
				model.setIsopen("false");
			} else if (!StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
				
				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
				
				if(now.before(startTime)||now.after(endTime)){
					model.setIsopen("false");
				} else{
					model.setIsopen("true");
				}
					
			} else if (StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
				
				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
				if(now.after(endTime)){
					model.setIsopen("false");
				} else{
					model.setIsopen("true");
				}
				
			} else if (!StringUtil.isNull(model.getSqkssj())&&StringUtil.isNull(model.getSqjssj())){
				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
				if(now.before(startTime)){
					model.setIsopen("false");
				} else{
					model.setIsopen("true");
				}
			}else {
				model.setIsopen("true");
			}
		}
		return model;
	}
	

	/**
	 * 
	 * @����:��ȡmodel
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-24 ����05:44:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * LstdJcszForm �������� 
	 * @throws
	 */
    public LstdJcszForm getModel()throws Exception{
		
		return getModel(new LstdJcszForm());
	}
	
   /**
    * 
    * @����:TODO(������һ�仰�����������������)
    * @���ߣ�cq [���ţ�785]
    * @���ڣ�2014-11-24 ����05:44:37
    * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
    * @param model
    * @return
    * @throws Exception
    * boolean �������� 
    * @throws
    */
	public boolean saveJcsz(LstdJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteLstdJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
		
	}

}
