<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript"  src="js/AjaxFunction.js"></script>
	<script language="javascript"  src="js/gygl/gyglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
	<script language="javascript">
function wsjcSave(mustFill){
	var zs = document.getElementById("zs").value;
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	}
	var url = "/xgxt/modiData.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	var tmpTable = window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType=save";
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	url += "&zs="+zs;
	document.forms[0].action = url;
	document.forms[0].submit();
	if (tmpTable=='wjcfb') {
		if (var_w<2) {
			setInterval("waitTime()",2200);
		}
		return;
	} else {
		alert("保存成功！");
		Close();
		dialogArgumentsQueryChick();
	}

}
function toSave(){
    	  var wxnr = "";
	      var bz   = "";
	      
	      if($("bz")){
	         bz = $("bz").value;
	      }	  
	      
	      if(bz.length>100){
	          alert("备注字数过大，限100字内！");
	          return false;
	      } 
    dataDoSave('xn-xq-qsh-jcsj-jcbm');
}

function getWsdjFs(){
	var tableName = "zgdd_wsjcdj";
	var dm = "fs";
	var pk = "mc";
	var pkValue = $("dj").value;
	getGyglDAO.getOneValue(tableName,dm,pk,pkValue,function (data){
		$("fs").value = data;
	});
}
	</script>
	</head>
	
	<body onload="loadScore()">		
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>公寓管理 - 信息维护 - 卫生检查</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					<!--有错误发生！-->
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="url" name="url" value="/sjcz/gywsjcb.jsp" />
				<input type="hidden" id="doType" name="doType"
					value="${doType }" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="${pkValue }" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="userType" name="userType"
					value="${userType }" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="${xxdm }" />
			    <input type="hidden" id="V_lddm" name="V_lddm" value="" />
					<div class="tab">
						<table width="100%"  border="0" class="formlist">
						 <thead>
		    				<tr>
		        				<th colspan="6"><span>${clin }</span></th>
		        			</tr>
		   				 </thead>
		   				 <tbody>
						<tr>
						<th>
							<font color="red">*</font>学年
						</th>
						<td>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>
						<th>
							<font color="red">*</font>学期
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq" value="${rs.xq}"/>
								<input type="text" value="${rs.xqmc}" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" style="width:90px" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>					
					</tr>
					<tr>
						<th>
							<font color="red">*</font>楼栋名称
						</th>
						<td>
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.ldmc}" name="ldmc" readonly="true"/>
								<html:hidden name="rs" property="lddm" value="${rs.lddm}"></html:hidden>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">									
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</logic:equal>
						</td>
						<th>
							<font color="red">*</font>寝室号
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" value="${rs.qsh}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<input type="hidden" name="qshV" value=""/>
								<html:select property="qsh" styleId="qsh" style="width:110px" onchange="showXsxx()">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</logic:equal>
						</td>					
					</tr>
					<logic:notEqual name="xxdm" value="11641">
					<logic:notEqual name="xxdm" value="10338">
					<tr>
						<th>
							<font color="red">*</font>检查时间								
						</th>
						<td>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="jcsj" value="${rs.jcsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('jcsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>			
						</td>
						<th>
							<font color="red">*</font>检查部门					
						</th>
						<td align="left">
						<logic:present name="noshowbm">
						<html:text name="rs" property="jcbm" maxlength="10"></html:text>
						</logic:present>
						<logic:notPresent name="noshowbm">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.bmmc}" readonly="true"/>
								<html:hidden name="rs" property="jcbm" value="${rs.jcbm}"></html:hidden>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="jcbm" style="width:150px" styleId="jcbm">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</logic:equal>	
						</logic:notPresent>													
						</td>					
					</tr>
					<tr>
						<th>
							分数						
						</th>
						<td align="left">
						    <html:text name="rs" property="fs" styleId="fs" onkeypress="return numInputValue(this,8,event)"></html:text>
						</td>
						<th>
							等级		
						</th>
						<td>
							<!-- 非中国地质大学 -->
							<logic:notEqual name="xxdm" value="10491">			
							<logic:equal value="1049701" name="xxdm">
								<html:select name="rs" property="dj" style="width:150px" styleId="dj" onchange="GetFs()">
									<html:option value=""></html:option>
									<html:options collection="djList" property="nwwsdjdm" labelProperty="nwwsdjmc" />
								</html:select>
								</logic:equal>
							<logic:notEqual value="1049701" name="xxdm">
								<html:select name="rs" property="dj" style="width:150px" styleId="dj" onchange="autoScore()">
									<html:options collection="djList" property="en" labelProperty="cn" />
								</html:select>
							</logic:notEqual>
							</logic:notEqual>
							<!-- 非中国地质大学 end-->
							<!-- 中国地质大学 -->
							<logic:equal name="xxdm" value="10491">		
								<html:select name="rs" property="dj" style="width:150px" styleId="dj" onchange="getWsdjFs()">
									<html:option value=""></html:option>
									<html:options collection="djList" property="mc" labelProperty="mc" />
								</html:select>
							</logic:equal>
							<!-- 中国地质大学 end-->
						</td>					
					</tr>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal name="xxdm" value="10338">
					<tr>
						<th>
							<font color="red">*</font>检查时间							
						</th>
						<td align="left">
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" onblur="dateFormatChg(this);getZs(this);"
								onclick="return showCalendar('jcsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" />
							</logic:notEqual>	
						</td>
						<th>
							检查周时						
						</th>
						<td>
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="zs" value="${rs.zs}" readonly="true" styleId="zs"></html:text>
							</logic:equal>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="zs" value="第${rs.zs}周" readonly="true" styleId="zs"></html:text>
							</logic:notEqual>									
						</td>					
					</tr>
					<tr>
						<th>
							分数					
						</th>
						<td align="left">
						    <html:text name="rs" property="fs" styleId="fs" onkeypress="return numInputValue(this,8,event)"></html:text>
						</td>
						<th>		
						</th>
						<td align="left">
						</td>					
					</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="11641">
					<tr>
						<th>
							<font color="red">*</font>检查周时									
						</th>
						<td align="left" colspan="3">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="zs" value="第${rs.zs}周" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="zs" style="width:150px" styleId="zs">
									<html:options collection="zsList" property="dm" labelProperty="mc" />
								</html:select>
							</logic:equal>			
						</td>
						<!-- <td align="right">
							<font color="red">*</font>检查时间：									
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="jcsj" value="${rs.jcsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('jcsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>			
						</td> -->
					</tr>
					<tr>
						<th>
							成绩		
						</th>
						<td>
							<html:select name="rs" property="fs" style="width:150px" styleId="fs">
								<html:option value=""></html:option>
								<html:options collection="wsjcdjList" property="cj" labelProperty="dj" />
							</html:select>
							 <html:text name="rs" property="fs" styleId="fs" onkeypress="return numInputValue(this,8,event)"
							 style="display:none"></html:text>
						</td>	
						<th>
							<font color="red">*</font>检查部门					
						</th>
						<td align="left">
						<logic:present name="noshowbm">
						<html:text name="rs" property="jcbm" ></html:text>
						</logic:present>
						<logic:notPresent name="noshowbm">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.bmmc}" readonly="true"/>
								<html:hidden name="rs" property="jcbm" value="${rs.jcbm}"></html:hidden>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="jcbm" style="width:150px" styleId="jcbm">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</logic:equal>	
						</logic:notPresent>													
						</td>							
					</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="1049701">
						<tr>
							<th>
								检查周									
							</th>
							<td align="left">
							   <html:select name="rs" property="zs" style="width:150px" styleId="zs">
									<html:options collection="zsList" property="en" labelProperty="cn" />
								</html:select>		
							</td>
							<th>		
							</th>
							<td align="left">
							</td>					
						</tr>
					</logic:equal>
	
					<logic:present name="showhzy">
						<tr>
							<th>
								生活区							
							</th>
							<td>
								<html:text name="rs" property="shq" readonly="true"></html:text>		
							</td>
							<th>		
							</th>
							<td align="left">
							</td>					
						</tr>
					</logic:present>
					<tr id="rsxsxx" style="display:none">
						<td colspan="4">
									<legend>
									    <span id="ldqs"></span>寝室当前入住人数：共<span id="rsNum"></span>人
									</legend>
							<table width="100%" class="tbstyle">							
								<tbody id="rsTables">
								  
								</tbody>								
							</table>
						</td>
					</tr>
					<tr>
						<th>
							备注
							<br>
							<span style="color: red">
							<限100字>	</span>									
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="bz" id="bz" type="_moz" 
							     style="word-break:break-all;width:99%"	onblur="chLeng(this,100)">${rs.bz}</textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
						 <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
						          <div class="btn">
						          	<button type="button" class="button2" onclick="dataCanModi(true)" style="width:80px" id="buttonModi">
										修 改
									</button>
									<logic:notEqual name="xxdm" value="11641">
									<button type="button" class="button2" onclick="toSave()" style="width:80px" id="buttonSave">
										保 存
									</button>
									</logic:notEqual>
									<logic:equal name="xxdm" value="11641">
									<button type="button" class="button2" onclick="wsjcSave('xn-xq-qsh-jcbm');" style="width:80px" id="buttonSave">
										保 存
									</button>
									</logic:equal>
									<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
										关 闭
									</button>						           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
					</tfoot>
				</table>
			</logic:notEmpty>	
			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
  </body>
