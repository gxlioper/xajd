//��ʼ����ѯ
var bj_gridSetting = {
		caption:"�༶��Ϣ�б�",
		pager:"pager",
		url:"xsxx_bjgl.do?method=bjList&type=query",
		colList:[
		   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},//,formatter:xhLink
		   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'8%'},
		   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
		   {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
		   {label:'����',name:'bjmc', index: 'bjmc',width:'5%',formatter:xhLink},
		   {label:'����',name:'bjdm', index: 'bjdm',key:true,hidden:true}
		],
		sortname: "bjdm",
	 	sortorder: "asc"
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='fdyrz_sq(\""+rowObject["bjdm"]+"\")'>����</a>";
}

function goFdyrzsq(){
	refreshForm("szdw_fdyrz_sq.do?method=fdyrzsq");
}
function query(){
	var map = {};
	map["nj"] = jQuery("#nj").val();
	map["xydm"] = jQuery("#xy").val();
	map["zydm"] = jQuery("#zy").val();
	jQuery("#dataTablebj").reloadGrid(map);
}
//����Ա��ְ
function fdyrz_sq(bjdm){
	var xzbj_text = jQuery("#xzbj_text").val();
	var xzbj_text_len =xzbj_text.split(",").length;
	var zjz = jQuery("#zjz").val();
	if(zjz==""){
		alertInfo("��ѡ��ר��ְ��");
	}else if(zjz=="��ְ"&& xzbj_text_len>2){
		alertInfo("��ְ����Ա������������༶��");
	}else{
		jQuery("#xzbj_text").val(jQuery("#xzbj_text").val()+","+bjdm);
		jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj&bjdm="+bjdm+"&type=sq");
	}
}