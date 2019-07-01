function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//¥���л�
function lddmChange(){
	jQuery("#lddm").change(function(){
		if(this.value == null || this.value == '' ){
			jQuery("#ch").empty();
			jQuery("#qsh").empty();
			return false;
		}
		var data = getData("ld");
    	var dataList = data["dataList"];
    	var flag = data['message'];
    	jQuery("#ch").empty();
		jQuery("#qsh").empty();
		if(flag && flag === 'true'){
			var html = "<option></option>";
			for(var i = 0;i < dataList.length;i++){
				html += "<option value='"+dataList[i]['ch']+"'>"+dataList[i]['chmc']+"</option>";
			}
			jQuery("#ch").append(html);
		}
		
	})
}

//����л�
function chChange(){
    jQuery("#ch").change(function(){
    	if(this.value == null || this.value == '' ){
			jQuery("#qsh").empty();
			return false;
		}
    	var data = getData("cc");
    	var dataList = data["dataList"];
    	var flag = data['message'];
    	jQuery("#qsh").empty();
		if(flag && flag === 'true'){
			var html = "<option></option>";
			for(var i = 0;i < dataList.length;i++){
				html += "<option value='"+dataList[i]['qsh']+"'>"+dataList[i]['qsh']+"</option>";
			}
			jQuery("#qsh").append(html);
		}
		
	})
}

//��ȡ��������Ҫ������
function getData(type){
	var url = "gyccgl_ccdj.do?method=changeSelect&type="+type;
	var data = null;
	var para = {lddm:jQuery("#lddm").val(),ch:jQuery("#ch").val()};
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:para,
		async: false,
		success:function(result){
		   data = result;
		}
		
	});
	return data;
}

//��������
function saveForm(type){
	var ids = "xn"+"-"+"xq"+"-"+"lddm"+"-"+"qsh"+"-"+"zje";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	/**
	 * �𻵳̶ȿ���֤
	 */
	var trObjArr = jQuery("#iterate").find("tr");
	var flag = false;
	jQuery(trObjArr).each(function(i,o){
		var wpdm = jQuery(o).find("[name='wpdms']").val();
		var shcd = jQuery(o).find("[name='shcds']").val();
		if(jQuery.trim(wpdm) && jQuery.trim(shcd)){
			flag = true;
			return flag;
		}
	});
	if(!flag){
		showAlert("�Ǽ���Ϣ����������д������һ����");
		return false;
	}
	var url = "gyccgl_ccdj.do?method=saveForm&type=" + type;
	ajaxSubFormWithFun("CcdjForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
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

//����
function add() {
	var url = "gyccgl_ccdj.do?method=add";
	var title = "�Ʋ��Ǽ�";
	showDialog(title, 700, 550, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gyccgl_ccdj.do?method=update&id=' + rows[0]["id"];
		var title = "�Ʋ��Ǽ��޸�";
		showDialog(title, 700, 550, url);
	}
}

//����
function view() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gyccgl_ccdj.do?method=viewccdj&id=' + rows[0]["id"];
		var title = "�Ʋ��Ǽǲ鿴";
		showDialog(title, 700, 450, url);
	}
}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gyccgl_ccdj.do?method=delRs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�𻵳̶�����
function changesShcd(){
	jQuery("select[name='shcds']").change(function(){
		changeSHCD(this);
	});
}

