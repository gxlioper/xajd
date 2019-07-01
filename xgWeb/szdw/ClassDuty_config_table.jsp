<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/hdzj_report_save" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>思政队伍-班级情况-学生干部职务</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>
			        	<logic:notEmpty name="rs">
							<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="pk"/>" />
			        		填写学生干部职务选项
			        	</logic:notEmpty>	
			        	<logic:empty name="rs">
							有错误发生！
						</logic:empty>
			        		</span>
			        	</th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <logic:notEqual name="userType" value = "xy" scope ="session">
			          		<button type="button" name="提交" onclick="sxjyCommonSave('/xgxt/research_answer_save.do?type=bjgbsz&pk=<bean:write name="rs" property="bjgbdm"/>','bjgbmc-zr','bjgbsz')">保 存</button>
			          </logic:notEqual>
			          </div></td>
			      </tr>
			    </tfoot>
			    <logic:notEmpty name="rs">
				<tbody>
				
					<tr style="height:22px">
						<th align="right">
							<font color="red">*</font>职务名称
						</th>
						<td align="left" >
							<html:text name="rs" property="bjgbmc" style="width: 180px"/>
						</td>
						
					</tr>
						<tr style="height:22px">
						<th align="right">
							<font color="red">*</font>职责内容
						</th>
						<td align="left" >
							<html:text name="rs" property="zr" style="width: 400px"/>
						</td>
						
					</tr>
						<tr style="height:22px">
						<th align="right">
							备注
						</th>
						<td align="left" >
							<html:text name="rs" property="bz" style="width: 400px"/>
						</td>
					</tr>
					</tbody>
					</logic:notEmpty>
				</table>
			
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>