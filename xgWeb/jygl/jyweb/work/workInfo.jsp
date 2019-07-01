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
		
		if ("false"==hasResume && "申请"==text){
			alert("对不起，您还未登记个人简历!");
		} else{
			jQuery.post('jyweb.do?method=saveStuJobs',{pkValue:encodeURI(pkValue),type:encodeURI(text)},function(data){
				if (data == 'true'){
					alert(text+"成功");
				} else {
					alert("您已"+text+"过此岗位!");
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
					<!-- logo 和 菜单 -->
					<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
				</div>
				<div class="index_mainframe">
					<div class="type_left">
						<div class="type_UserInfo">
							<h3>
								<span>用户信息</span>
							</h3>
							<div class="con">
								<logic:present name="jyweb_userName">
									<p>
										欢迎您：
										<font color="#025BBB">${jyweb_realName }</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
											注 销
										</button>
									</div>
								</logic:present>
								<logic:notPresent name="jyweb_userName">
									<p>
										欢迎您：
										<font color="#025BBB">游客</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=index')">
											登录
										</button>
									</div>
								</logic:notPresent>
							</div>
						</div>
					</div>
					<div class="type_right">
						<div class="rightcon">
							<h3>
								<span>招聘信息</span>
							</h3>
							<div class="tabcon">
								<h4>
									${rs.gsmc }
								</h4>
								<div class="module">
									<h5>
										招聘单位基本情况
									</h5>
									<table width="100%" border="0" class="tab_02">
										<tr>
											<td width="400px">
												单位性质：${rs.dwxzmc }
											</td>
											<td>
												行业分类：${rs.hyflmc }
											</td>
										</tr>
										<tr>
											<td>
												公司地址：${rs.dz }
											</td>
											<td>
												传 真：${rs.cz }
											</td>
										</tr>
										<tr>
											<td>
												EMAIL: ${rs.email }
											</td>
											<td>
												邮政编码：${rs.yb }
											</td>
										</tr>
										<tr>
											<td>
												联系电话: ${rs.lxdh }
											</td>
											<td>
												联 系 人：${rs.lxr }
											</td>
										</tr>
									</table>
								</div>
								<div class="module">
									<h5>
										招聘单位简介
									</h5>
									<p>
										${rs.dwjj }
									</p>
								</div>
								<div class="module">
									<h5>
										招聘职位要求
									</h5>
									<table width="100%" border="0" class="tab_02">
										<tr>
											<td width="400px">
												专业/岗位名称：${rs.zpzwmc }
											</td>
											<td>
												工作地点：${rs.gzdd }
											</td>
										</tr>
										<tr>
											<td>
												人 数：${rs.zprs }
											</td>
											<td>
												性 别：${rs.xb }
											</td>
										</tr>
										<tr>
											<td>
												学历要求：${rs.xlyq }
											</td>
											<td>
												外语要求：${rs.wyyq }
											</td>
										</tr>
										<tr>
											<td>
												发布日期：${rs.fbsj }
											</td>
											<td>
												有效期限：${rs.yxqx }
											</td>
										</tr>
										<tr>
											<td>
												招聘时间：${rs.zpsj }
											</td>
											<td>
												招聘地点：${rs.zpdd }
											</td>
										</tr>
									</table>
								</div>
								<div class="module">
									<h5>
										岗位职责
									</h5>
									<p>
										${rs.gwzz }
									</p>
								</div>
								<div class="module">
									<h5>
										岗位要求
									</h5>
									<p>
										${rs.zwyq }
									</p>
								</div>

								<html:hidden property="save_xh" value="${jyweb_userName }" />
								<html:hidden property="save_dwmc" value="${rs.gsmc }" />
								<html:hidden property="save_gwmc" value="${rs.zpzw }" />
								<html:hidden property="save_tdrq" value="${nowTime }" />
								<html:hidden property="save_joblb" value="投递" />

								<logic:equal value="stu" name="jyweb_userType" scope="session">
									<div class="btn">
										<button onclick="saveJobs('申请')">
											申请岗位
										</button>
										<button onclick="saveJobs('收藏')">
											收藏岗位
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
