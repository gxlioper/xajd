<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/general/cxpy/js/cxpy.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����Ϣ�б�",
				pager:"pager",
				url:"xsxx_gygl_cxcxpy.do?method=getStu&type=query&xn=${xn}&xq=${xq}",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'15%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}


			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}


			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="zjBcStu();return false;" class="btn_zj">���</a>
						</li>
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
				<span> ѧ����Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
