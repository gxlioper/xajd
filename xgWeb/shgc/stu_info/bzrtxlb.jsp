<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
	<body>
		<html:form action="/address_book">
			<input type="hidden" value="xh-xm-zddwmc-zddwdz-zdsj" id="notnull" name="notnull" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 通讯录 - 班主任通讯录信息维护</a>
				</p>
			</div>

			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>班主任通讯信息</span>
						</th>
					</tr>
				</thead>	
				<tbody>	
					<tr>
						<th>年级</th>
						<td>
							<html:select property="nj" style="width:180px" onchange="initZyList();initBjList();" name="rs">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>					
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:180px" name="rs"
								onchange="initZyList();initBjList();" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>	
						</td>		
					</tr>
					<tr>
						<th>专业</th>
						<td>
							<html:select property="zydm" style="width:180px" styleId="zy" name="rs"
								onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>	
						<th><span class="red">*</span>班级名称</th>
						<td>
							<html:select name="rs" property="bjdm" styleId="bj"
								style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>	
					</tr>
					<tr>					
						<th><span class="red">*</span>姓名</th>
						<td>
							<html:text name="rs" property="xm" styleId="xm"/>
						</td>
					
						<th>手机号码</th>
						<td>
							<html:text name="rs" property="sjhm" styleId="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
						</td>	
					</tr>
					<tr>					
						<th>单位电话</th>
						<td>
							<html:text name="rs" property="dwdh" styleId="dwdh" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
						</td>
					
						<th>家庭电话</th>
						<td>
							<html:text property="jtdh" name="rs" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"></html:text>
						</td>	
					</tr>						
					<tr>					
						<th>其他电话</th>
						<td>
							<html:text property="qtdh" name="rs" styleId="qtdh" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
						</td>						
					
						<th>电子邮箱</th>
						<td>
							<div class="pos" style="z-index:1">
					        	<html:text property="email" name="rs" styleId="email"
					        	onkeypress="checkEmaile(this)" 
					        	onblur="checkEmaile(this)"
					        	maxlength="30" value="${rs.email}"></html:text>
					       		<div id="emaliErrow" class="hide" >
							        <p>邮箱格式不正确</p>
							    </div>
					        </div>
						</td>	
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3" style="word-break:break-all;">
							<html:textarea property="bz"  name="rs" styleId="bz" style="width:90%" rows="3"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button
							onclick="commSave('/xgxt/address_book.do?doType=save','bjdm-xm')" id="saveButton"
							>
							保 存
						</button>
						<button onclick="window.close();return false;">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>			
			</table>
		
			<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
				window.close();				 	
			 	dialogArgumentsQueryChick();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>
			 	alert("操作失败!");
			</script>
			</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
