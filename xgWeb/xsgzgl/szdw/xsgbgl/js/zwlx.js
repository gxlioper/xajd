var gridSetting = {
		caption:"ѧ���ɲ�ְ������",
		pager:"pager",
		url:"szdw_xsgb_zwlx.do?method=zwlxList&type=query",
		colList:[
		   {label:'ְ������',name:'lxdm', index: 'lxdm',width:'1%',key:true,hidden:true},
		   {label:'��������',name:'lxmc', index: 'lxmc',width:'30%',formatter:zwlxLink},
		   {label:'��ע',name:'bz', index: 'bz',width:'30%'},
		   {label:'��������',name:'splc', index: 'splc',width:'40%'}
		],
		sortname: "lxdm",
	 	sortorder: "desc"
}
function add(){
	showDialog("����ѧ���ɲ�ְ������",700,300,"szdw_xsgb_zwlx.do?method=zwlxAdd");
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog("�޸�ѧ���ɲ�ְ������",700,300,'szdw_xsgb_zwlx.do?method=zwlxUpdate&lxdm='+rows[0]["lxdm"]);
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
				jQuery.post("szdw_xsgb_zwlx.do?method=zwlxDelete",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}
function save(method){
	if(yanzheng()){
		var url = "szdw_xsgb_zwlx.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			alertInfo(data["message"],function(ty){
				//alert(ty);
				if(ty=="ok"){
					if (parent.window){
						refershParent();
					}
				}
			});
		});
	}
}
function yanzheng(){
	var lxmc = jQuery("#lxmc").val();
	var bz = jQuery("#bz").val();
	var splc = jQuery("#splc").val();
	if(lxmc==""){
		alertInfo("ְ���������Ʋ���Ϊ�գ�");
	}else if(bz.length>=200){
		alertInfo("��ע���ܳ���200�֣�");
	}else if(splc == "" ){
		alertInfo("�������̲���Ϊ�գ�");
	}else{
		return true;
	}
	return false;
}

//ѧ������
function zwlxLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["lxdm"]+"\")'>"+cellValue+"</a>";
}

//�鿴
function view(lxdm){
	showDialog("ѧ���ɲ����Ͳ鿴",700,300,'szdw_xsgb_zwlx.do?method=zwlxck&lxdm='+lxdm);
}