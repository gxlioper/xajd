package common;

public class GlobalsVariable {

	//ϵͳ��
	public final static String XTDM_ADMIN = "admin";
	public final static String XTDM_XY = "xy";//ѧԺ�û�����
	public final static String XTDM_XX = "xx";//ѧУ�û�����
	public final static String XTDM_FDY = "fdy";//����Ա,�������û�����
	public final static String XTDM_JWFLAG_XF = "1";//ѧ�ְ����
	public final static String XTDM_JWFLAG_GD = "0";//���ɰ����
	
	public final static String XTDM_ISFDY = "isFdy";//���ڴ�SESSIONȡ�Ƿ񸨵�Ա����
	public final static String XTDM_FDYQX = "fdyQx";//���ڴ�SESSIONȡ�Ƿ񸨵�Ա����
	
	public final static String XTDM_STUDENT = "student";//���ڴ�SESSIONȡ�Ƿ�ѧ������
	public final static String XTDM_STU = "stu";//���ڴ�SESSIONȡ�Ƿ�ѧ������
	public final static String XTWH_SH_LIST = "shList";//���
	public final static String XTWH_XXBMLB_LIST = "xxbmlbList";//ѧУ�������
	public final static String XTWH_MZ_LIST = "mzList";//����
	public final static String XTWH_ZZMM_LIST = "zzmmList";//������ò
	public final static String XTWH_PYCC_LIST = "pyccList";//�������
	public final static String XTWH_RXFS_LIST = "rxfsList";//��ѧ��ʽ
	public final static String XTWH_KSLB_LIST = "kslbList";//�������
	public final static String XTWH_PYFS_LIST = "pyfsList";//������ʽ
	public final static String XTWH_YH_LIST = "yhList";//����
	
	public final static String XTWH_GNMK_PJPY = "��������";
	public final static String XTWH_GNMK_XSXX = "ѧ����Ϣ";
	public final static String XTWH_GNMK_XSCJ = "�γ̳ɼ�";
	public final static String XTWH_GNMK_WJCF = "Υ�ʹ���";
	public final static String XTWH_GNMK_QGZX = "�ڹ���ѧ";
	public final static String XTWH_GNMK_XSZZ = "ѧ������";
	public final static String XTWH_GNMK_GYGL = "��Ԣ����";
	public final static String XTWH_GNMK_XJYD = "ѧ���춯";
	public final static String XTWH_GNMK_CWSJ = "��������";
	public final static String XTWH_GNMK_RCSW = "�ճ�����";
	public final static String XTWH_GNMK_XJYDXX = "ѧ����Ϣ";
	
	//==================ͳһ�����֤ʹ��ѧУ����=========
	public final static String[] XTWH_TYSFRZ_DR = {"13798", "10022", "10143","10029"};//������֤ѧУ�����б�,������˰ְҵ ������ҵ��ѧ �������չ�ҵѧԺ,�׶�����ѧԺ
	public final static String[] XTWH_TYSFRZ_DR_NEW = {"10426"};//�ൺ�Ƽ���ѧ
	public final static String[] XTWH_TYSFRZ_JZ = {"13022", "14139", "12049", "10395", "10478", "10718"};//������֤ѧУ�����б�,���������,����������ְҵѧԺ,���ֽ�ְͨҵ����ѧԺ,����ѧԺ,�ܿ�ʦ��ѧԺ,����ʦ����ѧ
	public final static String[] XTWH_TYSFRZ_JZ_NEW = {"14073","51221"};//��new��������֤ѧУ�����б�,�������ù���ְҵѧԺ
	public final static String[] XTWH_TYSFRZ_SW = {"12865", "11080"};//��ά��֤ѧУ�����б�,�㽭��ҵְҵ����ѧԺ,��������ѧԺ
	public final static String[] XTWH_TYSFRZ_YB = {"10264", "11458"};//�װ���֤ѧУ�����б��Ϻ������ѧ,�Ϻ����ѧԺ
	public final static String[] XTWH_TYSFRZ_DK = {"13108"};//�����֤ѧУ�����б�������Ϣְҵ����ѧԺ
	public final static String[] XTWH_TYSFRZ_KB = {"400030","13657"};//������֤ѧУ�����б�������óѧУ,������ʵ��ְҵѧУ
	public final static String[] XTWH_TYSFRZ_KS = {"13815"};//������֤ѧУ�����б��Ĵ���Ϣְҵ����ѧԺ
	public final static String[] XTWH_TYSFRZ_XE = {"10504"};//ϣ����֤ѧУ�����б�����ũҵ��ѧ
	public final static String[] XTWH_TYSFRZ_HS = {"10742"};//������֤ѧУ�����б����������ѧ
	
