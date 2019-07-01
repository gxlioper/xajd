/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����09:24:48 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ��Ϣά�� 
 * @���ߣ� Dlq [���ţ�995]
 * @ʱ�䣺 2013-8-2 ����09:24:48
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RcxwxxwhService extends
		SuperServiceImpl<RcxwxxwhForm, RcxwxxwhDao> {

	private RcxwxxwhDao dao = new RcxwxxwhDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static final String BACK = "back";
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};

	public RcxwxxwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * ��ȡ��Ϊ��𼯺�
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����04:06:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jldldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwlbList(String jldldm,
			HttpServletRequest request) {
		return dao.getXwlbList(jldldm, request);
	}
	
	/**
	 * 
	 * ��ȡ��Ϊ���༯��
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����04:06:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		return dao.getXwdlList(request);
	}
	/**
	 * 
	 * @����:��ѯ���ճ���Ϊά�����Ƿ����
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:07:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByXwxxwh(RcxwxxwhForm model, String type)
			throws Exception {
		boolean flag = false;
		if ("save".equalsIgnoreCase(type)) {
			String shzt = dao.checkExistForSave(model);
			if (!"0".equalsIgnoreCase(shzt)) {
				flag = true;
			}
		} else if ("update".equalsIgnoreCase(type)) {
			String shzt = dao.checkExistForUpdate(model);
			if (!"0".equalsIgnoreCase(shzt)) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @����:�����ճ�ά��
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:09:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param checkShzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRcww(RcxwxxwhForm model) throws Exception {
		
	/*	if(checkShzt){
			model.setShzt(Constants.YW_WTJ);
		}*/
		String[] xwlbdmArr=model.getXwlbdmArr();
		String[] fzArray=model.getFzArray();
		String[] xwdldmArr=model.getXwdldmArr();
		String[] fssjArr=model.getFssjArr();
		String[] gflyArr=model.getGflyArr(); 
		//����
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		
		for(int i=0;i<xwlbdmArr.length;i++){
			
			String guid = UniqID.getInstance().getUniqIDHash();
			model.setId(guid);
			
			RcxwxxwhForm form=new RcxwxxwhForm();
			form.setRcxwlbdldm(xwdldmArr[i]);
			//��֤�Ƿ���Ҫ��
			boolean checkShzt=checkForSplc(form);
			if(model.getType().equals("submit")&&checkShzt){
				model.setShzt(Constants.YW_SHZ);//�����
			}else if(model.getType().equals("submit")&&!checkShzt){
				model.setShzt("");//�������
			}else{
				model.setShzt(Constants.YW_WTJ);//δ�ύ
			}
			// ��ȡ��������
			String splc = dao.getShlcID(xwdldmArr[i]);
			model.setSplc(splc);
			
			model.setFz(fzArray[i]);
			model.setRcxwlbdm(xwlbdmArr[i]);
			model.setRcxwlbdldm(xwdldmArr[i]);
			model.setFssj(fssjArr[i]);
			model.setGfly(gflyArr[i]);
			
			//������
			FormFile file = (FormFile) files.get("lbfj"+i);
			
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					model.setFjlj(destFile.getAbsolutePath());
					model.setFjmc(file.getFileName());
				}
			}
			
			boolean insertResult = super.runInsert(model);
			boolean result = false;
			if (checkShzt&&insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
				//�����������
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"rcsw_rcxwwh_rcxwsh.do","rcsw_rcxwwh_rcxwxxwh.do");
			}
			//����Ҫ���������ֱ�Ӱ����ݱ��浽�ճ���Ϊ�������
        	if(!checkShzt&&insertResult&& SUBMIT.equalsIgnoreCase(model.getType())){
        		
        		RcxwjgForm rcxwjgForm = new RcxwjgForm();
        		RcxwjgService rcxwjgService = new RcxwjgService();
        		BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(model));
        		//����о���ó��ѧУ���»�
        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
        			model.setShzt("1");
        			dao.runUpdate(model);
        		}
        		//rcxwjgForm.setId(model.getId());
        		rcxwjgForm.setId(guid);
        		rcxwjgForm.setSjly("1");
        		result = rcxwjgService.runInsert(rcxwjgForm);

        	}
		}
		
