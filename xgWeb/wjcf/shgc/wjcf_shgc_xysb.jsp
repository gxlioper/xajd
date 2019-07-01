<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>

		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/shgcwjcfwh.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>违纪处分 - <bean:message key="lable.xsgzyxpzxy" />申报 - 学生处分申报</a>
				</p>
			</div>
		
			<%--<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			--%><logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    return;
    </script>
				</logic:equal>
				
				<input type="hidden" name="sbsj" id="sbsj" value="${sbsj }"/>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/shgcxysbqrystu.do" />
				<div class="tab">
			    <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>填写申请表</span></th>
			        </tr>
			    </thead>
				<tfoot>
					 <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          <logic:notEqual name="doType" value="view">
				          		<button type="button" name="提 交" onclick="dataSave_shgc('/xgxt/shgcxysbdatasub.do')">提 交</button>
				          </logic:notEqual>
				            <button type="button" name="打 印" onclick="wjcfysbprint('wjcfysbprint.do?xh=')">打 印</button>
				          </div></td>
				      </tr>
				</tfoot>
				<tbody>
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th >
								<font color="red">*</font>学号
							</th>
							<td >
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="checkXhExists('cflb-cfyy-xq-wjjtsy-zacfqk-qtcfqk-xyyj-xxyj-bzryj-xgcyj-bz');showTopWin('/xgxt/stu_info.do',750,550);"
									id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th>
								<font color="red">*</font>学号
							</th>
							<td ><html:hidden name='rs' property="xh" styleId="xh" />
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<th >
							年度
						</th>
						<td align="left">
							<bean:write name='rs' property="nd" />
							<input type="hidden" name="nd" id="nd" value="<bean:write name='rs' property="nd" />" />
						</td>
					</tr>
					<tr >
						<th align="right">
							姓名
						</th>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<th align="right">
							学年
						</th>
						<td align="left">
							<bean:write name='rs' property="xn" />
							<input type="hidden" name="xn" id="xn" value="<bean:write name='rs' property="xn" />" />
						</td>
					</tr>
					<tr >
						<th align="right">
							性别
						</th>
						<td >
							<bean:write name='rs' property="xb" />
						</td>
						<th >
							<font color="red">*</font>处分类别
						</th>
						<td>
							<html:select property="cflb" style="width:150px"
								styleId="cflb">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm"
									labelProperty="cflbmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							年级
						</th>
						<td>
							<bean:write name='rs' property="nj" />
						</td>
						<th>
						<font color="red">*</font>处分事由
						</th>
						<td>
						<html:select property="cfyy" style="width:150px"
								styleId="cfyy">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="cfyydm"
									labelProperty="cfyymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />：
						</th>
						<td>
							<bean:write name='rs' property="xymc" />
						</td>
						<th><font color="red">*</font>学期</th>
						<td >
							<html:select property="xq" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
						</td>
					</tr>
					<tr >
						<th align="right">
							专业
						</th>
						<td >
							<bean:write name='rs' property="zymc" />
						</td>
						<td >&nbsp;	
						</td>
						<td >&nbsp;
						</td>
					</tr>
					<tr >
						<th align="right">
							班级
						</th>
						<td >
							<bean:write name='rs' property="bjmc" />
						</td>
						<td >&nbsp;</td><td align="left">&nbsp;</td>
					</tr>
					<tr >
						<th >
						<font color="red">*</font>具体违纪事由<br><font color="red">(字数在1000字以内)</font>
						</th>
						<td colspan="3" align="left"><html:textarea rows="3"  style="width:95%;inputtext;" property="jtwjsy" styleId="wjjtsy" onblur="chLeng(this,1000)" />
						</td>
					</tr>
					<tr >
						<th >
						<font color="red">*</font>治安处罚情况<br><font color="red">(字数在1000字以内)</font>
						</th>
						<td colspan="3" align="left"><html:textarea rows="3"  style="width:95%;inputtext;" property="zacfqk" styleId="zacfqk" onblur="chLeng(this,1000)" />
						</td>
					</tr>
					<tr>
						<th>
						<font color="red">*</font>其它处罚情况<br><font color="red">(字数在1000字以内)</font>
						</th>
						<td colspan="3" align="left"><html:textarea rows="3"  style="width:95%;inputtext;" property="qtcfqk" styleId="qtcfqk"  onblur="chLeng(this,1000)"/>
						</td>
					</tr>
					<logic:notEqual name="xxdm" value="11355">
					<tr>
						<th >
							<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />意见(备注)
						</th>
						<td colspan="3" align="left"><html:textarea rows="5" name='rs' style="width:95%;inputtext" property="bz" styleId="bz" />
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11355">
						<logic:equal name="isFdy" value="true">
						<tr>
							<th align="right"><font color="red">*</font>辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td align="left" colspan="3">
								<html:textarea  property="bzryj" styleId="bzryj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="isFdy" value="true">
						<logic:equal name="userType" value="xy">
						<tr>
							<th align="right"><font color="red">*</font>辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td align="left" colspan="3">
								<html:textarea  property="bzryj" styleId="bzryj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right"><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />、系处理依据&nbsp;&nbsp;<br/>及处理意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td align="left" colspan="3">
								<html:textarea  property="xyyj" styleId="xyyj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						</logic:equal>
						</logic:notEqual>
						<logic:equal name="userType" value="xx">
						<tr>
							<th align="right"><font color="red">*</font>辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td align="left" colspan="3">
								<html:textarea  property="bzryj" styleId="bzryj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th ><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />、系处理依据&nbsp;&nbsp;<br/>及处理意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td  colspan="3">
								<html:textarea  property="xyyj" styleId="xyyj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th ><font color="red">*</font>学校意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td  colspan="3">
								<html:textarea  property="xgcyj" styleId="xgcyj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="userType" value="admin">
						<tr>
							<th ><font color="red">*</font>辅导员意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td  colspan="3">
								<html:textarea  property="bzryj" styleId="bzryj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th ><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />、系处理依据&nbsp;&nbsp;<br/>及处理意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td  colspan="3">
								<html:textarea  property="xyyj" styleId="xyyj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th ><font color="red">*</font>学校意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><font color="red">(字数在100字以内)</font></th>
							<td colspan="3">
								<html:textarea  property="xgcyj" styleId="xgcyj" rows="6" style="width:95%;" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
						</logic:equal>
						<tr>
						<th ><font color="red">*</font>备注<br/><font color="red">(字数在100字以内)</font></th>
						<td  colspan="3">
							<html:textarea  property="bz" styleId="bz" rows="5" style="width:95%;inputtext" onblur="chLeng(this,100)">
							</html:textarea>
						</td>
						</tr>
					</logic:equal>
					</tbody>
				</table>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("申请成功！");
    </script>
	</logic:equal>
	<logic:equal name="inserted" value="no">
	<script>
    alert("申请失败！");
    </script>
	</logic:equal>
			</logic:notEmpty>
			<script>
				function wjcfysbprint(url){
					var xh = document.getElementById('xh').value;
					var cflb = document.getElementById('cflb').value;
					var cfyy = document.getElementById('cfyy').value;
					var sbsj = document.getElementById('sbsj').value;
					var xn = document.getElementById('xn').value;
					var nd = document.getElementById('nd').value;
					var pkVal = xh+xn+nd+sbsj;
					url += xh;
					url += '&cflb=';
					url += cflb;
					url += '&cfyy=';
					url += cfyy;
					url += '&pkVal=';
					url += pkVal;
					window.open(url);
			}
			</script>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("您有未通过审核的申报!\n请在未通过审核信息中查看，并重新申报");
			</script>
		</logic:equal>
	</body>
</html>
