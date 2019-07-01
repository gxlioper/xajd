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
						caption:"�����б�",
						pager:"pager",
						url:"ttgl_stjggl.do?method=stjgglList&type=query",
						colList:[
						   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'��������',name:'stqc', index: 'stqc',width:'8%',formatter:stLink},
						   {label:'��������',name:'stlx', index: 'stlx',width:'15%'},
						   {label:'��������',name:'strs', index: 'strs',width:'15%'},
						   {label:'״̬',name:'stzt', index: 'stzt',width:'15%'},
						   {label:'������Դ',name:'ly', index: 'ly',hidden:true}//���Խ�����������
						],
						sortname: "stqc",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function stzz(){
			var rows = jQuery("#dataTable").getSeletRow();
			var height = jQuery(window).height()-210;
			if (rows.length != 1) {
				showAlertDivLayer("����ѡ��һ����¼��");
			}else {
				if(rows[0]["stzt"]!="Ԥ����"){
					showAlertDivLayer("ֻ��Ԥ���ں���ʽ���Ų��ܽ���ְ��ṹ����");
				}else{
					var url = 'ttgl_stjggl.do?method=stjggl&jgid='+ rows[0]["jgid"];
					showDialog("ְ��ṹ����", 790,height, url);
				}
			}
		}
		function ryManage() {
			var rows = jQuery("#dataTable").getSeletRow();
            var height = jQuery(window).height()-210;
			if (rows.length != 1) {
				showAlertDivLayer("����ѡ��һ����¼��");
			}else {
				if(rows[0]["stzt"]=="Ԥ����"||rows[0]["stzt"]=="��ʽ"){
					var url = 'ttgl_stcygl.do?method=ryManage&jgid='+ rows[0]["jgid"];
					showDialog("��Աְ�����", 790,height, url);
				}else{
					showAlertDivLayer("ֻ��Ԥ���ں���ʽ���Ų��ܽ��г�Աְ�����");
				}
			}
		}

		function View(jgid,ly) {
            var height = jQuery(window).height()-210;
			showDialog("�鿴������Ϣ", 790,height, "ttgl_stcygl.do?method=view&jgid=" + jgid+"&ly="+ly);
		}

		function stLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='View(\""+ rowObject["jgid"] + "\",\""+ rowObject["ly"] + "\");'>" + cellValue
					+ "</a>";
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
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="stzz();return false;" >ְ��ṹ����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ryManage();return false;" class="btn_xg" >��Աְ�����</a>
						</li>
					</ul>
				</div>

				<!-- �������� -->
				<logic:equal name="userType" value="stu">
					<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				</logic:equal>
				<logic:notEqual name="userType" value="stu">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</logic:notEqual>
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
