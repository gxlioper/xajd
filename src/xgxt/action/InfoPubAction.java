/*
 * �������� 2006-9-18
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.DAO.SxjyDAO;
import xgxt.base.DealString;
import xgxt.base.Encrypt;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.SaveForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.hngydx.dao.HngydxGwglDAO;
import xgxt.qgzx.service.QgzxGwfbService;
import xgxt.qgzx.service.QgzxGwglService;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.xbemy.XbemyQgzxDAO;
import xgxt.szdw.zjlg.XjchDAO;
import xgxt.utils.CheckPower;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import common.Globals;

/**
 * @author bat_zzj
 * 
 * Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class InfoPubAction extends Action {
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase(""));
	}
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userType;
		String sUName;
//		String logMsg;
		String writeAble;
		// ͨ�õ����ݲ���ģ��
		HttpSession session = request.getSession();
		CommanForm chkUser =  (CommanForm) form;
		String xxdm = StandardOperation.getXxdm();
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
			boolean isStu = true;
			userType = session.getAttribute("userType").toString();
			isStu = (userType.equalsIgnoreCase("stu"));
			sUName = session.getAttribute("userName").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();
			String power;
			String para = "";
			String doType = request.getParameter("doType");
			int p = -1;
			if ("commInfoPub".equalsIgnoreCase(myAct)) {
				para = request.getParameter("tableName");
				if(isNull(para))
					para = "";
				power = "comm_pub.do?doType=add&tableName=" + para;
//				p = Base.chkUPower(sUName, power, isStu);
				if((xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) && isStu && "view_gwxx".equalsIgnoreCase(para) && "modi".equalsIgnoreCase(doType)){
					//�人���ڹ���ѧ
					return new ActionForward("/whlggwgl.do?method=showGwxxDetail");
				}
				if( xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) && isStu && "view_gwxx".equalsIgnoreCase(para) && "modi".equalsIgnoreCase(doType)){
					//�Ϻ������ڹ���ѧ
					return new ActionForward("/qgzxLogic.do?method=showPostDetails");
				}
				if( xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX) && isStu && "view_gwxx".equalsIgnoreCase(para) && "modi".equalsIgnoreCase(doType)){
					//�й������ڹ���ѧ
					return new ActionForward("/qgzxZgdzdx.do?method=showPostDetails");
				}
//				if (p == -1) {
//					request.setAttribute("errMsg", "��Ȩ���ʸ�ҳ�棡");
//					return new ActionForward("/errMsg.do", false);
//				}
				return dataPub(mapping, form, request, response, p);
			} else if ("commInfoSearch".equalsIgnoreCase(myAct)) {
				//����ģ��·��
				request.setAttribute("path", "data_search2.do?act=work");
				FormModleCommon.commonRequestSet(request);
				myActFwd = dataSearch(mapping, form, request, response);
				writeAble = request.getParameter("writeAble");
				if("".equalsIgnoreCase(writeAble) || writeAble == null){
				writeAble = (String)request.getAttribute("writeAble");
				}
				p = "yes".equalsIgnoreCase(writeAble) ? 1 : 0;
			} else if ("fdyxx".equalsIgnoreCase(myAct)) {	
				power = "fdyxx.do";
				p = Base.chkUPower(sUName, power, isStu);
				if(p == -1){
					request.setAttribute("errMsg", "��Ȩ���ʸ�ҳ��!");
					return new ActionForward("/errMsg.do",false);
				}
				myActFwd = fdyxx(mapping, form, request, response);	
			} else if ("wxszsjtj".equalsIgnoreCase(myAct)) {	
				myActFwd = wxszsjtj(mapping, form, request, response);	
			} else if ("fdyxxOne".equalsIgnoreCase(myAct)) {
				myActFwd = fdyxxOne(mapping, form, request, response);
			} else if ("gzdxscgwxxbPrint".equalsIgnoreCase(myAct)) {//���ݴ�ѧ��λ������ӡ`
				myActFwd = gzdxgwxxbPrint(mapping, form, request, response);
			}
			writeAble = (p == 1) ? "yes" : "no";
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("���������жϣ������µ�½��");
			return new ActionForward("/login.jsp", false);
		}
	}

		@SuppressWarnings("unchecked")
		private ActionForward dataPub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
	throws Exception {
		// �����޸ġ�ɾ��������

		ActionForward newFwd = new ActionForward();
		CommanForm commForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String tableName = request.getParameter("tableName");
		String from = request.getParameter("from");		
		String userName = session.getAttribute("userName").toString();
		
		String xxdm = StandardOperation.getXxdm();
		String writeAble = Base.getWriteAble(request);
		request.setAttribute("writeAble", writeAble);
		if (tableName.equalsIgnoreCase("view_gwxx")) {
			request.setAttribute("path", "comm_pub.do?doType=add&tableName=view_gwxx");//
			FormModleCommon.commonRequestSet(request);
		}
		
		String sql = "";
		String realTable = "";
		String pk = "";
		String[] jgsCheck = null;
		
		
		pkValue = (pkValue == null||pkValue.equals("")) ? " " : pkValue.trim();
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)){
		//����ʦ��ѧԺ	
			request.setAttribute("zdy", "zdy");
		}

		if (tableName == null) {
			commForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (tableName.equalsIgnoreCase("view_dwjlxx")) {
			//���⽻��
			realTable = "dwjlxxb";
			pk = "xn||nd||xq||jlxmdm";
		} else if (tableName.equalsIgnoreCase("view_gwxx")) {
			//��λ����
			realTable = "gwxxb";
			pk = "gwdm||gwsbsj";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
				//����Ƽ� ��λ���ͨ����Ͳ��������޸�
				sql = "select count(*) num from view_gwxx where shjg='ͨ��' and gwdm||gwsbsj=?";
				int num = Integer.parseInt(dao.getOneRs(sql, new String[]{pkValue},"num"));
				if(num>0){
					request.setAttribute("cModify","yes");
				}
				
				//��ȡ��λ�����б�
				sql = "select distinct gwdm,gwmc from gwmcdmb";
				List gwmcList = dao.getList(sql, new String[]{}, new String[]{"gwdm", "gwmc"});
				request.setAttribute("gwmcList", gwmcList);
			}		
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
				//����ɽ��ѧ liang �õ������ֶε���������	
					sql = "select * from jgszdshb where 1=2";
					String[] columnsNameString = dao.getColumnNameCN(dao.getColumnName(sql), "jgszdshb");
					//String copyNames = 
					List<HashMap<String,String>> columnsName = new ArrayList<HashMap<String,String>>(columnsNameString.length-3);
					for(int i=0;i<columnsNameString.length-3;i++) {
						HashMap<String,String> hashMapColumnName = new HashMap<String,String>();
						hashMapColumnName.put("key", "key" + i);
						hashMapColumnName.put("columnName", columnsNameString[i]);
						columnsName.add(hashMapColumnName);
					}	
					request.setAttribute("jgsshList", columnsName);
					//�������ʾ��ϸ������((˫��ҳ����ϵ�һ������)��һ��w = 800;h = 600;�Ĵ�����ʾ)
					if(doType.equalsIgnoreCase("modi")) {
						String jgsTempSQLString = "select * from jgszdshb where GWDM||GWSBSJ='" + pkValue + "'";
						String[] outputValue = {"WSCF","CXKM","QKS","DY","TY","DJSJ","NNS","NS"};
						String[] jgsTempSQL =  dao.getOneRs(jgsTempSQLString, new String[] {}, outputValue);
						request.setAttribute("jgsTempSQL", jgsTempSQL);
						request.setAttribute("jgsTempSQLNum", jgsTempSQL==null ? "0" : jgsTempSQL.length);
						QgzxDao QgzxDAO = new QgzxDao();
						if(QgzxDAO.isJobOk(pkValue)) {
						//�жϸø�λ�Ƿ��Ѿ�ͨ��
							request.setAttribute("cModify", "1");
						}	
					}
				}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				//�人����ѧ 
				//���ö�
				List xydList = dao.getList("select distinct xyddm,xydmc from xyddmb order by xyddm", new String[]{}, new String[]{"xyddm","xydmc"});
				//רҵ
				request.setAttribute("zydmList", dao.getZyList(""));
				request.setAttribute("xydList", xydList);
			}
			request.setAttribute("jcfsList", service.queryJcfsList(true));
		}		
		
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			commForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			// ɾ������
			StandardOperation.delete(realTable, pk, pkValue,request);
			newFwd = new ActionForward("/data_search2.do?go=go&act=" + from,
					false);
		} else if (doType.equalsIgnoreCase("save")) {
			// ��������
			boolean del = false;
			if (tableName.equalsIgnoreCase("view_dwjlxx")) {
				String nd = commForm.getNd();
				String xn = commForm.getXn();
				String xq = commForm.getXq();
				String xmdm = commForm.getXmdm();
				String jzrq = commForm.getJzrq();
				String pcsj = commForm.getPcsj();
				String jlqx = commForm.getJlqx();
				String jxjxe = request.getParameter("jxjxe");
				String nwgjhdq = request.getParameter("nwgjhdq");
				String jlxxxx = request.getParameter("jlxxxx");
				String jlstj = request.getParameter("jlstj");
				String sfxyfdcjd = DealString.toGBK(request.getParameter("sfxyfdcjd"));
//				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = StandardOperation.delete(realTable, "xn||nd||xq||jlxmdm", xn+nd+xq+xmdm, request);
//				} else {
//					del = StandardOperation.delete(realTable, pk, pkValue, request);
//				}
				if (del) {
					StandardOperation.insert(realTable, new String[] {"nd","xn","xq","jlxmdm","jlxxxx","jlstj","nwgjhdq","jzrq","jlqx","pcsj","jxjxe","sfxyfdcjd"},
							new String[] { nd, xn, xq, xmdm, jlxxxx,
							jlstj, nwgjhdq, jzrq, jlqx, pcsj, jxjxe, sfxyfdcjd }, request);
					request.setAttribute("path","comm_pub.do?doType=add&tableName=view_dwjlxx&&pkValue="+xn+nd+xq+xmdm);
					newFwd = new ActionForward("/sqb/comm_pub_mid.jsp", false);
					request.setAttribute("infoSaved", "yes");
				} else {
					commForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			}
			if (tableName.equalsIgnoreCase("view_gwxx")) {// ��λ��Ϣ
				String xq = request.getParameter("xqdm");
				String gwdm = DealString.toGBK(request.getParameter("gwdm"));
				String gwxz = request.getParameter("gwxz");
				String sqdw = DealString.toGBK(request.getParameter("sqdw"));
				String sqsj = request.getParameter("sqsj");
				String gznr = DealString.toGBK(request.getParameter("gznr"));
				String fzr = DealString.toGBK(request.getParameter("fzr"));
				String zjf = request.getParameter("zjf");
				String gzksrq = request.getParameter("gzkssj");
				String gzjsrq = request.getParameter("gzjssj");
				String xyrs = request.getParameter("xyrs");
				String syknss = request.getParameter("xyknsrs");
				String jcfs = request.getParameter("jcfs");
				String jybcbz = request.getParameter("jybcbz");
				String gzsj = DealString.toGBK(request.getParameter("gzsj"));
				String lxdh = request.getParameter("lxdh");
				String sqbg = DealString.toGBK(request.getParameter("sqbg"));
				String sqdwyj = DealString.toGBK(request.getParameter("sqdwyj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String xn = request.getParameter("xn");
				String nd = request.getParameter("nd");
				String xueqi = request.getParameter("xueqi");
				String ryyq = DealString.toGBK(request.getParameter("ryyq"));
				String zcjf = DealString.toGBK(request.getParameter("zcjf"));
				String gzdd = DealString.toGBK(request.getParameter("gzdd"));
				String myqgzxsj = DealString.toGBK(request.getParameter("myqgzxsj"));
				String mssj = DealString.toGBK(request.getParameter("mssj"));
				String tsyq = DealString.toGBK(request.getParameter("tsyq"));
				String dwzp = DealString.toGBK(request.getParameter("dwzp"));
				String rylsqk = DealString.toGBK(request.getParameter("rylsqk"));
				//����Ҫ��
				String gzyd = request.getParameter("gzyd");
				String gwtsyq = request.getParameter("gwtsyq");
				
				String sqkssj = request.getParameter("sqkssj");
				String sqjssj = request.getParameter("sqjssj");
				
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
				//����ɽ��ѧ  liang
					jgsCheck = new String[11];
					for(int i=0;i<8;i++) {
						if(request.getParameter("key" + i) != null) {
							jgsCheck[i] = "1";
						}else {
							jgsCheck[i] = "0";
						}
					}
				}
		
				if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
					//����Ƽ�ѧԺ��λ���ƿ�ά���б�
					sql = "select gwmc from gwmcdmb where gwdm=?";
					gwdm = dao.getOneRs(sql, new String[]{gwdm}, "gwmc");
				}
				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
					//��������
					//�õ���λ���룬ѭ�����롡
					gwdm=request.getParameter("gwdmList");
					String[] gwdmList=gwdm.split("!!#!!");
					sqdw=request.getParameter("sqdw");
					fzr=dao.getOneRs("select lxr from yrdwdmb where yrdwdm=?", new String[]{sqdw}, "lxr");
					for(int i=1;i<gwdmList.length;i++){					
						gznr=DealString.toGBK(request.getParameter("gznr"+gwdmList[i]));
						gzsj=DealString.toGBK(request.getParameter("gzsj"+gwdmList[i]));
						gwxz=DealString.toGBK(request.getParameter("gwxz"+gwdmList[i]));
						xyrs=request.getParameter("xyrs"+gwdmList[i]);
						syknss=request.getParameter("xyknsrs"+gwdmList[i]);	
						if(request.getParameter("type")!=null && !request.getParameter("type").equalsIgnoreCase(""))
						{
							gwdm=DealString.toGBK(gwdmList[i]);
						}else{
							gwdm=dao.getOneRs("select gwmc from gwdmb where gwdm=?", new String[]{DealString.toGBK(gwdmList[i])}, "gwmc");
						}
						del = StandardOperation.delete(realTable, new String []{"gwdm","sqdw","xn","xueqi","nd"}, new String[]{gwdm,sqdw,xn,xueqi,nd}, request);
						if(del){
							StandardOperation.insert(realTable, new String[]{"gwdm","sqdw","gznr","gzsj","xyrs","syknss","fzr","xn","nd","xueqi","gwxz"},
									new String[]{gwdm,sqdw,gznr,gzsj,xyrs,syknss,fzr,xn,nd,xueqi,gwxz}, request);
						}
						request.setAttribute("path","comm_pub.do?doType=add&tableName=view_gwxx");
						newFwd = new ActionForward("/sqb/comm_pub_mid.jsp", false);
						request.setAttribute("infoSaved", "yes");
					}
				}else{	
					if(service.checkExists("gwxxb", "gwdm||gwsbsj", pkValue)){
						//�޸ĸ�λ��Ϣ
						String[] columns = {"xq", "gwxz", "sqdw", "sqsj", "gznr", "fzr",
								            "zjf", "gzksrq", "gzjsrq", "xyrs", 
								            "syknss", "jcfs", "jybcbz", "gzsj",
								            "lxdh", "sqbg", "sqdwyj", "bz", "xn",
								            "nd", "xueqi", "spbcbz", "sqsyrs",
								            "sqsyknss", "ryyq", "zcjf", "gzdd",
								            "myqgzxsj", "mssj", "tsyq", "dwzp",
								            "rylsqk", "gzyd", "gwtsyq","gwfbr"};
						String[] values = {xq,gwxz, sqdw, sqsj, gznr, fzr, 
								           zjf, gzksrq, gzjsrq, xyrs, syknss,
								           jcfs, jybcbz, gzsj, lxdh, sqbg, sqdwyj, 
								           bz, xn, nd, xueqi,jybcbz,xyrs,syknss,
								           ryyq, zcjf ,gzdd, myqgzxsj, mssj, tsyq, 
								           dwzp, rylsqk, gzyd, gwtsyq,userName};
						//�㽭��ҵ��ѧ֮��ѧԺ
						//if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
							columns = new String[]{"xq", "gwxz", "sqdw", "sqsj", "gznr", "fzr",
						            "zjf", "gzksrq", "gzjsrq", "xyrs", 
						            "syknss", "jcfs", "jybcbz", "gzsj",
						            "lxdh", "sqbg", "sqdwyj", "bz", "xn",
						            "nd", "xueqi", "spbcbz", "sqsyrs",
						            "sqsyknss", "ryyq", "zcjf", "gzdd",
						            "myqgzxsj", "mssj", "tsyq", "dwzp",
						            "rylsqk", "gzyd", "gwtsyq","gwfbr","sqkssj","sqjssj"};
							values = new String[]{xq,gwxz, sqdw, sqsj, gznr, fzr, 
							           zjf, gzksrq, gzjsrq, xyrs, syknss,
							           jcfs, jybcbz, gzsj, lxdh, sqbg, sqdwyj, 
							           bz, xn, nd, xueqi,jybcbz,xyrs,syknss,
							           ryyq, zcjf ,gzdd, myqgzxsj, mssj, tsyq, 
							           dwzp, rylsqk, gzyd, gwtsyq,userName,sqkssj,sqjssj};
					//	}
						StandardOperation.update(realTable, columns, values, pk, pkValue, request);
						HashMap<String, String> map = new HashMap<String, String>();
						//�޸ĸ�λ��������Ϣ
						if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {
							//����ɽ��ѧ liang
							String jgsString = "select GWSBSJ from gwxxb where gwdm='" + gwdm + "' order by GWSBSJ";
							List gwList = dao.getList(jgsString, new String[] {}, new String[] {"GWSBSJ"});
							String gwLastDate = ((HashMap)gwList.get(gwList.size()-1)).get("GWSBSJ").toString();
							jgsCheck[8] = gwdm;
							jgsCheck[9] = sqdw;
							jgsCheck[10] = gwLastDate; // �ø�λ�������걨ʱ��
							//pkValue = gwdm + gwLastDate;
							//����λ���������ݸı��Ҫ��ѧ��������ø�λ�ġ���λ�걨ʱ�䡿��Ϊ�ø�λ�������걨ʱ��				
							//�����������浽����ɽ�Զ���˱���
							StandardOperation.update("jgszdshb", new String[] {"WSCF","CXKM","QKS","DY","TY","DJSJ","NNS","NS","GWDM","SQDW","GWSBSJ"}, jgsCheck, pk, pkValue, request);													
						}
						//�޸ĸ�λ����ʱ����Ϣ
						if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY) || xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
							//����Ƽ�ѧԺ || �Ϻ����� �������ʱ��		
							String gwsbsj = dao.getOneRs("select gwsbsj from gwxxb where gwdm=?",new String[]{gwdm}, "gwsbsj");
							String[][] kxf = new String[7][7];
							
							StandardOperation.delete("gwgzsjb", "gwdm||gwsbsj", gwdm+gwsbsj, request);	
							map = service.getGwInfo(pk, pkValue);
							gwdm = map.get("gwdm");
							gwsbsj = map.get("gwsbsj");
							//��ҳ���ȡ����ʱ����Ϣ������ҳ���Ϲ�����ʱ��
							for(int i=0; i<7; i++){
								for(int j=0; j<7; j++){
									kxf[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j+1))==null?"0":"1";
									if(kxf[i][j]!=null && kxf[i][j].equalsIgnoreCase("1")){
										StandardOperation.insert("gwgzsjb", new String[]{"gwdm","gwsbsj","xq","sj","kxbz"}, new String[]{gwdm,gwsbsj,String.valueOf(i+1),String.valueOf(j+1),kxf[i][j]}, request);
									}
								}
							}
						}		
						//�޸��Զ�����ֶ�ֵ����Ϣ
						if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
							//��������Ժ�����Զ�����ֶε�ֵ
							XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
							List filedList = xbDao.getFiledOfZddm("001");
							String[] filedInfo = new String[filedList.size()];
							String[] value = new String[filedInfo.length];
							for(int i=0;i<filedInfo.length; i++){
								HashMap<String, String> filed = (HashMap<String, String>) filedList.get(i);
								filedInfo[i] = filed.get("zddm");
								value[i] = DealString.toGBK(request.getParameter(filedInfo[i])); 
							}
							StandardOperation.update(realTable, filedInfo, value, "gwdm||gwsbsj", pkValue, request);
						}
					}else{
						//�����λ��Ϣ
						//���ݴ�ѧ
						if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
							// --------- 2010/5/17 edit by luojw -----------
							String gwxzmc = dao.getOneValue("gwxzdmb",
									"gwxzmc", "gwxzdm", gwxz);
							if ("��ʱ��λ".equalsIgnoreCase(gwxzmc)) {
								StandardOperation.insert(realTable,
										new String[] { "xq", "gwdm", "gwxz",
												"sqdw", "sqsj", "gznr", "fzr",
												"zjf", "gzksrq", "gzjsrq",
												"xyrs", "syknss", "jcfs",
												"jybcbz", "gzsj", "lxdh",
												"sqbg", "sqdwyj", "bz", "xn",
												"nd", "xueqi", "spbcbz",
												"sqsyrs", "sqsyknss", "ryyq",
												"zcjf", "gzdd", "myqgzxsj",
												"mssj", "tsyq", "dwzp",
												"rylsqk", "gzyd", "shjg",
												"xscshjg", "yrdwsh" ,"gwfbr"},
										new String[] { xq, gwdm, gwxz, sqdw,
												sqsj, gznr, fzr, zjf, gzksrq,
												gzjsrq, xyrs, syknss, jcfs,
												jybcbz, gzsj, lxdh, sqbg,
												sqdwyj, bz, xn, nd, xueqi,
												jybcbz, xyrs, syknss, ryyq,
												zcjf, gzdd, myqgzxsj, mssj,
												tsyq, dwzp, rylsqk, gzyd, "ͨ��",
												"ͨ��", "ͨ��" ,userName }, request);
							} else {
								StandardOperation.insert(realTable,
										new String[] { "xq", "gwdm", "gwxz",
												"sqdw", "sqsj", "gznr", "fzr",
												"zjf", "gzksrq", "gzjsrq",
												"xyrs", "syknss", "jcfs",
												"jybcbz", "gzsj", "lxdh",
												"sqbg", "sqdwyj", "bz", "xn",
												"nd", "xueqi", "spbcbz",
												"sqsyrs", "sqsyknss", "ryyq",
												"zcjf", "gzdd", "myqgzxsj",
												"mssj", "tsyq", "dwzp",
												"rylsqk", "gzyd" ,"gwfbr"},
										new String[] { xq, gwdm, gwxz, sqdw,
												sqsj, gznr, fzr, zjf, gzksrq,
												gzjsrq, xyrs, syknss, jcfs,
												jybcbz, gzsj, lxdh, sqbg,
												sqdwyj, bz, xn, nd, xueqi,
												jybcbz, xyrs, syknss, ryyq,
												zcjf, gzdd, myqgzxsj, mssj,
												tsyq, dwzp, rylsqk, gzyd,userName },
										request);
							}
							//							--------- end -----------
						} else {
							//�㽭��ҵ��ѧ֮��ѧԺ
							StandardOperation.insert(realTable, 
									new String[]{"xq", "gwdm", "gwxz", "sqdw",
									             "sqsj", "gznr", "fzr", "zjf",
									             "gzksrq","gzjsrq","xyrs", "syknss",
									             "jcfs", "jybcbz", "gzsj", "lxdh",
									             "sqbg", "sqdwyj", "bz", "xn", "nd",
									             "xueqi", "spbcbz", "sqsyrs", 
									             "sqsyknss", "ryyq", "zcjf", "gzdd",
									             "myqgzxsj", "mssj", "tsyq", "dwzp",
									             "rylsqk", "gwtsyq", "gwfbr","sqkssj","sqjssj"}, 
									new String[] { xq, gwdm, gwxz, sqdw, sqsj, 
									               gznr, fzr, zjf, gzksrq, gzjsrq, 
									               xyrs, syknss, jcfs, jybcbz, gzsj, 
									               lxdh, sqbg, sqdwyj, bz, xn, nd,
									               xueqi, jybcbz, xyrs, syknss, ryyq,
									               zcjf, gzdd, myqgzxsj, mssj, tsyq,
									               dwzp, rylsqk, gwtsyq,userName,sqkssj,sqjssj}, 
						     request);
						}
//						else {
//							//����ѧУ
//							StandardOperation.insert(realTable, 
//									new String[]{"xq", "gwdm", "gwxz", "sqdw",
//									             "sqsj", "gznr", "fzr", "zjf",
//									             "gzksrq","gzjsrq","xyrs", "syknss",
//									             "jcfs", "jybcbz", "gzsj", "lxdh",
//									             "sqbg", "sqdwyj", "bz", "xn", "nd",
//									             "xueqi", "spbcbz", "sqsyrs", 
//									             "sqsyknss", "ryyq", "zcjf", "gzdd",
//									             "myqgzxsj", "mssj", "tsyq", "dwzp",
//									             "rylsqk", "gwtsyq", "gwfbr"}, 
//									new String[] { xq, gwdm, gwxz, sqdw, sqsj, 
//									               gznr, fzr, zjf, gzksrq, gzjsrq, 
//									               xyrs, syknss, jcfs, jybcbz, gzsj, 
//									               lxdh, sqbg, sqdwyj, bz, xn, nd,
//									               xueqi, jybcbz, xyrs, syknss, ryyq,
//									               zcjf, gzdd, myqgzxsj, mssj, tsyq,
//									               dwzp, rylsqk, gwtsyq,userName}, 
//						     request);
//						}
						String gwsbsj = dao.getOneRs("select max(to_number(gwsbsj))gwsbsj from gwxxb where gwdm=?", new String[]{gwdm}, "gwsbsj");
						pkValue = gwdm+gwsbsj;
						if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {
							//����ɽ��ѧ liang
							String jgsString = "select GWSBSJ from gwxxb where gwdm='" + gwdm + "' order by GWSBSJ";
							List gwList = dao.getList(jgsString, new String[] {}, new String[] {"GWSBSJ"});
							String gwLastDate = ((HashMap)gwList.get(gwList.size()-1)).get("GWSBSJ").toString();
							jgsCheck[8] = gwdm;
							jgsCheck[9] = sqdw;
							jgsCheck[10] = gwLastDate; // �ø�λ�������걨ʱ��
							//pkValue = gwdm + gwLastDate;
							//����λ���������ݸı��Ҫ��ѧ��������ø�λ�ġ���λ�걨ʱ�䡿��Ϊ�ø�λ�������걨ʱ��				
							//�����������浽����ɽ�Զ���˱���
							StandardOperation.insert("jgszdshb", new String[] {"WSCF","CXKM","QKS","DY","TY","DJSJ","NNS","NS","GWDM","SQDW","GWSBSJ"}, jgsCheck, request);
							//�Ƚϸ�λǰ������������û�з����仯,�Ա��Զ��ı����ѧ���ı�־λ��״̬							
						}
						HashMap<String, String> map = new HashMap<String, String>();
//						save working hours
						map = dao.getMap("select gwdm,gwsbsj from gwxxb order by gwsbsj desc", new String[]{}, new String[]{"gwdm","gwsbsj"});
						if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY) || xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
							//����Ƽ�ѧԺ || �Ϻ����� �������ʱ��		
							dao.getOneRs("select gwsbsj from gwxxb where gwdm=?",new String[]{gwdm}, "gwsbsj");
							String[][] kxf = new String[7][7];
							
							StandardOperation.delete("gwgzsjb", "gwdm||gwsbsj", gwdm+gwsbsj, request);							
							gwdm = map.get("gwdm");
							gwsbsj = map.get("gwsbsj");
							//��ҳ���ȡ����ʱ����Ϣ������ҳ���Ϲ�����ʱ��
							for(int i=0; i<7; i++){
								for(int j=0; j<7; j++){
									kxf[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j+1))==null?"0":"1";
									if(kxf[i][j]!=null && kxf[i][j].equalsIgnoreCase("1")){
										StandardOperation.insert("gwgzsjb", new String[]{"gwdm","gwsbsj","xq","sj","kxbz"}, new String[]{gwdm,gwsbsj,String.valueOf(i+1),String.valueOf(j+1),kxf[i][j]}, request);
									}
								}
							}
						}		
						if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
							//��������Ժ�����Զ�����ֶε�ֵ
							XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
							List filedList = xbDao.getFiledOfZddm("001");
							String[] filedInfo = new String[filedList.size()];
							String[] value = new String[filedInfo.length];
							for(int i=0;i<filedInfo.length; i++){
								HashMap<String, String> filed = (HashMap<String, String>) filedList.get(i);
								filedInfo[i] = filed.get("zddm");
								value[i] = DealString.toGBK(request.getParameter(filedInfo[i])); 
							}
							StandardOperation.update(realTable, filedInfo, value, "gwdm||gwsbsj", map.get("gwdm")+map.get("gwsbsj"), request);
						}
					}					
						
					request.setAttribute("path","comm_pub.do?doType=add&tableName=view_gwxx&pkValue="+pkValue);
					newFwd = new ActionForward("/sqb/comm_pub_mid.jsp");
					request.setAttribute("infoSaved", "yes");
				}
			}
		} else {
			// ����ҳ��			
			sql = "select * from " + tableName + " where " + pk + "='" + pkValue + "'";
			String[] colList = dao.getColumnName(sql);
			String[] record = dao.getOneRs(sql, new String[] {}, colList);
			if (record == null) {
				record = new String[colList.length];
			}
			HashMap<String, String> map = new HashMap<String, String>();
			if (realTable.equalsIgnoreCase("dwjlxxb")) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), record[i]);
				}
				map.put("xmdm", map.get("jlxmdm"));
				if (doType.equalsIgnoreCase("modi")) {
					if (map.get("jxjxe") == null || map.get("jxjxe").equals(null)){
						map.put("zgje", "");
					}else {map.put("zgje", map.get("jxjxe").toString());}
					if (map.get("jlstj") == null || map.get("jlstj").equals(null)){
						map.put("jltj", "");
					}else {
						map.put("jltj", map.get("jlstj").toString());}
					if (map.get("jlxxxx") == null || map.get("jlxxxx").equals(null)){
						map.put("jlxx", "");
					}else {map.put("jlxx", map.get("jlxxxx").toString());}
					if (map.get("nwgjhdq") == null || map.get("nwgjhdq").equals(null)){
						map.put("qwdq", "");
					}else {map.put("qwdq", map.get("nwgjhdq").toString());}
					if (map.get("jlxmdm") == null || map.get("jlxmdm").equals(null)){
						map.put("xmdm", "");
					}else {map.put("xmdm", map.get("jlxmdm").toString());}
					if (map.get("dwjlfsmc") == null || map.get("dwjlfsmc").equals(null)){
						map.put("jlfs", "");
					}else{
						map.put("jlfs", map.get("dwjlfsmc").toString());}
					if (map.get("dwjllbmc") == null || map.get("dwjllbmc").equals(null)){
						map.put("jllb", "");
					}else{
						map.put("jllb", map.get("dwjllbmc").toString());}
				} 
				List xmdmList = null;
				sql = "select dwjlxmdm xmdm,dwjlxmmc xmmc from dwjlxmdmb";
				xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });

				String xmdm = request.getParameter("xmdm");
				
				if ((xmdm != null) && !xmdm.equalsIgnoreCase("")) {
					map.put("xmdm", xmdm == null ? "" : xmdm);
					map.put("xn", (request.getParameter("xn") == null) ? ""
							: request.getParameter("xn"));
					map.put("nd", (request.getParameter("nd") == null) ? ""
							: request.getParameter("nd"));
					map.put("xq", (request.getParameter("xq") == null) ? ""
							: request.getParameter("xq"));					
					map.put("jzrq", (request.getParameter("jzrq") == null) ? ""
							: request.getParameter("jzrq"));
					map.put("pcsj", (request.getParameter("pcsj") == null) ? ""
							: request.getParameter("pcsj"));
					map.put("zgje", (request.getParameter("zgje") == null) ? ""
							: request.getParameter("zgje"));
					map.put("jlqx", (request.getParameter("jlqx") == null) ? ""
							: request.getParameter("jlqx"));
					map.put("qwdq", (request.getParameter("qwdq") == null) ? ""
							: DealString.toGBK(request.getParameter("qwdq")));
					map.put("jlxx", (request.getParameter("jlxx") == null) ? ""
							: DealString.toGBK(request.getParameter("jlxx")));
					map.put("jltj", (request.getParameter("jltj") == null) ? ""
							: DealString.toGBK(request.getParameter("jltj")));
					sql = "select b.dwjllbmc from dwjlxmdmb a left join dwjlfldmb b on a.dwjllbdm=b.dwjllbdm where a.dwjlxmdm=?";
					String[] tmp1 = dao.getOneRs(sql, new String[] { xmdm },
							new String[] { "dwjllbmc" });
					map.put("dwjllbmc", tmp1[0]);
					sql = "select b.dwjlfsmc from dwjlxmdmb a left join dwjlfsdmb b on a.dwjlfsdm=b.dwjlfsdm where a.dwjlxmdm=?";
					String[] tmp2 = dao.getOneRs(sql, new String[] { xmdm },
							new String[] { "dwjlfsmc" });
					map.put("dwjlfsmc", tmp2[0]);
//					map.putAll(dao.getMap(sql, new String[]{xmdm}, new String[]{"dwjllbmc", "dwjlfsmc"}));
				}
				request.setAttribute("xmdmList", xmdmList);
			} else if (realTable.equalsIgnoreCase("gwxxb")) {
				QgzxDao qgzxDao = new QgzxDao();				
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), record[i]);
				}
//				map.put("xyknsrs", map.get("syknss"));
//				map.put("gzkssj", map.get("gzksrq"));
//				map.put("gzkssj", map.get("gzksrq"));
				if(map.get("xq") != null)map.put("xqdm", map.get("xq").toString());
				if(map.get("gzksrq") != null)map.put("gzkssj", map.get("gzksrq").toString());
				if(map.get("gzjsrq") != null)map.put("gzjssj", map.get("gzjsrq").toString());
				if(map.get("syknss") != null)map.put("xyknsrs", map.get("syknss").toString());
				if (doType.equalsIgnoreCase("modi")) {
					
					if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
						//����Ƽ�ѧԺ						
						sql = "select gwdm from gwmcdmb where gwmc=?";
						if(map.get("gwdm") != null)map.put("gwdm",dao.getOneRs(sql, new String[]{map.get("gwdm")}, "gwdm"));
					}
					map.put("knsbl", service.getSqsjInfo().get("knsbl"));
				}
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
					//��������					
					if(doType.equalsIgnoreCase("modi")){
						sql = "select * from view_gwxx where gwdm||gwsbsj=?";
						HashMap<String, String> rs=dao.getMap(sql, new String[]{pkValue}, new String[]{"gwdm","sqdw","yrdwmc","gznr","gzsj","xyrs","syknss","fzr","gwxz","gwsbsj"});
						request.setAttribute("rs", rs);
						request.setAttribute("sqdw", map.get("sqdw"));
						request.setAttribute("showmodi", "showmodi");
					}else{
						List gwList=dao.getList("select * from gwdmb",new String[]{}, new String[]{"gwdm","gwmc"});
						request.setAttribute("gwList", gwList);
					}
				}
				//�㽭��ҵ��ѧ֮��ѧԺ
				//if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
					String sjSql = "select xn,nd,xq, kssj,strtodatetime(substr(kssj,1,8)) kssj1,"
						+ "substr(kssj,9,2) kssj2," + "substr(kssj,11,2) kssj3,"
						+ "substr(kssj,13,2) kssj4,"
						+ "jssj,strtodatetime(substr(jssj,1,8)) jssj1,"
						+ "substr(jssj,9,2) jssj2," + "substr(jssj,11,2) jssj3,"
						+ "substr(jssj,13,2) jssj4,knsbl,myzgxs,myzgbc,myzgsjfs,jfglkg," 
						+"mtzgxs,cjffsj,bkkmsxz from gwsqsjb "
						+ "where rownum=1";
					Map<String, String> sjMap = dao.getMap(sjSql, new String[]{}, new String[]{"kssj","kssj1","kssj2","kssj3","kssj4","jssj","jssj1","jssj2","jssj3","jssj4"});
					request.setAttribute("sqsjRs", sjMap);
				//}
				
				// ���������ʦ��ѧԺ��λ��Ϣȡ�Դ���ά��
				if(xxdm.equalsIgnoreCase(Globals.XXDM_XYSFXY)){
					sql = "select gwdm,gwmc from gwmcdmb";
					List<HashMap<String, String>> gwmcList = dao.getList(sql, new String[]{}, new String[]{"gwdm", "gwmc"});
					request.setAttribute("gwmcList", gwmcList);
				}
				
				//��ȡУ�����������ţ��б�
				sql = "select * from dm_zju_xq";
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
					//��������
					sql = "select distinct bmdm dm,bmmc xqmc from ZXBZ_XXBMDM order by bmdm";
				}
				List xqdm = dao.getList(sql, new String[] {}, new String[] {"dm", "xqmc" });
				request.setAttribute("xqList1", xqdm);
				
				sql = "select * from gwsqsjb where rownum=1";
				//�ж��Ƿ����ڲ������������õ�ʱ�����
				String tag = dao.returntag(sql, new String[]{});
				if(!doType.equalsIgnoreCase("modi")){
					if ("empty".equalsIgnoreCase(tag)) {
						map.put("xn", "");
						map.put("nd", "");
						map.put("xueqi", "");
					} else {
						sql = "select xn,nd,xq,knsbl from gwsqsjb where rownum=1";
						String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {"xn", "nd", "xq", "knsbl" });
						map.put("xn", tmp[0]);
						map.put("nd", tmp[1]);
						map.put("xueqi", tmp[2]);
						map.put("knsbl", tmp[3]);
					}
				}
				//��ȡ��λ�����б�
				sql = "select * from gwxzdmb";
				List gwxz = dao.getList(sql, new String[] {}, new String[] {"gwxzdm", "gwxzmc" });
				request.setAttribute("gwxzList", gwxz);
				//��ȡ��λ��Ϣ�б�
				sql = "select * from yrdwdmb where 1=1";	
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					sql = "select a.* from yrdwdmb a where exists(select b.dwdm from dwsqsjb b where b.kssj<(select to_char(sysdate,'yyyymmddhh24miss') from dual) and b.jssj> (select to_char(sysdate,'yyyymmddhh24miss') from dual)) ";
				}
				
				if(qgzxDao.isYrdwUser(userName)){
					sql += " and dlm='" + userName + "'";
				}
				//��ȡ���뵥λ��Ϣ�б�
				List sqdwList = dao.getList(sql, new String[] {}, new String[] {"yrdwdm", "yrdwmc" });
				request.setAttribute("sqdwList", sqdwList);
				
				// ���ݴ�ѧ
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZDX)) {
					String xm = dao.getOneValue("view_yhxx", "xm", "yhm", userName);
					HashMap<String,String>hashMap=service.getGwxx(pkValue);
					String yrdwsh=hashMap.get("yrdwsh");
					if(!"ͨ��".equals(yrdwsh)){
						request.setAttribute("canWrite", "yes");
					}
					map.put("userName", userName);
					map.put("xm", xm);
				}
				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
					//��������
					String mxsbc=dao.getOneRs("select mxsbc from gwsqsjb", new String[]{}, "mxsbc");
					if(mxsbc==null || "".equalsIgnoreCase(mxsbc)){
						mxsbc="10";
						dao.runUpdate("update gwsqsjb set mxsbc=?",new String[]{mxsbc});
					}
					map.put("mxsbc", mxsbc);
				}
				String sqdw=request.getParameter("sqdw");
				if(sqdw!=null&&!sqdw.equals("")&&!xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){					
					sql="select lxr,lxdh from yrdwdmb where yrdwdm=?";			
					String[] tmp = dao.getOneRs(sql, new String[] { sqdw }, new String[] {"lxr", "lxdh" });
					if(tmp!=null && tmp.length>0){
					map.put("lxr", tmp[0]);
					map.put("lxdh", (tmp[1]!=null && !tmp[1].equalsIgnoreCase(""))?tmp[1]:"");
					map.put("sqdw",sqdw);
					}
				}				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
					//����Ƽ�ѧԺ 
					String[] sj = { "�����ޣ�7:30��8:20��", "��1-2�ڣ�8:30��10:05��",
							"��3-4�ڣ�10:25��12:00��", "���ݣ�12:00��13:45��",
							"��5-6�ڣ�13:50��15:25��", "��7-8�ڣ�15:30��17:05��",
							"�����ޣ�17:50��20:15��" };					
					List<HashMap> kxList = new ArrayList<HashMap>();
					pk = request.getParameter("pk");
					pkValue =  DealString.toGBK(request.getParameter("pkValue"));
					if (pkValue != null && !pkValue.equalsIgnoreCase("")) {	
						//�����޸Ĳ�ѯ
						sql = "select to_number(xq-1) xq, sj, kxbz from gwgzsjb where gwdm||gwsbsj = ? and kxbz=1 order by xq,sj ";
						
						List kxbz= dao.getList(sql, new String[]{pkValue}, new String[]{"xq","sj","kxbz"});
						
						request.setAttribute("kxbz", kxbz);
						request.setAttribute("kxbzNum", kxbz.size());
					}
					for (int i = 0; i < 7; i++) {
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}
					request.setAttribute("whkxList", kxList);
				}
				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					//�Ϻ����̼�����ѧ
					String[] sj = { "��1-2��",
							"��3-4��", "����",
							"��5-6��", "��7-8��",
							"����" };
					List<HashMap> kxList = new ArrayList<HashMap>();
					pk = request.getParameter("pk");
					pkValue =  DealString.toGBK(request.getParameter("pkValue"));
					if (pkValue != null && !pkValue.equalsIgnoreCase("")) {	
						//�����޸Ĳ�ѯ
						sql = "select to_number(xq-1) xq, sj, kxbz from gwgzsjb where gwdm||gwsbsj = ? and kxbz=1 order by xq,sj ";
						
						List kxbz= dao.getList(sql, new String[]{pkValue}, new String[]{"xq","sj","kxbz"});
						
						request.setAttribute("kxbz", kxbz);
						request.setAttribute("kxbzNum", kxbz.size());
					}
					for (int i = 0; i < 6; i++) {
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}
					request.setAttribute("whkxList", kxList);
				}
				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					//��������Ժ�Զ����ֶ�
					XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
					List  filedList = xbDao.getFiledInfo("001");
					request.setAttribute("filedList", filedList);
 					request.setAttribute("rsNum", filedList.size());
				}
			}
			
			String a = request.getQueryString();
			String b = request.getRequestURL().toString();	    
			b = b.substring(b.lastIndexOf('/'), b.length());
			b = b + "?" + a;

//			if(power == 1){
//				request.setAttribute("writeAble", power);
//			}
			if(request.getParameter("details") != null && request.getParameter("details").equalsIgnoreCase("details")){
				request.setAttribute("details", "details");
			}
			request.setAttribute("url", b);
			request.setAttribute("pkValue", pkValue);// ���ͱ�����
			request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
			request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
			request.setAttribute("rs", map);
			request.setAttribute("doType", doType);
			request.setAttribute("xxdm",xxdm);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				//��ɳ����
				//�õ���λ���������̶�����[�����ڡ���λ����ʱ����]
				String tempSql = "select * from gwsqsjb";
				String[] gwgdrs = dao.getOneRs(tempSql, new String[] {}, new String[] {"KNSBL"});
				request.setAttribute("xyknsrs", gwgdrs[0]);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				//����ְҵ
				request.setAttribute("zdy", "zdy");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && realTable.equalsIgnoreCase("gwxxb")){
				//��������
				request.setAttribute("doType",doType );
				newFwd= new ActionForward("/qgzx/ynys/qgzx_ynys_gwxxb.jsp",false);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY) && realTable.equalsIgnoreCase("gwxxb")){
				//��������Ժ
				newFwd = new ActionForward("/qgzx/xbemy/" + realTable + ".jsp",false);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) && realTable.equalsIgnoreCase("gwxxb")){
				//���Ϲ�ҵ��ѧ
				newFwd = new ActionForward("/qgzx/hngydx/qgzx_hngydx_gwxxb.jsp",false);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX) && !realTable.equalsIgnoreCase("dwjlxxb")){
				//��������
				if(doType != null && "modi".equalsIgnoreCase(doType) && "gwxxb".equalsIgnoreCase(realTable)){
					newFwd = new ActionForward("/qgzx/bjlhdx/modiGwxxb.jsp",false); 
				}else{
					newFwd=new ActionForward("/bjlhdx/qgzx/"+realTable + ".jsp",false);
				}
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX) && realTable.equalsIgnoreCase("gwxxb")){
				//�人��
				newFwd = new ActionForward("/qgzx/whlgdx/gwxxb.jsp",false);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) && "gwxxb".equalsIgnoreCase(realTable)){
				//�Ϻ����̼�����ѧ��λ��Ϣ����
				newFwd = new ActionForward("/qgzx/shgc/shgc_gwxxb.jsp",false);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)&& "gwxxb".equalsIgnoreCase(realTable)){
				//�㽭��ý
				request.setAttribute("timeFlag", service.getSqsjFlag());				
				newFwd = new ActionForward("/qgzx/zjcm/zjcm_gwxxb.jsp",false);
			}else if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm) && "gwxxb".equalsIgnoreCase(realTable)){
				//������һ
				newFwd = new ActionForward("/qgzx/nbty/nbty_gwxxb.jsp", false);
			}else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) && "gwxxb".equalsIgnoreCase(realTable)) {
				//���ݴ�ѧ
				newFwd = new ActionForward("/qgzx/gzdx/gzdx_gwxxb.jsp", false);
			} else{
				newFwd = new ActionForward("/sjcz/" + realTable + ".jsp", false);
			}
		}
		return newFwd;
	}
		

	private ActionForward dataSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		HttpSession session = request.getSession();
		// ͨ�õ����ݲ���ģ��
		CommanForm dataSearchForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		QgzxService qgzxService = new QgzxService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userType= session.getAttribute("userType").toString();
		String userTP= session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep=session.getAttribute("userDep").toString();
		String[] colList = null;
		String[] colListCN = null;
		String[] tmp=null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String dataType = request.getParameter("act");	
		String xxdm = StandardOperation.getXxdm();
		String nd = dataSearchForm.getNd();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		String gwfbr = request.getParameter("gwfbr");
		String yrdwdm = dataSearchForm.getYrdwdm();
		String type = request.getParameter("type");
		String page = "success";
		
		if (dataType == null) {
			dataType = "";
		}
		if ((nd == null) || nd.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.nd = '" + nd + "' ";
		}
		if ((xn == null) || xn.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and a.xn = '" + xn + "' ";
		}		
		if(StringUtils.isNotNull(gwfbr)){
			querry += " and a.gwfbr='" + userName + "'";
		}
		if (dataType.equalsIgnoreCase("comm")) {
			if ((xq == null) || xq.equalsIgnoreCase("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and a.xq = '" + xq + "' ";
			}
			realTable = "dwjlxxb";
			pk = "xn||nd||xq||jlxmdm";
			tips = "���⽻�� - ��Ϣ���� - ��Ϣ��ѯ";
			tableName = "view_dwjlxx";
			colList = new String[] {"r", "����", "nd", "xn", "xqmc", "dwjlxmmc",
					"dwjllbmc", "dwjlfsmc", "nwgjhdq", "pcsj", "sfxyfdcjd" };
			sql ="select distinct jlxmdm from dwjlxxb";
			List xmdmList = dao.getList(sql, new String[]{}, new String[]{"JLXMDM".toLowerCase()});
			request.setAttribute("xmdmList", xmdmList);
		} else if (dataType.equalsIgnoreCase("work")||dataType.equalsIgnoreCase("workPayPut")) {
			if(StringUtils.isNotNull(dataType) && StringUtils.isNotNull(type) && dataType.equalsIgnoreCase("workPayPut") && type.equalsIgnoreCase("audit") && Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
				//�㽭��ýѧԺ��𷢷����
				return new ActionForward("/qgzxcjff.do?method=cjffAudiSearch");
			}
			HngydxGwglDAO sdao = new HngydxGwglDAO();			
			page =  dataType.equalsIgnoreCase("work") ? "gwcx" : page;
			realTable = "gwxxb";
			pk = "gwdm||gwsbsj";
			tips = "�ڹ���ѧ - ��λ���� - ��λ��ѯ";			
			tableName = "view_gwxx";
			sql = "select distinct gwdm from gwxxb a where 1=1 ";
			String gwdm=DealString.toGBK(dataSearchForm.getGwdm());
			String gwxz = dataSearchForm.getGwxz();
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����
				tips = "ѧ���幤 - ��λ���� - ��λ��ѯ";
			}	
			if(qgzxService.isYrdwUser(userName) && StringUtils.isNull(yrdwdm)){
				//���˵�λֻ�ܲ�ѯ�����˵�λ����Ϣ
				yrdwdm = qgzxService.getYrdwUser(userName);
				dataSearchForm.setYrdwdm(yrdwdm);
			}
			if (StringUtils.isNotNull(yrdwdm)) {
				querry += "and a.sqdw = '" + yrdwdm + "' ";
			}
			if (!Base.isNull(xq)) {
				querry += "and a.xueqi = '" + xq + "' ";
			}
			if (!Base.isNull(gwdm)) {
				querry += "and a.gwdm like '%" + DealString.replaceImmitStr(gwdm) + "%' ";
			}
			if (!Base.isNull(gwxz)) {
				querry += "and a.gwxz = '" + gwxz + "' ";
			}	
			if (!Base.isNull(dataSearchForm.getJcfs())) {
				querry += "and a.jcfs = '" + dataSearchForm.getJcfs() + "' ";
			}
			if (!Base.isNull(dataSearchForm.getGwflag())) {
				querry += "and a.gwflag = '" + dataSearchForm.getGwflag() + "' ";
			}	
			if (!Base.isNull(dataSearchForm.getXxyj())) {
				querry += "and a.shjg = '" + dataSearchForm.getXxyj() + "' ";
			}	
			if(sdao.checkIsYrdw(userName) && 
					!(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)
					&& dataType.equalsIgnoreCase("work"))){//���˵�λֻ�ܲ�ѯ���Լ���λ�µ�ѧ��
				querry += " and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='"+userName+"')";
			}
			
			// ------2010/5/17 edit by luojw -----------
			// ���ݴ�ѧ
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
//				String userDep = request.getSession().getAttribute("userDep")
//						.toString();
//				if ("xy".equalsIgnoreCase(userType)
//						|| "stu".equalsIgnoreCase(userType)) {
//					querry += " and exists (select 1 from qgzx_xyrsb b where a.gwdm = b.gwdm and a.gwsbsj = b.gwsbsj and b.xydm = '"
//							+ userDep + "'  and b.fprs is not null and b.fprs > 0)";
//				}
				if(StringUtils.isNull(gwfbr)){
					querry += " and (gwfbr<>'" + userName + "'  or gwfbr is null)";
				}
			}
			// ------end-----------
			
			if(dataType.equalsIgnoreCase("work")){//�ڹ���ѧ	ѧ�������λ��Ϣ��ѯ		
				//pksbl�ֶ� ��ͨ���������ģ��޸Ĵ˴�ʱ��ע�ⲻ��Ӱ��ƶ���������ļ���
				colList = new String[] { "����", "nd", "xn", "xueqimc", "xqmc", "gwdm",
						"yrdwmc", "gwxzmc", "xyrs", "sqsyrs","jcfsmc","ƶ��������","shjg","sfyx" };
				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){//��������
					colList = new String[] { "����", "xn", "xueqimc", "gwdm",
							"yrdwmc", "xyrs","syknss","sqsyrs","sqsyknss", "shjg","sfyx" };
				} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					if ("xx".equals(userType) || "admin".equals(userType)) {
						colList = new String[] { "����","disabled", "nd", "xn", "xueqimc", "gwdm",
								"yrdwmc", "xyrs", "sqsyrs","yrdwsh","shjg" };
					} else {
						colList = new String[] { "����","disabled", "nd", "xn", "xueqimc", "gwdm",
								"yrdwmc", "sqsyrs","yrdwsh","shjg" };
					}
				}else if(Globals.XXDM_MJXY.equalsIgnoreCase(Base.xxdm)){
					colList = new String[] { "����", "nd", "xn", "xueqimc", "gwdm", "gwxzmc",
							"yrdwmc", "xyrs", "jcfsmc", "shjg","sfyx" };
				}else if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm)){//�㽭��ҵ��ѧ֮��ѧԺ
					colList = new String[] { "����", "xn", "xueqimc", "gwdm",
							"yrdwmc", "gwxzmc", "xyrs","sqsyrs","sqrs","lyrs","jcfsmc","ƶ��������","shjg","sfyx" };
				}
				if(userType.equalsIgnoreCase("stu")){//ѧ���û�
					colList = new String[] { "����", "xn", "xueqimc", "gwdm",
							"yrdwmc", "gwxzmc", "xyrs", "sqsyrs","jcfsmc","sfyx" };
					if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
						colList = new String[] { "����", "xn", "xueqimc", "xqmc", "gwdm",
								"yrdwmc", "gwxzmc", "xyrs", "jcfsmc","sfyx" };
					}
				}
				
			}else{//��𷢷�
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){//��������
					colList = new String[] { "����", "nd", "xn", "xueqimc","gwdm",
						"yrdwmc", "gwxzmc", "xyrs", "spbcbz","tmpsqsyrs","xymc" };
				}else{
					colList = new String[] { "����", "nd", "xn", "xueqimc", "xqmc", "gwdm",
						"yrdwmc", "gwxzmc", "xyrs", "spbcbz","sqsyrs" };
				}
				sql += " and shjg='ͨ��'";
				querry += " and shjg='ͨ��' ";
				if(!userTP.equals("admin"))
				{	
					userName = session.getAttribute("userName").toString();					
					sql +=" and exists (select yrdwdm from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
					querry += " and exists (select yrdwdm from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='" + userName + "')";
				}
			}
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){//�㽭���ڹ���ѧ
				request.setAttribute("gwcxview","1");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				//�人����ѧ ֻ�����˵�λ�ܹ���д�굥				
				if(sdao.checkIsYrdw(userName)){
					request.setAttribute("isYrdw", "yes");
				}
			}			
			//��ѯ��ǰ����
			sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
			tmp = dao.getOneRs(sql, new String[] {}, new String[] { "time" });
			String day = tmp[0].substring(8);			
			request.setAttribute("day", day);//��ǰ����
			
			//��ѯ��ǰ�û������ĸ�λ�����б�
			HashMap<String,String> paramter = new HashMap<String, String>();
			paramter.put("xn",xn);
			paramter.put("nd", nd);
			paramter.put("xq", xq);
			paramter.put("userName", userName);
			paramter.put("gwfbr", userName);
			paramter.put("shFlag","false");//�����ͨ������
			request.setAttribute("brfbgwList", qgzxService.getGwmcxxList(paramter,"no"));
			
			paramter = new HashMap<String, String>();
			paramter.put("xn",xn);
			paramter.put("nd", nd);
			paramter.put("xq", xq);
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				//���ݴ�ѧ
				paramter.put("gwfbr", userName);
			}
			if(qgzxService.isYrdwUser(userName)){
				paramter.put("yrdwdm", dataSearchForm.getYrdwdm());
			}
			paramter.put("userName", userName);
			paramter.put("shFlag","false");//�����ͨ������
			request.setAttribute("gwList", qgzxService.getFgwfbrGwmcxxList(paramter,"yes"));
			request.setAttribute("yf",tmp[0].substring(5,7));	//end ���������ڹ���ѧ�·�ȡ��ǰ �·�    
//			request.setAttribute("gwList", qgzxService.getNotUserGwList(userName,dataSearchForm.getYrdwdm(),dataSearchForm.getGwxz(),false));//���з�����λ�б�
			
			request.setAttribute("yrdwList", qgzxService.getYrdwList(userName));//���˵�λ�б�
			request.setAttribute("gwxzList", qgzxService.getGwxzCjList());//��λ����
			QgzxGwfbService gwfbService = new QgzxGwfbService();
			request.setAttribute("jcfsList",gwfbService.getSelectData("jcfs"));//�Ƴ귽ʽ
			//����б�
			request.setAttribute("chkList", qgzxService.getChkList(3));
		} else if(dataType.equalsIgnoreCase("hzjy")){//����������Ŀ����
			tips = new String("��ǰ����λ�ã��������� - ��������׼�� - ��Ŀ����");
			pk = request.getParameter("xmdm||xn||xq");
			tableName = request.getParameter("hzjyxmb");

		} else {
			dataSearchForm.setErrMsg("err");
			return mapping.findForward("false");
		}

		//��λ��Ϣ��ѯ
		if(dataType.equalsIgnoreCase("work")){
			String sfgq = request.getParameter("sfgq");//�Ƿ����
			if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm) && request.getParameter("go") == null){
				dataSearchForm.setSfgq("yx");
			}
			String sfkx = request.getParameter("sfkx");//�Ƿ����
			if(sfkx != null && "kx".equalsIgnoreCase(sfkx)){
				querry += " and sqsyrs>nvl((select count(*) num from xsgwxxb b where b.gwdm||b.gwsbsj=a.gwdm||a.gwsbsj and xxyj='ͨ��'),0)";
			}else if(sfkx != null && "bkx".equalsIgnoreCase(sfkx)){
				querry += " and sqsyrs=nvl((select count(*) num from xsgwxxb b where b.gwdm||b.gwsbsj=a.gwdm||a.gwsbsj and xxyj='ͨ��'),0)";
			}
			
			if(sfgq != null && "yx".equalsIgnoreCase(sfgq)){
				//��Ч��λ
				if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ
					querry += " and gzjsrq>to_char(sysdate,'yyyymmdd')";
				}else if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm)){ //�㽭��ҵ��ѧ֮��ѧԺ
					querry += " and gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��' and sqkssj<=to_char(sysdate,'YYYYMMDDHH24MISS') and sqjssj>=to_char(sysdate,'YYYYMMDDHH24MISS') ";
				}else{
					querry += " and gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��'";
				}
				sql = "select rownum r, " + pk + " ����,xq,gwdm,gwxz,sqdw,sqsj,gznr,gzsj,gzksrq,gzjsrq,xyrs,syknss,jybcbz,zjf,sqbg,fzr,lxdh,sqdwyj,qgbyj,xgbyj,shjg,spbcbz,sqsyrs,sqsyknss,gwsxbj,bz,xn,nd,xueqi,jcfs,gwsbsj,ryyq,tmpsqsyrs,tmpsqsyknss,dwdz,gwsl,xygirl,xyboy,gwtsyq,xscshjg,zcjf,jcfsmc,'��Ч' sfyx,gzdd,msdd,mssjd,rzyq_nj,rzyq_xb,rzyq_zy,rzyq_zymc,rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,mtbzgz,dqbzgz,xyddm,xydmc,xymc,yrdwlxdh,gwxzmc,xqmc,yrdwmc,xydm,xueqimc,yrdwsh,myqgzxsj,mssj,tsyq,rylsqk,dwzp,'' ƶ��������  from " + tableName + " a" + querry;
			}else if(sfgq != null && "gq".equalsIgnoreCase(sfgq)){
				//���ڸ�λ
				if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm)){//�㽭��ҵ��ѧ֮��ѧԺ
					querry += " and (gzjsrq<to_char(sysdate,'yyyymmdd') or shjg<>'ͨ��' or sqkssj>to_char(sysdate,'YYYYMMDDHH24MISS') or sqjssj<to_char(sysdate,'YYYYMMDDHH24MISS'))";
				}else{
					querry += " and (gzjsrq<to_char(sysdate,'yyyymmdd') or shjg<>'ͨ��')";
				}
				sql = "select rownum r, " + pk + " ����,xq,gwdm,gwxz,sqdw,sqsj,gznr,gzsj,gzksrq,gzjsrq,xyrs,syknss,jybcbz,zjf,sqbg,fzr,lxdh,sqdwyj,qgbyj,xgbyj,shjg,spbcbz,sqsyrs,sqsyknss,gwsxbj,bz,xn,nd,xueqi,jcfs,gwsbsj,ryyq,tmpsqsyrs,tmpsqsyknss,dwdz,gwsl,xygirl,xyboy,gwtsyq,xscshjg,zcjf,jcfsmc,'��Ч' sfyx,gzdd,msdd,mssjd,rzyq_nj,rzyq_xb,rzyq_zy,rzyq_zymc,rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,mtbzgz,dqbzgz,xyddm,xydmc,xymc,yrdwlxdh,gwxzmc,xqmc,yrdwmc,xydm,xueqimc,yrdwsh,myqgzxsj,mssj,tsyq,rylsqk,dwzp,'' ƶ�������� from " + tableName + " a" + querry;
			}
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				if("xx".equals(userType) || "admin".equals(userType)){
					sql = "select rownum r, " + pk + " ����,xq,gwdm,gwxz,sqdw,sqsj,gznr,gzsj,gzksrq,gzjsrq,xyrs,syknss,jybcbz,zjf,sqbg,fzr,lxdh,sqdwyj,qgbyj,xgbyj,shjg,spbcbz,sqsyrs,sqsyknss,gwsxbj,bz,xn,nd,xueqi,jcfs,gwsbsj,ryyq,tmpsqsyrs,tmpsqsyknss,dwdz,gwsl,xygirl,xyboy,gwtsyq,xscshjg,zcjf,jcfsmc,(case when gzjsrq>to_char(sysdate,'yyyymmdd') then '��Ч' else '��Ч' end) sfyx,gzdd,msdd,mssjd,rzyq_nj,rzyq_xb,rzyq_zy,rzyq_zymc,rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,mtbzgz,dqbzgz,xyddm,xydmc,xymc,yrdwlxdh,gwxzmc,xqmc,yrdwmc,xydm,xueqimc,yrdwsh,myqgzxsj,mssj,tsyq,rylsqk,dwzp,(case when yrdwsh='ͨ��' then 'disabled' else '' end)disabled from " + tableName + " a " + querry;
				}else{
					sql="select rownum r, a.gwdm||a.gwsbsj ����,a.xq,a.gwdm,a.gwxz,a.sqdw,a.sqsj,a.gznr,a.gzsj,a.gzksrq,a.gzjsrq,a.xyrs,a.syknss,a.jybcbz,a.zjf,a.sqbg,a.fzr," +
					"a.lxdh,a.sqdwyj,a.qgbyj,a.xgbyj,a.shjg,a.spbcbz, b.fprs sqsyrs,a.sqsyknss,a.gwsxbj,a.bz,a.xn,a.nd,a.xueqi,a.jcfs,a.gwsbsj,a.ryyq,a.tmpsqsyrs,a.tmpsqsyknss," +
					"a.dwdz,a.gwsl,a.xygirl,a.xyboy,a.gwtsyq,a.xscshjg,a.zcjf,a.jcfsmc,(case when a.gzjsrq>to_char(sysdate,'yyyymmdd') then '��Ч' else '��Ч' end), a.sfyx,a.gzdd,a.msdd,a.mssjd," +
					"a.rzyq_nj,a.rzyq_xb,a.rzyq_zy,a.rzyq_zymc,a.rzyq_wyyq,a.rzyq_gzjn,a.rzyq_qt,a.gzmd,a.gzyd,a.gznd,a.gzjj,a.mtbzgz,a.dqbzgz,a.xyddm,a.xydmc,a.xymc,a.yrdwlxdh,a.gwxzmc,a.xqmc," +
					"a.yrdwmc,a.xydm,a.xueqimc,a.yrdwsh,a.myqgzxsj,a.mssj,a.tsyq,a.rylsqk,a.dwzp,(case when a.yrdwsh='ͨ��' then 'disabled' else '' end)disabled from " + tableName + " a left join qgzx_xyrsb b on a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj " + querry+" and a.gwdm=b.gwdm and b.xydm='"+userDep+"'";
				}
			}else if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm)){//�㽭��ҵ��ѧ֮��ѧԺ
				sql = StringUtils.joinStr("select rownum r, ",
						pk,
						" ����,xq,gwdm,gwxz,sqdw,sqsj,gznr,gzsj,gzksrq,gzjsrq,",
						"xyrs,syknss,jybcbz,zjf,sqbg,fzr,lxdh,sqdwyj,qgbyj,",
						"xgbyj,shjg,spbcbz,sqsyrs,sqsyknss,gwsxbj,bz,xn,nd,",
						"xueqi,jcfs,gwsbsj,ryyq,tmpsqsyrs,tmpsqsyknss,dwdz,",
						"gwsl,xygirl,xyboy,gwtsyq,xscshjg,zcjf,jcfsmc,",
						"(case when gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��' and sqkssj<=to_char(sysdate,'YYYYMMDDHH24MISS') and sqjssj>=to_char(sysdate,'YYYYMMDDHH24MISS') then '��Ч' else '��Ч' end) sfyx,",
						"gzdd,msdd,mssjd,rzyq_nj,rzyq_xb,rzyq_zy,rzyq_zymc,",
						"rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,",
						"mtbzgz,dqbzgz,xyddm,xydmc,xymc,yrdwlxdh,gwxzmc,",
						"xqmc,yrdwmc,xydm,xueqimc,yrdwsh,myqgzxsj,mssj,tsyq,",
						"rylsqk,dwzp,'' ƶ��������,nvl(b.sqrs,0) sqrs,nvl(c.lyrs,0) lyrs from ",
						tableName,
						" a  left join (select count(*) sqrs,gwdm||gwsbsj gwdmsbsj from xsgwxxb b group by gwdm||gwsbsj) b on a.gwdm||a.gwsbsj=b.gwdmsbsj ",
						" left join (select count(*) lyrs,gwdm||gwsbsj gwdmsbsj from xsgwxxb b where xxyj='ͨ��' group by gwdm||gwsbsj) c on a.gwdm||a.gwsbsj=c.gwdmsbsj ",
						querry);
			}else{
				sql = StringUtils.joinStr("select rownum r, ",
						pk,
						" ����,xq,gwdm,gwxz,sqdw,sqsj,gznr,gzsj,gzksrq,gzjsrq,",
						"xyrs,syknss,jybcbz,zjf,sqbg,fzr,lxdh,sqdwyj,qgbyj,",
						"xgbyj,shjg,spbcbz,sqsyrs,sqsyknss,gwsxbj,bz,xn,nd,",
						"xueqi,jcfs,gwsbsj,ryyq,tmpsqsyrs,tmpsqsyknss,dwdz,",
						"gwsl,xygirl,xyboy,gwtsyq,xscshjg,zcjf,jcfsmc,",
						"(case when gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��' then '��Ч' else '��Ч' end) sfyx,",
						"gzdd,msdd,mssjd,rzyq_nj,rzyq_xb,rzyq_zy,rzyq_zymc,",
						"rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,",
						"mtbzgz,dqbzgz,xyddm,xydmc,xymc,yrdwlxdh,gwxzmc,",
						"xqmc,yrdwmc,xydm,xueqimc,yrdwsh,myqgzxsj,mssj,tsyq,",
						"rylsqk,dwzp,'' ƶ�������� from ",
						tableName,
						" a ",
						querry);
			}
		}else{
			sql = "select rownum r, " + pk + " ����,a.* from " + tableName + " a" + querry;
		}
		if(dataType.equalsIgnoreCase("work") && userType.equalsIgnoreCase("stu")){
			sql += " and shjg='ͨ��'";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//�㽭����
				String xydm = dao.getOneRs("select distinct xydm from view_xsjbxx where xh=?", new String[]{userName}, "xydm");
				sql += " and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and not exists(select 1 from view_njxyzybj c where b.xydm=c.xydm and c.xydm<>'"+xydm+"')) ";
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			int len = dataSearchForm.getPages().getStart()+dataSearchForm.getPages().getPageSize();
			// ------2010/5/17 edit by luojw -----------
			// ���ݴ�ѧ
			//����Ϧ��
//			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
//				StringBuffer sb = new StringBuffer();
//				sb.append("select rownum r, " + pk
//						+ " ����,xq,gwdm,gwxz,sqdw,sqsj,");
//				sb.append("gznr,gzsj,gzksrq,gzjsrq,xyrs,syknss,jybcbz,zjf,sqbg,fzr,lxdh,");
//				sb.append("sqdwyj,qgbyj,xgbyj,shjg,spbcbz,sqsyrs,sqsyknss,gwsxbj,bz,xn,nd,xueqi,");
//				sb.append("jcfs,gwsbsj,ryyq,tmpsqsyrs,tmpsqsyknss,dwdz,gwsl,xygirl,xyboy,gwtsyq,xscshjg,zcjf,jcfsmc,");
//				sb.append("(case when gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��' then '��Ч' else '��Ч' end) sfyx,");
//				sb.append("gzdd,msdd,mssjd,rzyq_nj,rzyq_xb,rzyq_zy,rzyq_zymc,rzyq_wyyq,rzyq_gzjn,rzyq_qt,gzmd,gzyd,gznd,gzjj,");
//				sb.append("mtbzgz,dqbzgz,xyddm,xydmc,xymc,yrdwlxdh,gwxzmc,xqmc,yrdwmc,xydm,xueqimc,yrdwsh,myqgzxsj,mssj,tsyq,rylsqk,dwzp ");
//				sb.append("from (select * from " + tableName + " a" + querry);
//				sb.append("union select * from " + tableName + " a where a.gwxzmc = '��ʱ��λ')");
//
//				sql = sb.toString();
//			}
			// --------end ------------
			System.out.println("select * from (select * from ("+sql+") where r<="+len
					+") where r>"+dataSearchForm.getPages().getStart());
			rs.addAll(dao.rsToVator("select * from (select * from ("+sql+") where r<="+len
					+") where r>"+dataSearchForm.getPages().getStart(), new String[] {}, colList));
			if(!Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm) && !Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm) && !"stu".equalsIgnoreCase(userType)){
				//����ƶ�������� ƶ�������� 11 ���� 0
				QgzxGwglService service = new QgzxGwglService();
				rs = service.accountGwpksbl(rs,11,0);
			}
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from ("+sql+")", new String[] {}, "count")));
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("userTP", userTP);
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("divId", request.getParameter("divId"));
		request.setAttribute("disDivId", request.getParameter("disDivId"));
		if(type!=null && type.equalsIgnoreCase("audit")){
			//�人����𷢷����
			return new ActionForward("/qgzx/whlgdx/work_pay_auditing.jsp");
		}
		return mapping.findForward(page);
	}

	private ActionForward fdyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		if (true){
			return new ActionForward("/szdw_teaInfo.do?method=teaManage",false);
		}
		
		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		String go = request.getParameter("go");
		String zwdm = request.getParameter("zxm");
		String bmdm = request.getParameter("xydm");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String yddh = DealString.toGBK(request.getParameter("yddh"));
		String xsgzyjfx = DealString.toGBK(request.getParameter("xsgzyjfx"));
		String fgnj = DealString.toGBK(request.getParameter("nj"));
		String sfyh = DealString.toGBK(request.getParameter("sfyh"));
		String xxdm = dao.getXxdm();
		String querry = " where 1=1 ";
		String pk = request.getParameter("pk");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String tableName = "fdyxxb";
		
		CommanForm comform = (CommanForm) form;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && act == null) {
			return new ActionForward("/szdw_xzcd.do", false);//fdyglstruts.xml�����ļ���
		}
		if ("xy".equalsIgnoreCase(userType)) {
			comform.setXydm(userDep);
			bmdm = userDep;
			request.setAttribute("writeAble", "no");
		}
		else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType))
		{
			request.setAttribute("writeAble", "yes");
		}
		String sql;
		pk = isNull(pk) ? " " : pk;
		if ((act != null) && act.equalsIgnoreCase("del")) {
			go = "go";
			String[] checkVal=comform.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pkKey = "zgh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName("fdyxxb");
				saveForm.setPk(pkKey);
				saveForm.setPkValue(checkVal);
				boolean res = dao.delDate(saveForm, comform);
				if (res) {
					dao.runUpdate("delete fdybjb a where not exists (select 1 from fdyxxb b where a.zgh=b.zgh)", new String[] {});
					dao.runUpdate("delete bzrbbb a where not exists (select 1 from fdyxxb b where a.zgh=b.zgh)", new String[] {});
					request.setAttribute("del", "ok");
				} else {
					request.setAttribute("del", "no");
				}
			}
		}
		ArrayList<String> inputList= new ArrayList<String>();
		if (isNull(zwdm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and zw=? ";
			inputList.add(zwdm);
		}
		if (isNull(bmdm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and bmdm=? ";
			inputList.add(bmdm);
		}
		if (isNull(xm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and xm like '%'||?||'%' ";
			inputList.add(xm);
			request.setAttribute("xm", xm);
		}
		if (isNull(yddh)) {
			querry += " and 1=1 ";
		} else {
			querry += " and yddh='" + yddh + "' ";
		}
		if (isNull(sfyh)) {
			querry += " and 1=1 ";
		} else if(sfyh.equalsIgnoreCase("��")){
			querry += " and b.yhm is null ";
		} else {
			querry += " and b.yhm is not null ";
		}
		if (isNull(xsgzyjfx)) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsgzyjfx like '%'||?||'%' ";
			inputList.add(xsgzyjfx);
			request.setAttribute("xsgzyjfx", xsgzyjfx);
		}
		if (isNull(fgnj)){
			querry += " and 1=1 ";
		} else if(fgnj.equalsIgnoreCase("no")){
			querry += " and (fgnj is null or fgnj = '') ";
		} else {
			querry += " and (fgnj = ? or qtfgnj like '%"+fgnj+"%')";
			inputList.add(fgnj);
		}
		String[] colList = new String[] { "pk","zgh","xm", "lxdh", "yddh","zyzz",
				"bmmc", "xsgzyjfx","sfyh","zmc" };
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
			colList = new String[] { "pk", "bmmc","xm","xb", "fgnj","bgdd",
					"bgdh", "yddh","sfyh","zmc" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
			colList = new String[] { "pk", "zgh", "xm", "lxdh", "yddh", "zyzz",
					"bmmc", "xsgzyjfx", "fdyzmc","sfyh","zmc"  };
		}
		String[] colListCN = dao.getColumnNameCN(colList, "view_fdyxx");
		colListCN[colListCN.length-2]="�Ƿ�ϵͳ�û�";
		colListCN[colListCN.length-1]="�����û���";
		List topTr = dao.arrayToList(colList, colListCN);
		sql = "select zwdm,zwmc from zwb";
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm",
		"zwmc" });
		
		if ("tgyhxx".equalsIgnoreCase(act)) {
			String zdm = request.getParameter("zdm");
			String dwdm = request.getParameter("dwdm");
			String[]  fdyz = comform.getCheckVal();
			String[] insertYhSql = new String[fdyz.length];
			String[] insertYhqxSql = new String[fdyz.length];
			Encrypt encrypt = new Encrypt();
			String password = encrypt.encrypt("888888");
			for(int i=0;i<insertYhSql.length;i++){
				insertYhSql[i] = "insert into yhb (yhm, xm, szbm, kl, dwdm, zdm,qx) select a.zgh,a.xm,a.bmdm,'" + password + "','"+dwdm+"','"+zdm+"','1' from fdyxxb a where a.zgh = '"+fdyz[i]+"' and not exists (select 1 from yhb where yhm = a.zgh)";
				insertYhqxSql[i]="insert into yhqxb (yhm, gnmkdm, dxq) select '"+fdyz[i]+"', a.gnmkdm, a.dxq from yhzqxb a where a.zdm = '"+zdm+"' and not exists (select 1 from yhqxb where yhm = '"+fdyz[i]+"')"; 
			}
//			boolean result = dao.runProcuder("{call proc_wxzy_szxxtgzyhb}", new String[]{});
			boolean result = true;
			int[] res = dao.runBatch(insertYhSql);

			for (int i = 0; i < res.length; i++) {
				result = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!result)
					break;
			}
			if(result){
				int[] res2 = dao.runBatch(insertYhqxSql);
				for (int i = 0; i < res2.length; i++) {
					result = (res2[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!result)
						break;
				}
			}
			
			request.setAttribute("result", result ? "true" : "false");
			go = "go";
		}
		if ((go != null) && go.equalsIgnoreCase("go")) {
			request.setAttribute("topTr", topTr);
			sql = "select rownum r,zgh pk,zgh,xm,lxdh,yddh,zyzz,bmmc,xsgzyjfx,(case when b.yhm is null then"+
				"'��' else '��' end) sfyh ,b.zmc zmc from view_fdyxx left join (select yhm, a.zdm, b.zmc from yhb a, yhzb b"+
                " where a.zdm = b.zdm) b on b.yhm =view_fdyxx.zgh ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
				sql = "select rownum r,zgh pk, bmmc,xm,xb, fgnj, bgdd,bgdh, yddh,(case when b.yhm is null then"+
                "'��' else '��' end) sfyh,b.zmc zmc from view_fdyxx left join (select yhm, a.zdm, b.zmc from yhb a, yhzb b"+
                " where a.zdm = b.zdm) b on b.yhm =view_fdyxx.zgh";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
				sql = "select rownum r,zgh pk,zgh,xm,lxdh,yddh,zyzz,bmmc,xsgzyjfx,fdyzmc(case when b.yhm is null then"+
				"'��' else '��' end) sfyh,b.zmc zmc from view_fdyxx left join (select yhm, a.zdm, b.zmc from yhb a, yhzb b"+
                " where a.zdm = b.zdm) b on b.yhm =view_fdyxx.zgh";
				String fdyz = request.getParameter("fdyz");
				if(!Base.isNull(fdyz)){
					querry += " and fdyz ='" + fdyz + "'";
				}
			}
			sql += querry;
			sql+=" order by zgh ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)) {
				String kssj = request.getParameter("kssj");
				String jssj = request.getParameter("jssj");
				if (!Base.isNull(kssj) || !Base.isNull(jssj)) {
					StringBuffer sb = new StringBuffer();
					sb.append(" and exists (select 1 from xmlg_szdw_fdygsjl b where view_fdyxx.zgh = b.zgh");
					if (!Base.isNull(kssj)) {
						sb.append(" and b.gssj>='" + kssj + "'");
					}
					if (!Base.isNull(jssj)) {
						sb.append(" and b.gssj<='" + jssj + "'");
					}
					sb.append(")");
					sql += sb.toString();
				}
			}
			
			 ArrayList<String[]> rs = new  ArrayList<String[]>();
			rs = CommonQueryDAO.commonQuery(sql,null, inputList.toArray(new String[]{}), colList, comform);
//			rs.addAll(dao.rsToVator(sql, inputList.toArray(new String[]{}), colList));
			//System.out.println(sql);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", String.valueOf(rs.size()));
		}
	
		sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmmc ";			
		List<HashMap<String, String>> partList = dao.getList(sql, new String[]{},new String[]{"bmdm", "bmmc"});
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("bmList", dao.getBmList());//fdyglstruts.xml�����ļ���
		}else{
			request.setAttribute("bmList", partList);
		}
		
//		//����ְҵ����:��˼��������Ϣ���в����û���Ϣ���е�����ͬ�����û���Ϣ��
//		if (Globals.XXDM_WXSYZYJSXY.equalsIgnoreCase(xxdm)) {
//			
//			SxjyDAO sxjyDao = new SxjyDAO();
//			tableName="view_wxsz_fdyxxtj";
//			request.setAttribute("fdyzList", sxjyDao.getFdyzList());
//			
//			if ("tgyhxx".equalsIgnoreCase(act)) {
//				boolean result = dao.runProcuder("{call proc_wxzy_szxxtgzyhb}", new String[]{});
//				request.setAttribute("result", result ? "true" : "false");
//			}
//		}
		
		
		request.setAttribute("path","fdyxx.do");
		String[] writeAndTitle = FormModleCommon.getWriteAbleAndTitle(request);
		
		request.setAttribute("title", writeAndTitle[1]);
		request.setAttribute("writeAble", writeAndTitle[0]);
		
		request.setAttribute("path", "user_man.do");
		request.setAttribute("qx", FormModleCommon.getWriteAbleAndTitle(request)[0]);
		
		request.setAttribute("yhzList", CommonQueryDAO.getYhzForSzdwList());
		request.setAttribute("dwList", CommonQueryDAO.getDwList());
		request.setAttribute("zwList", zwList);
		request.setAttribute("njList", Base.njList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	private ActionForward fdyxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//���人����ѧ ֱ����ת
			return mapping.findForward("fdyxxOne_whlg");
		}
		String act = request.getParameter("act");
		HashMap<String, String> map = new HashMap<String, String>();	
		SxjyDAO sxjyDao = new SxjyDAO();
		String sql = "";
		map.put("stuExists", "yes");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String type = request.getParameter("type");
		type = isNull(type) ? "" : type;
		String writeAble = "yes";
		HttpSession session = request.getSession();
		String userName = request.getSession().getAttribute("userName").toString();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)){
			request.setAttribute("isZZSF", "yes");
		}
		if ((act != null) && !act.equalsIgnoreCase("save")) {
			String pk = "";
			if(type.equalsIgnoreCase("one")){
				pk = userName;
				writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "fdyxxOne.do?act=view&type=one")?"yes":"no";
				request.setAttribute("ddxg", "yes");
				if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY )){
					request.setAttribute("isAhxgOne", "yes");
				}
			}else{
			pk = request.getParameter("pk");
			pk = isNull(pk) ? "" : pk;
			}
			sql = "select zgh, xm, xb, lxdh,bmdm, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fglxdm,dwlbdm from fdyxxb where zgh=?";
			String[] colList = new String[] { "zgh", "xm", "xb", "lxdh",
					"bmdm", "zw", "zyzz", "zzmm", "xl", "csrq", "mz", "zc",
					"lxgzsj", "xsgzyjfx", "jtzz", "yddh", "dzyx", "fblw",
					"kyjl", "jg", "grhjqk", "gzjl", "bz", "byyx", "sxzy","yzbm","xw","rwsj","gzfg","zwrzsj","jsrzsj","sjdw","txdz","bgdh","cz","jrgz","bgdd","zdxl","zdzy","gdxlzl","zyyjjg","fgnj","qtfgnj","pxqk","ssbyyx","ssbyzy","bsbyyx","bsbyzy","fglxdm","dwlbdm"};
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
				sql = "select zgh, xm, xb, lxdh,bmdm, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,bkbyyx,bksxzy,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fdyz from fdyxxb where zgh=?";
				colList = new String[] { "zgh", "xm", "xb", "lxdh", "bmdm",
						"zw", "zyzz", "zzmm", "xl", "csrq", "mz", "zc",
						"lxgzsj", "xsgzyjfx", "jtzz", "yddh", "dzyx", "fblw",
						"kyjl", "jg", "grhjqk", "gzjl", "bz", "byyx", "sxzy",
						"yzbm", "xw", "rwsj", "gzfg", "zwrzsj", "jsrzsj",
						"sjdw", "txdz", "bgdh", "cz", "jrgz", "bgdd", "zdxl",
						"zdzy", "gdxlzl", "zyyjjg", "fgnj", "qtfgnj", "pxqk",
						"bkbyyx", "bksxzy", "ssbyyx", "ssbyzy", "bsbyyx",
						"bsbyzy", "fdyz" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_FJGCXY)){
				//���δ���
				String zzdy=" select zzdydm dm,zzdymc mc from szdw_fjgc_zzdydmb ";
				List<HashMap<String,String>>zzdyList=dao.getList(zzdy, new String[]{}, new String[]{"dm","mc"});
				
				String xhfz=" select xhfzdm dm,xhfzmc mc from szdw_fjgc_xhfzdmb ";
				List<HashMap<String,String>>xhfzList=dao.getList(xhfz, new String[]{}, new String[]{"dm","mc"});
				
				sql = "select zgh, xm,zjz,sdbjrs, xb,bkbysj,yjsbysj,yjsbyzy,yjsbyyx,sdxsqk,xhfz,ndkhy,zzdy, lxdh,bmdm, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fglxdm,dwlbdm from fdyxxb where zgh=?";
				colList = new String[] {  "zgh", "xm", "xb", "lxdh",
						"bmdm", "zw", "zyzz", "zzmm", "xl", "csrq", "mz", "zc",
						"lxgzsj", "xsgzyjfx", "jtzz", "yddh", "dzyx", "fblw",
						"kyjl", "jg", "grhjqk", "gzjl", "bz", "byyx", "sxzy",
						"yzbm","xw","rwsj","gzfg","zwrzsj","jsrzsj","sjdw","txdz",
						"bgdh","cz","jrgz","bgdd","zdxl","zdzy","gdxlzl","zyyjjg",
						"fgnj","qtfgnj","pxqk","ssbyyx","ssbyzy","bsbyyx","bsbyzy",
						"fglxdm","dwlbdm","bkbysj","yjsbysj","yjsbyzy","yjsbyyx","sdxsqk",
						"xhfz","ndkhy","zzdy","zjz","sdbjrs"};
				FormModleCommon.setNdXnXqList(request);
				request.setAttribute("zzdyList", zzdyList);
				request.setAttribute("xhfzList", xhfzList);
			}
			String[] colV = dao.getOneRs(sql, new String[] { pk }, colList);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){
				sql= "select xn,xq,gssj,gsnr from xmlg_szdw_fdygsjl where zgh=?";
				List<HashMap<String,String>> gsList=dao.getList(sql, new String[]{pk}, new String[]{"xn","xq","gssj","gsnr"});
				request.setAttribute("gsList", gsList);
			}
			if (colV == null) {
				if(type.equalsIgnoreCase("one")){
					sql = "select zgh from fdyxxb where zgh = ?";
					String[] sffdy = dao.getOneRs(sql, new String[] { pk }, new String[] {"zgh"});
					if(null == sffdy){
						request.setAttribute("ffdy", "yes");
						String msg = "��ģ��ֻ�ܸ���Ա�û����в�������ȷ�ϣ�";
						request.setAttribute("yhInfo", msg);
						return new ActionForward("/yhInfo.do", false);
					}
				}
				colV = new String[colList.length];
			}
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
			}
			if ("xy".equalsIgnoreCase(userType)) {
				map.put("bmdm", userDep);
			}
			if(type.equalsIgnoreCase("one")){
				map.put("zgh",userName);
			}
			String[] bjList = null;
			if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY )){
				bjList = sxjyDao.getFdyBjListforAhjg(pk);
			}else {
				bjList = sxjyDao.getFdyBjList(pk);
			}
			String[] zyList = sxjyDao.getZyBjList(pk);
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				sql = "select '������Ŀ:'||lwtm||'   �����ڿ�����:'||fbqkmc lwxx from dtjsZgdzdxLwxxb where zgh = ?";
				String[] lwxx = dao.getArray(sql, new String[]{pk},"lwxx");
				request.setAttribute("lwxx", lwxx);
				sql = "select xmmc from dtjsZgdzdxKyxmb where zgh = ?";
				String[] kyxm = dao.getArray(sql, new String[]{pk},"xmmc");
				request.setAttribute("kyxm", kyxm);
				sql = "select '��������:'||zzmc||'   ���浥λ:'||cbdw fdyzz from dtjsZgdzdxfdyzzb where zgh = ?";
				String[] fdyzz = dao.getArray(sql, new String[]{pk},"fdyzz");
				request.setAttribute("fdyzz", fdyzz);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				sql = "select kssj||'��'||jssj||'     '||pxxm pxqk from zjlg_fdypxxmb where zgh = ? and shzt='ͨ��'";
				String[] pxqk = dao.getArray(sql, new String[]{pk},"pxqk");
				request.setAttribute("pxqk", pxqk);
			}
			request.setAttribute("fdybjList", bjList);
			request.setAttribute("fdyzyList", zyList);
			request.setAttribute("xbList", dao.getSexList());
			request.setAttribute("doType", act);
			request.setAttribute("pkValue", pk);
		} else if ((act != null) && act.equalsIgnoreCase("save")) {
			String ddxg = request.getParameter("ddxg");
			ddxg = isNull(ddxg) ? "" : ddxg;
			if(ddxg.equalsIgnoreCase("yes")){
				request.setAttribute("ddxg", "yes");
			}
			String pkValue =request.getParameter("pkValue");
			String zgh = request.getParameter("zgh");
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = request.getParameter("xb");
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String szdw = request.getParameter("bmdm");
			String zw = DealString.toGBK(request.getParameter("zw"));
			String zzmm = DealString.toGBK(request.getParameter("zzmm"));
			String xl = DealString.toGBK(request.getParameter("xl"));
			String csrq = DealString.toGBK(request.getParameter("csrq"));
			String grhjqk = DealString.toGBK(request.getParameter("grhjqk"));
			String gzjl = DealString.toGBK(request.getParameter("gzjl"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String zyzz = DealString.toGBK(request.getParameter("zyzz"));
			String mz = DealString.toGBK(request.getParameter("mz"));
			String zc = DealString.toGBK(request.getParameter("zc"));
			String lxgzsj = DealString.toGBK(request.getParameter("lxgzsj"));
			String xsgzyjfx = DealString.toGBK(request.getParameter("xsgzyjfx"));
			String jtzz = DealString.toGBK(request.getParameter("jtzz"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String dzyx = DealString.toGBK(request.getParameter("dzyx"));
			String fblw = DealString.toGBK(request.getParameter("fblw"));
			String kyjl = DealString.toGBK(request.getParameter("kyjl"));
			String jg = DealString.toGBK(request.getParameter("jg"));
			String byyx = DealString.toGBK(request.getParameter("byyx"));
			String sxzy = DealString.toGBK(request.getParameter("sxzy"));
			String yzbm = DealString.toGBK(request.getParameter("yzbm"));
			String xw = DealString.toGBK(request.getParameter("xw"));
			String rwsj = DealString.toGBK(request.getParameter("rwsj"));
			String gzfg = DealString.toGBK(request.getParameter("gzfg"));
			String zwrzsj = DealString.toGBK(request.getParameter("zwrzsj"));
			String jsrzsj = DealString.toGBK(request.getParameter("jsrzsj"));
			String sjdw = DealString.toGBK(request.getParameter("sjdw"));
			String txdz = DealString.toGBK(request.getParameter("txdz"));
			String bgdh = DealString.toGBK(request.getParameter("bgdh"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String jrgz = DealString.toGBK(request.getParameter("jrgz"));
			String bgdd = DealString.toGBK(request.getParameter("bgdd"));
			String zdxl = DealString.toGBK(request.getParameter("zdxl"));
			String zdzy = DealString.toGBK(request.getParameter("zdzy"));
			String gdxlzl = DealString.toGBK(request.getParameter("gdxlzl"));
			String zyyjjg = DealString.toGBK(request.getParameter("zyyjjg"));
			String fgnj = DealString.toGBK(request.getParameter("fgnj"));
			String qtfgnj = DealString.toGBK(request.getParameter("qtfgnj"));
			String pxqk  = DealString.toGBK(request.getParameter("pxqk"));
			String bkbyyx = DealString.toGBK(request.getParameter("bkbyyx"));
			String bksxzy = DealString.toGBK(request.getParameter("bksxzy"));
			String ssbyyx = DealString.toGBK(request.getParameter("ssbyyx"));
			String ssbyzy = DealString.toGBK(request.getParameter("ssbyzy"));
			String bsbyyx = DealString.toGBK(request.getParameter("bsbyyx"));
			String bsbyzy = DealString.toGBK(request.getParameter("bsbyzy"));
			String fglxdm = DealString.toGBK(request.getParameter("fglxdm"));
			String fdyz = DealString.toGBK(request.getParameter("fdyz"));//����Ա��
			String dwlbdm = DealString.toGBK(request.getParameter("dwlbdm"));//����Ա��
			
			String bkbysj = DealString.toGBK(request.getParameter("bkbysj"));//���Ʊ�ҵʱ��
			String yjsbysj = DealString.toGBK(request.getParameter("yjsbysj"));//�о�����ҵʱ��
			String yjsbyzy = DealString.toGBK(request.getParameter("yjsbyzy"));//�о�����ҵרҵ
			String yjsbyyx = DealString.toGBK(request.getParameter("yjsbyyx"));//�о�����ҵԺУ
			String sdxsqk = DealString.toGBK(request.getParameter("sdxsqk"));//����ѧ�����
			String sdbjrs = DealString.toGBK(request.getParameter("sdbjrs"));//����ѧ�����
			String xhfz = DealString.toGBK(request.getParameter("xhfz"));//����ԱЭ�����
			String ndkhy = DealString.toGBK(request.getParameter("ndkhy"));//��ȿ���Ϊ��
			String zzdy = DealString.toGBK(request.getParameter("zzdy"));//���δ���
			String zjz =  DealString.toGBK(request.getParameter("zjz"));//���δ���
			if ("xy".equalsIgnoreCase(userType)) {
				szdw = userDep;
			}
			Vector<HashMap<String, Object>> sqlVal = new Vector<HashMap<String, Object>>();
			HashMap<String, Object> sqls = new HashMap<String, Object>();
			sqls.put("sqlTxt", "delete fdyxxb where zgh=?");
			sqls.put("sqlVal", new String[] { pkValue });
			sqlVal.add(sqls);
			sqls = new HashMap<String, Object>();
			sqls.put("sqlTxt",
			"insert into fdyxxb(zgh,xm,xb,lxdh,bmdm,zw,zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fglxdm,dwlbdm) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			sqls.put("sqlVal",new String[] { zgh, xm, xb, lxdh, szdw, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fglxdm,dwlbdm });
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
				sqls.put("sqlTxt","insert into fdyxxb(zgh,xm,xb,lxdh,bmdm,zw,zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,bkbyyx,bksxzy,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fdyz,fglxdm) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				sqls.put("sqlVal",new String[] { zgh, xm, xb, lxdh, szdw, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,bkbyyx,bksxzy,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fdyz,fglxdm});
			}
			//����ѧԺ����Ա��Ϣ
			if(xxdm.equalsIgnoreCase(Globals.XXDM_FJGCXY)){
				sqls = new HashMap<String, Object>();
				sqls.put("sqlTxt","insert into fdyxxb(zgh,xm,xb,lxdh,bmdm,zw,zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fglxdm,dwlbdm,bkbysj,yjsbysj,yjsbyzy,yjsbyyx,sdxsqk,xhfz,ndkhy,zzdy,zjz,sdbjrs) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				sqls.put("sqlVal",new String[] { zgh, xm, xb, lxdh, szdw, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg,zwrzsj,jsrzsj,sjdw,txdz,bgdh,cz,jrgz,bgdd,zdxl,zdzy,gdxlzl,zyyjjg,fgnj,qtfgnj,pxqk,ssbyyx,ssbyzy,bsbyyx,bsbyzy,fglxdm,dwlbdm ,bkbysj,yjsbysj,yjsbyzy,yjsbyyx,sdxsqk,xhfz,ndkhy,zzdy,zjz,sdbjrs});
			}
			sqlVal.add(sqls);
			boolean res = dao.runUpdate(sqlVal);
			if (res) {
				request.setAttribute("added", "ok");
			} else {
				request.setAttribute("added", "no");
			}
		}
		if("xy".equalsIgnoreCase(userType))
		{
			request.setAttribute("writeAble", "no");
		}
		else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType))
		{
			request.setAttribute("writeAble", "yes");
		}
		
		request.setAttribute("rs", map);
		sql = "select zwdm,zwmc from zwb";		
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm","zwmc" });
		
		sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM ";			
		List<HashMap<String, String>> partList = dao.getList(sql, new String[]{},new String[]{"bmdm", "bmmc"});
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("bmList", dao.getBmList());//fdyglstruts.xml�����ļ���
		}else{
			request.setAttribute("bmList", partList);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
			request.setAttribute("fdyzList", sxjyDao.getFdyzList());
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			request.setAttribute("fglxList", sxjyDao.getFglxList());
		}
		if(writeAble.equalsIgnoreCase("yes")){
		request.setAttribute("writeAble2", writeAble);
		}
		request.setAttribute("path", "fdyxxOne.do?act=view&type=one");
		
		request.setAttribute("title", FormModleCommon.getWriteAbleAndTitle(request)[1]);
		request.setAttribute("type", type);
		request.setAttribute("zwList", zwList);
		request.setAttribute("JsZzmmList", sxjyDao.getJsZzmmList());
		request.setAttribute("JsXlList", sxjyDao.getJsXlList());
		request.setAttribute("sfList", sxjyDao.getSfList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("userType", userType);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("operation", request.getParameter("operation"));
		//		�㽭������������rs
		XjchDAO fdyDAO = new XjchDAO();
		request.setAttribute("dwlbList",fdyDAO.getFdydwlbList());
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
			return new ActionForward("/szdw/zgkd/zgkdfdyxxwh.jsp");
		}
		
		return mapping.findForward("success");
	}
	
	/**
	 * ˼����������ͳ��
	 * 
	 * @author luojw
	 */
	public ActionForward wxszsjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CommanForm comform = (CommanForm) form;
		String bmdm = comform.getBmdm();
		String xm = comform.getXm();
		String zw = comform.getZxm();
		String fdyz = comform.getFdyz();
		
		StringBuffer sb = new StringBuffer("where 1=1");
		sb.append(Base.isNull(bmdm) ? "" : " and bmdm = '" + bmdm + "'");
		sb.append(Base.isNull(zw) ? "" : " and zw = '" + zw + "'");
		sb.append(Base.isNull(fdyz) ? "" : " and fdyz = '" + fdyz + "'");
		sb.append(Base.isNull(xm) ? " and xm like '%' " : " and xm like '%" + xm + "%'");
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		String title =  "˼����������ͳ�Ʊ�";

		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		
		// �������
		SxjyDAO sxjyDao = new SxjyDAO();
		List<HashMap<String, String>> list =sxjyDao.getFdyTjList(sb.toString());
		
		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 18, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		ws.addCell(new Label(0, 2, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(1, 2, "�Ա�", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(2, 2, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(3, 2, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(4, 2, "����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(5, 2, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(6, 2, "ְ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(7, 2, "ְ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(8, 2, "������ò", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(9, 2, "����༶", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(10, 2, "��һѧ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(11, 2, "��ҵѧУ", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(12, 2, "�ڶ�ѧ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(13, 2, "��ҵѧУ", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(14, 2, "����ѧ��", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(15, 2, "��ҵѧУ", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(16, 2, "���˻����", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(17, 2, "�ƶ��绰", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
		ws.addCell(new Label(18, 2, "��������", wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {		
			ws.addCell(new Label(0, 3 + i, list.get(i).get("xm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(1, 3 + i, list.get(i).get("xb"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(2, 3 + i, list.get(i).get("bmmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(3, 3 + i, list.get(i).get("sfmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(4, 3 + i, list.get(i).get("mzmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(5, 3 + i, list.get(i).get("csrq"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(6, 3 + i, list.get(i).get("zc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(7, 3 + i, list.get(i).get("zwmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(8, 3 + i, list.get(i).get("zzmm"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(9, 3 + i, list.get(i).get("bjmc"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(10, 3 + i, list.get(i).get("bkbyyx"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(11, 3 + i, list.get(i).get("bksxzy"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(12, 3 + i, list.get(i).get("ssbyyx"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(13, 3 + i, list.get(i).get("ssbyzy"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(14, 3 + i, list.get(i).get("bsbyyx"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(15, 3 + i, list.get(i).get("bsbyzy"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(16, 3 + i, list.get(i).get("grhjqk"),wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(17, 3 + i, list.get(i).get("yddh"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			ws.addCell(new Label(18, 3 + i, list.get(i).get("fblw"), wcf2)); // �����ָ����ʽ�ĵ�Ԫ��ֵ
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
		return null;
	}
	
	/**
	 * ���ݴ�ѧ��λ��Ϣ���ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzdxgwxxbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (rs != null) {
			DAO dao = DAO.getInstance();
			rs = dao.getMapNotOut("select xn,nd,gwdm,sqdw,yrdwmc,fzr,lxdh,gzsj,gzksrq," +
					"gzjsrq,gznr,gzyd,xyrs,sqdwyj,xgbyj,yrdwsh,shjg from " +
					"view_gwxx where gwdm||gwsbsj=?", new String[]{pkValue});
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}
}
