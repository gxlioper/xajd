//��ͥ��Ϣ
var jtxx;
//��Ա��Ϣ
var dyxx;
//Ԥ����Ա
var ybdy;
//ѧ������
var xszz;
//Υ�ʹ���
var wjcf;
//��ҵ����
var jygl;
//��������
var pjpy;
//ѧ���ɼ�
var xscj;
//�ڹ���ѧ
var qgzx;
//��Ԣ����
var gygl;

//��ʾ��������
function showHiddenNr(id){
	if($(id)){
		if($(id).style.display == "none"){
			$(id).style.display = "";
		}else{
			$(id).style.display = "none";
		}
	}
}

//��ʾ��������
function showHiddenTable(id,tbid){
	
	if($(id)){
		var div_num = $(id).getElementsByTagName('div').length;

		for(var i=0;i<div_num;i++){
			var obj = $(id).getElementsByTagName('div')[i];
			var obj_id = obj.id;
			if(obj.style.display == "none"){
				obj.style.display = "";
			}else{
				obj.style.display = "none";
			}
		}
	}
	
	showHiddenNr(tbid);
}

//���ü�ͥ��Ϣ
function setJtxx(){
	
	//=================��ͥ������Ϣ===========================
	
	if($("p_lxdh1") && jtxx.lxdh1!=null){//��ϵ�绰
		$("p_lxdh1").innerHTML = jtxx.lxdh1;
	}
	if($("p_yb") && jtxx.yb!=null){//�ʱ�
		$("p_yb").innerHTML = jtxx.yb;
	}
	if($("p_jtszd") && jtxx.jtszd!=null){//��ͥ��ַ
		$("p_jtszd").innerHTML = jtxx.jtszd;
	}
	if($("p_jjzk") && jtxx.jjzk!=null){//����״��
		$("p_jjzk").innerHTML = jtxx.jjzk;
	}
	
	//=================��ͥ��Ա1===========================
	
	if($("p_jtcy1_xm") && jtxx.jtcy1_xm!=null){//��ͥ��Ա1����
		$("p_jtcy1_xm").innerHTML = jtxx.jtcy1_xm;
	}
	if($("p_jtcy1_gx") && jtxx.jtcy1_gx!=null){//��ͥ��Ա1��ϵ
		$("p_jtcy1_gx").innerHTML = jtxx.jtcy1_gx;
	}
	if($("p_jtcy1_nl") && jtxx.jtcy1_nl!=null){//��ͥ��Ա1��������
		$("p_jtcy1_nl").innerHTML = jtxx.jtcy1_nl;
	}
	if($("p_jtcy1_sfzh") && jtxx.jtcy1_sfzh!=null){//��ͥ��Ա1���֤��
		$("p_jtcy1_sfzh").innerHTML = jtxx.jtcy1_sfzh;
	}
	if($("p_jtcy1_mzmc") && jtxx.jtcy1_mzmc!=null){//��ͥ��Ա1����
		$("p_jtcy1_mzmc").innerHTML = jtxx.jtcy1_mzmc;
	}
	if($("p_jtcy1_zzmmmc") && jtxx.jtcy1_zzmmmc!=null){//��ͥ��Ա1������ò
		$("p_jtcy1_zzmmmc").innerHTML = jtxx.jtcy1_zzmmmc;
	}
	if($("p_jtcy1_zy") && jtxx.jtcy1_zy!=null){//��ͥ��Ա1ְҵ
		$("p_jtcy1_zy").innerHTML = jtxx.jtcy1_zy;
	}
	if($("p_jtcy1_zw") && jtxx.jtcy1_zw!=null){//��ͥ��Ա1ְ��
		$("p_jtcy1_zw").innerHTML = jtxx.jtcy1_zw;
	}
	if($("p_jtcy1_lxdh1") && jtxx.jtcy1_lxdh1!=null){//��ͥ��Ա1������λ�绰
		$("p_jtcy1_lxdh1").innerHTML = jtxx.jtcy1_lxdh1;
	}
	if($("p_jtcy1_lxdh2") && jtxx.jtcy1_lxdh2!=null){//��ͥ��Ա1���˵绰
		$("p_jtcy1_lxdh2").innerHTML = jtxx.jtcy1_lxdh2;
	}
	if($("p_jtcy1_gzdz") && jtxx.jtcy1_gzdz!=null){//��ͥ��Ա1������ַ
		$("p_jtcy1_gzdz").innerHTML = jtxx.jtcy1_gzdz;
	}
	
	//=================��ͥ��Ա2===========================
	
	if($("p_jtcy2_xm") && jtxx.jtcy2_xm!=null){//��ͥ��Ա2����
		$("p_jtcy2_xm").innerHTML = jtxx.jtcy2_xm;
	}
	if($("p_jtcy2_gx") && jtxx.jtcy2_gx!=null){//��ͥ��Ա2��ϵ
		$("p_jtcy2_gx").innerHTML = jtxx.jtcy2_gx;
	}
	if($("p_jtcy2_nl") && jtxx.jtcy2_nl!=null){//��ͥ��Ա2��������
		$("p_jtcy2_nl").innerHTML = jtxx.jtcy2_nl;
	}
	if($("p_jtcy2_sfzh") && jtxx.jtcy2_sfzh!=null){//��ͥ��Ա2���֤��
		$("p_jtcy2_sfzh").innerHTML = jtxx.jtcy2_sfzh;
	}
	if($("p_jtcy2_mzmc") && jtxx.jtcy2_mzmc!=null){//��ͥ��Ա2����
		$("p_jtcy2_mzmc").innerHTML = jtxx.jtcy2_mzmc;
	}
	if($("p_jtcy2_zzmmmc") && jtxx.jtcy2_zzmmmc!=null){//��ͥ��Ա2������ò
		$("p_jtcy2_zzmmmc").innerHTML = jtxx.jtcy2_zzmmmc;
	}
	if($("p_jtcy2_zy") && jtxx.jtcy2_zy!=null){//��ͥ��Ա2ְҵ
		$("p_jtcy2_zy").innerHTML = jtxx.jtcy2_zy;
	}
	if($("p_jtcy2_zw") && jtxx.jtcy2_zw!=null){//��ͥ��Ա2ְ��
		$("p_jtcy2_zw").innerHTML = jtxx.jtcy2_zw;
	}
	if($("p_jtcy2_lxdh1") && jtxx.jtcy2_lxdh1!=null){//��ͥ��Ա2������λ�绰
		$("p_jtcy2_lxdh1").innerHTML = jtxx.jtcy2_lxdh1;
	}
	if($("p_jtcy2_lxdh2") && jtxx.jtcy2_lxdh2!=null){//��ͥ��Ա2���˵绰
		$("p_jtcy2_lxdh2").innerHTML = jtxx.jtcy2_lxdh2;
	}
	if($("p_jtcy2_gzdz") && jtxx.jtcy2_gzdz!=null){//��ͥ��Ա2������ַ
		$("p_jtcy2_gzdz").innerHTML = jtxx.jtcy2_gzdz;
	}
	
	//=================��ͥ��Ա3===========================
	
	if($("p_jtcy3_xm") && jtxx.jtcy3_xm!=null){//��ͥ��Ա3����
		$("p_jtcy3_xm").innerHTML = jtxx.jtcy3_xm;
	}
	if($("p_jtcy3_gx") && jtxx.jtcy3_gx!=null){//��ͥ��Ա3��ϵ
		$("p_jtcy3_gx").innerHTML = jtxx.jtcy3_gx;
	}
	if($("p_jtcy3_nl") && jtxx.jtcy3_nl!=null){//��ͥ��Ա3��������
		$("p_jtcy3_nl").innerHTML = jtxx.jtcy3_nl;
	}
	if($("p_jtcy3_sfzh") && jtxx.jtcy3_sfzh!=null){//��ͥ��Ա3���֤��
		$("p_jtcy3_sfzh").innerHTML = jtxx.jtcy3_sfzh;
	}
	if($("p_jtcy3_mzmc") && jtxx.jtcy3_mzmc!=null){//��ͥ��Ա3����
		$("p_jtcy3_mzmc").innerHTML = jtxx.jtcy3_mzmc;
	}
	if($("p_jtcy3_zzmmmc") && jtxx.jtcy3_zzmmmc!=null){//��ͥ��Ա3������ò
		$("p_jtcy3_zzmmmc").innerHTML = jtxx.jtcy3_zzmmmc;
	}
	if($("p_jtcy3_zy") && jtxx.jtcy3_zy!=null){//��ͥ��Ա3ְҵ
		$("p_jtcy3_zy").innerHTML = jtxx.jtcy3_zy;
	}
	if($("p_jtcy3_zw") && jtxx.jtcy3_zw!=null){//��ͥ��Ա3ְ��
		$("p_jtcy3_zw").innerHTML = jtxx.jtcy3_zw;
	}
	if($("p_jtcy3_lxdh1") && jtxx.jtcy3_lxdh1!=null){//��ͥ��Ա3������λ�绰
		$("p_jtcy3_lxdh1").innerHTML = jtxx.jtcy3_lxdh1;
	}
	if($("p_jtcy3_lxdh2") && jtxx.jtcy3_lxdh2!=null){//��ͥ��Ա3���˵绰
		$("p_jtcy3_lxdh2").innerHTML = jtxx.jtcy3_lxdh2;
	}
	if($("p_jtcy3_gzdz") && jtxx.jtcy3_gzdz!=null){//��ͥ��Ա3������ַ
		$("p_jtcy3_gzdz").innerHTML = jtxx.jtcy3_gzdz;
	}
}

