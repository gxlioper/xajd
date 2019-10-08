package xsgzgl.qgzx.gwglnew;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.conditions.KnsrdCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.lxsh.LxshForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgDao;
import com.zfsoft.xgxt.jskp.xmjg.XmjgForm;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlxxModel;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import common.Globals;
/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 */
public class QgzxGwglService extends BasicService {
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	QgzxGwglDAO qgzxGwglDAO = new QgzxGwglDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public List<HashMap<String, String>> getGwxxPageList(QgzxGwglForm t, User user)
		throws Exception {
		return qgzxGwglDAO.getGwxxPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwxxAllList(QgzxGwglForm t, User user)
		throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return qgzxGwglDAO.getGwxxPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwsqPageList(QgzxGwglForm t, User user)
		throws Exception {
		return qgzxGwglDAO.getGwsqPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwsqAllList(QgzxGwglForm t, User user)
		throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return qgzxGwglDAO.getGwsqPageList(t, user);
	}
	
	public List<HashMap<String, String>> getGwshPageList(QgzxGwglForm t, User user)
		throws Exception {
		return qgzxGwglDAO.getGwshPageList(t, user);
	}
	
	/**
	 * ��ñ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		DAO dao = DAO.getInstance();
		String[] en = null;
		String[] cn = null;
		if("10052".equals(Base.xxdm)){
			 en = new String[] { "", "r", "xn", "bmmc","gwxh", "gwmc", "gwxzmc", "xqrs", "zgrs", "tgrs" };
			 cn = new String[] { "", "�к�", "ѧ��", "���˲���", "��λ���","��λ����", "��λ����", "��������","�ڸ�����","�˸�����" };
		}
		else{
		 en = new String[] { "", "r", "xn", "bmmc", "gwmc", "gwxzmc", "xqrs", "zgrs", "tgrs" };
		 cn = new String[] { "", "�к�", "ѧ��", "���˲���", "��λ����", "��λ����", "��������","�ڸ�����","�˸�����" };
		}
		if("stu".equalsIgnoreCase(type)){
			en = new String[]{"", "r", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "sfkns" };
			cn = new String[]{"", "�к�", "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","�Ƿ�������" };
		}else if("gwsq".equalsIgnoreCase(type)){
			//�㽭��ְͨҵ
			if("12036".equals(Base.xxdm)){
				en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
				cn = new String[]{"", "�к�", "ѧ��", "���˲���", "��λ����","��λ����", "��������", "������������", "����ʱ��", "���״̬" };
			}
			else{
			en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
			cn = new String[]{"", "�к�", "ѧ��", "���˲���", "��λ����","��λ����", "��������", "������������", "����ʱ��", "���״̬" };
			}
		}else if("gwsh".equalsIgnoreCase(type)){
			en = new String[]{"", "r", "xn", "yrbmmc", "gwmc","gwxzmc", "xqrs", "xqknss", "sqsj", "shzt" };
			cn = new String[]{"", "�к�", "ѧ��", "���˲���", "��λ����","��λ����", "��������", "������������", "����ʱ��", "���״̬" };
		}
		
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * ��ø�λ��ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwsqList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwsqList(model);
	}
	
	
	/**
	 * ��ø�λ��ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwshList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwshList(model);
	}
	
	
	/**
	 * ��ø�λ��ϢList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxxList(QgzxGwglForm model) throws Exception {

		return qgzxGwglDAO.getGwxxList(model);
	}
	
	
	/**
	 * ���ѧ����ϢList
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getStuList(QgzxGwglForm model,HttpServletRequest request) throws Exception {
		
		return qgzxGwglDAO.getStuList(model,request);
	}
	
	public List<HashMap<String, String>> getStuPageList(QgzxGwglForm t, User user, String sfxyzgsc)
		throws Exception {
		return qgzxGwglDAO.getStuPageList(t, user, sfxyzgsc);
	}

	
	/**
	 * ��ø�λ��ϢMap
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxxMap(QgzxGwglForm myForm) {
		return qgzxGwglDAO.getGwxxMap(myForm);
	}
	
	
	/**
	 * ��ø�λ����Ա��Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getGwryxx(QgzxGwglForm model,HttpServletRequest request)throws Exception{
		//��λ��Ϣ
		HashMap<String,String> rs = qgzxGwglDAO.getGwxxMap(model);
		//����ڸ���Ա������html
		model.setZgzt("zg");
		//�ڸ���Աѧ��
		String[] zgryxh = qgzxGwglDAO.getRyxhList(model);
		//�����ڸ�ѧ��ѧ��ֵ��ǰ̨
		setRequest(request,zgryxh,rs);
		List<HashMap<String, String>> zgryxxList = getGwxsxxList(zgryxh,model);
		// ========= �ڸ��������� < =========
		String zgknsrs = "0";
		if(zgryxxList.size() > 0){
			zgknsrs = zgryxxList.get(0).get("zgknsrs");
		}
		rs.put("zgknsrs", zgknsrs);
		// ========= �ڸ��������� > =========
		rs.put("zgryHtml", createRyxxHtml(zgryxxList,model));
		//����˸���Ա������html
		model.setZgzt("tg");
		String[] tgryxh = qgzxGwglDAO.getRyxhList(model);
		List<HashMap<String, String>> tgryxxList = getGwxsxxList(tgryxh,model);
		rs.put("tgryHtml", createRyxxHtml(tgryxxList,model));
		//��ǰʱ�� yyyyMMdd
		DAO dao = DAO.getInstance();
		String currTime = dao.getNowTime("yyyyMMdd");
		rs.put("tgsj", currTime);
		return rs;
	}
	
	
	/**
	 * ��ø�λ����MAP
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getGwsqMap(QgzxGwglForm model){
		return qgzxGwglDAO.getGwsqMap(model);
	}
	/** 
	 * @����:(�Ƿ������һ����λ)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-28 ����06:59:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isZhgw(QgzxGwglForm model) {
		ArrayList<HashMap<String, String>> spgws=qgzxGwglDAO.getSplcgw(model);
		String spgw=spgws.get(spgws.size()-1).get("spgw");
		return model.getGwid().equalsIgnoreCase(spgw);
	}
	
	/**
	 * �����ڸ�ѧ��ѧ��+�Ѿ��ﵽ����ڹ��� ��ѧ��ֵ��ǰ̨
	 * @param request
	 * @param zgryxh
	 * @throws SQLException 
	 */
	private void setRequest(HttpServletRequest request, String[] zgrypk, HashMap<String,String> gwxx) throws SQLException {
		String xn = gwxx.get("xn");
		int count = 0;
		//���ѧ���Ѿ��������޵�ѧ��ѧ��
		String[] sxxhs = getGwxsxh(xn); 
		StringBuilder xhs = new StringBuilder();
		for(int i = 0;i<zgrypk.length;i++){
			String zgryxh = zgrypk[i].split("!!@@!!")[0];
			//�ж��Ƿ���������
			KnsrdCondition knsrd = new KnsrdCondition();
			HashMap<String,String> knsTj = new HashMap<String,String>();
			if ("xq".equals(SQZQ)) {
				knsTj.put("xn", Base.currXn + ";" + Base.currXq);
			}else{
				knsTj.put("xn", Base.currXn);
			}
			String knsCount = knsrd.getKnsrdInfo(zgryxh, knsTj);
			if(knsCount==null && "".equals(knsCount)){
				knsCount="0";
			}	
			if(Integer.parseInt(knsCount)>0){
				count++;
			}
			xhs.append(zgryxh+"!!@@!!");
		}
		for(int i = 0;i<sxxhs.length;i++){
			String sxxh = sxxhs[i].split("!!@@!!")[0];
			xhs.append(sxxh+"!!@@!!");
		}
		request.setAttribute("yykns", count);
		request.setAttribute("xhs", xhs.toString());
	}


	/**
	 * ��ø�ѧ���Ѿ��������޵�ѧ��ѧ��
	 * @param xn
	 * @return
	 * @throws SQLException 
	 */
	private String[] getGwxsxh(String xn) throws SQLException {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		String xsgws = qgzxCsszService.getCssz().get("xsgws");
		if("0".equalsIgnoreCase(xsgws)){
			return new String[]{};
		}
		return qgzxGwglDAO.getGwxsxh(xn,xsgws);
	}


	/**
	 * ��ø�λѧ����Ϣ
	 * @param myForm
	 * @return
	 */
	private List<HashMap<String, String>> getGwxsxxList(String[] pks,QgzxGwglForm model) throws Exception {
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String,String>>();
		int zgknsrs = 0; // �ڸ���������
		for(int i = 0; i < pks.length;i++){
			String[] pkV = pks[i].split("!!@@!!");
			model.setXh(pkV[0]);
			model.setSgsj(pkV[1]);
			//model.setSqbhs(pkV[3]);
			HashMap<String, String> rs = qgzxGwglDAO.getGwxsxx(model);
			KnsrdCondition knsrd = new KnsrdCondition();
			//���û�ȡ�Ƿ����������� ѧ������
			HashMap<String,String> param= new HashMap<String,String>();
			HashMap<String, String> gwxx=qgzxGwglDAO.getGwxxMap(model);
			if ("xq".equals(SQZQ)) {
				param.put("xn", Base.currXn + ";" + Base.currXq);
			}else{
				param.put("xn", Base.currXn);
			}
			String knsCount = knsrd.getKnsrdInfo(pkV[0],param);
			if(knsCount==null && "".equals(knsCount)){
				knsCount="0";
			}	
			if(rs!=null&&rs.size()!=0){
				rs.put("r", String.valueOf(i+1));
				//�ж��Ƿ�������
				if(Integer.parseInt(knsCount)>0){
					rs.put("sfkns", "��");
					zgknsrs++;
				}else{
					rs.put("sfkns", "��");
				}
				ryxxList.add(rs);
			}
		}
		if(ryxxList.size() > 0){
			ryxxList.get(0).put("zgknsrs", String.valueOf(zgknsrs));
		}
		return ryxxList;
	}
	
	
	/**
	 * ���ѧ����Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getXsxxList(QgzxGwglForm model) {
		DAO dao = DAO.getInstance();
		String[] xhs = model.getPkValue().split("!!@@!!");
		List<HashMap<String, String>> ryxxList = new ArrayList<HashMap<String,String>>();
		for(int i = 0; i < xhs.length;i++){
			model.setXh(xhs[i]);
			HashMap<String, String> rs = qgzxGwglDAO.getXsxx(model);
			if(rs!=null&&rs.size()!=0){
				rs.put("r", String.valueOf(i+1));
				//�ж��Ƿ�������
				rs.put("sfkns", dao.isKns(xhs[i])?"��":"��");
				rs.put("sgsj", dao.getNowTime("yyyyMMdd"));
				ryxxList.add(rs);
			}
			
		}
		return ryxxList;
	}
	
	
	/**
	 * ������Ա��Ϣhtml
	 * @param tgryxxList
	 * @return
	 */
	private String createRyxxHtml(List<HashMap<String, String>> rsArrList,QgzxGwglForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String,String> rs = rsArrList.get(i);
				if("ryxxZj".equalsIgnoreCase(model.getDoType()) || "ryxxTg".equalsIgnoreCase(model.getDoType())){
					html.append("<tr><td width='7%;'><input type='checkbox' value='"+rs.get("xh")+"!!@@!!"+rs.get("sgsj")+"!!@@!!"+rs.get("sjly")+"!!@@!!"+rs.get("sqbh")+"'/></td>");
				}else{
					html.append("<tr><td width='7%;'>"+rs.get("r")+"</td>");
				}
				html.append("<td width='13%'><a href='javascript:void(0);' class='name' onclick='zxsxxView(&quot;"
						+ rs.get("xh")
						+ "&quot;);return false;'>"
						+ rs.get("xh") + "</a></td>");
//				html.append("<td>"+rs.get("xh")+"</td>");
				html.append("<td width='13%'>"+rs.get("xm")+"</td>");
				html.append("<td width='31%'>"+rs.get("bjmc")+"</td>");
				html.append("<td width='11%'>"+rs.get("sfkns")+"</td>");
				html.append("<td width='10%'>"+rs.get("sfzx")+"</td>");
				if("ryxxZj".equalsIgnoreCase(model.getDoType())||"ryxxTg".equalsIgnoreCase(model.getDoType())){
					html.append("<td width='15%'>");
					html.append("<a href=\"#\" class=\"name\" onclick=\"showXsxx('"+replaceHtml(rs.get("xh"))+"');return false;\" value=\"" +replaceHtml(rs.get("xh"))+"\">");
					html.append(replaceHtml(rs.get("qggws")));
					html.append("</a></td>");
					if(Base.xxdm.equals(Globals.XXDM_ZJJTZYJSXY)&&"ryxxZj".equalsIgnoreCase(model.getDoType())){
					html.append("<td width='10%'><input type='text' width='90px' value='"+rs.get("sgsj")+"' name='sgsj' onfocus=\"showCalendar(this.id,'yyyyMM');\" id='sgsj"+i+"'>");
					}
				}
				if("gwxxCk".equalsIgnoreCase(model.getDoType())){
					html.append("<td width='15%'>"+rs.get("sgsj")+"</td>");
					if("tg".equalsIgnoreCase(model.getZgzt())){
						html.append("<td>"+rs.get("tgsj")+"</td>");
					}
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * ���������ӵĸ�λ��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean gwxxBc(QgzxGwglForm model) throws Exception {
		String gwxh="";
		if("10052".equals(Base.xxdm)){
			gwxh=qgzxGwglDAO.GetGwxh(model.getYrbm(), model.getXn());
		}
		model.setGwxh(gwxh);
		String gwdm = UniqID.getInstance().getUniqIDHash();
		model.setGwdm(gwdm);
		boolean result = new QgzxGwglJgDao().runInsert(model);
		if(Base.xxdm.equalsIgnoreCase("10351") && result) {//���ݴ�ѧ���Ի�
			if(result){				
				model.setDoType("zj");
				return bcSqglXy(model);
			}
		}
		return result;
	}
	
	
	/**
	 * ͬһѧ��,ͬһ�����Ƿ������ͬ��λ���Ƶĸ�λ��Ϣ
	 * @param model
	 * @return
	 */
	private boolean isExist(QgzxGwglForm model) {
		return qgzxGwglDAO.isExist(model);
	}
	
	/**
	 * ͬһ������Ч��λ�����ظ�
	 * @param model
	 * @return
	 */
	private boolean isExistSj(QgzxGwglForm model) {
		return qgzxGwglDAO.isExistSj(model);
	}
	
	
	
	/**
	 * ͬһѧ��,ͬһ�����Ƿ������ͬ��λ���Ƶĸ�λ��Ϣ
	 * @param model
	 * @return
	 */
	private boolean checkZjGwsqInfoExist(QgzxGwglForm model) {
		return qgzxGwglDAO.checkZjGwsqInfoExist(model);
	}
	/**
	 * ͬһ������Ч��λ�����ظ�
	 * @param model
	 * @return
	 */
	private boolean checkZjGwsqInfoExistSj(QgzxGwglForm model) {
		return qgzxGwglDAO.checkZjGwsqInfoExistSj(model);
	}
	
	
	
	
	/**
	 * �����޸ĸ�λ��Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean update(QgzxGwglForm myForm) throws Exception {
		boolean flag =  new QgzxGwglJgDao().runUpdate(myForm);
		if(flag){
			flag = qgzxGwglDAO.runUpdate(myForm);
		}
		if(Base.xxdm.equalsIgnoreCase("10351")) {//���ݴ�ѧ���Ի�
			if(flag){		
				myForm.setDoType("xg");
				return bcSqglXy(myForm);
			}
		}		
		return flag;
	}
	
	
	/**
	 * �����޸ĸ�λ����
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean bcXgGwsq(QgzxGwglForm myForm) throws Exception {
		if("submit".equals(myForm.getType())){
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
		myForm.setSplcid(splc);
		boolean result = qgzxGwglDAO.runUpdate(myForm);
		if("submit".equals(myForm.getType())){
			result = shlc.runSubmit( myForm.getPkValue(),myForm.getSplcid(),myForm.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
			if(!result){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if(Base.xxdm.equalsIgnoreCase("10351")) {//���ݴ�ѧ���Ի�
			myForm.setDoType("xg");
			if(result){				
				return bcSqglXy(myForm);
			}
		}
		return result;
	}
	
	/**
	 * @���������ͨ��ʱ��λ�޸� ���ݴ�ѧ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * String ��������
	 */
	public String bcXgGwsh(QgzxGwglForm myForm) throws Exception {
		return qgzxGwglDAO.bcXgGwsh(myForm)? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	/**
	 * ��λ��Ϣ����
	 * @param xn
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwxxFz(String xn, QgzxGwglForm myForm) throws SQLException {
		String yxssz = myForm.getYxssz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] input = {xn,yxssz,gwkssj,gwjssj,myForm.getXq(),pkValue[i]};
			params.add(input);
		}
		return qgzxGwglDAO.gwxxSave(params)? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	
	/**
	 * ��λ��Ϣɾ��
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String gwxxSc(QgzxGwglForm myForm) throws Exception {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < pkValue.length; i++){
			if(isUsed(pkValue[i])){
				return "��λ�ѱ�ʹ��,���ܱ�ɾ����";
			}
			params.add(new String[]{pkValue[i]});
			list.add(pkValue[i]);
		}
		if("10351".equals(Base.xxdm)){//���ݴ�ѧ���Ի�
			delXygl(list);//ɾ�������˸�λ����ѧԺ
		}
		return qgzxGwglDAO.gwxxSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	
	
	
	/**
	 * ��λ��Ϣɾ��
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public String gwsqSc(QgzxGwglForm myForm) throws Exception {
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < pkValue.length; i++){
			params.add(new String[]{pkValue[i]});
			list.add(pkValue[i]);
		}
		if("10351".equals(Base.xxdm)){//���ݴ�ѧ���Ի�
			delXygl(list);
		}
		return qgzxGwglDAO.gwsqSc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	
	
	
	/**
	 * �жϸ�λ�Ƿ��ѱ�ʹ��
	 * @param pkValue
	 * @return
	 */
	private boolean isUsed(String pkValue) {
		return qgzxGwglDAO.isUsed(pkValue);
	}
	
	
	/**
	 * ������˵�λList
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxGwglForm myForm) {
		return qgzxGwglDAO.getYrbm(myForm);
	}
	
	
	/**
	 * ������˵�λ����
	 * @param yrdwdm
	 * @return
	 */
	public String getYrdwmc(String yrdwdm){
		return qgzxGwglDAO.getYrdwmc(yrdwdm);
	}
	
	
	/**
	 * ���ɸ�λ���루ѧ��+4λ��ˮ�ţ�
	 * @param xn
	 * @return
	 */
	public String getGwdm(String xn,int start) {
		String max = qgzxGwglDAO.getMaxGwdm();
		if(max==null){
			return xn+"0001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+start+1));
			String pre = "";
			for(int i = 0; i < 4-max.length();i ++){
				pre+="0";
			}
			return xn+pre+max;
		}
	}
	/**
	 * ����ҳ���Ĭ�ϲ���
	 * @param request
	 * @return
	 */
	public HashMap<String, String> setZjmrCs(HttpServletRequest request) {
		HashMap<String,String> rs = new HashMap<String,String>();
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		//��������ڹ�����Ա
		User user = getUser(request);
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			rs.put("dis", "true");
		}
		DAO dao = DAO.getInstance();
		HashMap<String,String> cs = qgzxCsszService.getCssz();
		String sfkfgwsq = cs.get("sfkfgwsq");
		String gwsqkssj = cs.get("gwsqkssj");
		String gwsqjssj = cs.get("gwsqjssj");
		String sfkfxsgwsq = cs.get("sfkfxsgwsq");
		String xsgwsqkssj = cs.get("xsgwsqkssj");
		String xsgwsqjssj = cs.get("xsgwsqjssj");
		String dsfxy = cs.get("dsfxy");
		String kcxs = cs.get("kcxs");
		String dqsj = dao.getNowTime("yyyyMMdd");
		if("on".equalsIgnoreCase(sfkfgwsq) 
				&& (null==gwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(gwsqkssj))
				&& (null==gwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(gwsqjssj))){
			rs.put("kycz", "true");
		}
		if("on".equalsIgnoreCase(sfkfxsgwsq) 
				&& (null==xsgwsqkssj || Integer.valueOf(dqsj) >= Integer.valueOf(xsgwsqkssj))
				&& (null==xsgwsqjssj || Integer.valueOf(dqsj) <= Integer.valueOf(xsgwsqjssj))){
			rs.put("xssqkg", "true");
		}else{
			rs.put("xssqkg", "false");
		}
		String yrbm = user.getUserDep();
		rs.put("yrbm", yrbm);
		//�������Ÿ���Ĭ��ֵ
		rs.put("ssbm", yrbm);
		rs.put("ssbmmc", getYrdwmc(yrbm));
		rs.put("yrdwmc", getYrdwmc(yrbm));
		rs.put("qgzq", cs.get("qgzq"));
		rs.put("xn", StringUtils.isNull(cs.get("yrdwgwsqxn")) ? Base.currXn : cs.get("yrdwgwsqxn"));
		rs.put("xq", Base.currXq);
		rs.put("xqmc", Base.getXqmcForXqdm(Base.currXq));
		rs.put("dsfxy", dsfxy);
		rs.put("kcxs", kcxs);
		return rs;
	}
	
	
	/**
	 * ����ҳ��
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
				html.append("<td style=\"width:2.5%\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
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
	 * ����ҳ��
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTMLByGwsh(SearchRsModel rsModel,
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
				html.append(" value='" + replaceHtml(rs[0]) + "' "+replaceHtml(rs[1])+"/> ");
				html.append("</td>");
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 2; j < rs.length; j++) {
				
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					if(j == 5) {
						html.append("<a href='javascript:void(0);' class='name' onclick='showViewLink(&quot;"
								+ replaceHtml(rs[0])
								+ "&quot;);return false;'>"
								+ replaceHtml(rs[j]) + "</a>");
					} else {
						html.append(replaceHtml(rs[j]));
					}
					
					html.append("</td>");
				}
				
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	/**
	 * ����ҳ���ѯ���
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML2(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
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
				// --------------------����HTML��չ�ֶ����������------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
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
	 * ��֤������Ϣ
	 * @param model
	 * @return
	 */
	public String checkBcInfo(QgzxGwglForm model) {
		if(isExist(model)){
			return StringUtils.isNull(model.getXq()) ? "ͬһѧ��ͬһ���Ÿ�λ�����ظ�����ȷ�ϣ�" :"ͬһѧ��ͬһѧ��ͬһ���Ÿ�λ�����ظ�����ȷ�ϣ�";
		}
		if(isExistSj(model)){
			return "ͬһ������Ч��λ�����ظ�����ȷ�ϣ�";
		}
		return "true";
	}
	
	/**
	 * ��֤���Ӹ�λ������Ϣ
	 * @param model
	 * @return
	 */
	public String checkZjGwsqInfo(QgzxGwglForm model) {
		if(checkZjGwsqInfoExist(model)){
			return StringUtils.isNull(model.getXq()) ? "��ѧ�걾���Ÿ�λ�����ظ�����ȷ�ϣ�" : "��ѧ�걾ѧ�ڱ����Ÿ�λ�����ظ�����ȷ�ϣ�";
		}
		if(checkZjGwsqInfoExistSj(model)){
			return "��������Ч��λ�����ظ�����ȷ�ϣ�";
		}
		return "true";
	}
	
	
	/**
	 * ��֤ɾ����Ϣ
	 * @param model
	 * @return
	 */
	public String checkScInfo(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0; i < pkValue.length; i++){
			if(isUsed(pkValue[i])){
				return "��λ�ѱ�ʹ��,����ɾ����";
			}
		}
		return "true";
	}
	
	
	/**
	 * ��֤������Ϣ
	 * @param model
	 * @return
	 */
	public String checkFzInfo(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwmcs="";
		for(int i = 0; i < pkValue.length; i++){
			// ͨ��pkValue��ȡ��λ��Ϣ
			model.setPkValue(pkValue[i]);
			HashMap<String,String> map = getGwxxMap(model);
			model.setYrbm(map.get("yrdwdm"));
			String gwmc = map.get("gwmc");
			String yrdwdm = map.get("yrdwdm");
			if(gwmcs.indexOf(","+gwmc+yrdwdm+",")!=-1){
				return "��ѡ��λ�а�����ͬ���Ƶĸ�λ��Ϣ��";
			}
			gwmcs+=","+gwmc+yrdwdm+",";
			model.setGwmc(gwmc);
			// ��֤ǰ�����pkValue
			model.setPkValue(null);
			if(isExist(model)){
				return "ͬһѧ��ͬһ���Ÿ�λ�����ظ�����ȷ�ϣ�";
			}
			if(isExistSj(model)){
				return "ͬһ������Ч��λ�����ظ�����ȷ�ϣ�";
			}
		}
		return "true";
	}

	
	/**
	 * ���������λ��Ա��Ϣ
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String bcZjryxx(QgzxGwglForm model) throws SQLException {
		DAO dao = DAO.getInstance(); 
		String gwdm = model.getGwdm();
		String sgsj = model.getSgsj();
		String sfsgwsqsxz = model.getSfsgwsqsxz();
		String[] xh = model.getXh().split("!!@@!!");
		//����ķ�����������
		int fknsrs = Integer.parseInt(model.getFknsrs());
		int count = 0;
		List<String[]> params = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				if("1".equals(sfsgwsqsxz) && isOver(gwdm,xh[i])){
					return "ѧ��Ϊ"+xh[i]+"��ѧ���ѵ��ﵱǰѧ������λ���ޣ�";
				}
				//��֤����������
				KnsrdCondition knsrd = new KnsrdCondition();
				
				HashMap<String, String> knsTj=new HashMap<String, String>();
				if ("xq".equals(SQZQ)) {
					knsTj.put("xn", Base.currXn + ";" + Base.currXq);
				}else{
					knsTj.put("xn", Base.currXn);
				}
				String kns= knsrd.getKnsrdInfo(xh[i],knsTj);
				
				KnsjgService knsjgService=new KnsjgService();
				List<HashMap<String, String>> knsList = knsjgService.getKnsInfoList(xh[i]);
				//��֤�Ƿ�ǰ�ڹ���λ��������
				if(StringUtil.isNull(kns) && knsList != null){
					//�Ƿ���������
					boolean isKns=false;
					for (int j = 0; j < knsList.size(); j++) {
						if(model.getXn().equals(knsList.get(i).get("xn"))){
							isKns= true;
						}
					}
					if(!isKns){
						if ("xq".equals(SQZQ)) {
							return "ѧ��Ϊ"+xh[i]+"��ѧ���ǵ�ǰѧ��ѧ���µ�������";
						}else{
							return "ѧ��Ϊ"+xh[i]+"��ѧ���ǵ�ǰѧ���µ�������";
						}
					}
				}
				
				if(kns==null || "".equals(kns)){
					kns = "0";
				}
				if(Integer.parseInt(kns)<=0){
					count++;
				}
				String[] el=null;
				if(Globals.XXDM_ZJJTZYJSXY.equals(Base.xxdm)){
				 el = new String[]{gwdm,xh[i],sgsj};
				}else{
					el = new String[]{gwdm,xh[i]};
				}
				params.add(el);
			}
		}
		if(count>fknsrs){
			return "�ø�λ����������δ�ܵ����λҪ��";
		}
		return qgzxGwglDAO.bcZjryxx(params)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	public boolean saveRyzj(QgzxGwglForm model, List<QgzxGwglForm> gwryList) throws Exception {
		boolean result = true;
		List<String[]> ryList = new ArrayList<String[]>();
		String[] ryxx = null;
		String[] xh = model.getXhs().split("!!@@!!");
		
		for (int i = 0; i < gwryList.size(); i++) {
			
			ryxx = new String[3];
			ryxx[0] = gwryList.get(i).getSgsj();
			ryxx[1] = gwryList.get(i).getGwdm();
			ryxx[2] = xh[i];
			ryList.add(ryxx);
		}
		if (result) {
			//��������
			qgzxGwglDAO.gwryUpdate(ryList);
		}
		return result;

	}
	
	
	/**
	 * �Ƿ�ﵽ��λ����
	 * @param string
	 * @return
	 */
	private boolean isOver(String gwdm,String xh) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		String zdgws = qgzxCsszService.getCssz().get("xsgws");
		if("0".equalsIgnoreCase(zdgws)||null==zdgws){
			return false;
		}
		String gws = qgzxGwglDAO.getGws(gwdm,xh);
		return Integer.parseInt(zdgws)<=Integer.parseInt(gws);
		
	}


	/**
	 * ���������λ��Ա��Ϣ
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String scRyxx(QgzxGwglForm model) throws SQLException {
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				String[] el = new String[]{gwdm,xh[i]};
				params.add(el);
			}
		}
		return qgzxGwglDAO.scRyxx(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	


	/**
	 * ���������˸���Ա��Ϣ
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String bcTgryxx(QgzxGwglForm model) throws SQLException {
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		String[] sgsj = model.getSgsj().split("!!@@!!");
		String[] sqids = model.getSqbhs().split("!!@@!!");
		String tgyy = model.getTgyy();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramssq = new ArrayList<String[]>();
		if(xh.length!=0){
			for(int i = 0; i < xh.length; i++){
				String[] el = new String[]{tgyy,gwdm,xh[i],sgsj[i]};
				params.add(el);
				//��Ӧ�������ݻ�ԭ
				String[] sqid = new String[]{sqids[i]};
				paramssq.add(sqid);
			}
		}
		return qgzxGwglDAO.bcTgryxx(params)&&qgzxGwglDAO.hySqsj(paramssq)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * �����λ�����˸�
	 */
	public String bcTggwxx(String[] gwdms, String tgyy) throws SQLException {
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsSq = new ArrayList<String[]>();
		for(int i = 0; i < gwdms.length; i++){
			String[] el = new String[]{tgyy,gwdms[i]};
			params.add(el);
			//��Ӧ�������ݻ�ԭ
			String[] elSq = new String[]{gwdms[i]};
			paramsSq.add(elSq);
		}
		return qgzxGwglDAO.bcTggwxx(paramsSq, params)?MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	
	/**
	 * ��Ա��Ϣ�鿴
	 * @param myForm
	 * @param request
	 * @return
	 */
	public HashMap<String, String> ryxxCk(QgzxGwglForm model,
			HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		//��λ��Ϣ
		HashMap<String,String> rs = qgzxGwglDAO.getXsxx(model);
		rs.put("sfkns", dao.isKns(model.getXh())?"��":"��");
		//����ڸ���Ա������html
		model.setZgzt("zg");
		List<HashMap<String, String>> zggwList = getGwxx(model);
		rs.put("zggwHtml", createGwxxHtml(zggwList,model));
		return rs;
	}


	/**
	 * ��ø�λ��Ϣ����ѧ��
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> getGwxx(QgzxGwglForm model) {
		return qgzxGwglDAO.getGwxx(model);
	}

	
	/**
	 * ������λ��Ϣҳ��
	 * @param gwxxList
	 * @param model
	 * @return
	 */
	private String createGwxxHtml(List<HashMap<String, String>> rsArrList,
			QgzxGwglForm model) {
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String,String> rs = rsArrList.get(i);
				html.append("<tr><td>"+rs.get("r")+"</td>");
				html.append("<td>"+rs.get("xn")+"</td>");
				html.append("<td>"+rs.get("bmmc")+"</td>");
				html.append("<td>"+rs.get("gwmc")+"</td>");
				html.append("<td>"+rs.get("gwxzmc")+"</td>");
				html.append("<td>"+rs.get("sgsj")+"</td>");
				html.append("</tr>");
			}
		}
		return html.toString();
	}


