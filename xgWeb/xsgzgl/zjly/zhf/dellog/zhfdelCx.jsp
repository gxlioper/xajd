<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "zjly_dellog.do?method=getZhfDelList&type=query",
				colList : [
							{ label : 'key', name : 'logid', index : 'logid',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '11%' },
							{ label : '����', name : 'xm', index : 'xm', width : '5%' },
							{ label : '������Ŀ', name : 'xmmkmc', index : 'xmmkmc', width : '8%' },
							{ label : '�Ʒ���Ŀ', name : 'jfxmmc', index : 'jfxmmc', width : '15%' },
							{ label : '��������', name : 'sxsm', index : 'sxsm', width : '10%' },
							{ label : '����/���ʱ��', name : 'cysj', index : 'cysj', width : '5%' },
							{ label : '¼��������', name : 'lrrxm', index : 'lrrxm', hidden : true},
							{ label : '״̬', name : 'shzt', index : 'shzt', width : '6%' },
							{ label : '¼��ʱ��', name : 'lrsj', index : 'lrsj', hidden : true},
							{ label : 'ɾ����', name : 'scrxm', index : 'scrxm',width : '5%'},	
							{ label : 'ɾ��ʱ��', name : 'scsj', index : 'scsj',width : '12%'}						
							],
			 	radioselect:false
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function exportData(){
			customExport('xg_zjly_dellog.do', exportData);
		}
		function exportData(){
			setSearchTj();// ���ø߼���ѯ����
			var url = "zjly_dellog.do?method=exportData&dcclbh=" + 'xg_zjly_dellog.do';// dcclbh,�������ܱ��,���ݱ��ֶ�
			url = addSuperSearchParams(url);// ���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zjly_dellog">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
							<li><a href="javascript:void(0);" onclick="exportData();return false;" class="btn_dc">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ۺϷ�ɾ����ϸ��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
