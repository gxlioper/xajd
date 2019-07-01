<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"zdxljkEcmm.do?method=getEcmmList",
				colList:[
				   {label:'职工号',name:'zgh', index: 'zgh',key:true},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'性别',name:'xb', index: 'xb'},
				   {label:'部门',name:'bmmc', index: 'bmdm'},
				   {label:'创建时间',name:'cjsj', index: 'cjsj'}
				],
				sortname: "cjsj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(xh){
				showDialog('修改',700,500,'zdxljkTbxs.do?method=viewThjl&xh='+xh);
			}

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					showDialog('二次密码修改',650,350,'zdxljkEcmm.do?method=editEcmm&zgh='+rows[0]["zgh"]);
				}
			}

			function thjlDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("zdxljkEcmm.do?method=del",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								searchRs();
							}});
						},'json');
					}});
				}
			}
			
			function addThjl(){
				showDialog('二次密码维护',650,350,'zdxljkEcmm.do?method=addEcmm');
			}
			
			function initEcmm(){
				
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("请选择您要操作的记录！");
				} else {
					showDialog('二次密码初始化',450,200,'zdxljkEcmm.do?method=initEcmm');
				}
			}
			function saveEcmm(ecmm){
				var ids = jQuery("#dataTable").getSeletIds();
				jQuery.post("zdxljkEcmm.do?method=saveInitEcmm",{ids:ids.toString(),ecmm:ecmm},function(data){
					showAlertDivLayer(data["message"]);
				},"json");
			}
			
			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jddj_jdqk" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="addThjl()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="thjlDel();" class="btn_sc">删除</a></li>						
						<li><a href="javascript:void(0);" onclick="initEcmm();" class="btn_sz">二次密码初始化</a></li>						
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		
			<div class="formbox">
				<!--标题start-->
				<h3 class="datetitle_01">
					<span>二次密码维护教师列表 </span>
				</h3>
	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</html:form>
	</body>
</html>
