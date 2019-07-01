<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stglzjsr/js/stgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "stgl_zjsr.do?method=getStglList",
				colList : [
					{label:'id',name:'id',index :'id',key:true,hidden:true},
					{label:'���ű��',name:'bh',index:'stbh',width:'10%'},
					{label:'��������',name:'stmc',index:'stmc',width:'15%',formatter:stmcLink},
					{label:'�������',name:'stlbmc',index:'stlbmc',width:'10%'},
					{label:'��������',name:'zd1',index:'zd1',width:'15%'},
					{label:'ָ����ʦ',name:'zdls',index:'zdls',width:'10%'},
					{label:'�糤',name:'szxm',index:'szxm',width:'15%'},
					{label:'�糤����',name:'szch',index:'szch',width:'15%'},
					{label:'��Ч״̬',name:'yxztmc',index:'yxztmc',width:'10%'}
				],
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
		<html:form action="/xlzx_pxwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc" >ɾ��</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();" class="btn_dr" >����</a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���Ź���</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
