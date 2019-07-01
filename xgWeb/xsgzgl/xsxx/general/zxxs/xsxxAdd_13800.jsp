<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type='text/javascript' src='js/check.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript">
		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}
		
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
			 		window.dialogArguments.document.getElementById('search_go').onclick();
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function checkKey(o) {
			o.value=o.value.replace(/[^\w\.\/]/ig,'');
		}
		
		function send(){
			var sfzh = $("sfzh").value;
			var csrq = $("csrq").value.replace(/\-/g,"");
			var jgshen=($("jgshen")&&$("jgshen")!=null)?$("jgshen").value:"";
			var jgshi= ($("jgshi")&&$("jgshi")!=null)?$("jgshi").value:"";
			var jgxian=($("jgxian")&&$("jgxian")!=null)?$("jgxian").value:"";
			var syshen=($("syshen")&&$("syshen")!=null)?$("syshen").value:"";
			var syshi=($("syshi")&&$("syshi")!=null)?$("syshi").value:"";
			var syxian=($("syxian")&&$("syxian")!=null)?$("syxian").value:"";
			var rxqxxshen=($("rxqxxshen")&&$("rxqxxshen")!=null)?$("rxqxxshen").value:"";
			var rxqxxshi=($("rxqxxshi")&&$("rxqxxshi")!=null)?$("rxqxxshi").value:"";
			var rxqxxxian=($("rxqxxxian")&&$("rxqxxxian")!=null)?$("rxqxxxian").value:"";
			var rxqbyxx = ($("rxqbyxx")&&$("rxqbyxx")!=null)?$("rxqbyxx").value:"";
			var rxqbyxx = ($("rxqbyxx")&&$("rxqbyxx")!=null)?$("rxqbyxx").value:"";
			var jtdzshen = ($("jtdzshen")&&$("jtdzshen")!=null)?$("jtdzshen").value:"";
			var jtdzshi  = ($("jtdzshi")&&$("jtdzshi")!=null)?$("jtdzshi").value:""; 
			var jtdzxian  = ($("jtdzxian")&&$("jtdzxian")!=null)?$("jtdzxian").value:"";
			
			var xh = $("xh").value;
			if(xh.length < 6){
				alert("学号请至少维护6位！");
			    return false;
			}
			if(!checkRxByTime()){
				return false;
			}
			if(!checkXsxxjlxx()){
				return false;
			}
			if(!checkXsshgxxx()){
				return false;
			}
			//stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");
			stuinfoSave("xsxx_zxxs_hbktzy.do?method=xsxxSave&oper=");			
		}		

	function checkRxByTime(){
		if($("rxrq") && $("byny")){
			if($("rxrq")!=null && $("rxrq")!="" 
				&& $("byny")!=null && $("byny")!="")
			return checkSjTj("rxrq","入学时间","byny","毕业时间")
		}
	}
	function addZpsc(url) {
		viewTempDiv('学生照片上传','tmpdiv1',400,200);
	}
	</script>
	</head>

	<body onload="showButton();addNotNullFiled();loadXsxxjl();loadXsshgx();">
		<html:form action="/xsxx_zxxs_hbktzy" method="post">
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" name="url" id="url"
				value="/sjcz/stu_info_modify.jsp" />
			<input type="hidden" name="variable" id="variable" value="ydinfo" />
			<input type="hidden" name="redirect" id="redirect" value="true" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" name="notnull" id="notnull"
				value="xh-xm-bjdm-zydm-xydm-nj" />

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }-维护</a>
				</p>
			</div>
			<div class="open_win" style="width:100%;height:500px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
				<table width="100%" border="0" class="formlist" id="rsTable">
					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_jbxx')">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_jbxx">
						<tr>
							<th width="10%">
								<span class="red">*</span>学号
							</th>
							<td width="30%">
								<html:text name="rs" property="xh" styleId="xh"
									styleClass="text_nor"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
							</td>
							<th>
								<span class="red">*</span>姓名
							</th>
							<td>
								<html:text name="rs" property="xm" styleId="xm" maxlength="16"
									styleClass="text_nor" />
							</td>
							<td rowspan="7" class="nohover"
								style="vertical-align:top; text-align:center;">
								<div class="open_img">
									<img
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
										width="96" height="128" />
									<button type="button" style="display: none" id="reloadF"
										onclick="refreshForm('stu_info_add.do?method=showStuInfo&oper=update&xh=${rs.xh}')"></button>
									<p style="color:#0F5DC1">
										类型：jpg
										<br />
										容量：小于100k
										<br />
										大小：96*128像素
									</p>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<html:radio name="rs" property="xb" value="1">男</html:radio>
								<html:radio name="rs" property="xb" value="2">女</html:radio>
							</td>
							<th>
								出生日期
							</th>
							<td>
								<html:text name="rs" property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								身份证号
							</th>
							<td align="left">
								<html:text name="rs" property="sfzh" styleId="sfzh"
									styleClass="text_nor"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
							<th>
								培养层次
							</th>
							<td align="left">
								<html:select property="pycc" name="rs" styleId="pycc">
									<html:options collection="pyccList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								户口所在地
							</th>
							<td colspan="3">
								<!--地址信息取标准码-->
								<logic:present name="ssList">
									<html:select name="rs" property="hkshen" styleId="hkshen"
										onchange="loadShi('hkshen','hkshi','hkxian');">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="hkshi" styleId="hkshi"
										onchange="loadXian('hkshi','hkxian')">
										<html:options collection="hkshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="hkxian" styleId="hkxian">
										<html:options collection="hkxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end地址信息取标准码-->
								<logic:notPresent name="ssList">
									<html:text name="rs" property="hkszd" maxlength="60"
										styleId="hkszd" styleClass="text_nor" style="width:90%" />
								</logic:notPresent>
							</td>
						</tr>
						<tr>
							<th>
								籍贯
							</th>
							<td colspan="3">
								<%--非乐山师范学院--%>
								<logic:notEqual value="10649" name="xxdm">
									<!--地址信息取标准码-->
									<logic:present name="ssList">
										<html:select name="rs" property="jgshen" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian');">
											<html:option value="">--请选择--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgxian" styleId="jgxian">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</logic:present>
									<!--end地址信息取标准码-->
									<logic:notPresent name="ssList">
										<html:text name="rs" property="jg" styleId="jg" maxlength="10"
											styleClass="text_nor" style="width:90%" />
									</logic:notPresent>
								</logic:notEqual>

								<%--乐山师范学院--%>
								<logic:equal value="10649" name="xxdm">
									<html:select name="rs" property="jgshen" styleId="jgshen"
										onchange="loadShi('jgshen','jgshi','jgxian');">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgshi" styleId="jgshi"
										onchange="loadXian('jgshi','jgxian')">
										<html:options collection="jgshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgxian" styleId="jgxian">
										<html:options collection="jgxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								来源地区
								<br />
								(生源地)
							</th>
							<td colspan="3">
								<!--地址信息取标准码-->
								<logic:present name="ssList">
									<html:select name="rs" property="syshen" styleId="syshen"
										onchange="loadShi('syshen','syshi','syxian')">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="syshi" styleId="syshi"
										onchange="loadXian('syshi','syxian')">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="syxian" styleId="syxian">
										<html:options collection="xianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end地址信息取标准码-->
								<logic:notPresent name="ssList">
									<html:text name="rs" property="syd" styleId="syd"
										maxlength="25" styleClass="text_nor" style="width:90%" />
								</logic:notPresent>
							</td>
						</tr>
						<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_xjxx');">
								<span>学籍信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_xjxx">
						<tr>
							<th width="10%">
								<span class="red">*</span>年级
							</th>
							<td width="30%">
								<html:select name="rs" property="nj" styleId="nj"
									style="width:90px" onchange="initXyzybj();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								学制(年)
							</th>
							<td colspan="2">
								<html:text name="rs" property="xz" styleId="xz"
									onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="3"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<input type="text" id="xymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.xymc }" />
								<button type="button" class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									选择
								</button>
								<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
							</td>
							<th>
								是否注册
							</th>
							<td colspan="2">
								<html:select property="sfzc" name="rs" style="width:120px"
									styleId="sfzc">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>专业
							</th>
							<td>
								<input type="text" id="zymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.zymc }" />
								<button type="button" class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									选择
								</button>
								<input type="hidden" name="zydm" tyleId="xydm"
									value="${rs.zydm}" />
							</td>
							<th>
								是否走读生
							</th>
							<td colspan="2">
								<html:select property="sfzd" name="rs" style="width:120px"
									styleId="sfzd">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>班级
							</th>
							<td>
								<input type="text" id="bjmc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.bjmc }" />
								<button type="button" class="btn_01" id="button_bj" style="display: "
									onclick="getXyzybj('bj');">
									选择
								</button>
								<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
							</td>
							<th>
								是否在校
							</th>
							<td colspan="2">
								<html:select property="sfzx" name="rs" styleId="sfzx">
									<html:option value=""></html:option>
									<html:option value="在校">在校</html:option>
									<html:option value="不在校">不在校</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学籍状态
							</th>
							<td>
								<html:select name="rs" property="xjzt" style="width:150"
									styleId="xjzt">
									<html:option value=""></html:option>
									<html:options collection="xjztList" property="zxdmmc"
										labelProperty="zxdmmc" />
								</html:select>
							</td>
							<th>
								是否毕业生
							</th>
							<td colspan="2">
								<html:select property="sfbys" name="rs" style="width:120px"
									styleId="sfbys">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								入学时间
							</th>
							<td align="left">
								<html:text name="rs" property="rxrq" styleId="rxrq"
									maxlength="10" styleClass="text_nor"
									onclick="return showCalendar('rxrq','y-mm-dd');" />
							</td>
							<th>
								是否毕业
							</th>
							<td colspan="2">
								<html:select property="sfyby" name="rs" styleId="sfyby"
									style="width:120px" styleId="sfyby">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								毕业时间
							</th>
							<td >
								<html:text property="byny" name="rs" styleId="byny"
									styleClass="text_nor" readonly="true" maxlength="10"
									onclick="return showCalendar('byny','y-mm-dd');" />
							</td>
							<th>校区信息</th>
							<td colspan="2">
								<html:select name="rs" property="yxdm" style="width:150"
									styleId="yxdm">
									<html:option value=""></html:option>
									<html:options collection="yxdmList" property="dm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_lxfs');">
								<span>联系方式及证件</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_lxfs">
						<tr>
							<th>
								固定电话
							</th>
							<td>
								<html:text name="rs" property="lxdh" styleId="lxdh"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="13" />
							</td>
							<th>
								手机号码
							</th>
							<td colspan="2">
								<html:text name="rs" property="sjhm" styleId="sjhm"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="11" />
							</td>
						</tr>
						<tr>
							<th>
								宿舍编号
							</th>
							<td>
								<html:text name="rs" property="ssbh" styleId="ssbh"
									disabled="true" />
							</td>
							<th>
								寝室电话
							</th>
							<td colspan="2">
								<html:text name="rs" property="qsdh" styleId="qsdh"
									disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								QQ号码
							</th>
							<td>
								<html:text name="rs" property="qqhm" styleId="qqhm"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="20" />
							</td>
							<th>
								电子邮箱
							</th>
							<td colspan="2">
								<html:text name="rs" property="dzyx" styleClass="text_nor"
									styleId="dzyx" />
							</td>
						</tr>
						<tr>
							<th>
								银行名称
							</th>
							<td>
								<html:select name="rs" property="yhdm" styleId="yhdm">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm"
										labelProperty="yhmc" />
								</html:select>
							</td>
							<th>
								银行卡号
							</th>
							<td colspan="2">
								<html:text name="rs" property="yhkh" maxlength="20"
									styleId="yhkh" onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								辅导员
							</th>
							<td>
								<html:text name="rs" property="fdyxm" styleId="fdyxm"
									disabled="true" />
							</td>
							<th>
								辅导员电话
							</th>
							<td colspan="2">
								<html:text name="rs" property="fdylxdh" styleId="fdylxdh"
									disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								班主任
							</th>
							<td>
								<html:text name="rs" property="bzrxm" styleId="bzrxm"
									disabled="true" />
							</td>
							<th>
								班主任电话
							</th>
							<td colspan="2">
								<html:text name="rs" property="bzrlxdh" styleId="bzrlxdh"
									disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								助理班主任
							</th>
							<td>
								<html:text name="rs" property="zlbzrxm" styleId="zlbzrxm"
									disabled="true" />
							</td>
							<th>
								一卡通号
							</th>
							<td colspan="2">
								<html:text name="rs" property="kh" styleId="kh"
									onkeypress="return onlyNum(this,8)" style="ime-mode:disabled"
									maxlength="20" />
							</td>
						</tr>
					</tbody>
					<%-- 新版本家庭信息与学生信息放在一块维护  --%>
					<%@ include file="/xsxx/stu_info_family.jsp"%>
					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_qtxx');">
								<span>其它信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx">
						<tr>
							<th>
								姓名拼音
							</th>
							<td>
								<html:text name="rs" property="xmpy" styleId="xmpy"
									maxlength="64" styleClass="text_nor"
									onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " />
							</td>
							<th>
								曾用名
							</th>
							<td align="left" colspan="2">
								<html:text name="rs" property="cym" styleId="cym"
									styleClass="text_nor" maxlength="16" />
							</td>

						</tr>
						<tr>
							<th>
								身高(cm)
							</th>
							<td align="left">
								<html:text name="rs" property="sg" styleId="sg"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="3" />
							</td>
							<th>
								体重(kg)
							</th>
							<td colspan="2">
								<html:text name="rs" property="tz" styleId="tz"
									onkeyup="value=value.replace(/[^\d|.]/g,'') "
									styleClass="text_nor" maxlength="4" />
							</td>
						</tr>
						<tr>
							<th>
								特长
							</th>
							<td>
								<html:text name="rs" property="tc" styleId="tc"
									styleClass="text_nor" maxlength="32" />
							</td>
							<th>
								考生类别
							</th>
							<td align="left" colspan="2">
								<html:select property="kslb" name="rs" styleId="kslb">
									<html:options collection="kslbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								入学方式
							</th>
							<td align="left">
								<logic:equal value="new" name="edition">
									<html:select property="rxfs" name="rs" styleId="rxfs">
										<html:options collection="rxfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="rxfs" styleId="rxfs"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
							<th>
								培养方式
							</th>
							<td align="left" colspan="2">
								<logic:equal value="new" name="edition">
									<html:select property="pyfs" name="rs" styleId="pyfs">
										<html:options collection="pyfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="pyfs" styleId="pyfs"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>

							<th>
								健康状况
							</th>
							<td>
								<html:text name="rs" property="jkzk" styleId="jkzk"
										styleClass="text_nor" maxlength="30" />
							</td>
							<th></th>
							<td colspan="2">
								
							</td>
						</tr>
						<tr>
						<th>
							备注
						</th>
						<td colspan="4">
							<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%"
										rows='8' onblur="chLeng(this,2000)"/>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div>
					<table width="100%" border="0" class="formlist" id="rsTable">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notPresent name="details">
										<button type="button" class="button2" id="buttonSave"
											style="height:20px;width:80px" onclick="send();">
											保 存
										</button>
									</logic:notPresent>
									<button type="button" class="button2" style="height:20px;width:80px"
										onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div id="tmpdiv1" style="display:none">
				<iframe name='mainFrame' style='height:100%; width:100%; '
					marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
					scrolling='yes'
					src='uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}'></iframe>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("操作成功！");
						Close();
						if(window.dialogArguments){
							window.dialogArguments.document.getElementById('search_go').click();
						}						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
