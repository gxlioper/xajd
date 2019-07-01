package xsgzgl.xsxx.gdjgxy.xxxg.xgsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;


/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸Č���_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XgshService extends xsgzgl.xsxx.general.xxxg.xgsh.XgshService 
 {
	XgshDAO dao = new XgshDAO();
	/**
	 * ��ÌW���޸���Ϣ
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public HashMap<String, String> getXgshInfo(XgshModel model, User user) {

		// �W����Ϣ���ֶ�
		String[] xsxxb = { "xh", "xm", "xb", "csrq", "nj", "xz", "xymc",
				"zzmmmc", "zymc", "mzmc", "bjmc", "xjztm", "rxrq", "sfzh",
				"jgmc", "hkszdmc", "sydmc", "sjhm", "dzyx", "qqhm", "jtdh",
				"jtyb", "jtszd", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz",
				"jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx",
				"jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1", "jtcy3_xm",
				"jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2", "jtcy3_lxdh1", "xmpy",
				"cym", "sg", "tz", "tc", "jkzk", "pyccmc", "sfzd", "kslbmc",
				"rxfsmc", "pyfsmc", "bz" ,"zw","zd1","zd2","grjl"};

		// ��ՈID
		String sqid = model.getSqid();

		List<HashMap<String, String>> list = dao.getXgxxList(sqid, xsxxb);

		HashMap<String, String> map = new HashMap<String, String>();
		// �W����Ϣ
		HashMap<String, String> xsxx = list.get(0);
		// �޸���Ϣ
		HashMap<String, String> xgxx = list.get(1);

		for (int i = 0; i < xsxxb.length; i++) {

			String zd = xsxxb[i];
			String xsxx_zd = xsxx.get(zd);
			String xgxx_zd = xgxx.get(zd);
			
			if(zd.equalsIgnoreCase("jtyb")){
				int a=0;
				a++;
			}
			if (Base.isNull(xgxx_zd) || Base.isNull(xgxx_zd.trim())) {
				map.put(zd, xsxx_zd);
			} else if (!Base.isNull(xgxx_zd)) {
				//���иĳ���  ��ʽΪ "strLiTT"����LiTT��β
				
				if(xgxx_zd.endsWith("LiTT")){
					xgxx_zd = xgxx_zd+"��";
				}
				String[] value = xgxx_zd.split("LiTT");
				if(value.length==1){
					map.put(zd, xgxx_zd);
				} else {		
					if("null".equalsIgnoreCase(value[0])){
						value[0]="��";
					}
					String msg = "�޸�ǰ��ϢΪ��" + value[0];
					StringBuilder html = new StringBuilder();
					html.append("<a ");
					html.append("title=\""+msg+"\"");
					html.append(">");
					html.append("<font color=\"red\">");
					if("null".equalsIgnoreCase(value[1])){
						html.append("��");
					}else{
						html.append(value[1]);
					}
					html.append("</font>");
					html.append("</a>");
					
					map.put(zd, html.toString());
				}
			}
		}

		return map;
	}
	
	/**
	 * �ύ�W���޸���Ϣ
	 * 
	 * @date 2013-01-24
	 * @author ΰ�����
	 */
	public void submitXxxg(String[] sqid, User user) {

		// �W����Ϣ���ֶ�
		String[] xsxxb = { "xm", "xb", "csrq",  "xz", "zzmm", 
				 "mz",  "xjztm", "rxrq", "sfzh", "jg", "hkszd",
				"syd", "sjhm", "dzyx", "qqhm", "jtyb", "xmpy", "cym", "sg",
				"tz", "tc", "jkzk", "pycc", "sfzd", "kslb", "rxfs", "bz","zw","grjl","zd1","zd2","pyfs","jtdh" };

		// �W���o����Ϣ���ֶ�
		String[] xsfzxxb = { "lxdh1", "jtszd", "jtcy1_xm", "jtcy1_gx",
				"jtcy1_gzdz", "jtcy1_lxdh2", "jtcy1_lxdh1", "jtcy2_xm",
				"jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh2", "jtcy2_lxdh1",
				"jtcy3_xm", "jtcy3_gx", "jtcy3_gzdz", "jtcy3_lxdh2",
				"jtcy3_lxdh1" };
			
		try {
			//�ύ���W����Ϣ��
			dao.updateXsxxb(sqid, xsxxb);
			//�ύ���W���o����Ϣ��
			dao.updateXsfzxxb(sqid, xsfzxxb);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
 }