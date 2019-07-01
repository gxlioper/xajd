<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				url:"qjsq.do?method=selectQjkc&type=query&xh="+jQuery("#xh").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'�γ̱��',name:'kcbh', index: 'kcbh',width:'25%',key:true},
				   {label:'�γ�����',name:'kcmc', index: 'kcmc',width:'25%'},
				   {label:'�ο���ʦ����',name:'rklsxm', index: 'rklsxm',width:'25%'},
				   {label:'�ο���ʦ��ϵ��ʽ',name:'rklslxfs', index: 'rklslxfs',width:'25%'}
				],
				sortname: "kcbh",
			 	sortorder: "desc"
			}
			jQuery("#dataTable").initGrid(gridSetting);

		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="qjsqForm" action="/qjsq.do?method=selectQjkc&type=query">
		<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="addQjkc();return false;" class="btn_zj">ѡ��</a>
						</li>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ѯ���
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
