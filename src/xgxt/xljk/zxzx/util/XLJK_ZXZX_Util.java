package xgxt.xljk.zxzx.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.New_Random_ID;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.utils.sql.SQL_Util;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.zxzx.form.xljk_xssqyy_Form;
import xgxt.xljk.zxzx.form.xljk_zxsxx_Form;
import xgxt.xljk.zxzx.form.xljk_zxszy_Form;
import xgxt.xljk.zxzx.model.xljk_zxxxx_zy_model;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlDao;
import xgxt.xsxx.cslg.xsjl.XsxxCslgXsjlForm;
import xsgzgl.comm.BasicModel;

import common.Globals;

public class XLJK_ZXZX_Util {
	public DAO mydao = DAO.getInstance();

	lrh_commen_util com_uti = new lrh_commen_util();

	HttpServletRequest request;

	private static XLJK_ZXZX_Util xljk_zxzx_Util = null;

	public XLJK_ZXZX_Util(HttpServletRequest request) {
		this.request = request;
	}

	public XLJK_ZXZX_Util() {
	}

	public static XLJK_ZXZX_Util getXLJK_ZXZX_Util() {
		if (xljk_zxzx_Util == null) {
			xljk_zxzx_Util = new XLJK_ZXZX_Util();
		}
		return xljk_zxzx_Util;
	}

	// ���ݱ����Ʋ�ѯ��������Ϣ�����ص���List����
	public List Find_All(String tableName) {
		String[] inputvalue = {};
		String sql = "select * from " + tableName;
		String[] tabTitle = mydao.getColumnName(sql);
		List li = mydao.getList(sql, inputvalue, tabTitle);
		return li;
	}

