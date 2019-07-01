package xsgzgl.gyjc.ccjgcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.gyjc.jcjglr.JcjglrDao;
import xsgzgl.gyjc.jcjglr.JcjglrService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.attachupload.utils.StringToolkit;
import common.newp.ArrayUtil;

public class CcjgService extends SuperServiceImpl<CcjgForm, CcjgDao> {
	
	public List<HashMap<String, String>> getQsxxPageList(CcjgForm t, User user) throws Exception{
		return dao.getQsxxPageList(t,user);
	}
	public CcjgForm getCcjgModel(CcjgForm t) throws Exception{
		return dao.getCcjgModel(t);
	}
	public List<HashMap<String, String>> getCcjgList(CcjgForm t) throws Exception{
		return dao.getCcjgList(t);
	}
	public List<HashMap<String, String>> getQscyList(CcjgForm t) throws Exception{
		return dao.getQscyList(t);
	}
	
	public List<HashMap<String, String>> getYdxgCcjgPageList(CcjgForm t, User user)throws Exception{
		return dao.getYdxgCcjgPageList(t,user);
	}
	public boolean getYHqx(User user)throws Exception{
		return Integer.parseInt(dao.getYhqx(user))>0;
	}
	public boolean editCcjgYdxg(CcjgForm model) throws Exception {
		boolean result = true;
		dao.delCcmx(model);
		dao.delCcmxbz(model);
		List<String[]> pfbzList = new ArrayList<String[]>();
		List<String[]> jclxList = new ArrayList<String[]>();
		String[] jcmxbz = null;
		String[] jcjxArr = null;
		String[] jclx=model.getJjlx().split(",");
		String[] pfbz = StringUtils.isNull(model.getPfid())?null:model.getPfid().split(",");
		for (int i = 0; i < jclx.length; i++) {
			jcjxArr= new String[7];
			jcjxArr[0]=model.getXydm();
			jcjxArr[1]=model.getLddm();
			jcjxArr[2]=model.getQsh();
			jcjxArr[3]=jclx[i];
			jcjxArr[4]=model.getTjr();
			jcjxArr[5]=model.getJcrq();
			jcjxArr[6]=model.getFjid();
			jclxList.add(jcjxArr);
		}
		if(null!=pfbz){
			for (int j = 0; j < pfbz.length; j++) {
				jcmxbz = new String[7];
				String[] pfbzAndXh = pfbz[j].split("-");
				if(pfbzAndXh.length>2){
					jcmxbz[0] = pfbzAndXh[1];
					jcmxbz[6] = pfbzAndXh[2];
				}else{
					jcmxbz[0] = pfbzAndXh[1];
					jcmxbz[6] = "";
					
				}
				jcmxbz[5] = pfbzAndXh[0];
				jcmxbz[1] = model.getXydm();
				jcmxbz[2] = model.getLddm();
				jcmxbz[3] = model.getQsh();
				jcmxbz[4] = model.getJcrq();
				pfbzList.add(jcmxbz);
		
			}
		}
			result=dao.jclxPlbc(jclxList);
			if(result&&null!=pfbz){
			result = dao.pfbzPlbc(pfbzList);
			}
		return result;

	}
	public boolean editCcjg(CcjgForm model) throws Exception {
		boolean result = true;
		String fjid = model.getFjid();
		String[] indexs = model.getIndexs();
		String[] fids = model.getFids();
		if(StringUtils.isNull(fjid)){
			fjid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		}
		String[] pfbz = StringUtils.isNull(model.getPfid())?null:model.getPfid().split(",");
		//����Ƿ����ظ�������д
		if(pfbz != null && pfbz.length >0){
			String[] noRepeatMxid = ArrayUtil.removeRepeatElementInArray(pfbz);
			if(pfbz.length != noRepeatMxid.length){
				throw new SystemException(MessageKey.XG_GYJC_PFBZ_REPEAT);
			}
		}
		//��������
		
		List<FormFile> fileArray = new ArrayList<FormFile>();
		List<String[]> fjbList = new ArrayList<String[]>();
		//�ж��Ƿ����ļ���
		if(model.getMultipartRequestHandler() != null){
			Hashtable<String,FormFile> files = model.getMultipartRequestHandler().getFileElements();
			for (int i = 0; i < indexs.length; i++) {
				FormFile file = files.get("fjid"+indexs[i]);
				if(file.getFileSize() > 4*1024*1024){
					throw new SystemException(MessageKey.XG_GYJC_FJSC_PHOTO_SIZE_LIM);
				}
				if (file != null && !StringUtil.isNull(file.getFileName())){
					fileArray.add(file);
					String curtime = DateUtils.getCurrTime();
					String fid = UniqID.getInstance().getUniqIDHash().toUpperCase();
					String originalname = file.getFileName();
					String ext = StringToolkit.getLastName(originalname);
					String generatename = fid + '.' + ext;
					String filesize = file.getFileSize() + "";
					fjbList.add(new String[]{fid,originalname,generatename,ext,filesize,curtime,fjid});
				}
			}
		}
		List<HashMap<String, String>> totalFjList = new JcjglrDao().getFjxxList(fjid, null);
		List<HashMap<String, String>> delFjList = new JcjglrDao().getFjxxList(fjid, fids);
		int totalnum = totalFjList != null ? totalFjList.size():0;
		int delnum = delFjList != null ? delFjList.size():0;
		
		if(pfbz != null && pfbz.length > 0){
			if(totalnum-delnum+fileArray.size() <= 0){
				//�ӳ��쳣,����δ��д�����ļ�¼
				throw new SystemException(MessageKey.XG_GYJC_FJ_BT);
			}
		}
		dao.delCcmx(model);
		dao.delCcmxbz(model);//ɾ�����ֱ�׼��ϸ
		List<String[]> pfbzList = new ArrayList<String[]>();
		List<String[]> jclxList = new ArrayList<String[]>();
		String[] jcmxbz = null;
		String[] jcjxArr = null;
		String[] jclx=model.getJjlx().split(",");
		for (int i = 0; i < jclx.length; i++) {
			jcjxArr= new String[7];
			jcjxArr[0]=model.getXydm();
			jcjxArr[1]=model.getLddm();
			jcjxArr[2]=model.getQsh();
			jcjxArr[3]=jclx[i];
			jcjxArr[4]=model.getTjr();
			jcjxArr[5]=model.getJcrq();
			jcjxArr[6]=fjid;
			jclxList.add(jcjxArr);
		}
		if(null!=pfbz){
			for (int j = 0; j < pfbz.length; j++) {
				jcmxbz = new String[5];
				jcmxbz[0] = pfbz[j];
				jcmxbz[1] = model.getXydm();
				jcmxbz[2] = model.getLddm();
				jcmxbz[3] = model.getQsh();
				jcmxbz[4] = model.getJcrq();
				pfbzList.add(jcmxbz);
		
			}
		}
			result=dao.jclxPlbc(jclxList);
			if(result&&null!=pfbz){
			result = dao.pfbzPlbc(pfbzList);
			}
			if(delnum > 0){
				if(!dao.delFjxxByfid(fjid, fids)){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			
			}
			if(fileArray.size() > 0){
				if(!dao.insertIntoFjb(fjbList)){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
			//Ϊ�˿�����������ԣ��ļ�ɾ���ļ��ϴ��������
			if(delnum != 0){
				new JcjglrService().delFile(delFjList);
			}
			if(fileArray.size() > 0){
				new JcjglrService().uploadFile(fileArray,fjbList);
			}
		return result;

	}
	/**
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-20 ����11:58:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveCcjg(CcjgForm model) {
		return dao.isHaveCcjg(model);
	}
	
	public boolean delCcmxbz(String[] ids) throws Exception {
		return  dao.delCcmxbz(ids);
	}
}
