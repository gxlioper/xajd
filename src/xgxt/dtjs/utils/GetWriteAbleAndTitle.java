package xgxt.dtjs.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class GetWriteAbleAndTitle {
	public static String [] getWriteAbleAndTitle(HttpServletRequest request){
		HttpSession session = request.getSession();						//�õ�session
		String userType = "";
		boolean isStu = true;
		String sUName = "";
		String sPath = "";
		String qStr = "";
		String power = "";
		String writeAble = "";
		userType = session.getAttribute("userType").toString();   //�õ��û�����
		isStu = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
		sUName = session.getAttribute("userName").toString();	//�õ���¼�û���
		sPath = request.getServletPath().replace("/", "");
		qStr = request.getQueryString();		
		if("".equalsIgnoreCase(qStr) || qStr == null){
			power = sPath;
		}else{
			power = sPath + "?" + qStr;
		}
		String messTemp[] = chkUPower(sUName, power, isStu);
		if (!Base.isNull(messTemp[0]) && messTemp[0].equals("0")) {
			// ֻ��
			writeAble = "no";
		} else if (!Base.isNull(messTemp[0]) && messTemp[0].equals("1")) {
			// ��д
			writeAble = "yes";
		}
		return new String [] {writeAble,messTemp[1]};
	}
	
	public static String [] chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		DAO daoBase = DAO.getInstance();
		if (!isStu) {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhqx a where yhm=? and dyym=?";
		} else {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhzqx a where zdm=? and dyym=?";
			uName = "6727";
		}
		//BEGIN ChenHuamao E-MAIL:chhuma@mail.china.com
		//�޸�java.lang.NullPointerException
		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq","title"});
		return ress;
		//END
	}

}
