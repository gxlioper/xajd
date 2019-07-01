/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-14 ����09:39:46 
 */  
package xgxt.xtwh.userLogin;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ParameterMode;

import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.CommanForm;
import xgxt.utils.Fdypd;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ͨ��ʦ��¼
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�· [���ţ�982]
 * @ʱ�䣺 2013-6-14 ����09:39:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class NtgsLogin {
	/**
	 * ͨ������֤��Ϣ������֤�����ṩ��Web Service�ӿڻ�ȡ�û���Ϣ��ת���ַ�ӿ�
	 * 
	 * @param ticket
	 *            ��֤��Ϣ
	 * @param valusercode
	 *            ��֤�˻�����ͳһ�����֤ƽ̨�ṩ��Ĭ���˺���valuser�����ͳһ�����֤û���ṩ����ô��ʹ��valuser;
	 * @return
	 */
	public static String TicketVal(String ticket, String valusercode,
			String wsUrl) {
		Service service = new Service();
		service.setMaintainSession(true);// ����Ϊ���ֻỰ
		try {
			Call call = (Call) service.createCall();
			// ���÷��ʵ�
			call.setTargetEndpointAddress(wsUrl);

			// ���ò�����
			call.setOperationName("getTicketVal");

			// ������ڲ���
			call.addParameter("ticket", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("valusercode", XMLType.XSD_STRING,
					ParameterMode.IN);
			// ���÷��ز�������
			call.setReturnType(XMLType.XSD_STRING);
			// ���÷���
			return call.invoke(new Object[] { ticket, valusercode }).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		JSONObject jsonObj=JSONObject.fromObject("{'phoneNo':'null','idNo':'11111111111111111111','userIdentity':'��ְ��','userCode':'js','gotoUrl':'null','mobilePhoneNo':'11111111111111111111','userSex':'','userDept':'��ͨ�ߵ�ʦ��ѧУ','userName':'����','success':'true','userType':'����'}");
		System.out.println("jsonObj:"+jsonObj);
		Iterator<String> it=jsonObj.keys();
		while(it.hasNext()){
			String key=it.next();
			System.out.println("key:"+key);
			System.out.println("value:"+jsonObj.get(key));
		}
		System.out.println("-------------json-end-------------");
	}
	public static String[] userLogin(HashMap<String, String> map,
			HttpServletRequest request, CommanForm chkForm,
			ActionMapping mapping) throws Exception {
		// ����ѧԺͳһ�����֤ѧ����֤
		String userType = "";
		String userName = map.get("userName");
		// String xxdm = map.get("xxdm");
		// String xxmc = map.get("xxmc");
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "select szbm,xm,zdm from yhb where yhm=?";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "szbm", "xm", "zdm" });
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		} else {
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm", "xm", "zdm" });
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			} else {
				chkForm.setErrMsg("�����ڸ��û���");
			}
		}

		if (userType.equalsIgnoreCase("teacher")) {
			boolean isFdy = Fdypd.isFdy(userName);
			session.setAttribute("isFdyUser", isFdy);
			session.setAttribute("isFdy", isFdy);
			session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
			session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
			session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
		} else {
			session.setAttribute("isFdyUser", false);
			session.setAttribute("isFdy", false);
			session.setAttribute("isBzr", false);
			session.setAttribute("fdyQx", false);
			session.setAttribute("bzrQx", false);
		}
		
		if (userChk != null) {
			userChk = new String[] { userChk[0], userChk[1], userChk[2],
					userType };
		}
		
		HashMap<String,String> xmlInfo = new HashMap<String,String>();
		if ("student".equalsIgnoreCase(userType)) {
			sql = "select xh,xm,xb,sfzh,sjhm,lxdzxx,xydm from view_xsjbxx where xh = ?";
			xmlInfo = dao.getMap(sql, new String[]{userName}, new String[]{"xh","xm","xb","sfzh","sjhm","lxdzxx","xydm"});
			xmlInfo.put("type", "ѧ��");
		}else if("teacher".equalsIgnoreCase(userType)){
			sql = "select * from (select a.yhm xh,a.xm,b.xb,b.sfzh,b.yddh sjhm,b.dzyx lxdzxx,a.szbm xydm from yhb a left join fdyxxb b on a.yhm = b.zgh) where xh = ?";
			xmlInfo = dao.getMap(sql, new String[]{userName}, new String[]{"xh","xm","xb","sfzh","sjhm","lxdzxx","xydm"});
			xmlInfo.put("type", "��ʦ");
		}
		if (xmlInfo != null && xmlInfo.size() > 0) {
			//setXml(xmlInfo);
		}
		
		return userChk;
	}
}
