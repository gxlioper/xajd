var checkId="ystxmmc-ystlbdm-xmlbdm-lxdh-gkdwdm-fzrlb-fzr-zdls-ystclsj-splc";
var zdmcList='�����ż��-�����Ż����';
var zsnumList='500-500';

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='YstwhView(\""
			+ rowObject["ystid"] + "\");'>" + cellValue + "</a>";
}

function YstwhView(ystid) {
	showDialog("�����Ų鿴", 750, 400, "ystglYstwh.do?method=viewYstwh&ystid="
			+ ystid);
}
function showXsxxNotF5CallBack(rowData) {
	jQuery("#stfzrxm").val(rowData["xm"]);
	jQuery("#fzr").val(rowData["xh"]);
}
//ѡ���ʦ�ص�����
function showFdysNotF5CallBackAnother(rowData) {
	jQuery("#stfzrxm").val(rowData["xm"]);
	jQuery("#fzr").val(rowData["zgh"]);
}

function showFdysNotF5CallBack(rows){
	jQuery("#zdlsxm").val(rows['xm']);
	jQuery("#zdls").val(rows['zgh']);
	jQuery("#ssbm").val(rows['bmdm']);
	jQuery("#zdlslxfs").val(rows['lxdh']);
	jQuery("#zcmc").val(rows['zcmc']);
	jQuery("#zdlszc").val(rows['zc']);
	
}
//��Ŀ���ø�ʽ��
function setXmsz(cellValue, rowObject) {
	var ystid = rowObject.ystid;
	var splc = rowObject.splc;
	var value = "δ����";
	var status = '0';
	var color;
	var currDate = jQuery("#currDate").val();
	if ((null == rowObject.sqkssj || currDate >= rowObject.sqkssj)
			&& (null == rowObject.sqjssj || currDate <= rowObject.sqjssj)&&'1'==rowObject.sqkg) {
		value = "������";
		status = '1';
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return xmsz(\"" + ystid
			+ "\",\"" + splc+ "\");' >" + value + "</a>";
	return value;
}
function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}
/*
 * ��Ŀ��������
 */
function xmsz(ystid,splc) {
	if(splc == "null" || splc == null || jQuery.trim(splc) == ""){
		showAlertDivLayer("�������������޸������������̣�");
		return false;
	}
	if (ystid == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		ystid = rows[0]["ystid"];
	}
	var url = 'ystglYstwh.do?method=xmsz&ystid=' + ystid;
	var title = "��Ŀ����";
	showDialog(title, 520, 380, url);
}
function saveYstwh(type) {
	var flg = true;
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(!checkTextAreaLength("ystjj-ysthjqk",zdmcList,zsnumList)){
		flag=false;
	}
	jQuery("#ssbm").attr("disabled",false);; 
	var url = "ystglYstwh.do?method=saveYstwh&type="+type;
	ajaxSubFormWithFun("YstwhForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			flg = false;
			 showAlert(data["message"]);
		}
	});
}
function add() {
	var url = "ystglYstwh.do?method=addYstwh";
	var title = "����������";
	showDialog(title, 800, 550, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else if ('false' == rows[0]["sfkxg"]) {
		showAlertDivLayer("��ѡ���û������걨����Ŀ��");
	} 
	else {
		var url = 'ystglYstwh.do?method=editYstwh&ystid=' + rows[0]["ystid"];
		var title = "�������޸�";
		showDialog(title, 800, 550, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
		var url = "ystglYstwh.do?method=delYstwh";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}

//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_N730203_YSTWH");
	return false;

}

var DCCLBH = "ystgl_ystgl_ystwh.do";// dcclbh,�������ܱ��

// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, YstwhExportData);
}

// ��������
function YstwhExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "ystglYstwh.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�޸�
function updatestcyxx(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} 
		var ystid = rows[0]["ystid"];
		var rtid = "";
		if(getsqkg(ystid) != 1){
			showAlertDivLayer("��ǰ��������Ŀδ������״̬���������޸ĳ�Ա��Ϣ��");
			return false;
		}
	var url = 'stglRtjg.do?method=RtjgWh&ystid=' + ystid+"&rtid="+rtid;
	var title = "�����ų�Ա��ϸ�޸�";
	showDialog(title, 770, 550, url);
	
	
}

function getsqkg(ystid){
	var sqkg = 1;
	var url = "stglRtjg.do?method=getSqkg";
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'ystid='+ystid,
			async: false,
			success:function(result){
				if(result==null||result=="null"){
					return false;
				}else{
					sqkg = result;
				}
			}
			
		});
	   return sqkg;
	
}

function addXs() {
	var xmdm=jQuery("#xmdm").val();
	var jcxf=jQuery("#jcxf").val();
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_xmsqxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'stglRtjg.do?method=getStu'+'&xhArr='+xhArr;
	showDialog('', 800, 550, url);
	return false;
}

