<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		</script>
	</head>
	<body onload="" >
		<html:form action="/jhzyGjlzjxj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${rs.xn }学年国家励志奖学金申请
						</font>
					</td>
				</tr>
			</table>
			
				<table width="100%" border="0" class="formlist">
					<!-- 学生基本信息 begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th align="right" width="20%">
								学号
							</th>
							<td align="left" width="30%">
					
									<input type="hidden" id="xh" name="xh" value="${rs.xh }"/>
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
									${rs.xh }
							</td>
							<th align="right" width="20%">
								姓名
							</th>
							<td align="left" width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								性别
							</th>
							<td align="left" width="">
								${rs.xb }
							</td>
							<th align="right" width="">
								学制
							</th>
							<td align="left" width="">
								${rs.xz }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								年级
							</th>
							<td align="left" width="">
								${rs.nj }
							</td>
							<th align="right" width="">
								<bean:message key="lable.xb" />
							</th>
							<td align="left" width="">
								${rs.xymc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								专业
							</th>
							<td align="left" width="">
								${rs.zymc }
							</td>
							<th align="right" width="">
								班级
							</th>
							<td align="left" width="">
								${rs.bjmc}
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								民族
							</th>
							<td align="left" width="">
								${rs.mzmc }
							</td>
							<th align="right" width="">
								政治面貌
							</th>
							<td align="left" width="">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								身份证号
							</th>
							<td align="left" width="">
								${rs.sfzh }
							</td>
							<th align="right" width="">
								出生年月
							</th>
							<td align="left" width="">
								${rs.csrq }
							</td>
						</tr>
					</tbody>
					<!-- 学生基本信息end -->
					
					<!-- 困难生信息 begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>学习情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th align="right" width="20%">
								成绩排名<br/>（名次/总人数）
							</th>
							<td align="left" width="30%" >
								${rs.cjpm}
								/${rs.bjzrs }

							</td>
							<th align="right" width="20%">
								考试情况
							</th>
							<td align="left" width="30%" >
								必修课&nbsp;&nbsp;&nbsp;${rs.bxkms}（门）
								<br/>及格以上${rs.jgms}（门）
							</td>
							
						</tr>
						<tr>
							<th align="right" width="20%">
							实行综合考评排名
							</th>
							<td align="left" width="30%" >
							${rs.sxzhkppm }
							</td>
							<th align="right" width="20%">
								如是，排名<br/>（名次/总人数）
							</th>
							<td align="left" width="30%" >
								${rs.zhkppm}/${rs.bjzrs }
				
							</td>
						</tr>
						</tbody>
						</table>
					
						<table width="100%" border="0" class="formlist">
						<!-- 学生基本信息 begin-->
						<thead>
							<tr>
								<th colspan="3">
									<span>大学期间主要获奖情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<th style="width:20%">
								<div align="center">获奖日期</div>
							</th>
							<th style="width:40%">
								<div align="center">获奖名称</div> 
							</th>
							<th style="width:40%">
								<div align="center">颁奖单位</div>
							</th>
							</tr>
							<tr>
								<td align="center">
									${rs.hjsj1}
								</td>
								<td align="center">
								${rs.hjmc1}
								</td>
								<td align="center">
									${rs.bjdw1}
								</td>
							</tr>
							<tr>
								<td align="center">
									${rs.hjsj2}
								</td>
								<td align="center">
									${rs.hjmc2}
								</td>
								<td align="center">
									${rs.bjdw2}
								</td>
							</tr>
							<tr>
								<td align="center">
									${rs.hjsj3}
								</td>
								<td align="center">
									${rs.hjmc3}
								</td>
								<td align="center">
								${rs.bjdw3}
								</td>
							</tr>
							<tr>
								<td align="center">
								${rs.hjsj4}
								</td>
								<td align="center">
									${rs.hjmc4}
								</td>
								<td align="center">
									${rs.bjdw4}
								</td>
							</tr>
						</tbody>
						</table>
						
						<table width="100%" border="0" class="formlist">
						<tbody>
						<tr>
							<th align="right" width="20%" >
							申请理由
								
							</th>
							<td align="left" width="" colspan="3">
									<div style="word-break:break-all;width:99%" >${rs.sqly }</div>
							</td>
						</tr>
						
					</tbody>
				
					<!-- 困难生信息 end-->			
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>