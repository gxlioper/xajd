<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript">
	function getValue(){
		var value="";
		var syrs=document.getElementById("xyrs").value;
		var knsbl=document.getElementById("knsbl").value;
		//alert(syrs);
		//去掉Math.round(syrs*(knsbl*0.01)); 因为这样计算不准确
		value=Math.round(syrs*(knsbl*0.01));
		document.getElementById("xyknsrs").value=value;
	}
	
	function print1(){
		var xxdm = document.getElementById('xxdm').value;
		if(xxdm == "13022"){//宁波理工
		
			var sqdw   = document.getElementById('sqdw').value;			
			var lxdh = document.getElementById('lxdh').value;
			var gznr = document.getElementById('gznr').value;
			var xyrs = document.getElementById('xyrs').value;
			var gzsj = document.getElementById('gzsj').value;
			var bz = document.getElementById('bz').value;
			var gwdm = document.getElementById('gwdm').value;
			var myqgzxsj = document.getElementById('myqgzxsj').value;
			var mssj = document.getElementById('mssj').value;
			var tsyq = document.getElementById('tsyq').value;
			var dwzp = document.getElementById('dwzp').value;
			var rylsqk = document.getElementById('rylsqk').value;
			var gwxz = document.getElementById('gwxz').options[document.getElementById('gwxz').selectedIndex].text;
			var fzr = document.getElementById('lxr').value;
		
			var url = "/xgxt/qgzxNblg.do?method=printGwsqb";
			if(gwxz !=null && gwxz.indexOf('固定')>-1){
				gwxz = 'gdgw';
			}else if (gwxz !=null && gwxz.indexOf('临时')>-1){
				gwxz = 'lsgw';
			}else {
				alert('岗位性质为必选！');
				return false;
			}
			
			url += "&sqdw=";
			url += sqdw;
			url += "&lxdh=";
			url += lxdh;
			url += "&gznr=";
			url += gznr;
			url += "&xyrs=";
			url += xyrs;
			url += "&gzsj=";
			url += gzsj;
			url += "&bz=";
			url += bz;
			url += "&gwdm=";
			url += gwdm;
			url += "&myqgzxsj=";
			url += myqgzxsj;
			url += "&mssj=";
			url += mssj;
			url += "&tsyq=";
			url += tsyq;
			url += "&dwzp=";
			url += dwzp;
			url += "&rylsqk=";
			url += rylsqk;
			url += "&gwxz=";
			url += gwxz;
			url += "&lxr=";
			url+= fzr;			
			window.open(url);
		}else{
			expAppTab('rsT','勤工助学岗位申报表','');
		}
	}
	
	function save(){
		var gznr = document.getElementById('gznr').value;
		var ryyq = document.getElementById('ryyq').value;
		if($('sqbg')){
		var sqbg = document.getElementById('sqbg').value;
		//申请报告
		if(sqbg != null && sqbg != ''){
			if(sqbg.replace(/[^\u0000-\u00ff]/g, "**").length > 125){	         
	          	alert("申请报告不能大于125个字符");
	          	return false;
	       }
		}
		}
		var bz = document.getElementById('bz').value;
		//工作内容
		if(gznr != null && gznr != ''){
			if(gznr.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          	alert("工作内容不能大于150个字符");
	          	return false;
	       }
		}
		// 人员要求
		if(ryyq != null && ryyq != ''){
			if(ryyq.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          	alert("人员要求不能大于100个字符");
	          	return false;
	       }
		}
		
		//备注
		if(bz != null && bz != ''){
			if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          	alert("备注不能大于60个字符");
	          	return false;
	       }
		}
		
		var xxdm='${xxdm}';
		var btzd='gwdm-sqdw-xyrs-jcfs-jybcbz-gznr';
		if(xxdm=="10653"){
			 btzd='gwdm-sqdw-xyrs-jcfs-jybcbz-gznr-gzkssj';
		}
		
		if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=',btzd)) 
			BatAlert.showTips('正在操作中，请稍等...'); 
	}
	
	function checkGwExists(){
		var pkValue=$("pkValue").value;
		var doType=$("doType").value;
		if("modi"!=doType){
			cqkjFunc.checkGwExists(pkValue,function(data){
				if("true"==data){
					if(confirm("岗位信息已存在是否修改?")){
						if(checkGwgzsjTime()){save()};
					}
				}else{
					if(checkGwgzsjTime()){save()};
				}
			});
		}else{
			if(checkGwgzsjTime()){save()};
		}
		
	}
	</script>