	// ���ر��ֶ�����Ӧ���������ƣ�ȡ��ͷ��
	public List Get_xljk_zxsxxb_Title() {
		String xxdm=Base.xxdm;
		String[] tabTitle = mydao
				.getColumnName("select rownum �к�,a.XN_ID,a.ZXXBH,a.ZXXXM,a.ZXXXB,a.ZXXZG from xljk_zxsxxb a");
		
		//����ѧԺ��ʾ��ѯʦ��ר��
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			tabTitle = mydao
			.getColumnName("select rownum �к�,a.XN_ID,a.ZXXBH,a.ZXXXM,a.ZXXXB,a.ZXXZG,a.ZC from xljk_zxsxxb a");
		}
		String[] tabTitleCN = mydao.getColumnNameCN(tabTitle, "xljk_zxsxxb");
		return mydao.arrayToList(tabTitle, tabTitleCN);
	}

	// ���ر��ֶ�����Ӧ���������ƣ�ȡ��ͷ��
	public List Get_xljk_zxszy_Title() {
		String[] tabTitle = mydao
				.getColumnName("select rownum �к�,a.ZXXBH,a.RQ,a.SJD from xljk_zxszyb a");
		String[] tabTitleCN = mydao.getColumnNameCN(tabTitle, "xljk_zxszyb");
		return mydao.arrayToList(tabTitle, tabTitleCN);
	}

	// ȡ��ͷ�����ݴ���һ���������ͱ����ֶ����飬ȡ����ͷ
	public List Get_Table_Title(String tableName, String[] zdm) {
		StringBuffer strbuf = new StringBuffer("select rownum �к�, a.");
		for (int i = 0; i < zdm.length; i++) {
			strbuf.append(zdm[i]);
			strbuf.append((i == zdm.length - 1 ? " " : ",a."));
		}
		strbuf.append(" from ").append(tableName).append(" a ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		String[] tabTitleCN = mydao.getColumnNameCN(tabTitle, tableName);
		return mydao.arrayToList(tabTitle, tabTitleCN);
	}

	// ���ر��ֶ�����Ӧ���������ƣ�ȡ��ͷ��
	public List Get_xljk_zxszy_Title2() {
	/*	String[] tabTitle = mydao
				.getColumnName("select rownum �к�,a.RQ,a.SJD,a.DD,'��ѯʦ' zxxbh from xljk_zxszyb a");
		String[] tabTitleCN = mydao.getColumnNameCN(tabTitle, "xljk_zxszyb");*/
		String[] tabTitle = new String[] {"�к�","����","ʱ���","��ѯ�ص�","��ѯʦ"};
		return mydao.arrayToList(tabTitle, tabTitle);
	}

	// ����Ψһ���ֶΣ��������������߲����ظ����ֶΣ���ѯһ����¼�����ص���xljk_zxsxx_Form����
	public xljk_zxsxx_Form Find_By_PrimaryKey(String table, String primaryKey,
			String pkValue) {
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		String sql = "select * from " + table + " where ";
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		String str = primaryKey + "= ?";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName("select * from " + table);
		List<HashMap<String, String>> li = mydao.getList(strbuf.toString(),
				new String[] { pkValue }, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!li.isEmpty()) {
			map = li.get(0);
			myform.setZxxmm(map.get("ZXXMM"));
			myform.setJj(map.get("JJ"));
			myform.setSex(map.get("ZXXXB"));
			myform.setZxxbh(map.get("ZXXBH"));
			myform.setZxxxm(map.get("ZXXXM"));
			myform.setZxxzg(map.get("ZXXZG"));
			myform.setZxszc(map.get("ZC"));
			if (map.get("ZXXXB").equals("��")) {
				myform.setSex_dm("1");
			} else if (map.get("ZXXXB").equals("Ů")) {
				myform.setSex_dm("2");
			} else {
				myform.setSex_dm("0");
			}
		}
		return myform;
	}

	// ����Ψһ���ֶΣ��������������߲����ظ����ֶΣ���ѯһ����¼�����ص���xljk_zxszy_Form����
	public xljk_zxszy_Form Find_By_PrimaryKey_Zxszy(String pkValue) {
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		String sql = "select * from xljk_zxszyb where xn_id = ?";
		String[] tabTitle = mydao.getColumnName("select * from  xljk_zxszyb ");
		List<HashMap<String, String>> li = mydao.getList(sql,
				new String[] { pkValue }, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!li.isEmpty()) {
			map = li.get(0);
			myform.setDd(map.get("DD"));
			myform.setRq(map.get("RQ"));
			myform.setSjd(map.get("SJD"));
			myform.setZxxbh(map.get("ZXXBH"));
			myform.setXh(map.get("XH"));
			myform.setFlag(map.get("FLAG"));
		}
		return myform;
	}

	// ����Ψһ���ֶΣ��������������߲����ظ����ֶΣ���ѯһ����¼�����ص���list����(�Ա����ı�����ʾ)
	public List Find_By_PrimaryKey2(String table, String primaryKey,
			String pkValue) {
		String sql = "select * from " + table + " where ";
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		String str = primaryKey + "=" + "?";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName("select * from " + table);
		return mydao.getList(strbuf.toString(), new String[] { pkValue },
				tabTitle);
	}

	// ��ѯʦ��Ĳ�ѯ������ͨ����ѯʦ�Ա���ѯʦ��������ѯʦ��Ž��в�ѯ
	public List Find_By_SqlComment(xljk_zxsxx_Form myform) {
		List li = null;
		String ZXXBH = myform.getZxxbh();
		String ZXXXM = myform.getZxxxm();
		String sex_dm = myform.getSex_dm();
		String sex = myform.getSex();
		StringBuffer tmpbuf = new StringBuffer();
		StringBuffer strbuf = new StringBuffer();
		String xxdm=Base.xxdm;
		String sql = "select rownum �к�,a.XN_ID,a.ZXXBH,a.ZXXXM,a.ZXXXB,a.ZXXZG,a.JJ from xljk_zxsxxb a where 1=1 ";
//		if (StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)) { // �й�����ѧԺ
//			sql = "select rownum �к�,bh ZXXBH,xm ZXXXM,xb ZXXXB,zg ZXXZG,jj JJ from xljk_zxsxxb2 a where 1=1 ";
//		}
		//����ѧԺ
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			sql = "select rownum �к�,a.XN_ID,a.ZXXBH,a.ZXXXM,a.ZXXXB,a.ZXXZG,a.JJ,b.ZCMC ZC from xljk_zxsxxb a left join xljk_mjxy_zcdmb  b  on a.ZC=b.ZCDM where 1=1 ";
		}
		strbuf.append(sql);
		if (ZXXBH != null && !ZXXBH.equalsIgnoreCase("")) {
			String str = " and a.ZXXBH =?";
			// '"+ZXXBH+"'
			strbuf.append(str);
			tmpbuf.append(ZXXBH + "!!SplitSignOne!!");
		}
		if (ZXXXM != null && !ZXXXM.equalsIgnoreCase("")) {
			if (Check_Input_Value.check2(ZXXXM) == false) {
				return li;
			}
			String str = " and a.ZXXXM like '%" + ZXXXM + "%' ";
			strbuf.append(str);
		}
		if (sex_dm != null && !sex_dm.equalsIgnoreCase("")) {
			String str = " and a.ZXXXB = ?";
			strbuf.append(str);
			tmpbuf.append(sex);
		}
		String[] tabTitle = mydao.getColumnName(sql);
		if (tmpbuf.toString().equals("")) {
			String[] in = {};
			li = mydao.getList(strbuf.toString(), in, tabTitle);
		} else {
			String[] inputvalue = tmpbuf.toString().split("!!SplitSignOne!!");
			li = mydao.getList(strbuf.toString(), inputvalue, tabTitle);
		}
		return li;
	}

	// �õ����е�����
	public List get_the_date(String zxsbh) {
		String[] outputValue = { "rq" };
		String sql = "select distinct RQ from xljk_zxszyb where zxxbh = ? ";
		String[] inputvalue = { zxsbh };
		List dates = mydao.getList(sql, inputvalue, outputValue);
		return dates;
	}

	// �õ����е�ʱ���
	public List<HashMap<String, String>> get_the_sjd() {
		List<HashMap<String, String>> dates = mydao.getSJDList();
		return dates;
	}

	// ����һ����¼����ѯʦ��Ϣ���У��������xljk_zxsxx_Form���ͣ�������boolean����
	public boolean insert_into_zxsxxb(xljk_zxsxx_Form myform) throws Exception {
		boolean flag = false;
		New_Random_ID newid = new New_Random_ID();
		String tableName = "zxsxx";
		String XN_ID = newid.new_xnid(tableName);
		String ZXXBH = myform.getZxxbh();
		String ZXXXM = myform.getZxxxm();
		String ZXXXB = myform.getSex();
		String ZXXMM = myform.getZxxmm();
		String ZXXZG = myform.getZxxzg();
		String JJ = myform.getJj();
		String zxszc = myform.getZxszc();
		String xxdm = Base.xxdm;
		xljk_zxsxx_Form tryform = new xljk_zxsxx_Form();
		tryform = this.Find_By_PrimaryKey("xljk_zxsxxb", "ZXXBH", ZXXBH);
		if (tryform.getZxxbh().equals(ZXXBH)) {
			flag = false;
		} else {
			String sql = "insert into xljk_zxsxxb (XN_ID,ZXXBH,ZXXXM,ZXXXB,ZXXMM,ZXXZG,JJ) values (?,?,?,?,?,?,?)";
			String[] input = { XN_ID, ZXXBH, ZXXXM, ZXXXB, ZXXMM, ZXXZG, JJ };
			if(xxdm.equalsIgnoreCase(Globals.XXDM_MJXY)){
				sql = "insert into xljk_zxsxxb (XN_ID,ZXXBH,ZXXXM,ZXXXB,ZXXMM,ZXXZG,JJ,ZC) values (?,?,?,?,?,?,?,?)";
				input =new String[] { XN_ID, ZXXBH, ZXXXM, ZXXXB, ZXXMM, ZXXZG, JJ,zxszc };
			}
			
			flag = mydao.runUpdate(sql, input);
		}
		return flag;
	}

	// �õ��Ա��б�
	public List get_sexList() {
		return mydao.getSexList();
	}

	// �õ���ѯʦ��Դ�еĵص��б�
	public List<HashMap<String, String>> get_mycdList() {
		String[] dddm = new String[] { "1", "2", "3", "4", "5" };
		String[] ddmc = new String[] { "�ǳ�����", "������", "һ��", "������", "�ǳ�������" };
		return mydao.arrayToList(dddm, ddmc);
	}

	// �õ���ѯʦ�б�
	public List<HashMap<String, String>> get_zxxList() {
		String[] outputValue = { "zxxbh", "zxxxm" };
		String sql = "select * from xljk_zxsxxb ";
		String[] inputvalue = {};
		List<HashMap<String, String>> dates = mydao.getList(sql, inputvalue,
				outputValue);
		return dates;
	}

	// ������ѯʦ��Ų��ҳ���ѯʦ����
	public String xljk_zxsxx_findName(String zxxbh) {
		xljk_zxsxx_Form myform = new xljk_zxsxx_Form();
		myform = this.Find_By_PrimaryKey("xljk_zxsxxb", "zxxbh", zxxbh);
		return myform.getZxxxm();
	}

	public String[] get_Vector2(String key) {
		List<HashMap<String, String>> li = null;
		li = com_uti.get_dmwhb_dmList(key);
		StringBuffer strbuf = new StringBuffer();
		HashMap<String, String> map = new HashMap<String, String>();
		int liLen = li.size();
		for (int i = 0; i < liLen; i++) {
			map = li.get(i);
			strbuf.append(map.get("DMMC")
					+ (i == liLen ? "" : "!!SplitSignOne!!"));
		}
		String[] Vector = strbuf.toString().split("!!SplitSignOne!!");
		return Vector;
	}

	// ɾ��һ����ѯʦ��Ϣ��¼������һ����ѯʦ��ţ�����һ��boolean����ֲ
	public boolean del_by_zxxbh(String zxxbh) throws Exception {

		String sql = "delete from xljk_zxsxxb where ZXXBH = ? ";
		boolean flag = false;
		flag = mydao.runUpdate(sql, new String[] { zxxbh });
		return flag;

	}

	// ����һ����ѯʦ��¼������һ��xljk_zxsxx_Form����ֵ������һ��boolean����ֲ
	public boolean update_by_zxxbh(xljk_zxsxx_Form myform) throws Exception {

		String sql = "update xljk_zxsxxb set ZXXXM=?,ZXXXB=?,ZXXMM=?,ZXXZG=?,JJ=?,ZC=? where ZXXBH=? ";
		String[] input = { myform.getZxxxm(), myform.getSex(),
				myform.getZxxmm(), myform.getZxxzg(), myform.getJj(),myform.getZxszc(),
				myform.getZxxbh() };
		boolean flag = false;
		flag = mydao.runUpdate(sql, input);
		return flag;
	}

	// ��ѯ��ѯʦ��Դ���������xljk_zxszy_Form,�����õ��Ĳ�������ѯʦ��ţ�ʱ��δ��룬���ڣ����ص���List����
	public List Zxszy_findby_sql(xljk_zxszy_Form myform) {
		String tableName = myform.getTableName();
		String zxxbh = myform.getZxxbh();
		String sjd = myform.getSjd();
		String rq = myform.getRq();
		String sql = "select * from " + tableName + "��where 1=1 ";
		String[] columns = new String[] { "zxxbh", "sjd", "rq" };
		String[] values = new String[] { zxxbh, sjd, rq };
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		strbuf.append(SQL_Util.getWhereSqlUseValue(columns, values));
		String[] tabTitle = mydao.getColumnName("select * from " + tableName);
		return mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	// ��ѯ��ѯʦ��Դ���������xljk_zxszy_Form,�����õ��Ĳ�������ѯʦ��ţ�ʱ��δ��룬���ڣ����ص���List����
	public List Zxszy_findby_sql2(xljk_zxszy_Form myform) {
		dealDate datedeal = new dealDate();
		String tableName = myform.getTableName();
		String zxxbh = myform.getZxxbh();
		String sjd = myform.getSjd();
		String rq = myform.getRq();
		String dd = myform.getDd();
		String[] columns = null;
		String[] values = null;
		columns = new String[] { "zxxbh", "sjd", "dd" };
		values = new String[] { zxxbh, sjd, dd };
		String sql = "select * from " + tableName + " where 1=1 ";
		
		if ("XLJK_ZXSZYB".equalsIgnoreCase(tableName)) {
			sql = "select a.*,(select ZXXXM from xljk_zxsxxb where ZXXBH=a.zxxbh) zxxxm from XLJK_ZXSZYB a where 1=1 ";
		}
		
		// ---------2010/5/26 edit by luojw --------------
//		String xxdm = StandardOperation.getXxdm();
//		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) && "XLJK_ZXSZYB".equalsIgnoreCase(tableName)) {// ���ݴ�ѧ
//			columns = new String[] { "zxxbh", "sjd", "dd" }; 		
//			values = new String[] { zxxbh, sjd, dd };
//			sql = "select a.sjd,a.dd,(select b.zxxxm from xljk_zxsxxb b where a.zxxbh = b.zxxbh) zxxbh from XLJK_ZXSZYB a where 1=1 ";
//		}
		// ---------end --------------
		String today = datedeal.getToday();
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		strbuf.append(SQL_Util.getWhereSqlUseValue(columns, values));
		if (StringUtils.isNotNull(rq)) {
			strbuf.append(" and rq='").append(rq).append("'");
		} else {
			strbuf.append(" and rq>='").append(today).append("'"); // ��ѯ�ڽ���֮�������
		}
		String[] tabTitle = mydao.getColumnName(sql);
		return mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	// ��ѯ��ѯʦ��Դ���������xljk_zxszy_Form,�����õ��Ĳ�������ѯʦ��ţ�ʱ��δ��룬���ڣ����ص���List����
	// ���ڲ�ѯ���õ���Դ�����ø÷����Զ��ѹ�����Դ����
	public List Zxszy_findby_sql3(xljk_zxszy_Form myform) throws Exception {
		this.zxszy_update_pastsource();
		dealDate datedeal = new dealDate();
		String tableName = myform.getTableName();
		String zxxbh = myform.getZxxbh();
		String sjd = myform.getSjd();
		String rq = myform.getRq();
		String dd = myform.getDd();
		String[] columns = null;
		String[] values = null;
		String today = datedeal.getToday();
		StringBuffer strbuf = new StringBuffer();
		columns = new String[] {"zxxbh", "sjd", "dd" };
		values = new String[] { zxxbh, sjd, dd };
		//sfyy �����Ƿ�ԤԼ,��ʾ��ѯʦ�Ƿ��Ѿ���ԤԼ��ѧ��
		String sql = "select xn_id,rq,zxxbh,sjd,dd,(case when xh is not null then '��' else '��' end)sfyy from "+tableName+" where 1=1 ";
		strbuf.append(sql);
		strbuf.append(SQL_Util.getWhereSqlUseValue(columns, values));
		if (!StringUtils.isNull(rq)) {
			strbuf.append(" and rq='").append(rq).append("'");
		} else {
			strbuf.append(" and rq>='").append(today).append("'"); // ��ѯ�ڽ���֮�������
		}
//		if (!StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)) { // �й�����ѧԺ
//			strbuf.append(" and flag='0' ");
//		} else {
//			strbuf.append(" and flag='0'");
//		}
		String[] tabTitle = mydao.getColumnName(sql);
		System.out.println(strbuf.toString());
		return mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	public List Zxszy_findby_sql4(xljk_zxszy_Form myform) {
		String xh = myform.getXh();
//		String tableName = myform.getTableName();
		String zxxbh = myform.getZxxbh();
		String sjd = myform.getSjd();
		String rq = myform.getRq();
//		String sql = "select * from " + tableName + " where 1=1 ";
//		String xxdm = StandardOperation.getXxdm();
//		if(Globals.XXDM_ZGMSXY.equals(xxdm) || "12061".equals(xxdm)){
		String sql = "SELECT distinct b.*,a.zxxxm FROM xljk_zxsxxb a LEFT JOIN xljk_zxszyb b ON a.zxxbh = b.zxxbh WHERE b.xh IS NOT NULL";
//		}
		String dd = myform.getDd();
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		String[] columns = new String[] { "zxxbh", "sjd", "dd", "rq" };
		String[] values = new String[] { zxxbh, sjd, dd, rq };
		
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				strbuf.append(" and b.").append(columns[i]).append("='").append(
						values[i]).append("'");
			}
		}
		strbuf.toString();
		
		List li = null;
		if (StringUtils.isNotNull(xh)) {
			if (Check_Input_Value.check2(xh) == false) {
				return li;
			}
			strbuf.append(" and xh like '%" + xh + "%' ");
		}
		String[] tabTitle = mydao.getColumnName(sql);
		return mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	public List Zxszy_findby_sql5(xljk_zxszy_Form myform) {
		String xh = myform.getXh();
//		String tableName = myform.getTableName();
		String zxxbh = myform.getZxxbh();
		String sjd = myform.getSjd();
		String rq = myform.getRq();
		String xxdm = StandardOperation.getXxdm();
		String dd = myform.getDd();
		String xm = myform.getXm();
		String xymc = myform.getXymc();
		String sql;
		String[] columns;
		String[] values;
		if(Globals.XXDM_ZGMSXY.equals(xxdm)){
//			sql = "SELECT distinct b.*,a.xm FROM xljk_zxsxxb2 a LEFT JOIN xljk_zxszyb b ON a.bh = b.zxxbh WHERE xn_id IS NOT NULL";
			sql = "SELECT distinct b.*,a.zxxxm FROM xljk_zxsxxb a LEFT JOIN xljk_zxszyb b ON a.zxxbh = b.zxxbh WHERE b.xh IS NOT NULL";
			columns = new String[] { "zxxbh", "sjd", "dd", "rq"};
			values = new String[] { zxxbh, sjd, dd, rq };
		}else if(Globals.XXDM_GZDX.equals(xxdm)){
			sql = "SELECT distinct b.*,a.zxxbh FROM view_zxszy a LEFT JOIN view_zxszy b ON a.zxxbh = b.zxxbh WHERE b.xh IS NOT NULL";
			columns = new String[] { "zxxbh", "sjd", "dd", "rq","xm","xymc"};
			values = new String[] { zxxbh, sjd, dd, rq,xm,xymc};
		}else{
//		    sql = "select * from " + tableName + " where 1=1 ";
			sql = "SELECT distinct b.*,a.zxxxm FROM xljk_zxsxxb a LEFT JOIN xljk_zxszyb b ON a.zxxbh = b.zxxbh WHERE b.xh IS NOT NULL";
		    columns = new String[] { "zxxbh", "sjd", "dd", "rq" };
			values = new String[] { zxxbh, sjd, dd, rq };
		}
		
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				strbuf.append(" and b.").append(columns[i]).append("='").append(
						values[i]).append("'");
			}
		}
		strbuf.toString();
		
		List li = null;
		if (xh != null && !xh.equalsIgnoreCase("")) {
			if (Check_Input_Value.check2(xh) == false) {
				return li;
			}
			strbuf.append(" and b.xh like '%" + xh + "%' ");
		}
		strbuf.append(" and b.xh <> ' ' ");
		String[] tabTitle = mydao.getColumnName(sql);
		return li = mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	// ��ѯ�����õ����ڣ����ǽ����Ժ�Ŀ������ڣ�����Ҫ����Դ���д���
	public List zxszy_find_canuseDays() {
		dealDate datedeal = new dealDate();
		String sql = "select distinct(rq) from xljk_zxszyb where rq>=?";
		String today = datedeal.getToday();
		String[] in = { today };
		String[] out = { "rq" };
		List li = mydao.getList(sql, in, out);
		return li;
	}

	// ������ѯʦ��Դ���������ӣ�
	public String Add_zxszy_pl(xljk_zxszy_Form myform, String doType)
			throws Exception {
		boolean flag = false;
		String message = "true";
		String fromrq = myform.getFromrq();
		String torq = myform.getTorq();
		String tableName = myform.getTableName();
		String day = myform.getMonthday();
		String sql = "";
		String[] output = { "rq" };
		List test_li = null;
		dealDate datedeal = new dealDate();// fromrq torq
		if (StringUtils.isArrayNotNull(new String[] { fromrq, torq })) {
			String[] str1 = fromrq.split("-");
			String[] str2 = torq.split("-");
//			for (int i = 0; i < 3; i++) {
				boolean bool = false;
				if(!bool){
					if((Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]))<0){
						bool = true;
					}
					if(!bool){
						if((Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]))==0){
							if((Integer.valueOf(str2[1]) - Integer.valueOf(str1[1]))<0){
								bool = true;
							}
						}
					}
					if(!bool){
						if((Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]))==0){
							if((Integer.valueOf(str2[1]) - Integer.valueOf(str1[1]))==0){
								if((Integer.valueOf(str2[2]) - Integer.valueOf(str1[2]))<0){
									bool = true;
								}
							}
						}
					}
				}
				if (bool) { // ��ʼʱ����ڽ���ʱ��
					message = "date error";
					return message;
				}
