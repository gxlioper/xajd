package com.zfsoft.xgxt.xszz.knsrd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcDao;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcForm;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import common.Globals;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: 2013��ѧ������--�������϶�
 * @���ߣ� Penghui.Qu
 * @ʱ�䣺 2013-4-22 ����08:51:11
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdService extends SuperServiceImpl<KnsrdForm, KnsrdDao>
		implements Constants {
	
	private static final String SQSH = "1";// �������
	
	private ShlcInterface shlc = new CommShlcImpl();

	private KnsrdDao dao = new KnsrdDao();
	
	private KnsdcDao dcDao = new KnsdcDao();
	
	private KnsjgDao jgDao = new KnsjgDao();

	public KnsrdService() {
		super.setDao(dao);
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @����:��word��ʽ��ӡ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-18 ����04:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsxx ѧ����Ϣ
	 * @param jtqkmodel  ��ͥ��Ϣ
	 * @param xxmc  ѧУ����
	 * @param knsdc	����������
	 * @param rddc rddc
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdForm model,
			List<HashMap<String, String>> knsdc, String rddc,HttpServletRequest request) throws Exception {
		/*��ͥ�������*/
		JtqkdcService jtqkdcService = new JtqkdcService();
		String xh=xsxx.get("xh");
		// ����ѧ����ͥ������Ϣ
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xh);
		
		
		//�����ѧ��ͥ������Ϣ���Ի���ѯ
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xh);
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(knsdc == null){
			knsdc = new ArrayList<HashMap<String,String>>();
		}
		if(jtqkmodel == null){
			jtqkmodel = new JtqkdcForm();
		}
		if(rddc == null){
			rddc = "";
		}

		data.putAll(xsxx);
		data.put("xxmc", Base.xxmc);
		data.put("sqrdly", HtmlUtil.xmlZy(model.getSqly()));
		data.put("kndc", knsdc);
		data.put("rddc", rddc);
		//�й�����ѧԺ���Ի�
		if(Base.xxdm.equals("10355")){
			data.put("Jtrjnsrs", model.getJtrjnsr());
		}
		data.put("jtqk", jtqkmodel);// ��ͥ���
		HashMap<String, String> knsqMap=getKnsqInfo(model.getGuid());
		data.put("knsqInfo", knsqMap);
		data.put("jtqkInfo", jtqkInfo);//�Ϻ������ѧ���Ի���ѯ
		
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh,4);
		List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
		data.put("pjjgList4line", pjjgList4line);
		data.put("pjjgList3line", pjjgList3line);
		List<HashMap<String, String>> cyList4line = jtqkService.getJtcyList(xh,4);
		data.put("cyList4line", cyList4line);
		List<HashMap<String, String>> cyList3line = jtqkService.getJtcyList(xh,3);
		data.put("cyList3line", cyList3line);
		ZzxmjgService zzxmjgService =new ZzxmjgService();
		List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
		data.put("zzjgList4line", zzjgList4line);
		data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// ��������
		data.put("rxny", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));//��ѧ����
		//��ѧ�ż��س�Ա�б� 
		if(Base.xxdm.equals("10264")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyListSh(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (3 - cyList.size()) < 0 ? 0 : (3 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else if(Base.xxdm.equals("90211")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));
			data.put("rxrq", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));
			
		}else if(Base.xxdm.equals("10277")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (4 - cyList.size()) < 0 ? 0 : (4 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
		}
		
		// ============Ϊ���������α�ţ�A,B...��������ѡ�еĵ��α��浽knsdczmbhstr begin============
		String knsdczmbhstr = "";
		String rddcmc = ""; // ���ѵ�������
		for (int i = 0; i < knsdc.size(); i++) {
			HashMap<String, String> knsdcMap = knsdc.get(i);
			String knsdczmbh = String.valueOf((char)(65+i));
			knsdcMap.put("knsdczmbh", knsdczmbh);
			if(knsdcMap.get("dcdm").equals(rddc)){
				knsdczmbhstr = knsdczmbh;
				rddcmc = knsdcMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdczmbhstr);
		data.put("rddcmc", rddcmc);
		// ============Ϊ���������α�ţ�A,B...��������ѡ�еĵ��α��浽knsdczmbhstr end============
		// ============�ڹ���ѧ ��λ¼����� begin============
		XsxxglService xsxxglService = new XsxxglService();
		List<HashMap<String, String>> stuQgzxXsgwxxList = xsxxglService.getStuQgzxXsgwxxList(xsxx.get("xh"));
		HashMap<String, String> stuQgzxXsgwxxMap = new HashMap<String, String>();
		if(stuQgzxXsgwxxList.size() > 0){
			stuQgzxXsgwxxMap = stuQgzxXsgwxxList.get(0);
		}
		data.put("stuQgzxXsgwxxMap", stuQgzxXsgwxxMap);
		// ============�ڹ���ѧ ��λ¼����� end============
		// ============��������  begin============
		// �ѻ�ѧ��
		StringBuffer jxjStr = new StringBuffer();
		List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xsxx.get("xh"));
		for (int i = 0; i < pjjg.size(); i++) {
			HashMap<String, String> pj = pjjg.get(i);
			if(StringUtils.isNotNull(pj.get("xmmc")) && pj.get("xmmc").contains("��ѧ��")){
				jxjStr.append(pj.get("xn"))
				.append(pj.get("xqmc")).append(" ")
				.append(pj.get("xmmc")).append(" ");
			}
		}
		data.put("jxjStr", jxjStr.toString());
		// ============��������  end============
		// ����
		WdgwsqService wdgwsqService = new WdgwsqService();
		HashMap<String, String> qsxx= wdgwsqService.getQsxx(xsxx.get("xh"));
		String qsbh=null;
		if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
			qsbh="";
		}else{
			qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
		}
		data.put("qsbh", qsbh);
		data.put("qsxx", qsxx);
		// ����ְ��
		DwwhService dwwhService = new DwwhService();
		data.put("zwmc", dwwhService.getZwForXh(xsxx.get("xh")));
		
		//��ü�ͥ��Ա��ϵ���еĸ�����Ϣ�����ڴ�ӡ
		HashMap<String, String> fqInfo = jtqkService.getfqInfo(xsxx.get("xh"));
		//��ü�ͥ��Ա�����е�ĸ����Ϣ�����ڴ�ӡ
		HashMap<String, String> mqInfo = jtqkService.getmqInfo(xsxx.get("xh"));
		
		if(fqInfo == null){
			fqInfo = new HashMap<String, String>();
		}
		if(mqInfo == null){
			mqInfo = new HashMap<String, String>();
		}
		
		data.put("fqInfo", fqInfo);
		data.put("mqInfo", mqInfo);
		
		//����ʦ��
		if("10718".equals(Base.xxdm)) {
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// ��������
			ZzxmjgService zzxmjg = new ZzxmjgService(); 
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "��");
		    }else{
		        data.put("zxdk", "��");
		    }
		}
		
		if("10277".equals(Base.xxdm)){
			data.put("knyylist", getKnnyList(knsqMap.get("ylzd5")));
		}
		
		//ѧ����Ƭ
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xsxx.get("xh"));
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//ȡĬ����Ƭ
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		
		data.put("photo", photo);
		
		int i=1;
		if(null!=infoList){
		for(HashMap<String,String> sh:infoList){
			data.put("fdysh"+i, sh.get("shzt") == null?"":sh.get("shzt"));
		}}
		List<HashMap<String,String>> shInfoList = ShlcUtil.getShlcInfo(model.getGuid());
		String shsjTemp = "";
		String[] shsjTempArray;
		for(int gg = 0; gg<shInfoList.size();gg++){
			shsjTemp = shInfoList.get(gg).get("shsj");
			if(shsjTemp!=null){
				shsjTemp = shsjTemp.substring(0, shsjTemp.indexOf(" "));
				shsjTempArray = shsjTemp.split("-");
				shInfoList.get(gg).put("shsjnian", shsjTempArray[0]);  //���ʱ����
				shInfoList.get(gg).put("shsjyue", shsjTempArray[1]);  //���ʱ����
				shInfoList.get(gg).put("shsjri", shsjTempArray[2]);  //���ʱ����
			}
		}
		data.put("shInfoList", shInfoList);
		//����ʦ����������������Ϣ
		HashMap<String, String> spxxInfo =getSpxxInfo(model.getGuid());
		if(spxxInfo.isEmpty()){
			spxxInfo=new HashMap<String,String>();
			spxxInfo.put("ejtjdcdm", "");
			spxxInfo.put("sjtjdcdm", "");
		}
		if(StringUtils.isNull(spxxInfo.get("ejtjdcdm"))){
			spxxInfo.put("ejtjdcdm", "");
		}
		if(StringUtils.isNull(spxxInfo.get("sjtjdcdm"))){
			spxxInfo.put("sjtjdcdm", "");
		}
		
		/*�����մɹ�������ְҵ����ѧԺ��ѧУ�ļ�ͥ�������û�м�ͥ�������롢��ͥ�˾��������ֶ���Ҫ��������*/
		if(Globals.XXDM_JXTCGYMSZYJSXY.equals(Base.xxdm)){
			HashMap<String, String> jtsrqk = jtqkdcService.getJtsrqkByXh(model.getXh());
			data.put("jtsrqk", jtsrqk);
		}
		
		data.putAll(spxxInfo);
		return getWord(data);
	}
	
	
	/**
	 * @����: �ƶ��˻�ȡ��ӡFile
	 * @���ߣ��´���[���ţ�1620]
	 * @���ڣ�2018-7-11����04:00
	 * @throws Exception 
	 * 
	 */
	public File printWordForYdxg(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdForm model,
			List<HashMap<String, String>> knsdc, String rddc,HttpServletRequest request) throws Exception {
		String xh=xsxx.get("xh");
		Map<String, Object> data = new HashMap<String, Object>();
		data.putAll(xsxx);
		if(model.getGuid() != null){
			HashMap<String, String> knsqMap=getKnsqInfo(model.getGuid());
			data.put("knsqInfo", knsqMap);
		} else{
			HashMap<String, String> knsqMap=  new HashMap<String, String>();;
			knsqMap.put("dcmc", model.getDcmcYdxg());
			data.put("knsqInfo", knsqMap);
		}
		// ����ѧ����ͥ������Ϣ
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xsxx.get("xh"));
		List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
		data.put("cyList", cyList);
		//��ͥ��Ա�б����
		int blankSize = (4 - cyList.size()) < 0 ? 0 : (4 - cyList.size());
		data.put("blankList", jtqkService.getBlankList(blankSize));
		data.put("Jtrjnsrs", model.getJtrjnsr());
		return getWord(data);
	}
	
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @����: ���������word��ʽ��ӡ
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-18 ����04:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsxx ѧ����Ϣ
	 * @param jtqkmodel  ��ͥ��Ϣ
	 * @param xxmc  ѧУ����
	 * @param knsdc	����������
	 * @param rddc rddc
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File printjgForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdForm model,
			List<HashMap<String, String>> knsdc, String rddc,HttpServletRequest request) throws Exception {
		/*��ͥ�������*/
		JtqkdcService jtqkdcService = new JtqkdcService();
		String xh=xsxx.get("xh");
		// ����ѧ����ͥ������Ϣ
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xh);
		//�����ѧ��ͥ������Ϣ���Ի���ѯ
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xh);	
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(knsdc == null){
			knsdc = new ArrayList<HashMap<String,String>>();
		}
		if(jtqkmodel == null){
			jtqkmodel = new JtqkdcForm();
		}
		if(rddc == null){
			rddc = "";
		}
		
		data.putAll(xsxx);
		data.put("xxmc", Base.xxmc);
		data.put("sqrdly", HtmlUtil.xmlZy(model.getSqly()));
		data.put("kndc", knsdc);
		//�й�����ѧԺ���Ի�
		if(Base.xxdm.equals("10355")){
			data.put("Jtrjnsrs", model.getJtrjnsr());
		}
		data.put("jtqk", jtqkmodel);// ��ͥ���
		HashMap<String, String> knsqjgMap=getKnsqjgInfo(model.getGuid());
		data.put("knsqInfo", knsqjgMap);
		data.put("jtqkInfo", jtqkInfo);//�Ϻ������ѧ���Ի���ѯ
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh,4);
		List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
		data.put("pjjgList4line", pjjgList4line);
		data.put("pjjgList3line", pjjgList3line);
		List<HashMap<String, String>> cyList4line = jtqkService.getJtcyList(xh,4);
		data.put("cyList4line", cyList4line);
		List<HashMap<String, String>> cyList3line = jtqkService.getJtcyList(xh,3);
		data.put("cyList3line", cyList3line);
		ZzxmjgService zzxmjgService =new ZzxmjgService();
		List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
		data.put("zzjgList4line", zzjgList4line);
		data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// ��������
		data.put("rxny", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));//��ѧ����
		//����ʦ����������������Ϣ
		HashMap<String, String> spxxInfo =getSpxxInfo(model.getGuid());
		if(spxxInfo.isEmpty()){
			spxxInfo=new HashMap<String,String>();
			spxxInfo.put("ejtjdcdm", "");
			spxxInfo.put("sjtjdcdm", "");
		}
		data.putAll(spxxInfo);
		data.put("rddc", spxxInfo.get("yjtjdc")==null?rddc:spxxInfo.get("yjtjdc"));
		//��ѧ�ż��س�Ա�б� 
		if(Base.xxdm.equals("10264")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyListSh(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (3 - cyList.size()) < 0 ? 0 : (3 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else if(Base.xxdm.equals("90211")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));
			data.put("rxrq", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));
			
		}else if(Base.xxdm.equals("10277")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (4 - cyList.size()) < 0 ? 0 : (4 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
		}
		
		// ============Ϊ���������α�ţ�A,B...��������ѡ�еĵ��α��浽knsdczmbhstr begin============
		String knsdczmbhstr = "";
		String rddcmc = ""; // ���ѵ�������
		for (int i = 0; i < knsdc.size(); i++) {
			HashMap<String, String> knsdcMap = knsdc.get(i);
			String knsdczmbh = String.valueOf((char)(65+i));
			knsdcMap.put("knsdczmbh", knsdczmbh);
			if(knsdcMap.get("dcdm").equals(rddc)){
				knsdczmbhstr = knsdczmbh;
				rddcmc = knsdcMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdczmbhstr);
		data.put("rddcmc", rddcmc);
		// ============Ϊ���������α�ţ�A,B...��������ѡ�еĵ��α��浽knsdczmbhstr end============
		// ============�ڹ���ѧ ��λ¼����� begin============
		XsxxglService xsxxglService = new XsxxglService();
		List<HashMap<String, String>> stuQgzxXsgwxxList = xsxxglService.getStuQgzxXsgwxxList(xsxx.get("xh"));
		HashMap<String, String> stuQgzxXsgwxxMap = new HashMap<String, String>();
		if(stuQgzxXsgwxxList.size() > 0){
			stuQgzxXsgwxxMap = stuQgzxXsgwxxList.get(0);
		}
		data.put("stuQgzxXsgwxxMap", stuQgzxXsgwxxMap);
		// ============�ڹ���ѧ ��λ¼����� end============
		// ============��ѧ��  begin============
		// �ѻ�ѧ��
		StringBuffer jxjStr = new StringBuffer();
		List<HashMap<String, String>> pjjg =  pjjgService.getHjqkList(xsxx.get("xh"));
		for (int i = 0; i < pjjg.size(); i++) {
			HashMap<String, String> pj = pjjg.get(i);
			//if(StringUtils.isNotNull(pj.get("xmmc")) && pj.get("xmmc").contains("��ѧ��")){ 
			if(StringUtils.isNotNull(pj.get("xmmc"))){
				jxjStr.append(pj.get("xn"))
				.append(pj.get("xqmc")).append(" ")
				.append(pj.get("xmmc")).append(" ");
			}
		}
		data.put("jxjStr", jxjStr.toString());
		// ============��ѧ��  end============
		// ����
		WdgwsqService wdgwsqService = new WdgwsqService();
		HashMap<String, String> qsxx= wdgwsqService.getQsxx(xsxx.get("xh"));
		String qsbh=null;
		if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
			qsbh="";
		}else{
			qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
		}
		data.put("qsbh", qsbh);
		data.put("qsxx", qsxx);
		// ����ְ��
		DwwhService dwwhService = new DwwhService();
		data.put("zwmc", dwwhService.getZwForXh(xsxx.get("xh")));
				
		//��ü�ͥ��Ա��ϵ���еĸ�����Ϣ�����ڴ�ӡ
		HashMap<String, String> fqInfo = jtqkService.getfqInfo(xsxx.get("xh"));
		//��ü�ͥ��Ա�����е�ĸ����Ϣ�����ڴ�ӡ
		HashMap<String, String> mqInfo = jtqkService.getmqInfo(xsxx.get("xh"));
		
		if(fqInfo == null){
			fqInfo = new HashMap<String, String>();
		}
		if(mqInfo == null){
			mqInfo = new HashMap<String, String>();
		}
		
		data.put("fqInfo", fqInfo);
		data.put("mqInfo", mqInfo);
		
		
		//����ʦ��
		if("10718".equals(Base.xxdm)) {
			ZzxmjgService zzxmjg = new ZzxmjgService(); 
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqjgInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// �������� 
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "��");
		    }else{
		        data.put("zxdk", "��");
		    }
		}
		//�Ϻ�����ѧԺ���Ի�
		if("10277".equals(Base.xxdm)){
			data.put("knyylist", getKnnyList(knsqjgMap.get("ylzd5")));
		}
		
		/*�����մɹ�������ְҵ����ѧԺ��ѧУ�ļ�ͥ�������û�м�ͥ�������롢��ͥ�˾��������ֶ���Ҫ��������*/
		if(Globals.XXDM_JXTCGYMSZYJSXY.equals(Base.xxdm)){
			HashMap<String, String> jtsrqk = jtqkdcService.getJtsrqkByXh(xh);
			data.put("jtsrqk", jtsrqk);
		}
		
		//ѧ����Ƭ
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xsxx.get("xh"));
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//ȡĬ����Ƭ
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		
		data.put("photo", photo);
		
		int i=1;
		if(null!=infoList){
		for(HashMap<String,String> sh:infoList){
			data.put("fdysh"+i, sh.get("shzt") == null?"":sh.get("shzt"));
		}}
		return getWord(data);
	}
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 * @����:��ȡ�ĵ�
	 * @���ߣ��Ų�·
	 * @���ڣ�2013-6-18 ����04:13:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return File ��������
	 * @throws
	 */
	private File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"xszz/knsrdb_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(""+data.get("bjmc")+""+data.get("xh")+"["+data.get("xm")+"]"));
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			}
		}catch (Exception e) {
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdsq.xml", FreeMarkerUtil.getFileName(""+data.get("bjmc")+""+data.get("xh")+"["+data.get("xm")+"]"));
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			}
		}

		return wordFile;
	
	}

	/**
	 * 
	 * @����:�����������϶�����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����08:50:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveKnssq(KnsrdForm model) throws Exception {
		// ���������������Ϣ����
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		model.setXn(jcszModel.getRdxn());
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			model.setXq("on");
		}
		else{
			model.setXq(jcszModel.getRdxq());
		}
		// ���жϵ�ǰѧ��ѧ�ڸ�ѧ����ľ�������������
		KnsrdForm knsrdModel = dao.getModel(model, new String[] {
				model.getXh(), model.getXn(), model.getXq() });
		
		//��ȡ��������
		if(Globals.XXDM_ZJDX.equals(Base.xxdm)){//���
		HashMap<String,String> sqxzMap = dao.getSqxz(model.getYlzd1(),model.getXh(),model.getXn(),model.getXq());
		if(StringUtils.isNull(model.getYlzd1())&&StringUtils.isNotNull(sqxzMap.get("xh"))){
			model.setYlzd9("�����϶�");
		}else{
			model.setYlzd9(sqxzMap.get("sqxz"));
		}
		}
		
		if (!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())&&"submit".equalsIgnoreCase(model.getType())&&jcszModel != null && !StringUtil.isNull(jcszModel.getSplc())) {
			// �����������̵������¼��
			model.setShlc(jcszModel.getSplc());
		}
		
		
		//�������޸Ķ����������,���������Ϊ�գ��������·���
		if(StringUtil.isNull(model.getShlc())){
			
			model.setShlc(jcszModel.getSplc());
		}
		
		
		// ��������ʱ��Ϊ��ǰϵͳʱ��,Ĭ�����״̬Ϊ��δ��ˡ�
		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		
		if(model.getType().equals("submit")){
			model.setShzt(YW_SHZ);
		}else{
			model.setShzt(YW_WTJ);
		}
		
		
		if (null == knsrdModel) {

			boolean isSuccess = dao.runInsert((KnsrdForm) StringUtils.formatBean(model));

			if (isSuccess) {
				if(model.getType().equals("submit")){
					// ��ȡ������Ϣ
					knsrdModel = dao.getModel(model, new String[] { model.getXh(),model.getXn(), model.getXq() });
	
					if (null != jcszModel
							&& !StringUtil.isNull(jcszModel.getSplc())) {
						// �����������״̬
						//dao.insertShzt(knsrdModel, jcszModel.getSplc());
						isSuccess = shlc.runSubmit(knsrdModel.getGuid(), jcszModel.getSplc(),knsrdModel.getXh(),"xszz_knsrd_sh.do","xszz_knsrd_sq.do");
					}
				}
			}
			return isSuccess;
		} else {
			boolean isSuccess = dao.runUpdate((KnsrdForm) StringUtils.formatBean(model));
			if (isSuccess) {
				if(model.getType().equals("submit")){
					// ��ȡ������Ϣ
					knsrdModel = dao.getModel(model, new String[] { model.getXh(),model.getXn(), model.getXq() });
	
					if (null != jcszModel
							&& !StringUtil.isNull(jcszModel.getSplc())) {
						// �����������״̬
						//dao.insertShzt(knsrdModel, jcszModel.getSplc());
						isSuccess = shlc.runSubmit(knsrdModel.getGuid(), jcszModel.getSplc(),knsrdModel.getXh(),"xszz_knsrd_sh.do","xszz_knsrd_sq.do");
					}
				}
			}
			return isSuccess;
		}
	}
	
	public boolean updateModel(KnsrdForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid,xh,"xszz_knsrd_sh.do","xszz_knsrd_sq.do");
	}

	// ɾ������
	public int runDelete(String[] values) throws Exception {

		// ɾ����˼�¼
		dao.delShzt(values);

		// ɾ�������¼
		int delNum = dao.delKnssq(values);

		// ɾ����ش���
		String[] ids = dao.getExistsId(values);

		// ȥ���ظ�
		List<String> valuesList = new ArrayList<String>(Arrays.asList(values));
		valuesList.removeAll(Arrays.asList(ids));

		String[] yscId = valuesList.toArray(new String[] {});

		if (yscId != null && yscId.length > 0) {
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(yscId, "ѧ������");
		}

		return delNum;
	}

	/**
	 * 
	 * @����:��ȡ�������������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����01:52:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSplcInfo(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}

		return dao.getSplcInfo(model);
	}

	/**
	 * 
	 * @����:�������϶�--��˲�ѯ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����04:19:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getShjlList(KnsrdForm t, User user)
			throws Exception {

		return dao.getShjlList(t, user);
	}

	/**
	 * 
	 * @����:��˲���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����04:11:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	@TransactionControl
	public boolean saveKnssh(KnsrdForm t, User user) throws Exception {

		//HashMap<String, String> beforeSpxx = dao.getBeforeSpxx(t);

//		// ��ǰ��˸�λ�ڵ�һ�����������˻ز��������������߼�����������
//		if (StringUtil.isNull(beforeSpxx.get("sjgw")) && TH.equals(t.getShzt())) {
//			return false;
//		}
		//��������
		JcszDao jcszDao=new JcszDao();
		JcszForm jcszForm= jcszDao.getModel();
		if (TG.equals(t.getShjg())&&OPEN.equals(jcszForm.getRssfkz())){
			checkRskz(t);
		}
		ShxxModel model = new ShxxModel();
		model.setYwid(t.getGuid());
		model.setShlc(jcszForm.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getXtgwid());
		model.setSqrid(t.getXh());
		model.setTzlj("xszz_knsrd_sh.do");
		model.setTzljsq("xszz_knsrd_sq.do");
		
		if(t.getShjg().equals("1")){
			// �O��ҵ���ֶ�1[ҵ������]
			model.setZd1("�Ƽ�����");
			// �O��ҵ���ֶ�2[ҵ��ID]
			model.setZd2(t.getRddc());
			// �O��ҵ���ֶ�3
			KnsdcForm dcForm = new KnsdcForm();
			dcForm.setDcdm(t.getRddc());
			dcForm = dcDao.getModel(dcForm);							
			model.setZd3(dcForm.getDcmc());
			/*�㽭����ְҵ����ѧԺ�����ҳ�����������������ֶ�*/
			if("12866".equals(Base.xxdm)){
				model.setZd4("����������");
				model.setZd5(t.getYlzd4());
				model.setZd6(t.getYlzd4());
			}
			if("10335".equals(Base.xxdm)){
			model.setZd4("�޳��������");
			// �O��ҵ���ֶ�2[ҵ��ID]
			KnsrdForm ywModel = dao.getModel(t);
			HashMap<String,String> sqxzMap = dao.getSqxz(t.getRddc(),ywModel.getXh(),ywModel.getXn(),ywModel.getXq());
			model.setZd9(sqxzMap.get("sqxz"));
			model.setZd6(t.getYlzd3());
			model.setZd10(t.getYlzd10());
			}
			//�������ո��Ի�
			if("13871".equals(Base.xxdm)){
				model.setZd6(t.getKnpx());
				model.setZd4("��������");
			}
			
		}
		
		boolean result = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			//����������е����״̬���϶����α��
			KnsrdForm kdModel = new KnsrdForm();
			kdModel.setGuid(t.getGuid());
			if(zhzt.equals(Constants.SH_TG)){
				kdModel.setRddc(t.getRddc());
			}
			kdModel.setShzt(zhzt);
			kdModel.setYlzd3(t.getYlzd3());
			kdModel.setShrddc(t.getRddc());
			result = dao.runUpdateNotCommit(kdModel);
			// ���һ�����ͨ��ʱ
			if(result && zhzt.equals(Constants.SH_TG)){
				// ������ʽ��
				KnsjgDao knsjgDao = new KnsjgDao();
				KnsjgForm knsjgModel = new KnsjgForm();
				KnsrdForm copyModel = getModel(t);
				copyModel.setRddc(t.getRddc());
				copyModel.setYlzd3(t.getYlzd3());
				//�������ո��Ի�
				if("13871".equals(Base.xxdm)){
					copyModel.setKnpx(t.getKnpx());
				}
				BeanUtils.copyProperties(knsjgModel, copyModel);
				// �Ȱ�ѧ�š�ѧ�ꡢѧ���ж��Ƿ����
				Map<String, String> map = knsjgDao.getXsknsjg(knsjgModel
						.getXh(), knsjgModel.getXn(), knsjgModel.getXq());
				if (!map.isEmpty()) {
					knsjgDao.delKnsjg(knsjgModel.getXn(), knsjgModel.getXq(), knsjgModel.getXh());
				}
				
				knsjgModel.setSjly(SQSH);
				knsjgModel.setLylcywid(t.getGuid());
				
				/*�㽭����ְҵ����ѧԺ����������������������ylzd4��*/
				if("12866".equals(Base.xxdm)){
					knsjgModel.setYlzd4(t.getYlzd4());
				}
				//����ʦ����ѧ���Ի�
				if("10346".equals(Base.xxdm)){
					knsjgModel.setYlzd4(copyModel.getYlzd9());
				}
				knsjgDao.runInsertNotCommit(knsjgModel);
			}
		
		return result;
	}

	/** 
	 * @����:��������
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-10 ����04:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * void �������� 
	 * @throws 
	 */
	private void checkRskz(KnsrdForm t) throws Exception{
		JcszDao jcszDao=new JcszDao();
		JcszForm model=jcszDao.getModel();
		String rskzfw = model.getRskzfw();
		String rskzjb = model.getRskzjb();
		String rskzkg = model.getRssfkz();
		String rskzsplc=model.getSplc();
		KnsrdForm knsrdForm=dao.getModel(t);
		if (Integer.parseInt(dao.getSplcXh(t.getXtgwid(),rskzsplc))>=Integer.parseInt(dao.getSplcXh(rskzjb,rskzsplc))){
			//��ȡ����������Ϣ
			HashMap<String, String> rsszMap=jcszDao.getRsszOne(rskzfw,t.getXh(),knsrdForm,t.getRddc());
			String xzrs = rsszMap.get("zzrs");
			//δ���þͲ�����
			if (StringUtil.isNull(xzrs)){
				return ;
			}
			String tgrs = null;
			
			if (RSKZFW_NJXY.equals(rskzfw)){
				tgrs = dao.getTgrsByNjxy(t, rsszMap);
			} else if (RSKZFW_NJZY.equals(rskzfw)){
				tgrs = dao.getTgrsByNjzy(t, rsszMap);
			} else if (RSKZFW_XY.equals(rskzfw)){
				tgrs = dao.getTgrsByXy(t, rsszMap);
			} else if (RSKZFW_ZY.equals(rskzfw)){
				tgrs = dao.getTgrsByZy(t, rsszMap);
			} else {
				tgrs = dao.getTgrsByBj(t, rsszMap);
			}
			
			if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))){
				throw new SystemException(MessageKey.RSKZ_FAIL,xzrs);
			}
		}
	}
	/**
	 * 
	 * @����:������˲���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����10:13:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean cancelKnssh(String ywid) throws Exception{
		//����ҵ��״̬
		KnsrdForm model = new KnsrdForm();
		model.setGuid(ywid);
		model.setRddc("");
		model.setShzt(Constants.YW_SHZ);
		boolean result = this.updateModel(model);
		//�ع����������
		if(result){
			jgDao.delJgb(ywid);
		}
		return result;
	}

	/**
	 * 
	 * @����:������˱���---�������ʧ�ܵ�ѧ�š�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����02:22:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<String> ��������
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(KnsrdForm t, User user) throws Exception {

		String[] ids = t.getId();
		String[] gwid = t.getGwid();
		String[] xhs = t.getXhs();
		
		JcszForm jcszForm= new JcszDao().getModel();
		//���ж�ѡ���ѧ���������Ƿ񳬹�ѡ��ѧ��������Ŀ����������
		if(TG.equals(t.getShzt())&&OPEN.equals(jcszForm.getRssfkz())){
			String sqids = "";
			for(int i=0;i<ids.length;i++){
				sqids += "'"+ids[i]+"',";
			}
			if(sqids.length()>0){
				sqids=sqids.substring(0, sqids.length()-1);
			}
			
			List<HashMap<String,String>> results = dao.getXzrs(sqids);
			for(int i=0;i<results.size();i++){
				HashMap<String,String> rmap = (HashMap<String,String>) results.get(i);
				if(new Integer(rmap.get("dqjb")).intValue()>=new Integer(rmap.get("kzjb")).intValue()){
					HashMap<String,String> tmap = dao.getKzrsTgrs(rmap.get("dqjb"),rmap.get("kzbm"),rmap.get("rskzfw"),t.getRddc());
					if(tmap!=null&&tmap.size()>0){
						if(new Integer(rmap.get("xzrs")).intValue()>new Integer(tmap.get("zzme")).intValue()-new Integer(tmap.get("ytggs")).intValue()){
							return MessageUtil.getText(MessageKey.SYS_AUD_PERS_OUT);
						}
					}
				}
			}
		}
		
		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			KnsrdForm model = new KnsrdForm();
			model.setGuid(ids[i]);
			model.setXtgwid(gwid[i]);								
			model.setRddc(t.getRddc());
			model.setYlzd3(t.getYlzd3());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);

			boolean isSuccess = saveKnssh(model, user);

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
	 * @����: �Ƿ������޸��������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����04:06:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public boolean allowUpdateSetting() {

		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @����:��ѧ�š�ѧ�ꡢѧ�ڲ�ѯѧ���������������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-25 ����04:59:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 *             KnsrdForm ��������
	 * @throws
	 */
	public KnsrdForm getModelByXnXq(KnsrdForm model) throws Exception {

		return dao.getModel(model, new String[] { model.getXh(), model.getXn(),
				model.getXq() });
	}

	/**
	 * 
	 * @����:���������������ɾ�������������Ӧ��Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����01:49:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean delKnssqFromKnsjg(String[] values) throws Exception {

		if (values == null || values.length == 0) {
			return false;
		}

		int num = dao.delKnssqFromKnsjg(values);

		if (num > 0) {
			dao.delKnsshFromKnsjg(values);
		}

		return num > 0;
	}

	/**
	 * 
	 * @����: ������ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����09:58:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return Map<String,Object> ��������
	 * @throws Exception 
	 * 
	 */
	public Map<String, Object> getShqkInfo(User user) throws Exception {
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();

		String xn = jcszModel.getRdxn();
		String xq = jcszModel.getRdxq();

		// ����������
		int zrs = Integer.valueOf(dao.getSqrs(user, xn, xq));
		// ����������
		List<HashMap<String, String>> shqkInfoList = dao.getShqkTjxx(user, xn,
				xq);
		// ����������ͨ��������ͨ���ʡ��������ͳ�����
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("zrs", zrs);

		List<HashMap<String, String>> shqkList = new ArrayList<HashMap<String, String>>();

		if (zrs == 0) {
			resultMap.put("zztgrs", "0");
			resultMap.put("zztgl", "0%");
		}

		for (int i = 0, n = shqkInfoList.size(); i < n; i++) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.putAll(shqkInfoList.get(i));

			double tgrs = Double.valueOf(shqkInfoList.get(i).get("tg"));
			double bgtrs = Double.valueOf(shqkInfoList.get(i).get("btg"));
			double dshrs = Double.valueOf(shqkInfoList.get(i).get("dsh"));
			DecimalFormat formater = new DecimalFormat("#.##%");

			if (tgrs + bgtrs + dshrs == 0) {
				map.put("tgl", "0%");
				map.put("btgl", "0%");
				map.put("dshl", "0%");
			} else {
				double tgl = tgrs / (tgrs + bgtrs + dshrs);
				double btgl = bgtrs / (tgrs + bgtrs + dshrs);
				double dshl = dshrs / (tgrs + bgtrs + dshrs);

				map.put("tgl", formater.format(tgl));
				map.put("btgl", formater.format(btgl));
				map.put("dshl", formater.format(dshl));
			}

			shqkList.add(map);

			if (i == n - 1) {
				// ����ͨ����
				double zztgl = tgrs / zrs;
				resultMap.put("zztgrs", shqkInfoList.get(i).get("tg"));
				resultMap.put("zztgl", formater.format(zztgl));
			}

		}

		resultMap.put("shqkList", shqkList);
		return resultMap;
	}

	/**
	 * 
	 * @����: �����ͳ�Ʋ�ѯ����ѧ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-3 ����04:25:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentsList(KnsrdForm model,
			User user) throws Exception {
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();

		String xn = jcszModel.getRdxn();
		String xq = jcszModel.getRdxq();

		model.setXn(xn);
		model.setXq(xq);

		return dao.getStudentsFromShtj(model, user);
	}
	/** 
	 * @����:�����������䷽ʽ��ͳ�ƶ�Ӧ��λ������
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����02:20:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rskzfw
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getShtgrs(JcszForm model) {
		if (StringUtils.isNull(model.getRskzfw())){
			return null;
		}
		return dao.getTgrs(model);
	}
	
	/**
	 * 
	 * @����:����GUID��ѯ������������Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-27 ����04:00:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKnsqInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getKnsqInfo(guid);
	}
	
	/**
	 * 
	 * @����:����GUID��ѯ��������������Ϣ
	 * @���� HongLin[���ţ�707]
	 * @���ڣ�2014-2-18 ����04:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKnsqjgInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getKnsqjgInfo(guid);
	}
	
	public HashMap<String, String> getSpxxInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getSpxxInfo(guid);
	}
	
	/**
	 * 
	 * @����:�жϵ�ǰ�û���ǰ�����Ƿ��������¼
	 * @���ߣ�cq
	 * @���ڣ�2014-2-13 ����03:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean getExists(KnsrdForm model, String user) throws Exception{
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		String xq = jcszModel.getRdxq();
		KnsrdForm knsrdModel = new KnsrdForm();
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			knsrdModel = dao.getModelOfXn(model, new String[] {
					user, jcszModel.getRdxn()});
		}else{
		knsrdModel = dao.getModel(model, new String[] {
				user, jcszModel.getRdxn(), xq });
		}
		
		if(null == knsrdModel){
			return false;
		}else {
			return true;
		}
		
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-18 ����03:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * void �������� 
	 * @throws 
	 */
	public HashMap<String, String> getNewKnsdc(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("���ID����Ϊ�գ�");
			throw new NullPointerException();
		}

		return dao.getNewKnsdc(model);
	}
	
	/**
	 * 
	 * @����:��˻�ȡ����zd5ֵ
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-8-14 ����02:53:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getNewBjpjjg(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("���ID����Ϊ�գ�");
			throw new NullPointerException();
		}

		return dao.getNewBjpjjg(model);
	}
	
	public HashMap<String, String> getNewKnyy(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("���ID����Ϊ�գ�");
			throw new NullPointerException();
		}

		return dao.getNewKnyy(model);
	}
	
	
	
	//��˵�������ҳ���÷���
	public List<HashMap<String,String>> getAllListSh(KnsrdForm t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getShjlList(t, user);
	}
	
	/**
	 * 
	 * @����: �����������ƽ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����03:33:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSqlymcList() {
		
		return dao.getSqlymcList();
	}
	
	/**
	 * @����:�㽭��ѧ���Ի�_��������һ�����ͨ�������������������ı�������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-10-19 ����10:56:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyKnsfp(KnsrdForm model) throws Exception{
		return dao.getXyKnsfp(model);
	}
	
	/**
	 * @����������ԭ�����->�����������ƣ���</br>�ָ������ڴ�����view
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��11�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param knyydm
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	public String getKnyymc(String knyydm) throws Exception{
		if(StringUtils.isNull(knyydm)){
			return "";
		}
		List<HashMap<String, String>> list=new KnsdcService().getKnyyList();
		HashMap<String,String> knyyMap=new HashMap<String,String>();
		for(HashMap<String,String> temp:list){
			knyyMap.put(temp.get("yydm"),temp.get("yymc"));
		}
		String[] arr=knyydm.split(",");
		StringBuilder knyymc=new StringBuilder();
		for(int i=0;i<arr.length-1;i++){
			knyymc.append(knyyMap.get(arr[i])).append("</br>");
		}
		knyymc.append(knyyMap.get(arr[arr.length-1]));
		return knyymc.toString();
	}
	
	/**
	 * @����������ԭ�����->��������ԭ��mapList,�����Ӧ��checked="1"�����ڴ�����update
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��11�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param knyydm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getKnnyList(String knyydm) throws Exception{
		List<HashMap<String, String>> knyyList=new KnsdcService().getKnyyList();  //����ԭ��list
		if(StringUtils.isNull(knyydm)){
			return knyyList;
		}
		for(HashMap<String,String> tempMap:knyyList){
			for(String yydm:knyydm.split(",")){
				if(yydm.equals(tempMap.get("yydm"))){
					tempMap.put("checked", "1"); //��������ԭ��ѡ
				}
			}
		}
		return knyyList;
	}
	
	public KnsrdForm getModel(KnsrdForm t) throws Exception {
		KnsrdForm form=dao.getModel(t);
		//�Ϻ�����ѧԺ���Ի�
		if("10277".equals(Base.xxdm)){
			String knyy=new KnsrdService().getKnyymc(form.getYlzd5());
			form.setYymc(knyy);
		}
		return form;
	}
	
	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-18 ����10:20:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	
	
	/**
	 * @description	����ȡ��ͥ��������list
	 * @author 		�� ������1282��
	 * @date 		��2017-12-13 ����04:24:35
	 * @return
	 */
	public List<HashMap<String, String>> getJtknlxList(){
		return dao.getJtknlxList();
	}
	
	/**
	 * @description	�� �ߵ�����Ʒlist
	 * @author 		�� ������1282��
	 * @date 		��2017-12-13 ����04:22:13
	 * @return
	 */
	public List<HashMap<String,String>> getGdxfpLxList(){
		return dao.getGdxfpLxList();
	}

	/**
	 * @����:��ȡ���������б�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/31 14:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String, String>> getSqlyList() {

		return dao.getSqlyList();
	}

	public String[] getSqlyListByDms(String[] ids) throws SQLException {

		return dao.getSqlyListByDms(ids);
	}
}
