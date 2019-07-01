<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生信息列表",
				pager:"pager",
				url:"xsxx_tygl_xszpdr.do?type=query",
				colList:[
						   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink,key:true },
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   {label:'年级',name:'nj', index: 'nj',width:'8%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'18%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'19%'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
						   {label:'招生照片是否导入',name:'xssfdr', index: 'xssfdr',width:'5%'},
						   {label:'学生照片是否导入',name:'sfdr', index: 'sfdr',width:'5%'}
						],
						multiselect:false
				
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xsxxView(\""+cellValue+"\");'>"+cellValue+"</a>";
			}

			//查看
			function xsxxView(xh){
				showDialog("查看学生",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh);
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
				
		// 导出
		function xszpdc() {
			showDialog("", 400, 250, 'xsxx_xszpgl.do?method=xszpdc');
		}

		
		function exportZpxx(mmfs,zpType){
			setSearchTj();//设置高级查询条件
			var url = "xsxx_xszpgl.do?method=xszpdc&type=exp&photoNameType="+mmfs+"&zpType="+zpType;
				url = addSuperSearchParams(url);
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
		}
		

		//照片导入
		function xszpdr(){
			showDialog('招生照片导入',800,400,'xsxx_xszpgl.do?method=xszpdrManage&zpType=xszp');
			}
		function zpdr(){
			showDialog('学生照片导入',800,400,'xsxx_xszpgl.do?method=xszpdrManage');
			}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="xszpdr();return false;" class="btn_dr">招生照片导入</a></li>
						<li><a href="javascript:void(0);" onclick="zpdr();return false;" class="btn_dr">学生照片导入</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="xszpdc();return false;" class="btn_dc">照片导出</a></li>
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
				<span>学生信息列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
