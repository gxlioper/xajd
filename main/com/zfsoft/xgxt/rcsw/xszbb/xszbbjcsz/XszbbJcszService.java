package com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤����ģ��
 * @�๦������: TODO(ѧ��֤�����������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����02:55:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbJcszService extends SuperServiceImpl<XszbbJcszForm, XszbbJcszDao>{
	
	private XszbbJcszDao dao = new XszbbJcszDao();
	
	public XszbbJcszService() {
		super.setDao(dao);
	}
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public XszbbJcszForm getModel(XszbbJcszForm t)throws Exception{
		
		XszbbJcszForm  model= dao.getModel();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
//		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
//		
//		if (model != null) {
//			
//			if (Constants.CLOSE.equals(model.getSqkg())){
//				model.setIsopen("false");
//			} else if (!StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
//				
//				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
//				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
//				
//				if(now.before(startTime)||now.after(endTime)){
//					model.setIsopen("false");
//				} else{
//					model.setIsopen("true");
//				}
//					
//			} else if (StringUtil.isNull(model.getSqkssj())&&!StringUtil.isNull(model.getSqjssj())){
//				
//				Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
//				if(now.after(endTime)){
//					model.setIsopen("false");
//				} else{
//					model.setIsopen("true");
//				}
//				
//			} else if (!StringUtil.isNull(model.getSqkssj())&&StringUtil.isNull(model.getSqjssj())){
//				Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
//				if(now.before(startTime)){
//					model.setIsopen("false");
//				} else{
//					model.setIsopen("true");
//				}
//			}else {
//				model.setIsopen("true");
//			}
//		}
		return model;
	}
	

//	if (model != null) {
//		if (Constants.CLOSE.equals(model.getSqkg())){
//			model.setIsopen("false");
//		} else if (!StringUtil.isNull(model.getSqjssj())){
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
//			Date startTime=sdf.parse(model.getSqkssj()+" 00:00:00");
//			Date endTime=sdf.parse(model.getSqjssj()+" 23:59:59");
//			
//			String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
//			Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
//			
//			if (now.before(startTime) || now.after(endTime)){
//				model.setIsopen("false");
//			} else {
//				model.setIsopen("true");
//			}
//		} else {
//			model.setIsopen("true");
//		}
//	}
//	return model;
	
	/**
	 * 
	 * @����:TODO(��ѯ����������Ϣ(�޲���))
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:20:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * XszbbJcszForm �������� 
	 * @throws
	 */
    public XszbbJcszForm getModel()throws Exception{
		
		return getModel(new XszbbJcszForm());
	}
	
    /**
     * 
     * @����:TODO(�������������Ϣ)
     * @���ߣ�Dlq[���ţ�995]
     * @���ڣ�2013-12-26 ����09:21:10
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
	public boolean saveJcsz(XszbbJcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;
		
	}
}
