var DCCLBH = "xsxx_xnxj_xjjg.do";//dcclbh,�������ܱ��
var gridSetting = {
	caption : "ѧ��С�����б�",
	pager : "pager",
	url : "xsxx_xnxj_xjjggl.do?method=viewXnxjjgList&type=query",
	colList : [ {
		label : 'id',
		name : 'id',
		index : 'id',
		width : '10%',
		key : true,
		hidden : true
	}, {
		label : 'ѧ��',
		name : 'xh',
		index : 'xh',
		width : '15%',
	    formatter:xhLink
	}, {
		label : '����',
		name : 'xm',
		index : 'xm',
		width : '15%'
	},{
		label : '�༶',
		name : 'bjmc',
		index : 'bjdm',
		width : '20%'
	}, {
		label : 'ѧ��',
		name : 'xn',
		index : 'xn',
		width : '10%'
	}, {
		label : 'sjly',
		name : 'sjly',
		index : 'sjly',
		hidden : true
	},{
		label : '��дʱ��',
		name : 'txsj',
		index : 'txsj',
		width : '10%'
	}

	],
	sortname : "txsj",
	sortorder : "asc"
}



/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */

function xhLink(cellValue,rowObject){
	//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	if(rowObject['id'] == null || rowObject['id'] == '' || rowObject['id'] == undefined){
		return cellValue;
	}
	var onclickfn = "onclick=\"" + "showDialog('ѧ����ϸ��Ϣ' , 720 , 480 , 'xsxx_xnxj_xjjggl.do?method=xnxjjdckAll&xh=" + cellValue + "')" + "\"";
	
	var href = "href = 'javascript:void(0);'";

	var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
	
	return el;
}

jQuery(function() {
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});

function add() {
	var url = "xsxx_xnxj_xjjggl.do?method=xnxjjdadd";
	var title = "��дѧ��С��";
	showDialog(title, 720, 500, url);
}

function saveXnxj(){
	var xh = jQuery('#xh').val();
	var xn = jQuery('#xn').val();
	var xjnr = jQuery('#xjnr').val();

	if(xh == '' || xn == '' || xjnr == ''){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	//С�����ݲ��ó���2000�ַ�
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("С����������ֳ���Ϊ"+2000+",���Ѿ���������ȷ�ϣ�");
		obj.focus();
		return false;
	}
	
	var url = "xsxx_xnxj_xjjggl.do?method=doXnxjjgtx";
	
	ajaxSubFormWithFun("xnxjjgForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
	
}

function updateXnxj(){
	var xh = jQuery('#xh').val();
	var xjnr = jQuery('#xjnr').val();

	if(xh == '' || xjnr == ''){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}

	//С�����ݲ��ó���2000�ַ�
	var obj = document.getElementById("xjnr");
	if(obj.value.length > 2000){
		showAlertDivLayer("С����������ֳ���Ϊ"+2000+",���Ѿ���������ȷ�ϣ�");
		obj.focus();
		return false;
	}
	var url = "xsxx_xnxj_xjjggl.do?method=doXnxjjgxg";
	
	ajaxSubFormWithFun("xnxjjgForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
	
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("��ѡ��ļ�¼Ϊ��˹����ļ�¼�������޸ģ�������ѡ��");
			return false;
		}
		
		var url = 'xsxx_xnxj_xjjggl.do?method=xnxjjgupdate&id=' + rows[0]["id"]
		var title = "�޸�ѧ��С����Ϣ";
		showDialog(title, 720, 480, url);
	}

}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']!='0'){
				showAlertDivLayer("��ѡ�ļ�¼�а�����˹����ļ�¼������ɾ����������ѡ��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsxx_xnxj_xjjggl.do?method=doXnxjjgsc", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function ck(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
		return false;
	} 
		
		var url = 'xsxx_xnxj_xjjggl.do?method=xnxjjdck&xh=' + rows[0]["xh"]+'&xn=' + rows[0]["xn"]
		var title = "�鿴ѧ��С����Ϣ";
		showDialog(title, 720, 480, url);
}

// ��ӡ����
function printXnxjSq(url) {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		var id = jQuery("#dataTable").getSeletIds();
		var url = url + "&id=" +id;
		window.open(url);
	}
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_xnxj_xjjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
