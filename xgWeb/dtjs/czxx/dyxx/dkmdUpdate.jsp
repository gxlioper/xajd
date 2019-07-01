<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script type="text/javascript">
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
	</script>
	</head>
	
	<body onload="jd()">
		<html:form action="/czxxDtjsDyxx">
			<input type="hidden" id="pk" name="pk" value="${pk }" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>培训时间查看</span>
						</th>
					</tr>
				</thead>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          	<button type="button" name="提交" onclick="sumitInfo('/xgxt/czxxDtjsDyxx.do?method=dkmdUpdate','del')">删除</button>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr>
					<td colspan="4" align="center">
						<logic:notPresent name="rsList">
							无培训名单
						</logic:notPresent>
						<logic:present name="rsList">
						<fieldset>
							<legend>
								培训次数： <bean:write name="rsNum" />
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<td></td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rsList" id="s" indexId="index">
									<tr>
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								
							</table>
							
						</fieldset>
						</logic:present>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