//���õ��Ž���
function setDtjs(){
	
	//=================��ʽ��Ա===========================
	if($("p_dyxx_xn") && dyxx.xn!=null){//ѧ��
		$("p_dyxx_xn").innerHTML = dyxx.xn;
	}
	if($("p_dyxx_xqmc") && dyxx.xqmc!=null){//ѧ��
		$("p_dyxx_xqmc").innerHTML = dyxx.xqmc;
	}
	if($("p_dyxx_nd") && dyxx.nd!=null){//���
		$("p_dyxx_nd").innerHTML = dyxx.nd;
	}
	if($("p_dyxx_rdsj") && dyxx.rdsj!=null){//�뵳ʱ��
		$("p_dyxx_rdsj").innerHTML = dyxx.rdsj;
	}
	if($("p_dyxx_bz") && dyxx.bz!=null){//��ע
		$("p_dyxx_bz").innerHTML = dyxx.bz;
	}
	//=================Ԥ����Ա===========================
	if($("p_ybdy_xn") && ybdy.xn!=null){//ѧ��
		$("p_ybdy_xn").innerHTML = ybdy.xn;
	}
	if($("p_ybdy_xqmc") && ybdy.xqmc!=null){//ѧ��
		$("p_ybdy_xqmc").innerHTML = ybdy.xqmc;
	}
	if($("p_ybdy_nd") && ybdy.nd!=null){//���
		$("p_ybdy_nd").innerHTML = ybdy.nd;
	}
	if($("p_ybdy_zzzt") && ybdy.zzzt!=null){//����ʱ��
		if(ybdy.zzzt == "yes"){
			$("p_ybdy_zzzt").innerHTML = "��";
		}else{
			$("p_ybdy_zzzt").innerHTML = "��";
		}
	}
	if($("p_ybdy_kssj") && ybdy.kssj!=null){//��ʼʱ��
		$("p_ybdy_kssj").innerHTML = ybdy.kssj;
	}
	if($("p_ybdy_jssj") && ybdy.jssj!=null){//����ʱ��
		$("p_ybdy_jssj").innerHTML = ybdy.jssj;
	}
	if($("p_ybdy_bz") && ybdy.bz!=null){//��ע
		$("p_ybdy_bz").innerHTML = ybdy.bz;
	}
}

