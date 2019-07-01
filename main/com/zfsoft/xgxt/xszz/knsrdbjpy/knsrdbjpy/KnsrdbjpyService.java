package com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
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
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

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
public class KnsrdbjpyService extends SuperServiceImpl<KnsrdbjpyForm, KnsrdbjpyDao>
		implements Constants {
	
	private static final String SQSH = "2";// �������
	
	private ShlcInterface shlc = new CommShlcImpl();

	private KnsrdbjpyDao dao = new KnsrdbjpyDao();
	
	private KnsdcDao dcDao = new KnsdcDao();
	
	private KnsjgDao jgDao = new KnsjgDao();

	public KnsrdbjpyService() {
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
	 * @param knsdcbjpy	����������
	 * @param rddc rddc
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdbjpyForm model,
			List<HashMap<String, String>> knsdcbjpy, String rddc,HttpServletRequest request) throws Exception {

		// ����ѧ����ͥ������Ϣ
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xsxx.get("xh"));
		
		//�����ѧ��ͥ������Ϣ���Ի���ѯ
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xsxx.get("xh"));
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsxx == null){
			xsxx = new HashMap<String, String>();
		}
		if(knsdcbjpy == null){
			knsdcbjpy = new ArrayList<HashMap<String,String>>();
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
		data.put("kndc", knsdcbjpy);
		data.put("rddc", rddc);
		data.put("jtqk", jtqkmodel);// ��ͥ���
		data.put("knsqInfo", getKnsqInfo(model.getGuid()));
		data.put("jtqkInfo", jtqkInfo);//�Ϻ������ѧ���Ի���ѯ
		
		//��ѧ�ż��س�Ա�б� 
		if(Base.xxdm.equals("10264")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyListSh(xsxx.get("xh"));
			data.put("cyList", cyList);
			//��ͥ��Ա�б����
			int blankSize = (3 - cyList.size()) < 0 ? 0 : (3 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
		}
		
		// ============Ϊ���������α�ţ�A,B...��������ѡ�еĵ��α��浽knsdczmbhstr begin============
		String knsdcbjpyzmbhstr = "";
		String rddcmc = ""; // ���ѵ�������
		for (int i = 0; i < knsdcbjpy.size(); i++) {
			HashMap<String, String> knsdcbjpyMap = knsdcbjpy.get(i);
			String knsdcbjpyzmbh = String.valueOf((char)(65+i));
			knsdcbjpyMap.put("knsdczmbh", knsdcbjpyzmbh);
			if(knsdcbjpyMap.get("dcdm").equals(rddc)){
				knsdcbjpyzmbhstr = knsdcbjpyzmbh;
				rddcmc = knsdcbjpyMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdcbjpyzmbhstr);
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
		PjjgService pjjgService = new PjjgService();
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
			ZzxmjgService zzxmjg = new ZzxmjgService();
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			String csny = xsxx.get("csrq") == null ? "" : xsxx.get("csrq").replace("-", "").substring(0, 6);
			data.put("csny", csny);
			KnsrdbjpyForm myForm = (KnsrdbjpyForm) model;
			data.put("pyxzdb", myForm.getBjpyxzdbxms());
			data.put("pyhsj", DateTranCnDate.fomartDateToCn(myForm.getBjpyjgpyhsj(),FomartDateType.day));
//			data.put("fdyxm", getNewKnsdcbjpy(model).get("shr"));
//			data.put("fdyspsj", DateTranCnDate.fomartDateToCn(getNewKnsdcbjpy(model).get("shsj"),FomartDateType.day));
			// ========== �����Ϣ begin ============
			ShlcDao shlcDao = new ShlcDao();
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getGuid(), "asc");
			if(shyjList.size() > 0){
				HashMap<String, String> shyj = shyjList.get(0);
				data.put("fdyxm", shyj.get("xm"));
				data.put("fdyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 1){
				HashMap<String, String> shyj = shyjList.get(1);
				data.put("xyxm", shyj.get("xm"));
				data.put("xyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 2){
				HashMap<String, String> shyj = shyjList.get(2);
				data.put("xxxm", shyj.get("xm"));
				data.put("xxspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			// ========== �����Ϣ end ============
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "��");
		    }else{
		        data.put("zxdk", "��");
		    }
			data.put("rddc", model.getPddc() == null ? "" : model.getPddc());
			data.put("bjpyjgrdly", HtmlUtil.xmlZy(model.getBjpyjgrdly() == null ? "" : model.getBjpyjgrdly()));
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
		data.putAll(spxxInfo);
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
	 * @param knsdcbjpy	����������
	 * @param rddc rddc
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File printjgForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdbjpyForm model,
			List<HashMap<String, String>> knsdcbjpy, String rddc,HttpServletRequest request) throws Exception {

		// ����ѧ����ͥ������Ϣ
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xsxx.get("xh"));
		
		//�����ѧ��ͥ������Ϣ���Ի���ѯ
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xsxx.get("xh"));	
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsxx == null){
			xsxx = new HashMap<String, String>();
		}
		if(knsdcbjpy == null){
			knsdcbjpy = new ArrayList<HashMap<String,String>>();
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
		data.put("kndc", knsdcbjpy);
		data.put("rddc", rddc);
		data.put("jtqk", jtqkmodel);// ��ͥ���
		data.put("knsqInfo", getKnsqjgInfo(model.getGuid()));
		data.put("jtqkInfo", jtqkInfo);//�Ϻ������ѧ���Ի���ѯ
		
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
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
		}
		
		// ============Ϊ���������α�ţ�A,B...��������ѡ�еĵ��α��浽knsdczmbhstr begin============
		String knsdcbjpyzmbhstr = "";
		String rddcmc = ""; // ���ѵ�������
		for (int i = 0; i < knsdcbjpy.size(); i++) {
			HashMap<String, String> knsdcbjpyMap = knsdcbjpy.get(i);
			String knsdcbjpyzmbh = String.valueOf((char)(65+i));
			knsdcbjpyMap.put("knsdczmbh", knsdcbjpyzmbh);
			if(knsdcbjpyMap.get("dcdm").equals(rddc)){
				knsdcbjpyzmbhstr = knsdcbjpyzmbh;
				rddcmc = knsdcbjpyMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdcbjpyzmbhstr);
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
		PjjgService pjjgService = new PjjgService();
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
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqjgInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			String csny = xsxx.get("csrq") == null ? "" : xsxx.get("csrq").replace("-", "").substring(0, 6);
			data.put("csny", csny);
			KnsrdbjpyForm myForm = getModel(model);
			data.put("pyxzdb", myForm.getBjpyxzdbxms());
			data.put("pyhsj", DateTranCnDate.fomartDateToCn(myForm.getBjpyjgpyhsj(),FomartDateType.day));
//			data.put("fdyxm", getNewKnsdcbjpy(model).get("shr"));
//			data.put("fdyspsj", DateTranCnDate.fomartDateToCn(getNewKnsdcbjpy(model).get("shsj"),FomartDateType.day));
			// ========== �����Ϣ begin ============
			ShlcDao shlcDao = new ShlcDao();
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getGuid(), "asc");
			if(shyjList.size() > 0){
				HashMap<String, String> shyj = shyjList.get(0);
				data.put("fdyxm", shyj.get("xm"));
				data.put("fdyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 1){
				HashMap<String, String> shyj = shyjList.get(1);
				data.put("xyxm", shyj.get("xm"));
				data.put("xyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 2){
				HashMap<String, String> shyj = shyjList.get(2);
				data.put("xxxm", shyj.get("xm"));
				data.put("xxspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			// ========== �����Ϣ end ============
			ZzxmjgService zzxmjg = new ZzxmjgService(); 
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "��");
		    }else{
		        data.put("zxdk", "��");
		    }
			data.put("rddc", myForm.getPddc() == null ? "" : myForm.getPddc());
			data.put("bjpyjgrdly", HtmlUtil.xmlZy(myForm.getBjpyjgrdly() == null ? "" : myForm.getBjpyjgrdly()));
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
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
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
	public boolean saveKnssq(KnsrdbjpyForm model) throws Exception {
		// ���������������Ϣ����
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = jcszDao.getModel();
		
		model.setXn(jcszModel.getXn());
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			model.setXq("on");
		}
		else{
			model.setXq(jcszModel.getXq());
		}
		// ���жϵ�ǰѧ��ѧ�ڸ�ѧ����ľ�������������
		KnsrdbjpyForm knsrdbjpyModel = dao.getModel(model, new String[] {
				model.getXh(), model.getXn(), model.getXq() });

		
		
		
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
			model.setShzt(YW_BJPYZ);
		}else{
			model.setShzt(YW_WTJ);
		}
		
		
		if (null == knsrdbjpyModel) {
			return dao.runInsert((KnsrdbjpyForm) StringUtils.formatBean(model));
		} else {
			return dao.runUpdate((KnsrdbjpyForm) StringUtils.formatBean(model));
		}
	}
	
	public boolean updateModel(KnsrdbjpyForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid,xh,"xszz_knsrdbjpy_sh.do","xszz_knsrdbjpy_sq.do");
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
	public List<HashMap<String, String>> getSplcInfo(KnsrdbjpyForm model) {

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
	public List<HashMap<String, String>> getShjlList(KnsrdbjpyForm t, User user)
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
	public boolean saveKnssh(KnsrdbjpyForm t, User user) throws Exception {

		//HashMap<String, String> beforeSpxx = dao.getBeforeSpxx(t);

//		// ��ǰ��˸�λ�ڵ�һ�����������˻ز��������������߼�����������
//		if (StringUtil.isNull(beforeSpxx.get("sjgw")) && TH.equals(t.getShzt())) {
//			return false;
//		}
		//��������
		KnsjcszbjpyDao jcszDao=new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszForm= jcszDao.getModel();
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
		model.setTzlj("xszz_knsrdbjpy_sh.do");
		model.setTzljsq("xszz_knsrdbjpy_sq.do");
	   
		
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
			if("10335".equals(Base.xxdm)){
			model.setZd4("�޳��������");
			// �O��ҵ���ֶ�2[ҵ��ID]
			model.setZd6(t.getYlzd3());
			}
		}
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			//����������е����״̬���϶����α��
			KnsrdbjpyForm kdModel = new KnsrdbjpyForm();
			kdModel.setGuid(t.getGuid());
			if(zhzt.equals(Constants.SH_TG)){
				kdModel.setRddc(t.getRddc());
			}
			kdModel.setShzt(zhzt);
			kdModel.setYlzd3(t.getYlzd3());
			result = dao.runUpdate(kdModel);
			// ���һ�����ͨ��ʱ
			if(result && zhzt.equals(Constants.SH_TG)){
				// ������ʽ��
				KnsjgDao knsjgbjpyDao = new KnsjgDao();
				KnsjgForm knsjgbjpyModel = new KnsjgForm();
				KnsrdbjpyForm copyModel = getModel(t);
				copyModel.setRddc(t.getRddc());
				copyModel.setYlzd3(t.getYlzd3());
				BeanUtils.copyProperties(knsjgbjpyModel, copyModel);

				// �Ȱ�ѧ�š�ѧ�ꡢѧ���ж��Ƿ����
				Map<String, String> map = knsjgbjpyDao.getXsknsjg(knsjgbjpyModel
						.getXh(), knsjgbjpyModel.getXn(), knsjgbjpyModel.getXq());
				if (!map.isEmpty()) {
					knsjgbjpyDao.delKnsjg(knsjgbjpyModel.getXn(), knsjgbjpyModel.getXq(), knsjgbjpyModel.getXh());
				}
				
				knsjgbjpyModel.setSjly(SQSH);
				knsjgbjpyModel.setLylcywid(t.getGuid());
				knsjgbjpyDao.runInsert(knsjgbjpyModel);
				
			}
			// �˻� ������ʱ
			if(result && zhzt.equals(Constants.SH_TH) && "-1".equals(t.getThgw())){
				KnsrdbjpyForm kdModelOld = new KnsrdbjpyForm();
				kdModelOld.setGuid(t.getGuid());
				KnsrdbjpyForm kdModelNew = dao.getModel(kdModelOld);
				JgcxDao jgcxDao = new JgcxDao();
				// ���°༶�����
				boolean rs = jgcxDao.cxBjpyxzpy(kdModelNew.getXn(), kdModelNew.getXq(), kdModelNew.getXh());
				if(rs){
					// ���½����ѯ��
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(kdModelNew.getGuid());
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
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
	private void checkRskz(KnsrdbjpyForm t) throws Exception{
		KnsjcszbjpyDao jcszDao=new KnsjcszbjpyDao();
		KnsjcszbjpyForm model=jcszDao.getModel();
		String rskzfw = model.getRskzfw();
		String rskzjb = model.getRskzjb();
		String rskzkg = model.getRssfkz();
		KnsrdbjpyForm knsrdbjpyForm=dao.getModel(t);
		if (t.getXtgwid().equals(rskzjb) && OPEN.equals(rskzkg)){
			//��ȡ����������Ϣ
			HashMap<String, String> rsszMap=jcszDao.getRsszOne(rskzfw,t.getXh(),knsrdbjpyForm);
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
				throw new SystemException(MessageKey.RSKZ_FAIL,tgrs);
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
		KnsrdbjpyForm model = new KnsrdbjpyForm();
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
	public String savePlsh(KnsrdbjpyForm t, User user) throws Exception {

		String[] ids = t.getId();
		String[] gwid = t.getGwid();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			KnsrdbjpyForm model = new KnsrdbjpyForm();
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
	 *             KnsrdbjpyForm ��������
	 * @throws
	 */
	public KnsrdbjpyForm getModelByXnXq(KnsrdbjpyForm model) throws Exception {

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
	public boolean delKnssqFromKnsjgbjpy(String[] values) throws Exception {

		if (values == null || values.length == 0) {
			return false;
		}

		int num = dao.delKnssqFromKnsjgbjpy(values);

		if (num > 0) {
			dao.delKnsshFromKnsjgbjpy(values);
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
	 * 
	 */
	public Map<String, Object> getShqkInfo(User user) {
		// ���������������Ϣ����
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = new KnsjcszbjpyForm();
		try {
			jcszModel = jcszDao.getModel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String xn = jcszModel.getXn();
		String xq = jcszModel.getXq();

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
	public List<HashMap<String, String>> getStudentsList(KnsrdbjpyForm model,
			User user) throws Exception {
		// ���������������Ϣ����
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = jcszDao.getModel();
		String xn = jcszModel.getXn();
		String xq = jcszModel.getXq();

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
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xn,
			String xq) {
		if (StringUtils.isNull(rskzfw)){
			return null;
		}
		return dao.getTgrs(rskzfw,xn,xq);
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
	public boolean getExists(KnsrdbjpyForm model, String user) throws Exception{
		// ���������������Ϣ����
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = jcszDao.getModel();
		String xn = jcszModel.getXn();
		String xq = jcszModel.getXq();
		KnsrdbjpyForm knsrdbjpyModel = new KnsrdbjpyForm();
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			knsrdbjpyModel = dao.getModelOfXn(model, new String[] {
					user, xn});
		}else{
		knsrdbjpyModel = dao.getModel(model, new String[] {
				user, xn, xq });
		}
		
		if(null == knsrdbjpyModel){
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
	public HashMap<String, String> getNewKnsdcbjpy(KnsrdbjpyForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("���ID����Ϊ�գ�");
			throw new NullPointerException();
		}

		return dao.getNewKnsdcbjpy(model);
	}
	
	
	//��˵�������ҳ���÷���
	public List<HashMap<String,String>> getAllListSh(KnsrdbjpyForm t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getShjlList(t, user);
	}
}
