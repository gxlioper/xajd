<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
	<script type="text/javascript" src="xljkwzdx/xljkzx/js/zxsgl.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"咨询师信息列表",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"xljk_zxsgl.do?method=queryZxsxxAction",
			colList:[
			   {label:'职工号',name:'zgh', index: 'zgh', key:true, formatter:zghLink},
			   {label:'姓名',name:'xm', index: 'xm'},
			   {label:'性别',name:'xb', index: 'xb'},
			   {label:'年龄',name:'age', index: 'age'},
			   {label:'联系电话',name:'lxdh', index: 'lxdh'},
			   {label:'部门',name:'bmmc', index: 'bmmc'},
			   {label:'在岗状态',name:'status', index: 'status', formatter:zgztChange},
			   {label:'任职资质',name:'zxszg', index: 'zxszg'}
			],
			sortname: "zgh",
		 	sortorder: "desc"
		}
		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function reloadZxsxxDataTable(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		/**
		 * 高级查询
		 * @return
		 */
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		/**
		 * 在岗状态代码转换中文
		 * 
		 * @param cellValue
		 * @param rowObject
		 * @return
		 */
		function zgztChange(cellValue, rowObject) {
			var returnText;
			switch (cellValue) {
				case "0":
					returnText="不在岗";
					break;
				case "1":
					returnText="在岗";
					break;
				default:
					returnText="";
					break;
			}
			return returnText;
		}
	</script>
  </head>
  
  <body>
    <div class="tab_cur">
		<p class="location">
			<em>您的当前位置：</em><a>${title }</a>
		</p>
	</div>
	<html:form action="/xljk_zxsgl">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="addZxsxx();return false;" class="btn_zj" id="zjButton">增加</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="updateZxsxx();return false;" class="btn_xg" id="xgButton">修改</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="deleteZxsxx();return false;" class="btn_sc" id="scButton">删除</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="setZxsxxStatus();return false;" class="btn_sz" id="szButton">设置在岗状态</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="setZxyysm();return false;" class="btn_sz" id="szButton">咨询预约说明</a>
					</li>
				</logic:equal>
			  </ul>
			</div>
		</div>
		<!-- 过滤条件 -->	
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- 过滤条件 end-->
	</html:form>
	<div>
		<h3 class="datetitle_01">
			<span> 
				咨询师信息列表
			</span>
		</h3>
	</div>
	<div class="formbox" style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;">
		<table id="dataTable" ></table>
	</div>
	<div id="pager"></div>
  </body>
</html>