</head>
<body>
	<html:form action="/comm_pub" method="post">
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		<logic:present name="gwsbsj">
		<input type="hidden" id="gwsbsj" name="gwsbsj" value="${gwsbsj}" />
		</logic:present>
		
		<!-- 浙江工业大学之江学院 -->
<%--		<logic:equal value="13275" name="xxdm">--%>
			<input type="hidden" id="kssj" name="kssj" value="${sqsjRs.kssj}"/>
			<input type="hidden" id="jssj" name="jssj" value="${sqsjRs.jssj}"/>
<%--		</logic:equal>--%>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em>
				<a>
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
						学生义工 - 岗位发布 - 岗位信息发布
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						勤工助学 - 岗位发布 - 岗位信息发布
					</logic:notEqual>
				</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		
		<div>
		   <table border="0" align="center" style="width: 100%;">
		   <tr>
		   <td>
<%--		   <logic:equal value="13275" name="xxdm">--%>
		   <div class="prompt" id="div_help">
		       <h3><span>提示：</span></h3>
		       <p>同时满足以下3个条件的岗位为“有效岗位”，否则为“无效岗位”。学生不可以申请“无效岗位”！<br/>
				1.当前系统时间需在该岗位的“工作结束日期”之前；<br/>
				2.审核通过；<br/>
				3.当前系统时间需在该岗位的“申请开始时间”和“申请结束时间”之间。
			   </p>
				<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		   	</div>
<%--		   </logic:equal>--%>
<%--		   <logic:notEqual value="13275" name="xxdm">--%>
<%--		   <div class="prompt">--%>
<%--		       <h3><span>提示：</span></h3>--%>
<%--		       <p>同时满足以下2个条件的岗位为“有效岗位”，否则为“无效岗位”。学生不可以申请“无效岗位”！<br/>--%>
<%--				1.当前系统时间需在该岗位的“工作结束日期”之前；<br/>--%>
<%--				2.审核通过。--%>
<%--			   </p>--%>
<%--		   	</div>--%>
<%--		   </logic:notEqual>--%>
		    
		   	</td>
		   	</tr>
		   	</table>
		 </div>
		 
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				有错误发生！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="knsbl" name="knsbl" value="<bean:write name="rs" property="knsbl"/>" />
			<input type="hidden" id="xueqi" name="xueqi" value="<bean:write name="rs" property="xueqi"/>" />
			<input type="hidden" id="url" name="url" value="/sjcz/gwxxb.jsp" />
		<div class="tab">
		  <table width="100%" class="formlist" id="rsT" border="1">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>校区</th>
					<td>
						<html:select name="rs" property="xqdm" style="width:120px"
							styleId="xqdm">
							<!-- 浙江工业大学之江学院 -->
