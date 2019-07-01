<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="xsgzgl/xtwh/general/mobilemessage/js/mobilemessage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		var gridSetting = {
				caption : "���ŷ��ͼ�¼�б�",
				pager : "pager",
				url : "xtwh_mobileMsg.do?method=mobileMessageList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '������', name : 'xm', index : 'xm', width : '10%' },
							{ label : '�ռ���', name : 'sxr', index : 'sxr', width : '10%' ,formatter :textFormatter},
							{ label : '�ռ�ʧ����', name : 'failsxr', index : 'failsxr', width : '10%' ,formatter :textFormatter},
							{ label : '��������', name : 'fsnrformat', index : 'fsnrformat', width : '50%' ,},
							{ label : '����ʱ��', name : 'fssj', index : 'fssj', width : '20%'}],
					sortname : "fssj",
				    sortorder : "desc" }
	
		jQuery(function() {
			gridSetting["params"] = getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
	
		});
		function sendMsg(){
			var typeid=jQuery("#typeid").val();
			var url="xtwh_mobileMsg.do?method=mobileMsgAdd";
			//showTopWin(url,'900','650');
			showDialog('������Ϣ',800,300,url);
		}
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xtwh_mobileMsg">

			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="sendMsg();return false;"  >����Ⱥ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="viewMsg();return false;"  >�鿴����</a>
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
				<span>���ŷ��ͼ�¼�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
