/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-12 ����02:42:07 
 */  
package com.zfsoft.xgxt.khgl.khpf;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.OtmMapping;
import common.Globals;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import xgxt.action.Base;
import xgxt.base.DBEncrypt;
import xgxt.base.Encrypt;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.io.File;
import java.util.*;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���˹���
 * @�๦������: Service
 * @���ߣ�cq [����:785]
 * @ʱ�䣺 2015-8-12 ����02:42:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KhpfService extends SuperServiceImpl<KhpfForm, KhpfDao> {
	
	public static final String SFNZ_N = "1";	//�Ƿ�����_��
	public static final String SFNZ_Y = "2";	//�Ƿ�����_��
	public static final String SFTJ_Y = "1";	//�Ƿ��ύ_��
	public static final String SFTJ_N = "2";	//�Ƿ��ύ_��
	public static final String YHLX_XS = "1";	//�û�����_ѧ��
	public static final String YHLX_JS = "2";	//�û�����_��ʦ
	public static final String YHLX_BR = "3";	//�û�����_����
	public static final String PFZT_WP = "0";	//����״̬_δ��
	public static final String PFZT_YP = "1";	//����״̬_����
	public static final String KHGL_PFZT = "1";	//����
	
	
	public static final String BR_BR = "br";		//�Լ�������ע��
	public static final String XS_JS_BZR = "bzr";
	public static final String XS_JS_FDY = "fdy";
	public static final String XS_JS_BZRFDY = "bzrfdy";
	public static final String XS_JS_BYX = "byx";
	public static final String XS_JS_XX = "xx";
	public static final String XS_XS_BB = "bb";
	public static final String JS_XS_BZR = "bzr";
	public static final String JS_XS_FDY = "fdy";
	public static final String JS_XS_BZRFDY = "bzrfdy";
	public static final String JS_XS_BYX = "byx";
	public static final String JS_XS_XX = "xx";
	public static final String JS_JS_BBM = "bbm";
	public static final String JS_JS_QX = "qx";
	
	
	/**
	 * 
	���ֶ���	���ݷ�Χ			���ݷ�Χ����		��ע
	-------------------------------------------------------------------------
						����   ��   ����			
	����		����				br				ֻ���Լ����Լ�
	
	-------------------------------------------------------------------------			
						ѧ��   ��   ��ʦ			
	ѧ��		������			bzr				ֻ�ܶ��Լ����������
			����Ա			fdy				ֻ�ܶ��Լ��ศ��Ա��
			������+����Ա	bzrfdy			ֻ�ܶ��Լ��������+����Ա��
			��Ժϵ			byx				ֻ�ܶ��Լ�Ժϵ��ʦ����ֻ���ж�ѧԺ���ţ�
			ȫУ				xx				�ܶ����б����ֶ�����
			
	-------------------------------------------------------------------------			
						ѧ��   ��   ѧ��			
	ѧ��		����				bb				���Լ���ѧ����
	
	-------------------------------------------------------------------------			
					��ʦ   ��   ѧ��			
	��ʦ		������			bzr				�����������༶ѧ��
			����Ա			fdy				����Ա�����༶ѧ��
			������+����Ա	bzrfdy			������+����Ա�����༶ѧ��
			��Ժϵ			byx				��Ժϵ������ѧ��
			ȫУ				xx 				�ܶ����б����ֶ�����
			
	-------------------------------------------------------------------------			
						��ʦ   ��   ��ʦ			
	��ʦ		������			bbm				���Լ�ͬһ���Ž�ʦ
			ȫУ				qx				����Ա�������н�ʦ  
	 **/

	
	/**
	 * @throws Exception  
	 * @����:�������ֲ�ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-12 ����04:30:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhpflList(KhpfForm model, User user) throws Exception {
		
		//ѧ���ͽ�ʦ��һ���Ĳ�ѯ����
		if("stu".equalsIgnoreCase(user.getUserType())){
			return dao.getXsPageList(model, user);
		}
		return dao.getJsPageList(model, user);
	}


	/**
	 * @throws Exception  
	 * @����:���ݵ�½�˲�ѯ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-13 ����04:36:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> quePf(KhpfForm model, User user) throws Exception {
		
		String userType = user.getUserType();
		List<HashMap<String, String>> quePf = null;
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			return dao.jsPf(model, user);
		}else{
		if("stu".equals(userType) && YHLX_JS.equals(model.getKhlx())){
			//ѧ������ʦ
			quePf = dao.stuEvaTea(model, user);			
		}else if ("stu".equals(userType) && YHLX_XS.equals(model.getKhlx())){
			//ѧ����ѧ��
			quePf = dao.stuEvaStu(model, user);	
		}else if (YHLX_XS.equals(model.getKhlx())){
			//��ʦ��ѧ��
			quePf = dao.teaEvaStu(model, user);	
		}else if (YHLX_JS.equals(model.getKhlx())){
			//��ʦ����ʦ
			quePf = dao.teaEvaTea(model, user);	
		}
		}
		
		return quePf;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������Ŀid�Ϳ��˱�id��ѯ��Ŀ��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-14 ����09:43:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @param khbid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public KhpfForm getXmKhb(String xmid, String khbid) throws Exception{
		return dao.getXmKhb(xmid, khbid);
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���濼������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-14 ����03:28:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveKhpf(KhpfForm form, User user) throws Exception{
		return dao.saveKhpf(form, user);		
	}
	
	
	/**
	 * 
	 * @����:���ݿ��˱�ID��ѯ������Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-14 ����04:07:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmInfo(String xmid){
		return dao.getXmInfo(xmid);
	}
	
	public HashMap<String, String> getPfxxInfo(String xmid,User user){
		return dao.getPfxxInfo(xmid,user);
	}

	public HashMap<String, String> getPfxxInfo_HNCS(String xmid,String xh){
		return dao.getPfxxInfo_HNCS(xmid, xh);
	}
	
	
	/**
	 * 
	 * @����:��ѯ��Ա��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-14 ����04:10:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getRyInfo(String ry){
		
		HashMap<String, String> ryInfo = dao.getJsInfo(ry);
		
		if(null == ryInfo||ryInfo.size()==0){
			ryInfo = dao.getXsInfo(ry);
		}
		
		return ryInfo;
	}


	/**
	 * @throws Exception  
	 * @����:���濼�˷���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-15 ����11:41:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveKhfs(KhpfForm model, User user) throws Exception {
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			return dao.saveKhfs_HNCS(model, user);
		}else{
			dao.saveKhfs(model, user);
			 return dao.saveKhzt(model, user);
		}
		 
	}
	
	public boolean saveBz(KhpfForm model, User user) throws Exception {
		return dao.saveBz(model, user);
	}
	
	/** 
	 * @����:���濼���������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-2 ����04:15:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveKhYjjy(KhpfForm model, User user) throws Exception {
		return dao.saveKhYjjy(model, user);
	}
	
	/** 
	 * @����:��ȡ���۽������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-2 ����05:07:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String getKhYjjy(KhpfForm model, User user) throws Exception {
		return dao.getKhYjjy(model, user);
	}


	/**
	 * @throws Exception  
	 * @����: ��ѯ���˷����б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-15 ����01:43:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfList(KhpfForm model, User user) throws Exception {
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			return dao.getKhPfList(model, user);
		}else{
		// ���濼������״̬��
		dao.saveKhzt(model, user);
		
		return dao.getPfList(model,user);
		}
	}
	/**
	 * 
	 * @����:�������֣����ϳ���ѧԺ���Ի���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-8-9 ����04:39:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhPfList(KhpfForm model, User user) throws Exception {
		return dao.getKhPfList(model,user);
	}
	
	public List<HashMap<String, String>> getPfTjList(KhpfForm model, User user) throws Exception {
		return dao.getPfTjList(model,user);
	}
	
	public HashMap<String,String> getZpxx(KhpfForm model) throws Exception {
		return dao.getZpxx(model);
	}
	
	public HashMap<String,String> getZpxxList(String xh) throws Exception {
		return dao.getZpxxList(xh);
	}
	
	
	/**
	 * 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-17 ����11:39:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsCanSubmit(KhpfForm model, User user){
		return dao.checkIsCanSubmit(model, user);
	}


	/**
	 * @throws Exception  
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-17 ����11:51:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean submitBjzcf(KhpfForm model, User user) throws Exception {		
//		boolean submitBjzcf=checkIsCanSubmit(model,user);
		model.setShzt(KHGL_PFZT);
		//�ύ״̬�޸�ǰ�ٴ������������ݣ�����Ч���Ż���ɾ����
		boolean submitBjzcf=dao.batchSaveKhfs(model,user);
		if(submitBjzcf){
			submitBjzcf=dao.submitBjzcf(model, user);
		}
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//���������Ի����㽭��ҵ��ʦѧԺ��������������
			submitBjzcf=dao.submitYjjy(model, user);
		}
		return submitBjzcf;
	}
	
	public boolean submitKhpf(KhpfForm model, User user) throws Exception {
		boolean result = false;
		//�ύǰ�ٴα���һ�·�������
		model.setShzt(KHGL_PFZT);
//		return dao.submitKhpf(model, user);
		result = dao.batchSubmitKhpf(model);
		//���ݺ��ύ״̬�ı�󣬸������������������һ��
		if(result){
			result = dao.afterTheCurrently(model,user);
		}
		return result;
	}
	/**
	 * 
	 * @����:���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-1-11 ����05:05:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean sh(KhpfForm model, User user) throws Exception {		
		String[] khdxrs = model.getKhdxr().split(",");
		boolean flag=true;
		
		for (int i = 0; i < khdxrs.length; i++) {
			KhpfForm pfshForm = new KhpfForm();
			pfshForm.setXmid(model.getXmid());
			pfshForm.setKhbid(model.getKhbid());
			pfshForm.setXmszid(model.getXmszid());
			pfshForm.setKhdxr(khdxrs[i]);
			pfshForm.setPfr(user.getUserName());
			pfshForm.setShzt(KHGL_PFZT);
			pfshForm.setPflx(model.getPflx());
			flag=dao.beforeTheCurrently(pfshForm, user);
			flag=dao.submitKhpf(pfshForm, user);
			flag=dao.afterTheCurrently(pfshForm,user);
		}
		return flag;
	}

	/**
	 *  ���ϳ��г���.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-19 10:14
	 * @param model
	 * @param user
	 * @return boolean
	 * @throw
	 */
	public boolean cx(KhpfForm model, User user) throws Exception {
		String[] khdxrs = model.getKhdxr().split(",");
		boolean flag=true;

		for (int i = 0; i < khdxrs.length; i++) {
			KhpfForm pfshForm = new KhpfForm();
			pfshForm.setXmid(model.getXmid());
			pfshForm.setKhbid(model.getKhbid());
			pfshForm.setXmszid(model.getXmszid());
			pfshForm.setKhdxr(khdxrs[i]);
			pfshForm.setPfr(user.getUserName());
			pfshForm.setShzt(PFZT_WP);
			pfshForm.setPflx(model.getPflx());
			flag=dao.submitKhpf(pfshForm, user);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @����:������Ŀidɾ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-8-20 ����02:37:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delKhpfForXmid(String[] xmid) throws Exception{
		
		boolean bl = false;
		
		if(dao.delKhpfForXmid(xmid)){
			bl = dao.delWxPfmx();
		}
		return bl;
	}
	
	public boolean isJs(KhpfForm model){
		return dao.isJs(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����ȡ���ύ[�����������ѧԺ���Ի�����]
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-7 ����03:02:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param khpfForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelTjRecord(KhpfForm khpfForm) throws Exception{
		return dao.cancelTjRecord(khpfForm);
	}

	/**
	 *  �����û���Ϣ����ȡ�����������İ༶�İ�������������Ϣ�б�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 11:51
	 * @param user
	 * @return List<HashMap<String,String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getBzpfmmList(User user) {

		List<HashMap<String,String>> bzpfmmList = dao.getBzpfmmList(user);
		if(bzpfmmList != null){
			DBEncrypt encrypt = new DBEncrypt();
			//����
			for(HashMap<String,String> map:bzpfmmList){
				String password = map.get("password");
				if(StringUtils.isNotNull(password)){
					map.put("password",encrypt.dCode(password.getBytes()));
				}
			}
		}

		return bzpfmmList;
	}

	/**
	 *  �����������뱣��.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 16:50
	 * @param usernameArr
	 * @param passwordArr
	 * @return boolean
	 * @throw
	 */
	public boolean scbzpfmmSave(String[] usernameArr, String[] passwordArr) throws Exception {

		boolean rs = true;
		if(usernameArr != null){
			DBEncrypt dbEncrypt = new DBEncrypt();
			Encrypt encrypt = new Encrypt();
			for (int i=0;i<usernameArr.length;i++){
				String username = usernameArr[i];
				String password = passwordArr[i];

				if(StringUtils.isNull(password)){
					continue;
				}
				rs = dao.scbzpfmmSave(username,dbEncrypt.eCode(password),encrypt.encrypt(password));
			}
		}

		return rs;
	}

	/**
	 *  ��ȡ�༶�����ɼ����б����˼�¼������һ��ָ���ܷ֣�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 09:52
	 * @param model
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
    public List<HashMap<String,String>> getBjzccjzbList(KhpfForm model, User user) throws Exception {

		return dao.getBjzccjzbList(model, user);
    }

	/**
	 *  ���ɰ༶�����ɼ��ܱ�Excel�ļ�����.
	 *  <p>���ϳ���ѧԺ</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 10:36
	 * @param resultList
	 * @return java.io.File[]
	 * @throw Exception
	 */
	public File[] getBjzccjzbFiles(List<HashMap<String, String>> resultList) throws Exception {

		List<File> fileList = new ArrayList<File>();
		Map<String,List<Map<String,String>>> classMap = new OtmMapping().setResultMap("bjdm", resultList).getResultMap();

		Collection<List<Map<String,String>>> classCollection = classMap.values(); //���ѧ���б��ϣ��԰༶Ϊ��λ
		Iterator<List<Map<String,String>>> iterator = classCollection.iterator();

		while(iterator.hasNext()){
			//������ظ�ʽ
			WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//���ñ�������
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������

			WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������

			title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
			title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�
//		title_cf.setBackground(Colour.YELLOW);	//���ñ��ⱳ��ɫ

			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

			body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
			body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

			List<Map<String,String>> list = iterator.next();	//�༶��Ӧ�����ѧ���б�
			String xymc = list.get(0).get("xymc");	//ѧԺ����
			String zymc = list.get(0).get("zymc");	//רҵ����
			String bjdm = list.get(0).get("bjdm");	//�༶����
			String bjmc = list.get(0).get("bjmc");	//�༶����
			String xn = list.get(0).get("xn");		//ѧ��
			String title = "���ϳ���ѧԺѧ�����������ۺ����ʲ������ܱ�";	//����
			String title_tip = "ѧԺ���ƣ�"+(xymc==null?"":xymc)+"    "
					              + "רҵ���ƣ� "+(zymc==null?"":zymc)+"    "
					 			  + "�༶���ƣ� "+(bjmc==null?"":bjmc)+"    "
								  + "ѧ�꣺ "+(xn==null?"":xn);

			//��Excel
			String fileName = bjmc+"��"+bjdm+"��"+ ".xls";
			File file = new File(System.getProperty("java.io.tmpdir"),fileName);

			if(!file.exists()){
				file.createNewFile();
			}

			//����������
			WritableWorkbook wwb = Workbook.createWorkbook(file);

			//����������
			WritableSheet sheet = wwb.createSheet(bjmc, 0);

			//���ø����п�
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 14);
			sheet.setColumnView(2, 14);
			sheet.setColumnView(3, 18);
			sheet.setColumnView(4, 18);
			sheet.setColumnView(5, 14);
			sheet.setColumnView(6, 14);
			sheet.setColumnView(7, 14);
			sheet.setColumnView(8, 14);
			sheet.setColumnView(9, 14);

			//�ϲ���Ԫ��
			sheet.mergeCells(0, 0, 9, 0);	//���ϳ���ѧԺѧ�����������ۺ����ʲ������ܱ�
			sheet.mergeCells(0, 1, 9, 1);	//ѧԺ רҵ �༶ ѧ��

			//�������⼰��ͷ
			Label t_0_0 = new Label(0, 0,title,title_cf);
			Label h_0_1 = new Label(0,1,title_tip,body_cf);

			Label h_0_2 = new Label(0,2,"ѧ����",head_cf);
			Label h_1_2 = new Label(1,2,"����",head_cf);
			Label h_2_2 = new Label(2,2,"�Ա�",head_cf);
			Label h_3_2 = new Label(3,2,"˼��Ʒ������",head_cf);
			Label h_4_2 = new Label(4,2,"רҵ�Ļ�����",head_cf);
			Label h_5_2 = new Label(5,2,"��������",head_cf);
			Label h_6_2 = new Label(6,2,"����ˮƽ",head_cf);
			Label h_7_2 = new Label(7,2,"�۷�",head_cf);
			Label h_8_2 = new Label(8,2,"�ܷ�",head_cf);
			Label h_9_2 = new Label(9,2,"�༶����",head_cf);

			sheet.addCell(t_0_0);
			sheet.addCell(h_0_1);

			sheet.addCell(h_0_2);
			sheet.addCell(h_1_2);
			sheet.addCell(h_2_2);
			sheet.addCell(h_3_2);
			sheet.addCell(h_4_2);
			sheet.addCell(h_5_2);
			sheet.addCell(h_6_2);
			sheet.addCell(h_7_2);
			sheet.addCell(h_8_2);
			sheet.addCell(h_9_2);

			//����������Ԫ��
			if(list.size()>0){
				for(int j=0;j<list.size();j++){
					Map<String,String> map = list.get(j);
					Label xjh = new Label(0, j+3, map.get("xjh"), body_cf);	//ѧ����
					Label xm = new Label(1, j+3, map.get("xm"), body_cf);		//����
					Label xb = new Label(2, j+3, map.get("xb"), body_cf);		//�Ա�
					Label sxpdszf = new Label(3, j+3, map.get("sxpdszf"), body_cf);		//˼��Ʒ������
					Label zywhszf = new Label(4, j+3, map.get("zywhszf"), body_cf);		//רҵ�Ļ�����
					Label sxszf = new Label(5, j+3, map.get("sxszf"), body_cf);		//��������
					Label nlspf = new Label(6, j+3, map.get("nlspf"), body_cf);		//����ˮƽ
					Label kf = new Label(7, j+3, map.get("kf"), body_cf);	//�۷�
					Label zf = new Label(8, j+3, map.get("zf"), body_cf);		//�ܷ�
					Label pm = new Label(9, j+3, map.get("pm"), body_cf);		//�༶����

					sheet.addCell(xjh);
					sheet.addCell(xm);
					sheet.addCell(xb);
					sheet.addCell(sxpdszf);
					sheet.addCell(zywhszf);
					sheet.addCell(sxszf);
					sheet.addCell(nlspf);
					sheet.addCell(kf);
					sheet.addCell(zf);
					sheet.addCell(pm);
				}
			}
			wwb.write();
			wwb.close();
			fileList.add(file);
		}
		return fileList.toArray(new File[]{});
	}
}
