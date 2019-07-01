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
				value="wspyxjgrSq" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>发展教育-发展教育相关-学术奖励集体申报</a>
				</p>
			</div>
			<logic:empty name="rs">
				<logic:empty name="szbjdm" >
				<div align="center"><font color="red">${message}</font></div>
				</logic:empty>
			</logic:empty>
			
			<logic:notEmpty name="szbjdm" >
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
				<ul>
					<li id="003m" >
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=wspyjtsb')"><span>
						&nbsp;五四评优先进集体&nbsp;</span></a>
					</li>
					<li id="004m" class="ha">
						<a href="#" onclick="refreshForm('szdwfzjy.do?method=xsjljtsb')" ><span>
						&nbsp;学术奖励集体申报&nbsp;</span></a>
					</li>
				</ul>
  			</div>
  			</div>
  			</logic:notEmpty>
  			<logic:notEmpty name="szbjdm" >
				<div align="center"><font color="red">${message}</font></div>
			</logic:notEmpty>
  			<logic:notEmpty name="rs">
  			
			<input type="hidden" id="bjdm" name="bjdm"
				value="<bean:write name='rs' property='bjdm'/>" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="8"><span>学术奖励集体申报</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="8"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <button type="button" name="申请" onclick="szsxDataDoSave('szdwfzjy.do?method=saveXsjlsbjtsq','');"  id="buttonSave">申　请</button>
			         </div>
			         </td>    
			      </tr>
			    </tfoot>
				<tbody>
						<tr>
							<th align="right"   colspan="2" >
								班级名称：
							</th>
							<td align="left" colspan="2" >
								<bean:write name='rs' property="bjmc" />
							</td>
							<th align="right" colspan="2" >
								年级：
							</th>
							<td align="left" colspan="2" >
								<bean:write  name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left" colspan="2">
								<bean:write name='rs' property="xymc" />
							</td>
							<th align="right" colspan="2">
								专业：
							</th>
							<td align="left" colspan="2">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2">
								班级人数：
							</th>
							<td align="left" colspan="2">
								<html:text name='rs' property="bjrs" readonly = "true"/>
							</td>
							<th align="right" colspan="2">
								 班长：
							</th>
							<td align="left" colspan="2">
								<html:text name='rs' property="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2" >
								四六级通过人数：
							</th>
							<td align="left" colspan="2" >
								<html:text name='rs' property="sljtgrs" />
							</td>
							<th align="right" colspan="2">
								学期总学分：
							</th>
							<td align="left" colspan="2">
								<html:text name='rs' property="xqzxf" />
							</td>
						</tr>
						<tr>
							<th align="right" colspan="2" rowspan="6">
								一学期各课程成绩统计情况：
							</th>
							<th   colspan="2">
								<div align="center">
									课程名称及学分
								</div>
							</th>
							<th >
								<div align="center">
								最高分
								</div>
							</th>
							<th >
								<div align="center">
								最低分
								</div>
							</th>
							<th >
								<div align="center">
								平均分
								</div>
							</th>
							<th >
								<div align="center">
								优秀率
								</div>
							</th>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc1" />
							</td>
							<td align="left">
								<html:text name='rs' property="zgf1" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf1" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf1" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl1" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc2" />
							</td>
							<td align="left">
								<html:text name='rs' property="zgf2" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf2" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf2" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl2" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc3"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zgf3" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf3" style="width:80px" />
							</td>
							<td align="left">
								<html:text name='rs' property="pjf3" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl3" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc4"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zgf4" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf4" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf4" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl4" style="width:80px"/>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								<html:text name='rs' property="kcmc5" />
							</td>
							<td align="left">
								<html:text name='rs' property="zgf5" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="zdf5" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="pjf5" style="width:80px"/>
							</td>
							<td align="left">
								<html:text name='rs' property="yxl5" style="width:80px"/>
							</td>
						</tr>
						<tr align="left">
							<th align="right" colspan="2" >
								班级情况及申请说明：
							</th>
							<td colspan="6" >
								<html:textarea name = "rs" property="bjjjsqsm" style="width:99%" rows="5" />
							</td>
						</tr>
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
