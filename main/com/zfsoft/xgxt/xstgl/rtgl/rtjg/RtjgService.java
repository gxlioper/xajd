/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-7 ����10:41:06 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-7 ����10:41:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RtjgService extends SuperServiceImpl<RtjgForm, RtjgDao> {
	
	//��ȡ������Ϣ
	public HashMap<String, String> getStxxMap(RtjgForm rtjg) throws Exception{
		return dao.getStxxMap(rtjg);
	}
	
	//��ȡ���ų�Ա��Ϣ�����usertype = stu,ֻ�ܲ鿴���˵���Ϣ��
	public List<HashMap<String, String>> getStxxCylist(RtjgForm rtjg,User user){
		return dao.getStxxCylist(rtjg, user);
	}
	
	//��ȡ�������ÿ��أ����ڽ��ά�����ж��Ƿ�����޸�Ȩ�ޣ�sqkg:1:���裻sqkg:0;�����裩
	public String getSqShKg(String stid) throws Exception{
		return dao.getSqShKg(stid);
	}
	
	//���Ž��ά�����ѧ����ѧ��ѡ��ҳ��
	public List<HashMap<String, String>> getXsxxList(RtjgForm model, User user,String xhArr) throws Exception {
		if("".equals(xhArr)||null==xhArr){
			model.setXhArr(new String[]{});
		}
		else{
			model.setXhArr(xhArr.split(","));
		}
		return dao.getXsxxList(model, user);

	}
	
	//���³�Ա�������� by yxy 
	public boolean saveAddRtCySl(String stid,String num) throws Exception{
		return dao.saveAddRtCySl(stid, num);
	}
	
	//���³�Ա��������(del) by yxy 
	public boolean saveDelRtCySl(String stid,String num) throws Exception{
		return dao.saveDelRtCySl(stid, num);
	}
	
	//��ȡ��Ա����б�
	public List<HashMap<String, String>>  getRylbList(){
		return dao.getRylbList();
	}
	
	//��ȡ������html
	public String getSelectOption(){
		return dao.getSelectOption();
	}
}
