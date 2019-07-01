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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type='text/javascript' src='js/check.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript">
		//照片上传
		function uploadStuPic(){
		
			jQuery.ajaxSetup({async:false});
			
			var xh = jQuery("#xh").val();
		
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh='+xh,//服务器端程序
			  secureuri:false,
			  fileElementId:'stuPic'//input框的ID
			});
			
			jQuery('#stuImg').empty();
			jQuery("#stuImg").html('<img src="stuPic.jsp?xh=${rs.xh }&flg=true" border="0" align="absmiddle" style="width:120px;height:160px" />').show();
		
			jQuery.ajaxSetup({async:true});
		}
		
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
			var xxdm = $("xxdm").value;
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
			
			if(xxdm=="10649"){//乐山师范
			  if(syshen==""){
			     alert("生源省不能为空！");
			     return false;
			  }else{
			    if($('syshi').options.length>1){
			       if(syshi==""){
			           alert("生源市不能为空！");
			           return false;
			       }else{
			          if($('syxian').options.length>1){
			             if(syxian==""){
			                alert("生源县区不能为空！");
			                return false;
			             }
			          }
			       }
			    }
			  }			  
			  if(jgshen==""){
			     alert("籍贯省不能为空！");
			     return false;
			  }else{
			    if($('jgshi').options.length>1){
			       if(jgshi==""){
			           alert("籍贯市不能为空！");
			           return false;
			       }else{
			          if($('jgxian').options.length>1){
			             if(jgxian==""){
			                alert("籍贯县区不能为空！");
			                return false;
			             }
			          }
			       }
			    }
			  }
			  if(rxqxxshen==""){
			     alert("入校前毕业学校省不能为空！");
			     return false;
			  }else{
			    if($('rxqxxshi').options.length>1){
			       if(rxqxxshi==""){
			           alert("入校前毕业学校市不能为空！");
			           return false;
			       }else{
			          if($('rxqxxxian').options.length>1){
			             if(rxqxxxian==""){
			                alert("入校前毕业学校县区不能为空！");
			                return false;
			             }
			          }
			       }
			    }
			  }	
			  if(jtdzshen==""){
			     alert("家庭地址省不能为空！");
			     return false;
			  }else{
			    if($('jtdzshi').options.length>1){
			       if(jtdzshi==""){
			           alert("家庭地址市不能为空！");
			           return false;
			       }else{
			          if($('jtdzxian').options.length>1){
			             if(jtdzxian==""){
			                alert("家庭地址县区不能为空！");
			                return false;
			             }
			          }
			       }
			    }
			  }			  		  			  
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
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");				
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
	
	function showZpscDiv(){
	
		var xh = jQuery("#xh").val();
		
		if(xh == ""){
			alert("請先維護學號");
		}else{
			tipsWindown("系统提示","id:addPic","380","130","true","","true","id");
		}
	}
	</script>
	</head>

	<body
		onload="showButton();addNotNullFiled();loadXsxxjl();loadXsshgx();">
		<html:form action="/stu_info_add" method="post">
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
								<logic:equal value="update" name="oper" >
									<html:hidden name="rs" styleId="xh" property="xh"/>
									<html:text name="rs" styleId="xh" property="xh" disabled="true"
										style="cursor:hand" styleClass="text_nor" />
								</logic:equal>
								<logic:equal value="add" name="oper">
									<html:text name="rs" property="xh" styleId="xh"
										styleClass="text_nor"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
								</logic:equal>
							</td>
							<th>
								<span class="red">*</span>姓名
							</th>
							<td>
								<html:hidden name="rs" property="xm" styleId="xm"/>
								<html:text name="rs" property="xm" styleId="xm" maxlength="16" disabled="true"
									styleClass="text_nor" />
							</td>
							<td rowspan="7" class="nohover"
								style="vertical-align:top; text-align:center;">
								<div class="open_img">
									<div id="stuImg">
										<img style="width:120px;height:160px" 
											src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
											border="0">
									</div>
									<br />
									<button
										onclick='showZpscDiv()'
										style="width:100px" class="btn_01">
										上传照片
									</button>
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
								<html:hidden name="rs" property="xb" styleId="xb"/>
								<html:radio name="rs" property="xb" value="男" disabled="true">男</html:radio>
								<html:radio name="rs" property="xb" value="女" disabled="true">女</html:radio>
							</td>
							<th>
								出生日期
							</th>
							<td>
								<html:hidden name="rs" property="csrq" styleId="csrq"/>
								<html:text name="rs" property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								民族
							</th>
							<td>
								<html:hidden name="rs" property="mz" styleId="mz"/>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px" disabled="true">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<html:hidden name="rs" property="zzmm" styleId="zzmm"/>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px" disabled="true">
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
								<html:hidden name="rs" property="sfzh" styleId="sfzh"/>
								<html:text name="rs" property="sfzh" styleId="sfzh" disabled="true"
									styleClass="text_nor"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
							<th>
								培养层次
							</th>
							<td align="left">
								<html:hidden name="rs" property="pycc" styleId="pycc"/>
								<logic:equal value="new" name="edition">
									<html:select property="pycc" name="rs" styleId="pycc" disabled="true">
										<html:options collection="pyccList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="pycc" styleId="pycc" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								户口所在地
							</th>
							<td colspan="3">
								<!--地址信息取标准码-->
								<logic:present name="ssList">
									<html:hidden name="rs" property="hkshen" styleId="hkshen"/>
									<html:select name="rs" property="hkshen" styleId="hkshen" disabled="true"
										onchange="loadShi('hkshen','hkshi','hkxian');" style="width:120px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="hkshi" styleId="hkshi"/>
							<html:select name="rs" property="hkshi" styleId="hkshi" disabled="true"
										onchange="loadXian('hkshi','hkxian')" style="width:120px">
										<html:options collection="hkshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="hkxian" styleId="hkxian"/>
							<html:select name="rs" property="hkxian" styleId="hkxian" style="width:120px" disabled="true">
										<html:options collection="hkxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end地址信息取标准码-->
								<logic:notPresent name="ssList">
									<html:hidden name="rs" property="hkszd" styleId="hkszd"/>
									<html:text name="rs" property="hkszd" maxlength="60" disabled="true"
										styleId="hkszd" styleClass="text_nor" style="width:90%" />
								</logic:notPresent>
							</td>
						</tr>
						<tr>
							<th>
								籍贯
							</th>
							<td colspan="3">
									<!--地址信息取标准码-->
									<logic:present name="ssList">
										<html:hidden name="rs" property="jgshen" styleId="jgshen"/>
										<html:select name="rs" property="jgshen" styleId="jgshen" disabled="true"
											onchange="loadShi('jgshen','jgshi','jgxian');" style="width:120px">
											<html:option value="">--请选择--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="jgshi" styleId="jgshi"/>
							<html:select name="rs" property="jgshi" styleId="jgshi" disabled="true"
											onchange="loadXian('jgshi','jgxian')" style="width:120px">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="jgxian" styleId="jgxian"/>
							<html:select name="rs" property="jgxian" styleId="jgxian" style="width:120px" disabled="true">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" style="width:120px"/>
										</html:select>
									</logic:present>
									<!--end地址信息取标准码-->
									<logic:notPresent name="ssList">
										<html:hidden name="rs" property="jg" styleId="jg"/>
										<html:text name="rs" property="jg" styleId="jg" maxlength="10" disabled="true"
											styleClass="text_nor" style="width:90%" />
									</logic:notPresent>
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
									<html:hidden name="rs" property="syshen" styleId="syshen"/>
									<html:select name="rs" property="syshen" styleId="syshen" disabled="true"
										onchange="loadShi('syshen','syshi','syxian')" style="width:120px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="syshi" styleId="syshi"/>
							<html:select name="rs" property="syshi" styleId="syshi" style="width:120px" disabled="true"
										onchange="loadXian('syshi','syxian')">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="syxian" styleId="syxian"/>
							<html:select name="rs" property="syxian" styleId="syxian" style="width:120px" disabled="true">
										<html:options collection="xianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end地址信息取标准码-->
								<logic:notPresent name="ssList">
									<html:hidden name="rs" property="syd" styleId="syd"/>
									<html:text name="rs" property="syd" styleId="syd" disabled="true"
										maxlength="25" styleClass="text_nor" style="width:90%" />
								</logic:notPresent>
							</td>
						</tr>
						<!-- 广东体育职业技术学院 -->
						<logic:equal value="12578" name="xxdm">
							<tr>
								<th>
									学籍号
								</th>
								<td>
									<html:text name="rs" property="xjh" styleId="xjh"
										readonly="true" styleClass="text_nor" maxlength="40" />
								</td>
								<th>
									户口性质
								</th>
								<td colspan="2">
									<html:select property="hkxz" name="rs" styleId="hkxz">
										<html:option value="">--请选择--</html:option>
										<html:option value="农业户口">农业户口</html:option>
										<html:option value="非农业户口">非农业户口</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									加入政治面貌日期
								</th>
								<td>
									<html:text name="rs" property="jrzzmmrq" styleId="jrzzmmrq"
										onclick="return showCalendar('jrzzmmrq','y-mm-dd');"
										readonly="true" styleClass="text_nor" />
								</td>
								<th>
									是否华侨
								</th>
								<td colspan="2">
									<html:select property="sfhq" name="rs" styleId="sfhq">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									高考考生号
								</th>
								<td colspan="4">
									<html:text name="rs" property="ksh" styleId="ksh"
										readonly="true" styleClass="text_nor" maxlength="32" />
								</td>
							</tr>
							<tr>
								<th>
									出生地
								</th>
								<td colspan="4">
									<%--							<html:text name="rs" property="csd" styleId="csd" styleClass="text_nor" maxlength="60"/>--%>
									<!--地址信息取标准码-->
									<logic:present name="ssList">
										<html:select name="rs" property="csds" styleId="csds"
											onchange="loadShi('csds','csdshi','csdxian');">
											<html:option value="">--请选择--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:select name="rs" property="csdshi" styleId="csdshi"
											onchange="loadXian('csdshi','csdxian')">
											<html:options collection="csdshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:select name="rs" property="csdxian" styleId="csdxian">
											<html:options collection="csdxianList" property="xiandm"
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
						</logic:equal>
					</tbody>

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
								<html:hidden name="rs" property="nj" styleId="nj"/>
								<html:select name="rs" property="nj" styleId="nj" disabled="true"
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
								<html:hidden name="rs" property="xz" styleId="xz"/>
								<html:text name="rs" property="xz" styleId="xz" disabled="true"
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
								<html:hidden name="rs" property="xymc" styleId="xymc"/>
								<input type="text" id="xymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.xymc }" disabled="disabled"/>
								<%--<button class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									选择
								</button>
								--%><input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
							</td>
							<th>
								是否注册
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfzc" styleId="sfzc"/>
								<html:select property="sfzc" name="rs" style="width:120px" disabled="true"
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
								<html:hidden name="rs" property="zymc" styleId="zymc"/>
								<input type="text" id="zymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.zymc }" disabled="disabled"/>
								<%--<button class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									选择
								</button>
								--%><input type="hidden" name="zydm" tyleId="xydm"
									value="${rs.zydm}" />
							</td>
							<th>
								是否走读生
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfzd" styleId="sfzd"/>
								<html:select property="sfzd" name="rs" style="width:120px" disabled="true"
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
								<html:hidden name="rs" property="bjmc" styleId="bjmc"/>
								<input type="text" id="bjmc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.bjmc }" disabled="disabled"/>
								<%--<button class="btn_01" id="button_bj" style="display: "
									onclick="getXyzybj('bj');">
									选择
								</button>
								--%><input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
							</td>
							<th>
								是否在校
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfzx" styleId="sfzx"/>
								<html:select property="sfzx" name="rs" styleId="sfzx" disabled="true">
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
								<html:hidden name="rs" property="xjzt" styleId="xjzt"/>
								<html:select name="rs" property="xjzt" style="width:150" disabled="true"
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
								<html:hidden name="rs" property="sfbys" styleId="sfbys"/>
								<html:select property="sfbys" name="rs" style="width:120px" disabled="true"
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
								<html:hidden name="rs" property="rxrq" styleId="rxrq"/>
								<html:text name="rs" property="rxrq" styleId="rxrq" disabled="true"
									maxlength="10" styleClass="text_nor"
									onclick="return showCalendar('rxrq','y-mm-dd');" />
							</td>
							<th>
								是否毕业
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfyby" styleId="sfyby"/>
								<html:select property="sfyby" name="rs" styleId="sfyby" disabled="true"
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
								<html:hidden name="rs" property="byny" styleId="byny"/>
								<html:text property="byny" name="rs" styleId="byny" disabled="true"
									styleClass="text_nor" readonly="true" maxlength="10"
									onclick="return showCalendar('byny','y-mm-dd');" />
							</td>
							<th>校区信息</th>
							<td colspan="2">
								<html:hidden name="rs" property="yxdm" styleId="yxdm"/>
								<html:select name="rs" property="yxdm" style="width:150" disabled="true"
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
								联系电话
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
								<html:hidden name="rs" property="yhdm" styleId="yhdm"/>
								<html:select name="rs" property="yhdm" styleId="yhdm" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm"
										labelProperty="yhmc" />
								</html:select>
							</td>
							<th>
								银行卡号
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="yhkh" styleId="yhkh"/>
								<html:text name="rs" property="yhkh" maxlength="20" disabled="true"
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
								<html:hidden name="rs" property="kh" styleId="kh"/>
								<html:text name="rs" property="kh" styleId="kh" disabled="true"
									onkeypress="return onlyNum(this,8)" style="ime-mode:disabled"
									maxlength="20" />
							</td>
						</tr>
					</tbody>

					<%-- 新版本家庭信息与学生信息放在一块维护  --%>
					<logic:equal value="new" name="edition">
						<%@ include file="/xsxx/stu_info_family.jsp"%>
					</logic:equal>
					<%-- end --%>

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

								<logic:equal value="new" name="edition">
									<html:hidden name="rs" property="kslb" styleId="kslb"/>
									<html:select property="kslb" name="rs" styleId="kslb" disabled="true">
										<html:options collection="kslbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:hidden name="rs" property="kslb" styleId="kslb"/>
									<html:text name="rs" property="kslb" styleId="kslb" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								入学方式
							</th>
							<td align="left">
								<logic:equal value="new" name="edition">
									<html:hidden name="rs" property="rxfs" styleId="rxfs"/>
									<html:select property="rxfs" name="rs" styleId="rxfs" disabled="true">
										<html:options collection="rxfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:hidden name="rs" property="rxfs" styleId="rxfs"/>
									<html:text name="rs" property="rxfs" styleId="rxfs" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
							<th>
								培养方式
							</th>
							<td align="left" colspan="2">
								<logic:equal value="new" name="edition">
									<html:hidden name="rs" property="pyfs" styleId="pyfs"/>
									<html:select property="pyfs" name="rs" styleId="pyfs" disabled="true">
										<html:options collection="pyfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:hidden name="rs" property="pyfs" styleId="pyfs"/>
									<html:text name="rs" property="pyfs" styleId="pyfs" disabled="true"
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

					</tbody>
					<tr>
						<th>
							备注
						</th>
						<td colspan="4">
							<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%"
										rows='8' onblur="chLeng(this,2000)"/>
						</td>
					</tr>
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
										<button class="button2" id="buttonSave"
											style="height:20px;width:80px" onclick="send();">
											保 存
										</button>
									</logic:notPresent>
									<button class="button2" style="height:20px;width:80px"
										onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<!-- 上传照片 -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
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
