<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��λ��Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"ydsq.do?method=selectRzcwxx&type=query",
				colList:[
				   {label:'��λ��Ϣid',name:'rzcwxx', index: 'rzcwxx',key:true,hidden:true},
				   {label:'¥������',name:'ldmc', index: 'ldmc'},
				   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
				   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
				   {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
				   {label:'�����꼶',name:'nj', index: 'nj'},
				   {label:'����<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
				   //{label:'ѧ��',name:'xh', index: 'xh'},
				   //{label:'����',name:'xm', index: 'xm'},
				   {label:'����',name:'rzcwxx', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectRzcw('"+cell+"','"+rowObject["xh"]+"');\">ѡ��</label>";
				   }}
				],
				sortname: "sfrz",
			 	sortorder: "desc",
			 	params:{
				 	xh:"${xh}"
				}
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function selectRzcw(rzcwxx,xh){
				var api = frameElement.api;
				var rzcwxxSetting = {
						caption:"��ѡ��λ��Ϣ",
						multiselect:false,
						rowNum:1,
						url:"ydsq.do?method=selectRzcwxx&type=query&rzcwxx="+rzcwxx,
						colList:[
						   {label:'��λ��Ϣid',name:'rzcwxx', index: 'rzcwxx',key:true,hidden:true},
						   {label:'¥������',name:'ldmc', index: 'ldmc'},
						   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
						   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
						   {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
						   {label:'�����꼶',name:'nj', index: 'nj'},
						   {label:'����<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'}
<%--						   {label:'qsmcCk',name:'qsmcCk', index: 'qsmcCk',hidden:true}--%>
						   //{label:'ѧ��',name:'xh', index: 'xh'},
						   //{label:'����',name:'xm', index: 'xm'}
						],
						sortname: "sfrz",
					 	sortorder: "desc"
					}
				if (api){
					if (api.get('childDialog')){
						api.get('parentDialog').jQuery("#rzcwxxTable").initGrid(rzcwxxSetting);
						api.get('parentDialog').jQuery("#yxzrzcwxx").show();
						api.get('parentDialog').jQuery("#rzcwxx").val(rzcwxx);
<%--						api.get('parentDialog').jQuery("#qsmcCk").val(qsmcCk);--%>
						if(xh!=='null'){
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
							//api.get('parentDialog').jQuery("#showSfcwcsh").hide();
						}else{
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']:eq(0)").attr("checked","checked");
							api.get('parentDialog').jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");

						}
						
					} else {
						var W = api.opener;
						W.showRzcwxx(rzcwxx);
						W.location="javascript:showRzcwxx("+rzcwxx+")";
					}
				} else if (parent.window){
					parent.window.document.location="javascript:showRzcwxx("+rzcwxx+")";
				}
				iFClose();
			}
			
			
			function searchRs(){
				var xh=jQuery("#xh").val();
				var map = getSuperSearch();
				map["xh"]=xh;
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
		<input type="hidden" value="${xh}" id="xh"/>
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<html:form action="/ydsq">
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
				<span> ��λ��Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
