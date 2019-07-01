package com.zfsoft.xgxt.gygl.ssyd.ydsh;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.gygl.ssyd.ydjg.YdjgForm;
import com.zfsoft.xgxt.gygl.ssyd.ydjg.YdjgService;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������:�����춯���
 * @���ߣ� qilm
 * @ʱ�䣺2013-9-29
 * @�汾�� V1.0
 */
public class YdshService extends SuperServiceImpl<YdshForm, YdshDAO> {
	/**
	 * ������Դ��1[������]
	 */
	private static final String SJLY_SPL = "1";
	/**
	 * �Ƿ�λ��ʼ��[0:��ʼ��]
	 */
	private static final String  SFCWCSH_CSH = "0";
	
	/**
	 *  ����
	 */
	public static String _YDLX_TS = "00";
	/**
	 *  �������
	 */
	public static String _YDLX_SSTZ = "01";
	/**
	 *  ��ס
	 */
	public static String _YDLX_SSRZ = "03";
	
	private YdshDAO dao = new YdshDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public YdshService() {
		super.setDao(dao);
	}
	/**
	 * @����:�����춯���
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	@TransactionControl
	public boolean ydsh(YdshForm form,User user) throws Exception{
		
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
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
		model.setYwid(form.getSsydsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("ydshbase.do");
		model.setTzljsq("ydsqbase.do");
		
		// �O��ҵ���ֶ�1[ҵ������]
		model.setZd1("ԭ��λ�Ƿ��ʼ��");
		
		// �O��ҵ���ֶ�1[ҵ��ID]
		model.setZd2(form.getSfcwcsh());		
		String sfcwcsh = "0".equals(form.getSfcwcsh())?"��":"��";
		
		// �O��ҵ���ֶ�1[ҵ��ID��Ӧ����]
		model.setZd3(sfcwcsh);	
		
		ShlcService shlcService = new ShlcService();
		HashMap<String, String> shlcXx = shlcService.getXx(form.getSplcid());
		
		String zsfxs = shlcXx.get("zsfxs");
		if("1".equals(zsfxs)){
			model.setZd4("ס�޷�");
			
			model.setZd5(form.getJflx());
			if("01".equals(form.getJflx())) {
				String jflx = "����";
				model.setZd6(jflx+" "+form.getZsfje());
			}else if("02".equals(form.getJflx())) {
				String jflx = "�˻�";
				model.setZd6(jflx+" "+form.getZsfje());
			}
		}
		// ============ ��˴�λ begin ==========
		YdsqService ydsqService = new YdsqService();
		//������λ��Ϣ
		String ydhcwxx = "";
		//���������������õ�������
		if(form.getSsydlx().equals(_YDLX_SSTZ)){
			ydhcwxx = form.getCwxx();
		}else if(form.getSsydlx().equals(_YDLX_SSRZ)){//��ס����
			ydhcwxx = form.getRzcwxx();
		}
		if(ydhcwxx!=null && !"".equals(ydhcwxx) && !"null_null_null".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			HashMap<String, String> cwxxMap = ydsqService.getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			form.setYdhlddm(cwxxMap.get("lddm"));
			form.setYdhldmc(cwxxMap.get("ldmc"));
			form.setYdhqsh(cwxxMap.get("qsh"));
			form.setYdhcwh(cwxxMap.get("cwh"));
			String ydglxh = cwxxMap.get("xh");//�춯����ѧ��
			ydglxh = (ydglxh!=null && !"".equals(ydglxh) && !"null".equals(ydglxh)) ? ydglxh : "";
			form.setYdglxh(ydglxh);
		}else{
			form.setYdhlddm("");
			form.setYdhldmc("");
			form.setYdhqsh("");
			form.setYdhcwh("");
			form.setYdglxh("");
		}
		model.setZd7(form.getYdhlddm());
		model.setZd8(form.getYdhldmc());
		model.setZd9(form.getYdhqsh());
		model.setZd10(form.getYdhcwh());
		// ============ ��˴�λ end ==========
		boolean result = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			YdshForm upForm = new YdshForm();
			upForm.setSsydsqid(form.getSsydsqid());
			upForm.setShzt(zhzt);
			result = dao.runUpdateNotCommit(upForm, form.getSsydsqid());

			if (result) {
//				String ssydlxmc = form.getSsydlx().equals(_YDLX_TS) ? "����" : "�������";
//				//���ô�����Ϣ
//				BaseDbcz dbcz=new BaseDbcz();
//				dbcz.setSqPath("ydsqbase.do");
//				dbcz.setShPath("ydshbase.do");
//				dbcz.setGnmkMc("�����춯���");
//				dbcz.setXmmc(ssydlxmc);
//				dbcz.shPush(form.getSsydsqid(),form.getSplcid());
			}
			
			// ���һ�����ͨ��ʱ
			if(result && zhzt.equals(Constants.SH_TG)){
				
				// ���������춯�����
				result = insertYdjg(form);
				
				// �����������λ��Ϣ������
				result = tzQS(form);
			}
			
		
		return result;
	}
	/**
	 * @����:�{����λ��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws SQLException  
	 */
	private boolean tzQS(YdshForm form) throws Exception {

		boolean returnFlg = true;
		
		// �춯���MAP
		HashMap<String, String>  ydshMap = new HashMap<String, String>();
		if(YdshService._YDLX_SSRZ.equals(form.getSsydlx())){
			ydshMap = dao.getYdshRzForm(form);
		}else{
			ydshMap = dao.getYdshForm(form);
		}
		
		// ѧ��
		String xh = ydshMap.get("xh");		
		
		// �춯ǰ��λ��Ϣ
		String ydqlddm = ydshMap.get("ydqlddm")==null ?"":ydshMap.get("ydqlddm");
		String ydqqsh = ydshMap.get("ydqqsh")==null ?"":ydshMap.get("ydqqsh");
		String ydqcwh = ydshMap.get("ydqcwh")==null ?"":ydshMap.get("ydqcwh");
		
		// �Ƿ�λ��ʼ��
		String sfcwcsh = form.getSfcwcsh();
		HashMap<String, String> cwxxMap = new HashMap<String, String>();
		// ����/����ʱ��
		String tstzsj = ydshMap.get("tstzsj")==null ?"":ydshMap.get("tstzsj");
		
		// �����춯���ͣ�����
		if(ydshMap.get("ssydlx")!=null && _YDLX_TS.equals(ydshMap.get("ssydlx"))){
			
			cwxxMap.put("xh", "");
			cwxxMap.put("lddm", ydqlddm);
			cwxxMap.put("qsh", ydqqsh);
			cwxxMap.put("cwh", ydqcwh);
			cwxxMap.put("rzsj", "");//��סʱ��
			
			// �Ƿ��ʼ��
			if(SFCWCSH_CSH.equals(sfcwcsh)){

				cwxxMap.put("nj", "");
				cwxxMap.put("bjdm", "");
				cwxxMap.put("zydm", "");
				cwxxMap.put("xydm", "");
			}
			returnFlg = dao.updateCwxxb(cwxxMap);
			
			// �����춯���ͣ��������
		}else if(ydshMap.get("ssydlx")!=null && _YDLX_SSTZ.equals(ydshMap.get("ssydlx"))){
			// �춯����ѧ��
			String ydglxh = ydshMap.get("ydglxh")==null ?"":ydshMap.get("ydglxh");
			
			// �춯��λ��Ϣ
			String ydhlddm = ydshMap.get("ydhlddm")==null ?"":ydshMap.get("ydhlddm");
			String ydhqsh = ydshMap.get("ydhqsh")==null ?"":ydshMap.get("ydhqsh");
			String ydhcwh = ydshMap.get("ydhcwh")==null ?"":ydshMap.get("ydhcwh");

			// �춯��Ĵ�λ��Ϣ����
			cwxxMap.put("lddm", ydhlddm);
			cwxxMap.put("qsh", ydhqsh);
			cwxxMap.put("cwh", ydhcwh);
			cwxxMap.put("xh", xh);
			cwxxMap.put("nj", ydshMap.get("ydqxsnj"));
			cwxxMap.put("bjdm", ydshMap.get("ydqxsbjdm"));
			cwxxMap.put("zydm", ydshMap.get("ydqxszydm"));
			cwxxMap.put("xydm", ydshMap.get("ydqxsxydm"));
			cwxxMap.put("rzsj", tstzsj);//��סʱ��
			
			returnFlg = dao.updateCwxxb(cwxxMap);
			
			// �й���ѧ��
			if(returnFlg && !"".equals(ydglxh)){
				
				cwxxMap = new HashMap<String, String>();
				// �춯ǰ�Ĵ�λ��Ϣ����
				cwxxMap.put("lddm", ydqlddm);
				cwxxMap.put("qsh", ydqqsh);
				cwxxMap.put("cwh", ydqcwh);
				cwxxMap.put("xh", ydglxh);
				cwxxMap.put("nj", ydshMap.get("ydhxsnj"));
				cwxxMap.put("bjdm", ydshMap.get("ydhxsbjdm"));
				cwxxMap.put("zydm", ydshMap.get("ydhxszydm"));
				cwxxMap.put("xydm", ydshMap.get("ydhxsxydm"));
				cwxxMap.put("rzsj", tstzsj);//��סʱ��
				returnFlg = dao.updateCwxxb(cwxxMap);
				
				//�޹���ѧ��
			}else if( returnFlg){
				
				cwxxMap =  new HashMap<String, String>() ;
				cwxxMap.put("xh", "");
				cwxxMap.put("lddm", ydqlddm);
				cwxxMap.put("qsh", ydqqsh);
				cwxxMap.put("cwh", ydqcwh);
				cwxxMap.put("rzsj", "");//��סʱ��
				
				// �Ƿ��ʼ��
				if(SFCWCSH_CSH.equals(sfcwcsh)){
	
					cwxxMap.put("nj", "");
					cwxxMap.put("bjdm", "");
					cwxxMap.put("zydm", "");
					cwxxMap.put("xydm", "");
				}
				returnFlg = dao.updateCwxxb(cwxxMap);
			}
		}else if(ydshMap.get("ssydlx")!=null && _YDLX_SSRZ.equals(ydshMap.get("ssydlx"))){
			// �춯��λ��Ϣ
			String ydhlddm = ydshMap.get("ydhlddm")==null ?"":ydshMap.get("ydhlddm");
			String ydhqsh = ydshMap.get("ydhqsh")==null ?"":ydshMap.get("ydhqsh");
			String ydhcwh = ydshMap.get("ydhcwh")==null ?"":ydshMap.get("ydhcwh");

			// �춯��Ĵ�λ��Ϣ����
			cwxxMap.put("lddm", ydhlddm);
			cwxxMap.put("qsh", ydhqsh);
			cwxxMap.put("cwh", ydhcwh);
			cwxxMap.put("xh", xh);
			cwxxMap.put("nj", ydshMap.get("ydqxsnj"));
			cwxxMap.put("bjdm", ydshMap.get("ydqxsbjdm"));
			cwxxMap.put("zydm", ydshMap.get("ydqxszydm"));
			cwxxMap.put("xydm", ydshMap.get("ydqxsxydm"));
			cwxxMap.put("rzsj", tstzsj);//��סʱ��
			cwxxMap.put("rzyydm", ydshMap.get("tstzyy"));
			
			dao.updateCwxxb(cwxxMap);
			
		}
		
		return returnFlg;
	}
	/**
	 * 
	 * @����:���������춯�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	private boolean insertYdjg(YdshForm form) throws Exception{
		
		boolean returnFlg = true;
		YdjgForm ydjgForm = new YdjgForm();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		YdjgService ydjgService = new YdjgService();
				
		// �춯���MAP
		HashMap<String, String>  ydshMap = new HashMap<String, String>();
		if(YdshService._YDLX_SSRZ.equals(form.getSsydlx())){
			ydshMap = dao.getYdshRzForm(form);
		}else{
			ydshMap = dao.getYdshForm(form);
		}
		String guid = UniqID.getInstance().getUniqIDHash();
		ydjgForm.setSsydid(guid);
		ydjgForm.setXh(ydshMap.get("xh"));
		ydjgForm.setCzsj(date);
		ydjgForm.setXn(ydshMap.get("xn"));
		ydjgForm.setXq(ydshMap.get("xq"));
		ydjgForm.setSsydlx(ydshMap.get("ssydlx"));
		ydjgForm.setTstzsj(ydshMap.get("tstzsj"));
		ydjgForm.setTstzyy(ydshMap.get("tstzyy"));
		ydjgForm.setBz(ydshMap.get("bz"));
		ydjgForm.setTjsqrxm(ydshMap.get("tjsqrxm"));//�ύ����������
		//����ǰ��λ��Ϣ
		ydjgForm.setYdqlddm(ydshMap.get("ydqlddm"));
		ydjgForm.setYdqldmc(ydshMap.get("ydqldmc"));
		ydjgForm.setYdqqsh(ydshMap.get("ydqqsh"));
		ydjgForm.setYdqcwh(ydshMap.get("ydqcwh"));
		ydjgForm.setYdqqsrzsj(ydshMap.get("rzsj"));
		//�����ᴲλ��Ϣ		
		ydjgForm.setYdhlddm(ydshMap.get("ydhlddm"));
		ydjgForm.setYdhldmc(ydshMap.get("ydhldmc"));
		ydjgForm.setYdhqsh(ydshMap.get("ydhqsh"));
		ydjgForm.setYdhcwh(ydshMap.get("ydhcwh"));
		//�춯������ԭ����
		ydjgForm.setYdhnj(ydshMap.get("ydhnj"));
		ydjgForm.setYdhxydm(ydshMap.get("ydhxydm"));
		ydjgForm.setYdhzydm(ydshMap.get("ydhzydm"));
		ydjgForm.setYdhbjdm(ydshMap.get("ydhbjdm"));		
		
		ydjgForm.setFjxx(ydshMap.get("fjxx"));// ������Ϣ
		ydjgForm.setSfcwcsh(form.getSfcwcsh());//�Ƿ�λ��ʼ��
		ydjgForm.setJflx(form.getJflx());//�ɷ�����
		ydjgForm.setZsfje(form.getZsfje());//ס�޷ѽ��
		ydjgForm.setSjly(SJLY_SPL);					//�趨������Դ
		ydjgForm.setSsydsqid(ydshMap.get("ssydsqid"));//����ID
		ydjgForm.setYdqqsrzsj(ydshMap.get("ydqrzsj"));//�춯ǰ������סʱ��
		
		if(ydshMap.get("ydglxh")!=null && !"".equals(ydshMap.get("ydglxh"))){
			YdjgForm ydjgFormGl = new YdjgForm();
			String guidGl = UniqID.getInstance().getUniqIDHash();
			ydjgFormGl.setSsydid(guidGl);
			ydjgFormGl.setXh(ydshMap.get("ydglxh"));
			ydjgFormGl.setCzsj(date);
			ydjgFormGl.setXn(ydshMap.get("xn"));
			ydjgFormGl.setXq(ydshMap.get("xq"));
			ydjgFormGl.setSsydlx(ydshMap.get("ssydlx"));
			ydjgFormGl.setTstzsj(ydshMap.get("tstzsj"));
			ydjgFormGl.setTstzyy(ydshMap.get("tstzyy"));
			ydjgFormGl.setBz(ydshMap.get("bz"));
			//����ǰ��λ��Ϣ
			ydjgFormGl.setYdqlddm(ydshMap.get("ydhlddm"));
			ydjgFormGl.setYdqldmc(ydshMap.get("ydhldmc"));
			ydjgFormGl.setYdqqsh(ydshMap.get("ydhqsh"));
			ydjgFormGl.setYdqcwh(ydshMap.get("ydhcwh"));
			//�����ᴲλ��Ϣ
			ydjgFormGl.setYdhlddm(ydshMap.get("ydqlddm"));
			ydjgFormGl.setYdhldmc(ydshMap.get("ydqldmc"));
			ydjgFormGl.setYdhqsh(ydshMap.get("ydqqsh"));
			ydjgFormGl.setYdhcwh(ydshMap.get("ydqcwh"));
			//�춯������ԭ����
			ydjgFormGl.setYdhnj(ydshMap.get("ydhnj"));
			ydjgFormGl.setYdhxydm(ydshMap.get("ydhxydm"));
			ydjgFormGl.setYdhzydm(ydshMap.get("ydhzydm"));
			ydjgFormGl.setYdhbjdm(ydshMap.get("ydhbjdm"));
			ydjgForm.setFjxx(ydshMap.get("fjxx"));
			ydjgFormGl.setSfcwcsh(form.getSfcwcsh());//�Ƿ�λ��ʼ��
			ydjgForm.setJflx(form.getJflx());//�ɷ�����
			ydjgForm.setZsfje(form.getZsfje());//ס�޷ѽ��
			ydjgFormGl.setSjly(SJLY_SPL);					//�趨������Դ
			ydjgFormGl.setSsydsqid(ydshMap.get("ssydsqid"));//����ID
			ydjgFormGl.setYdqqsrzsj(ydshMap.get("ydhrzsj"));//�춯ǰ������סʱ��
			
			//����������춯��Ϣ
			returnFlg = ydjgService.runInsert(ydjgFormGl);
		}
		
		if(returnFlg) returnFlg = ydjgService.runInsert(ydjgForm);
		return returnFlg;
	}
	/** 
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param shlc �������ID
	 * @param ssydsqid ����ID
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(String shlc, String ssydsqid) throws Exception{
		
		YdjgService ydjgService = new YdjgService();
		
		// �������������״̬
		YdshForm upForm = new YdshForm();
		upForm.setSsydsqid(ssydsqid);
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, ssydsqid);
		
		if(result){
			
			// ��������IDȡ���춯���
			List<HashMap<String, String>> ydjgLst = ydjgService.getYdjg(ssydsqid);
			
			// �춯����
			String ssydlx = "";
			for(HashMap<String, String> ydjg : ydjgLst){
				
				// �춯����
				ssydlx = ydjg.get("ssydlx");

				HashMap<String, String> cwxxMap =  new HashMap<String, String>();
				HashMap<String, String> xsInfo = ydjgService.getXsInfo(ydjg.get("xh"));
				cwxxMap.put("xh", ydjg.get("xh"));	
				cwxxMap.put("nj", xsInfo.get("nj"));
				cwxxMap.put("bjdm", xsInfo.get("bjdm"));
				cwxxMap.put("zydm", xsInfo.get("zydm"));
				cwxxMap.put("xydm", xsInfo.get("xydm"));
				cwxxMap.put("rzsj", ydjg.get("ydqqsrzsj"));
				cwxxMap.put("lddm", ydjg.get("ydqlddm"));
				cwxxMap.put("qsh", ydjg.get("ydqqsh"));
				cwxxMap.put("cwh", ydjg.get("ydqcwh"));
				
				// ѧ����ס��Ϣ�ع�
				dao.updateCwxxb(cwxxMap);

				// ��������մ�λ���
				if(_YDLX_SSTZ.equalsIgnoreCase(ssydlx)&& ydjgLst!=null && ydjgLst.size()==1){
					cwxxMap =  new HashMap<String, String>();
					cwxxMap.put("xh", "");	
					cwxxMap.put("rzsj", "");
					cwxxMap.put("nj", ydjg.get("ydhnj"));
					cwxxMap.put("bjdm", ydjg.get("ydhbjdm"));
					cwxxMap.put("zydm", ydjg.get("ydhzydm"));
					cwxxMap.put("xydm", ydjg.get("ydhxydm"));					
					cwxxMap.put("lddm", ydjg.get("ydhlddm"));
					cwxxMap.put("qsh", ydjg.get("ydhqsh"));
					cwxxMap.put("cwh", ydjg.get("ydhcwh"));	
					
					// �մ�λ��Ϣ�ع�
					dao.updateCwxxb(cwxxMap);			
				}else if(_YDLX_SSRZ.equalsIgnoreCase(ssydlx) && ydjgLst!=null && ydjgLst.size()==1){ // ��ס
					cwxxMap =  new HashMap<String, String>();
					cwxxMap.put("xh", "");	
					cwxxMap.put("rzsj", "");
					cwxxMap.put("rzyydm", "");
					cwxxMap.put("nj", ydjg.get("ydhnj"));
					cwxxMap.put("bjdm", ydjg.get("ydhbjdm"));
					cwxxMap.put("zydm", ydjg.get("ydhzydm"));
					cwxxMap.put("xydm", ydjg.get("ydhxydm"));					
					cwxxMap.put("lddm", ydjg.get("ydhlddm"));
					cwxxMap.put("qsh", ydjg.get("ydhqsh"));
					cwxxMap.put("cwh", ydjg.get("ydhcwh"));	
					
					// �մ�λ��Ϣ�ع�
					dao.updateCwxxb(cwxxMap);			
				}
			}
			// �춯����������ݵ����
			if(ydjgLst!=null && ydjgLst.size()>0){
				
				// ɾ���������Ӧ�������춯�����
				int count = ydjgService.runDeleteYdjg(ssydsqid);
				
				
				// ɾ���ɹ�
				if(count >0){
	
//					String ssydlxmc = ssydlx.equals(_YDLX_TS) ? "����" : "�������";
//					//���ô�����Ϣ
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setGnmkMc("�����춯���");
//					dbcz.setXmmc(ssydlxmc);
//					dbcz.shPush(ssydsqid,shlc);
					
					return true;
				}else{
					return false;
				}
			}
		}
		return result;
		
	}
	/**
	 * @throws Exception  
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����05:09:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(YdshForm t, User user) throws Exception {
		

		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		String[] ssydlxs = t.getSsydlxs();
		String[] sqshHideCwxxs = t.getSqshHideCwxxs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			YdshForm model = new YdshForm();
			
			model.setSplcid(splcs[i]);
			model.setSsydsqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSfcwcsh(t.getSfcwcsh());
			model.setJflx(t.getJflx());
			model.setZsfje(t.getZsfje());
			model.setXh(xhs[i]);
			model.setSsydlx(ssydlxs[i]);
			// ========== ��λ���� begin ============
			if(ssydlxs[i].equals(_YDLX_SSTZ)){
				model.setCwxx(sqshHideCwxxs[i]);
			}else if(ssydlxs[i].equals(_YDLX_SSRZ)){//��ס����
				model.setRzcwxx(sqshHideCwxxs[i]);
			}
			// ========== ��λ���� end ============
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
	
}