//			}
			String[] date_test = datedeal.getDateBounds2(fromrq, torq);
			for (int i = 0; i < date_test.length; i++) {
				if ("".equals(day)) {
					sql = "select * from  " + tableName + " where rq = ?";
					test_li = mydao.getList(sql, new String[] { date_test[i] },
							output);
					if (test_li.size() != 0) {
						message = "date exits";
						return message;
					}
				} else {
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					Date d;
					int y;
					d = f.parse(date_test[i]);
					rightNow.setTime(d);
					y = rightNow.get(Calendar.DAY_OF_WEEK);
					int day1 = Integer.parseInt(day);
					if (y == day1) {
						sql = "select * from  " + tableName + " where rq = ?";
						test_li = mydao.getList(sql,new String[] { date_test[i] }, output);
					}
					if(test_li != null){
						if (test_li.size() != 0) {
							message = "date exits";
							return message;
						}
					}
				}

			}
			if (message.equals("true")) {
				String[] columns = { "XN_ID", "RQ", "FLAG", "SJD", "DD" };
				flag = Xljk_DAO.getXLJK_DAO().batchInsert(columns, myform,
						doType);
				message = flag == true ? "insert_success" : "insert_fail";
			}
		}
		return message;
	}

	// ������ѯʦ��Դ������һ�����Դ��
	public String Add_zxszy_mt(xljk_zxszy_Form myform, String doType)
			throws Exception {
		boolean flag = false;
		String message = "true";
		String rq = myform.getRq();
		String tableName = myform.getTableName();
		String[] output = { "rq" };
		String sql = "";
		if (StringUtils.isNotNull(rq)) {
			sql = "select * from  " + tableName + " where rq = ?";
			List test_li = mydao.getList(sql, new String[] { rq }, output);
			if (test_li.size() != 0) {
				message = "date exits";
				return message;
			}
			if (message.equals("true")) {
				String[] columns = { "XN_ID", "RQ", "FLAG", "SJD", "DD" };
				flag = Xljk_DAO.getXLJK_DAO().batchInsert(columns, myform,
						doType);
				message = flag == true ? "insert_success" : "insert_fail";
			}
		}
		return message;
	}

	// ������ѯʦ��Դ������ʱ�䣬�ص㣬ʱ��Σ���ѯʨ���������Դ��
	public String Add_zxszy_xx(xljk_zxszy_Form myform) throws SQLException {
		New_Random_ID newid = new New_Random_ID();
		String message = "true";
		String rq = myform.getRq();
		String tableName = myform.getTableName();
		String dd = myform.getDd();
		String sjd = myform.getSjd();
		String[] output = { "xn_id" };
		String xn_id = "";
		xn_id = newid.new_xnid("xljk_zxszyb");
		String[] columns = { "XN_ID", "RQ", "FLAG", "SJD", "DD" };
		String[] values = { xn_id, rq, "0", sjd, dd };
		boolean flag = false;
		String sql = "select * from " + tableName
				+ " where rq=? and sjd=? and dd=? ";
		List test_li = mydao.getList(sql, new String[] { rq, sjd, dd }, output);
		if (test_li.size() != 0) {
			message = "date exits";
			return message;
		}
		if (message.equals("true")) {
			flag = StandardOperation.insert(tableName, columns, values,
					this.request); // ����һ����¼
			message = flag == true ? "insert_success" : "insert_fail";
		}
		return message;
	}

	// ƥ����ѯʦ��������ѯʦ��Դ�ı�ţ���Ӹü�¼��Ӧ����ѯʦ�����������ѯʦ��Դ��ź���ѯʦ��ţ����ص���boolean
	public boolean Pipei_Zxs(String id, String zxxbh) throws Exception {
		boolean flag = false;
		String[] xn_id = id.split(",");
		String tableName = "xljk_zxszyb";
		String[] columns = { "ZXXBH" };
		String[] values = { zxxbh };
		String primaryKey = "XN_ID";
		for (int i = 0; i < xn_id.length; i++) {
			flag = StandardOperation.update(tableName, columns, values,
					primaryKey, xn_id[i], this.request);
		}

		return flag;
	}

	// �鿴��ѯʦ��Դ����Ϣ������������¼��ʱ�䣬�ص㣬ʱ��κ�����Ӧ����ѯʦ����ϸ��Ϣ�����������Դ���,���ص��Ƕ�Ӧ����������
	public xljk_zxxxx_zy_model Zxszy_View(String xn_id) {
		xljk_zxxxx_zy_model mymodel = new xljk_zxxxx_zy_model();
		xljk_zxszy_Form zxszy_form = new xljk_zxszy_Form();
		zxszy_form = this.Find_By_PrimaryKey_Zxszy(xn_id);
		String zxxbh = zxszy_form.getZxxbh();
		xljk_zxsxx_Form zxsxx_form = new xljk_zxsxx_Form();
		if (StringUtils.isNotNull(zxxbh)) {
//			if (StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)) { // �й�����ѧԺ
//				String sql = "select BH ZXXBH,XM ZXXXM,MM ZXXMM,XB ZXXXB,ZG ZXXZG,JJ,DD,CSNY,LXDH from xljk_zxsxxb2 where bh =?";
//				HashMap<String, String> map = mydao.getMap(sql,
//						new String[] { zxxbh }, new String[] { "ZXXMM", "JJ",
//								"ZXXXB", "ZXXBH", "ZXXXM", "ZXXZG", "ZXXXB" });
//				zxsxx_form.setZxxmm(map.get("ZXXMM"));
//				zxsxx_form.setJj(map.get("JJ"));
//				zxsxx_form.setSex(map.get("ZXXXB"));
//				zxsxx_form.setZxxbh(map.get("ZXXBH"));
//				zxsxx_form.setZxxxm(map.get("ZXXXM"));
//				zxsxx_form.setZxxzg(map.get("ZXXZG"));
//				if ("��".equals(map.get("ZXXXB"))) {
//					zxsxx_form.setSex_dm("1");
//				} else if ("Ů".equals(map.get("ZXXXB"))) {
//					zxsxx_form.setSex_dm("2");
//				} else {
//					zxsxx_form.setSex_dm("0");
//				}
//			} else {
				zxsxx_form = this.Find_By_PrimaryKey("xljk_zxsxxb", "ZXXBH",
						zxxbh);
//			}
		}
		mymodel.setDd(zxszy_form.getDd());
		mymodel.setRq(zxszy_form.getRq());
		mymodel.setSjd(zxszy_form.getSjd());
		mymodel.setZxxbh(zxszy_form.getZxxbh());
		mymodel.setZxxxm(zxsxx_form.getZxxxm());
		mymodel.setSex(zxsxx_form.getSex());
		mymodel.setZxxzg(zxsxx_form.getZxxzg());
		mymodel.setJj(zxsxx_form.getJj());
		return mymodel;
	}

	// ������ѯʦ��Դ��Ψһ���ɾ����ѯʦ��Դ��¼
	public String zxszy_del(String xn_id) throws Exception {
		String tableName = "xljk_zxszyb";
		String pk = "XN_ID";
		boolean flag = this.deleteExsits(tableName, pk, xn_id);
		return flag == true ? "del_success" : "del_fail";
	}

	// ���ݱ��������ֶ�ֵ��ѯ���������Ĳ����������ֶε��ֶ������ֶ�ֵ�����ص��Ǽ���
	public List zxszy_findby_ohterKey(String ohterKey, String otherKeyValue) {
		List<xljk_zxszy_Form> li = new Vector<xljk_zxszy_Form>();
		xljk_zxszy_Form myform = new xljk_zxszy_Form();
		String sql = "select * from xljk_zxszyb where " + ohterKey + " = ? ";
		String[] tabTitle = mydao.getColumnName("select * from  xljk_zxszyb ");
		List<HashMap<String, String>> lii = mydao.getList(sql,
				new String[] { otherKeyValue }, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!lii.isEmpty()) {
			for (int i = 0; i < lii.size(); i++) {
				map = lii.get(i);
				myform.setZY_XN_ID(map.get("XN_ID"));
				myform.setDd(map.get("DD"));
				myform.setSjd(map.get("SJD"));
				myform.setRq(map.get("RQ"));
				myform.setFlag(map.get("FLAG"));
				myform.setXh(map.get("XH"));
				myform.setZxxbh(map.get("ZXXBH"));
				li.add(myform);
			}
		}
		return li;
	}

	// ���ݱ��������ֶ�ֵ��ѯ���������Ĳ����������ֶε��ֶ������ֶ�ֵ�����������������ص��Ǽ���
	public List Find_By_OtherKey(String tableName, String pk,
			String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = "select * from " + tableName + " where 1=1 ";
		strbuf.append(sql);
		String[] tabTitle = mydao.getColumnName("select * from  " + tableName);
		for (int i = 0; i < otherKeys.length; i++) {
			strbuf.append(" and ").append(otherKeys[i]).append("=?");
		}
		return mydao.getList(strbuf.toString(), otherKeyValues, tabTitle);
	}

	// ������ѯʦ��Դ����Ҫ�ǽ�û�б�Ԥ���������Ѿ����ڵ���Դ����
	// ����Щ������Դ�ı�׼λ��Ϊ1�����ص���boolean����
	public boolean zxszy_update_pastsource() throws Exception {
		boolean flag = false;
		dealDate datedeal = new dealDate();
		String sql = "update xljk_zxszyb set flag='1' where rq < ? and  flag='0' ";
		String today = datedeal.getToday();
		String[] input = { today };
		flag = mydao.runUpdate(sql, input);
		String time = datedeal.getTime();
		sql = "update xljk_zxszyb set flag='1' where flag='0' and sjd < ? and rq=? ";
		String[] input2 = { time, today };
		flag = mydao.runUpdate(sql, input2);
		return flag;
	}

	// ��֤ѧ���Ƿ�Ϸ����Żص���string
	// ����ǺϷ��û������ص���true,����ǷǷ��û������ص���no this xh����������������ô���ص���wrong mm
	public String xssqyy_check_student(xljk_xssqyy_Form myform) {
		String message = "true";
		String xh = myform.getXh();
		String xssrmm = myform.getXssrmm();
		String xsmm = "";
		String sql = "select * from view_xsjbxx where xh=? ";
		String[] input = { xh };
		String[] tabTitle = mydao.getColumnName("select * from VIEW_XSJBXX ");
		List<HashMap<String, String>> li = mydao.getList(sql, input, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		if (li.isEmpty()) {
			message = "no this xh";
			return message;
		} else {
			for (int i = 0; i < li.size(); i++) {
				Encrypt ept = new Encrypt();
				map = li.get(i);
				xsmm = map.get("MM");
				xsmm = ept.decrypt(xsmm);
			}
			if (xsmm.equals(xssrmm)) {
				return message;
			} else {
				message = "wrong mm";
				return message;
			}
		}
	}

	// ����ѧ�Ų��ҳ�ѧ���������Ϣ
	// �������ѧ�ţ����ص���form���͵����ݣ������ѧ����һЩ������Ϣ
	public xljk_xssqyy_Form xssqyy_findStu_byId(String xh) {
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		boolean cf = Check_Input_Value.check2(xh);
		if (cf == false) {
			return myform;
		}
		String sql = "select a.XM,a.XB,a.XYMC,a.ZYMC,a.QSDH,a.SJHM,b.CSRQ,a.NJ,(select b.qxmc from dmk_qx b where b.qxdm = substr(b.jg, 0, instr(b.jg, '/') - 1)) || (select b.qxmc from dmk_qx b where b.qxdm = substr(b.jg,instr(b.jg, '/') + 1,instr(b.jg, '/', 1, 2) - instr(b.jg, '/') - 1)) || (select b.qxmc from dmk_qx b where b.qxdm = substr(b.jg, instr(b.jg, '/', -1) + 1)) jg from View_xsjbxx a , view_xsxxb b where a.xh = ? and a.xh=b.xh";
		String[] input = { xh };
		String[] tabTitle = mydao.getColumnName("select a.XM,a.XB,a.XYMC,a.ZYMC,a.QSDH,a.SJHM,b.CSRQ,a.NJ,(select b.qxmc from dmk_qx b where b.qxdm = substr(b.jg, 0, instr(b.jg, '/') - 1)) || (select b.qxmc from dmk_qx b where b.qxdm = substr(b.jg,instr(b.jg, '/') + 1,instr(b.jg, '/', 1, 2) - instr(b.jg, '/') - 1)) || (select b.qxmc from dmk_qx b where b.qxdm = substr(b.jg, instr(b.jg, '/', -1) + 1)) jg from VIEW_XSJBXX a ,view_xsxxb b where  a.xh=b.xh");
		List<HashMap<String, String>> li = mydao.getList(sql, input, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < li.size(); i++) {
			map = li.get(i);
			myform.setXh(xh);
			myform.setXm(map.get("XM"));
			myform.setXs_sex(map.get("XB"));
			myform.setXy(map.get("XYMC"));
			myform.setZy(map.get("ZYMC"));
			myform.setNj(map.get("NJ"));
			myform.setCsrq(map.get("CSRQ"));
			myform.setQsdh(map.get("QSDH"));
			myform.setSjhm(map.get("SJHM"));
			myform.setJg(map.get("JG"));
		}
		return myform;
	}

	// ������ѯʦ��Դ�������ţ����ҳ�������Դ�ľ�����Ϣ
	// ���������Դ�������ţ��Żص��� form���͵����ݣ��������Դ�������Ϣ
	public xljk_xssqyy_Form xssqyy_findYq_byXnid(String xnid) {
		xljk_xssqyy_Form yqform = new xljk_xssqyy_Form();
		String sql = "select a.ZXXBH,a.RQ,a.SJD,a.DD,b.ZXXXM ,b.ZXXXB from xljk_zxszyb a, xljk_zxsxxb b where a.XN_ID=? and a.ZXXBH=b.ZXXBH ";
		String[] input = { xnid };
		String[] tabTitle = mydao
				.getColumnName("select a.ZXXBH,a.RQ,a.SJD,a.DD,b.ZXXXM ,b.ZXXXB from xljk_zxszyb a, xljk_zxsxxb b where a.ZXXBH=b.ZXXBH");
//		if (StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)) { // �й�����ѧԺ
//			sql = "select a.ZXXBH,a.RQ,a.SJD,a.DD,b.XM ZXXXM,b.XB ZXXXB from xljk_zxszyb a, xljk_zxsxxb2 b where a.XN_ID=? and a.ZXXBH=b.BH ";
//			tabTitle = mydao
//					.getColumnName("select a.ZXXBH,a.RQ,a.SJD,a.DD,b.XM ZXXXM,b.XB ZXXXB from xljk_zxszyb a, xljk_zxsxxb2 b where a.ZXXBH=b.BH");
//		}
		List<HashMap<String, String>> li = mydao.getList(sql, input, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < li.size(); i++) {
			map = li.get(i);
			yqform.setZxs_sex(map.get("ZXXXB"));
			yqform.setZxxxm(map.get("ZXXXM"));
			yqform.setZXXBH(map.get("ZXXBH"));
			yqform.setSjd(map.get("SJD"));
			yqform.setRq(map.get("RQ"));
			yqform.setDd(map.get("DD"));
		}
		return yqform;
	}

	// �ж��û������Ƿ�Ϸ�
	public boolean checkInput(String input) {
		boolean flag = false;
		String[] testIn = input.split("'");
		int len = testIn.length;
		if (len == 1) {
			flag = true;
		}
		return flag;
	}

	// ��ѧ����д��ԤԼ������ύ,����¼�����ݿ�����,ͬʱ����Ӧ����Դ����
	public boolean xssqyy_tableDetail_Rec(xljk_xssqyy_Form myform)
			throws Exception {
		//TODO
		//-----------2010/5/27 edit by luojw ---------------
		Xljk_DAO dao = new Xljk_DAO();
		boolean flag = false;
		New_Random_ID newid = new New_Random_ID();
		String XLJK_YYDJB_xnid = newid.new_xnid("XLJK_YYDJB");
		String zxszy_xnid = myform.getZxszy_xnid();
		String sxzymycd = myform.getZymycd();
		String xh = myform.getXh();
		String zxnr = myform.getZxnr();
		String mbqw = myform.getQwmb();
		String csrq = myform.getCsrq();
		String jg = myform.getJg();
		String jtjjzk = myform.getJtjjzk();
		String qsdh = myform.getQsdh();
		String sjhm = myform.getSjhm();
		String sfzxg_flag = myform.getSfzxg_flag();
		String jjcd = request.getParameter("jjcd");//myform.getJjcd();//�����̶�
		String yyfs = myform.getYyfs(); // ȡ��ԤԼ��ʽ
		String sql = "select a.XN_ID from  xljk_yydjb_jjcy a where a.xh=? ";
		List<HashMap<String, String>> test_li = mydao.getList(sql,
				new String[] { xh }, new String[] { "XN_ID" });
		StringBuffer strbuf = new StringBuffer();
		if (test_li.size() == 0) {
			
			String[] values = new String[5];
			
			for (int i = 0; i < 5; i++) {
				String id = newid.new_xnid("YYDJB_JJCY");
				values[i] = id;
				strbuf.append(id + (i == 4 ? "" : ","));
			}
			
			flag = dao.saveYydjb(values,xh);
			
		} else {
			HashMap<String, String> map = new HashMap<String, String>();
			int liLen = test_li.size();
			for (int i = 0; i < liLen; i++) {
				map = test_li.get(i);
				strbuf.append(map.get("XN_ID") + (i == liLen ? "" : ","));
			}
		}
		String[] XN_IDVector = strbuf.toString().split(",");
		if (sfzxg_flag.equals("yes")) {
			String YYDJB_ZXLS_xnid = newid.new_xnid("YYDJB_ZXLS");
			String jgmc = myform.getJgmc();
			String ysxm = myform.getYsxm();
			String qzsj = myform.getQzsj();
			String sffyyw = myform.getSffyyw();
			String zdjg = myform.getZdjg();
			String[] columns = { "XN_ID", "JGMC", "YSXM", "QZSJ", "ZDJG",
					"SFFYYW", "XH", "YYDJB_XNID" };
			String[] values = { YYDJB_ZXLS_xnid, jgmc, ysxm, qzsj, zdjg,
					sffyyw, xh, XLJK_YYDJB_xnid };

			//��ʷ��ѯ��Ϣ
			flag = StandardOperation.insertNoLog("XLJK_YYDJB_ZXLS", columns, values);
		}
		String YYDJB_ZXYQ_xnid = newid.new_xnid("YYDJB_ZXYQ");
		String zxsxb = myform.getZxs_sex();
		String zxdd = myform.getDd();
		String zxsj = myform.getRq() + " " + myform.getSjd();
		String qt = myform.getQt();
		String[] columns = { "XN_ID", "ZXSXB", "ZXSJ", "ZXDD", "QT",
				"YYDJB_XNID" };
		String[] values = { YYDJB_ZXYQ_xnid, zxsxb, zxsj, zxdd, qt,
				XLJK_YYDJB_xnid };
//		flag = StandardOperation.insert("XLJK_YYDJB_ZXYQ", columns, values,
//				this.request);
		flag = StandardOperation.insertNoLog("XLJK_YYDJB_ZXYQ", columns, values);
		String[] gxArr = { myform.getJTCY1_gx(), myform.getJTCY2_gx(),
				myform.getJTCY3_gx(), myform.getJTCY4_gx(),
				myform.getJTCY5_gx() };
		String[] csnyArr = { myform.getJTCY1_csny(), myform.getJTCY2_csny(),
				myform.getJTCY3_csny(), myform.getJTCY4_csny(),
				myform.getJTCY5_csny() };
		String[] xlArr = { myform.getJTCY1_xl(), myform.getJTCY2_xl(),
				myform.getJTCY3_xl(), myform.getJTCY4_xl(),
				myform.getJTCY5_xl() };
		String[] zysfArr = { myform.getJTCY1_zysf(), myform.getJTCY2_zysf(),
				myform.getJTCY3_zysf(), myform.getJTCY4_zysf(),
				myform.getJTCY5_zysf() };
		
		//������ͥ��ϵ��
		flag = dao.saveYydjb(gxArr, csnyArr, xlArr, zysfArr, XN_IDVector);
		
		String[] coledn = new String[] { "XN_ID", "XH", "SXZYMYCD", "ZXNR",
				"MBQW", "ZXSZY_XN_ID", "CSRQ", "JG", "JTJJZK", "QSDH", "SJHM",
				"SFZXG_FLAG", "YYFS","JJCD" };
		String[] valend = { XLJK_YYDJB_xnid, xh, sxzymycd, zxnr, mbqw,
				zxszy_xnid, csrq, jg, jtjjzk, qsdh, sjhm, sfzxg_flag, yyfs,
				jjcd };
		
		//ԤԼ�Ǽ�
		flag = StandardOperation.insertNoLog("XLJK_YYDJB", coledn, valend);
		String[] zxszy_col = { "FLAG", "XH" };
		String[] zxszy_val = { "1", xh }; 
		
		//������ѯʦ��Ϣ
		flag = StandardOperation.update("xljk_zxszyb", zxszy_col, zxszy_val,
				"XN_ID", zxszy_xnid, this.request);
		return flag;
	}

	public xljk_xssqyy_Form xssqyy_find_rec(String zxszy_xnid) {
		xljk_xssqyy_Form rsform = new xljk_xssqyy_Form();
		xljk_xssqyy_Form stuform = new xljk_xssqyy_Form();
		xljk_zxszy_Form zyform = this.Find_By_PrimaryKey_Zxszy(zxszy_xnid);
		String xh = zyform.getXh();
		stuform = this.xssqyy_findStu_byId(xh);
		rsform.setXh(xh);
		rsform.setXm(stuform.getXm());
		rsform.setXs_sex(stuform.getXs_sex());
		rsform.setXy(stuform.getXy());
		rsform.setZy(stuform.getZy());
		rsform.setCsrq(stuform.getCsrq());
		rsform.setQsdh(stuform.getQsdh());
		rsform.setSjhm(stuform.getSjhm());
		String xxdm = StandardOperation.getXxdm();
		String[] outputValues;
		String sql;
		if(Globals.XXDM_ZGMSXY.equals(xxdm)){
			outputValues = new String[] { "XN_ID", "XH", "SXZYMYCD",
					"ZXNR", "MBQW", "ZXSZY_XN_ID", "CSRQ", "JG", "JTJJZK", "QSDH",
					"SJHM", "SFZXG_FLAG","YYFS" };
			sql = "select a.XN_ID,a.XH,a.SXZYMYCD,a.ZXNR,a.MBQW,a.ZXSZY_XN_ID,a.CSRQ,a.JG,a.JTJJZK,a.QSDH,a.SJHM,a.SFZXG_FLAG,a.YYFS from  xljk_yydjb a where a.ZXSZY_XN_ID=?";
		}else{
			outputValues = new String[] { "XN_ID", "XH", "SXZYMYCD",
				"ZXNR", "MBQW", "ZXSZY_XN_ID", "CSRQ", "JG", "JTJJZK", "QSDH",
				"SJHM", "SFZXG_FLAG","JJCD" };
		    sql = "select a.XN_ID,a.XH,a.SXZYMYCD,a.ZXNR,a.MBQW,a.ZXSZY_XN_ID,a.CSRQ,a.JG,a.JTJJZK,a.QSDH,a.SJHM,a.SFZXG_FLAG,a.JJCD from  xljk_yydjb a where a.ZXSZY_XN_ID=?";
		}
		//		if (StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)) {
//			sql = "select a.XN_ID,a.XH,a.SXZYMYCD,a.ZXNR,a.MBQW,a.ZXSZY_XN_ID,"
//					+ "a.CSRQ,a.JG,a.JTJJZK,a.QSDH,a.SJHM,a.SFZXG_FLAG,YYFS"
//					+ " from  xljk_yydjb a where a.ZXSZY_XN_ID=?";
//			outputValues = FormUtils.arrayCopy(outputValues,
//					new String[] { "YYFS" });
//		}
		List<HashMap<String, String>> temp_list = mydao.getList(sql,
				new String[] { zxszy_xnid }, outputValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = temp_list.size();
		for (int i = 0; i < tempLen; i++) {
			map = temp_list.get(i);
			rsform.setZymycd(map.get("SXZYMYCD"));
			rsform.setZxnr(map.get("ZXNR"));
			rsform.setQwmb(map.get("MBQW"));
			rsform.setCsrq(map.get("CSRQ"));
			rsform.setJg(map.get("JG"));
			rsform.setJtjjzk(map.get("JTJJZK"));
			rsform.setQsdh(map.get("QSDH"));
			rsform.setSjhm(map.get("SJHM"));
			rsform.setSfzxg_flag(map.get("SFZXG_FLAG"));
			rsform.setZxszy_xnid(zxszy_xnid);
			rsform.setYydjb_xnid(map.get("XN_ID"));
			rsform.setJjcd(map.get("JJCD"));
		}
		if (StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)) {
			rsform.setYyfs(map.get("YYFS")); // �õ�ԤԼ��ʽ
		}

		sql = "select a.GX,a.CSNY,a.XL,a.ZYSF  from  xljk_yydjb_jjcy a where a.XH=?";
		temp_list = mydao.getList(sql, new String[] { xh }, new String[] {
				"GX", "CSNY", "XL", "ZYSF" });
		tempLen = temp_list.size();
		for (int i = 0; i < tempLen; i++) {
			map = temp_list.get(i);
			if (i == 0) {
				rsform.setJTCY1_gx(map.get("GX"));
				rsform.setJTCY1_csny(map.get("CSNY"));
				rsform.setJTCY1_xl(map.get("XL"));
				rsform.setJTCY1_zysf(map.get("ZYSF"));
			} else if (i == 1) {
				rsform.setJTCY2_gx(map.get("GX"));
				rsform.setJTCY2_csny(map.get("CSNY"));
				rsform.setJTCY2_xl(map.get("XL"));
				rsform.setJTCY2_zysf(map.get("ZYSF"));
			} else if (i == 2) {
				rsform.setJTCY3_gx(map.get("GX"));
				rsform.setJTCY3_csny(map.get("CSNY"));
				rsform.setJTCY3_xl(map.get("XL"));
				rsform.setJTCY3_zysf(map.get("ZYSF"));
			} else if (i == 3) {
				rsform.setJTCY4_gx(map.get("GX"));
				rsform.setJTCY4_csny(map.get("CSNY"));
				rsform.setJTCY4_xl(map.get("XL"));
				rsform.setJTCY4_zysf(map.get("ZYSF"));
			} else if (i == 4) {
				rsform.setJTCY5_gx(map.get("GX"));
				rsform.setJTCY5_csny(map.get("CSNY"));
				rsform.setJTCY5_xl(map.get("XL"));
				rsform.setJTCY5_zysf(map.get("ZYSF"));
			}
		}
		if (rsform.getSfzxg_flag().equalsIgnoreCase("yes")) {
			sql = "select a.XN_ID,a.JGMC,a.YSXM,a.QZSJ,a.ZDJG,a.SFFYYW from xljk_yydjb_zxls a where a.YYDJB_XNID=?";
			temp_list = mydao.getList(sql, new String[] { rsform
					.getYydjb_xnid() }, new String[] { "XN_ID", "JGMC", "YSXM",
					"QZSJ", "ZDJG", "SFFYYW" });
			tempLen = temp_list.size();
			for (int i = 0; i < tempLen; i++) {
				map = temp_list.get(i);
				rsform.setYydjb_sfzyg_xnid(map.get("XN_ID"));
				rsform.setJgmc(map.get("JGMC"));
				rsform.setYsxm(map.get("YSXM"));
				rsform.setQzsj(map.get("QZSJ"));
				rsform.setZdjg(map.get("ZDJG"));
				rsform.setSffyyw(map.get("SFFYYW"));
			}
		}
		sql = "select a.XN_ID,a.ZXSXB,a.ZXSJ,a.ZXDD,a.QT from xljk_yydjb_zxyq a where a.YYDJB_XNID=?";
		temp_list = mydao.getList(sql, new String[] { rsform.getYydjb_xnid() },
				new String[] { "XN_ID", "ZXSXB", "ZXSJ", "ZXDD", "QT" });
		tempLen = temp_list.size();
		for (int i = 0; i < tempLen; i++) {
			map = temp_list.get(i);
			String[] sj = map.get("ZXSJ").split(" ");
			rsform.setYydjb_zxyq_xnid(map.get("XN_ID"));
			rsform.setZxs_sex(map.get("ZXSXB"));
			rsform.setSjd(sj[1]);
			rsform.setRq(sj[0]);
			rsform.setDd(map.get("ZXDD"));
			rsform.setQt(map.get("QT"));
		}
		sql = "select a.ZXXBH from XLJK_ZXSZYB a where a.XN_ID=?";
		temp_list = mydao.getList(sql, new String[] { rsform.getZxszy_xnid() },
				new String[] { "ZXXBH" });
		tempLen = temp_list.size();
		String zxxbh = "";
		for (int i = 0; i < tempLen; i++) {
			map = temp_list.get(i);
			zxxbh = map.get("ZXXBH");
		}
		rsform.setZXXBH("zxxbh");
		sql = "select a.ZXXXM from xljk_zxsxxb a where a.ZXXBH=?";
		temp_list = mydao.getList(sql, new String[] { zxxbh },
				new String[] { "ZXXXM" });
		tempLen = temp_list.size();
		String zxxxm = "";
		for (int i = 0; i < tempLen; i++) {
			map = temp_list.get(i);
			zxxxm = map.get("ZXXXM");
		}
		rsform.setZxxxm(zxxxm);
		return rsform;
	}

	public boolean xssqyy_cancle_table(String zxszy_xnid) throws Exception {
		String[] zy_xnid = zxszy_xnid.split(",");
		int zyLen = zy_xnid.length;
		String sql = "";
		String yydjb_xnid = "";
		String sfzxg_flag = "";
		for (int i = 0; i < zyLen; i++) {
			sql = "select a.XN_ID,a.SFZXG_FLAG,a.XH from  xljk_yydjb a where a.ZXSZY_XN_ID=?";
			List<HashMap<String, String>> temp_list = mydao.getList(sql,
					new String[] { zy_xnid[i] }, new String[] { "XN_ID", "XH",
							"SFZXG_FLAG" });
			HashMap<String, String> map = new HashMap<String, String>();
			int tempLen = temp_list.size();
			for (int j = 0; j < tempLen; j++) {
				map = temp_list.get(j);
				sfzxg_flag = map.get("SFZXG_FLAG");
				yydjb_xnid = map.get("XN_ID");
			}
			if (sfzxg_flag.equalsIgnoreCase("yes")) {
				String[] otherKey = { "YYDJB_XNID" };
				String[] otherKayValue = { yydjb_xnid };
				StandardOperation.delete2("XLJK_YYDJB_ZXLS", "XN_ID", otherKey,
						otherKayValue, this.request);
			}
			String[] othK1 = { "YYDJB_XNID" };
			String[] othKv1 = { yydjb_xnid };
			StandardOperation.delete2("XLJK_YYDJB_ZXYQ", "XN_ID", othK1,
					othKv1, this.request);
			StandardOperation.delete("XLJK_YYDJB", "XN_ID", yydjb_xnid,
					this.request);
			String[] columns = { "XH", "FLAG" };
			String[] values = { "", "0" };
			StandardOperation.update("XLJK_ZXSZYB", columns, values, "XN_ID",
					zy_xnid[i], this.request);
		}
		return false;
	}

	private boolean deleteExsits(String tableName, String columns, String xn_id) {
		DAO dao = DAO.getInstance();
		StringBuffer existValues = new StringBuffer();
		// deal XN_ID
		String[] pkValue = xn_id.split(",");
		for (int i = 0; i < pkValue.length; i++) {
			existValues.append("'" + pkValue[i] + "'");
			existValues.append((i == pkValue.length - 1) ? "" : ",");
		}
		StringBuffer bf = new StringBuffer();
		bf.append("delete from ");
		bf.append(tableName);
		bf.append(" where ");
		bf.append(columns);
		bf.append(" in (" + existValues + ")");
		try {
			return dao.runUpdate(bf.toString(), new String[] {});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** ����ԤԼ��ʽ */
	public List<HashMap<String, String>> getYyfsList() {
		String[] columns = new String[] { "����ԤԼ", "�绰ԤԼ", "�ֳ�ԤԼ" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(columns, columns);
	}
	
	/**
	 * ɾ�������������Ϣ
	 * 
	 * @author luojw
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delXljk(SaveForm form, xljk_zxszy_Form model)
			throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}
	
	/**
	 * ����ƥ����ѯʦ
	 * 
	 * @author luojw
	 */
	public boolean savePpzxs(String xn_id, String zxxbh) throws Exception {
		Xljk_DAO dao = new Xljk_DAO();
		return dao.savePpzxs(xn_id, zxxbh);
	}
	
	/**
	 * ��ѯʦר���б�
	 * 2010.12.2qlj
	 */
	public List<HashMap<String,String>>getZxszcList(){
		Xljk_DAO dao=new Xljk_DAO();
		HashMap<String,String>hashMap=new HashMap<String,String>();
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		
		hashMap.put("dm", "");
		hashMap.put("mc", "---��ѡ��---");
		list.add(hashMap);
		list.addAll(dao.getZxszcList());
		return list;
	}
	
	/**
	 * 
	 * @param xsjlForm
	 * @param request
	 * @param wwb
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void expData(xljk_xssqyy_Form myform,
			HttpServletRequest request, WritableWorkbook wwb)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Xljk_DAO dao = new Xljk_DAO();
		MakeQuery makeQuery=new MakeQuery();
		String []colLikeList={"xh","zxxbh"};
		String []colList={"dd","sjd","rq"};
		
		myform.setDd(myform.getDd_dm());
		myform.setSjd(myform.getSjd_dm());
		makeQuery.makeQuery(colList, colLikeList, myform);
		List<HashMap<String,String>>list=dao.getTjList(makeQuery.getInputList(),makeQuery.getQueryString().toString()+dao.getSql(myform).toString());
		
		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,6, 0);
			ws.addCell(new Label(0,0,"                                            ѧ��ԤԼ��ѯ��                   ",wcfTytle));
			wcfTytle = new WritableCellFormat(); 
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 ���ñ��߿�
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.addCell(new Label(0,1,"���",wcfTytle));
			ws.addCell(new Label(1,1,"����",wcfTytle));
			ws.addCell(new Label(2,1,"ϵ��",wcfTytle));
			ws.addCell(new Label(3,1,"�꼶רҵ",wcfTytle));
			ws.addCell(new Label(4,1,"��ϵ��ʽ",wcfTytle));
			ws.addCell(new Label(5,1,"ԤԼ��ʦ",wcfTytle));
			ws.addCell(new Label(6,1,"ԤԼʱ��",wcfTytle));
			
			for(int i=0; i <list.size();i++){
				HashMap<String,String>hashMap=list.get(i);
				ws.addCell(new Label(0,i+2,""+(i+1),wcfTytle));
				ws.addCell(new Label(1,i+2,hashMap.get("xm"),wcfTytle));
				ws.addCell(new Label(2,i+2,hashMap.get("zymc"),wcfTytle));
				ws.addCell(new Label(3,i+2,hashMap.get("nj"),wcfTytle));
				ws.addCell(new Label(4,i+2,hashMap.get("lxdh"),wcfTytle));
				ws.addCell(new Label(5,i+2,hashMap.get("zxxxm"),wcfTytle));
				ws.addCell(new Label(6,i+2,hashMap.get("rq")+"  "+hashMap.get("sjd"),wcfTytle));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public boolean findInfo(String xnId, String zxxbh,BasicModel model) throws Exception {
		String sql = "select rq,sjd,rownum r from xljk_zxszyb where zxxbh=?";
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql, "", new String[] { zxxbh }, new String[] {"rq","sjd"}, model);
		for (String[] val : list) {
			String rq = val[0];
			String sjd = val[1];
			sql = "select rq,sjd,rownum r from xljk_zxszyb where xn_id=?";
			ArrayList<String[]> listpp = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql, "", new String[] { xnId }, new String[] {"rq","sjd"}, model);
			for (String[] valpp : listpp) {
				String rqpp = valpp[0];
				String sjdpp = valpp[1];
				if(rq.equals(rqpp) && sjd.equals(sjdpp)){
					return true;
				}
			}
		}
		return false;
	}
}
