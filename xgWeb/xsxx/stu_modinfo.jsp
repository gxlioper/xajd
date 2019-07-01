<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
	<script>
		function saveXsxx(){
			var flag = true;	
        	refreshForm('xsxxgl.do?method=saveStuinfoModi');
          	$("saveButton").disabled=true;
    	}
    	
    	function showColumns(){
			var zdList = val('zdList');
			var zdColumn = zdList.split("!!");			
			for(var i=0; i<zdColumn.length-1; i++){	
				if(zdColumn[i] == "jg"){
					if(document.getElementById("jgshen")){
						document.getElementById("jgshen").disabled=false;
					}
					if(document.getElementById("jgshi")){
						document.getElementById("jgshi").disabled=false;
					}
					if(document.getElementById("jgxian")){
						document.getElementById("jgxian").disabled=false;
					}
				}
				if(zdColumn[i] == "syd"){
					if(document.getElementById("syshen")){
						document.getElementById("syshen").disabled=false;
					}
					if(document.getElementById("syshi")){
						document.getElementById("syshi").disabled=false;
					}
					if(document.getElementById("syxian")){
						document.getElementById("syxian").disabled=false;
					}
				}
				if(zdColumn[i] == "hkszd"){
					if(document.getElementById("hkshen")){
						document.getElementById("hkshen").disabled=false;
					}
					if(document.getElementById("hkshi")){
						document.getElementById("hkshi").disabled=false;
					}
					if(document.getElementById("hkxian")){
						document.getElementById("hkxian").disabled=false;
					}
				}
				if(document.getElementById(zdColumn[i])){					
					document.getElementById(zdColumn[i]).disabled=false;
				}
						
				if(zdColumn[i]=="xb"){
					if(document.getElementById('xbn')){
						document.getElementById('xbn').disabled=false;
					}
					if(document.getElementById('xbv')){
						document.getElementById('xbv').disabled=false;
					}
				}
				if(zdColumn[i]=='xydm'){
					if(document.getElementById("xy")){
						document.getElementById("xy").disabled=true;
					}			
				}
				if(zdColumn[i]=='zydm'){
					if(document.getElementById("zy")){
						document.getElementById("zy").disabled=true;
					}
				}
				if(zdColumn[i]=='bjdm'){
					if(document.getElementById("bj")){
						document.getElementById("bj").disabled=true;
					}
				}			
			}
			var ele = ['jt1','jt2','jt3'];
			for(var i=0; i<ele.length; i++){
				if(document.getElementById(ele[i])){
					if(document.getElementById(ele[i])){
						document.getElementById(ele[i]).style.display="none";
					}
				}
			}
		}
	</script>
