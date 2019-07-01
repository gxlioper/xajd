<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 头文件 -->
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxZgkd.js'></script>
	<script type='text/javascript' src='js/check.js'></script>	
	<script language="javascript" src="js/xgutil.js"></script>
	
	<script>
		function zgdzdxSaveXsxx(){
			var sfzc = document.getElementById('sfzc').value;
			var nfby = document.getElementById('nfby').value;
			var sfzcV = document.getElementById('sfzcV').value;
			var nfbyV = document.getElementById('nfbyV').value;
			var xh = document.getElementById('xh').value;
			var flag = true;	
			//判断必须填写字段是否填写
			//增加时判断学生信息是否已经存在
			var result = true;
			dwr.engine.setAsync(false);
			var yhjs = val('yhjs');
			if(yhjs == "stu"){
				yhjs = "student";
			}			
			xsxxZgkd.getBtzdByYh(yhjs,"xsxxb",function(data){
				if(data != null){
					for(var i=0; i<data.length; i++){
						if(ele(data[i].en) && ele(data[i].en).value == ""){
							if(data[i].en == "syd"){
								alert("生源地或来源地区不能为空！");
							}else{
								alert(data[i].cn + "不能为空！");								
							}
							result = false;
							break;
						}
					}
				}				
			});
			xsxxZgkd.getBtzdByYh(yhjs,"xsfzxxb",function(data){
				if(data != null){
					for(var i=0; i<data.length; i++){
						if(ele(data[i].en) && ele(data[i].en).value == ""){
							alert(data[i].cn + "不能为空！");
							result = false;
							break;
						}
					}
				}				
			});
			dwr.engine.setAsync(true);
			if(result){
				var tvalue = ["xh", "xm", "xy", "zy", "bj", "nj"];
				for(var i=0;i<tvalue.length;i++){
					if(document.getElementById(tvalue[i]).value==""){
					alert("请将带＊号的项目填写完整！");
					return false;
					}
				}
	         	refreshForm('xsxxZgdzdx.do?method=saveStuinfoModi');
	          	$("buttonSave").disabled=true;
          }
    }
	 function sfbyChek(){
				var nfby = document.getElementById('nfby').value;			
				var xh = document.getElementById('xh').value;
				    if(nfby=='是'){
					  //能否毕业判断
					  getStuDetails.zgdzdxCheckBy(xh,function(data){
						   if(data==false){
							  alert('该学生还不符合毕业条件！');
							  document.getElementById("nfby").options[0].selected=true;				
							  return false;						
						   }
					  });
					}									
	 }
	 
	 
	function sfzcChek(){
	            var sfzc = document.getElementById('sfzc').value;
				var xh = document.getElementById('xh').value;  
				if(sfzc=='已注册'){				
					//是否注册判断
					getStuDetails.zgdzdxCheckZc(xh,function(data){
						if(data==false){
							alert('该学生还不符合注册条件，请确认费用是否缴清！');
							document.getElementById("sfzc").options[0].selected=true;				
							return false;	
						}
					});
				}
	 }
	</script>
