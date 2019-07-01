<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript">
	function jd(){
		if($("jd")){
			$("jd").focus();
		}
	}
	function saveFdyxx(mustFill,dzyx){
		var eles = mustFill.split("-");
    	var zgh=document.getElementById("xh").value;
    	var doType=document.getElementById("doType").value;
		for(i = 0;i<eles.length;i++){
			if(document.getElementById(eles[i]).value == ""){
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
		if(document.getElementById(dzyx).value!=""){
		   if(isEmail(document.getElementById(dzyx).value) == false){
				alert("您的邮箱地址出错了");
				return false;
			}
		}
		if($("fdyz")){
			if($("fdyz").value == ""){
				alert("请确认该辅导员所属组别！");
				return false;
			}
		}
		if($("zyzz")){
			if (document.getElementById("zyzz").value.length >2000) {
			alert("主要职责的长度不能超过2000个汉字!");
			return false;
			}
		}
		if (document.getElementById("grhjqk").value.length >2000) {
			alert("个人获奖情况的长度不能超过2000个汉字!");
			return false;
		}
		if($("fblw")){
		if (document.getElementById("fblw").value.length >2000) {
			alert("发表论文的长度不能超过2000个汉字!");
			return false;
		}
		}
		if($("kyjl")){
		if (document.getElementById("kyjl").value.length >2000) {
			alert("科研经历的长度不能超过2000个汉字!");
			return false;
		}
		}
		if (document.getElementById("gzjl").value.length >2000) {
			alert("工作经历的长度不能超过2000个汉字!");
			return false;
		}
		if (document.getElementById("bz").value.length >2000) {
			alert("备注的长度不能超过2000个汉字!");
			return false;
		}
		if(doType=="add"){	
	    getXjydInfo.getColumnEx("fdyxxb","zgh",zgh,function(data){				
			if(data==true){
				alert('该职工号已存在！');
				document.getElementById("xh").focus();
				return false;
			}else{
			   refreshForm("/xgxt/fdyxxOne.do?act=save");
			}
	});
	}else{
	    refreshForm("/xgxt/fdyxxOne.do?act=save");
	}	
	} 
	
	function writable()
	{
		if($("userType")){
			var userType =document.all['userType'].value;
			if("xy"==userType){
				if(document.forms[0].buttonModi){
					document.forms[0].buttonModi.style.display = "none";
				}
			}
			else if("xx"==userType||"admin"==userType){
				if(document.forms[0].buttonModi){
					document.forms[0].buttonModi.style.display = "none";
				}
			}
		}
	}
	
	function xlzddis(){
		var xlVal = '';
		if($("xl")){
			xlVal = $("xl").value;
		}
		if($("xxdm").value=="10338"){
		if($("sssdis") && $("bssdis")){
			if(xlVal=="硕士"){
				$("sssdis").style.display = "";
				$("bssdis").style.display = "none";
			}else if(xlVal=="博士"||xlVal=="博士后"){
				$("sssdis").style.display = "";
				$("bssdis").style.display = "";
			}else{
				$("sssdis").style.display = "none";
				$("bssdis").style.display = "none";
			}
		}
		}
	}
	
	function getQtfgnjValue(){
  	 	 var getSelectValue = document.getElementById("qtfgnjChoose").value;
  	 	 for(var i = 0;i<document.getElementById("qtfgnjChoose").options.length;i++){
  	 	 	if(document.getElementById("qtfgnjChoose").options[i].value ==getSelectValue){
  	 	 		document.getElementById("qtfgnjChoose").options[i]=null;
  	 	 	}
  	 	 }
  	 	 if(document.getElementById("qtfgnj").value.length==0){
  		 	document.getElementById("qtfgnj").value = getSelectValue;
  		 }else{
  		 	document.getElementById("qtfgnj").value += ",";
  		 	document.getElementById("qtfgnj").value += getSelectValue;
  		 }         
	}
	
	function qtfgnjOptions(){
		if($("fgnj")){
			var getSelectValue = document.getElementById("fgnj").value;
			if($('qtfgnjChoose')){
		  	 	 for(var i = 0;i<document.getElementById("qtfgnjChoose").options.length;i++){
		  	 	 	if(document.getElementById("qtfgnjChoose").options[i].value ==getSelectValue){
		  	 	 		document.getElementById("qtfgnjChoose").options[i]=null;
		  	 	 	}
		  	 	 }
	  	 	 }
  	 	 }
	}
	
	function addZpsc(url) {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 400;
	var d_height_top = 150;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 4;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; '>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<iframe name='mainFrame' style='height:100%; width:100%; ' marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' src='";
	dd_html += url;
	dd_html += "'></iframe>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}	

</script>
	</head>
	<body  onload="jd();xyDisabled('szbm');writable();qtfgnjOptions();xlzddis();" >
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>

		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/data_search" method="post">
			<input type="hidden" name ="xxdm" id = "xxdm" value="<bean:write name="xxdm" />"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<logic:present name="added">
				<logic:present name="ddxg">
				<logic:equal value="ok" name="added">
					<script>
			          alert("操作成功！");
					  refreshForm("fdyxxOne.do?act=view&type=one");
			        </script>
				</logic:equal>
				<logic:equal value="no" name="added">
					<script>
			          alert("操作失败！");
					  refreshForm("fdyxxOne.do?act=view&type=one");
			        </script>
				</logic:equal>
				</logic:present>
				<logic:notPresent name="ddxg">
				<logic:equal value="ok" name="added">
					<script>
			          alert("操作成功！");
					  dialogArgumentsQueryChick();
			          Close();
			        </script>
				</logic:equal>
				<logic:equal value="no" name="added">
					<script>
			          alert("操作失败！");
					  dialogArgumentsQueryChick();
			          Close();
			        </script>
				</logic:equal>
				</logic:notPresent>
			</logic:present>
			<logic:present name="ffdy">
				<script>
				alert("您不是辅导员，不能修改登记自己的信息!");
    		 	location.href="about:blank";
    		 	 </script>
			</logic:present>
			<logic:notPresent name="added">
				<logic:empty name="rs">
					<p align="center">
						有错误发生！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
					    alert("您输入的学号无效!");
					    </script>
					</logic:equal>
					<input type="hidden" id="doType" name="doType"
						value="${doType }" />
					<input type="hidden" id="userType" name="userType" value="${userType }" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="${pkValue }" />
					<input type="hidden" id="disableEle" name="disableEle" value="" />
					<input type="hidden" id="readonlyEle" name="readonlyEle"
						value="zgh" />
					<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
					<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
					<logic:present name="isAhxgOne">  
						<input type="hidden" id="xsgzyjfx" name="xsgzyjfx" value="<bean:write name='rs' property="xsgzyjfx"/>" />
						<input type="hidden" id="zyzz" name="zyzz" value="<bean:write name='rs' property="zyzz"/>" />
					</logic:present>
					<logic:present name="ddxg">
						<input type="hidden" id="ddxg" name="ddxg" value="yes" />
					</logic:present>
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>字段维护</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
							<tr>
								<logic:present name="ddxg">
									<th align="right">
										<font color="red">*</font>工号
									</th>
									<td align="left">
										<html:text name='rs' property="zgh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</td>
								</logic:present>
								<logic:notPresent name="ddxg">
								<th align="right">
									<font color="red">*</font>工号
								</th>
								<td align="left">
									<logic:equal value="add" name="doType">
									<html:text name='rs' property="zgh" maxlength="20"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</logic:equal>
									<logic:notEqual value="add" name="doType">
										<html:text name='rs' property="zgh"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
									</logic:notEqual>
								</td>
								</logic:notPresent>
								<th align="right">
									<font color="red">*</font>姓名
								</th>
								<td align="left">
									<html:text name='rs' property="xm" styleId="xm" maxlength="20" />
									<input name="buttonFindStu" type="hidden" />
								</td>
								<th align="left" width="15%" rowspan="6">
									<img style="width:120px;height:160px" src="<%=request.getContextPath()%>/teaPic.jsp?zgh=<bean:write name="rs" property="zgh" />"
													border="0" align="absmiddle" />
									<div align="center">
									<logic:notEqual value="add" name="doType">
									<button type="button"  onclick="addZpsc('uploadPicture.do?method=uploadPicture&type=fdy&id=<bean:write name='rs' property="zgh"/>')"
											style="width:100px" id="buttonSave">
											上传照片
									</button>
									</logic:notEqual>
									</div>
								</th>
							</tr>
							<tr>
								<th align="right">
									性别
								</th>
								<td align="left">
									<html:select name='rs' property="xb" styleId="xb">
										<html:option value=""></html:option>
										<html:options collection="xbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<logic:notEqual name="xxdm" value="10290" scope="session">
								<th align="right">
									<font color="red">*</font>联系电话
								</th>
								<td align="left">
									<html:text name='rs' property="lxdh" maxlength="20" onkeyup="chkIsNum(this);" styleId="lxdh" />
								</td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="10290" scope="session">
								<th align="right">
									办公电话
								</th>
								<td align="left">
									<html:text name='rs' property="bgdh" onkeyup="chkIsNum(this);" maxlength="15" styleId="bgdh" />
								</td>
								</logic:equal>
							</tr>
							<tr>
								<th align="right">
									移动电话
								</th>
								<td align="left">
									<html:text name='rs' property="yddh" styleId="yddh" onkeyup="chkIsNum(this);" maxlength="20"/>
								</td>
								<th align="right">
									电子邮箱
								</th>
								<td align="left">
									<html:text name='rs' property="dzyx" styleId="dzyx" maxlength="50" onblur="if(!isEmail(this.value) && this.value != ''){this.value='';alert('email格式不正确！');}"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									民族
								</th>
								<td align="left">
									<html:select name="rs" property="mz" style="width:140px"
										styleId="mz">
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>
								</td>
								<th align="right">
									籍贯
								</th>
								<td align="left">
									<html:select name="rs" property="jg" style="width:100px"
										styleId="zwdm">
										<html:options collection="sfList" property="sfdm"
											labelProperty="sfmc" />
									</html:select>
								</td>
							</tr>
							<tr>
		    					<th width="16%" height="20%"><div align="right">出生日期</div></th>
		    					<td width="37%" height="20%"><html:text name='rs' property="csrq" styleId="csrq" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('csrq','y-mm-dd');" />			
								</td>
		 						<th width="16%" height="20%"><div align="right">上岗日期</div></th>
		    					<td width="37%" height="20%"><html:text name='rs' property="lxgzsj" styleId="lxgzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('lxgzsj','y-mm-dd');" />			
								</td>
							</tr>
							<tr>
								<th align="right">
									政治面貌
								</th>
								<td align="left">
									<html:select name='rs' property="zzmm" styleId="zzmm">
										<html:option value=""></html:option>
										<html:options collection="JsZzmmList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<logic:equal name = "xxdm" value = "10338" scope="session">
								<th align="right">
									学历
								</th>
								<td align="left">
									<html:select name='rs' property="xl" styleId="xl" onchange="xlzddis()">
										<html:option value=""></html:option>
										<html:options collection="JsXlList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								</logic:equal>
								<logic:notEqual name = "xxdm" value = "10338" scope="session">
								<th align="right">
									学历
								</th>
								<td align="left">
									<html:select name='rs' property="xl" styleId="xl">
										<html:option value=""></html:option>
										<html:options collection="JsXlList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								</logic:notEqual>
							</tr>
							<logic:present name="isZZSF">
							    <logic:equal value="yes" name="isZZSF">
							         <tr>
								<th align="right">
									学位
								</th>
								<td align="left">
									<html:text name='rs' property="xw" maxlength="15" styleId="xw" />
								</td>
								<th align="right">
									入伍时间
								</th>
								<td width="37%" height="20%"><html:text name='rs' property="rwsj" styleId="rwsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rwsj','y-mm-dd');" />			
								</td>
							</tr>  
							    </logic:equal>
							</logic:present>
							<logic:notPresent name="isZZSF">
						    <tr>
								<th align="right">
									学位
								</th>
								<td align="left">
									<html:text name='rs' property="xw" maxlength="15" styleId="xw" />
								</td>
								<logic:notEqual name="xxdm" value="10290" scope="session">
								<th align="right">
									上级单位
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="sjdw" maxlength="30" styleId="xw" />			
								</td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="10290" scope="session">
								<th align="right">
									传真
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="cz" maxlength="15" styleId="cz" />
								</td>
								</logic:equal>
							</tr>  
							</logic:notPresent>
							<logic:equal name="xxdm" value="10290" scope="session">
							<tr>
								<th align="right">
									主要分管年级
								</th>
								<td align="left">
									<html:select name = "rs" property="fgnj" style="width:60px" styleId="nj" onchange="qtfgnjOptions();"> 
	          							<html:option value=""></html:option> 
	          							<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        							</html:select>
								</td>
								<th align="right">
									其他分管年级
								</th>
								<td align="left">
									<html:text name="rs"  property="qtfgnj" styleId="qtfgnj" style="width:150px;height:21px;font-size:10pt;" />
									<span style="width:18px;border:0px solid red;">
									<html:select name = "rs" property="qtfgnjChoose" style="margin-left:-150px;width:168px; background-color:#FFEEEE;" styleId="qtfgnjChoose" onchange="getQtfgnjValue();"> 
	          							<html:option value=""></html:option> 
	          							<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        						</html:select>
	        						</span>
								</td>
							</tr>
							</logic:equal>
							<logic:equal name = "xxdm" value = "12702">
							<tr>
								<th align="right">
									第一学历
								</th>
								<td align="left">
									<html:text name='rs' property="bkbyyx" maxlength="15" styleId="bkbyyx" />
								</td>
								<th align="right">
									毕业院校
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="bksxzy" maxlength="15" styleId="bksxzy" />
								</td>
							</tr>
							<tr>
								<th align="right">
									第二学历
								</th>
								<td align="left">
									<html:text name='rs' property="ssbyyx" maxlength="15" styleId="ssbyyx" />
								</td>
								<th align="right">
									毕业院校
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="ssbyzy" maxlength="15" styleId="ssbyzy" />
								</td>
							</tr>
							<tr>
								<th align="right">
									第三学历
								</th>
								<td align="left">
									<html:text name='rs' property="bsbyyx" maxlength="15" styleId="bsbyyx" />
								</td>
								<th align="right">
									毕业院校
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="bsbyzy" maxlength="15" styleId="bsbyzy" />
								</td>
							</tr>
							</logic:equal>
							<logic:equal name = "xxdm" value = "10338" scope="session">
							<tr>
								<th align="right">
									本科生毕业院校
								</th>
								<td align="left">
									<html:text name='rs' property="byyx" maxlength="15" styleId="byyx" />
								</td>
								<th align="right">
									本科生所学专业
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="sxzy" maxlength="15" styleId="sxzy" />
								</td>
							</tr>
							</logic:equal>
							<logic:equal name = "xxdm" value = "10388" scope="session">
							<tr>
								<th align="right">
									本科毕业院校
								</th>
								<td align="left">
									<html:text name='rs' property="byyx" maxlength="15" styleId="byyx" />
								</td>
								<th align="right">
									本科所学专业
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="sxzy" maxlength="15" styleId="sxzy" />
								</td>
							</tr>
							<tr>
								<th align="right">
									本科毕业时间
								</th> 
								<td>
									 <html:text name='rs' property="bkbysj" styleId="bkbysj" 
										onclick="return showCalendar('bkbysj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
								</td>
								<th align="right">
									研究生毕业院校
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="yjsbyyx" maxlength="10" styleId="yjsbyyx" />
								</td>
							</tr>
							<tr>
								<th align="right">
									研究生所学专业
								</th>
								<td align="left" >
									<html:text name='rs' property="yjsbyzy" maxlength="10" styleId="yjsbyzy" />
								</td>
								<th align="right">
									研究生毕业时间
								</th> 
								<td colspan="3">
									 <html:text name='rs' property="yjsbysj" styleId="yjsbysj" 
										onclick="return showCalendar('yjsbysj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
								</td>
							</tr>
							<tr>
								<th align="right">
									享受政治待遇
								</th>
								<td align="left" >
									<html:select name='rs' property="zzdy" styleId="zzdy">
										<html:option value=""></html:option>
										<html:options collection="zzdyList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th align="right">
									辅导员协会分组情况
								</th> 
								<td colspan="3">
									<html:select name='rs' property="xhfz" styleId="xhfz">
										<html:option value=""></html:option>
										<html:options collection="xhfzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									专兼职
								</th>
								<td align="left" >
									<html:select  property="zjz" value="${rs.zjz }">
										<html:option value=""></html:option>
										<html:option value="专职">专职</html:option>
										<html:option value="兼职">兼职</html:option>
									</html:select>
								</td>
								<th align="right">
									年度考核为优
								</th> 
								<td colspan="3">
									<html:select name='rs' property="ndkhy" styleId="ndkhy">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							</logic:equal>
							
							<logic:notEqual name = "xxdm" value = "10338" scope="session">
								<logic:notEqual name = "xxdm" value = "10388" scope="session">
								<tr>
									<th align="right">
										毕业院校
									</th>
									<td align="left">
										<html:text name='rs' property="byyx" maxlength="15" styleId="byyx" />
									</td>
									<th align="right">
										所学专业
									</th>
									<td align="left" colspan="3">
										<html:text name='rs' property="sxzy" maxlength="15" styleId="sxzy" />
									</td>
								</tr>
								</logic:notEqual>
							</logic:notEqual>
							<tr style="display: none;" id = "sssdis">
								<th align="right">
									硕士生毕业院校
								</th>
								<td align="left">
									<html:text name='rs' property="ssbyyx" maxlength="15" styleId="ssbyyx" />
								</td>
								<th align="right">
									硕士生所学专业
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="ssbyzy" maxlength="15" styleId="ssbyzy" />
								</td>
							</tr>
							<tr style="display: none;" id = "bssdis">
								<th align="right">
									博士生毕业院校
								</th>
								<td align="left">
									<html:text name='rs' property="bsbyyx" maxlength="15" styleId="bsbyyx" />
								</td>
								<th align="right">
									博士生所学专业
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="bsbyzy" maxlength="15" styleId="bsbyzy" />
								</td>
							</tr>                                     
							<logic:equal name="xxdm" value="10290" scope="session">
							<tr>
								<th align="right">
									在读学历
								</th>
								<td align="left">
									<html:text name='rs' property="byyx" maxlength="15" styleId="zdxl" />
								</td>
								<th align="right">
									在读专业
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="sxzy" maxlength="15" styleId="sxzy" />
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th align="right">
									<font color="red">*</font>所在部门
								</th>
								<td align="left">
									<html:select name="rs" property="bmdm" style="width:140px"
										styleId="szbm">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>
								</td>
								<th align="right">
									<font color="red">*</font>职务
								</th>
								<td align="left" colspan="3">
									<html:select name="rs" property="zw" style="width:100px"
										styleId="zwdm">
										<html:option value=""></html:option>
										<html:options collection="zwList" property="zwdm"
											labelProperty="zwmc" />
									</html:select>
								</td>
							</tr>
							<logic:equal value="10878" name="xxdm" scope="session">
							<tr>
								<th align="right">
									兼职工作
								</th>
								<td align="left" colspan="5">
									<html:text name='rs' property="jrgz" styleId="jrgz" style="width:90%" maxlength="50"/>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th align="right">
									任职时间
								</th>
								<td align="left">
									<html:text name='rs' property="zwrzsj" styleId="zwrzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zwrzsj','y-mm-dd');" />			
								</td>
								<th align="right">
									职称获得时间
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="jsrzsj" styleId="jsrzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jsrzsj','y-mm-dd');" />			
								</td>
							</tr>		
							<tr>
								<th align="right">
									职称
								</th>
								<td align="left">
									<html:text name='rs' property="zc" maxlength="20" styleId="zc" />
								</td>
								<logic:equal name="xxdm" value="11407" scope="session">
								<th align="right">
									<font color="red">*</font>专业
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="xsgzyjfx" styleId="xsgzyjfx" maxlength="20"/>
								</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="11407" scope="session">
								<logic:notPresent name="isAhxgOne">  
								<th align="right">
									学生工作研究方向
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="xsgzyjfx" maxlength="30" styleId="xsgzyjfx" />
								</td>
								</logic:notPresent>
								<logic:present name="isAhxgOne">  
								<td align="right">
								</td>
								<td align="left" colspan="3">
								</td>
								</logic:present>
								</logic:notEqual>
							</tr>							
							<tr>
								<th align="right">
									家庭住址
								</th>
								<td align="left" colspan="5">
									<html:text name='rs' property="jtzz" maxlength="50" styleId="jtzz" style="width:90%"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									通讯地址
								</th>
								<td align="left" colspan="5">
									<html:text name='rs' property="txdz" styleId="txdz" maxlength="25" style="width:90%"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									办公地点
								</th>
								<td align="left" colspan="5">
									<html:text name='rs' property="bgdd" styleId="bgdd" maxlength="25" style="width:90%"/>
								</td>
							</tr>
							<logic:notEqual name="xxdm" value="10290" scope="session">
							<tr>
								<th align="right">
									办公电话
								</th>
								<td align="left">
									<html:text name='rs' property="bgdh" onkeyup="chkIsNum(this);" maxlength="15" styleId="bgdh" />
								</td>
								<th align="right">
									传真
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="cz" maxlength="15" styleId="cz" />
								</td>
							</tr>
							</logic:notEqual>
							<tr align="left">
								
								<th align="right">
									所在系别
								</th>
								<td>
									<logic:present name="fdyzyList">
									<logic:iterate id="s" name="fdyzyList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      							</logic:present>
								</td>
								<th align="right">
									邮编
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="yzbm" styleId="yzbm" maxlength="6" onkeyup="checkInputData(this)"/>
								</td>
							</tr> 
							<logic:equal name="xxdm" value="10338">
							<tr align="left">
								<th align="right">
									分工类型
								</th>
								<td>
									<html:select name="rs" property="fglxdm">
										<html:option value=""></html:option>
										<html:options collection="fglxList" property="fglxdm" labelProperty="fglxmc" />
									</html:select>
								</td>
								<th align="right">
									队伍类别
								</th>
								<td align="left" colspan="3">
									<html:select name="rs" property="dwlbdm">
										<html:option value=""></html:option>
										<html:options collection="dwlbList" property="lbdm" labelProperty="lbmc" />
									</html:select>
								</td>
							</tr> 
							</logic:equal>
							<tr align="left">
								<th align="right">
									负责班级
								</th>
								<logic:notEqual name="xxdm" value="10388">
								<td colspan="5">
								<logic:present name="fdybjList">
									<logic:iterate id="s" name="fdybjList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      						</logic:present>
								</td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="10388">
								<td >
								<logic:present name="fdybjList">
									<logic:iterate id="s" name="fdybjList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      						</logic:present>
								</td>
								<th align="right">
									所带学生人数
								</th>
								<td align="left" colspan="3">
									<html:text name='rs' property="sdbjrs" maxlength="15" styleId="sdbjrs" />
								</td>
								</logic:equal>
							</tr>
							<logic:equal name="xxdm" value="10388">
								<th align="right">
									所带学生获奖情况<br/>
									<font color="red">(限制字数500)</font>
								</th>
								<td align="left" colspan="5">
									<html:textarea name='rs' property="sdxsqk" rows="4" style="word-break:break-all;width:99%" styleId="sdxsqk" 
									onblur="chLeng(this,500);"></html:textarea>
								</td>
								</logic:equal>
							<logic:equal name="xxdm" value="11355">
							<tr>
								<th align="right">
									辅导员类型
								</th>
								<td align="left" colspan="5">
									<html:select name="rs" property="fglxdm">
										<html:option value=""></html:option>
										<html:option value="01">专职</html:option>
										<html:option value="02">兼职</html:option>
									</html:select>
								</td>
							</tr>
							</logic:equal>
							<logic:equal name="xxdm" value="12702">
							<tr align="left">
								<th align="right">
									<font color="red">*</font>所属组别
								</th>
								<td colspan="5">
									<html:select name="rs" property="fdyz">
										<html:option value=""></html:option>
										<html:options collection="fdyzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							</logic:equal>
							<logic:present name="isZZSF">
							    <logic:equal value="yes" name="isZZSF">
							    <tr align="left">
								<th align="right">
									工作分工
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='gzfg' style="width:99%"
										rows='4' />
								</td>
							    </tr>
							    </logic:equal>
							</logic:present>  
							<logic:notPresent name="isAhxgOne">   
							<tr align="left">
								<th align="right">
									主要职责<br/>
									<font color="red">(限制字数2000)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='zyzz' styleId= "zyzz" style="word-break:break-all;width:99%"
										rows='4' onblur="chLeng(this,2000);"/>
								</td>
							</tr>
							</logic:notPresent> 
							<tr align="left">
								<th align="right">
									个人获奖情况<br/>
									<font color="red">(限制字数2000)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='grhjqk' styleId="grhjqk" style="word-break:break-all;width:99%"
										rows='5' onblur="chLeng(this,2000);"/>
								</td>
							</tr> 
							<logic:equal name="xxdm" value="10290" scope="session">
							<tr align="left">
								<th align="right">
									高中以上学历经历
									<br/><font color="red">(限制字数200)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='gdxlzl' styleId="gdxlzl" style="word-break:break-all;width:99%" onblur="chLeng(this,200);"
										rows='5' />
								</td>
							</tr>
							<tr align="left">
								<th align="right">
									主要研究成果	<br/><font color="red">(限制字数25)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='zyyjjg' styleId="zyyjjg" style="word-break:break-all;width:99%"
										rows='5' onblur="chLeng(this,25);" />
								</td>
							</tr>
							</logic:equal>
							<logic:notEqual name = "xxdm" value="10491" scope="session">
							<tr align="left">
								<th align="right">
									发表论文<br/><font color="red">(限制字数2000)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='fblw' styleId="fblw" style="word-break:break-all;width:99%"
										rows='4' onblur="chLeng(this,2000);"/>
								</td>
							</tr>
							<tr align="left">
								<th align="right">
									科研经历<br/><font color="red">(限制字数2000)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='kyjl' styleId="kyjl" style="word-break:break-all;width:99%"
										rows='5' onblur="chLeng(this,2000);"/>
								</td>
							</tr>
							</logic:notEqual>
							<logic:equal name = "xxdm" value="10491" scope="session">
							<tr align="left">
								<th align="right">
									发表论文
								</th>
								<td colspan="5">
									<logic:present name="lwxx">
									<logic:iterate id="lw" name ="lwxx">
										<bean:write name="lw" /><br/>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr align="left">
								<th align="right">
									科研经历
								</th>
								<td colspan="5">
									<logic:present name="kyjl">
									<logic:iterate id="ky" name ="kyjl">
										<bean:write name="ky" /><br/>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr align="left">
								<th align="right">
									辅导员著作
								</th>
								<td colspan="5">
									<logic:present name="fdyzz">
									<logic:iterate id="zz" name ="fdyzz">
										<bean:write name="zz" /><br/>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							</logic:equal>
							<tr align="left">
								<th align="right">
									工作经历<br/><font color="red">(限制字数2000)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='gzjl' styleId="gzjl" style="word-break:break-all;width:99%"
										rows='8' onblur="chLeng(this,2000)"/>
								</td>
							</tr>
							<logic:equal name = "xxdm" value ="10338" >
							<tr align="left">
								<th align="right">
									培训情况
								</th>
								<td colspan="5">
									<logic:present name="pxqk">
									<logic:iterate id="px" name ="pxqk">
										<bean:write name="px" /><br/>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							</logic:equal>
							<logic:notEqual name = "xxdm" value ="10338" >
							<tr align="left">
								<th align="right">
									培训情况<br/><font color="red">(限制字数300)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='pxqk' styleId="pxqk" style="word-break:break-all;width:99%" onblur="chLeng(this,300);"
										rows='8' onblur="chLeng(this,300)"/>
								</td>
							</tr>
							</logic:notEqual>
							<logic:present name="gsList">
							
							<tr align="left">
								
								<th align="right">
									重大过失
								</th>
								<td colspan="4">	
									<textarea rows="5" id="gsnr" name="gsnr" style="width:99%" readonly="readonly"><logic:iterate name="gsList" id="s"><bean:write name="s" property="gssj" />:<bean:write name="s" property="gsnr" />
									</logic:iterate></textarea>					
								</td>
							</tr> 
							</logic:present>
							<tr align="left">
								<th align="right">
									备注<br/><font color="red">(限制字数300)</font>
								</th>
								<td colspan="5">
									<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLeng(this,2000);"
										rows='4' />
								</td>
							</tr>
							</tbody>
							 <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
						          	<logic:present name="writeAble">
									<logic:notEqual value="view" name="operation">
									<button type="button" name="保存" onclick="saveFdyxx('xh-xm-bmdm-zw','dzyx');" id="buttonSave">
										保 存
									</button>
									<button type="button"  onclick="dataCanModi(true)"
										 id="buttonModi" >
										修 改
									</button>
									</logic:notEqual>
									</logic:present>
									<logic:notEqual value="one" name="type">
										 <button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
									</logic:notEqual>						           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
						</table>
						</div>
					<div id="tmpdiv1">
						
					</div>
					<logic:notEqual value="one" name="type">
						<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/fdyxxOne.do?act=view&pk=${pkValue}')"></button>
					</logic:notEqual>
					<logic:equal value="one" name="type">
						<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/fdyxxOne.do?act=view&type=one')"></button>
					</logic:equal>
				</logic:notEmpty>
			</logic:notPresent>
		</html:form>
	</body>
</html>
