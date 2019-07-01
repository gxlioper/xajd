
function initGridSetting(){
	var gridSetting = {};
	if("10704" == jQuery("#xxdm").val()){
		var gridSetting = {
				caption : "����ѧ���б�",
				pager : "pager",
				url : "xlzx_tsxs.do?method=tsxsManage&doType=query",
				colList:[ 
				   {label : 'id',name : 'id',index : 'id',hidden : true}, 
				   {label : 'ѧ��',name : 'xh',index : 'xh',formatter : xhLink}, 
				   {label : '����',name : 'xm',index : 'xm'}, 
				   {label : '�Ա�',name : 'xb',index : 'xb'}, 
				   {label : '�꼶',name : 'nj',index : 'nj'}, 
				   {label : 'ѧԺ����',name : 'xymc',index : 'xymc'}, 
				   {label : '�༶',name : 'bjmc',index : 'bjmc'}, 
				   {label : 'ά��ʱ��',name : 'xgsj',index : 'xgsj'}, 
				   {label : 'Ԥ���̶�',name : 'knlxdm',index : 'knlxdm',formatter : getKnlxmc}
				  ],
				sortname : "",
				sortorder : ""
			}
		
		
		
	}else if("11527" == jQuery("#xxdm").val()){
		var gridSetting = {
				caption : "����ѧ���б�",
				pager : "pager",
				url : "xlzx_tsxs.do?method=tsxsManage&doType=query",
				colList:[ 
				   {label : 'id',name : 'id',index : 'id',hidden : true}, 
				   {label : 'ѧ��',name : 'xh',index : 'xh',formatter : xhLink}, 
				   {label : '����',name : 'xm',index : 'xm'}, 
				   {label : '�Ա�',name : 'xb',index : 'xb'}, 
				   {label : '�꼶',name : 'nj',index : 'nj'}, 
				   {label : 'ѧԺ����',name : 'xymc',index : 'xymc'}, 
				   {label : '�༶',name : 'bjmc',index : 'bjmc'}, 
				   {label : 'ά��ʱ��',name : 'xgsj',index : 'xgsj'}, 
				   {label : '��������',name : 'knlxdm',index : 'knlxdm',formatter : getKnlxmc},
				   {label : '��ע״̬',name : 'gzztmc',index : 'gzztmc'},
				   {label : '�ܴ�',name : 'zc',index : 'zc'}
				  ],
				sortname : "",
				sortorder : ""
			}
	}else{
		var gridSetting = {
				caption : "����ѧ���б�",
				pager : "pager",
				url : "xlzx_tsxs.do?method=tsxsManage&doType=query",
				colList:[ 
				   {label : 'id',name : 'id',index : 'id',hidden : true}, 
				   {label : 'ѧ��',name : 'xh',index : 'xh',formatter : xhLink}, 
				   {label : '����',name : 'xm',index : 'xm'}, 
				   {label : '�Ա�',name : 'xb',index : 'xb'}, 
				   {label : '�꼶',name : 'nj',index : 'nj'}, 
				   {label : 'ѧԺ����',name : 'xymc',index : 'xymc'}, 
				   {label : '�༶',name : 'bjmc',index : 'bjmc'}, 
				   {label : 'ά��ʱ��',name : 'xgsj',index : 'xgsj'}, 
				   {label : '��������',name : 'knlxdm',index : 'knlxdm',formatter : getKnlxmc},
				   {label : '��ע״̬',name : 'gzztmc',index : 'gzztmc'}
				  ],
				sortname : "",
				sortorder : ""
		}
	}
  gridSetting["params"]=getSuperSearch();
  jQuery("#dataTable").initGrid(gridSetting);
	
}

jQuery(function(){
	initGridSetting();
});

function searchRs() {
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	var rs = "";
	if("11527" == jQuery("#xxdm").val()){
		rs = "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail_11527('"
			+ rowObject["xh"] + "','"+ rowObject["id"] + "','"+rowObject["zc"]+"');\">" + cellValue + "</a>"
	}else{
		rs = "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"
		+ rowObject["xh"] + "','"+ rowObject["id"]+"');\">" + cellValue + "</a>"
	}
	return rs;
}

function getKnlxmc(cellValue, rowObject) {
	var knlxmc = '';
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery.post("xlzx_tsxs.do?method=getKnlxmc", {
		knlxdm : cellValue
	}, function(data) {
		knlxmc = data;
	}, '');
	jQuery.ajaxSetup( {
		async : true
	});
	return knlxmc;
}
function showDetail(xh,id) {
	showDialog("����ѧ������", 700, 310,
			"xlzx_tsxs.do?method=tsxsDetail&doType=view&xh=" + xh+"&id="+id);
}
function showDetail_11527(xh,id,zc) {
	showDialog("����ѧ������", 700, 310,
			"xlzx_tsxs.do?method=tsxsDetail&doType=view&xh=" + xh+"&id="+id+"&zc="+zc);
}

function addTsxs() {
	showDialog("��������ѧ��", 700, 350, "xlzx_tsxs.do?method=tsxsDetail&doType=add");

}

function updateTsxs() {
	var xh = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	} else {
		xh = rowsValue[0]["xh"];
		
	}
	 if("11527" == jQuery("#xxdm").val()){
		 zc = rowsValue[0]["zc"];
		 showDialog("�޸�����ѧ������", 700, 340,
					"xlzx_tsxs.do?method=tsxsDetail&doType=update&xh=" + xh+"&zc="+zc);
	 }else{
		 showDialog("�޸�����ѧ������", 700, 340,
					"xlzx_tsxs.do?method=tsxsDetail&doType=update&xh=" + xh); 
	 }
	
}

function deleteTsxs() {
	var dealTsxs = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		// ��֤�Ƿ���̸����¼
		if (delValidate(rowsValue) == "true") {
			showAlert("��ѡ���ѧ���д���̸����¼������ɾ����");
			return false;
		}
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				dealTsxs += rowsValue[i]["id"];
			} else {
				dealTsxs += rowsValue[i]["id"] + ",";
			}
		}
		showConfirm("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.ajaxSetup( {
					async : false
				});
				jQuery.post("xlzx_tsxs.do?method=deleteTsxsInfo", {
					dealTsxs : dealTsxs
				}, function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				jQuery.ajaxSetup( {
					async : true
				});
			}
		});
	}
}

function delValidate(rowsValue) {
	var flag = false;
	for ( var i = 0; i < rowsValue.length; i++) {
		jQuery.ajaxSetup( {
			async : false
		});
		jQuery.post("xlzx_thjl.do?method=haveThjlFlagByXh", {
			xh : rowsValue[i]["xh"]
		}, function(data) {
			flag = data;
		}, '');
		jQuery.ajaxSetup( {
			async : true
		});
		if (flag == "true") {
			break;
		}
	}
	return flag;
}

function setTsxsGzzt() {
	var dealTsxs = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlert("��ѡ����Ҫ���õļ�¼��");
	} else {
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				dealTsxs += rowsValue[i]["id"];
			} else {
				dealTsxs += rowsValue[i]["id"] + ",";
			}
		}
		showDialog("����ѧ��״̬����", 380, 200,
				"xlzx_tsxs.do?method=setTsxsGzzt&dealTsxs=" + dealTsxs);
	}
}

function exportTsxsList() {
	customExport("xlzx_tsxs_tsxs.do", exportTsxsData, 700, 500);
}

// ��������
function exportTsxsData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xlzx_tsxs.do?method=exportTsxsData&dcclbh="
			+ "xlzx_tsxs_tsxs.do";// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//����
function drxx(){
	toImportData("IMPORT_N10220");
	return false;
}