	//�������ż�
	public final static String PJPY_JXJ = "jxj";
	public final static String PJPY_RYCH = "rych";
	public final static String PJPY_ZHCPJXJ = "zhcpjxj";
	
	// ���Ž��� ͨ�� �������
	public final static String DTJS_DYXXB = "dyxxb";// ��Ա��Ϣ
	public final static String DTJS_YBDYB = "ybdyxxb";// Ԥ����Ա
	public final static String DTJS_FZDXB = "czxx_fzdxb";// ��չ����
	public final static String DTJS_JJFZB = "rdjjfzxxb";// �뵳��������
	public final static String DTJS_RDSQB = "czxx_rdsqb";//�뵳����
	
	// ���Ž��� ͨ�� �������ʱ���ֶ�
	public final static String DTJS_DYXXB_RDSJ = "rdsj";// ��Ա��Ϣ(�뵳ʱ��)
	public final static String DTJS_DYXXB_ZZSJ = "zzsj";// ��Ա��Ϣ(ת��ʱ��)
	public final static String DTJS_YBDYB_KSSJ = "kssj";// Ԥ����Ա(��ʼʱ��)
	public final static String DTJS_FZDXB_KSSJ = "kssj";// ��չ����(��ʼʱ��)
	public final static String DTJS_JJFZB_KSSJ = "kssj";// �뵳��������(��ʼʱ��)
	

	// �ʾ���� �������ʹ���
	public final static String WJDC_ONECHOOSE = "oneChoose";// ��ѡ��
	public final static String WJDC_ALLCHOOSE = "allChoose";// ��ѡ��
	public final static String WJDC_QUESTIONS = "questions";// �ʴ���

	// �ʾ���� ����ģ�����
	public final static String WJDC_XLPC = "xlpc";// �����ղ�
	public final static String WJDC_SXZK = "sxzk";// ˼��״������
	public final static String WJDC_SXSZK = "sxszk";// ʵϰ��״������
	
