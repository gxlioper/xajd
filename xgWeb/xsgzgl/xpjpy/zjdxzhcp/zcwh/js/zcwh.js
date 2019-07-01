/*������LINK*/
function cprLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewZcmdxx(\""+rowObject["id"]+"\",\""+rowObject["xmdm"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/*��������˲鿴����*/
function viewZcmdxx(id,xmdm) {
	var url = "xpjpy_zcwh.do?method=zcwhView&id=" + id + "&zcxmdmForTop=" + xmdm;
	showDialog("�鿴�۲��",700,475,url);
}

/*�۲�ά��LINK*/
function zcczLink(cellValue, rowObject) {
	if(rowObject["tjzt"] != "1"){
		return "<a href='javascript:void(0);' class='name' onclick='zccz(\""+rowObject["id"]+"\",\""+rowObject["xmdm"]+"\",\""+cellValue+"\");'>����ά��</a>";
	}else{
		return "<font color='red'>������ά��!</font>";
	}
}
/*����۲����*/
function zccz (id,xmdm){
	/*ȡ��̨�������Ĳ�������ѧ��*/
	var xn = jQuery("#xn").val();
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	var url = "xpjpy_zcwh.do?method=zcwhEdit&id="+id.toString()+"&xn=" + xn + "&zcxmdmForTop=" + xmdm;
	document.location.href=url;
}

/**
 * �۲�����������ά��
 * @return
 */
function zcwhEdit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	/*ȡ��̨�������Ĳ�������ѧ��*/
	var xn = jQuery("#xn").val();
	var map = getSuperSearch();
	var jsonStr = JSON.stringify(map);
	
	if(ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}else{
		var zcxmdmForTop = rows[0]["xmdm"];
		var url = "xpjpy_zcwh.do?method=zcwhEdit&id="+ids.toString()+"&xn="+xn + "&zcxmdmForTop=" + zcxmdmForTop;
		url = url+"&jsonStr="+ encodeURI("{}");
		/*���ύѧԺ��*/
		var tjbj = 0;
		for(var i=0;i<rows.length;i++){
			if(rows[i]['tjzt']=='1'){
				tjbj++;
			}
		}
		if(tjbj==rows.length){
			showConfirmDivLayer("ѡ�еļ�¼<font color='blue'>���ύ</font>��������ѡ��",{"okFun":function(){
			}});
			return false;
		}else if(tjbj!=0){
			showConfirmDivLayer("���Զ�ȥ��<font color='blue'>���ύ</font>����ά����ѧԺ",{"okFun":function(){
				document.location.href=url;
			}});
			return false;
		}
		document.location.href=url;
	}
}

/**
 * �۲�ѧ��������ѯ
 * @return
 */
function doQuery(){

	var jsonStr = jQuery("#jsonStr").val();
	var map = {};
	if(jsonStr){
		map = JSON.parse(jsonStr);
	}
	map["id"] = jQuery("#id").val();
	map["xn"] = jQuery("#xn").val();
	map["xh"] = jQuery("#xh").val();
	jQuery("#dataTable").reloadGrid(map);
	
}

/**
 * �鿴�۲��
 * @returns {Boolean}
 */
function zcwhView(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var zcxmdmForTop = rows[0]["xmdm"];
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴��ѧԺ��");
		return false;
	} 
	var id = rows[0]["id"];
	var url = "xpjpy_zcwh.do?method=zcwhView&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop;
	showDialog("�鿴�۲��",700,475,url);
}

/**
 * ����������-�ۺϲ���-�۲�ά��
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
 * ʵʱ�����۲��
 * @param fs
 * @param xmdm
 * @param xh
 */
