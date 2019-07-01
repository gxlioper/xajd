<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xbzj/js/xbzj.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"支教学生信息列表",
				pager:"pager",
				url:"rcsw_xbzj.do?method=cxXbzjList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'18%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'18%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'学期',name:'xqmc', index: 'xqmc',width:'5%'},
				   {label:'起始时间',name:'zjsj', index: 'zjsj',width:'13%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' onclick='viewXbzjxs(\""+rowObject["id"]+"\");' class='name'>"+cellValue+"</a>";
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
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">删除</a></li>						
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="view()" class="btn_ck">查看</a></li>
						<logic:equal name="writeAble" value="yes">	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入数据</a></li>
						</logic:equal>
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
				<span> 支教学生信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
