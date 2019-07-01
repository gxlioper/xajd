<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"学生列表",
					pager:"pager",
					url:"xszz_xszzbjpy_xzszgl.do?method=updateXzsz&type=query&bjdm=${rs.bjdm}",
					colList:[      
				         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
						   {label:'学号',name:'xh', index: 'xh',width:'10%'},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'年级',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},
						   {label:'专业',name:'zymc', index: 'zydm',width:'16%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
						   {label:'是否学生代表',name:'sfxsdbmc', index: 'sfxsdbmc',width:'10%'},
						   {label:'是否学生代表',name:'sfxsdb', index: 'sfxsdb',hidden:true},
						   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true}
						],
						sortname: "xh",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function xzszDel() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要删除的学生！");
			} else {
				showConfirmDivLayer("您确定要删除选择的学生吗？",{"okFun":function(){
					jQuery.post("xszz_xszzbjpy_xzszgl.do?method=xzszDel",{values:ids.toString(), bjdm: "${rs.bjdm}"},function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					},'json');
					}
				});
			}
		}
				
		function xsdbBc() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1) {
				showAlertDivLayer("请选择一个学生！");
			} else {
				jQuery.post("xszz_xszzbjpy_xzszgl.do?method=xsdbBc",{values:ids.toString(), bjdm: "${rs.bjdm}"},function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				},'json');
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_xszzbjpy_xzszgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<logic:equal name="rs" property="sfksbjpy" value="false">
						<font color="red">删除</font>学生后，班级评议小组的提交状态改为<font color="red">未提交</font>，请重新进行<font color="red">提交</font>操作。
					</logic:equal>
					<logic:equal name="rs" property="sfksbjpy" value="true">
						当前班级已经开始评议，无法进行人员调整！
					</logic:equal>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="rs" property="sfksbjpy" value="false">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="xzszDel();return false;" >删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_csh" onclick="xsdbBc();return false;" >设置学生代表</a>
							</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
