<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�༶��Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"zwzxkqKqjg.do?method=showBjList&type=query",
				colList:[
				   {label:'bjdm',name:'bjdm', index: 'bjdm',width:'13%',hidden : true,key:true},
				   {label:'xydm',name:'xydm', index: 'xydm',width:'13%',hidden : true},
				   {label:'zydm',name:'zydm', index: 'zydm',width:'13%',hidden : true},
				   {label:'bjrs',name:'bjrs', index: 'bjrs',width:'13%',hidden : true},
				   {label:'�꼶',name:'nj', index: 'nj',width:'13%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'18%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'19%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'19%'},
				   {label:'dbfdy',name:'dbfdy', index: 'dbfdy',hidden : true},
				   {label:'����',name:'bjdm', index: '',width:'12%',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectBj('"+cell+"','"+rowObject["bjmc"]+"','"+rowObject["bjrs"]+"','"+rowObject["dbfdy"]+"');\">ѡ��</label>";
				   }}
				],
				sortname: "xydm",
			 	sortorder: "desc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectBj(bjdm,bjmc,bjrs,dbfdy){
				 var W;
				 var api = frameElement.api;
					if (api){
						if (api.get('childDialog')){
							W=api.get('parentDialog')
						} else {
							W = api.opener;
						}
					} else if (parent.window){
						W=parent.window;						
					}
					W.document.getElementById('bjmc').value=bjmc;
					W.document.getElementById('bjdm').value=bjdm;
					W.document.getElementById('ydrs').value=bjrs;
					if(W.document.getElementById('dbfdy')){
						W.document.getElementById('dbfdy').innerText=dbfdy=='null'?'':dbfdy;
					}

				iFClose();
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/zwzxkqKqjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
					<!--����start-->
			<h3 class="datetitle_01">
				<span> �༶��Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
