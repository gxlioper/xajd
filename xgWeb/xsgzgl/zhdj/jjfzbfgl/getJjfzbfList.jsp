<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zhdj/jjfzbfgl/js/jjfzbfgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "sgygc_jjfzbfgl.do?method=getJjfzbfList&type=query",
				colList : [
					{label : 'key', name : 'bfid', index : 'bfid', hidden : true},
                    {label : '入党积极分子', name : 'rdjjfzxm', index : 'rdjjfzxm', width : '15%'},
                    {label : '帮扶对象', name : 'bfdxxm', index : 'bfdxxm', width : '15%'},
                    {label : '建立帮扶计划时间', name : 'jlsj', index : 'jlsj', width : '20%'},
                    {label : '帮扶情况实施表', name : 'ssqk', index : 'ssqk', width : '15%'},
                    {label : '最后修改时间', name : 'zhxgsj', index : 'zhxgsj', width : '20%'}

			],
				sortname : "jlsj",
				sortorder : "desc",
				radioselect:true
			}
			var map = getSuperSearch();
			map["xydm"]=jQuery("#xydm").val();
			map["js"]=jQuery("#js").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dzdy_cygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addBfjh();return false;">新增帮扶计划</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ckBfjh();return false;" class="btn_ck" >帮扶计划查看</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateBfjh();return false;" class="btn_xg" >帮扶计划修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_sc" onclick="delBfjh();return false;">帮扶计划删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="whSsqk();return false;" class="btn_sz" >维护实施情况表</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ckSsqk();return false;" class="btn_ck" >查看实施情况表</a>
						</li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>





					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">

			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
