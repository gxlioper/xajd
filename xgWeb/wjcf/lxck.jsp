<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js"></script>
		<script type="text/javascript">
<!--
	function wintz() {
		var pkValue = curr_row.getElementsByTagName("input")[0].value;
		window.close();
		if ($('xxdm').value=='10827') {
				window.dialogArguments.document.forms[0].action="csmz_cxcfsqdefault.do?pkValue="+pkValue;
				window.dialogArguments.document.forms[0].submit();	
		} else {
				window.dialogArguments.document.forms[0].action="wjcf_zjlg_addLxckxx2.do?pkValue="+pkValue;
				window.dialogArguments.document.forms[0].submit();
		}
		
	}
//-->
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }违纪处分 － 学校审核 － 审核 － 留校察看提醒</a>
			</p>
		</div>

		<html:form action="wjcflxcktj" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
							记录数：${rsNum }
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="2" scope="request">
									<td id="${tit.en}" onclick="tableSort(this)" nowrap>
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
								<tr onmousemove="rowOnClick(this)"
									style="cursor:hand;background-color: <logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
									ondblclick="">

									<td nowrap="nowrap">
										<input type="hidden"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
									<td align="center">
										<button type="button" id="btn_add" onclick="wintz()">
											申请
										</button>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
