<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/comm/jcsjwh/js/ddwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"jcsj_ddwh.do?method=ddwhList&type=query",
				colList:[
				   {label:'大队代码',name:'dddm', index: 'dddm',width:'30%',key:true},
				   {label:'大队名称',name:'ddmc', index: 'ddmc',width:'30%'},
				   {label:'区队数',name:'qds', index: 'qds',width:'30%',formatter:setQd}
				],
				sortname: "dddm",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				searchRs();
			});
			
			function searchRs(){
				var map = getSuperSearch();	
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zjjcddwh">
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="zjDdsj();return false;" class="btn_zj" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="modDdsj();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delDdsj();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qdwh();return false;" class="btn_sz" >区队维护</a>
						</li>
					</logic:equal>
				  </ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div>
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 大队信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
