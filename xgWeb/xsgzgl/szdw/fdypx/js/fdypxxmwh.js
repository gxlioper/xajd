var gridSetting = {
		caption:"����Ա��ѵ��Ŀά��",
		pager:"pager",
		url:"szdw_fdypxxmwh.do?method=fdypxxmList&type=query",
		colList:[
		   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'1%',key:true,hidden:true},
		   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'20%',formatter:xhLink},
		   {label:'��ѵ�ص�',name:'pxdd', index: 'pxdd',width:'20%'},
            {label:'��֯����',name:'bmdm', index: 'bmdm',width:'20%',hidden:true},
			{label:'��֯����',name:'zzbm', index: 'zzbm',width:'20%'},
		   {label:'��ѵʱ��',name:'pxsj', index: 'pxsj',width:'20%'},
		   {label:'����ʱ��',name:'fbsj', index: 'fbsj',width:'20%'},
		   {label:'������',name:'fbr', index: 'fbr',width:'20%'},
            {label:'��ѵѧʱ',name:'pxxs', index: 'pxxs',width:'20%'}
		],
		sortname: "fbsj",
	 	sortorder: "desc"
	}
var gridSetting_xz = {
		caption:"����Ա��ѵ��Ŀ",
		pager:"pager",
		url:"szdw_fdypxxmwh.do?method=fdypxxmsqList&type=query",
		colList:[
		   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'1%',key:true,hidden:true},
		   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'��ѵ�ص�',name:'pxdd', index: 'pxdd',width:'20%'},
            {label:'��֯����',name:'bmdm', index: 'bmdm',width:'20%',hidden:true},
            {label:'��֯����',name:'zzbm', index: 'zzbm',width:'20%'},
		   {label:'��ѵʱ��',name:'pxsj', index: 'pxsj',width:'20%'},
		   {label:'����ʱ��',name:'fbsj', index: 'fbsj',width:'20%'},
		   {label:'������',name:'fbr', index: 'fbr',width:'20%'},
            {label:'��ѵѧʱ',name:'pxxs', index: 'pxxs',width:'20%'}
		],
		sortname: "fbsj",
	 	sortorder: "desc"
	}
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["xmdm"]+"\")'>"+cellValue+"</a>";
}
function add(){
	showDialog("������ѵ��Ŀ",700,350,"szdw_fdypxxmwh.do?method=fdypxxmAdd");
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog("�޸���ѵ��Ŀ",700,350,'szdw_fdypxxmwh.do?method=fdypxxmUpdate&xmdm='+rows[0]["xmdm"]);
	}
}

function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		
		confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
			//alert(ty);
			if(ty=="ok"){
				jQuery.post("szdw_fdypxxmwh.do?method=fdypxxmDelete",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
		
		
	}
	
}
function view(xmdm){
	showDialog("�鿴��ѵ��Ŀ",600,260,'szdw_fdypxxmwh.do?method=fdypxxmView&xmdm='+xmdm+"&view_type=1",null);
}
function save(method){
	if(yanzheng()){
		var url = "szdw_fdypxxmwh.do?method="+method+"&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}
}
function yanzheng(){
	var xmmc = jQuery("#xmmc").val();
	var pxdd = jQuery("#pxdd").val();
	var sqkg = jQuery("input[name='sqkg']:checked").val();
	var sqkssj = jQuery("#sqkssj").val();
	var sqjssj = jQuery("#sqjssj").val();
	var pxsj = jQuery("#pxsj").val();
	var pxjj = jQuery("#pxjj").val();
	
	if(xmmc==""){
		alertInfo("��ѵ��Ŀ���Ʋ���Ϊ�գ�");
	}else if(pxdd==""){
		alertInfo("����д��ѵ�ص�");
	}else if(sqkg==undefined){
		alertInfo("��ѡ����ѵ����");
	}
//	else if(sqkssj=="" || sqjssj ==""){
//		alertInfo("������������ֹʱ��");
//	}
	else if(!sqjssj ==""&&sqkssj>sqjssj){
		alertInfo("���뿪ʼʱ�䲻�ܴ��ڽ���ʱ��");
	}else if(pxsj==""){
		alertInfo("��������ѵʱ��");
	}else if(pxjj.length>=2000){
		alertInfo("��ѵ��鲻�ܳ���2000��");
	}else{
		return true;
	}
	return false;
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//ѡ����Ŀ
function xzxm(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�������Ŀ��");
	} else {
		frameElement.api.get('parentDialog').jQuery("#xzpxxm").load('szdw_fdypxxmwh.do?method=fdypxxmxzView&xmdm='+rows[0]["xmdm"],function(){
			iFClose();
		});
	}
}