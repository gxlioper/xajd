<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function savegrjl(){   
	var xsxh = document.getElementById("xsxh").value;
	var lxdh = document.getElementById("lxdh").value;
	var yzbm = document.getElementById("yzbm").value;
	var email = document.getElementById("email").value;
	var lxdz =document.getElementById("lxdz").value;

	var hjqk = document.getElementById("hjqk").value;
	var xxqk = document.getElementById("xxqk").value;
	var xjysjl = document.getElementById("xjysjl").value;
	var shsjqk = document.getElementById("shsjqk").value;
	var gzjl =document.getElementById("gzjl").value;
	var grtc = document.getElementById("grtc").value;
	var zwtj =document.getElementById("zwtj").value;
	
	if(xsxh==""){
	alert("学号不能为空！");
	return false;
	}
	if(lxdz!=""&&lxdz.length>25){
	alert("联系地址长度过大，请简略！");
	return false;
	}
	
	if(lxdh.length<7&&lxdh!=""){
	alert("电话号码长度不合要求！");
	return false;
	}
	if(lxdh.length>13&&lxdh!=""){
	alert("电话号码长度不合要求！");
	return false;
	}
	if(!isNumber(lxdh)&&lxdh!=""){
	alert("电话号码应为数字！");
	return false;
	}
	if(yzbm.length!=6&&yzbm!=""){
	alert("邮政编码长度应为6位");
	return false;
	}
	if(!isNumber(yzbm)&&yzbm!=""){
	alert("邮政编码应为数字！");
	return false;
	}
	if(!isEmail(email)&&email!=""){
	alert("电子邮箱不合法！");
	return false;
	}	
	if(lxdz==""&&lxdh==""&&email==""){
	alert("请至少填写一个联系方式！");
	return false;
	}
	if(hjqk.length>1200&&hjqk!=""){
		alert("获奖情况不能超过1200个汉字");
		return false;
		}
	if(xxqk.length>1200&&xxqk!=""){
		alert("学习情况不能超过1200个汉字");
		return false;
		}
	if(xjysjl.length>1200&&xjysjl!=""){
		alert("校级以上奖励不能超过1200个汉字");
		return false;
		}
	if(shsjqk.length>1200&&shsjqk!=""){
		alert("社会实践情况不能超过1200个汉字");
		return false;
		}
	if(gzjl.length>1200&&gzjl!=""){
		alert("工作经历不能超过1200个汉字");
		return false;
		}
	if(grtc.length>1200&&grtc!=""){
		alert("个人特长不能超过1200个汉字");
		return false;
		}
	if(zwtj.length>1200&&zwtj!=""){
		alert("自我推荐不能超过1200个汉字");
		return false;
		}
		 	document.forms[0].action = "/xgxt/savegrjl.do?doType=save";
		 	document.forms[0].submit();
		 	document.getElementById('tjan').disabled=true;
	}
	
	function returntobegin(){   
		 	document.forms[0].action = "/xgxt/savegrjl.do";
		 	document.forms[0].submit();
	}
	
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
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
	</script>
	</head>
