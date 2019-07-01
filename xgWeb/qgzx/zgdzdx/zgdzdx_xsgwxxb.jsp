<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function stringFormat(){
			var element = ['kcjqgzxsj','sqly','bz'];
			for(var i=0; i<element.length; i++){
				if(ele(element[i])){
					ele(element[i]).innerHTML = formatContentWidth(ele(element[i]).innerText,30);
				}
			}
		}
	</script>
</head>	
	<body onload="stringFormat()">
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 申请 - 申请结果查询 - 详细信息</a>
				</p>
			</div>	

			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>

			<logic:notEmpty name="rs">
				<div class="tab">
				<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>学号</th>
						<td>
							${rs.xh}
						</td>
						<th>岗位名称</th>
						<td>
							${rs.gwdm}
						</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td>
							${rs.xm}
						</td>
						<th>年度</th>
						<td>
							${rs.nd}
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${rs.xb}
						</td>
						<th>学年</th>
						<td>
							${rs.xn}
						</td>
					</tr>
					<tr>
						<th>年级</th>
						<td>
							${rs.nj}
						</td>
						<th>学期</th>
						<td>
							${rs.xqmc}
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							${rs.xymc}
						</td>
						<th>联系电话</thd>
						<td>
							${rs.lxdh}
						</td>
					</tr>
					<tr>
						<th>专业</th>
						<td>
							${rs.zymc}
						</td>
						<th>是否困难生</th>
						<td>
						    ${rs.sfpks}
						</td>
					</tr>
					<tr>
						<th>班级</th>
						<td>
							${rs.bjmc}
						</td>
						<th>有何特长</th>
						<td>
						    ${rs.yhtc}
						</td>
					</tr>
					<tr>
						<th>政治面貌</th>
						<td>
							${rs.zzmm}
						</td>
						<th>学校审核</th>
						<td>
							${rs.xxyj}
						</td>
					</tr>					
					<tr>
						<th>申请理由</th>
						<td colspan="3" id="kcsgz">
							${rs.xssq}
						</td>
					</tr>
					<tr>
						<th>可参加勤工助学时间</th>
							<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" class="formlist">
									<tr>
										<th>
											时间
										</th>
										<th>
											星期一
										</th>
										<th>
											星期二
										</th>
										<th>
											星期三
										</th>
										<th>
											星期四
										</thd>
										<th>
											星期五
										</th>
										<th>
											星期六
										</th>
										<th>
											星期日
										</th>
									</tr>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}6"
													value="${kxsj.sat}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="□" />
												<input type="hidden" name="index${kxsj.sjIndex}7"
													value="${kxsj.sun}" />
											</td>

										</tr>
									</logic:iterate>
								</table>
									<script language="javascript">
										for(var i=0;i<7;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>
							</td>
						</logic:present>
						<logic:notPresent name="kxList">
							<td height="22" colspan="3">
								<table id="tbSj" class="formlist">
									<tr>
										<th>
											时间
										</th>
										<th>
											星期一
										</th>
										<th>
											星期二
										</th>
										<th>
											星期三
										</th>
										<th>
											星期四
										</th>
										<th>
											星期五
										</th>
										<th>
											星期六
										</th>
										<th>
											星期日
										</th>
									</tr>
									<logic:iterate id="kxsj" name="whkxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3" id="bz">
							${rs.bz}
						</td>
					</tr>
					</tbody>
				    <tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button" id="btn_gb"
								onclick="Close();return false;"
								style="width:80px">
								关 闭
							</button>	
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
			</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>