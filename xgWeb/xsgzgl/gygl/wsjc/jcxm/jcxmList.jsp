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
				url:"wsjcJcxm.do?method=getJcxmList",
				colList:[
				   {label:'代码',name:'xmdm', index: 'xmdm',hidden:true,key:true},
				   {label:'项目名称',name:'xmmc', index: 'xmmc'},
				   {label:'项目内容',name:'xmnr', index: 'xmnr'},
				   {label:'检查对象',name:'jcdx', index: 'jcdx',formatter:function(v,r){
					   return v == "0" ? "寝室" : "床位";
				   }}
				],
				sortname: "xmdm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["xmmc"] = jQuery("#xmmc").val();
				map["jcdx"] = jQuery("#jcdx").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					showDialog('修改',400,260,'wsjcJcxm.do?method=edit&xmdm='+rows[0]["xmdm"]);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("wsjcJcxm.do?method=delete",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('增加',400,260,'wsjcJcxm.do?method=add');;
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/wsjcJcxm" method="post" styleId="form">
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="12%">项目名称</th>
							<td width="15%">
								<input type="text" id="xmmc"/>
							</td>
							<th width="12%">检查对象</th>
							<td width="15%">
								<html:select property="jcdx" styleId="jcdx">
									<html:option value=""></html:option>
									<html:option value="0">寝室</html:option>
									<html:option value="1">床位</html:option>
								</html:select>
							</td>
							</td>
							<td align="right">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 检查项目列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
