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
		
		if ("false"==hasResume && "申请"==text){
			alert("对不起，您还未登记个人简历!");
		} else{
			jyweb.saveStuJobs(pkV,userName,text,function(data){
			
				if (data){
					alert(text+"成功");
				} else {
					alert("您已"+text+"过此岗位");
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
						招聘单位基本情况
					</h5>
					<table width="100%" border="0" class="tab_02">
						<tr>
							<td width="400px">
								单位性质： ${rs.dwxzmc }
							</td>
							<td>
								行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业&nbsp;：${rs.hyflmc }
							</td>
						</tr>
						<tr>
							<td>
								联&nbsp;系&nbsp;人&nbsp;：${rs.lxr }
							</td>
							<td>
								联系电话：${rs.lxdh }
							</td>
						</tr>
						<tr>
							<td>
								传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真&nbsp;：${rs.cz }
							</td>
							<td>
								电子邮箱：${email }
							</td>
						</tr>
						<tr>
							<td>
								网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址&nbsp;：${rs.wz }
							</td>
							<td>
								邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编&nbsp;：${rs.yb }
							</td>
						</tr>
						<tr>
							<td colspan="2">
								联系地址：${rs.dz }
							</td>
						</tr>
					</table>
				</div>
				<div class="module">
					<h5>
						招聘单位简介
					</h5>
					<p>
						${rs.dwjj}
					</p>
				</div>

				<logic:iterate id="g" name="gwxxList" indexId="i">
					<div class="module">
						<h5>
							招聘专业/岗位${i+1 }
						</h5>
						<table width="100%" border="0" class="tab_02">
							<tr>
								<td width="400px">
									专业/岗位名称：${g.zpzwmc }    
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal value="stu" name="jyweb_userType" scope="session">
										<a href="#" onclick="saveJobs('申请','${g.zpzw }${rs.dwmc }${g.fbsj }')"><font color="blue">申请</font></a>
										&nbsp;&nbsp;&nbsp;
										<a href="#" onclick="saveJobs('收藏','${g.zpzw }${rs.dwmc }${g.fbsj }')"><font color="blue">收藏</font></a>
									</logic:equal>
								</td>
								<td>
									招聘人数：${g.zprs }
								</td>
							</tr>
							<tr>
								<td>
									岗位性质：${g.gwxz }
								</td>
								<td>
									外语要求：${g.wyyq }
								</td>
							</tr>
							<tr>
								<td>
									学历要求：${g.xlyq }
								</td>
								<td>
									发布时间：${g.fbsj }
								</td>
							</tr>
							<tr>
								<td>
									招聘时间：${g.zpsj }
								</td>
								<td>
									招聘地点：${g.zpdd }
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
