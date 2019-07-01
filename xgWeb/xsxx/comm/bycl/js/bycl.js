/**
 * ��֤�Ƿ���ڿ���
 * 
 * @param ids
 *            Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function searchRs(){

	var map = getSuperSearch();	
	var sfyby = jQuery("#sfyby").val();
	map["sfyby"] = sfyby;
	if("1" ==sfyby){
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}
}

// ҳǩ�л�
function selectTab(obj,sfyby){
	jQuery("#sfyby").val(sfyby);
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	if('1' == sfyby){
		jQuery("#li_qxby").show();
		jQuery("#li_by").hide();
		jQuery("#li_bydr").hide();
		jQuery("#li_jcdy").hide();
	}else{
		jQuery("#li_by").show();
		jQuery("#li_bydr").show();
		jQuery("#li_jcdy").show();
		jQuery("#li_qxby").hide();
	}
	searchRs();
}	
// ��ҵ����
function bycl(){	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		var map = getSuperSearch();
		var url = "bycl.do?method=bycl";
		// �߼���ѯ
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		// ģ����ѯ
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&path=";
		url +=map["path"];					
		url +="&selected=all";

		
		showDialog("��ҵ����",420,230,url);
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		showDialog("��ҵ����",420,230,"bycl.do?method=bycl&values="+ids.toString());
	}
}

// ȡ����ҵ����
function qxbycl(){	
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		var map = getSuperSearch();
		var url = "bycl.do?method=qxbycl";
		// �߼���ѯ
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		// ģ����ѯ
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&path=";
		url +=map["path"];					
		url +="&selected=all";
		
		confirmInfo("��ȷ��Ҫ<font color='blue'>��ȡ����ҵ��</font><br><font color='red'>ȫ���ļ�¼</font>��?",function(ty){
			if(ty=="ok"){
				jQuery.post(url,{},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	} else {
		var ids = jQuery("#dataTable").getSeletIds();
		confirmInfo("��ȷ��Ҫ����<font color='red'>"+ids.length +"����¼</font>����<font color='blue'>��ȡ����ҵ��</font>������?",function(ty){
			if(ty=="ok"){
				jQuery.post("bycl.do?method=qxbycl",{values:ids.toString()},function(data){
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}
		});
	}
}

function exportConfig() {
	var path = "xjyd_bycl.do";
	customExport(path, exportData,690,500);
}
// ��������
function exportData() {

	var sfyby = jQuery("#sfyby").val();
	var path = "xjyd_bycl.do";
	setSearchTj();// ���ø߼���ѯ����
	var url = "bycl.do?method=exportData&dcclbh="+path+"&sfyby="+sfyby;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'   onclick='zxsxxView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	return cellValue;
}
// �ϰ�鿴������
function zxsxxView(xh){

		showDialog("ѧ����Ϣ��ѯ",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
}
// �°�鿴������
// function zxsxxView(xh) {
// showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
// + "&xs");
// }

function getJcXxWord(){
	var xh="";
	var ids = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		if(jQuery("#dataTable").getRowCount() == '0'){
				showAlertDivLayer("û��ѧ����Ϣ�������²�ѯ��");
				return ;
			}
		var url = "bycl.do?method=printPlJcXx";
		var map = getSuperSearch();
		//�߼���ѯ
		url +="&searchTj=";
		url +=map["searchTj"];
		url +="&searchTjz=";
		url +=map["searchTjz"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		url +="&searchLx=";
		url +=map["searchLx"];

		//ģ����ѯ
		url +="&input_mhcx=";
		url +=map["input_mhcx"];
		url +="&mhcx_lx=";
		url +=map["mhcx_lx"];
		window.open(url);
	}
	else if(ids.length == 1){
		xh = ids[0]["xh"];
		var url = "bycl.do?method=printJcXx&xh="+xh;
		var flag=false;
		jQuery.ajaxSetup({async:false});
		jQuery.post("bycl.do?method=checkJcxx",{xh:xh},function(data){
			 if(data["message"]=="true"){
				 flag=true;
	   	 }else{
	   		 showAlert(data["message"]);
	   		}
			}, 'json');
		jQuery.ajaxSetup({async:true});
		if(flag){
			window.open(url);
		}
	}
	else{
		for(var i=0;i<ids.length;i++){
			if(i==ids.length-1){
				xh +=ids[i]["xh"];
			}else{
				xh +=ids[i]["xh"]+",";
			}
		}
		var url ="bycl.do?method=printJcXxZip&xh="+xh;
		window.open(url);
				
	}
}

/**
 * ������ҽҩ��ѧ���Ի����밴ť
 */
function byxsImport(){
	var url = "bycl.do?method=byxsImport";
	var title = "��ҵѧ����Ϣ����";
	showDialog(title, 500, 309, url);
}
/**
 * ����ģ��
 * @return
 */
function mbDownload(){
	var url = "bycl.do?method=downloadFile";
	window.open(url);
}

/**
 * ���ļ����Ƹ�ֵ��input��
 * @param obj
 * @return
 */
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/**
 * ��ȡinput file������
 * @param obj
 * @return
 */
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

/**
 * �ļ����Ϳ���
 * @param obj
 * @return
 */
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

/**
 * ������֤
 * @return
 */
function saveRr(){
	var url = "bycl.do?method=SaveDrForm";
	if(!check("byny")){
		return showAlert("��ѡ���ҵ���£�");
	}
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("byclForm", url, function(data) {
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
				      if(data["message"] == "����ʧ��,����ϸ�˶ԡ���������.xls����"){
				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
				    		  jQuery("#errortr").show();
				    		  jQuery("#errora").attr("data_file","bycl.do?method=downloadFileError&filename="+data["gid"]);
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
 * ������������
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}