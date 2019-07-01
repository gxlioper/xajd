/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-24 ����09:05:37 
 */
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import xgxt.utils.GetTime;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������϶�����ģ��
 * @�๦������: TODO(�������϶�����) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-24 ����09:05:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdsqService extends
		SuperServiceImpl<KnsrdsqForm, KnsrdsqDao> {

	private KnsrdsqDao dao = new KnsrdsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public KnsrdsqService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ���õ�ָ��Id)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-24 ����11:43:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdzbid(){
		
		return dao.getKnsrdzbid();
	}


	/**
	 * 
	 * @����:TODO(�����������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-26 ����09:03:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveKnsrdsq(JSONArray jsonArray,KnsrdsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
    	model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		for(int i=0;i<jsonArray.length(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i); 
			KnsrdzbsqsxForm knsrdzbsqsxForm = new KnsrdzbsqsxForm();
			String sqsxid = UniqID.getInstance().getUniqIDHash();
			setKnsrdzbsqsx( jsonJ, knsrdzbsqsxForm);
			knsrdzbsqsxForm.setId(sqsxid);
			knsrdzbsqsxForm.setSqid(sqid);
			//��������ָ������-ָ�����Ա������������
			dao.addKnsrdzbsqsx(knsrdzbsqsxForm);
			JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
			//������ָ������-ָ�����ݱ������������
			for(int j=0;j<zbnrJsonArray.length(); j++){
				KnsrdzbsqnrForm knsrdzbsqnrForm = new KnsrdzbsqnrForm();
				JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
				String sqnrid = UniqID.getInstance().getUniqIDHash();
				knsrdzbsqnrForm.setId(sqnrid);
				knsrdzbsqnrForm.setSqid(sqid);
				setKnsrdzbsqnr(jsonZbnr,knsrdzbsqnrForm);
				dao.addKnsrdzbsqnr(knsrdzbsqnrForm);
			}
		}
		// ��ȡ��������
		JcszDao jcszDao = new JcszDao();
		JcszForm jcszModel = jcszDao.getModel();
		if (jcszModel != null && !StringUtil.isNull(jcszModel.getSplc())) {
			// �����������̵������¼��
			model.setSplc(jcszModel.getSplc());
		}
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_xszz_knsrd_knsh.do","xg_xszz_knsrd_knsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ��,ѧ�꣬ѧ���жϸ�ѧ���������϶������Ƿ��Ѿ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:17:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByKnsrdsq(KnsrdsqForm model)
	throws Exception {
		boolean flag = false;
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:54:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jsonArray
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateKnsrdsq(JSONArray jsonArray,KnsrdsqForm model) throws Exception {
		
		boolean insertResult =false;
		String sqid = model.getSqid();
		boolean delKnssqsx = false;
		boolean delKnssqnr = false;
		//����sqid ɾ��������ָ������-ָ������ ��ϸ����
		delKnssqsx = dao.deleteKnsrdzbsqsx(sqid);
		//����sqid ɾ������ָ������-ָ�����ݱ� ��ϸ����
		delKnssqnr = dao.deleteKnsrdzbsqnr(sqid);
		if(delKnssqsx && delKnssqnr){
				if(model.getType().equals(SUBMIT)){
					model.setShzt(Constants.YW_SHZ);//�����
				}else{
					model.setShzt(Constants.YW_WTJ);//δ�ύ
				}
				model.setSqid(sqid);
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		    	model.setXn(Base.currXn);
				model.setXq(Base.currXq);
				for(int i=0;i<jsonArray.length(); i++){
					JSONObject jsonJ = jsonArray.getJSONObject(i); 
					KnsrdzbsqsxForm knsrdzbsqsxForm = new KnsrdzbsqsxForm();
					String sqsxid = UniqID.getInstance().getUniqIDHash();
					setKnsrdzbsqsx( jsonJ, knsrdzbsqsxForm);
					knsrdzbsqsxForm.setId(sqsxid);
					knsrdzbsqsxForm.setSqid(sqid);
					//��������ָ������-ָ�����Ա������������
					dao.addKnsrdzbsqsx(knsrdzbsqsxForm);
					JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
					//������ָ������-ָ�����ݱ������������
					for(int j=0;j<zbnrJsonArray.length(); j++){
						KnsrdzbsqnrForm knsrdzbsqnrForm = new KnsrdzbsqnrForm();
						JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
						String sqnrid = UniqID.getInstance().getUniqIDHash();
						knsrdzbsqnrForm.setId(sqnrid);
						knsrdzbsqnrForm.setSqid(sqid);
						setKnsrdzbsqnr(jsonZbnr,knsrdzbsqnrForm);
						dao.addKnsrdzbsqnr(knsrdzbsqnrForm);
					}
				}
				// ��ȡ��������
				JcszDao jcszDao = new JcszDao();
				JcszForm jcszModel = jcszDao.getModel();
				if (jcszModel != null && !StringUtil.isNull(jcszModel.getSplc())&&StringUtil.isNull(model.getSplc())) {
					// �����������̵������¼��
					model.setSplc(jcszModel.getSplc());
				}
				insertResult = super.runUpdate(model);
				boolean result = false;
				if (insertResult&& SUBMIT.equalsIgnoreCase(model.getType())) {
//					shlc.deleteShjl(model.getSqid());    ע��ԭ������˻ص������˺��������ύ��ɾ��ԭ����˼�¼
					result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_xszz_knsrd_knsh.do","xg_xszz_knsrd_knsq.do");
					return result;
				}
		
		}
		return insertResult;
	}
	
	/**
	 * 
	 * @����:TODO(������ָ������-ָ������Form����������ֵ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-26 ����09:24:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jsonJ
	 * @param knsrdzbsqsxForm
	 * @return
	 * KnsrdzbsqsxForm �������� 
	 * @throws
	 */
	private KnsrdzbsqsxForm setKnsrdzbsqsx(JSONObject jsonJ,
			KnsrdzbsqsxForm knsrdzbsqsxForm) {
		
		String sxid = jsonJ.getString("sxid");
		String jtqk =  jsonJ.getString("jtqk"); 
		knsrdzbsqsxForm.setSxid(sxid);
		knsrdzbsqsxForm.setJtqk(jtqk);
		return knsrdzbsqsxForm;
		
	}
	
	/**
	 * 
	 * @����:TODO(������ָ������-ָ�����ݱ���������ֵ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jsonJ
	 * @param knsrdzbsqnrForm
	 * @return
	 * KnsrdzbsqnrForm �������� 
	 * @throws
	 */
	private KnsrdzbsqnrForm setKnsrdzbsqnr(JSONObject jsonJ,
			KnsrdzbsqnrForm knsrdzbsqnrForm) {
		String nrid = jsonJ.getString("nrid");
		String fz =  jsonJ.getString("fz"); 
		knsrdzbsqnrForm.setNrid(nrid);
		knsrdzbsqnrForm.setSqfz(fz);
		//knsrdzbsqnrForm.setShfz(fz);
		return knsrdzbsqnrForm;
	}
	/**
	 * 
	 * @����:TODO(��ȡ������ָ������-ָ�����Լ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:53:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(KnsrdsqForm model)throws Exception {
		
		return dao.getKnsrdzbsxList(model);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ����ָ������-ָ�����ݱ�ids)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:52:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdzbsqnrids(KnsrdsqForm model)throws Exception {
		
		return dao.getKnsrdzbsqnrids(model);
	}
	
	
	/**
	 * 
	 * @����:TODO(ɾ��������ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-26 ����05:17:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteKnsrdzbsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
				if(delId.size()>0){
					ShlcDao shlcdao = new ShlcDao();
					shlcdao.delelteShjl(str);
				}
			}else{
				HashMap<String, String> hm=dao.getKnsrdzbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?knsrdzbsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��������ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-26 ����05:25:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int knsrdzbsqDelete(String[] sqids) throws Exception {
		for(int i=0;i<sqids.length;i++){
			//����sqid ɾ��������ָ������-ָ������ ��ϸ����
			 dao.deleteKnsrdzbsqsx(sqids[i]);
			//����sqid ɾ������ָ������-ָ�����ݱ� ��ϸ����
			 dao.deleteKnsrdzbsqnr(sqids[i]);
		}
		return runDelete(sqids);
	}
	
	/**
	 * 
	 * @����:TODO(�ύ�������϶�����״̬)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:32:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitKnsrdsq(KnsrdsqForm model) throws Exception {
		
		boolean resultKnsrdsq = dao.updateKnsrdsq(model.getSqid(), Constants.YW_SHZ);
		
		boolean result = false;
		if(resultKnsrdsq){
//		// ��ȡ��������         ע�����ɣ�Ҫ��������iD��ȡ�������̡������¼���е��������̺�ϵͳ���õ����������п��ܲ�һ�¡�
//		JcszDao jcszDao = new JcszDao();
//		JcszForm jcszModel = jcszDao.getModel();
		//�����������
		result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_xszz_knsrd_knsh.do","xg_xszz_knsrd_knsq.do");
		}
		return result;
	}
	

	/**
	 * 
	 * @����:TODO(�����������϶�����״̬)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:51:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateKnsrdsq(KnsrdsqForm model) throws Exception {
		boolean resultBbsq = dao.updateKnsrdsq(model.getSqid(),model.getShzt());
		return resultBbsq;
	}
	
	
	/**
	 * 
	 * @����:TODO(ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:52:09
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

	
}
