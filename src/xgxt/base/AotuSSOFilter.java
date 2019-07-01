package xgxt.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.CommanAction;
import xgxt.utils.Fdypd;

import com.zfsoft.utils.StringUtil;
import common.GlobalsVariable;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ⵥ�������
 * @�๦������: �ⵥ�������
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-12-1 ����05:34:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class AotuSSOFilter implements Filter {

	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean  aotoSSOFlag = true;
		HttpSession session = ((HttpServletRequest)request).getSession();
		String userName = (String) session.getAttribute("userName");
		if(StringUtils.isNotEmpty(userName)){
			CommanAction test = new CommanAction();
		    String [] returnStrings = test.userLoginForPage(userName);
		    if(!StringUtil.isNull(returnStrings[4])){
		    	aotoSSOFlag = false;
		    }
		}
		if(aotoSSOFlag){
			String curUser = "";
			CommanAction commA = new CommanAction();
			try {
				if (commA.checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_DR)) {//������֤
					curUser = commA.drSsorz((HttpServletRequest)request, (HttpServletResponse)response);
				} else if (commA.checkXxdmExists(GlobalsVariable.XTWH_TYSFRZ_JZ)) {//������֤
					curUser = commA.jzSsorz((HttpServletRequest)request, curUser);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			//curUser="zf01";//    ��������------------------------------------------
			if(StringUtils.isNotEmpty(curUser)){
				//��ȡ�û��������ԭ���ļ����û���Ϣ����
				System.out.print("��ǰ��¼�����ã�"+curUser);
			    CommanAction test = new CommanAction();
			    String [] returnStrings = test.userLoginForPage(curUser);
			    if(!StringUtil.isNull(returnStrings[4])){
				    request.setAttribute("errMsg",returnStrings[0]);
				    session.setAttribute("pjzq", returnStrings[1]); //ѧУ����������,xn
					session.setAttribute("userOnLine", returnStrings[2]); //�û����ͣ�ѧ������ʦ��
					session.setAttribute("userName", returnStrings[3]); //��½��
					session.setAttribute("userDep", returnStrings[4]); //�û�����
					session.setAttribute("userNameReal", returnStrings[5]); //�û�����
					session.setAttribute("xxmc", returnStrings[6]);
					session.setAttribute("xxdm", returnStrings[7]);
					session.setAttribute("LoginXn", returnStrings[8]);
					session.setAttribute("LoginXq", returnStrings[9]);
					session.setAttribute("LoginZc", returnStrings[10]);
					session.setAttribute("openType",returnStrings[11]);
					session.setAttribute("bmmc", returnStrings[12]);
					session.setAttribute("userType", returnStrings[14]);
					session.setAttribute("istysfrz", "yes");
					if(returnStrings[13].equalsIgnoreCase("yes")){
						session.setAttribute("isFdy", true);
					}else{
						 session.setAttribute("isFdy", false);
				    }
				    if(returnStrings[15].equalsIgnoreCase("yes")){
						session.setAttribute("isBzr", true);
					}else{
						 session.setAttribute("isBzr", false);
				    }    
				    if(returnStrings[16].equalsIgnoreCase("yes")){
						session.setAttribute("fdyQx", true);
					}else{
						 session.setAttribute("fdyQx", false);
				    }    
				    if(returnStrings[17].equalsIgnoreCase("yes")){
						session.setAttribute("bzrQx", true);
					}else{
						 session.setAttribute("bzrQx", false);
				    }
				    //		 �汾
				    String edition = Base.getEdition();
						// �Ƿ���Ҫ�߼���ѯ
				    String superSearch = Base.getSuperSearch();
				
					session.setAttribute("edition", edition);
					session.setAttribute("superSearch", superSearch); 
					session.setAttribute("isFdy", Fdypd.isFdy(curUser));
				    session.setAttribute("fdyQx", Fdypd.fdybjck(curUser));
				    session.setAttribute("isFdyUser", Fdypd.isFdy(curUser));//�Ƿ��Ǹ���Ա
				    
				    //�Ƿ�Ԣ����Ա
				    String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
				    DAO dao = DAO.getInstance();
					String num = dao.getOneRs(gyglySql, new String[] { curUser }, "num");
					String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
							: "no";
					session.setAttribute("gyglyQx", gyglyQx);
					
					//�Ƿ���Ժ�û�
					String sySql = "select sydm from fdyxxb where zgh=?";
					String sydm = dao.getOneRs(sySql, new String[] { userName },
							"sydm");
					String syQx = !Base.isNull(sydm) ? "yes" : "no";
					
					session.setAttribute("syQx", syQx);// �Ƿ�����Ժ�û�
					
					// �Ƿ����ѧԺ�û�
					String sfjryx= Fdypd.checkSfjrXy(curUser).get("sfjryx");
					// �ǿ�ȡ����
					if(!Base.isNull(sfjryx)){
						session.setAttribute("sfjryx", sfjryx);
					}else{
						session.setAttribute("fdyQx", false);
						session.setAttribute("bzrQx", false);
						session.setAttribute("isFdy", false);
						session.setAttribute("isBzr", false);
					}
			    }
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
