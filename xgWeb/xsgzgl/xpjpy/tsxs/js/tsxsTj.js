var tjszDialog ;
var gridSetting = {
	caption : "��Ŀ�б�",
	pager : "pager",
	url : "xpj_tsxs.do?method=viewTsxsTj&type=query",
	colList : [
  	    {label : 'ѧ�ڴ���',name : 'xq',index : 'xq',hidden : true},
	    {label : '���ʹ���',name : 'lxdm',index : 'lxdm',hidden : true},
		{label : 'ѧ��',name : 'xn',index : 'xn',width : '30%'}, 
		{label : 'ѧ��',name : 'xqmc',index : 'xqmc',width : '20%'},
		{label : '��������',name : 'lxmc',index : 'lxmc',width : '30%'},
		{label : '������',name : 'zrs',index : 'zrs',width : '20%',formatter:setZrs}
		],
	sortname : "xn,xq,lxdm",
	sortorder : "desc"
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});


function setColor(value,status){
	var color = "#0000ff";
	return value = "<font color='"+color+"'>" + value + "</font>";
}

/*
 *������
 */
function setZrs(cellValue,rowObject){
	var xn = rowObject.xn;
	var xq = rowObject.xq;
	var lxdm = rowObject.lxdm;
	var value = rowObject.zrs;
//	value = setColor(value,status);//
	value = "<a  href='javascript:void(0);' class='name' onclick='return tsxsXx(\""+xn+"\",\""+xq+"\",\""+lxdm+"\");'>"+value+"</a>";
	return value;
}

/*
 * ����ѧ����Ϣ
 */
function tsxsXx(xn,xq,lxdm){
	var url = 'xpj_tsxs.do?method=tsxsXx';
	url += "&xn=" + xn;
	url += "&xq=" + xq;
	url += "&lxdm=" + lxdm;
	var title = "����ѧ����Ϣ";
	showDialog(title, 800, 505, url,{close:function(){query();}});
}

function query(){
	var map = {};
	map["xn"] = jQuery("#xn").val();
	map["xq"] = jQuery("#xq").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add(mklx) {
	
	var url = "xpj_tsxs.do?method=tsxsZj&mklx="+mklx;
	var title = "����ѧ������";
	showDialog(title, 800,550, url,{close:function(){query();}});
}

function del() {
	var result = false;// jQuery����ѭ��
	var xn = "";
	var xq = "";
	var lxdm = "";
	var flag = false;

	var rows = jQuery("#dataTable").getSeletRow();
	var json = "{data:[";
	for ( var i = 0; i < rows.length; i++) {
		if (flag) {
			json += ",";
		} else {
			flag = true;
		}
		json += "{";
		json += "xn:'" + rows[i].xn + "'";
		json += ",xq:'" + rows[i].xq + "'";
		json += ",lxdm:'" + rows[i].lxdm + "'";
		json += "}";
	}
	json += "]}";
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",{"okFun":function(){
			var url = "xpj_tsxs.do?method=tsxsScForLx";
			jQuery.post(url, {
				json : json
			}, function(data) {
				if(data["success"] == false){
					showAlertDivLayer(data["message"]);
				}else{
					showAlertDivLayer(data["message"], {},{"clkFun": function(tag) {
						jQuery("#dataTable").reloadGrid();
					}});
				}
			}, 'json');
		}});
	}
}

//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_TSXSDR_10787");
	return false;

}

