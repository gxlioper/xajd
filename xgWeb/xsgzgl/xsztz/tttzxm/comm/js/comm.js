//ɾ����
function delRow(){
	var obj = jQuery("[name='chk']:checked").parent().parent();
	if(obj.length == 0){
		showAlert("����ѡ���Ա��Ϣ���ٽ���ɾ��������");
		return false;
	}
	jQuery(obj).remove();
	jQuery("[name='chkall']").removeAttr("checked");
	calculateRs();
}

//������
function addRow(){
	if(jQuery("#xmmc").val() == "" || jQuery("#xmmc").val() == null){
		  showAlert("����ѡ����Ŀ��");
		  return false;
	}
	var html = "";//<label id = 'xh'></label>
	html += "<tr name='deltr'>";
	html += "<td><input type='checkbox' name='chk'></td>"
	html += "<td>��Ա</td>";
	html += "<td><input name='xh' onfocus='setStyle(this)' title='��������ûس�' onKeyDown = 'Enterxh(event,this)' style='width:90%' onblur='inputBlur(this)'/></td>";
	html += "<td><label name = 'xm'></label></td>";
	html += "<td><label name = 'xymc'></label></td>";
	html += "<td><label name = 'bjmc'></label></td>";
	html += "</tr>";
	jQuery("#autotable > table").append(html);
	calculateRs();
}

//ѡ�������� 
function selectAll(obj){
	if(obj.checked){
		jQuery("[name='chk']").attr("checked","checked");
	}else{
		jQuery("[name='chk']").removeAttr("checked");
	}
}

//�����س��¼�
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
				jQuery(obj).attr("title","ѧ�Ų����ڻ�ѧ���ظ�����Ȩ�޲μӸ���Ŀ��");
				jQuery(obj).css("border-color","red");
				jQuery(parentObj).find("[name='xm']").text("");
				jQuery(parentObj).find("[name='xymc']").text("");
				jQuery(parentObj).find("[name='bjmc']").text("");
				jQuery(obj).blur();
			}
			
	 
		}
}

//��ʦ�������ѧ��
function addRowDialog(){
	if(jQuery("#xmmc").val() == "" || jQuery("#xmmc").val() == null){
		  showAlert("����ѡ����Ŀ��");
		  return false;
	}
	var xhs = getxhs();
	 var xmdm = jQuery("#xmdm").val();
    var url = "ttxm_comm.do?method=getStu&xhs="+xhs+"&xmdm="+xmdm;
    var title = "ѧ��ѡ��";
	showDialog(title, 770, 550, url);
}

//ѡ��ӳ�
function selectdz(obj){
	jQuery("[name='dzchk']").removeAttr("checked");
	jQuery(obj).attr("checked","checked");
	var xh = jQuery(obj).parent().parent().find("[name='xh']").val();
	jQuery("#dzxh").val(xh);
}

//�õ�����ӵ�ѧ���ַ���
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

//�õ�����ӵ�ѧ���ַ���
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

//��������
function calculateRs(){
	var len = jQuery("#autotable > table").find("tr").length-1;
	jQuery("#cyrs").text(len + "��");
}

//�۽��¼����ع�����
function setStyle(obj){
	jQuery(obj).attr("title","��������ûس�");
	jQuery(obj).css("border-color","");
}

//ѧ�������ӳ�Աʱblur�¼�����������֤һ��ֵ������ʾ��Ϣ
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
		showAlert("ѧ�Ų����ڻ�ѧ���ظ�!");
	    return false;
	}
}

//�ж�ס�޽����Ҫ�����Ƿ�Ϊ�յĺ���
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

//����ı�������
function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("�������ɲ��ܳ���500�֣�");
		return false;
	}
}

/**
 * �б�չ��/����
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
 * ���ӳ��Ƿ��Ѿ�ѡȡ
 */
function checkDzIsSelect(){
	if(jQuery("[name='dzchk']:checked").length != 1){
		return false;
	}else{
		return true;
	}
}

//����ѡ����Ŀ����ճ�Ա��Ϣ
function reSelectXm(){
	jQuery("[name='deltr']").remove();
}

//�鿴�ӳ�����
function dzxhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='dzxhView(\""
			+ rowObject["dzxm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�ӳ�ѧ��
function dzxhView(ttjgid, xh) {
	showDialog("ѧ����ϸ��Ϣ",700,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh);
}



