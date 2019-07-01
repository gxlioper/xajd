/**
 * @author yxy
 * @return
 */
//初始化页面字段信息
function initData(){
	//首先清空tbody里面所有的html
	jQuery("#jtcyxx").empty();
	jQuery("#jtqkxx").empty();
	jQuery("#fjxx").empty();
	//获取后台字段信息
	var JsonObj = getData();
	var jtqkdclist = JsonObj.jtqkdclist;
	var jtcylist = JsonObj.jtcylist;
	var fjxxList = JsonObj.fjxxList;
	//拼接html
	if(jtqkdclist != 'null' && jtqkdclist != null && jtqkdclist != "" && jtqkdclist.length > 0 ){
		initDataHtml("jtqkxx",jtqkdclist);
	}
	if(jtcylist != 'null' && jtcylist != null && jtcylist != "" && jtcylist.length > 0 ){
		initDataHtml("jtcyxx",jtcylist);
	}
	if(fjxxList != 'null' && fjxxList != null && fjxxList != "" && fjxxList.length > 0 ){
		initDataHtmlForFjxx("fjxx",fjxxList);
	}
	//绑定单个单击事件
	jQuery("[name='sfbtbz']").bind("click",changeDgBt());
	//初始化工具栏
	initTool("jtcyxx");
	initTool("jtqkxx");
	//初始化批量必填checkbox按钮
	initPlbtcheckbox("jtcyxx");
	initPlbtcheckbox("jtqkxx");
	//如果一个字段都没有，隐藏保存按钮
	var len = jQuery("#jtcxx").find("tr").length+jQuery("#jtqkxx").find("tr").length;
	if(len == 0){
		jQuery("#btn_bc").hide();
	}
}

//保存表单信息
function saveData(){
	var url = "jtqkdc_xgzdsz.do?method=saveData";
	ajaxSubFormWithFun("XgzdForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlertDivLayer(data["message"],{},{"clkFun":function(){
    			 //window.location.reload(true);
    			 initData();
				}});
    	 }else{
    		 showAlertDivLayer(data["message"]);
    		}
		});
}

//获取后台字段信息
function getData(){
	var url = "jtqkdc_xgzdsz.do?method=xgzdCx&type=search";
	var JsonObj = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		success:function(result){
		  JsonObj = result;
		}
	});
	return JsonObj;
}

//拼接html
function initDataHtml(id,jsonAarry){
	var html = "";
	var jsonAarrylen = jsonAarry.length;
	//三个字段为一行，求余数
	var modlen = jsonAarrylen % 3;
	//三个字段为一行，整行拼接
	if(jsonAarrylen >=3){
		for(var i = 0;i+2<jsonAarrylen;i=i+3){
			html +="<tr>";
			//第一个字段
			html +="<th width='20%'>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(必填)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(必填)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i]['sfbt']+"'>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i]['guid']+"'>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i]['zddm']+"'>必填</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i]['zddm']+"'>必填</label>";
			}
			
			html +="</td>";	
			//第一个字段结束
			
			//第二个字段
			html +="<th width='20%'>";
			if(jsonAarry[i+1]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i+1]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(必填)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				html += jsonAarry[i+1]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(必填)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i+1]['sfbt']+"'/>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i+1]['guid']+"'/>";
			if(jsonAarry[i+1]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i+1]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i+1]['zddm']+"'>必填</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i+1]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i+1]['zddm']+"'>必填</label>";
			}
			
			html +="</td>";	
			//第二个字段结束
			
			//第三个字段
			html +="<th width='20%'>";
			if(jsonAarry[i+2]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i+2]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(必填)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				html += jsonAarry[i+2]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(必填)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i+2]['sfbt']+"'/>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i+2]['guid']+"'/>";
			if(jsonAarry[i+2]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i+2]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i+2]['zddm']+"'>必填</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i+2]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i+2]['zddm']+"'>必填</label>";
			}
			
			html +="</td>";	
			
			//第三个字段结束
			
			html +="</tr>";
		}
	}
	//不满一行的余留字段拼接
	if(modlen != 0){
		html +="<tr>"
		for( var i = jsonAarrylen-modlen;i<jsonAarrylen;i++){
		
			html +="<th width='20%'>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(必填)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(必填)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i]['sfbt']+"'/>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i]['guid']+"'/>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i]['zddm']+"'>必填</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i]['zddm']+"'>必填</label>";
			}
			
			html +="</td>";
		}
		for( var i = modlen;i<3;i++){
			html +="<th width='20%'></th>";
			html +="<td width='12%'></td>";			
		}
		html +="</tr>"
	}
	
	jQuery("#"+id).append(html);
}

