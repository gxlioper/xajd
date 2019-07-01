<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/jgcx/jgcx/js/jgcx.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "考核对象列表",
					pager : "pager",
					radioselect:true,
					url : "khgljgcx.do?method=xmjgList&type=query",
					colList : [ 
					   {label : 'xydm',name : 'xydm',index : 'xydm',hidden:true},
					   {label : '学号',name : 'xh',index : 'xh',width : '20%'}, 
					   {label : '姓名',name : 'xm',index : 'xm',width : '15%'}, 
					   {label : '性别',name : 'xb',index : 'xb',width : '5%'}, 
					   {label : '年级',name : 'nj',index : 'nj',width : '10%'}, 
					   {label : '学院',name : 'xymc',index : 'xymc',width : '25%'},
					   {label : '班级',name : 'bjmc',index : 'bjmc',width : '25%'},
					   {label : '总分',name : 'zf',index : 'zf',width : '5%'},
					   {label : '排名',name : 'pm',index : 'pm',width : '5%'}
					   ],
					sortname : "pm",
					sortorder : "asc"
				}

			jQuery(function() {
				jQuery("#btn_fh").bind("click",function(){
					document.location.href='khgljgcx.do?method=jgcxList';
					
				});
				
				var map = {xmid:jQuery("#xmid").val(),khlx:jQuery("#khlx").val()};
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khlx"] = jQuery("#khlx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khgljgcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${model.xmid} />
	<input type="hidden" name="khlx" id="khlx" value=${model.khlx} />
	<input type="hidden" name="dclb" id="dclb" value='xs' />
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
						<li>
							<a id="btn_fh" class="btn_fh"> 返 回 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dftj();return false;" class="btn_dc">打分统计</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>当前评分项目为：<font color="blue">${xmInfo.xmmc }</font></span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
