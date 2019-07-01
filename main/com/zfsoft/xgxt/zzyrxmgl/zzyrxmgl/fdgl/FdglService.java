package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl;


import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @��������������������Ŀ����-��������service
 * @author��Lu.Yao ��1271��
 * @date��2017-10-20 ����11:15:56 
 */
public class FdglService extends SuperServiceImpl<ZzyrxmglActionForm, FdglDao> {

	private FdglDao dao = new FdglDao();
	
	public FdglService() {
		super.setDao(dao);
	}

	/** 
	 * @description����ȡ�ҵĸ���ҳ������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����09:28:00 
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageList2(ZzyrxmglActionForm model,
			User user) throws Exception {
		return dao.getPageList2(model,user);
	}

	/**
	 * @throws Exception  
	 * @description������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����09:56:14 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertFdgl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean result = super.runInsert(model);
		if(result){
			String[] kfxydm = model.getKfxydm().split(",");
			for(int i =  0; i< kfxydm.length ; i++){
				dao.insertKfxydm(kfxydm[i],model.getFdfbid());			
			}
		}
		return result;
	}

	/** 
	 * @description���鿴
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����03:41:54 
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) throws Exception {
		return dao.getModelMap(t);
	}

	/** 
	 * @description���޸�
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����03:45:55 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateFdgl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean result = super.runUpdate(model);
		if(result){
			String[] kfxydm = model.getKfxydm().split(",");
			dao.deleteKfxydm(model);
			for(int i =  0; i< kfxydm.length ; i++){
				dao.insertKfxydm(kfxydm[i],model.getFdfbid());			
			}
		}
		return result;
	}

	/** 
	 * @description����ѯ��������ѧԺlist
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����04:04:08 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKfxydmByid(ZzyrxmglActionForm t) {
		return dao.getKfxydmByid(t);
	}

	/** 
	 * @description��ɾ��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-17 ����04:25:46 
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public int runDelete(String values) throws Exception {
		int num = dao.runDelete(values.split(","));
		if(num > 0){
			String[] id = values.split(",");
			for(int i=0;i<id.length;i++){
				ZzyrxmglActionForm model = new ZzyrxmglActionForm();
				model.setFdfbid(id[i]);
				dao.deleteKfxydm(model);
			}
		}
		return num;
	}

	/** 
	 * @description����ȡ������Ա��Ϣlist
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����11:27:49 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmrsList(ZzyrxmglActionForm model) {
		return dao.getBmrsList(model);
	}

	/** 
	 * @description���������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����02:00:04 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateShzt(ZzyrxmglActionForm model) throws Exception{
		return dao.updateShzt(model);
	}

	/** 
	 * @description���鿴
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-18 ����03:47:39 
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap2(ZzyrxmglActionForm model) {
		return dao.getModelMap2(model);
	}

	/** 
	 * @description����д������¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����08:45:54 
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean addFdjl(ZzyrxmglActionForm model, User user) throws Exception {
		boolean flag = true;
		String[] fdrq = model.getFdrq().split(",");
		String[] fdnr = model.getFdnr().split(",");
		String[] fdbz = model.getFdbz().split(",");
		String[] fdjssj = model.getFdjssj().split(",");
		String[] gs = model.getGs().split(",");
		String[] fddd = model.getFddd().split(",");
		String[] fdpj = model.getFdpj().split(",");
		dao.deleteFdjlPl(model);
		for(int i= 0 ; i < fdrq.length ; i++){
			ZzyrxmglActionForm zmodel = new ZzyrxmglActionForm();
			if(!StringUtils.isNull(fdrq[i])){
				zmodel.setFdxxid(model.getFdxxid());
					zmodel.setFdrq(fdrq[i]);					
				if(fdnr.length > i){
					zmodel.setFdnr(fdnr[i]);				
				}
				if(fdjssj.length > i){
					zmodel.setFdjssj(fdjssj[i]);
				}
				if(gs.length > i){
					zmodel.setGs(gs[i]);
				}
				if(fddd.length > i){
					zmodel.setFddd(fddd[i]);
				}
				if(fdpj.length > i){
					zmodel.setFdpj(fdpj[i]);
				}
				if(fdbz.length > i){
					zmodel.setFdbz(fdbz[i]);
				}else{
					zmodel.setFdbz("");
				}
				zmodel.setFdlx(model.getFdlx());
				flag &= dao.insertFdjl(zmodel);
			}
		}
		return flag;
	}

	/** 
	 * @description����ȡ������¼list
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����08:58:05 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdjlList(ZzyrxmglActionForm model) {
		return dao.getFdjlList(model);
	}

	/** 
	 * @description��ɾ��������¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����09:40:12 
	 * @param model
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int deleteFdjl(ZzyrxmglActionForm model) throws Exception{
		return dao.deleteFdjl(model);
	}

	/** 
	 * @description���ж��ܷ�ɾ��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����04:40:08 
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkCandel(String values) {
		return dao.checkCandel(values);
	}

	/** 
	 * @description���ж���ͬ�⸨������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-19 ����04:57:56 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkYtyfdrs(ZzyrxmglActionForm model) {
		return dao.checkYtyfdrs(model);
	}

	/** 
	 * @description���ж��ܷ�ͬ�⸨��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-23 ����02:49:50 
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkCancancel(ZzyrxmglActionForm model) {
		return dao.checkCancancel(model);
	}
	
	/**
	 * @description	�� ֻ��ÿ����ǰ�����������
	 * @author 		�� ������1282��
	 * @date 		��2017-11-24 ����10:25:35
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean sfksq(){
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		int day = today.getDate();
		if(day <= 5){
			return true;
		}else{
			return false;
		}
	}
	
}
