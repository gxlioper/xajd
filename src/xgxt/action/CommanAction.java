/*
 * �������� 2006-8-24
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import com.google.gson.Gson;
import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.LogInfo;
import com.zfsoft.xgxt.base.log.LogService;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import com.zfsoft.xgxt.xtgl.RSALoginService;
import com.zfsoft.zfca.tp.cas.authentication.AttributePrincipal;
import common.Globals;
import common.GlobalsVariable;
import edu.yale.its.tp.cas.client.filter.CASFilter;
import edu.yale.its.tp.cas.client.filter.CASFilterRequestWrapper;
import eu.bitwalker.useragentutils.UserAgent;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tomcat.util.buf.Base64;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import test.des.DES2;
import thauth.Roam;
import thauth.ThauthConst;
import validate.CookieValidate;
import validate.CookieValidating;
import xgxt.DAO.DAO;
import xgxt.DAO.LdapTestDao;
import xgxt.DAO.ZjjdDbCenterDAO;
import xgxt.base.DealString;
import xgxt.base.Encrypt;
import xgxt.base.MD5;
import xgxt.comm.xml.XMLReader;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.pjpy.WSForCjJd.WebserviceClient;
import xgxt.pjpy.ahjg.CommanDAO;
import xgxt.sso.ZfsoftMd5;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.mmzh.MmZhService;
import xgxt.xtwh.userLogin.*;
import xgxt.xtwh.xtwhCriterion.LoginCriterionService;
import xsgzgl.xtwh.general.menu.Node;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.io.*;
import java.net.*;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author bat_zzj
 * 
 *         Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */

public class CommanAction extends Action {

	private Log logger = LogFactory.getLog(CommanAction.class);
	private MmZhService mmzh = new MmZhService();

	private static long nd = 1000*24*60*60;//һ��ĺ�����
	private static long nh = 1000*60*60;//һСʱ�ĺ�����
	private static String cs = "5";//�������
	// DAO dao = DAO.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myActFor = null;
		String myAct = mapping.getParameter();
		String xxdm = StandardOperation.getXxdm();
		if ("userLogin".equalsIgnoreCase(myAct)) {
			// �û���½
			HttpSession session = request.getSession();
			CommanForm commanForm = (CommanForm) form;
			// String tmp =
			// DealString.toGBK(request.getParameter("returnPage"));
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)
					|| DealString.toGBK(request.getParameter("type"))
							.equalsIgnoreCase("nblg")
					|| DealString.toGBK(request.getParameter("returnPage"))
							.equalsIgnoreCase("yes")
					|| DealString.toGBK(request.getParameter("returnPage"))
							.equalsIgnoreCase("notYz")
					// ��������(����)����Ҫ��֤��
					|| "noyzm".equalsIgnoreCase(request.getParameter("dllx"))) {

			} else {
				String yzm = commanForm.getYzm();
				String sYzm = (String) session.getAttribute("yzm");
				String host = IPRequest.getIpAddress(request);
				if(StringUtil.isNull(yzm) || StringUtil.isNull(sYzm) || StringUtil.isNull(host)){
					return mapping.findForward("false");
				}else if (!yzm.equalsIgnoreCase(sYzm) && !host.equals("127.0.0.1")&&!host.equals("0:0:0:0:0:0:0:1")) {
					commanForm.setErrMsg("��֤�����");
					session.setAttribute("yzm", "");
					return mapping.findForward("false");
				}
			}
			myActFor = userLogin(mapping, form, request, response);
			LoginCriterionService loginCriterionService = new LoginCriterionService();
			// loginCriterionService.setUserRole(request);

			if ((xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XHSFDX) || xxdm
					.equalsIgnoreCase(Globals.XXDM_CQDZKJ))
					&& "noyzm".equalsIgnoreCase((String) request
							.getParameter("dllx"))) {
				request.setAttribute("userType_niub", session
						.getAttribute("userType"));
				return mapping.findForward("success");
			}

		}else if("tyrzLogin".equalsIgnoreCase(myAct)){
			myActFor = tyrzLogin(mapping, form, request, response);
			
		} else if ("getPublicKey".equalsIgnoreCase(myAct)){
			RSALoginService rsaLoginService = new RSALoginService();
			RSAPublicKey publicKey = rsaLoginService.generateKey(request);
			
			byte[] modulus = publicKey.getModulus().toByteArray();
	        byte[] exponent = publicKey.getPublicExponent().toByteArray();
			
	        Map<String,String> map = new HashMap<String,String>();
	        
	        map.put("modulus", org.apache.commons.codec.binary.Base64.encodeBase64String(modulus));
	        map.put("exponent", org.apache.commons.codec.binary.Base64.encodeBase64String(exponent));
	        JSONObject data = JSONObject.fromObject(map);
	        response.getWriter().print(data);
	        return null;
		} else if ("userLogout".equalsIgnoreCase(myAct)) {
			// �û��ǳ�
			myActFor = userLogout(mapping, form, request, response);
		} else if ("initMenu".equalsIgnoreCase(myAct)) {
			// ѡ�еĵ����˵�
			String menuTop = request.getParameter("menuTop");
			// ѡ�е���˵�
			String chMenu = request.getParameter("chMenu");
			// ��ݷ�ʽ��ת·��
			String gnmkpath = request.getParameter("gnmkpath");
			request.setAttribute("menuTop", menuTop);
			request.setAttribute("chMenu", chMenu);
			request.setAttribute("gnmkpath", gnmkpath);
			// ���ز˵�
			CommanForm chkUser = (CommanForm) form;
			try {
				int i = Base.chkTimeOut(request.getSession());
				if (i <= 2) {
					chkUser.setErrMsg("��½��ʱ�������µ�½��");
					return new ActionForward("/login.jsp", false);
				}

				String act = request.getParameter("act");
				if (act.equalsIgnoreCase("top")) {
					// һ���˵�
					myActFor = topMenuInit(mapping, form, request, response,
							"success_top");
				} else if (act.equalsIgnoreCase("top_list")) {
					// һ���˵�
					myActFor = topMenuInit(mapping, form, request, response,
							"success_top_list");
				} else {
					// ���������˵�
					myActFor = leftMenuInit(mapping, form, request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				chkUser.setErrMsg("���������жϣ������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
		} else if ("errMsg".equalsIgnoreCase(myAct)) {
			// ������Ϣ
			myActFor = errMsg(mapping, form, request, response);
		} else if ("chgPass".equalsIgnoreCase(myAct)) {
			// ��������
			myActFor = chgPass(mapping, form, request, response);
		} else if ("passEdit".equalsIgnoreCase(myAct)) {
			// ��������
			return mapping.findForward("success");
		} else if ("xgMmQz".equalsIgnoreCase(myAct)) {
			// ǿ���޸�����
			myActFor = xgMmQz(mapping, form, request, response);
		} else if ("chkLoginDbCenter".equalsIgnoreCase(myAct)) {
			// ���������֤ģʽ��½
			myActFor = chkLoginDbCenter(mapping, form, request, response);
		} else if ("chkXmlgLoginDbCenter".equalsIgnoreCase(myAct)) {
			// ���������������֤ģʽ��½
			myActFor = chkXmlgLoginDbCenter(mapping, form, request, response);
		} else if ("chkZjsyLoginDbCenter".equalsIgnoreCase(myAct)) {
			myActFor = chkZjsyLoginDbCenter(mapping, form, request, response);
		} else if ("STUPWDINIT".equalsIgnoreCase(myAct)) {
			// ѧ�������ʼ��
			myActFor = stuPwdInit(mapping, form, request, response);
		} else if ("chkZjsytjdl".equalsIgnoreCase(myAct)) {
			myActFor = chkZjsytjDl(mapping, form, request, response);
		} else if ("fdyChange".equalsIgnoreCase(myAct)) {
			myActFor = fdyUserChange(mapping, form, request, response);
		} else if ("userTypeChange".equalsIgnoreCase(myAct)) {
			myActFor = userTypeChange(mapping, form, request, response);
		} else if ("userMenuChange".equalsIgnoreCase(myAct)) {
			myActFor = userMenuChange(mapping, form, request, response);
		} else if ("hnsfSSOLogin".equalsIgnoreCase(myAct)) {
			myActFor = hnsfSSOLogin(mapping, form, request, response);

			//�����ص������Σ�hljKjxySSOLoginӦ���õľ�����������ڳ��thauthallSSOLogin������ѧУ���ã�
		} else if ("hljKjxySSOLogin".equalsIgnoreCase(myAct)||"thauthallSSOLogin".equalsIgnoreCase(myAct)) {
			myActFor = hljKjxySSOLogin(mapping, form, request, response);
		} else if ("zfxg_casLogin".equalsIgnoreCase(myAct)) {
			myActFor = zfxg_casLogin(mapping, form, request, response);
		}
		return myActFor;
	}
	private ActionForward tyrzLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		CommanForm chkUser = (CommanForm) form;
		String userName = (String) session.getAttribute("userName");
		request.setAttribute("uName", userName);
		String xhSql = "select xh userName from view_xsjbxx where xh = ?";
		String checkName = dao.getOneRs(xhSql, new String[] { userName },
				"userName");
		String userType = null;
		if (StringUtil.isNull(checkName)) {
			userType = "teacher";
		} else {
			userType = "student";
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
		"xxmc");
		String sql="";
		ActionForward tmpAF;
		String xxdm = StandardOperation.getXxdm();
		if ("student".equalsIgnoreCase(userType)) {
			sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=?";
			        tmpAF = checkUsr(mapping, request, session, chkUser, 
			          userType, xxmc, xxdm, sql, userName);
			 
		} else if("teacher".equalsIgnoreCase(userType)) {
			sql = "select szbm,xm,qx from yhb where yhm=?  and zdm is not null";
			tmpAF = checkUsr(mapping, request, session, chkUser,
					userType, xxmc, xxdm, sql,userName);
		} else {
			// chkUser.setErrMsg("�û����Ͳ���ʶ��");
			tmpAF = mapping.findForward("false");
		}
		request.setAttribute("message", "ok");

		// �汾
		String edition = Base.getEdition();
		// �Ƿ���Ҫ�߼���ѯ
		String superSearch = Base.getSuperSearch();

		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);

		// -------------------------2011.9.14
		// qlj-------------------------------------
		// ===================�޸��û���½ʱ�� begin=======================

		// ��ʽ: yyyy-mm-dd HH24:MI:SS
		String dlsj = GetTime.getNowTime4();
		// ���û���½ʱ�����õ�session��
		session.setAttribute("loginTime", dlsj);

		List<String> inputV = new ArrayList<String>();
		StringBuilder updateTime = new StringBuilder();

		String checkTime = " select count(1)num from xg_xtwh_yhdlb where yhm='"
				+ userName + "' and yhlx='" + userType + "' ";
		String num = dao.getOneRs(checkTime.toString(), new String[] {}, "num");

		// ===============�ж��û��Ƿ���� begin================
		if ("0".equalsIgnoreCase(num)) {
			updateTime
					.append(" insert into xg_xtwh_yhdlb(yhm,yhlx,dlsj)values(?,?,?) ");
			inputV.add(userName);
			inputV.add(userType);
			inputV.add(dlsj);
			dao.runUpdate(updateTime.toString(), inputV
					.toArray(new String[] {}));
		} else {
			updateTime
					.append(" update xg_xtwh_yhdlb set dlsj=? where yhm=? and yhlx=? ");
			inputV.add(dlsj);
			inputV.add(userName);
			inputV.add(userType);
			dao.runUpdate(updateTime.toString(), inputV
					.toArray(new String[] {}));
		}
		// ===============�ж��û��Ƿ���� end================
		// ===================�޸��û���½ʱ�� end=======================
		//=======�û���¼��־��¼=======
		LogInfo loginfo = new LogInfo() ;
		UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
		loginfo.setOsName(userAgent.getOperatingSystem().getName());
		loginfo.setBrowserName(userAgent.getBrowser().getName());
		if(userAgent.getBrowserVersion() != null){
			loginfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
		}
		loginfo.setIp(IPRequest.getIpAddress(request));
		loginfo.setDescription("ͳһ�����֤��¼���û�����"+userName);
		loginfo.setClassName(mapping.getType());
		loginfo.setMethodName("userLogin");
		loginfo.setUsername(userName);
		loginfo.setUserType(userType);
		new LogService().runInsert(loginfo);
		new LogService().insertDllog();
		String dljc = XMLReader.getFlowControl("xsxx", "dljc");

		if ("new".equalsIgnoreCase(edition)) {
			if ("student".equalsIgnoreCase(userType)) {

				if ("no".equalsIgnoreCase(dljc)) {
					return mapping.findForward("stuLogin");
				} else {
					return new ActionForward("/xsxx_xsdl.do", false);
				}
			} else if ("teacher".equalsIgnoreCase(userType)) {
				// return mapping.findForward("success");
				return mapping.findForward("teaLogin");
			}
		}

		return mapping.findForward("success");
		
	}
	private ActionForward userLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO ѧ����֤
		Encrypt ept = new Encrypt();
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		CommanForm chkUser = (CommanForm) form;
		String userName = request.getParameter("userName");
		request.setAttribute("uName", userName);

		// =======����userName��userType,��¼ҳ�治��ѡ��ѧ��or��ʦ====2013-1-16==qph===begin========
		String userType = null;
		String xhSql = "select xh userName from view_xsjbxx where xh = ?";
		String checkName = dao.getOneRs(xhSql, new String[] { userName },
				"userName");
		//��ʦ����Ի��ж�
		if(Base.xxdm.equals("10698")&&StringUtil.isNull(checkName)){
			xhSql = "select xh userName from view_xsxxb where xh = ? and xjztm = '��ѧ��'";
			checkName = dao.getOneRs(xhSql, new String[] { userName },
			"userName");
		}
		if (StringUtil.isNull(checkName)) {
			userType = "teacher";
		} else {
			userType = "student";
		}
		// =======����userName��userType,��¼ҳ�治��ѡ��ѧ��or��ʦ====2013-1-16==qph===end========

		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				"xxmc");
		String xxdm = StandardOperation.getXxdm();
