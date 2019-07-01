<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/zgjg/js/zbjg.js"></script>
		<script type="text/javascript">
		


	
	jQuery(function() {
		var gridSetting = {
				pager : "pager",
				url : "xlzxnew_zbjg.do?method=searchZbjgCx",
				colList : [
						{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
						{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
						{ label : '��ʼ����', name : 'zbksrq', index : 'zbksrq', width : '10%' },
						{ label : '��������', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
						{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true },
						{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true }
						],
				 };
		 var map = getSuperSearch();
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	});

</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzxnew_zbjg" >
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">�ϱ�</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
					</ul>
				</div>
					<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
