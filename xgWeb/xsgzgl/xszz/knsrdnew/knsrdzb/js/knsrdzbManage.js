jQuery(document).ready(function(){ 
	changeSqkg();
});

//���¸�λ����
function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled",false);
	}else if("0"==sqkg){
		jQuery("table select,input:not(input:radio[name=sqkg])").attr("disabled","disabled");
		
	}
}

function saveJcsz(){
	
	var sqkglength = jQuery("[name=sqkg]:checked").length;
	if(sqkglength==0){
		showAlertDivLayer("���������뿪��!");
		return false;
	}
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("��ѡ���������!");
		return false;
	}
	if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
		showAlertDivLayer("����ʱ��ͽ���ʱ�������д!");		
		return false;
	}
	var url = "rcsw_dxsylbx_jcszgl.do?method=saveYlbxJcsz";
	ajaxSubFormWithFun("ylbxJcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
	
}



function add(){
	var url = "xg_xszz_knsrd_knzbgl.do?method=addKnsrdzb";
	var title = "�����������϶�ָ������";
	showDialog(title,800,530,url);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xg_xszz_knsrd_knzbgl.do?method=updateKnsrdzb&zbid='+rows[0]["zbid"];
		var title = "�޸��������϶�ָ������";
		showDialog(title,800,530,url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xg_xszz_knsrd_knzbgl.do?method=delKnsrdzb", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="���������϶�ָ���Ѿ�ʹ�ò���ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

var gridSetting = {
		caption:"�������϶�ָ���б�",
		pager:"pager",
		url:"xg_xszz_knsrd_knzbgl.do?method=knsrdzbManage&type=query",
		colList:[
		   {label:'key',name:'zbid', index: 'zbid',key:true ,hidden:true},		
		   {label:'����',name:'zbmc', index: 'zbmc',width:'50%'},
		   {label:'����',name:'qyzt', index: 'qyzt',hidden:true},	
		   {label:'����״̬',name:'qyztmc', index: 'qyztmc',width:'50%'}
		],
		sortname: "zbid",
	 	sortorder: "asc"
	} 


jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function query(){
	var map = {};
	jQuery("#dataTable").reloadGrid(map);
}

function openQy() {
	var rows = jQuery("#dataTable").getSeletRow();
	//var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
	} else {
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xg_xszz_knsrd_knzbgl.do?method=qyKnsrdzb", {
					zbid : rows[0]["zbid"]
				}, function(data) {
					if(data["message"]=="�����������϶�ָ��ɹ���" ){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
			    			 jQuery("#dataTable").reloadGrid();
							}});
			    	 }else{
			    		 showAlertDivLayer(data["message"]);
			    	 }
					//jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function copy(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xg_xszz_knsrd_knzbgl.do?method=copyKnsrdzb&zbid='+rows[0]["zbid"];
		var title = "�����������϶�ָ��";
		showDialog(title,450,190,url);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		var url = 'xg_xszz_knsrd_knzbgl.do?method=viewKnsrdzb&zbid='+rows[0]["zbid"];
		var title = "�鿴�������϶�ָ������";
		showDialog(title,760,525,url);
	}
}



