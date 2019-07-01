<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/stuinfoFunction.js"></script>
<script type="text/javascript" src="js/xsxx/xjyd.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
<script>
	function save(shzt){
		refreshForm('xjyd.do?method=xjydShOne&doType=save&shjg=' + shzt);
	}
	
</script>
</head>

<body>
	<html:form action="/xjyd.do" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<!--用户信息-->
		<%@ include file="/xsxx/yhxx.jsp"%>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.jbxx.style.display=(document.all.jbxx.style.display =='none')?'':'none'"> 
							<span>基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody id="jbxx">
					<tr>
						<th width="24%">学号</th>
						<td width="26%">
							${rs.xh}				
						</td>
						<th>性别</th>
						<td>${rs.xb }</td>
					</tr>
					<tr>
						<th width="24%">姓名</th>
						<td width="26%">
							${rs.xm }
						</td>
						<th>出生日期</th>
						<td>${rs.csrq }</td>
					</tr>
					<tr>
						<th>手机号码</th>
						<td>${rs.sjhm }</td>
						<th>固定电话</th>
						<td>${rs.lxdh }</td>
					</tr>
					<tr>
						<th>异动序号</th>
						<td>
							${rs.ydxh}					
						</td>
						<th>处理文号</th>
						<td>
							${rs.clwh}
						</td>
					</tr>					
					<tr>
						<th>异动类别</th>
						<td>
							${rs.ydlbmc}
							<input type="hidden" id="ydlbdm" name="ydlbdm" value="${rs.ydlbm}"/>
						</td>
						<th>异动日期</th>
						<td>
							${rs.ydrq}
						</td>
					</tr>
					<tr>	
						<th>异动说明</th>
						<td>
							${rs.ydsm}
						</td>
						<th>异动截止日期</th>
						<td>
							${rs.ydjzrq}
						</td>
					</tr>
					<tr>
						<th>异动原因</th>
						<td colspan="3">  
							${rs.ydyy}
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.xjxx.style.display=(document.all.xjxx.style.display =='none')?'':'none'"> 
							<span>学籍信息</span>
						</th>
					</tr>
				</thead>
				<tbody id="xjxx">
					<tr>
						<td colspan="2">
							<table class="formlist" style="width:100%;height:100%"> 
								<thead>
									<tr>
										<td colspan="2" align="center"><b>异动前信息</b></td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th height="20px">年级</th>
										<td width="70%">
											${rs.ydqnj}
										</td>
									</tr>
									<tr>
										<th height="20px"><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											${rs.ydqxymc}
										</td>
									</tr>
									<tr>
										<th height="20px">专业</th>
										<td>
											${rs.ydqzymc}
										</td>
									</tr>
									<tr>
										<th height="20px">班级</th>
										<td>
											${rs.ydqbjmc}
										</td>
									</tr>
									<tr>
										<th height="20px">学制</th>
										<td>
											${rs.ydqxz}
										</td>
									</tr>
									<tr>
										<th height="20px">学籍状态</th>
										<td>
											${rs.ydqxjztm}
										</td>
									</tr>
									<tr>
										<th height="20px">是否在校</th>
										<td>
											${rs.ydqsfzx}
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td colspan="2">
							<table class="formlist">
								<thead>
									<tr>
										<td colspan="2" align="center"><b>异动后信息</b></td>
									</tr>
								</thead>
								<tbody>
									<tr height="20px">
										<th>年级</th>
										<td>
											${rs.ydhnj}						
										</td>
									</tr>
									<tr height="20px">
										<th><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											${rs.ydhxymc}
										</td>
									</tr>
									<tr height="20px">
										<th>专业</th>
										<td>
											${rs.ydhzymc}
										</td>
									</tr>
									<tr height="20px">
										<th>班级</th>
										<td>
											${rs.ydhbjmc}
										</td>
									</tr>
									<tr height="20px">
										<th>学制</th>
										<td>
											${rs.ydhxz}
										</td>
									</tr>
									<tr height="20px">
										<th>学籍状态</th>
										<td>
											${rs.ydhxjztm}
										</td>
									</tr>
									<tr height="20px">
										<th>是否在校</th>
										<td>
											${rs.ydhsfzx}
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>					
				</tbody>
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.shxx.style.display=(document.all.shxx.style.display =='none')?'':'none'"> 
							<span>审核信息</span>
						</th>
					</tr>
				</thead>
				<logic:notEqual value="view" name="doType">
					<tbody id="shxx" style="display:none">
						<tr>
							<th>
								${rs.xtgwmc}审核意见
								<br />
								<font color="red"><限250字>
								</font>
							</th>
							<td colspan="3"> 
								<html:hidden property="xtgwid" name="rs"/>
								<html:textarea property="shyj" name="rs" cols="60" rows="6" onblur="chLeng(this,250)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>审核人</th>
							<td> 
								${userNameReal}
							</td>
							<th>审核时间</th>
							<td> 
								${dqsj}
							</td>
						</tr>
					</tbody>
				</logic:notEqual>
				<logic:equal value="view" name="doType">
					<tbody id="shxx" style="display:none">
						<logic:iterate id="v" name="shxxList">
						<tr>
							<th>
								${v.xtgwmc}审核
							</th>
							<td colspan="3"> 
								${v.shzt}
							</td>
						</tr>
						<tr>
							<th>
								${v.xtgwmc}审核意见
							</th>
							<td colspan="3"> 
								${v.shyj}
							</td>
						</tr>
						<tr>
							<th>审核人</th>
							<td> 
								${v.shr}
							</td>
							<th>审核时间</th>
							<td> 
								${v.shsj}
							</td>
						</tr>
						</logic:iterate>
					</tbody>
				</logic:equal>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						<div class="buttontool">
							<logic:notEqual value="view" name="doType">
								<button type="button" class="" onclick="save('通过');" >
									通&nbsp;&nbsp;过
								</button>
								<button type="button" class="" onclick="save('不通过');" >
									不通过
								</button>
								<button type="button" class="" onclick="save('退回')" >
									退&nbsp;&nbsp;回
								</button>
							</logic:notEqual>
							
							<button type="button" class="" onclick="Close();return false;" >
								关&nbsp;&nbsp;闭
							</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</html:form>
</body>
</html>