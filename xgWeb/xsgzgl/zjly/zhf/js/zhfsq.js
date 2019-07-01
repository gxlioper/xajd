
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function saveForm(){	
	var flg = true;
	var checkList = [];
	jQuery.each(jQuery("#dataList tr"),function(i,n){
			var checkStr = '';
			var xmdm = jQuery(n).find("select[name=xmmkdms] option:selected").val();
			var jfdm = jQuery(n).find("select[name=jfxmdms] option:selected").val();
			var sxsm = jQuery(n).find("input[name=sxsms]").val();
			var cysj = jQuery("#cysj"+i).val();
			if(xmdm == null || xmdm == '' || jfdm == null || jfdm == '' || sxsm == null || sxsm == '' || cysj == null || cysj == '' ){
				flg = false;
				return false;
			}else{
				checkStr = xmdm+','+jfdm+','+sxsm+','+cysj;
				checkList.push(checkStr);
			}		
	})
	if(!flg){
		showAlert("�뽫��*�ı�������д������");
		return false;
	}
		
	if (jQuery("#dataList tr").size() == 0){
		showAlert("����д�Ʒ���Ŀ��Ϣ��");
		return false;
	}
	
	var flagg = true;
	
	for(var i = 0;i<checkList.length;i++){
		var flag = true;
		for(var j = i+1;j<checkList.length;j++){
			if(checkList[i] == checkList[j]){
				flag = false;
				break;
			}
		}
		if(!flag){
			flagg = false;
			break;
		}
	}
	
	if(!flagg){
		showAlert("ͬһ�Ʒ���Ŀͬһ����ʱ��ͬһ����˵�������ظ���д��");
		return false;
	}
	
	var url = "zhf_sq.do?method=saveZhf";
	ajaxSubFormWithFun("form",url,function(data){
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

function saveFormForUpdate(){
	if (jQuery("#xmmkdm").val() == "" || jQuery("#jfxmdm").val() == "" || jQuery("#cysj").val() == "" || jQuery("#sxsm").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}	
	var url = "zhf_sq.do?method=saveZhfForUpdate";
	ajaxSubFormWithFun("form",url,function(data){
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

var i =0;

function addRow(){
	html="";
	
	var xmdmhtml=jQuery("#cxzd").html()
	html+="<tr>"
    html+="<td><select id='xmmkdms' name='xmmkdms' onchange='getJfxmList(this);' style='width:200px'>" + xmdmhtml +"</select></td>"
    html+="<td><select name='jfxmdms' onchange='getFs(this);' style='width:200px'><option value=''>---��ѡ��---</option></select></td>"
    html+="<td><div id='fs"+ i +"'></td>";
	html+="<td><input type='text' name='sxsms' maxlength='20' value='' style='width:250px'></td>";
	html+="<td style='width:80px'><input type='text' name='cysjs' id='cysj"+ i +"' value='' onfocus=\"showCalendar('cysj" +i+"','y-mm-dd');\" style='width:100px'></td>";
	html+="<td style='width:120px'><input onchange='checkFileType(this)' type='file' name='fj"+i+"'></td>"
	html+="<td style='width:80px'><a href='#'onclick='delRow(this);' class='name' style='width:100px'>ɾ��</a></td></tr>";
	i++;
	jQuery("#dataList").append(html);
}

function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["png","gif","jpg","jpeg","zip","rar","pdf","txt","doc","docx","xls","xlsx"];
	if (jQuery.inArray(type, types) == -1){
		showAlert("����ѡ����ļ����Ͳ������ϴ���");
		obj.value="";
	}
}

function delRow(obj){
	jQuery(obj).parents("tr:eq(0)").remove();
}

//�õ�ģ����Ŀ�б�
function getJfxmList(obj){
	
	var jfxmSelect = jQuery(obj).parents("tr:eq(0)").find("select[name=jfxmdms]");
	var xmdm = jQuery(obj).find("option:selected").val();
	
	jfxmSelect.find("option").remove();
	
	if (xmdm == ""){
		return false;
	}
	jQuery.getJSON("zhf_sq.do?method=getJfxmList",{xmmkdm:xmdm},function(data){
		
		jfxmSelect.append("<option value=''>---��ѡ��---</option>");
		jQuery.each(data,function(i,n){
			var option = jQuery("<option value='"+n["jfxmdm"]+"'>"+n["jfxmmc"]+"</option>");
			jfxmSelect.append(option);							
		});
	});
}

//�õ�ģ����Ŀ�б�
function getJfxmListForUpdate(obj){
	
	var jfxmSelect = jQuery("#jfxmdm");
	var xmdm = jQuery(obj).find("option:selected").val();
	
	jfxmSelect.find("option").remove();
	
	if (xmdm == ""){
		return false;
	}
	jQuery.getJSON("zhf_sq.do?method=getJfxmList",{xmmkdm:xmdm},function(data){
		
		jfxmSelect.append("<option value=''>---��ѡ��---</option>");
		jQuery.each(data,function(i,n){
			var option = jQuery("<option value='"+n["jfxmdm"]+"'>"+n["jfxmmc"]+"</option>");
			jfxmSelect.append(option);							
		});
	});
}


//�õ�����
function getFs(obj){
	var jfdm = jQuery(obj).find("option:selected").val();
	if (jfdm == ""){
		return false;
	}
	jQuery.getJSON("zhf_sq.do?method=getFs",{jfxmdm:jfdm},function(data){
		jQuery(obj).parents("tr:eq(0)").find("td:eq(2)").find("div").html(data);
	});	
}

function getFsForUpdate(obj){
	var jfdm = jQuery(obj).find("option:selected").val();
	if (jfdm == ""){
		return false;
	}
	jQuery.getJSON("zhf_sq.do?method=getFs",{jfxmdm:jfdm},function(data){
		jQuery("#fs").empty();
		jQuery("#fs").html(data);
	});	
}

function getKhyd(obj){
	var jfdm = jQuery(obj).find("option:selected").val();
	if (jfdm == ""){
		return false;
	}
	jQuery.post("zhf_sq.do?method=getKhyd",{jfxmdm:jfdm},function(data){
		jQuery("#khyd").empty();
		jQuery("#khyd").html(data);
	});
}

function add(){
	var sqkg = jQuery("#sqkg").val();
	if(sqkg == '0'){
		showAlertDivLayer("��ǰ�����ѹر�");
		return false;
	}
	var url = "zhf_sq.do?method=addZhfsq";
	var title = "����";
	showDialog(title, 1200, 500, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows[0]["shzt"]=='1'){
		showAlertDivLayer("����˵ļ�¼�����޸ģ�");
	}
	else {
		var url = 'zhf_sq.do?method=updateZhfsq&id=' + rows[0]["id"];
		var title = "�޸�";
		showDialog(title, 1000, 550, url);
	}

}

// ɾ��
function del(){
	var flg = true;
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	jQuery.each(rows,function(i,n){
		if(n["shzt"] == '1'){
			flg = false;
			return false;
		}	
	})
	if(!flg){
		showAlertDivLayer("����˵ļ�¼����ɾ����");
		return false;
	}
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
			jQuery.post("zhf_sq.do?method=delete",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function delFile(id){
	jQuery.post("zhf_sq.do?method=deleteFile",{id:id},function(data){
		jQuery("#fileTd").empty().append("<input type='file' name='lbfj' onchange='checkFileType(this)'/>");
	});
}

function view(id){
	showDialog('�鿴',650,450,'zhf_sq.do?method=viewZhfsq&id='+id);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}