<%--	<logic:equal value="teacher" name="userOnLine">--%>
<%--		<script language="javascript">--%>
<%--			alert('该页面为学生填写页面！');--%>
<%--		</script>--%>
<%--	</logic:equal>--%>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 学生个人简历 - 个人简历登记</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" class="formlist" id="grjl">
				<thead>
					<tr><th colspan="5"><span>个人资料</span></th></tr>
				</thead>
			<tbody>
				<tr>
				<td colspan="4">
				<logic:notEqual value="12061" name="xxdm" scope="session">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证号
					<html:text name="rs" property="id" readonly="true" />
					<html:checkbox name="rs" property="idsee" value="no" />(保密) 
				</logic:notEqual>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>学号
					<html:text name='rs' property="xsxh" styleId="xsxh"
						style="width:150px"
						readonly="true" />
					<button onclick="showTopWin('/xgxt/grjlTurnInfo.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<font color="red">*</font>学号
				<html:text property="xsxh" name="rs" styleId="xsxh"
						readonly="true" style="width:150px" />
				</logic:equal>
				</td>
				<td rowspan="6" align="center" width=20%">
					照片
				</td>
				</tr>
				<tr>
					<th width=15%">
						姓名
					</th>
					<td width=25%">
						<html:text name="rs" property="name" style="100%" readonly="true" />
					</td>
					<th width=15%">
						性别
					</th>
					<td width=25%">
						<html:text name="rs" property="xb" style="100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						出生年月
					</th>
					<td>
						<html:text name="rs" property="csny" style="100%" readonly="true" />
					</td>
					<th>
						民族
					</th>
					<td>
						<html:text name="rs" property="mz" style="100%" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th>
						学历
					</th>
					<td>
						<html:text name="rs" property="xl" style="100%" maxlength="10"/>
					</td>
					<th>
						政治面貌
					</th>
					<td>
						<html:select name="rs" property="zzmm" style="width:150px">
							<html:option value="无党派民主人士">无党派民主人士</html:option>
							<html:options collection="zzmmList" property="zzmm"
								labelProperty="zzmm" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						专业名称
					</th>
					<td>
						<html:text name="rs" property="zymc" style="100%" readonly="true" />
					</td>
					<th>
						辅修专业
					</th>
					<td>
						<html:text name="rs" property="fxzymc" style="100%" />
					</td>
				</tr>
				
				<tr>
					<th>
						生源地区
					</th>
					<td>
						<html:text name="rs" property="sydq" style="100%" readonly="true" />
					</td>
					<th></th>
					<td></td>
				</tr>
			</tbody>
		</table>
		<br/>
		
		<table width="100%" class="formlist" id="">
			<thead>
				<tr><th colspan="5"><span>联系方式</span></th></tr>	
			</thead>
			<tbody>
				<tr>
					<th width=15%">
						联系地址
					</th>
					<td width=25%">
						<html:text name="rs" property="lxdz" maxlength="150"/>
					</td>
					<th width=15%"> 
						联系电话
					</th>
					<td colspan="2" width=45%">
						<html:text name="rs" property="lxdh" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<th>
						邮政编码
					</th>
					<td>
						<html:text name="rs" property="yzbm" style="width=100%" maxlength="6"/>
					</td>
					<th>
						电子邮箱
					</th>
					<td colspan="2">
						<html:text name="rs" property="email" style="width=100%" maxlength="25"/>
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<table width="100%" class="formlist" id="">
			<thead>
				<tr><th colspan="5"><span>学生综合情况</span></th></tr>
			</thead>
			<tbody>
				<tr>
					<th width="15%">
						获奖情况
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="hjqk" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th width="15%">
						学习情况
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="xxqk" rows="4"
							style="width: 95%;word-break:break-all;" />
					</td>
				</tr>
				<tr>
					<th>
						校级以 上奖励
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="xjysjl" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						社会实 践情况
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="shsjqk" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						工作经历
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="gzjl" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						个人特长
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="grtc" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						自我推荐
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="zwtj" rows="6"
							style="width: 95%;word-break:break-all;" />
					</td>
				</tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<tr>
						<th>
							学校推荐
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xxtj" rows="6"
								style="width: 95%;word-break:break-all;" />
						</td>
					</tr>
				</logic:equal>
				</tbody>
			</table>
			<br/>
	
			<table width="100%" class="formlist" id="printcj">
				<thead><tr><th colspan="4"><span>学生成绩表</span></th></tr></thead>
				<logic:notEqual value="10338" name="xxdm" scope="session">
					<tr>
						<th>
							科目名称
						</th>
						<th>
							分数
						</th>
						<th>
							科目名称
						</th>
						<th>
							分数
						</th>
					</tr>
					<tr>
						<td align="center">
							科目1
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目13
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目2
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目14
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目3
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目15
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目4
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目16
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目5
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目17
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目6
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目18
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目7
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目19
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目8
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目20
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目9
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目21
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目10
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目22
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目11
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目23
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							科目12
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							科目24
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal value="10338" name="xxdm" scope="session">
						<logic:empty name="xscjList">
							没有考试科目
						</logic:empty>
						<logic:notEmpty name="xscjList">
							<logic:iterate id="xscjs1" name="xscjList">
								<tr>
								<logic:iterate id="xscjs" name="xscjs1">
								<td align="center">
								<logic:iterate id="xscj" name="xscjs" offset="0" length="1">
									<bean:write name="xscj"/>
								</logic:iterate>
								</td>
								<td align="center">
								<logic:iterate id="xscj" name="xscjs" offset="1" length="2">
									<bean:write name="xscj"/>
								</logic:iterate>
								</td>
								</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</logic:equal>
					<tfoot>
						<tr>
							<td align="center" colspan="4">
								<div class="btn">
									<button class="button2" onclick="savegrjl()" 
										id="tjan" style="width:80px">
										提 交
									</button>
									<button class="button2" onclick="returntobegin()" type="reset"
										style="width:80px">
										重 置
									</button>
									<button class="button2" onclick="expAppTab('grjl','个人简历','')"
										style="width:80px">
										打 印
									</button>
									<logic:equal value="10338" name="xxdm" scope="session">
									<button class="button2" onclick="expAppTab('printcj','个人简历','')"
										style="width:80px">
										打印成绩
									</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
                        alert("提交成功！");
                   </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
                        alert("提交失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="exists">
				<logic:equal name="exists" value="exists">
					<script>
    alert("学校审核尚未通过，请等待学校审核通过以后再来申请！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>

	</body>
</html>
