<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"xsxx_xsgl.do?method=showStudentsForQshr&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'��ס¥��',name:'ldmc', index: 'ldmc',width:'10%'},
				   {label:'��ס����',name:'qsh', index: 'qsh',width:'5%'},
				   {label:'��ס��λ',name:'cwh', index: 'cwh',width:'5%'},
				   {label:'����',name:'xh', index: '',width:'10%',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectStudent('"+cell+"');\">ѡ��</label>";
				   }}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
			    var map = getSuperSearch();
			    gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectStudent(xh){
				showConfirmDivLayer("��ȷ��Ҫ������ס��", {
					"okFun" : function() {
						jQuery.post("gyglnew_zsxxgl.do?method=qshr", {
							lddm : jQuery("#lddm").val(),
							qsh : jQuery("#qsh").val(),
							cwh : jQuery("#cwh").val(),
							xh: xh
						},
							function(data) {
								if(data["message"]=="����ɹ���"){
						    		 showAlert(data["message"],{},{"clkFun":function(){
											if (parent.window){
												var api = frameElement.api,W = api.opener;									
												W.location.reload();
												closeDialog();
											}
										}});
						    	 }else{
						    		 showAlert(data["message"]);
						    	}
							}, 'json');
					}
				});
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${lddm}" id="lddm" />
		<input type="hidden" value="${qsh}" id="qsh" />
		<input type="hidden" value="${cwh}" id="cwh" />
	
		<html:form action="/xsxx_xsgl">
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
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
