var xslxdms = "";
var xslxmcs = "";
function init(){
				var xslxdm = $("xslxdm").value;
				var xslxList = xslxdm.split(",");
				for(var i=0;i<xslxList.length;i++){
					jQuery("input[type='checkbox'][name=xslxBoxList][value='"+xslxList[i]+"']").attr("checked",true);
				}	
			}

jQuery(function() {
	var gridSetting = {
		caption : "����ѧ���б�",
		pager : "pager",
		url : "tsxs_tsxswh.do?method=tsxsglManage&doType=query",
		colList : [ {
			label : 'ѧ��',
			name : 'xh',
			index : 'xh',
			key:true,
			formatter : xhLink
		}, {
			label : '����',
			name : 'xm',
			index : 'xm'
		}, {
			label : '�Ա�',
			name : 'xb',
			index : 'xb'
		}, {
			label : '�꼶',
			name : 'nj',
			index : 'nj'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc'
		}, {
			label : '�����༶',
			name : 'bjmc',
			index : 'bjmc'
		}, {
            label : 'רҵ�༶',
            name : 'zybjmc',
            index : 'zybjmc'
        },{
			label : '¼��ʱ��',
			name : 'lrsj',
			index : 'lrsj'
		}, {
			label : 'ѧ������',
			name : 'xslxdm',
			index : 'xslxdm',
			formatter : getXslxmc
		}, {
			label : '��ע״̬',
			name : 'gzztmc',
			index : 'gzztmc'
		} , {
            label : '��Ժ',
            name : 'symc',
            index : 'symc'
        } ],
		sortname : "lrsj",
		sortorder : "desc"
	};
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"
			+ rowObject["xh"] + "');\">" + cellValue + "</a>";
}


function showDetail(xh) {
	showDialog("����ѧ������", 700, 400,
			"tsxs_tsxswh.do?method=tsxsView&xh=" + xh);
}

function addTsxs() {
	showDialog("��������ѧ��", 700, 400, "tsxs_tsxswh.do?method=tsxsAdd");

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
	showDialog("�޸�����ѧ��", 700, 400,
			"tsxs_tsxswh.do?method=tsxsUpdate&xh=" + xh);
}

function deleteTsxs() {
	var  ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("tsxs_tsxswh.do?method=delTsxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
function save(){
 	if( jQuery("#xh").val()=="" ||jQuery("#clcs").val()==""  ||jQuery("#gzzt").val()=="" ||
 			jQuery("input[type='checkbox'][name='xslxBoxList']:checked").size()==0 || getXslx()==""){
 		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
 	}
	
	
	var url = "tsxs_tsxswh.do?method=tsxsSave";
	

	ajaxSubFormWithFun("TsxsglForm", url, function(data) {
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
function getXslx(){
	 var size = jQuery("input[type='checkbox'][name='xslxBoxList']:checked").size();
	 if(size == 0){
		showAlert('��ѡ��ѧ�����ͣ�');
		return "";
	}
	
	var flag = false;
	jQuery("input[type='checkbox'][name=xslxBoxList]:checked").each(function(index){
		if(flag){
			xslxdms += ",";
			xslxmcs += ",";
		}else{
			flag = true;
		}
	
		xslxmcs += jQuery(this).parent().children().eq(-1).html();
		xslxdms += jQuery(this).val();
	});
	jQuery("#xslxdm").val(xslxdms);
	jQuery("#xslxmc").val(xslxmcs);
}
function getXslxmc(cellValue, rowObject) {
	var xslxmc = '';
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery.post("tsxs_xslxwh.do?method=getXslxmc", {
		xslxdm : cellValue
	}, function(data) {
		xslxmc = data;
	}, '');
	jQuery.ajaxSetup( {
		async : true
	});
	return xslxmc;
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
	
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlert("��ѡ����Ҫ���õļ�¼��");
	} else {
		showDialog("����ѧ��״̬����", 380, 200,
				"tsxs_tsxswh.do?method=setTsxsGzzt&ids=" + ids);
	}
}
var dcclbh="tsxsgl_tsxswh.do";
function exportTsxsList() {
	customExport(dcclbh, exportTsxsData, 700, 500);
}

// ��������
function exportTsxsData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "tsxs_tsxswh.do?method=exportTsxsData&dcclbh="
			+ dcclbh;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//����
function drxx(){
	toImportDataNew("IMPORT_XSXX_TSXS");
	return false;
}

