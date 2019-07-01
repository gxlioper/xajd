<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		
	</head>
	<body >
		<html:form action="/jtqkdzGl" method="post" >
		
		<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
		
					<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${rs.xn }学年家庭情况调查 
						</font>
					</td>
				</tr>
			</table>
				
					<jsp:include page="/xsgzgl/xszz/jhzy/jtqkdz/xsxx.jsp" flush="true"></jsp:include>
					
				<logic:notEmpty name="jtxxList">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="8">
									<span>家庭调查情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								<th align="" >
									<div align="center">姓名</div>
								</th>
								<th align="right" >
									<div align="center">年龄</div>
								</th>
								<th align="right" >
									<div align="center">关系</div>
								</th>
								<th align="right" >
									<div align="center">工作(学习)单位</div>
								</th>
								<th align="right" >
									<div align="center">职业</div>
								</th>
								<th align="right" >
									<div align="center">联系电话</div>
								</th>
								<th align="right" >
									<div align="center">年收入</div>
								</th>
								<th align="right" >
									<div align="center">健康状况</div>
								</th>
								</tr>
								
							
								<logic:iterate id="jtxx" name="jtxxList" >
									<tr>
								<td>
									<div align="center">${jtxx.cyxm }</div>
								</td>
								<td>
									<div align="center">${jtxx.cynl }</div>
								</td>
								<td>
									<div align="center">${jtxx.cygx }</div>
								</td>
								<td>
									<div align="center">${jtxx.cygzxxdw }</div>
								</td>
								<td>
									<div align="center">${jtxx.cyzy }</div>
								</td>
								<td>
									<div align="center">${jtxx.cylxdh }</div>
								</td>
								<td>
									<div align="center">${jtxx.cynsr }</div>
								</td>
								<td>
									<div align="center">${jtxx.cyjkzk }</div>
								</td>
							</tr>
								</logic:iterate>
							
						</tbody>
					</table>
					</logic:notEmpty>	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>家庭调查情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							是否孤残
						</th>
						<td align="left" width="30%">
							
							${rs.sfgc }
						</td>
						<th align="right" width="20%">
							是否单亲
						</th>
						<td align="left" width="30%">
							${rs.sfdq }
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							烈士子女
						</th>
						<td align="left" width="30%">
							${rs.sflszn }
						</td>
						<th align="right" width="20%">
							是否低保
						</th>
						<td align="left" width="30%">
							${rs.sfdb }
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							家庭户口
						</th>
						<td align="left" width="30%">
							${rs.jthk }
						</td>
						<th></th>
						<td></td>
						
					</tr>
					<tr>
						<th align="right" width="20%">
							所在省市县
						</th>
						<td align="left" colspan="3">
							${rs.smc }${rs.simc }${rs.xmc }
						</td>
					</tr>
					
					<tr>
						<th align="right" width="20%">
							家庭地址
						</th>
						<td align="left" width="30%">
						${rs.jtdz}
						</td>
						<th align="right" width="20%">
							家庭电话
						</th>
						<td align="left" width="30%">
							${rs.jtdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							家庭邮编
						</th>
						<td align="left" width="30%">
							${rs.jtyb}
						</td>
						<th align="right" width="20%">
							家庭人口数
						</th>
						<td align="left" width="30%">
							${rs.jtrks}
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							家庭人均收入
						</th>
						<td align="left" width="30%">
						${rs.jtrjsr}（元）
						</td>
						<th align="right" width="20%">
							家庭月总收入
						</th>
						<td align="left" width="30%">
							${rs.jtnzsr}（元）
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							收入来源
						</th>
						<td align="left" width="30%">
							${rs.srly}
						</td>
						<th align="right" width="20%">
							
						</th>
						<td align="left" width="30%">
							
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
					已获资助情况
						</th>
						<td align="left" colspan="3">
							<div style="word-break:break-all;width:97%">${rs.yhzzqk}</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
						家庭受灾情况
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtszqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
				突发事件情况
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.tfsjqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
						残疾年迈情况
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.cjnmqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							家庭事业情况
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtsyqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
						家庭欠债情况
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtqzqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							家庭其他情况
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtqtqk }</div>
						</td>
					</tr>
					</tbody>
				</table>
				
				
<!--				<iframe src="xszz_jhzy_ckXsjtknxx.do?pkStr=2012380200105122011-2012" width="900px" height="450px"></iframe>-->
				
				</div>
				<table class="formlist">
								<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="btn">
								
								<button type="button" onclick="Close();return false;" id="buttonClose">
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
