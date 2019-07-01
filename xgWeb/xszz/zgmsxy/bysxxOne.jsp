<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
		
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于100个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("学校审核意见不能大于100个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zgmsxy_xszz.do?method=bysshSave');
			$("buttonSave").disabled=true;
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/zgmsxy_xszz.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生资助 - 审核 - 毕业生信息审核 - 单个审核</a>
				</p>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<div class="tab">
			<table width="98%" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>国家助学贷款毕业生个人信息采集表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						学号
					</th>
					<td>
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					
					<th>
						毕业年月
					</th>
					<td>
						<div align="left">
							<bean:write name="rs" property="byny" />
						</div>
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<th>性别
					</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
				</tr>

				<tr>
					<th>
						身份证号
					</th>
					<td>
						<bean:write name="rs" property="sfzh" />
					</td>
					
					<th>
						邮政编码
					</th>
					<td>
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<th>
						家庭地址
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<th>
						父亲姓名
					</th>
					<td>
						<bean:write name="rs" property="fqxm" />
					</td>
					<th>
						父亲联系电话
					</th>
					<td>
						<bean:write name="rs" property="fqdh" />
					</td>
				</tr>
				<tr>
					<th>
						父亲工作单位
					</th>
					<td colspan="3">
						<bean:write name="rs" property="fqgzdw" />
					</td>
				</tr>
				<tr>
					<th>
						母亲姓名
					</th>
					<td>
						<bean:write name="rs" property="mqxm" />
					</td>
					<th>
						母亲联系电话
					</th>
					<td>
						<bean:write name="rs" property="mqdh" />
					</td>
				</tr>
				<tr>
					<th>
						母亲工作单位
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mqgzdw" />
					</td>
				</tr>
				<tr>
					<th>
						首次毕业去向
					</th>
					<td colspan="3">
						<bean:write name="rs" property="brjyqxhdw" />
					</td>
				</tr>
				<tr>
					<th>
						当前工作单位及地址
					</th>
					<td colspan="3">
						<bean:write name="rs" property="dqgzdwjdz" />
					</td>
				</tr>
				<tr>
					<th>
						当前工作单位邮编
					</th>
					<td>
						<bean:write name="rs" property="dqgzdwyb" />
					</td>
					<th>
						当前工作单位电话
					</th>
					<td>
						<bean:write name="rs" property="dqgzdwdh" />
					</td>
				</tr>
				<tr>
					<th>家庭电话</th>
					<td><bean:write name="rs" property="jtgddh" /></td> 
					<th>手机</th>
					<td><bean:write name="rs" property="lxdh" /></td>
				</tr>
				<tr>
					<th>邮箱及QQ号</th>
					<td colspan="3"><bean:write name="rs" property="brdzyxjdzlxfs" /></td>
				</tr>
				<tr>
					<th>
						联系方式变更情况
					</th>
					<td colspan="3">
						<bean:write name="rs" property="lxfsbgqk" />
					</td>
				</tr>

				<logic:equal name="userType" value="xy">
					<tr>
						<th>
							<bean:message key="lable.xb" />审核
						</th>
						<td colspan="3">
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</tr>					
					<tr>
						<th>
							<bean:message key="lable.xb" />审核意见
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xyshyj" rows='3'
								style="width:100%;word-break:break-all;" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value=""/>
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<th>
							<bean:message key="lable.xb" />审核
						</th>
						<td>
							<bean:write name='rs' property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
						<th>
							学校审核
						</th>
						<td>
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					
					<tr>
						<th>
							<bean:message key="lable.xb" />审核意见
						</th>
						<td colspan="3">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<th>
							学校审核意见
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xxshyj" rows='3'
								style="width:100%;word-break:break-all;" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value=""/>
						</td>
					</tr>
				</logic:equal>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			        	<logic:notEqual name="userType" value="admin">
			        	<logic:equal name="rs" property="xxsh" value="未审核">
						<button type="button" onclick="yz()" id="buttonSave">
							保 存
						</button>
					</logic:equal>
					<logic:notEqual name="rs" property="xxsh" value="未审核">
						<button type="button" onclick="yz()" id="buttonSave" disabled="disabled">
							保 存
						</button>
					</logic:notEqual>
			        </logic:notEqual>
			        <logic:equal name="userType" value="admin">
					<button type="button" onclick="yz()" id="buttonSave">
						保 存
					</button>
					</logic:equal>
			            <button type="button" name="关闭" onclick="window.close();return false;" id="buttonClose">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
<!--			<div class="buttontool" align="center">-->
<!--				<logic:notEqual name="userType" value="admin">-->
<!--					<logic:equal name="rs" property="xxsh" value="未审核">-->
<!--						<button type="button" class="button2" onclick="yz()" style="width:80px"-->
<!--							id="buttonSave">-->
<!--							保 存-->
<!--						</button>-->
<!--					</logic:equal>-->
<!--					<logic:notEqual name="rs" property="xxsh" value="未审核">-->
<!--						<button type="button" class="button2" onclick="yz()" style="width:80px"-->
<!--							id="buttonSave" disabled="disabled">-->
<!--							保 存-->
<!--						</button>-->
<!--					</logic:notEqual>-->
<!--				</logic:notEqual>-->
<!--				<logic:equal name="userType" value="admin">-->
<!--					<button type="button" class="button2" onclick="yz()" style="width:80px"-->
<!--						id="buttonSave">-->
<!--						保 存-->
<!--					</button>-->
<!--				</logic:equal>-->
<!--				&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--				<button type="button" class="button2"-->
<!--					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"-->
<!--					style="width:80px" id="buttonClose">-->
<!--					关 闭-->
<!--				</button>-->
<!--			</div>-->
		</html:form>
		<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         		Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
			</logic:present>
	</body>
</html>
