<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/sjxmsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"�û���Ϣ�б�",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"qgzx_kycxsjxmsq.do?method=getTea&type=query&xmid="+jQuery("#xmid").val()+"&zghs="+jQuery("#zghs").val(),
				colList : [ {
					label : 'key',
					name : 'zgh',
					index : 'zgh',
					key : true,
					hidden : true
				},{
					label : '�к�',
					name : 'r',
					index : 'r',
					width : '5%'
				},{
					label : '�û���',
					name : 'zgh',
					index : 'zgh',
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
					name : 'xymc',
					index : 'xymc',
					width : '15%'
				},{
					label : '��ϵ�绰',
					name : 'lxdh',
					index : 'lxdh',
					width : '15%'
				}],
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function sqjsZj() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("������ѡ��һ����ʦ��");
					return false;
				}
				
					setSqjs(rows);
				
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/qgzx_kycxsjxmsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xmid" styleId="xmid"/>
		<input type="hidden" name="zghs" id="zghs" value="${zghs}"/>
			<div class="toolbox">
			<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="sqjsZj();return false;" class="btn_zj">���</a>
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
