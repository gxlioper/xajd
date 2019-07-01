<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsq.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"综合素质计分项目明细表",
				pager:"pager",
				url:"zhf_sq.do?method=getZhfsqList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '15%'  },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '10%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '所属项目', name : 'xmmkmc', index : 'xmmkmc', width : '10%' },
							{ label : '计分项目', name : 'jfxmmc', index : 'jfxmmc', width : '10%' },
							{ label : '分数', name : 'fs', index : 'fs', width : '10%' },
							{ label : '申请状态', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true}					
							],
				sortname: "shzt",
			 	sortorder: "asc",
			 	radioselect:false
		}	
				
		jQuery(function(){
			var map = {};
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function query(){
			var map = {};
			map["cxzd"] = jQuery("#cxzd").val();
			jQuery("#dataTable").reloadGrid(map);
		}
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zhf_sq" onsubmit="return false;">
		<input type="hidden" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sq">
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >申请</a>
						</li>
						<li id="li_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
					</ul>
					
				</div>
			    <!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%" style="margin-left:20px">
								计分项目名称
							</th>
							<td>
								<input type="text" id="cxzd" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										查 询
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>			
			</div>
			</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>综合素质计分项目明细列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
