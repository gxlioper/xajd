<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>

<!-- ������ -->
<%@ include file="/xszz/hiddenValue.jsp"%>
<!-- ������ end-->
<div id="child5">
	<table width="100%" align="center" class="tbstyle">
		<logic:notEmpty name="zzrs">
			<input type="hidden" id="maxLength" name="maxLength"
				value="${zzrssize}" />
			<logic:iterate id="rs" name="zzrs" indexId="index">
				<tr id="">
					<td id="td${index}" align="center" class="style2"
						bgcolor="#FFddcc" colspan="1"
						onclick="document.all.xszz${index}.style.display=(document.all.xszz${index}.style.display =='none')?'':'none';getXszzInfo(${index});">
						${rs.tabCN}
						<input type="hidden" id="tab${index}" name="tab${index}"
							value="${rs.tabEN}" />
					</td>
				</tr>
				<tbody style="display:none" width="100%" class="tbstyle"
					id="xszz${index}">
				</tbody>
			</logic:iterate>
		</logic:notEmpty>
		<logic:equal value="isXcxy" name="isXcxy">
			<thead>
				<tr>
					<td>
						ѧ��
					</td>
					<td>
						ѧ��
					</td>
					<td>
						����
					</td>
					<td>
						<bean:message key="lable.xsgzyxpzxy" />����
					</td>
					<td>
						רҵ����
					</td>
					<td>
						�༶����
					</td>
					<td>
						������Ŀ����
					</td>
					<td>
						�������
					</td>
				</tr>
			</thead>
			<tbody width="100%" class="tbstyle" id="xszz">
			</tbody>
		</logic:equal>
	</table>
</div>