//���Һ�����
function qshChange(){
	jQuery("#qsh,#xn,#xq").change(function(){
		var xn = jQuery("#xn").val();
		var xq = jQuery("#xq").val();
		var qsh = jQuery("#qsh").val();
		var lddm = jQuery("#lddm").val();
		var rs = (xn != "" && xq != "" && qsh != "" && lddm != "");
		var data = null;
		if(rs){
			var url = "gyccgl_ccdj.do?method=qshChange";
			var data = null;
			var para = {lddm:lddm,qsh:qsh,xn:xn,xq:xq};
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				data:para,
				async: false,
				success:function(result){
				   data = result;
				}
				
			});
			//������(0)���Һ�̨���ص�����Ҳ�ǲ�����(0),����Ҫˢ��
			var ymexists = jQuery("#isexist").val();
			var reqexists = data['isexist'];
			if(ymexists == "0" && reqexists == "0"){
				return;
			}else{
				jQuery("#isexist").val(reqexists);
				if(reqexists == "1"){
					jQuery("#id").val(data["id"]);
					jQuery("#zje").val(data["zje"]);
					jQuery("#bz").val(data["bz"]);
				}else{
					jQuery("#id").val("");
					jQuery("#zje").val("");
					jQuery("#bz").val("");
				}
				//�����𻵳̶�������html
				var shcdList = data["shcdList"];
				var selectHtml ="<select name='shcds' onchange='changeSHCD(this);' style='width:98%'>"
				selectHtml += "<option></option>";
				for ( var i = 0; i < shcdList.length; i++) {
					selectHtml += "<option value='"+shcdList[i]["shcddm"]+"'>"+shcdList[i]["shcdmc"]+"</option>";
				}
				selectHtml+"</select>";
				//���ԭ����tbody html����
				jQuery("#iterate").empty();
				//����tr��html
				var dataList = data["dataList"];
				var iterateInnerHtml = ""; 
				for ( var i = 0; i < dataList.length; i++) {
					iterateInnerHtml +="<tr>";
					iterateInnerHtml +="<td>"+dataList[i]["wpmc"]+"<input name='wpdms' value='"+dataList[i]["wpdm"]+"' type='hidden' /></td>";
					var je ="";
					if(reqexists == "1" && dataList[i]["je"]!= "null" && dataList[i]["je"]!= null){
						je = dataList[i]["je"];
					}
					
					iterateInnerHtml +="<td>"+selectHtml+"</td>";
					iterateInnerHtml +="<td name='je'>"+je+"</td></tr>";
				}
				jQuery("#iterate").append(iterateInnerHtml);
				/**
				 * ������ֵ
				 */
				if(reqexists == "1"){
					for ( var int = 0; int < dataList.length; int++) {
						jQuery("#iterate > tr").eq(int).find("select").val(dataList[int]["shcd"]);
					}
				}
			}
		}
	})
}

function changeSHCD(obj){
	var shcddm = obj.value;
	var je = jQuery("#shcd > option[value='"+shcddm+"']").text();
	jQuery(obj).parent().parent().find("[name='je']").text(je);
	var zje = 0;
	jQuery("[name='je']").each(function(){
		var je = (!jQuery(this).text()) ? "0" : jQuery(this).text();
		zje += parseFloat(je);
	});
	jQuery("#zje").val(zje);
}

/**
 * ����
 * @return
 */
function dr(){
	var url = "gyccgl_ccdj.do?method=dr";
	var title = "����";
	showDialog(title, 770, 350, url);
}

//����
function downloadxzmb(){
	window.open("gyccgl_ccdj.do?method=downloadFile");
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}

/**
 * ���浼��
 * @return
 */
function  saveDr(){
	var url = "gyccgl_ccdj.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("CcdjForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   			   
   			 showAlert(data["message"],{},{"clkFun":function(){
   				      if(data["message"].indexOf("����ϸ�˶�") != "-1"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#errora").attr("data_file","gyccgl_ccdj.do?method=downloadFileError&filename="+data["gid"]);
   				    		  
   				    	  }
   				      }else{
   				    	 jQuery("#errortr").hide();
   				    	jQuery("#errora").attr("data_file","");
   				      }
   				      jQuery("#drmb").val("");
				}});
   		}
		});
	
}

/**
 * ����
 * @return
 */
function jgExportData() {
	var url = "gyccgl_ccdj.do?method=exportData";
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//���ļ����Ƹ�ֵ��input��
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
	checkFileType(obj);
}

//��ȡinput file������
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

//�ļ����Ϳ���
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�xls���͵��ļ���");
		return false;
	}
}
