<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
            var gridSetting = {
                caption:"�鵥�б�",
                pager:"pager",
                url:"dekt_smwhgl.do?method=smwhList&type=query",
                colList:[
                    {label:'key',name:'smid', index: 'smid',key:true ,hidden:true},
                    {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
                    {label:'����',name:'ssm', index: 'ssm',width:'10%',formatter:xhLink},
                    {label:'������',name:'cbs', index: 'cbs',width:'15%'},
                    {label:'����',name:'author', index: 'author',width:'8%'},
                    {label:'����',name:'lx', index: 'lx',width:'8%'},
                    {label:'�Ƿ��Ƽ�',name:'sftjmc', index: 'sftjmc',width:'8%'},
                    {label:'����������',name:'ebook', index: 'ebook',width:'15%',formatter:ebookLink}
                ]
            };
jQuery(function(){

	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}
function view(smid) {
	showDialog("�鿴�鱾��Ϣ",700,300, "dekt_smwhgl.do?method=view&smid=" + smid);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["smid"] + "\");'>" + cellValue
			+ "</a>";
}
function ebookLink(cellValue, rowObject) {
    if(cellValue == null) cellValue='';
return "<a href='javascript:void(0);'  onclick='ebook(\""
			+ rowObject["ebook"] + "\");'>" + cellValue + "</a>";
}
function ebook (url){
    if(url.indexOf("http://") == -1 || url.indexOf("https://") == -1 ){
        url = "https://"+url;
	}
	window.open(url);
}
function add(){
	var url = "dekt_smwhgl.do?method=add";
	var title = "�����鱾��Ϣ";
	showDialog(title,700,500,url);
	
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
			var url = 'dekt_smwhgl.do?method=update&smid=' + rows[0]["smid"];
			showDialog("�޸��鱾��Ϣ", 700,500, url);
	}
}


//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("dekt_smwhgl.do?method=del", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


//�°浼��
function dr() {
	toImportDataNew("IMPORT_N820104_SMWH");
	return false;
}

var DCCLBH = "xg_dekt_smwhgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "dekt_smwhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_smwhgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
