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
				url:"khglPfzgl.do?method=showPfcy&type=query&zgh="+'${zgh}'+"&pfzid="+'${pfzid}'+"&bjfp=y",
				colList:[
				   {label:'班级代码',name:'bjdm', index: 'bjdm',hidden:true,key:true},
				   {label:'年级',name:'nj', index: 'nj',width:'10%'},
				   {label:'学院',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'班级人数',name:'bjrs', index: 'bjrs',width:'10%'},
				   {label:'已参与人数',name:'pfzrs', index: 'pfzrs',width:'10%'}
					],
				sortname: "",
			 	sortorder: ""
			}
			
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
			
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/khglPfzgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id ="pfzid" value="${pfzid}"/>
			<input type="hidden" id ="zgh" value="${zgh}"/>
			<input type="hidden" id ="khdxrs" value="${khdxrs}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="savePfcybj('save');return false;" class="btn_sz">参与评分</a>
						</li>
						<logic:notEqual value="1" name="sfqx">
						<li>
							<a href="javascript:void(0);" onclick="savePfcybj('del');return false;" class="btn_sz">取消评分</a>
						</li>
						</logic:notEqual>
				</ul>
				</div>
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> <font color="blue">${bmmc} ${xm}(职工号:${zgh})&nbsp;</font> 带班班级列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
