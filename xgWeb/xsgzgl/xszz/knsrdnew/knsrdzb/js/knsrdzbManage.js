jQuery(document).ready(function(){ 
	changeSqkg();
});

//更新岗位申请
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
		showAlertDivLayer("请设置申请开关!");
		return false;
	}
	var splc = jQuery("#splc").val();
	var sqkg = jQuery("[name=sqkg]:checked").val();
	
	if ("1"==sqkg && (splc == "" || splc == null)){
		showAlertDivLayer("请选择审核流程!");
		return false;
	}
	if("1"==sqkg && (jQuery("#sqjssj").val()=="" || jQuery("#sqkssj").val()=="")){
		showAlertDivLayer("开放时间和结束时间必须填写!");		
		return false;
	}
	var url = "rcsw_dxsylbx_jcszgl.do?method=saveYlbxJcsz";
	ajaxSubFormWithFun("ylbxJcszForm",url,function(data){
		showAlertDivLayer(data["message"]);
	});
	
}



function add(){
	var url = "xg_xszz_knsrd_knzbgl.do?method=addKnsrdzb";
	var title = "增加困难生认定指标设置";
	showDialog(title,800,530,url);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xg_xszz_knsrd_knzbgl.do?method=updateKnsrdzb&zbid='+rows[0]["zbid"];
		var title = "修改困难生认定指标设置";
		showDialog(title,800,530,url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xg_xszz_knsrd_knzbgl.do?method=delKnsrdzb", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的困难生认定指标已经使用不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

var gridSetting = {
		caption:"困难生认定指标列表",
		pager:"pager",
		url:"xg_xszz_knsrd_knzbgl.do?method=knsrdzbManage&type=query",
		colList:[
		   {label:'key',name:'zbid', index: 'zbid',key:true ,hidden:true},		
		   {label:'名称',name:'zbmc', index: 'zbmc',width:'50%'},
		   {label:'启用',name:'qyzt', index: 'qyzt',hidden:true},	
		   {label:'启用状态',name:'qyztmc', index: 'qyztmc',width:'50%'}
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
		showAlertDivLayer("请选择一条您要启用的记录！");
	} else {
		
		showConfirmDivLayer("您确定要启用选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xg_xszz_knsrd_knzbgl.do?method=qyKnsrdzb", {
					zbid : rows[0]["zbid"]
				}, function(data) {
					if(data["message"]=="启用困难生认定指标成功！" ){
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
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xg_xszz_knsrd_knzbgl.do?method=copyKnsrdzb&zbid='+rows[0]["zbid"];
		var title = "复制困难生认定指标";
		showDialog(title,450,190,url);
	}
}

function view(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		var url = 'xg_xszz_knsrd_knzbgl.do?method=viewKnsrdzb&zbid='+rows[0]["zbid"];
		var title = "查看困难生认定指标设置";
		showDialog(title,760,525,url);
	}
}



