
var num = '10000'; //���������Ϣ����

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ���澺����
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveBfjsFswh(obj,xmdm,bjdm){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	if (max != "" && Number(fs) > Number(max)){
		showAlertDivLayer("¼��������ܴ������֣�"+max,{},{"clkFun":function(){
		}});
		return false;
	}
	
	if (min != "" && Number(fs) < Number(min)){
		showAlertDivLayer("¼��������ܵ�����С�֣�"+min,{},{"clkFun":function(){
		}});
		return false;
	}
	
	jQuery.post("xpjpyBfjsFswh.do?method=saveBfjsFswh",{xn:xn,xq:xq,xmdm:xmdm,bjdm:bjdm,fs:fs},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}



/**
 * �ύ������
 * @returns {Boolean}
 */
function submitJsfs(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['id']=ids.toString();
	map['tjzt']='tj';
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	}else{
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpjpyBfjsFswh.do?method=checkIsCanSubmit",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("��ȷ���ύ��ǰ�༶��",{"okFun":function(){
					jQuery.post("xpjpyBfjsFswh.do?method=submitBjJsf",map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json'); 
				}});
			} else {
				showAlertDivLayer("������Ŀ��¼����ɺ���ύ��Ŀǰ���а༶�ľ�����δ¼�룬��ȷ�ϣ�");						
			}
		});
	jQuery.ajaxSetup({async:true});
	}
	
}


/**
 * ����������� �ϡ��¡����Ҽ��¼�
 * @param obj
 */
function toNext(obj,event){
	var event = event || window.event;
	//��   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	
	//��      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	
	//��   ���� �س�
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	
	//�� 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
	
}
/**
 * ����������
 */
function ExportData(){
	var url="xpjpyBfjsFswh.do?method=exportViewJsfs";
	var map = getSuperSearch();
	url = addSuperSearchParams(url,map);// ���ø߼���ѯ����
	document.location.href=url;
}



/**
 * �����ֵ��� 
 */
function toImport(){
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	showDialog("�����ֵ���",550,280,"xpjpyBfjsFswh.do?method=toImportJsfs&jsonStr="+jsonStr,{close:function(){
		if (jQuery("#search_go")){
			jQuery("#search_go").click();
		}
	}});
}






/**
 * ���ؾ����ֵ���ģ��
 */
function downloadTemplate(){
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var url = "xpjpyBfjsFswh.do?method=downloadTemplate&editType='dr'";
	url = addSuperSearchParams(url,map);// ���ø߼���ѯ����
	jQuery("#BfjsFswhModel").attr("target","_blank");
	jQuery("#BfjsFswhModel").attr("action",url);
	jQuery("#BfjsFswhModel").submit();

}


/**
 * ���� ������--�ϴ��ļ�
 * @returns {Boolean}
 */
function uploadJsfs(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
		return false;
	}
	
	url = addSuperSearchParams("xpjpyBfjsFswh.do?method=importJsfs",map);// ���ø߼���ѯ����
	jQuery("#BfjsFswhModel").attr("target","");
	jQuery("#BfjsFswhModel").attr("action",url).submit();
}



//���ø߼���ѯ����������������ʹ��
function addSuperSearchParams(url,map){
	
	if(url.indexOf("?") > -1){
		url += "&";
	}else{
		url += "?";
	}
	url += "path=" + map.path + "&mhcx_lx=" + map.mhcx_lx;
	jQuery("input[name=searchLx],input[name=searchTj],input[name=searchTjz],input[name=input_mhcx]").remove();
	
	var html = "<input type = 'hidden' name='searchLx' value='"+map.searchLx+"'>";
	html += "<input type = 'hidden' name='searchTj' value='"+map.searchTj+"'>";
	html += "<input type = 'hidden' name='searchTjz' value='"+map.searchTjz+"'>";
	if (map.input_mhcx){
		html += "<input type = 'hidden' name='input_mhcx' value='"+map.input_mhcx+"'>";
	}
	jQuery("#BfjsFswhModel").append(html);
	return url;
}

