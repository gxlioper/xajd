<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "学生扩展信息结果表",
		pager : "pager",
		url : "xsxx_kzxxjggl.do?method=list&actionType=query",
		colList : [
				{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
				{ name : 'sjly', index : 'sjly', hidden : true },
				{ label : '学号', name : 'xh', index : 'xh', width : '7%',formatter : xhLink },
				{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
				{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
				{ label : '学院', name : 'xymc', index : 'xydm', width : '13%' },
				{ label : '专业', name : 'zymc', index : 'zydm', width : '13%' },
				{ label : '班级', name : 'bjmc', index : 'bjdm', width : '13%' },
				{ label : '记录时间', name : 'jrsj', index : 'jrsj', width : '8%' }],
		sortname : "jrsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});

	/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 学号链接
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('学生扩展信息详情' , 800 , 550 , 'xsxx_kzxxjggl.do?method=ck&jgid="
				+ rowObject['jgid'] + "')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}
	
	function ck(){
		var rows = jQuery("#dataTable").getSeletRow();
				
				if(rows.length != 1){
					showAlertDivLayer("请选择一条您要查看的记录！");
					return false;
				}
				
				showDialog("学生扩展信息查看",800,550,"xsxx_kzxxjggl.do?method=ck&jgid="+rows[0]["jgid"]);
	}
	
</script>
	</head>

	<body>
		<input type="hidden" name="kgzt" id="kgzt" value="${csszModel.ksqkg }"/>
		<input type="hidden" name="xzkg" id="xzkg" value="${csszModel.xzkg }"/>
		<input type="hidden" name="kxzkz" id="kxzkz" value="${csszModel.kxzkz }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/rcsw_zdzm_sqgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<!--<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addZdzmJg();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateZdzmJg();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteZdzmJg();return false;" class="btn_sc"  >删除</a>
						</li>
						--></logic:equal>		
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="ck();return false;" >查看</a>
						</li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生扩展信息&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
