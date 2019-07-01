package xsgzgl.gyjc.jcjglr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.attachupload.utils.StringToolkit;
import common.newp.ArrayUtil;

public class JcjglrService extends SuperServiceImpl<JcjglrForm,JcjglrDao> {
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	 /**
	 * @throws Exception 
	  * 
	  * @����:  ��ȡ�����ȼ�List
	  * @���ߣ�����Դ[���ţ�1206]
	  * @���ڣ�2017-4-20 ����07:18:24
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param t
	  * @return
	  * List<HashMap<String,String>> �������� 
	  * @throws
	  */
	 public List<HashMap<String, String>> getCommWpdjList(JcjglrForm t,User user) throws Exception{
		return dao.getCommWpdjList(t,user); 
	 }
	 
	 /**
		 * @throws Exception 
		 * 
		 * @����:�ύ��¼
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-21 ����05:48:31
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param mxids
		 * @return
		 * boolean �������� 
		 * @throws
		 */
		public boolean tjRecode(String[] mxids,String username) throws Exception{
			if(!dao.isFjXbtCheck(mxids)){
				throw new SystemException(MessageKey.XG_GYJC_FJ_BT);
			}
			return dao.tjRecode(mxids,username);
		}
		
		/**
		 * @throws Exception 
		 * 
		 * @����: ��������¼��
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-24 ����11:36:37
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * boolean �������� 
		 * @throws
		 */
		@SuppressWarnings("unchecked")
		public boolean saveJcjglr(JcjglrForm jcjg) throws Exception{
			String[] mxids = jcjg.getMxids();
			String[] pfids = jcjg.getPfids();
			String[] indexs = jcjg.getIndexs();
			String[] jjlxs = jcjg.getJjlxs();
			String[] cwhs = jcjg.getCwhs();
			String[] xhs = jcjg.getXhs();
			//����Ƿ����ظ�������д
			//�޸Ĺ��򣬼������ͺ�����������Ҫ���뵥���ж�
			List<HashMap<String, String>> qtlxList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> jllxList = new ArrayList<HashMap<String, String>>();
			List<String> CfPdList = new ArrayList<String>();
			if(mxids != null){
				for (int i = 0; i < jjlxs.length; i++) {
					HashMap<String, String> tempMap = new HashMap<String, String>();
					if(jjlxs[i].equals("3")){
						tempMap.put("mxid",mxids[i]);
						tempMap.put("pfid",pfids[i]);
						jllxList.add(tempMap);
					}else{
						tempMap.put("mxid",mxids[i]);
						tempMap.put("pfid",pfids[i]);
						tempMap.put("cwh", null);
						tempMap.put("xh", null);
						qtlxList.add(tempMap);
						CfPdList.add(pfids[i]);
					}
					
				}
				if(jllxList != null && jllxList.size() > 0){
					for (int i = 0; i < xhs.length; i++) {
						HashMap<String, String> temp = jllxList.get(i);
						HashMap<String, String> tempInput = new HashMap<String, String>();
						tempInput.put("xh",xhs[i]);
						tempInput.put("cwh", cwhs[i]);
						tempInput.put("mxid", temp.get("mxid"));
						tempInput.put("pfid", temp.get("pfid"));
						qtlxList.add(tempInput);
						CfPdList.add(temp.get("pfid")+xhs[i]);
					}
				}
			}
			
			/**
			 * �������͹����ظ��ж�
			 */
			if(CfPdList != null && CfPdList.size() >0){
				String[] noRepeatPfid = ArrayUtil.removeRepeatElementInArray(CfPdList.toArray(new String[]{}));
				if(CfPdList.size() != noRepeatPfid.length){
					throw new SystemException(MessageKey.XG_GYJC_PFBZ_REPEAT);
				}
			}
			String tjzt = jcjg.getTjzt();
			List<String[]> insertList = new ArrayList<String[]>();
			String fjid = jcjg.getFjid();
			if(StringUtils.isNull(fjid)){
				fjid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			}
			String[] fids = jcjg.getFids();
			//ɾ����ϸ��׼id
			String[] mxidflags = jcjg.getMxidflags();
			boolean rs = dao.delOldMxbz(mxidflags);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			//��������
			
			List<FormFile> fileArray = new ArrayList<FormFile>();
			List<String[]> fjbList = new ArrayList<String[]>();
			//�ж��Ƿ����ļ���
			if(jcjg.getMultipartRequestHandler() != null){
				Hashtable<String,FormFile> files = jcjg.getMultipartRequestHandler().getFileElements();
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
			List<HashMap<String, String>> totalFjList = dao.getFjxxList(fjid, null);
			List<HashMap<String, String>> delFjList = dao.getFjxxList(fjid, fids);
			int totalnum = totalFjList != null ? totalFjList.size():0;
			int delnum = delFjList != null ? delFjList.size():0;
			List<String> pfidflagList = new ArrayList<String>();
			if(pfids == null || pfids.length == 0){
				pfidflagList = null;
			}else{
				for (int i = 0; i < pfids.length; i++) {
					if(StringUtils.isNotNull(pfids[i])){
						pfidflagList.add(pfids[i]);
					}
				}
			}
			if(mxids != null && mxids.length > 0){
				if(pfidflagList != null && pfidflagList.size()>0){
					/**
					if(totalnum-delnum+fileArray.size() <= 0){
						//�ӳ��쳣,����δ��д�����ļ�¼
						throw new SystemException(MessageKey.XG_GYJC_FJ_BT);
					}*/
				}
				for (int i = 0; i < mxids.length; i++) {
					insertList.add(new String[]{qtlxList.get(i).get("mxid"),qtlxList.get(i).get("pfid"),qtlxList.get(i).get("cwh"),qtlxList.get(i).get("xh")});
				}
				rs = dao.insertJcMxBz(insertList);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
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
			
			if(totalnum-delnum+fileArray.size() <= 0){
				fjid = "";
			}
			JcjglrForm jcjglrForm = this.getModel(mxidflags[0]);
			if(jcjglrForm != null){
				rs = dao.updateMxid(jcjglrForm ,fjid );
			}
			
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			if("1".equals(tjzt)){
				rs = dao.tjRecodeForTrans(mxidflags,jcjg.getTjr());
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
			//Ϊ�˿������������ԣ��ļ�ɾ�����ļ��ϴ��������
			if(delnum != 0){
				this.delFile(delFjList);
			}
			if(fileArray.size() > 0){
				this.uploadFile(fileArray,fjbList);
			}
			return rs;
			
		}
		
		/**
		 * 
		 * @����: ��ȡ���һ�����Ϣ
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-25 ����11:35:55
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param lddm
		 * @param qsh
		 * @return
		 * HashMap<String,String> �������� 
		 * @throws
		 */
		public HashMap<String, String> getQsjbxx(String lddm,String qsh){
			return dao.getQsjbxx(lddm, qsh);
		}
		
		/**
		 * 
		 * @����: ��ȡ�����ϸList
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-25 ����11:48:19
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param rcid
		 * @param lddm
		 * @param qsh
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public HashMap<String, String> getJcmxMap(String rcid,String lddm,String qsh,String username,String flag){
			List<HashMap<String, String>> jcmxList = dao.getJcmxList(rcid, lddm, qsh,username,flag);
			String wsjc = "0";
			String aqjc = "0";
			String jljc = "0";
			String wsmxid = "";
			String aqmxid = "";
			String jlmxid = "";
			String wstjzt = "0";
			String aqtjzt = "0";
			String jltjzt = "0";
			HashMap<String, String> fhMap = new HashMap<String, String>();
			for (HashMap<String, String> hashMap : jcmxList) {
				fhMap.put("fjid", hashMap.get("fjid"));
				if(hashMap.get("jjlx").equals("1")){
					wsjc = "1";
					wsmxid = hashMap.get("guid");
					wstjzt = hashMap.get("tjzt");
					
				}else if(hashMap.get("jjlx").equals("2")){
					aqjc = "2";
					aqmxid = hashMap.get("guid");
					aqtjzt = hashMap.get("tjzt");
				}else{
					jljc = "3";
					jlmxid = hashMap.get("guid");
					jltjzt = hashMap.get("tjzt");
				}
			}
			fhMap.put("wsjc", wsjc);
			fhMap.put("aqjc", aqjc);
			fhMap.put("jljc", jljc);
			fhMap.put("wsmxid", wsmxid);
			fhMap.put("aqmxid", aqmxid);
			fhMap.put("jlmxid", jlmxid);
			fhMap.put("wstjzt",wstjzt);
			fhMap.put("aqtjzt",aqtjzt);
			fhMap.put("jltjzt",jltjzt);
			return fhMap;
		}
		
		/**
		 * 
		 * @����: ��ȡ��������������
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-25 ����11:20:30
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param xydm
		 * @param jjlx
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String,String>> getFjSelectList(String xydm,String jjlx,String js){
			return dao.getFjSelectList(xydm, jjlx, js);
			
		} 
		
		/**
		 * 
		 * @����: ��ȡ�Ӽ�����������
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-25 ����11:27:18
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getZjSelectList(String xydm,String jjlx,String js){
			return dao.getZjSelectList(xydm, jjlx, js);
		}
		
		/**
		 * 
		 * @����: ��ȡ����д�����ֱ�׼
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-25 ����10:22:33
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param rcid
		 * @param lddm
		 * @param qsh
		 * @param jjlx
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getJcxFxCx(String rcid,String lddm,String qsh,String jjlx){
			return dao.getJcxFxCx(rcid, lddm, qsh, jjlx);
			
		}
		
		/**
		 * 
		 * @����: ����
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-26 ����07:25:47
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param mxids
		 * @param username
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
		public boolean cxRecode(String[] mxids) throws Exception{
			return dao.cxRecode(mxids);
			
		}
		
		/**
		 * 
		 * @����: ��ȡ�ļ���Ϣ
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-28 ����03:11:44
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param fid
		 * @return
		 * HashMap<String,String> �������� 
		 * @throws
		 */
		public HashMap<String, String> getWjxxx(String fid){
			return dao.getWjxxx(fid);
		}
		
		/**
		 * 
		 * @����: ��ȡ���ϴ�������Ϣ
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-28 ����04:13:47
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param rcid
		 * @param lddm
		 * @param qsh
		 * @param jjlx
		 * @return
		 * boolean �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getYscfjxx(String fjid){
			return dao.getYscfjxx(fjid);
		}
		
		/**
		 * 
		 * @����: ɾ������
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-28 ����05:58:04
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * void �������� 
		 * @throws
		 */
		public void delFile(List<HashMap<String, String>> delFjList){
			String basePath = resource.getString("filesys.local.dir");
			//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			for (int i = 0; i < delFjList.size(); i++) {
				String filepath = basePath+"/"+delFjList.get(i).get("generatename");
				File file = new File(filepath);
				if (file.exists()){
					 file.delete();
				}
			}
		}
		
		/**
		 * @throws Exception 
		 * 
		 * @����: �ϴ�����
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-4-28 ����06:11:23
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param fileArray
		 * void �������� 
		 * @throws
		 */
		public void uploadFile(List<FormFile> fileArray,List<String[]> fjbList) throws Exception{
			String basePath = resource.getString("filesys.local.dir");
			//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			for (int i = 0; i < fileArray.size(); i++) {
				File srcFile = FileUtil.conversionFormFile(fileArray.get(i));
				File destFile = new File(basePath+"/"+fjbList.get(i)[2]);
				FileUtils.copyFile(srcFile, destFile);
			}
			
		}
		
		/**
		  * 
		  * @����:�ƶ�ѧ����ѯ�б�
		  * @���ߣ�cq [���ţ�785]
		  * @���ڣ�2017-4-24 ����02:28:50
		  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		  * @param t
		  * @param user
		  * @return
		  * @throws Exception
		  * List<HashMap<String,String>> �������� 
		  * @throws
		  */
		 public List<HashMap<String, String>> getYdxgList(JcjglrForm t,User user) throws Exception{
			 return dao.getYdxgList(t, user);
		 }
		 
		 
		 /**
		  * 
		  * @����:Ϊ�˱���һ���ж��ѧԺ��״��
		  * @���ߣ�cq [���ţ�785]
		  * @���ڣ�2017-4-24 ����03:04:12
		  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		  * @param t
		  * @param user
		  * @return
		  * @throws Exception
		  * List<HashMap<String,String>> �������� 
		  * @throws
		  */
		 public List<HashMap<String, Object>> getYdxgCxList(JcjglrForm t,User user) throws  Exception{
			 List<HashMap<String, String>> ydxgList = getYdxgList(t, user);
			 List<HashMap<String, Object>> totalList = new ArrayList<HashMap<String,Object>>();
			 for (int i = 0; i < ydxgList.size(); i++) {
				 HashMap<String, String> tempMap1 = ydxgList.get(i);
				 HashMap<String, Object> tempMap2 = new HashMap<String, Object>();
				 tempMap2.putAll(tempMap1);
				 tempMap2.put("xyList", dao.getXyForRcid(ydxgList.get(i).get("guid"), user));
				 totalList.add(tempMap2);
			 }
			 
			 return totalList;
		 }
		 
		 /**
		  * 
		  * @����:�ƶ�ѧ��¼���ѯlist
		  * @���ߣ�cq [���ţ�785]
		  * @���ڣ�2017-4-25 ����03:11:10
		  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		  * @param rcid
		  * @param user
		  * @return
		  * List<HashMap<String,Object>> �������� 
		  * @throws
		  */
		 public List<HashMap<String, Object>> getLrcxList(JcjglrForm form, User user) throws Exception{
			 
			 List<HashMap<String, String>> lrcxList = dao.getLrcxList(form, user);
			 List<HashMap<String, Object>> totalList = new ArrayList<HashMap<String,Object>>();
			 
			 if(!lrcxList.isEmpty()){
				 List<HashMap<String, String>> lrcxMxList = dao.getLrcxMx(form.getRcid(),user,lrcxList);
				
				 
				 for (int i = 0; i < lrcxList.size(); i++) {
					 HashMap<String, String> tempMap1 = lrcxList.get(i);
					 HashMap<String, Object> tempMap2 = new HashMap<String, Object>();
					 List<HashMap<String, String>> tempList = new ArrayList<HashMap<String,String>>();
					 tempMap2.putAll(tempMap1);
					 //������˵���˵㣬һ��Ϊ��Ч��,�������ݿ��ν���
					 for (int j = 0; j < lrcxMxList.size(); j++) {
						 if(tempMap1.get("lddm").equals(lrcxMxList.get(j).get("lddm"))&&tempMap1.get("qsh").equals(lrcxMxList.get(j).get("qsh"))){
							 tempList.add(lrcxMxList.get(j));
						 }
					 }
					 tempMap2.put("dj", tempList);
					 
					 totalList.add(tempMap2);
				 }
			 }
			 
			 return totalList;
		 }

		
		 /**
		  * 
		  * @����:�����ύ��ѯ
		  * @���ߣ�cq [���ţ�785]
		  * @���ڣ�2017-4-26 ����08:53:35
		  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		  * @param model
		  * @param user
		  * @return
		  * List<HashMap<String,Object>> �������� 
		  * @throws
		  */
		public List<HashMap<String, String>> getPlList(JcjglrForm t, User user) throws Exception {
			return dao.getPlList(t,user);
		}

		/**
		 * 
		 * @����:������¼�룬�����һ����¼
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2017-4-27 ����04:22:55
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @param user
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public HashMap<String, String> getWsflr(JcjglrForm model, User user) {
			return dao.getOneWsf(model,user);
		}

		
		/**
		 * 
		 * @����:����¥����������ҺŻ�ȡ���ҳ�Ա
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2017-4-27 ����04:35:41
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param string
		 * @param string2
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getQscy(String lddm, String qsh) {
			return dao.getQscy(lddm,qsh);
		}
		
		/**
		 * 
		 * @����:��ȡ�����б�
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2017-4-28 ����04:26:14
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param t
		 * @param user
		 * @return
		 * @throws Exception
		 * List<HashMap<String,Object>> �������� 
		 * @throws
		 */
		public List<HashMap<String, Object>> getQsInfo(JcjglrForm t,User user) throws  Exception{
			
			return ydsjldPicker(dao.getQsxx(t,user));
		 }
		public List<HashMap<String, String>> getPfBzList(String jclx,String js,String xydm) throws  Exception{
			
			return dao.getBzList(jclx,js,xydm);
		 }
		
		/**
		 * 
		 * @����:��װ���ƶ�ѡ������ʽҪ�� 2��ѡ�����ʱ�����ޣ�����˭ʱ��Ƚϳ�ԣ���˷�����ȡ��������Ϊ���÷���
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2017-4-28 ����09:33:04
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param list
		 * @return
		 * List<HashMap<String,Object>> �������� 
		 * @throws
		 */
		public List<HashMap<String, Object>> ydPicker(List<HashMap<String, String>> list){
			
			List<HashMap<String, Object>> totalList = new ArrayList<HashMap<String,Object>>();
			List<HashMap<String, String>> lastList = new ArrayList<HashMap<String,String>>();
			HashMap<String, Object> firstMap = new HashMap<String, Object>(); //��һ��ѡ��
			
			HashMap map = new HashMap();
			int i = 1;
			
			for (HashMap<String, String> hashMap : list) {
				map.put(hashMap.get("rcid"), hashMap.get("rcid"));
			
				if(map.size()!=i){
					firstMap = new HashMap<String, Object>();
					lastList = new ArrayList<HashMap<String,String>>();
					i=map.size();
				} 
				
				firstMap.put("value", hashMap.get("guid"));
				firstMap.put("text", hashMap.get("fjwsqkyq"));
				firstMap.put("children", lastList);
				
				HashMap<String, String> lastMap = new HashMap<String, String>();  //���һ�� ѡ��
				lastMap.put("value", hashMap.get("bzid"));
				lastMap.put("text", hashMap.get("wsqkyq"));
				lastList.add(lastMap);  
				
				if(!totalList.contains(firstMap)){
					totalList.add(firstMap);
				}
			}
			
			return totalList;
			
		}
		
		
		/**
		 * 
		 * @����:��������
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2017-4-28 ����03:23:20
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param list
		 * @return
		 * List<HashMap<String,Object>> �������� 
		 * @throws
		 */
		public List<HashMap<String, Object>> ydsjldPicker(List<HashMap<String, String>> list){
			
			List<HashMap<String, Object>> totalList = new ArrayList<HashMap<String,Object>>();
			List<HashMap<String, Object>> secondList = new ArrayList<HashMap<String,Object>>();
			List<HashMap<String, String>> lastList = new ArrayList<HashMap<String,String>>();
			
			HashMap<String, Object> firstMap = new HashMap<String, Object>(); //��һ��ѡ��
			HashMap<String, Object> secondMap = new HashMap<String, Object>(); //�ڶ���ѡ��
			
			HashMap firstM = new HashMap();
			HashMap secondM = new HashMap();
			
			for (HashMap<String, String> hashMap : list) {
				secondM.put(hashMap.get("ch"), hashMap.get("ch"));
				firstM.put(hashMap.get("lddm"), hashMap.get("lddm"));
				
				//�ж����һ���Ƿ���Ҫ��ʼ��
				if(secondM.size()!=1){
					secondMap = new HashMap<String, Object>();
					lastList = new ArrayList<HashMap<String,String>>();
					secondM.clear();
				}
				
				//�жϵڶ����Ƿ���Ҫ��ʼ��
				if(firstM.size()!=1){
					firstMap = new HashMap<String, Object>();
					secondList = new ArrayList<HashMap<String,Object>>();
					firstM.clear();
				}
				
				//���һ�� ѡ��
				HashMap<String, String> lastMap = new HashMap<String, String>();
				lastMap.put("value", hashMap.get("qsh")+"-"+hashMap.get("xydm")+"-"+hashMap.get("xymc"));
				lastMap.put("text", hashMap.get("qsh"));
				lastList.add(lastMap);
				
				//�ڶ���
				secondMap.put("value", hashMap.get("ch"));	
				secondMap.put("text", hashMap.get("ch"));
				secondMap.put("children", lastList);
				
				if(!secondList.contains(secondMap)){
					secondList.add(secondMap);
				}
				
				//��һ��
				firstMap.put("value", hashMap.get("lddm"));	
				firstMap.put("text", hashMap.get("ldmc"));
				firstMap.put("children", secondList);
				
				if(!totalList.contains(firstMap)){
					totalList.add(firstMap);
				}
				
			}
			
			return totalList;
			
		}
		public HashMap<String, String> getRczt(String rcid, User user){
			return dao.getRczt(rcid, user);
		}
		
		/**
		 * 
		 * @����:����һ�������ѯ��ȡ���ֱ�׼
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2017-5-5 ����09:40:13
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param resultMap
		 * @param user
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getPfbzForResu(JcjglrForm form, User user) {
			return dao.getPfbzForResu(form, user);
		}
		
		/**
		 * 
		 * @����: �鿴ʱ��ѯ����ȼ�
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-5-9 ����02:56:51
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getFxdjcxForView(String rcid,String xydm,String qsh,String lddm,String flag,String jcrq){
			return dao.getFxdjcxForView(rcid, xydm, qsh, lddm, flag, jcrq);
		}
		
		
		
		/**
		 * ���Ҽ��list
		 * @param form
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public List<HashMap<String, String>> qsjcList(JcjglrForm form, User user) throws Exception{
			return dao.qsjcList(form, user);
		}
}
