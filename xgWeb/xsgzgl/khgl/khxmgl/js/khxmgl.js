var checkId="xmmc-kssj-jssj";
var gridSetting = {
	caption : "考核项目列表",
	pager : "pager",
	url : "khglKhxmgl.do?method=getKhxmglList&type=query",
	colList : [ 
	   {label : 'xmid',name : 'xmid',index : 'xmid',key : true,hidden:true},
	   {label : '考核对象',name : 'khdxid',index : 'khdxid',hidden:true},
	   {label : 'sfypf',name : 'sfypf',index : 'sfypf',hidden:true},
	   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '30%', formatter:xmmcFormatter},
	   {label : '考核开始时间',name : 'kssj',index : 'kssj',width : '20%'},
	   {label : '考核结束时间',name : 'jssj',index : 'jssj',width : '20%'},
	   {label : '考核对象',name : 'khdxmc',index : 'khdxmc',width : '20%'},
	   {label : '评分对象设置',name : 'pfdxsz',index : 'pfdxsz',width : '10%',formatter:setPfdx}
	   ]
}
function xmmcFormatter(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewKhxm(\""+rowObject["xmid"]+"\");'>"+cellValue+"</a>";
}
//考核对象详情查看
function viewKhxm(xmid) {
	showDialog("考核项目查看", 800, 650, "khglKhxmgl.do?method=viewKhxm&xmid="+xmid);
}

function setPfdx(cellValue, rowObject){
	var xmid = rowObject.xmid;
	var value = "未设置";
	var color;
	if (cellValue == '1') {
		value="已设置";
		color = "have";
	} else {
		color = "non";
	}
	 value = "<a  href='javascript:void(0);' onclick='return pfdxsz(\"" + xmid
		+ "\",\"" + rowObject.sfypf+ "\",\""+rowObject.khdxid+"\");' ><font class='" + color + "'>" + value + "</font></a>";
	 return value;
}
function pfdxsz(xmid,sfypf,khdxid) {
	if("1"==sfypf){
		showAlertDivLayer("该项目已评分，不允许设置评分对象！");
		return false;
	}
	var url = 'khglKhxmgl.do?method=pfdxSz&xmid=' + xmid+"&khdxid="+khdxid;
	var title = "评分对象设置";
	showDialog(title, 800, 570, url);
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

function query() {
	var map = {};
	map["xmmc"] = jQuery("#xmmc").val();
	jQuery("#dataTable").reloadGrid(map);
}
function add() {
	
	var url = "khglKhxmgl.do?method=addKhxm";
	var title = "增加考核项目";
	showDialog(title, 700, 320, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	} 
	var url = 'khglKhxmgl.do?method=updateKhxm&xmid=' + rows[0]["xmid"];
	var title = "修改考核项目";
	showDialog(title, 700, 320, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhxmgl.do?method=delKhxm", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}

function saveForm(type){	  
	  if(!checkNotNull(checkId)){
		  showAlert("请将必填项填写完整！");
			return false;
	  }
	  if(jQuery("#xmms").val().length>500){
		  showAlert("项目描述最多输入500字！");
			return false;
	  }
    var url = "khglKhxmgl.do?method=saveKhxm&type="+type;
     ajaxSubFormWithFun("KhxmglForm",url,function(data){
    	 if (data["message"] == "保存成功！") {
 			showAlert(data["message"], {}, {
 				"clkFun" : function() {
 					if (parent.window) {
 						refershParent();
 					}
 				}
 			});
 		} else {
 			showAlert(data["message"]);
 		}
		});
 }

function pfdxSz() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您需要操作的记录！");
		return false;
	}
	if("1"==rows[0]["sfypf"]){
		showAlertDivLayer("该项目已评分，不允许设置评分对象！");
		return false;
	}
	var url = 'khglKhxmgl.do?method=pfdxSz&xmid=' + rows[0]["xmid"]+"&khdxid="+rows[0]["khdxid"];
	var title = "评分对象设置";
	showDialog(title, 800, 570, url);
}

/**
 * 列表展开/收起
 * @param obj
 * @return
 */
function showPfzmx(obj,index){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery(obj).parents(".dateline").children(".pfdxTbody").toggle();
}
function addRow(){
	var cloneTab=jQuery("#tab_orign").clone();
	cloneTab.show();
	jQuery("#pfdxDiv").append(cloneTab);	
}
//删除评分对象
function delRow(obj){
	jQuery(obj).parents(".dateline").remove();
}

function changePffw(obj){
	var khlx=jQuery("#khlx").val();
	var url="khglKhxmgl.do?method=getPffw&pfzid="+obj.value+"&khlx="+khlx;
	jQuery.post(url, {}, function(data) {
		var pffw="<select name='sjfwdm'>";
		for(var i = 0; i < data.length; i++){
			pffw +="<option value='"+data[i].sjfwdm+"'>"+data[i].sjfw+"</option>";
		}
		pffw += "</select>"
		jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("td[name='pffwTd']").html(pffw);
		jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("th[name='pffwTh']").empty().append("评分组范围");
		if(0==data.length){
			jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("select[name='sjfwdm']").hide();
			jQuery(obj).parents(".pfdxTbody").find("tr[name='pfzTr']").find("th[name='pffwTh']").empty();
		}
		
	}, 'json');
}
function changeJsfs(obj){
	var jsfs=obj.value;
	//去头尾求平均(比例)显示计算方式百分比，其他不显示
	if("2"==jsfs){
		jQuery(obj).parents(".pfdxTbody").find("tr").last().css("display","");
	}else{
		jQuery(obj).parents(".pfdxTbody").find("tr").last().find("input[name='jsfsbfb']").val("");
		jQuery(obj).parents(".pfdxTbody").find("tr").last().css("display","none");
	}
	
}
//评分对象设置保存
function savePfdxSz() {
	var flg=true;
	var nullsFlag=true;
	var pfdxJson= [];
	var samePfz="";
	var pfzmc="";
	var sjfwmc="";
	var khbmc="";
	jQuery.each(jQuery(".pfdxTbody"),function(i,n){
		if(jQuery(n).parents(".dateline").css("display")=="none"){
			return true;
		}
		var obj = {};
			var pfzid = jQuery(n).find("select[name=pfzid] option:selected").val();
			 pfzmc = jQuery(n).find("select[name=pfzid] option:selected").text();
			var sjfwdm = jQuery(n).find("select[name=sjfwdm] option:selected").val();
			 sjfwmc = jQuery(n).find("select[name=sjfwdm] option:selected").text();
			var khbid = jQuery(n).find("select[name=khbid] option:selected").val();
			 khbmc = jQuery(n).find("select[name=khbid] option:selected").text();
			var szqz = jQuery(n).find("input[name=szqz]").val();
			var jsfs = jQuery(n).find("select[name=jsfs] option:selected").val();
			var jsfsbfb = jQuery(n).find("input[name=jsfsbfb]").val();
			
			var checkMsg=pfzid+sjfwdm+khbid;
			//验证评分组是否重复添加
			if(samePfz.indexOf(checkMsg) > -1){
				flg=false;
				return false;
			}else{
				samePfz+="&"+pfzid+sjfwdm+khbid;
			}
			//验证必填项
			if(""==szqz||(""==jsfsbfb&&"2"==jsfs)||""==khbid||undefined==khbid){
				nullsFlag=false;
				return false;
			}
			obj.pfzid=pfzid;
			obj.sjfwdm=sjfwdm;
			obj.khbid=khbid;
			obj.szqz=szqz;
			obj.jsfs=jsfs;
			obj.jsfsbfb=jsfsbfb;
			pfdxJson.push(obj);
	});
	if(!flg){
		showAlertDivLayer("评分对象【<font class='red'>"+pfzmc+"-"+sjfwmc+"-"+khbmc+"</font>】已存在，请勿重复添加！");
		return false;
	}
	if(!nullsFlag){
		showAlertDivLayer("请将<span class='red'>*</span>必填项填写完整！");
		return false;
	}
	var pfdxJson = JSON.stringify(pfdxJson);
	jQuery("#pfdxJson").val(pfdxJson);
	var url = "khglKhxmgl.do?method=savePfdxSz";
	ajaxSubFormWithFun("KhxmglForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	}


//导入
function dr() {
		toImportDataNew("IMPORT_N930101_KhxmDR");
}