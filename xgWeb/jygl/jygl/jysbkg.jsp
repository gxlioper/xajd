<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
	</head>
	<body>
		<div class="tab">
			<html:form action="/bdzcgl" method="post">
				<input type="hidden" id="userType" name="userType"
					value="${userType }" />
				<input type="hidden" id="userName" name="userName"
					value="${userName }" />
				<input type="hidden" id="message" value="${message }"/>
				<input type="hidden" name="pkValue" value="${rs.zckssj}"/>
				<input type="hidden" name="njV" id="njV">
				<input type="hidden" name="xyV" id="xyV">
				<input type="hidden" name="zyV" id="zyV">
				<input type="hidden" name="bjV" id="bjV">


				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title }</a>
					</p>
				</div>

				<table class="formlist" width="70%">
					<thead>
						<tr>
							<th colspan="2">
								<span>�ϱ�����</span>
							</th>
						</tr>
					</thead>

					<tr>
						<th>
							��ǰ״̬��
						</th>
						<td>
							<font color="red">${flg }</font>
						</td>
					</tr>
					<tr>
						<th>
							������
						</th>
						<td>
							<logic:equal value="��" name="flg">
								<button class="button2"
									onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jysbkg&flg=��')"
									style="width:80px" disabled="true">
									����
								</button>
							</logic:equal>
							<logic:equal value="��" name="flg">
								<button class="button2"
									onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jysbkg&flg=��')"
									style="width:80px">
									����
								</button>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<th>
							�رգ�
						</th>
						<td>
							<logic:equal value="��" name="flg">
								<button class="button2"
									onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jysbkg&flg=��')"
									style="width:80px">
									�ر�
								</button>
							</logic:equal>
							<logic:equal value="��" name="flg">
								<button class="button2"
									onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jysbkg&flg=��')"
									style="width:80px" disabled="true">
									�ر�
								</button>
							</logic:equal>
						</td>
					</tr>
				</table>
				<p style="width:500px" />
					&nbsp;
			</html:form>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
					alert('�����ɹ�');
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert('����ʧ��');
				</script>
				</logic:equal>

			</logic:present>
		</div>
	</body>
	</html>