<%--							<logic:notEqual value="13275" name="xxdm">--%>
<%--								<html:option value=""></html:option>--%>
<%--							</logic:notEqual>--%>
							<html:options collection="xqList1" property="dm"
								labelProperty="xqmc" />
						</html:select>
					</td>
					<th><span class="red">*</span>岗位名称</th>
					<td height="22" align="left">
						<%--重庆科技学院岗位名称可维护--%>
						<logic:equal value="11551" name="xxdm">
							<html:select property="gwdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="gwmcList" property="gwdm"
									labelProperty="gwmc" />
							</html:select>
						</logic:equal>
						
						<%--信阳师范学院岗位名称可维护--%>
						<logic:equal value="10477" name="xxdm">
							<html:select property="gwdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="gwmcList" property="gwmc"
									labelProperty="gwmc" />
							</html:select>
						</logic:equal>

						<logic:notEqual value="11551" name="xxdm">
							  <logic:notEqual value="10477" name="xxdm">
									<logic:notEqual value="modi" name="doType">
										<html:text name="rs" property="gwdm" styleId="gwdm"
											onkeyup="value=value.replace('-','');replaceStr(this)" maxlength="20" />
									</logic:notEqual>
									<logic:equal value="modi" name="doType">
										<html:text name="rs" property="gwdm" styleId="gwdm"
											readonly="true"  maxlength="20"/>
									</logic:equal>
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>岗位性质</th>
					<td>
						<html:select name="rs" property="gwxz" style="width:120px"
							styleId="gwxz">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm"
								labelProperty="gwxzmc" />
						</html:select>
					</td>
					<th><span class="red">*</span>用人单位</th>
					<td>
						<html:select name="rs" property="sqdw" styleId="sqdw"
							style="width:120px" onchange="getYrdwInfo()">
							<html:option value=""></html:option>
							<html:options collection="sqdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:text name="rs" property="xn" style="width: 90px"
							readonly="true" />
					</td>
					<th>年度</th>
					<td>
						<html:text name="rs" property="nd" style="width: 90px"
							readonly="true" />

					</td>

				</tr>
				<tr>
					<th>
						<logic:equal name="xxdm" value="10653">
						<span class="red">*</span>
						</logic:equal>
						工作开始日期
					</th>
					<td>
						<html:text name='rs' property="gzkssj" styleId="gzkssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzkssj','y-mm-dd');" />
					</td>
					<th>工作结束日期</th>
					<td>
						<html:text name='rs' property="gzjssj" styleId="gzjssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzjssj','y-mm-dd');" />
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>需求人数</th>
					<td height="22" align="left">
						<!-------------重庆科技学院----------------->
						<logic:equal value="11551" name="xxdm">
							<input type="text" id="xyrs" name="xyrs" maxlength="3" onkeyup="value=value.replace(/[^\d|.]/g,'') "
								value="${rs.sqsyrs}"/>
						</logic:equal>
						<logic:notEqual value="11551" name="xxdm">
							<html:text name="rs" property="xyrs" styleId="xyrs" maxlength="3" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
						</logic:notEqual>
					</td>
					<th>使用困难生数</th>
					<logic:notEqual value="11417" name="xxdm">
						<td >
							<!-------------重庆科技学院----------------->
							<logic:equal value="11551" name="xxdm">
								<input type="text" id="xyknsrs" name="xyknsrs" maxlength="3" onkeyup="value=value.replace(/[^\d|.]/g,'') "
									value="${rs.sqsyknss}"/>
							</logic:equal>
							<logic:notEqual value="11551" name="xxdm">
								<!-- 长沙民政 -->
								<logic:equal value="10827" name="xxdm">
									<input type="text" name="xyknsrs" id="xyknsrs" maxlength="3" onkeyup="value=value.replace(/[^\d|.]/g,'') "
										value="<bean:write name='xyknsrs'/>" readonly="readonly" />
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<html:text name="rs" property="xyknsrs" styleId="xyknsrs" maxlength="3"
									onkeyup="value=value.replace(/[^\d]/g,'') "/>
								</logic:notEqual>
							</logic:notEqual>
						</td>
					</logic:notEqual>
					<%--北京联合大学--%>
					<logic:equal value="11417" name="xxdm">
						<td>
							<html:text name="rs" property="xyknsrs" styleId="xyknsrs"
								onfocus="getValue();" />
						</td>
					</logic:equal>
				</tr>
				<tr>
					<th><span class="red">*</span>计酬方式</th>
					<td>
						<logic:notEqual value="11417" name="xxdm">
							<html:select name="rs" property="jcfs" onchange="subloadPost();loadJcbz(this.value)">
								<html:options collection="jcfsList" property="dm" labelProperty="mc"/>
								<!-- 长沙民政 -->
								<logic:equal value="10827" name="xxdm">
									<html:option value="n">志愿服务</html:option>
								</logic:equal>
							</html:select>
						</logic:notEqual>
						<!--北京联合大学-->
						<logic:equal value="11417" name="xxdm">
							<input type="text" value="按小时" readonly="true" />
						</logic:equal>
						<!--北京联合大学-->
					</td>
					<logic:notEqual value="11417" name="xxdm">
						<th><span class="red">*</span>建议报酬标准</th>
						<td>
							<!-- 重庆科技学院 -->
							<logic:equal value="11551" name="xxdm">
								<input type="text" id="jybcbz" name="jybcbz" maxlength="20" value="${rs.spbcbz}" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
							</logic:equal>

							<logic:notEqual value="11551" name="xxdm">
								<logic:equal value="10827" name="xxdm">
									<logic:equal value="0" name="rs" property="jybcbz">
										<input type="text" name="jybcbz" id="jybcbz" value="0" maxlength="20" readonly="true"/>
									</logic:equal>
									<logic:notEqual value="0" name="rs" property="jybcbz">
										<html:text name="rs" property="jybcbz" styleId="jybcbz" maxlength="20" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<html:text name="rs" property="jybcbz" styleId="jybcbz" maxlength="20" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
								</logic:notEqual>
							</logic:notEqual>
							<span id="jybcbzDw"></span>
						</td>
					</logic:notEqual>
					<logic:equal value="11417" name="xxdm">
						<th><span class="red">*</span>报酬标准</th>
						<td>
							<html:text name="rs" property="mxsbc" styleId="mxsbc"
								readonly="true" />
							元/小时
						</td>
					</logic:equal>
				</tr>
				<%--非重庆科技学院--%>
				<logic:notEqual value="11551" name="xxdm">
					<%--非浙江机电职业技术学院--%>
					<logic:notEqual value="12861" name="xxdm">
						<tr>
							<th>工作时间</th>
							<%--非北京联合大学--%>
							<logic:notEqual value="11417" name="xxdm">
								<td>
									<html:text name="rs" property="gzsj" styleId="gzsj" maxlength="20"/>
									(例：周一上，周二下...)
									<span id="gzsjDw"></span>
								</td>
							</logic:notEqual>
							<%--北京联合大学--%>
							<logic:equal value="11417" name="xxdm">
								<td>
									<html:select property="gzsj" styleId="gzsj" name="rs">
										<html:option value=""></html:option>
										<html:option value="临时">临时</html:option>
										<html:option value="正常工作日">正常工作日</html:option>
										<html:option value="节假日">节假日</html:option>
										<html:option value="寒暑假">寒暑假</html:option>
									</html:select>
								</td>
							</logic:equal>
							<%--非北京联合大学--%>
							<logic:notEqual value="11417" name="xxdm">
								<th>联系电话</th>
								<td>
									<html:text name="rs" property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "
										maxlength="15" />
								</td>
							</logic:notEqual>
							<%--北京联合大学--%>
							<logic:equal value="11417" name="xxdm">
								<th>联系人</th>
								<td>
									<html:text name="rs" property="lxr" styleId="lxr"
										readonly="true" />
								</td>
							</logic:equal>
						</tr>
