
var num = '10000'; //���������Ϣ����

/**
 * ȡ������ѧ��
 */
function cpxsDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ�������ʸ��ѧ����");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['tjzt']=='1'){
				showAlertDivLayer("�ύ�ļ�¼����ɾ����");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��ȡ��ѡ��ѧ���Ĳ����ʸ���",{"okFun":function(){
			jQuery.post("zdzcwh.do?method=delCpxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * �۲�ֵ���
 */
function toImport(){
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	map['num']=num; //���ص��������
	//���⵼�����ݳ��ڴ棬������������
	jQuery.ajaxSetup({async:false});
	jQuery.post("zdzcwh.do?method=checkDownload",map,function(data){
		if (data == "true"){
			showDialog("�۲�ֵ���",550,250,"zdzcwh.do?method=toImportZdzc&jsonStr="+jsonStr,{close:function(){
				if (jQuery("#search_go")){
					jQuery("#search_go").click();
				}
			}});
		} else {
			showAlertDivLayer("�������ݹ��࣬����<font color='red'>"+num+"</font>���������Ӳ�ѯ��������е�����");						
		}
	});
	jQuery.ajaxSetup({async:true});
}
function toImportForAll(){
    var map = getSuperSearch();
    var jsonStr = JSON.stringify(map);
    map['num']=num; //���ص��������
    //���⵼�����ݳ��ڴ棬������������
    jQuery.ajaxSetup({async:false});
    jQuery.post("zdzcwh.do?method=checkDownload",map,function(data){
        if (data == "true"){
            showDialog("�۲�ֵ���",550,250,"zdzcwh.do?method=toImportZdzc&jsonStr="+jsonStr,{close:function(){
                    if (jQuery("#search_go")){
                        jQuery("#search_go").click();
                    }
                }});
        } else {
            showAlertDivLayer("�������ݹ��࣬����<font color='red'>"+num+"</font>���������Ӳ�ѯ��������е�����");
        }
    });
    jQuery.ajaxSetup({async:true});
}

//�߼���ѯ
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �����۲��
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfs(obj,xmdm,xh){
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
	
	jQuery.post("xpj_zcfs.do?method=saveZcfs",{xn:xn,xq:xq,xmdm:xmdm,xh:xh,fs:fs},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}


/**
 * �۲������� �ϡ��¡����Ҽ��¼�
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
 * �����۲�ֵ���ģ��
 */
function downloadTemplate(){
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var url = "zdzcwh.do?method=downloadTemplate";
	url = addSuperSearchParams(url,map);// ���ø߼���ѯ����
	jQuery("#zdzcform").attr("target","_blank");
	jQuery("#zdzcform").attr("action",url);
	jQuery("#zdzcform").submit();

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
	jQuery("#zdzcform").append(html);
	return url;
}


/**
 * ���� �۲��--�ϴ��ļ�
 * @returns {Boolean}
 */
function uploadZcfs(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
		return false;
	}
	
	url = addSuperSearchParams("zdzcwh.do?method=importZcfs",map);// ���ø߼���ѯ����
	jQuery("#zdzcform").attr("target","");
	jQuery("#zdzcform").attr("action",url).submit();
}


/**
 * �۲��ύ
 */
function cpxsTj(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['tjzt']='tj';
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("zdzcwh.do?method=checkIsCanSubmit",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("��ȷ���ύ��ǰѧ����",{"okFun":function(){
					jQuery.post("zdzcwh.do?method=tjCpxs",map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json'); 
				}});
			} else {
				showAlertDivLayer("ѧ���۲�ɼ�¼����ɺ���ύ��Ŀǰ����ѧ�����۲��δ¼�룬��ȷ�ϣ�");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * ȡ���ύ
 */
function cpxsQxtj(){
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	map['values']=ids.toString();
	map['tjzt']='qxtj';
	showConfirmDivLayer("��ȷ��ȡ���ύ��ǰѧ����",{"okFun":function(){
		jQuery.post("zdzcwh.do?method=tjCpxs",map,function(data){
			showAlertDivLayer("ȡ���ύ�ɹ�");
			jQuery("#dataTable").reloadGrid();
		},'json'); 
	}});
}