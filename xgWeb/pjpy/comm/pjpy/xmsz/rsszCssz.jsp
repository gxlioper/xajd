<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function checkBlxs(obj){
				if (obj.value > 3){
					ymPrompt.alert({
						message:'������0~3֮�������!',
						handler:function(){
							obj.focus();
						}
					});					
				}
			}
		</script>
	</head>
	<body>
	
		<html:form action="/pjpy_comm_rssz">
			<input type="hidden" name="szfw" value="${szfw }"/>
			<input type="hidden" name="xmdm" value="${xmdm }"/>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>��ϸ����</span>
						</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th>����С��(λ)</th>
						<td>
							<div class="pos" style="z-index:2">
							<html:text property="blxs" 
									   maxlength="2" 
									   onfocus="displayMsgDiv()"
									   onkeyup="value=value.replace(/[^\d]/g,'')"
									   onblur="hideMsgDiv();checkBlxs(this)"></html:text> 
							<div id="msg_div" class="hide">
				              <div class="prompcon">
				                <p>������0~3֮�������!</p>
				              </div></div>
				            </div>	
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" id="footId">
							<div align="right">
								<button type="button" name="����" class="" onclick="refreshForm('pjpy_comm_rssz.do?method=rsszCssz&doType=save');">
									�� ��
								</button>
								<button type="button" name="ȡ��" class="" onclick="window.close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<input type="hidden" id="message" value="${message }"/>
			<input type="hidden" id="doType" value="${doType }"/>
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
