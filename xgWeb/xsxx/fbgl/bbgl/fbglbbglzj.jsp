<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($){
				loadDfbsj();
				$("#pzgzid").bind("change",function(){
					loadDfbsj();
				});
			});
		</script>
	</head>
	<body>
	<div>
		<html:form action="/fbglbbgl.do?method=add&type=query">
			<input type="hidden" value="wbb" id="bbzt"/>
			<input type="hidden" value="${pk}" id="pk"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
				<table width="100%" border="0" class="formlist">
					<tr>
						<th align="right" width="10%">
							<font color="red">*</font>������
						</th>
						<td align="left">
							<html:select property="pzgzid" styleId="pzgzid" disabled="false"
								style="width:125px;">
								<html:options collection="pzgzList" property="pzgzid"
									labelProperty="pzgzmc" />
							</html:select>
							<a href="#" onclick="ckxx();return false;" style="text-decoration: underline;color: red;">������鿴</a>
							<span>����ʾ���� <span id="tjpz"></span></span>
						</td>
					</tr>
				</table>
		</html:form>
		<div class="toolbox">
			ÿ������: <input id="mbrs" type="text" onblur="checkInt(this);" maxlength="4"/> <button id="scbjgs" onclick="gennerBjgs();return false;">���ɰ༶����</button>
			<div id="table"></div>
			<%@include file="/xsgzgl/comm/browser/progressBar.jsp"%>
		</div>
		</div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="save('fbglbbgl.do?method=saveBb');return false;" type="button">
									����
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="buttonClose" onclick="iFClose();" type="button">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
	</body>
</html>
