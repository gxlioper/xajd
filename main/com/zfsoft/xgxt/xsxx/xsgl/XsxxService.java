package com.zfsoft.xgxt.xsxx.xsgl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xlzx.tsxsgl.TsxsService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48
 * @�汾�� V5.12.20
 */
public class XsxxService extends SuperServiceImpl<XsxxForm, XsxxDao> {

	private XsxxDao dao = new XsxxDao();

	public XsxxService() {
		super.setDao(dao);
	}

	public List<HashMap<String, String>> getPageListForGygl(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		return dao.getPageListForGygl(model, user, searchTjByGyfdy);
	}
	
	public List<HashMap<String, String>> getPageListForGyglSsyd(XsxxForm model,
			User user, String searchTjByGyfdy) throws Exception {
		return dao.getPageListForGyglSsyd(model, user, searchTjByGyfdy);
	}
	
	/**
	 * ѧ�������������룬������������Ա��
	 */
	public List<HashMap<String, String>> showStudentsKnsrdsqBjpy(XsxxForm model, User user)
	throws Exception {
		return dao.showStudentsKnsrdsqBjpy(model, user);
	}

	/**
	 * ��ѯѧ��������Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxx(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getXsjbxx(xh);
	}

	/**
	 * ͨ��ѧ�Ų�ѯѧ��
	 * @param model
	 * @param xhs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageList(XsxxForm model,String xhs[])
			throws Exception{
		return dao.getPageList(model,xhs);
	}


	/**
	 * 
	 * @����:������¼���� ���ݴ�ѧ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����04:30:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXlfdlr(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListForXlfdlr(model, user);
	}

	/**
	 * 
	 * @����:������¼���� ���ݴ�ѧ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����04:30:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForXxsbjggl(XsxxForm model,
			User user, String sblx) throws Exception {

		return dao.getPageListForXxsbjggl(model, user, sblx);
	}

	/**
	 * 
	 * @����:�ҽ���ʦ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����04:30:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForJjlsk(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListForJjlsk(model, user);
	}
	
	/**
	 * 
	 * @����:��ȡ����ģ��ѧ����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����05:15:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPageListForDtgl(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListForDtgl(model, user);
	}

	/**
	 * ��ѯѧ����ϸ������Ϣ
	 * 
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getXsjbxxMore(String xh){

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getXsjbxxMore(xh);
		} catch (Exception e) {
			logger.error("select to_char(to_date(a.csrq, 'yyyy-mm-dd')) from xsxxb a where xh ='"+xh+"'");
			throw new NullPointerException();
		}
	}

	/**
	 * ��ѯѧ����ϸ������Ϣ(��У��)
	 * 
	 * @param xh
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getXsjbxxZjsMore(String xh){

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getXsjbxxZjsMore(xh);
		} catch (Exception e) {
			logger.error("select to_char(to_date(a.csrq, 'yyyy-mm-dd')) from xsxxb a where xh ='"+xh+"'");
			throw new NullPointerException();
		}
	}
	/**
	 * ��ѧ�Ų�ѯѧ����ϸ������Ϣ-��ס����Ϣ
	 * 
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMoreWithZSXX(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getXsjbxxMoreWithZSXX(xh);
	}

	/**
	 * ��ѯ���Ź����ѧ����ϸ������Ϣ
	 * 
	 * @param xh
	 * @deprecated
	 * @return
	 */
	public HashMap<String, String> getXsjbxxMoreForDtgl(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getXsjbxxMoreForDtgl(xh);
	}

	/**
	 * 
	 * @����:��ѯ��Ƭ��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-20 ����10:41:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return InputStream ��������
	 * @throws
	 */
	public InputStream getPhotoStream(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		InputStream is = dao.getPhotoStream(xh);

		return is;
	}
	/**
	 * 
	 * @����:��ѯ�߿���Ƭ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-2 ����10:52:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * InputStream �������� 
	 * @throws
	 */
	public InputStream getGkPhotoStream(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		InputStream is = dao.getGkPhotoStream(xh);

		return is;
	}

	/**
	 * 
	 * @����:Ĭ����Ƭ��Base64����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-9-29 ����04:02:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * @throws IOException
	 *             String ��������
	 * @throws
	 */
	public String getDefaultPhotoBase64(HttpServletRequest request)
			throws IOException {

		String defaultPhotoPath = request.getSession().getServletContext()
				.getRealPath("/images/type_pic.gif");

		File file = new File(defaultPhotoPath);

		return FileUtil.getBASE64(file);
	}
	