//���ù�Ԣ����
function setGygl(){
	
	if($("p_gygl_ldmc") && gygl.ldmc!=null){//ѧ��
		$("p_gygl_ldmc").innerHTML = gygl.ldmc;
	}
	if($("p_gygl_cs") && gygl.cs!=null){//ѧ��
		$("p_gygl_cs").innerHTML = gygl.cs;
	}
	if($("p_gygl_qsh") && gygl.qsh!=null){//���
		$("p_gygl_qsh").innerHTML = gygl.qsh;
	}
	if($("p_gygl_cwh") && gygl.cwh!=null){//�뵳ʱ��
		$("p_gygl_cwh").innerHTML = gygl.cwh;
	}
	if($("p_gygl_qsdh") && gygl.qsdh!=null){//��ע
		$("p_gygl_qsdh").innerHTML = gygl.qsdh;
	}
	if($("p_gygl_sfbz") && gygl.sfbz!=null){//��ע
		$("p_gygl_sfbz").innerHTML = gygl.sfbz;
	}
}

//����������������
function setPjpy(){

	var jspName = pjpy[0].jspName;

	if(pjpy.length >0){
		if(jspName == "area.jsp"){//��ʾ��
			setPjpyArea();
		}else if(jspName == "list.jsp"){//�б�
			setPjpyList();
		}
	}
}

