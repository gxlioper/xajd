/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����04:44:40 
 */
package com.zfsoft.xgxt.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;

import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �̶�ѡ��ͳһ����
 * @���ߣ� Penghui.Qu
 * @ʱ�䣺 2013-4-18 ����04:44:40
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class OptionUtil {

	public final static String ISNOT = "isNot";// �Ƿ�
	public final static String HAVENOT = "haveNot";// ����
	public final static String SHZT = "shzt";// ���״̬
	public final static String ONOFF = "on-off";// �������ر�
	public final static String FMJZ = "fmjz";// ��ĸ����
	public final static String PKXJB = "pkxjb";// ƶ���ؼ���.
	public final static String PKLX = "pklx";// ƶ������
	public final static String PKDJ = "pkdj";// ƶ���ȼ�
	public final static String SFKN = "sfkn";// �Ƿ�����
	public final static String KNDJ = "kndj";// ���ѵȼ�
	public final static String PYJG = "pyjg";// ������
	public final static String JTHKXZ = "jthkxz";// ��ͥ��������
	public final static String YXSSZ = "yxssz";// ��Чʱ����
	public final static String GSLX = "gslx";// ��˾����
	public final static String FYLX = "fylx";// ��������
	public final static String XMNZT = "xmnzt";// ��Ŀ��״̬
	//������ҵ��ѧ���Ի���ʼ	 
	public final static String FMJZQK = "fmjzqk"; //��ĸ�������
	public final static String JRJKZK = "jrjkzk"; //���˽���״��
	public final static String BRJKZK = "brjkzk"; //���˽���״��
	public final static String JTZNS = "jtzns"; //��ͥ��Ů��
	public final static String ZDZNS = "zdzns"; //���Լ��ڶ�����Ů��
	public final static String JTSYS = "jtsys"; //��ͥ������
	public final static String JTSRQK = "jtsrqk"; //��ͥ������� 
	//������ҵ��ѧ���Ի�����	
	//����ְҵ����ѧУ���Ի���ʼ
	public final static String XSLB = "xslb"; //ѧ�����
	//����ְҵ����ѧУ���Ի�����	
	//�Ϻ����ѧԺ���Ի���ʼ	 
	public final static String DKQK = "dkqk"; //�������
	public final static String JTCS = "jtcs"; //��ͥ����
	public final static String JTSRLY = "jtsrly"; //������Դ
	public final static String JQLX = "jqlx"; //��������
	//�Ϻ����ѧԺ���Ի�����
	//������ũ��ְҵѧԺ ���Ի���ʼ	 
	public final static String DBHTKZ = "dbhtkz"; //�ͱ�������֤
	public final static String TTXDJDXXQK = "ttxdjdxxqk"; //̥ͬ�ֵܾͶ�ѧУ���
	public final static String FMSFJK = "fmsfjk"; //��ĸ�Ƿ񽡿�
	public final static String TBXDSTJK = "tbxdstjk"; //̥ͬ�ֵ����彡��
	public final static String ZXDKQK = "zxdkqk"; //���޹�����ѧ����
	public final static String QGZXQK = "qgzxqk"; //�ڹ���ѧ���
	public final static String XSRCXFQK = "xsrcxfqk"; //ѧ����У�ճ��������	
	//������ũ��ְҵѧԺ ���Ի�����
	//�㽭����
	public final static String GZKH_SJXZ = "gzkh_sjxz"; //��������-ʱ��ѡ��
	public final static String ZHCP_XMLX = "zhcp_xmlx";// �ۺϲ���--��Ŀ����
	public final static String BFJS_XMLX = "bfjs_xmlx";// ��羺��--��Ŀ����
	public final static String ZHCP_TJZT = "zhcp_tjzt";// �ۺϲ���--�ύ״̬
	public final static String PJPY_TJDW = "pjpy_tjdw";// ��������-ͳ�Ƶ�λ

	public final static String RCSW_XZKG = "rcsw_xzkg";// �ճ�����-���ؿ���
	public final static String ENCN = "en-cn"; // ��Ӣ������
	
	//����ʦ�� ���Ի���ʼ
	public final static String YLZD9 = "ylzd9"; // ��ͥ���
	public final static String YLZD10 = "ylzd10"; // �ֺ����
	//����ʦ�� ���Ի�����
	
	public final static String ABC = "abc";//��ͥ��������
	public final static String THJL_GZDJ = "thjl_gzdj";//��ע�ȼ�
	public final static String THJL_GZLX = "thjl_gzlx";//��ע����
	public final static String GYBX_MYD = "gybx_myd";//�����
	public final static String SFDQ = "sfdq";//�Ƿ��ף�����ʦ��
	public final static String SFDQ_10335 = "sfdq_10335";//�Ƿ��ף��㽭��W��ͥ��r�{���ͥ��r�ֶΣ�ԓ�ֶ�ԭ�@ʾ���֞��Ƿ���H��
	public final static String SFGE = "sfge";//�Ƿ�¶�������ʦ��
	public final static String SFCJ = "sfcj";//�Ƿ�м�������ʦ��
	public final static String YWJY = "ywjy";//�����������
	public final static String GZJY = "gzjy";//���н�������
	public final static String BKJY = "bkjy";//���ƽ�������������
	public final static String SFSSMZ_DZN = "sfssmzjtknzn";//�Ƿ����������ͥ������Ů������ʦ��
	public final static String SFBLDK = "sfbldk";//�Ƿ����������ʦ��
	public final static String CYJKZK = "cyjkzk";//��ͥ��Ա����״��������ʦ��
	public final static String XB = "xb";//�Ա�����ʦ��
	//�����Ƽ���ѧ���Ի�
	public final static String SFDQ_10704 = "sfdq_10704";
	public final static String ZXDK_10704 = "zxdk_10704";
	
	
	public final static String GKDW="gkdw";//�ҿ���λ�����Ź���
	public final static String KHLX="khlx";//��������
	public final static String FZLX = "fzlx";//����Ա����:��ֵ����
	public final static String JSFS = "jsfs";//����Ա����:���㷽ʽ
	private String[][] values = null;
	private final static String DM = "dm";
	private final static String MC = "mc";
	private final static String CJPD = "cjpd";
	private final static String ZTWH = "ztwh";
	private final static String KQDJ = "kqdj";
	private final static String KHPF = "khpf";
	private final static String SSXQ = "ssxq";  //��ý����У��
	
	private final static String CJLB = "cjlb";
	
	public static final String JCLX_WSJC = "1";//�������
	public static final String JCLX_AQJC = "2";//��ȫ���
	public static final String JCLX_JLJC = "3";//���ɼ��
	public static final String SFZJLX = "sfzjlx";
	public static final String SQLY_10026 = "sqly";
	//�Ͼ��ߵ�ְҵ����ѧԺ
	public static final String JTKNLX = "jtknlx";

	//����ʦ��
	public static final String CJLX_10511 = "cjlx_10511";
	public static final String JTSZD_10511 = "jtszd_10511";
	public static final String JTFZYY_10511 = "jtfzyy_10511";




	public static int page=0;
	private Log logger = LogFactory.getLog(OptionUtil.class);

	/**
	 * 
	 * @����:�����ͻ�ȡѡ���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-18 ����04:49:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param type
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getOptions(String type) {

		if (ISNOT.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "��" }, { "0", "��" } };
		} else if (HAVENOT.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "��" }, { "0", "��" } };
		} else if (SHZT.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "δ���" }, { "1", "ͨ��" },
					{ "2", "��ͨ��" } }; //, { "3", "�˻�" }, { "4", "������" }
		} else if (YXSSZ.equalsIgnoreCase(type)) {
			values = new String[][] { { "cq", "����" }, { "xs", "��ʱ" } };
		} else if (GSLX.equalsIgnoreCase(type)) {
			values = new String[][] { { "st", "ʵ��" }, { "wd", "����" } };
		} else if (ONOFF.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "����" }, { "0", "�ر�" } };
		} else if (FMJZ.equalsIgnoreCase(type)) {
			values = new String[][] { { "��ĸ˫ȫ", "��ĸ˫ȫ" }, { "��ĸ˫��", "��ĸ˫��" },
					{ "����ĸ��", "����ĸ��" }, { "����ĸ��", "����ĸ��" } };
		} else if (PKXJB.equalsIgnoreCase(type)) {
			values = new String[][] { { "���Ҽ�ƶ����", "���Ҽ�ƶ����" },
					{ "ʡ��ƶ����", "ʡ��ƶ����" }, { "��ƶ����", "��ƶ����" } };
		} else if (JTHKXZ.equalsIgnoreCase(type)) {
			if(Base.xxdm.equals("11458")){
				values = new String[][] { { "��ҵ����", "��ҵ����" }, { "��ũҵ����", "��ũҵ����" } };
			}else if(Base.xxdm.equals("90211")) {
				values = new String[][] { { "ũҵ", "ũҵ" }, { "��ũҵ", "��ũҵ" } };
			}else {
				values = new String[][] { { "����", "����" }, { "ũ��", "ũ��" } };
			}
		} else if (ZHCP_XMLX.equalsIgnoreCase(type)) {
			CsszService csszService = new CsszService();
			String zclxdjxs = csszService.getCsz("zclxdjxs"); //�۲�ȼ���ʾ
			
			//�㽭����ѧ���Ի���ʾ
			if("1".equals(zclxdjxs)){
				values = new String[][] { { "1", "�ӷ�" }, { "2", "����" },
						{ "3", "Ĭ�Ϸ�" },{ "4", "�ȼ�" } };
			}else {
				values = new String[][] { { "1", "�ӷ�" }, { "2", "����" },
						{ "3", "Ĭ�Ϸ�" } };
			}
		} else if (BFJS_XMLX.equalsIgnoreCase(type)) {
				values = new String[][] { { "1", "�ӷ�" }, { "2", "����" },
						{ "3", "Ĭ�Ϸ�" } };
		}else if (PJPY_TJDW.equalsIgnoreCase(type)) {
			values = new String[][] { { "cpz", "������" }, { "njzy", "�꼶רҵ" },
					{ "bj", "�༶" } };
		} else if (ZHCP_TJZT.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "δ�ύ" }, { "1", "���ύ" },
					{ "2", "ȡ���ύ" } };
		} else if (RCSW_XZKG.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "����������" }, { "1", "���ͨ���󷽿�����" } };
		} else if (ENCN.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "����" }, { "1", "��Ӣ��" } };
		} else if (PKDJ.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "һ������" }, { "1", "����" },
					{ "2", "��������" } };
		} else if (SFKN.equalsIgnoreCase(type)) {
			values = new String[][] { { "������", "������" }, { "����", "����" } };
		} else if (KNDJ.equalsIgnoreCase(type)) {
			values = new String[][] { { "һ������", "һ������" }, { "�ر�����", "�ر�����" } };
		} else if (PYJG.equalsIgnoreCase(type)) {
			values = new String[][] { { "1", "ͬ������" }, { "0", "��ͬ������" } };
		} else if (PKLX.equalsIgnoreCase(type)) {
			values = new String[][] { { "0", "�¶�" }, { "1", "��ʿ��Ů" },
					{ "2", "������" }, { "3", "�ز���" }, { "4", "�ͱ���" },
					{ "5", "˫�¸�" }, { "6", "��ũ��" }, { "7", "������" },
					{ "8", "��������" }, { "9", "����Ů" }, { "10", "���˲м�" } };
			if(Base.xxdm.equals("11998") || Base.xxdm.equals("10344")){
				values = new String[][] {{"�¶�","�¶�"},{"�������ͥ","�������ͥ"},{"����ƶ","����ƶ"},{"����","����"},{"���ѹ���","���ѹ���"},
						{"Ʒѧ����","Ʒѧ����"},{"�м�ѧ��","�м�ѧ��"},{"���׼�ͥ ","���׼�ͥ "},{"�屣�� ","�屣�� "},{"�ͱ��� ","�ͱ��� "},{"ũ��ͱ��� ","ũ��ͱ��� "}
						,{"�м��� ","�м��� "},{"��Ů ","��Ů "},{"�������� ","�������� "},{"��ĸɥʧ�Ͷ����� ","��ĸɥʧ�Ͷ����� "}
						,{"���������Ÿ���Ů ","���������Ÿ���Ů "},{"ũ���������� ","ũ���������� "},{"������ƶ ","������ƶ "}};
			}
		}else if (ABC.equalsIgnoreCase(type)){
			values = new String[][] { { "A", "A" }, { "B", "B" }, { "C", "C" }};
		}else if (FMJZQK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "˫��", "˫��" }, { "���׹���", "���׹���" },
					{ "ĸ�׹���", "ĸ�׹���" }, { "������(����)", "������(����)" }, { "������", "������" }};		
		}else if (JRJKZK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "��֢������м�" },
					{ "2", "���Բ�����Ⱦ��" }, { "4", "��ͨ����(��ʱ�ԡ���Ҫ�������ƻ��Ϊ���ص�����)" },
					{ "5", "����" }};
		}else if (BRJKZK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "1", "����м�" },
					{ "2", "���Բ�����Ⱦ��" }, { "4", "��ͨ����(��ʱ�ԡ���Ҫ�������ƻ��Ϊ���ص�����)" },
					{ "5", "����" }}; 
		}else if (JTZNS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "4��������" }, { "1", "3��" },
					{ "2", "2��" }, { "3", "1��" }};
		}else if (ZDZNS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "4��������" }, { "1", "3��" },
					{ "2", "2��" }, { "3", "1��" }, { "4", "��" }};
		}else if (JTSYS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "0", "4��������" }, { "1", "2-3��" },
					{ "2", "1��" }};
		}else if (JTSRQK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "��ĸ˫������������Դ", "��ĸ˫������������Դ" }, { "һ����������Դ(����)", "һ����������Դ(����)" },
					{ "һ����������Դ(ĸ��)", "һ����������Դ(ĸ��)" }, { "��ĸ˫������������Դ", "��ĸ˫������������Դ" }};
		}else if (DKQK.equalsIgnoreCase(type)) {
			values = new String[][] {{ "��δ�����Ҳ�׼������", "��δ�����Ҳ�׼������" },{ "�Ѿ�����ô�������", "�Ѿ�����ô�������" },{ "��δ���׼������", "��δ���׼������" },{ "׼��������Դ�ش���", "׼��������Դ�ش���" },{ "�Ѿ�������Դ�ش���", "�Ѿ�������Դ�ش���" }};
		}else if (JTCS.equalsIgnoreCase(type)) {
			values = new String[][] {{ "ũ��", "ũ��" },{ "����", "����" },{ "�ɲ�", "�ɲ�" },{ "����", "����" }};
		}else if (JTSRLY.equalsIgnoreCase(type)) {
			values = new String[][] {
					{ "���ʡ����𡢽����������������Ͷ�����", "���ʡ����𡢽����������������Ͷ�����" },
					{ "�����ݽ𡢻������Ͻ𡢻�������ѡ�ʧҵ���ս�", "�����ݽ𡢻������Ͻ𡢻�������ѡ�ʧҵ���ս�" },
					{ "�̳С��������衢�������ۼ�ͥ�Ʋ���õ�����", "�̳С��������衢�������ۼ�ͥ�Ʋ���õ�����" },
					{ "����Ϣ���м�֤ȯ����������Ʊ����������", "����Ϣ���м�֤ȯ����������Ʊ����������" },
					{ "���̡��쳧�Լ�������ֲҵ�� ��ֲҵ���ӹ�ҵ�۳���Ҫ�ɱ��������", "���̡��쳧�Լ�������ֲҵ�� ��ֲҵ���ӹ�ҵ�۳���Ҫ�ɱ��������" },
					{ "�����ѡ���(��)����", "�����ѡ���(��)����" },
					{ "��ıְҵ����", "��ıְҵ����" },
					{ "����Ӧ�������ͥ������", "����Ӧ�������ͥ������" }};
		}else if(YLZD9.equalsIgnoreCase(type)) {
			if(Base.xxdm.equals("10511")) {
				values = new String[][] {{ "˫�׽�ȫ", "˫�׽�ȫ" },{ "�¶�", "�¶�" },{ "���ף���ĸһ��ȥ����", "���ף���ĸһ��ȥ����" },{ "��ĸ����", "��ĸ����" },{ "˫���вм�", "˫���вм�" },{ "���˲м�", "���˲м�" },{ "���������Ÿ�����", "���������Ÿ�����" },{ "�ز�", "�ز�" },{ "�屣��", "�屣��" }};
			}		
		}else if(YLZD10.equalsIgnoreCase(type)) {
			if(Base.xxdm.equals("10511")) {
				values = new String[][] {{ "��Ȼ�ֺ�", "��Ȼ�ֺ�" },{ "ͻ������", "ͻ������" },{ "��ͥ��Ա����", "��ͥ��Ա����" },{ "�ҳ�ʧҵ", "�ҳ�ʧҵ" },{ "����", "����" }};
			}
		}else if (JQLX.equalsIgnoreCase(type)) {
			values = new String[][] {{ "���", "���" },{ "����", "����" },{ "����", "����" }};
		}
		else if(GZKH_SJXZ.equals(type)){
			values = new String[][]{{"07","7ʱ"},{"08","8ʱ"},{"09","9ʱ"},{"10","10ʱ"},{"11","11ʱ"},{"12","12ʱ"},{"13","13ʱ"},{"14","14ʱ"},{"15","15ʱ"},{"16","16ʱ"},{"17","17ʱ"},{"18","18ʱ"},{"19","19ʱ"},{"20","20ʱ"},{"21","21ʱ"},{"22","22ʱ"},{"23","23ʱ"},{"24","24ʱ"}};
		}else if(THJL_GZDJ.equals(type)){
			values = new String[][]{{"����","����"},{"����","����"},{"����","����"}};
		}else if(THJL_GZLX.equals(type)){
			values = new String[][]{{"Σ������","Σ������"},{"�ص��ע","�ص��ע"},{"һ���ע","һ���ע"},{"ȡ����ע","ȡ����ע"},{"����ע","����ע"}};
		}else if(GYBX_MYD.equals(type)){
			values = new String[][]{{"����","����"},{"������","������"},{"�ǳ�����","�ǳ�����"}};
		}else if(DBHTKZ.equals(type)){
			values = new String[][]{{"��","��"},{"�еͱ�֤","�еͱ�֤"},{"������֤","������֤"}};
		}else if(TTXDJDXXQK.equals(type)){
			values = new String[][]{{"ͬ���ֵܽ�����һ����ѧ������ͬʱ�ڸ��л��ѧ�Ͷ�","ͬ���ֵܽ�����һ����ѧ������ͬʱ�ڸ��л��ѧ�Ͷ�"},{"������ѧ������ͬʱ�ڸ��л��ѧ�Ͷ�","������ѧ������ͬʱ�ڸ��л��ѧ�Ͷ�"}};
		}else if(FMSFJK.equals(type)){
			values = new String[][]{{"��","��"},{"���ز�","���ز�"},{"����м�","����м�"}};
		}else if(TBXDSTJK.equals(type)){
			values = new String[][]{{"����","����"},{"���ز�","���ز�"},{"����м�","����м�"}};
		}else if(ZXDKQK.equals(type)) {
			values = new String[][]{{"��","��"},{"��У԰�ع�����ѧ����","��У԰�ع�����ѧ����"},{"����Դ�ع�����ѧ����","����Դ�ع�����ѧ����"}};
		}else if(QGZXQK.equals(type)) {
			values = new String[][]{{"δ�μӹ�","δ�μӹ�"},{"���ڲμ�","���ڲμ�"},{"���ڲμ�","���ڲμ�"}};
		}else if(XSRCXFQK.equals(type)) {
			values = new String[][]{{"������Ѳ�����800Ԫ","������Ѳ�����800Ԫ"},{"������ѳ���800Ԫ","������ѳ���800Ԫ"}};
		}else if(SFDQ.equals(type)){
			values = new String[][]{{"��","��"},{"����","����"},{"����ȥ��","����ȥ��"},{"ĸ��ȥ��","ĸ��ȥ��"},{"����","����"}};
		}else if(SFGE.equals(type)){
			values = new String[][]{{"��","��"},{"��","��"}};
		}else if(SFCJ.equals(type)){
			values = new String[][]{{"��","��"},{"��","��"}};
		}else if(YWJY.equals(type)){
			values = new String[][]{{"",""},{"�������⣬0�˽����������","�������⣬0�˽����������"},{"�������⣬1�˽����������","�������⣬1�˽����������"},{"�������⣬2�˽����������","�������⣬2�˽����������"},{"�������⣬3�˼����Ͻ����������","�������⣬3�˼����Ͻ����������"}};
		}else if(GZJY.equals(type)){
			values = new String[][]{{"",""},{"�������⣬0�˽��ܸ��н���","�������⣬0�˽��ܸ��н���"},{"�������⣬1�˽��ܸ��н���","�������⣬1�˽��ܸ��н���"},{"�������⣬2�˽��ܸ��н���","�������⣬2�˽��ܸ��н���"},{"�������⣬3�˼����Ͻ��ܸ��н���","�������⣬3�˼����Ͻ��ܸ��н���"}};
		}else if(BKJY.equals(type)){
			values = new String[][]{{"",""},{"�������⣬0�˽��ܱ��Ƽ����Ͻ���","�������⣬0�˽��ܱ��Ƽ����Ͻ���"},{"�������⣬1�˽��ܱ��Ƽ����Ͻ���","�������⣬1�˽��ܱ��Ƽ����Ͻ���"},{"�������⣬2�˽��ܱ��Ƽ����Ͻ���","�������⣬2�˽��ܱ��Ƽ����Ͻ���"},{"�������⣬3�˼����Ͻ��ܱ��Ƽ����Ͻ���","�������⣬3�˼����Ͻ��ܱ��Ƽ����Ͻ���"}};
		}else if(SFSSMZ_DZN.equals(type)){
			values = new String[][]{{"��","��"},{"��","��"}};
		}else if(SFBLDK.equals(type)){
			values = new String[][]{{"��","��"},{"�ǣ��Ѱ���У԰�ع�����ѧ����","�ǣ��Ѱ���У԰�ع�����ѧ����"},{"�ǣ��Ѱ�����Դ��������ѧ����","�ǣ��Ѱ�����Դ��������ѧ����"},{"�ǣ��Ѱ�����������","�ǣ��Ѱ�����������"}};
		}else if(CYJKZK.equals(type)){
			values = new String[][]{{"����","����"},{"�м�","�м�"},{"���Բ�","���Բ�"},{"������","������"}};
		}else if(XB.equals(type)){
			values = new String[][]{{"��","��"},{"Ů","Ů"}};
		}else if(GKDW.equals(type)){
			values = new String[][]{{"����ѧԺ","����ѧԺ"}};
		}
		else if(KHLX.equals(type)){
			values = new String[][]{{"1","ѧ��"},{"2","��ʦ"}};
		}else if(CJPD.equalsIgnoreCase(type)){
			values = new String[][]{{"",""},{"����","����"},{"��","��"},{"����","����"},{"������","������"}};
		}else if(ZTWH.equalsIgnoreCase(type)){
			values = new String[][]{{"",""},{"����","����"},{"����","����"}};
		}
		else if(FZLX.equals(type)){
			values = new String[][]{{"1","�̶�"},{"0","����"}};
		}else if(KQDJ.equalsIgnoreCase(type)){
			values = new String[][]{{"",""},{"��","��"},{"��","��"}};
		}
		else if(JSFS.equals(type)){
			values = new String[][]{{"0","ƽ����"},{"1","ȥͷβ��ƽ��"},{"2","ȥͷβ��ƽ��(����)"}};
		}else if(KHPF.equalsIgnoreCase(type)){
			values = new String[][]{{"��","��"},{"��","��"}};
		}else if(SFDQ_10335.equalsIgnoreCase(type)){
			values = new String[][]{{"����","����"},{"�¶�","�¶�"},{"˫��","˫��"}};
		}else if(SFDQ_10704.equalsIgnoreCase(type)){
			values = new String[][]{{"����","����"},{"ĸ��ȥ��","ĸ��ȥ��"},{"����ȥ��","����ȥ��"},{"������ʧ","������ʧ"},{"ĸ����ʧ","ĸ����ʧ"}};
		}else if(ZXDK_10704.equalsIgnoreCase(type)){
			values = new String[][]{{"������ѧ����","������ѧ����"},{"��Դ����ѧ����","��Դ����ѧ����"},{"��������","��������"}};
		}else if(XSLB.equalsIgnoreCase(type)) {
			values = new String[][]{{"���������໤��δ������","���������໤��δ������"},{"������ʿ��Ů","������ʿ��Ů"},{"�屣������δ������","�屣������δ������"},{"�м�ѧ��","�м�ѧ��"},{"�ͱ���ͥ��Ů","�ͱ���ͥ��Ů"},{"�������ͥ��Ů","�������ͥ��Ů"}};
		}
		else if(FYLX.equalsIgnoreCase(type)) {
			values = new String[][]{{"0","��������"},{"1","����׷��"}};
		}
		else if(XMNZT.equalsIgnoreCase(type)) {
			values = new String[][]{{"0","���"},{"1","����"}};
		}else if(SSXQ.equals(type)){
			values = new String[][]{{"001","��ɳ"},{"002","ͩ��"}};
		}else if(CJLB.equals(type)){
			values = new String[][]{{"�����м�","�����м�"},{"�����м�","�����м�"},{"�����м�","�����м�"},{"�����м�","�����м�"}};
		}else if(SFZJLX.equals(type)){
			values = new String[][]{{"�������֤","�������֤"},{"����֤","����֤"},{"ʿ��֤","ʿ��֤"},{"��ְ�ɲ�֤","��ְ�ɲ�֤"},{"����������֤","����������֤"},{"�����������/���֤��","�����������/���֤��"}
			,{"������������/���֤��","������������/���֤��"},{"̨�����������½ͨ��֤","̨�����������½ͨ��֤"},{"�������þ�ס֤","�������þ�ס֤"},{"����","����"},{"���ڱ�","���ڱ�"},{"����","����"}};
		}else if(SQLY_10026.equals(type)){
			values = new String[][]{{"��ͥ������Ȼ�ֺ�","��ͥ������Ȼ�ֺ�"},{"��ͥ����ͻ�������¼�","��ͥ����ͻ�������¼�"},{"��ͥ��Ա��м�","��ͥ��Ա��м�"},{"�������Ͷ����������","�������Ͷ����������"},{"��ͥ�����ѧ��Ů�϶�","��ͥ�����ѧ��Ů�϶�"},{"��ͥ��Աʧҵ","��ͥ��Աʧҵ"}
			,{"��ͥǷծ","��ͥǷծ"},{"����������ͥ","����������ͥ"},{"�ͱ�","�ͱ�"},{"����","����"}};
		}else if(JTKNLX.equals(type)){
			values = new String[][]{{"�����������ϼ�ͥ��Ů�����ͱ���","�����������ϼ�ͥ��Ů�����ͱ���"},{"�¶�","�¶�"},{"����������ͥ��������ѧ���������С�����ֲᡷ","����������ͥ��������ѧ���������С�����ֲᡷ"},{"�м�ѧ��","�м�ѧ��"},{"����ְ����ͥ��Ů","����ְ����ͥ��Ů"},{"�����м��˼�ͥ��Ů","�����м��˼�ͥ��Ů"}
			,{"ũ������봿ũ����ͥ��Ů","ũ������봿ũ����ͥ��Ů"},{"�ͱ���Ե��ͥ��Ů","�ͱ���Ե��ͥ��Ů"},{"������ʿ�����������ˡ�������Ů","������ʿ�����������ˡ�������Ů"},{"���������ͥ��������ѧ��","���������ͥ��������ѧ��"},{"����ɽ��������ѧ��","����ɽ��������ѧ��"},{"������ͻ�������ƶ��ͥ��Ů","������ͻ�������ƶ��ͥ��Ů"}		
			};
		}
		else if(CJLX_10511.equals(type)){
			values = new String[][]{{"�����м�","�����м�"},{"�����м�","�����м�"},
					{"�����м�","�����м�"},{"�����м�","�����м�"}
			};
		}else if(JTSZD_10511.equals(type)){
			values = new String[][]{{"�سǼ�����","�سǼ�����"},{"С����","С����"}, {"ũ��","ũ��"}
			};
		}else if(JTFZYY_10511.equals(type)){
			values = new String[][]{{"ס��������","ס��������"},{"��������","��������"}, {"���������","���������"}
					, {"ҽ�ƽ��","ҽ�ƽ��"}, {"����","����"}
			};
		}else if(CJLB.equals(type)){
			values = new String[][]{{"ס��������","ס��������"},{"��������","��������"}, {"���������","���������"}
					, {"ҽ�ƽ��","ҽ�ƽ��"}, {"����","����"}
			};
		}
	
		return creatList();
	}

	// �����б���
	private List<HashMap<String, String>> creatList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (values == null) {
			logger.error("δ���������ѡ�");
			throw new NullPointerException();
		}

		for (int i = 0; i < values.length; i++) {

			HashMap<String, String> map = new HashMap<String, String>();

			map.put(DM, values[i][0]);
			map.put(MC, values[i][1]);

			list.add(map);
		}

		return list;
	}

}
