<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysqnew/js/yyzxDetail.js"></script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysqnew" method="post">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyzxrq" id="yyzxrq" value="${yyzxInfo.yyzxrq}"/>	
			<input type="hidden" name="status" id="status" value="${yyzxInfo.status}" />
			<div style='width:100%;height:460px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
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
								<td  width="34%" colspan="3">
									${yyzxInfo.zxsxm}
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
							<tr style="height:10px">
								<th width="16%">
									咨询地址
								</th>
								<td   colspan="3">
									${yyzxInfo.address}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									任职资质<br/>
								</th>
								<td colspan="3">
									${yyzxInfo.zxszg }
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									简介<br/>
								</th>
								<td colspan="3">
									${yyzxInfo.zxsjj}
								</td>
							</tr>
							
					</tbody>
					
					 <thead>
						<tr >
							<th colspan="4">
								<span>预约申请信息</span>
							</th>
						</tr>
					</thead>
						<tbody id="yyzxInfo"> 
						<tr style="height:10px">
							<th>
								预约咨询日期
							</th>
							<td >
								<span class="red"><B>${yyzxInfo.yyzxrq}</B></span>
							</td>
							<th width="16%">
								预约咨询时段
							</th>
							<td width="34%">
							<logic:equal name ="doType" value="view">
									${ yyzxInfo.yyqssj}&nbsp;
								<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
									至&nbsp;${yyzxInfo.yyjssj}
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name ="doType" value="view">
								<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${ yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
								<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${ yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
							</logic:notEqual>
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<logic:notEqual name ="doType" value="view"><span class="red">*</span></logic:notEqual>预留联系号码
							</th>
							<td>
							<logic:equal name ="doType" value="view">
									${yyzxInfo.xstell}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:text property="xstell" styleId="xstell"   maxlength="11"  value="${ yyzxInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
								</logic:notEqual>
							</td>
							<th width="16%">
								学年学期
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr  style="height:10px">
							<th>
								<logic:notEqual name ="doType" value="view"><span class="red">*</span></logic:notEqual>咨询主题
							</th>
							<td colspan="3">
								<logic:equal name ="doType" value="view">
									${yyzxInfo.yyzxzt}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yyzxInfo.yyzxzt}" />
								</logic:notEqual>
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								咨询概要
								<logic:notEqual name ="doType" value="view"><br/>
								<font color="red"><B>(限500字)</B></font></logic:notEqual>
							</th>
							<td colspan="3">
								<logic:notEqual name ="doType" value="view">
									<html:textarea  property='yyzxxq' styleId="yyzxxq" style="word-break:break-all;width:99%" value="${ yyzxInfo.yyzxxq}" onblur="chLeng(this,500);" rows='4' />
								</logic:notEqual>
								<logic:equal name ="doType" value="view">
									${ yyzxInfo.yyzxxq}
								</logic:equal>
							</td>
						</tr>
					</tbody>
						
					<thead name ="yyqkInfo">
						<tr>
							<th colspan="4">
								<span>预约反馈情况</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									预约状态
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.statusmc}
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo">
								<th  width="16%">
									咨询安排日期
								</th>
								<td  width="34%" >
									<span class="red"><B>${yyzxInfo.zxrq}</B></span>
								</td>
								<th width="16%">
									咨询时段
								</th>
								<td width="34%">
									${yyzxInfo.yyqssj}&nbsp;
									<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
										至&nbsp;${yyzxInfo.yyjssj}
									</logic:notEqual>
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo">
								<th  width="16%">
									咨询电话
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxtell}
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo" >
								<th  width="16%">
									咨询地址
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxdz}
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									预约失败原因<br/>
								<logic:notEqual name="doType" value="view"><font color="red"><B>(限500字)</B></font></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
								<logic:equal name ="doType" value="view">
									${yyzxInfo.yysbyy}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='4' />
								</logic:notEqual>
								</td>
							</tr>
					</tbody>
						
					<thead name="zxxgInfo">
						<tr >
							<th colspan="4">
								<span>咨询相关信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxInfo" name="zxxgInfo">
							<tr style="height:10px">
								<th  width="16%">
									咨询情况
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxztmc}
								</td>
							</tr>
							<tr style="height:40px">
								<th  width="16%">
									咨询评价
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

