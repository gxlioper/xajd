<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"ahgf_sztz.do?method=getXfhzList",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%'},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc',index:'xqmc'},
				   {label:'�ܷ�',name:'tzzf',index:'tzzf'}
				],
				sortname:"bjdm,tzzf",
				sortorder:"desc"
			};

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//����
			function exportConfig(){
				var DCCLBH='nhgf_sztz_xfhz.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='nhgf_sztz_xfhz.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "ahgf_sztz.do?method=exportXfhzList&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
		</script>
	</head>

	<body>
		<html:form action="/ahgf_sztz" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>						
						<li><a href="javascript:void(0);" onclick="history.back();" class="btn_fh">����</a></li>						
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
				<span>������չѧ�ֻ���</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
