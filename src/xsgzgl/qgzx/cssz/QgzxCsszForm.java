package xsgzgl.qgzx.cssz;

import xsgzgl.comm.form.CommForm;
/**
 * �ڹ���ѧ-��������-��������
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cjbz;//����׼
	private String xsgws;//ѧ����λ��
	private String sxsz;//������ֵ
	private String sxzd;//�����ֶ�
	private String sfyxffje;//�Ƿ������Ž��
	private String sfjfhb;//�Ƿ�ͨ����ɻ������Ƴ�𷢷�
	private String sfkfgwsq;//�Ƿ񿪷Ÿ�λ����
	private String cjsx;//�������
	private String ksyf;//��𷢷ſ�ʼ�·�
	private String jsyf;//��𷢷Ž����·�
	private String kssj;//��𷢷ſ�ʼʱ��
	private String jssj;//��𷢷Ž���ʱ��
	private String gwsqkssj;//��λ���뿪ʼʱ��
	private String gwsqjssj;//��λ�������ʱ��
	private String xsxsgws;//ѧ���������λ��
	private String sfkfxsgwsq;//�Ƿ񿪷�ѧ����λ����
	private String xsgwsqkssj;//ѧ����λ���뿪ʼʱ��
	private String xsgwsqjssj;//ѧ����λ�������ʱ��
	private String xsgwsqsplc;//ѧ��У����λ������������
	private String kcxs;	//����ʱ���Ƿ���ʾ�γ�ʱ��
	
	private String rskzjb;//�������Ƽ���
	private String qxfw;//Ȩ�޷�Χ
	private String yjqxfwkz;
	
	private String sfsdgwcjsx;//�Ƿ��趨��λ�������(��:yes  ��:no)
	private String gwzgcjsx;//��λ��߳������[Ԫ/�� ��ÿ����λÿ��ÿ�³�����ޣ�]
	private String sfkgggwcjsx;//���˵�λ�����λʱ�ɷ���ĸ�λ�������(��:yes  ��:no)
	private String dsfxy; //������Э��
	private String sfxyzgsc;//�Ƿ�ֻ���ڹ��ʸ����ѧ��������(��:yes  ��:no)
	private String sqfs; // ���뷽ʽ(ɽ���ƾ���ѧ)
	private String pkscjbz;//ƶ��������׼(���ݴ�ѧ)
	private String pkscjzgsx;//ƶ��������������
	
	private String yrdwgwsqxn;//���˵�λ��λ����ѧ��
	
	private String gdgcjbz;//�̶��ڳ���׼�������Ƽ���ѧ��
	//��������������
	private String sqkg;
	private String splc;
	private String sqkssj;
	private String sqjssj;
	private String sfyxcgcjsx;//�Ƿ�������������ޣ������Ի���
	private String yrdwsplc;//У�ڸ�λ�����������̣�ԭ�����˵�λ�������̣�
	
	private String xsyjgwsqsplc;//ѧ��Ժ����λ������������
	
	private String yjrskzjb;//Ժ���������Ƽ���
	private String yjqxfw;//Ժ��Ȩ�޷�Χ

	private String id;
	private String sfkfxwgwsq;//У���λ���뿪��
	private String xwgwsqkssj;//У���λ���뿪ʼʱ��
	private String xwgwsqjssj;//У���λ�������ʱ��
	private String xwgwsplc;//У���λ������������
	private String xwxsgwsqsplc;//ѧ��У���λ������������
	private String gzscwhsplc;//����ʱ��ά����������
	private String xslzsplc;//ѧ����ְ��������
	private String gzsqkg;//�����걨����
	private String gzsqkssj;//�����걨��ʼʱ��
	private String gzsqjssj;//�����걨����ʱ��
	private String gzsqsplc;//�����걨��������

	private String gzscsx;//����ʱ������

	public String getGzscsx() {
		return gzscsx;
	}

	public void setGzscsx(String gzscsx) {
		this.gzscsx = gzscsx;
	}

	public String getYjqxfwkz() {
		return yjqxfwkz;
	}

	public void setYjqxfwkz(String yjqxfwkz) {
		this.yjqxfwkz = yjqxfwkz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSfkfxwgwsq() {
		return sfkfxwgwsq;
	}

	public void setSfkfxwgwsq(String sfkfxwgwsq) {
		this.sfkfxwgwsq = sfkfxwgwsq;
	}

	public String getXwgwsqkssj() {
		return xwgwsqkssj;
	}

	public void setXwgwsqkssj(String xwgwsqkssj) {
		this.xwgwsqkssj = xwgwsqkssj;
	}

	public String getXwgwsqjssj() {
		return xwgwsqjssj;
	}

	public void setXwgwsqjssj(String xwgwsqjssj) {
		this.xwgwsqjssj = xwgwsqjssj;
	}

	public String getXwgwsplc() {
		return xwgwsplc;
	}

	public void setXwgwsplc(String xwgwsplc) {
		this.xwgwsplc = xwgwsplc;
	}

	public String getXwxsgwsqsplc() {
		return xwxsgwsqsplc;
	}

	public void setXwxsgwsqsplc(String xwxsgwsqsplc) {
		this.xwxsgwsqsplc = xwxsgwsqsplc;
	}

	public String getGzscwhsplc() {
		return gzscwhsplc;
	}

	public void setGzscwhsplc(String gzscwhsplc) {
		this.gzscwhsplc = gzscwhsplc;
	}

	public String getXslzsplc() {
		return xslzsplc;
	}

	public void setXslzsplc(String xslzsplc) {
		this.xslzsplc = xslzsplc;
	}

	public String getGzsqkg() {
		return gzsqkg;
	}

	public void setGzsqkg(String gzsqkg) {
		this.gzsqkg = gzsqkg;
	}

	public String getGzsqkssj() {
		return gzsqkssj;
	}

	public void setGzsqkssj(String gzsqkssj) {
		this.gzsqkssj = gzsqkssj;
	}

	public String getGzsqjssj() {
		return gzsqjssj;
	}

	public void setGzsqjssj(String gzsqjssj) {
		this.gzsqjssj = gzsqjssj;
	}

	public String getGzsqsplc() {
		return gzsqsplc;
	}

	public void setGzsqsplc(String gzsqsplc) {
		this.gzsqsplc = gzsqsplc;
	}

	/**
	 * @return the yjrskzjb
	 */
	public String getYjrskzjb() {
		return yjrskzjb;
	}
	/**
	 * @param yjrskzjbҪ���õ� yjrskzjb
	 */
	public void setYjrskzjb(String yjrskzjb) {
		this.yjrskzjb = yjrskzjb;
	}
	/**
	 * @return the yjqxfw
	 */
	public String getYjqxfw() {
		return yjqxfw;
	}
	/**
	 * @param yjqxfwҪ���õ� yjqxfw
	 */
	public void setYjqxfw(String yjqxfw) {
		this.yjqxfw = yjqxfw;
	}
	/**
	 * @return the xsyjgwsqsplc
	 */
	public String getXsyjgwsqsplc() {
		return xsyjgwsqsplc;
	}
	/**
	 * @param xsyjgwsqsplcҪ���õ� xsyjgwsqsplc
	 */
	public void setXsyjgwsqsplc(String xsyjgwsqsplc) {
		this.xsyjgwsqsplc = xsyjgwsqsplc;
	}
	public String getYrdwsplc() {
		return yrdwsplc;
	}
	public void setYrdwsplc(String yrdwsplc) {
		this.yrdwsplc = yrdwsplc;
	}
	/**
	 * @return the yrdwgwsqxn
	 */
	public String getYrdwgwsqxn() {
		return yrdwgwsqxn;
	}
	/**
	 * @param yrdwgwsqxnҪ���õ� yrdwgwsqxn
	 */
	public void setYrdwgwsqxn(String yrdwgwsqxn) {
		this.yrdwgwsqxn = yrdwgwsqxn;
	}
	/**
	 * @return the sfyxcgcjsx
	 */
	public String getSfyxcgcjsx() {
		return sfyxcgcjsx;
	}
	/**
	 * @param sfyxcgcjsxҪ���õ� sfyxcgcjsx
	 */
	public void setSfyxcgcjsx(String sfyxcgcjsx) {
		this.sfyxcgcjsx = sfyxcgcjsx;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssjҪ���õ� sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public String getPkscjbz() {
		return pkscjbz;
	}
	public void setPkscjbz(String pkscjbz) {
		this.pkscjbz = pkscjbz;
	}
	public String getPkscjzgsx() {
		return pkscjzgsx;
	}
	public void setPkscjzgsx(String pkscjzgsx) {
		this.pkscjzgsx = pkscjzgsx;
	}
	public String getSqfs() {
		return sqfs;
	}
	public void setSqfs(String sqfs) {
		this.sqfs = sqfs;
	}
	public String getXsgws() {
		return xsgws;
	}
	public void setXsgws(String xsgws) {
		this.xsgws = xsgws;
	}
	public String getSxsz() {
		return sxsz;
	}
	public void setSxsz(String sxsz) {
		this.sxsz = sxsz;
	}
	public String getSxzd() {
		return sxzd;
	}
	public void setSxzd(String sxzd) {
		this.sxzd = sxzd;
	}
	public String getSfyxffje() {
		return sfyxffje;
	}
	public void setSfyxffje(String sfyxffje) {
		this.sfyxffje = sfyxffje;
	}
	public String getSfjfhb() {
		return sfjfhb;
	}
	public void setSfjfhb(String sfjfhb) {
		this.sfjfhb = sfjfhb;
	}
	public String getCjbz() {
		return cjbz;
	}
	public void setCjbz(String cjbz) {
		this.cjbz = cjbz;
	}
	public String getCjsx() {
		return cjsx;
	}
	public void setCjsx(String cjsx) {
		this.cjsx = cjsx;
	}
	public String getKsyf() {
		return ksyf;
	}
	public void setKsyf(String ksyf) {
		this.ksyf = ksyf;
	}
	public String getJsyf() {
		return jsyf;
	}
	public void setJsyf(String jsyf) {
		this.jsyf = jsyf;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getSfkfgwsq() {
		return sfkfgwsq;
	}
	public void setSfkfgwsq(String sfkfgwsq) {
		this.sfkfgwsq = sfkfgwsq;
	}
	public String getGwsqkssj() {
		return gwsqkssj;
	}
	public void setGwsqkssj(String gwsqkssj) {
		this.gwsqkssj = gwsqkssj;
	}
	public String getGwsqjssj() {
		return gwsqjssj;
	}
	public void setGwsqjssj(String gwsqjssj) {
		this.gwsqjssj = gwsqjssj;
	}
	public String getXsxsgws() {
		return xsxsgws;
	}
	public void setXsxsgws(String xsxsgws) {
		this.xsxsgws = xsxsgws;
	}
	public String getSfkfxsgwsq() {
		return sfkfxsgwsq;
	}
	public void setSfkfxsgwsq(String sfkfxsgwsq) {
		this.sfkfxsgwsq = sfkfxsgwsq;
	}
	public String getXsgwsqkssj() {
		return xsgwsqkssj;
	}
	public void setXsgwsqkssj(String xsgwsqkssj) {
		this.xsgwsqkssj = xsgwsqkssj;
	}
	public String getXsgwsqjssj() {
		return xsgwsqjssj;
	}
	public void setXsgwsqjssj(String xsgwsqjssj) {
		this.xsgwsqjssj = xsgwsqjssj;
	}
	public String getXsgwsqsplc() {
		return xsgwsqsplc;
	}
	public void setXsgwsqsplc(String xsgwsqsplc) {
		this.xsgwsqsplc = xsgwsqsplc;
	}
	/**
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjbҪ���õ� rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	/**
	 * @return the qxfw
	 */
	public String getQxfw() {
		return qxfw;
	}
	/**
	 * @param qxfwҪ���õ� qxfw
	 */
	public void setQxfw(String qxfw) {
		this.qxfw = qxfw;
	}
	/**
	 * @return the sfsdgwcjsx
	 */
	public String getSfsdgwcjsx() {
		return sfsdgwcjsx;
	}
	/**
	 * @param sfsdgwcjsxҪ���õ� sfsdgwcjsx
	 */
	public void setSfsdgwcjsx(String sfsdgwcjsx) {
		this.sfsdgwcjsx = sfsdgwcjsx;
	}
	/**
	 * @return the gwzgcjsx
	 */
	public String getGwzgcjsx() {
		return gwzgcjsx;
	}
	/**
	 * @param gwzgcjsxҪ���õ� gwzgcjsx
	 */
	public void setGwzgcjsx(String gwzgcjsx) {
		this.gwzgcjsx = gwzgcjsx;
	}
	/**
	 * @return the sfkgggwcjsx
	 */
	public String getSfkgggwcjsx() {
		return sfkgggwcjsx;
	}
	/**
	 * @param sfkgggwcjsxҪ���õ� sfkgggwcjsx
	 */
	public void setSfkgggwcjsx(String sfkgggwcjsx) {
		this.sfkgggwcjsx = sfkgggwcjsx;
	}
	public String getDsfxy() {
		return dsfxy;
	}
	public void setDsfxy(String dsfxy) {
		this.dsfxy = dsfxy;
	}
	/**
	 * @return the sfxyzgsc
	 */
	public String getSfxyzgsc() {
		return sfxyzgsc;
	}
	/**
	 * @param sfxyzgscҪ���õ� sfxyzgsc
	 */
	public void setSfxyzgsc(String sfxyzgsc) {
		this.sfxyzgsc = sfxyzgsc;
	}
	/**
	 * @return the kcxs
	 */
	public String getKcxs() {
		return kcxs;
	}
	/**
	 * @param kcxsҪ���õ� kcxs
	 */
	public void setKcxs(String kcxs) {
		this.kcxs = kcxs;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-9 ����04:19:42 
	 * @return		: the gdgcjbz
	 */
	public String getGdgcjbz() {
		return gdgcjbz;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-9 ����04:19:42 
	 * @param 		��gdgcjbz the gdgcjbz to set
	 */
	public void setGdgcjbz(String gdgcjbz) {
		this.gdgcjbz = gdgcjbz;
	}
	
}
