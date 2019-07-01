<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xszz/zzdy/zzmdgl/js/zzmdgl.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		var gridSetting = {
				caption : "���������б�",
				pager : "pager",
				url : "xszz_zzdyzzmdgl.do?method=getZzmdList&type=query",
				colList : [ 
				    	   {label : 'id',name : 'id',index : 'id',key : true,hidden:true},
				    	   {label : 'ѧ��',name : 'xh',index : 'xh',width : '15%' ,formatter : xhLink },
				    	   {label : '����',name : 'xm',index : 'xm',width : '10%'},
				    	   {label : 'ѧ��',name : 'xn',index : 'xn',width : '10%'},
				    	   {label : 'ѧ��',name : 'xqmc',index : 'xqmc',width : '10%'},
				    	   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '10%'},
				    	   {label : '�����ܽ��',name : 'zzzje',index : 'zzzje',width : '10%'},
				    	   {label : '��������',name : 'ffys',index : 'ffys',width : '5%'},
				    	   {label : 'ѧԺ',name : 'xymc',index : 'xymc',width : '20%'},
				    	   {label : '���Ž��',name : 'yffje',index : 'yffje',width : '10%'},
				    	   {label : '����״̬',name : 'ffztmc',index : 'ffztmc',width : '10%'}
				    	   
				    	   ]
			}

			jQuery(function() {
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
		
	</head>
	<body>
	<html:form action="/xszz_zzdyzzmdgl" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
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
					<li><a href="javascript:void(0);" onclick="zzmdTb();" class="btn_sx">ͬ��</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span> ��Ŀ�����б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
