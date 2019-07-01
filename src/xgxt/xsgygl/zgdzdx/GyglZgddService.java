package xgxt.xsgygl.zgdzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import xgxt.action.Base;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;


public class GyglZgddService extends GyglTyService {

	GyglZgddDAO dao = new GyglZgddDAO();

	/**
	 * ����ͼ��ͳ�ƽ����
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<Object> setTbtj(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ͳ����Ϣ�б�
		List<HashMap<String, String>> infoList = setTjxxList(model);

		List<Object> rs = new ArrayList<Object>();
		// У��¥����Ϣ
		List<HashMap<String, String>> xqldList = getXqldInfoList(infoList);
		// ¥��������Ϣ
		List<HashMap<String, String>> ldcsList = getLdcsInfoList(infoList);
		// ������Ϣ
		List<HashMap<String, String>> csqsList = getQsInfoList(infoList);
		// ��һ��
		if (xqldList != null && xqldList.size() > 0) {

			for (int i = 0; i < xqldList.size(); i++) {

				HashMap<String, Object> map = new HashMap<String, Object>();
				HashMap<String, String> xqld = xqldList.get(i);

				map.put("xqmc", xqld.get("xqmc"));
				map.put("ldmc", xqld.get("ldmc"));

				// �ڶ���
				List<Object> csList = new ArrayList<Object>();

				for (int j = 0; j < ldcsList.size(); j++) {
					HashMap<String, String> ldcs = ldcsList.get(j);
					if (ldcs.get("xqdm").equalsIgnoreCase(xqld.get("xqdm"))
							&& ldcs.get("lddm").equalsIgnoreCase(
									xqld.get("lddm"))) {

						HashMap<String, Object> obMap = new HashMap<String, Object>();

						// ������
						List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

						for (int k = 0; k < csqsList.size(); k++) {
							HashMap<String, String> qs = csqsList.get(k);
							if (ldcs.get("xqdm").equalsIgnoreCase(
									qs.get("xqdm"))
									&& ldcs.get("lddm").equalsIgnoreCase(
											qs.get("lddm"))
									&& ldcs.get("cs").equalsIgnoreCase(
											qs.get("cs"))) {
								qsList.add(qs);
							}
						}
						obMap.put("ldcs", ldcs);
						obMap.put("qsList", qsList);

						csList.add(obMap);

					}
				}

				map.put("csList", csList);

				rs.add(map);
			}
		}

		return rs;
	}
	
	/**
	 * ���У��¥����Ϣ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXqldInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getXqldInfoList(model);
	}
	
	/**
	 * ���У��¥����Ϣ�б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXqldInfoList(
			List<HashMap<String, String>> list) {

		List<HashMap<String, String>> xqldList = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < list.size(); i++) {

			HashMap<String, String> after_map = list.get(i);
			// У������
			String after_xqdm = after_map.get("xqdm");
			// У������
			String after_xqmc = after_map.get("xqmc");
			// ¥������
			String after_lddm = after_map.get("lddm");
			// ¥������
			String after_ldmc = after_map.get("ldmc");

			boolean flag = true;
			
			if (i > 0) {
				
				HashMap<String, String> before_map = list.get(i - 1);
				// У������
				String before_xqdm = before_map.get("xqdm");
				// У������
				String before_xqmc = before_map.get("xqmc");
				// ¥������
				String before_lddm = before_map.get("lddm");
				// ¥������
				String before_ldmc = before_map.get("ldmc");
				
				if(after_xqdm.equalsIgnoreCase(before_xqdm) &&
					after_xqmc.equalsIgnoreCase(before_xqmc) &&
					after_lddm.equalsIgnoreCase(before_lddm) &&
					after_ldmc.equalsIgnoreCase(before_ldmc) ){
					
					flag = false;
					
				}
			}
			
			if(flag){
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xqdm", after_xqdm);
				map.put("xqmc", after_xqmc);
				map.put("lddm", after_lddm);
				map.put("ldmc", after_ldmc);
				
				xqldList.add(map);
			}
		}
		
		return xqldList;
	}
	
	/**
	 * ���¥��������Ϣ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getLdcsInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getLdcsInfoList(model);
	}
	
	/**
	 * ���¥��������Ϣ�б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLdcsInfoList(
			List<HashMap<String, String>> list) {

		List<HashMap<String, String>> ldcsList = new ArrayList<HashMap<String, String>>();

		// ����Ҫͳ�Ƶ���
		String[] noTj = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {

			if (!"no".equalsIgnoreCase(noTj[i])) {
				HashMap<String, String> before_map = list.get(i);

				// У������
				String before_xqdm = before_map.get("xqdm");
				// ¥������
				String before_lddm = before_map.get("lddm");
				// ����
				String before_cs = before_map.get("cs");
				// ������Ŀ
				int fjs = 1;
				// ��λ��
				String before_cws = before_map.get("cws");
				// �մ�λ��
				String before_kcws = before_map.get("kcws");

				for (int j = i + 1; j < list.size(); j++) {

					HashMap<String, String> after_map = list.get(j);

					// У������
					String after_xqdm = after_map.get("xqdm");
					// ¥������
					String after_lddm = after_map.get("lddm");
					// ����
					String after_cs = after_map.get("cs");
					// ��λ��
					String after_cws = after_map.get("cws");
					// �մ�λ��
					String after_kcws = after_map.get("kcws");

					if (after_xqdm.equalsIgnoreCase(before_xqdm)
							&& after_lddm.equalsIgnoreCase(before_lddm)
							&& after_cs.equalsIgnoreCase(before_cs)) {

						noTj[j] = "no";

						// �ۼӷ�����
						fjs++;
						// �ۼӴ�λ��
						before_cws = String.valueOf(Integer.parseInt(before_cws)
								+ Integer.parseInt(after_cws));
						// �ۼӿմ�λ��
						before_kcws = String.valueOf(Integer
								.parseInt(before_kcws)
								+ Integer.parseInt(after_kcws));
					}
				}
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xqdm", before_xqdm);
				map.put("lddm", before_lddm);
				map.put("cs", before_cs);
				map.put("fjs", String.valueOf(fjs));
				map.put("cws", String.valueOf(before_cws));
				map.put("kcws", String.valueOf(before_kcws));

				ldcsList.add(map);
			}
		}

		return ldcsList;
	}
	
	/**
	 * ���������Ϣ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getQsInfoList(List<HashMap<String, String>> list)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		List<HashMap<String, String>> qsInfolist = new ArrayList<HashMap<String, String>>();
		
		for (HashMap<String, String> map : list) {
			
//			String xqdm = map.get("xqdm");//У������
//			String ssbh = map.get("ssbh");//������
//			String lddm = map.get("lddm");//¥������
//			String cs = map.get("cs");//����
//			String qsh = map.get("qsh");//���Һ�
			//String sxnj = map.get("sxnj");//��д�꼶
			String xymc = map.get("xymc");//ѧԺ����
			String qtxy = map.get("qtxy");//����ѧԺ
			//String color = map.get("color");//��ɫ
			
			//���ڻ�ס���
			if (!Base.isNull(qtxy) && qtxy.length() > 0) {
				
				String[] fp = Base.isNull(xymc) ? null : xymc.split("/");
				String[] qt = qtxy.split("/");

				int size_fp = (fp != null && fp.length > 0) ? fp.length : 0;
				int size_qt = (qt != null && qt.length > 0) ? qt.length : 0;
				int n = 0;
				
				String[][] arr_xynj = new String[size_fp + size_qt][2];
				
				if (fp != null && fp.length > 0) {

					for (int i = 0; i < fp.length; i++) {
						String xynj = fp[i];
						String xy = "";
						xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								0, xynj.length() - 4);
						String nj = "";
						nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								xynj.length() - 3, xynj.length() - 1);

						arr_xynj[n][0] = xy;
						arr_xynj[n][1] = nj;

						n++;
					}

					for (int i = 0; i < qt.length; i++) {

						String xynj = qt[i];
						String xy ="";
						String nj = "";
						xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								0, xynj.length() - 4);
						nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								xynj.length() - 3, xynj.length() - 1);
						
						arr_xynj[n][0] = xy;
						arr_xynj[n][1] = nj;

						n++;
					}
				} else {
					for (int i = 0; i < qt.length; i++) {
						String xynj = qt[i];
						String xy = "";
						String nj = "";
						xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								0, xynj.length() - 4);
						nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
								xynj.length() - 3, xynj.length() - 1);
						arr_xynj[i][0] = xy;
						arr_xynj[i][1] = nj;
					}
				}
				xymc = getXymc(arr_xynj);
			}
			//�����ڻ�ס���
			else{
				
				String[] fp = Base.isNull(xymc) ? null : xymc.split("/");

				int size_fp = (fp != null && fp.length > 0) ? fp.length : 0;
				int n = 0;

				String[][] arr_xynj = new String[size_fp][2];
				
				for (int i = 0; i < size_fp; i++) {
					String xynj = fp[i];
					//System.out.println(xynj);
					String xy = "";
					xy = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(0,
							xynj.length() - 4);
					String nj = "";
					nj = "()".equalsIgnoreCase(xynj) ? "" : xynj.substring(
							xynj.length() - 3, xynj.length() - 1);

					arr_xynj[n][0] = xy;
					arr_xynj[n][1] = nj;

					n++;
				}
				
				xymc = getXymc(arr_xynj);
			}
			map.put("xymc", xymc);
			qsInfolist.add(map);
		}
		
		return qsInfolist;
	}
	
	/**
	 * ���ƴ��ѧԺ
	 * 
	 * @author luojw
	 */
	public String getXymc(String[][] xynj) {
		
		StringBuffer qsxy = new StringBuffer();//����ѧԺ;
		
		if (xynj != null && xynj.length > 0) {

			String[] xy = new String[xynj.length];
			String[][] nj = new String[xynj.length][2];

			int n = 0;
			int m = 0;
			
			for (int i = 0; i < xynj.length; i++) {
				
				String xymc = Base.isNull(xynj[i][0]) ? "" : xynj[i][0];
				String sxnj = Base.isNull(xynj[i][0]) ? "" : xynj[i][1];

				boolean flag = true;
				
				for (int j = 0; j < n; j++) {
					if (xymc.equalsIgnoreCase(xy[j])) {
						flag = false;
						break;
					}
				}
				nj[m][0] = xymc;
				nj[m][1] = sxnj;
				m++;
				
				if (flag) {
					xy[n] = xymc;
					n++;
				}
			}
			
			if (xy != null && xy.length > 0) {

				for (int i = 0; i < xy.length; i++) {

					if (!Base.isNull(xy[i])) {
						if (i != 0) {
							qsxy.append("/");
						}
						qsxy.append(xy[i]);
						qsxy.append("(");
						if (nj != null && nj.length > 0) {

							StringBuffer sb_nj = new StringBuffer();// �����꼶;

							for (int j = 0; j < nj.length; j++) {
								if (!Base.isNull(nj[j][0])) {
									if (nj[j][0].equalsIgnoreCase(xy[i])) {
										if (!Base.isNull(sb_nj.toString())) {
											sb_nj.append(",");
										}
										sb_nj.append(nj[j][1]);
									}
								}
							}
							qsxy.append(sb_nj);
						}
						qsxy.append(")");
					}
				}
			}
		}
		
		return qsxy.toString();
		
	}
	
