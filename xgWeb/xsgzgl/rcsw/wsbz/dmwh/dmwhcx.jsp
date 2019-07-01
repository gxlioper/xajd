<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/dmwh/js/dmwh.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "代码为维护查询列表",
				pager : "pager",
				url : "wsbz_dmwh.do?method=getDmwhCx&type=query",
				colList : [ {
					label : 'key',
					name : 'fddm',
					index : 'fddm',
					key : true,
					hidden : true
				}, {
					label : '维护分队名称',
					name : 'fdmc',
					index : 'fdmc',
					width : '10%',
					formatter:xhLink
				}, {
					label : '监督时间',
					name : 'sj',
					index : 'sj',
					width : '10%'
				},{
					label : '监督地点',
					name : 'dd',
					index : 'dd',
					width : '10%'
				},{
					label : '人数',
					name : 'rs',
					index : 'rs',
					width : '10%'
				}],
				sortname : "fdmc",
				sortorder : "asc"
			}
			var map = {};
			map['fdmc']=jQuery('#fdmc').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/wsbz_dmwh"  onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="set();return false" class="btn_xg" >全局设置</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">维护分队名称</th>
						<td>
							<input type="text" id="fdmc" name="fdmc" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go"  onclick="doQuery();">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>代码为维护查询列表&nbsp;&nbsp; </span><font></font corlor="red">（建议：对代码进行统一编码 ，同时请勿轻易删除以下记录！）</font>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
