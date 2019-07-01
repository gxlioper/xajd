<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_pcjgcx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 在线测试 - 普测结果查询 - 成绩记录信息</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>
			        		<logic:empty name="rs">
				              未找到任何记录！
				            </logic:empty>
				            <logic:notEmpty name="rs">
			        		成绩记录信息
			        		</logic:notEmpty>
			        		</span></th>
			        </tr>
			    </thead>
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button name="关闭" onclick="Close();return false;"  id="buttonClose">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			<logic:notEmpty name="rs">
				<tbody>
						<tr>
							<th align="right">
								试卷流水号：
							</th>
							<td align="left">
								<html:text name='rs' property="sjlsh" style="width: 120px"
									styleId="sjlsh" readonly="true"/>
							</td>
							<th align="right"></th>
							<td align="left"></td>
						</tr>
						<tr>
							<th align="right">
								学号：
							</th>
							<td align="left">
									<html:text name="rs" property="xh" style="width: 120px"
									styleId="xh" readonly="true"/>
							</td>
							<th align="right">
								姓名：
							</th>
							<td align="left">
								<html:text name='rs' property="xm" style="width: 120px"
									styleId="xm" onblur="" readonly="true"/>
							</td>
						</tr>					
						<tr>
							<th align="right">
								所在班级：
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" style="width: 120px"
									styleId="bjmc" readonly="true"/>
							</td>
							<th align="right">
								所在<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" style="width: 120px"
									styleId="xymc" readonly="true"/>
							</td>
						</tr>			
						<tr>
							<th align="right">
								答题时间：
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="dtsj" style="width:100%"
									styleId="stda" readonly="true"/>
							</td>
						</tr>	
						<tr align="left">
							<th align="right">
								答题用时：
							</th>
							<td align="left">
								<html:text name='rs' property="dtys" style="width: 120px"
									styleId="dtys" readonly="true"/>
							</td>
							<th align="right">
								成绩：
							</th>
							<td colspan="3">
								<html:text name='rs' property="cj" style="width: 120px"
									styleId="cj" readonly="true"/>
							</td>
						</tr>		
						<tr>
							<th align="right">
								备注（老师评语）：
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:95%"
									rows='5' readonly="true"/>
							</td>
						</tr>
					
				</tbody>
			</logic:notEmpty>
			</table>
			</div>
		</html:form>
	</body>
</html>
