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
				caption:"艺术团类别列表",
				pager:"pager",
				url:"ystglDmwh.do?method=getYstlbList&type=query",
				colList:[
				   {label:'艺术团类别代码',name:'ystlbdm', index: 'ystlbdm',key:true,width:'15%'},
				   {label:'艺术团类别名称',name:'ystlbmc', index: 'ystlbmc',width:'85%'}
				],
				sortname: "ystlbdm",
			 	sortorder: "asc"
			} 
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function query(){
				var map = {};
				map["ystlbmc"] = jQuery("#ystlbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		function add(){
			var url = "ystglDmwh.do?method=addYstlb";
			var title = "增加艺术团类别";
			showDialog(title,470,180,url);
		}
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				var url = 'ystglDmwh.do?method=updateYstlb&ystlbdm='+rows[0]["ystlbdm"];
				var title = "修改艺术团类别";
				showDialog(title,470,190,url);
			}
		}
		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
						jQuery.post("ystglDmwh.do?method=delYstlb",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
			}
		}
		function newChgCode(obj){
			allNotEmpThenGo(obj.id);
		}
		</script>
		
	</head>
	<body>
	<html:form action="/ystglDmwh" method="post">
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="ystglDmwh.do?method=getYstlbList"><span>艺术团类别</span></a></li>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="ystglDmwh.do?method=getXmlbList"><span>项目类别</span></a></li>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="ystglDmwh.do?method=getGkdwList"><span>挂靠单位</span></a></li>
					</ul>
				</div>
			</div>	
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >艺术团类别名称</th>
						<td>
							<input type="text" id="ystlbmc" name="ystlbmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}" />
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
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 代码维护列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
