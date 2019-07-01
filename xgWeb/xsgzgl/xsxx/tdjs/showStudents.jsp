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
				multiselect:false,
				rowNum:10,
				url:"tdjs.do?method=showStudents&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'15%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'15%'},
				   {label:'性别',name:'xb', index: 'xb',width:'10%'},
				   {label:'学院',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'}
				],
				params:{
				 	bjdm:"<bean:write name='tdjsForm' property='bjdm'/>"
				},
				sortname: "xh",
			 	sortorder: "asc"
			 	
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = {};

				map["xh"] = jQuery("#xh").val();
				map["xm"] = jQuery("#xm").val();
				map["bjdm"]=jQuery("#bjdm").val();
				
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<html:form action="/tdjs">
			<html:hidden property="bjdm" styleId="bjdm"/>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<input type="text" name="xh" id="xh"/>
								</td>
								<th>姓名</th>
								<td>
									<input type="text" name="xm" id="xm"/>
								</td>
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
