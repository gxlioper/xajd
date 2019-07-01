var gridSetting = {
	caption : "�������б�",
	pager : "pager",
	url : "khglPfzgl.do?method=getPfzglList&type=query",
	colList : [ 
	   {label : 'pfzid',name : 'pfzid',index : 'pfzid',key : true,hidden:true},
	   {label : 'khdxid',name : 'khdxid',index : 'khdxid',hidden:true},
	   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true},
	   {label : 'khlx',name : 'khlx',index : 'khlx',hidden:true}, 
	   {label : 'sfnz',name : 'sfnz',index : 'sfnz',hidden:true}, 
	   {label : 'khdxsfnz',name : 'khdxsfnz',index : 'khdxsfnz',hidden:true}, 
	   {label : '����������',name : 'pfzmc',index : 'pfzmc',width : '25%'},
	   {label : '����',name : 'pflxmc',index : 'pflxmc',width : '25%'},
	   {label : '���˶���',name : 'khdxmc',index : 'khdxmc',width : '25%'}, 
	   {label : '���˶���������',name : 'khdxrs',index : 'khdxrs',width : '25%',formatter:khdxrsLink},
	   {label : 'δ��������',name : 'wfprs',index : 'wfprs',width : '25%',formatter:wfprLink}
	   ],
	sortname : "pfzmc",
	sortorder : "desc"
}
//���˶�������鿴
function yhck(khdxid,khlx,sfnz,khdxmc) {
	showDialog("�鿴", 750, 550, "khglKhdxgl.do?method=viewKhdxList&khdxid="
			+ khdxid + "&khlx=" + khlx+"&sfnz="+sfnz+"&khdxmc="+khdxmc);
}

function wfprCk(khdxid,pfzid,khlx,sfnz,pflx) {
	showDialog("�鿴", 750, 550, "khglKhdxgl.do?method=viewKhdxList&khdxid="
			+ khdxid + "&pfzid=" + pfzid+"&khlx=" + khlx+"&sfnz="+sfnz+"&fpzt=wfp"+"&pflx="+pflx);
}

function khdxrsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["khdxid"]+"\",\""+rowObject["khlx"]+"\",\""+rowObject["khdxsfnz"]+"\",\""+rowObject["khdxmc"]+"\");'>"+cellValue+"</a>";
}
//δ�����˲鿴
function wfprLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='wfprCk(\""+rowObject["khdxid"]+"\",\""+rowObject["pfzid"]+"\",\""+rowObject["khlx"]+"\",\""+rowObject["khdxsfnz"]+"\",\""+rowObject["pflx"]+"\");'>"+cellValue+"</a>";
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

function query() {
	var map = {};
	map["pfzmc"] = jQuery("#pfzmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "khglPfzgl.do?method=addPfz";
	var title = "����������";
	showDialog(title, 350, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var yfpcy='0';
	if (rows.length != 1) {
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	}
	if(rows[0]["khdxrs"]!=rows[0]["khdxrs"]){
		yfpcy='1';
	}
	//���ö��������޸�
	if ('2'==rows[0]["sfnz"]) {
		showAlertDivLayer("���ö��������޸ģ�");
		return false;
	}
	var url = 'khglPfzgl.do?method=updatePfz&pfzid=' + rows[0]["pfzid"]+"&yfpcy="+yfpcy;
	var title = "�޸�������";
	showDialog(title, 350, 200, url);
	
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer(jQuery("#lable_some_sc").val());
		return false;
	}
	for ( var int = 0; int < rows.length; int++) {
		if('2'==rows[int]["sfnz"]){
			showAlertDivLayer("���ö�������ɾ����");
			return false;
		}
		
	}
	showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				jQuery.post("khglPfzgl.do?method=delPfz", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});	
}
function pfzwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ������Ҫ�����ļ�¼��");
		return false;
	}
	var pflx=rows[0]["pflx"];
	var pfzid=rows[0]["pfzid"];
	var khdxid=rows[0]["khdxid"];
	var khlx=rows[0]["khlx"];
	var url="khglPfzgl.do?method=showKhdx&pflx="+pflx+"&pfzid="+pfzid+"&khdxid="+khdxid+"&khlx="+khlx;
	document.location.href=url;
}
