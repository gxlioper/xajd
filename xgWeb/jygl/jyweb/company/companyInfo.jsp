<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/jyweb.ini"%>
	<script type="text/javascript">
	function saveJobs(text,pkV){
		
		var userName = $('userName').value;
		var hasResume = $('hasResume').value;
		
		if ("false"==hasResume && "����"==text){
			alert("�Բ�������δ�ǼǸ��˼���!");
		} else{
			jyweb.saveStuJobs(pkV,userName,text,function(data){
			
				if (data){
					alert(text+"�ɹ�");
				} else {
					alert("����"+text+"���˸�λ");
				}
			});
		}
	}
	</script>
	</head>
	<body>
			<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue" />
			<input type="hidden" name="userName" value="${jyweb_userName }" id="userName" />
			<input type="hidden" name="hasResume" value="${hasResume }"	id="hasResume" />
	
	
		<div class="index_mainbody">
			<div class="index_topframe">
				<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
			</div>
		<div class="index_mainframe">
			<div class="type_mid">
				<h3>
					<span>${rs.dwmc }</span>
				</h3>
				<div class="module">
					<h5>
						��Ƹ��λ�������
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="400px">
								��λ���ʣ� ${rs.dwxzmc }
							</td>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ҵ&nbsp;��${rs.hyflmc }
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;ϵ&nbsp;��&nbsp;��${rs.lxr }
							</td>
							<td>
								��ϵ�绰��${rs.lxdh }
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;��${rs.cz }
							</td>
							<td>
								�������䣺${email }
							</td>
						</tr>
						<tr>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ַ&nbsp;��${rs.wz }
							</td>
							<td>
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;��${rs.yb }
							</td>
						</tr>
						<tr>
							<td colspan="2">
								��ϵ��ַ��${rs.dz }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						��Ƹ��λ���
					</h5>
					<p>
						${rs.dwjj}
					</p>
				</div>

				<logic:iterate id="g" name="gwxxList" indexId="i">
					<div class="module">
						<h5>
							��Ƹרҵ/��λ${i+1 }
						</h5>
						<table width="100%" border="0" class="tab_02">
							<tr>
								<td width="400px">
									רҵ/��λ���ƣ�${g.zpzwmc }    
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal value="stu" name="jyweb_userType" scope="session">
										<a href="#" onclick="saveJobs('����','${g.zpzw }${rs.dwmc }${g.fbsj }')"><font color="blue">����</font></a>
										&nbsp;&nbsp;&nbsp;
										<a href="#" onclick="saveJobs('�ղ�','${g.zpzw }${rs.dwmc }${g.fbsj }')"><font color="blue">�ղ�</font></a>
									</logic:equal>
								</td>
								<td>
									��Ƹ������${g.zprs }
								</td>
							</tr>
							<tr>
								<td>
									��λ���ʣ�${g.gwxz }
								</td>
								<td>
									����Ҫ��${g.wyyq }
								</td>
							</tr>
							<tr>
								<td>
									ѧ��Ҫ��${g.xlyq }
								</td>
								<td>
									����ʱ�䣺${g.fbsj }
								</td>
							</tr>
							<tr>
								<td>
									��Ƹʱ�䣺${g.zpsj }
								</td>
								<td>
									��Ƹ�ص㣺${g.zpdd }
								</td>
							</tr>
						</table>
					</div>
				</logic:iterate>

			</div>
		</div>
		<div class="index_botframe">
			<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
		</div>
		</div>
	</body>
</html>
