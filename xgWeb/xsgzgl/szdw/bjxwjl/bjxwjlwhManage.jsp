<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/wh.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "�༶��Ϊ��¼",
		pager : "pager",
		url : "szdw_bjxwjlwh.do?method=query",
		colList : [
				{ label : 'key', name : 'guid', index : 'guid',key : true, hidden : true },
				{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%'},
				{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '4%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '10%' },
				{ label : '�༶', name : 'bjmc', index : 'bjdm', width : '14%' },
				{ label : '��¼��', name : 'jlrmc', index : 'jlrmc', width : '8%' },
				{ label : '��¼ʱ��', name : 'jlsj', index : 'jlsj', width : '8%' },
				{ label : '����', name : 'jlnrxs', index : 'jlnrxs', width : '40%' }],
		sortname : "jlsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
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
		<html:form action="/szdw_bjxwjlwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addSq();return false;"  title="����ð�ť�����������дҳ�档">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateSq();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteSq();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ckSq();return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
						</logic:equal>				
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�༶��Ϊ��¼&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