//		if( SAVE.equalsIgnoreCase(model.getType())){
//			return insertResult;
//		}
		return true;
	}
	
	
	public boolean submitRcww(RcxwxxwhForm model) throws Exception {
		
		boolean isSplc =  false;
		if(!"�������".equalsIgnoreCase(model.getSplc())){
			isSplc = true;
		}
		String shzt="";
		if(isSplc){
			shzt = Constants.YW_SHZ;
		}
		
		boolean resultRcww = dao.updateRcxwxxwh(model.getId(),model.getSplc(), shzt);
		boolean result = false;
		if(resultRcww&&isSplc){
		// ��ȡ��������
//		String splc = dao.getShlcID(model.getRcxwlbdldm());
		//�����������
		result = shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(),"rcsw_rcxwwh_rcxwsh.do","rcsw_rcxwwh_rcxwxxwh.do");
		}
		if(resultRcww&&!isSplc){//�������ֱ�ӽ������
			if(Globals.XXDM_TJJM.equals(Base.xxdm)){
    			model.setShzt("1");
    			dao.runUpdate(model);
    		}
			RcxwjgForm rcxwjgForm = new RcxwjgForm();
    		RcxwjgService rcxwjgService = new RcxwjgService();
    		RcxwxxwhForm myForm=dao.getModel(model);
    		myForm.setRcxwlbdldm(model.getRcxwlbdldm());
    		BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(myForm));
    		rcxwjgForm.setRcxwxxid(model.getId());
    		//rcxwjgForm.setId(guid);
    		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
    			rcxwjgForm.setSjly("1");
    		}
    		result=rcxwjgService.runInsert(rcxwjgForm);
		}
		return result;
	}
	
	
	public boolean updateRcww(RcxwxxwhForm model,boolean checkShzt) throws Exception {
		
		
		if(checkShzt){
			if(model.getType().equals("submit")){
				model.setShzt(Constants.YW_SHZ);//�����
			}
		}else{
			if(model.getType().equals("submit")){
				model.setShzt("");//�������
			}
		}
		
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//������
		FormFile file = (FormFile) files.get("lbfj");
		
		if (file != null && !StringUtil.isNull(file.getFileName())){
			String basePath = resource.getString("filesys.local.dir");
			//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
			
			if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
				File srcFile = FileUtil.conversionFormFile(file);
				File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
				FileUtils.copyFile(srcFile, destFile);
				model.setFjlj(destFile.getAbsolutePath());
				model.setFjmc(file.getFileName());
			}
		}
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (checkShzt && insertResult && model.getType().equals("submit")) {
			//shlc.deleteShjl(model.getId());
			//result = shlc.runSubmit(model.getId(), splc);
			result = shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(),"rcsw_rcxwwh_rcxwsh.do","rcsw_rcxwwh_rcxwxxwh.do");
		}
		return insertResult && result;
	}
	
	
	/**
	 * 
	 * @����:��ɾ���ճ���Ϊ���ͬʱɾ���ճ���Ϊά��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����04:30:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delRcxwwhFromRcxwjg(String[] values) throws Exception {
		if (values == null || values.length == 0) {
			return false;
		}
		int num = dao.delRcxwwhFromRcxwjg(values);
		return num > 0;
	}
	
	/**
	 * 
	 * @����:���������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����04:30:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkForSplc(RcxwxxwhForm model) throws Exception {
		boolean result = false;
		String splc = dao.checkForSplc(model.getRcxwlbdldm());
		if(!"�������".equalsIgnoreCase(splc)){
			result = true;
		}
		return result;
	}
	
	
	// ɾ������
	public int runDeleteXwxx(String[] values) throws Exception {
		// ɾ����Ϊ��¼
		int delNum = dao.delXwxx(values);
		if (delNum >0){
			// ɾ�������
			for(String sqid: values){
				// ɾ��ԭ�����
				shlc.deleteShjl(sqid);
			}
		}
		return delNum;
	}
	
	/**
	 * 
	 * @����:��ѯ��ȡһ����Ϊά������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����04:29:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneXwxxList(String  xwjgId) throws Exception {
		return dao.getOneXwxxList(xwjgId);
	}
	
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-3 ����09:18:51
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
	
   public boolean updateRcxwModel(RcxwxxwhForm model) throws Exception {
		boolean resultRcww = dao.updateRcxwxxwh(model.getId(),model.getSplc(),model.getShzt());
		return resultRcww;
	}
	
   /**
	 * @����: ��ѯ��ȡ��Ϊ�����Ϣ
	 * @���ߣ�HongLin [���ţ�707]
	 * @���ڣ�2014-2-21 ����10:50:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXwlbxx(HttpServletRequest request,String lbdm) {
		return dao.getXwlbxx(request,lbdm);
	}
	
	/** 
	 * @����: �ж���Ϣ�Ƿ��ظ�(ѧ�š�ѧ�ڡ�ѧ�ꡢ��Ϊ�б�����ʱ��)
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-24 ����05:47:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String xwlbStr,String fssjStr){
		String [] xwlbArr = xwlbStr.split("!!");
		String [] fssjArr = fssjStr.split("!!");
		List<HashMap<String,String>> list = dao.getRcxwxxSfcf(request,xh,xn,xq,xwlbArr,fssjArr);
		String message = "��";
		if(list!=null && list.size()>0){
			message = "��Ϊ��¼��Ϣ���ظ���¼��<br/>";
			for (int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					message+="����Ϊ���"+list.get(i).get("rcxwlbmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"��";	
				}else{
					message+="����Ϊ���"+list.get(i).get("rcxwlbmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"����";
				}
				
			}
			message+="����ȷ�ϣ�";
		}
		return message;
	}
	
	
}
