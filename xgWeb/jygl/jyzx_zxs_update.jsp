<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
	
	function jyzxZxsUpdate(){
	 var name = document.getElementById("name").value;
     var email = document.getElementById("email").value;
     var userid = document.getElementById("userid").value;
     var lxdh = document.getElementById("lxdh").value;
     
     var zxsjj = document.getElementById("zxsjj").value;
		if(zxsjj.length>300){
			alert("咨询师简介字数不能超过300个");
			return false;
		}
     if(name==""){
     alert("姓名不能为空！");
     return false;
     }
     if(isNumber(name)){
     alert("姓名不能为数字！");
     return false;
     }
     if(userid==""){
     alert("用户名不能为空！");
     return false;
     }  
     if(lxdh!=""&&!isNumber(lxdh)){
     alert("联系电话应为数字！");
     return false;
     }  
     if((email != null) && (email!= "") && (!isEmail(email))){
     alert("电子邮箱不合法！");
     return false;
     }
		 document.forms[0].action = "/xgxt/jyzxZxsUpdate.do?doType=update";
		 document.forms[0].submit();       
    }
    
    //exclude left and right space; 
	function trim(s){
 		return rtrim(ltrim(s)); 
	}
	//exclude left space; 
	function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	} 
	//exclude right space; 
	function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	}
    
    function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    } 
	
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>咨询师信息修改</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询师信息修改</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="jyzxZxsUpdate();return false;" id="saveButton">
										提 交
									</button>
									<button onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								咨询师编号
							</th>
							<td width="30%">
								<html:text name="rs" property="num" 
									maxlength="3" 
									onkeyup="value=value.replace(/[^\d]/g,'')"
									readonly="true" />
							</td>
							<th width="16%">
								咨询师姓名
							</th>
							<td width="30%">
								<html:text name="rs" property="name" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th>
								咨询师年龄
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<html:text name="rs" property="age" 
											  maxlength="3"
											  onkeyup="checkAge(this)" />
									<div id="ageErrow" class="hide">
							            <p>请输入1-120的数字</p>
							        </div>
							    </div>
							</td>
							<th>
								咨询师性别
							</th>
							<td>
								<html:select name="rs" property="xb">
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								登陆名
							</th>
							<td>
								<html:text name="rs" property="userid" maxlength="10" />
							</td>
							<th>
								联系电话
							</th>
							<td>
								<div class="pos" style="z-index:2">
									<html:text name="rs" property="lxdh"  
										onkeyup="checkPhoneV4(this)"
										maxlength="11"
									/>
									<div id="phoneErrow" class="hide">
											<p>
												电话格式不正确
											</p>
									</div>
								</div>	
							</td>
							<th style="display: none">
								登陆密码
							</th>
							<td style="display: none">
								<html:text name="rs" property="password"  />
							</td>
						</tr>
						<tr>
							<th>
								咨询师资格
							</th>
							<td>
								<html:text name="rs" property="zxszg"  maxlength="15" />
							</td>
							<th>
								电子邮箱
							</th>
							<td>
							<div class="pos" style="z-index:1">
								<html:text name="rs" property="email" 
									maxlength="30"
									onkeyup="checkEmaile(this)"
								/>
								<div id="emaliErrow" class="hide">
										<p>
											邮箱格式不正确
										</p>
								</div>
							</div>
							</td>
						</tr>
						<tr>
							<th>
								咨询师简介
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxsjj" rows="6"
									onblur="checkLen(this,300)"
									style="width:90%" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功!");
                      if (window.dialogArguments) {
							Close();
							dialogArgumentsQueryChick();
					  }
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("修改失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