<%--						<logic:equal value="13275" name="xxdm">--%>
							<tr>
								<th>
									<span class="red">*</span>申请开始时间
								</th>
								<td>
									<input type="hidden" name="sqkssj" id="sqkssj" value="" />
									<input type="text" readonly style="cursor:hand;width:80px"
										onclick="return showCalendar('sqkssj1','y-mm-dd');"
										value="<logic:empty name="rs" property="sqkssj1">${sqsjRs.kssj1 }</logic:empty><logic:notEmpty name="rs" property="sqkssj1"><bean:write name="rs" property="sqkssj1"/></logic:notEmpty>" name="sqkssj1"
										id="sqkssj1" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
									－
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<logic:empty name="rs" property="sqkssj2">${sqsjRs.kssj2 }</logic:empty><logic:notEmpty name="rs" property="sqkssj2"><bean:write name="rs" property="sqkssj2"/></logic:notEmpty>"
										name="sqkssj2" id="sqkssj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<logic:empty name="rs" property="sqkssj3">${sqsjRs.kssj3 }</logic:empty><logic:notEmpty name="rs" property="sqkssj3"><bean:write name="rs" property="sqkssj3"/></logic:notEmpty>"
										name="sqkssj3" id="sqkssj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<logic:empty name="rs" property="sqkssj4">${sqsjRs.kssj4 }</logic:empty><logic:notEmpty name="rs" property="sqkssj4"><bean:write name="rs" property="sqkssj4"/></logic:notEmpty>"
										name="sqkssj4" id="sqkssj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								</td>
								<th>
									<span class="red">*</span>申请结束时间
								</th>
								<td>
									<input type="hidden" name="sqjssj" id="sqjssj" value="" />
									<input type="text" readonly style="cursor:hand;width:80px"
										onclick="return showCalendar('sqjssj1','y-mm-dd');"
										value="<logic:empty name="rs" property="sqjssj1">${sqsjRs.jssj1 }</logic:empty><logic:notEmpty name="rs" property="sqjssj1"><bean:write name="rs" property="sqjssj1"/></logic:notEmpty>" name="sqjssj1"
										id="sqjssj1" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
									－
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<logic:empty name="rs" property="sqjssj2">${sqsjRs.jssj2 }</logic:empty><logic:notEmpty name="rs" property="sqjssj2"><bean:write name="rs" property="sqjssj2"/></logic:notEmpty>"
										name="sqjssj2" id="sqjssj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<logic:empty name="rs" property="sqjssj3">${sqsjRs.jssj3 }</logic:empty><logic:notEmpty name="rs" property="sqjssj3"><bean:write name="rs" property="sqjssj3"/></logic:notEmpty>"
										name="sqjssj3" id="sqjssj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<logic:empty name="rs" property="sqjssj4">${sqsjRs.jssj4 }</logic:empty><logic:notEmpty name="rs" property="sqjssj4"><bean:write name="rs" property="sqjssj4"/></logic:notEmpty>"
										name="sqjssj4" id="sqjssj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								</td>
							</tr>