	/**
	 * ���ͳ�ƻ�����Ϣ
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjjbxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getTjjbxxList(model);
	}
	
	/**
	 * ���������Ϣ
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getQtxyxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getQtxyxxList(model);
	}
	
	/**
	 * ƴ��ͳ����Ϣ
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> setTjxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		//ͳ�ƻ�����Ϣ
		List<HashMap<String, String>> list =getTjjbxxList(model);
		List<HashMap<String, String>> tjjbxxList = new ArrayList<HashMap<String, String>>();
		//ͳ��������Ϣ
		List<HashMap<String, String>> qtxxList =getQtxyxxList(model);
		//�ϲ����ͳ����Ϣ
		List<HashMap<String, String>> infoList = new ArrayList<HashMap<String, String>>();
		
		// ����Ҫͳ�Ƶ���
		String[] noTj = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {

			if (!"no".equalsIgnoreCase(noTj[i])) {
				HashMap<String, String> before_map = list.get(i);

				String before_bxynjrs = before_map.get("bxynjrs");
				String before_cs = before_map.get("cs");
				String before_cws = before_map.get("cws");
				String before_fpbj = before_map.get("fpbj");
				String before_lddm = before_map.get("lddm");
				String before_ldmc = before_map.get("ldmc");
				String before_nj = before_map.get("nj");
				String before_qsh = before_map.get("qsh");
				String before_ssbh = before_map.get("ssbh");
				String before_wxsrs = before_map.get("wxsrs");
				String before_xqdm = before_map.get("xqdm");
				String before_xqmc = before_map.get("xqmc");
				String before_xydm = before_map.get("xydm");
				String before_xymc = before_map.get("xymc");

				for (int j = i + 1; j < list.size(); j++) {
					HashMap<String, String> after_map = list.get(j);
					String after_cs = after_map.get("cs");
					String after_lddm = after_map.get("lddm");
					String after_qsh = after_map.get("qsh");
					String after_xymc = after_map.get("xymc");
					
					if (after_qsh.equalsIgnoreCase(before_qsh)
							&& after_lddm.equalsIgnoreCase(before_lddm)
							&& after_cs.equalsIgnoreCase(before_cs)) {

						noTj[j] = "no";

						// �ۼ�ѧԺ
						before_xymc = before_xymc + "/" + after_xymc;
					}
				}
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bxynjrs", before_bxynjrs);
				map.put("cs", before_cs);
				map.put("cws", before_cws);
				map.put("fpbj", before_fpbj);
				map.put("lddm", before_lddm);
				map.put("ldmc", before_ldmc);
				map.put("nj", before_nj);
				map.put("qsh", before_qsh);
				map.put("ssbh", before_ssbh);
				map.put("wxsrs", before_wxsrs);
				map.put("xqdm", before_xqdm);
				map.put("xqmc", before_xqmc);
				map.put("xydm", before_xydm);
				map.put("xymc", before_xymc);

				tjjbxxList.add(map);
			}
		}
		
		for (HashMap<String, String> jbMap : tjjbxxList) {
			
			String xqdm = jbMap.get("xqdm");
			String lddm = jbMap.get("lddm");
			String cs = jbMap.get("cs");
			String qsh = jbMap.get("qsh");
			String cws = jbMap.get("cws");
			String bxynjrs = jbMap.get("bxynjrs");
			String wxsrs = jbMap.get("wxsrs");
			String fpbj = jbMap.get("fpbj");
			
			//����ѧԺ����
			String qtxynjrs = "0";
			
			//����ѧԺ����
			String qtxy = "";
			
			for (HashMap<String, String> qtMap : qtxxList) {
				
				String qt_xqdm = qtMap.get("xqdm");
				String qt_lddm = qtMap.get("lddm");
				String qt_cs = qtMap.get("cs");
				String qt_qsh = qtMap.get("qsh");
				String qt_yzrs = qtMap.get("yzrs");
				String qt_xymc = qtMap.get("xymc");
					
				//ͬ����
				if(xqdm.equalsIgnoreCase(qt_xqdm) &&
						lddm.equalsIgnoreCase(qt_lddm) &&
						cs.equalsIgnoreCase(qt_cs) &&
						qsh.equalsIgnoreCase(qt_qsh)){
					
					// ��ס������
					qtxynjrs = String.valueOf(Integer.parseInt(qtxynjrs)
							+ Integer.parseInt(qt_yzrs));
					
					// ����ѧԺ
					qtxy += qt_xymc+"/";
				}
				
				//��ͬ����
				if ((!xqdm.equalsIgnoreCase(qt_xqdm)
						|| !lddm.equalsIgnoreCase(qt_lddm)
						|| !cs.equalsIgnoreCase(qt_cs)
						|| !qsh.equalsIgnoreCase(qt_qsh))
						&& !"0".equalsIgnoreCase(qtxynjrs)) {
					break;
				}
			}
			
			qtxy = Base.isNull(qtxy) ? "" : qtxy
					.substring(0, qtxy.length() - 1);
			
			//�մ�λ
			String kcws = String.valueOf(Integer.parseInt(cws)
					- Integer.parseInt(qtxynjrs) - Integer.parseInt(bxynjrs)
					- Integer.parseInt(wxsrs));
			
			//��ɫ
			String color = "";
			
			if (Integer.parseInt(kcws) < 0) {// �쳣��������
				color = "blue";
			} else if ("����".equalsIgnoreCase(fpbj)) {// �Ƿ���
				color = "green";
			} else if (Integer.parseInt(wxsrs) > 0) {// �Ƿ������У��
				color = "#800080";
			} else if ("0".equalsIgnoreCase(qtxynjrs)
					&& "0".equalsIgnoreCase(bxynjrs)
					&& "0".equalsIgnoreCase(wxsrs)) {// �Ƿ������
				color = "red";
			} else if (Integer.parseInt(kcws) > 0) {// �Ƿ��������
				color = "orange";
			} else{
				color = "black";
			}
			
			jbMap.put("qtxynjrs", qtxynjrs);
			jbMap.put("qtxy", qtxy);
			jbMap.put("kcws", kcws);
			jbMap.put("color", color);
			
			infoList.add(jbMap);
		}
		
		return infoList;
	}
}