//��������������ʾ������
function setPjpyArea(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_pjpy_Info";
	}
	
	for(var i=1;i<pjpy.length;i++){
		var xmid = "div_"+pjpy[i].xmmc;
		var tbid = "tb_"+pjpy[i].xmmc;
		var divHtml = "";
		
		var pjsj = "";
		
		if(pjpy[i].pjxn!=null && pjpy[i].pjxn!="��"){
			pjsj+="ѧ�꣺"+pjpy[i].pjxn;
		}
		if(pjpy[i].xqmc!=null && pjpy[i].pjxq!="��"){
			pjsj+="ѧ�ڣ�"+pjpy[i].xqmc;
		}
		if(pjpy[i].pjnd!=null && pjpy[i].pjnd!="��"){
			pjsj+="��ȣ�"+pjpy[i].pjnd;
		}
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"3\"><span>";
			divHtml+= "����������Ϣ";
			divHtml+= "(";
			divHtml+= pjpy[i].xmmc;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='30%'>����ʱ��</td>";
			divHtml+= "<td width='30%'>����ʱ��</td>";
			divHtml+= "<td>��Ŀ���</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+pjsj+"</td>";
			divHtml+= "<td width='30%'>"+pjpy[i].sqsj+"</td>";
			divHtml+= "<td>"+pjpy[i].xmje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+pjsj+"</td>";
			divHtml+= "<td width='30%'>"+pjpy[i].sqsj+"</td>";
			divHtml+= "<td>"+pjpy[i].xmje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//�������������б�����
function setPjpyList(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_pjpy_Info";
	}
	var divHtml = "";
	
	var xmid = "div_pjpy_thead";
	var tbid = "tb_pjpy_thead";
		
	divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
	divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"4\"><span>";
	divHtml+= "����������Ϣ";
	divHtml+= "</span></th></tr></thead>";
	divHtml+= "<tbody id='"+tbid+"'><tr>";
	divHtml+= "<td width='25%'>��Ŀ����</td>";
	divHtml+= "<td width='25%'>����ʱ��</td>";
	divHtml+= "<td width='25%'>����ʱ��</td>";
	divHtml+= "<td>��Ŀ���</td>";
	divHtml+= "</tr>";

			
	for(var i=1;i<pjpy.length;i++){
			
		var pjsj = "";
		
		if(pjpy[i].pjxn!=null && pjpy[i].pjxn!="��"){
			pjsj+="ѧ�꣺"+pjpy[i].pjxn;
		}
		if(pjpy[i].xqmc!=null && pjpy[i].pjxq!="��"){
			pjsj+="ѧ�ڣ�"+pjpy[i].xqmc;
		}
		if(pjpy[i].pjnd!=null && pjpy[i].pjnd!="��"){
			pjsj+="��ȣ�"+pjpy[i].pjnd;
		}

		divHtml+= "<tr>";
		divHtml+= "<td width='25%'>"+pjpy[i].xmmc+"</td>";
		divHtml+= "<td width='25%'>"+pjsj+"</td>";
		divHtml+= "<td width='25%'>"+pjpy[i].sqsj+"</td>";
		divHtml+= "<td>"+pjpy[i].xmje+"</td>";
		divHtml+= "</tr>";		
	}
	
	divHtml+= "</tbody></table>";
	$(div_id).innerHTML+=divHtml;
}

//����ѧ���ɼ�����
function setXscj(){

	var jspName = xscj[0].jspName;

	if(xscj.length >0){
		if(jspName == "groupByXn.jsp"){//ѧ��ɼ�
			setXscjByXn();
		}else if(jspName == "groupByXq.jsp"){//ѧ�ڳɼ�
			setXscjByXq();
		}
	}
}

