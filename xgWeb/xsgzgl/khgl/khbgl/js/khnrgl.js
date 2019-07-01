var checkId="khnr-fzzdf-pflx";


jQuery(function(){
var autoSetting1 = {
		dataTable:"xg_khgl_tk_zbx",
		dataField:"yjzb",
		dataFieldKey:"",
		dataFieldKeyId:"",
		scrollHeight:135										
	}
var autoSetting2 = {
		dataTable:"xg_khgl_tk_zbx",
		dataField:"ejzb",
		dataFieldKey:"",
		dataFieldKeyId:"",
		scrollHeight:135										
	}

	// 模糊搜索下拉指标名称
	jQuery("#yjzb").setAutocomplete(autoSetting1);
	jQuery("#ejzb").setAutocomplete(autoSetting2);
	fzlxChange();
	
	jQuery(".ui_close").bind("click",function(){
		if (parent.window){
			refershParent();
		}
	});
});

//分值类型切换事件
function fzlxChange(){
	var fzlx = jQuery("[name='fzlx']:checked").val();
	if("1"==fzlx){
		jQuery("#fzzgf").val("");
		jQuery("#fzlxSpan").css("display","none");
	}else{
		jQuery("#fzlxSpan").css("display","");
	}
}
function searchRs() {
	var map = {};
	jQuery("#dataTable").reloadGrid(map);
}
function add() {
	//是否已评分，已评分考核表不能增加、删除不能操作，修改（只能对一级指标、二级指标、考核内容修改）
	var sfypf = jQuery("#sfypf").val(); 
	//1:已评分
	if("1"==sfypf){
		showAlertDivLayer("该考核表已有用户评分，不允许增加考核内容！");
		return false;
	}
	var khbid=jQuery("#khbid").val();
	var url = "khglKhnrgl.do?method=addKhnr&khbid="+khbid;
	var title = "增加考核内容";
	showDialog(title, 700, 350, url);
}

function update() {
	var sfypf = jQuery("#sfypf").val(); 
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	}
	var url = 'khglKhnrgl.do?method=updateKhnr&zbmxid=' + rows[0]["zbmxid"]+"&sfypf="+sfypf;
	var title = "修改考核内容";
	showDialog(title, 700, 350, url);
	
}

function del() {
	//是否已评分，已评分考核表不能增加、删除不能操作，修改（只能对一级指标、二级指标、考核内容修改）
	var sfypf = jQuery("#sfypf").val(); 
	//1:已评分
	if("1"==sfypf){
		showAlertDivLayer("该考核表已有用户评分，不允许删除考核内容！");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhnrgl.do?method=delKhnr", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}

function saveForm(type){	  
	var fzlx = jQuery("[name='fzlx']:checked").val();
	var fzzgf=jQuery("#fzzgf").val();
	var fzzdf=jQuery("#fzzdf").val();
	  if(!checkNotNull(checkId)){
		  showAlert("请将必填项填写完整！");
			return false;
	  }
	  if("1"!=fzlx&&(null==fzzdf||""==fzzdf)){
		  showAlert("请将必填项填写完整！");
			return false;
	  }
	  if(null!=fzzgf&&""!=fzzgf&&(parseFloat(fzzdf)>parseFloat(fzzgf))){
		  showAlert("分值最低分不能大于分值最高分！");
			return false;
	  }
    var url = "khglKhnrgl.do?method=saveKhnr&type="+type;
     ajaxSubFormWithFun("KhnrglForm",url,function(data){
   	 if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   			var api = frameElement.api;
			var gotoPath="khglKhnrgl.do?method=getKhnrglList&khbid="+jQuery("#khbid").val();
			if (api){
				if (api.get('childDialog')){
					api.reload(api.get('parentDialog') ,gotoPath);
				} else {
					var W = api.opener;
					W.location=gotoPath;			
				}
			} else if (parent.window){
				parent.window.document.location=gotoPath;						
			}
			
			iFClose();
   			}});
   	 }else{
   		 showAlert(data["message"]);
   		 
   	 }
		});
 }
//导入
function dr() {
	var sfypf = jQuery("#sfypf").val(); 
	if("1"==sfypf){
		showAlertDivLayer("该考核表已有用户评分，不允许导入考核内容！");
		return false;
	}
		toImportDataNew("IMPORT_N930101_KHNRDR");
}