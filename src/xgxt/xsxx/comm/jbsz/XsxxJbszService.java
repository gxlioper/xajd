package xgxt.xsxx.comm.jbsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.MakeQuery;
import xgxt.xsxx.comm.XsxxCommService;
import xgxt.xtwh.xtwhOther.DmwhSelectMethod;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_Service��
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

public class XsxxJbszService extends XsxxCommService {

	XsxxJbszDAO dao = new XsxxJbszDAO();

	// ====================�ֶ�����=============================
	
	/**
	 * ����ֶ����ò�ѯ����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZdszRsList(XsxxJbszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "search_sjy", "search_xgwz",
				"search_lrxz", "search_wkxz", "search_lrxs", "search_sfqy" };
		String[] queryLikeList = new String[] { "search_zdm", "search_ymxs",
				"search_xsmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_xsxx_zdszb";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * �����ֶ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveZdsz(XsxxJbszForm model, User user,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_xsxx_zdszb";
		// ����
		String pk = "zd";
		// ����ֵ
		String[] pkValue = model.getZd();
		// �����ֶ�
		String[] arrzd = new String[] { "zd", "sjy", "xgwz", "zdm", "xsmc",
				"lrxz", "wkxz", "lrxs", "lyb", "sfqy" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);

		return saveInfoToDb(saveForm, model, user);

	}

	/**
	 * ��������ֶ��б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQyzdList(XsxxJbszForm model) {

		String tableName = "xg_view_xsxx_zdszb";
		StringBuilder query = new StringBuilder();
		query.append(" where 1 = 1 ");
		query.append(" and search_sfqy = ? ");
		query.append(" and not exists(select 1 from ");
		query.append(" xg_xsxx_xsqwzb b where ");
		query.append(" xg_view_xsxx_zdszb.search_zd = b.zd) ");

		String[] inPutList = new String[] { "��" };
		String[] colList = new String[] { "search_zd", "search_ymxs" };
		String sql = "";
		return getRsList(tableName, query.toString(), inPutList, colList, sql);
	}

	/**
	 * ����ֶ������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getZdszRsList() {

		// ��
		String tableName = "xg_view_xsxx_zdszb";
		// ���ֵ
		String[] colList = new String[] { "search_zd", "search_sjy",
				"search_xgwz", "search_ymxs", "search_lyb", "search_sfqy",
				"dm", "mc", "lydmwh" };

		return getRsList(tableName, "", new String[] {}, colList, "");
	}

	/**
	 * �����ֶ����ô�������ͼ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean createNewView(XsxxJbszForm model, User user)
			throws Exception {

		boolean flag = false;

		// �ֶ��б�
		List<HashMap<String, String>> zdList = getZdszRsList();

		if (zdList != null && zdList.size() > 0) {

			// �ֶ�
			String[] zd = new String[zdList.size()];
			// ����Դ
			String[] sjy = new String[zdList.size()];
			// ѧ��Ϊ׼
			String[] xgwz = new String[zdList.size()];
			// ҳ����ʾ
			String[] ymxs = new String[zdList.size()];
			// ��Դ��
			String[] lyb = new String[zdList.size()];
			// �Ƿ�����
			String[] sfqy = new String[zdList.size()];
			// ��Դ�����
			String[] dm = new String[zdList.size()];
			// ��Դ������
			String[] mc = new String[zdList.size()];
			// ��Դ����ά��
			String[] lydmwh = new String[zdList.size()];

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				zd[i] = map.get("search_zd");
				// ����Դ
				sjy[i] = map.get("search_sjy");
				// ѧ��Ϊ׼
				xgwz[i] = map.get("search_xgwz");
				// ҳ����ʾ
				ymxs[i] = map.get("search_ymxs");
				// ��Դ��
				lyb[i] = map.get("search_lyb");
				// �Ƿ�����
				sfqy[i] = map.get("search_sfqy");
				// ��Դ�����
				dm[i] = map.get("dm");
				// ��Դ������
				mc[i] = map.get("mc");
				// ��Դ����ά��
				lydmwh[i] = map.get("lydmwh");
			}

			// ������ѧ����Ϣ���ֶ�
			String[] bksxx_zd = getTableZd("bks_xsjbxx");

			// ������ѧ����Ϣ���ֶΣ����������ñ��е��ֶΣ�
			String[] output_zd = getRepeatArrValue(zd, bksxx_zd);

			if (zd != null && zd.length > 0) {

				// ����ͼ�����
				StringBuilder createSql = new StringBuilder();
				createSql.append("create or replace ");
				createSql.append("view xg_view_xsxxb as ");
				createSql.append("select ");

				// ѧ��Ϊ׼���ж�
				StringBuilder xgwzSql = new StringBuilder();
				xgwzSql.append("select ");

				// ������ѧ��������Ϣ
				StringBuilder bksSql = new StringBuilder();
				bksSql.append("select ");

				// ѧ����Ϣ
				StringBuilder stuSql = new StringBuilder();
				stuSql.append("union all select ");

				// ��ͼ��ע
				ArrayList<String> commentList = new ArrayList<String>();

				for (int i = 0; i < zd.length; i++) {
	
					createSql.append(getEspecialZd(zd[i], lyb[i], dm[i], mc[i],
							lydmwh[i]));

					// �Ƿ�ѧ��Ϊ׼
					if ("��".equalsIgnoreCase(xgwz[i])) {
						xgwzSql.append("b.");
					} else {
						xgwzSql.append("c.");
					}
					xgwzSql.append(zd[i]);

					// �ֶ��ڱ�����ѧ����Ϣ�����Ƿ����
					if (isExistInArr(output_zd, zd[i])) {
						bksSql.append(getEspecialGs(zd[i]));
					} else {
						bksSql.append("'' " + zd[i]);
					}

					stuSql.append(zd[i]);

					if (i != zd.length - 1) {
						createSql.append(",");
						xgwzSql.append(",");
						bksSql.append(",");
						stuSql.append(",");
					}
					
					//��ͼ��ע
					getViewComment(commentList,zd[i], ymxs[i], lyb[i], lydmwh[i]);
				}

				bksSql.append(" from bks_xsjbxx a ");
				bksSql.append(" where not exists (select 1 from ");
				bksSql.append(" xsxxb b where a.xh = b.xh) ");

				stuSql.append(" from xsxxb a ");

				createSql.append(" from (");
				createSql.append(xgwzSql);
				createSql.append(" from (");
				createSql.append(bksSql);
				createSql.append(stuSql);
				createSql.append(") a ");
				createSql.append("left join xsxxb b on a.xh = b.xh ");
				createSql.append("left join bks_xsjbxx c on a.xh = c.xh ");
				createSql.append(") a where a.xh is not null");

				DAO dao = DAO.getInstance();

				// ִ�н���ͼ���
				flag = dao.creatView(createSql.toString(), new String[]{});
				
				if (flag) {
					//ִ����ͼ��ע
					if(commentList!=null && commentList.size()>0){
						for(int i=0;i<commentList.size();i++){
							dao.runUpdateTab(commentList.get(i));
						}
					}
				}
				//System.out.println(createSql);
				//System.out.println(commentSql);
			}
		}
		return flag;

	}

	/**
	 * ��������ֶ�(��ʽ)
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	private String getEspecialGs(String zd) {

		StringBuilder returnZd = new StringBuilder();
		// �꼶
		if ("nj".equalsIgnoreCase(zd)) {
			returnZd.append("to_char(nj) nj");
		}
		// ѧ��
		else if ("xz".equalsIgnoreCase(zd)) {
			returnZd.append("substr(xz, 0, 1) xz");
		}
		// ����
		else {
			returnZd.append(zd);
		}

		return returnZd.toString();
	}

	/**
	 * ��������ֶ�(ȡֵ)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	private String getEspecialZd(String zd, String lyb, String dm, String mc,
			String lydmwh) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String zddm = "";
		String zdmc = "";

		if (zd.length() > 1) {
			zddm = zd.substring(zd.length() - 2, zd.length());
		}

		if ("dm".equalsIgnoreCase(zddm)) {
			zdmc = zd.substring(0, zd.length() - 2) + "mc";
		} else {
			zdmc = zd + "mc";
		}

		StringBuilder returnZd = new StringBuilder();
		
		// �Ա�
		if ("xb".equalsIgnoreCase(zd)) {

			// �������ļ���ȡ��Ů������
			String man = XMLReader.getFlowControl("comm", "��");
			String woman = XMLReader.getFlowControl("comm", "Ů");

			returnZd.append("decode(a.xb,'" + man + "','��'");
			returnZd.append(",'" + woman + "','Ů',xb) xb");
		}
		// �꼶
		else if ("nj".equalsIgnoreCase(zd)) {
			returnZd.append("a." + zd + " dqszj,");
			returnZd.append("a." + zd);
		}
		// ѧԺ
		else if ("xydm".equalsIgnoreCase(zd)) {
			returnZd.append("(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xymc,");
			returnZd.append("(select distinct b.xymc from view_njxyzybj_all b where a.xydm = b.xydm) xy,");
			returnZd.append("a.xydm bmdm,");
			returnZd.append("a.xydm");
		}
		// רҵ
		else if ("zydm".equalsIgnoreCase(zd)) {
			returnZd.append("(select distinct b.zymc from view_njxyzybj_all b where a.zydm = b.zydm) zymc,");
			returnZd.append("(select distinct b.zymc from view_njxyzybj_all b where a.zydm = b.zydm) zy,");
			returnZd.append("a.zydm");
		}
		//�༶
		else if ("bjdm".equalsIgnoreCase(zd)) {
			returnZd.append("(select distinct b.bjmc from view_njxyzybj_all b where a.bjdm = b.bjdm) bjmc,");
			returnZd.append("(select distinct b.bjmc from view_njxyzybj_all b where a.bjdm = b.bjdm) xzb,");
			returnZd.append("a.bjdm");
		}
		// ��Դ��,���ᣬ�������ڵ�
		else if ("syd".equalsIgnoreCase(zd)||"jg".equalsIgnoreCase(zd)||"hkszd".equalsIgnoreCase(zd)) {
			
			//��Դ����
			if ("syd".equalsIgnoreCase(zd)){
				returnZd.append("a." + zd + " lydq,");
				returnZd.append("(case when length(a.syd) > 6 then substr(a.syd, 0, 6) else '' end) syds,");
				returnZd.append("(select b.sydq from dmk_sydq b where b.sydqdm = substr(a.syd, 0, 6)) sydsmc,");
			}
			
			// ��Դ��ǿգ��еط�ά����
			if (!Base.isNull(lyb)) {
				
				returnZd.append("a." + zd + ",");

				returnZd.append("(select b.sydq from dmk_sydq b where b.sydqdm = substr(a." + zd + ", 0, 6))||");
				returnZd.append("(select b.qxmc from dmk_qx b where b.qxdm = substr(a." + zd + ", 8, 6))||");
				returnZd.append("(select b.qxmc from dmk_qx b where b.qxdm = substr(a." + zd + ", 15, 6))"
								+ zdmc);

			} else {
				returnZd.append("a." + zd);
			}
		}
		// ����
		else {
			// ������Դ�ڴ���ά��(������)
			if ("yes".equalsIgnoreCase(lydmwh)) {
				// ������
				String selmethod = lyb;
				ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) DmwhSelectMethod.class
						.getMethod(selmethod, (Class[]) null).invoke(null,
								(Object[]) null);

				if (list != null && list.size() > 0) {

					returnZd.append("a." + zd + ",");
					returnZd.append(" case ");

					for (int i = 0; i < list.size(); i++) {

						HashMap<String, String> map = list.get(i);
						zddm = map.get("dm");
						String zd_mc = map.get("mc");

						returnZd.append(" when a." + zd + " = '" + zddm + "'");
						returnZd.append(" then '" + zd_mc+"'");
					}
					returnZd.append(" else a." + zd + " end " + zdmc);
				}
			} else {
				// ��Դ��ǿգ��еط�ά����
				if (!Base.isNull(lyb)) {

					returnZd.append("a." + zd + ",");
					returnZd.append("(select distinct b." + mc + " from ");
					returnZd.append(lyb + " b where a." + zd);
					returnZd.append("= b." + dm + " and rownum = 1)  " + zdmc);
				} else {
					returnZd.append("a." + zd);
				}
			}
		}

		return returnZd.toString();
	}

	/**
	 * �����ͼ��ע
	 * 
	 * @author ΰ�����
	 */
	private void getViewComment(List<String> commentList,String zd, String ymxs, String lyb,
			String lydmwh) {

		String zddm = "";
		String zdmc = "";

		if (zd.length() > 1) {
			zddm = zd.substring(zd.length() - 2, zd.length());
		}

		if ("dm".equalsIgnoreCase(zddm)) {
			zdmc = zd.substring(0, zd.length() - 2) + "mc";
		} else {
			zdmc = zd + "mc";
		}

		StringBuilder comment = new StringBuilder();
		comment.append("comment on column xg_view_xsxxb.");
		comment.append(zd);
		comment.append(" is '" + ymxs + "'");

		commentList.add(comment.toString());
		
		comment = new StringBuilder();

		// �Ա�
		if ("xb".equalsIgnoreCase(zd)) {

			comment.append("comment on column xg_view_xsxxb.xb");
			comment.append(" is '�Ա�'");

			commentList.add(comment.toString());
		}
		// �꼶
		else if ("nj".equalsIgnoreCase(zd)) {
			comment.append("comment on column xg_view_xsxxb.nj");
			comment.append(" is '�꼶'");

			commentList.add(comment.toString());
		}
		// ѧԺ
		else if ("xydm".equalsIgnoreCase(zd)) {
			comment.append("comment on column xg_view_xsxxb.xy");
			comment.append(" is '"+Base.YXPZXY_KEY+"'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.xydm");
			comment.append(" is '"+Base.YXPZXY_KEY+"����'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.xymc");
			comment.append(" is '"+Base.YXPZXY_KEY+"����'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.bmdm");
			comment.append(" is '���Ŵ���'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
		}
		// רҵ
		else if ("zydm".equalsIgnoreCase(zd)) {

			comment.append("comment on column xg_view_xsxxb.zydm");
			comment.append(" is 'רҵ����'");

			commentList.add(comment.toString());

			comment = new StringBuilder();

			comment.append("comment on column xg_view_xsxxb.zymc");
			comment.append(" is 'רҵ����'");

			commentList.add(comment.toString());

			comment = new StringBuilder();
		}
		// �༶
		else if ("bjdm".equalsIgnoreCase(zd)) {
			
			comment.append("comment on column xg_view_xsxxb.bjdm");
			comment.append(" is '�༶����'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.bjmc");
			comment.append(" is '�༶����'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
			
			comment.append("comment on column xg_view_xsxxb.xzb");
			comment.append(" is '������'");

			commentList.add(comment.toString());
			
			comment = new StringBuilder();
		}
		//��Դ��,���ᣬ�������ڵ�
		else if ("syd".equalsIgnoreCase(zd)||"jg".equalsIgnoreCase(zd)||"hkszd".equalsIgnoreCase(zd)) {
			
			if ("syd".equalsIgnoreCase(zd)){
				
				comment.append("comment on column xg_view_xsxxb.lydq is '��Դ����'");
				commentList.add(comment.toString());
				
				comment = new StringBuilder();
				
				comment.append("comment on column xg_view_xsxxb.syds is '��Դ��ʡ'");
				commentList.add(comment.toString());
				
				comment = new StringBuilder();
				comment.append("comment on column xg_view_xsxxb.sydsmc is '��Դ��ʡ����'");
				commentList.add(comment.toString());
				
				comment = new StringBuilder();
			}
			
			// ��Դ��ǿգ��еط�ά����
			if (!Base.isNull(lyb)) {
				comment.append("comment on column xg_view_xsxxb.");
				comment.append(zdmc);
				comment.append(" is '��Դ��'");

				commentList.add(comment.toString());
			}
		}
		// ����
		else {
			// ������Դ�ڴ���ά��(������)
			if ("yes".equalsIgnoreCase(lydmwh)) {
				comment.append("comment on column xg_view_xsxxb.");
				comment.append(zdmc);
				comment.append(" is '" + ymxs + "����'");
				
				commentList.add(comment.toString());
			} else {
				// ��Դ��ǿգ��еط�ά����
				if (!Base.isNull(lyb)) {
					comment.append("comment on column xg_view_xsxxb.");
					comment.append(zdmc);
					comment.append(" is '" + ymxs + "����'");
					
					commentList.add(comment.toString());
				}
			}
		}
	}

	/**
	 * ��֤�ֶ����ÿɷ񱣴�
	 * 
	 * @author ΰ�����
	 */
	public String checkSaveZdsz(XsxxJbszForm model) {
		
		//�����Ӧ�������ֶ��б������ֶΣ�
		List<HashMap<String, String>> list = dao.getYfpZdList(model);
		
		String message = "";
		
		if(list!=null && list.size()>0){
			
			message = "�����ֶ��Ѿ���Ӧ����ѧ����Ϣ��\n��������Ϊ�����ã�\n";
			message+="(";
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String xsmc = map.get("xsmc");
				message += (i+1)+":"+xsmc;
				if (i != list.size() - 1) {
					message += ",";
				}
				
				if(i!=0&&i%4==0){
					message += "\n";
				}
			}
			message+=")";
		}
		return message;
	}
	
	// ====================�ֶ����� end=============================

	// ====================��ʾ������=============================
	/**
	 * �����ʾ����ѯ����б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsqRsList(XsxxJbszForm model, User user,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xsqsfqy", "xsqsfzd", "xsqsfzk" };
		String[] queryLikeList = new String[] { "search_xsqmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// ��
		String tableName = "xg_view_xsxx_xsqsz";
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by to_number(xsqxssx) ");

		// ����ֵ
		String[] inPutList = myQuery.getInputList();

		return getRsArrList(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * ������ʾ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsqsz(XsxxJbszForm model, User user) throws Exception {

		return dao.saveXsqsz(model, user);

	}

	/**
	 * ����ҳ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveYmsz(XsxxJbszForm model, User user) throws Exception {

		// �����ʾ��
		boolean flag = delXsqInfo(user);

		String[] xsqdm = model.getXsqdm();

		// ������ʾ��
		if (flag) {
			flag = saveXsq(model, user);
			model.setXsqdm(xsqdm);
		}

		// ������ʾ���ϲ���
		if (flag) {
			flag = saveXsqHbh(model, user);
			model.setXsqdm(xsqdm);
		}

		// ������ʾ���ֶ�λ��
		if (flag) {
			flag = saveXsqZdwz(model, user);
			model.setXsqdm(xsqdm);
		}
		return flag;
	}

	/**
	 * �����ʾ����Ϣ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean delXsqInfo(User user) throws Exception {

		// �����ʾ��
		boolean flag = dao.delXsq();

		// ��պϲ���
		if (flag) {
			flag = dao.delHbh();
		}

		// ����ֶ�λ��
		if (flag) {
			flag = dao.delZdwz();
		}

		return flag;
	}
	
	/**
	 * ������ʾ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsq(XsxxJbszForm model, User user) throws Exception {

		boolean flag = true;

		if (model.getXsqdm() != null && model.getXsqdm().length > 0) {
			// �����
			String tableName = "xg_xsxx_xsqszb";
			// ����
			String pk = "xsqdm";
			// ����ֵ
			String[] pkValue = model.getXsqdm();
			// �����ֶ�
			String[] arrzd = new String[] { "xsqdm", "xsqmc", "szhs", "zpxs",
					"zpwz", "zpszhs", "xssx" };

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);

			flag = saveInfoToDb(saveForm, model, user);
		}
		return flag;
	}

	/**
	 * ������ʾ���ϲ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsqHbh(XsxxJbszForm model, User user) throws Exception {

		boolean flag = true;

		if (model.getXsqdm() != null && model.getXsqdm().length > 0) {
			// �����
			String tableName = "xg_xsxx_xsqhbb";
			// ����
			String pk = "xsqdm";
			// ����ֵ
			String[] pkValue = model.getXsqdm();
			
			// �����ֶ�
			String[] arrzd = new String[] { "xsqdm", "hbh" };

			model.setXsqdm(model.getHbh_szxsq());

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);

			flag = saveInfoToDb(saveForm, model, user);
		}
		return flag;

	}

	/**
	 * ������ʾ���ֶ�λ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsqZdwz(XsxxJbszForm model, User user) throws Exception {

		boolean flag = true;

		if (model.getXsqdm() != null && model.getXsqdm().length > 0) {
			// �����
			String tableName = "xg_xsxx_xsqwzb";
			// ����
			String pk = "xsqdm";
			// ����ֵ
			String[] pkValue = model.getXsqdm();

			// �����ֶ�
			String[] arrzd = new String[] { "xsqdm", "zd", "szh", "szl" };

			model.setXsqdm(model.getZd_szxsq());

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);

			flag = saveInfoToDb(saveForm, model, user);
		}
		return flag;

	}

	/**
	 * ��������ֶ��б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsqList(XsxxJbszForm model) {

		String tableName = "xg_view_xsxx_xsqsz";
		StringBuilder query = new StringBuilder();
		ArrayList<String> inPutList = new ArrayList<String>();

		// ��ʾ���Ƿ�����
		if (!Base.isNull(model.getXsqsfqy())) {
			query.append(" where xsqsfqy=? ");
			inPutList.add(model.getXsqsfqy());
		}

		query.append(" order by to_number(xsqxssx) ");

		String[] colList = new String[] { "xsqdm", "xsqmc", "szhs", "zpxs",
				"zpwz", "zpszhs", "xssx", "sfzd" ,"sfzk"};

		String sql = "";

		return getRsList(tableName, query.toString(), inPutList
				.toArray(new String[] {}), colList, sql);
	}

	/**
	 * �����ʾ�����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, Object>> getXsqInfoList(XsxxJbszForm model) {

		// ��ʾ���б�
		List<HashMap<String, String>> xsqList = getXsqList(model);
		// ��ʾ���б�
		List<HashMap<String, String>> hbhList = getXsqhbhList(model);
		// �ֶ�λ���б�
		List<HashMap<String, String>> zdwzList = getXsqZdwzList(model);
		// ��ʾ���б�
		List<HashMap<String, Object>> rsList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < xsqList.size(); i++) {

			HashMap<String, String> xsqMap = xsqList.get(i);
			// ��ʾ������
			String xsqdm = xsqMap.get("xsqdm");
			// ��ʾ������
			String xsqmc = xsqMap.get("xsqmc");
			// ��ռ����
			String szhs = xsqMap.get("szhs");
			// ��Ƭ��ʾ
			String zpxs = xsqMap.get("zpxs");
			// ��Ƭλ��
			String zpwz = xsqMap.get("zpwz");
			// ��Ƭ��ռ��
			String zpszhs = xsqMap.get("zpszhs");
			// ��ʾ˳��
			String xssx = xsqMap.get("xssx");

			HashMap<String, Object> map = new HashMap<String, Object>();

			// ���б�
			List<HashMap<String, Object>> rowList = new ArrayList<HashMap<String, Object>>();

			for (int j = 1; j <= Integer.parseInt(szhs); j++) {

				HashMap<String, Object> row = new HashMap<String, Object>();

				// ������
				row.put("szh", String.valueOf(j));

				// ��Ҫ��Ƭ��ʾ�Ҳ��ǵ�һ��
				if ("��".equalsIgnoreCase(zpxs) && j <= Integer.parseInt(zpszhs)) {
					if (j == 1) {
						// �Ƿ�����
						row.put("isFirst", "yes");
					}
					row.put("isZp", "yes");
				} else {
					row.put("isZp", "no");
				}

				// ===================�ϲ�����===========================
				// ��Ҫ�ϲ�
				boolean isHb = false;

				for (int k = 0; k < hbhList.size(); k++) {

					HashMap<String, String> hbhMap = hbhList.get(k);
					// ������ʾ��
					String szxsq = hbhMap.get("xsqdm");
					// ������
					String hbh = hbhMap.get("hbh");

					if (xsqdm.equalsIgnoreCase(szxsq)
							&& hbh.equalsIgnoreCase(String.valueOf(j))) {
						isHb = true;
						break;
					}
				}

				if (isHb) {
					row.put("isHb", "yes");
				} else {
					row.put("isHb", "no");
				}
				// ===================�ϲ�����end===========================

				// ===================�ֶ�λ��===========================

				// �ֶ�λ��
				HashMap<String, String> zdMap = new HashMap<String, String>();
				zdMap.put("left", "");
				zdMap.put("right", "");

				for (int k = 0; k < zdwzList.size(); k++) {
					HashMap<String, String> zdwzMap = zdwzList.get(k);
					// ������ʾ��
					String szxsq = zdwzMap.get("xsqdm");
					// ������
					String szh = zdwzMap.get("zdszh");

					if (xsqdm.equalsIgnoreCase(szxsq)
							&& szh.equalsIgnoreCase(String.valueOf(j))) {

						// �ֶ�
						String zd = zdwzMap.get("zd");
						// �ֶ���
						String zdm = zdwzMap.get("zdm");
						// ������
						String szl = zdwzMap.get("zdszl");
						szl = "1".equalsIgnoreCase(szl) ? "left" : "right";

						zdMap.put(szl + "zd", zd);
						zdMap.put(szl + "zdm", zdm);
						zdMap.put(szl, "yes");
					}
				}

				row.put("zdMap", zdMap);

				// ===================�ֶ�λ�� end===========================

				rowList.add(row);
			}

			map.put("xsqdm", xsqdm);
			map.put("xsqmc", xsqmc);
			map.put("szhs", szhs);
			map.put("zpxs", zpxs);
			map.put("zpwz", zpwz);
			map.put("zpszhs", zpszhs);
			map.put("xssx", xssx);
			map.put("rowList", rowList);

			rsList.add(map);
		}

		return rsList;
	}

	/**
	 * ��������ʾ������
	 * 
	 * @author ΰ�����
	 */
	public String getMaxXsqdm() {
		return dao.getMaxXsqdm();
	}

	/**
	 * ��úϲ�����Ϣ
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXsqhbhList(XsxxJbszForm model) {

		String tableName = "xg_xsxx_xsqhbb";
		StringBuilder query = new StringBuilder();
		ArrayList<String> inPutList = new ArrayList<String>();

		String[] colList = new String[] { "xsqdm", "hbh" };
		String sql = "";

		return getRsList(tableName, query.toString(), inPutList
				.toArray(new String[] {}), colList, sql);
	}

	/**
	 * �����ʾ���ֶ�λ����Ϣ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsqZdwzList(XsxxJbszForm model) {

		String tableName = "xg_view_xsxx_xsqzd";
		StringBuilder query = new StringBuilder();
		if (!Base.isNull(model.getSearch_sfqy())) {
			query.append(" where sfqy='" + model.getSearch_sfqy() + "' ");
		}

		query.append(" order by xsqdm,zdszh,zdszl ");

		ArrayList<String> inPutList = new ArrayList<String>();

		String[] colList = new String[] { "xsqdm", "zd", "zdm", "zdszh","xgwz",
				"zdszl", "lrxz", "wkxz", "lrxs", "sfqy", "sfzd", "sfzk" };
		String sql = "";

		return getRsList(tableName, query.toString(), inPutList
				.toArray(new String[] {}), colList, sql);
	}

	// ====================��ʾ������ end=============================

}