function saveZcfs(obj,xmdm,xh){
	var xn = jQuery("#xn").val();
	var fs = obj.value;
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	
	if (max != "" && Number(fs) > Number(max)){
		showAlertDivLayer("¼��������ܴ������֣�"+max,{},{"clkFun":function(){
		}});
		return false;
	}
	if (min != "" && Number(fs) < Number(min) && Number(fs) != ""){
		showAlertDivLayer("¼��������ܵ�����С�֣�"+min,{},{"clkFun":function(){
		}});
		return false;
	}
	jQuery.post("xpjpy_zcwh.do?method=saveZcfs",{xn:xn,xmdm:xmdm,xh:xh,fs:fs},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}


/**
 * ���Ӳ���ѧ��
 * @return
 */
function cpxsAdd(){
	var url = "xpjpy_zcwh.do?method=cpxsAdd";
	var title = "���Ӳ���ѧ��";
	showDialog(title,800,550,url);
}

/**
 * ȡ����ɾ������ѧ��
 */
function cpxsDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ�������ʸ��ѧ����");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		/*ѭ��ѡ�м�¼���������ύ���ݣ����������ɾ��*/
		for(var i=0;i<ids.length;i++){
			if(rows[i]['tjzt']=='1'){
				showAlertDivLayer("�ύ�ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��ȡ��ѡ��ѧ���Ĳ����ʸ���",{"okFun":function(){
			jQuery.post("xpjpy_zcwh.do?method=cpxsDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
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
		jQuery.post("xpjpy_zcwh.do?method=checkIsCanSubmit",map,function(data){
			if (data == "true"){
				showConfirmDivLayer("��ȷ���ύ��ǰѧ����",{"okFun":function(){
					jQuery.post("xpjpy_zcwh.do?method=tjCpxs",map,function(data){
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
	map['values'] = ids.toString();
	map['tjzt'] = 'qxtj';
	showConfirmDivLayer("��ȷ��ȡ���ύ��ǰѧ����",{"okFun":function(){
		jQuery.post("xpjpy_zcwh.do?method=tjCpxs",map,function(data){
			showAlertDivLayer("ȡ���ύ�ɹ�");
			jQuery("#dataTable").reloadGrid();
		},'json'); 
	}});
}

/**
 * �۲�ֵ���
 * @return
 */
var DCCLBH = 'xpjpy_zhcp_zcwh.do';
function cpxsExport() {
	var jsonStr = jQuery("#jsonStr").val();
	var xhxm = jQuery("#xh").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	var url = "xpjpy_zcwh.do?method=cpxsExport&dcclbh="+DCCLBH + "&xh="+xhxm + "&xm="+xhxm + "&zcxmdmForTop"+zcxmdmForTop;
	url = addSuperSearchParams(url,map)
	jQuery("#zcwhForm").attr("target","_blank");
	jQuery("#zcwhForm").attr("action",url);
	jQuery("#zcwhForm").submit();
}



/**
 * �۲�ֵ���
 * @return
 */
function cpxsImport(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	var urlOne = "xpjpy_zcwh.do?method=checkDownload&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop;
	var urlTwo = "xpjpy_zcwh.do?method=toImportZcfs&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop + "&jsonStr=" + jsonStr;
	/*���⵼�����ݳ��ڴ棬������������*/
	jQuery.ajaxSetup({async:false});
	jQuery.post(urlOne,map,function(data){
		if (data == "true"){
			showDialog("�۲�ֵ���",550,250,urlTwo,{close:function(){
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
 * �۲�ֵ���ģ��
 * @return
 */
function downloadTemplate(){
	var id = jQuery("#id").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	var url = "xpjpy_zcwh.do?method=downloadTemplate&id=" + id + "&zcxmdmForTop=" + zcxmdmForTop;
	url = addSuperSearchParams(url,map);// ���ø߼���ѯ����
	jQuery("#zcwhForm").attr("target","_blank");
	jQuery("#zcwhForm").attr("action",url);
	jQuery("#zcwhForm").submit();
}

/**
 * ���ø߼���ѯ����������������ʹ��
 * @param url
 * @param map
 * @return
 */
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
	jQuery("#zcwhForm").append(html);
	return url;
}

/**
 * �����۲�֣��ϴ��ļ�
 * @return
 */
function uploadZcfs(){
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	var zcxmdmForTop = jQuery("#zcxmdmForTop").val();
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("�����ļ�ֻ��Ϊ.xls��ʽ,��ȷ�ϣ�");
		return false;
	}
	
	url = addSuperSearchParams("xpjpy_zcwh.do?method=importZcfs&zcxmdmForTop=" + zcxmdmForTop,map);// ���ø߼���ѯ����
	jQuery("#zcwhForm").attr("target","");
	jQuery("#zcwhForm").attr("action",url).submit();
}


/**
 * �۲���ύ
 * @return
 */
function zcwhSubmit(){
	/*ѡ���¼��ID*/
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var zcxmdmForTop = rows[0]["xmdm"];
		doSubmit(ids.toString(),zcxmdmForTop);
	}
}

/*
 * ִ���۲���ύ
 */
function doSubmit(id,zcxmdmForTop){
	var xn = jQuery("#xn").val();
	jQuery.ajaxSetup({async:false});
		jQuery.post("xpjpy_zcwh.do?method=checkZcfSubmit",{id:id,xn:xn,zcxmdmForTop:zcxmdmForTop},function(data){
			if (data == "true"){
				showConfirmDivLayer("�۲���ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��",{"okFun":function(){
					jQuery.post("xpjpy_zcwh.do?method=submitXyzcf",{id:id},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							searchRs();
						}});
					},"json");
				}});
			} else {
				showAlertDivLayer("ȫ��ѧ���۲�ɼ�¼����ɺ���ύ����ѧԺ����ѧ�����۲��δ¼�룬��ȷ�ϣ�");						
			}
		});
	jQuery.ajaxSetup({async:true});
}

/**
 * ȡ���ύ
 * @return
 */
function zcwhCancelSubmit(){
	/*ѡ���¼��ID*/
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}else{
		var id = rows[0]["id"];
		if("0" == rows[0]["tjzt"]){
			showAlert("ֻ��ȡ��״̬Ϊ���ύ�ļ�¼��");
			return false;
		}
		showConfirmDivLayer("��ȷ��ȡ���ύ��ǰ��¼��",{"okFun":function(){
			jQuery.post("xpjpy_zcwh.do?method=zcwhCancelSubmit",{id:id},function(data){
				showAlertDivLayer("ȡ���ύ�ɹ�!");
				jQuery("#dataTable").reloadGrid();
			},'json'); 
		}});
	}
}

/**
 * һ��ͬ���۲�֣�5�����
 */
function dataSynchronization(){
	jQuery.post("xpjpy_zcwh.do?method=dataSynchronization",function(data){
		showAlertDivLayer(data["message"],{},{"okFun":function(){
			}});
		jQuery("#dataTable").reloadGrid();
	},"json");
}


