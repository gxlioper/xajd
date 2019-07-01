package xgxt.gygl.gywh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.init.InitGygl;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.gygl.cwgl.GyglCwglDAO;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_��Ԣά��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class GyglGywhService extends GyglCommService {

	GyglGywhDAO dao = new GyglGywhDAO();
	
	public List<HashMap<String,String>>qsxxList;
	
	/**
	 * ��ȡ����������Ϣ
	 * 
	 * @return List<HashMap<String,String>>
	 * @throws Exception
	 */
	public List<String[]> getJcsjList(GyglGywhForm myForm, User user,
			HttpServletRequest request) throws Exception {

		List<String[]> jcsjList = new ArrayList<String[]>();

		String mklx = myForm.getMklx();

		getJcColList(myForm);

		// У��
		if ("xq".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=xq";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getXqsjList(myForm, request);
			// ԰��
		} else if ("yq".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=yq";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getYqsjList(myForm, request);
			// ¥��
		} else if ("ld".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=ld";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getLdsjList(myForm, user, request);
			// ����
		} else if ("qs".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=qs";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getQssjList(myForm, user, request);
			// ��λ
		} else if ("cw".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_jcsj.do&searchType=cw";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getCwsjList(myForm, request);
		} else if ("ldwh".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_ldwh.do";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getLdsjList(myForm, user, request);
		} else if ("qswh".equalsIgnoreCase(mklx)) {
			String path = "gygl_gywh_qswh.do";
			myForm.getSearchModel().setPath(path);
			jcsjList = dao.getQssjList(myForm, user, request);
		}
		return jcsjList;
	}
	
	/**
	 * ��ȡҵ��������Ϣ
	 * @return List<HashMap<String,String>>
	 * @throws Exception 
	 */
	public List<String[]>getYwsjList(GyglGywhForm myForm,HttpServletRequest request) throws Exception{
		
		List<String[]>ywsjList=new ArrayList<String[]>();
		
		String mklx=myForm.getMklx();
		
		getYwColList(myForm);
		//���ҷ���
		if("qsfp".equalsIgnoreCase(mklx)){
			String path = "gygl_gywh_ywsj.do&searchType=qsfp";
			myForm.getSearchModel().setPath(path);
			ywsjList=dao.getQsfpsjList(myForm,request);
		//��λ����
		}else if("cwfp".equalsIgnoreCase(mklx)){
			String path = "gygl_gywh_ywsj.do&searchType=cwfp";
			myForm.getSearchModel().setPath(path);
			ywsjList=dao.getCwfpsjList(myForm,request);
		//��ʷ��Ϣ	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			String path = "gygl_gywh_ywsj.do&searchType=lsxx";
			myForm.getSearchModel().setPath(path);
			ywsjList=dao.getLsxxsjList(myForm,request);
		//����	
		}
		return ywsjList;
	}
	
	/**
	 * ��ȡ������������ֶ�
	 * @return String[]
	 * @throws Exception 
	 */
	public void getJcColList(GyglGywhForm myForm){
		
		String[] colList=null;
		
		String mklx=myForm.getMklx();
		String czxq=myForm.getCzxq();
		String czyq=myForm.getCzyq();
		//У��
		if("xq".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","dm","xqmc","sjly"};
		//԰��
		}else if("yq".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","yqdm","yqmc","xqmc","sjly"};
		//¥��	
		}else if("ld".equalsIgnoreCase(mklx) || "ldwh".equalsIgnoreCase(mklx) ){
			
			//ͬʱ����У����԰��
			if("��".equalsIgnoreCase(czxq) && "��".equalsIgnoreCase(czyq)){
				colList=new String[]{"pkValue","disabled","xqmc","yqmc","lddm","ldmc","xbxd","cs","zrs"};
			//����У��,������԰��
			}else if("��".equalsIgnoreCase(czxq) && "��".equalsIgnoreCase(czyq)){
				colList=new String[]{"pkValue","disabled","xqmc","lddm","ldmc","xbxd","cs","zrs"};
			//����԰��,������У��
			}else if("��".equalsIgnoreCase(czxq) && "��".equalsIgnoreCase(czyq)){
				colList=new String[]{"pkValue","disabled","yqmc","lddm","ldmc","xbxd","cs","zrs"};
			//��������
			}else{
				colList=new String[]{"pkValue","disabled","lddm","ldmc","xbxd","cs","zrs"};
			}
			
		//����	
		}else if("qs".equalsIgnoreCase(mklx)  || "qswh".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","ldmc","cs","qsh","cws","kcws","rzrs","xb","kfhz","sfbz","fpbj","rzqk","xbxd"};
		//��λ
		}else if("cw".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","disabled","lddm","ldmc","cs","qsh","cwh","cwbj","sjly"};
		}
		myForm.setColList(colList);
	}
	
	/**
	 * ��ȡҵ����������ֶ�
	 * @return String[]
	 * @throws Exception 
	 */
	public void getYwColList(GyglGywhForm myForm){
		
		String[] colList=null;
		
		String mklx=myForm.getMklx();
		
		String fpdx=myForm.getFpdx();
		//У��
		if("qsfp".equalsIgnoreCase(mklx)){
			
			if("xy".equalsIgnoreCase(fpdx) || "bj".equalsIgnoreCase(fpdx)){
				colList=new String[]{"pkValue","ldmc","cs","qsh","fpdx","bmmc","sjly"};
			}else if("njxy".equalsIgnoreCase(fpdx) || "njzy".equalsIgnoreCase(fpdx)){
				colList=new String[]{"pkValue","ldmc","cs","qsh","fpdx","nj","bmmc","sjly"};
			}
			
		//��λ����
		}else if("cwfp".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","xh","xm","bjmc","ldmc","qsh","cs","cwh","sjly"};
		//ѧ��ס����ʷ��Ϣ	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			colList=new String[]{"pkValue","xh","xm","bjmc","ldmc","qsh","cwh","sjly"};
		}
		myForm.setColList(colList);
	}
	
	/**
	 * ��ȡ�������ݱ�ͷ��Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getJcsjTopTr(GyglGywhForm myForm,
			MessageResources message) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;
		String czxq = myForm.getCzxq();
		String czyq = myForm.getCzyq();
		String mklx = myForm.getMklx();

		String xqdm = message.getMessage("lable.xiaoqu")+"����";
		String xqmc = message.getMessage("lable.xiaoqu")+"����";
		String yqdm = message.getMessage("lable.yuanqu")+"����";
		String yqmc = message.getMessage("lable.yuanqu")+"����";
		String lddm = message.getMessage("lable.ld")+"����";
		String ldmc = message.getMessage("lable.ld")+"����";
		String cs = message.getMessage("lable.cs");
		String qs = message.getMessage("lable.qs")+"��";
	
		// У��
		if ("xq".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { "����", xqdm, xqmc, "������Դ" };
			colListEN = myForm.getColList();
			// ԰��
		} else if ("yq".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { "����", yqdm, yqmc, xqdm, "������Դ" };
			colListEN = myForm.getColList();
			// ¥��
		} else if ("ld".equalsIgnoreCase(mklx) || "ldwh".equalsIgnoreCase(mklx)) {
			colListEN = myForm.getColList();
			
//			ͬʱ����У����԰��
			if("��".equalsIgnoreCase(czxq) && "��".equalsIgnoreCase(czyq)){
				colListCN = new String[] { "����", xqmc,yqmc,lddm, ldmc,"�Ա�", cs,"������" };
			//����У��,������԰��
			}else if("��".equalsIgnoreCase(czxq) && "��".equalsIgnoreCase(czyq)){
				colListCN = new String[] { "����", xqmc,lddm, ldmc,"�Ա�", cs,"������"  };
			//����԰��,������У��
			}else if("��".equalsIgnoreCase(czxq) && "��".equalsIgnoreCase(czyq)){
				colListCN = new String[] { "����",yqmc,lddm, ldmc,"�Ա�", cs,"������"  };
			//��������
			}else{
				colListCN = new String[] { "����",lddm, ldmc,"�Ա�", cs, "������"  };
			}
			
			// ����
		} else if ("qs".equalsIgnoreCase(mklx) || "qswh".equalsIgnoreCase(mklx)) {
			colListEN = myForm.getColList();
			colListCN = new String[] { "����", ldmc, cs, qs, "��λ��","�մ�λ��","��ס����","�Ա�",
					"�ɷ��ס", "�շѱ�׼","������", "������ס���","¥���Ա�����"};
		} else if ("cw".equalsIgnoreCase(mklx)) {
			colListEN = myForm.getColList();
			colListCN = new String[] { "����", lddm, ldmc, cs, qs, "��λ��", "��λ���",
					"������Դ" };
		} else if ("zsxx".equalsIgnoreCase(mklx)) {
			colListEN = new String[] { "��סʱ��","�춯ʱ��","�춯ǰ��λ","�춯��λ"};
			colListCN = new String[] { "��סʱ��","�춯ʱ��","�춯ǰ��λ","�춯��λ"};
		}

		return dao.arrayToList(colListEN, colListCN);

	}
	
	/**
	 * ��ȡҵ�����ݱ�ͷ��Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>> getYwsjTopTr(GyglGywhForm myForm){

		DAO dao = DAO.getInstance();
	
		String[] colListCN = null;
		String[] colListEN = null;
		
		String mklx=myForm.getMklx();
		
		String fpdx=myForm.getFpdx();
		
		//���ҷ���
		if("qsfp".equalsIgnoreCase(mklx)){
			
			if("xy".equalsIgnoreCase(fpdx) || "bj".equalsIgnoreCase(fpdx)){
				colListCN=new String[]{"����","¥������","¥��","���Һ�","�������","��������","������Դ"};
				colListEN=myForm.getColList();
			}else if("njxy".equalsIgnoreCase(fpdx) || "njzy".equalsIgnoreCase(fpdx)){
				colListCN=new String[]{"����","¥������","¥��","���Һ�","�������","�꼶","��������","������Դ"};
				colListEN=myForm.getColList();
			}
			
		//��λ����
		}else if("cwfp".equalsIgnoreCase(mklx)){
			colListCN=new String[]{"����","ѧ��","����","�༶","¥������","���Һ�","����","��λ��","������Դ"};
			colListEN=myForm.getColList();
		//ѧ��ס����ʷ��Ϣ	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			colListCN=new String[]{"����","ѧ��","����","�༶","¥������","���Һ�","��λ��","������Դ"};
			colListEN=myForm.getColList();
		}
		return dao.arrayToList(colListEN, colListCN);
		
	}
	
	/**
	 * ɾ����������
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delJcsj(GyglGywhForm myForm,
			HttpServletRequest request) throws Exception{
		String mklx=myForm.getMklx();
		
		DelDataDetect delData=new DelDataDetect();
		DelDetectModel model=new DelDetectModel();
		
		if("xq".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=xq");
			myForm.setTableName("dm_zju_xq");
			myForm.setQuery(" where dm= ");
		//԰��
		}else if("yq".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=yq");
			myForm.setTableName("yqdmb");
			myForm.setQuery(" where yqdm= ");
		//¥��	
		}else if("ld".equalsIgnoreCase(mklx) || "ldwh".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=ld");
			myForm.setTableName("sslddmb");
			myForm.setQuery(" where lddm= ");
		//����	
		}else if("qs".equalsIgnoreCase(mklx) || "qswh".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=qs");
			myForm.setTableName("ssxxb");
			myForm.setQuery(" where lddm||'!!@!!'||cs||'!!@!!'||qsh= ");
		}else if("cw".equalsIgnoreCase(mklx)){
			model.setPath("gygl_gywh_jcsj.do&searchType=cw");
			myForm.setTableName("xg_gygl_qtcwxxb");
			myForm.setQuery(" where lddm||cs||qsh||cwh= ");
		}
		model.setPkValue(myForm.getCheckVal());
		delData.dataDetect(model,request);
		if(model.isBool()){
			
			dao.delJcsj(myForm);
		}else{
			
			request.setAttribute("delMessage", model.getMessage());
		}
		return model.isBool();
	}
	
	/**
	 * ɾ��ҵ������
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delYwsj(GyglGywhForm myForm, HttpServletRequest request)
			throws Exception {
		String mklx = myForm.getMklx();

		DelDataDetect delData = new DelDataDetect();
		DelDetectModel model = new DelDetectModel();
		// ���ҷ���
		if ("qsfp".equalsIgnoreCase(mklx)) {
			model.setPath("gygl_gywh_ywsj.do&searchType=qsfp");
			myForm.setTableName("xg_gygl_qsfpb");
			myForm.setQuery(" where lddm||cs||qsh= ");
			// ��λ����
		} else if ("cwfp".equalsIgnoreCase(mklx)) {
			model.setPath("gygl_gywh_jcsj.do&searchType=cw");
			myForm.setTableName("xszsxxb");
			myForm.setQuery(" where xh= ");
			// ��ʷ��Ϣ
		} else if ("lsxx".equalsIgnoreCase(mklx)) {
			myForm.setTableName("xszslsxxb");
			myForm.setQuery(" where guid= ");
		}
		model.setPkValue(myForm.getCheckVal());
		delData.dataDetect(model, request);
		if (model.isBool()) {

			dao.delJcsj(myForm);
			
		} else {

			request.setAttribute("delMessage", model.getMessage());
		}
		return model.isBool();
	}
	
	/**
	 * ��ȡ��������ҳǩ��Ϣ
	 * @param jbszModel
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getJcsjCard(GyglJbszForm jbszModel){
		List<HashMap<String,String>>cardList=new ArrayList<HashMap<String,String>>();
		String czxq=jbszModel.getCzxq();
		String czyq=jbszModel.getCzyq();
		HashMap<String,String>hashMap=new HashMap<String,String>();
		if("��".equalsIgnoreCase(czxq)){
			hashMap.put("dm", "xq");
			hashMap.put("mcxs", "lable");
			hashMap.put("mc", "lable.xiaoqu");
			cardList.add(hashMap);
		}
		
		if("��".equalsIgnoreCase(czyq)){
			hashMap=new HashMap<String,String>();
			hashMap.put("dm", "yq");
			hashMap.put("mcxs", "lable");
			hashMap.put("mc", "lable.yuanqu");
			cardList.add(hashMap);
		}
		
//		hashMap=new HashMap<String,String>();
//		hashMap.put("dm", "ld");
//		hashMap.put("mcxs", "lable");
//		hashMap.put("mc", "lable.ld");
//		cardList.add(hashMap);
//		
//		hashMap=new HashMap<String,String>();
//		hashMap.put("dm", "qs");
//		hashMap.put("mcxs", "lable");
//		hashMap.put("mc", "lable.qs");
//		cardList.add(hashMap);
//		
//		hashMap=new HashMap<String,String>();
//		hashMap.put("dm", "cw");
//		hashMap.put("mcxs", "xsmc");
//		hashMap.put("mc", "������λ��Ϣ����ס�˴�λ��");
//		cardList.add(hashMap);
		return cardList;
	}
	
	
	/**
	 * ��ȡҵ������ҳǩ��Ϣ
	 * @param jbszModel
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getYwsjCard(){
		List<HashMap<String,String>>cardList=new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String>hashMap=new HashMap<String,String>();
		//���ҷ���
		hashMap.put("dm", "qsfp");
		hashMap.put("mcxs", "xsmc");
		hashMap.put("mc", "���ҷ���");
		cardList.add(hashMap);
		
		//��λ����
		hashMap=new HashMap<String,String>();
		hashMap.put("dm", "cwfp");
		hashMap.put("mcxs", "xsmc");
		hashMap.put("mc", "��λ����");
		cardList.add(hashMap);
		
		//��ʷ��Ϣ
		hashMap=new HashMap<String,String>();
		hashMap.put("dm", "lsxx");
		hashMap.put("mcxs", "xsmc");
		hashMap.put("mc", "��ʷ��Ϣ");
		cardList.add(hashMap);
		return cardList;
	}
	
	/**
	 * ��������ģ����Ϣ��ʼ��
	 * @param myForm
	 */
	public void JcmkxxInit(GyglGywhForm myForm){
		String mklx=myForm.getMklx();
		if("xq".equalsIgnoreCase(mklx)){
			myForm.setTableName("dm_zju_xq");
			myForm.setRealTable("dm_zju_xq");
		//԰��
		}else if("yq".equalsIgnoreCase(mklx)){
			myForm.setTableName("yqdmb");
			myForm.setRealTable("yqdmb");
		//¥��	
		}else if("ld".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_sslddm");
			myForm.setRealTable("sslddmb");
		//����	
		}else if("qs".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_ssxx");
			myForm.setRealTable("ssxxb");
		//��λ
		}else if("cw".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_gygl_qtcwxxb");
			myForm.setRealTable("xg_gygl_qtcwxxb");
		}else if("qswh".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_ssxx");
			myForm.setRealTable("ssxxb");
		}else if("ldwh".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_view_gygl_sslddm");
			myForm.setRealTable("sslddmb");
		}
	}
	
	/**
	 * ������ģ����Ϣ��ʼ��
	 * @param myForm
	 */
	public void YwmkxxInit(GyglGywhForm myForm){
		String mklx=myForm.getMklx();
		//���ҷ���
		if("qsfp".equalsIgnoreCase(mklx)){
			myForm.setTableName("xg_gygl_qsfpb");
			myForm.setRealTable("xg_gygl_qsfpb");
		//��λ����
		}else if("cwfp".equalsIgnoreCase(mklx)){
			myForm.setTableName("xszsxxb");
			myForm.setRealTable("xszsxxb");
		//��ʷ��Ϣ	
		}else if("lsxx".equalsIgnoreCase(mklx)){
			myForm.setTableName("xszslsxxb");
			myForm.setRealTable("xszslsxxb");
		}
	}
	
	/**
	 * ��ȡ����У����Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getXqxx(GyglGywhForm myForm){
		return dao.getXqxx(myForm);
	}
	
	/**
	 * ��ȡ����԰����Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getYqxx(GyglGywhForm myForm){
		return dao.getYqxx(myForm);
	}	

	/**
	 * ��ȡ¥����Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getLdxx(GyglGywhForm myForm){
		return dao.getLdxx(myForm);
	}
	
	/**
	 * ��ȡ������ϸ��Ϣ
	 * @param myForm
	 * @return  HashMap<String,String>
	 * @author qlj
	 */
	public HashMap<String,String>getQsxx(GyglGywhForm myForm){
		//������ϸ��Ϣ
		HashMap<String,String>qsxxMap= dao.getQsxx(myForm);
		//�Ѿ���סѧ������
		qsxxMap.putAll(dao.getYzrCws(myForm));
		return qsxxMap;
	}
	
	/**
	 * ��ȡ������ϸ��Ϣ
	 * @param myForm
	 * @return  HashMap<String,String>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getCwxx(GyglGywhForm myForm){
		return dao.getCwxx(myForm);
	}
	
	/**
	 * ��ȡ¥����Ϣ
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,String>>getLdList(GyglGywhForm myForm){
		return dao.getLdList(myForm);
	}
	
	/**
	 * ��ȡ԰����Ϣ(DWR ¥��ά��)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getYqxxList(){
		return dao.getYqList();
	}
	
	
	/**
	 * ��ȡ԰����Ϣ(DWR ¥��ά��)
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getYqList(String xqdm){
		return dao.getYqxxList(xqdm);
	}
	
	public boolean  zdscQs(GyglGywhForm myForm,User user) throws Exception{
		
		List<HashMap<String,String>>ssxxList=new ArrayList<HashMap<String,String>>();
		//���Һ�λ��
		String bls=myForm.getBls();
		
		String wsxz=myForm.getWsxz();
		//��������
		String[]qsslArr=myForm.getQsslArr();
		//��λ��
		String[]cwsArr=myForm.getCwsArr();
		//�շѱ�׼
		String[]sfbzArr=myForm.getSfbzArr();
		//������
		String[]fpbjArr=myForm.getFpbjArr();
		//����
		String[]csArr=myForm.getCsArr();  
		//�Ա�
		String[]xbArr=myForm.getXbArr();
		//�ɷ��ס
		String[]kfhzArr=myForm.getKfhzArr();
		//��Ź���
		String bhgz=myForm.getBhgz();
		
		//¥����Ϣ
		qsxxList=dao.getQsList(myForm);
		for(int i=0;i<qsslArr.length;i++){
			HashMap<String,String>ssxxMap=new HashMap<String,String>();
			if(!Base.isNull(qsslArr[i]) 
					&& !"".equalsIgnoreCase(qsslArr[i])){
				ssxxMap.put("bls", bls);
				ssxxMap.put("qssl", qsslArr[i]);
				ssxxMap.put("cws", cwsArr[i]);
				ssxxMap.put("sfbz", sfbzArr[i]);
				ssxxMap.put("fpbj", fpbjArr[i]);
				ssxxMap.put("cs", csArr[i]);
				ssxxMap.put("xb", xbArr[i]);
				ssxxMap.put("kfhz", kfhzArr[i]);
				ssxxMap.put("bhgz", bhgz);
				ssxxMap.put("wsxz", wsxz);
				ssxxList.add(ssxxMap);
			}
			
		}
		
		//�Զ��������������Ϣ
		boolean blog= saveZdscqs(zdsc(ssxxList,myForm),myForm);
		GyglGywhModel model=new GyglGywhModel();
		model.setSfplcz(true);
		creatGyglCwxx(model,user);
		
		return blog;
	}
	
	/**
	 * ������λ��Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void creatGyglCwxx(GyglGywhModel model, User user) throws Exception {

		// ������Ϣ��
		String tableName = "ssxxb";
		String[] colList = new String[] { "lddm", "cs", "qsh", "cws" };
		
		StringBuilder query =new StringBuilder();
		
		ArrayList<String>inputVal=new ArrayList<String>();
		
		if(!model.isSfplcz()){
			query.append(" where lddm=? ");
			query.append(" and cs=? ");
			query.append(" and qsh=? ");
			inputVal.add(model.getLddmQuery());
			inputVal.add(model.getCsQuery());
			inputVal.add(model.getQshQuery());
		}
		
		query.append(" order by lddm,cs,qsh ");
		
		List<HashMap<String, String>> qsList = getRsList(tableName, query.toString(),
				inputVal.toArray(new String[]{}), colList, "");

		// �����ֶ�
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "cwh" };

		// ¥��
		ArrayList<String> ldList = new ArrayList<String>();
		// ����
		ArrayList<String> csList = new ArrayList<String>();
		// ���Һ�
		ArrayList<String> qshList = new ArrayList<String>();
		// ��λ��
		ArrayList<String> cwhList = new ArrayList<String>();

		if (qsList != null && qsList.size() > 0) {

			for (int i = 0; i < qsList.size(); i++) {

				HashMap<String, String> map = qsList.get(i);
				// ¥������
				String lddm = map.get("lddm");
				// ¥������
				String cs = map.get("cs");
				// ¥������
				String qsh = map.get("qsh");
				// ��ס�˴�λ��
				String cws = map.get("cws");

				if (!Base.isNull(cws)) {
					for (int j = 0; j < Integer.parseInt(cws); j++) {
						ldList.add(lddm);
						csList.add(cs);
						qshList.add(qsh);
						cwhList.add(String.valueOf(j+1));
					}
				}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qshList.toArray(new String[] {}));
			model.setCwh(cwhList.toArray(new String[] {}));

			tableName = "xg_gygl_cwxxb";
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			if(!model.isSfplcz()){
				saveForm.setPk(" lddm||cs||qsh ");
				HashMap<String,String>qsMap=qsList.get(0);
				saveForm.setPkValue(new String[] {qsMap.get("lddm")+qsMap.get("cs")+
						qsMap.get("qsh")});
			}else{
				saveForm.setPk("1");
				saveForm.setPkValue(new String[] { "1" });
			}
			saveForm.setArrzd(arrzd);

			saveInfoToDb(saveForm, model, user);
		}
	}
	
	/**
	 * �������λ��Ϣ
	 * @param myForm
	 * @param user
	 * @return boolean
	 * @throws Exception
	 * @author qlj
	 */
	public boolean creatGyglQtcwxx(GyglGywhForm myForm, User user) throws Exception{
		
		//����������Ϣ��
		String tableName = "xg_gygl_qtcwxxb";

		GyglGywhModel model = new GyglGywhModel();
		// ��λ������(ǰ̨��ȡ)
		String[] cwh = myForm.getCwhArr();
		if (cwh != null && cwh.length > 0) {
			// ��λ�������(ǰ̨��ȡ)
			String[] cwlxArr = myForm.getCwbjArr();
			// ���鳤��
			int len = cwh.length;
			// ����
			String[] pkValue = new String[len];
			// ¥������
			String lddm = myForm.getLddm();
			// ����
			String cs = myForm.getCs();
			// ���Һ�
			String qsh = myForm.getQsh();

			int n = 0;
			for (int i = 0; i < len; i++) {
				// ƴ����
				pkValue[i] = lddm + cs + qsh + cwh[i];
				if (!"δ��ס".equalsIgnoreCase(cwlxArr[i])) {
					n++;
				}
			}

			// ��Ҫ����������ֶ�
			String[] arrzd = { "lddm", "cs", "qsh", "cwh", "cwbj", "sjly" };
			String[] lddmArr = new String[n];
			String[] qshArr = new String[n];
			String[] csArr = new String[n];
			String[] cwbjArr = new String[n];
			String[] sjlyArr = new String[n];
			String[] cwhArr = new String[n];

			int m = 0;
			for (int i = 0; i < len; i++) {
				if (!"δ��ס".equalsIgnoreCase(cwlxArr[i])) {
					lddmArr[m] = lddm;
					csArr[m] = cs;
					qshArr[m] = qsh;
					sjlyArr[m] = "�ֶ�";
					cwbjArr[m] = cwlxArr[i];
					cwhArr[m] = cwh[i];
					m++;
				}
			}

			SaveForm saveForm = new SaveForm();
			model.setLddm(lddmArr);
			model.setCs(csArr);
			model.setQsh(qshArr);
			model.setCwh(cwhArr);
			model.setSjly(sjlyArr);
			model.setCwbj(cwbjArr);

			saveForm.setTableName(tableName);
			saveForm.setPk(" lddm||cs||qsh||cwh ");
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);
			//ͨ�ñ��淽��
			return saveInfoToDb(saveForm, model, user);
			
		} else {

			return true;
		}
	}
	
	/**
	 * �����Զ����ɵ�����
	 * @param scqsxxList
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 * @author qlj
	 */
	public boolean saveZdscqs(List<HashMap<String, String>>scqsxxList
			,GyglGywhForm myForm) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
		GyglGywhModel model=new GyglGywhModel();
		// �������ݲ��� �ı���
		String realTable = "ssxxb";
		
		String[] arrzd = new String[] { "lddm", "qsh","cs","cws", "fpbj", "sfbz","xb","kfhz","sjly"};
		
		// ��ǰҳ��ѧ������(��ɾ��������ҳ��Ϣɾ��ʱ)
		int len=scqsxxList.size();
		
		String[] pkValue=new String[len];
		String[] lddm = new String[len];
		String[] qsh = new String[len];
		String[] cs = new String[len];
		String[] cws = new String[len];
		String[] fpbj = new String[len];
		String[] sfbz = new String[len];
		String[] xb = new String[len];
		String[] kfhz = new String[len];
		String[] sjly = new String[len];
		for (int i = 0; i < len; i++) {
			HashMap<String,String>scqsxxMap=scqsxxList.get(i);
			pkValue[i] = "2";
			sjly[i] = "�Զ�";
			lddm[i]=myForm.getLddm();
			qsh[i]=scqsxxMap.get("qsh");
			cs[i]=scqsxxMap.get("cs");
			cws[i]=scqsxxMap.get("cws");
			fpbj[i]=scqsxxMap.get("fpbj");
			sfbz[i]=scqsxxMap.get("sfbz");
			xb[i]=scqsxxMap.get("xb");
			kfhz[i]=scqsxxMap.get("kfhz");
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk("1");
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
		model.setSjly(sjly);
		model.setLddm(lddm);
		model.setQsh(qsh);
		model.setCs(cs);
		model.setCws(cws);
		model.setFpbj(fpbj);
		model.setSfbz(sfbz);
		model.setXb(xb);
		model.setKfhz(kfhz);
			
		myForm.setQss(String.valueOf(len));
		return  ghxyNjzrwhService.saveTyxx(saveForm, model);
	}
	
	
	/**
	 * ��ȡ¥����Ϣ�б�
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>> getLcxxList(GyglGywhForm myForm) {
		// ��ȡ����
		String cs = myForm.getCs();
		List<HashMap<String, String>> lcxxList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < qsxxList.size(); i++) {
			HashMap<String, String> qsxxMap = qsxxList.get(i);

			// ��ȡ��¥�����ϸ��Ϣ
			if (cs.equalsIgnoreCase(qsxxMap.get("cs"))) {
				HashMap<String, String> lcxxMap = new HashMap<String, String>();
				lcxxMap.put("lddm", qsxxMap.get("lddm"));
				lcxxMap.put("cs", qsxxMap.get("cs"));
				lcxxMap.put("qsh", qsxxMap.get("qsh"));
				lcxxList.add(lcxxMap);
			}

		}
		
		return lcxxList;
	}
	
	/**
	 * �Զ���������
	 * @param ssxxList
	 * @param myForm
	 * @return List<HashMap<String, String>>
	 * @author qlj
	 */
	public List<HashMap<String, String>> zdsc(List<HashMap<String, String>> ssxxList,
			GyglGywhForm myForm) {
		// �Զ�����
		List<HashMap<String, String>> zdscqsList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < ssxxList.size(); i++) {

			HashMap<String, String> ssxxMap = ssxxList.get(i);
			//��������
			int qssl = Integer.parseInt(ssxxMap.get("qssl"));
			int m = 1;
			//����(��������)
			int n = 1;
			while(qssl>=n){
				HashMap<String,String>zdscqsMap=new HashMap<String,String>();
				ssxxMap.put("szbh", String.valueOf((m)));
				
				if("blxs".equalsIgnoreCase(ssxxMap.get("bhgz"))){
					ssxxMap = scSsbh(ssxxMap);
				}else if("gdws".equalsIgnoreCase(ssxxMap.get("bhgz"))){
					ssxxMap =scKdwQsh(ssxxMap);
				}
				myForm.setCs(ssxxMap.get("cs"));
				List<HashMap<String, String>> lcxxList = getLcxxList(myForm);
				boolean blog = true;
				for (int j = 0; j < lcxxList.size(); j++) {
					HashMap<String, String> lcxxMap = lcxxList.get(j);
					if (ssxxMap.get("qsh").equalsIgnoreCase(lcxxMap.get("qsh"))) {
						m++;
						blog = false;
						break;
					}
				}
				if (blog) {
					m++;
					n++;
					zdscqsMap.put("qsh", ssxxMap.get("qsh"));
					zdscqsMap.put("lddm", myForm.getLddm());
					zdscqsMap.put("cws", ssxxMap.get("cws"));
					zdscqsMap.put("fpbj", ssxxMap.get("fpbj"));
					zdscqsMap.put("sfbz", ssxxMap.get("sfbz"));
					zdscqsMap.put("cs", myForm.getCs());
					zdscqsMap.put("kfhz", ssxxMap.get("kfhz"));
					zdscqsMap.put("xb",ssxxMap.get("xb"));
					zdscqsList.add(zdscqsMap);
				}
				
			}
		}

		return zdscqsList;
	}
	
	/**
	 * ��0�����Һ�����
	 * @param ssxxMap
	 * @return
	 */
	public HashMap<String,String> scSsbh(HashMap<String,String>ssxxMap){
		//������
		String bls=ssxxMap.get("bls");
		//����
		String cs=ssxxMap.get("cs");
		String szbh=ssxxMap.get("szbh");
		String ling="0";
		if("2".equalsIgnoreCase(bls)){
			ling="00";
		}else if("3".equalsIgnoreCase(bls)){
			ling="000";
		}
		ssxxMap.put("qsh", cs+ling+szbh);
		return ssxxMap;
	}
	
	/**
	 * �̶�λ�����Һ�����
	 * @param ssxxMap
	 * @return
	 */
	public HashMap<String,String> scKdwQsh(HashMap<String,String>ssxxMap){
		//��ʾλ��
		String wsxz=ssxxMap.get("wsxz");
		//����
		String cs=ssxxMap.get("cs");
		String szbh=ssxxMap.get("szbh");
		//��ų���
		int len=szbh.length();
		//����ֻ������һλ
		int xsws=Integer.parseInt(wsxz)-1;
		xsws=xsws-len;
		String qsh="";
		qsh+=cs;
		for(int i=0;i<xsws;i++){
			qsh+="0";
		}
		qsh+=szbh;
		
		
		ssxxMap.put("qsh", qsh);
		return ssxxMap;
	}
	
	/**
	 * ����У��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean addXqxx(GyglGywhForm myForm) throws Exception{

		return dao.addXqxx(myForm);
	}
	
	/**
	 * �޸�У��
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean modiXqxx(GyglGywhForm myForm) throws Exception{

		return dao.modiXqxx(myForm);
	}
	
	/**
	 * ����԰����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean addYqxx(GyglGywhForm myForm) throws Exception{

		return dao.addYqxx(myForm);
	}
	
	/**
	 * ����¥����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean addLdxx(GyglGywhForm myForm) throws Exception{

		return dao.addLdxx(myForm);
	}
	
	/**
	 * ����������Ϣ(��λ��Ϣ��������λ��Ϣ)
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean addQsxx(GyglGywhForm myForm,User user) throws Exception{
		
		boolean blog=dao.addQsxx(myForm);	
		//�������ҳɹ���ִ��
		if(blog){
			GyglGywhModel model=new GyglGywhModel();
			//���ô�λ����ʱ�Ƿ�Ϊ��������
			model.setSfplcz(false);
			model.setLddmQuery(myForm.getLddm());
			model.setCsQuery(myForm.getCs());
			model.setQshQuery(myForm.getQsh());
			//������λ��Ϣ
			creatGyglCwxx(model,user);
			//����������λ��Ϣ
			creatGyglQtcwxx(myForm,user);
		}
		return blog;
	}
	
	/**
	 * �޸�԰����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean modiYqxx(GyglGywhForm myForm) throws Exception{

		return dao.modiYqxx(myForm);
	}
	
	/**
	 * �޸�¥����Ϣ
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean modiLdxx(GyglGywhForm myForm) throws Exception{
		if(!"���".equalsIgnoreCase(myForm.getXbxd())){
			updateQsxb(myForm);
		}
		return dao.modiLdxx(myForm);
	}
	
	public boolean modiQsxx(GyglGywhForm myForm,User user) throws Exception{

		boolean blog= dao.modiQsxx(myForm);
		//�������ҳɹ���ִ��
		if(blog){
			GyglGywhModel model=new GyglGywhModel();
			//���ô�λ����ʱ�Ƿ�Ϊ��������
			model.setSfplcz(false);
			model.setLddmQuery(myForm.getLddm());
			model.setCsQuery(myForm.getCs());
			model.setQshQuery(myForm.getQsh());
			//������λ��Ϣ
			creatGyglCwxx(model,user);
			//����������λ��Ϣ
			creatGyglQtcwxx(myForm,user);
		}
		return blog;
	}
	
	public List<HashMap<String,String>>getXqList(){
		return dao.getXqList();
	}
	
	public List<HashMap<String,String>>getQsfpList(GyglGywhForm myForm){

		return dao.getLdrzqk(myForm);
	}
	
	/**
	 * ��ȡ¥��¥��ͳ����Ϣ
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdcsList(GyglGywhForm myForm){
		
		HashMap<String,String>ldxx=dao.getLdxx(myForm);
		//��ס���
		List<HashMap<String,String>>lcrzqk=dao.getLcrzqk(myForm);
		//�������
		List<HashMap<String,String>>lcfpqk=dao.getLcfpqk(myForm);
		//��������
		List<HashMap<String,String>>lcqss=dao.getLcqss(myForm);
		int cs=Integer.parseInt(ldxx.get("cs"));
		List<HashMap<String,String>>lcxxList=new ArrayList<HashMap<String,String>>();
		for(int i=1;i<=cs;i++){
			HashMap<String,String>lcxxMap=new HashMap<String,String>();
			String lc=String.valueOf(i);
			lcxxMap.put("cs", lc);
			//��ס���
			lcxxMap.put("rzrs", "0");
			for(int j=0;j<lcrzqk.size();j++){
				HashMap<String,String>ldrzqkMap=lcrzqk.get(j);
				if(lc.equalsIgnoreCase(ldrzqkMap.get("cs"))){
					lcxxMap.put("rzrs", ldrzqkMap.get("rzrs"));
					break;
				}
				
			}
			//�������
			lcxxMap.put("yfps","0");
			for(int k=0;k<lcfpqk.size();k++){
				HashMap<String,String>lcfpqkMap=lcfpqk.get(k);
				if(lc.equalsIgnoreCase(lcfpqkMap.get("cs"))){
					//�Ѿ�������������
					lcxxMap.put("yfps", lcfpqkMap.get("yfps"));
					break;
				}
				
			}
			//��������
			lcxxMap.put("qss", "0");
			for(int l=0;l<lcqss.size();l++){
				HashMap<String,String>lcqssMap=lcqss.get(l);
				if(lc.equalsIgnoreCase(lcqssMap.get("cs"))){
					//�Ѿ�������������
					lcxxMap.put("qss", lcqssMap.get("qss"));
					break;
				}
			}
			lcxxList.add(lcxxMap);
		}
		return lcxxList;
	}
	
	/**
	 * ��ȡ��������ͳ�����
	 * @return
	 */
	public List<HashMap<String,String>>getKxssTjList(GyglGywhForm myForm){
		GyglCwglDAO cwglDao=new GyglCwglDAO();
		//HashMap<String,String>ldxx=dao.getLdxx(myForm);
		//�������
		List<HashMap<String,String>>wfpcws=dao.getWfpcws(myForm);
		//��������
		List<HashMap<String,String>>kxqss=dao.getKxqss(myForm);
		
		List<HashMap<String,String>>lcxxList=new ArrayList<HashMap<String,String>>();
		
		GyglCwglForm cwglForm=new GyglCwglForm();
		cwglForm.setLddm(myForm.getLddm());
		List<HashMap<String,String>>csList=cwglDao.getLdlcxx(cwglForm);
		for(int i=0;i<csList.size();i++){
			HashMap<String,String>lcxxMap=new HashMap<String,String>();
			HashMap<String,String>ldlcxxMap=csList.get(i);
			
			lcxxMap.put("cs", ldlcxxMap.get("cs"));
			//�������
			lcxxMap.put("wrzcw","0");
			for(int k=0;k<wfpcws.size();k++){
				HashMap<String,String>wfpcwsMap=wfpcws.get(k);
				if(ldlcxxMap.get("cs").equalsIgnoreCase(wfpcwsMap.get("cs"))){
					//�Ѿ�������������
					lcxxMap.put("wrzcw", wfpcwsMap.get("wrzcw"));
					break;
				}
				
			}
			//��������
			lcxxMap.put("qss", "0");
			for(int l=0;l<kxqss.size();l++){
				HashMap<String,String>kxqssMap=kxqss.get(l);
				if(ldlcxxMap.get("cs").equalsIgnoreCase(kxqssMap.get("cs"))){
					//�Ѿ�������������
					lcxxMap.put("qss", kxqssMap.get("kxqss"));
					break;
				}
			}
			lcxxList.add(lcxxMap);
		}
		return lcxxList;
	}
	
	/**
	 * ��ȡ¥���б�
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getCwsList(GyglGywhForm myForm){
		
		int cws=Integer.parseInt(myForm.getCws());
		
		List<HashMap<String,String>>cwsList=new ArrayList<HashMap<String,String>>();
		
		int cwgs=1;
		
		if(!Base.isNull(myForm.getCwgs())){
			 cwgs = Integer.parseInt(myForm.getCwgs());
		}
		
		for(int i=cwgs;i<=cws;i++){
			HashMap<String,String>cwsMap=new HashMap<String,String>();
			cwsMap.put("dm", String.valueOf(i));
			cwsMap.put("mc", String.valueOf(i));
			cwsList.add(cwsMap);
		}
		
		myForm.setCws(null);
		
		return cwsList;
	}
	
	public List<String[]>getXszsxxInfo(GyglGywhForm myForm) throws Exception{
		return dao.getXszsxxInfo(myForm);
	}
	
	/**
	 * ����¥���Ա��޸�����޸������Ա�
	 * @param myForm
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean updateQsxb(GyglGywhForm myForm) throws Exception{
		
		return dao.updateQsxb(myForm);
	}
	
	/**
	 * �޸�������Ϣ
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateQsxx(GyglGywhForm myForm,User user) throws Exception{
		return dao.updateQsxx(myForm,user);
	}
	
	public void setQsBhgz(GyglGywhForm myForm){
		String bhgzxz=InitGygl.initGygl.getBhgz();
		String[]bhgzxzArr=bhgzxz.split("!!@@!!");
		if(bhgzxzArr!=null && bhgzxzArr.length>0){
			String bhgz=bhgzxzArr[0];
			if("blxs".equalsIgnoreCase(bhgz)){
				myForm.setBhgz(bhgz);
			}else if("gdws".equalsIgnoreCase(bhgz)){
				myForm.setBhgz(bhgz);
			}
		}
	}
}