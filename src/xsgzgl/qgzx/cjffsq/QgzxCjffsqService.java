package xsgzgl.qgzx.cjffsq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cjffjg.QgzxCjffjgDAO;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
/**
 * �ڹ���ѧ-������-�����Ϣ����
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffsqService extends BasicService{
	
	
	QgzxCjffsqDAO qgzxCjffsqDAO = new QgzxCjffsqDAO();
	QgzxCsszDAO qgzxCsszDAO = new QgzxCsszDAO();
	ZdydrService zdydrService = new ZdydrService();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[]{};
		/*ȡ�������ñ��е�����*/
		String csz = getCspzxx("cjffgs");
		
		if("12309".equals(Base.xxdm)){
			 en = new String[] { "",  "r", "xn", "bmmc", "ffny", "yffrs","ffrs","jfhbje","zcje","ffje","shztmc" };
			 cn = new String[] { "",  "�к�", "ѧ��", "���˲���", "��������","Ӧ��������","�ѷ�������","�������<Ԫ>","�Գ���<Ԫ>","����ܶ�<Ԫ>","����״̬"};
		}else {
			 en = new String[] { "", "r", "xn", "bmmc", "ffny", "yjsrs","bksrs","ffje","khdj","shztmc" };
			 cn = new String[] { "", "�к�", "ѧ��", "���˲���", "��������","�о���������","������������","���Ž��<Ԫ>","����״̬"};
		}
		if("xq".equals(QgCommUtilf.getQgzq())){
			if("12309".equals(Base.xxdm)){
				 en = new String[] { "", "r", "xn","xqmc","bmmc", "ffny", "yffrs","ffrs","jfhbje","zcje","ffje","shztmc" };
				 cn = new String[] { "", "�к�", "ѧ��", "ѧ��", "���˲���", "��������","Ӧ��������","�ѷ�������","�������<Ԫ>","�Գ���<Ԫ>","����ܶ�<Ԫ>","����״̬"};
			}else {
				 en =new String[] { "", "r", "xn","xqmc", "bmmc", "ffny", "yffrs","ffrs","ffje","khdj","shztmc" };
				 cn = new String[] { "", "�к�", "ѧ��", "ѧ��","���˲���", "��������","Ӧ��������","�ѷ�������","���Ž��<Ԫ>","����״̬"};
			}
		}
		if("cjff".equalsIgnoreCase(type)){
			QgzxCsszService qgzxCsszService = new QgzxCsszService();
			HashMap<String, String> map=qgzxCsszService.getCssz();
			String sfsdgwcjsx=map.get("sfsdgwcjsx");
			if("13855".equals(Base.xxdm)){
				if("yes".equals(sfsdgwcjsx)){
					en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","gs", "je","khdj","bz" };
					cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","��λ�������","�ڸ�״̬","�ϸ�ʱ��", "��ʱ","���<Ԫ>","���˵ȼ�","��ע"};
				}else{
					en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","gs", "je","khdj","bz" };
					cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","�ڸ�״̬","�ϸ�ʱ��", "��ʱ","���<Ԫ>","���˵ȼ�","��ע"};
				}
			}else if("10338".equals(Base.xxdm)){
					en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gs", "je","bz" };
					cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����", "(������ʱx��������+�ۺϹ�ʱx�ۺϵ���)x���ѵȼ�����","���<Ԫ>","��ע"};
			}else if("12309".equals(Base.xxdm)){
				en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","gwcjbz","gs","jfhb","zc","je","bz" };
				cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","�ڸ�״̬","�ϸ�ʱ��","��λ����׼","��ʱ","���ѻ���<Ԫ>","�Գ�<Ԫ>","���<Ԫ>","��ע"};
			}else{
				if("yes".equals(sfsdgwcjsx)){
					/*���������ñ��еĳ�𷢷Ź�ʱ��ʾΪ1ʱ��ʾ*/
					if("1".equals(csz)){
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","gs", "je","bz" };
						cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","��λ�������","�ڸ�״̬","�ϸ�ʱ��", "��ʱ","���<Ԫ>","��ע"};
					}else{
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","je","bz" };
						cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","��λ�������","�ڸ�״̬","�ϸ�ʱ��","���<Ԫ>","��ע"};
					}
					
					if("10351".equals(Base.xxdm)){
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","sfkns","gs", "je","bz" };
						cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","��λ�������","�ڸ�״̬","�ϸ�ʱ��","�Ƿ�������", "��ʱ","���<Ԫ>","��ע"};
					}
				}else{
					/*���������ñ��еĳ�𷢷Ź�ʱ��ʾΪ1ʱ��ʾ*/
//					if("1".equals(csz)){
						en = new String[] { "r", "xh", "xm","pycc", "gwmc","gwxzmc","gwcjsx","mxsgz","zgztmc","sgsj","gs", "je","bz" };
						cn = new String[] { "�к�", "ѧ��", "����","�������", "��λ����", "��λ���","��������","ÿСʱ����","�ڸ�״̬","�ϸ�ʱ��", "��ʱ","���<Ԫ>","��ע"};
//					}else{
//						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","je","bz" };
//						cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","�ڸ�״̬","�ϸ�ʱ��","���<Ԫ>","��ע"};
//					}
					
	                 if("10351".equals(Base.xxdm)){
	                	 en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","sfkns","gs", "je","bz" };
	 					cn = new String[] { "�к�", "ѧ��", "����", "��λ����", "��λ����","�ڸ�״̬","�ϸ�ʱ��","�Ƿ�������", "��ʱ","���<Ԫ>","��ע"};
					 }

				}
			}
			
		}
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * ��ó����ϢList
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCjxxList(QgzxCjffsqForm model) throws Exception {
		return qgzxCjffsqDAO.getCjxxList(model);
	}

	public List<HashMap<String,String>> export(QgzxCjffsqForm myForm,User user) throws Exception {
		return qgzxCjffsqDAO.export(myForm,user);
	}
	/**
	 * ����ҳ���ѯ���
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				
				for (int j = 2; j < 4; j++) {
					html.append("<td style='display:none'  ");
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 4; j < rs.length; j++) {
					html.append("<td id='rs_"+j+"' align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 4) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	
	/**
	 * ������˲����б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxCjffsqForm model) {
		return qgzxCjffsqDAO.getYrbm(model);
	}
	
	/**
	 * ������˲����б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYrbmOfUser(User user) {
		return qgzxCjffsqDAO.getYrbmOfUser(user);
	}
	
	
	/**
	 * ��ø�λ�����б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getGwdm(QgzxCjffsqForm model) {
		return qgzxCjffsqDAO.getGwdm(model);
	}
	
	
	/**
	 * ��ø�λ��ԱList
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getGwryList(QgzxCjffsqForm model) throws Exception {
		StringBuilder query = new StringBuilder();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String zgzt = model.getZgzt();
		String gwdm = model.getGwdm();
		String ffny = model.getFfny();
		String xq = model.getXq();
		if(!Base.isNull(xn)){
			query.append(" and xn = '"+xn+"'");
		}
		if(!Base.isNull(yrbm)){
			query.append(" and yrdwdm = '"+yrbm+"'");
		}
		if(!Base.isNull(zgzt)){
			query.append(" and zgzt = '"+zgzt+"'");
		}
		if(!Base.isNull(gwdm)){
			query.append(" and gwdm = '"+gwdm+"'");
		}
		if(!Base.isNull(xq)){
			query.append(" and xq = '"+xq+"'");
		}
//		if(StringUtils.isNotNull(xn) && StringUtils.isNotNull(yrbm)){
//			query.append(" and yrdwdm in (select yrdwdm from xg_qgzx_gwffztb where tjzt = '1' and yrdwdm = '"+yrbm+"'  and xn = '"+xn+"')  ");
//		}
		if(!Base.isNull(ffny)){
			return qgzxCjffsqDAO.getGwryList(model,query.toString());
		}else{
			return new ArrayList<String[]>();
		}
		
	}

	
	
	/**
	 * ������𷢷���Աhtmlҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public String createFFryHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user,QgzxCjffsqForm myForm) throws SQLException {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		String sfyxffje = qgzxCsszService.getCssz().get("sfyxffje");//�Ƿ������Ž��
//		String dis = ("no".equalsIgnoreCase(sfyxffje))?"disabled":"";
		String dis = "";
		HashMap<String,String> ffztMap = qgzxCjffsqDAO.getFfzt(myForm);
		/*ȡ�������ñ��е�����*/
		String csz = getCspzxx("cjffgs");
		//�㽭�����Ի�_����ڹ�������Ϣ
		String jeDis = ("10338".equalsIgnoreCase(Base.xxdm)?"disabled":"");
		
		StringBuilder html = new StringBuilder();
		html.append("<input type='hidden' id='ffzt' name='ffzt' value='"+ffztMap.get("tjzt")+"'/>");
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr style='height:22px'><td><input type='hidden' id='xh_"+i+"' name='xh' value='"+rs[1]+"'/>");
				html.append("<input type='hidden' id='gwdm_"+i+"' name='gwdm' value='"+rs[2]+"'/>"+rs[0]+"</td>");
				
				html.append("<td>"+rs[1]+"</td><td width='10%'>"+rs[3]+"</td><td>"+rs[4]+"</td><td width='5%'>"+rs[5]+"</td>");
				html.append("<td>"+rs[11]+"</td><td width='5%'>"+rs[6]+"</td><td><input type='hidden' id='mxsgz' size='6' value='"+rs[14]+"'/>"+rs[7]+"</td>");