//		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
//			String ip = request.getRemoteAddr();
//			if (!ip.substring(0, 3).equalsIgnoreCase("10.")
//					&& !ip.equalsIgnoreCase("127.0.0.1")) {
//				chkUser.setErrMsg("ֻ���������û�����ϵͳ��");
//				return mapping.findForward("false");
//			}
//		}
		
		String enPassword = request.getParameter("password");
		RSALoginService rsaLoginService = new RSALoginService();
		String password = rsaLoginService.decryptParameter(enPassword, request);
		rsaLoginService.removePrivateKey(request);
		
		String sql = "";

		String jsName = request.getParameter("jsName");
		String xnOrGdFlag = dao.getOneRs("select jwflag from xtszb",
				new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ
		ActionForward tmpAF;
		session.setAttribute("showShgcKnsTs", "notshow");

		// �㽭��ҵͳһ��ݵ�½
		if (Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)) {
			// �㽭��ҵ��¼��֤�Ƿ���ѧ����֤��ʶ trueΪͳһ�����֤��false Ϊѧ����֤
			String zjsySsoFlag = Base.getInitProperties().get("zjsySSOFlag");
			if (!StringUtils.isNull(zjsySsoFlag)
					&& "true".equalsIgnoreCase(zjsySsoFlag)) {

				// ���ͳһ�����֤ʧ�ܣ�����ѧ���ٴ���֤������������SESSION�д���û��������룬�û����͵���Ϣ��
				session.setAttribute("uType", userType);
				session.setAttribute("uName", userName);
				session.setAttribute("jName", jsName);
				session.setAttribute("pWord", password);

				HashMap<String, String> endPointUriMap = Base
						.getInitProperties();
				String endUri = endPointUriMap.get("zjsyWebendPointUri");
				String realPath = request.getRequestURL().toString();
				String appAtt = StringUtils.isNull(userType) ? ""
						: ("teacher".equalsIgnoreCase(userType) ? "userType:teacher"
								: ("student".equalsIgnoreCase(userType) ? "userType:student"
										: ""));
				boolean hasApp = StringUtils.isNull(appAtt) ? false : true;
				if (!StringUtils.isNull(realPath)) {
					realPath = realPath.substring(0,
							realPath.lastIndexOf("/") + 1);
				} else {
					realPath = "";
				}
				String sUserName = session.getAttribute("userName") == null ? ""
						: session.getAttribute("userName").toString();// session�Ƿ�����û���
				if ("".equalsIgnoreCase(sUserName)) {
					DES2 des2 = new DES2();// ��,����Ҫ�õ�����
					byte[] encryptorByteName = des2.createEncryptor(userName);// ���û�������
					byte[] encryptorBytePwd = des2.createEncryptor(password);// ���������
					String byteToStringName = des2
							.byteToString(encryptorByteName);// �û�����BYTEתΪSTRING
					String byteToStringPwd = des2
							.byteToString(encryptorBytePwd);// ���뽫BYTEתΪSTRING
					// byte[] op = des2.stringToByte(ss);
					// byte[] ee = des2.createDecryptor(op);
					// logger.debug(new String(ee).toString());
					// ·��ת��ͳһ��֤��ַ
					request.setAttribute("forwardPath", endUri
							+ "?Action=Login&AppCode=xgxt&CurrentUrl="
							+ realPath
							+ "chkZjsyLoginDbCenter.do&AppAttribute=" + appAtt
							+ "&hasAppAttribute=" + hasApp + "&ssoAccount="
							+ byteToStringName + "&ssoPass=" + byteToStringPwd);
					return new ActionForward("/zjsy.jsp", false);
				}
			}
		}
		String zjdxSsoFlag = (String)Base.getInitProperties().get("zjdxSSOFlag");
	    boolean dbcenterFlag = false;
	    String token=null;
		// �㽭��ѧͳһ��ݵ�½
		if (Globals.XXDM_ZJDX.equalsIgnoreCase(xxdm)) {
			// �㽭��ѧ��¼��֤�Ƿ���ѧ����֤��ʶ trueΪͳһ�����֤��false Ϊѧ����֤
			if (!StringUtils.isNull(zjdxSsoFlag)
					&& "true".equalsIgnoreCase(zjdxSsoFlag)) {

				// ���ͳһ�����֤ʧ�ܣ�����ѧ���ٴ���֤������������SESSION�д���û��������룬�û����͵���Ϣ��
				session.setAttribute("uType", userType);
				session.setAttribute("uName", userName);
				session.setAttribute("jName", jsName);
				session.setAttribute("pWord", password);

				
				String sUserName = session.getAttribute("userName") == null ? ""
						: session.getAttribute("userName").toString();// session�Ƿ�����û���
				if ("".equalsIgnoreCase(sUserName)) {
					HashMap<String, String> endPointUriMap = Base
					.getInitProperties();
					logger.info("�����֤��ʼ");
					token = login(request, response,endPointUriMap);
					//token = "1111";
					logger.info("token:"+token);
					if (StringUtil.isNull(token) && "student".equalsIgnoreCase(userType)) {
						 chkUser.setErrMsg("ͳһ�����֤�û������������");
						return mapping.findForward("false");
					}
					
					
				}
			}
		}

		// ������������ְҵ password Ϊ��
		if (DealString.toGBK(request.getParameter("type")).equalsIgnoreCase(
				"nblg")) {
			password = "";
		} else {
			if ((xnOrGdFlag != null && xnOrGdFlag.trim().equals("1"))
					&& userType.equalsIgnoreCase("student")) {
			} else {
				password = ept.encrypt(password);
			}
		}
		// ���������û����ж��û�����
		if (DealString.toGBK(request.getParameter("type")).equalsIgnoreCase(
				"nblg")) {
			if (userName.length() >= 10) {
				userType = "student";
			} else {
				userType = "teacher";
			}
			if (userType.equalsIgnoreCase("student")) {
				sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=?";
			} else if (userType.equalsIgnoreCase("teacher")
					|| ((jsName != null) && jsName.equalsIgnoreCase("admin"))) {
				sql = "select szbm,xm,qx from yhb where yhm=?";
			} else {
				chkUser.setErrMsg("�û����Ͳ���ʶ��");
				return mapping.findForward("false");
			}
			tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
					userType, xxmc, xxdm, sql, password, userName, true);
			if (tmpAF != null)
				return tmpAF;
		} else if (DealString.toGBK(request.getParameter("returnPage"))
				.equalsIgnoreCase("yes")) {
			// ����ְҵldap�ж�
			// password = request.getParameter("password");
			// LdapTestDao ld = new LdapTestDao("cn=root", "password");
			// String [] loginMessage=ld.ss(userName,password);
			// if(!loginMessage[4].equalsIgnoreCase("")){
			// chkUser.setErrMsg(loginMessage[4]);
			// return mapping.findForward("false");
			// }else{
			// if(loginMessage[0].equalsIgnoreCase("s")){
			// userType="student";
			// sql = "select xm,bmdm szbm from view_xsjbxx where xh=?";
			// tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
			// userType, xxmc, xxdm, sql, password, userName,true);
			// }else{
			// userType="teacher";
			// sql = "select szbm,xm from yhb where yhm=?";
			// tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
			// userType, xxmc, xxdm, sql, password, userName,true);
			// }
			// request.setAttribute("ldap", "yes");
			//				 
			// if(tmpAF != null)
			// return tmpAF;
			// }

			// ����ְҵldap�ж�
			LdapTestDao ld = new LdapTestDao("cn=root", "password");
			String[] loginMessage = ld.ss(userName, password);
			if (!loginMessage[4].equalsIgnoreCase("")) {
				chkUser.setErrMsg(loginMessage[4]);
				return mapping.findForward("false");
			} else {
				String sqlTemp = "";
				sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=?";
				String[] tmp = dao.getOneRs(sql, new String[] { userName },
						new String[] { "xm" });
				if (tmp != null && tmp[0] != null) {
					userType = "student";
					sqlTemp = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=?";
				}
				sql = "select szbm,xm,qx from yhb where yhm=?";
				tmp = dao.getOneRs(sql, new String[] { userName },
						new String[] { "xm" });
				if (tmp != null && tmp[0] != null) {
					userType = "teacher";
					sqlTemp = "select szbm,xm,qx from yhb where yhm=?";
				}
				request.setAttribute("ldap", "yes");
				tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
						userType, xxmc, xxdm, sqlTemp, password, userName, true);
			}

			if (tmpAF != null)
				return tmpAF;
		} else {
			 logger.info("###########################ѧ����֤��ʼ###########################");
			if ("student".equalsIgnoreCase(userType)) {
				sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=? and mm=?";
				if ((!StringUtils.isNull(zjdxSsoFlag)) && 
				          ("true".equalsIgnoreCase(zjdxSsoFlag))&&StringUtils.isNotNull(token)) {
				          sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=?";
				          dbcenterFlag = true;
				        }
				        tmpAF = checkUsrAndPwd(mapping, request, session, chkUser, 
				          userType, xxmc, xxdm, sql, password, userName, dbcenterFlag);
				 
			} else if("teacher".equalsIgnoreCase(userType)
					|| ((jsName != null) && jsName.equalsIgnoreCase("admin"))) {
				sql = "select szbm,xm,qx from yhb where yhm=? and kl=? and zdm is not null";
				logger.info("sql:"+sql+"###"+userName+"MM:"+password);
				 if ((!StringUtils.isNull(zjdxSsoFlag)) && 
				            ("true".equalsIgnoreCase(zjdxSsoFlag)) && (StringUtils.isNotNull(token))) {
				            sql = "select szbm,xm,qx from yhb where yhm=?  and zdm is not null";
				            dbcenterFlag = true;
				          }
				tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
						userType, xxmc, xxdm, sql, password, userName, dbcenterFlag);
			} else {
				// chkUser.setErrMsg("�û����Ͳ���ʶ��");
				tmpAF = mapping.findForward("false");
			}
//			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
//				// ����ְҵ����ѧԺ��¼������Ϣ
//				request.getSession().setAttribute(
//						"message",
//						dao.getMap("select fbnr,fbsj from xtwh_message",
//								new String[] {},
//								new String[] { "fbnr", "fbsj" }));
//			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					&& userType.equalsIgnoreCase("student")) {
				// �Ϻ�������������½������Ϣ
				String num = dao
						.getOneRs(
								"select count(*) num from shgc_knsxx where xh=? and xxsh in ('һ������','�ر�����','����')",
								new String[] { userName }, "num");
				if (Integer.parseInt(num) > 0) {
					num = dao
							.getOneRs(
									"SELECT floor(to_date((to_number(substr(NVL(replace(MAX(rdsj),'-02-29','-02-28'),'1900-01-01'),0,4))+1)||substr(NVL(replace(MAX(rdsj),'-02-29','-02-28'),'1900-01-01'),5,10),'yyyy-mm-dd')-SYSDATE) num FROM shgc_knsxx WHERE xh=? AND xxsh IN ('һ������','�ر�����','����')",
									new String[] { userName }, "num");
					if (Integer.parseInt(num) <= 0) {
						session.setAttribute("showShgcKnsTs", "show");
					}
				}
			}
			if (tmpAF != null)
				return tmpAF;
		}
		if (!StringUtils.isNull(Base.initProperties.get("fdyCkSbj"))
				&& "true".equalsIgnoreCase(Base.initProperties.get("fdyCkSbj"))) {// ��ʾ�Ƿ���Ҫ���Ƹ���Ա������Դ
			session.setAttribute("showChange", "true");
		}

		// //��ʾ��䣨С���ȣ�
		// String tsyj = Base.getTsyj();
		// session.setAttribute("tsyj", tsyj);

		// ��ݷ�ʽ
		// CommXtwhService xtwhService = new CommXtwhService();
		// List<HashMap<String, String>> kjfsList = xtwhService
		// .getKjfsMenuList(userName);
		// session.setAttribute("kjfsList", kjfsList);

		request.setAttribute("message", "ok");

		// �汾
		String edition = Base.getEdition();
		// �Ƿ���Ҫ�߼���ѯ
		String superSearch = Base.getSuperSearch();

		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);

		// -------------------------2011.9.14
		// qlj-------------------------------------
		// ===================�޸��û���½ʱ�� begin=======================

		// ��ʽ: yyyy-mm-dd HH24:MI:SS
		String dlsj = GetTime.getNowTime4();
		// ���û���½ʱ�����õ�session��
		session.setAttribute("loginTime", dlsj);

		List<String> inputV = new ArrayList<String>();
		StringBuilder updateTime = new StringBuilder();

		String checkTime = " select count(1)num from xg_xtwh_yhdlb where yhm='"
				+ userName + "' and yhlx='" + userType + "' ";
		String num = dao.getOneRs(checkTime.toString(), new String[] {}, "num");

		// ===============�ж��û��Ƿ���� begin================
		if ("0".equalsIgnoreCase(num)) {
			updateTime
					.append(" insert into xg_xtwh_yhdlb(yhm,yhlx,dlsj)values(?,?,?) ");
			inputV.add(userName);
			inputV.add(userType);
			inputV.add(dlsj);
			dao.runUpdate(updateTime.toString(), inputV
					.toArray(new String[] {}));
		} else {
			updateTime
					.append(" update xg_xtwh_yhdlb set dlsj=? where yhm=? and yhlx=? ");
			inputV.add(dlsj);
			inputV.add(userName);
			inputV.add(userType);
			dao.runUpdate(updateTime.toString(), inputV
					.toArray(new String[] {}));
		}
		// ===============�ж��û��Ƿ���� end================
		// ===================�޸��û���½ʱ�� end=======================
		//=======�û���¼��־��¼=======
		LogInfo loginfo = new LogInfo() ;
		UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
		loginfo.setOsName(userAgent.getOperatingSystem().getName());
		loginfo.setBrowserName(userAgent.getBrowser().getName());
		if(userAgent.getBrowserVersion() != null){
			loginfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
		}
		loginfo.setIp(IPRequest.getIpAddress(request));
		loginfo.setDescription("ϵͳ��¼���û�����"+userName);
		loginfo.setClassName(mapping.getType());
		loginfo.setMethodName("userLogin");
		loginfo.setUsername(userName);
		loginfo.setUserType(userType);
		new LogService().runInsert(loginfo);
		new LogService().insertDllog();
		String dljc = XMLReader.getFlowControl("xsxx", "dljc");

		if ("new".equalsIgnoreCase(edition)) {
			if ("student".equalsIgnoreCase(userType)) {

				if ("no".equalsIgnoreCase(dljc)) {
					return mapping.findForward("stuLogin");
				} else {
					return new ActionForward("/xsxx_xsdl.do", false);
				}
			} else if ("teacher".equalsIgnoreCase(userType)) {
				// return mapping.findForward("success");
				return mapping.findForward("teaLogin");
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * @param mapping
	 * @param request
	 * @param session
	 * @param chkUser
	 * @param userType
	 * @param xxmc
	 * @param xxdm
	 * @param sql
	 * @param password
	 * @param userName
	 * @param dbcenterFlag
	 *            ͳһ�����֤��־�����ڼ���Ƿ���ͳһ�����֤����
	 * @return ActionForward
	 */
	private ActionForward checkUsrAndPwd(ActionMapping mapping,
			HttpServletRequest request, HttpSession session,
			CommanForm chkUser, String userType, String xxmc, String xxdm,
			String sql, String password, String userName, boolean dbcenterFlag) {
		DAO dao = DAO.getInstance();
		try {
			String[] userChk = null;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)
					&& "teacher".equals(userType) && !dbcenterFlag) {
				String result = dao.getOneRs(
						"select count(1) cout from fdyxxb where zgh=?",
						new String[] { userName }, "cout");
				if (result != null && !result.trim().equals("")
						&& Integer.parseInt(result) > 0) {
					// ������Ա��������صĸ���Ա��Ϣʱ��Ҫ�����Ӧ�ļ����Ϣ
					Encrypt ept = new Encrypt();
					String tmppwd = ept.encrypt(password);
					String endPointUri = Base.getEndPointUri();// "http://10.128.32.40/zjdxgc/service.asmx?wsdl";
					String qnameStr = "http://www.zf_webservice.com/JSCheckPassword";
					String methodName = "JSCheckPassword";
					String[] paramArr = new String[] { "strJSBH", "strPassword" };
					String soapActionUri = "http://www.zf_webservice.com/JSCheckPassword";
					WebserviceClient wsc = new WebserviceClient(endPointUri,
							qnameStr, methodName, paramArr, soapActionUri);
					if (wsc.getFdyMM(userName, tmppwd)) {
						userChk = dao.getOneRs(
								"select bmdm szbm,xm from fdyxxb where zgh=?",
								new String[] { userName }, new String[] {
										"szbm", "xm", "qx" });
						session.setAttribute("isFdy", true);
					} else {
						chkUser.setErrMsg("�û������������");
						return mapping.findForward("false");
					}
					wsc = null;
					ept = null;
				} else {// ���Ǹ���Ա
					userChk = dao.getOneRs(sql, new String[] { userName,
							password }, new String[] { "szbm", "xm", "qx" });

					session.setAttribute("isFdy", false);
				}
				session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
				session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
				session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
			} else if (userType.equalsIgnoreCase("teacher")) {
				// userChk = dao.getOneRs(
				// "select bmdm szbm,xm from fdyxxb where zgh=?",
				// new String[] { userName },
				// new String[] { "szbm", "xm" });
				// if ((userChk != null) && (userChk.length == 2)) {//
				// ����Ǹ���Ա�������ø���Ա��־

				session.setAttribute("isFdy", Fdypd.isFdy(userName));
				session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
				session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
				session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));

				String sfjryx = Fdypd.checkSfjrXy(userName).get("sfjryx");
				if (!Base.isNull(sfjryx)) {
					session.setAttribute("sfjryx", sfjryx);
					
					// �û�Ȩ��ѡ��Ĭ���ɴ�С
					if ("true".equalsIgnoreCase(sfjryx)){
						session.setAttribute("fdyQx", false);
						session.setAttribute("bzrQx", false);
					}

				} else {
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
					session.setAttribute("isFdy", false);
					session.setAttribute("isBzr", false);
				}
				// } else {
				// session.setAttribute("isFdy", false);
				// }
			} else {
				session.setAttribute("isFdy", false);
				session.setAttribute("fdyQx", false);
				session.setAttribute("isBzr", false);
				session.setAttribute("bzrQx", false);
			}

			String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
			String num = dao.getOneRs(gyglySql, new String[] { userName },
					"num");
			String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
					: "no";

			String sySql = "select sydm from fdyxxb where zgh=?";
			String sydm = dao.getOneRs(sySql, new String[] { userName },
					"sydm");
			String syQx = !Base.isNull(sydm) ? "yes" : "no";
			
			session.setAttribute("syQx", syQx);// �Ƿ�����Ժ�û�
			session.setAttribute("gyglyQx", gyglyQx);// �Ƿ��Ǹ���Ա
			session.setAttribute("isFdyUser", Fdypd.isFdy(userName));// �Ƿ��Ǹ���Ա
			// ������Ǹ���Ա
			// sql ��select bmdm szbm,xm from yhb where yhm=? and kl=?
			if (dbcenterFlag) {// ��������ͳһ�����֤������
				userChk = dao.getOneRs(sql, new String[] { userName },
						new String[] { "szbm", "xm", "qx" });
			} else if (!xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {// ��������Ƽ�ѧԺʱ
				userChk = dao.getOneRs(sql,
						new String[] { userName, password }, new String[] {
								"szbm", "xm", "qx" });
				if(xxdm.equals("10698") && userChk == null && userType.equals("student")){
					sql = "select xm,xydm szbm,'1' qx from view_xsxxb where xh=? and mm=? and xjztm = '��ѧ��'";
					userChk = dao.getOneRs(sql,
							new String[] { userName, password }, new String[] {
									"szbm", "xm", "qx" });
				}
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {// ��ɳ����
				if (userType != null && userType.equalsIgnoreCase("student")) {
					sql = "select bjmc from view_xsjbxx where xh=?";
					session.setAttribute("bjmc", dao.getOneRs(sql,
							new String[] { userName }, "bjmc"));
				}
			}

			if ((userChk != null) && (userChk.length == 3)
					&& ("1".equalsIgnoreCase(userChk[2]))) {

				if (!userType.equalsIgnoreCase("teacher")) {
					sql = "select count(*) sum  from xsfzxxb where xh=?";
					String tmp = dao.getOneRs(sql, new String[] { userName },
							"sum");
					if ((tmp != null) && tmp.equalsIgnoreCase("0")) {
						sql = "insert into xsfzxxb(xh) values(?)";
						dao.runUpdate(sql, new String[] { userName });
					}
				}
				//�ж����������������Ƿ񳬹�5�Σ������������¼2018/08/02 start
				String sqlStr;
				String updateSql;
				if("teacher".equalsIgnoreCase(userType)){
					sqlStr = "select mmcwcs,cwsj from yhb where yhm=? and zdm is not null";
					updateSql = "update yhb set mmcwcs = ?, cwsj = ? where yhm = ? and zdm is not null";
				} else {
					sqlStr = "select mmcwcs,cwsj from xsmmb where xh=?";
					updateSql = "update xsmmb set mmcwcs = ?, cwsj = ? where xh = ?";
				}

				String[] mmcwcs = dao.getOneRs(sqlStr,new String[] { userName }, new String[] {"mmcwcs","cwsj"});
				mmcwcs[0] = mmcwcs[0] == null ? "0" : mmcwcs[0];
				if(cs.equals(mmcwcs[0])){
					Date cwsj = DateUtils.parseDate(mmcwcs[1],new String[]{"yyyy-MM-dd HH:mm:ss"});
					Date nowDate = new Date();
					long timeDiff = nowDate.getTime() - cwsj.getTime();
					long hour = timeDiff/nh;//��������Сʱ
					if(hour >= 24){
						//��ռ�¼
						dao.update(updateSql, new String[]{"0","",userName});
					} else {
						chkUser.setErrMsg("�����������������ޣ��˺ű�������24Сʱ������ٴε�¼��");
						return mapping.findForward("false");
					}
				}
				//��¼�ɹ����֮ǰ��¼
				dao.update(updateSql, new String[]{"0","",userName});
				//�ж����������������Ƿ񳬹�5�Σ������������¼2018/08/02 end

				setComLogonInfo(mapping, session, chkUser, userType, xxmc,
						userName, userChk, request); // ͳһ��֤�������¼��������

			} else {
				// �㽭��ó����ҳ��
				// if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJMZYJSXY)) {
				// String errMsg = "�����Ǵ�ϵͳ���û�������ϵ��ϵͳ�Ĺ���Ա";
				// session.setAttribute("errMsg", errMsg);
				// }

				// ����������ְԺ����ҳ��
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					return new ActionForward("/loginWrongfornblg.jsp", false);
				}

				String tmp = (String) request.getAttribute("ldap");
				if (tmp != null && !tmp.equalsIgnoreCase("")) {
					return new ActionForward("/loginWrongldap.jsp", false);
				}
				if ((userChk != null) && (userChk.length == 3)
						&& ("0".equalsIgnoreCase(userChk[2]))) {
					chkUser.setErrMsg("�û�����Ȩ��δ���ţ���͹���Ա��ϵ");
				} else {

					//��¼����������2018/08/02 start
					String sqlStr;
					String updateSql;
					if("teacher".equalsIgnoreCase(userType)){
						sqlStr = "select mmcwcs from yhb where yhm=? and zdm is not null";
						updateSql = "update yhb set mmcwcs = ?, cwsj = ? where yhm = ? and zdm is not null";
					} else {
						sqlStr = "select mmcwcs from xsmmb where xh=?";
						updateSql = "update xsmmb set mmcwcs = ?, cwsj = ? where xh = ?";
					}

					String[] mmcwcs = dao.getOneRs(sqlStr,new String[] { userName }, new String[] {"mmcwcs"});
					mmcwcs[0] = mmcwcs[0] == null ? "0" : mmcwcs[0];
					if(cs.equals(mmcwcs[0])){
						chkUser.setErrMsg("�����������������ޣ��˺ű�������24Сʱ������ٴε�¼��");
						return mapping.findForward("false");
					}
					int cunum = Integer.valueOf(mmcwcs[0]) + 1;//�������
					String cwsj = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
					dao.update(updateSql, new String[]{String.valueOf(cunum),cwsj,userName});

					chkUser.setErrMsg2("24Сʱ�������������������ܳ���5�Σ�");
					chkUser.setErrMsg("�û������������");
					//��¼����������2018/08/02 end
				}
				if (request.getAttribute("dbCenter") != null
						&& request.getAttribute("dbCenter").equals("yes")) {
					String errMsg = "�����Ǵ�ϵͳ���û�������ϵ��ϵͳ�Ĺ���Ա";
					session.setAttribute("errMsg", errMsg);
					request.setAttribute("returnUri", Base.getInitProperties()
							.get("dbCenterreturnUri"));
					return mapping.findForward("returnMain_err");
				} else {
					return mapping.findForward("false");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("δ֪��������\n" + e.toString());
			return mapping.findForward("false");
		}
		return null;
	}
	private ActionForward checkUsr(ActionMapping mapping,
			HttpServletRequest request, HttpSession session,
			CommanForm chkUser, String userType, String xxmc, String xxdm,
			String sql,String userName) {
		DAO dao = DAO.getInstance();
		try {
			String[] userChk = null;
			if (userType.equalsIgnoreCase("teacher")) {

				session.setAttribute("isFdy", Fdypd.isFdy(userName));
				session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
				session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
				session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));

				String sfjryx = Fdypd.checkSfjrXy(userName).get("sfjryx");
				if (!Base.isNull(sfjryx)) {
					session.setAttribute("sfjryx", sfjryx);
					
					// �û�Ȩ��ѡ��Ĭ���ɴ�С
					if ("true".equalsIgnoreCase(sfjryx)){
						session.setAttribute("fdyQx", false);
						session.setAttribute("bzrQx", false);
					}

				} else {
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
					session.setAttribute("isFdy", false);
					session.setAttribute("isBzr", false);
				}
				// } else {
				// session.setAttribute("isFdy", false);
				// }
			} else {
				session.setAttribute("isFdy", false);
				session.setAttribute("fdyQx", false);
				session.setAttribute("isBzr", false);
				session.setAttribute("bzrQx", false);
			}

			String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
			String num = dao.getOneRs(gyglySql, new String[] { userName },
					"num");
			String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
					: "no";

			String sySql = "select sydm from fdyxxb where zgh=?";
			String sydm = dao.getOneRs(sySql, new String[] { userName },
					"sydm");
			String syQx = !Base.isNull(sydm) ? "yes" : "no";
			
			session.setAttribute("syQx", syQx);// �Ƿ�����Ժ�û�
			session.setAttribute("gyglyQx", gyglyQx);// �Ƿ��Ǹ���Ա
			session.setAttribute("isFdyUser", Fdypd.isFdy(userName));// �Ƿ��Ǹ���Ա
			// ������Ǹ���Ա
		
				userChk = dao.getOneRs(sql,
						new String[] { userName}, new String[] {
								"szbm", "xm", "qx" });
				if(userType.equals("student")){
					sql = "select xm,xydm szbm,'1' qx from view_xsxxb where xh=? and xjztm = '��ѧ��'";
					userChk = dao.getOneRs(sql,
							new String[] { userName}, new String[] {
									"szbm", "xm", "qx" });
				}
			

			
			if ((userChk != null) && (userChk.length == 3)
					&& ("1".equalsIgnoreCase(userChk[2]))) {

				if (!userType.equalsIgnoreCase("teacher")) {
					sql = "select count(*) sum  from xsfzxxb where xh=?";
					String tmp = dao.getOneRs(sql, new String[] { userName },
							"sum");
					if ((tmp != null) && tmp.equalsIgnoreCase("0")) {
						sql = "insert into xsfzxxb(xh) values(?)";
						dao.runUpdate(sql, new String[] { userName });
					}
				}

				setComLogonInfo(mapping, session, chkUser, userType, xxmc,
						userName, userChk, request); // ͳһ��֤�������¼��������

			} 
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("δ֪��������\n" + e.toString());
			return mapping.findForward("false");
		}
		return null;
	}

	private ActionForward userLogout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm chkUser = (CommanForm) form;
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		if ("stu".equalsIgnoreCase(userType)
				|| "student".equalsIgnoreCase(userType)) {
			userType = "userType:student";
		} else {
			userType = "userType:teacher";
		}
		dao.writeLog("", null, null, "�û��˳�", request);
		// session.removeAttribute("userType");
		// session.removeAttribute("userName");
		// session.removeAttribute("userDep");
		// session.removeAttribute("isFdy");
		// session.removeAttribute("isBzr");
		// session.removeAttribute("fdyQx");
		// session.removeAttribute("bzrQx");
		session.invalidate();
		// chkUser.setErrMsg("�Ѿ��ɹ��˳�ϵͳ��");