function delXs() {
	var sjly="";
	var xhs="";
	var xh="";
	var rtids = "";
	var message = "ɾ���ɹ���";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ��ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		sjly=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=sjly]").val();
		xh=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=xh]").val();
		temprtid = jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=rtid]");
		if("1"==sjly){
			if(i!=0){
				xhs+=",";
			}
			xhs+=xh;
		}
		if(temprtid != null){
			if(jQuery(temprtid).val() != '' &&��jQuery(temprtid).val() != null){
				rtids+= jQuery(temprtid).val()+",";
			}
		}
	}
	if(""!=xhs){
		showAlertDivLayer("<font color='red'>["+xhs+"]</font>"+"Ϊ�������ݣ�����ɾ����");
		return false;
	}
	if(rtids != "" && rtids != null){
		var url = "stglRtjg.do?method=delCyxx";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'values='+rtids.toString(),
		async: false,
		success:function(result){
			if(result==null||result=="null"){
				return false;
			}else{
				message = "ɾ���ɹ���";;
			}
		 }
	    });
	}

	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
	showAlert(message);
}

function setSqxs(rows,selobj) {

	var html = "";
	var jcxf=jQuery("#jcxf").val();
	var api = frameElement.api;
	var qqxsTrLen = api.get('parentDialog').jQuery("#tbody_xmsqxx tr").length;	
	for ( var int = 0; int < rows.length; int++) {
		var index=qqxsTrLen+int;
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/>"+"<input type='hidden' name='xh' id = 'xh_"+int+"' value='" +rows[int]["xh"]+"'/><input type='hidden' name='rtid' value=''/>"+"</td>";
		html += "<td>" + rows[int]["xh"] + "</td>";
		html += "<td>" + rows[int]["xm"] + "</td>";
		html += "<td>" + rows[int]["xb"] + "</td>";
		html += "<td>"+selobj+"</td>"
		html +="<td>"+"<input name = 'tc' id='tc_"+int  + "' maxlength='100'>";
		html +="<td>"+"<input name = 'sqly' id='sqly_"+int  + "' maxlength='100'>";
		

		jQuery("#tbody_xmsqxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.sqxsZj(html);

	iFClose();
}

function sqxsZj(html){
	jQuery("#tbody_xmsqxx").append(html);	
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

//ά����Ϣ����
function saveJgwh(){
	//alert(parseInt(jQuery("#flagnum").val()));
	if(jQuery("#tbody_xmsqxx tr").length == 0){
		showAlert("������д��Ա��Ϣ��");
		return false;
	}
	var flag = true;
	if(jQuery("input[name='usertype']").val() != 'stu'){
		jQuery("#tbody_xmsqxx tr").each(function(b){
			var temptc=jQuery(this).find("td:eq(5) input[name='tc']").val();
			var tempsqly=jQuery(this).find("td:eq(6) input[name='sqly']").val();
			if(temptc == '' || tempsqly == ''){
				flag = false;
				showAlert("��"+"<font color='red'>*</font>"+"�������Ϊ�գ�");
				return false;
			}
		});
	}else{
		var temptc=jQuery("input[name='tc']").val();
		var tempsqly=jQuery("input[name='sqly']").val();
		if(temptc == '' || tempsqly == ''){
			flag = false;
			showAlert("��"+"<font color='red'>*</font>"+"�������Ϊ�գ�");
			return false;
		}
	}

	if(flag == false){
		return false;
	}
	if(jQuery("input[name='usertype']").val() != 'stu'){
		jQuery("select[name='rylbdm']").attr("disabled",false);
		var url = "stglRtjg.do?method=saveJgwh"
			ajaxSubFormWithFun("RtjgForm", url, function(data) {
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
	}else{
		var url = "stglRtjg.do?method=saveJgwh_XS"
			ajaxSubFormWithFun("RtjgForm", url, function(data) {
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
	
}

//����
function copyYstxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����������Ŀ��");
		return false;
	}
	var url = "ystglYstwh.do?method=copyYstxx&ystxmmc="+rows[0]['ystxmmc']+"&ystid="+rows[0]['ystid'];
	var title = "�����Ÿ���";
	showDialog(title, 400, 180, url);
}


//����������
function saveCopyYstxx(){
	if(jQuery("#ystxmmc").val()=="" || jQuery("#xn").val()==""){
		showAlert("��������Ŀ���ƺ���Чѧ�겻��Ϊ�գ�")
		return false;
	}
	var url = "ystglYstwh.do?method=saveCopyYstxx"
		ajaxSubFormWithFun("YstwhForm", url, function(data) {
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


//ajax��̬��ȡ��Ŀ���list
function changeYstlb(){
	var ystlbdm=jQuery("#ystlbdm").val();
	var html="";
	jQuery.post('ystglYstwh.do?method=getXmlblist',{'ystlbdm':ystlbdm},function(data){
		if(data != null){
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				html+="<option value='"+o.xmlbdm+"'>"+o.xmlbmc+"</option>";
			}
		}
		jQuery("#xmlbdm").html(html);
	},'json');
};