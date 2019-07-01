package xgxt.sjdrdc;

import xgxt.comm.search.SearchModel;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ͨ�õ��뵼��model
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: sjf</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-03-29</p>
 */
public class ExportAndImportModel {
	SearchModel searchModel = new SearchModel(); // �߼���ѯmodel
	
	private String bm;			// ����
	private String mk;			// ģ��
	private String[] sx;		// ˳��
	private String[] zd;		// �����ֶ�
	private String[] zdsm;      // �ֶ�˵��
	private String[] ch_zdmc;
	private String path;        // ·��
	
	private String exportId;
	private String filePath;	// �ļ�·�� 
	private String realTable;	// ����
	private String mappingItems;// ӳ����Ŀ
	private int index;          // excel���е��к�
	private String nf;
	private String yf;
	
	public String getMk() {
		return mk;
	}

	public void setMk(String mk) {
		this.mk = mk;
	}

	public String[] getZd() {
		ch_zdmc = getCh_zdmc();
		zd = new String[ch_zdmc.length];
		for(int i=0;i<ch_zdmc.length;i++){
			String[] temp = ch_zdmc[i].split("!split!");
			if(temp!=null && temp.length==2){
				zd[i] = temp[0];
			}
		}
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String[] getSx() {
		if(zd != null){
			sx = new String[zd.length];
			for(int i=0; i<ch_zdmc.length; i++){
				sx[i] = i+"";
			}
		}
		return sx;
	}
	
	public String getBm(){
		return bm;
	}

	public String[] getZdsm() {
		ch_zdmc = getCh_zdmc();
		zdsm = new String[ch_zdmc.length];
		
		for(int i=0;i<ch_zdmc.length;i++){
			String[] temp = ch_zdmc[i].split("!split!");
			if(temp!=null && temp.length==2){
				zdsm[i] = temp[1];
			}
		}
		return zdsm;
	}

	public void setZdsm(String[] zdsm) {
		this.zdsm = zdsm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getMappingItems() {
		return mappingItems;
	}

	public void setMappingItems(String mappingItems) {
		this.mappingItems = mappingItems;
	}

	public void setSx(String[] sx) {
		this.sx = sx;
	}
	
	public String[][] getSplitMapping(){
		String[] mappingOne = mappingItems.split("!!SplitOne!!");
		String[][] splitMappingOne = new String[mappingOne.length][2];
		
		for(int mappingOneNum =0;mappingOneNum<mappingOne.length;mappingOneNum++){//�ָ��Ӧ����Ϣ
			splitMappingOne[mappingOneNum] = mappingOne[mappingOneNum].split("!!SplitTwo!!");
		}
		
		return splitMappingOne;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getCh_zdmc() {
		return ch_zdmc == null ? new String[0] : ch_zdmc;
	}

	public void setCh_zdmc(String[] chZdmc) {
		ch_zdmc = chZdmc;
	}

	public String getExportId() {
		return exportId;
	}

	public void setExportId(String exportId) {
		this.exportId = exportId;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getNf() {
		return nf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getYf() {
		return yf;
	}
}
