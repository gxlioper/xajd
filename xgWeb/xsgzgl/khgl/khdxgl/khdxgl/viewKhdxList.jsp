<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/khdxgl/js/khdxwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		function initGridSetting(){
			var gridSetting = {};
			if("1"==jQuery("#khlx").val()){
			 gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"khglKhdxgl.do?method=viewKhdxList&type=query&khlx=1&khdxid="+'${khdxid}'+"&sfnz="+'${sfnz}'+"&fpzt="+'${fpzt}'+"&pfzid="+'${pfzid}'+"&pflx="+'${pflx}',
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'�Ƿ��ɲ�',name:'sfbgbmc', index: 'sfbgbmc',width:'10%'}
						],
				sortname: "xh",
			 	sortorder: "asc",
			}
			}else{
			var gridSetting = {
					caption:"��ѯ���",
					pager:"pager",
					rowNum:10,
					url:"khglKhdxgl.do?method=viewKhdxList&type=query&khlx=2&khdxid="+'${khdxid}'+"&sfnz="+'${sfnz}'+"&fpzt="+'${fpzt}'+"&pfzid="+'${pfzid}'+"&pflx="+'${pflx}',
					colList:[
							   {label:'�û���',name:'yhm', index: 'yhm',width:'10%',key:true},
							   {label:'����',name:'xm', index: 'xm',width:'10%'},
							   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
							   {label:'��������',name:'bmmc', index: 'bmmc',width:'20%'},
							   {label:'�û����',name:'yhsf', index: 'yhsf',width:'10%'}
							],
					sortname: "yhm",
				 	sortorder: "asc",
				}
			}
			return gridSetting;
		}
		jQuery(function(){
			var gridSetting=initGridSetting();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
			
			jQuery("#btn_fh").bind("click",function(){
				if (parent.window){
					refershParent();
				}
			});
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglKhdxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id="khlx" value="${khlx}"/>
			<input type="hidden" id="pflx" value="${pflx}"/>
			<input type="hidden" id="khdxmc" value="${khdxmc}"/>
			<input type="hidden" id="sfnz" value="${sfnz}"/>
			<input type="hidden" id="fpzt" value="${fpzt}"/>
			
			   
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
