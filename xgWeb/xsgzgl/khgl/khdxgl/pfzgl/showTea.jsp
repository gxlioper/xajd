<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khdxgl/pfzgl/js/pfzwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"khglPfzgl.do?method=showKhdx&type=query&pfzid="+'${pfzid}'+"&khdxid="+'${khdxid}'+"&pflx="+'${pflx}',
				colList:[
						   {label:'职工号',name:'zgh', index: 'zgh',width:'10%',key:true},
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'},
						   {label:'用户身份',name:'yhsf', index: 'yhsf',width:'10%'},
						   {label:'评分成员',name:'pfcys', index: 'pfcys',width:'10%',formatter:pfcyTeaLink}
						],
				sortname: "zgh",
			 	sortorder: "asc",
			}
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<html:form method="post" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="khdxid" value="${khdxid}"/>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<!-- pflx为1时，参与考核行为的对象为学生 -->
			<input type="hidden" id ="pflx" value="${pflx}"/>
			<!-- khlx为1时，被考核象为学生(学号)，否则为教师(职工号) -->
			<input type="hidden" id ="khlx" value="${khlx}"/>
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>	
						<li>
							<a id="btn_fh" class="btn_fh"> 返 回 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pfcySz();return false;" class="btn_sz">评分组成员设置</a>
						</li>
						<logic:equal name="xxdm" value="10504">
						<logic:equal name="pflx" value="1">
							<li>
								<a href="javascript:void(0);" onclick="pfcybjSz();return false;" class="btn_sz">评分组成员(班级)设置</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="pfcydr();return false;" class="btn_dr">评分成员导入</a>
							</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="qkpfr();return false;" class="btn_sz">清空评分人</a>
						</li>
				</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 考核对象(教师)列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
