<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript">
		//下载模板
		function xzmb(){
			var url = "/xgxt/commImpExp.do?method=commImpExp&doType=exp";
			wjcfDataExport(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/commImpExp" enctype="multipart/form-data">
			<!-- 隐藏域 -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<!-- 标题 -->
			<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

			<br>
			<br>
			<div class="tab">
			<table class="formlist" align="center" width="80%">
			<tbody>
				<tr>
					<td align="right" style="width: 20%">
						导入文件选择：
					</td>
					<td>
						<input type="file" name="uploadFile" style="width:60%" id="kk" contenteditable="false"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">注：文件大小小于&lt;10M&gt;</font>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red">导入用模板请通过本模块下载，用户手动整理可能会有异常</font>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" class="button2" 
							id="buttonSave"
							onclick="if(check_File('kk','')){saveUpdate('/xgxt/commImpExp.do?method=commImpExp&doType=imp','')}"
							style="width: 80px">
							确&nbsp;&nbsp;定
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" 
							id="buttonSave"
							onclick="xzmb()"
							style="width: 80px">
							下载模板
						</button>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="other/tsxx.jsp"%>
	</body>
</html>