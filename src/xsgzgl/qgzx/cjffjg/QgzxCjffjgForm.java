package xsgzgl.qgzx.cjffjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/**
 * �ڹ���ѧ-������-�����Ϣ����
 *
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffjgForm extends CommForm {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private User user;
    private String wbh;
    private String pkValue;//����
    private String doType;//��������
    private String xn;//ѧ��
    private String yrbm;//���˲���
    private String gwdm;//��λ����
    private String gwmc;//��λ����
    private String gwry;//��λ��Ա
    private String xm;//����
    private String zgzt;//�ڸ�״̬
    private String ffny;//�·�
    private String tjzt;//�ύ״̬
    private String gs;//��ʱ
    private String je;//���
    private String khdj;//���˵ȼ�
    private String bz;//��ע
    private String cjsx;//�������
    private String sxzd;
    private String sxsz;
    private String cjffr;//��𷢷���
    private String jcdlgs; //����������ʱ
    private String zhdlgs; //�ۺϵ�����ʱ
    private String xq;//ѧ��
    //���������Ի��ֶ�
    private String jfhb;//���ѻ������
    private String zc;//���ѻ������

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getCjffr() {
        return cjffr;
    }

    public void setCjffr(String cjffr) {
        this.cjffr = cjffr;
    }

    //�����޸�
    private String yffxh;
    private String yffgw;

    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    @Override
    public SearchModel getSearchModel() {
        return searchModel;
    }

    @Override
    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPkValue() {
        return pkValue;
    }

    public void setPkValue(String pkValue) {
        this.pkValue = pkValue;
    }

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getYrbm() {
        return yrbm;
    }

    public void setYrbm(String yrbm) {
        this.yrbm = yrbm;
    }

    public String getGwdm() {
        return gwdm;
    }

    public void setGwdm(String gwdm) {
        this.gwdm = gwdm;
    }

    public String getGwmc() {
        return gwmc;
    }

    public void setGwmc(String gwmc) {
        this.gwmc = gwmc;
    }

    public String getGwry() {
        return gwry;
    }

    public void setGwry(String gwry) {
        this.gwry = gwry;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZgzt() {
        return zgzt;
    }

    public void setZgzt(String zgzt) {
        this.zgzt = zgzt;
    }

    public String getFfny() {
        return ffny;
    }

    public void setFfny(String ffny) {
        this.ffny = ffny;
    }

    public String getTjzt() {
        return tjzt;
    }

    public void setTjzt(String tjzt) {
        this.tjzt = tjzt;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return the yffxh
     */
    public String getYffxh() {
        return yffxh;
    }

    /**
     * @param yffxhҪ���õ� yffxh
     */
    public void setYffxh(String yffxh) {
        this.yffxh = yffxh;
    }

    /**
     * @return the yffgw
     */
    public String getYffgw() {
        return yffgw;
    }

    /**
     * @param yffgwҪ���õ� yffgw
     */
    public void setYffgw(String yffgw) {
        this.yffgw = yffgw;
    }

    /**
     * @return the khdj
     */
    public String getKhdj() {
        return khdj;
    }

    /**
     * @param khdjҪ���õ� khdj
     */
    public void setKhdj(String khdj) {
        this.khdj = khdj;
    }

    /**
     * @return the cjsx
     */
    public String getCjsx() {
        return cjsx;
    }

    /**
     * @param cjsxҪ���õ� cjsx
     */
    public void setCjsx(String cjsx) {
        this.cjsx = cjsx;
    }

    /**
     * @return the sxzd
     */
    public String getSxzd() {
        return sxzd;
    }

    /**
     * @param sxzdҪ���õ� sxzd
     */
    public void setSxzd(String sxzd) {
        this.sxzd = sxzd;
    }

    /**
     * @return the sxsz
     */
    public String getSxsz() {
        return sxsz;
    }

    /**
     * @param sxszҪ���õ� sxsz
     */
    public void setSxsz(String sxsz) {
        this.sxsz = sxsz;
    }

    /**
     * @return the wbh
     */
    public String getWbh() {
        return wbh;
    }

    /**
     * @param wbhҪ���õ� wbh
     */
    public void setWbh(String wbh) {
        this.wbh = wbh;
    }

    /**
     * @return the jcdlgs
     */
    public String getJcdlgs() {
        return jcdlgs;
    }

    /**
     * @param jcdlgsҪ���õ� jcdlgs
     */
    public void setJcdlgs(String jcdlgs) {
        this.jcdlgs = jcdlgs;
    }

    /**
     * @return the zhdlgs
     */
    public String getZhdlgs() {
        return zhdlgs;
    }

    /**
     * @param zhdlgsҪ���õ� zhdlgs
     */
    public void setZhdlgs(String zhdlgs) {
        this.zhdlgs = zhdlgs;
    }

    /**
     * @return the jfhb
     */
    public String getJfhb() {
        return jfhb;
    }

    /**
     * @param jfhbҪ���õ� jfhb
     */
    public void setJfhb(String jfhb) {
        this.jfhb = jfhb;
    }

    /**
     * @return the zc
     */
    public String getZc() {
        return zc;
    }

    /**
     * @param zcҪ���õ� zc
     */
    public void setZc(String zc) {
        this.zc = zc;
    }


}
