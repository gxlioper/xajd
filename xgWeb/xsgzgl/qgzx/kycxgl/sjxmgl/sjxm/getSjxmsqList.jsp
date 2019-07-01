<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/sjxmsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ʵ����Ŀ�б�",
				pager : "pager",
				url : "qgzx_kycxsjxmsq.do?method=getSjxmsqList&type=query",
				colList : [
							{ label : 'key', name : 'xmid', index : 'xmid',key:true,hidden : true },
							{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
							{ label : 'sfytx', name : 'sfytx', index : 'sfytx',hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt',hidden : true },
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink},
							{ label : '��Ŀ���', name : 'xmbh', index : 'xmbh', width : '8%' },
							{ label : '��Ŀ����', name : 'xmsxmc', index : 'xmsxmc', width : '10%' },
							{ label : '��Ŀ������', name : 'xm', index : 'xm', width : '10%' },
							{ label : '��Ŀ������λ', name : 'ssdwmc', index : 'ssdwmc', width : '12%' },
							{ label : '��Ŀ��ʼʱ��', name : 'kssj', index : 'kssj', width : '10%' },
							{ label : '��Ŀ����ʱ��', name : 'jssj', index : 'jssj', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' }
							]
					 }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_kycxsjxmsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="xmwh();return false;"  >��Ŀά��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cywh();return false;" class="btn_sz" >��Աά��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc" >�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sz">����</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ʵ����Ŀ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