//������ѧ��ѧ��ɼ�
function setXscjByXn(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_xscj_Info";
	}
	
	for(var i=1;i<xscj.length;i++){
		var xmid = "div_"+xscj[i].xn;
		var tbid = "tb_"+xscj[i].xn;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"3\"><span>";
			divHtml+= "�ɼ���Ϣ";
			divHtml+= "(";
			divHtml+= "ѧ�꣺"+xscj[i].xn;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='30%'>ѧ��</td>";
			divHtml+= "<td width='30%'>�γ�����</td>";
			divHtml+= "<td>�ɼ�</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+xscj[i].xqmc+"</td>";
			divHtml+= "<td width='30%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='30%'>"+xscj[i].xqmc+"</td>";
			divHtml+= "<td width='30%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//������ѧ��ѧ�ڳɼ�
function setXscjByXq(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_xscj_Info";
	}
	
	for(var i=1;i<xscj.length;i++){
		var xmid = "div_"+xscj[i].xn+xscj[i].xq;
		var tbid = "tb_"+xscj[i].xn+xscj[i].xq;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"2\"><span>";
			divHtml+= "�ɼ���Ϣ";
			divHtml+= "(";
			divHtml+= "ѧ�꣺"+xscj[i].xn+" ѧ�ڣ�"+xscj[i].xqmc;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='50%'>�γ�����</td>";
			divHtml+= "<td>�ɼ�</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+xscj[i].kcmc+"</td>";
			divHtml+= "<td>"+xscj[i].cj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//�����ڹ���ѧ����
function setQgzx(){

	var jspName = qgzx[0].jspName;

	if(qgzx.length >0){
		if(jspName == "qggwOnly.jsp"){//�ڹ���λ
			setQggw();
		}else if(jspName == "cjffOnly.jsp"){//��𷢷�
			setCjff();
		}
	}
}

//�����ڹ���λ
function setQggw(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_qgzx_Info";
	}
	
	for(var i=1;i<qgzx.length;i++){
		var xmid = "div_"+qgzx[i].gwdm;
		var tbid = "tb_"+qgzx[i].gwdm;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"2\"><span>";
			divHtml+= "�ڹ���ѧ��Ϣ";
			divHtml+= "(";
			divHtml+= "��λ���ƣ�"+qgzx[i].gwdm;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='50%'>��λ�걨ʱ��</td>";
			divHtml+= "<td>ѧ������ʱ��</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+qgzx[i].gwsbsj+"</td>";
			divHtml+= "<td>"+qgzx[i].sqsj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='50%'>"+qgzx[i].gwsbsj+"</td>";
			divHtml+= "<td>"+qgzx[i].sqsj+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//���ó�𷢷�
function setCjff(){

	var div_id = "div_otherInfo";
	if(!$(div_id)){
		div_id = "div_qgzx_Info";
	}
	
	for(var i=1;i<qgzx.length;i++){
		var xmid = "div_"+qgzx[i].gwdm+qgzx[i].sqsj;
		var tbid = "tb_"+qgzx[i].gwdm+qgzx[i].sqsj;
		var divHtml = "";
		
		if(!$(xmid)){
			divHtml= "<div id=\""+xmid+"\">"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"5\"><span>";
			divHtml+= "�ڹ���ѧ��Ϣ";
			divHtml+= "(";
			divHtml+= "��λ���ƣ�"+qgzx[i].gwdm;
			divHtml+= "       ";
			divHtml+= "��λ�걨ʱ�䣺"+qgzx[i].sqsj;
			divHtml+= ")";
			divHtml+= "</span></th></tr></thead>";
			divHtml+= "<tbody id='"+tbid+"'><tr>";
			divHtml+= "<td width='20%'>ѧ��</td>";
			divHtml+= "<td width='20%'>ѧ��</td>";
			divHtml+= "<td width='20%'>���</td>";
			divHtml+= "<td width='20%'>�·�</td>";
			divHtml+= "<td>���</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='20%'>"+qgzx[i].xn+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].xqmc+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].nd+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].yf+"</td>";
			divHtml+= "<td>"+qgzx[i].cjje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			divHtml+= "</div>"
				
			$(div_id).innerHTML+=divHtml;
			
		}else{
			divHtml+= "<div>"
			divHtml+= "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
			divHtml+= "<tbody><tr>";
			divHtml+= "<td width='20%'>"+qgzx[i].xn+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].xqmc+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].nd+"</td>";
			divHtml+= "<td width='20%'>"+qgzx[i].yf+"</td>";
			divHtml+= "<td>"+qgzx[i].cjje+"</td>";
			divHtml+= "</tr></tbody>";
			divHtml+= "</table>"
			divHtml+= "</div>"
			
			$(xmid).innerHTML+=divHtml;
		}
	}
}

