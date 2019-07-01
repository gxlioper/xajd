/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzDao;
import com.zfsoft.xgxt.rcsw.kqgl.zjsy.ZjsyKqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: �������service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DtxxjgService extends SuperServiceImpl<DtxxjgForm, DtxxjgDao> {

	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID = "-1";
	/**
	 * ���������
	 */
	public static String _SJLY_JGK = "0";
	/**
	 * ��������
	 */
	public static String _SJLY_SQ = "1";
	DtxxjgDao dao = new DtxxjgDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public DtxxjgService() {
		this.setDao(dao);
	}

	/**
	 * 
	 * @����:��ø��˽׶���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����10:16:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getGrJdxx(String xh) throws Exception {
		List<HashMap<String, String>> newlist = new ArrayList<HashMap<String, String>>();
		ShlcpzDao sd = new ShlcpzDao();
		List<HashMap<String, String>> list = sd.getGrJdxx(xh);
		// newlist.addAll(list.subList(0, 5));
		// Collections.reverse(newlist);
		// newlist.addAll(list.subList(5,list.size()));
		// Collections.reverse(list);
		return list;
	}

	/**
	 * 
	 * @����:��ȡ���˽׶εĵ��Ž����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����03:29:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param jddm
	 * @return HashMap<String,String> ��������
	 */

	public HashMap<String, String> getGrDtjgxx(String xh, String jddm) {
		return dao.getGrDtjgxx(xh, jddm);
	}

	/**
	 * 
	 * @����:�����������ݵ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-29 ����10:29:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean saveForDtxxsq(DtxxsqForm model) throws Exception {
		DtxxjgForm df = new DtxxjgForm();
		df.setSqid(model.getDtxxsqid());
		df.setSjly(_SJLY_SQ);
		df.setJddm(model.getJddm());
		df.setGrxj(model.getGrxj());
		df.setKssj(model.getSqsj());
		df.setXh(model.getXh());
		df.setZd3(model.getZd3());
		//if("11527".equalsIgnoreCase(Base.xxdm)) {
		df.setZd5(model.getZd5()); // ����
		//}
		df.setJlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		df.setKssj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		HashMap<String, String> xsdtxx =dao.getGrDtjgxx(model.getXh(), model.getJddm());
		if(!xsdtxx.isEmpty()){
			df.setDtxxjgid(df.getXh()+df.getJddm());
			return runUpdate(df);
		}
		else{
			return runInsert(df);
		}
	}

	/**
	 * @throws Exception
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public String save(DtxxjgForm model) throws Exception {
		String jddmStr = model.getJddmstr();
		String kssjStr = model.getKssjstr();
		String grxjStr = model.getGrxjstr();
		String zd5Str = model.getZd5str();
		String dtxxjgidStr = model.getDtxxjgidstr();
		String zd1Str = model.getZd1str();
		String zd2Str = model.getZd2str();
		String zd3Str = model.getZd3str();
		String zd8Str = model.getZd8str();
		String zd9Str = model.getZd9str();
		String zd10Str = model.getZd10str();
		DtxxsqService sqService = new DtxxsqService();
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService
				.getXsjbxxMoreForDtgl(model.getXh());
		int age = sqService.getAge(xsjbxx.get("csrq"));
		if (StringUtils.isNotNull(jddmStr) && StringUtils.isNotNull(kssjStr)
				&& StringUtils.isNotNull(grxjStr)&&StringUtils.isNotNull(zd1Str)
				&&StringUtils.isNotNull(zd2Str)) {
			String[] jddm = jddmStr.split(",");
			String[] kssj = kssjStr.split(",");
			String[] grxj = grxjStr.split(",");
			String[] zd5 = zd5Str.split(",");
			String[] dtxxjgid = dtxxjgidStr.split(",");
			String[] zd1 = zd1Str.split(",");
			String[] zd2 = zd2Str.split(",");
			String[] zd3 = zd3Str.split(",");
			String[] zd8 = zd8Str.split(",");
			String[] zd9 = zd9Str.split(",");
			String[] zd10 = zd10Str.split(",");
			// ѭ������
			for (int i = 0; i < jddm.length; i++) {
				model.setJddm(formatStr(jddm[i]));
				model.setKssj(formatStr(kssj[i]));
				model.setGrxj(formatStr(grxj[i]));
				model.setZd5(formatStr(zd5[i]));
				model.setDtxxjgid(formatStr(dtxxjgid[i]));
				model.setSjly(_SJLY_JGK);
				model.setJlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				model.setZd1(formatStr(zd1[i]));
				model.setZd2(formatStr(zd2[i]));
				model.setZd3(formatStr(zd3[i]));
				model.setZd8(formatStr(zd8[i]));
				model.setZd9(formatStr(zd9[i]));
				model.setZd10(formatStr(zd10[i]));
				String jdmc = sqService.getJdmc(model.getJddm());
				if(StringUtils.equals(jdmc,"�뵳��������") && age<18){
					return "����ʧ�ܣ��뵳�������������������18�꣡";
				}
				if(StringUtils.equals(jdmc,"Ԥ����Ա") && age<19){
					return "����ʧ�ܣ�Ԥ����Ա�����������19�꣡";
				}
				if (!saveOrUpdate(model)) {
					return "����ʧ�ܣ�";
				}
			}
		}
		return "����ɹ���";
	}

	public boolean saveOrUpdate(DtxxjgForm model) throws Exception {
		if (StringUtils.isNull(model.getJddm())) {
			// ����׶δ���Ϊ����ֱ�����������������ӻ��޸Ĳ���
			return true;
			// ���û�п�ʼʱ��Ҳû�н���ʱ��
		} else if (StringUtils.isNull(model.getKssj())
				&& StringUtils.isNull(model.getGrxj())) {
			// ���û����idֵ��ֱ�ӷ��أ��������ɾ��
			if (StringUtils.isNotNull(model.getDtxxjgid())) {
				return runDelete(new String[] { model.getDtxxjgid() }) > 0 ;
			}
			return true;
		}
		// ����סidִ���޸�
		if (StringUtils.isNotNull(model.getDtxxjgid())) {
			return runUpdate(model);
		} else {
			return runInsert(model);
		}
	}

	/**
	 * 
	 * @����:��ʽ���ַ��������ǰ̨���ݣ�-1��Ϊ�����ݣ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����07:37:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @return boolean ��������
	 * @throws
	 */
	private String formatStr(String str) {
		if (!StringUtils.isNotNull(str)) {
			return null;
		} else if (str.equals("-1")) {
			return null;
		}
		return str;
	}

	@Override
	public DtxxjgForm getModel(DtxxjgForm t) throws Exception {
		t = dao.getModel(t);
		return t;
	}

	@Override
	public DtxxjgForm getModel(String keyValue) throws Exception {
		DtxxjgForm t = new DtxxjgForm();
		t.setDtxxjgid(keyValue);
		return getModel(t);
	}

	/**
	 * 
	 * @����:��ȡ�������׶���Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-29 ����10:10:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dtxxjgid
	 * @return
	 * @throws Exception
	 *             String[] ��������
	 */
	public String[] getJgForJdxx(String dtxxjgid) throws Exception {
		List<String> dtxxjgidForJdxx = new ArrayList<String>();
		DtxxjgForm df = getModel(dtxxjgid);
		// ��ȡ�����׶ν����Ϣ
		List<HashMap<String, String>> list = dao.getDtxxjgJdxx(df.getXh());
		for (HashMap<String, String> hm : list) {
			dtxxjgidForJdxx.add(hm.get("xh")+hm.get("jddm"));
		}
		return dtxxjgidForJdxx.toArray(new String[] {});
	}

	/**
	 * @throws Exception
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:31:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] �������� String���� 0Ϊ�ɹ�ɾ������Ϊ����ɾ����
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		String[] nowIds;// ��ǰҪɾ����ids

		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// ��ɾ����id����

		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if (null == ids || ids.length <= 0) {
			return null;
		}
		int unDelXsts = 0;// ����ɾ��ѧ������
		for (String id : ids) {
			// ��ȡ�˽����Ϣѧ��������ؽ׶ν����Ϣ
			nowIds = getJgForJdxx(id);
			for (String str : nowIds) {
				if (!isCanDel(str)) {// ������κ�һ���׶���Ϣ����ɾ�� ������ɾ��
					HashMap<String, String> hm = getAllProperty(str);
					noDel.append(hm.get("xh"));
					noDel.append("&nbsp;");
					noDel.append(hm.get("xm"));
					noDel.append(",</br>");
					isHaveNoId = true;
					unDelXsts++;
					break;
				} else {
					delId.add(str);
				}
			}
		}

		int sjsc = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		int i = ids.length - unDelXsts;
		String str = noDel.toString();
		// ȥ�������ය��
		str = isHaveNoId ? str : _BCZSCID;
		return new String[] { String.valueOf(i), str };
	}

	/**
	 * 
	 * @����:��ȡ���������Ϣ��getModel����չ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-30 ����11:18:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 */
	public HashMap<String, String> getAllProperty(String id) throws Exception {
		HashMap<String, String> property = null;
		DtxxjgForm hm = new DtxxjgForm();
		hm.setDtxxjgid(id);
		List<HashMap<String, String>> list = getAllList(hm);
		if (null != list && list.size() == 1) {// ���������� ֻ��һ������ ��Ϊ��������
			property = list.get(0);
		}
		return property;
	}

	/**
	 * 
	 * @����:�Ƿ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-29 ����09:53:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkvalue
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean isCanDel(String pkvalue) throws Exception {
		DtxxjgForm df = getModel(pkvalue);
		// ���ڶ�Ӧ������Ϣ��Ϊѧ��������������� ����ɾ��
		if (df != null && StringUtils.isNotNull(df.getSqid())) {
			return false;
		}
		return true;
	}

	/**
	 * ��ѯѧ��������Ϣ
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}
	
	public File getExportFile(DtxxjgForm model,User user) throws Exception{
		//����Excle�ļ�
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook book = Workbook.createWorkbook(tempxls);
		WritableSheet sheet = book.createSheet("ѧ����չ����", 0);
		
		//���ñ�����ʽ
		WritableCellFormat title = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		title = ExcelMethods.getWcf(WritableFont.ARIAL,20, true, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.NONE);
		
		//���ñ�ͷ��ʽ
		WritableCellFormat top = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		top = ExcelMethods.getWcf(WritableFont.ARIAL,12, false, Alignment.LEFT, VerticalAlignment.CENTRE, false, Border.NONE);
		
		//����������ʽ
		WritableCellFormat content = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		content = ExcelMethods.getWcf(WritableFont.ARIAL,10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
        
		sheet.mergeCells(0, 0, 15, 0);
		sheet.setColumnView(9, 29);
		addCell(sheet,0,0,title,"�ⷢչ���й�Ԥ����Ա����");
		
		//ƴ���в�����Ϣ
		StringBuilder topString = new StringBuilder();
		topString.append("    ��֧���ƣ����£�:                                                                               ����ˣ�");
		topString.append(user.getRealName());
		topString.append("                                                              ");
		topString.append("���ʱ��:");
		topString.append( DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
		sheet.mergeCells(0, 1, 15, 1);
		addCell(sheet,0,1,top,topString.toString());
		addCell(sheet,0,2,content,"���");
		addCell(sheet,1,2,content,"����");
		addCell(sheet,2,2,content,"�Ա�");
		addCell(sheet,3,2,content,"����");
		addCell(sheet,4,2,content,"���֤��");
		addCell(sheet,5,2,content,"����");
		addCell(sheet,6,2,content,"���ڰ༶(����)");
		addCell(sheet,7,2,content,"����ְ��");
		addCell(sheet,8,2,content,"ÿѧ�ڳɼ�����/�༶������");
		addCell(sheet,9,2,content,"�����");
		addCell(sheet,10,2,content,"�����뵳ʱ��");
		addCell(sheet,11,2,content,"��Ϊ��������ʱ��");
		addCell(sheet,12,2,content,"��ʱ�εزμӵ�У��ѵ");
		addCell(sheet,13,2,content,"��ϵ��");
		addCell(sheet,14,2,content,"�Ƿ��в�����γ�");
		addCell(sheet,15,2,content,"־Ը����");
		int xh = 1;
		int rindex = 3;
		int cindex = 0;
		List<HashMap<String,String>> mzlist = getExportList(model,user);
		for(int i=0;i<mzlist.size();i++){
			addCell(sheet,cindex++,rindex,content,String.valueOf(xh++));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xm"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xb"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("mzmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("sfzh"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("jgmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("bjmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zwmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xqpm"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xmmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("rdsqsj"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("rdjjfssj"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zd8"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zd9"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("isbjg"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zd10"));
			rindex++;
			cindex=0;
		}
		book.write();
		book.close();
		return tempxls;
	}
	
	public void addCell(WritableSheet sheet, int c, int r, WritableCellFormat cellFormat, String str) throws RowsExceededException, WriteException{
		Label label = new Label(c,r,str);
		label.setCellFormat(cellFormat);
		sheet.addCell(label);
	}
	
	/**
	 * 
	 * @����;������Ϣ��������Ϣ�ļ���
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-25 ����10:07:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getExportList(DtxxjgForm model,User user) throws Exception{
		List<HashMap<String,String>> mzlist = dao.getMcExportData(model, user);
		List<HashMap<String,String>> infolist = dao.getMcInfoData(model, user);
		for(int i =0;i<mzlist.size();i++){
			for(int j =0;j<infolist.size();j++){
				if(mzlist.get(i).get("xh").equals(infolist.get(j).get("xh"))){
					mzlist.get(i).put("xmmc", infolist.get(j).get("xmmc"));
					mzlist.get(i).put("isbjg", infolist.get(j).get("isbjg"));
					mzlist.get(i).put("zwmc", infolist.get(j).get("zwmc"));
					mzlist.get(i).put("xqpm", infolist.get(j).get("xqpm"));
				}
			}
		}
		
		return mzlist;
	}
	
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר-Ԥ��չ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-23 ����10:47:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxYsfzList(String xymc) throws Exception{
		return dao.getDtxxYsfzList(xymc);
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר-Ԥ����Ա
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-24 ����01:52:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xymc
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxYbdyList(String xymc) throws Exception{
		return dao.getDtxxYbdyList(xymc);
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר-Ԥ����Աת������ʽ��Ա��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-24 ����03:37:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xymc
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxZsdyList(String xymc) throws Exception{
		return dao.getDtxxZsdyList(xymc);		
	}
	
	/**
	 * @�������������ո��Ի�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��6�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getExportList_13871(DtxxjgForm t, User user)throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportList_13871(t, user);
	}

	/**
	* @description: TODO ��������-�������ӵ���
 	* @param model
	* @param user
	* @return java.io.File
	* @author Wang ChenHui
	* @date 2019/5/24 13:03
	*/
	public List<HashMap<String,String>> getJjfzList(DtxxjgForm model, User user) throws Exception {
		return dao.getDtxxJjfzList(model,user);
	}

	/**
	* @description: TODO Ԥ����Ա���յǼ�
	* @param model
	* @param user
	* @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	* @author Wang ChenHui
	* @date 2019/5/24 15:09
	*/
	public List<HashMap<String,String>> getFzdxList(DtxxjgForm model, User user) throws Exception{
		return dao.getDtxxFzdxList(model,user);
	}

	/**
	* @description: TODO Ԥ����Ա���յǼ�
	* @param model
	* @param user
	* @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	* @author Wang ChenHui
	* @date 2019/5/24 15:09
	*/
	public List<HashMap<String,String>> getYbdyList(DtxxjgForm model, User user) throws Exception{
		return dao.getYbdyList(model,user);
	}

	/**
	* @description: TODO Ԥ����Աת����ʾ�б�
	* @param model
	* @param user
	* @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	* @author Wang ChenHui
	* @date 2019/5/24 15:02
	*/
	public List<HashMap<String,String>> getZsdyList(DtxxjgForm model, User user) throws Exception{
		return dao.getZsdyList(model,user);
	}
}
