<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysq/js/yysqDetail.js"></script>
		<script type="text/javascript">	
			function query(){
				document.location.href="xlzx_yysq.do?method=yysqDetail&xxxq="+jQuery("#xxxq").val()+"&date="+jQuery("#pbdate").val();
			}
		</script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysq" method="post">
		<!--  <input type="hidden" id="url" name="url" value="xlzx_yysq.do?method=yysqDetail" /> -->
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pbdate" id="pbdate" value="${zxspbInfo.pbdate}" />
			<logic:equal name="xxdm" value="10026">
			<logic:notEqual name="doType" value="view">	
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">校区</th>
						<td>
							<html:select property="xxxq" styleId="xxxq" style="width:150px;">
								<html:option value="">---请选择校区---</html:option>
								<html:options collection="xqList" property="dm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
			</logic:notEqual>
			</logic:equal>	
			<div style='width:100%;overflow-y:hidden;overflow-x:hidden'>
				<table width="100%" border="1" class="formlist">
					<logic:notEqual name="doType" value="view">	
						<thead>
							<tr >
								<th colspan="5">
									<span>预约信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="zxspbInfo">
							<tr style="height:10px">
								<th  width="20%">
									预约咨询日期
								</th>
								<td width="20%" >
									<span class="red"><B>${zxspbInfo.pbdate}</B></span>
								</td>
								<th width="20%">
									学年学期
								</th>
								<td width="40%" colspan="2">
									${currxn}&nbsp;&nbsp;${currxq}
								</td>
							</tr>
							<logic:notEmpty name="zxspbInfo" property="bz">
								<tr style="height:50px">
									 <th>
										预约说明
									</th>
									<td  colspan="4">
										${zxspbInfo.bz}
									</td>
								</tr>
							</logic:notEmpty>
						</tbody>
					</logic:notEqual>
					<thead>
						<tr >
							<th colspan="5">
								<span>预约咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
						<logic:iterate name="zxsInfoList" id="zxsList" indexId="index">
							<input type="hidden" name="kjdrs" id="kjdrs${index}" value="${zxsList.kjdrs}" />
							<input type="hidden" name="yjdrs" id="yjdrs${index}"  value="${zxsList.yjdrs}" />
							<tr style="height:10px">
								<logic:equal name="xxdm" value="10026">
									<td align="left" rowspan="7" >
								</logic:equal>
								<logic:notEqual name="xxdm" value="10026">
									<td align="left" rowspan="6" >
								</logic:notEqual>
									<div align="center"><img src="<%=request.getContextPath() %>/teaPic.jsp?zgh=${zxsList.zgh}" style="height:133px;" border="0"   id="zhaopian"/>
									<logic:notEqual name="doType" value="view">
										<br><br><button  name="buttonSave" id="buttonSave${index}" onclick="addYyInfo('${zxsList.zgh}');return false;"  style="display:none">预约</button>
									</logic:notEqual>
									</div>
									</button>
								</td>
								<th >
									姓名
								</th>
								<td >
									${zxsList.xm}
								</td>
								<td colspan="2">
									<logic:equal name="zxsList" property="kjdrs" value="">
										<span class="blue">该咨询师当日已成功预约</span><span class="red">${zxsList.yjdrs}</span><span class="blue">人,无上限</span>
									</logic:equal>
									<logic:notEqual name="zxsList" property="kjdrs" value="">
										<span class="blue">该咨询师当日已<logic:notEqual name="pbfs" value="2">成功</logic:notEqual>预约</span><span class="red">${zxsList.yjdrs}</span><span class="blue">人,上限</span><span class="red">${zxsList.kjdrs}</span><span class="blue">人</span>
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									性别
								</th>
								<td>
									${zxsList.xb }
								</td>
								<th width="20%">
									年龄
								</th>
								<td>
									${zxsList.age}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									联系电话
								</th>
								<td >
									${zxsList.lxdh }
								</td>
								<th>
									所在部门
								</th>
								<td >
									${zxsList.bmmc }
								</td>
							</tr>
							<logic:equal name="xxdm" value="10026">
								<tr style="height:10px">
									<th>
										校区<br/>
									</th>
									<td colspan="3">
										${zxsList.xqmc }
									</td>
								</tr>
							</logic:equal>
							<tr style="height:10px">
								<th >
									咨询地址
								</th>
								<td   colspan="3">
									${zxsList.address }
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									任职资质<br/>
								</th>
								<td colspan="3">
									${zxsList.zxszg }
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									简介<br/>
								</th>
								<td colspan="3">
									${zxsList.zxsjj }
								</td>
							</tr>
							<tr style="height:10px"><td colspan="5"></td></tr>
						</logic:iterate>
					</tbody>
					<logic:equal name="doType" value="view">
						<logic:notEmpty name="yysqInfo">
							<thead>
								<tr >
									<th colspan="5">
										<span>学生预约信息</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr style="height:10px">
									<th  width="20%">
										预约咨询日期
									</th>
									<td width="20%" >
										<span class="red"><B>${zxspbInfo.pbdate}</B></span>
									</td>
									<th width="20%">
										学年学期
									</th>
									<td width="40%" colspan="2">
										${currxn}&nbsp;&nbsp;${currxq}
									</td>
								</tr>
								<tr>
									<th>
										预约咨询时段
									</th>
									<td>
										<logic:notEqual value="2" name="pbfs">
											${yysqInfo.qssj}
										<logic:notEqual  name="yysqInfo" property="jssj" value="">
											 &nbsp;至&nbsp;${yysqInfo.jssj}
										</logic:notEqual>
										</logic:notEqual>
										<logic:equal value="2" name="pbfs">
											${yysqInfo.sjdmc}
										</logic:equal>
										
									<th>
										预留联系号码
									</th>
									<td  colspan="2">
											${yysqInfo.xstell}
									</td>
								</tr>
								<tr style="height:10px">
									<th>
										咨询主题
									</th>
									<td colspan="4">
											${yysqInfo.yyzxzt}
									</td>
								</tr>
								<tr style="height:50px">
									<th>咨询概要<br/>
									</th>
									<td colspan="4">
										${yysqInfo.yyzxxq}
									</td>
								</tr>
							</tbody>
						</logic:notEmpty>
					  </logic:equal>
					</table>
				</div>
				<!--
				 <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button id="btn_fh" onclick="returnPage();return false;">
										返回
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			   -->
		</html:form>
	</body>
</html>