//����������ϸ��Ϣ
function setXszz() {
	var html = "";
	if (xszz[1].zzxxPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < xszz.length; i++) {
			html += "<tr>";
			html += "<td width='16%'>" + xszz[i].xmmc + "</td>";
			html += "<td width='16%'>" + xszz[i].sqsj + "</td>";
			html += "<td width='16%'>" + xszz[i].sqzq + "</td>";
			html += "<td width='16%'>" + xszz[i].xmzzjb + "</td>";
			html += "<td width='16%'>" + xszz[i].xmzzje + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		if ($("zzxx_tbody")) {
			$("zzxx_tbody").innerHTML = html;
		}
		
	} else {
		if (xszz[1].zzglPage == "yes") {
			var div_id = "div_otherInfo";
			if(!$(div_id)){
				div_id = "div_xszz_Info";
			}
			
			for (var i = 2; i < xszz.length; i++) {
			
				var xmid = "div_" + xszz[i].xmmc;
				var tbid = "tb_"+xszz[i].xmmc;
			
				var divHtml = "";
				if (!$(xmid)) {
					divHtml = "<div id=\"" + xmid + "\">";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"4\"><span>";
					divHtml += "ѧ������";
					divHtml += "(";
					divHtml += xszz[i].xmmc;
					divHtml += ")";
					divHtml += "</span></th></tr></thead>";
					divHtml += "<tbody id='"+tbid+"'><tr>";
					divHtml += "<td width='16%'>\u7533\u8bf7\u65f6\u95f4</td>";
					divHtml += "<td width='16%'>\u7533\u8bf7\u5468\u671f</td>";
					divHtml += "<td width='16%'>\u9879\u76ee\u7ea7\u522b</td>";
					divHtml += "<td width='16%'>\u9879\u76ee\u91d1\u989d</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='16%'>" + xszz[i].sqsj + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].sqzq + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzjb + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzje + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					divHtml += "</div>";
					$(div_id).innerHTML += divHtml;
				} else {
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='16%'>" + xszz[i].sqsj + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].sqzq + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzjb + "</td>";
					divHtml += "<td width='16%'>" + xszz[i].xmzzje + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					$(xmid).innerHTML += divHtml;
				}
			}
		}
	}
}

//����Υ�ʹ�����Ϣ
function setWjcf() {
	var html = "";
	if (wjcf[1].wjxxPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < wjcf.length; i++) {
			html += "<tr>";
			html += "<td width='12%'>" + wjcf[i].xn + "</td>";
			html += "<td width='12%'>" + wjcf[i].xqmc + "</td>";
			html += "<td width='12%'>" + wjcf[i].nd + "</td>";
			html += "<td width='12%'>" + wjcf[i].cflbmc + "</td>";
			html += "<td width='12%'>" + wjcf[i].cfyymc + "</td>";
			html += "<td width='12%'>" + wjcf[i].cfsj + "</td>";
			html += "<td width='12%'>" + wjcf[i].wjsj + "</td>";
			html += "<td width='12%'>" + wjcf[i].cxjg + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		if ($("wjxx_tbody")) {
			$("wjxx_tbody").innerHTML = html;
		}
	} else {
		if (wjcf[1].wjglPage == "yes") {
			var div_id = "div_otherInfo";
			if(!$(div_id)){
				div_id = "div_wjcf_Info";
			}
				
			for (var i = 2; i < wjcf.length; i++) {
				
				var xmid = "div_" + wjcf[i].cflbmc;
				var tbid = "tb_"+wjcf[i].cflbmc;
				var divHtml = "";
				if (!$(xmid)) {
					divHtml = "<div id=\"" + xmid + "\">";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<thead><tr onclick=\"showHiddenTable('"+xmid+"','"+tbid+"')\" style=\"cursor: hand\"><th colspan=\"7\"><span>";
					divHtml += "Υ����Ϣ";
					divHtml += "(";
					divHtml += wjcf[i].cflbmc;
					divHtml += ")";
					divHtml += "</span></th></tr></thead>";
					divHtml += "<tbody id='"+tbid+"'><tr>";
					divHtml += "<td width='12%'>\u5b66\u5e74</td>";
					divHtml += "<td width='12%'>\u5b66\u671f</td>";
					divHtml += "<td width='12%'>\u5e74\u5ea6</td>";
					divHtml += "<td width='12%'>\u5904\u5206\u539f\u56e0</td>";
					divHtml += "<td width='12%'>\u5904\u5206\u65f6\u95f4</td>";
					divHtml += "<td width='12%'>\u8fdd\u7eaa\u65f6\u95f4</td>";
					divHtml += "<td width='12%'>\u64a4\u6d88\u7ed3\u679c</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='12%'>" + wjcf[i].xn + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].xqmc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].nd + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfyymc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].wjsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cxjg + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					divHtml += "</div>";
					$(div_id).innerHTML += divHtml;
				} else {
					divHtml += "<div>";
					divHtml += "<table border=\"0\" class=\"formlist\" align=\"center\" style=\"width: 100%\">";
					divHtml += "<tbody><tr>";
					divHtml += "<td width='12%'>" + wjcf[i].xn + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].xqmc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].nd + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfyymc + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cfsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].wjsj + "</td>";
					divHtml += "<td width='12%'>" + wjcf[i].cxjg + "</td>";
					divHtml += "</tr></tbody>";
					divHtml += "</table>";
					divHtml += "</div>";
					$(xmid).innerHTML += divHtml;
				}
			}
		}
	}
}

