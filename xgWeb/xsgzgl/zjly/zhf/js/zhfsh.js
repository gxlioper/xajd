
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function DoCheck(){
var ch=document.getElementsByName("check");
	if(document.getElementsByName("selectall")[0].checked==true){
		for(var i=0;i<ch.length;i++){
			ch[i].checked=true;
		}
	}else{
		for(var i=0;i<ch.length;i++){
			ch[i].checked=false;
		}
	}
}

/**
 * �˻�
 * @return
 */
function back(){
	var backIds = [];
	 obj  = jQuery("input[name='check']");
	 jQuery.each(obj,function(i,n){
		 if(jQuery(n).prop("checked")){
			 backIds.push(jQuery(n).val());
		 }
	 });
	    if (backIds.length != 0) {
			tipsWindownNew("������ʾ","id:tempDiv",400,200);
			return false;
		}else{
			showAlertDivLayer("��ѡ����Ҫ�˻صļ�¼��");
			return false;
		}
}

function backForm(){
	var backIds = [];
	 obj  = jQuery("input[name='check']");
	 jQuery.each(obj,function(i,n){
		 if(jQuery(n).prop("checked")){
			 backIds.push(jQuery(n).val());
		 }
	 });
	    if (backIds.length == 0) {
			showAlertDivLayer("��ѡ����Ҫ�˻صļ�¼��");
			return false;
		}
	var url = "zhf_sh.do?method=saveBackJfxm&backIds="+backIds;
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


function saveFormForSd(){
	var updateIds = [];
	 //obj = document.getElementsByName("check");
	 obj  = jQuery("input[name='check']");
	 jQuery.each(obj,function(i,n){
		 if(jQuery(n).prop("checked")){
			 updateIds.push(jQuery(n).val());
		 }
	 });
	    if (updateIds.length == 0) {
			showAlertDivLayer("��ѡ����Ҫ�󶨵ļ�¼��");
			return false;
		}
	var url = "zhf_sh.do?method=saveZhfForSd&ids="+delIds+"&updateIds="+updateIds;
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
    html+="<td><select id='xmmkdms' name='xmmkdms' onchange='getJfxmList(this);'>" + xmdmhtml +"</select></td>"
    html+="<td><select name='jfxmdms' onchange='getFs(this);'><option>---��ѡ��---</option></select></td>"
    html+="<td><div id='fs"+ i +"'></td>";
	html+="<td><input type='text' name='sxsms' maxlength='20' value='' style='width:200px'></td>";
	html+="<td><input type='text' name='cysjs' id='cysj"+ i +"' value='' onfocus=\"showCalendar('cysj" +i+"','y-mm-dd');\" style='width:100px'></td>";
	html+="<td><input onchange='checkFileType(this)' type='file' name='fj"+i+"'></td>"
	html+="<td><a href='#'onclick='delRow(this);' class='name'>ɾ��</a></td></tr>";
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

var delIds = [];
function delRow(obj){
	var id = jQuery(obj).parents("tr:eq(0)").find("input:eq(0)").val();
	delIds.push(id);
	jQuery(obj).parents("tr:eq(0)").remove();
}

/**
 * ����ɾ�����ģ�
 * @param obj
 * @return
 */
function del(obj){
	var id = jQuery(obj).parents("tr:eq(0)").find("input:eq(0)").val();
	if (id=="") {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("zhf_sh.do?method=zhfDelete", {
					values : id.toString()
				}, function(data) {	 
					showAlert(data["message"]);
						window.reload(); 
					}, 'json');
			}
		});
	}

	
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
/**
 * �޸�
 * @return
 */
function xg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}else{
		var url = 'zhf_sh.do?method=viewZhfxg&xh=' + rows[0]["xh"];
		showDialog('�޸�', 1000, 550, url);
	}
}

function viewXg(xh){
	showDialog('�鿴',1000,550,'zhf_sh.do?method=viewZhfxg&xh='+xh);
}
function xgLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXg(\""
			+ rowObject["xh"]+"\");'>" + cellValue
			+ "</a>";
}

function sh(){
	var flg = true;
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
//		var xhs = [];
//		jQuery.each(rows,function(i,n){
//			if(n["shzt"]=='0'){
//				flg = false;
//				return false;
//			}
//			xhs.push(n["xh"]);
//		});
//		if(!flg){
//			showAlertDivLayer("���󶨵ļ�¼�����ٴ��󶨣�");
//			return false;
//		}
//		var url = 'zhf_sh.do?method=plSd&xhs=' + xhs;
//		showConfirmDivLayer("��ȷ��Ҫ���������",{"okFun":function(){
//			jQuery.post("zhf_sh.do?method=savePlSd",{xhs:xhs},function(data){
//				showAlertDivLayer(data["message"]);
//				jQuery("#dataTable").reloadGrid();
//			},'json');
//		}});
		showAlertDivLayer("��ѡ��һ����Ҫ�󶨵ļ�¼��");
		return false;
	}else{
//		 if(rows[0]["shzt"]=='0'){
//			showAlertDivLayer("���󶨵ļ�¼�����ٴ��󶨣�");
//		}else {
			var url = 'zhf_sh.do?method=getSdByXh&xh=' + rows[0]["xh"]+'&shzt='+rows[0]["shzt"];
			showDialog("��", 1000, 550, url);
//		}		
	} 
}

function delFile(id){
	jQuery.post("zhf_sq.do?method=deleteFile",{id:id},function(data){
		jQuery("#fileTd").empty().append("<input type='file' name='lbfj' onchange='checkFileType(this)'/>");
	});
}

function viewSh(xh){
	showDialog('�鿴',1000,550,'zhf_sh.do?method=viewZhfsh&xh='+xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewSh(\""
			+ rowObject["xh"]+"\");'>" + cellValue
			+ "</a>";
}

//��������
function zhfshExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zhf_sh.do?method=exportData";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


/**
 * ��ӡ�ۺϷֻ��ܱ�word
 */
function zhfshPrint(){	
	var rows = jQuery("#dataTable").getSeletRow();	
	 if (rows.length == 0){
			showAlertDivLayer("��ѡ��һ����¼��");
	 } else {	
		
			var xhs = [];
			jQuery.each(rows,function(i,n){
				xhs.push(n["xh"]);
			});
		var url="zhf_sh.do?method=downloadZip";
		post(url, {value:xhs});
	}
}
function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
} 
//��ӡword
function getWord(){	
	var rows = jQuery("#dataTable").getSeletRow();	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����¼��");
	 } else if (rows.length > 1){
		 var guid = jQuery("#dataTable").getSeletIds();
		var url="xszz_zzxmjg.do?method=downloadZip";
//		var ids = jQuery("#dataTable").getSeletIds();
//		url += "&value="+ids;
//		window.open(url);
		post(url, {value:guid});
	 } else {
		var url="xszz_zzxmjg.do?method=downloadWord";		
		url += "&guid="+rows[0]["guid"];
		window.open(url);
     }
}