	/**
	 * ��֤ɾ����Ա��Ϣ
	 * @param myForm
	 * @return
	 */
	public String checkScRyxx(QgzxGwglForm model) {
		String message = "true";
		String gwdm = model.getGwdm();
		String[] xh = model.getXh().split("!!@@!!");
		for(int i = 0; i < xh.length; i++){
			if(isUsed(gwdm,xh[i])){
				return "ѧ��Ϊ"+xh[i]+"��ѧ���ѷ��Ź������ɾ����";
			}
		}
		return message;
	}


	/**
	 * �ڴ˸�λ���Ƿ񷢷Ź����
	 * @param gwdm
	 * @param string
	 * @return
	 */
	private boolean isUsed(String gwdm, String xh) {
		return qgzxGwglDAO.isUsed(gwdm,xh);
	}


	/**
	 * �������Ӹ�λ����
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean bcZjGwsq(QgzxGwglForm model) throws Exception {
		String gwdm = UniqID.getInstance().getUniqIDHash();
		model.setGwdm(gwdm);
		String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
		model.setSplcid(splc);
		if("submit".equals(model.getType())){
			model.setShzt(Constants.YW_SHZ);
		}else{
			model.setShzt(Constants.YW_WTJ);
		}
		boolean result = qgzxGwglDAO.runInsert(model);
		if("submit".equals(model.getType())){
			result = shlc.runSubmit(model.getGwdm(),model.getSplcid(),model.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
			if(!result){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if(Base.xxdm.equalsIgnoreCase("10351") && result) {//���ݴ�ѧ���Ի�
			model.setDoType("zj");
			return bcSqglXy(model);
		}
		return result;
	}

	
	/**
	 * 
	 */
	public boolean insertGwxxForZjlyzy(String[] zjs,String type,User user) throws Exception {
		List<QgzxGwglForm> gwsqrList = new ArrayList<QgzxGwglForm>();
		List<QgzxGwglForm> gwsqList = new ArrayList<QgzxGwglForm>();
		if(null != zjs && zjs.length>0){//�������������	
			for(int i = 0;i<zjs.length;i++){
				QgzxGwglForm model = new QgzxGwglForm();
				model.setUser(user);
				model.setType(type);
				//��ȡϵͳ��ǰʱ��
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String sqsj = df.format(System.currentTimeMillis());
				model.setSqsj(sqsj);
				String[] zj = zjs[i].split(",");
				if(zj.length <3){
					for(int j = 0;j<zj.length;j++){
						if(j==0){						
							model.setLxr(zj[j]);
						}else if(j==1){
							model.setLxphone(zj[j]);
						}
						gwsqrList.add(model);
					}
				} else{
					for(int j = 0;j<zj.length;j++){
						//���ɸ�λ���룬Ψһ��ʶ
						String gwdm = UniqID.getInstance().getUniqIDHash();
						model.setGwdm(gwdm);
						String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
						model.setSplcid(splc);
						if("submit".equals(model.getType())){
							model.setShzt(Constants.YW_SHZ);
						}else{
							model.setShzt(Constants.YW_WTJ);
						}
						if(j==0){						
							model.setGwmc(zj[j]);
						}else if(j==1){
							model.setXqrs(zj[j]);
						}else if(j==2){
							model.setGwlx(zj[j]);
						}else if(j==3){
							model.setKnsrs(zj[j]);
						}else if(j==4){
							model.setGwshr(zj[j]);
						}else if(j==5){
							model.setGwshrxm(zj[j]);
						}else if(j==6) {
							model.setGwryyq(zj[j]);
						}else if(j==7) {
							model.setGznr(zj[j]);
						}
					}
					gwsqList.add(model);
				}
			}
		}
		boolean result = true;
		if((null != zjs && zjs.length>0)){
			result = qgzxGwglDAO.insertGwsqForZjlyzy(gwsqrList,gwsqList,user);
			//����λ��Ϣ��ӵ����״̬��
			for(QgzxGwglForm model :gwsqList){
				if("submit".equals(model.getType())){
					result = shlc.runSubmit(model.getGwdm(),model.getSplcid(),model.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
					if(!result){
						throw new SystemException(MessageKey.SYS_SAVE_FAIL);
					}
				}
			}
			gwsqList.clear();
			gwsqrList.clear();
		}
		return result;
	}
	
	/**
	 * @throws Exception  
	 * @����:��������ѧԺ���ݷ�Χ(���ݴ�ѧר��)(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-14 ����02:14:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public boolean bcSqglXy(QgzxGwglForm model) throws Exception {
		boolean result = true;
			
		if("xg".equalsIgnoreCase(model.getDoType())){			
			result = qgzxGwglDAO.delSqglXy(model.getGwdm());//ɾ��
		}
		if(result){
			//if(null != model.getSqxy() && !model.getSqxy()[0].equalsIgnoreCase("")){//�����������ѧԺ��Χ				
				result = qgzxGwglDAO.plInsertGlXy(model);//�����������ѧԺ��Χ
			//}
		}		
		return result;
	}
	
	
	/**
	 * ��λ��Ϣ��˱���
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String gwxxshBc(QgzxGwglForm model) throws Exception {
		boolean flag = false;
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwxh ="";
		if("10052".equals(Base.xxdm)){
			gwxh = qgzxGwglDAO.GetGwxh(model.getYrbm(),model.getXn());
		}
		String shzt = model.getShzt();
		String shyj = model.getShyj();
		String shr = model.getUser().getUserName();
		String gwcjbz = model.getGwcjbz();
		String jfhb= model.getJfhb();
		String zc = model.getZc();
		List<String[]> params = new ArrayList<String[]>();
		List<String[]> paramsByZs = new ArrayList<String[]>();
		if(pkValue.length == 0){
			return MessageInfo.MESSAGE_SH_FALSE;
		}
		if("1".equalsIgnoreCase(shzt)){ // ���ͨ��
			int okNum = 0;
			StringBuilder notOk = new StringBuilder(); // �ظ���λ
			for(int i = 0; i < pkValue.length; i++){
				QgzxGwglForm formTemp = new QgzxGwglForm();
				formTemp.setPkValue(pkValue[i]);
				HashMap<String, String> formMap = getGwsqMap(formTemp);
				formTemp.setXn(formMap.get("xn"));
				formTemp.setYrbm(formMap.get("yrdwdm"));
				formTemp.setGwmc(formMap.get("gwmc"));
				formTemp.setGwjssj(formMap.get("gwjssj"));
				formTemp.setGwkssj(formMap.get("gwkssj"));
				formTemp.setXq(formMap.get("xq"));
				if(isExist(formTemp) || isExistSj(formTemp)){ // �ظ���λ
					notOk.append(formMap.get("gwmc") + "��");
				}else{
					String[] el ={};
					if("12867".equalsIgnoreCase(Base.xxdm)) {
						el = new String[]{shyj,shzt,shr,pkValue[i]};
					}else {
						el = new String[]{shyj,shzt,shr,gwcjbz,jfhb,zc,pkValue[i]};
					}
					String[] el2 = new String[]{gwxh,pkValue[i]};
					params.add(el);
					paramsByZs.add(el2);
					okNum++;
				}
			}
			flag = qgzxGwglDAO.gwxxshBc(params);
			if(flag){
				flag = qgzxGwglDAO.insertByZs(paramsByZs);
			}
			String resultMsg = "��˳ɹ�"+okNum+"����";
			if(pkValue.length - okNum > 0){
				resultMsg += "�ظ���λ��" + notOk.toString().substring(0, notOk.toString().length() - 1);
			}
			return flag?resultMsg : MessageInfo.MESSAGE_SH_FALSE;
		}else{
			for(int i = 0; i < pkValue.length; i++){
				String[] el = new String[]{shyj,shzt,shr,gwcjbz,jfhb,zc,pkValue[i]};
				params.add(el);
			}
			flag = qgzxGwglDAO.gwxxshBc(params);
			if(flag){ // ɾ����λ
				List<String[]> paramsSc = new ArrayList<String[]>();
				paramsSc.add(pkValue);
				flag = qgzxGwglDAO.gwxxSc(paramsSc);
			}
			return flag?MessageInfo.MESSAGE_SH_SUCCESS : MessageInfo.MESSAGE_SH_FALSE;
		}
	}
	//���˵�λ���
	public List<HashMap<String, String>> getYddwLbList() throws Exception{
		return qgzxGwglDAO.getYddwLbList();
	}
	
	//�ʽ���Դ
	public List<HashMap<String, String>> getZjlyList() throws Exception{
		return qgzxGwglDAO.getZjlyList();
	}
	
	/**
	 * ��λ��Ϣ����
	 * @param xn
	 * @param myForm
	 * @return
	 * @throws SQLException 
	 */
	public String gwsqFz(String xn, QgzxGwglForm myForm,String xm,String xq) throws SQLException {
		String yxssz = myForm.getYxssz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String[] pkValue = myForm.getPkValue().split("!!@@!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] input = {xn,yxssz,gwkssj,gwjssj,xm,xq,pkValue[i]};
			params.add(input);
		}
		return qgzxGwglDAO.gwsqSave(params)? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	
	/**
	 * ��֤������Ϣ
	 * @param model
	 * @return
	 */
	public String checkFzInfoSq(QgzxGwglForm model) {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		String gwmcs="";
		for(int i = 0; i < pkValue.length; i++){
			// ͨ��pkValue��ȡ��λ��Ϣ
			model.setPkValue(pkValue[i]);
			HashMap<String,String> map = getGwsqMap(model);
			model.setYrbm(map.get("yrdwdm"));
			String gwmc = map.get("gwmc");
			String yrdwdm = map.get("yrdwdm");
			if(gwmcs.indexOf(","+gwmc+yrdwdm+",")!=-1){
				return "��ѡ��λ�а�����ͬ���Ƶĸ�λ��Ϣ��";
			}
			gwmcs+=","+gwmc+yrdwdm+",";
			model.setGwmc(gwmc);
			// ��֤ǰ�����pkValue
			model.setPkValue(null);
			if(isExistSq(model)){
				return "ͬһѧ��ͬһ���Ÿ�λ�����ظ�����ȷ�ϣ�";
			}
			if(isExistSjSq(model)){
				return "ͬһ������Ч��λ�����ظ�����ȷ�ϣ�";
			}
		}
		return "true";
	}
	
	/**
	 * ͬһѧ��,ͬһ�����Ƿ������ͬ��λ���Ƶĸ�λ��Ϣ
	 * @param model
	 * @return
	 */
	private boolean isExistSq(QgzxGwglForm model) {
		return qgzxGwglDAO.isExistSq(model);
	}
	
	/**
	 * ͬһ������Ч��λ�����ظ�
	 * @param model
	 * @return
	 */
	private boolean isExistSjSq(QgzxGwglForm model) {
		return qgzxGwglDAO.isExistSjSq(model);
	}
	
	/** 
	 * @����:��ȡ����ѧԺlist�����ݴ�ѧר�ã�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-14 ����04:01:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * void �������� 
	 * @throws 
	 */
	public void setGlxyList(QgzxGwglForm model,String gwdm) {
		List<HashMap<String,String>> list = qgzxGwglDAO.getGlxydm(gwdm);
		if(null != list && list.size()>0){
			String[] strs = new String[list.size()];
			for(int i = 0;i<list.size();i++){
				strs[i] = list.get(i).get("xydm");
			}
			model.setSqxy(strs);
		}
	}
	
	/** 
	 * @����:�Ƿ�ΪԺϵ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-17 ����05:17:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isYxBm(String bmdm){
		return qgzxGwglDAO.isYxbm(bmdm);
	}
	
	/**
	 * @throws Exception  
	 * @����:ɾ��������ѧԺ��Χ���������ݴ�ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-17 ����05:18:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delXygl(List<String> list) throws Exception{
		return qgzxGwglDAO.delXygl(list);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-6-2 ����10:10:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(QgzxGwglForm model,
			User user) throws Exception {
		List<HashMap<String, String>> fdylist=qgzxGwglDAO.getFdyPageList(model);
			return fdylist;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-7 ����03:24:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(QgzxGwglForm model, User user)  throws Exception {
		String splc = new QgzxCsszService().getCssz().get("yrdwsplc");
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplcid();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splc);
		boolean flag = new QgzxGwglDAO().runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getGwdm(), splc, model.getSqr(), "qgzx_gwglnew_gwsh.do", "qgzx_gwglnew_gwsq.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	
	/**
	 * 
	 *������˱���
	 */
	@TransactionControl
	public boolean gwxxshBcNew(QgzxGwglForm form, User user) throws Exception{
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getGwdm());
		model.setSqrid(form.getSqr());
		model.setTzlj("qgzx_gwglnew_gwsh.do");
		model.setTzljsq("qgzx_gwglnew_gwsq.do");
		boolean reuslt = false;
		//��ֹ�������ʱ�����޸ı��ֶε������������������������ֶ�����������ݲ�һ��
		new QgzxGwglDAO().runUpdate(form);
		QgzxGwglForm allSqxxForm = new QgzxGwglDAO().getModel(form.getGwdm());
		if(isExistSj(allSqxxForm)){ // �ظ���λ
			throw new SystemException();
		}
		String zhzt = shlc.runAuditingNotCommit(model);
		form.setShzt(zhzt);
		reuslt = new QgzxGwglDAO().runUpdateNotCommit(form, form.getGwdm());
		// ���浽�����
		if (zhzt.equalsIgnoreCase(Constants.YW_TG) && reuslt) {
			new QgzxGwglDAO().deleteJgbData(allSqxxForm);
			QgzxGwglForm jgForm = new QgzxGwglForm();
			BeanUtils.copyProperties(jgForm, StringUtils.formatData(allSqxxForm));
			jgForm.setSjly("1");
			String gwxh = "";
			if("10052".equals(Base.xxdm)){
				gwxh = qgzxGwglDAO.GetGwxh(jgForm.getYrdwdm(),jgForm.getXn());
			}
			jgForm.setGwxh(gwxh);
			reuslt = new QgzxGwglJgDao().runInsertNotCommit(jgForm);
		}
		return reuslt;
	}
	
	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-8 ����05:34:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(QgzxGwglForm t, User user) throws Exception {
		//XyzsSqDao zssqDao = new XyzsSqDao();
		QgzxGwglForm model = new QgzxGwglForm();
		String[] sqids = t.getId();
		String[] gwid = t.getGwids();
		String[] sqrs = t.getSqrs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�
	
		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplcid(splcids[i]);
			// model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setGwdm(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSqr(sqrs[i]);
			boolean isSuccess = gwxxshBcNew(model, user);
			if (!isSuccess) {
				failZghs.add(sqrs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failZghs);
		if (failZghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����11:03:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(QgzxGwglForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = new QgzxGwglDAO().runUpdate(myForm, myForm.getGwdm());
		if (result) {
			QgzxGwglJgDao jgService = new QgzxGwglJgDao();
		
			// ɾ��������е�������
			jgService.runDelete(new String[]{myForm.getGwdm()});
		
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-19 ����11:03:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cxshnew(String ywid, QgzxGwglForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		model.setShzt(shzt);
		new QgzxGwglDAO().runUpdate(model, ywid);
		return cancelFlag;
	}
	
	public List<HashMap<String,String>> getGwPageList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getPageList(model,user);
	}
	public List<HashMap<String,String>> getExportList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getExportList(model,user);
	}

	public List<HashMap<String,String>> getGwfbshList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getGwfbshList(model,user);
	}

	public List<HashMap<String,String>> getJgPageList(QgzxGwglForm model, User user) throws Exception {
		return qgzxGwglDAO.getJgPageList(model,user);
	}
	public List<HashMap<String,String>> getYrdwList(QgzxGwglForm t,User user) throws Exception {
		return qgzxGwglDAO.getYrdwList(t,user);
	}

	public List<HashMap<String,String>> getGwList(){
		return qgzxGwglDAO.getGwlbList();
	}
	public HashMap<String,String> getGwlbById(String id){
		return qgzxGwglDAO.getGwlbById(id);
	}
	public HashMap<String,String> getGwxxByGwdm(String gwdm){
		return qgzxGwglDAO.getGwxxByGwdm(gwdm);
	}

	public boolean saveGw(QgzxGwglForm t,User user) throws Exception {
		boolean flag = false;
		if("submit".equals(t.getType())){
			t.setShzt(Constants.YW_SHZ);
		} else {
			t.setShzt(Constants.YW_WTJ);
		}
		String dwlb = qgzxGwglDAO.getDwlb(user.getUserName());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//У�ڵ�λ
			t.setSplc(splcMap.get("yrdwsplc"));
		} else {//У�ⵥλ
			t.setSplc(splcMap.get("xwgwsplc"));
		}
		t.setFbsj(DateUtils.getCurrTime());
		t.setGwdm(UniqID.getInstance().getUniqIDHash());
		t.setXn(Base.currXn);
		t.setXq(Base.currXq);
		flag = qgzxGwglDAO.insertGwxx(t);
		if(flag && "submit".equals(t.getType())){
			ShlcInterface shlc = new CommShlcImpl();
			flag = shlc.runSubmit(t.getGwdm(), t.getSplc(),user.getUserName(),"qgzx_gwglnew_gwfbsh.do","qgzx_gwglnew_gwfb.do");
		}
		return flag;
	}

	public boolean updateGw(QgzxGwglForm t,User user) throws Exception {
		boolean flag = false;
		if("submit".equals(t.getType())){
			t.setShzt(Constants.YW_SHZ);
		} else {
			t.setShzt(Constants.YW_WTJ);
		}
		String dwlb = qgzxGwglDAO.getDwlb(user.getUserName());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//У�ڵ�λ
			t.setSplc(splcMap.get("yrdwsplc"));
		} else {//У�ⵥλ
			t.setSplc(splcMap.get("xwgwsplc"));
		}
		t.setFbsj(DateUtils.getCurrTime());
		flag = qgzxGwglDAO.updateGwxx(t);
		if(flag && "submit".equals(t.getType())){
			flag = shlc.runSubmit(t.getGwdm(), t.getSplc(),user.getUserName(),"qgzx_gwglnew_gwfbsh.do","qgzx_gwglnew_gwfb.do");
		}
		return flag;
	}

	public boolean submitGwfb(QgzxGwglForm model,User user) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			String dwlb = qgzxGwglDAO.getDwlb(user.getUserName());
			HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
			if("01".equals(dwlb)){//У�ڵ�λ
				model.setSplc(splcMap.get("yrdwsplc"));
			} else {//У�ⵥλ
				model.setSplc(splcMap.get("xwgwsplc"));
			}
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = qgzxGwglDAO.updateGwfbSq(model);
		boolean result = false;
		if(flag){
			result = shlc.runSubmit(model.getGwdm(), model.getSplc(),user.getUserName(),"qgzx_gwglnew_gwfbsh.do","qgzx_gwglnew_gwfb.do");
		}
		return result;
	}

