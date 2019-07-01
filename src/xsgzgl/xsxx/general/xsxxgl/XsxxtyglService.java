package xsgzgl.xsxx.general.xsxxgl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgForm;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import sun.misc.BASE64Encoder;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.szbb.SzbbService;
import xsgzgl.xsxx.general.xgzdpz.XgzdpzForm;
import xsgzgl.xsxx.general.xgzdpz.XgzdpzService;

/**
 * ѧ����Ϣ - ͨ�ù���servLiTTice
 * lt
 * 2013-1-7
 */
public class XsxxtyglService extends CommService {
	
	XsxxtyglDao dao = new XsxxtyglDao();
	
	private String TH_WIDTH = "20%";//th ��ȱ���
	private String TD_WIDTH = "30%";//td ��ȱ���
	private String WKXZ = "����Ϊ��";//�ɷ�Ϊ��
	private String FOU = "��";       //��
	private String SPLIT = "!!@@!!"; //�ָ���
	private String BQ_SRK = "�����";//�����
	private String BQ_XLK = "������";//������
	private String BQ_TSGS = "�����ʽ";//�����ʽ
	private String BQ_WIDTH = "200px";//Ĭ�ϱ�ǩ���
	private String FGF1 = "LiTT";
	private String FGF2 = "LiTT!!LiTT";
	
	
	/**
	 * ���ѧ����Ϣ��ͷ
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public List<HashMap<String, String>> getTopList(HashMap<String, String> map) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = getCxjgzdpzList(map);
		String[] cnArray = null;
		String[] enArray = null;
		if (rs != null && !rs.isEmpty()) {//�����ñ�ȡ��ͷ
			enArray = new String[rs.size()+1];
			cnArray = new String[rs.size()+1];
			enArray[0] = "pk";
			cnArray[0] = "";
			for (int i=0;i<rs.size();i++) {
				enArray[i+1] = rs.get(i) != null ? rs.get(i).get("zd") : "δ֪��";
				cnArray[i+1] = rs.get(i) != null ? rs.get(i).get("zdmc") : "δ֪��";
			}
		} else {//Ϊ����ϵͳĬ�ϱ�ͷ
			if ("zxs".equalsIgnoreCase(map.get("cd"))) {//��У����ͷ
				cnArray = new String[]{"", "ѧ��", "����", "�Ա�", "�꼶","ѧԺ", "רҵ", "�༶"};
				enArray = new String[]{"pk", "xh", "xm", "xb", "nj","xymc", "zymc", "bjmc"};
			} else {//����У����ͷ 
				cnArray = new String[]{"", "ѧ��", "����", "�Ա�", "�꼶","ѧԺ", "רҵ", "�༶"};
				enArray = new String[]{"pk", "xh", "xm", "xb", "nj","xymc", "zymc", "bjmc"};
			}
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(enArray, cnArray);
		return topTr;
	}
	/**
	 * 
	 * @����:�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-18 ����01:53:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yhdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYhmc(String yhdm){
		String yhmc = null;
		XsxxglService xs=new XsxxglService();
		List<HashMap<String, String>> list=xs.getYhList();
		if(null==list||list.size()<=0){
			yhmc="";
		}
		for(HashMap<String, String> hm:list){
			if(hm.get("yhdm").equals(yhdm)){
				yhmc= hm.get("yhmc");
				break;
			}
		}
		return yhmc;
	}
	/**
	 * �����У�������
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public ArrayList<String[]> getZxsxxList(XsxxtyglForm myForm,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getZxsxxList(myForm, user);
	}
	
	/**
	 * ��ȡѧ����Ϣ��ѯ��������ֶ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getCxjgzdpzList(HashMap<String, String> map) {
		return dao.getCxjgzdpzList(map);
	}
	
	/**
	 * ��ѯ��ʾ���б�
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getXsqlbList(XsxxtyglForm myForm) {
		return dao.getXsqlbList(myForm);
	}
	
	/**
	 * ��ȡ�ֶ������б�
	 * @return
	 */
	public List<HashMap<String, String>> getZdpzList() {
		return dao.getZdpzList();
	}
	
	/**
	 * ����ѧ����Ϣ����ҳ��
	 * @return
	 */
	public List<HashMap<String, Object>> loadXsxxzjPage(List<HashMap<String, String>> xsqList, 
			List<HashMap<String, String>> zdpzList, XsxxtyglForm myForm) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		StringBuffer pagehtml = new StringBuffer();

		//ƴ��ҳ��HTML��ʼ 
		pagehtml.append("<table width='100%' border='0' class='formlist'>");
		
