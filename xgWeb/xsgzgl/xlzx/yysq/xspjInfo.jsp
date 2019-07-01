<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script language="javascript" src="xsgzgl/xlzx/yysq/js/xspjInfo.js"></script>
	</head>
  
  <body>
  <input type="hidden" name="yyid" id="yyid" value="${zxInfo.yyid}" />
   	<html:form action="/xlzx_yysq" method="post">
			<div style='width:100%;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询评价</span>
							</th>
						</tr>
					</thead>
						<tbody id="xspjInfo">
							<th width="20%">
								<span class="red">*</span>本次咨询评价<br/>
								<font color="red"><B>(限500字)</B></font>
							</th>
							<td colspan="3">
									<html:textarea  property="xspj" styleId="xspj" style="word-break:break-all;width:99%" value="${zxInfo.xspj}" onblur="chLeng(this,500);" rows='13' />
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
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveZxInfo();return false;">
									保 存
								</button>
								<button onclick="Close();return false;">
									关 闭
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>

  </body>
</html>
