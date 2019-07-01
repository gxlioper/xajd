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

		jQuery(function(){
			var gridSetting = {
						caption:"���⽻������б�",
						pager:"pager",
						url:"xg_dwjl_dwjl.do?method=dwjlcx&type=query",
						colList:[
						   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'����ѧУ',name:'jlxx', index: 'jlxx',width:'15%'},
						   {label:'����רҵ',name:'jlzy', index: 'jlzy',width:'15%'},
						   {label:'������ʼʱ��',name:'jlkssj', index: 'jlkssj',width:'15%'},
						   {label:'��������ʱ��',name:'jljssj', index: 'jljssj',width:'15%'}
						],
						sortname: "jlkssj",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var url = "xg_dwjl_dwjl.do?method=addDwjl";
			showDialog("����",790,476,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			}else {
				var url = 'xg_dwjl_dwjl.do?method=updateDwjl&id='+ rows[0]["id"];
				showDialog("�޸�", 790,470, url);
			}

		}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xg_dwjl_dwjl.do?method=dwjlDel", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

		function dwjlView(id, xh) {
			showDialog("�鿴", 700, 401, "xg_dwjl_dwjl.do?method=viewDwjl&id=" + id
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='dwjlView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

	
		//�Զ��嵼�� ����
		function exportConfig() {
			customExport( "xg_dwjl_dwjljg.do", ExportData);
		}

		// ��������
		function ExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xg_dwjl_dwjl.do?method=exportData&dcclbh=xg_dwjl_dwjljg.do";
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		
		// ����
		function zpdc() {
			showDialog("", 400, 150, 'rcsw_ylbx_ylbxjggl.do?method=zpdc');
		}
		// ����
		function dszmdy() {
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length!=1){
				showAlertDivLayer("��ѡ��һ����Ҫ��ӡ�ļ�¼��");
				return false;
				}
			var url="rcsw_ylbx_ylbxjggl.do?method=dszmdy&jgid="+ids;
			window.open(url);
		}
		//�°浼��
		function dr() {
			toImportDataNew("IMPORT_N011201_DWJLWH");
			return false;

		}
				
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
		<html:form action="/xg_dwjl_dwjl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="userType" value="stu">
				  		    <logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">����</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:notEqual>
						
					</ul>
				</div>
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