</head>
<body onload="showColumns();loadXsxxjl();loadXsshgx();">
		<html:form action="/xsxxgl.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="${zdList}"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgdzdx/zgdzdx_stu_modinfo.jsp"/>
			<input type="hidden" name="redirect" id="redirect" value=""/>
			<input type="hidden" name="variable" id="variable" value=""/>
			<input type="hidden" name="sfzcV" id="sfzcV" value="${rs.sfzc}"/>
			<input type="hidden" name="nfbyV" id="nfbyV" value="${rs.nfby}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 信息修改 - 修改个人信息</a>
				</p>
			</div>
			<logic:notEqual name="userOnLine" value="student" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					本页面只有学生可以访问
				</p>
			</logic:notEqual>
			<logic:equal value="student" name="userOnLine" scope="session">
			<logic:equal name="sqsjFlag" value="false">
				<script>
		   			 alert("不在设定的修改时间范围内,暂不开放该功能!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
	
			<div class="tab">
			<table class="formlist" id="rsTable" width="100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>个人信息修改</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>学号</th>
					<td>
						<html:text property="xh" name="rs" disabled="true" styleId="xh" maxlength="20"/>	
						<html:hidden property="xh" name="rs"/>
					</td>
					<th>姓名</th>
					<td>
						<html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/>
					</td>
				</tr>				
				<tr>
					<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" name="rs" disabled="true" styleId="xy" style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
					</td>
					<th><span class="red">*</span>专业</th>
					<td>
						<html:select property="zydm" name="rs" disabled="true" styleId="zy"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
					</td>
				</tr>				
				<tr>
					<th>年级</th>
					<td>
						<html:select property="nj" name="rs" disabled="true" styleId="nj"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>
					<th><span class="red">*</span>班级</th>
					<td>
						<html:select property="bjdm" name="rs" disabled="true" styleId="bj"  style="width:160px">
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
						<html:select property="mz" name="rs" disabled="true" styleId="mz"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
						</html:select>
					</td>
					<th>政治面貌</th>
					<td>
						<html:select property="zzmm" name="rs" disabled="true" styleId="zzmm"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学籍状态</th>
					<td>
						<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm"  style="width:160px">
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
						<html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'')"/> 千克
					</td>
				</tr>				
				<tr>
					<th>身份证号</th>
					<td>
						<html:text property="sfzh" name="rs" disabled="true" styleId="sfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
					</td>
					<th>特长</th>
					<td>
						<html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/>
					</td>
				</tr>
				<!-- 南京技师 -->
				<logic:equal name="xxdm" value="8001">
					<tr>
						<th>培养层次</th>
						<td>
							<html:select name="rs" property="pycc" style="" onchange="" disabled="true">
								<html:option value="">----请选择----</html:option>
								<html:options collection="xsccList" property="xsccdm" labelProperty="xsccmc" />
							</html:select>
						</td>
						<th>户口性质</th>
						<td align="left" colspan="2">
							<html:select name="rs" property="hkxz" style="" onchange="" disabled="true">
								<html:option value="">----请选择----</html:option>
								<html:options collection="hkxzList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>					
				</logic:equal>
				<!-- 非南京技师 -->
				<logic:notEqual name="xxdm" value="8001">
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
				</logic:notEqual>
					<tr>
						<th>户口所在地</th>
						<td colspan="3">
							<!--地址信息取标准码-->
							<logic:present name="ssList">
								<html:select name="rs" property="hkshen" styleId="hkshen" onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="hkshi" styleId="hkshi"
									onchange="loadXian('hkshi','hkxian')" disabled="true">
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
								<html:select name="rs" property="hkxian" styleId="hkxian" disabled="true">
									<html:options collection="xianList" property="xiandm"
										labelProperty="xianmc" />
								</html:select>
							</logic:present>
							<!--end地址信息取标准码-->
						    <logic:notPresent name="ssList">
						    	<html:text name="rs" property="hkszd" maxlength="60" disabled="true" style="width:90%"/>
						    </logic:notPresent>								
						</td>
					</tr>
				<tr>
					<th>来源地区</th>
					<td colspan="3">
						<!--地址信息取标准码-->
						<logic:present name="ssList">
							<html:select name="rs" property="syshen" styleId="syshen" onchange="loadShi('syshen','syshi','syxian')" disabled="true">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="syshi" styleId="syshi"
								onchange="loadXian('syshi','syxian')" disabled="true">								
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="syxian" styleId="syxian" disabled="true">								
								<html:options collection="xianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
						</logic:present>
						<!--end地址信息取标准码-->
						<logic:notPresent name="ssList">
							<html:text property="syd" name="rs" disabled="true" styleId="syd" maxlength="25"/>
						</logic:notPresent>
					</td>			
				</tr>
				<tr>
					<th>籍贯</th>
					<td colspan="3">
						<!--地址信息取标准码-->
						<logic:present name="ssList">
							<html:select name="rs" property="jgshen" styleId="jgshen" onchange="loadShi('jgshen','jgshi','jgxian');" disabled="true">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="jgshi" styleId="jgshi"
								onchange="loadXian('jgshi','jgxian')" disabled="true">
								<html:options collection="jgshiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="jgxian" styleId="jgxian" disabled="true">
								<html:options collection="jgxianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
						</logic:present>
						<!--end地址信息取标准码-->
						<logic:notPresent name="ssList">
							<html:text property="jg" name="rs" disabled="true" styleId="jg" maxlength="10"/>
						</logic:notPresent>
					</td>
				</tr>				
				<tr>
					<th>电子邮箱</th>
					<td>
						<div class="pos" style="z-index:1">
							<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32" onblur="checkEmaile(this)"/>
							<div id="emaliErrow" class="hide">
								<p>
									电子邮箱格式不正确
								</p>
							</div>
						</div>
						
					</td>
					<th>联系电话</th>
					<td>
						<div class="pos" style="z-index:2">
							<html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15" onblur="checkPhoneV4(this)"/>
							<div id="phoneErrow" class="hide">
								<p>
									电话格式不正确
								</p>
							</div>
						</div>
					</td>
				</tr>				
				<tr>
					<th>手机号码</th>
					<td>
						<div class="pos" style="z-index:2">
							<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="checkPhoneError(this,'sjhmerror')"/>
							<div id="sjhmerror" class="hide">
								<p>
									手机号码格式不正确
								</p>
							</div>
						</div>						
					</td>
					<th>银行名称</th>
					<td>
						<html:select property="yhdm" name="rs" styleId="yhdm" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="yhList" property="yhdm" labelProperty="yhmc"/>
						</html:select>
					</td>
				</tr>	
				<tr>
					<th>QQ号码</th>
					<td>
						<html:text property="qqhm" name="rs" disabled="true" styleId="qqhm" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " />
					</td>					
					<th>银行卡号</th>
					<td>
						<html:text name="rs" property="yhkh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " disabled="true"/>
					</td>				
				</tr>
				
				<!--武汉理工大学华夏学院-->
				<logic:equal value="1049701" name="xxdm">
					<tr>
						<th>乘车区间</th>
						<td colspan="3">
							<html:text property="ccqj" name="rs"  style="width:100%" maxlength="50" disabled="true"/>
						</td>
					</tr>
				</logic:equal>
				<!--end武汉理工大学华夏-->

				<!--南京技师-->
				<logic:equal name="xxdm" value="8001">
					<tr>
						<th>入学前文化程度</th>
						<td>
							<html:select property="rxqwhcd" name="rs" disabled="true">
								<html:option value="">----请选择----</html:option>
								<html:options collection="rxqwhcdList" property="dm" labelProperty="mc" />
							</html:select>	
						</td>
						<th>注册顺序号码</th>
						<td>
							<html:text name="rs" property="zcsxhm" 
								onkeypress="return onlyNum(this,10)"
								style="ime-mode:disabled" disabled="true"/>
						</td>
					</tr>
					<tr>
						<th>入学时间</th>
						<td>
							<html:text property="rxrq" name="rs" disabled="true" styleId="rxrq" maxlength="10" onclick="return showCalendar('rxrq','y-mm-dd');" />
						</td>
						<th>一卡通号码</th>
						<td>
							<html:text name="rs" property="kh" 
								onkeypress="return onlyNum(this,8)"
								style="ime-mode:disabled" disabled="true"/>
						</td>
					</tr>
				</logic:equal>
				<!--end南京技师-->

				<!--非南京技师-->
				<logic:notEqual name="xxdm" value="8001">
					<tr>
						<th>入学时间</td>
						<td>
							<html:text property="rxrq" name="rs" disabled="true" styleId="rxrq" maxlength="10" onclick="return showCalendar('rxrq','y-mm-dd');" />
						</td>
						<th>卡号</th>
						<td>
							<html:text name="rs" property="kh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " disabled="true"/>
						</td>
					</tr>
				</logic:notEqual>
				<!--end非南京技师-->
				<tr>
					<th>是否在校</th>
					<td>
						<html:select property="sfzx" name="rs" style="width:120px" disabled="true">
							<html:option value=""></html:option>
							<html:option value="在校">在校</html:option>
							<html:option value="不在校">不在校</html:option>
						</html:select>
					</td>
					<th>是否毕业生</th>
					<td>
						<html:select property="sfbys" name="rs" style="width:120px" disabled="true">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>是否已毕业</th>
					<td>
						<html:select property="sfyby" name="rs" style="width:120px" disabled="true">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<th>毕业时间</th>
					<td>
						<html:text property="byny" name="rs" disabled="true" styleId="byny" maxlength="10"  onclick="return showCalendar('byny','y-mm-dd');"/>
					</td>
				</tr>	
				
				<%@ include file="/xsxx/bjqnzzxy/xsxx_stu_bjqnzzxy.jsp"%>
				<tr>
					<th>家庭地址</th>
					<td colspan="3">
						<html:text property="jtszd" name="rs" disabled="true" styleId="jtszd" maxlength="25" style="width:600px"/>
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
				<%@ include file="/xsxx/bjqnzzxy/xsjtxx_stu_bjqnzzxy.jsp"%>
				<!--天津交通职业学院-->
				<logic:equal value="12883" name="xxdm">
				<tr>
	                <td colspan="4">
	                <%@ include file="/xsxx/xxjlxxb.jsp"%>
	                </td>
	            </tr>
				</logic:equal>
				<!--end天津交通职业学院-->
				</tbody>

				<thead>
					<tr>
						<th colspan="4" style="cursor:hand" align="center"
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
								<html:text property="jtcy1_sfzh" name="rs" disabled="true" styleId="jtcy1_sfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
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
						<th colspan="4" style="cursor:hand" align="center"
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
									<html:text property="jtcy2_sfzh" name="rs" disabled="true" styleId="jtcy2_sfzh" maxlength="18"  onblur="if(!checkSfzh(this)){this.focus();}"/>
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
				</tbody>

				<thead>
					<tr>
						<th colspan="4" style="cursor:hand" align="center"
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
								<html:text property="jtcy3_sfzh" name="rs" disabled="true" styleId="jtcy3_sfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
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
				<logic:equal value="yes" name="writeAble">
				<tfoot>
				 <logic:notEmpty name="errMsg">
				  <tr>
			        <td colspan="4">
						<div class="bz">
							<span class="red">提示：${errMsg}</span>
						</div>			          	
			        </td>
			      </tr>
				</logic:notEmpty>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          	<div class="btn">							
							<button type="button" id="saveButton" style="width:100px"
								<logic:notEmpty name="errMsg">
									disabled="disabled" 
								</logic:notEmpty>
								onclick="saveXsxx()">
								保 存 信 息
							</button>
							<!--天津交通职业学院-->
							<logic:equal value="12883" name="xxdm">
							<button type="button"  id="buttonPrint" style="width:100px"
								onclick="showOpenWindow('xsxxgl.do?method=printTjjtzyXsdjb&xh='+val('xh'),800,600)">
								打印学生登记表
							</button>
							</logic:equal>
							<!--end天津交通职业学院-->
						</div>
			        </td>
			      </tr>
			    </tfoot>
				</logic:equal>
			</table>
			
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
		</logic:equal>
		</html:form>
</body>
</html>