</head>
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgdzdx/zgdzdx_stu_modinfo.jsp"/>
			<input type="hidden" name="redirect" id="redirect" value=""/>
			<input type="hidden" name="variable" id="variable" value=""/>
			<input type="hidden" name="sfzcV" id="sfzcV" value="${rs.sfzc}"/>
			<input type="hidden" name="nfbyV" id="nfbyV" value="${rs.nfby}"/>
			<input type="hidden" name="yhjs" id="yhjs" value="${userType}"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 信息修改 - 修改个人信息</a>
				</p>
			</div>
			<logic:equal name="userType" value="admin" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					本页面只有学生和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</p>
			</logic:equal>

			<logic:equal name="userType" value="xx" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					本页面只有学生和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</p>
			</logic:equal>

			<logic:equal value="student" name="userOnLine">
			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("不在设定时间范围内,暂不开放该功能!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			</logic:equal>

			<div class="tab">
			<table class="formlist" id="rsTable" width="100%">
				<thead>
					<tr>
					<th colspan="4">
						<span>个人信息修改
						<logic:present name="shjg">
						<font color="red">
						(审核未通过)
						</font>
						</logic:present>
						</span>
					</th>
					</tr>
				</thead>
				<tbody>
					<tr>			
						<th><span class="red">*</span>学号</th>
						<td>
							<html:text property="xh" name="rs" disabled="true" styleId="xh" maxlength="20"/>	
							<html:hidden property="xh" name="rs"/>			
							<logic:equal value="xy" name="userType">
								<button type="button"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									id="buttonFindStu">
									选择
								</button>
							</logic:equal>
						</td>
						<th>姓名</th>
						<td>
							<html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/>
						</td>
					</tr>					
					<tr>
						<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" name="rs" disabled="true" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						</td>
						<th><span class="red">*</span>专业</th>
						<td>
							<html:select property="zydm" name="rs" disabled="true" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>年级</th>
						<td>
							<html:select property="nj" name="rs" disabled="true" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th><span class="red">*</span>班级</th>
						<td>
							<html:select property="bjdm" name="rs" disabled="true" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>性别</th>
						<td>
							<html:radio property="xb" value="男" name="rs" disabled="true" styleId="xbn">男</html:radio>
							<html:radio property="xb" value="女" name="rs" disabled="true"  styleId="xbv">女</html:radio>
						</td>
						<th>学制</th>
						<td>
							<html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>年
						</td>
					</tr>					
					<tr>
						<th>民族</th>
						<td>
							<html:select property="mz" name="rs" disabled="true" styleId="mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="zzmm" name="rs" disabled="true" styleId="zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>学籍状态</th>
						<td>
							<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm">
								<html:option value=""></html:option>
								<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
							</html:select>
						</td>
						<th>出生日期</th>
						<td>
							<html:text property="csrq" name="rs" disabled="true" readonly="" onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"/>
						</td>
					</tr>					
					<tr>
						<th>姓名拼音</th>
						<td>
							<html:text property="xmpy" name="rs" disabled="true" styleId="xmpy" maxlength="32"/>
						</td>
						<th>曾用名</th>
						<td>
							<html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16"/>
						</td>
					</tr>					
					<tr>
						<th>身高</th>
						<td>
							<html:text property="sg" name="rs" disabled="true" styleId="sg" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> 厘米
						</td>
						<th>体重</th>
						<td>
							<html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> 千克
						</td>
					</tr>					
					<tr>
						<th>身份证号</th>
						<td>
							<html:text property="sfzh" name="rs" disabled="true"  styleClass="text_nor" onblur="if(!checkSfzh(this)){this.focus();}"  styleId="sfzh" maxlength="18"/>
						</td>
						<th>特长</th>
						<td>
							<html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/>
						</td>
					</tr>					
					<tr>
						<th>培养方式</th>
						<td>
							<html:text property="pyfs" name="rs" disabled="true" styleId="pyfs" maxlength="32"/>
						</td>
						<th>培养层次</th>
						<td>
							<html:text property="pycc" name="rs" disabled="true" styleId="pycc" maxlength="32"/>
						</td>
					</tr>					
					<tr>
						<th>入学方式</th>
						<td>
							<html:text property="rxfs" name="rs" disabled="true" styleId="rxfs" maxlength="32"/>
						</td>
						<th>考生类别</th>
						<td>
							<html:text property="kslb" name="rs" disabled="true" styleId="kslb" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<th>入学时间</th>
						<td>
							<html:text property="rxrq" name="rs" disabled="true" styleId="rxrq" onclick="return showCalendar('rxrq','y-mm-dd');"/>
						</td>
						<th>考生号</th>
						<td>
							<html:text property="ksh" name="rs" disabled="true"
										styleId="ksh" maxlength="32" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
					</tr>
					<tr>
						<th>来源地区</th>
						<td>
							<html:text property="syd" name="rs" disabled="true" styleId="syd" maxlength="25"/>
						</td>
						<th>籍贯</th>
						<td>
							<html:text property="jg" name="rs" disabled="true" styleId="jg" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>电子邮箱</th>
						<td>
							<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32"/>
						</td>
						<th>联系电话</th>
						<td>
							<html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15"/>
						</td>
					</tr>					
					<tr>
						<th>手机号码</th>
						<td>
							<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th>毕业学校</th>
						<td>
							<html:text property="rxqdw" name="rs" disabled="true" styleId="rxqdw" maxlength="125"/>				
						</td>
					</tr>
					<tr>
						<th>入学年级</th>
						<td colspan="3">
							<html:select property="rxnj" name="rs" disabled="true" styleId="rxnj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>是否毕业生</th>
						<td>
							<html:select property="sfbys" name="rs" styleId="sfbys" disabled="true">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
						<th>是否已毕业</th>
						<td>
							<html:select property="sfyby" name="rs" styleId="sfyby" disabled="true">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>能否毕业</th>
						<td colspan="3">
							<html:select property="nfby" name="rs" disabled="true" styleId="nfby" onchange="sfbyChek()">
								<html:option value=""></html:option>
								<html:options collection="nfbyList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					
					<tr>
						<th>是否注册</th>
						<td>
							<logic:equal value="" property="sfzc" name="rs">
								<html:select property="sfzc" name="rs" styleId="sfzc" disabled="true" onchange="sfzcChek()"  value="未注册">
								<html:option value=""></html:option>
								<html:options collection="sfzcList" property="en" labelProperty="cn"/>
								</html:select>
							</logic:equal>
							<logic:notEqual value="" property="sfzc" name="rs">
								<html:select property="sfzc" name="rs" styleId="sfzc" disabled="true" onchange="sfzcChek()" >
								<html:option value=""></html:option>
								<html:options collection="sfzcList" property="en" labelProperty="cn"/>
								</html:select>
							</logic:notEqual>					
						</td>	
						<th>毕业时间</th>
						<td>
							<html:text property="byny" name="rs" disabled="true"
								readonly="true" onclick="return showCalendar('byny','y-mm-dd');"
								styleId="byny" />
						</td>		
					</tr>
					<tr>
						<th>是否在校</th>
						<td colspan="3">
							<html:select property="sfzx" name="rs" styleId="sfzx" disabled="true">
								<html:option value=""></html:option>
								<html:option value="在校">在校</html:option>
								<html:option value="不在校">不在校</html:option>
							</html:select>
						</td>						
					</tr>
					<tr>
						<th>学生类型</th>
						<td>
							<html:select property="xslx" name="rs"  disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xsLxList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>学生类别</th>
						<td>
							<html:select property="xslb" name="rs"  disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xsLbList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>备注</th>
						<td colspan="3">
							<html:textarea property="bz" disabled="true" name="rs" rows="5" cols="80" onblur="chLeng(this,500)"></html:textarea>
						</td>					
					</tr>
					<tr>
						<th>家庭地址</th>
						<td>
							<html:text property="jtszd" name="rs" disabled="true" styleId="jtszd" maxlength="25"/>
						</td>
						<th>家庭联系电话</th>
						<td>
							<html:text property="lxdh1" name="rs" disabled="true" styleId="lxdh1" maxlength="25" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
					</tr>
					<tr>
						<th>家庭邮编</th>
						<td>
							<html:text property="yb" name="rs" disabled="true" styleId="yb" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th>家庭经济情况</th>
						<td>
							<html:text property="jjzk" name="rs" disabled="true" styleId="jjzk" maxlength="100"/>
						</td>
					</tr>
					</tbody>					
					
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									<span>学生家庭成员信息1</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt1">
					<td colspan="4">					
					<table class="formlist" width="100%">	
					<tbody>				
						<tr>
							<th>姓名</th>
							<td>
								<html:text property="jtcy1_xm" name="rs" disabled="true" styleId="jtcy1_xm" maxlength="25"/>
							</td>
							<th>与本人关系</th>
							<td>
								<html:text property="jtcy1_gx" name="rs" disabled="true" styleId="jtcy1_gx" maxlength="10"/>
							</td>
						</tr>						
						<tr>
							<th>出生日期</th>
							<td>
								<html:text property="jtcy1_nl" name="rs" disabled="true" styleId="jtcy1_nl" readonly="" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" styleId="jtcy1_nl"/>
							</td>
							<th>身份证号</th>
							<td>
								<html:text property="jtcy1_sfzh" name="rs" disabled="true" styleId="jtcy1_sfzh" maxlength="18"/>
							</td>
						</tr>
						
						<tr>
							<th>民族</th>
							<td>
								<html:select property="jtcy1_mz" name="rs" disabled="true" styleId="jtcy1_mz">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
							</td>
							<th>政治面貌</th>
							<td>
								<html:select property="jtcy1_zzmm" name="rs" disabled="true" styleId="jtcy1_zzmm">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th>职业</th>
							<td>
								<html:text property="jtcy1_zy" name="rs" disabled="true" styleId="jtcy1_zy" maxlength="10"/>
							</td>
							<th>职务</th>
							<td>
								<html:text property="jtcy1_zw" name="rs" disabled="true" styleId="jtcy1_zw" maxlength="10"/>
							</td>
						</tr>							
						<tr>
							<th>工作单位电话</th>
							<td>
								<html:text property="jtcy1_lxdh1" name="rs" disabled="true" styleId="jtcy1_lxdh1" maxlength="25"/>
							</td>
							<th>个人电话</th>
							<td>
								<html:text property="jtcy1_lxdh2" name="rs" disabled="true" styleId="jtcy1_lxdh2" maxlength="25"/>
							</td>
						</tr>						
						<tr>
							<th>工作地址</th>
							<td colspan="3">
								<html:text property="jtcy1_gzdz" name="rs" disabled="true" styleId="jtcy1_gzdz" maxlength="25" style="width:100%"/>
							</td>			
						</tr>	
					</tbody>		
					</table>
					</td>
					</tr>
					</tbody>

					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
								<span>学生家庭成员信息2</span>
							</th>
						</tr>
					</thead>

					<tbody>
					<tr id="jt2">
					<td colspan="4">					
					<table class="formlist" width="100%">
					<tbody>				
					<tr>
						<th>姓名</th>
						<td>
							<html:text property="jtcy2_xm" name="rs" disabled="true" styleId="jtcy2_xm" maxlength="25"/>
						</td>
						<th>与本人关系</th>
						<td>
							<html:text property="jtcy2_gx" name="rs" disabled="true" styleId="jtcy2_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>出生日期</th>
						<td>
							<html:text property="jtcy2_nl" name="rs" disabled="true" styleId="jtcy2_nl" readonly="" onclick="return showCalendar('jtcy2_nl','y-mm-dd');" styleId="jtcy2_nl"/>
						</td>
						<th>身份证号</th>
						<td>
							<html:text property="jtcy2_sfzh" name="rs" disabled="true" styleId="jtcy2_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>民族</th>
						<td>
							<html:select property="jtcy2_mz" name="rs" disabled="true" styleId="jtcy2_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="jtcy2_zzmm" name="rs" disabled="true" styleId="jtcy2_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>职业</th>
						<td>
							<html:text property="jtcy2_zy" name="rs" disabled="true" styleId="jtcy2_zy" maxlength="10"/>
						</td>
						<th>职务</th>
						<td>
							<html:text property="jtcy2_zw" name="rs" disabled="true" styleId="jtcy2_zw" maxlength="10"/>
						</td>
					</tr>						
					<tr>
						<th>工作单位电话</th>
						<td>
							<html:text property="jtcy2_lxdh1" name="rs" disabled="true" styleId="jtcy2_lxdh1" maxlength="25"/>
						</td>
						<th>个人电话</th>
						<td>
							<html:text property="jtcy2_lxdh2" name="rs" disabled="true" styleId="jtcy2_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>工作地址</th>
						<td colspan="3">
							<html:text property="jtcy2_gzdz" name="rs" disabled="true" styleId="jtcy2_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
					</tbody>
					</table>
					</td>
					</tr>
					<tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									<span>学生家庭成员信息3</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt3">
					<td colspan="4">
					<table class="formlist" width="100%">
					<tbody>				
					<tr>
						<th>姓名</th>
						<td>
							<html:text property="jtcy3_xm" name="rs" disabled="true" styleId="jtcy3_xm" maxlength="25"/>
						</td>
						<th>与本人关系</th>
						<td>
							<html:text property="jtcy3_gx" name="rs" disabled="true" styleId="jtcy3_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>出生日期</th>
						<td>
							<html:text property="jtcy3_nl" name="rs" disabled="true" styleId="jtcy3_nl" readonly="" onclick="return showCalendar('jtcy3_nl','y-mm-dd');" styleId="jtcy3_nl"/>
						</td>
						<th>身份证号</th>
						<td>
							<html:text property="jtcy3_sfzh" name="rs" disabled="true" styleId="jtcy3_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>民族</th>
						<td>
							<html:select property="jtcy3_mz" name="rs" disabled="true" styleId="jtcy3_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>政治面貌</th>
						<td>
							<html:select property="jtcy3_zzmm" name="rs" disabled="true" styleId="jtcy3_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>职业</th>
						<td>
							<html:text property="jtcy3_zy" name="rs" disabled="true" styleId="jtcy3_zy" maxlength="10"/>
						</td>
						<th>职务</th>
						<td>
							<html:text property="jtcy3_zw" name="rs" disabled="true" styleId="jtcy3_zw" maxlength="10"/>
						</td>			
					</tr>
						
					<tr>
						<th>工作单位电话</th>
						<td>
							<html:text property="jtcy3_lxdh1" name="rs" disabled="true" styleId="jtcy3_lxdh1" maxlength="25"/>
						</td>
						<th>个人电话</th>
						<td>
							<html:text property="jtcy3_lxdh2" name="rs" disabled="true" styleId="jtcy3_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>工作地址</th>
						<td colspan="3">
							<html:text property="jtcy3_gzdz" name="rs" disabled="true" styleId="jtcy3_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
					</tbody>
					</table>
					</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" class="button2" id="buttonSave"
							onclick="zgdzdxSaveXsxx()">
							保存信息
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");				
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
