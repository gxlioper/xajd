<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/dmwh/js/lbdm.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function searchRs() {
			var map = "";
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
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_txhd">
		<input type="hidden" id="tabType" value="lbwh"/>
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
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'lbwh');"><span>类别维护</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'hdgg');"><span>活动规格</span></a></li>
			      </ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th id="th_lbmc" width="10%">类别名称</th>
						<td id="td_lbmc" width="10%">
							<input type="text" id="lbmc" name="lbmc" maxlength="20" onkeydown="keydown_search(event)" />
						</td>
						<th id="th_hdggmc" style="display:none" width="10%">活动规格</th>
						<td id="td_hdggmc" style="display:none" width="10%">
							<input type="text" id="hdggmc" name="hdggmc" maxlength="20" onkeydown="keydown_search(event)" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="setSearch_url();searchRs();return false">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span id="spanmc"> 类别维护列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
