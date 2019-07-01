<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function cxycsj(){
			var jcb = document.getElementById("jcb").value;
			var jcsj = document.getElementById("jcsj").value;
			allNotEmpThenGo("xtwh_xssjtb.do?method=ckSbsj&jcb="+jcb+"&jcsj="+jcsj);
			}

		function sjDc(){
			var jcb = document.getElementById("jcb").value;
			var jcsj = document.getElementById("jcsj").value;
			var url = "xtwh_xssjtb.do?method=ycsjExport&jcb="+jcb+"&jcsj="+jcsj;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	

			}
		</script>
	</head>
	<body >

		<html:form action="/xtwh_xssjtb" method="post">
				<input type="hidden" id="search_go" onclick="cxycsj()"></input>
					<input type="hidden" id="jcb" value="${jcb}"></input>
					<input type="hidden" id="jcsj" value="${jcsj}"></input>
			<div  style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
					<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" indexId="index" offset="0">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
							<logic:equal value="xg_xtwh_bmdmb" name="jcb">
								<logic:iterate name="rs" id="s" indexId="index">
								<tr>
									<logic:iterate id="v" name="s" offset="1">
									<td align="left">
									${v}
									</td>
								</logic:iterate>
								</tr>
								</logic:iterate>
							</logic:equal>
							
							<logic:equal value="xg_xtwh_zydmb" name="jcb">
								<logic:iterate name="rs" id="s" indexId="index">
								<tr>
								<logic:iterate id="v" name="s" offset="1">
									<td align="left">
									${v}
									</td>
								</logic:iterate>
								</tr>
								</logic:iterate>
							</logic:equal>	
							
							<logic:equal value="xg_xtwh_bjdmb" name="jcb">
								<logic:iterate name="rs" id="s" indexId="index">
								<tr>
									<logic:iterate id="v" name="s" offset="1">
									<td align="left">
									${v}
									</td>
								</logic:iterate>
								</tr>
								</logic:iterate>
							</logic:equal>	
							
							<logic:equal value="xg_xtwh_xsxxb" name="jcb">
								<logic:iterate name="rs" id="s" indexId="index">
								<tr>
									<logic:iterate id="v" name="s" offset="1">
									<td align="left">
									${v}
									</td>
								</logic:iterate>
								</tr>
								</logic:iterate>
							</logic:equal>	
								
							</logic:notEmpty>
						</tbody>
						
					</table>
					</logic:notEmpty>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xssjtbForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>	
			</div>
			
			<table width="100%" border="0" class="formlist">
						<tfoot>
						<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" id="buttonSave" onclick="sjDc();">
											导 出
										</button>
										<button type="button" id="buttonSave" onclick="window.close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
					</table>
			
		</html:form>

		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
