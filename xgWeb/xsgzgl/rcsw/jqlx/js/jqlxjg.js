function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXsJqlxSq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function viewXsJqlxSq(sqid, xh) {
	showDialog("ѧ��������У�����ѯ", 750, 420, "rcsw_jqlx.do?method=viewJqlxsq&sqid=" + sqid
			+ "&xh=" + xh);
}

function add(){
	var url = "rcsw_jqlx.do?method=addJqlxJg";
	var title = "����������У���";
	showDialog(title,750,530,url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sjlx = rows[0]["sjlx"];
		/*if (sjlx=='1'){
			showAlertDivLayer("���������ݲ������޸ģ�");
			return false;
		}else{*/
		if(jQuery("#xxdm").val() == '10344'){
			if (sjlx=='1'){
				showAlertDivLayer("���������ݲ������޸ģ�");
				return false;
			}
		}
			var url = 'rcsw_jqlx.do?method=updateJqlxJg&sqid='+rows[0]["sqid"]+'&xh='+rows[0]["xh"];
			var title = "�޸ļ�����У���";
			if (sjlx=='1'){
				showDialog(title,750,543,url);
			}else{
				showDialog(title,750,530,url);
			}
		//}
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_jqlx.do?method=delJqlxJg", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>�����ݡ�";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�ļ�¼Ϊ��������ݣ�����ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//�Զ��嵼�� ����
function exportConfig() {
	var exportBh = "rcsw_jqlxjg.do";
	if("11458" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11458.do";
	}
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11488.do";
	}
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(exportBh, xszbbjgExportData);
}

// ��������
function xszbbjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var exportBh = "rcsw_jqlxjg.do";
	if("11458" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11458.do";
	}
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11488.do";
	}
	var url = "rcsw_jqlx.do?method=exportData&dcclbh="+exportBh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡ�����
function printjqlxsqb(url){
	var sqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				sqid +=rows[i]["sqid"];
			}else{
				sqid +=rows[i]["sqid"]+",";
			}
		}		
		var url = url + "&sqid=" +sqid;
		window.open(url);
	}
}