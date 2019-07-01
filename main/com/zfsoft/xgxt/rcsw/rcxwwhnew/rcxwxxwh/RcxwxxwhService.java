
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwxxwh;

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

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgService;

/**
 * �ճ���Ϊ��Ϣά�� 
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
	 * ��ȡ��ΪС�༯��
	 */
	public List<HashMap<String, String>> getXwxlList(String jldldm,
			HttpServletRequest request) {
		return dao.getXwxlList(jldldm, request);
	}
	
	/**
	 * ��ȡ��Ϊ���༯��
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		return dao.getXwdlList(request);
	}
	/**
	 * ��ȡ��Ϊ��𼯺�
	 */
	public List<HashMap<String, String>> getXwlbList(User user) throws Exception {
		return dao.getXwlbList(user);
	}
	/**
	 * ��ѯ���ճ���Ϊά�����Ƿ����
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
	 * �����ճ�ά��
	 */
	public boolean saveRcww(RcxwxxwhForm model) throws Exception {
		
	/*	if(checkShzt){
			model.setShzt(Constants.YW_WTJ);
		}*/
		String[] xwlbdmArr=model.getXwlbdmArr();
		String[] fzArray=model.getFzArray();
		String[] xwdldmArr=model.getXwdldmArr();
		String[] xwxldmArr=model.getXwxldmArr();
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
			model.setRcxwlbxldm(xwxldmArr[i]);
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
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"rcsw_rcxwwhnew_rcxwsh.do","rcsw_rcxwwhnew_rcxwxxwh.do");
			}
			//����Ҫ���������ֱ�Ӱ����ݱ��浽�ճ���Ϊ�������
        	if(!checkShzt&&insertResult&& SUBMIT.equalsIgnoreCase(model.getType())){
        		RcxwjgForm rcxwjgForm = new RcxwjgForm();
        		RcxwjgService rcxwjgService = new RcxwjgService();
        		BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(model));
        		//rcxwjgForm.setId(model.getId());
        		rcxwjgForm.setId(guid);
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
		result = shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(),"rcsw_rcxwwhnew_rcxwsh.do","rcsw_rcxwwhnew_rcxwxxwh.do");
		}
		if(resultRcww&&!isSplc){//�������ֱ�ӽ������
			RcxwjgForm rcxwjgForm = new RcxwjgForm();
    		RcxwjgService rcxwjgService = new RcxwjgService();
    		RcxwxxwhForm myForm=dao.getModel(model);
    		myForm.setRcxwlbdldm(model.getRcxwlbdldm());
    		BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(myForm));
    		rcxwjgForm.setRcxwxxid(model.getId());
    		//rcxwjgForm.setId(guid);
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
			result = shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(),"rcsw_rcxwwhnew_rcxwsh.do","rcsw_rcxwwhnew_rcxwxxwh.do");
		}
		return insertResult && result;
	}
	
	/**
	 * ��ɾ���ճ���Ϊ���ͬʱɾ���ճ���Ϊά��
	 */
	public boolean delRcxwwhFromRcxwjg(String[] values) throws Exception {
		if (values == null || values.length == 0) {
			return false;
		}
		int num = dao.delRcxwwhFromRcxwjg(values);
		return num > 0;
	}
	
	/**
	 * ���������
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
	 * ��ѯ��ȡһ����Ϊά������
	 */
	public Map<String, String> getOneXwxxList(String  xwjgId) throws Exception {
		return dao.getOneXwxxList(xwjgId);
	}
	
	/**
	 * ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
    public boolean updateRcxwModel(RcxwxxwhForm model) throws Exception {
		boolean resultRcww = dao.updateRcxwxxwh(model.getId(),model.getSplc(),model.getShzt());
		return resultRcww;
	}
    /**
	 * ��ѯ��ȡ��ΪС����Ϣ
	 */
	public List<HashMap<String,String>> getXwxlxx(HttpServletRequest request,String lbxldm) {
		return dao.getXwxlxx(request,lbxldm);
	}
	/** 
	 * �ж���Ϣ�Ƿ��ظ�(ѧ�š�ѧ�ڡ�ѧ�ꡢ��Ϊ�б�����ʱ��)
	 */
	public String getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String xwxlStr,String fssjStr){
		String [] xwxlArr = xwxlStr.split("!!");
		String [] fssjArr = fssjStr.split("!!");
		List<HashMap<String,String>> list = dao.getRcxwxxSfcf(request,xh,xn,xq,xwxlArr,fssjArr);
		String message = "��";
		if(list!=null && list.size()>0){
			message = "��Ϊ��¼��Ϣ���ظ���¼��<br/>";
			for (int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					message+="����ΪС�ࣺ"+list.get(i).get("rcxwlbxlmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"��";	
				}else{
					message+="����ΪС�ࣺ"+list.get(i).get("rcxwlbxlmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"����";
				}
			}
			message+="����ȷ�ϣ�";
		}
		return message;
	}
}