//		String xxdm = Base.xxdm;
//		if (Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)) {// ��ҵְҵͳһ��ݵȳ�
//			String srcUrl = request.getRequestURL().toString();
//			srcUrl = StringUtils.isNull(srcUrl) ? "" : srcUrl.substring(0,
//					srcUrl.lastIndexOf("/") + 1);
//			srcUrl += "login.jsp";
//			HashMap<String, String> endPointUriMap = Base.getInitProperties();
//			String endUri = endPointUriMap.get("zjsyWebendPointUri");
//			endUri += "?Action=Logout&AppCode=xgxt&CurrentUrl=";
//			endUri += srcUrl;
//			endUri += "&AppAttribute=" + userType + "&hasAppAttribute=false";
//			request.setAttribute("forwardPath", endUri);
//			return new ActionForward("/zjsy.jsp", false);
//		}
		
		if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_JZ_NEW)) {// ��new������ͳһ�����֤�ǳ�
			logger.info("��new������ͳһ�����֤ ���ǳ���ʼ��");
			String srcUrl = request.getRequestURL().toString();
			srcUrl = StringUtils.isNull(srcUrl) ? "" : srcUrl.substring(0,
					srcUrl.lastIndexOf("/") + 1);
			srcUrl += "login.jsp";
//			String casLogoutURL = session.getServletContext().getInitParameter("casServerLogoutUrl");
//			String redirectURL = casLogoutURL+"?service="+URLEncoder.encode(srcUrl);
//			response.sendRedirect(redirectURL);
			
			String redirectURL = srcUrl;
			logger.info("��new�����ǵǳ���ַ��"+redirectURL);
			request.setAttribute("sw_redirect_url", redirectURL);
			return new ActionForward("/sw_redirect.jsp", false);
		}
		if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DK)) {// �����֤
			logger.info("���ͳһ�����֤ ���ǳ���ʼ��");
//			String srcUrl = request.getRequestURL().toString();
//			srcUrl = StringUtils.isNull(srcUrl) ? "" : srcUrl.substring(0,
//					srcUrl.lastIndexOf("/") + 1);
//			srcUrl += "login.jsp";
//			srcUrl += "zfxg_casLogin.do";
//			String redirectURL = srcUrl;
			String redirectURL = "http://cas.jsit.edu.cn/cas/logout";
			logger.info("��Ƶǳ���ַ��"+redirectURL);
			request.setAttribute("sw_redirect_url", redirectURL);
			return new ActionForward("/sw_redirect.jsp", false);
		}
		if (Globals.XXDM_HZSFDX.equalsIgnoreCase(Base.xxdm)) {//��ʦ��֤�˳�����֤��¼��
			String redirectURL = "https://account.ccnu.edu.cn/cas/logout?service=http://218.199.196.168:8080/ssoserver/login?ywxt=xg";
			request.setAttribute("sw_redirect_url", redirectURL);
			return new ActionForward("/sw_redirect.jsp", false);
		}
		return mapping.findForward("false");
	}

	private ActionForward topMenuInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actFwd) throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm menuInit = (CommanForm) form;
		String sql = "";
		HttpSession session = request.getSession();
		String menuListTop = null;
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userOnLine").toString();

		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? and length(gnmkdm)=3 order by xssx";
		} else if (userType.equalsIgnoreCase("student")) {
			sql = "select gnmkdm,gnmkmc from view_yhzqx where zmc=? and length(gnmkdm)=3 order by xssx";
			userName = "ѧ��";
		} else {
			return mapping.findForward("false");
		}

		String[] inputValue = { userName };
		String[] outputValue = { "gnmkdm", "gnmkmc" };
		menuListTop = dao.getStringToSplit(sql, inputValue, outputValue);

		if ("".equalsIgnoreCase(menuListTop)) {
			menuInit.setErrMsg("Err0001");
			return mapping.findForward("false");
		} else if ("err".equalsIgnoreCase(menuListTop)) {
			menuInit.setErrMsg("Err0002");
			return mapping.findForward("false");
		} else {
			request.setAttribute("menuList", menuListTop);
			return mapping.findForward(actFwd);
		}
	}

	private ActionForward leftMenuInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm menuInit = (CommanForm) form;
		String sql = "";
		HttpSession session = request.getSession();
		// String menuListLeft = "";
		String xxdm = session.getAttribute("xxdm").toString();
		String userName = session.getAttribute("userName").toString();
		// userChage:1��ְҵ
		if ("1".equalsIgnoreCase(Base.userChange)) {
			String type = (String) session.getAttribute("type");
			if ("fdy".equalsIgnoreCase(type)) {
				userName += "_fdy";
			} else if ("bzr".equalsIgnoreCase(type)) {
				userName += "_bzr";
			}
		}
		String menuTop = request.getParameter("menuTop");
		String userType = session.getAttribute("userOnLine").toString();
		String jsName = session.getAttribute("userType") == null ? "" : session
				.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy") == null ? "" : session
				.getAttribute("isFdy").toString();

		if (isFdy != null && isFdy.equals("true")) {
			jsName = "fdy";
		}

		if ("".equalsIgnoreCase(menuTop) || (menuTop == null)) {
			menuTop = "N01";
		}
		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select gnmkdm id,fjgndm pid,gnmkmc name,dyym,nvl(dxq,'0') dxq from view_yhqx_dj where yhm=? and substr(gnmkdm,1,3)=? and gnmkdm <> ? order by to_number(xssx),gnmkdm";
			String[] returnRs = getUsrSQL(sql, userName, xxdm, false, Boolean
					.parseBoolean(isFdy));
			sql = returnRs[0];
			userName = returnRs[1];
		} else if (userType.equalsIgnoreCase("student")) {
			sql = "select gnmkdm id,fjgndm pid,gnmkmc name,dyym,nvl(dxq,'0') dxq from view_yhzqx_dj where zmc=? and substr(gnmkdm,1,3)=? and gnmkdm <> ? order by to_number(xssx),gnmkdm";
			userName = "ѧ��";
		} else {
			return mapping.findForward("false");
		}

		String[] inputValue = { userName, menuTop, menuTop };
		String[] outputValue = { "id", "pid", "name", "dyym","dxq" };

		List<HashMap<String, String>> menuList = dao.getList(sql, inputValue, outputValue);
		// menuList����Ϊ�༶�˵��б�
		List<Node> nodeList = lineTypeTraslateTree(menuList);
		String menuHtml = createMenuHtml(nodeList);

		request.setAttribute("userType", jsName);
		request.setAttribute("menuTop", menuTop);
		request.setAttribute("menuHtml", menuHtml);
		return mapping.findForward("success_left");
	}

	/**
	 * ת�����޼����˵�
	 * @param menuList
	 * @return
	 */
	private List<Node> lineTypeTraslateTree(List<HashMap<String, String>> menuList) {
		JSONArray jsonArray = JSONArray.fromObject(menuList);
		List<Node> nodes = JSONArray.toList(jsonArray, Node.class);
		int len = nodes.size(), i = 0, j = 0;
		Map<String, Node> nodeMap = new HashMap<String, Node>(len);
		for (; i < len; i++) {
			nodeMap.put(nodes.get(i).getId(), nodes.get(i));
		}
		List<Node> result = new ArrayList<Node>(len);
		for (; j < len; j++) {
			Node curNode = nodes.get(j), parentNode = nodeMap.get(curNode.getPid());
			if (parentNode == null) {
				result.add(curNode);
			} else {
				if (parentNode.getSubNodes() == null) {
					parentNode.setSubNodes(new ArrayList<Node>());
				}
				parentNode.getSubNodes().add(curNode);
			}
		}
		return result;
	}

	/**
	 * ������ุ�˵�
	 * @param nodeList
	 * @return
	 */
	private String createMenuHtml(List<Node> nodeList) {

		if(nodeList == null||nodeList.size()==0){
			return "";
		}
		StringBuilder menuHtml = new StringBuilder();
		for(Node node:nodeList){
			menuHtml.append("<li class=\"firLi\"><span class=\"title\">");
			menuHtml.append(node.getName());
			menuHtml.append("</span>");
			if(node.getSubNodes() != null && node.getSubNodes().size() != 0){
				menuHtml.append("<i class=\"fa fa-angle-down pull-right\"></i>");
			}
			menuHtml.append(getChildMenuHtml(node.getSubNodes()));
			menuHtml.append("</li>");
		}
		return menuHtml.toString();
	}

	/**
	 * �ݹ鹹������Ӳ˵�
	 * ��ʱû��ʱ�俼�Ƿǵݹ鷽ʽ����ʹ�ݹ鱾��ҲӦ����json����ǰ̨js�ݹ飬
	 * ���кܶ���ʷ�����İ���Ҫ�޸ģ���̨ƴ���ֲ����ŵķ�ʽ����������£�
	 * ������Ч���ݵķ�ʽ
	 * ��ţ�Ƶļ���Ҳ���������������������ĳЩ�˵���֪��
	 * @param nodeList
	 * @return
	 */
	private String getChildMenuHtml(List<Node> nodeList) {

		StringBuilder childMenuHtml = new StringBuilder();
		if(nodeList == null || nodeList.size() == 0){
			return "";
		}

		for(Node node:nodeList){
			childMenuHtml.append("<ul class=\"child-menu\">");
			childMenuHtml.append("<li class=\"nextpage\"><span><a href=\"");
			childMenuHtml.append(node.getDyym()==null?"javascript:void(0)":node.getDyym());
			childMenuHtml.append("\"  target=\"framecon\" class=\"open_03\"><span>");
			childMenuHtml.append(node.getName());
			childMenuHtml.append("</span></a></span></li>");

			if(node.getSubNodes() != null){
				childMenuHtml.append("<i class=\"fa fa-angle-right pull-right\"></i>");
				childMenuHtml.append("<div class=\"posleft\">");
				childMenuHtml.append(getChildMenuHtml(node.getSubNodes()));
				childMenuHtml.append("</div>");
			}

			childMenuHtml.append("</ul>");
		}
		return childMenuHtml.toString();
	}


	/**
	 * @author Administrator
	 * @param sql
	 *            ������Ӧ��sql���������������Ҫ������������ʹ�ã�
	 * @param userName
	 *            ������Ӧ��userName���������������Ҫ������������ʹ�ã�
	 * @param xxdm
	 *            ���ݸ�����ͬ��ѧУ������������������ֵ,��Ҫ�����ǽ�ʦ�û��� ����ȷ���Ǵ��û���Ȩ�ޱ���ȡȨ�޻��Ǵ��û���ȡȨ��
	 * @param pflag
	 *            �ж��Ƿ������˵��� true ��ʾ��ʾ�����˵��ϵ��б�false���Ӳ˵��б�
	 * @param groupflag
	 *            �ж��Ƿ��Ǹ���Ա sql&userName �����ڷ��ص������У����� 0��sql 1��userName
	 */
	public String[] getUsrSQL(String sql, String userName, String xxdm,
			boolean pflag, boolean groupflag) {
		String[] result = new String[2];
		switch (Integer.parseInt(xxdm)) {
		case 11551:
			sql = pflag ? (groupflag ? "select gnmkdm,gnmkmc from view_yhzqx where zmc=? and length(gnmkdm)=3 order by to_number(xssx)"
					: sql)
					: (groupflag ? "select gnmkdm,gnmkmc,nvl(dyym,'#') dyym,nvl(dxq,'0') dxq from view_yhzqx where zmc=? and substr(gnmkdm,1,3)=? and gnmkdm <> ? order by to_number(xssx),gnmkdm"
							: sql);
			// pflag && groupflag ? "select gnmkdm,gnmkmc,nvl(dyym,'#')
			// dyym,nvl(dxq,'0') dxq from view_yhzqx where zmc=? and
			// substr(gnmkdm,1,3)=? order by to_number(xssx),gnmkdm" : "select
			// gnmkdm,gnmkmc from view_yhqx where yhm=? and length(gnmkdm)=3
			// order by to_number(xssx)" ;
			userName = groupflag ? "����Ա" : userName;
			break;
		default:
			;
		}
		result[0] = sql;
		result[1] = userName;
		// logger.debug(sql);
		// logger.debug(userName);
		return result;
	}

	private ActionForward errMsg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String No = Base.chgNull(request.getParameter("No"), " ", 0);

		if (No.equalsIgnoreCase("404")) {
			request.setAttribute("errMsg", "�����ʵ���Դ�����ڣ������Ѿ���ɾ����");
		} else {
			String msg = "";
			if (request.getAttribute("errMsg") != null) {
				msg = request.getAttribute("errMsg").toString();
			}
			request.setAttribute("errMsg", msg);
		}
		request.setAttribute("No", No);
		return mapping.findForward("success");
	}

	private ActionForward chgPass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = null;
		String sql1 = null;
		Encrypt ept = new Encrypt();
		CommanForm chgForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String oldPassword = ept.encrypt(request.getParameter("oldP"));
		String newPassword = ept.encrypt(request.getParameter("newP1"));
		if (userType.equalsIgnoreCase("student")) {
			sql = "select count(*) num from xsmmb where xh=? and mm=?";
			sql1 = "update xsmmb set mm=? where xh=?";
		} else {
			sql = "select count(*) num from yhb where yhm=? and kl=?";
			sql1 = "update yhb set kl=? where yhm=?";
		}
		String[] rs = dao.getOneRs(sql, new String[] { userName, oldPassword },
				new String[] { "num" });
		String resualt = "";
		if (newPassword.length() > 128) {
			resualt = "�����볤�ȹ�������������!";
			return mapping.findForward("success");
		}
		if (rs[0].equalsIgnoreCase("1")) {
			boolean tf = dao.runUpdate(sql1, new String[] { newPassword,
					userName });
			if (tf) {
				resualt = "������³ɹ���";
				// �����޸ĳɹ��� ����session�û�����ȼ�
				session.setAttribute("yhmmdj", request.getParameter("yhmmdj"));
			} else {
				resualt = "�������ʧ�ܣ�";
			}
		} else {
			resualt = "ԭ�����������";
		}
		// request.setAttribute("changed",resualt);
		chgForm.setChanged(resualt);
		return mapping.findForward("success");
	}

	private ActionForward xgMmQz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = null;
		String sql1 = null;
		Encrypt ept = new Encrypt();
		CommanForm chgForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String oldPassword = ept.encrypt(request.getParameter("oldP"));
		String newPassword = ept.encrypt(request.getParameter("newP1"));
		String wtid = request.getParameter("wtid");
		String wtda = request.getParameter("wtda");
		String flag = request.getParameter("flag");
		if (userType.equalsIgnoreCase("student")) {
			sql = "select count(*) num from xsmmb where xh=? and mm=?";
			sql1 = "update xsmmb set mm=? where xh=?";
		} else {
			sql = "select count(*) num from yhb where yhm=? and kl=?";
			sql1 = "update yhb set kl=? where yhm=?";
		}
		String[] rs = dao.getOneRs(sql, new String[] { userName, oldPassword },
				new String[] { "num" });
		String resualt = "";
		if (newPassword.length() > 128) {
			resualt = "�����볤�ȹ�������������!";
			return mapping.findForward("success");
		}
		if (rs[0].equalsIgnoreCase("1")) {
			boolean tf = dao.runUpdate(sql1, new String[] { newPassword,
					userName });
			if(flag.equals("false")){
				tf = dao.runInsert("xg_login_mb", new String[]{"wtid","yhm","wtda"}, new String[]{wtid,userName,wtda});
			}else{
				tf = dao.runUpdate("update xg_login_mb set wtid = ?,wtda = ? where yhm = ?", new String[]{wtid,wtda,userName});
			}
			request.setAttribute("wtda", mmzh.getmbjl(userName).get("wtda"));
			request.setAttribute("wtid", mmzh.getmbjl(userName).get("wtid"));
			request.setAttribute("mbList", mmzh.getMbList());
			if (tf) {
				resualt = "������³ɹ���";
				// �����޸ĳɹ��� ����session�û�����ȼ�
				session.setAttribute("yhmmdj", request.getParameter("yhmmdj"));
			} else {
				resualt = "�������ʧ�ܣ�";
			}
		} else {
			resualt = "ԭ�����������";
		}
		chgForm.setChanged(resualt);
		return mapping.findForward("success");
	}

	private ActionForward chkLoginDbCenter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward tempaf = null;
		// ͳһ�����֤
		MD5 md5 = new MD5();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm chkUser = (CommanForm) form;
		String userName = request.getParameter("userName");
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				"xxmc");
		String xxdm = StandardOperation.getXxdm();
		String userType = request.getParameter("jsName");
		String[] userChk = null;

		logger.debug("xxdm:" + xxdm);
		if (Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)) {
			HashMap<String, String> endPointUriMap = Base.getInitProperties();
			String endUri = endPointUriMap.get("zjsyWebendPointUri");
			String srcUrl = request.getRequestURL().toString();
			srcUrl = StringUtils.isNull(srcUrl) ? "" : srcUrl.substring(0,
					srcUrl.lastIndexOf("/") + 1);
			srcUrl += "chkZjsyLoginDbCenter.do";
			request.setAttribute("forwardPath", endUri
					+ "?Action=Login&AppCode=xgxt&CurrentUrl=" + srcUrl
					+ "&AppAttribute="
					+ "&hasAppAttribute=false&ssoAccount=&ssoPass=");
			// logger.debug(request.getAttribute("forwardPath"));
			return new ActionForward("/zjsy.jsp", false);
		} else if (Globals.XXDM_HNDX.equalsIgnoreCase(xxdm)
				|| Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
			HngyLogin loginClass = new HngyLogin();
			userChk = loginClass.userLogin(userName, request, chkUser, mapping);
			userType = userChk[3];
			setComLogonInfo(mapping, session, chkUser, userType, xxmc,
					userName, userChk, request);
			return mapping.findForward("success");
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)// ���ݴ�ѧ
				|| Globals.XXDM_HYZYJSXY.equalsIgnoreCase(xxdm)// ��Դְҵ����ѧԺ
				|| Globals.XXDM_NGSXY.equalsIgnoreCase(xxdm)// �Ϲ���ѧԺ
				|| Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)// ����ũҵ��ѧ
				|| "10861".equalsIgnoreCase(xxdm)) {// �㶫��ְͨҵ����ѧԺ
			// �㶫��ͨͳһ�����֤�ӿ�
			if ("10861".equalsIgnoreCase(xxdm)) {
				AttributePrincipal principal = request.getUserPrincipal() != null ? (AttributePrincipal) request
						.getUserPrincipal()
						: null;
				if (principal == null) {
					logger.error("δ֪���󣬴�sso��֤�û�ʧ��.....");
					String errMsg = "δ֪���󣬴�sso��֤�û�ʧ��.....";
					session.setAttribute("errMsg", errMsg);
					return mapping.findForward("gzdx_err");
				}
				userName = principal != null ? principal.getName() : "";
			} else {
				userName = (String) session
						.getAttribute(CASFilter.CAS_FILTER_USER);
			}

			logger.info("userName:" + userName);

			GzdxLogin loginClass = new GzdxLogin();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userName", userName);
			// map.put("xxdm", xxdm);
			// map.put("xxmc", xxmc);
			userChk = loginClass.userLogin(map, request, chkUser, mapping);
			if (userChk != null && userChk.length > 0) {
				userType = userChk[3];
				setComLogonInfo(mapping, session, chkUser, userType, xxmc,
						userName, userChk, request);
				// ת��ָ���Ĺ���
				String url = request.getParameter("url");
				String gnmkdm = request.getParameter("gnmkdm");

				String returnUrl = getUrl(dao, userName, url, gnmkdm);
				// �趨�Ǵ�ͳһ�����֤�����ϵͳ
				session.setAttribute("isDbCenter", "yes");
				if (!Base.isNull(returnUrl)) {
					return new ActionForward(returnUrl, true);
				}

				String superSearch = Base.getSuperSearch();
				String edition = Base.getEdition();

				session.setAttribute("edition", edition);
				session.setAttribute("superSearch", superSearch);

				logger.info("edition:" + edition);
				logger.info("superSearch:" + superSearch);

				if ("student".equalsIgnoreCase(userType)) {
					return mapping.findForward("stuLogin");
				} else {
					return mapping.findForward("teaLogin");
				}

			} else {
				String errMsg = "���û������ڣ���ȷ��";
				session.setAttribute("errMsg", errMsg);
				return mapping.findForward("gzdx_err");
			}
		} else if (Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)) {// ����ѧԺ
			SjxyLogin loginClass = new SjxyLogin();
			// ---------2010/6/18 edit by luojw ------------
			String userDz = Base.initProperties.get("sjxyUserDz");
			userName = (String) session.getAttribute(userDz);
			// (String) request.getParameter("userName");
			// ---------2010/6/18 end ------------
			String errMsg = (String) request.getParameter("errMsg");
			if (Base.isNull(userName)) {
				if (Base.isNull(errMsg)) {
					return mapping.findForward("sj");
				} else {
					session.setAttribute("errMsg", DealString.toGBK(errMsg));
					return mapping.findForward("gzdx_err");
				}
			} else {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("userName", userName);
				userChk = loginClass.userLogin(map, request, chkUser, mapping);
				if (userChk != null && userChk.length > 0) {
					userType = userChk[3];
					setComLogonInfo(mapping, session, chkUser, userType, xxmc,
							userName, userChk, request);
					return mapping.findForward("success");
				} else {
					errMsg = "���û������ڣ���ȷ��";
					session.setAttribute("errMsg", DealString.toGBK(errMsg));
					return mapping.findForward("gzdx_err");
				}
			}
		} else if (Globals.XXDM_NJGZZY.equalsIgnoreCase(xxdm)) {// �ߵ�ְҵ����ѧԺ
			NjgzLogin loginClass = new NjgzLogin();
			userChk = loginClass.userLogin(chkUser, request, mapping);
			if (userChk != null && userChk.length > 0) {
				userType = userChk[3];
				userName = userChk[4];
				setComLogonInfo(mapping, session, chkUser, userType, xxmc,
						userName, userChk, request);
				// ת��ָ���Ĺ���
				String url = request.getParameter("url");
				String gnmkdm = request.getParameter("gnmkdm");

				String returnUrl = getUrl(dao, userName, url, gnmkdm);
				if (!Base.isNull(returnUrl)) {
					return new ActionForward(returnUrl, true);
				}
				return mapping.findForward("success");
			} else {
				String errMsg = "���û������ڣ���ȷ��";
				session.setAttribute("errMsg", errMsg);
				return mapping.findForward("gzdx_err");
			}
		} else if (Globals.XXDM_NMGKJDX.equalsIgnoreCase(xxdm)) {// ���ɹſƼ���ѧ

			SjxyLogin loginClass = new SjxyLogin();

			HttpServletRequest req = (HttpServletRequest) request;
			userName = req.getUserPrincipal().getName();

			logger.info("userName:" + userName);

			AttributePrincipalImpl principal = (AttributePrincipalImpl) req
					.getUserPrincipal();
			Map<String, Object> attributes = principal.getAttributes();
			request.setAttribute("userProperties", attributes);
			// �û�������Ϣ
			logger.info("��ȡ�û���ţ�" + attributes.get("pid"));
			logger.info("��ȡ�û�������" + attributes.get("pname"));
			logger.info("��ȡ�û��Ա�" + attributes.get("psex"));
			logger.info("��ȡ�û������ţ�" + attributes.get("topid"));
			logger.info("��ȡ�û��������ƣ�" + attributes.get("topname"));
			logger.info("��ȡ�û�С���ţ�" + attributes.get("typeid"));
			logger.info("��ȡ�û�С�����ƣ�" + attributes.get("typename"));

			String userLx = (String) attributes.get("topid");
			logger.info("userLx:" + userLx);

			// ��ר��ѧ��
			if ("1".equalsIgnoreCase(userLx)) {
				userName = (String) attributes.get("pid");
			}

			logger.info("userName:" + userName);

			String errMsg = (String) request.getParameter("errMsg");
			if (Base.isNull(userName)) {
				if (Base.isNull(errMsg)) {
					return mapping.findForward("sj");
				} else {
					session.setAttribute("errMsg", DealString.toGBK(errMsg));
					return mapping.findForward("gzdx_err");
				}
			} else {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("userName", userName);
				userChk = loginClass.userLogin(map, request, chkUser, mapping);
				if (userChk != null && userChk.length > 0) {
					userType = userChk[3];
					setComLogonInfo(mapping, session, chkUser, userType, xxmc,
							userName, userChk, request);
					return mapping.findForward("success");
				} else {
					errMsg = "���û������ڣ���ȷ��";
					session.setAttribute("errMsg", DealString.toGBK(errMsg));
					return mapping.findForward("gzdx_err");
				}
			}
		} else if (Globals.XXDM_NTGDSF.equals(xxdm)) {// ��ͨ�ߵ�ʦ��
			// ͨ��request��ȡ��֤��Ϣ��
			String ticket = request.getParameter("ticket");
			// ��֤�˻�����ͳһ�����֤ƽ̨�ṩ��Ĭ���˺���valuser�����ͳһ�����֤û���ṩ����ô��ʹ��valuser;
			String uiaRzzh = Base.initProperties.get("valuser");// "valuser";
			// ��֤����Web Service��ַ�����齫wsUrl�ŵ�ϵͳ�����У�
			String wsUrl = Base.initProperties.get("wsUrl");// "http://192.168.101.37:8081/uia/services/AuthenticationInterface?wsdl";
			// ͨ������֤��Ϣ������֤�����ṩ��Web Service�ӿڻ�ȡ�û���Ϣ��ת���ַ�ӿ�
			String json = NtgsLogin.TicketVal(ticket, uiaRzzh, wsUrl);
			String errMsg;
			if (json != null) {
				// ����json�����������Ϣ������ERROR�����Ұ���SUCCESS����ô˵��������ȷ
				// ȥ��SUCCESS
				// json = json.replaceAll("success","");
				// ת��jsonΪJSON����
				// logger.debug("-------------json-start-------------");
				JSONObject jsonObj = JSONObject.fromObject(json);
				// logger.debug("jsonObj:" + jsonObj);
				/*
				 * Iterator<String> it = jsonObj.keys(); while (it.hasNext()) {
				 * String key = it.next(); logger.info("key:" + key);
				 * logger.debug("value:" + jsonObj.get(key)); }
				 * logger.debug("-------------json-end-------------");
				 */

				String success = jsonObj.getString("success");
				if (success.equals("true")) {
					String gotoUrl = jsonObj.getString("gotoUrl");
					// logger.debug("��ǰgotoUrlΪ:"+gotoUrl);
					// Ӧ��ϵͳ�е��û���
					userName = jsonObj.getString("userCode");
					/*
					 * if(goToUrl.equals("")||null==goToUrl||goToUrl.equals("null"
					 * )){ goToUrl = "��Ҫ��дϵͳ��ҳ���磺index.jsp"; }else{
					 */
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("userName", userName);
					// �û���Ϣ
					userChk = NtgsLogin.userLogin(map, request, chkUser,
							mapping);
					if (userChk != null && userChk.length > 0) {
						userType = userChk[3];
						// ����ϵͳ
						setComLogonInfo(mapping, session, chkUser, userType,
								xxmc, userName, userChk, request);

						String edition = Base.initProperties.get("edition");
						session.setAttribute("edition", edition);
						// logger.debug("edition:"+edition);
						if ((null == gotoUrl) || "null".endsWith(gotoUrl)
								|| "".equals(gotoUrl)) {
							if ("stu".equalsIgnoreCase(userType)) {
								return mapping.findForward("stuLogin");
							} else {
								return mapping.findForward("teaLogin");
							}
						} else {
							// logger.debug("������ת��gotoUrl:"+gotoUrl);
							// return mapping.findForward(gotoUrl);
							return new ActionForward(gotoUrl);
						}
					}
				}
				// }
			} else {
				errMsg = "���û������ڣ���ȷ��";
				session.setAttribute("errMsg", DealString.toGBK(errMsg));
				return mapping.findForward("gzdx_err");
			}
		} else if (Globals.XXDM_SDTYXY.equals(xxdm)) {// �׶�����ѧԺ�����¼

			String strRedirecturl = request.getParameter("redirectUrl");

			com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper reqWrapper = 
				new com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper(
					request);
			userName = reqWrapper.getRemoteUser();
			logger.info("strRedirecturl>>>" + strRedirecturl);
			if (strRedirecturl != null && strRedirecturl.length() > 0) {
				response.sendRedirect(strRedirecturl);
			}

			logger.info("userName:" + userName);

			GzdxLogin loginClass = new GzdxLogin();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userName", userName);
			// map.put("xxdm", xxdm);
			// map.put("xxmc", xxmc);
			userChk = loginClass.userLogin(map, request, chkUser, mapping);
			if (userChk != null && userChk.length > 0) {
				userType = userChk[3];
				setComLogonInfo(mapping, session, chkUser, userType, xxmc,
						userName, userChk, request);
				// ת��ָ���Ĺ���
				String url = request.getParameter("url");
				String gnmkdm = request.getParameter("gnmkdm");

				String returnUrl = getUrl(dao, userName, url, gnmkdm);
				// �趨�Ǵ�ͳһ�����֤�����ϵͳ
				session.setAttribute("isDbCenter", "yes");
				if (!Base.isNull(returnUrl)) {
					return new ActionForward(returnUrl, true);
				}

				String superSearch = Base.getSuperSearch();
				String edition = Base.getEdition();

				session.setAttribute("edition", edition);
				session.setAttribute("superSearch", superSearch);

				logger.info("edition:" + edition);
				logger.info("superSearch:" + superSearch);

				if ("student".equalsIgnoreCase(userType)) {
					return mapping.findForward("stuLogin");
				} else {
					return mapping.findForward("teaLogin");
				}

			} else {
				String errMsg = "���û������ڣ���ȷ��";
				session.setAttribute("errMsg", errMsg);
				return mapping.findForward("gzdx_err");
			}
		}

		String sql = "";

		userName = (String) session.getAttribute("userName");
		userType = (String) session.getAttribute("userType");

		logger.info("userName:" + userName);
		logger.info("userType:" + userType);
		// �汾
		String edition = Base.initProperties.get("edition");
		session.setAttribute("edition", edition);
		if ("new".equalsIgnoreCase(edition)) {
			if ("stu".equalsIgnoreCase(userType)) {
				return mapping.findForward("stuLogin");
			} else {
				// return mapping.findForward("success");
				return mapping.findForward("teaLogin");
			}
		}

		// logger.debug(userType);
		String strSysDatetime = request.getParameter("strSysDatetime");
		String verify = request.getParameter("verify");
		if ((userName == null) || (userType == null)
				|| (strSysDatetime == null)) {
			return mapping.findForward("false");
		}

		String strKey = "";
		String time = "";
		sql = "select zfssokey from view_zf_key where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {},
				new String[] { "zfssokey" });
		if (tmp == null) {
			chkUser.setErrMsg("���ù�����Կʧ�ܣ�");
			return mapping.findForward("false");
		}
		strKey = tmp[0];
		sql = "select round(to_number(sysdate - to_date('" + strSysDatetime
				+ "','YYYY-MM-DDHH24:MI:SS'))*1440*60) SYSDATESTR from dual";
		// ������㽭������Զ�������������ĵ����ݿ�
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJGSZYJSXY)) {
			ZjjdDbCenterDAO zjjdDAO = new ZjjdDbCenterDAO();
			tmp = zjjdDAO.getOneRs(sql, new String[] {},
					new String[] { "SYSDATESTR" });
		} else {
			tmp = dao.getOneRs(sql, new String[] {},
					new String[] { "SYSDATESTR" });
		}
		// tmp = dao.getOneRs(sql, new String[] {}, new String[] { "SYSDATESTR"
		// });
		if (tmp == null) {
			chkUser.setErrMsg("���ù���ʱ��ʧ�ܣ�");
			return mapping.findForward("false");
		}
		time = tmp[0];
		if (Integer.parseInt(time) > 60) {
			chkUser.setErrMsg("���������֤��ʱ��");
			return mapping.findForward("false");
		}

		String res = "";
		if (Globals.XXDM_HNDX.equalsIgnoreCase(xxdm)
				|| Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
			res = md5.getMD5ofStr(userName + strKey + strSysDatetime);
		} else {
			res = md5
					.getMD5ofStr(userName + strKey + strSysDatetime + userType);
		}
		if (!res.equalsIgnoreCase(verify)) {
			chkUser.setErrMsg("�����֤ʧ�ܣ�");
			return mapping.findForward("false");
		}
		if (Globals.XXDM_HNDX.equalsIgnoreCase(xxdm)) {
			setComLogonInfo(mapping, session, chkUser, userType, xxmc,
					userName, userChk, request);
			return mapping.findForward("success");
		}
		userType = ((userType != null) && userType
				.equalsIgnoreCase("department")) ? "teacher" : userType;

		request.setAttribute("dbCenter", "yes");
		if (userType.equalsIgnoreCase("student")) {
			sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=? ";
			tempaf = checkUsrAndPwd(mapping, request, session, chkUser,
					userType, xxmc, xxdm, sql, "", userName, true);
			if (tempaf != null)
				return tempaf;
		} else if (userType.equalsIgnoreCase("teacher")) {
			sql = "select szbm,xm,qx from yhb where yhm=? ";
			tempaf = checkUsrAndPwd(mapping, request, session, chkUser,
					userType, xxmc, xxdm, sql, "", userName, true);
			if (tempaf != null)
				return tempaf;
		} else {
			chkUser.setErrMsg("�û����Ͳ���ʶ��");
			return mapping.findForward("false");
		}

		// ת��ָ���Ĺ���
		String url = request.getParameter("url");
		String gnmkdm = request.getParameter("gnmkdm");

		String returnUrl = getUrl(dao, userName, url, gnmkdm);
		if (!Base.isNull(returnUrl)) {
			return new ActionForward(returnUrl, true);
		}

		// �汾
		// String edition = Base.initProperties.get("edition");
		// session.setAttribute("edition", edition);
		// if ("new".equalsIgnoreCase(edition)) {
		// if ("student".equalsIgnoreCase(userType)) {
		// return mapping.findForward("stuLogin");
		// } else if ("teacher".equalsIgnoreCase(userType)) {
		// return mapping.findForward("teaLogin");
		// }
		// }
		if ("new".equalsIgnoreCase(edition)) {
			if ("student".equalsIgnoreCase(userType)) {
				String dljc = XMLReader.getFlowControl("xsxx", "dljc");
				if ("no".equalsIgnoreCase(dljc)) {
					return mapping.findForward("stuLogin");
				} else {
					return new ActionForward("/xsxx_xsdl.do", false);
				}
			} else if ("teacher".equalsIgnoreCase(userType)) {
				// return mapping.findForward("success");
				return mapping.findForward("teaLogin");
			}
		}

		return mapping.findForward("success");
	}

	private String getUrl(DAO dao, String userName, String url, String gnmkdm) {
		if (url != null && !url.trim().equals("")) {
			return "/" + url;
			// return new ActionForward("/" + (url.contains("\\.do") ? url :
			// url+".do"));
		} else if (gnmkdm != null && !gnmkdm.trim().equals("")) {
			String[] dxqAndDyym = dao
					.getOneRs(
							"select a.dxq,b.dyym from yhqxb a,gnmkdmb b where a.gnmkdm=b.gnmkdm and a.gnmkdm=? and a.yhm=?",
							new String[] { gnmkdm, userName }, new String[] {
									"dxq", "dyym" });

			if (dxqAndDyym != null && dxqAndDyym[0] != null
					&& dxqAndDyym[1].trim().equals("1")) {
				return dxqAndDyym[1];
			}
		}
		return null;
	}

	// ������ ͳһ�����֤
	private ActionForward chkXmlgLoginDbCenter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward tempaf = null;
		MD5 md5 = new MD5();
		String userType = null;
		String sql = "";
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		CommanForm chkUser = (CommanForm) form;

		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				"xxmc");// ѧУ����
		String xxdm = StandardOperation.getXxdm(); // ѧУ����

		String userName = request.getParameter("User"); // �û���
		String stamp = request.getParameter("Stamp"); // ʱ���
		String cypt = request.getParameter("Cypt"); // �����ܳ�
		String returnUrl = request.getParameter("Retn"); // ���󷵻�·��
		String stamptime = stamp;// δ�������ʱ���
		// response.sendRedirect(returnUrl);

		session.setAttribute("xmlg_return", returnUrl);// ��Ҫ���صĵ�ַ�Ž�session
		// Ȼ����xmlg_return.jsp��ȡ�����Զ���ת

		ActionForward myAct = new ActionForward("/xmlg_return.jsp", false);

		if (null != stamp) {
			stamp = stamp.replace("-", "");
		}

		String time = ""; // ����ʱ��
		String md5Key = "";// ������Կ
		// ���ù�����Կ�ܳ�
		sql = "select zfssokey from view_zf_key where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {},
				new String[] { "zfssokey" });

		logger.info("userName:" + userName);
		// logger.debug("zfssokey:" + userName);

		if (tmp == null) {
			chkUser.setErrMsg("���ù�����Կʧ�ܣ�");
			session.setAttribute("xmlgmessage", "���ù�����Կʧ�ܣ�");
			return myAct;
		} else {
			md5Key = tmp[0];
		}

		sql = "select round(to_number(sysdate - to_date('" + stamp
				+ "','yyyymmddhh24miss'))*1440*60) SYSDATESTR from view_zf_key";
		tmp = dao.getOneRs(sql, new String[] {}, new String[] { "SYSDATESTR" });
		// �Ƿ�ɹ�����ʱ��
		if (tmp == null) {
			chkUser.setErrMsg("���ù���ʱ��ʧ�ܣ�");
			session.setAttribute("xmlgmessage", "���ù���ʱ��ʧ�ܣ�");
			return myAct;
		}
		// ��֤ʱ���Ƿ񳬹�1����
		time = tmp[0];
		if (Integer.parseInt(time) > 10000) {
			chkUser.setErrMsg("���������֤��ʱ��");
			session.setAttribute("xmlgmessage", "���������֤��ʱ��");
			return myAct;
		}

		// ����û�����
		String searchinfo = dao.getOneRs("select yhm from yhb where yhm=?",
				new String[] { userName }, "yhm");
		if (!(null == searchinfo) && !("".equalsIgnoreCase(searchinfo))) {
			userType = "teacher";
		} else {
			searchinfo = dao.getOneRs("select xh from view_xsjbxx where xh=?",
					new String[] { userName }, "xh");
			if (!(null == searchinfo) && !("".equalsIgnoreCase(searchinfo))) {
				userType = "student";
			} else {
				chkUser.setErrMsg("�û����Ͳ���ʶ��");
				session.setAttribute("xmlgmessage", "�û�����ʶ��");
				return myAct;
			}
		}

		String res = md5.getMD5ofStr(userName + stamptime + md5Key);
		logger.info("res:" + res);

		// ������
		// String res = md5.getMD5ofStr(md5Key);

		if (!res.equalsIgnoreCase(cypt)) {
			chkUser.setErrMsg("�����֤ʧ�ܣ�");
			session.setAttribute("xmlgmessage", "�����֤ʧ�ܣ�");
			return myAct;
		}

		// ��֤�û��Ϸ��Բ���ʼ��
		if ("teacher".equalsIgnoreCase(userType)) {
			sql = "select xm,szbm,qx from yhb where yhm=? ";
		} else if ("student".equalsIgnoreCase(userType)) {
			sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=? ";
		} else {
			sql = "";
		}
		tempaf = checkUsrAndPwd(mapping, request, session, chkUser, userType,
				xxmc, xxdm, sql, "", userName, true);
		if (tempaf != null) {
			return tempaf;
		}

		if ("student".equalsIgnoreCase(userType)
				|| "stu".equalsIgnoreCase(userType)) {
			return mapping.findForward("stuLogin");
		} else {
			return mapping.findForward("teaLogin");
		}

		// return mapping.findForward("success");
	}

	private ActionForward stuPwdInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		Encrypt ept = new Encrypt();
		CommanForm commanForm = (CommanForm) form;
		String xh = request.getParameter("xh");
		String doflag = request.getParameter("doflag");
		String mm = ept.encrypt("888888");
		String sql = "";
		boolean flag = false;
		if (doflag != null && !doflag.equals("")) {
			if (xh == null || xh.equals("")) {
				sql = "update xsmmb set mm = '" + mm + "'";
			} else {
				sql = "update xsmmb set mm = '" + mm + "' where xh = '" + xh
						+ "'";
			}
			flag = dao.runUpdate(sql, new String[] {});
			if (flag == true) {
				commanForm.setChanged("�����ʼ���ɹ�!");
				return mapping.findForward("success");
			}
			return mapping.findForward("false");
		}
		return mapping.findForward("success");
	}

	private ActionForward setComLogonInfo(ActionMapping mapping,
			HttpSession session, CommanForm chkUser, String userType,
			String xxmc, String userName, String[] userChk,
			HttpServletRequest request) throws Exception {
		String sql;
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		/** �洢��¼�û��Ļ�����Ϣ,�洢��session�� */
		session.setAttribute("pjzq", StandardOperation.getCollegePriseCycle());// ѧУ����������,xn
		// Ϊÿѧ����һ��;xq
		// Ϊѧ����һ��
		session.setAttribute("userOnLine", userType);// �û����ͣ�ѧ������ʦ��
		session.setAttribute("userName", userName);// ��½��
		session.setAttribute("userDep", userChk[0]);// �û�����
		session.setAttribute("userNameReal", userChk[1]);// �û�����
		session.setAttribute("xxmc", xxmc);
		// ========2010.10.19 lr========
		// session.setAttribute("userMac",
		// dao.getMacAddressIP(request.getRemoteAddr()));
		// ========end 2010.10.19 lr========
		// ========2012.07.12 yeyp=========
		session.setAttribute("userChange", Base.getUserChange());// <!--
		// �û�Ȩ���л���ͨ�ã�0
		// ��ְҵ��1
		// -->
		// ========2012.07.12 yeyp=========
		// ========2012.02.26 luojw========
		session.setAttribute("userMac", "");
		// ========2012.02.26 luojw========

		// session.setAttribute("isFdy", false);
		// Modify 10.12 LiRiong
		session.setAttribute("xxdm", xxdm);
		session.setAttribute("LoginXn", Base.currXn);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			session.setAttribute("LoginXq", dao.getOneRs(
					"select xqmc from xqdzb where xqdm=? ",
					new String[] { Base.currXq }, "xqmc"));
		} else {
			session.setAttribute("LoginXq", Base.currXq);
		}
		session.setAttribute("LoginZc", StandardOperation.getCurrZc());
		String openType = request.getParameter("openType");
		if ("".equalsIgnoreCase(openType) || openType == null) {
			openType = "2";
		}
		session.setAttribute("openType", openType);

		/** ʶ���û��������� */
		String adminDep = "";
		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];
			if (Base.isNull(adminDep)) {
				chkUser.setErrMsg("�û�����ʶ����������µ�½��");
				return mapping.findForward("false");
			}
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			if (userTmp == null) {
				chkUser.setErrMsg("�û�����ʶ����������µ�½��");
				return mapping.findForward("false");
			}
			session.setAttribute("bmmc", userTmp[1]);
			String sySql = "select sydm from fdyxxb where zgh=?";
			String sydm = dao.getOneRs(sySql, new String[] { userName },
					"sydm");
			String bmlbTmp = "";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				sql = "select bmlb from zxbz_xxbmdm where bmdm=? ";
				bmlbTmp = dao.getOneRs(sql, new String[] { userTmp[0] },
						new String[] { "bmlb" })[0];
			}
			if (userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			} else if (userTmp[2].equalsIgnoreCase("5")
					|| bmlbTmp.equalsIgnoreCase("5")) {
				userType = "xy";
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					session.setAttribute("userDep", userTmp[0]);
				}
			} else {
				userType = "xx";
			}

			if (!Base.isNull(sydm)) {
				userType = "sy";
				//2018/12/07
				session.setAttribute("userSyDep",sydm);
				//2018/12/07
			}
		} else {
			userType = "stu";
		}
		// //��ȡһ������������ڰ�ȫ����У��
		// Random rdm = new Random();
		// session.setAttribute("jymPurview", rdm.nextInt());
		dao.writeLog("", null, null, "�û���¼", request);
		// System.out.print("�û�"+userName+"����");
		session.setAttribute("userType", userType);// �����ࣩ
		if ("1".equalsIgnoreCase(Base.userChange)) {
			session.setAttribute("type", userChange(request));
		}

		// �����û�����ȼ��������û�����ǿ���޸�
		String yhmmdj = request.getParameter("yhmmdj");
		if (!StringUtils.isNull(yhmmdj)) {
			// yhmmdj���Բ�Ϊ��ʱ���ã����ڿ����û��Ǵ�ѧ��ƽ̨��¼��
			session.setAttribute("yhmmdj", yhmmdj);
		}

		return mapping.findForward("success");
	}

	/**
	 * ����û�����
	 * 
	 * @param request
	 * @return
	 */
	private String userChange(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sfjryx = (String) session.getAttribute("sfjryx");
		String userType = (String) session.getAttribute("userType");
		Boolean isFdy = (Boolean) session.getAttribute("isFdy");
		Boolean isBzr = (Boolean) session.getAttribute("isBzr");
		String type = "qt";
		if (sfjryx != null && "true".equalsIgnoreCase(sfjryx)) {
			if ("xy".equalsIgnoreCase(userType)) {
				type = "xy";
			} else if ("xx".equalsIgnoreCase(userType)) {
				type = "xx";
			}
		} else {
			if (isFdy.booleanValue()) {
				type = "fdy";
			} else if (isBzr.booleanValue()) {
				type = "bzr";
			}
		}

		return type;
	}

	public String[] userLoginForPage(String userName) {
		// TODO ѧ����֤
		DAO dao = DAO.getInstance();
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				"xxmc");
		// String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String chkUser = "";
		String isFdy = "no";
		String isBzr = "no";
		String fdyQx = "no";
		String bzrQx = "no";
		String[] userTmp = null;
		String[] userChk = null;
		String[] sfjsChk = null;
		String userType = "";
		String count = dao.getOneRs(
				"select count(1) cnt from view_xsjbxx where xh = ?",
				new String[] { userName }, "cnt");
		if ("1".equalsIgnoreCase(count)) {
			userType = "student";
		} else {
			userType = "teacher";
		}
		if (userType.equalsIgnoreCase("student")) {
			sql = "select xm,bmdm szbm from view_xsjbxx where xh=?";
		} else if (userType.equalsIgnoreCase("teacher")) {
			sql = "select szbm,xm from yhb where yhm=?";
		} else {
			chkUser = "�û����Ͳ���ʶ��";
		}
		userChk = dao.getOneRs(sql, new String[] { userName }, new String[] {
				"szbm", "xm" });
		if (userChk == null) {
			userChk = new String[] { "", "" };
		}
		if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			sfjsChk = dao.getOneRs(
					"select bmdm szbm,xm from fdyxxb where zgh=?",
					new String[] { userName }, new String[] { "szbm", "xm" });
			if ((sfjsChk != null) && (sfjsChk.length == 2)) {// ����Ǹ���Ա�������ø���Ա��־
				isFdy = "yes";
			} else {
				isFdy = "no";
			}
		} else {
			if (Fdypd.isFdy(userName)) {
				isFdy = "yes";
			} else {
				isFdy = "no";
			}
			if (Fdypd.isBzr(userName, "")) {
				isBzr = "yes";
			} else {
				isBzr = "no";
			}
			if (Fdypd.fdybjck(userName)) {
				fdyQx = "yes";
			} else {
				fdyQx = "no";
			}
			if (Fdypd.bzrbjck(userName)) {
				bzrQx = "yes";
			} else {
				bzrQx = "no";
			}
		}
		String adminDep = "";
		String userOnLine = userType;

		logger.info(userType);

		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];
			if (Base.isNull(adminDep)) {
				chkUser = "�û�����ʶ����������µ�½��";

			}
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			if (userTmp == null) {
				chkUser = "�û�����ʶ����������µ�½��";
				return new String[] { chkUser,
						StandardOperation.getCollegePriseCycle(), userOnLine,
						userName, "", userChk[1], xxmc,
						StandardOperation.getXxdm(), Base.currXn, Base.currXq,
						StandardOperation.getCurrZc(), "2", "", isFdy, userType };
			}
			sql = "select bmdm from bks_zydm where bmdm = ?";
			String[] userTmp2 = dao.getOneRs(sql, new String[] { userTmp[0] },
					new String[] { "bmdm" });
			if (userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			} else if (userTmp2 != null) {
				userType = "xy";
			} else {
				userType = "xx";
			}
		} else {
			userType = "stu";
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] temp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			userTmp = new String[] { userChk[1], temp[1], "" };
		}
		return new String[] { chkUser,
				StandardOperation.getCollegePriseCycle(), userOnLine, userName,
				userTmp[0], userChk[1], xxmc, StandardOperation.getXxdm(),
				Base.currXn, Base.currXq, StandardOperation.getCurrZc(), "2",
				userTmp[1], isFdy, userType, isBzr, fdyQx, bzrQx };
	}

	/**
	 * �㽭��ҵͳһ��ݵ�½��֤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chkZjsyLoginDbCenter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		CommanForm chkUser = (CommanForm) form;
		String appAccount = request.getParameter("appAccount");// �û���
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();// session�Ƿ�����û���
		String[] userChk = new String[2];
		String userType = request.getParameter("userType");

		String verify = request.getParameter("Verify");// ͳһ�����֤��־ falseΪ��֤ʧ��
		// String ssoAccount = request.getParameter("ssoAccount");//��֤ʧ�ܴ����û���
		// String ssoPass = request.getParameter("ssoPass");//��֤ʧ�ܴ�������
		// logger.debug("verify:  " + verify);

		if (StringUtil.isNull(verify)) {
			// logger.debug("���Ż��������ٴ���ת���Ż�SSO......");
			HashMap<String, String> endPointUriMap = Base.getInitProperties();
			String endUri = endPointUriMap.get("zjsyWebendPointUri");
			String realPath = request.getRequestURL().toString();
			String appAtt = StringUtils.isNull(userType) ? ""
					: ("teacher".equalsIgnoreCase(userType) ? "userType:teacher"
							: ("student".equalsIgnoreCase(userType) ? "userType:student"
									: ""));
			boolean hasApp = StringUtils.isNull(appAtt) ? false : true;
			if (!StringUtils.isNull(realPath)) {
				realPath = realPath.substring(0, realPath.lastIndexOf("/") + 1);
			} else {
				realPath = "";
			}
			// ·��ת��ͳһ��֤��ַ
			request.setAttribute("forwardPath", endUri
					+ "?Action=Login&AppCode=xgxt&CurrentUrl=" + realPath
					+ "chkZjsyLoginDbCenter.do&AppAttribute=" + appAtt
					+ "&hasAppAttribute=" + hasApp + "&ssoAccount="
					+ "&ssoPass=");
			return new ActionForward("/zjsy.jsp", false);

		}

		if ("False".equalsIgnoreCase(verify)) {// ��֤ʧ�ܽ���ѧ�������֤
			String xxmc = dao.getOneRs("select xxmc from xtszb",
					new String[] {}, "xxmc");
			String xxdm = StandardOperation.getXxdm();
			String sql = "";
			String xnOrGdFlag = dao.getOneRs("select jwflag from xtszb",
					new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ
			xnOrGdFlag = StringUtils.isNull(xnOrGdFlag) ? "" : xnOrGdFlag
					.trim();
			ActionForward tmpAF;

			String uName = zjsyWebServiceDecrypto(appAccount);
			// logger.debug("======>"+appAccount+"========>" + uName +
			// "======>"+userType);

			String uType = session.getAttribute("uType") == null ? "" : session
					.getAttribute("uType").toString();
			String jName = session.getAttribute("jName") == null ? "" : session
					.getAttribute("jName").toString();
			String pWord = session.getAttribute("pWord") == null ? "" : session
					.getAttribute("pWord").toString();
			Encrypt ept = new Encrypt();
			if ((xnOrGdFlag != null && xnOrGdFlag.trim().equals("1"))
					&& uType.equalsIgnoreCase("student")) {

			} else {
				pWord = ept.encrypt(pWord);
			}

			if (uType.equalsIgnoreCase("student")) {
				sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=? and mm=?";
				tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
						uType, xxmc, xxdm, sql, pWord, uName, false);
			} else if (uType.equalsIgnoreCase("teacher")
					|| ((jName != null) && jName.equalsIgnoreCase("admin"))) {
				sql = "select szbm,xm,qx from yhb where yhm=? and kl=?";
				tmpAF = checkUsrAndPwd(mapping, request, session, chkUser,
						uType, xxmc, xxdm, sql, pWord, uName, false);
			} else {
				chkUser.setErrMsg("�û����Ͳ���ʶ��");
				return mapping.findForward("false");
			}

			// �汾
			String edition = Base.getEdition();
			// �Ƿ���Ҫ�߼���ѯ
			String superSearch = Base.getSuperSearch();
			String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
			String num = dao.getOneRs(gyglySql, new String[] { userName },
					"num");
			String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
					: "no";
			session.setAttribute("gyglyQx", gyglyQx);
			session.setAttribute("edition", edition);
			session.setAttribute("superSearch", superSearch);
			session.setAttribute("isFdyUser", false);
			session.setAttribute("isFdy", false);
			session.setAttribute("isBzr", false);
			session.setAttribute("fdyQx", false);
			session.setAttribute("bzrQx", false);
			session.setAttribute("LoginXq", Base.currXq);
			session.setAttribute("LoginZc", StandardOperation.getCurrZc());
			session.setAttribute("openType", "");
			session.setAttribute("loginTime", "");
			session.setAttribute("LoginXn", Base.currXn);
			session.setAttribute("pjzq", StandardOperation
					.getCollegePriseCycle());// ѧУ����������,xn
			session.setAttribute("istysfrz", "false");

			// ��ת����ҳ
			if (tmpAF != null)
				return tmpAF;

		} else {
			if (!StringUtils.isNull(userName)) {// ���ڶ��û����������֤
				String teacherInfo = dao.getOneRs(
						"select yhm from yhb where yhm=?",
						new String[] { userName }, "yhm");
				if (!StringUtils.isNull(teacherInfo)) {// �û�����ǽ�ʦ
					userType = "teacher";
				} else {
					String studentInfo = dao.getOneRs(
							"select xh from view_xsjbxx where xh=?",
							new String[] { userName }, "xh");
					if (!StringUtils.isNull(studentInfo)) {// �û������ѧ��
						userType = "student";
					} else {
						chkUser.setErrMsg("�û�����ʶ����������µ�½��");
						return mapping.findForward("false");
					}
				}
				String[] userInfo = new String[2];
				if ("teacher".equalsIgnoreCase(userType)) {
					userInfo = dao.getOneRs(
							"select xm,szbm from yhb where yhm=?",
							new String[] { userName }, new String[] { "xm",
									"szbm" });
					if (userInfo != null && userInfo.length == 2
							&& !StringUtils.isNull(userInfo[1])) {
						userChk[0] = userInfo[1];
						userChk[1] = userInfo[0];
					} else {
						chkUser.setErrMsg("�û�����ʶ����������µ�½��");
						return mapping.findForward("false");
					}
				} else if ("student".equalsIgnoreCase(userType)) {
					userInfo = dao.getOneRs(
							"select xm,bmdm szbm from view_xsjbxx where xh=?",
							new String[] { userName }, new String[] { "xm",
									"szbm" });
					if (userInfo != null && userInfo.length == 2
							&& !StringUtils.isNull(userInfo[1])) {
						userChk[0] = userInfo[1];
						userChk[1] = userInfo[0];
					} else {
						chkUser.setErrMsg("�û�����ʶ����������µ�½��");
						return mapping.findForward("false");
					}
				}
				String[] fdyInfo = dao.getOneRs(
						"select bmdm szbm,xm from fdyxxb where zgh=?",
						new String[] { userName },
						new String[] { "szbm", "xm" });
				if ((fdyInfo != null) && (fdyInfo.length == 2)) {// ����Ǹ���Ա�������ø���Ա��־
					session.setAttribute("isFdy", true);
					session.setAttribute("fdyQx", true);
					session.setAttribute("bzrQx", true);
				} else {
					session.setAttribute("isFdy", false);
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
				}

				setComLogonInfo(mapping, session, chkUser, userType, Base.xxmc,
						userName, userChk, request);// �����û���Ϣ
				// �汾
				String edition = Base.getEdition();
				// �Ƿ���Ҫ�߼���ѯ
				String superSearch = Base.getSuperSearch();
				String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
				String num = dao.getOneRs(gyglySql, new String[] { userName },
						"num");
				String gyglyQx = !Base.isNull(num)
						&& !"0".equalsIgnoreCase(num) ? "yes" : "no";
				session.setAttribute("gyglyQx", gyglyQx);
				session.setAttribute("edition", edition);
				session.setAttribute("superSearch", superSearch);
				session.setAttribute("gyglyQx", gyglyQx);
				session.setAttribute("edition", edition);
				session.setAttribute("superSearch", superSearch);
				session.setAttribute("isFdyUser", false);
				session.setAttribute("isFdy", false);
				session.setAttribute("isBzr", false);

				session.setAttribute("LoginXq", Base.currXq);
				session.setAttribute("LoginZc", StandardOperation.getCurrZc());
				session.setAttribute("openType", "");
				session.setAttribute("loginTime", "");
				session.setAttribute("LoginXn", Base.currXn);
				session.setAttribute("pjzq", StandardOperation
						.getCollegePriseCycle());// ѧУ����������,xn
				session.setAttribute("istysfrz", "false");

			} else {// �����ھ��ж��Ƿ����appAccount����
				// logger.debug("appcount : " + appAccount + "11");
				if (!StringUtils.isNull(appAccount)) {// �ǿն��û����������֤

					userName = zjsyWebServiceDecrypto(appAccount);

					// DES2 des2 = new DES2();//��,����Ҫ�õ�����
					// byte[] stringToByte =
					// des2.stringToByte(appAccount);//��STRINGתΪBYTE
					// byte[] decryptorByte =
					// des2.createDecryptor(stringToByte);//��ת����Ľ���ΪBYTE
					// userName = new
					// String(decryptorByte).toString();//��BYTEתΪSTRING
					// logger.debug("appcount : " + appAccount +
					// "--------username : " + userName);
					// ���ڶ��û����������֤
					String teacherInfo = dao.getOneRs(
							"select yhm from yhb where yhm=?",
							new String[] { userName }, "yhm");
					if (!StringUtils.isNull(teacherInfo)) {// �û�����ǽ�ʦ
						userType = "teacher";
					} else {
						String studentInfo = dao.getOneRs(
								"select xh from view_xsjbxx where xh=?",
								new String[] { userName }, "xh");
						if (!StringUtils.isNull(studentInfo)) {// �û������ѧ��
							userType = "student";
						} else {
							chkUser.setErrMsg("�û�����ʶ����������µ�½��");
							return mapping.findForward("false");
						}
					}
					String[] userInfo = new String[2];
					if ("teacher".equalsIgnoreCase(userType)) {
						userInfo = dao.getOneRs(
								"select xm,szbm from yhb where yhm=?",
								new String[] { userName }, new String[] { "xm",
										"szbm" });
						if (userInfo != null && userInfo.length == 2
								&& !StringUtils.isNull(userInfo[1])) {
							userChk[0] = userInfo[1];
							userChk[1] = userInfo[0];
						} else {
							chkUser.setErrMsg("�û�����ʶ����������µ�½��");
							return mapping.findForward("false");
						}
					} else if ("student".equalsIgnoreCase(userType)) {
						userInfo = dao
								.getOneRs(
										"select xm,bmdm szbm from view_xsjbxx where xh=?",
										new String[] { userName },
										new String[] { "xm", "szbm" });
						if (userInfo != null && userInfo.length == 2
								&& !StringUtils.isNull(userInfo[1])) {
							userChk[0] = userInfo[1];
							userChk[1] = userInfo[0];
						} else {
							chkUser.setErrMsg("�û�����ʶ����������µ�½��");
							return mapping.findForward("false");
						}
					}

					String[] fdyInfo = dao.getOneRs(
							"select bmdm szbm,xm from fdyxxb where zgh=?",
							new String[] { userName }, new String[] { "szbm",
									"xm" });
					if ((fdyInfo != null) && (fdyInfo.length == 2)) {// ����Ǹ���Ա�������ø���Ա��־
						session.setAttribute("isFdy", true);
						session.setAttribute("fdyQx", true);
					} else {
						session.setAttribute("isFdy", false);
						session.setAttribute("fdyQx", false);
					}

					setComLogonInfo(mapping, session, chkUser, userType,
							Base.xxmc, userName, userChk, request);// �����û���Ϣ

					// �汾
					String edition = Base.getEdition();
					// �Ƿ���Ҫ�߼���ѯ
					String superSearch = Base.getSuperSearch();
					String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
					String num = dao.getOneRs(gyglySql,
							new String[] { userName }, "num");
					String gyglyQx = !Base.isNull(num)
							&& !"0".equalsIgnoreCase(num) ? "yes" : "no";
					session.setAttribute("gyglyQx", gyglyQx);
					session.setAttribute("edition", edition);
					session.setAttribute("superSearch", superSearch);

					session.setAttribute("isFdyUser", false);
					session.setAttribute("isFdy", false);
					session.setAttribute("isBzr", false);
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
					session.setAttribute("LoginXq", Base.currXq);
					session.setAttribute("LoginZc", StandardOperation
							.getCurrZc());
					session.setAttribute("openType", "");
					session.setAttribute("loginTime", "");
					session.setAttribute("LoginXn", Base.currXn);
					session.setAttribute("pjzq", StandardOperation
							.getCollegePriseCycle());// ѧУ����������,xn
					session.setAttribute("istysfrz", "false");

				} else {// Ϊ������ѧ���Լ�����֤������֤
					// HashMap<String, String> endPointUriMap =
					// Base.getInitProperties();
					// String endUri = endPointUriMap.get("zjsyWebendPointUri");
					// String realPath = request.getRequestURL().toString();
					// if (!StringUtils.isNull(realPath)) {
					// realPath = realPath.substring(0,
					// realPath.lastIndexOf("/")+1);
					// } else {
					// realPath = "";
					// }
					// request.setAttribute("forwardPath", endUri
					// + "?Action=Login&AppCode=xgxt&CurrentUrl=" + realPath
					// + "chkZjsyLoginDbCenter.do&AppAttribute="
					// + "&hasAppAttribute=false");
					// return new ActionForward("/zjsy.jsp", false);

					String xxmc = dao.getOneRs("select xxmc from xtszb",
							new String[] {}, "xxmc");
					String xxdm = StandardOperation.getXxdm();
					String sql = "";
					String xnOrGdFlag = dao.getOneRs(
							"select jwflag from xtszb", new String[] {},
							"jwflag");// �����ѧ��ľͻ��1,������ֵ
					xnOrGdFlag = StringUtils.isNull(xnOrGdFlag) ? ""
							: xnOrGdFlag.trim();
					ActionForward tmpAF;

					String uName = session.getAttribute("uName") == null ? ""
							: session.getAttribute("uName").toString();
					String uType = session.getAttribute("uType") == null ? ""
							: session.getAttribute("uType").toString();
					String jName = session.getAttribute("jName") == null ? ""
							: session.getAttribute("jName").toString();
					String pWord = session.getAttribute("pWord") == null ? ""
							: session.getAttribute("pWord").toString();
					Encrypt ept = new Encrypt();
					if ((xnOrGdFlag != null && xnOrGdFlag.trim().equals("1"))
							&& uType.equalsIgnoreCase("student")) {

					} else {
						pWord = ept.encrypt(pWord);
					}

					// �汾
					String edition = Base.getEdition();
					// �Ƿ���Ҫ�߼���ѯ
					String superSearch = Base.getSuperSearch();
					String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
					String num = dao.getOneRs(gyglySql,
							new String[] { userName }, "num");
					String gyglyQx = !Base.isNull(num)
							&& !"0".equalsIgnoreCase(num) ? "yes" : "no";
					session.setAttribute("gyglyQx", gyglyQx);
					session.setAttribute("edition", edition);
					session.setAttribute("superSearch", superSearch);

					session.setAttribute("isFdyUser", false);
					session.setAttribute("isFdy", false);
					session.setAttribute("isBzr", false);
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
					session.setAttribute("LoginXq", Base.currXq);
					session.setAttribute("LoginZc", StandardOperation
							.getCurrZc());
					session.setAttribute("openType", "");
					session.setAttribute("loginTime", "");
					session.setAttribute("LoginXn", Base.currXn);
					session.setAttribute("pjzq", StandardOperation
							.getCollegePriseCycle());// ѧУ����������,xn
					session.setAttribute("istysfrz", "false");

					if (uType.equalsIgnoreCase("student")) {
						sql = "select xm,bmdm szbm,'1' qx from view_xsjbxx where xh=? and mm=?";
						tmpAF = checkUsrAndPwd(mapping, request, session,
								chkUser, uType, xxmc, xxdm, sql, pWord, uName,
								false);
					} else if (uType.equalsIgnoreCase("teacher")
							|| ((jName != null) && jName
									.equalsIgnoreCase("admin"))) {
						sql = "select szbm,xm,qx from yhb where yhm=? and kl=?";
						tmpAF = checkUsrAndPwd(mapping, request, session,
								chkUser, uType, xxmc, xxdm, sql, pWord, uName,
								false);
					} else {
						chkUser.setErrMsg("�û����Ͳ���ʶ��");
						return mapping.findForward("false");
					}
					if (tmpAF != null)
						return tmpAF;

				}
			}
		}
		if (session.getAttribute("uName") != null) {
			session.removeAttribute("uName");
		}
		if (session.getAttribute("uType") != null) {
			session.removeAttribute("uType");
		}
		if (session.getAttribute("jName") != null) {
			session.removeAttribute("jName");
		}
		if (session.getAttribute("pWord") != null) {
			session.removeAttribute("pWord");
		}

		logger.info("userType:  " + userType);
		if ("student".equalsIgnoreCase(userType)
				|| "stu".equalsIgnoreCase(userType)) {
			return mapping.findForward("stuLogin");
		} else {
			return mapping.findForward("teaLogin");
		}

		// return mapping.findForward("success");
	}

	/**
	 * �㽭��ҵͳһ��½
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chkZjsytjDl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forwardPath = request.getParameter("forwardPaths");
		forwardPath += "&Action=Login&AppCode=xgxt&AppAttribute=userType:teacher&hasAppAttribute=true";
		request.setAttribute("forwardPath", forwardPath);
		return mapping.findForward("success");
	}

	/**
	 * ����Ա�����л�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward fdyUserChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		CommanForm myform = (CommanForm) form;
		String urls = myform.getUrls();
		String fdyQx = session.getAttribute("fdyQx").toString();
		Object isDbCenter = session.getAttribute("isDbCenter");
		if (fdyQx != null && "true".equalsIgnoreCase(fdyQx)) {
			// session.setAttribute("isFdy", "false");
			session.setAttribute("fdyQx", "false");
			// session.setAttribute("isFdy", "false");
			session.setAttribute("bzrQx", "false");
			// session.setAttribute("isBzr", "false");
		}
		if (fdyQx != null && "false".equalsIgnoreCase(fdyQx)) {
			// session.setAttribute("isFdy", "true");
			session.setAttribute("fdyQx", "true");
			// session.setAttribute("isFdy", "true");
			session.setAttribute("bzrQx", "true");
			// session.setAttribute("isBzr", "true");
		}

		session.setAttribute("userNjList", null);
		session.setAttribute("userXyList", null);
		session.setAttribute("userZyPyList", null);
		session.setAttribute("userZyList", null);
		session.setAttribute("userBjList", null);
		if (isDbCenter != null) {
			return new ActionForward("/main2.jsp", false);
		}
		return new ActionForward(urls, false);
	}

	/**
	 * �л��û�Ȩ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward userTypeChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		CommanForm myform = (CommanForm) form;
		String urls = myform.getUrls();

		String qhlx = request.getParameter("qhlx");

		Object isDbCenter = session.getAttribute("isDbCenter");

		if ("isFdy".equalsIgnoreCase(qhlx)) {
			session.setAttribute("fdyQx", "true");
			session.setAttribute("bzrQx", "false");
		} else if ("isBzr".equalsIgnoreCase(qhlx)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "true");
		} else if ("isJd".equalsIgnoreCase(qhlx)) {
			session.setAttribute("fdyQx", "true");
			session.setAttribute("bzrQx", "true");
		} else if ("isXy".equalsIgnoreCase(qhlx)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "false");
		} else if ("isSy".equalsIgnoreCase(qhlx)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "false");
		} else if ("isXx".equalsIgnoreCase(qhlx)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "false");
		}

		session.setAttribute("userNjList", null);
		session.setAttribute("userXyList", null);
		session.setAttribute("userZyPyList", null);
		session.setAttribute("userZyList", null);
		session.setAttribute("userBjList", null);
		session.setAttribute("qhlx", qhlx);
		request.setAttribute("qhlx", qhlx);
		if (isDbCenter != null) {
			return new ActionForward("/main2.jsp", false);
		}
		return new ActionForward(urls, false);
	}

	/**
	 * �л��û�Ȩ�޲˵�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward userMenuChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		Object isDbCenter = session.getAttribute("isDbCenter");
		String type = request.getParameter("type");
		if ("fdy".equalsIgnoreCase(type)) {
			session.setAttribute("fdyQx", "true");
			session.setAttribute("bzrQx", "false");
		} else if ("bzr".equalsIgnoreCase(type)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "true");
		} else if ("xy".equalsIgnoreCase(type)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "false");
		} else if ("sy".equalsIgnoreCase(type)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "false");
		} else if ("xx".equalsIgnoreCase(type)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "false");
		}
		session.setAttribute("type", type);
		session.setAttribute("userNjList", null);
		session.setAttribute("userXyList", null);
		session.setAttribute("userZyPyList", null);
		session.setAttribute("userZyList", null);
		session.setAttribute("userBjList", null);
		if (isDbCenter != null) {
			return new ActionForward("/main2.jsp", false);
		}
		return new ActionForward("/teaPage.jsp", false);
	}

	/**
	 * ����ʦ��ѧԺ�ϵ��¼�ӿ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward hnsfSSOLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		CommanForm chkUser = (CommanForm) form;

		// ��ȡ��֤�����ṩ�������ļ���Ϣ
		// ���Ե�ʱ�������TOMCAT��װ·���пո�Ļ������ܻ���ֶ�ȡʧ�ܵ����⡣����ʵʩ��ʱ��Ҫע���ȡ·���в����пո�
		String is_config = StringUtil.decodeURIPath("hnsfclient.properties");

		if (!StringUtils.isNull(is_config)) {
			// ���ǵ�·���ո������,�����%20ȫ���滻Ϊ�ո�.(%20ת��Ϊ�ո�)
			is_config = is_config.replaceAll("%20", " ");
		}

		// ���´����ǵ��õ�����˾�ṩ�Ĵ��룬���ڻ�ȡ�û���
		Cookie all_cookies[] = request.getCookies();
		Cookie myCookie;
		String decodedCookieValue = null;
		if (all_cookies != null) {
			for (int i = 0; i < all_cookies.length; i++) {
				myCookie = all_cookies[i];
				if ("iPlanetDirectoryPro".equalsIgnoreCase(myCookie.getName())) {
					decodedCookieValue = URLDecoder.decode(myCookie.getValue(),
							"GB2312");
				}
			}
		}

		IdentityFactory factory = IdentityFactory.createFactory(is_config);
		IdentityManager im = factory.getIdentityManager();

		String userName = "";
		if (decodedCookieValue != null) {
			userName = im.getCurrentUser(decodedCookieValue);// ��ȡ�û���
		}

		if (StringUtils.isNull(userName)) {// ��֤ʧ�ܷ��ز���ʾ
			request.setAttribute("errMsg", "���û���Ϣ��֤ʧ�ܣ�����ϵ����Ա��");
			return mapping.findForward("false");
		} else {

			// ���ڶ��û����������֤
			String teacherInfo = dao.getOneRs(
					"select yhm from yhb where yhm=?",
					new String[] { userName }, "yhm");
			String userType = "";
			if (!StringUtils.isNull(teacherInfo)) {// �û�����ǽ�ʦ
				userType = "teacher";
			} else {
				String studentInfo = dao.getOneRs(
						"select xh from view_xsjbxx where xh=?",
						new String[] { userName }, "xh");
				if (!StringUtils.isNull(studentInfo)) {// �û������ѧ��
					userType = "student";
				} else {
					request.setAttribute("errMsg", "���û�����ʶ����������µ�½������ϵ����Ա��");
					return mapping.findForward("false");
				}
			}
			String[] userInfo = new String[2];
			String[] userChk = new String[2];
			if ("teacher".equalsIgnoreCase(userType)) {
				userInfo = dao.getOneRs("select xm,szbm from yhb where yhm=?",
						new String[] { userName },
						new String[] { "xm", "szbm" });
				if (userInfo != null && userInfo.length == 2
						&& !StringUtils.isNull(userInfo[1])) {
					userChk[0] = userInfo[1];
					userChk[1] = userInfo[0];
				} else {
					request.setAttribute("errMsg", "���û�����ʶ����������µ�½������ϵ����Ա��");
					return mapping.findForward("false");
				}
			} else if ("student".equalsIgnoreCase(userType)) {
				userInfo = dao.getOneRs(
						"select xm,bmdm szbm from view_xsjbxx where xh=?",
						new String[] { userName },
						new String[] { "xm", "szbm" });
				if (userInfo != null && userInfo.length == 2
						&& !StringUtils.isNull(userInfo[1])) {
					userChk[0] = userInfo[1];
					userChk[1] = userInfo[0];
				} else {
					request.setAttribute("errMsg", "���û�����ʶ����������µ�½������ϵ����Ա��");
					return mapping.findForward("false");
				}
			}
			String[] fdyInfo = dao.getOneRs(
					"select bmdm szbm,xm from fdyxxb where zgh=?",
					new String[] { userName }, new String[] { "szbm", "xm" });
			if ((fdyInfo != null) && (fdyInfo.length == 2)) {// ����Ǹ���Ա�������ø���Ա��־
				session.setAttribute("isFdy", true);
				session.setAttribute("fdyQx", true);
			} else {
				session.setAttribute("isFdy", false);
				session.setAttribute("fdyQx", false);
			}

			setComLogonInfo(mapping, session, chkUser, userType, Base.xxmc,
					userName, userChk, request);// �����û���Ϣ
		}

		return mapping.findForward("success");
	}

	/**
	 * �㽭��ҵְҵWEBSERVICE������
	 * 
	 * @param password
	 * @return ����ʧ�ܷ���""
	 */
	public String zjsyWebServiceDecrypto(String password) {
		String str = "";
		try {
			String endPoint = "http://192.168.250.234:8082/SecurityService.asmx?wsdl";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endPoint));
			call.setOperationName(new QName("http://tempuri.org/", "Decrypto"));

			call.addParameter(new QName("http://tempuri.org/", "Source"),
					org.apache.axis.Constants.XSD_STRING, ParameterMode.IN);
			call.setReturnType(org.apache.axis.Constants.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://tempuri.org/Decrypto");

			str = (String) call.invoke(new Object[] { password });
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("���ܵ��� ʧ��.........");
		}
		return str;
	}

	/**
	 * �������Ƽ�ѧԺ�����¼�ӿ�
	 * 
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward hljKjxySSOLogin(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommanDAO dao = new CommanDAO();
		String strUserIp = request.getRemoteAddr();// ���IP
		String ticket = request.getParameter("bill");// ���ܵ��Ż�������Ʊ��
		HttpSession session = request.getSession();

		Roam roam = new Roam(); // ����һ���µ����ζ���

		Hashtable ht = roam.thauthCheckTicket(ticket, Globals.APPID, strUserIp);// ������֤��������֤Ʊ��ʱ����Ч
		// String mm = (String)ht.get(ThauthConst.THAUTH_MM);//ȡ�����ؽ���е�����
		String zjh = (String) ht.get(ThauthConst.THAUTH_ZJH);// ȡ���Żؽ���е�֤����
		// String yhm=(String)ht.get(ThauthConst.THAUTH_XM);
		// String dw=(String)ht.get(ThauthConst.THAUTH_DW);
		logger.info("֤����:" + zjh);
		int code = ((Integer) ht.get(ThauthConst.THAUTH_CODE)).intValue();// ��ȡ���ؽ���е�code��ֵ
		logger.info("code:" + code);
		// zjh="zf01";
		if (code == 0) {// �ж�code��ֵ�������0��˵�����γɹ�
			String userType = dao.getUserType(zjh);
			boolean result = dao.ssoLogin(zjh, request, response);
			if (result) {
				// �汾
				String edition = Base.initProperties.get("edition");
				session.setAttribute("edition", edition);
				if ("new".equalsIgnoreCase(edition)) {
					if ("stu".equalsIgnoreCase(userType)) {
						return mapping.findForward("stuLogin");
					} else {
						return mapping.findForward("teaLogin");
					}
				}
			} else {
				return mapping.findForward("false");
			}
		} else {
			request.setAttribute("errMsg", "����ʧ�ܣ�code������0");
			return mapping.findForward("false");
		}
		request.setAttribute("errMsg", "�汾����,������ϵͳ�汾!");
		return mapping.findForward("false");

	}

	// check school code exists in sso
	public boolean checkXxdmExists(String[] array) {
		String xxdm = Base.xxdm;
		for (String s : array) {
			if (s.equalsIgnoreCase(xxdm)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @����:TODO������˾������֤ͳһ��ַ
	 * @���ߣ�ltt[���ţ�205]
	 * @���ڣ�2013-9-17 ����03:42:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward zfxg_casLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String curUser = "";

		try {
			if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DR)) {// ������֤
				curUser = drSsorz(request, response);
			} else if(checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DR_NEW)){//������֤
				curUser = drSsorzNew(request, response);
			} else if(checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_XE)){//ϣ����֤
				curUser = xeHnRz(request, response);
			}else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_JZ)) {// ������֤
				curUser = jzSsorz(request, curUser);
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DK)) {// �����֤
				curUser = dkSsorz(request, curUser);
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_KB)) {// ������֤ 
				curUser = kbSsorz(request, curUser);
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_KS)) {// ������֤ 
				curUser = ksSsorz(request, curUser);
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_HS)) {// ������֤
				curUser = hsSsorz(request, curUser);
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_JZ_NEW)) {// ������֤��new��
				curUser = jzSsorzNew(request, curUser);
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_SW)) {// ��ά��֤
				curUser = swSsorz(request, response, curUser);
				if(curUser.contains("$$sw$$_")){
					request.setAttribute("sw_redirect_url", curUser.replace("$$sw$$_", ""));
					return new ActionForward("/sw_redirect.jsp", false);
				}
			} else if (Base.xxdm.equals(Globals.XXDM_ZJCMXY)) {
				String zfsoftSsoKey = "zfssokey";
				String yhm = request.getParameter("yhm");
				String date = request.getParameter("date");
				String jym = request.getParameter("jym");
				String key = yhm + date + zfsoftSsoKey;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String currDate=sdf.format(new Date());
				if (!currDate.equals(date)) {
					request.setAttribute("errMsg",
							"ʱ���ѹ��ڲ�������������µ�¼!");
					return mapping.findForward("errorMsg");
				}
				logger.info("yhm:"+yhm);
				logger.info("date:"+date);
				logger.info("key:"+key);
				logger.info("����key:"+ZfsoftMd5.getMd5(key));
				logger.info("jym:"+jym);
				// ��֤
				if (ZfsoftMd5.authenticateKey(jym.toUpperCase(), key)) {
					curUser = yhm;
				} else {
					request.setAttribute("errMsg", "�Ƿ����ʣ����¼���������ʣ�");
					return mapping.findForward("errorMsg");
				}
			} else if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_YB)) {//�װ�Խ�
				String userid = request.getParameter("userid");
				String sid = request.getParameter("sid");
				String sig = request.getParameter("sig");
				logger.info("userid=" + userid + "--" + "sid=" + sid + "--sig="
						+ sig);
				try {
					URL url = new URL("http://f.yiban.cn/project/auth?sig=" + sig);
					logger.info(url.toString());
					URLConnection conn = url.openConnection();
					conn.setDoInput(true);
					conn.setDoOutput(true);

					//OutputStream out = conn.getOutputStream();
					//StringBuffer params = new StringBuffer("?sig=" + sig);
					//out.write(params.toString().getBytes("UTF-8"));

					InputStream in = conn.getInputStream();
					InputStreamReader isr = new InputStreamReader(in, "UTF-8");
					char[] buf = new char[1024];
					int count = isr.read(buf);
					String result = new String(buf, 0, count);
					logger.info("yiban-result=>" + result);
					if (result != null && "1".equals(result)) {
						curUser = sid;
					}
				} catch (Exception e) {
					logger.info("�����װ�POST�ӿڳ���");
					e.printStackTrace();
				}
				// �Ϻ������ѧ ���ݿ��ԺŻ�ȡѧ��
				if("10264".equals(Base.xxdm)){
					String xh = getXhByKsh_shhy(curUser);
					logger.info("�Ϻ������ѧ ���ݿ��ԺŻ�ȡѧ�ţ����Ժ�=" + curUser + " ѧ��=" + xh);
					if(!StringUtils.isNull(xh)){
						curUser = xh;
					}
				}
				// �Ϻ����ѧԺ      ѧ�����װ࣬��ʦ������
				if("11458".equals(Base.xxdm) && ("".equals(curUser) || curUser == null)){
					curUser = jzSsorz(request, curUser); // ������֤
					logger.info("�Ϻ����ѧԺ  ��ʦ������ curUser=" + curUser);
				}
			} else if (Base.xxdm.equals(Globals.XXDM_ZJDX)) {//zjdx
				String uid = null;
				String token = null;
				Cookie[] cs = request.getCookies();
				if (cs == null) {
					return null;
				}
				for (int i = 0; i < cs.length; i++) {
					if ("iPlanetDirectoryPro".equals(cs[i].getName())) {
						token = cs[i].getValue();
						break;
					}
				}
				if (token == null || "".equals(token.trim())) {
					request.setAttribute("errMsg", "ͳһ�����֤��½ʧ�ܣ����¼���������ʣ�");
					return mapping.findForward("errorMsg");
				}
				/*CookieValidateNew cv = new CookieValidateNew();*/
				CookieValidate cv = new CookieValidating();
				String status = cv.validate(request, response, token);
				if (status == null || "".equals(status.trim())) {
					request.setAttribute("errMsg", "ͳһ�����֤��½ʧ�ܣ����¼���������ʣ�");
					return mapping.findForward("errorMsg");
				}
				if ("1".equalsIgnoreCase(status.trim())) {
					HashMap<String, String> endPointUriMap = Base
					.getInitProperties();
					uid = getUidByToken(token, endPointUriMap);
				}
				curUser = uid;
			} else {
				request.setAttribute("errMsg", "��δʵ��ͳһ�����֤������ϵ����Ա��");
				return mapping.findForward("errorMsg");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request
					.setAttribute("errMsg",
							"���õ�������֤�ӿڳ������⣬��������֤�������ò���ȷ������ϵ����Ա��");
			return mapping.findForward("errorMsg");
		}
		
		// =========== ��ά��֤ ר�� begin ===============
		String targetUrl = "";
		if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_SW)) {
			String[] curUserArr = curUser.split("@@swOk@@_");
			curUser = curUserArr[0];
			if(curUserArr.length == 2){
				targetUrl = curUserArr[1];
			}
			logger.info("��ǰ��¼curUser:" + curUser + " targetUrl=" + targetUrl);
		}
		// =========== ��ά��֤ ר�� end ===============

		//����������start
		if(Base.xxdm.equals("13645")){
			curUser = doGllgSsoLogin(request,response);
		}
		//����������end
		// ��ȡ�û��������ԭ���ļ����û���Ϣ����
		System.out.print("��ǰ��¼�����ã�" + curUser);
		HttpSession session = request.getSession();
		CommanAction test = new CommanAction();
		String[] returnStrings = test.userLoginForPage(curUser);
		if (!StringUtil.isNull(returnStrings[4])) {
			request.setAttribute("errMsg", returnStrings[0]);
			session.setAttribute("pjzq", returnStrings[1]); // ѧУ����������,xn
			session.setAttribute("userOnLine", returnStrings[2]); // �û����ͣ�ѧ������ʦ��
			session.setAttribute("userName", returnStrings[3]); // ��½��
			session.setAttribute("userDep", returnStrings[4]); // �û�����
			session.setAttribute("userNameReal", returnStrings[5]); // �û�����
			session.setAttribute("xxmc", returnStrings[6]);
			session.setAttribute("xxdm", returnStrings[7]);
			session.setAttribute("LoginXn", returnStrings[8]);
			session.setAttribute("LoginXq", returnStrings[9]);
			session.setAttribute("LoginZc", returnStrings[10]);
			session.setAttribute("openType", returnStrings[11]);
			session.setAttribute("bmmc", returnStrings[12]);
			session.setAttribute("userType", returnStrings[14]);
			session.setAttribute("istysfrz", "yes");
			LogInfo loginfo = new LogInfo();
			UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
			loginfo.setOsName(userAgent.getOperatingSystem().getName());
			loginfo.setBrowserName(userAgent.getBrowser().getName());
			if(userAgent.getBrowserVersion() != null){
				loginfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
			}
			loginfo.setIp(IPRequest.getIpAddress(request));
			loginfo.setDescription("ϵͳ��¼���û�����"+session.getAttribute("userName").toString());
			loginfo.setClassName(mapping.getType());
			loginfo.setMethodName("userLogin");
			loginfo.setUsername(session.getAttribute("userName").toString());
			loginfo.setUserType(session.getAttribute("userType").toString());
			new LogService().runInsert(loginfo);
			new LogService().insertDllog();
			if (returnStrings[13].equalsIgnoreCase("yes")) {
				session.setAttribute("isFdy", true);
			} else {
				session.setAttribute("isFdy", false);
			}
			if (returnStrings[15].equalsIgnoreCase("yes")) {
				session.setAttribute("isBzr", true);
			} else {
				session.setAttribute("isBzr", false);
			}
			if (returnStrings[16].equalsIgnoreCase("yes")) {
				session.setAttribute("fdyQx", true);
			} else {
				session.setAttribute("fdyQx", false);
			}
			if (returnStrings[17].equalsIgnoreCase("yes")) {
				session.setAttribute("bzrQx", true);
			} else {
				session.setAttribute("bzrQx", false);
			}
			// �汾
			String edition = Base.getEdition();
			// �Ƿ���Ҫ�߼���ѯ
			String superSearch = Base.getSuperSearch();

			session.setAttribute("edition", edition);
			session.setAttribute("superSearch", superSearch);
			session.setAttribute("isFdy", Fdypd.isFdy(curUser));
			session.setAttribute("fdyQx", Fdypd.fdybjck(curUser));
			session.setAttribute("isFdyUser", Fdypd.isFdy(curUser));// �Ƿ��Ǹ���Ա

			// �Ƿ�Ԣ����Ա
			String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
			DAO dao = DAO.getInstance();
			String num = dao
					.getOneRs(gyglySql, new String[] { curUser }, "num");
			String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
					: "no";
			session.setAttribute("gyglyQx", gyglyQx);

			// �Ƿ����ѧԺ�û�
			String sfjryx = Fdypd.checkSfjrXy(curUser).get("sfjryx");
			// �ǿ�ȡ����
			if (!Base.isNull(sfjryx)) {
				session.setAttribute("sfjryx", sfjryx);
				
				// �û�Ȩ��ѡ��Ĭ���ɴ�С
				if("true".equalsIgnoreCase(sfjryx)){
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
				}
			} else {
				session.setAttribute("fdyQx", false);
				session.setAttribute("bzrQx", false);
				session.setAttribute("isFdy", false);
				session.setAttribute("isBzr", false);
			}
			// =========== ��ά��֤ ר�� begin ===============
			if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_SW)) {
				if(!"".equals(targetUrl)){
					if(targetUrl.contains("{base64}")){
						targetUrl = new String(Base64.base64Decode(targetUrl.replace("{base64}", "")));
					}
					request.setAttribute("sw_redirect_url", targetUrl);
					return new ActionForward("/sw_redirect.jsp", false);
				}
			}
			// =========== ��ά��֤ ר�� end ===============
			if (checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_JZ_NEW)) {// ��new������ͳһ�����֤����ת
				targetUrl = request.getParameter("targetUrl");
				if(!StringUtil.isNull(targetUrl)){
					System.out.print("��new������ͳһ�����֤����ת targetUrl: " + targetUrl);
					request.setAttribute("forwardPath", targetUrl);
					return new ActionForward("/zjsy.jsp", false);
				}
			}
			//=====����new���񼯳�begin=======
			if(checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DR_NEW)){
				targetUrl = request.getParameter("targetUrl");
				if(!StringUtil.isNull(targetUrl)){
					logger.info("�����ɷ����ַtargetUrl��" + targetUrl);
					request.setAttribute("sw_redirect_url", targetUrl);
					return new ActionForward("/sw_redirect.jsp", false);
				}
			}
			//=====����new���񼯳�end=======
			if ("student".equalsIgnoreCase(returnStrings[2])) {
				return mapping.findForward("stuLogin");
			} else {
				return mapping.findForward("teaLogin");
			}
		}
		request.setAttribute("errMsg", returnStrings[0]);
		return mapping.findForward("errorMsg");
	}

	/**
	 * ��������ѧ�����¼
	 * @param request
	 * @param response
	 * @return
	 */
	public String doGllgSsoLogin(HttpServletRequest request, HttpServletResponse response){

		String user = request.getParameter("u");
		String time = request.getParameter("t");
		logger.info("ѧ��ϵͳ��¼�û���"+ user);
		logger.info("ѧ��ϵͳ��¼ʱ�䣺"+ time);
		if(user == null || "".equals(user.trim())){
			throw new IllegalArgumentException("��¼�û���������Ϊ�գ�����");
		}
		if(time == null || "".equals(time.trim())){
			throw new IllegalArgumentException("����ʱ�������Ϊ�գ�����");
		}

		DAO dao = DAO.getInstance();
		String sql = "select yhm from yhb where yhm=?";
		String yhm = dao.getOneRs(sql,new String[]{user},"yhm");
		if(StringUtils.isNull(yhm)){
			throw new IllegalArgumentException("��¼�û��������Ϸ�������");
		}

		String url = MessageUtil.getText("gllgddyzUrl");
		String privateLock = MessageUtil.getText("lockCode");
		String md5Rs = xgxt.utils.MD5.md5(user+time+privateLock);

		CloseableHttpClient httpclient = null;
		CloseableHttpResponse result = null;
		httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		String param = "{\"v_code\":"+md5Rs+"}";
		formparams.add(new BasicNameValuePair("sso", param));
		UrlEncodedFormEntity uefEntit = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		httppost.setEntity(uefEntit);
		try {
			result = httpclient.execute(httppost);
			int statusCode = result.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = result.getEntity();
				if (entity != null) {
					String rsData = EntityUtils.toString(entity, "UTF-8");
					Map resultMap = new Gson().fromJson(rsData, Map.class);
					String sts = resultMap.get("status").toString();
					if("1".equals(sts)){
						logger.info("��֤�ɹ�,�����¼��");
						return user;
					} else{
						logger.info("��֤ʧ�ܣ�����");
						throw new RuntimeException("��֤ʧ�ܣ�����");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("������֤��ַʧ�ܣ�����");
		}
		return null;
	}

	/** 
	 * �Ϻ������ѧ ���ݿ��ԺŻ�ȡѧ��
	 */
	public String getXhByKsh_shhy(String curUser) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xh from xsxxb where ksh=? ", new String[] {curUser}, "xh");
	}

	// ����ͳһ�����֤���õ�client.propertiesg��������֤����
	public String jzSsorz(HttpServletRequest request, String curUser)
			throws UnsupportedEncodingException, Exception {
		logger.info("����ͳһ�����֤ ����½��ʼ��");
		String is_config = request.getRealPath("/client.properties");
		logger.info(is_config);
		Cookie all_cookies[] = request.getCookies();
		Cookie myCookie;
		String decodedCookieValue = null;
		if (all_cookies != null) {
			for (int i = 0; i < all_cookies.length; i++) {
				myCookie = all_cookies[i];
				if (myCookie.getName().equals("iPlanetDirectoryPro")) {
					decodedCookieValue = URLDecoder.decode(myCookie.getValue(),
							"GB2312");
				}
			}
		}
		IdentityFactory factory = IdentityFactory.createFactory(is_config);
		IdentityManager im = factory.getIdentityManager();
		// ��COOKIE�л�ȡ��֤�����û�
		if (decodedCookieValue != null) {
			curUser = im.getCurrentUser(decodedCookieValue);
		}
		logger.info("curUser:" + curUser);
		return curUser;
	}
	
	// ��new������ͳһ�����֤
	public String jzSsorzNew(HttpServletRequest request, String curUser) {
		logger.info("��new������ͳһ�����֤ ����½��ʼ��");
		curUser = request.getRemoteUser();
		logger.info("curUser:" + curUser);
		
		return curUser;
	}
	
	// ���ͳһ�����֤
	public String dkSsorz(HttpServletRequest request, String curUser) {
		logger.info("���ͳһ�����֤ ����½��ʼ��");
		curUser = request.getHeader("UID");
		logger.info("curUser:" + curUser);
		
		return curUser;
	}
	// ����ͳһ�����֤
	public String kbSsorz(HttpServletRequest request, String curUser) {
		logger.info("����ͳһ�����֤ ����½��ʼ��");
		CASFilterRequestWrapper reqWrapper = new CASFilterRequestWrapper(request); //��ȡ�����¼ϵͳ�ش��ĵ�¼���ơ�
		curUser = reqWrapper.getRemoteUser();
		logger.info("curUser:" + curUser);
		return curUser;
	}
	// ����ͳһ�����֤
	public String ksSsorz(HttpServletRequest request, String curUser) {
		logger.info("����ͳһ�����֤ ����½��ʼ��");
		HttpSession session = request.getSession();
		Assertion assertion= (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		String ssoid = assertion.getPrincipal().getName();
		logger.info("curUser:" + ssoid);
		return curUser;
	}

	public String hsSsorz(HttpServletRequest request, String curUser) {
		logger.info("����ͳһ�����֤ ����½��ʼ��");
		HttpSession session = request.getSession();
		Assertion assertion= (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		String ssoid = assertion.getPrincipal().getName();
		logger.info("curUser:" + ssoid);
		return curUser;
	}

	// ����ͳһ�����֤���õ�casFilterConfig.xml��������֤����
	public String drSsorz(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String curUser;
		// ��ȡ��֤���ĵ��û���
		com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper reqWrapper =
			new com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper(
				request);
		curUser = reqWrapper.getRemoteUser();

		String strRedirecturl = request.getParameter("redirectUrl");
		logger.info("strRedirecturl>>>" + strRedirecturl);
		if (strRedirecturl != null && strRedirecturl.length() > 0) {
			response.sendRedirect(strRedirecturl);
		}
		return curUser;
	}

	// ����ͳһ�����֤���õ�casFilterConfig.xml��������֤����
	public String drSsorzNew(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String curUser;
		// ��ȡ��֤���ĵ��û���
		org.jasig.cas.client.authentication.AttributePrincipal principal = (org.jasig.cas.client.authentication.AttributePrincipal) request.getUserPrincipal();
		curUser = principal.getName();

//		String strRedirecturl = request.getParameter("redirectUrl");
//		logger.info("strRedirecturl>>>" + strRedirecturl);
//		if (strRedirecturl != null && strRedirecturl.length() > 0) {
//			response.sendRedirect(strRedirecturl);
//		}
		return curUser;
	}
	
	// ϣ��ͳһ�����֤���õ�casFilterConfig.xml��������֤����
	public String xeHnRz(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// ��ȡ��֤���ĵ��û���
		HttpSession session = request.getSession();
		String curUser = (String) session.getAttribute("edu.yale.its.tp.cas.client.filter.user");
		logger.info("userid:"+curUser);

		return curUser;
	}
	
	// ��άͳһ�����֤���õ�swsso.properties��������֤����
	public String swSsorz(HttpServletRequest request, HttpServletResponse response, String curUser)
			throws UnsupportedEncodingException, Exception {
		String is_config = request.getRealPath("/swsso.properties");
		
		HttpSession session = request.getSession();
		Properties properties =  new Properties();
		properties.load(new FileReader(is_config));
		// CAS��¼��ַ
		String CAS_LOGIN_URL = properties.getProperty("CAS_LOGIN_URL");
		// CASƱ����֤��ַ
		String CAS_VALIDATE_URL = properties.getProperty("CAS_VALIDATE_URL");
		// CASע����ַ
		String CAS_LOGOUT_URL = properties.getProperty("CAS_LOGOUT_URL");
		// ҵ��ϵͳ��֤���ɸ���֮��ĵ�¼��ַ
		String CURRENT_SERVICE_URL = properties.getProperty("CURRENT_SERVICE_URL");
		// ҵ��ϵͳ��¼�ɹ�����ʾ��ҳ���URL
		String DEFAULT_TARGET_URL = properties.getProperty("DEFAULT_TARGET_URL");
		
		// ����
		String casServerLoginUrl = CAS_LOGIN_URL;
		String casServerServiceValidateUrl = CAS_VALIDATE_URL;

		// ��¼����֤ϵͳ������֤�ɹ����뽫��session����Ϊtrue���Ա�֤�û���������ϵͳʱ��ϵͳ���ᷴ������֤ϵͳ���н���
		Boolean isSSOLogin = (Boolean) session.getAttribute("isSSOLogin");
		logger.info("isSSOLogin = " + isSSOLogin);

		// �ж�����������Ƿ����targetUrl
		String targetUrl = request.getParameter("targetUrl");
		logger.info("�ж�����������Ƿ����targetUrl = " + targetUrl);

		if (targetUrl == null || targetUrl.length() == 0) {

			// �������ڣ���ʹ��Ĭ��ҳ����ΪtargetUrl
			targetUrl = DEFAULT_TARGET_URL;
		} else {
			if (targetUrl.startsWith("{base64}")) {
				targetUrl = targetUrl.replace("{base64}", "");
				targetUrl = Base64.base64Decode(targetUrl);
				logger.info("base64Decode targetUrl = " + targetUrl);
			}
		}

		if (isSSOLogin != null && isSSOLogin.booleanValue()) {

			// ����SSO��֤
			// ��ת��targetUrl
//			response.sendRedirect(targetUrl);
			return "$$sw$$_" + targetUrl;
		} else {
			// ���򣬽�����֤
			// ����serviceΪ��ǰҳ��ķ��ʵ�ַ����ssoProcessPage�ķ��ʵ�ַ
			// ��service����֤�����Ʊ��У��ʱ�������õ�
			String service = CURRENT_SERVICE_URL;

			targetUrl = "{base64}"
					+ new String(Base64.encode(targetUrl.getBytes()));
			service = service + "?targetUrl=" + targetUrl;

			logger.info("service===" + service);

			// �ж�����������Ƿ����ticket
			String ticket = request.getParameter("ticket");
			if (ticket == null || ticket.length() == 0) {
				String loginUrl = casServerLoginUrl + "?service="
						+ URLEncoder.encode(service, "UTF-8");

				// ��ת����֤ϵͳ������֤����֤�ɹ�����֤ϵͳ��������ת��service�����ַ��ͬʱ�����ticket������
//				response.sendRedirect(loginUrl);
				return "$$sw$$_" + loginUrl; 
			} else {
				// ��֤ticket
				// ͨ����̨����ģ������
				// java��HttpClient
				// c#��WebClient
				// php��curl
				String serviceValidateUrl = casServerServiceValidateUrl
						+ "?ticket=" + ticket + "&service="
						+ URLEncoder.encode(service, "UTF-8");

				logger.info("serviceValidateUrl = "
						+ serviceValidateUrl);

				// resp ������Ϊ xml��ʽ�����ݣ�����ο���֤�ӿ�
				String resp = "";

				// ģ��http get����
				URL netURL = new URL(serviceValidateUrl);
				URLConnection connection = netURL.openConnection();
				connection.setConnectTimeout(5000);
				connection.connect();
				InputStream urlInputStream = null;
				urlInputStream = connection.getInputStream();

				BufferedReader rufferedReader = new BufferedReader(
						new InputStreamReader(urlInputStream));
				String sCurrentLine = "";
				while ((sCurrentLine = rufferedReader.readLine()) != null) {
					resp += sCurrentLine;
				}

				logger.info(" resp= " + resp);

				if (resp != null
						&& resp.indexOf("<cas:authenticationSuccess>") != -1) {
					curUser = resp.substring(
							resp.indexOf("<cas:user>") + 10,
							resp.indexOf("</cas:user>"));
					logger.info(curUser);
				}
			}
		}
		
		return curUser + "@@swOk@@_" + targetUrl;
	}
	/**
	 * �����û���������ʵ���û���¼����󵥵㡿
	 * 
	 * @param request
	 * @param response
	 * @return String ���ص�¼�ɹ�������ƣ������¼���ɹ��򷵻ؿ�
	 * @throws InterruptedException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response,HashMap<String, String> endPointUriMap) {
		HttpURLConnection httpConn = null;
		String ssoid = null;
		try {
			
			String endUri = endPointUriMap.get("zjdxWebendPointUri");
			String appUid = endPointUriMap.get("appUid");
			String appPwd = endPointUriMap.get("appPwd");
			 String name = request.getParameter("userName");
			String password = request.getParameter("password2");
			logger.info("endUri"+endUri);
			logger.info("appUid"+appUid);
			logger.info("appPwd"+appPwd);
			logger.info("name"+name);
			logger.info("password"+password);
			URL url = new URL(endUri);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("POST");
			httpConn.addRequestProperty("name", name);
			httpConn.addRequestProperty("password", password);
			httpConn.addRequestProperty("module", "DataStore");
			httpConn.addRequestProperty("type", "1");
			httpConn.addRequestProperty("appUid", appUid);
			httpConn.addRequestProperty("appPwd", appPwd);
			httpConn.setDoOutput(true);
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			try{
			httpConn.setConnectTimeout(3000);
			httpConn.setReadTimeout(3000);
			}catch (Exception e) {
				e.printStackTrace();
			}
			httpConn.connect();
			
			StringBuffer resultBuffer = new StringBuffer();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
	            String tempLine = "";
	            while ((tempLine = reader.readLine()) != null) {
	                resultBuffer.append(tempLine);
	            }
	            logger.info(name+"###########################");
	            logger.info(name+"   "+resultBuffer.toString());
	            logger.info("###########################");
			ssoid = httpConn.getHeaderField("iPlanetDirectoryPro");
			if (ssoid == null || "".equals(ssoid.trim())) {
				return null;
			}
			ssoid = URLEncoder.encode(ssoid, "UTF-8");
			Cookie sso_cookie = new Cookie("iPlanetDirectoryPro", ssoid);
			sso_cookie.setPath("/");
			sso_cookie.setDomain(".zju.edu.cn");
			response.addCookie(sso_cookie);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
		return ssoid;
	}
	/**
	 * ��������ֵ��ͳһ�����֤��ȡ�û�������󵥵㡿
	 * 
	 * @param token
	 *            ����ֵ
	 * @return String ���û����ڵ�¼״̬�򷵻��û��������򷵻ؿա�
	 * @throws IOException
	 */
	public String getUidByToken(String token,HashMap<String,String> endPointUriMap) throws IOException {
		String uid = null;
		if (token == null || "".equals(token.trim())) {
			return null;
		}
		HttpURLConnection httpConn = null;
		try {
			String endUri = endPointUriMap.get("zjdxWebendPointUri");
			String appUid = endPointUriMap.get("appUid");
			String appPwd = endPointUriMap.get("appPwd");
			URL url = new URL(endUri);
			URLConnection conn = url.openConnection();
			httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("GET");
			httpConn.addRequestProperty("iPlanetDirectoryPro", token);
			httpConn.addRequestProperty("appUid", appUid);
			httpConn.addRequestProperty("appPwd", appPwd);
			httpConn.connect();
			uid = httpConn.getHeaderField("ZJU_SSO_UID");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
		return uid;
	}
}
