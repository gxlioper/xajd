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
				caption:"物品信息列表",
				pager:"pager",
				url:"gygl_qywpxx.do?method=cxGywpxxList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'物品名称',name:'wpmc', index: 'wpmc',width:'20%',formatter:wpLink},
				   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'15%'},
				   {label:'寝室号',name:'qsh', index: 'qsh',width:'10%'},
				   {label:'所属大类',name:'wpdlmc', index: 'wpdlmc',width:'15%'},
				   {label:'物品类别',name:'wplbmc', index: 'wplbmc',width:'15%'},
				   {label:'物品数量',name:'sl', index: 'sl',width:'5%'},
				   {label:'是否完好',name:'sfwh', index: 'sfwh',width:'10%'}
				]
				
			}
			
			function wpLink(cellValue,rowObject){
				return "<a href='javascript:void(0);'onclick='gywpxxView(\""+rowObject["id"]+"\")' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
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
					结果集中若寝室号值为“#”则为楼栋物品。
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<%--<li><a href="javascript:void(0);" onclick="back()" class="btn_fh">返回</a></li>--%>
						<li><a href="javascript:void(0);" onclick="updateGywpxx();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="delGywpxx()" class="btn_sc">删除</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="viewGywpxx()" class="btn_ck">查看</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出数据</a></li>--%>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果	 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
