jQuery(function() {
	var gridSetting = {
		caption : "��Ʒ�����б�",
		pager : "pager",
		url : "axcswpjg.do?method=wpjgList&type=query",
		colList : [ {
			label : 'key',
			name : 'jgid',
			index : 'jgid',
			key : true,
			hidden : true
		},{
			label : 'xmdm',
			name : 'xmdm',
			index : 'xmdm',
			hidden : true
		},{
			label : 'sqid',
			name : 'sqid',
			index : 'sqid',
			hidden : true
		},{
			label : 'ѧ��',
			name : 'xh',
			index : 'xh',
			width : '12%',
			formatter:xhLink
		},{
			label : 'ѧ��',
			name : 'xn',
			index : 'xn',
			width : '12%'
		},{
			label : '����',
			name : 'xm',
			index : 'xm',
			width : '8%'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc',
			width : '20%'
		}, {
			label : '�༶',
			name : 'bjmc',
			index : 'bjdm',
			width : '20%'
		}, {
			label : '����ʱ��',
			name : 'sqsj',
			index : 'sqsj',
			width : '18%'
		}, {
			label : '��Ʒ����',
			name : 'xmmc',
			index : 'xmmc',
			width : '10%'
		}, {
			label : '��Ʒ���',
			name : 'xmlbmc',
			index : 'xmlbmc',
			width : '8%'
		}],
		sortname : "sqsj",
		sortorder : "desc"
	}
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




function add(){
	var url = "axcswpjg.do?method=wpjgZj";
	var title = "���ӽ����Ϣ";
	showDialog(title,680,550,url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(sjly == '1'){
		showAlertDivLayer("�������ݲ����޸ģ�");
	}else {
		var url = 'axcswpjg.do?method=wpjgXg&jgid='+ rows[0]["jgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "�޸Ľ����Ϣ";
		showDialog(title, 680,550, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpjg.do?method=delWpjg", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="�������ݲ����޸ģ�";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function wpjgView(jgid, xh) {
	showDialog("�����ѯ", 680,400, "axcswpjg.do?method=wpjgCk&jgid=" + jgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='wpjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "axcs_axcswpjg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wpjgExportData);
}

// ��������
function wpjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "axcswpjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function getWpxx(obj){
	var url = "axcs_wpsz_ajax.do?method=getWpxx";
	jQuery.post(url, {
		xmdm:obj.value
	}, function(data) {
		dataObj = data;
		var xmtpDiv="<img style='width:170px;height:148px;margin:0;float:left;padding-right: 2px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+obj.value+"&type=view'>";
			jQuery("#xmtp").empty().append(xmtpDiv);
			jQuery("#xmlbid").empty().append(dataObj.xmlbmc);
		}, 'json');
}

function getWpxxByXmdm(xmdm){
	var url = "axcs_wpsz_ajax.do?method=getWpxx";
	jQuery.post(url, {
		xmdm:xmdm
	}, function(data) {
		dataObj = data;
		var xmtpDiv="<img style='width:170px;height:148px;margin:0;float:left;padding-right: 2px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+xmdm+"&type=view'>";
			jQuery("#xmtp").empty().append(xmtpDiv);
			jQuery("#xmlbid").empty().append(dataObj.xmlbmc);
		}, 'json');
}