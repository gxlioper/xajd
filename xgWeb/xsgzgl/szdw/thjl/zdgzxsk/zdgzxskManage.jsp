<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	 	
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type='text/javascript'>
			jQuery(function() {
				var gridSetting = {
					caption : "ѧ���б�",
					pager : "pager",
					url : "szdw_zdgzxsk.do?method=zdgzxskManage&doType=query",
					colList : [ {
						label : 'id',
						name : 'id',
						index : 'id',
						hidden : true,
						key : true
					}, {
						label : 'ѧ��',
						name : 'xh',
						index : 'xh',
						formatter : xhLink
					}, {
						label : '����',
						name : 'xsxm',
						index : 'xsxm'
					}, {
						label : '�Ա�',
						name : 'xsxb',
						index : 'xsxb'
					}, {
						label : '�꼶',
						name : 'nj',
						index : 'nj'
					}, {
						label : jQuery("#xbmc").val(),
						name : 'xymc',
						index : 'xymc'
					}, {
						label : '�༶',
						name : 'bjmc',
						index : 'bjmc'
					}, {
						label : '��ע�ȼ�',
						name : 'gzdj',
						index : 'gzdj'
					}, {
						label : '̸��ʱ��',
						name : 'thsj',
						index : 'thsj',
						formatter : getNewDate
					}, {
						label : '̸����ʦ',
						name : 'jsxm',
						index : 'jsxm'
					} ],
					sortname : "",
					sortorder : ""
				};
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs() {
				var map = getSuperSearch();
	
				jQuery("#dataTable").reloadGrid(map);
			}
	
			function getNewDate(cellValue) {
				var newDate = cellValue.substring(0, 10);
				return newDate;
			}

			function xhLink(cellValue, rowObject) {
				return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"xhLinkViewZdgzxsk('"+rowObject["id"]+"');\">" + cellValue + "</a>";
			}
	
			function xhLinkViewZdgzxsk(id) {
				showDialog("�鿴̸����¼", 700, 505,
						"szdw_thjl.do?method=thjlDetail&doType=view&id=" + id);
			}
			function viewZdgzxsk() {
				var id = '';
				var rowsValue = jQuery("#dataTable").getSeletRow();
				if (rowsValue.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
					return false;
				} else {
					id = rowsValue[0]["id"];
				}
				showDialog("�鿴̸����¼", 700, 505,
						"szdw_thjl.do?method=thjlDetail&doType=view&id=" + id);
			}
	
			function exportZdgzxsk() {
				customExport("szdw_thjl_thjl.do", exportZdgzxskData, 700, 500);
			}
	
			// ��������
			function exportZdgzxskData() {
				setSearchTj();// ���ø߼���ѯ����
				var url = "szdw_zdgzxsk.do?method=exportZdgzxskData&dcclbh="
						+ "szdw_thjl_thjl.do";// dcclbh,�������ܱ��
				url = addSuperSearchParams(url);// ���ø߼���ѯ����
				jQuery("#form").eq(0).attr("action", url);
				jQuery("#form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_zdgzxsk" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="viewZdgzxsk();return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:notEqual name="userType" value="stu">
							<logic:equal value="yes" name="writeAble">
								<li>
									<a href="#" onclick="exportZdgzxsk();return false;" class="btn_dc">����</a>
								</li>
							</logic:equal>
						</logic:notEqual>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ̸����¼��Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