//拼接html，附件信息相关
function initDataHtmlForFjxx(id,jsonArray){
	var html = "";
	for(var i = 0;i<jsonArray.length;i=i+1){
		html +="<tr>";
		//第一个字段
		html +="<th width='20%' colspan='2'>";
		if(jsonArray[i]['sfbt'] == "yes"){
			html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
			html += jsonArray[i]['zdmc'];
			html += "<span name='sfbt' class='bold' style=''><font color='red'>(必填)</font></span>";
		}else{
			html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
			html += jsonArray[i]['zdmc'];
			html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(必填)</font></span>";

		}
			
		html +="</th>";
	    html +="<td width='12%' colspan='4'>";
		html +="<input type='hidden' name = 'sfbts' value = '"+jsonArray[i]['sfbt']+"'>";
		html +="<input type='hidden' name = 'guids' value = '"+jsonArray[i]['guid']+"'>";
		if(jsonArray[i]['sfbt'] == "yes"){
			html +="<input type='checkbox' id = '"+jsonArray[i]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonArray[i]['zddm']+"'>必填</label>";
		}else{
			html +="<input type='checkbox' id = '"+jsonArray[i]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonArray[i]['zddm']+"'>必填</label>";
		}
		
		html +="</td>";	
		//第一个字段结束
	}
	jQuery("#"+id).append(html);
}

//单个checkbox点击
function changeDgBt(){
	jQuery("[name='sfbtbz']").click(function(){
		if(jQuery(this).prop("checked") == true){
			jQuery(this).parent().find("[name='sfbts']").val("yes");
			var zdmc = jQuery(this).parent().prev().text();
			jQuery(this).parent().prev().find(".bold").show();
		}else{
			jQuery(this).parent().find("[name='sfbts']").val("no");
		//	alert(jQuery(this).parent().prev().html());
			jQuery(this).parent().prev().find(".bold").hide();
		}
		var id = jQuery(this).parent().parent().parent().attr("id");
		initPlbtcheckbox(id);
	});
}

//批量checkbox点击
function changePlbt(name,value){
	jQuery("[name='"+name+"']").removeAttr("checked");
	jQuery("[name='"+name+"'][value='"+value+"']").attr("checked","checked");
	jQuery("#"+name).find("[name='sfbts']").val(value);	
	if(value == 'yes'){
		jQuery("#"+name).find("[name='sfbtbz']").attr("checked","checked");
		jQuery("#"+name).find("th").find(".bold").show();
	}else{
		jQuery("#"+name).find("[name='sfbtbz']").removeAttr("checked");
		jQuery("#"+name).find("th").find(".bold").hide();
	}
}

//初始化工具栏
function initTool(id){
	//如果存在字段，那就显示工具栏thead，否则隐藏掉
	if(jQuery("#"+id).find("tr").length > 0){
		jQuery("#"+id+"_thead").show();
	}else{
		jQuery("#"+id+"_thead").hide();
	}
}

//初始化批量必填checkbox
function initPlbtcheckbox(id){
	var flag = jQuery("#"+id).find("[name='sfbtbz']:checked").length;
	var lenth = jQuery("#"+id).find("[name='sfbtbz']").length;
	if(flag == lenth && flag > 0){
		jQuery("[name='"+id+"'][value='yes']").attr("checked","checked");
	}else if(flag == 0 && lenth != 0){
		jQuery("[name='"+id+"'][value='no']").attr("checked","checked");
	}else{
		jQuery("[name='"+id+"']").removeAttr("checked");
	}
}