
var num = '10000'; //���������Ϣ����

/**
 * �۲��¼��
 * @returns {Boolean}
 */
function editZcfs(){
	
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var ids = jQuery("#dataTable").getSeletIds();
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	var url = "xpj_zcfs.do?method=editZcfss&id="+ids.toString()+"&xn="+xn+"&xq="+xq;
	/*�����ύ������ֻ�ܹ�ѡһ����¼ �����۲�ά�� */
	if(ids.length != 1 && jQuery("#szyf").val() == "1"){
		showAlertDivLayer("�빴ѡһ����¼�����۲��ά����");
		return false;
	}
	if(ids.length == 0){
		
		url = url+"&jsonStr="+encodeURI(jsonStr);
		
		var flag=true;
		jQuery.post("xpj_zcfs.do?method=checkSubmitInfo",map,function(data){
			if (data == "false"){
				flag=false;

			}
		});
		if("false"==flag){
			showAlertDivLayer("û��δ�ύ��Ϣ");
			return false;
		}	
		
		showConfirmDivLayer("ȷ��Ҫ��ѯ��ǰ����ѧ�����۲���Ϣ��",{"okFun":function(){
			document.location.href=url;
		}});
		
	}else{
		url = url+"&jsonStr="+ encodeURI("{}");
		
		var tjbj = 0;  //���ύ�༶��
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<rows.length;i++){
			if(rows[i]['tjzt']=='1'){
				tjbj++;
			}
		}
		if(tjbj==rows.length){
			showConfirmDivLayer("ѡ�еİ༶��<font color='blue'>���ύ</font>��������ѡ��",{"okFun":function(){
			}});
			return false;
		}else if(tjbj!=0){
			showConfirmDivLayer("���Զ�ȥ��<font color='blue'>���ύ</font>����ά���İ༶",{"okFun":function(){
				document.location.href=url;
			}});
			return false;
		}
		
		document.location.href=url;
		
	}

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
	var zcyf = jQuery("#zcyf").val();
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
	
	jQuery.post("xpj_zcfs.do?method=saveZcfs",{xn:xn,xq:xq,xmdm:xmdm,xh:xh,fs:fs,zcyf:zcyf},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}


/**
 * ִ���۲���ύ
 * @param id
 */
function doSubmit(id){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpj_zcfs.do?method=checkIsCanSubmit",{id:id,xn:xn,xq:xq},function(data){
			if (data == "true"){
				showConfirmDivLayer("�۲���ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��",{"okFun":function(){
					jQuery.post("xpj_zcfs.do?method=submitBjzcf",{id:id},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							searchRs();
						}});
					},"json");
				}});
			} else {
				showAlertDivLayer("ȫ��ѧ���۲�ɼ�¼����ɺ���ύ���ð༶����ѧ�����۲��δ¼�룬��ȷ�ϣ�");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * ¼�۲��ҳ��ִ���۲���ύ
 * @param id
 */
function doEditSubmit(id){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpj_zcfs.do?method=checkIsCanSubmit",{id:id,xn:xn,xq:xq},function(data){
			if (data == "true"){
				showConfirmDivLayer("�۲���ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��",{"okFun":function(){
					jQuery.post("xpj_zcfs.do?method=submitBjzcf",{id:id},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							document.location.href="xpj_zcfs.do?method=viewZcfs&id="+id;
						}});
					},"json");
				}});
			} else {
				showAlertDivLayer("ѧ���۲�ɼ�¼����ɺ���ύ���ð༶����ѧ�����۲��δ¼�룬��ȷ�ϣ�");						
			}
		});
	jQuery.ajaxSetup({async:true});
}


/**
 * �ύ�۲��
 * @returns {Boolean}
 */
