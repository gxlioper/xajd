package xgxt.jygl.njjs;

public class NjjsJywebService {

	private NjjsJywebDAO dao = new NjjsJywebDAO();

	
	/**
	 * ѧ���걨���
	 * @param xh
	 * @param pkValue
	 * @return
	 * @throws Exception 
	 */
	public boolean saveXsbmsh(String xh ,String pkValue) throws Exception{
		
		
		return dao.runInsert("xg_jyweb_njjs_xsbmshb", new String[]{"xh","gwid"}, new String[]{xh,pkValue});
	}
}
