<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/tdjs/js/tdgl.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"团队信息列表",
				pager:"pager",
				url:"tdjs.do?method=tdglManage&type=query",
				colList:[
				   {label:'团队',name:'bjdm', index: 'bjdm',hidden:true,key:true},
				   {label:'年级',name:'nj', index: 'nj',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmdm',width:'25%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'25%'},
				   {label:'团队',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'指导老师',name:'zdls', index: 'zgh',width:'10%'},
				   {label:'团队学生',name:'bjrs', index: 'bjrs',width:'10%',formatter:tdxsLink}
				],
				sortname: "bjdm",
			 	sortorder: "asc"
			}

			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["nj"] = jQuery("#nj").val();
				map["bmdm"] = jQuery("#xy").val();
				map["zydm"] = jQuery("#zy").val();
				map["bjmc"] = jQuery("#bjmc").val();
				map["zdls"] = jQuery("#zdls").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<html:form action="/tdjs" method="post">
			<input type="hidden" id="xyV" name="xyV" value=""/>
			<input type="hidden" id="zyV" name="zyV" value=""/>
			<input type="hidden" id="bjV" name="bjV" value=""/>	
		
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
						<li><a href="javascript:void(0);" onclick="createTd();" class="btn_zj">创建团队</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="delTdinfo();" class="btn_sc">删除</a></li>						
						<li><a href="javascript:void(0);" onclick="fpxs();" class="btn_sz">分配学生</a></li>						
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>年级</th>
								<td>
									<html:select property="nj" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
								</td>
								<th><bean:message key="lable.xb" /></th>
								<td>
									<html:select property="bmdm" onchange="initZyArray('xy','zy');"  styleId="xy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>专业</th>
								<td>
									<html:select property="zydm" onchange=""  styleId="zy" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>		
								</td>
							</tr>
							<tr>
								<th>团队</th>
								<td>
									<input type="text" name="bjmc" id="bjmc"/>
								</td>
								<th>指导老师</th>
								<td>
									<input type="text" id="zdls" name="zdls"/>
								</td>
								<td colspan="2">
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
						</tbody>
					</table>
				</div>
			</div>
			
		
			<div class="formbox">
				<!--标题start-->
				<h3 class="datetitle_01">
					<span> 团队信息列表 <font color="blue">（注：可以同时选择多个团队分配学生）</font></span>
				</h3>
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</html:form>
		
		<div id="cjtd" style="display:none;"></div>
		
	</body>
</html>