	/**
	 * @��������ȡ��Ƭ��Base64���룬��û�����ȡĬ����Ƭ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��10�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param defaultPhotoPath
	 * @return
	 * @throws IOException
	 * String ��������
	 */
	public String getPhotoBase64(String xh,String defaultPhotoPath)throws Exception {
		InputStream is = getPhotoStream(xh);
		if (is==null){
			File file = new File(defaultPhotoPath);
			return FileUtil.getBASE64(file);
		}
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (StringUtil.isNull(photo)){
			File file = new File(defaultPhotoPath);
			return FileUtil.getBASE64(file);
		}
		
		return photo;
	}

	/**
	 * @����:�ж�ѧ����ѧ����Ϣ�����Ƿ���ڣ�������У����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-30 ����05:15:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public boolean getCheckStuExists(String xh) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getCheckStuExists(xh);

	}

	/**
	 * 
	 * @����:����ѧ�ź�ѧ�����ѧ���ɼ�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-9-30 ����01:54:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getCjList(String xh, String xn,
			String xq) {

		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getCjlist(xh, xn, xq);
	}
	
	/**
	 * @����:����ѧ�ź�ѧ�����ѧ���ɼ�����߷֡���ͷ֡�ƽ���֡�����������ѧϰ�ɼ��� �㽭����ְҵѧԺ
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-12-13 ����01:22:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getXxcj(String xh, String xn, String xq) {
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		return dao.getXxcj(xh, xn, xq);
	}

	/**
	 * @����:������-����W����Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-25 ����08:58:10
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForTsxs(XsxxForm model,
			User user) throws Exception {

		List<HashMap<String, String>> lst = dao.getPageListForTsxs(model, user);
		TsxsService tsxsSv = new TsxsService();
		if (lst != null & lst.size() > 0) {
			for (HashMap<String, String> thjlInfo : lst) {

				// ��ȡ������������
				if (thjlInfo.get("knlxdm") != null
						&& !"".equals(thjlInfo.get("knlxdm"))) {
					String knlxmc = tsxsSv.getKnlxMc(thjlInfo.get("knlxdm"));
					thjlInfo.put("knlxmc", knlxmc);
				}
			}
		}

		return lst;
	}

	/**
	 * 
	 * @����: ȡ��ѡ���ѧ��ѧ���б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����02:40:52
	 * @param xzxsKey
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectedStudents(String xzxsKey)
			throws Exception {
		return dao.getSelectedStudents(xzxsKey);
	}

	/**
	 * @����:ȡ��ѡ���ѧ��ѧ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����02:41:14
	 * @param xzxsKey
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public int getSelectedCount(String xzxsKey) throws Exception {
		return dao.getSelectedCount(xzxsKey);
	}

	/**
	 * @����: ѡ��ѧ���б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����03:25:02
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectStudentsList(XsxxForm model,
			User user) throws Exception {
		return dao.getSelectStudentsList(model, user);
	}

	/**
	 * @����: ѡ��ѧ��(ȫ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����03:26:34
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSelectAllStudentsList(
			XsxxForm model, User user) throws Exception {

		return dao.getSelectAllStudentsList(model, user);
	}

	/**
	 * @����: ����ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����04:38:48
	 * @param values
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean runInsertSelect(String values, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.insertSelect(values, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @����: ��������ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����10:29:33
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean runInsertSelect(XsxxForm model, User user, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.insertSelect(model, user, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @����: ����ɾ��ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����11:12:44
	 * @param model
	 * @param user
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean runDelSelect(XsxxForm model, User user, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.delSelect(model, user, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @����: ����ɾ��ѡ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����11:14:36
	 * @param values
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean runDelSelect(String values, String xzxsKey) {
		boolean result = false;
		try {
			result = dao.delSelect(values, xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @����: ɾ��ѡ��(���У�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����11:14:36
	 * @param xzxsKey
	 * @return boolean ��������
	 * @throws
	 */
	public boolean runDelSelectAll(String xzxsKey) {
		boolean result = false;
		try {
			result = dao.delSelectAll(xzxsKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * @����: �߼���ѯģʽ(ȫ��ѧ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-13 ����11:45:56
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListAll(XsxxForm model,
			User user) throws Exception {
		return dao.getPageListAll(model, user);
	}

	/**
	 * 
	 * @����:����ѧ�Ż�ȡ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-16 ����07:49:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByXh(String xh) {
		return dao.getBzrxxByXh(xh);
	}

	/**
	 * 
	 * @����:���ݰ༶�����ȡ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-16 ����07:49:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getBzrxxByBjdm(String bjdm) {
		return dao.getBzrxxByBjdm(bjdm);
	}
 
	/**
	 * @����: �㽭�������ѧ��,ѧ�����ѧ��ƽ���ɼ�
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����02:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String  getZjjcPjcj(String xh, String xn,
			String xq) {
		return dao.getZjjcPjcj(xh, xn, xq);
	}
	
	
	/**
	 * @����: �㽭�������ѧ��,ѧ�����ѧ�������������ͳɼ�
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����02:02:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public String  getZjjcZdcj(String xh, String xn,
			String xq) {
		return dao.getZjjcZdcj(xh, xn, xq);
	}
	
	/**
	 * 
	 * @����:����ũҵ������ѧ�Ż�ȡƽ���ɼ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-11 ����10:26:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public HashMap<String,String>  getHznyPjcjWithPm(String xh, String xn,
			String xq) {
		return dao.getHznyPjcjWithPm(xh, xn, xq);
	}
	
	/*
	 * ����ʯ��ѧ��֤��ӡʡ���ؽ�ȡ
	 */
	public HashMap<String, String> getXsjtSsx(String xh) {
		return dao.getXsjtSsx(xh);
	}
	
	/*
	 * ����ʯ��ѧ��֤��ӡ���»������ȡ
	 */
	public HashMap<String, String> getXsHcxx(String xh) {
		return dao.getXsHcxx(xh);
	}


	/**
	 * @throws Exception  
	 * @����:�õ���ס��ѧ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����04:15:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForZzdgl(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForZzdgl(model,user);
	}
	
	/** 
	 * @����:�õ�����Ϣ���ѧ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-2 ����11:05:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXnwxjkhk(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForXnwxjkhk(model,user);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����10:25:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> transformYear_Month(String csrq){
		return dao.transformYear_Month(csrq);
	}
	
	/**
	 * 
	 * @����:��ȡ��ͥ��Ա��Ϣ(����)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:36:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtcyxxList(String xh){
		return dao.getJtcyxxList(xh);
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getFather(String xh){
		return dao.getFather(xh);
	}
	
	/**
	 * 
	 * @����:��ȡĸ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-25 ����01:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getMother(String xh){
		return dao.getMother(xh);
	}
	
	/**
	 * 
	 * @����: ��ƽ������ѧ���Ѵ���Ա����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����04:52:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForHkxx(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForHkxx(model,user);
	}
	
	/**
	 * @����: ������ѧ���У԰�ش���Ѵ�����Ա����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-8-22 ����02:15:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForXydHkwh(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForXydHkwh(model,user);
	}
	
	/**
	 * @����: ��Դ���Ѵ���Ա����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-8-22 ����02:15:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForSydHkwh(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForSydHkwh(model,user);
	}
	
	/**
	 * 
	 * @����: ��ҵ������Ա��Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-3 ����04:52:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsForByHkxx(XsxxForm model,
			User user) throws Exception {
		return dao.showStudentsForByHkxx(model,user);
	}
	
	/** 
	 * @����:����ѧ����������ҳ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-26 ����09:29:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param xn
	 * @param xq
	 * @param lxdm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForTsxsByTy(XsxxForm model,String xn,String xq,String lxdm,
			User user) throws Exception {
		return dao.showStudentsForTsxsByTy(model,xn,xq,lxdm,user);
	}
	
	/**
	 * @����: �㽭��ѧ�³�𷢷Ų�ѯ����ѧ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-20 ����05:39:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsCjffAdd(XsxxForm model) throws Exception {
		return dao.showStudentsCjffAdd(model);
	}
	
	/**
	 * @����: ����֯��ϵת��ѧ��ҳ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����08:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> showStudentsFordzzgxzc(XsxxForm model, User user)
	throws Exception {
		return dao.showStudentsFordzzgxzc(model, user);
	}
	
	/** 
	 * @����:�����Ƽ���ѧѧ����λ�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-20 ����09:58:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForXiAnKjGwSq(XsxxForm model, User user) throws Exception{
		return dao.showStudentsForXiAnKjGwSq(model, user);
	}
	
	/** 
	 * @����:��ȡ����Ա��Ϣ��ȥһ�����ݣ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-17 ����01:53:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getFdyxxByXh(String xh) {
		return dao.getFdyxxByXh(xh);
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡȫ��ѧ���б�(���һ���ר�ã����칤�̴�ѧ)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-23 ����10:54:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> showStudentsForQshr(XsxxForm model,User user) throws Exception{
		return dao.getStudentsForQshr(model,user);
	}
	
	/**
	 * @description	�� ѡ��ѧ���б��Ÿɲ���
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-16 ����10:45:43
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> showStudentsForTgb(XsxxForm model, User user) throws Exception {
		return dao.showStudentsForTgb(model,user);
	}
}
