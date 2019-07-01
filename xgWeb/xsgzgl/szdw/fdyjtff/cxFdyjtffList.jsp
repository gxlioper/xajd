<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl//szdw/fdyjtff/js/fdyjtff.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"津贴发放信息列表",
				pager:"pager",
				url:"szdw_fdyjtff.do?method=cxFdyjtffList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'职工号',name:'zgh', index: 'zgh',width:'15%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xbmc', index: 'xbmc',width:'5%'},
				   {label:'部门',name:'bmmc', index: 'bmdm',width:'20%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'学期',name:'xqmc', index: 'xqmc',width:'5%'},
				   {label:'补助类型',name:'bzlxmc', index: 'bzlxmc',width:'15%'},
				   {label:'金额',name:'bzje', index: 'bzje',width:'20%'}
				],
				sortname: "zgh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' onclick='jtffView(\""+rowObject["id"]+"\");' class='name'>"+cellValue+"</a>";
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
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span> 津贴发放信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
