/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-26 ����05:30:12 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.kqgl.cssz.KqCsszDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-26 ����05:30:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqwhService extends SuperServiceImpl<KqwhForm, KqwhDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * ��ȡkqwhform
	 */
	public KqwhForm getModel(String id) throws Exception{
		return dao.getModel(id);
	}
	
	/**
	 * 
	 * @����:��ȡ��ǰѧ�ꡢѧ�ڡ��·�����ܴ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����11:20:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDqZc(){
		return dao.getDqZc();
	}

	/** 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����02:16:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKqinfo(String id) {
		return dao.getKqinfo(id);
	}

	/** 
	 * @����:���濼����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����02:21:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveKqinfo(KqwhForm model, List<KqwhForm> list) throws Exception {
		boolean flag = runUpdate(model);
		if(flag){
			
			//�������״̬
			flag = saveTj(model);
			
			if(flag){
				flag = dao.delKqInfo(model.getId());
			}
			
			if(flag){
				List<String[]> params = new ArrayList<String[]>();
				for(int i=0;i<list.size();i++){
					String[] info = new String[6];
					info[0] = UniqID.getInstance().getUniqIDHash();
					info[1] = model.getId();
					info[2] = list.get(i).getXh();
					info[3] = list.get(i).getBjcs();
					info[4] = list.get(i).getSjcs();
					info[5] = list.get(i).getKkjs();
					params.add(info);
				}
				flag = dao.saveKqInfo(params);
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�������״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-28 ����09:47:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTj(KqwhForm model)throws Exception{
		
		boolean flag = false;
		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		KqCsszDao kqCsszDao = new KqCsszDao();
		String splc = kqCsszDao.getModel().getSplc();
		model.setSplc(splc);
		Date date = new Date();
        DateFormat df2 = DateFormat.getDateTimeInstance();
        model.setJlsj(df2.format(date));
		
		//���¿��ڽ����
		flag = runUpdate(model);
		
		// ���������Ϣ
		if (!"save".equals(model.getType())&&flag) {
			flag = shlc.runSubmit(model.getId(), splc, model.getBjdm(), "rcsw_zjsy_kqsh.do", "rcsw_zjsy_kqwh.do");
		}
		
		return flag;
	}

	/** 
	 * @����:��ѯ�ж�������¼�����ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����07:38:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> isCanSubmit(String values, KqwhForm kqwhForm, User user) throws Exception {
		return dao.isCanSubmit(values, kqwhForm, user);
	}

	
	/** 
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����08:39:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public int[] submit(String values, KqwhForm model, User user) throws Exception {
		
		//��ѯ��Ҫ�ύ��ѧ������
		List<HashMap<String, String>> isCanSubmit = isCanSubmit(values,model,user);
		
		int okNum = 0;
		int notOkNum = 0;
		for (int i = 0; i < isCanSubmit.size(); i++) {
			KqwhForm kqwhForm = new KqwhForm();
			kqwhForm.setId(isCanSubmit.get(i).get("id"));
			kqwhForm.setXn(isCanSubmit.get(i).get("xn"));
			kqwhForm.setXq(isCanSubmit.get(i).get("xq"));
			kqwhForm.setYf(isCanSubmit.get(i).get("yf"));
			kqwhForm.setZc(isCanSubmit.get(i).get("zc"));
			kqwhForm.setBjdm(isCanSubmit.get(i).get("bjdm"));
			kqwhForm.setCqrs(isCanSubmit.get(i).get("cqrs"));
			kqwhForm.setBz(isCanSubmit.get(i).get("bz"));
			kqwhForm.setJlr(user.getUserName());
			kqwhForm.setType("submit");
			
			if(saveTj(kqwhForm)){
				okNum++;
			}else{
				notOkNum++;
			}
		}
		int[] result = {okNum,notOkNum};
		return result;
	}
	
	
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-28 ����10:40:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}

}