		if (xsqList != null && !xsqList.isEmpty()) {
			for (HashMap<String , String> map : xsqList) {
				//ƴ��thead
				String xsqdm = map.get("xsqdm");
				pagehtml.append("<thead><tr><th colspan='4' ><span>");
				pagehtml.append("onclick=displayTbody('tbody_"+ xsqdm +"')");
				pagehtml.append(map.get("xsqmc"));
				pagehtml.append("</span></th></tr></thead>");
				
				//ƴ��tbody
				pagehtml.append("<tbody id='"+ xsqdm +"' ");
				pagehtml.append("style='display:");
				pagehtml.append(FOU.equalsIgnoreCase(map.get("sfzk")) ? "none" : "");//�Ƿ�չ��ҳǩ�Ĵ���
				pagehtml.append("'>");
				
				//ƴ��tr
				pagehtml.append("<tr ><th align='right' width='"+ TH_WIDTH +"'>");
				for (HashMap<String, String> zdmap : zdpzList) {
					String[] yzgsArray = zdmap.get("lrxz") != null ? zdmap.get("lrxz").split(SPLIT) : null;
					String width = yzgsArray != null && yzgsArray.length > 1 ? yzgsArray[1] : BQ_WIDTH;//��ǩ��� Ĭ��Ϊ200px
					String zd = zdmap.get("zd");
					
					pagehtml.append("<font color='red'>");
					pagehtml.append(WKXZ.equalsIgnoreCase(zdmap.get("wkxz")) ? "" : "*");
					pagehtml.append(zdmap.get("zdmc"));
					pagehtml.append("</font></th><td align='left' width='"+ TD_WIDTH +"'>");
					if (BQ_SRK.equalsIgnoreCase(zdmap.get("lrxs"))) {//�����Ĵ���
						pagehtml.append("<html:text styleId='"+ zd +"' property='"+ zd +"' style='width:"+ width +"'></html:text>");
					} else if (BQ_XLK.equalsIgnoreCase(zdmap.get(""))) {//������Ĵ���
						pagehtml.append("<html:select styleId='"+ zd +"' property='"+ zd +"' style='width:"+ 
								width +"'><html:option value=''></html:option><html:options collection=''/></html:select>");
					} else if (BQ_TSGS.equalsIgnoreCase(zdmap.get(""))) {//�����ʽ�Ĵ���
						
					}
					pagehtml.append("");
				}
			}
		}
		return rs;
	}

	
	/**
	 * ͨ��������ȡ��������ֶ�
	 * @param tableName
	 * @return
	 */
	public String[] getColumnByTable(String tableName) {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = dao
				.getList(
						"select column_name from user_col_comments where table_name=upper(?)",
						new String[] { tableName },
						new String[] { "column_name" });
		String[] array = new String[rs.size()];
		for (int i=0;i<rs.size();i++) {
			array[i] = rs.get(i).get("column_name");
		}
		return array;
	} 
	
	/**
	 * ����ѧ��������Ϣ
	 * @param valueMap
	 * @return
	 */
	public boolean saveInfo(HashMap<String, String> valueMap){
		return dao.saveInfo(valueMap);
	}
	
	/**
	 * ����ѧ�����룬��Ƭ������Ա��Ϣ
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsqtxx(XsxxtyglForm myForm, User user) throws Exception{
		return dao.saveXsqtxx(myForm, user);
	}
	
	/**
	 * ͨ���޸ķ�������д��־��
	 * @param BasicModel model
	 * model ������� valueMap��user����Ϣ
	 * user ��������д��־
	 * tableName ָ���޸ĵı���
	 * pk �����ֶ�
	 * valueMap ����ֵ���޸��ֶ���Ϣ
	 * @return
	 * @throws Exception 
	 */
	public boolean updateInfo(XsxxtyglForm myForm, String tableName){
		myForm.setPk("xh");
		return dao.updateInfo(myForm, tableName);
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯ��У����Ϣ����Ҫ�滻�޸�ʱ�����Ϣ����ѧ������ʱ����ʹ�ã�
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getZxsxxByXh(XsxxtyglForm myForm) {
		String userType = myForm.getUser().getUserType();
		HashMap<String, String> map = dao.getZxsxxByXh(myForm);//xs
		if ("stu".equalsIgnoreCase(userType)) {
			HashMap<String, String> xgMap = dao.getZxsxxByStu(getSql(), myForm.getXh());//xg
			
			//�ж���Щ�ֶ��޸Ĺ�
			HashMap<String, String> xgAllMap = getXgAllMap(myForm.getXh());
			List<String> xggzdArray = new ArrayList<String>(); 
			if (xgAllMap != null) {
				for (Map.Entry<String, String>entry:xgAllMap.entrySet()) {
					String paramName = entry.getKey();
					String paramValue= entry.getValue();
					if (StringUtils.isNotNull(paramValue) && paramValue.contains("LiTT"))  {
						xggzdArray.add(paramName);
					}
				}
			}
			//���޸Ĺ����Ӷ��滻
			if (xgMap != null && !xgMap.isEmpty()) {
				for (Map.Entry<String, String>entry:xgMap.entrySet()) {
					String paramName = entry.getKey();
					String paramValue= entry.getValue();
					for (String s : xggzdArray) {
						if (s.equalsIgnoreCase(paramName)) {
							map.put(paramName, paramValue);	
						}
					}
				}
			}
				
		}
		return map;
	}

	/**
	 * ͨ��ѧ�Ų�ѯ��У����Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getZxsxxByXhCk(XsxxtyglForm myForm) {
		HashMap<String, String> map = dao.getZxsxxByXh(myForm);
	
		return map;
	}
	
	public String getSql() {
		StringBuffer sql = new StringBuffer("select a.*,(case when jg is not null then " +
				"(substr(jg,0,2))||'0000' else '' end) jgshen,(case when substr(jg,3,2)<>'00' " +
				"then (substr(jg,0,4))||'00' else '' end ) jgshi,(case when substr(jg,5,2)<>'00' " +
				"then jg else '' end) jgxian,(case when substr(syd,3,2)<>'00' then substr(syd,0,4)||'00' else '' end) sydshi," +
				"(case when substr(syd,5,2)<>'00' then syd else '' end) sydx," +
				"(case when syd is not null then substr(syd,0,2)||'00' else '' end) sydshen," +
				"(case when a.hkszd is not null then (substr(a.hkszd,0,2))||'0000' else '' end) hkshen," +
				"(case when substr(a.hkszd,3,2)<>'00' then (substr(a.hkszd,0,4))||'00' else '' end) hkshi," +
				"(case when substr(a.hkszd,5,2)<>'00' then hkszd else '' end) hkxian from (select ");
	// ȥ��bjdm,xydm,nj,zydm
		String[] zd = new String[] {  "bz", "xm","jtszd",
				"xmpy",  "syd", "csrq", "sfzh", "xb", "mz", "zzmm",
				"lxdh", "dzyx", "cym", "sg", "tz", "tc", "kslb", "rxfs",
				"pyfs", "pycc", "xjlb", "xszp", "xjztm", "xz", "whcd", "rxqdw",
				"jtdh", "jrgqtsj", "jrgcdsj", "jtcygc", "jlcfjl", "jkzk",
				"jtdz", "jtyb", "jg", "xx", "ah", "sfdk", "shgxxm1", "shgxgx1",
				"shgxgzdw1", "shgxzw1", "shgxdwdh1", "shgxsjhm1", "shgxxm2",
				"shgxgx2", "shgxgzdw2", "shgxzw2", "shgxdwdh2", "shgxsjhm2",
				"jtqkjj", "jtjjqk", "sjhm", "byxx", "kh", "rxrq", "fdyxm",
				"gkcj", "qqhm", "hkxz", "zyjb", "hkszd", "ssyq", "ssld",
				"jtdzs", "jtdzx", "sfzsb", "sfzfx", "zjdm", "sfyby", "byny",
				"sfzx", "zw", "thbs", "dybj", "shbj", "xwzsxlh", "xwzsbh",
				"xw", "xzxm", "zsxlh", "zsbh", "bjyjl", "csd", "zsjj", "xxxs",
				"bxlx", "bxxs", "fxzyfx", "fxzy", "zylb", "dqszj", "pyfx",
				"zyfx", "xxszd", "ksh", "xxfx", "zslb", "gj", "sfjh", "ccqj",
				"byzffztdm", "xwzsxxdz", "jgs", "jgshi", "jgx", "ssbh", "rxnj",
				"nfby", "sfzc", "dasfyl", "daylyy", "yxdm", "sfzz", "sfsf",
				"sfdl", "dxhwp", "bysj", "zxwyyzdm", "wydj", "jsjdj", "lxdz",
				"yzbm", "shzw", "jypx", "xmsj", "zgzs", "jljn", "sybz1",
				"sybz2", "sybz3", "xldm", "zkzh", "grjl", "sfcj", "ssch",
				"rzrq", "zsjzrq", "qsdh", "ykth", "yhkh", "xslb", "xslx",
				"sfbys", "yhdm", "hkshen", "hkshi", "hkxian", "zcsxhm",
				"rxqwhcd", "xsqrxxbz", "dah", "ylbxh", "rxqdwdz", "rxqdwyb",
				"rxqdwdh", "gzbx", "sfgat", "sfssmz", "sfzd", "syds", "sydshi",
				"sydx", "byzh", "xjh", "jrzzmmrq", "sfhq", "csds", "csdshi",
				"csdxian", "lxdh1", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz",
				"jtcy1_lxdh1", "jtcy1_lxdh2", "jtcy2_xm", "jtcy2_gx",
				"jtcy2_gzdz", "jtcy2_lxdh1", "jtcy2_lxdh2", "jtcy3_xm",
				"jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh1", "jtcy3_lxdh2", "zd1",
				"zd2", "zd3", "zd4", "zd5" };
		for (String s : zd) {
			sql.append("substr(a."+s+", instr(a."+s+", 'LiTT') + 4, length(a."+s+")) " + s);
			sql.append(",");
		}
		String rs = sql.toString().substring(0, sql.toString().length() - 1);
		sql = new StringBuffer(rs);
		sql.append(" from xg_xsxx_xxxgzdxgb a where " +
				"sqid=(select sqid from xg_xsxx_xxxgsqb where xh=? and shjg<>'tg'" +
				" and rownum<2)) a");
		//System.out.println(sql.toString());
		return sql.toString();
	}
	
	public HashMap<String, String> getXgAllMap(String xh) {
		DAO mydao = DAO.getInstance();
		return mydao.getMapNotOut("select * from xg_xsxx_xxxgzdxgb a where " +
				"sqid=(select sqid from xg_xsxx_xxxgsqb where xh=? and shjg<>'tg'" +
				" and rownum<2)", new String[]{xh}); 
	}
	
	/**
	 * ɾ����У����Ϣ
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public Boolean deleteZxsxx(XsxxtyglForm myForm, User user) {
		boolean flag = false;
		String[] pkValue = myForm.getPkValue();
		try {
			flag = dao.deleteZxsxx(pkValue, user);
			if (flag) {
				dao.deleteXsfzxx(pkValue, user);
				dao.deleteXsmmxx(pkValue, user);
				dao.deleteXszpxx(pkValue, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ��ȡ�鿴ҳ���й���ģ���б���Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> getCkxsgnmkxx() {
		return dao.getCkxsgnmkxx();
	}

	/**
	 * ���һ������ģ�����ģ��
	 * @param list
	 * @return
	 */
	public List<HashMap<String, Object>> getCkywlbList(List<HashMap<String, String>> list) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (HashMap<String, String> map : list) {
				if (map != null && map.get("zmk") != null && map.get("zmk").split("!!@@!!").length > 0) {
					HashMap<String, Object> obj = new HashMap<String, Object>();
					obj.put("dm", map.get("gnmk"));
					obj.put("xsmc", map.get("zmk").split("!!@@!!"));
					rs.add(obj);
				}
			}
		}
		return rs;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯ�Ƿ����
	 * @param xh
	 * @return
	 */
	public String chkStuIsExists(String xh) {
		return dao.chkStuIsExists(xh);
	}
	
	/**
	 * ��ȡ�ֶ�ͬ�����ñ�
	 * @return
	 */
	public List<HashMap<String, String>> getXsxxTbzdpzb() {
		return dao.getXsxxTbzdpzb();
	}

	/**
	 * ��ȡ�ֶ�ͬ������
	 * @return
	 */
	public HashMap<String, String> getXsxxTbzdpzbStr(String type) {
		XgzdpzService xgservice = new XgzdpzService();
		XgzdpzForm model = new XgzdpzForm();
		model.setLb(type);
		List<HashMap<String, String>> list = xgservice.getXgzdList(model);
		StringBuffer sb = new StringBuffer();
		String xgzt = "0";//��ʶ�޸�״̬
		if (list != null && !list.isEmpty()) {
			for (HashMap<String, String> map : list) {
				if (StringUtils.isNotNull(map.get("sfzd")) || StringUtils.isNotNull(map.get("sfbt"))) {
					xgzt = "1";
				}
				if (!"xh".equalsIgnoreCase(map.get("zd"))) {//ѧ�Ų����޸�
					sb.append(map.get("zd"));
					sb.append("!!@@!!");
					sb.append(map.get("sfzd"));
					sb.append("!!@@!!");
					sb.append(map.get("sfbt"));
					sb.append("LiTTLiTT");
				} 
			}
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("xgzt", xgzt);
		rs.put("zdstr", sb.toString());
		return rs;
	}
	
	/**
	 * ��ѯ����У����Ϣ�б�
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFzxsxxList(XsxxtyglForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFzxsxxList(myForm, user);
	}

	public boolean cshYhmm(XsxxtyglForm myForm) throws Exception{
		return dao.cshYhmm(myForm);
	}
	
	/**
	 * ��ȡ�ֶ������б�
	 * @return
	 */
	public List<HashMap<String, String>> getZdszList() {
		List<HashMap<String, String>> list = dao.getZdszList();
		return list;
	}
	
	/**
	 * ��������ֵ
	 * @return
	 */
	public HashMap<String, String> getCsszjg() {
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut("select * from xg_xsxx_xxxgcsszb", new String[]{});
	}
	
	/**
	 * �˻�������
	 * @return
	 */
	public HashMap<String, String> getXsthsq(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut("select * from xg_xsxx_xxxgsqb where xh = ? and shjg = 'th'", new String[]{xh});
	}
	
	/**
	 * ��ȡѧ���޸��ֶ�����Ӧ��ֵ
	 * @param list
	 * @return
	 */
	public String getStuZdszValue(HashMap<String, String> xsxxMap) {
		StringBuffer str = new StringBuffer("");
		List<HashMap<String, String>> list = getZdszList();
		if (list != null) {
			for (HashMap<String, String> map : list) {
				if ("stu".equalsIgnoreCase(map.get("lb")) && ("n".equalsIgnoreCase(map.get("sfzd")) || StringUtils.isNull(map.get("sfzd")))) {
					str.append(map.get("zd"));
					str.append(FGF1);
					str.append(xsxxMap.get(map.get("zd")));
					str.append(FGF2);
				}
			}
		}
		String jg = xsxxMap.get("jg");
		String hkszd = xsxxMap.get("hkszd");
		String syd = xsxxMap.get("syd");
		
		if (!StringUtils.isNull(jg) && jg.length() == 6){
			if ("0000".equalsIgnoreCase(jg.substring(2, 6))) {//ʡ
				str.append("jgshen");
				str.append(FGF1);
				str.append(jg);
				str.append(FGF2);
				str.append("jgshi" + FGF1+ FGF2);
				str.append("jgxian" + FGF1+ FGF2);
			} else if ("00".equalsIgnoreCase(jg.substring(4, 6))) {//��
				str.append("jgshen");
				str.append(FGF1);
				str.append(jg.substring(0, 2)+"0000");
				str.append(FGF2);
				str.append("jgshi" +FGF1 +jg.substring(0,4)+"00"+ FGF2);
				str.append("jgxian" + FGF1+ FGF2);
			} else if (StringUtils.isNotNull(jg) && !"00".equalsIgnoreCase(jg.substring(4, 6))) {
				str.append("jgshen");
				str.append(FGF1);
				str.append(jg.substring(0, 2)+"0000");
				str.append(FGF2);
				str.append("jgshi" +FGF1 +jg.substring(0,4)+"00"+ FGF2);
				str.append("jgxian" + FGF1+jg+ FGF2);
			} else {
				str.append("jgshen");
				str.append(FGF1);
				str.append(FGF2);
				str.append("jgshi" + FGF1+ FGF2);
				str.append("jgxian" + FGF1+ FGF2);
			}	
		}
		
		
		if (!StringUtils.isNull(hkszd) && hkszd.length() == 6){
			if ("0000".equalsIgnoreCase(hkszd.substring(2, 6))) {//ʡ
				str.append("hkshen");
				str.append(FGF1);
				str.append(hkszd);
				str.append(FGF2);
				str.append("hkshi" + FGF1+ FGF2);
				str.append("hkxian" + FGF1+ FGF2);
			} else if ("00".equalsIgnoreCase(hkszd.substring(4, 6))) {//��
				str.append("hkshen");
				str.append(FGF1);
				str.append(hkszd.substring(0, 2)+"0000");
				str.append(FGF2);
				str.append("hkshi" +FGF1 +hkszd.substring(0,4)+"00"+ FGF2);
				str.append("hkxian" + FGF1+ FGF2);
			} else if (StringUtils.isNotNull(hkszd) && !"00".equalsIgnoreCase(hkszd.substring(4, 6))) {
				str.append("hkshen");
				str.append(FGF1);
				str.append(hkszd.substring(0, 2)+"0000");
				str.append(FGF2);
				str.append("hkshi" +FGF1 +hkszd.substring(0,4)+"00"+ FGF2);
				str.append("hkxian" + FGF1+hkszd+ FGF2);
			} else {
				str.append("hkshen");
				str.append(FGF1);
				str.append(FGF2);
				str.append("hkshi" + FGF1+ FGF2);
				str.append("hkxian" + FGF1+ FGF2);
			}
		}
		
		if (!StringUtils.isNull(syd) && syd.length() == 6){
			if ("0000".equalsIgnoreCase(syd.substring(2, 6))) {//ʡ
				str.append("syshen");
				str.append(FGF1);
				str.append(syd);
				str.append(FGF2);
				str.append("syshi" + FGF1+ FGF2);
				str.append("syxian" + FGF1+ FGF2);
			} else if ("00".equalsIgnoreCase(syd.substring(4, 6))) {//��
				str.append("syshen");
				str.append(FGF1);
				str.append(syd.substring(0, 2)+"0000");
				str.append(FGF2);
				str.append("syshi" +FGF1 +syd.substring(0,4)+"00"+ FGF2);
				str.append("syxian" + FGF1+ FGF2);
			} else if (StringUtils.isNotNull(syd) && !"00".equalsIgnoreCase(syd.substring(4, 6))) {
				str.append("syshen");
				str.append(FGF1);
				str.append(syd.substring(0, 2)+"0000");
				str.append(FGF2);
				str.append("syshi" +FGF1 +syd.substring(0,4)+"00"+ FGF2);
				str.append("syxian" + FGF1+syd+ FGF2);
			} else {
				str.append("syshen");
				str.append(FGF1);
				str.append(FGF2);
				str.append("syshi" + FGF1+ FGF2);
				str.append("syxian" + FGF1+ FGF2);
			}	
		}
		return str.toString();
	}
	
	/**
	 * ��ȡѧ������еļ�¼��
	 * @param xh
	 * @return
	 */
	public String getStuSqzt(String xh) {
		return dao.getStuSqzt(xh);
	}
	
	/**
	 * ��ȡѧ��������Ϣ�Ƿ����
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuSqztMap(String xh) {
		return dao.getStuSqztMap(xh);
	}
	
	/**
	 * �޸�ѧ������ ���޸���Ϣ
	 * @param valueMap
	 * @param xsxxMap
	 * @return
	 */
	public boolean updateStusqxx(String sqid, HashMap<String, String> valueMap, HashMap<String, String> xsxxMap, HashMap<String, String> sqxxMap,String sfxgFlag) throws Exception{
		boolean result = false;
		DAO mydao = DAO.getInstance();
		//�޸������ʱ�䣬����״̬Ϊwsh
		String sqlArr = "update xg_xsxx_xxxgsqb set xgsj =(select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual),shjg='wsh' where sqid=?";
		result = mydao.runUpdate(sqlArr, new String[]{sqid});
		//�˻�ʱ���޸ĵ�һ�����״̬Ϊxcs
		if (result&&"th".equals(sqxxMap.get("shjg"))) {
			valueMap.put("sqid", sqid);
			 HashMap<String, String> spgwMap = dao.getMinSpgw();
			sqlArr = "update xg_xsxx_xxxgshb set shzt='xcs' where sqid=? and gwid=?";
			result = mydao.runUpdate(sqlArr, new String[]{sqid,spgwMap.get("spgw")});
		}
		if (result&&"true".equals(sfxgFlag)) {
			valueMap.put("sqid", sqid);
			result = dao.updateInfo(valueMap, xsxxMap);
		}
		return result;
	}
	
	/**
	 * ����ѧ������ ���޸���Ϣ
	 * @param valueMap
	 * @param xsxxMap
	 * @return
	 */
	public String insertStusqxx(HashMap<String, String> valueMap, HashMap<String, String> xsxxMap, String splc,String userType) throws Exception{
		boolean result = false;
		DAO mydao = DAO.getInstance();
		String guid = getGuid();
		String[] sqlArr = new String[3];
		sqlArr[0] = "insert into xg_xsxx_xxxgsqb(sqid,xh) values ('" + guid
				+ "','" + valueMap.get("xh") + "')";
		sqlArr[1] = "insert into xg_xsxx_xxxgshb (sqid,gwid) select '" + guid
				+ "',spgw from xg_xtwh_spbz where splc='" + splc + "'";// ��˱�û�б��氡
		sqlArr[2] = "insert into xsfzxxb (xh) select xh from xsxxb a where xh='"+valueMap.get("xh")+"'" +
				" and not exists (select 1 from xsfzxxb b where b.xh='"+valueMap.get("xh")+"' and a.xh=b.xh)";
		valueMap.put("sqid", guid);
		result = mydao.checkBatch(mydao.runBatch(sqlArr));
		if (result) {
			result = dao.saveInfo(valueMap, xsxxMap,userType);
		}
		if(result){
			return guid;
		}else{
			return "";
		}
	}
	
	/**
	 * ��ȡ���ݿ��е�GUID
	 * @return
	 */
	public String getGuid() {
		return dao.getGuid();
	}
	
	public static void main(String...strings) {
		System.out.println("112345".substring(2, 6));
	}
	
	/**
	 * ��ȡѧ���������
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXgshxx(User user) {
		List<HashMap<String, String>> xgshxxList= new ArrayList<HashMap<String, String>>();
		//ѧ���û�����Ϣ����
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			HashMap<String, String> csszMap = getCsszjg();
			//��������ı��洦��
			if (StringUtils.isNotNull(csszMap.get("shlid")) && !"wxsh".equalsIgnoreCase(csszMap.get("shlid"))) {
				HashMap<String, String> sqxxMap = getStuSqztMap(user.getUserName()); 
				//��������������û���ͨ�����Ҳ��������״̬�У�
				if (StringUtils.isNotNull(sqxxMap.get("sqid"))) {
				//δ��˻���ͨ��ʱ����Ҫ��ѯ�����Ϣ
				if("th".equals(sqxxMap.get("shjg"))){
					xgshxxList = dao.getXgshxx(sqxxMap.get("sqid"));
				}
				}
			} 
		}
		return xgshxxList;
	}
	
	/**
	 * 
	 * ��ȡ��ͼ���������λ
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getMinSpgw()
			throws Exception {
		return dao.getMinSpgw();
	}
	
	/**
	 * ����html
	 * 
	 * @author xucy
	 * @throws 
	 */
	public String createHTML(SearchRsModel rsModel,
			List<HashMap<String, String>> topTr, ArrayList<String[]> rsArrList) {

		// IE�汾
		String ie = rsModel.getIe();
		StringBuilder html = new StringBuilder();
		if(topTr!=null&&topTr.size()>0){
				//ѧ�żӳ�������
					if (rsArrList != null && rsArrList.size() > 0) {
						for(int j = 0; j < rsArrList.size(); j++){
							String[] rs = rsArrList.get(j);
							html.append("<tr >");
							html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

							html.append("<input type='checkbox' name='primarykey_checkVal' ");
							html.append(" id='pkValue_" + j + "' ");
							html.append(" value='" + rs[0] + "'/> ");
							html.append("</td>");
							for (int k = 1;k < rs.length; k++) {
								html.append("<td align=\"left\" nowrap=\"nowrap\" ");
								// IE8
								if ("5.8".equalsIgnoreCase(ie)) {
									html.append("height=\"28.5px\"");
								} else {
									html.append("height=\"29px\"");
								}
								if("xh".equals(topTr.get(k).get("en"))){
									html.append(" class=\"name\" style=\"cursor:hand\"   href=\"#\" onclick=\"zxsxxView('"+rs[k]+"');return false\"   ");
								}
								html.append(">");
								html.append(rs[k]);
								html.append("</td>");
							}
							html.append("</tr>");
					}
					}
		}
	

		return html.toString();
	}

	public HashMap<String,String> getWdgz(String id){
		return dao.getWdgz(id);
	}
	
	public List<HashMap<String, String>> getFdyBzrListByBjdm(String bjdm) {
		SzbbService service = new SzbbService();
		return service.getFdyBzrListByBjdm(bjdm);
	}
	
	/**
	 * 
	 * @����:��ȡ˼����Ϣ�б�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-9 ����04:13:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param szlx
	 * @param list
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSzxxList(String szlx,List<HashMap<String, String>> list) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (list != null && list.size() > 0) {
			for (HashMap<String, String> map : list) {
				if (szlx.equalsIgnoreCase(map.get("lx"))) {
					rs.add(map);
				}
			}
		}
		return rs;
	}

	/** 
	 * @����:(��ȡѧ���ɲ���Ϣlist)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-17 ����02:34:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param string
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGbxxList(String xh) {
		return dao.getGbxxList(xh);
	}
	
	/**
	 * ��У����Ϣ�Զ��嵼��
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxsxxExportDataList(XsxxtyglForm t, User user) throws Exception {
		
		return dao.getZxsxxExportDataList(t,user);
	}
	
	/**
	 * ����У����Ϣ�Զ��嵼��
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFzxsxxExportDataList(XsxxtyglForm t, User user) throws Exception {
		
		return dao.getFzxsxxExportDataList(t,user);
	}
	public List<HashMap<String, String>> getTyExportDataList(XsxxtyglForm t, User user) throws Exception {
		
		return dao.getTyExportDataList(t,user);
	}
	public List<HashMap<String, String>> getTgbExportDataList(XsxxtyglForm t, User user) throws Exception {
		
		return dao.getTgbExportDataList(t,user);
	}
	/** 
	 * @����:(��ȡ����������Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-29 ����02:26:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getCxpyxxByXh(XsxxtyglForm model) {
		List<HashMap<String, String>> cxpyList=dao.getCxpyxxByXh(model);
		HashMap<String, String> map=new HashMap<String, String>();
		for(int i=0;i<cxpyList.size();i++){
			HashMap<String, String> cxpy=cxpyList.get(i);
			map.put("py"+i+"01", cxpy.get("cxpy01"));
			map.put("py"+i+"02", cxpy.get("cxpy02"));
			map.put("dj"+i+"01", cxpy.get("cxdjmc01"));
			map.put("dj"+i+"02", cxpy.get("cxdjmc02"));
			map.put("bzr"+i+"01", cxpy.get("bzr01"));
			map.put("bzr"+i+"02", cxpy.get("bzr02"));
		}
		return map;
	}
	
	public String checkxszpSfcz(String xh){
		String  zp =  dao.checkxszpSfcz(xh);
		return "0".equalsIgnoreCase(zp)?"false":"true";
	}

	/**
	 * @param kcList 
	 * @param xnList  
	 * @����:(��ȡѧ���ɼ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����10:28:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCjList(XsxxtyglForm myForm, List<String> xnList, List<String> kcList) {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for (String kcmc : kcList) {
			HashMap<String, String> map=dao.getCjMap(myForm,xnList,kcmc);
			list.add(map);
		}
		return list;
	}

	/**
	 * @throws Exception  
	 * @����:ѧ���γ�list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����10:33:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getKcList(XsxxtyglForm myForm) throws Exception {
		return dao.getKcList(myForm);
	}

	/** 
	 * @����:(ѧ��ѧ��list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����10:38:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getXnList(XsxxtyglForm myForm) throws Exception{
		return dao.getXnList(myForm);
	}
	/**
	 * �յ�list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}

	/** 
	 * @����:(��ȡѧ����Ƭ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-3 ����02:49:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getZpMap(XsxxtyglForm myForm,HttpServletRequest request,String column) throws Exception{
		Blob blob= dao.getZpMap(myForm,column);
		BufferedInputStream bin = null;
		byte[] bytes = null;
		InputStream in = null;
		if(blob==null){
			ByteArrayOutputStream byStream = new ByteArrayOutputStream();
			int ch=0;
			ServletContext application = request.getSession().getServletContext();
			in =  new FileInputStream(new File(application.getRealPath("/images/type_pic.gif"))); 
			while((ch=in.read())!=-1){
				byStream.write(ch);
			}
			bytes = byStream.toByteArray();
			byStream.close();
		}else{
			try {
				bin = new BufferedInputStream(blob.getBinaryStream());
				bytes = new byte[(int)blob.length()];
				int len = bytes.length;
				int offset = 0;
				int read = 0;
				while(offset<len&&(read=bin.read(bytes,offset,len-offset))>0){
					offset+=read;
				}
			} catch(IOException e){
				e.printStackTrace();
			} finally{
				if (bin != null){
					bin.close();
				}
			}
		}
			BASE64Encoder encoder = new BASE64Encoder();
			String zp=bytes != null ? encoder.encode(bytes) : null;
			HashMap<String, String> map=new HashMap<String, String>();
			map.put(column, zp);
		return map;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ��б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getCjList(String xh) {
		return dao.getCjList(xh);
	}
	
	/**
	 * �°�ѧ����Ϣ�ļ�ͥ��Ա����
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getJtcyxxXsList(String xh)
	throws Exception {
		return dao.getJtcyxxXsList(xh);
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ��Ƿ�����
	 * @param xh
	 * @return
	 */
	public String getQfqk(String xh) {
		return dao.getQfqk(xh);
	}
	
	public List<HashMap<String, String>> getCjListByXhXnXq(String xh,String xn,String xq){
		return dao.getCjListByXhXnXq(xh, xn, xq);
	}
	/**
	 * @����: �㽭����ְҵѧԺ�����ȡ����Ա��Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-8 ����10:12:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>getFdyxx(String xh){
		return dao.getFdyxx(xh);
	}
	/**
	 * @����: �㽭����ְҵѧԺ�����ȡ��������Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-8 ����10:14:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>getBzrxx(String xh){
		return dao.getBzrxx(xh);
	}
	/**
	 * @����: �㽭����ְҵѧԺ�����ͥ���������϶�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-8 ����10:15:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>getKnrd(String xh){
		return dao.getKnrd(xh);
	}
	
	/**
	 * 
	 * @����: ȡ����Ա����by�༶
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-3 ����11:47:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFdyByBj(String bjdm){
		return dao.getFdyByBj(bjdm);
	}
	
	/**
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException  
	 * @����:��ȡ�༶�����������ļ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-15 ����07:14:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	public File getBjhmcExcelList(XsxxtyglForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, WriteException, IOException {
		List<HashMap<String,String>> bjdms = dao.getHmcBjs(model);
		
		Map<HashMap<String,String>,List<HashMap<String,String>>> allBjxxMap = new HashMap<HashMap<String,String>,List<HashMap<String,String>>>();
		
		if(bjdms.size()>0){			
			for(HashMap<String,String> bjmp : bjdms){
				String bjdm = bjmp.get("bjdm");
				List<HashMap<String,String>> xsxxs = dao.getbjHmcXxxx(bjdm);
				allBjxxMap.put(bjmp,xsxxs);
			}
		}
		File file = getBjhmcExl(allBjxxMap);
		return file;
	}
	
	/** 
	 * @����:��ȡ�༶������excel�ļ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-16 ����01:42:37
	 * @�޸ļ�¼: xw[���ţ�1426]-20170316-�����ݺϲ���һ���ļ���
	 * @param list
	 * @param bjxx
	 * @return
	 * @throws IOException
	 * @throws WriteException
	 * File �������� 
	 * @throws 
	 */
	public File getBjhmcExl(Map<HashMap<String,String>,List<HashMap<String,String>>> allBjxxMap) throws IOException, WriteException{
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		Set<HashMap<String,String>> keySet = allBjxxMap.keySet();
		String sheetName = "���ε���"+keySet.size()+"���༶";
		
		if(!file.exists()){
			file.createNewFile();
		}			
			 WritableWorkbook  wwb = Workbook.createWorkbook(file);
			 WritableSheet sheet = wwb.createSheet(sheetName, 0);
			 WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
			 WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
			 WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
			 WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
			 WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			 WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			 WritableCellFormat body_jtdz = new WritableCellFormat(bodyFont);//���ü�ͥ��ַ��Ԫ������
			 title_cf.setAlignment(jxl.format.Alignment.LEFT);//���ñ��ⵥԪ�����
			 title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�
			 title_cf.setBackground(Colour.YELLOW);	//���ñ��ⱳ��ɫ
			 head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			 head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
			 head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			 body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
			 body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
			 body_jtdz.setAlignment(jxl.format.Alignment.LEFT);
			 body_jtdz.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			 //���ø����п�
			 sheet.setColumnView(0, 12);
			 sheet.setColumnView(1, 12);
			 sheet.setColumnView(2, 8);
			 sheet.setColumnView(3, 12);
			 sheet.setColumnView(4, 12);
			 sheet.setColumnView(5, 12);
			 sheet.setColumnView(6, 18);
			 sheet.setColumnView(7, 40);
			 sheet.setColumnView(8, 18);
			 sheet.setColumnView(9, 22); 
			 
			 int k = 0;
			 for(Map.Entry<HashMap<String,String>, List<HashMap<String,String>>> entry:allBjxxMap.entrySet()){
				 HashMap<String,String> bjxx = entry.getKey();	//�༶��Ϣ
				 List<HashMap<String,String>> list = entry.getValue();	//�༶��Ӧ��ѧ���б�
				 
				 Label label_title0 = new Label(0, k, bjxx.get("xymc")+"            "+bjxx.get("bjmc")+"                   "+"������"+bjxx.get("zs")+"��"+"  "+"(����:"+bjxx.get("nan")+"��"+"      "+"Ů����"+bjxx.get("nv")+"��)",title_cf);
				 sheet.addCell(label_title0);
				 sheet.mergeCells(0, k, 9, k);
				 sheet.mergeCells(0, k+1, 0, k+2);
				 sheet.mergeCells(1, k+1, 1, k+2);
				 sheet.mergeCells(2, k+1, 2, k+2);
				 sheet.mergeCells(3, k+1, 3, k+2);
				 sheet.mergeCells(4, k+1, 4, k+2);
				 sheet.mergeCells(5, k+1, 5, k+2);
				 sheet.mergeCells(6, k+1, 7, k+1);
				 sheet.mergeCells(8, k+1, 8, k+2);
				 sheet.mergeCells(9, k+1, 9, k+2);
				 Label label_title1 = new Label(0, k+1,"����",head_cf);
				 Label label_title2 = new Label(1, k+1,"ѧ��",head_cf);
				 Label label_title3 = new Label(2, k+1,"�Ա�",head_cf);
				 Label label_title4 = new Label(3, k+1,"����",head_cf);
				 Label label_title5 = new Label(4, k+1,"�ҳ�����",head_cf);
				 Label label_title6 = new Label(5, k+1,"ʡ������",head_cf);
				 Label label_title7 = new Label(6, k+1,"��ͥ��ϸͨѶ��ַ",head_cf);
				 Label label_title8 = new Label(6, k+2,"��(��\\��\\��)",head_cf);
				 Label label_title9 = new Label(7, k+2,"��(��\\��\\��)��(��)����(�ֵ�)",head_cf);
				 Label label_title10 = new Label(8, k+1,"��ͥ�绰",head_cf);
				 Label label_title11 = new Label(9, k+1,"���֤��",head_cf);
				 sheet.addCell(label_title1);
				 sheet.addCell(label_title2);
				 sheet.addCell(label_title3);
				 sheet.addCell(label_title4);
				 sheet.addCell(label_title5);
				 sheet.addCell(label_title6);
				 sheet.addCell(label_title7);
				 sheet.addCell(label_title8);
				 sheet.addCell(label_title9);
				 sheet.addCell(label_title10);
				 sheet.addCell(label_title11);			 
				 if(list.size()>0){
					 for(int i = 0;i<list.size();i++){
						 HashMap<String, String> map = list.get(i);
						 Label xm = new Label(0, k+3+i, map.get("xm"), body_cf);
						 Label xh = new Label(1, k+3+i, map.get("xh"), body_cf);
						 Label xb = new Label(2, k+3+i, map.get("xb"), body_cf);
						 Label mz = new Label(3, k+3+i, map.get("mzmc"), body_cf);
						 Label jzxm = new Label(4, k+3+i, map.get("jzxm"), body_cf);
						 Label shen = new Label(5, k+3+i, map.get("hkshenmc"), body_cf);
						 Label shi = new Label(6, k+3+i, map.get("hkshimc"), body_cf);
						 Label jtdz = new Label(7, k+3+i, (map.get("hkxianmc")==null?"":map.get("hkxianmc"))+(map.get("jtdz")==null?"":map.get("jtdz")), body_jtdz);
						 Label jtdh = new Label(8, k+3+i, map.get("jtdh"), body_cf);
						 Label sfzh = new Label(9, k+3+i, map.get("sfzh"), body_cf);
						 sheet.addCell(xm);
						 sheet.addCell(xh);
						 sheet.addCell(xb);
						 sheet.addCell(mz);
						 sheet.addCell(jzxm);
						 sheet.addCell(shen);
						 sheet.addCell(shi);
						 sheet.addCell(jtdz);
						 sheet.addCell(jtdh);
						 sheet.addCell(sfzh);
					 }
				 }
				 k = k + 3 + list.size();
			 }
			 
			 wwb.write();
			 wwb.close();
			
			 return file;			 
	}
	
	public List<HashMap<String,String>>getXnXscj(String xh) throws Exception{
		List<HashMap<String,String>> list=dao.getXnXscj(xh);
		
//		for(int i=0;i<22-list.size();i++){
//			list.add(new HashMap<String,String>());
//		}
		
		int length=22-list.size();
		for(int i=0;i<length;i++){
			list.add(new HashMap<String,String>());
		}
		
		return list;
	}
	
	public HashMap<String,String> getCxpd(String xh,String xn,String xq){
		return dao.getCxpd(xh, xn, xq);
	}
	
	public boolean checkDownLoadAuth(XsxxtyglForm myForm,User user){
		boolean flag= dao.checkDownLoadAuth(myForm,user);
		return flag;
	}
	public String getXsxz(String xh) {
		return dao.getXsxz(xh);
	}
	public List<HashMap<String, String>> getJcrqjyy(String xh,int n) {
		List<HashMap<String, String>> list = dao.getJcrqjyy(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	public File geTyxx(XsxxtyglForm model) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		List<HashMap<String,String>> TyList=dao.tyView(model);
		data.put("rs", TyList.get(0));	
		File file = FreeMarkerUtil.getExcelFile(data, "classpath://templates//xsxx", "hnsw_tyxx_12303.xml", "��Ա��Ϣ");
		return file;
	}
	public String getYxyj(String xh){
		return dao.getYxyj(xh);
	}
	public boolean setYxyj(XsxxglModel model) throws Exception{
		return dao.setYxyj(model);
	}
}
