jQuery(function(){
	var gridSetting = {
		caption : "���˱��б�",
	pager : "pager",
	url : "khglKhbgl.do?method=getKhbglList&type=query",
	colList : [ 
	   {label : 'khbid',name : 'khbid',index : 'khbid',key : true,hidden:true},
	   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true}, 
	   {label : 'sfnz',name : 'sfnz',index : 'sfnz',hidden:true}, 
	   {label : 'sfqy',name : 'sfqy',index : 'sfqy',hidden:true},
	   {label : '���˱�����',name : 'khbmc',index : 'khbmc',width : '20%'},
	   {label : '���˶���',name : 'khdxmc',index : 'khdxmc',width : '15%'},
	   {label : '����ʱ��',name : 'cjsj',index : 'cjsj',width : '12%'},
	   {label : '�޸�ʱ��',name : 'xgsj',index : 'xgsj',width : '12%'},
	   {label : '������',name : 'sts',index : 'sts',width : '8%'},
	   {label : '�Ƿ�����',name : 'sfqymc',index : 'sfqymc',width : '8%', formatter:setColor}
	   ]
}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})


function setColor(cellValue, rowObject) {
	var color;
	if (rowObject.sfqy == '1') {
		color = "have";
	} else {
		color = "non";
	}
	return value = "<font class='" + color + "'>" + cellValue + "</font>";
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "khglKhbgl.do?method=addKhb";
	var title = "���ӿ��˱�";
	showDialog(title, 350, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	} 
	var url = 'khglKhbgl.do?method=updateKhb&khbid=' + rows[0]["khbid"];
	var title = "�޸Ŀ��˱�";
	showDialog(title, 350, 200, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
	showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglKhbgl.do?method=delKhb", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}

function khnrwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�����ļ�¼��");
		return false;
	}
	var khbid=rows[0]["khbid"];
	var url="khglKhnrgl.do?method=getKhnrglList&khbid="+khbid;
	document.location.href=url;
}
//��������Ԥ��
function khnryl(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�����ļ�¼��");
		return false;
	}
	var khbid=rows[0]["khbid"];
	var khbmc=rows[0]["khbmc"];
	var url="khglKhbgl.do?method=khnryl&khbid="+khbid+"&khbmc="+khbmc;
	showDialog('��������Ԥ��',800,550,url);
}

function khbfz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�����ļ�¼��");
		return false;
	}
	var khbid=rows[0]["khbid"];
	var url="khglKhbgl.do?method=khbfz&khbid="+khbid;
	showDialog('���˱���',350,200,url);
}
//���˱���������
function qySz(qyzt) {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var czmc=null;
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ������Ҫ�����ļ�¼��");
		return false;
	}
	if("1"==qyzt){
		czmc="����";
	}else{
		czmc="ͣ��";
	}
	showConfirmDivLayer("��ȷ��"+czmc+"ѡ��Ŀ��˱���", {
			"okFun" : function() {
				jQuery.post("khglKhbgl.do?method=qySz", {
					values : ids.toString(),
					sfqy:qyzt
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}
