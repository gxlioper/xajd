<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="method" name="method"
				value="wspyxjjtSq" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>发展教育-发展教育相关-集体申报项目</a>
				</p>
			</div>
			<logic:empty name="rs">
				<logic:empty name="szbjdm" >
					<div align="center"><font color="red"><bean:write name = "message" /></font></div>
				</logic:empty>
			</logic:empty>
			
			<logic:notEmpty name="szbjdm" >
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
				<ul>
					<li id="003m" class="ha">
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=wspyjtsb')"><span>
						&nbsp;五四评优先进集体&nbsp;</span></a>
					</li>
					<li id="004m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xsjljtsb')" ><span>
						&nbsp;学术奖励集体申报&nbsp;</span></a>
					</li>
				</ul>
  			</div>
  			</div>
  			</logic:notEmpty>
  			
  			<logic:notEmpty name="szbjdm" >
				<div align="center"><font color="red">${message }</font></div>
			</logic:notEmpty>
			
  			<logic:notEmpty name="rs">
			<input type="hidden" id="bjdm" name="bjdm"
				value="<bean:write name='rs' property='bjdm'/>" />
			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>五四评优先进集体</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <button type="button" name="申请" onclick="szsxDataDoSave('szdwfzjy.do?method=saveWspyxjjtsq','xh-sbjx');"  id="buttonSave">申　请</button>
			         </div>
			         </td>    
			      </tr>
			    </tfoot>
				<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>申报奖项：
							</th>
							<td colspan="3">
								<html:text name = "rs" property="sbjx" style="width:99%"  styleId="sbjx"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								班级名称：
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<th align="right">
								年级：
							</th>
							<td align="left">
								<bean:write name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<th align="right">
								专业：
							</th>
							<td align="left">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								春季成绩合格率：
							</th>
							<td align="left">
								<html:text name='rs' property="cjcjhgl" />
							</td>
							<th align="right">
								春季成绩优秀率：
							</th>
							<td align="left">
								<html:text name='rs' property="cjcjyxl" />
							</td>
						</tr>
						<tr>
							<th align="right">
								秋季成绩合格率：
							</th>
							<td align="left">
								<html:text name='rs' property="qjcjhgl " />
							</td>
							<th align="right">
								秋季成绩优秀率：
							</th>
							<td align="left">
								<html:text name='rs' property="cjcjyxl" />
							</td>
						</tr>
						<tr>
							<th align="right">
								英语4级通过率：
							</th>
							<td align="left">
								<html:text name='rs' property="yysjhgl" />
							</td>
							<th align="right">
								英语4级优秀率：
							</th>
							<td align="left">
								<html:text name='rs' property="yysjyxl" />
							</td>
						</tr>
						<tr>
							<th align="right">
								英语6级通过率：
							</th>
							<td align="left">
								<html:text name='rs' property="yyljhgl" />
							</td>
							<th align="right">
								英语6级优秀率：
							</th>
							<td align="left">
								<html:text name='rs' property="yyljyxl" />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								备注：
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="bz" style="width:99%" rows="5" />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("申请失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