<script type='text/javascript'>
function loadScore(){
  if($('doType').value=='add'){
   if($("xxdm").value!="1049701" && $("xxdm").value!="10338"){//武汉理工华夏<bean:message key="lable.xsgzyxpzxy" />,浙江理工
   document.forms[0].fs.value='90';
   }
  }
if($('doType').value != 'add'){
document.getElementById('rsxsxx').style.display=''; 
getRzXsXx();
}
}
function autoScore(){
 if($("xxdm").value!="1049701"){//武汉理工华夏<bean:message key="lable.xsgzyxpzxy" />
   if($("xxdm").value=="11647"){
      if(document.forms[0].dj.value=='优秀'){
          document.forms[0].fs.value='90';
      }else if(document.forms[0].dj.value=='良好'){
          document.forms[0].fs.value='80';   
      }else if(document.forms[0].dj.value=='合格'){
          document.forms[0].fs.value='70';
      }else if(document.forms[0].dj.value=='不合格'){
          document.forms[0].fs.value='60';
      }     
   } 
   if($("xxdm").value=="10338"){
   
   }
   if($("xxdm").value=="10863"){
      if(document.forms[0].dj.value=='A级'){
          document.forms[0].fs.value='90';
      }else if(document.forms[0].dj.value=='B级'){
          document.forms[0].fs.value='80';   
      }else if(document.forms[0].dj.value=='C级'){
          document.forms[0].fs.value='60';
      }
   }else{  
      if(document.forms[0].dj.value=='优秀'){
          document.forms[0].fs.value='90';
      }else if(document.forms[0].dj.value=='良好'){
          document.forms[0].fs.value='80';
      }else if(document.forms[0].dj.value=='中等'){
          document.forms[0].fs.value='70';
      }else if(document.forms[0].dj.value=='及格'){
          document.forms[0].fs.value='60';
      }else if(document.forms[0].dj.value=='不及格'){
          document.forms[0].fs.value='50';
      }
   }
 }  
}
function showXsxx(){
     if($("qsh").value!=""){
	      document.getElementById("rsxsxx").style.display="";
	      getRzXsXx();
	 }else{
	      document.getElementById("rsxsxx").style.display="none";
	 }
}

</script>
</html>