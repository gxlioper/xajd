<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生信息列表",
				pager:"pager",
				url:"demo.do?method=studentList&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink,key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
				   {label:'性别',name:'xb', index: 'xb',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xydm',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["xh"] = jQuery("#xh").val();
				map["xm"] = jQuery("#xm").val();
				map["xymc"] = jQuery("#xymc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					showDialog('修改学生',400,300,'demo.do?method=updateStudent&xh='+rows[0]["xh"]);
				}
			}

			function delStudent(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					jQuery.post("demo.do?method=delStudent",{values:ids.toString()},function(data){
						alert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}

			
		</script>
	</head>

	<body>
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
					<li><a href="javascript:void(0);" onclick="showDialog('增加学生',400,300,'demo.do?method=addStudent');" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="delStudent();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										查 询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>学号</th>
							<td>
								<input type="text" id="xh"/>
							</td>
							<th>姓名</th>
							<td>
								<input type="text" id="xm"/>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<input type="text" id="xymc"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
