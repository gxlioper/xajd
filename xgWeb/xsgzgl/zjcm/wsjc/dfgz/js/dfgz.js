function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �޸�
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'cjWsfDfgz.do?method=updateDfgz&dfszid=' + rows[0]["dfszid"];
		var title = "�޸�";
		showDialog(title, 600, 300, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("cjWsfDfgz.do?method=delGzsz", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

function saveForm(type){
	
	if(check("ccny-wwsj-wwzzsj") == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	
	var url = "cjWsfDfgz.do?method=saveForm&type=" +type;
	ajaxSubFormWithFun("DfgzForm", url, function(data) {
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
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function addQqxs() {
	var number = parseInt(jQuery("#tbody_gzxx").find("tr:last").find("td:eq(0)").children().attr("id").substr(9,1)) + 1;
	var html = "<tr><td><input type='checkbox' id='checkbox_" + number
	+ "'</td>";
	html+="<td>"
	html+= jQuery("#tbody_gzxx").find("tr:first").find("td:eq(1)").html();
	html+="</td><td>"
	html+= jQuery("#tbody_gzxx").find("tr:first").find("td:eq(2)").html();
	html+="</td>"
	html+="<td><input type='checkbox' id='checkbox_" + number + "' name='bybyb'/></td>"
	
//	if(jQuery("#tbody_gzxx").find("tr").length > 0){
//		var number = jQuery("#tbody_gzxx").find("tr:last").firstChild.attr('id').substr(9,1).parseInt() + 1;
//		var html = "<tr><td><input type='checkbox' id='checkbox_" + number
//		+ "'</td>";
//		html+="<td><html:select property='pfzid' styleId='pfzid'><html:options collection='pfzList' property='pfzid' labelProperty='pfzmc'/></html:select></td>";
//		html+="<td><html:text property='ccbl' styleId='ccbl' onkeyup='value=value.replace(/[^\d]/g,'');' />%</td>";
//		html+="<td><input type='checkbox' id='checkbox_" + number
//		+ "' name='bybyb'/></td>"
//	}else{
//		var number = 0;
//		var html = "<tr><td><input type='checkbox' id='checkbox_" + number
//		+ "'/></td>";
//		html+="<td><html:select property='pfzid' styleId='pfzid'><html:options collection='pfzList' property='pfzid' labelProperty='pfzmc'/></html:select></td>";
//		html+="<td><html:text property='ccbl' styleId='ccbl' onkeyup='value=value.replace(/[^\d]/g,'');' />%</td>";
//		html+="<td><input type='checkbox' id='checkbox_" + number
//		+ "' name='bybyb'/></td>"
//	}
	jQuery("#tbody_gzxx").append(html);
}

function changeNy(){
	var ny = jQuery("#ccny").val();
	var wwsj = ny + "-01";
	var nys = ny.split("-");
	var wwzzsj = ny +"-"+getDaysInMonth(nys[0],nys[1]);
	jQuery("#wwsj").val(wwsj);
	jQuery("#wwzzsj").val(wwzzsj);
}

/**
 * ��ȡ ��ǰ�µ�����
 * @param year
 * @param month
 * @return
 */
function getDaysInMonth(year,month){ 
	month = parseInt(month,10); //parseInt(number,type)��������������������2����������ʾ���ƵĻ���Ĭ����10���ơ� 
	var temp = new Date(year,month,0); 
	return temp.getDate(); 
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

function gzsd(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�����ļ�¼��");
		return false;
	}
	if("0"!=rows[0]["ytjqs"]){
		showAlertDivLayer("���·��������ּ�¼�����������ã�");
		return false;
	}
	var url = 'cjWsfDfgz.do?method=gzsd&dfszid=' + rows[0]["dfszid"];
	var title = "��������";
	showDialog(title, 600, 400, url);
}


function gzszLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='szck(\""+rowObject["dfszid"]+"\");'>"+cellValue+"</a>";
}

//����鿴
function szck(dfszid) {
	showDialog("�鿴", 600, 400, "cjWsfDfgz.do?method=viewGzsz&dfszid=" + dfszid);
}

