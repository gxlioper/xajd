<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
	function saveJobs(text){
	
		var pkValue = $('pkValue').value;
		var userName = $('userName').value;
		var hasResume = $('hasResume').value;
		
		if ("false"==hasResume && "����"==text){
			alert("�Բ�������δ�ǼǸ��˼���!");
		} else{
			jQuery.post('jyweb.do?method=saveStuJobs',{pkValue:encodeURI(pkValue),type:encodeURI(text)},function(data){
				if (data == 'true'){
					alert(text+"�ɹ�");
				} else {
					alert("����"+text+"���˸�λ!");
				}
			});
		}
	}
</script>
	</head>


	<body>
		<html:form action="/jyweb">
			<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue" />
			<input type="hidden" name="userName" value="${jyweb_userName }"
				id="userName" />
			<input type="hidden" name="hasResume" value="${hasResume }"
				id="hasResume" />

			<div class="index_mainbody">
				<div class="index_topframe">
					<!-- logo �� �˵� -->
					<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
				</div>
				<div class="index_mainframe">
					<div class="type_left">
						<div class="type_UserInfo">
							<h3>
								<span>�û���Ϣ</span>
							</h3>
							<div class="con">
								<logic:present name="jyweb_userName">
									<p>
										��ӭ����
										<font color="#025BBB">${jyweb_realName }</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
											ע ��
										</button>
									</div>
								</logic:present>
								<logic:notPresent name="jyweb_userName">
									<p>
										��ӭ����
										<font color="#025BBB">�ο�</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=index')">
											��¼
										</button>
									</div>
								</logic:notPresent>
							</div>
						</div>
					</div>
					<div class="type_right">
						<div class="rightcon">
							<h3>
								<span>��Ƹ��Ϣ</span>
							</h3>
							<div class="tabcon">
								<h4>
									${rs.gsmc }
								</h4>
								<div class="module">
									<h5>
										��Ƹ��λ�������
									</h5>
									<table width="100%" border="0" class="tab_02">
										<tr>
											<td width="400px">
												��λ���ʣ�${rs.dwxzmc }
											</td>
											<td>
												��ҵ���ࣺ${rs.hyflmc }
											</td>
										</tr>
										<tr>
											<td>
												��˾��ַ��${rs.dz }
											</td>
											<td>
												�� �棺${rs.cz }
											</td>
										</tr>
										<tr>
											<td>
												EMAIL: ${rs.email }
											</td>
											<td>
												�������룺${rs.yb }
											</td>
										</tr>
										<tr>
											<td>
												��ϵ�绰: ${rs.lxdh }
											</td>
											<td>
												�� ϵ �ˣ�${rs.lxr }
											</td>
										</tr>
									</table>
								</div>
								<div class="module">
									<h5>
										��Ƹ��λ���
									</h5>
									<p>
										${rs.dwjj }
									</p>
								</div>
								<div class="module">
									<h5>
										��ƸְλҪ��
									</h5>
									<table width="100%" border="0" class="tab_02">
										<tr>
											<td width="400px">
												רҵ/��λ���ƣ�${rs.zpzwmc }
											</td>
											<td>
												�����ص㣺${rs.gzdd }
											</td>
										</tr>
										<tr>
											<td>
												�� ����${rs.zprs }
											</td>
											<td>
												�� ��${rs.xb }
											</td>
										</tr>
										<tr>
											<td>
												ѧ��Ҫ��${rs.xlyq }
											</td>
											<td>
												����Ҫ��${rs.wyyq }
											</td>
										</tr>
										<tr>
											<td>
												�������ڣ�${rs.fbsj }
											</td>
											<td>
												��Ч���ޣ�${rs.yxqx }
											</td>
										</tr>
										<tr>
											<td>
												��Ƹʱ�䣺${rs.zpsj }
											</td>
											<td>
												��Ƹ�ص㣺${rs.zpdd }
											</td>
										</tr>
									</table>
								</div>
								<div class="module">
									<h5>
										��λְ��
									</h5>
									<p>
										${rs.gwzz }
									</p>
								</div>
								<div class="module">
									<h5>
										��λҪ��
									</h5>
									<p>
										${rs.zwyq }
									</p>
								</div>

								<html:hidden property="save_xh" value="${jyweb_userName }" />
								<html:hidden property="save_dwmc" value="${rs.gsmc }" />
								<html:hidden property="save_gwmc" value="${rs.zpzw }" />
								<html:hidden property="save_tdrq" value="${nowTime }" />
								<html:hidden property="save_joblb" value="Ͷ��" />

								<logic:equal value="stu" name="jyweb_userType" scope="session">
									<div class="btn">
										<button onclick="saveJobs('����')">
											�����λ
										</button>
										<button onclick="saveJobs('�ղ�')">
											�ղظ�λ
										</button>
									</div>
								</logic:equal>

							</div>
						</div>
					</div>
				</div>
				<div class="index_botframe">
					<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
				</div>
			</div>
			<logic:present name="message">
				<script>
		alert("${message}");
	</script>
			</logic:present>

		</html:form>
	</body>
</html>
