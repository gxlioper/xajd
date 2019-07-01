<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript">

		</script> 
	</head>
	<body onload="">
		<html:form action="/xtwhSysz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="tab">		
			<!-- 项目基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>首页调查统计
							（已接受调查：
							<logic:iterate name="rsList" id="rs" indexId="index">
								<logic:equal name="index" value="0">
									${rs.all }人
								</logic:equal>
							</logic:iterate>
							）
							
							</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="">
						<td align="left" colspan="2">
							<p title="${dcMap.dcnr}">调查内容：${dcMap.dcinfo }</p>
						</td>
					</tr>
					<logic:empty name="rsList">
						<tr style="">
							<td align="left">
								暂时无人投票
							</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="rsList">
						<logic:iterate name="rsList" id="rs" indexId="index">
							<logic:notEqual name="index" value="0">
								<tr style="">
									<th width="35%">
										<p title="${rs.xxnr }">${rs.xxinfo }</p>	
									</th>
									<td width="">
										${rs.bl }%<img src="fdygl/fdygzdc/total.jpg" width="${rs.bl }px" height="10px"/>
									</td>
								</tr>
							</logic:notEqual>
						</logic:iterate>
					</logic:notEmpty>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>