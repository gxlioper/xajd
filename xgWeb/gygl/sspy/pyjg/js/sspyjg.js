/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 * @return
 */
function sspyjgAdd(){
	var url = "sspyjg.do?method=sspyjgAdd";
	var title = "�������Ž������";
	showDialog(title,790,550,url);
}

/**
 * ѡ��¥��onchange�¼�
 * @return
 */
function loadLdxx(){
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx',{lddm:jQuery('#lddm').val()},function(data){
		var qsch = parseInt(data.qsch);
		var ldcs = parseInt(data.ldcs);
		var sfhlc = data.sfhlc;
		var ldxb = data.ldxb;
		var count = 0;
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--��ѡ��--</option>");
		while(count<ldcs){
			var chdm;
			var chmc;
			if('��' == sfhlc){
				
				if((qsch+count)>= 0){
					chdm = qsch>0 ? qsch+count:qsch+count+1;
					chmc = chdm + "��";
				}else{
					chdm = qsch+count;
					chmc = "B" + Math.abs(chdm) + "��";	
				}
			}else{
				chdm = qsch+count;
				chmc = chdm<0 ? "B" + Math.abs(chdm) + "��" : chdm + "��";
			}
			var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
			jQuery('#ch').append(option);
			
			count ++;
		}
		loadQs();
	},'json');
}


function loadQs(){
	jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm',{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
		jQuery('#qsh').empty();
		jQuery('#qsh').append("<option value=''>--��ѡ��--</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
				jQuery('#qsh').append(option);
			}
		}
	});
}

function loadQsdh(){
	var obj = new Object;
	obj.lddm = jQuery('#lddm').val();
	obj.ch = jQuery('#ch').val();
	obj.qsh = jQuery('#qsh').val();
	jQuery.getJSON('gypy.do?method=loadQsxxdh',obj,function(data){
			var qsdh = data.qsdh;
			jQuery('#qsdh').val(qsdh);
			jQuery('#xydm').val(data.xydm);
	});
	
	jQuery("#xsList").empty();
	var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
	var html = "";
	jQuery.post("sspysq.do?method=getCwxx", {
		ldqsxx : ldqsxx
	}, function(data) {
		for(var i =0;i<data.length;i++){
			html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
		}
		jQuery("#xsList").append(html);
	}, 'json');
}


/**
 * ��������ֶμ���
 */
var ids = "lddm-qsh-ch-xn-xq-pyxmdm-sqly";

/**
 * ����
 * @return
 */
function sspyjgSave(){
	
	/*��֤�����ֶ�*/
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		return false;
	}
	
	var url = "sspyjg.do?method=sspyjgSave";
	ajaxSubFormWithFun("sspyjgForm", url, function(data){
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

/**
 * �޸�
 * @return
 */
function sspyjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if ("1" == rows[0]["sjly"]){
			showAlertDivLayer("����������ݲ����޸ģ�");
			return false;
		}
		var title = "�޸���������";
		var url = "sspyjg.do?method=sspyjgUpdate&guid="+rows[0]["guid"];
		showDialog(title, 680, 490, url);
	}
}

/**
 * ɾ��
 * @return
 */
function sspyjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("sspyjg.do?method=sspyjgDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * �������ܱ��
 */
var DCDLBH = "sspy_jg.do";

/**
 * �������ŵ���
 * @return
 */
function sspyjgExport(){
	customExport(DCDLBH, sspyjgExportData);
}

/**
 * ����ִ��
 */
function sspyjgExportData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "sspyjg.do?method=sspyjgExport&dcclbh=" + DCDLBH;
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ���Һ�����
 * @return
 */
function qshLink(cellValue, rowObject){
	return "<a hrer='javascript:void(0);' class='name' onclick='sspyjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

/**
 * �鿴
 * @return
 */
function sspyjgView(guid){
	var url = "sspyjg.do?method=sspyjgView&guid="+guid;
	var title = "�������Ž���鿴";
	showDialog(title,700,485,url);
}