	public boolean cancelGwfb(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid,lcid);
	}
	public boolean updateGwfb(QgzxGwglForm model) throws Exception {
		return qgzxGwglDAO.updateGwfbSq(model);
	}

	public HashMap<String, String> getYrdwByUser(String yhm){
		return qgzxGwglDAO.getYrdwByUser(yhm);
	}

	public boolean saveSh(QgzxGwglForm model,User user) throws Exception{

		ShxxModel shxxModel = new ShxxModel();
		// �������ID
		shxxModel.setShlc(model.getSplc());
		// �����
		shxxModel.setShr(user.getUserName());
		// ������
		shxxModel.setShyj(model.getShyj());
		// ���״̬
		shxxModel.setShzt(model.getShjg());
//        shxxModel.setThgw(model.getThgw());
		// ��˸�λID
		shxxModel.setGwid(model.getGwid());
		// ҵ��ID(��Ϊ����ID)
		shxxModel.setYwid(model.getGwdm());
		shxxModel.setSqrid(model.getYrdwid());
		shxxModel.setTzlj("qgzx_gwglnew_gwfbsh.do");
		shxxModel.setTzljsq("qgzx_gwglnew_gwfb.do");

		boolean result = false;
		String zhzt = shlc.runAuditing(shxxModel);
		QgzxGwglForm form = new QgzxGwglForm();
		form.setGwdm(model.getGwdm());
		form.setShzt(zhzt);
		form.setSfzd(model.getSfzd());
		form.setSfxsgz(model.getSfxsgz());
		result = qgzxGwglDAO.updateGwfbSqxx(form);
		return result;
	}
	public String plshBc(QgzxGwglForm t, User user) throws Exception {

		QgzxGwglForm model = new QgzxGwglForm();
		String[] sqids = t.getId();
		String[] gwid = t.getGwids();
		String[] sqrs = t.getSqrs();
		String[] splcids = t.getSplcids();
		List<String> failZghs = new ArrayList<String>();
		//Ҫ��Ҫ����֤�д��о�

		for (int i = 0, n = sqids.length; i < n; i++) {
			model.setSplc(splcids[i]);
			model.setGwid(gwid[i]);
			model.setGwdm(sqids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setYrdwid(sqrs[i]);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failZghs.add(sqrs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failZghs);
		if (failZghs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	public boolean cancelSh(QgzxGwglForm model) throws Exception {
		model.setShzt(Constants.YW_SHZ);
		return qgzxGwglDAO.updateGwfbSqxx(model);
	}

	public String yz(User user){

		if(qgzxGwglDAO.isHmdYh(user)){
			return "���ѱ�����Ա��������������ܷ�����λ��";
		}

		if(!qgzxGwglDAO.haveDw(user)){
			return "δ��ѯ�����ĵ�λ��Ϣ����ȷ�ϣ�";
		}
		return "success";
	}

	public boolean gwSc(String gwdms, User user) throws Exception {

		return qgzxGwglDAO.deleteGw(gwdms.split(","));
	}

	public boolean checkIsUsed(String[] gwdms){
		return qgzxGwglDAO.checkIsUsed(gwdms);
	}

	public boolean insertJg(QgzxGwglForm t, User user) throws Exception {
		t.setFbsj(DateUtils.getCurrTime());
		t.setGwdm(UniqID.getInstance().getUniqIDHash());
		t.setXn(Base.currXn);
		t.setXq(Base.currXq);
		t.setShzt("1");
		t.setSjly("1");
		return qgzxGwglDAO.insertJg(t);
	}

	public boolean updateJg(QgzxGwglForm t, User user) throws Exception {
		t.setFbsj(DateUtils.getCurrTime());
		return qgzxGwglDAO.updatejg(t);
	}

	public boolean checkGwmc(String gwmc) throws Exception{
		return qgzxGwglDAO.checkGwmc(gwmc);
	}
}
