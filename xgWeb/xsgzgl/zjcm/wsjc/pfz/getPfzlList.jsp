<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/pfz/js/pfz.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "评分组列表",
					radioselect:true,
					pager : "pager",
					url : "cjWsfPfz.do?method=getPfzlList&type=query",
					colList : [ 
					   {label : 'pfzid',name : 'pfzid',index : 'pfzid',key:true ,hidden:true},
					   {label : '组名称',name : 'pfzmc',index : 'pfzmc',width : '20%'}, 
					   {label : '所属校区',name : 'ssxqmc',index : 'ssxqmc',width : '20%'}, 
					   {label : '用户',name : 'yhcount', index : 'yhcount',width : '10%',formatter:yhsLink}
					   ],
					sortname : "pfzmc",
					sortorder : "desc"
				}

			function yhsLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["pfzid"]+"\");'>"+cellValue+"</a>";
			}

			//评分组详情查看
			function yhck(pfzid) {
				showDialog("查看用户", 800, 505, "cjWsfPfz.do?method=viewPfzList&pfzid=" + pfzid);
			}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/cjWsfPfz" method="post">
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
					<li><a href="javascript:void(0);" onclick="rywh();" class="btn_sz">人员维护</a></li>						
				</ul>
			</div>
			</logic:equal>
				
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >组名称</th>
						<td>
							<input type="text" id="pfzmc" name="pfzmc" maxleng="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
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
					<span> 考核对象列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