//������������Ϣ
function setXljk() {
	var html = "";
	if (xljk[1].xlcsPage != null && xljk[1].xlcsPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < xljk.length; i++) {
			if (xljk[i].xslx == "xlcs") {
				html += "<tr>";
				html += "<td width='33%'>" + xljk[i].xlcsxmmc + "</td>";
				html += "<td width='33%'>" + xljk[i].cssj + "</td>";
				html += "<td width='33%'>" + xljk[i].xlcsjgmc + "</td>";
				html += "</tr>";
			}
		}
		html += "</table>";
		if ($("xlcs_tbody")) {
			$("xlcs_tbody").innerHTML = html;
		}
	}
	html = "";
	if (xljk[1].tsxsPage != null && xljk[1].tsxsPage == "yes") {
		html += "<table summary='' class='dateline' align='' width='100%'>";
		for (i = 2; i < xljk.length; i++) {
			if (xljk[i].xslx == "tsxs") {
				html += "<tr>";
				html += "<td width='33%'>" + xljk[i].tbgxxslbmc + "</td>";
				html += "<td width='33%'>" + xljk[i].zxqjzysj + "</td>";
				html += "<td width='33%'>" + xljk[i].xytbgxcs + "</td>";
				html += "</tr>";
			}
		}
		html += "</table>";
		$("tsxs_tbody").innerHTML = html;
	}
}

//����������ϸ��Ϣ
function setJygl() {
	if ($("bys_rxnf") && jygl.rxnf != null) {//��ѧ���
		$("bys_rxnf").innerHTML = jygl.rxnf;
	}
	if ($("bys_bynf") && jygl.bynf != null) {//��ҵ���
		$("bys_bynf").innerHTML = jygl.bynf;
	}
	if ($("jyxy_dwmc") && jygl.dwmc != null) {//��λ����
		$("jyxy_dwmc").innerHTML = jygl.dwmc;
	}
	if ($("jyxy_dwxzmc") && jygl.dwxzmc != null) {//��λ��������
		$("jyxy_dwxzmc").innerHTML = jygl.dwxzmc;
	}
	if ($("jyxy_zgdwmc") && jygl.zgdwmc != null) {//���ܵ�λ����
		$("jyxy_zgdwmc").innerHTML = jygl.zgdwmc;
	}
	if ($("jyxy_bdkssj") && jygl.bdkssj != null) {//������ʼʱ��
		$("jyxy_bdkssj").innerHTML = jygl.bdkssj;
	}
	if ($("jyxy_bdjssj") && jygl.bdjssj != null) {//��������ʱ��
		$("jyxy_bdjssj").innerHTML = jygl.bdjssj;
	}
}