<%--						</logic:equal>--%>
					</logic:notEqual>

					<!--中国地质大学-->
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>负责人</th>
							<td colspan="3">
								<html:text name="rs" property="fzr" styleId="fzr" maxlength="10"/>
							</td>
						</tr>
					</logic:equal>
					<!--end中国地质大学-->

					<%--浙江机电职业技术学院--%>
					<logic:equal value="12861" name="xxdm">
						<tr>
							<th>联系电话</th>
							<td colspan="3">
								<html:text name="rs" property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "
									maxlength="15" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>工作时间</th>
							<td colspan="3">
									<html:textarea name="rs" property="gzsj" styleId="gzsj" style="width:100%;height:80px"/>
									(例：周一上，周二下...)
									<span id="gzsjDw"></span>
								</td>
						</tr>
					</logic:equal>
					<%--中国地质大学--%>
					<logic:equal value="10491" name="xxdm">
						<tr>
							<th>工作地点</th>
							<td colspan="3">
								<html:text name="rs" property="gzdd" styleId="gzdd"
									style="width:50%" maxlength="150" />
								(例：图书馆)
							</td>
						</tr>
					</logic:equal>
					<!-- 井冈山大学 -->
					<logic:equal value="10419" name="xxdm">
						<tr>
							<th>工作地点</th>
							<td colspan="3">
								<html:text property="gzdd" name="rs" style="width:100%"
									styleId="gzdd" />
							</td>
						</tr>
						<tr>
							<th>学生审核条件设置</th>
							<td colspan="3">
								<logic:iterate id="jgsrs" name="jgsshList" indexId="index">
									<input type="checkbox" value=""
										id="<bean:write name="jgsrs"  property="key"/>"
										name="<bean:write name="jgsrs" property="key"/>" />
									<bean:write name="jgsrs" property="columnName" />
								</logic:iterate>
							</td>
						</tr>
					</logic:equal>
					<!-- 井冈山大学 (是否是显示详细数据) -->
					<logic:present name="jgsTempSQL">
						<input type="hidden" id="jgsTempSQLNum" value="${jgsTempSQLNum}" />
						<logic:iterate id="jgsSql" name="jgsTempSQL" indexId="index">
							<input type="hidden" id="tmpkey${index}"
								value="<bean:write name="jgsSql"/>" />
						</logic:iterate>
						<script>
						var length = document.getElementById('jgsTempSQLNum').value;
						for(var i=0; i<parseInt(length); i++){
							var id =  document.getElementById("tmpkey"+i).value;
							if(id == "1"){
								document.getElementById("key" + i).checked="checked";
							}
							
						}
					</script>
					</logic:present>
					<%--浙江大学宁波理工学院--%>
					<logic:equal value="13022" name="xxdm">
						<tr>
							<th>每月勤工助学时间</th>
							<td>
								<html:text name="rs" property="myqgzxsj" styleId="myqgzxsj" />
							</td>
							<th>考核人</th>
							<td>
								<html:text name="rs" property="fzr" styleId="lxr"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>特殊要求</th>
							<td>
								<html:text name="rs" property="tsyq" styleId="tsyq" />
							</td>
							<th>是否需学生处勤工助学中心代为招聘</th>
							<td>
								<html:select name="rs" property="dwzp" onchange="subloadPost()"
									styleId="dwzp" style="width:60px">
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>招聘面试时间</th>
							<td colspan="3">
								<html:text name='rs' property="mssj" styleId="msj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('mssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>人员落实情况</th>
							<td colspan="3">
								<html:textarea name="rs" property="rylsqk" cols="80"
									rows="4" styleId="rylsqk"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th><span class="red">*</span>工作内容</th>
						<td colspan="3">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								cols="80" rows="5" onblur="chLeng(this,'150')"></html:textarea>
						</td>
					</tr>
				</logic:notEqual>

				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>联系人</th>
						<td>
							<html:text name="rs" property="lxr" styleId="lxr"
								readonly="true" />
						</td>
						<th>联系电话</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>自筹经费</th>
						<td>
							<html:text name="rs" property="zcjf" styleId="zcjf" />
						</td>
						<th></th>
						<td>
						</td>
					</tr>

					<tr>
						<th><span class="red">*</span>工作时间
							<input type="hidden" id="gzsj" name="gzsj" />
						</th>

						<logic:present name="whkxList">
							<td colspan="3">
								<table id="tbSj" class="formlist">
									<tr>
										<th>
											时间
										</th>
										<th>
											星期一
										</th>
										<th>
											星期二
										</th>
										<th>
											星期三
										</th>
										<th>
											星期四
										</th>
										<th>
											星期五
										</th>
										<th>
											星期六
										</th>
										<th>
											星期日
										</th>
									</tr>
									<logic:iterate id="kxsj" name="whkxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
							<!-- begin 获取已经存在的空闲时间信息 -->
							<logic:present name="kxbz">
								<input type="hidden" id="kxbzNum"
									value="<bean:write name="kxbzNum"/>" />
								<logic:iterate id="kxinfo" name="kxbz" indexId="index">
									<input type="hidden" id="kxinfo${index}"
										value="${kxinfo.xq}${kxinfo.sj}" />
								</logic:iterate>
								<script>
						var length = document.getElementById('kxbzNum').value;
						for(var i=0; i<parseInt(length); i++){
							var id =  document.getElementById("kxinfo"+i).value;
							document.getElementById(id).checked="checked";
						}
					</script>
							</logic:present>
							<!-- end 获取已经存在的空闲时间信息 -->

						</logic:present>
					</tr>
				</logic:equal>
				<tr>
					<th>岗位要求</th>
					<td colspan="3">
						<html:textarea name="rs" 
						               property="gwtsyq" 
						               cols="80"
						               rows="5" 
						               styleId="gwtsyq" 
						               onblur="chLeng(this,'100')"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>人员要求</th>
					<td colspan="3">
						<html:textarea name="rs" property="ryyq" cols="80" rows="5" styleId="ryyq" onblur="chLeng(this,'100')"></html:textarea>
					</td>
				</tr>
				<logic:notPresent name="showshgc">
					<%--重庆科技学院--%>
					<logic:notEqual value="11551" name="xxdm">
						<%--浙江机电职业技术学院--%>
						<logic:notEqual value="12861" name="xxdm">
							<tr>
								<th>申请报告</th>
								<td colspan="3">
									<html:textarea name="rs" property="sqbg" cols="80" rows="5" styleId="sqbg" onblur="chLeng(this,'125')"></html:textarea>
								</td>
							</tr>
						</logic:notEqual>
					</logic:notEqual>
				</logic:notPresent>
				<%--重庆科技学院--%>
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>申请原因及单位意见</th>
						<td colspan="3"/>
							<html:textarea name="rs" property="sqdwyj" cols="80"
								rows="5"></html:textarea>
						</td>
					</tr>
				</logic:equal>
				<%--end重庆科技学院--%>
				<!--非重庆科技学院-->
				<logic:notEqual value="11551" name="xxdm">
					<!--非淮阴工学院-->
					<logic:notEqual value="11049" name="xxdm">
						<tr>
							<th>用人单位意见</th>
							<td colspan="3">
								<html:textarea name="rs" property="sqdwyj" cols="80" onblur="chLeng(this,'100')"
									rows="5"></html:textarea>
							</td>
						</tr>
					</logic:notEqual>
					<!--end非淮阴工学院-->	
				</logic:notEqual>
				<!--end非重庆科技学院-->
				
				<tr>
					<th>备注</th>
					<td colspan="3">
						<html:textarea name="rs" property="bz" cols="80" rows="5" styleId="bz" onblur="chLeng(this,'60')"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz noPrin">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						<logic:match value="yes" name="writeAble">
			            <!-- 重庆科技学院 -->
						<logic:equal value="11551" name="xxdm">
							<%--重庆科技学院岗位审核通过后不可以再修改--%>
							<logic:present name="cModify">
								<button type="button" class="button2" style="width:80px" id="buttonSave" disabled="disabled">
									保 存
								</button>
							</logic:present>

							<logic:notPresent name="cModify">
								<%--长沙民政--%>
								<logic:equal value="10827" name="xxdm">
									<button type="button" class="button2"
										onclick="if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gzjssj')) BatAlert.showTips('正在操作中，请稍等...');"
										style="width:80px" id="buttonSave">
										保 存
									</button>
								</logic:equal>
								<%--end长沙民政--%>
								<logic:notEqual value="10827" name="xxdm">
									<button type="button" class="button2"
										onclick="if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gzjssj')) BatAlert.showTips('正在操作中，请稍等...');"
										style="width:80px" id="buttonSave">
										保 存
									</button>
								</logic:notEqual>
							</logic:notPresent>

						</logic:equal>

						<logic:notEqual value="11551" name="xxdm">
							<%-- 井冈山大学 --%>
							<logic:equal value="10419" name="xxdm">
								<logic:present name="cModify">
									<button type="button" class="button2" style="width:80px" id="buttonSave"
										disabled="disabled">
										保 存
									</button>
								</logic:present>
								<logic:notPresent name="cModify">
									<button type="button" class="button2"
										onclick="if(checkGwgzsjTime()){if(dataDoSavePubGw('/xgxt/comm_pub.do?doType=save&tableName=view_gwxx&Value=','gwdm-sqdw-xyrs-xyknsrs-jcfs-jybcbz-gznr-gzjssj-gzsj')) BatAlert.showTips('正在操作中，请稍等...'); }"
										style="width:80px" id="buttonSave">
										保 存
									</button>
								</logic:notPresent>
							</logic:equal>
							<%-- 非井冈山大学 --%>
							
							<logic:notEqual value="10419" name="xxdm">
								<button type="button" class="button2"
									onclick="checkGwExists()"
									style="width:80px" id="buttonSave">
									保 存
								</button>
							</logic:notEqual>
						</logic:notEqual>

						<logic:present name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
								onclick="printReport('qgzx_bb_gwsbb.do?gwdm=')">
								打 印 预 览 
							</button>
						</logic:present>

						<logic:equal value="12742" name="xxdm">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="xlsDown/qgzx_gdnzzy_gwxxb_ls.doc" target="_blank">临时岗位申请表下载</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="xlsDown/qgzx_gdnzzy_gwxxb_gd.doc" target="_blank">固定岗位申请表下载</a>
						</logic:equal>
						</logic:match>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			<!--非长沙民政职业技术学院-->
			<logic:notEqual value="10827" name="xxdm">
				<span class="red">提示:岗位使用困难生比例不得低于${rs.knsbl}%</span>
			</logic:notEqual>
			<!--end非长沙民政职业技术学院-->
		</logic:notEmpty>
	</html:form>
</body>
</html>