function submitZcfs(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	}else if(ids.length == 1){
		doSubmit(ids.toString());
	}else{
		showConfirmDivLayer("�۲���ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��", {
			"okFun" : function() {
				jQuery.post("xpj_zcfs.do?method=plCheckIsCanSubmit", {
					id:ids.toString(),
					xn:xn,
					xq:xq
				}, function(data) {
					var mes="�ɹ��ύ��<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+=" ����ѧ�����۲��δ¼�룬��ȷ�ϣ�";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
		
	};
	
}


/**
 * �鿴�۲��
 * @returns {Boolean}
 */
function viewZcfs(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�İ༶��");
		return false;
	} 
	
	var id = rows[0]["id"];
	showDialog("�鿴�۲��",700,410,"xpj_zcfs.do?method=viewZcfs&id="+id);
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
 * �л��۲���ҳtabҳ
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,zcjg){
	jQuery("#zcjg").val(zcjg);

	if (zcjg == "cpzpm"){
		var map = getSuperSearch();
		map["zcjg"]="cpzpm";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} if(zcjg == "bjpm"){
		var map = getSuperSearch();
		map["zcjg"]="bjpm";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}else {
		var map = getSuperSearch();
		map["zcjg"]="njzypm";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting3);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}



/**
 * �۲��¼�롢�鿴--��ѯ
 */
function doQuery(){

	var jsonStr = jQuery("#jsonStr").val();
	var map = {};
	if(jsonStr){
		map = JSON.parse(jsonStr);
	}
	map["id"] = jQuery("#id").val();
	map["xn"] = jQuery("#xn").val();
	map["xq"] = jQuery("#xq").val();
	map["xh"] = jQuery("#xh").val();
	map["zcyf"] = jQuery("#zcyf").val();
	jQuery("#dataTable").reloadGrid(map);
	
}

/**
 * �����۲�֣��鿴ҳ��
 */
function exportZcf(){
	var id = jQuery("#id").val();
	var xh = jQuery("#xh").val();
	document.location.href="xpj_zcfs.do?method=exportViewZcfs&id="+id+"&xh="+xh;
}

function exportZcfOfYf(){
	var id = jQuery("#id").val();
	var xh = jQuery("#xh").val();
	var zcyf = jQuery("#zcyf").val();
	document.location.href="xpj_zcfs_yf.do?method=exportViewZcfs&id="+id+"&zcyf="+zcyf+"&xh="+xh;
}


/**
 * �۲�ֵ��� 
 */
function toImport(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var zcyf = jQuery("#zcyf").val();
	var map = JSON.parse(jsonStr);
	map['num']=num; //���ص��������
	map['zcyf']=zcyf;
	//���⵼�����ݳ��ڴ棬������������
	jQuery.ajaxSetup({async:false});
	jQuery.post("xpj_zcfs.do?method=checkDownload&id="+id,map,function(data){
		if (data == "true"){
			showDialog("�۲�ֵ���",550,250,"xpj_zcfs.do?method=toImportZcfs&id="+id+"&jsonStr="+encodeURI(jsonStr)+"&zcyf="+zcyf,{close:function(){
				if (jQuery("#search_go")){
					jQuery("#search_go").click();
				}
			}});
		} else {
			showAlertDivLayer("��������ģ�����ݹ��࣬����<font color='red'>"+num+"</font>���������Ӳ�ѯ�������ڽ��е��������");						
		}
	});
	jQuery.ajaxSetup({async:true});
}

/**
 * ͬ���۲��
 * @param xmdm
 */
function getIntefaceData(xmdm){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['xmdm']=xmdm;
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

/**
 * ͬ���۲�֣��Ϻ�Ϸ����Ի���
 * @param xmdm
 */
function getIntefaceDataS(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var xmdms = [];
	jQuery("[name='zcxm']").each(function(i,n){
		xmdms.push(jQuery(n).attr('xmdm'));
	});
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	map['xmdms'] = xmdms.join(",");
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

function getIntefaceData_13431(){
    var id = jQuery("#id").val();
    var xn = jQuery("#xn").val();
    var xq = jQuery("#xq").val();
    var xmdms = [];
    jQuery("[name='zcxm']").each(function(i,n){
    	var xmmc = jQuery(n).attr('xmmc');
    	if(xmmc == "������" || xmmc == "������" || xmmc == "�ӷ�" || xmmc == "����"){
            xmdms.push(jQuery(n).attr('xmdm'));
		}

    });
    var jsonStr = jQuery("#jsonStr").val();
    var map = JSON.parse(jsonStr);
    map['id']=id;
    map['xn']=xn;
    map['xq']=xq;
    map['xmdms'] = xmdms.join(",");
    jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
        showAlertDivLayer(data["message"],{},{"okFun":function(){
        }});
        jQuery("#dataTable").reloadGrid();
    },"json");
}
/**
 * ͬ���۲�֣����������ѧҵˮƽ��
 * @param xmdm
 */
function getIntefaceDataXysp(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var xmdms = [];
	jQuery("[name='zcxm']").each(function(i,n){
		xmdms.push(jQuery(n).attr('xmdm'));
	});
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	map['xmdms'] = xmdms.join(",");
	jQuery.post("xpj_zcfs.do?method=getIntefaceData&nzcs="+1,map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

/**
 * ͬ���۲�֣����������˼����£�
 * @param xmdm
 */
function getIntefaceDataSxdd(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var xmdms = [];
	jQuery("[name='zcxm']").each(function(i,n){
		xmdms.push(jQuery(n).attr('xmdm'));
	});
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	map['xmdms'] = xmdms.join(",");
	jQuery.post("xpj_zcfs.do?method=getIntefaceData&nzcs="+2,map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

/**
 * �����۲�ֵ���ģ��
 */
function downloadTemplate(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var zcyf=jQuery("#zcyf").val();
	var map = JSON.parse(jsonStr);
	
	var url = "xpj_zcfs.do?method=downloadTemplate&id="+id+"&zcyf="+zcyf;
	url = addSuperSearchParams(url,map);// ���ø߼���ѯ����
	jQuery("#zcfsForm").attr("target","_blank");
	jQuery("#zcfsForm").attr("action",url);
	jQuery("#zcfsForm").submit();

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
	
	url = addSuperSearchParams("xpj_zcfs.do?method=importZcfs",map);// ���ø߼���ѯ����
	jQuery("#zcfsForm").attr("target","");
	jQuery("#zcfsForm").attr("action",url).submit();
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
	jQuery("#zcfsForm").append(html);
	return url;
}

/**
 * �����۲��(�·�)
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfsYf(obj,xmdm,xh){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	var zcyf = jQuery("#zcyf").val();
	
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
	
	jQuery.post("xpj_zcfs_yf.do?method=saveZcfs",{xn:xn,xq:xq,xmdm:xmdm,xh:xh,fs:fs,zcyf:zcyf},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}

/**
 * �ύ�۲��
 * @returns {Boolean}
 */
function submitZcfsyf(){
	
	var ids = jQuery("#id").val();
	var zcyf = jQuery("#zcyf").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	

		showConfirmDivLayer("�۲���ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��", {
			"okFun" : function() {
				jQuery.post("xpj_zcfs.do?method=plCheckIsCanSubmityf", {
					id:ids.toString(),
					xn:xn,
					xq:xq,
					zcyf:zcyf
				}, function(data) {
					var mes="�ɹ��ύ��<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+=" ����ѧ�����۲��δ¼�룬��ȷ�ϣ�";
						showAlertDivLayer(mes);
						return false;
					}else{
						//�ύ�ɹ���ı��������ֵ
						showAlertDivLayer(mes);
						jQuery("#dataTable").reloadGrid();
						jQuery("#tjzt").find("[value='"+zcyf+"']").text("1");
						jQuery("#zcyf").change();
						return false;
					}
					
				}, 'json');
			}
		});
		

	
}

/**
 * ���ɹŵ��ӽ��ճ���Ϊ������ͬ�����۲�༭ҳ��
 * @return
 */
function getIntefaceDataNmgdz(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();

	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}

//����Ӧ��ְҵ����ѧԺ
function getIntefaceDataNmgzc(){
	var id = jQuery("#id").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();

	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	map['id']=id;
	map['xn']=xn;
	map['xq']=xq;
	jQuery.post("xpj_zcfs.do?method=getIntefaceData",map,function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
		}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}