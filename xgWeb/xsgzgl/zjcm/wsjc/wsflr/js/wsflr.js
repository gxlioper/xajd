
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ¼��ļ�¼��");
	}
	var url = "cjWsflr.do?method=addWsflr&ccny=" + rows[0]["ccny"];
	document.forms[0].action=url;
	document.forms[0].submit();
}
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

function savefs(obj,wsfid,qsh){
	var str = jQuery(obj).val();
	if(str != ''){
		var reg = /^[0-9]+(.[0-9]{1})?$/;
		var result = reg.test(str);
		if(!result){
			showAlert("��ֵֻ��������һλС��");		
			return false;
		}
		if(parseInt(jQuery(obj).val()) > 100){
			showAlert("��ֵ���Ϊ100����ȷ�ϣ�");		
			return false;
		}
	}		
	var fz = obj.value;	
	jQuery.post("cjWsflr.do?method=saveWsf",{wsfid:wsfid,qsh:qsh,fz:fz},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}

function saveBz(obj,wsfid,qsh){
	if(jQuery(obj).val().length > 500){
		showAlert("���������������Ϊ500,���Ѿ���������ȷ�ϣ�",{},{"clkFun":function(){
		return false;
		}});
		
	}
	var fzbz = obj.value;
	jQuery.post("cjWsflr.do?method=saveWsf",{wsfid:wsfid,qsh:qsh,fzbz:fzbz},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}

function Tj(){
    var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	var flag = false;
	if (rows.length < 1) {
		showAlertDivLayer("������ѡ��һ����Ҫ�϶��ļ�¼��");
		return false;
	}
	jQuery.each(rows,function(i,n) {
		if(n['tjzt'] == '1') {
			flag = true;
			return false;						
		}	
	})
	if(flag) {
		showAlertDivLayer("���ύ�ļ�¼�����ٴ��ύ");
		return false;
	}
	jQuery.ajaxSetup({async:false});
	jQuery.post("cjWsflr.do?method=checkIsCanSubmit",{ids:ids},function(data){
		if (data == "true"){
			showConfirmDivLayer("��ȷ���ύ��",{"okFun":function(){
				jQuery.post("cjWsflr.do?method=tj",{ids:ids},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json'); 
			}});
		}else {
			showAlertDivLayer("��ֵδ¼�룬��ȷ�ϣ�");						
	    }
	});
	jQuery.ajaxSetup({async:true});
}

function fh(){
	refreshForm("/xgxt/cjWsf_wsflr.do");
}

function yzfz(obj){
	var reg = /^[0-9]+(.[0-9]{1})?$/;
	var str = jQuery(obj).val();
	var result = reg.test(str);
	if(!result){
		showAlert("ֻ��������һλС��");
		return false;
	}	
}

function ytjqslink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='ytjqsck(\""
	+ rowObject["ccny"]+"\");'>" + cellValue
	+ "</a>";
}

function wtjqslink(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='wtjqsck(\""
	+ rowObject["ccny"]+"\");'>" + cellValue
	+ "</a>";
}

function ytjqsck(ccny){
	showDialog("�鿴", 800, 350, "cjWsflr.do?method=viewqs&ccny="+ccny+"&tjzt=ytj");
}

function wtjqsck(ccny){
	showDialog("�鿴", 800, 350, "cjWsflr.do?method=viewqs&ccny="+ccny+"&tjzt=wtj");
}

function checkNumberinput(obj,num){
	if(parseInt(jQuery(obj).val()) > parseInt(num)){
		showAlert("��ֵ���Ϊ100����ȷ�ϣ�");
		return false;
	}
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

var DCCLBH = "cjWsf_wsflr.do";

//��������
function wsflrExportData() {
	setSearchTj();//���ø߼���ѯ����
	var ccny = jQuery("#ccny").val();
	var url = "cjWsflr.do?method=exportData&ccny="+ccny+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wsflrExportData);
}