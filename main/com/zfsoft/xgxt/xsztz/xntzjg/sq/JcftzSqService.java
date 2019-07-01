/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-29 ����09:04:00 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-29 ����09:04:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzSqService extends SuperServiceImpl<JcftzSqForm, JcftzSqDao>{
	private JcftzSqDao dao = new JcftzSqDao();
	private XmsbjgDao xmsbjgDao = new XmsbjgDao();
	
	/** 
	 * @����:�õ��϶�ѧ���б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����02:27:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForRenDing(JcftzSqForm t, User user) throws Exception{
		return dao.getPageListForRenDing(t, user);
	}
	
	public HashMap<String, String> getXmxx(String xmdm){
		return dao.getXmxx(xmdm);
	}
	
	public HashMap<String, String> getXmxxByjgid(String jgid){
		return dao.getXmxxByjgid(jgid);
	}
	
	/** 
	 * @����:ʵʱ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����02:27:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveJcftzSq(JcftzSqForm t) throws Exception{
		return dao.saveJcftzSq(t);
	}
	
	/** 
	 * @����:�Ƿ���ύ
	 * @���ߣ�����[���ţ�1282]
	 * @�޸��ߣ�yxy������������Ŀ�ж�
	 * @���ڣ�2016-3-29 ����04:33:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public void checkIsCanSubmit(JcftzSqForm form) throws Exception {
		String[] ids = form.getXmdms();
		//����ģʽ
		String[] csms = form.getCsms();
		boolean result = true;
		for(int i = 0;i<ids.length;i++){
			List<HashMap<String, String>> rymap = null;
			String Csms = csms[i];
			if("1".equals(Csms)){
				rymap = dao.getJcfTzRyxx(ids[i]);
			}else if("2".equals(Csms)){
				rymap = dao.getJcfTzTtxx(ids[i]);
			}
			for(int j=0;j<rymap.size();j++){
				String jgid = rymap.get(j).get("jgid");
				if("1".equals(Csms)){
					result = dao.checkFz(jgid);
				}else{
					result = dao.checkTtFz(jgid);
				}
				form.setJgid(jgid);
				if (!result) {
					HashMap<String,String> map = getXmxx(ids[i]);
					form.setTzhjcf((map.get("jcxf")));
					if("1".equals(Csms)){
						dao.saveJcftzSq(form);
					}else{
						dao.saveJcftzTtSq(form);
					}
					
				}
				if("1".equals(Csms)){
					result = dao.checkQq(jgid);
				}else{
					result = dao.checkTtQq(jgid);
				}
				
				if (!result) {
					form.setSfqq("0");
					if("1".equals(Csms)){
						dao.saveJcftzSq(form);
					}else{
						dao.saveJcftzTtSq(form);
					}
				}
			}
			
		}
	}
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����04:46:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String tj(JcftzSqForm form, User user) throws Exception{
		List<String> failXms = new ArrayList<String>();
		String[] ids = form.getIds();
		String splc = dao.getShlc();
		form.setRdsplc(splc);
		form.setXfrdsqzt(Constants.YW_SHZ);
		for(int i = 0;i<ids.length;i++){
			String sqid = UniqID.getInstance().getUniqIDHash();
			form.setSqid(sqid);
			boolean result = dao.tj(ids[i], user, form);
			if (!result) {
				failXms.add(ids[i]);
			}
			ShlcInterface shlc = new CommShlcImpl();
			XmsbjgForm model = xmsbjgDao.getModel(ids[i]);
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXmdm(), "sztz_jcftz_sh.do", "sztz_jcftz_sq.do");
			}
		}
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}
		
	} 
	
	public String getFs(String jxdm){
		return dao.getFs(jxdm);
	}
	
	/**
	 * 
	 * @����:������Ŀ�ϱ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-4 ����01:42:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTtRenDing(JcftzSqForm t, User user)
	throws Exception {
		return dao.getPageListForTtRenDing(t, user);
   }
	
	/** 
	 * @����:������Ŀʵʱ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����02:27:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveJcftzTtSq(JcftzSqForm t) throws Exception{
		return dao.saveJcftzTtSq(t);	
	}
}
