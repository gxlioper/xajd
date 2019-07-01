/**
 * @author yxy
 * @return
 */
//��ʼ��ҳ���ֶ���Ϣ
function initData(){
	//�������tbody�������е�html
	jQuery("#jtcyxx").empty();
	jQuery("#jtqkxx").empty();
	jQuery("#fjxx").empty();
	//��ȡ��̨�ֶ���Ϣ
	var JsonObj = getData();
	var jtqkdclist = JsonObj.jtqkdclist;
	var jtcylist = JsonObj.jtcylist;
	var fjxxList = JsonObj.fjxxList;
	//ƴ��html
	if(jtqkdclist != 'null' && jtqkdclist != null && jtqkdclist != "" && jtqkdclist.length > 0 ){
		initDataHtml("jtqkxx",jtqkdclist);
	}
	if(jtcylist != 'null' && jtcylist != null && jtcylist != "" && jtcylist.length > 0 ){
		initDataHtml("jtcyxx",jtcylist);
	}
	if(fjxxList != 'null' && fjxxList != null && fjxxList != "" && fjxxList.length > 0 ){
		initDataHtmlForFjxx("fjxx",fjxxList);
	}
	//�󶨵��������¼�
	jQuery("[name='sfbtbz']").bind("click",changeDgBt());
	//��ʼ��������
	initTool("jtcyxx");
	initTool("jtqkxx");
	//��ʼ����������checkbox��ť
	initPlbtcheckbox("jtcyxx");
	initPlbtcheckbox("jtqkxx");
	//���һ���ֶζ�û�У����ر��水ť
	var len = jQuery("#jtcxx").find("tr").length+jQuery("#jtqkxx").find("tr").length;
	if(len == 0){
		jQuery("#btn_bc").hide();
	}
}

//�������Ϣ
function saveData(){
	var url = "jtqkdc_xgzdsz.do?method=saveData";
	ajaxSubFormWithFun("XgzdForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlertDivLayer(data["message"],{},{"clkFun":function(){
    			 //window.location.reload(true);
    			 initData();
				}});
    	 }else{
    		 showAlertDivLayer(data["message"]);
    		}
		});
}

//��ȡ��̨�ֶ���Ϣ
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

//ƴ��html
function initDataHtml(id,jsonAarry){
	var html = "";
	var jsonAarrylen = jsonAarry.length;
	//�����ֶ�Ϊһ�У�������
	var modlen = jsonAarrylen % 3;
	//�����ֶ�Ϊһ�У�����ƴ��
	if(jsonAarrylen >=3){
		for(var i = 0;i+2<jsonAarrylen;i=i+3){
			html +="<tr>";
			//��һ���ֶ�
			html +="<th width='20%'>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(����)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(����)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i]['sfbt']+"'>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i]['guid']+"'>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i]['zddm']+"'>����</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i]['zddm']+"'>����</label>";
			}
			
			html +="</td>";	
			//��һ���ֶν���
			
			//�ڶ����ֶ�
			html +="<th width='20%'>";
			if(jsonAarry[i+1]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i+1]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(����)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				html += jsonAarry[i+1]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(����)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i+1]['sfbt']+"'/>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i+1]['guid']+"'/>";
			if(jsonAarry[i+1]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i+1]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i+1]['zddm']+"'>����</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i+1]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i+1]['zddm']+"'>����</label>";
			}
			
			html +="</td>";	
			//�ڶ����ֶν���
			
			//�������ֶ�
			html +="<th width='20%'>";
			if(jsonAarry[i+2]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i+2]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(����)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				html += jsonAarry[i+2]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(����)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i+2]['sfbt']+"'/>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i+2]['guid']+"'/>";
			if(jsonAarry[i+2]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i+2]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i+2]['zddm']+"'>����</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i+2]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i+2]['zddm']+"'>����</label>";
			}
			
			html +="</td>";	
			
			//�������ֶν���
			
			html +="</tr>";
		}
	}
	//����һ�е������ֶ�ƴ��
	if(modlen != 0){
		html +="<tr>"
		for( var i = jsonAarrylen-modlen;i<jsonAarrylen;i++){
		
			html +="<th width='20%'>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style=''><font color='red'>(����)</font></span>";
			}else{
				html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
				html += jsonAarry[i]['zdmc'];
				html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(����)</font></span>";

			}
				
			html +="</th>";
		    html +="<td width='12%'>";
			html +="<input type='hidden' name = 'sfbts' value = '"+jsonAarry[i]['sfbt']+"'/>";
			html +="<input type='hidden' name = 'guids' value = '"+jsonAarry[i]['guid']+"'/>";
			if(jsonAarry[i]['sfbt'] == "yes"){
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonAarry[i]['zddm']+"'>����</label>";
			}else{
				html +="<input type='checkbox' id = '"+jsonAarry[i]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonAarry[i]['zddm']+"'>����</label>";
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

//ƴ��html��������Ϣ���
function initDataHtmlForFjxx(id,jsonArray){
	var html = "";
	for(var i = 0;i<jsonArray.length;i=i+1){
		html +="<tr>";
		//��һ���ֶ�
		html +="<th width='20%' colspan='2'>";
		if(jsonArray[i]['sfbt'] == "yes"){
			html += "<span name='btxh' class='bold' style=''><font color='red'>*</font></span>";
			html += jsonArray[i]['zdmc'];
			html += "<span name='sfbt' class='bold' style=''><font color='red'>(����)</font></span>";
		}else{
			html += "<span name='btxh' class='bold' style='display:none'><font color='red'>*</font></span>";
			html += jsonArray[i]['zdmc'];
			html += "<span name='sfbt' class='bold' style='display:none'><font color='red'>(����)</font></span>";

		}
			
		html +="</th>";
	    html +="<td width='12%' colspan='4'>";
		html +="<input type='hidden' name = 'sfbts' value = '"+jsonArray[i]['sfbt']+"'>";
		html +="<input type='hidden' name = 'guids' value = '"+jsonArray[i]['guid']+"'>";
		if(jsonArray[i]['sfbt'] == "yes"){
			html +="<input type='checkbox' id = '"+jsonArray[i]['zddm']+"' name = 'sfbtbz' checked/><label for ='"+jsonArray[i]['zddm']+"'>����</label>";
		}else{
			html +="<input type='checkbox' id = '"+jsonArray[i]['zddm']+"' name = 'sfbtbz' /><label for ='"+jsonArray[i]['zddm']+"'>����</label>";
		}
		
		html +="</td>";	
		//��һ���ֶν���
	}
	jQuery("#"+id).append(html);
}

//����checkbox���
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

//����checkbox���
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

//��ʼ��������
function initTool(id){
	//��������ֶΣ��Ǿ���ʾ������thead���������ص�
	if(jQuery("#"+id).find("tr").length > 0){
		jQuery("#"+id+"_thead").show();
	}else{
		jQuery("#"+id+"_thead").hide();
	}
}

//��ʼ����������checkbox
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