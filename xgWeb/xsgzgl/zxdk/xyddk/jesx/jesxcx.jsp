<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/jesx/js/jesx.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		    var gridSetting = {
				caption : "列表",
				pager : "pager",
				url : "gjdk_jesx.do?method=getJesxCx&type=query",
				colList : [ {
					label : '学历层次代码',
					name : 'xlccdm',
					index : 'xlccdm',
					key : true,
					hidden:true
				}, {
					label : '学历层次',
					name : 'xlccmc',
					index : 'xlccmc',
					width : '50%',
					formatter:xlccLink
				}, {
					label : '贷款额度上限(元)',
					name : 'jesx',
					index : 'jesx',
					width : '50%'
				}],
				sortname : "xlccdm",
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
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">贷款上限维护</a></li>
				</ul>
			</div>
			</logic:equal>
				
		</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th width="12%"  >学历层次</th>
							<td width="12%"  > 
								<input type="text" name="xlccmc" id="xlccmc" onkeydown="keydown_search(event)" style="width:150px;" >
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
					<span> 贷款上限维护 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
