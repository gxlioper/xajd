//删除行
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("请先选择记录，再进行删除操作！");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
	calculateRs();
}

function getxhs(){
	var xhs = "";
	var xhobj = jQuery("[name='xh']");
	jQuery(xhobj).each(function(i){
		xhs +=this.value;
		if(xhobj.length-1 != i){
			xhs +=",";
		}
		
	});
	return xhs;
}

//老师身份增加学生
function addRowDialog(){
	if(jQuery("#xydm").val() == ""){
		return showAlert("学院不可为空！");
	}
	var xhs = getxhs();
	var xydm = jQuery("#xydm").val();
    var url = "xlzxnew_ybjg.do?method=addStu&xhs="+xhs+"&xydm="+xydm;
    var title = "学生选择";
	showDialog(title, 770, 550, url);
}

function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}

//老师身份增加学生
function addXy(){
    var url = "xlzxnew_ybjg.do?method=addXy";
    var title = "学院选择";
	showDialog(title, 770, 550, url);
}


