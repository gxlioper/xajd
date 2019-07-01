/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-1-27 ����10:14:22 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcDao;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcForm;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.knsrd.KnsrdForm;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdzbsqnrForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������϶�����ģ��
 * @�๦������: TODO(�������϶����) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-27 ����10:14:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdshService extends SuperServiceImpl<KnsrdshForm, KnsrdshDao> implements Constants{

	private KnsrdshDao dao = new KnsrdshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private KnsdcDao dcDao = new KnsdcDao();
	private KnsjgDao jgDao = new KnsjgDao();
	
	public KnsrdshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(�õ�������ָ�����Լ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:22:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(KnsrdshForm model)throws Exception {
		return dao.getKnsrdzbsxList(model);
	}
	
	/**
	 * 
	 * @����:TODO(�õ��������϶�ָ������Ids)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:23:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdzbsqnrids(KnsrdshForm model)throws Exception {
		return dao.getKnsrdzbsqnrids(model);
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-10-9 ����10:57:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(KnsrdshForm t, User user) throws Exception {
		String[] ids = t.getSqids();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		JSONArray jsonArray = new JSONArray("[]");
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			KnsrdshForm model = new KnsrdshForm();
			model.setSqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRddc(t.getRddc());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSplc(t.getSplc());
			model.setXh(xhs[i]);
			
			boolean isSuccess = saveSh(model, user,"",jsonArray);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:19:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @param nrids
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSh(KnsrdshForm form,User user,String nrids,JSONArray jsonArray)throws Exception {
		
		//������������¼��ָ��������������ɾ�������²���
		if(!"".equals(nrids)){
			String[]nrid = nrids.split(",");
			dao.deleteKnsrdzbsqnrb(nrid);
		}
		//������ָ���������ݱ��������˺������
		for(int i=0;i<jsonArray.length(); i++){
			JSONObject jsonJ = jsonArray.getJSONObject(i); 
			JSONArray zbnrJsonArray = jsonJ.getJSONArray("zbnr");
			//������ָ������-ָ�����ݱ������������
			for(int j=0;j<zbnrJsonArray.length(); j++){
				KnsrdzbsqnrForm knsrdzbsqnrForm = new KnsrdzbsqnrForm();
				JSONObject jsonZbnr = zbnrJsonArray.getJSONObject(j); 
				String sqnrid = UniqID.getInstance().getUniqIDHash();
				knsrdzbsqnrForm.setId(sqnrid);
				knsrdzbsqnrForm.setSqid(form.getSqid());
				setKnsrdzbsqnr(jsonZbnr,knsrdzbsqnrForm);
				dao.addKnsrdzbsqnr(knsrdzbsqnrForm);
			}
		}
		
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("xg_xszz_knsrd_knsh.do");
		model.setTzljsq("xg_xszz_knsrd_knsq.do");
		
		if(form.getShjg().equals("1")){
			// �O��ҵ���ֶ�1[ҵ������]
			model.setZd1("�϶�����");
			// �O��ҵ���ֶ�2[ҵ��ID]
			model.setZd2(form.getRddc());
			// �O��ҵ���ֶ�3
			KnsdcForm dcForm = new KnsdcForm();
			dcForm.setDcdm(form.getRddc());
			dcForm = dcDao.getModel(dcForm);
			model.setZd3(dcForm.getDcmc());
		}
		boolean reuslt = false;
		try {
			String shzt = shlc.runAuditing(model);
			KnsrdshForm upForm = new KnsrdshForm();
			upForm.setSqid(form.getSqid());
			upForm.setShzt(shzt);
			reuslt = dao.runUpdate(upForm, form.getSqid());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(shzt.equalsIgnoreCase(Constants.YW_TG)){
        		//���������������
				KnsjgDao knsjgDao = new KnsjgDao();
				KnsjgForm knsjgModel = new KnsjgForm();
				KnsrdshForm copyModel = getModel(form);
				copyModel.setRddc(form.getRddc());
				BeanUtils.copyProperties(knsjgModel, copyModel);
				// �Ȱ�ѧ�š�ѧ�ꡢѧ���ж��Ƿ����
				Map<String, String> map = knsjgDao.getXsknsjg(knsjgModel
						.getXh(), knsjgModel.getXn(), knsjgModel.getXq());
				if (!map.isEmpty()) {
					knsjgDao.delKnsjg(knsjgModel.getXn(), knsjgModel.getXq(), knsjgModel.getXh());
				}

				knsjgModel.setSjly("1");
				knsjgModel.setLylcywid(copyModel.getSqid());
				knsjgDao.runInsert(knsjgModel);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}
	
	private KnsrdzbsqnrForm setKnsrdzbsqnr(JSONObject jsonJ,
			KnsrdzbsqnrForm knsrdzbsqnrForm) {
		
		String nrid = jsonJ.getString("nrid");
		String fz =  jsonJ.getString("fz"); 
		String shfz =  jsonJ.getString("shfz"); 
		knsrdzbsqnrForm.setNrid(nrid);
		knsrdzbsqnrForm.setSqfz(fz);
		knsrdzbsqnrForm.setShfz(shfz);
		return knsrdzbsqnrForm;
		
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����08:58:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(KnsrdshForm model){
		boolean resultKnsrdsh = false;
		try {
			//�����������϶�
			resultKnsrdsh = dao.cancelKnsrdzbsq(model.getSqid(), Constants.YW_SHZ);
			if(resultKnsrdsh){
				//ɾ���������϶�����еļ�¼
				jgDao.delJgb(model.getSqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultKnsrdsh;
	}
	
	/**
	 * 
	 * @����:TODO(�������϶����뼯��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����03:45:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sxid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbnrsqList(String sxid,String sqid) throws Exception {
		return dao.getKnsrdzbnrsqList(sxid,sqid);
	}

}
