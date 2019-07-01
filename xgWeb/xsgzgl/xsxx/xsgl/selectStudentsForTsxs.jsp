<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		
	</head>

	<body>
		<!-- 提示信息 END-->
		<html:form action="/xsxx_xsgl">
			<input type="hidden" id="xn" value="${xn}"/>
			<input type="hidden" id="xq" value="${xq}"/>
			<input type="hidden" id="lxdm" value="${lxdm}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<li id="li_xz">
						<a href="javascript:void(0);" onclick="selStudent();return false;" class="btn_zj" id="xzButton">增加</a>
					</li>
				  </ul>
				</div>
				<!-- 按钮 -->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
	<script type="text/javascript">
			var gridSetting = {
				caption:"学生信息列表",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"xsxx_xsgl.do?method=showStudentsForTsxsByTy&type=query&xn="+jQuery("#xn").val()+"&xq="+jQuery("#xq").val()+"&lxdm="+jQuery("#lxdm").val(),
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'18%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			var gridSetting1 = {
					caption:"学生信息列表",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"xsxx_xsgl.do?method=showStudentsForTsxsByTy&type=query&xn="+jQuery("#xn").val()+"&xq="+jQuery("#xq").val()+"&lxdm="+jQuery("#lxdm").val(),
					colList:[
					   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true},
					   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
					   {label:'性别',name:'xb', index: 'xb',width:'6%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					   {label:'专业',name:'zymc', index: 'zydm',width:'15%'},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'18%'},
					   {label:'英语成绩一',name:'kc1', index: '',width:'20%'},
					   {label:'英语成绩二',name:'kc2', index: '',width:'20%'},
					   {label:'CET-4成绩',name:'cet4', index: '6',width:'20%'}
					],
					sortname: "xh",
				 	sortorder: "asc"
				}

			jQuery(function(){
				var map = getSuperSearch();
				if(jQuery("#xxdm").val() == '10279'){
					gridSetting1["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting1);
				}else{
					gridSetting["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting);
				}
			});
				
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function selStudent(){	
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0){
					showAlertDivLayer("请至少选择一位学生!");
					return false;
				} else {
					var ids = jQuery("#dataTable").getSeletIds();
					showConfirmDivLayer("您确定要增加这些学生吗？", {
						"okFun" : function() {
							jQuery.post("xpj_tsxs.do?method=plzjTsxs", {
								values : ids.toString(),
								type : "save",
								xn : jQuery("#xn").val(),
								xq : jQuery("#xq").val(),
								lxdm : jQuery("#lxdm").val()
							},
							function(data) {
								showAlert(data["message"], {}, {
									"clkFun" : function(tag) {
										if (tag == "ok") {
<%--											var api = frameElement.api;--%>
<%--											W = api.opener;--%>
<%--											jQuery(w).find("#closeButtion").click();--%>
											refershParent();
										}
									}
								});
							}, 'json');							
						}
					});										
				}
			}
		</script>
</html>
