//��ʼ����ѯ
var gridSetting = {
		caption:"��λѡ���б�",
		pager:"pager",
		multiselect:false,
		url:"xsgwsqnew_sq.do?method=wdgwxzCx&type=query",
		colList:[
		   {label:'ѧ��',name:'xn', index: 'xn',hidden:true},
		   {label:'a',name:'gwdm', index: 'gwdm',hidden:true},
		   {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
		   {label:'��λ����',name:'gwmc', index: 'gwmc',width:'10%'},
		   {label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'10%'},
		   {label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'10%'},
		   {label:'��������',name:'xqrs', index: 'xqrs',width:'10%'},
		   {label:'��������',name:'sqrs', index: 'sqrs',width:'10%'},
		   {label:'��������',name:'knss', index: 'knss',width:'10%'},
		   {label:'�ڸ�����',name:'zgrs', index: 'zgrs',width:'10%'},
		   {name:'sfksq', index: 'sfksq',hidden:true},
		   {label:'����',name:'xh', index: '',width:'15%',noSort:true,formatter:czsz}
		],
		//dblclick:function(rowObject){
			//ѡ���λ
		//	xzGw(rowObject);
		//},
		sortname: "xn,sfksq",
	 	sortorder: "desc"
	}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function czsz(cellValue,row){
	if("N"==row["sfksq"]){
	return "<button type='button' class='btn_01' onclick=\"warn();\">ѡ��</button>";
	}
	else{
	 return "<label class='btn_01' onclick=\"xzGw('"+row["gwdm"]+"');\">ѡ��</label>";
	}
	
}
function warn(){
	showAlert("�ø�λ��������������������ѡ��");
	return false;
}

//ѡ���λ
function xzGw(gwdm){
	var gotoPath = jQuery("#gotoPath").val();
	var loadid = jQuery("#loadid").val();
	//alert(gotoPath+loadid);
	if (gotoPath.split("?").length > 1){
		gotoPath = gotoPath+"&gwdm="+gwdm;
	} else {
		gotoPath = gotoPath+"?gwdm="+gwdm;
	}
	var api = frameElement.api, W = api.opener;
	
	if (api){
		if (api.get('childDialog')){
			api.get('parentDialog').jQuery("#"+loadid).load(gotoPath);
			//api.reload(api.get('parentDialog') ,gotoPath);
		} else {
			//W.location=gotoPath;	
			W.jQuery("#"+loadid).load(gotoPath);
		}
	} else if (parent.window){
		window.parent.jQuery("#"+loadid).load(gotoPath);
	}
	
	iFClose();
}
