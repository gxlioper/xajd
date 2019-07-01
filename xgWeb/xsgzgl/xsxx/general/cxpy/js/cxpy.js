var DCCLBH="xsxx_gygl_cxcxpy.do";


//增加操行评语
function addCxpy(){
	showDialog('增加学生操行评语',800,530,'xsxx_gygl_cxcxpy.do?method=addCxpy');
}
//增加学生
function addTr(){
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	//showTopWin('xsxx_gygl_cxcxpy.do?method=getStu&xn='+xn+'&xq='+xq,800,700);
	showDialog('选择学生',800,600,'xsxx_gygl_cxcxpy.do?method=getStu&xn='+xn+'&xq='+xq);
	
}
//删除学生
function delTr(){
	var array = jQuery("[name=xsxx]:checked");
	for (var i=0;i<array.length;i++) {
		var xsxx = jQuery(array[i]).parent().parent();
		var xh=xsxx.find("td").eq(1).text();
		xh+=",";
		var xhs=jQuery("#xhs").val();
		while(xhs.indexOf(xh)!=-1){
			xhs=xhs.replace(xh,"");
		}
		jQuery("#xhs").val(xhs);
		xsxx.remove();
	}
}

//选择学生
function zjBcStu(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	var api = frameElement.api,W = api.get('parentDialog');
//	W.document.getElementById("xhs").value=str;
//	api.close();
//	W.document.getElementById("btn_getXsxx").onclick();
	if(rows.length>=1){
		ids+=",";
		var id= W.document.getElementById("xhs").value;
		id+=ids;
		W.document.getElementById("xhs").value=id;
		api.close();
		W.document.getElementById("btn_getXsxx").onclick();
	}else{
		alertInfo("请勾选需要添加的数据！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
	
}
//保存操行评语
function bcCxpy(){
	document.forms[0].action = window.location.href;
	document.forms[0].submit();

}

//保存
function save(){
	var xn=jQuery("#xn").val();
	var xq=jQuery("#xq").val();
	var cxdjdm=jQuery("#cxdjdm").val();
	var cxpy=jQuery("#cxpy").val();
	var array = jQuery("[name=xsxx]");
	var bzr=jQuery("#bzr").val();
	var xxdm = jQuery("#xxdm").val();
	if(xn==""){
		alertInfo("请选择学年！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if("13943" == xxdm) {
		if(jQuery("#sqsj").val()=="" || jQuery("#sqsj").val()==null){
			alertInfo("请填写时间！",function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
	}else {
		if(xq==""||xq==null){
			alertInfo("请选择学期！",function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
	}
	if(cxdjdm==""||cxdjdm==null){
		alertInfo("请选择操行等级！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(bzr==""||bzr==null){
		alertInfo("请填写班主任！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(cxpy==""){
		alertInfo("请填写操行评语！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if(array.length==0){
		alertInfo("请选择学生！",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var url="xsxx_gygl_cxcxpy.do?method=save";
	ajaxSubFormWithFun("cxpyForm",url,function(data){
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

function removeXs(){
	var array = jQuery("#tbody_zgryxx>tr");
	array.remove();
	jQuery("#xhs").val("");
}
//全选
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if (rows[0]["sjly"] == "1"){
			showAlertDivLayer("流程数据不能修改！");
			return false;
		}
		showDialog('修改学生操行评语',800,380,'xsxx_gygl_cxcxpy.do?method=updateCxpy&pk='+rows[0]["pk"]);
	}
}
//查看
function viewCxpy(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		showDialog('查看学生操行评语',800,310,'xsxx_gygl_cxcxpy.do?method=viewCxpy&pk='+rows[0]["pk"]);
	}
}
function cxpyView(id){
	showDialog('查看学生操行评语',800,310,'xsxx_gygl_cxcxpy.do?method=viewCxpy&pk='+id);
}

function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xsxx_gygl_cxcxpy.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 导入
 */
function importConfig() {
	toImportData("IMPORT_N710502");
	return false;
}