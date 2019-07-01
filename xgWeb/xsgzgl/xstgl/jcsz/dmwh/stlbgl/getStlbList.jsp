<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xstgl/jcsz/dmwh/js/dmwh.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function searchRs() {
			var map = getSuperSearch();
			var tabType = jQuery("#tabType").val();
			map["tabType"] = tabType;
			jQuery("#dataTable").reloadGrid(map);
		}
		jQuery(function() {
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
		
	</head>
	<body>
	<html:form action="/stglStlbDmwh" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="tabType" value="stlb"/>
	<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
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
				
			
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'stlb');"><span>社团类别</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'xmlb');"><span>项目类别</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'rylb');"><span>人员类别</span></a></li>
			      </ul>
			</div>
		</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th id="stlb_th" width="12%">社团类别名称</th>
							<td id="stlb_td" width="12%">
								<input type="text" name="stlbmc" id="stlbmc" onkeydown="keydown_search(event)" style="width:150px;"/>
							</td>
							<input type="hidden" value="1" id="flag"/>
							<th width="12%" id="th_xmlb" style="display:none">项目类别名称</th>
							<td width="12%" id="td_xmlb" style="display:none">
								<input type="text" name="xmlbmc" id="xmlbmc" onkeydown="keydown_search(event)" style="width:150px;"/>
							</td>
							<th width="12%"  id="rylb_th" style="display:none">人员类别名称</th>
							<td width="12%"  id="rylb_td" style="display:none"> 
								<input type="text" name="rylbmc" id="rylbmc" onkeydown="keydown_search(event)" style="width:150px;" >
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="setStlbmc_stdmwh();searchRs();return false"">
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
					<span> 代码维护列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