//				if("1".equals(csz)){
					html.append("<td>");
//				}else{
//					html.append("");
//				}
				/*���ݲ������ñ��е�ֵ�жϹ�ʱ�Ƿ���ʾ��0:����ʾ,1:��ʾ����MengWei��20170810*/
//				if("1".equals(csz)){
					html.append("<input type='text' id='gs_"+i+"' name='gs' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);autoJsje(this);' value='"+rs[8]+"' "+jeDis+"/></td>");
//				}
				html.append("<td><input type='text' id='je_"+i+"' name='je' maxlength='7' style='width:50px;' size='4' onblur='checkInputNum(this);' value='"+rs[9]+"' "+dis+"/></td>");
				html.append("<td><input type='text' id='bz' name='bz' size='6' style='width:50px;' onblur='checkLen(this,1000)' value='"+rs[10]+"'/></td></tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * ��֤�޸���Ϣ
	 * @param model
	 * @return
	 */
	public String checkUpdateInfo(QgzxCjffsqForm model) {
		if(isOver(model)){
			return "�ò��Ų����ڷ��Ž��򱾴η��Ž����ڻ���ʣ����,��ȷ��!";
		}
		return "true";
	}
	
	

	
	/**
	 * ����Ƿ񳬹�ʣ����
	 * @param model
	 * @return
	 */
	private boolean isOver(QgzxCjffsqForm model) {
		double zje = 0;
		String[] je = model.getJe().split("!!@@!!");
		for(int i = 0;i < je.length;i++){
			zje+=Double.parseDouble(je[i]);
		}
		return qgzxCjffsqDAO.isOver(model,zje);
	}
	
	/**
	 * ��ѯĬ�ϲ���
	 * @param request 
	 * @return
	 */
	public HashMap<String, String> setCxmrCs(HttpServletRequest request) {
		QgzxCsszService csszService = new QgzxCsszService();
		QgzxGlyglService glyglService = new QgzxGlyglService();
		User user = getUser(request);
		HashMap<String,String> rs = new HashMap<String,String>();
		//������ڹ�����Ա
		if(glyglService.sfQggly(user.getUserName())){
			rs.put("isGly", "true");
		}
		
//		rs.put("dqsj", DateUtils.getDayOfMonth());
		rs.put("kssj", csszService.getFfkssj());
		rs.put("jssj", csszService.getFfjssj());
		return rs;
	}
	
	/**
	 * ����Ĭ�ϲ���
	 * @param request
	 * @param model 
	 * @return
	 */
	public HashMap<String, String> setFfmrCs(HttpServletRequest request, QgzxCjffsqForm model) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> rs = qgzxCsszService.getCssz();
		
		//�����ѡ�к��������š�
		String pkValue = request.getParameter("pkValue");
		if(pkValue!=null&&!"".equalsIgnoreCase(pkValue)){
			request.setAttribute("pkValue", pkValue);
			String[] pkV = pkValue.split("!!@@!!");
			model.setXn(pkV[0]);
			model.setYrbm(pkV[1]);
			List<HashMap<String,String>> gwList = getGwdm(model);
			rs.put("xn",pkV[0]);
			rs.put("yrbm", pkV[1]);
			rs.put("ffny", pkV[2]);
			rs.put("dis", "true");
			request.setAttribute("gwList", gwList);
			request.setAttribute("xnList", Base.getXnndList());
			request.setAttribute("bmList", getYrbm(model));
			request.setAttribute("ffnyList", getFFny(model));
		}else{
			QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
			User user = getUser(request);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			if("xq".equals(QgCommUtilf.getQgzq())){
				model.setXq(null);
			}
			List<HashMap<String,String>> bmList = getYrbmYgw(model);

			//��������ڹ�����Ա
			if(!qgzxGlyglService.sfQggly(user.getUserName())){
				rs.put("disQg", "true");
				model.setYrbm(user.getUserDep());
			} else {
				model.setYrbm(bmList.get(0).get("bmdm"));
			}
			List<HashMap<String,String>> gwList = getGwdm(model);
			//Ĭ��ѧ��
			rs.put("xn", Base.currXn);
			//Ĭ�ϲ���
			rs.put("yrbm", user.getUserDep());
			//Ĭ�Ϸ�������yyyy-MM
			rs.put("ffny", "");
			request.setAttribute("gwList", gwList);
			request.setAttribute("xnList", Base.getXnndList2());
			request.setAttribute("bmList", bmList);
			request.setAttribute("ffnyList", getFFny(model));
		}
		return rs;
	}

	
	/**
	 * ��÷�������
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getFFny(QgzxCjffsqForm model) {
		QgzxCsszService csszService = new QgzxCsszService();
		String ksyf = csszService.getFfkssj().substring(0,6);//���ſ�ʼʱ��
		String jsyf = csszService.getFfjssj().substring(0,6);//���Ž���ʱ��
		if(Base.isNull(jsyf)){
			jsyf = ksyf;
		}
		String yffny = qgzxCjffsqDAO.getGwffny(model);
		List<HashMap<String,String>> ffnyList = scYfList(ksyf,jsyf,yffny);
		return ffnyList;
	}
	
	
	/**
	 * ���ݿ�ʼʱ��ͽ���ʱ�������б�
	 * @param kssj yyyyMM
	 * @param jssj yyyyMM
	 * @return
	 */
	private List<HashMap<String, String>> scYfList(String kssj, String jssj, String yffny) {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		while(Integer.parseInt(kssj)<=Integer.parseInt(jssj)){
			String curryf = jssj.substring(0,4)+"-"+jssj.substring(4,6);
			if(yffny.indexOf(curryf)==-1){
				HashMap<String,String > map = new HashMap<String,String>();
				map.put("ffny", curryf);
				list.add(map);
			}
			//�·ݼ�1
			jssj = yfjy(jssj);
		}
		
		return list;
	}

	/**
	 * �·ݼ�һyyyyMM
	 * @param curryf
	 * @return
	 */
	private String yfjy(String curryf){
		String nf = curryf.substring(0,4);
		String yf = curryf.substring(4,6);
		if("01".equalsIgnoreCase(yf)){
			nf = String.valueOf(Integer.parseInt(nf)-1);
			yf = "12";
		}else{
			yf = String.valueOf(Integer.parseInt(yf)-1);
		}
		return nf+(yf.length()==1?"0"+yf:yf);
	}

	/**
	���������Ի�����
	 **/
	public String  saveCjffForWcsy(QgzxCjffsqForm model) throws Exception{
		String[] xh = model.getXh().split("!!@@!!");
		String[] gwdm = model.getGwdm().split("!!@@!!");
		String ffny = model.getFfny();
		String[] gs = model.getGs().split("!!@@!!");
		String[] je = model.getJe().split("!!@@!!");
		String[] bz = model.getBz().split("!!@@!!");
		String[] khdj = model.getKhdj().split("!!@@!!");
		
		String [] jfhbs = model.getJfhb().split("!!@@!!");
		String [] zcs  = model.getZc().split("!!@@!!");
		
		String cjffr = model.getCjffr();
		List<String[]>params = new ArrayList<String[]>();
		List<String[]>delParams = new ArrayList<String[]>();
		//ѧ��--���
		Map<String,List<HashMap<String,String>>> cjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> cjMap = null;
		List<HashMap<String,String>> cjList= null;
		//��λ--���
		Map<String,List<HashMap<String,String>>> gwcjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> gwcjMap = null;
		List<HashMap<String,String>> gwcjList= null;
	
		
		if(xh[0]==null ||"".equalsIgnoreCase(xh[0])){
			return "����ʧ�ܣ��ø�λ�·���ѧ����Ϣ";
		}
		for (int i = 0; i < gwdm.length; i++) {
			if(gwcjMapList.containsKey(gwdm[i])){
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjMapList.get(gwdm[i]).add(gwcjMap);
				
			}else{
				gwcjList = new ArrayList<HashMap<String,String>>();
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjList.add(gwcjMap);
				gwcjMapList.put(gwdm[i], gwcjList);
			
			}
		}
		for (int i = 0; i < xh.length; i++) {
			if(cjMapList.containsKey(xh[i])){
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjMapList.get(xh[i]).add(cjMap);
				
			}
			else{
				cjList = new ArrayList<HashMap<String,String>>();
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjList.add(cjMap);
				cjMapList.put(xh[i], cjList);
			
			}
		}

		
		for(int i = 0;i < xh.length;i++){
			//���·���ʱ���ֵ���ﲻ��ɾ��  bug ע��
			//delParams.add(new String[]{xh[i],gwdm[i],ffny});
			params.add(new String[]{xh[i],gwdm[i],ffny,gs[i],Base.isNull(je[i])?"0":je[i],bz[i],khdj[i],cjffr,model.getXn(),model.getXq(),jfhbs[i],zcs[i]});
		}
		//ɾ��֮ǰ���з��ŵ�
		String yffxh=model.getYffxh();
		String yffgw=model.getYffgw();
		int m=-1;
		if(StringUtils.isNotNull(yffxh)){
			String yfxh[]=yffxh.split("!!@@!!");
			String yfgw[]=yffgw.split("!!@@!!");
			for(String str:yfxh){
				m++;
				if(StringUtils.isNull(str)){
					continue;
				}
				System.out.println("del:---xh="+str+"; gwid="+yfgw[m]+"; ffny="+ffny);
				delParams.add(new String[]{str,yfgw[m],ffny});
			}
		}
		//ɾ��
		boolean flag = qgzxCjffsqDAO.scXsCjffxx(delParams);
		//������ڽ������ ��ɾ����
		if(flag){
			String[] pkValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny(),model.getXq()};
			flag = qgzxCjffsqDAO.delCjff(pkValue);
		}
		if(flag){
			//����
			System.out.println("-------------�ָ���------------------");
			for(String[] par:params){
				
				System.out.println("add:---xh="+par[0]+"; gwid="+par[1]+"; ffny="+par[2]);
			}
			flag = qgzxCjffsqDAO.bcCjffxxforWcsy(params);
		}
		if(flag){
			//״̬
			model.setTjzt("0");
			flag = qgzxCjffsqDAO.bcGwffZt(model);
		}
		return flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	
	}
	/**
	 * ���棨�ύ����𷢷���Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String saveCjffxx(QgzxCjffsqForm model) throws Exception {
		
		String[] xh = model.getXh().split("!!@@!!");
		String[] gwdm = model.getGwdm().split("!!@@!!");
		String ffny = model.getFfny();
		String[] gs = model.getGs().split("!!@@!!");
		String[] je = model.getJe().split("!!@@!!");
		String[] bz = model.getBz().split("!!@@!!");
		String[] khdj = model.getKhdj().split("!!@@!!");
		String[] jcdlgs = model.getJcdlgs().split("!!@@!!");
		String[] zhdlgs = model.getZhdlgs().split("!!@@!!");
		
		String cjffr = model.getCjffr();
		List<String[]>params = new ArrayList<String[]>();
		List<String[]>delParams = new ArrayList<String[]>();
		Map<String,List<HashMap<String,String>>> cjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> cjMap = null;
		List<HashMap<String,String>> cjList= null;
		Map<String,List<HashMap<String,String>>> gwcjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> gwcjMap = null;
		List<HashMap<String,String>> gwcjList= null;
		String sfsdcjsx = qgzxCsszDAO.getCssz().get("sfsdgwcjsx");
		StringBuffer gwcjOut = new StringBuffer();
		StringBuffer xscjOut = new StringBuffer();
		StringBuffer gsOut = new StringBuffer();
		
		if(xh[0]==null ||"".equalsIgnoreCase(xh[0])){
			return "����ʧ�ܣ��ø�λ�·���ѧ����Ϣ";
		}
		for (int i = 0; i < gwdm.length; i++) {
			if(gwcjMapList.containsKey(gwdm[i])){
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjMapList.get(gwdm[i]).add(gwcjMap);
				
			}else{
				gwcjList = new ArrayList<HashMap<String,String>>();
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjList.add(gwcjMap);
				gwcjMapList.put(gwdm[i], gwcjList);
			
			}
		}
		for (int i = 0; i < xh.length; i++) {
			if(cjMapList.containsKey(xh[i])){
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjMapList.get(xh[i]).add(cjMap);
				
			}
			else{
				cjList = new ArrayList<HashMap<String,String>>();
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjList.add(cjMap);
				cjMapList.put(xh[i], cjList);
			
			}
		}
		//���������Ի���λ������ޡ�������������
		/*for (Map.Entry<String, List<HashMap<String,String>>> entry : gwcjMapList
					.entrySet()) {
			//��λ���
			double gwcj = 0;
			for (HashMap<String,String> gwcjxxMap : entry.getValue()) {
				if(""!=gwcjxxMap.get("je")&&null!=gwcjxxMap.get("je")){
					gwcj+=Double.parseDouble(gwcjxxMap.get("je"));
				}
				
			}
			HashMap<String,String> gwxx = qgzxCjffsqDAO.getGwxx(entry.getKey());
			HashMap<String,String> gwcjxx = qgzxCjffsqDAO.getCjxxByGwdm(entry.getKey(), model.getFfny(), xh);
			if(""!=gwcjxx.get("zje")&&null!=gwcjxx.get("zje")){
				gwcj+=Double.parseDouble(gwcjxx.get("zje"));
			}
			if("yes".equals(sfsdcjsx)&&""!=gwxx.get("gwcjsx")&&null!=gwxx.get("gwcjsx")&&Double.parseDouble(gwxx.get("gwcjsx"))<gwcj){
				gwcjOut.append("["+gwxx.get("gwmc")+"]");
			}
		}*/
		/*for (Map.Entry<String, List<HashMap<String,String>>> entry : cjMapList
				.entrySet()) {
			
			//ҳ�淢�ų��
			double ffje = 0;
			//��ʱ
			double ffgs = 0;
			for (HashMap<String,String> cjxxMap : entry.getValue()) {
				if(""!=cjxxMap.get("je")&&null!=cjxxMap.get("je")){
				ffje+=Double.parseDouble(cjxxMap.get("je"));
				}
				if(""!=cjxxMap.get("gs")&&null!=cjxxMap.get("gs")){
					ffgs+=Float.parseFloat(cjxxMap.get("gs"));
					}
			}
			
			HashMap<String,String> zjeAndzgs = qgzxCjffsqDAO.getCjxxByXh(entry.getKey(),model.getFfny(),gwdm);
			if(""!=zjeAndzgs.get("zje")&&null!=zjeAndzgs.get("zje")){
				ffje+=Double.parseDouble(zjeAndzgs.get("zje"));
			}
			if(""!=zjeAndzgs.get("zgs")&&null!=zjeAndzgs.get("zgs")){
				ffgs+=Double.parseDouble(zjeAndzgs.get("zgs"));
			}
			if("gs".equals(model.getSxzd())&&(Double.parseDouble(model.getSxsz())<ffgs)){
				gsOut.append("["+entry.getKey()+"]");
			}
			
		    if(Double.parseDouble(model.getCjsx())<ffje){
				xscjOut.append("["+entry.getKey()+"]");
			}
			
		}*/
		if("10052".equals(Base.xxdm)&&0!=gwcjOut.length()){
			return "<font color='red'>"+gwcjOut+"</font>"+"���Ž��ܴ��ڸ�λÿ�³������"+model.getCjsx()+"Ԫ";
		}
		if(0!=gsOut.length()){
			return "<font color='red'>"+gsOut+"</font>"+"���Ź�ʱ���ܴ���ѧ��ÿ�¹�ʱ����"+model.getSxsz()+"Сʱ";

		}
		//�㽭��ý����׼��λ��ͬ ����ʱ���� ����֤�������
		/*ɽ���Ƽ���ѧ�����·ݣ�07��08�·ݲ����Ʒ��Ž��*/
		String ffyf = model.getFfny().substring(5,7);
        if(0!=xscjOut.length() && !"10351".equals(Base.xxdm) && !"11647".equals(Base.xxdm)){
        	if("10424".equals(Base.xxdm)){
        		if(!"07".equals(ffyf) && !"08".equals(ffyf)){
        			return "<font color='red'>"+xscjOut+"</font>"+"���Ž��ܴ���ѧ��ÿ�³������"+model.getCjsx()+"Ԫ";
        		}
        	}else{
        		return "<font color='red'>"+xscjOut+"</font>"+"���Ž��ܴ���ѧ��ÿ�³������"+model.getCjsx()+"Ԫ";
        	}
		}
		for(int i = 0;i < xh.length;i++){
			//���·���ʱ���ֵ���ﲻ��ɾ��  bug ע��
			//delParams.add(new String[]{xh[i],gwdm[i],ffny});
			params.add(new String[]{xh[i],gwdm[i],ffny,gs[i],Base.isNull(je[i])?"0":je[i],bz[i],cjffr,model.getXn(),model.getXq()});
		}
		//ɾ��֮ǰ���з��ŵ�
		String yffxh=model.getYffxh();
		String yffgw=model.getYffgw();
		int m=-1;
		if(StringUtils.isNotNull(yffxh)){
			String yfxh[]=yffxh.split("!!@@!!");
			String yfgw[]=yffgw.split("!!@@!!");
			for(String str:yfxh){
				m++;
				if(StringUtils.isNull(str)){
					continue;
				}
				System.out.println("del:---xh="+str+"; gwid="+yfgw[m]+"; ffny="+ffny);
				delParams.add(new String[]{str,yfgw[m],ffny});
			}
		}
		//ɾ��
		boolean flag = qgzxCjffsqDAO.scXsCjffxx(delParams);
		//������ڽ������ ��ɾ����
		if(flag){
			String[] pkValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny()};
			flag = qgzxCjffsqDAO.delCjff(pkValue);
		}
		if(flag){
			//����
			System.out.println("-------------�ָ���------------------");
			for(String[] par:params){
				
				System.out.println("add:---xh="+par[0]+"; gwid="+par[1]+"; ffny="+par[2]);
			}
			flag = qgzxCjffsqDAO.bcCjffxx(params);
		}
		if(flag){
			//״̬
			model.setTjzt("0");
			flag = qgzxCjffsqDAO.bcGwffZt(model);
		}
		return flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * ��֤��𷢷���Ϣ
	 * @param model
	 * @return
	 */
	public String checkTjInfo(QgzxCjffsqForm model) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		//�Ƿ񾭷ѻ��������Ƴ�𷢷ţ�����������֤
		if("no".equalsIgnoreCase(qgzxCsszService.getCssz().get("sfjfhb"))){
			return "";
		}
		String ffje = model.getJe();
		String zje = getTotal(ffje);
		if ("12309".equals(Base.xxdm)) {
			String zcje = model.getZc();
			String zczje = getTotal(zcje);
			model.setZc(zczje);
		}
		model.setJe(zje);
		return qgzxCjffsqDAO.yzCjffxx(model);
	}
	
	/**
	 * ����ܽ��
	 * @param ffje
	 * @return
	 */
	private String getTotal(String ffje) {
		String[] je = ffje.split("!!@@!!");
		double zje = 0;
		for(int i=0;i<je.length;i++){
			zje+=Double.parseDouble(je[i]);
		}
		return String.valueOf(zje);
	}


	/**
	 * ɾ����𷢷���Ϣ
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String czCjffxx(QgzxCjffsqForm model,User user) throws Exception {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		boolean flag = false;
		if("del".equalsIgnoreCase(model.getDoType())){
			flag = qgzxCjffsqDAO.scCjffxx(params);
		}else if("tj".equalsIgnoreCase(model.getDoType())){
			flag = qgzxCjffsqDAO.tjCjffxx(params,user);
		}
		return flag ? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	public String cxtj(QgzxCjffsqForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		boolean flag = false;
		flag=qgzxCjffsqDAO.cxtj(params);
		return flag ? MessageInfo.MESSAGE_CANCEL_NEW_SUCCESS : MessageInfo.MESSAGE_CANCEL_NEW_FALSE;
	}
	
	
	public boolean isHaveFfxx(QgzxCjffsqForm qf){
		String[] pkValue = qf.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		return qgzxCjffsqDAO.isHaveFfxx(params);
	}
	/**
	 * �����Ϣ���˲鿴
	 * @param model
	 * @return
	 */
	public HashMap<String, String> cjxxCk(QgzxCjffsqForm model) {
		HashMap<String,String> rs = qgzxCjffsqDAO.cjxxCk(model);
		List<HashMap<String,String>> cjmxList = qgzxCjffsqDAO.getCjmxList(model);
		rs.put("cjmxHtml", createCjmxHtml(cjmxList));
		rs.put("listSize", cjmxList.size()+"");
		rs.put("xqmc", Base.getXqmcForXqdm(rs.get("xq")));
		return rs;
	}

	
	/**
	 * ������𷢷���ϸhtml
	 * @param cjmxList
	 * @return
	 */
	private String createCjmxHtml(List<HashMap<String, String>> rsArrList) {
		String csz = getCspzxx("cjffgs");
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String, String> rs = rsArrList.get(i);
				html.append("<tr><td>"+rs.get("r")+"</td>");
				html.append("<td>"+rs.get("xh")+"</td>");
				html.append("<td>"+rs.get("xm")+"</td>");
				html.append("<td>"+rs.get("gwmc")+"</td>");
				html.append("<td>"+rs.get("gwxzmc")+"</td>");
				if("1".equals(csz)){
					html.append("<td>"+(Base.isNull(rs.get("gs"))?"":rs.get("gs"))+"</td>");
				}
				html.append("<td>"+(Base.isNull(rs.get("je"))?"":rs.get("je"))+"</td>");
				html.append("<td>"+(Base.isNull(rs.get("yhkh"))?"":rs.get("yhkh"))+"</td>");
				html.append("<td id='bz"+i+"' title='"+(Base.isNull(rs.get("bz"))?"":rs.get("bz"))+"'>"+(Base.isNull(rs.get("bz"))?"":rs.get("bz"))+"</td>");
				html.append("<td>"+(Base.isNull(rs.get("cjffrXm"))?"":rs.get("cjffrXm"))+"</td></tr>");
			}
		}
		return html.toString();
	}


	/**
	 * ���ɳ���׼��ѧ�ţ���λ���룩
	 * @param model
	 * @return
	 */
	public String scCjbz(QgzxCjffsqForm model) {
		KnsjgDao knsjgDao = new KnsjgDao();
		String zd1 = "";
		String zd2 = qgzxCjffsqDAO.getGwxxMap(model).get("gwxzmc");
		HashMap<String,String> knsrddc = knsjgDao.getKnsrddc(model.getXh(),model.getXn());
		if(knsrddc!=null && knsrddc.size()!=0){
			zd1 = knsrddc.get("dcmc");
		}
	
		String cjbz = qgzxCjffsqDAO.getCjbz(new String[]{zd1,zd2,Base.xxdm});
		if(Base.isNull(cjbz)){
			QgzxCsszService qgzxCsszService = new QgzxCsszService();
			cjbz = qgzxCsszService.getCssz().get("cjbz");
		}
		return cjbz;
	}

	
	/**
	 * �Զ������ύ��𷢷���Ϣ�����ڵģ�
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean aotoTjCjffxx() throws Exception {
		return qgzxCjffsqDAO.aotoTjCjffxx();
	}


	/** 
	 * @����: ��ҳ��ʼ��
	 * @���ߣ�������[���ţ�964]
	 * @���ڣ�2014-4-21 ����04:46:50
	 * @param request
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> setFfmrCsNew(HttpServletRequest request,
			QgzxCjffsqForm model) {QgzxCsszService qgzxCsszService = new QgzxCsszService();
			List<HashMap<String, String>> bmList= new ArrayList<HashMap<String, String>>();
			HashMap<String,String> rs = qgzxCsszService.getCssz();
			User user = getUser(request);
			//�����ѡ�к��������š�
			String pkValue = request.getParameter("pkValue");
			if(pkValue!=null&&!"".equalsIgnoreCase(pkValue)){
				request.setAttribute("pkValue", pkValue);
				String[] pkV = pkValue.split("!!@@!!");
				model.setXn(pkV[0]);
				model.setXq(pkV[3]);
				if("none".equals(pkV[3])){
					model.setXq(null);
				}
				model.setYrbm(pkV[1]);
				List<HashMap<String,String>> gwList = getGwdm(model);
				rs.put("xn",pkV[0]);
				rs.put("yrbm", pkV[1]);
				rs.put("ffny", pkV[2]);
				rs.put("cjffr", user.getUserName());
				rs.put("dis", "true");
				request.setAttribute("gwList", gwList);
				request.setAttribute("xnList", Base.getXnndList());
				request.setAttribute("bmList", getYrbmYgw(model));
				request.setAttribute("ffnyList", getFFny(model));
			}else{
				QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
				model.setXn(Base.currXn);
				if("xq".equals(QgCommUtilf.getQgzq())){
					model.setXq(Base.currXq);
				}
				model.setCjffr(user.getUserName());

				//��������ڹ�����Ա
				if(!qgzxGlyglService.sfQggly(user.getUserName())){
					bmList=getYrbmOfUser(user);
					rs.put("disQg", "true");
				}else{
					bmList=getYrbmYgw(model);
				}
				model.setYrbm(bmList.get(0).get("bmdm"));
				List<HashMap<String,String>> gwList = getGwdm(model);
				//Ĭ��ѧ��
				rs.put("xn", Base.currXn);
				//��𷢷���
				rs.put("cjffr", user.getUserName());
				//Ĭ�ϲ���
				rs.put("yrbm", user.getUserDep());
				request.setAttribute("gwList", gwList);
				request.setAttribute("xnList", Base.getXnndList());
				request.setAttribute("bmList", bmList);
				request.setAttribute("ffnyList",getFFny(model) );
				//Ĭ�Ϸ�������yyyy-MM
			   String dqrq = DateUtils.getYear()+"-"+DateUtils.getMonth();
			   rs.put("ffny", dqrq);
			}
			QgCommUtilf.setCssz(request);
			request.setAttribute("xqList", Base.getXqList());
			return rs;
	}
	

	/**
	 * ������˲����б��и�λ�ģ�
	 * @param model
	 * @return �����б�
	 */
	public List<HashMap<String, String>> getYrbmYgw(QgzxCjffsqForm model) {
		return qgzxCjffsqDAO.getYrbmYgw(model);
	}
	
	/**
	 * 
	 * @����:��ѯ���Ѷ��٣��Ѿ�ʹ�ö��٣�ʣ�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-21 ����03:55:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getJftx (User user){
		return qgzxCjffsqDAO.getJftx(user);
	}


	/** 
	 * @����:����һ����𷢷���ϸ����Excel�ļ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��7�� ����8:59:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param path
	 * @param model
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getCjxxExcel(String path, QgzxCjffsqForm model) {
		HashMap<String,String> rs = qgzxCjffsqDAO.cjxxCk(model);
		List<HashMap<String,String>> cjmxList = qgzxCjffsqDAO.getCjmxList(model);
		
		File file = new File(path,"cjmx_"+System.currentTimeMillis()+".xls");
		
		// ���ļ�
		WritableWorkbook book = null;
		
		 try {
			book = Workbook.createWorkbook(file);
			WritableSheet sheet1 = book.createSheet("��𷢷���ϸ��", 0);
			//�ϲ���Ԫ��
			sheet1.mergeCells(0, 0, 9, 0); 
			
			sheet1.mergeCells(0, 1, 9, 1);
			
			sheet1.mergeCells(0, 2, 1, 2);
			sheet1.mergeCells(2, 2, 4, 2);
			sheet1.mergeCells(5, 2, 6, 2);
			sheet1.mergeCells(7, 2, 9, 2);
			
			sheet1.mergeCells(0, 3, 1, 3);
			sheet1.mergeCells(2, 3, 4, 3);
			sheet1.mergeCells(5, 3, 6, 3);
			sheet1.mergeCells(7, 3, 9, 3);
			
			sheet1.mergeCells(0, 4, 9, 4);
			
			//��Ԫ���ʽ
		    WritableCellFormat wcf1 = new WritableCellFormat();  
		    WritableCellFormat wcf2 = new WritableCellFormat();  
		    wcf1.setAlignment(Alignment.CENTRE);//��ˮƽ���뷽ʽָ��Ϊ���� 
		    wcf1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
		    wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); //���ñ߿�
		    
		    wcf2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
		    wcf2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
		    
			
			Label label_c0r0 = new Label(0,0,"��𷢷���ϸ��",wcf1);
			
			Label label_c0r1 = new Label(0,1,"��λ��Ϣ",wcf2);
			
			Label label_c0r2 = new Label(0,2,"ѧ��",wcf2);
			Label label_c2r2 = new Label(2,2,rs.get("xn"),wcf2);
			Label label_c5r2 = new Label(5,2,"���˲���",wcf2);
			Label label_c7r2 = new Label(7,2,rs.get("yrdwmc"),wcf2);
			
			Label label_c0r3 = new Label(0,3,"��������",wcf2);
			Label label_c2r3 = new Label(2,3,rs.get("ffny"),wcf2);
			Label label_c5r3 = new Label(5,3,"�ύ״̬");
			Label label_c7r3 = new Label(7,3,rs.get("tjztmc"),wcf2);
			
			Label label_c0r4 = new Label(0,4,"�����ϸ��Ϣ",wcf2);
			
			Label label_c0r5 = new Label(0,5,"�к�",wcf2);
			Label label_c1r5 = new Label(1,5,"ѧ��",wcf2);
			Label label_c2r5 = new Label(2,5,"����",wcf2);
			Label label_c3r5 = new Label(3,5,"��λ����",wcf2);
			Label label_c4r5 = new Label(4,5,"��λ����",wcf2);
			Label label_c5r5 = new Label(5,5,"��ʱ",wcf2);
			Label label_c6r5 = new Label(6,5,"���",wcf2);
			Label label_c7r5 = new Label(7,5,"���п���",wcf2);
			Label label_c8r5 = new Label(8,5,"��𷢷���",wcf2);
			Label label_c9r5 = new Label(9,5,"��ע",wcf2);
			
			sheet1.addCell(label_c0r0);
			
			sheet1.addCell(label_c0r1);
			
			sheet1.addCell(label_c0r2);
			sheet1.addCell(label_c2r2);
			sheet1.addCell(label_c5r2);
			sheet1.addCell(label_c7r2);
			
			sheet1.addCell(label_c0r3);
			sheet1.addCell(label_c2r3);
			sheet1.addCell(label_c5r3);
			sheet1.addCell(label_c7r3);
			
			sheet1.addCell(label_c0r4);
			
			sheet1.addCell(label_c0r5);
			sheet1.addCell(label_c1r5);
			sheet1.addCell(label_c2r5);
			sheet1.addCell(label_c3r5);
			sheet1.addCell(label_c4r5);
			sheet1.addCell(label_c5r5);
			sheet1.addCell(label_c6r5);
			sheet1.addCell(label_c7r5);
			sheet1.addCell(label_c8r5);
			sheet1.addCell(label_c9r5);
			
			for(int i=0;i<cjmxList.size();i++){
				Label label0 = new Label(0,6+i,1+i+"",wcf2);
				Label label1 = new Label(1,6+i,cjmxList.get(i).get("xh"),wcf2);
				Label label2 = new Label(2,6+i,cjmxList.get(i).get("xm"),wcf2);
				Label label3 = new Label(3,6+i,cjmxList.get(i).get("gwmc"),wcf2);
				Label label4 = new Label(4,6+i,cjmxList.get(i).get("gwxzmc"),wcf2);
				Label label5 = new Label(5,6+i,cjmxList.get(i).get("gs"),wcf2);
				Label label6 = new Label(6,6+i,cjmxList.get(i).get("je"),wcf2);
				Label label7 = new Label(7,6+i,cjmxList.get(i).get("yhkh"),wcf2);
				Label label8 = new Label(8,6+i,cjmxList.get(i).get("cjffrXm"),wcf2);
				Label label9 = new Label(9,6+i,cjmxList.get(i).get("bz"),wcf2);
				
				sheet1.addCell(label0);
				sheet1.addCell(label1);
				sheet1.addCell(label2);
				sheet1.addCell(label3);
				sheet1.addCell(label4);
				sheet1.addCell(label5);
				sheet1.addCell(label6);
				sheet1.addCell(label7);
				sheet1.addCell(label8);
				sheet1.addCell(label9);
			}
			
			sheet1.setColumnView(0, 5);
			sheet1.setColumnView(1, 15);
			sheet1.setColumnView(2, 15);
			sheet1.setColumnView(3, 15);
			sheet1.setColumnView(4, 15);
			sheet1.setColumnView(5, 5);
			sheet1.setColumnView(6, 8);
			sheet1.setColumnView(7, 25);
			sheet1.setColumnView(8, 15);
			sheet1.setColumnView(9, 20);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally {
			// д�����ݲ��ر��ļ�
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		return file;
	}


	/** 
	 * @����������б�
	 * @throws Exception 
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getCjxxShList(QgzxCjffsqForm t, User user) throws Exception {
		return qgzxCjffsqDAO.getCjxxShList(t, user);
	}
	
	public boolean saveSh(QgzxCjffsqForm t, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(t.getSplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(t.getShyj());
		// ���״̬
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		// ��˸�λID
		model.setGwid(t.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(t.getSqid());
		model.setTzlj("qgzx_cjffsh_cjxxgl.do");
		model.setTzljsq("qgzx_cjffsq_cjxxgl.do");
		boolean result = false;
		try {
			String shzt = shlc.runAuditing(model);
			result = qgzxCjffsqDAO.updateShzt(t.getSqid(),shzt);
			// ���浽�����
			if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
				//TODO
//				TsqkjgForm tsqkjgForm = new TsqkjgForm();
//				TsqkjgService tsqkjgService = new TsqkjgService();
//				TsqktbForm tsqktbForm = new TsqktbDao().getModel(form.getSqid());
//				BeanUtils.copyProperties(tsqkjgForm, StringUtils.formatData(tsqktbForm));
//				tsqkjgForm.setLylcywid(tsqktbForm.getSqid());
//				tsqkjgForm.setSjly("1");
//				if(tsqkjgService.isHaveRecordForjg(tsqkjgForm)){
//					//���������д������ݣ���ɾ���ٲ���
//					new TsqkjgDao().deleteForSq(tsqkjgForm);
//					tsqkjgService.runInsert(tsqkjgForm);
//				}else{
//					tsqkjgService.runInsert(tsqkjgForm);
//				}
				qgzxCjffsqDAO.deleteJg(t.getSqid());
				qgzxCjffsqDAO.insertJg(t.getSqid());
				
				String pkValue=qgzxCjffsqDAO.getPkvalue(t.getSqid());
				QgzxCjffjgDAO qgzxCjglDAO = new QgzxCjffjgDAO();
				List<String[]> paramsPkvalue = new ArrayList<String[]>();
				paramsPkvalue.add(pkValue.split("!!@@!!"));
				qgzxCjglDAO.tjCjffxx(paramsPkvalue);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String savePlsh(QgzxCjffsqForm t, User user) throws Exception{
		String[] sqids = t.getSqids();
		String[] gwids = t.getGwids();
		String[] splcs = t.getSplcs();
		List<String> failXms = new ArrayList<String>();
		QgzxCjffsqForm model = new QgzxCjffsqForm();
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplc(splcs[i]);
			model.setGwid(gwids[i]);
			model.setSqid(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShjg());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(sqids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShjg())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * @�������������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * String ��������
	 */
	public String cxsh(String ywid, QgzxCjffsqForm t, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), t.getShid(), t.getSplc());
		qgzxCjffsqDAO.updateShzt(ywid, shzt);
		return cancelFlag;
	}
	
	/**
	 * @������������ɾ��������е�����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��6��14�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * boolean ��������
	 */
	public boolean cxshdel(QgzxCjffsqForm t) throws Exception {
		//TODO
		/*t.setShzt(Constants.YW_SHZ);
		boolean result = qgzxCjffsqDAO.runUpdate(t, t.getSqid());
			// ɾ��������е�����
			TsqkjgDao tsqkjgDao = new TsqkjgDao();
			result = tsqkjgDao.delByLclyywid(t.getSqid());*/
		String pkValue=qgzxCjffsqDAO.getPkvalue(t.getSqid());
		QgzxCjffjgDAO qgzxCjglDAO = new QgzxCjffjgDAO();
		List<String[]> paramsPkvalue = new ArrayList<String[]>();
		
		paramsPkvalue.add(pkValue.split("!!@@!!"));
		qgzxCjglDAO.cxtj(paramsPkvalue);
		
		qgzxCjffsqDAO.deleteJg(t.getSqid());
		return true;
	}
	
	/**
	 * @����: ȡ�������ñ��еĲ���ֵ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-10 ����10:32:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cjffgs
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCspzxx(String cjffgs) {
		return qgzxCjffsqDAO.getCspzxx(cjffgs);
	}
	
	/**
	 * @description	�� ����иò��Ÿø�λ�Ĺ�ʱû��ά�����������ύ���ൺ�Ƶ���Ի���
	 * @author 		�� ������1282��
	 * @date 		��2017-11-21 ����03:09:56
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String sfTj(QgzxCjffsqForm model) throws Exception{
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		String[] values = pkValue[0].split("!!@@!!");
		String xn = values[0];
		String yrdwdm = values[1];
		String ffny = values[2];
		String[] params = new String[]{ffny,xn,yrdwdm,ffny};
		List<HashMap<String,String>> list = qgzxCjffsqDAO.getWwhGw(params);
		StringBuilder sb = new StringBuilder();
		if(list.size() > 0 ){
			for(int i = 0;i<list.size();i++){
				sb.append(list.get(i).get("gwmc"));
				if(i != list.size() - 1){
					sb.append("��");
				}
			}
			return sb.toString();
		}else{
			return sb.append("").toString();
		}
	}

	public HashMap<String,Object> saveImport(InputStream inputStream, String path, String drmkdm,User user) throws Exception{

		HashMap<String, Object> resultMap = null;
		//��ȡ�����й���
		List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = Workbook.getWorkbook(inputStream);
			sheet = wb.getSheet(0);
			//��֤ģ��ͷ����  error:01
			resultMap = zdydrService.checkImportHeader(sheet, drgzxxList);
			if((Boolean) resultMap.get("result")){
				//ģ��������������Ĳ���
				//��ȡ�����д���List<HashMap<String,String>>  ÿ�ж�Ӧһ��HashMap
				List<HashMap<String,String>> excelDataList = zdydrService.getExcelDataList(sheet,drgzxxList);

				if(excelDataList.isEmpty()){
					resultMap.put("totalCount", excelDataList.size());
					return resultMap;
				}

				//���Ի�������֤��װ
				/*List<HashMap<String,Object>> lhList = new ArrayList<HashMap<String, Object>>();
				HashMap<String,Object> xhxmlhMap = new HashMap<String, Object>();
				xhxmlhMap.put("tableName","xsxxb");
				xhxmlhMap.put("drlArr",new String []{"xh","xm"});
				xhxmlhMap.put("drlmcArr",new String []{"ѧ��","����"});

				HashMap<String,Object> rdnrlhMap = new HashMap<String, Object>();
				rdnrlhMap.put("tableName","xg_dekt_xmdmb");
				rdnrlhMap.put("drlArr",new String []{"lx","rdxm","rdnrbz","dj"});
				rdnrlhMap.put("drlmcArr",new String []{"����","�϶���Ŀ","�϶����ݼ���׼","�ȼ�"});

				lhList.add(xhxmlhMap);
				lhList.add(rdnrlhMap);*/

				//������������ڲ�ѯ��֤�ظ���
				/*String tableName = "(SELECT a.*,b.lx,b.RDXM,b.RDNRBZ,b.DJ FROM XG_DEKT_DEKTXFSQB a " +
						"LEFT JOIN XG_DEKT_XMDMB b ON a.XMID = b.XMID)";*/

				//����ǰ������������֤ error:02
				resultMap = zdydrService.checkExcelDataList(excelDataList,drgzxxList,null,null);
				if((Boolean) resultMap.get("result")){
					//��֤ͨ��������Ҫ�ж�excel�����б����Ƿ����ظ�
					resultMap = zdydrService.checkExcelDataRepeat(excelDataList,drgzxxList);
					if((Boolean) resultMap.get("result")){
						//�������ظ���������Ĳ������ݵ����ݿ�Ĳ���
						boolean insertResult = this.insertDataIntoDB(excelDataList,user);
						if(insertResult){
							resultMap.put("totalCount", excelDataList.size());
						}
					}else{
						//�����ظ������ݴ���������ʾ����excel�ļ���������error:03
						String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
						resultMap.put("errorTipsExcelName", errorTipsExcelName);
					}
				}else{
					//��֤��ͨ�������ݴ���������ʾ����excel�ļ���������
					String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
					resultMap.put("errorTipsExcelName", errorTipsExcelName);
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList,User user) throws Exception {

		String splc=qgzxCjffsqDAO.getSplcId();
		String xn = Base.currXn;
		List<String[]> jcffsqbList = new ArrayList<String[]>();
		List<String[]> gwffztsqList = new ArrayList<String[]>();
		//�ڹ���ѧ-��λ��������״̬�������ظ��ж���map
		Map<String,String> map = new HashMap<String, String>();
		//�ύ�����
		List<String> sqids = new ArrayList<String>();
		//ɾ�����ݸ�λ��������״̬������list
		List<String[]> delParam = new ArrayList<String[]>();
		for(HashMap<String,String> excelData:excelDataList){
			String xh = excelData.get("xh");
			String yrdwdm = excelData.get("bmdm");
			String gwdm = excelData.get("gwdm");
			String ffny = excelData.get("ffny");
			String gz = excelData.get("gz");
			String gs = excelData.get("gs");

			jcffsqbList.add(new String[]{xh,gwdm,ffny,gs,gz,xn});
			delParam.add(new String[]{xh,gwdm,ffny});
			//��λ��������״̬������
			String pk = xn+","+yrdwdm+","+ffny;
			if(!map.containsKey(pk)){
				String sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
				gwffztsqList.add(new String[]{yrdwdm,ffny,xn,gwdm, sqid,splc});
				sqids.add(sqid);
				map.put(pk,pk);
			}
		}
		boolean result;
		//ɾ��
		result = qgzxCjffsqDAO.scXsCjffxx(delParam);

		//������ڽ������ ��ɾ����
		if(result){
			for(String key : map.keySet()){
				String[] pkValue = key.split(",");
				result = qgzxCjffsqDAO.delCjff(pkValue);
			}

		}
		if(result){
			result = qgzxCjffsqDAO.batchInsertDataIntoDB(jcffsqbList,gwffztsqList);
		}

		if(result){
			for(String sqid : sqids){
				shlc.runSubmit(sqid, splc, user.getUserName(), "qgzx_cjffsh_cjxxgl.do", "qgzx_cjffsq_cjxxgl.do");
			}
		}
		return result;
	}
}
