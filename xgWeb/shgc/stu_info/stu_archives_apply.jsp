<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript">
		function printLjsApp(url){
		   document.forms[0].action = url;
		   document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
		}
		
		/**
		 * 替换特殊字符
		 * */
		function replaceStr(obj){
			if(obj){
				var value = obj.value;
				obj.value = value.replace('+','＋');
				value = obj.value;
				obj.value = value.replace('%','％');
				value = obj.value;
				obj.value = value.replace('#','＃');
				value = obj.value;
				obj.value = value.replace('&','＆');
				//ele(obj.id).value = value.replace('','');		
			}
		}
	</script>
	<%@include file="/comm/other/backgroud.jsp" %>
</head>
<body>
	<html:form action="/stu_archives_apply" method="post">
		<input type="hidden" name="url" id="url" value="/shgc/stu_info/stu_archives_apply.jsp"/>
		<input type="hidden" value="xh-xm-xb-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息-转档管理-转档申请</a>
			</p>
		</div>
		
		<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4"><span>历届学生转档申请</span></th>
					</tr>
				</thead>						
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th width="20%"><span class="red">*</span>学号</th>
						<td width="30%">
							<html:text name="rs" property="xh" styleId="xh"
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button class="btn_01"
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								id="buttonFindStu">
								选择
							</button>							
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th  width="20%"><span class="red">*</span>学号</th>
						<td  width="30%">
							<input type="text" id="xh" name="xh" value="<bean:write name="userName"/>" readonly="readonly" />
						</td>
					</logic:equal>
					<th  width="20%"><bean:message key="lable.xsgzyxpzxy" /></th>
					<td  width="30%">	
						${rs.xymc}							
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm}
					</td>							
					<th>专业</th>
					<td>
						${rs.zymc}
					</td>
				</tr>
				<tr>	
					<th>性别</th>
					<td>
						${rs.xb}
					</td>
					<th>班级</th>
					<td>
						${rs.bjmc}
					</td>
				</tr>						
				<tr>
					<th><span class="red">*</span>户口所属区县</th>
					<td>
						<html:text property="hkssqx" styleId="hkssqx" onkeyup="replaceStr(this)" maxlength="20" />
					</td>
					<th><span class="red">*</span>户口所属街道</th>
					<td>
						<html:text  property="hkssjd" styleId="hkssjd" onkeyup="replaceStr(this)" maxlength="25"></html:text>
					</td>
				</tr>	
				<tr>
					<th><span class="red">*</span>户口详细地址</th>
					<td>
						<html:text  property="hkxxdz" styleId="hkxxdz" onkeyup="replaceStr(this)" maxlength="40"/>
					</td>
					<th>电话或其他联系方式</th>
					<td>
						<html:text  property="lxfs" styleId="lxfs" onkeyup="replaceStr(this)" onkeypress="return onlyNum(this,25)"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>档案转往地的单位详细地址</th>
					<td>
						<html:text  property="zddwdz" styleId="zddwdz" onkeyup="replaceStr(this)" maxlength="50"/>
					</td>
					<th>档案转往地邮编</th>
					<td>
						<html:text property="zddwyb" styleId="zddwyb" onkeypress="return onlyNum(this,10)"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>档案转往地的单位名称(全称)</th>
					<td>
						<html:text  property="zddwmc" styleId="zddwmc" onkeyup="replaceStr(this)" maxlength="50"/>
					</td>
					<th></th>
					<td></td>
				</tr>
				
				<tr>
					<th><span class="red">*</span>申请理由</th>
					<td colspan="3">
						<html:textarea  property="sqly" cols="80" rows="4" style="width: 95%;word-break:break-all;" onkeyup="replaceStr(this)" onblur="chLeng(this,150)" styleId="sqly"/>
					</td>
				</tr>	
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button class="button2"
							onclick="commSave('/xgxt/stu_archives_apply.do?doType=save',
							'xh-sqly-zddwmc-zddwdz-hkssqx-hkssjd-hkxxdz')">
							提 交
						</button>
						<button class="button2" onclick="printLjsApp('stu_info_add.do?method=printLjsApp')">
							打 印
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>					
			</table>
		</div>
			<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("申请成功！");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("申请失败！");
				</script>
			</logic:equal>
			</logic:notEmpty>
			<logic:equal value="no" name="isLjs">
			<script>
				alert("只有毕业两年以上的学生才可操作，你不是毕业两年以上的学生！");
			</script>				
			</logic:equal>
	</html:form>
</body>
</html>
