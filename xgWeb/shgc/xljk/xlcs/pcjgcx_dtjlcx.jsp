<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
		function dtjlb_tj_search(){
		    allNotEmpThenGo('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=dtjlb_tj_search');
		}
	</script>
	</head>
	<body>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_pcjgcx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>
			
			
		<div class="toolbox">
			  <div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th align="left">
								试卷名
							</th>
							<td>
								<html:select property="sjlsh" style="width:120px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sjList" property="SJLSH"
										labelProperty="SJM" />
								</html:select>
							</td>
							<th>
								学 号
							</th>
							<td>
								<html:text  property="xh" styleId="xh"
									onkeypress="" readonly="true" style="width:90px"/>
							</td>
							<th >
								日 期
							</th>
							<td>
								<html:text property="dtsj" styleId="dtsj"
									readonly="true" style="width:180px"/>
							</td>
						</tr>
						<tr>
							<th>
								试卷流水号
							</th>
							<td>
								<html:text property="sjlsh" style="width:100px" readonly="true"/>
							</td>
							<th >
								成 绩(分值)
							</th>
							<td>
								<html:text property="cj" style="width:100px" readonly="true"/>
							</td>
							<td colspan="2">
						</tr>					
					</tbody>
				</table>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	当前记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			<logic:notEmpty name="rs">
					  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick=""
							style="cursor:hand">
								<td >
									<input type="hidden" id="XN_ID" name="XN_ID"
										value="<bean:write name="s" property="XN_ID"/>" />
									<bean:write name="s" property="STXH"/>
								</td>
								<td>
									<bean:write name="s" property="SJLSH"/>
								</td>
								<td>
									<bean:write name="s" property="STLSH"/>
								</td>
								<td>
									<bean:write name="s" property="DTDA"/>
								</td>
								<td>
									<bean:write name="s" property="DTFZ"/>
								</td>
								<td>
									<bean:write name="s" property="SSLXMC"/>
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
				<div class="buttontool" id="btn" align="right">
					<button  onclick="Close();return false;" >
						关&nbsp;&nbsp;闭
					</button>
				</div>
		</html:form>
	</body>
</html>
