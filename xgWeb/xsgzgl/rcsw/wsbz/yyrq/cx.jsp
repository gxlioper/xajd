<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/yyrq/js/yyrq.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"ԤԼʱ���б�",
				pager:"pager",
				url:"wsbz_yyrq.do?method=getYyrqList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',key:true,hidden:true,width:'30%'},
				   {label:'ԤԼʱ��',name:'yyrq', index: 'yyrq',width:'30%'}
				],
				sortname: "yyrq",
			 	sortorder: "asc"
			} 
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = {};
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>
	<body>
	<html:form action="/wsbz_yyrq" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span>ԤԼʱ���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
