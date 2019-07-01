<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gywp/js/gywp.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"公寓物品维护信息",
				pager:"pager",
				url:"gygl_qywpxx.do?method=cxGywplrxxList&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',hidden:true,key:true},
				   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'15%'},
				   {label:'层号',name:'ch', index: 'ch',width:'15%'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'20%'},
				   {label:'物品种类数',name:'rs', index: 'rs',width:'20%',formatter:rsLink}
				]
				
			}
			
			function rsLink(cellValue,rowObject){
				return "<a href='javascript:void(0);'onclick='cxGywpxxList(\""+rowObject["pk"]+"\")' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}


			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					结果集中若层号、寝室号值为“#”则为楼栋物品。
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="addGywpxx()" class="btn_zj">物品登记</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
