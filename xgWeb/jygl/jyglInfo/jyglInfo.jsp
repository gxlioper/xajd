<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>

<!-- Òş²ØÓò -->
<%@ include file="/xszz/hiddenValue.jsp"%>
<!-- Òş²ØÓò end-->
<div>
	<table width="100%" align="center" class="tbstyle">
		<logic:notEmpty name="jyrs">
			<input type="hidden" id="jymaxLength" name="jymaxLength"
				value="${jyrssize}" />
			<logic:iterate id="rs" name="jyrs" indexId="index">
				<tr id="">
					<td id="jygltd${index}" align="center" class="style2"
						bgcolor="#FFddcc" colspan="1"
						onclick="document.all.jygl${index}.style.display=(document.all.jygl${index}.style.display =='none')?'':'none';getJyglInfo(${index});">
						${rs.cn}
						<input type="hidden" id="jytab${index}" name="jytab${index}"
							value="${rs.en}" />
					</td>
				</tr>
				<tbody style="display:none" width="100%" class="tbstyle"
					id="jygl${index}">
				</tbody>
			</logic:iterate>
		</logic:notEmpty>
	</table>
</div>

