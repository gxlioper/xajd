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
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
		var gridSetting = {
				caption : "短信发送记录列表",
				pager : "pager",
				url : "xtwh_mobileMsg.do?method=mobileMessageList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '发送人', name : 'xm', index : 'xm', width : '10%' },
							{ label : '收件人', name : 'sxr', index : 'sxr', width : '10%' ,formatter :textFormatter},
							{ label : '收件失败人', name : 'failsxr', index : 'failsxr', width : '10%' ,formatter :textFormatter},
							{ label : '发送内容', name : 'fsnrformat', index : 'fsnrformat', width : '50%' ,},
							{ label : '发送时间', name : 'fssj', index : 'fssj', width : '20%'}],
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
			showDialog('发送信息',800,300,url);
		}
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xtwh_mobileMsg">

			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="sendMsg();return false;"  >短信群发</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="viewMsg();return false;"  >查看详情</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>短信发送记录列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
