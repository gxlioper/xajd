<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script>
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		//��ʼ��
		jQuery(document).ready(function(){ 
			var url = "qgzx_gwglnew.do?method=getStu&type=query&pkValue="+jQuery("#pkValue").val();
			//�ڹ���ѧ���Ի�
			var xn = jQuery("#xn").val();
			url = url+"&xn="+xn+"&sfxyzgsc="+jQuery("#sfxyzgsc").val();
			
			var gridSetting = {
					caption:"ѧ���б�",
					pager:"pager",
					url:url,
					colList:[
		                {label:'key',name:'xh', index: 'xh',hidden:true,key:true},
						{label:'�к�',name:'r', index: 'r',width:'4%'},
						{label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
						{label:'����',name:'xm', index: 'xm',width:'9%'},
						{label:'�Ա�',name:'xb', index: 'xb',width:'7%'},
						{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
						{label:'רҵ',name:'zymc', index: 'zymc',width:'16%'},
						{label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
						{label:'�Ƿ�������',name:'sfkns', index: 'sfkns',width:'10%'}
					],
					sortname: "r",
				 	sortorder: "asc"
				}
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		//��ӱ���ѧ��
		function zjBcStu(){
			var api = frameElement.api,W = api.get('parentDialog');
			var str = "";
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length>=1){
				for(var i=0;i<rows.length;i++){
					var pkValue = rows[i]["xh"];
					str += pkValue+"!!@@!!";
				}
				W.document.getElementById("xhs").value=str;
				api.close();
				W.document.getElementById("btn_getXsxx").onclick();
			}else{
				showAlert("��ѡ����Ҫ��ӵ�ѧ����");
			}
		}
		</script>
	</head>
	<body>

		<html:form action="/qgzx_gwglnew" method="post">
			
			<!-- ���Ի������� -->
			<input type="hidden" name="xn" id="xn" value="${xn}"/>
			<!-- ���Ի������� -->
			<input type="hidden" name="sfxyzgsc" id="sfxyzgsc" value="${sfxyzgsc}"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="zjBcStu();return false;" class="btn_zj">���</a>
						</li>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>ѧ���б�&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
		</html:form>
	</body>
</html>
