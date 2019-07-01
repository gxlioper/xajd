<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�û���Ϣ�б�",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"xmsbGlygl.do?method=glyZj&type=query",
				colList : [ {
					label : 'key',
					name : 'yhm',
					index : 'yhm',
					key : true,
					hidden : true
				},{
					label : '�к�',
					name : 'r',
					index : 'r',
					width : '5%'
				},{
					label : '�û���',
					name : 'yhm',
					index : 'yhm',
					width : '10%'
				},{
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '������',
					name : 'zmc',
					index : 'zmc',
					width : '15%'
				}, {
					label : '���ڲ���',
					name : 'bmmc',
					index : 'bmmc',
					width : '15%'
				}],
				
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function zjbcGly(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("������ѡ��һ���û���");
					return false;
				} 
					 	jQuery.post("xmsbGlygl.do?method=saveGly", {
							values : ids.toString()
						}, function(data) {
						    		 showAlert(data["message"],{},{"clkFun":function(){
						    				if (parent.window){
						    					refershParent();
						    				}
						    			}});
							},"json");

			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/xmsbGlygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="zjbcGly();return false;" class="btn_zj">���</a>
						</li>
				</ul>
				</div>
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
					<!--����start-->
			<h3 class="datetitle_01">
				<span> �û���Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y: scroll;height:300px;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
