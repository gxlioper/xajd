/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-28 ����10:57:14 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�����ϱ����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-28 ����10:51:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfshService extends SuperServiceImpl<CfshForm, CfshDao> {
	
	private CfshDao dao=new CfshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public CfshService(){
		this.setDao(dao);
	}

	/** 
	 * @����:(��ȡ�����ϱ���Ϣ)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-28 ����03:20:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getCfsbxx(CfshForm model) {
		return dao.getCfsbxx(model);
	}
	public HashMap<String, String> getCfsbxxForjg(CfshForm model) {
		return dao.getCfsbxxForjg(model);
	}
	/** 
	 * @����:(�������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����04:46:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean ydsh(CfshForm form, User user) throws Exception{
		
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShzt());
		// ��˸�λID
		model.setGwid(form.getGwid());
		model.setThgw(form.getThgw());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getYwid());
		model.setSqrid(form.getXh());
		model.setTzlj("wjcf_cfsh.do?method=cxCfshList");
		model.setTzljsq("wjcf_cfsbgl.do?method=cxCfsbList");
		
		String zhzt = shlc.runAuditing(model);
		CfshForm shForm=new CfshForm();
		shForm.setCfid(form.getYwid());
		shForm.setSbjg(zhzt);
		if(!form.getKzzd1().equals(form.getCflbdm())){
			shForm.setKzzd1(form.getKzzd1());	//��ԭ��������������ϱ����е�kzzd1�ֶΣ�
		}
//		5F061D3D90BC4133E0538713470AE88F
//		5F061D3D90BC4133E0538713470AE88F
//		5F06379A58B8412DE0538713470A4F70
		shForm.setCflbdm(form.getCflbdm());	//���´���������
		
		shForm.setKzzd2(form.getCfwh());	//���´��ַ��ĵ�kzzd2
		shForm.setKzzd3(form.getCfsj());	//���´���ʱ�䵽kzzd3
		shForm.setKzzd5(form.getCfdqsj());	//���´��ֵ������ڵ�kzzd5

		boolean result=dao.runUpdate(shForm, shForm.getCfid());
		
		//���һ����λ���
		if(result && zhzt.equals(Constants.TG)){
			form.setSjly("1");
			if("12915".equals(Base.xxdm)){
				form.setCflsh(getLsh(form));
			}
			else{
				form.setCflsh("");
			}
			
			result= insertWjjgk(form);
			
		}
		return result;
	}
	/**
	 * 
	 * @����:�Ϻ����ȴ���֪ͨ����ˮ�Ż�ȡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-26 ����10:13:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getLsh(CfshForm model) {
		StringBuffer lshstr=new StringBuffer();
		HashMap<String,String> map =dao.getSbxx(model);
		List<HashMap<String, String>> lshList = dao.getLsh(map);
		if (lshList.size() > 0) {
			int lsh=0;
			System.out.println("lsh:"+lshList.get(0).get("cflsh"));
			lsh = Integer.parseInt(Base.isNull(lshList.get(0).get("cflsh"))?"0":lshList.get(0).get("cflsh"))+1;
			if(lsh>9&&lsh<100){
				lshstr.append("0").append(lsh);
			}
			else if(lsh>99){
				lshstr.append(lsh);
			}
			else{
				lshstr.append("00").append(lsh);
			}
		} else {
			lshstr.append("001");
		}
		return lshstr.toString();
	}
	/**
	 * @throws Exception  
	 * @����:TODO(��������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����05:11:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	private boolean insertWjjgk(CfshForm form) throws Exception {
		
		return dao.insertWjjgk(form);
	}

	/** 
	 * @����:(�Ƿ������һ����λ)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����06:59:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isZhgw(CfshForm model) {
		ArrayList<HashMap<String, String>> spgws=dao.getSplcgw(model);
		String spgw=spgws.get(spgws.size()-1).get("spgw");
		return model.getGwid().equalsIgnoreCase(spgw);
	}

	/** 
	 * @����:(�Ƿ��пɻع�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����10:24:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean sfkcx(String ywid) {
		return dao.sfkcx(ywid);
	}

	/** 
	 * @����:(���һ����˻ع�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����10:41:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @param string
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(CfshForm myForm, String ywid) throws Exception{
		CfshForm shForm=new CfshForm();
		shForm.setCfid(ywid);
		shForm.setSbjg(Constants.SHZ);
		
		//��ȡȨ���ж�ֵ
		int cffwqxPd = this.cffwqxPd(dao.getZcFwqxByCfid(ywid),myForm.getSplcid(),myForm.getGwid());
		//�Ƿ������һ��
		boolean isZhgw=this.isZhgw(myForm);
		//Ȩ���ж�Ϊ2�軹ԭ�������һ����Ȩ���ж�Ϊ0��1�軹ԭ
		if(cffwqxPd==2||(isZhgw&&(cffwqxPd==0||cffwqxPd==1))){
			shForm.setCflbdm(myForm.getKzzd1());
		}
		
//		shForm.setKzzd1("");	//���ﲻ���ÿգ���ֹ��γ�������
		
		boolean result=dao.runUpdate(shForm, shForm.getCfid());
		if(result){
			if(dao.getJgk(ywid)!=null&&""!=dao.getJgk(ywid)){
			int count=dao.deleteJgk(ywid);
			if(count>0){
				//���ô�����Ϣ
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfsh.do?method=cxCfshList");
				dbcz.setSqPath("wjcf_cfsbgl.do?method=cxCfsbList");
				dbcz.setGnmkMc("Υ�ʹ������");
				dbcz.setXmmc("�������");
				dbcz.shPush(ywid, splcid);*/
			}else{
				return false;
			}
			}
		}
		
		return result;
	}

	/** 
	 * @����:(�Ƿ񿴳���)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����12:07:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @param string
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public boolean canCancel(CfshForm model) {
		ShlcDao shlcDao=new ShlcDao();
		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		String nextGwid=shlcDao.getNextGwid(model.getSplcid(), shxx.get("gwid"));
		boolean result=false;
		if (StringUtils.isNull(nextGwid)) {
			result=dao.sfkcx(shxx.get("ywid"));
		}else{
			return true;
		}
		return result;
	}

	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream fjCx(CfshForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfsbb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-23 ����05:32:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(CfshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcids = t.getSplcids();
		
		

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			CfshForm model = new CfshForm();
			model.setGuid(ids[i]);
			model.setGwid(gwids[i]);
			model.setYwid(ids[i]);
			model.setSplcid(splcids[i]);
			model.setShyj(t.getShyj());
			model.setShzt(t.getShzt());
			model.setXh(xhs[i]);
			model.setKzzd1(t.getKzzd1());
			model.setCflbdm(t.getCflbdm());
			model.setCfwh(t.getCfwh());
			model.setCfsj(t.getCfsj());
			model.setCfdqsj(t.getCfdqsj());
			boolean isSuccess = ydsh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/** 
	 * @����:���ַ���Ȩ���ж� 0��δ��д��1���ȼ�����Ȩ�޸�λ��2���ȼ�����Ȩ�޸�λ��3�ȼ�����Ȩ�޸�λ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��27�� ����7:05:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @param splcid
	 * @param string2
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int cffwqxPd(String ffqx, String splcid, String gwid) {
		String shgwjb = dao.getShgwjb(splcid,gwid);
//		String qxwgjb = dao.getQxgwjb(splcid,cflbdm);
		String qxwgjb = dao.getShgwjb(splcid,ffqx);
		
		if(StringUtils.isNotNull(qxwgjb)){
			int s = Integer.parseInt(shgwjb);
			int q = Integer.parseInt(qxwgjb);
			return s<q?1:(s==q?2:3);
		}else{
			return 0;
		}
	}

	/**
	 * @����:��ô��ֵ���ʱ��Ĭ��ֵ������ʱ��+��������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��25�� ����2:28:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws Exception
	 */
	public String defaultCfdqsj(CfshForm model) throws Exception {
		String result = "";
		
		String cflbdm = model.getCflbdm();
		String cfsj = model.getCfsj();
		
		int yearValue = 0;
		int monthValue = 0;
		int dayValue = 0;
		
		//���ݴ����������ȡ��������
		HashMap<String, String>  cflbInfo = null;
		if(StringUtils.isNotNull(cflbdm)){
			cflbInfo = new CflbdmwhDao().cflbInfoById(cflbdm);
		}
		
		if(cflbInfo!=null && cflbInfo.size()>0){
			if("1".equals(cflbInfo.get("sfszcfqx"))){
				String cfqx = cflbInfo.get("cfqx");
				if(StringUtils.isNotNull(cfqx)){
					String year = cfqx.split("-")[0];
					String month = cfqx.split("-")[1];
					String day = cfqx.split("-")[2];
					
					yearValue = Integer.parseInt(year);
					monthValue = Integer.parseInt(month);
					dayValue = Integer.parseInt(day);
					
				}
			}
		}
		
		
		//���ݴ���ʱ��ʹ������޼��㴦�ֵ�������
		if(!(yearValue==0&&monthValue==0&&dayValue==0)){
			if(StringUtils.isNotNull(cfsj)){
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(cfsj);
				calendar.setTime(date);
				
				calendar.add(Calendar.YEAR, yearValue);
				calendar.add(Calendar.MONTH, monthValue);
				calendar.add(Calendar.DATE, dayValue);
				
				result = sdf.format(calendar.getTime());
			}
		}else{
			result = "hidden";
		}
		
		return result;
	}

	public HashMap<String,String> getxsnl(String xh) {
		return dao.getxsnl(xh);
	}

	public HashMap<String,String> getbjrs(String bjdm) {
		return dao.getbjrs(bjdm);
	}
}
