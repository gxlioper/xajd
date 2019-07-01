<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxyycl/js/zxyyclDetail.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysq" method="post" styleId="form">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="pbdate" value="${yyzxInfo.yyzxrq}" />
			<input type="hidden" name="zxid" id="zxid" value="${yyzxInfo.zxid}" />
			<input type="hidden" name="id" id="id" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="zgh" id="zgh" value="${yyzxInfo.zgh}" />
			<input type="hidden" name="xh" id="xh" value="${yyzxInfo.xh}" />
			<input type="hidden" name="yjdrs" id="yjdrs" value="${yjdrs}" />
			<input type="hidden" name="kjdrs" id="kjdrs" value="${yyzxInfo.kjdrs}" />
			<div style='width:100%;height:520px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>预约咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
							<tr style="height:10px">
								<th  width="16%">
									姓名
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxm}
								</td>
								<th  width="16%">
									职工号
								</th>
								<td  width="34%">
									${yyzxInfo.zgh}
								</td>
							</tr>
							<tr style="height:10px">
								<th  width="16%">
									性别
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxb }
								</td>
								<th width="16%">
									年龄
								</th>
								<td  width="34%">
									${yyzxInfo.zxsage}
								</td>
							</tr>
							<tr style="height:10px">
								<th width="16%">
									联系电话
								</th>
								<td  width="34%">
									${yyzxInfo.lxdh }
									
								</td>
								<th width="16%">
									所在部门
								</th>
								<td  width="34%">
									${yyzxInfo.bmmc }
									
								</td>
							</tr>							
					</tbody>
					<logic:notEmpty name="yyzxInfo" property="yyzxzt">
						 <thead>
							<tr >
								<th colspan="4">
									<span>学生预约信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="yyzxInfo"> 
							<tr style="height:10px">
								<th>
									预约咨询日期
								</th>
								<td >
									<span class="red"><B>${ yyzxInfo.yyzxrq}</B></span>
								</td>
								 <th  width="16%">
									预约咨询时段
								</th>
								<td  width="34%" >
									${yyzxInfo.yyqssj}&nbsp;
									<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
											至&nbsp;${yyzxInfo.yyjssj}
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									预留联系号码
								</th>
								<td >
									${ yyzxInfo.xstell}
								</td>
								<th>
									学年学期
								</th>
								<td >
									${ yyzxInfo.xn}&nbsp;&nbsp;${ yyzxInfo.xqmc}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									咨询主题
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxzt}
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									咨询概要<br/>
								</th>
								<td colspan="3">
									${ yyzxInfo.yyzxxq}
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<thead>
						<tr >
							<th colspan="4">
								<span>预约反馈信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									预约状态
								</th>
								<td  width="34%">
									<input type="hidden" name="status" id="status" value="${yyzxInfo.status}">
									<input type="hidden" name="yystatus" id="yystatus" value="">
									<logic:equal name ="doType" value="yyfk">
										<input type="radio"  name="sfty"  value="1" id="yyfk_yycg"
										<logic:notEqual name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:notEqual>
											onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yycg">预约成功</label>
										<input type="radio"  name="sfty"  value="2" id="yyfk_yysb"
										<logic:equal name ="yyzxInfo" property="status"  value="5">
											checked="true"
										</logic:equal>
										onclick="sfAgree()"/><label style='cursor:pointer' for="yyfk_yysb">预约失败</label>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.statusmc}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
											${yyzxInfo.statusmc}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<input type="radio"  name="sfty"  value="1" checked="true" onclick="sfAgree()"/>预约成功
												<input type="radio"  name="sfty"  value="2" onclick="sfAgree()"/>预约失败
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
									
								</td>
								
								<td colspan="2">
									<logic:equal name="yyzxInfo" property="kjdrs" value="">
										<span class="blue">该咨询师当日已成功预约</span><span class="red">${yjdrs}</span><span class="blue">人,无上限</span>
									</logic:equal>
									<logic:notEqual name="yyzxInfo" property="kjdrs" value="">
										<span class="blue">该咨询师当日已成功预约</span><span class="red">${yjdrs}</span><span class="blue">人,上限</span><span class="red">${yyzxInfo.kjdrs}</span><span class="blue">人</span>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>咨询安排日期
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxrq"  name="zxrq" 
										<logic:notEqual name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.zxrq}"
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="zxrq"  value="">
											value="${yyzxInfo.yyzxrq}"
										</logic:equal> onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="showNewJdrs(this);"/>
									</logic:equal>
									
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											<span class="red"><B>${yyzxInfo.zxrq}</B></span>
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxrq}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxrq" styleId="zxrq"  value="${yyzxInfo.yyzxrq}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  onchange="showNewJdrs(this);"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
								<th  width="16%">
									咨询时段
								</th>
								<td  width="34%" >
									<logic:equal name ="doType" value="yyfk">
										   <input type="text" id="zxqssj"  name="zxqssj"  style="width:30%"  
												<logic:notEqual name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.zxqssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxqssj"  value="">
													value="${yyzxInfo.yyqssj}"
													</logic:equal>
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
											<input type="text" id="zxjssj"  name="zxjssj" style="width:30%" 
												<logic:notEqual name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.zxjssj}"
												</logic:notEqual>
												<logic:equal name ="yyzxInfo" property="zxjssj"  value="">
													value="${yyzxInfo.yyjssj}"
													</logic:equal>					
												 onfocus="WdatePicker({dateFmt:'HH:mm'})" />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
												${yyzxInfo.zxqssj}&nbsp;
												<logic:notEmpty name="yyzxInfo" property="zxjssj">至&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
													${yyzxInfo.zxqssj}&nbsp;
													<logic:notEmpty name="yyzxInfo" property="zxjssj">至&nbsp;${yyzxInfo.zxjssj}</logic:notEmpty>
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="${yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
												<html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="${yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									联系电话
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxtell"  name="zxtell"  
											<logic:notEqual name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.zxtell}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxtell"  value="">
												value="${yyzxInfo.lxdh}"
												</logic:equal>
											maxlength="11" onblur="checkInputData(this);"/>
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxtell}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxtell}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxtell" styleId="zxtell"  value="${yyzxInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId" >
								<th  width="16%">
									咨询地址
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<input type="text" id="zxdz"  name="zxdz" style="width:400px" maxlength="50"
											<logic:notEqual name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.zxdz}"
											</logic:notEqual>
											<logic:equal name ="yyzxInfo" property="zxdz"  value="">
												value="${yyzxInfo.address}"
												</logic:equal>
										  />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxdz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxdz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:text property="zxdz" styleId="zxdz" maxlength="50" style="width:400px" value="${yyzxInfo.address}" />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yyfkId">
								<th  width="16%">
									备注
									<logic:equal name ="doType" value="yyfk"><br/><font color="red"><B>(限500字)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name="doType" value="view"><br/><font color="red"><B>(限500字)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='bz' styleId="bz" 												
											value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.zxbz}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.zxbz}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='bz' styleId="bz" value="${yyzxInfo.zxbz}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>	
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									<logic:equal name ="doType" value="yyfk"><span class="red">*</span></logic:equal>预约失败原因
									<logic:equal name ="doType" value="yyfk"><br><font color="red"><B>(限500字)</B></font></logic:equal>
									<logic:notEqual name ="doType" value="yyfk"><logic:equal name ="yyzxInfo" property="status" value="1"><logic:notEqual name ="doType"  value="view"><br><font color="red"><B>(限500字)</B></font></logic:notEqual></logic:equal></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
									<logic:equal name ="doType" value="yyfk">
										<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
									</logic:equal>
									<logic:notEqual name ="doType" value="yyfk">
										<logic:notEqual name ="yyzxInfo" property="status" value="1">
											${yyzxInfo.yysbyy}
										</logic:notEqual>
										<logic:equal name ="yyzxInfo" property="status" value="1">
											<logic:equal name ="doType"  value="view">
												${yyzxInfo.yysbyy}
											</logic:equal>
											<logic:notEqual name ="doType"  value="view">
												<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
											</logic:notEqual>
										</logic:equal>
									</logic:notEqual>
								</td>
							</tr>
							
					</tbody>
						<thead name = "zxxgInfo">
							<tr >
								<th colspan="4">
									<span>咨询相关信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxInfo" name = "zxxgInfo">
								<tr style="height:10px">
									<th  width="16%">
										<logic:equal name ="doType" value="zxfk"><span class="red">*</span></logic:equal>咨询状态
									</th>
									<td  width="34%" colspan="3">
										<input type="hidden" name="xspjzt" id="xspjzt" value="${yyzxInfo.xspjzt}">
										<input type="hidden" name="zxstatus" id="zxstatus" value="${yyzxInfo.zxzt}">
										<input type="hidden" name="zxzt" id="zxzt" value="">
										<logic:equal name ="doType" value="zxfk">
											<html:radio name="yyzxInfo" property="zxzt" value="2"/>已咨询
											<html:radio name="yyzxInfo" property="zxzt" value="3"/>未咨询</logic:equal>
										<logic:notEqual name ="doType" value="zxfk">
											${yyzxInfo.zxztmc}
										</logic:notEqual>
									</td>
								</tr>
								<tr style="height:40px">
									<th  width="16%">
										咨询反馈
										<logic:equal name ="doType" value="zxfk"><br/><font color="red"><B>(限500字)</B></font></logic:equal>
									</th>
									<td  width="34%" colspan="3">
										<logic:equal name ="doType" value="zxfk">
											<html:textarea  property='zxsfk' styleId="zxsfk" style="word-break:break-all;width:99%" value="${yyzxInfo.zxsfk}" onblur="chLeng(this,500);" rows='4' />
										</logic:equal>
										<logic:notEqual name ="doType" value="zxfk">
											${yyzxInfo.zxsfk}
										</logic:notEqual>
									</td>
								</tr>
								<tr style="height:40px">
									<th  width="16%">
										学生咨询评价
									</th>
									<td  width="34%" colspan="3">
											${yyzxInfo.xspj}
									</td>
								</tr>
						</tbody>
					</table>
				</div>
			  <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" id="btx">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveyyzxInfo();return false;">
										保 存
									</button>
									<button onclick="Close();return false;">
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

