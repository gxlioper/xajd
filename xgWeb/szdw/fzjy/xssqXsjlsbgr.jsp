<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body >
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<input type="hidden" id="method" name="method"
				value="xsjlsbgrSq" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>发展教育-发展教育相关-学生申报项目</a>
				</p>
			</div>
			<logic:empty name="rs">
				<!-- 非学生 -->
				<logic:notEqual name="userType" value="stu">
					<div align="center"><font color="red"><bean:write name = "message" /></font></div>
				</logic:notEqual>
			</logic:empty>
			<logic:equal name="userType" value="stu">
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
				<ul>
					<li id="001m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqYcjy')"><span>
						&nbsp;英才工程申请&nbsp;</span></a>
					</li>
					<li id="002m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqJzfdy')" id=""><span>
						&nbsp;兼职辅导员&nbsp;</span></a>
					</li>
					<li id="003m">
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqWspyxjgr')"><span>
						&nbsp;五四评优先进个人&nbsp;</span></a>
					</li>
					<li id="004m" class="ha">
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xssqXsjlsbgr')"><span>
						&nbsp;学术奖励申报&nbsp;	</span></a>
					</li>
				</ul>
  				</div>
  			</div>
  			</logic:equal>
  			<logic:empty name="rs">
				<!-- 非学生 -->
				<logic:equal name="userType" value="stu">
					<div align="center"><font color="red"><bean:write name = "message" /></font></div>
				</logic:equal>
			</logic:empty>
  			<logic:notEmpty name="rs">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name='rs' property='xh' />" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>学术奖励申报</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <button type="button" name="提交"onclick="szsxDataDoSave('szdwfzjy.do?method=saveXsjlsbgrsq','xh-sbjhmc');"  id="buttonSave">申　请</button>
			         </div>
			         </td>    
			      </tr>
			    </tfoot>
				<tbody>
						<tr>
							<th align="right">
								申报学术科技成果名称:
							</th>
							<td colspan="3">
								<html:text name = "rs" property="sbxskjcgmc" style="width:99%"  styleId="sbxskjcgmc"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>学号：
							</th>
							<td align="left">
								<bean:write name='rs' property="xh" />
							</td>
							<th align="right">
								姓名：
							</th>
							<td align="left">
								<bean:write name='rs' property="xm"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								性别：
							</th>
							<td align="left">
								<bean:write name='rs' property="xb"/>
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
								班级：
							</th>
							<td align="left">
								<bean:write name='rs' property="bjmc"/>
							</td>
							<th align="right">
								申报学术科技成果类型:
							</th>
							<td align="left">
									<html:select name = "rs" property="sbxskjcglxdm" styleId="sbxskjcglxdm">
										<html:option value=""></html:option>
										<html:options collection="sbxskjcglxList" property="sbxskjcglxdm"
										labelProperty="sbxskjcglxmc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								成果完成时间：
							</th>
							<td align="left">
								<html:text name= "rs"  property="cgwcsj" styleId="cgwcsj" readonly = "true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cgwcsj','y-mm-dd');" />
							</td>
							<th align="right">
								成果级别:
							</th>
							<td align="left">
								<html:select name = "rs" property="cgjb" styleId="cgjb">
										<html:option value=""></html:option>
										<html:options collection="cgjbList" property="cgjbdm"
										labelProperty="cgjbmc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								学生科研成果价值说明：
							</th>
							<td colspan="3">
								<html:textarea name = "rs" property="xskycgjzsm" style="width:99%"  rows="5"/>
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
    dialogArgumentsQueryChick();
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
