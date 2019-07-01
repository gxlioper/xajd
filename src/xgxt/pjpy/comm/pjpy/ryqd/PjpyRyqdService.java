package xgxt.pjpy.comm.pjpy.ryqd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.Fdypd;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class PjpyRyqdService  extends PjpyCommService{

	/**
	 * ���������꼶��ѧԺ��רҵ���༶
	 * @param request
	 * @return
	 */
	public void setPjnjxyzybj(
			HttpServletRequest request) {
		PjpyRyqdDAO dao=new PjpyRyqdDAO();
		
		//����ʹ��ͨ�õĲ�ѯ���,��������Ҫ���⴦��
		String xy="";
		String zy="";
		String bj="";
		String nj="";
		xy = StringUtils.isNull(xy) ? request
				.getParameter("queryequals_pjxydm") : xy;
		zy = StringUtils.isNull(zy) ? request
				.getParameter("queryequals_pjzydm") : zy;
		bj = StringUtils.isNull(bj) ? request
				.getParameter("queryequals_pjbjdm") : bj;
		nj = StringUtils.isNull(nj) ? request
				.getParameter("queryequals_pjnj") : nj;
		// ��ȡ�����༶�б�
		request.setAttribute("pjbjList", dao.getPjbjList(xy,zy,nj));
		// ��ȡ�����꼶�б�
		request.setAttribute("pjnjList", dao.getPjnjList());
		// ��ȡ����ѧԺ�б�
		request.setAttribute("pjxyList", dao.getPjxyList());
		// ��ȡ����רҵ�б�
		request.setAttribute("pjzyList", dao.getPjzyList(xy,nj));
		
	}
	
	/**
	 * ͬ��������Ϣ��ѧԺ��רҵ���༶��
	 * @return boolean
	 */
	public boolean tbbmInfo(){
		String sql="{call pro_xg_pjpy_bmtb()}";
		return runPro(sql);
	}
	
	/**
	 * ͬ��ѧ����Ϣ��ѧԺ��רҵ���༶��
	 * @return boolean
	 */
	public boolean tbxsInfo(){
		String sql="{call pro_xg_pjpy_xsxxtb()}";
		return runPro(sql);
	}
	
	
	public void savePjbm(HttpServletRequest request,PjpyRyqdForm myForm) throws Exception{
		
		GhxyNjzrwhService ghxyNjzrwhService=new GhxyNjzrwhService();
		pjpyRyqdModel model=new pjpyRyqdModel();
		PjpyRyqdDAO dao=new PjpyRyqdDAO();
		
		HashMap<String,String>njxyzyMap=dao.getNjXyZy(myForm);
		
		//�������ݲ��� �ı���
		String realTable = "xg_pjpy_xsb";
		
		String[] xh = myForm.getSave_xh();
		String[] xm= myForm.getSave_xm();
		String[] cbv = myForm.getPrimarykey_cbv();
		String save_xydm=njxyzyMap.get("xydm");
		String save_bjdm=myForm.getSave_bjdm();
		String save_zydm=njxyzyMap.get("zydm");
		String save_nj=njxyzyMap.get("nj");
		String[] pkValue = new String[cbv.length];
		// �жϲ����Ƿ�ɹ�
		boolean reslut = false;
		
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		//��������
		String pk ="xh||pjxn||pjxq||pjnd";
		
		String[] onezd = new String[] { "pjxn", "pjxq","pjnd" };
		String[] arrzd = new String[] { "xh", "xm","xydm","zydm", "bjdm", "nj","sfysz" };
		for (int i = 0; i < cbv.length; i++) {
			pkValue[i]=cbv[i];
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setOnezd(onezd);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		if (cbv != null) {
			String[] xhArr = new String[cbv.length];
			String[] xmArr = new String[cbv.length];
			String[] xydmArr = new String[cbv.length];
			String[] zydmArr = new String[cbv.length];
			String[] bjdmArr = new String[cbv.length];
			String[] njArr = new String[cbv.length];
			String[] sfyszArr = new String[cbv.length];
			int m=0;
			for (int i = 0; i < cbv.length; i++) {
				for(int j=0;j<xh.length;j++){
					if(cbv[i].equalsIgnoreCase(xh[j]+pjxn+pjxq+pjnd)){
						xhArr[m]=xh[j];
						xmArr[m]=xm[j];
						xydmArr[m]=save_xydm;
						zydmArr[m]=save_zydm;
						bjdmArr[m]=save_bjdm;
						njArr[m]=save_nj;
						sfyszArr[m]="��";
						m++;
						break;
					}
				}
			}
			
			model.setPjxn(pjxn);
			model.setPjxq(pjxq);
			model.setPjnd(pjnd);
			model.setXh(xhArr);
			model.setXm(xmArr);
			model.setNj(njArr);
			model.setXydm(xydmArr);
			model.setZydm(zydmArr);
			model.setBjdm(bjdmArr);
			model.setSfysz(sfyszArr);
		}
		reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}
	
	/**
	 * ����������Ա����
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean plRysz(PjpyRyqdForm myForm) throws Exception{
		PjpyRyqdDAO dao=new PjpyRyqdDAO();
		return dao.plRysz(myForm);
	}
	
	
	/**
	 * ִ�д洢����
	 * @param String sql
	 * @return boolean
	 */
	public boolean runPro(String sql){
		DAO dao=DAO.getInstance();
		try {
			return dao.runProcuder(sql, new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ��ȡ�꼶��רҵ���༶�б�
	 * 
	 * @param request
	 */
	public static void setPjNjXyZyBjListForFdyBzr(HttpServletRequest request) {
		
		PjpyRyqdDAO dao=new PjpyRyqdDAO();
		// ��request�����꼶ѧԺרҵ�༶List�ķ���
		HttpSession session = request.getSession();

		String fdyQx =  session.getAttribute("fdyQx").toString();
		String bzrQx =  session.getAttribute("bzrQx").toString();
		String userType =  session.getAttribute("userType").toString();
		String userDep =  session.getAttribute("userDep").toString();
		String userName =  session.getAttribute("userName").toString();

		if (Boolean.parseBoolean(fdyQx) && Boolean.parseBoolean(bzrQx)) {
			request.setAttribute("bjList", Fdypd.getFdyBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyBzrZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyBzrXyList(userName));
		} else if (Boolean.parseBoolean(fdyQx)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		} else if (Boolean.parseBoolean(bzrQx)) {
			request.setAttribute("bjList", Fdypd.getBzrbjList(userName));
			request.setAttribute("zyList", Fdypd.getBzrZyList(userName));
			request.setAttribute("xyList", Fdypd.getBzrXyList(userName));
		} else {

			String xy = "";
			String zy = "";
			String bj = "";
			String nj = "";
			if ("xy".equalsIgnoreCase(userType)) {
				xy = userDep;
			}

			// ����ʹ��ͨ�õĲ�ѯ���,��������Ҫ���⴦��
			xy = StringUtils.isNull(xy) ? request
					.getParameter("queryequals_pjxydm") : xy;
			zy = StringUtils.isNull(zy) ? request
					.getParameter("queryequals_pjzydm") : zy;
			bj = StringUtils.isNull(bj) ? request
					.getParameter("queryequals_pjbjdm") : bj;
			nj = StringUtils.isNull(nj) ? request
					.getParameter("queryequals_pjnj") : nj;

			xy = (xy == null) ? "" : xy;
			zy = (zy == null) ? "" : zy;
			bj = (bj == null) ? "" : bj;
			nj = (nj == null) ? "" : nj;
			String bjKey = xy + "!!" + zy + "!!" + nj;
			request.setAttribute("pjxyList", dao.getPjxyList());
			request.setAttribute("pjzyList", dao.getPjzyList(xy,nj));
			request.setAttribute("pjbjList", dao.getPjbjList(xy,zy,nj));
			
		}
		request.setAttribute("pjnjList", dao.getPjnjList());
	}
	
	/**
	 * �����Աȷ��
	 * 
	 * @author lyl
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getRyqdList(PjpyRyqdForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xg_pjpy_pjry";
		String[] queryList = new String[] { "pjxn", "pjxq", "pjnd","nj","xydm","zydm","bjdm","pjxydm","pjzydm","pjbjdm","sfysz","sftz" };
		String[] queryLikeList = new String[] {"xh","xm"};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		String[] inputValue = myQuery.getInputList();
		String [] colList={"bgcolor","pkValue","xh","xm","pjnj","pjxymc","pjzymc","pjbjmc","sfysz"};
		return getRsArrList(tableName, query, inputValue, colList, "", model);
	}
	
}
