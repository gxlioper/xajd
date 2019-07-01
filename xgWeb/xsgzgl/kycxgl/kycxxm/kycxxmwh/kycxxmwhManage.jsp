<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"科研类别列表",
				pager:"pager",
				url:"kycxgl_kycxxm_kycxxmwhgl.do?method=kycxxmwhManage&type=query",
				colList:[
				   {label:'科研类别代码',name:'lbdm', index: 'lbdm',key:true,width:'25%'},
				   {label:'科研类别名称',name:'lbmc', index: 'lbmc',width:'25%'},
				   {label:'审核流程',name:'lcxx', index: 'lcxx',width:'50%'},
				   {label : '申请开关',name : 'sqkg',index : 'sqkg',width : '8%',formatter:setSqkg},
				   {label : '申请开始时间',name : 'sqkssj',index : 'sqkssj',hidden : true},
				   {label : '申请结束时间',name : 'sqjssj',index : 'sqjssj',hidden : true}
				],
				sortname: "lbdm",
			 	sortorder: "asc"
			}
			function query(){
				var map = {};
				map["lbmc"] = jQuery("#lbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			// 申请开关 
			function setSqkg(cellValue,rowObject){
				var lbdm = rowObject.lbdm;
				var value = "未开启";
				var color = "#ff0000";
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "已开启";
						color = "#004400";
					}
				}
				return "<a  href='javascript:void(0);' onclick='return sjkg(\""+lbdm+"\");' ><font color='"+color+"'>"+value+"</font></a>";
			}
			//时间开关
			function sjkg(lbdm) {
				if(lbdm == null){ //点击按钮
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("请选择一条您要设置的记录！");
						return false;
					}
					lbdm = rows[0]["lbdm"];
				}
				var url = 'kycxgl_kycxxm_kycxxmwhgl.do?method=kycxxmwhSjkg&lbdm=' + lbdm;
				var title = "申请时间控制";
				showDialog(title, 610, 210, url);
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function add(){
				var url = "kycxgl_kycxxm_kycxxmwhgl.do?method=addKycxxmwh";
				var title = "增加科研类别";
				showDialog(title,470,180,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'kycxgl_kycxxm_kycxxmwhgl.do?method=updateKycxxmwh&lbdm='+rows[0]["lbdm"];
					var title = "修改科研类别";
					showDialog(title,470,180,url);
				}
			}
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(),{"okFun":function(){
							jQuery.post("kycxgl_kycxxm_kycxxmwhgl.do?method=delKycxxmwh",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
		</script>
	</head>
	<body>
	<html:form action="/kycxgl_kycxxm_kycxxmwhgl" method="post">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					</logic:equal>
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">科研类别名称</th>
						<td width="5%">
							<input type="text" id="lbmc" name="lbmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>科研类别列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
