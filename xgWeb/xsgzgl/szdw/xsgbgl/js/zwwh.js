var gridSetting = {
		caption:"ѧ���ɲ�ְ���б�",
		pager:"pager",
		url:"szdw_xsgb_zwwh.do?method=zwwhList&type=query",
		colList:[
		   {label:'zwid',name:'zwid', index: 'zwid',width:'20%',key:true,hidden:true},
		   {label:'ְ������',name:'zwmc', index: 'zwmc',width:'20%',formatter:zwmcLink},
		   {label:'ְ��ְ��',name:'zwzz', index: 'zwzz',width:'20%'},
		   {label:'ְ������',name:'lxmc', index: 'lxmc',width:'20%'},
		   {label:'��ע',name:'bz', index: 'bz',width:'20%'}
		],
		sortname: "lxdm",
	 	sortorder: "desc"
}
function add(){
	showDialog("����ѧ���ɲ�ְ��",700,400,"szdw_xsgb_zwwh.do?method=zwwhAdd");
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog("�޸�ѧ���ɲ�ְ��",700,300,'szdw_xsgb_zwwh.do?method=zwwhUpdate&zwid='+rows[0]["zwid"]);
	}
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
			//alert(ty);
			if(ty=="ok"){
				jQuery.post("szdw_xsgb_zwwh.do?method=zwwhDelete",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}
function save(method){
	if(yanzheng()){
		var url = "szdw_xsgb_zwwh.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}
}
function yanzheng(){
	var zwmc = jQuery("#zwmc").val();
	var lxdm = jQuery("#lxdm").val();
	var zwzz = jQuery("#zwzz").val();
	var bz = jQuery("#bz").val();
	if(zwmc==""){
		alertInfo("������ְ������");
	}else if(lxdm==""){
		alertInfo("��ѡ��ְ������");
	}else if(zwzz==""){
		alertInfo("������ְ��ְ��");
	}else if(zwzz.length>=200){
		alertInfo("ְ��ְ���ܳ���200��");
	}else if(bz.length>=200){
		alertInfo("��ע���ܳ���200��");
	}else{
		return true;
	}
	return false;
}

//ְ����������
function zwmcLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["zwid"]+"\")'>"+cellValue+"</a>";
}

//�鿴
function view(zwid){
	showDialog("�鿴ѧ���ɲ�ְ��",700,300,'szdw_xsgb_zwwh.do?method=zwck&zwid='+zwid);
}