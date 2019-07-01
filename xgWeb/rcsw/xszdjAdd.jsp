<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body onload="checkWinType();">		
		<html:form action="/rcsw_xszgl.do" method="post">
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
   					alert("您输入的学号无效!");
   				</script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
			<input type="hidden" id="url" name="url" value="/rcsw_xszgl.do?method=xszdjAdd" />
				
				
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" class="button2"
										onclick="saveData('rcsw_xszgl.do?method=xszdjAdd&type=save','xh')"
										>
										保 存
									</button>
									<button type="button" class="button2" onclick="window.close();return false;">
										关 闭
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>学生证登记信息维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>学号
							</th>
							<td>
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="readonly"
									       styleId="xh" 
									       onkeypress="autoFillStuInfo(event.keyCode,this);" />
								<logic:notEqual value="student" name="userOnLine">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" 
										id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>
								<input type="hidden" id="sqsj" name="save_djsj" value="${rs.save_djsj}" />
							</td>	
							<th>
								年级
							</th>
							<td>
								<html:text name='rs' property="nj" styleId="nj" disabled="true"/>
							</td>						
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<th>
								专业
							</th>
							<td>
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								<html:text name='rs' property="mzmc" styleId="mzmc" disabled="true" />
							</td>
							<th>
								班级
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								政治面貌
							</th>
							<td>
								<html:text name='rs' property="zzmmmc" styleId="zzmmmc" disabled="true" />
							</td>
							<th>
								学制
							</th>
							<td>
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>							
						</tr>
						<tr>
							<th>
								出生日期
							</th>
							<td>
								<html:text name='rs' property="csrq" styleId="csrq" disabled="true" />
							</td>
							<th>
								入学时间
							</th>
							<td>
								<html:text name='rs' property="rxrq" styleId="rxrq" disabled="true" />
							</td>
						</tr>						
						<tr>
							<th>
								身份证号
							</th>
							<td>
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<th>
								手机号码
							</th>
							<td>
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								乘车区间
							</th>
							<td colspan="3">
								<html:text name='rs' property="save_ccqj" styleId="ccqj" style="width:100%"></html:text>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
