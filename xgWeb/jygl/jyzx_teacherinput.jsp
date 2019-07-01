<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 职业咨询 - 咨询教师登记</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询师登记表</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="提交" 
											id="saveButton"
											onclick="saveUpdate('/xgxt/jyzx_xszxyy_save.do?act=save','num-name-zxszg-userid-lxdh')">
										提 交
									</button>
									<button name="重置" type="reset">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
							<font color="red">*</font>咨询师编号
							</th>
							<td width="34%">
								<html:text name="rs" property="num" maxlength="3" 
									onkeyup="value=value.replace(/[^\d]/g,'')"
									style="width=70%" />
							</td>
							<th width="16%">
								<font color="red">*</font>咨询师姓名
							</th>
							<td width="34%">
								<html:text name="rs" property="name" maxlength="10"
									style="width=55%" />
							</td>
						</tr>
						<tr>
							<th>
								咨询师年龄
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<html:text name="rs" property="age" maxlength="3"
										  onblur="checkAge(this)"/>
									<div id="ageErrow" class="hide">
						                <p>请输入1-120的数字</p>
						            </div>
						        </div>
							</td>
							<th>
								咨询师性别
							</th>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="xb" value="男" checked="checked" />
								&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="xb" value="女" />
								&nbsp;&nbsp;女
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>用户名
							</th>
							<td>
								<html:text name="rs" property="userid" maxlength="10"
									/>
							</td>
							<th>
								<font color="red">*</font>联系电话
							</th>
							<td>
							<div class="pos" style="z-index:2">
								<html:text name="rs" property="lxdh" 
									onblur="checkPhoneV4(this)"
									maxlength="11" />
								<div id="phoneErrow" class="hide">
										<p>
											电话格式不正确
										</p>
								</div>	
							</div>
								
								<html:password name="rs" property="password" maxlength="12" style="display:none"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>咨询师资格
							</th>
							<td>
								<html:text name="rs" property="zxszg"  maxlength="15"/>
							</td>
							<th>
								电子邮箱
							</th>
							<td>
							<div class="pos" style="z-index:1">
								<html:text name="rs" property="email" 
									maxlength="30"
									onblur="checkEmaile(this)"/>
								<div id="emaliErrow" class="hide">
										<p>
											邮箱格式不正确
										</p>
								</div>	
							 </div>
								<html:password name="rs" property="password2" maxlength="12" style="display:none"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询师简介
								<br/>
								<font color="red"><限300字></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxsjj" rows="5" 
									onblur="checkLen(this,300)"
									style="width:100%" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("提交成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

