<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script language="javascript" src="xsgzgl/xlzx/yysqnew/js/xspjInfo.js"></script>
	</head>
  
  <body>
  <input type="hidden" name="yyid" id="yyid" value="${zxInfo.yyid}" />
   	<html:form action="/xlzx_yysqnew" method="post">
			<div style='width:100%;height:120px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯ����</span>
							</th>
						</tr>
					</thead>
						<tbody id="xspjInfo">
							<th width="16%">
								������ѯ����<br/>
								<font color="red"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
									<html:textarea  property='xsp' styleId="xspj" style="word-break:break-all;width:99%" value="${zxInfo.xspj}" onblur="chLeng(this,500);" rows='4' />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table  border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveZxInfo();return false;">
									�� ��
								</button>
								<button onclick="Close();return false;">
									�� ��
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>

  </body>
</html>
