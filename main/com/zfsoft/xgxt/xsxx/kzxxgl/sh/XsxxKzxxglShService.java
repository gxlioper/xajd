/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����09:35:23 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.sh;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgAction;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-19 ����09:35:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglShService extends
		SuperServiceImpl<XsxxKzxxglShForm, XsxxKzxxglShDAO> {
	private ShlcInterface shlc = new CommShlcImpl();	
	
	private ExtendService extendService = new ExtendService();
	
	private XsxxKzxxglJgService jgService = new XsxxKzxxglJgService();
	/**
	 * @throws Exception  
	 * @����: ������˱���
	 * @���ڣ�2014-4-25 ����04:12:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(XsxxKzxxglShForm model, User user) throws Exception {	
		XsxxKzxxglShForm dataModel = dao.getModel(model.getSqid());
		ShxxModel shxxModel = new ShxxModel();
		shxxModel.setYwid(model.getSqid());
		shxxModel.setShlc(model.getSplc());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShyj(model.getShyj());
		shxxModel.setShzt(model.getShjg());
		shxxModel.setThgw(model.getThgw());
		shxxModel.setGwid(model.getXtgwid());
		shxxModel.setSqrid(model.getXh());
		shxxModel.setTzlj("");
		shxxModel.setTzljsq("");
		boolean reuslt = false;
		try {
			String zhzt  = shlc.runAuditing(shxxModel); //��˲���{����һ�����ݵ���˱���}
			dataModel.setShzt(zhzt);//��ȡ���״̬��־��������Model
			reuslt = dao.runUpdate(dataModel);//���������{�������������״̬��Ϣ}
			if(reuslt && Constants.SH_TG.equals(zhzt)){ //������ͨ�� ����һ�����ݵ������
				//�����������¼
				XsxxKzxxglJgForm jgModel = new XsxxKzxxglJgForm();
				String jgid = UniqID.getInstance().getUniqIDHash();
				jgModel.setJgid(jgid);
				jgModel.setJrsj(DateUtils.getCurrTime());
				jgModel.setSjly("1");
				jgModel.setSqid(dataModel.getSqid());
				jgModel.setXh(dataModel.getXh());
				
				XsxxKzxxglJgForm modelByXh = jgService.getModelByXh(dataModel.getXh());
				if(modelByXh != null){
					jgService.deleteByXh(modelByXh.getXh());
					reuslt = extendService.deleteData(modelByXh.getJgid(), XsxxKzxxglJgAction.DATA_EXTEND_MODULE);
				}
				reuslt = jgService.runInsert(jgModel);
				//��չ�ֶ���ʱ���ݵ����ձ�
				reuslt = extendService.copyTempDataToFinal(dataModel.getSqid(), jgid, XsxxKzxxglJgAction.DATA_EXTEND_MODULE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
		
	}

}
