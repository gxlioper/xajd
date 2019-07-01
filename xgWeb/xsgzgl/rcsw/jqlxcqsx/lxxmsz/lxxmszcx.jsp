<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/jqlxcqsx/lxxmsz/js/lxxmsz.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		    var gridSetting = {
				caption : "列表",
				pager : "pager",
				url : "jqlx_lxxmsz.do?method=getXmszCx&type=query",
				colList : [ {
					label : 'xmid',
					name : 'xmid',
					index : 'xmid',
					hidden : true,
					key : true
				}, {
					label : '留校项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '25%',
					formatter:xmmcLink
				}, {
					label : '留校时间段',
					name : 'qzsj',
					index : 'qzsj',
					width : '25%'
				}, {
					label : '留校项目说明',
					name : 'lxxmsm',
					index : 'lxxmsm',
					width : '50%'
				}],
				sortname : "xmmc",
				sortorder : "asc"
			}	
		jQuery(function() {
			
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
	</head>
	<body>
	<html:form action="/stglStlbDmwh" method="post">
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
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>
				</ul>
			</div>
			</logic:equal>
				
		</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th width="12%">留校项目名称</th>
							<td width="12%"> 
								<input type="text" name="xmmc" id="xmmc" onkeydown="keydown_search(event)" style="width:150px;" >
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false"">
										查 询
									</button>
								</div>
							</td>
						</tr>					
					</table>
				</div>
				<div style="display:none">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span>留校项目设置列表</span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
