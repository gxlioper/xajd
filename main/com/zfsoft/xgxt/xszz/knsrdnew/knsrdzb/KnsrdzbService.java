package com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-20 ����02:12:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdzbService extends SuperServiceImpl<KnsrdzbForm, KnsrdzbDao>
		implements Constants {
	
	public static String _BCZSCID="-1";
	private KnsrdzbDao dao = new KnsrdzbDao();
	public KnsrdzbService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-20 ����02:12:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getKnszbPageList(KnsrdzbForm model) throws Exception{
		return dao.getKnszbPageList(model);
	}
	public boolean checkZbmc(String zbdm,String zbmc){
		return dao.checkZbmc(zbdm, zbmc);
	}
	/**
	 * 
	 * @����:TODO(����������ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����11:25:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jsonArray
	 * @param zbmc
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveKnszbInfo(JSONArray jsonArray,String zbmc) throws Exception{
		boolean result = false;
		
		String zbid = UniqID.getInstance().getUniqIDHash();
		KnsrdzbForm knsrdzbForm = new KnsrdzbForm();
		knsrdzbForm.setZbid(zbid);
		knsrdzbForm.setZbmc(zbmc);
		knsrdzbForm.setQyzt("0");
		result = dao.addKnsrdzb(knsrdzbForm);
		if(result){
			for(int i=0;i<jsonArray.length(); i++){
				JSONObject jsonJ = jsonArray.getJSONObject(i); 
				KnsrdzbsxForm knsrdzbsxForm = new KnsrdzbsxForm();
				String sxid = UniqID.getInstance().getUniqIDHash();
				setKnsrdzbsx(zbid, jsonJ, knsrdzbsxForm, sxid);
				result = dao.addKnsrdzbsx(knsrdzbsxForm);
				if(result){
					JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
					for(int j=0;j<zbnrJsonArray.length(); j++){
						KnsrdzbnrForm knsrdzbnrForm = new KnsrdzbnrForm();
						JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
						 setKnsrdzbnr(sxid, knsrdzbnrForm, jsonZbnr);
						result =  dao.addKnsrdzbnr(knsrdzbnrForm);
						 
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(����������ָ����ϸ��Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����11:24:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jsonArray
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateKnszbInfo(JSONArray jsonArray,KnsrdzbForm model,String existNum) throws Exception{
		boolean result = true;
		
		String zbid = model.getZbid();
		if(existNum.equals("0")){
			KnsrdzbForm knsrdzbForm = new KnsrdzbForm();
			knsrdzbForm.setZbid(zbid);
			knsrdzbForm.setZbmc(model.getZbmc());
			knsrdzbForm.setQyzt(model.getQyzt());
			dao.updateKnsrdzb(knsrdzbForm);
		}
		
		
		if(existNum.equalsIgnoreCase("0")){
			String[] sxids = dao.getKnsrdzbnrsxidList(zbid);
			dao.deleteKnsrdzbnr(sxids);
			dao.deleteKnsrdzbsx(zbid);
		}
		
		for(int i=0;i<jsonArray.length(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i); 
			KnsrdzbsxForm knsrdzbsxForm = new KnsrdzbsxForm();
			String sxid = "";
			sxid = jsonJ.getString("sxid");
			
			if(sxid != null && !"".equalsIgnoreCase(sxid) && !existNum.equals("0")){
				knsrdzbsxForm = setKnsrdzbsx(zbid, jsonJ, knsrdzbsxForm, sxid);
				dao.updateKnsrdzbsx(knsrdzbsxForm);
			}else{
				sxid = UniqID.getInstance().getUniqIDHash();
				knsrdzbsxForm = setKnsrdzbsx(zbid, jsonJ, knsrdzbsxForm, sxid);
				dao.addKnsrdzbsx(knsrdzbsxForm);
				
			}
			
			JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
			for(int j=0;j<zbnrJsonArray.length(); j++){
				KnsrdzbnrForm knsrdzbnrForm = new KnsrdzbnrForm();
				JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
				String nrid = "";
				if(!existNum.equals("0")){
					nrid = jsonZbnr.getString("nrid");
				}
				
				if(nrid != null && !"".equalsIgnoreCase(nrid) && !existNum.equals("0") ){
					knsrdzbnrForm = setKnsrdzbnr(sxid, knsrdzbnrForm, jsonZbnr);
					knsrdzbnrForm.setNrid(nrid);
					dao.updateKnsrdzbnr(knsrdzbnrForm);
				}else{
					knsrdzbnrForm = setKnsrdzbnr(sxid, knsrdzbnrForm, jsonZbnr);
					dao.addKnsrdzbnr(knsrdzbnrForm);
				}
			}
		}
		
		return result;
	}

	/** 
	 * @����:TODO(���������϶�ָ������Form������ֵ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-22 ����03:28:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sxid
	 * @param knsrdzbnrForm
	 * @param jsonZbnr
	 * void �������� 
	 * @throws 
	 */
	private KnsrdzbnrForm setKnsrdzbnr(String sxid, KnsrdzbnrForm knsrdzbnrForm,
			JSONObject jsonZbnr) {
		
		 String nrmc = jsonZbnr.getString("nrmc");
		 String fzlx = jsonZbnr.getString("fzlx");
		 String fz = jsonZbnr.getString("fz");
		 String xssx = jsonZbnr.getString("xssx");
		 knsrdzbnrForm.setNrmc(nrmc);
		 knsrdzbnrForm.setFzlx(fzlx);
		 knsrdzbnrForm.setFz(fz);
		 knsrdzbnrForm.setXssx(xssx);
		 knsrdzbnrForm.setSxid(sxid);
		 return knsrdzbnrForm;
	}

	/** 
	 * @����:TODO(���������϶�ָ������Form������ֵ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-22 ����03:18:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @param jsonJ
	 * @param knsrdzbsxForm
	 * @param sxid
	 * void �������� 
	 * @throws 
	 */
	private KnsrdzbsxForm setKnsrdzbsx(String zbid, JSONObject jsonJ,
			KnsrdzbsxForm knsrdzbsxForm, String sxid) {
		
		String sxmc = jsonJ.getString("sxmc");
		String qzbl =  jsonJ.getString("qzbl"); 
		String sxxssx = jsonJ.getString("xssx"); 
		knsrdzbsxForm.setSxmc(sxmc);
		knsrdzbsxForm.setQzbl(qzbl);
		knsrdzbsxForm.setXssx(sxxssx);
		knsrdzbsxForm.setSxid(sxid);
		knsrdzbsxForm.setZbid(zbid);
		return knsrdzbsxForm;
		
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�������϶�ָ�����Լ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����11:23:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(String zbid) throws Exception {
		return dao.getKnsrdzbsxList(zbid);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�������϶�ָ�����ݼ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����11:22:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sxid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbnrList(String sxid) throws Exception {
		return dao.getKnsrdzbnrList(sxid);
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶�ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����11:22:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qyKnsrdzb(String zbid) throws Exception {
		
		boolean qyFlag = false;
		boolean tyFlag = false;
		String[] zbids  = dao.getKnsrdzbidsList(zbid);
		if(zbids.length<=0){
			tyFlag = true;
		}else{
			tyFlag = dao.tyKnsrdzb(zbids);
		}
		if(tyFlag){
			qyFlag = dao.qyKnsrdzb(zbid);
		}
		
		return qyFlag;
	}
	
	
	/**
	 * 
	 * @����:TODO(�����������϶�ָ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-23 ����02:06:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jsonArray
	 * @param zbmc
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean copyKnsrdzb(KnsrdzbForm model) throws Exception{
		boolean result = false;
		
		String zbid = UniqID.getInstance().getUniqIDHash();
		KnsrdzbForm knsrdzbForm = new KnsrdzbForm();
		knsrdzbForm.setZbid(zbid);
		knsrdzbForm.setZbmc(model.getZbmc());
		knsrdzbForm.setQyzt("0");
		result = dao.addKnsrdzb(knsrdzbForm);
		
		if(result){
			String[]sxids = dao.getKnsrdzbnrsxidList(model.getZbid());
			for(int i=0;i<sxids.length;i++){
				String sxid = UniqID.getInstance().getUniqIDHash();
				KnsrdzbsxForm knsrdzbsxForm = dao.getKnsrdzbsxModel(sxids[i]);
				KnsrdzbsxForm copyKnsrdzbsxForm = new KnsrdzbsxForm();
				copyKnsrdzbsxForm.setSxid(sxid);
				copyKnsrdzbsxForm.setZbid(zbid);
				copyKnsrdzbsxForm.setSxmc(knsrdzbsxForm.getSxmc());
				copyKnsrdzbsxForm.setQzbl(knsrdzbsxForm.getQzbl());
				copyKnsrdzbsxForm.setXssx(knsrdzbsxForm.getXssx());
				result = dao.addKnsrdzbsx(copyKnsrdzbsxForm);
				if(result){
					String[]nrids = dao.getKnsrdzbnridList(sxids[i]);
					for(int j=0;j<nrids.length;j++){
						KnsrdzbnrForm knsrdzbnrForm = dao.getKnsrdzbnrModel(nrids[j]);
						KnsrdzbnrForm copyKnsrdzbnrForm = new KnsrdzbnrForm();
						copyKnsrdzbnrForm.setSxid(sxid);
						copyKnsrdzbnrForm.setNrmc(knsrdzbnrForm.getNrmc());
						copyKnsrdzbnrForm.setFzlx(knsrdzbnrForm.getFzlx());
						copyKnsrdzbnrForm.setFz(knsrdzbnrForm.getFz());
						copyKnsrdzbnrForm.setXssx(knsrdzbnrForm.getXssx());
						result = dao.addKnsrdzbnr(copyKnsrdzbnrForm);
					}
				}
			}
		}
		return result;
	}
	
	public String[] deleteKnsrdzb(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getKnsrdzb(str);
				noDel.append(hm.get("zbmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?knsrdzbDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	private int knsrdzbDelete(String[] zbids) throws Exception {
	    
		for(int i=0;i<zbids.length;i++){
			String[] sxids = dao.getKnsrdzbnrsxidList(zbids[i]);
			dao.deleteKnsrdzbnr(sxids);
			dao.deleteKnsrdzbsx(zbids[i]);
		}
		return runDelete(zbids);
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-11 ����10:40:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkExistByKnsrdsq(String zbid) throws Exception {
		
		return dao.checkExistForUpdate(zbid);
		
	}
	
	
}
