//删除行
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("请先选择成员信息，再进行删除操作！");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
	calculateRs();
}

//增加行
function addRow(){
	if(jQuery("#xmmc").val() == "" || jQuery("#xmmc").val() == null){
		  showAlert("请先选择项目！");
		  return false;
	}
	var html = "";//<label id = 'xh'></label>
	html += "<tr name='deltr'>";
	html += "<td><input type='checkbox' name='chk'></td>"
	html += "<td>队员</td>";
	html += "<td><input name='xh' onfocus='setStyle(this)' title='输入完毕敲回车' onKeyDown = 'Enterxh(event,this)' style='width:90%' onblur='inputBlur(this)'/></td>";
	html += "<td><label name = 'xm'></label></td>";
	html += "<td><label name = 'xymc'></label></td>";
	html += "<td><label name = 'bjmc'></label></td>";
	html += "</tr>";
	jQuery("#autotable > table").append(html);
	calculateRs();
}

//选择所有行 
function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}

//输入框回车事件
function Enterxh(e,obj){
	
		if(e.keyCode==13){
			var xhs = getxhs1(obj);
		    var xh = obj.value;
		    var xmdm = jQuery("#xmdm").val();
			var jsonPara = {xh:xh,xhs:xhs,xmdm:xmdm};
			var url = "ttxm_comm.do?method=EnterXh";
			var jsonResult = null;
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:jsonPara,
			async: false,
			success:function(result){
					jsonResult = result;
			}
			
			
		   });
			var parentObj = jQuery(obj).parent().parent();
			if(jsonResult['xh']){
				jQuery(parentObj).find("[name='xm']").text(jsonResult['xm']);
				jQuery(parentObj).find("[name='xymc']").text(jsonResult['xymc']);
				jQuery(parentObj).find("[name='bjmc']").text(jsonResult['bjmc']);
			}else{
				jQuery(obj).attr("title","学号不存在或学号重复或无权限参加该项目！");
				jQuery(obj).css("border-color","red");
				jQuery(parentObj).find("[name='xm']").text("");
				jQuery(parentObj).find("[name='xymc']").text("");
				jQuery(parentObj).find("[name='bjmc']").text("");
				jQuery(obj).blur();
			}
			
	 
		}
}

//老师身份增加学生
function addRowDialog(){
	if(jQuery("#xmmc").val() == "" || jQuery("#xmmc").val() == null){
		  showAlert("请先选择项目！");
		  return false;
	}
	var xhs = getxhs();
	 var xmdm = jQuery("#xmdm").val();
    var url = "ttxm_comm.do?method=getStu&xhs="+xhs+"&xmdm="+xmdm;
    var title = "学生选择";
	showDialog(title, 770, 550, url);
}

//选择队长
function selectdz(obj){
	jQuery("[name='dzchk']").removeAttr("checked");
	jQuery(obj).attr("checked","checked");
	var xh = jQuery(obj).parent().parent().find("[name='xh']").val();
	jQuery("#dzxh").val(xh);
}

//得到已添加的学号字符串
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

//得到已添加的学号字符串
function getxhs1(obj){
	var xhs = "";
	var xhobj = jQuery("[name='xh']").not(obj);
	jQuery(xhobj).each(function(i){
		xhs +=this.value;
		if(xhobj.length-1 != i){
			xhs +=",";
		}
		
	});
	return xhs;
}

//计算人数
function calculateRs(){
	var len = jQuery("#autotable > table").find("tr").length-1;
	jQuery("#cyrs").text(len + "人");
}

//聚焦事件，回归设置
function setStyle(obj){
	jQuery(obj).attr("title","输入完毕敲回车");
	jQuery(obj).css("border-color","");
}

//学生身份添加成员时blur事件触发：再验证一遍值给出提示信息
function inputBlur(obj){
	var xhs = getxhs1(obj);
    var xh = obj.value;
    var xmdm = jQuery("#xmdm").val();
	var jsonPara = {xh:xh,xhs:xhs,xmdm:xmdm};
	var url = "ttxm_comm.do?method=EnterXh";
	var jsonResult = null;
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:jsonPara,
	async: false,
	success:function(result){
			jsonResult = result;
	}
	
	
   });
	var parentObj = jQuery(obj).parent().parent();
	if(jsonResult['xh']){
		jQuery(parentObj).find("[name='xm']").text(jsonResult['xm']);
		jQuery(parentObj).find("[name='xymc']").text(jsonResult['xymc']);
		jQuery(parentObj).find("[name='bjmc']").text(jsonResult['bjmc']);
	}else{
		jQuery(parentObj).find("[name='xm']").text("");
		jQuery(parentObj).find("[name='xymc']").text("");
		jQuery(parentObj).find("[name='bjmc']").text("");
		showAlert("学号不存在或学号重复!");
	    return false;
	}
}

//判断住宿结果重要内容是否为空的函数
function checkContentIsNull(){
    var flag = true;
	jQuery("[name='xm']").each(function(){
		if(jQuery(this).text() == ""){
			flag = false;
			return false;
		}
	});
	return flag;
}

//检查文本域字数
function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("申请理由不能超过500字！");
		return false;
	}
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
	jQuery("#autotbody").toggle();
}

/**
 * 检查队长是否已经选取
 */
function checkDzIsSelect(){
	if(jQuery("[name='dzchk']:checked").length != 1){
		return false;
	}else{
		return true;
	}
}

//重新选择项目后，清空成员信息
function reSelectXm(){
	jQuery("[name='deltr']").remove();
}

//查看队长链接
function dzxhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dzxhView(\""
			+ rowObject["dzxm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//队长学号
function dzxhView(ttjgid, xh) {
	showDialog("学生详细信息",700,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh);
}



