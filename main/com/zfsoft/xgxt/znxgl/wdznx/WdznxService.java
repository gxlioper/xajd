/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-27 ����06:56:07 
 */
package com.zfsoft.xgxt.znxgl.wdznx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbService;
import com.zfsoft.utils.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-8-27 ����06:56:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WdznxService extends SuperServiceImpl<WdznxForm, WdznxDao> {
	private SxbService sxbservice = new SxbService();
	public List<HashMap<String, String>> getFjxList(WdznxForm t, User user)
			throws Exception {
		return dao.getFjxList(t, user);
	}
	
	//�������list
	public List<HashMap<String, String>> getZtlbList() throws Exception{
		return dao.getZtlbList();
	}
	
	//��ȡ���ͱ��в������ݵ�xjbh,���ڲ�����ձ���
	public HashMap<String,String> getXjbh(WdznxForm t){
		return dao.getXjbh(t);
	}
	
	//��ʦ����
	public boolean saveteaXX(WdznxForm model) throws Exception{
		 boolean result = true;
		 String[] jsrbh = model.getJsrbhs();
		 String xjbh = UniqID.getInstance().getUniqIDHash();
		 xjbh = xjbh.toUpperCase();
		    //���ű����һ�����ż�¼
		    if(model != null){
		    	model.setXjbh(xjbh);
		    	 result = this.save(model);
		    	 if(result){
		    		  //�ż���Ų�Ϊ�յ�ʱ��ŶԽ��ܱ���в���
		    		  if(!StringUtil.isNull(xjbh)){
		    			  for(int i=0;i<jsrbh.length;i++){
						    	SxbForm sxb = new SxbForm();
						    	sxb.setJsrbh(jsrbh[i]);
						    	sxb.setXjbh(xjbh);
						    	result = sxbservice.save(sxb);
						    	if(!result){
						    		//�������ʧ�ܣ����ؼ�
						    	}
						 }
		    		  }else{
		    			  //�ż����Ϊ�գ�����false
		    			  return false;
		    			  
		    		  }
		    		 
				 }else{
					//�����벻�ɹ�ʱ,����false
					 return false;
				 }
		    }
		   return result;
	}
	
	//ѧ������
	public boolean savestuXX(WdznxForm model) throws Exception{
		 boolean result = true;
		 String xjbh = UniqID.getInstance().getUniqIDHash();
		 xjbh = xjbh.toUpperCase();
		    //���ű����һ�����ż�¼
		    if(model != null){
		    	 model.setXjbh(xjbh);
		    	 result = this.save(model);
		    	 if(result){
		    		  if(!StringUtil.isNull(xjbh)){
		    			  SxbForm sxb = new SxbForm();
		    			  sxb.setJsrbh(model.getJsrbh());
					      sxb.setXjbh(xjbh);
		    			  result = sxbservice.save(sxb);
		    		  }else{
		    			  //�ż����Ϊ�գ�����false
		    			  return false;
		    			  
		    		  }
				 }else{
					//�����벻�ɹ�ʱ,����false
					 return false;
				 }
		    	
		    }
		   return result;
	}
	
	//��ȡѧ���б�
	public List<HashMap<String, String>> getXsxxList(WdznxForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		return dao.getXsxxList(model, user);
	}
	
	//��ȡ��ʦ�б�
	public List<HashMap<String, String>> getTeaxxList(WdznxForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		return dao.getTeaList(model, user);
	}
	
	//�ż��鿴
	public HashMap<String, String> XjckMap(WdznxForm t){
		return dao.XjckMap(t);
	}
	
	/**
     * 
     * @����:����
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-12-7 ����03:19:04
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param sx
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean save(WdznxForm t) throws Exception{
    	return dao.save(t);
    } 
	
}
