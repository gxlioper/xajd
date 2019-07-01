/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-17 ����10:08:21 
 */ 
package com.zfsoft.xgxt.szdw.thjl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������: ̸����¼ά��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-7-17 ����10:08:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwThjlService extends
		SuperServiceImpl<SzdwThjlForm, SzdwThjlDao> {

	private ShlcInterface shlc = new CommShlcImpl();
private SzdwThjlDao dao = new SzdwThjlDao();
	
	public SzdwThjlService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:������ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-8-13 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public HashMap<String,String> getThjlListById(String id) throws Exception {

		return dao.getThjlListById(id);
	}

	public HashMap<String,String> getSqMap(String id)throws Exception {
		return dao.getSqMap(id);
	}
	/** 
	 * @����:������ѯ̸����¼��Ϣ(���ݴ�ѧ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-16 ����04:59:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getThjlListByIdForWzdx(String id) throws Exception{
		return dao.getThjlListByIdForWzdx(id);
	}
	/**
	 * 
	 * @����:������ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-8-13 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getThjlListByXh(String xh) throws Exception {
		
		return dao.getThjlListByXh(xh);
	}
	
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-19 ����04:04:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThjlListByXhForWzdx(String xh) throws Exception {
		
		return dao.getThjlListByXhForWzdx(xh);
	}
	
	/**
	 * ������̸����¼��Ϣ��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveThjlInfo(SzdwThjlForm model)
			throws Exception {
		
		return dao.saveThjlInfo(model);
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-16 ����04:06:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveThjlInfoForWzdx(SzdwThjlForm model)
			throws Exception {

			return dao.saveThjlInfoForWzdx(model);
}
	
	
	
	/**
	 * 
	 * @����:ɾ��̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-8-13 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delThjlById(String[] id) throws Exception {
		
		return dao.delThjlById(id);
	}
	
	/**
	 * 
	 * @����:����ID�޸�̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	
	public boolean updateThjlInfo(SzdwThjlForm model) throws Exception{
		
		return dao.updateThjlInfo(model);
	}
	public boolean update(SzdwThjlForm model) throws Exception{
		boolean flag = dao.update(model);

		if(flag && "submit".equals(model.getType())){
			flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getZgh(),"szdw_thjl_thjl_sh.do","szdw_thjl_thjl_sq.do");
		}
		return flag;
	}
	/** 
	 * @����:ͨ��ְ����ȡ�ý�ʦ��Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-24 ����10:50:17
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		return dao.getInfoByZgh(zgh);
	}

	/** 
	 * @����:ȡ�ý�ʦ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-24 ����11:49:46
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJsInfoList(SzdwThjlForm myForm) {
		return dao.getJsInfoList(myForm);
	}
	
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-16 ����02:11:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhhwtList(){
		return dao.getKhhwtList();
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-16 ����02:11:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYthrgxList(){
		return dao.getYthrgxList();
	}

	public List<HashMap<String, String>> getAllXjydList(String xh) throws Exception{
		return dao.getAllXjydList(xh);
	}
	public List<HashMap<String,String>> getBjgcj(String xh) throws Exception{
		return dao.getBjgcj(xh);
	}
	public List<HashMap<String,String>> getSqList(SzdwThjlForm t, User user) throws Exception {
		return dao.getSqList(t,user);
	}

	public boolean zjsqBc(SzdwThjlForm model,String type) throws Exception {
		boolean flag;
		if("update".equals(type)) {
			model.setShzt("5");
			flag = dao.updateSq(model);
		} else {
			model.setSqid(UniqID.getInstance().getUniqIDHash());
			model.setSplc(dao.getSplc());
			model.setShzt("5");
			flag = dao.save(model);
		}

		if(flag){
			flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getZgh(),"szdw_thjl_thjl_sh.do","szdw_thjl_thjl_sq.do");
		}
		return flag;
	}

	public boolean del(String[] ids) throws Exception {
		return dao.del(ids);
	}
}
