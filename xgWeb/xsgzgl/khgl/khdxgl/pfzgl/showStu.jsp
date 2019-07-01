<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/pfzgl/js/pfzwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"khglPfzgl.do?method=showKhdx&type=query&pfzid="+'${pfzid}'+"&khdxid="+'${khdxid}'+"&khlx="+'${khlx}'+"&pflx="+'${pflx}',
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'�Ƿ��ɲ�',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'},
				   {label:'���ֳ�Ա',name:'pfcys', index: 'pfcys',width:'10%',formatter:pfcyStuLink}
						],
				sortname: "xh",
			 	sortorder: "asc",
			}
			
		jQuery(function(){
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id ="pflx" value="${pflx}"/>
			<input type="hidden" id ="khlx" value="${khlx}"/>
			<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>	<li>
							<a id="btn_fh" class="btn_fh"> �� �� </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pfcySz();return false;" class="btn_sz">�������Ա����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qkpfr();return false;" class="btn_sz">���������</a>
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
				<span> ���˶���(ѧ��)�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