	//������
	public final static String XLJK_DXSRGYS_QTZZYZ = "����֢״";
	public final static String XLJK_DXSRGYS_JSFLYZ = "�������";
	public final static String XLJK_DXSRGYS_YYZYZ = "����֢";
	public final static String XLJK_DXSRGYS_SJZYZ = "��֢";
	public final static String XLJK_DXSRGYS_QTZZYW = "qtzz";
	public final static String XLJK_DXSRGYS_JSFLYW = "jsfl";
	public final static String XLJK_DXSRGYS_YYZYW = "yyz";
	public final static String XLJK_DXSRGYS_SJZYW = "sjz";
	public final static String XLJK_KTEYS_AYZ  = "ayz";
	public final static String XLJK_KTEYS_BYZ  = "byz";
	public final static String XLJK_KTEYS_CYZ  = "cyz";
	public final static String XLJK_KTEYS_DYZ  = "dyz";
	public final static String XLJK_KTEYS_EYZ  = "eyz";
	public final static String XLJK_KTEYS_FYZ  = "fyz";
	public final static String XLJK_KTEYS_GYZ  = "gyz";
	public final static String XLJK_KTEYS_HYZ  = "hyz";
	public final static String XLJK_KTEYS_IYZ  = "iyz";
	public final static String XLJK_KTEYS_LYZ  = "lyz";
	public final static String XLJK_KTEYS_MYZ  = "myz";
	public final static String XLJK_KTEYS_NYZ  = "nyz";
	public final static String XLJK_KTEYS_OYZ  = "oyz";
	public final static String XLJK_KTEYS_Q1YZ = "q1yz";
	public final static String XLJK_KTEYS_Q2YZ = "q2yz";
	public final static String XLJK_KTEYS_Q3YZ = "q3yz";
	public final static String XLJK_KTEYS_Q4YZ = "q4yz";
	public final static String XLJK_KTEYS_X1YZ = "x1yz";
	public final static String XLJK_KTEYS_X2YZ = "x2yz";
	public final static String XLJK_KTEYS_X3YZ = "x3yz";
	public final static String XLJK_KTEYS_X4YZ = "x4yz";
	public final static String XLJK_KTEYS_Y1YZ = "y1yz";
	public final static String XLJK_KTEYS_Y2YZ = "y2yz";
	public final static String XLJK_KTEYS_Y3YZ = "y3yz";
	public final static String XLJK_KTEYS_Y4YZ = "y4yz";
	//======ѧ����Ϣ============
	public final static String XSXX_KTEYS_XJZTLIST = "xjztList";
	public final static String XSXX_KTEYS_YDLBLIST = "ydlbList";
	public final static String XSXX_KTEYS_SFZXLIST = "sfzxList";
	public final static String XSXX_KTEYS_GNMK = "mkmc";//ģ������
	public final static String XSXX_KTEYS_CXJG = "cxjg";//��ѯ���
	
	
   //=======================���ܴ���=======================
	//==================================��������==============================================
	
	public final static String GNDM_PJPY_RSTZ = "pjpy_rstz";                //����������������
	public final static String GNDM_PJPY_JXJSQRSZ = "pjpy_jxjsqrsz";        //�������Ž�ѧ�������ƺ�����������
	public final static String GNDM_PJPY_JXJSQ = "pjpy_jxjsq";              //�������Ž�ѧ������
	public final static String GNDM_PJPY_RYCHSQ = "pjpy_rychsq";            //�������������ƺ�����
	public final static String GNDM_PJPY_JXJSH = "pjpy_jxjsh";              //�������Ž�ѧ�����
	public final static String GNDM_PJPY_ZHSZCP = "pjpy_zhcpwh";            //�ۺ����ʲ���
	public final static String GNDM_PJPY_TJSZ = "pjpy_tjsz";                //��������
	public final static String GNDM_PJPY_ZHCPJXJSQ = "pjpy_zhcpjxjsq";      //�ۺϲ�����ѧ������
	public final static String GNDM_PJPY_ZHCPJXJSH = "pjpy_zhcpjxjsh";      //�ۺϲ�����ѧ�����
	public final static String GNDM_PJPY_JXJJESZ = "pjpy_jxjjesz";          //��ѧ��������
	public final static String GNDM_PJPY_ZHCPJXJSB = "pjpy_zhcpjxjsb";      //�ۺϲ�����ѧ���ϱ�
	public final static String GNDM_PJPY_JXJSB = "pjpy_jxjsb";              //��ѧ���ϱ�
	//==================================end��������==============================================
	
	//==================ѧ����Ϣ========================
	public final static String GNDM_XSXX_XJYDSH = "xjydsh";              //ѧ���춯���
	
	//==================Υ�ʹ���========================
	public final static String GNDM_WJCF_WXSH = "wxsh"; //������˱�־
	
	//==================����5,6������========================
	public final static String[] JXGL_JXJZ_FIVELV = {""};
	public final static String[] JXGL_JXJZ_SIXLV = {"14073"};//�������ù���ְҵѧԺ
	
}
