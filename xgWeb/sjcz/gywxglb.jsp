<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript">
	   function toSave(){
	      var wxnr = "";
	      var bz   = "";
	      if($("wxnr")){
	         wxnr = $("wxnr").value;
	      }
	      if($("bz")){
	         bz = $("bz").value;
	      }	  
	      if(wxnr.length>100){
	          alert("维修内容字数过大，限100字内！");
	          return false;
	      } 
	      if(bz.length>100){
	          alert("备注字数过大，限100字内！");
	          return false;
	      } 	         
	      dataDoSave('xn-xq-qsh-bxsj-wxnr');
	   }
	</script>
	</head>
	<body onload="checkWinType();">		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					<!-- 有错误发生！ -->
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="url" name="url" value="/sjcz/gywxglb.jsp" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
			    <input type="hidden" id="V_lddm" name="V_lddm" value="" />	
<%--			    <input type="hidden" id="isGyFdy" name="isGyFdy"--%>
<%--					value="<bean:write name="isGyFdy" scope="request"/>" />	--%>
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>公寓维修</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th width="20%">
							<font color="red">*</font>学年
						</th>
						<td width="30%">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>							
						</td>
						<th width="20%">
							<font color="red">*</font>学期
						</th>
						<td>
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
						<th align="right">
							<font color="red">*</font>楼栋名称
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.ldmc}" readonly="true"/>
								<html:hidden name="rs" property="lddm" value="${rs.lddm}"></html:hidden>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">									
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</logic:equal>							
						</td>
						<th align="right">
							<font color="red">*</font>寝室号
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" value="${rs.qsh}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<input type="hidden" name="qshV" value=""/>
								<html:select property="qsh" style="width:110px">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</logic:equal>
						</td>					
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>报修时间									
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="bxsj" value="${rs.bxsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="bxsj" readonly="true" onblur="dateFormatChg(this)"
								onclick="return showCalendar('bxsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>														
						</td>
						<th align="right">
							维修时间							
						</th>
						<td align="left">
							<html:text name="rs" property="wxsj" readonly="true" onblur="dateFormatChg(this);checkWxsj()"
								onclick="return showCalendar('wxsj','y-mm-dd');" style="cursor:hand " />							
						</td>					
					</tr>
					<tr style="display:none" id="wxry">
					<logic:notPresent name="isNBZYJSXY">
						<th align="right">
								维修人员									
						</th>
						<td align="left">
							<html:select name="rs" property="rydm" style="width:150px" styleId="rydm">
								<html:options collection="ryList" property="rydm" labelProperty="rymc" />
							</html:select>			
						</td>
					</logic:notPresent>	
						<logic:present name="showhzy">
						<th align="right">
								维修部门
						</th>
						<td align="left">
							<html:select name="rs" property="bmdm" style="width:150px" styleId="rydm">
								<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
							</html:select>				
						</td>			
						</logic:present>
						<logic:notPresent name="showhzy">
							<th align="right">
							</th>
							<td align="left">	
							</td>			
						</logic:notPresent>			
					</tr>
					<logic:present name="isGDBYXY">
						<tr>
							<th align="right">
								报修部门
							</th>
							<td align="left">
								<html:text name="rs" property="bxbm" styleId="bxbm"
									value="${rs.bxbm}" />
							</td>
							<th align="right">
								报修人
							</th>
							<td align="left">
								<html:text name="rs" property="bxr" styleId="bxr"
									value="${rs.bxr}" />
							</td>
						</tr>
					</logic:present>
					<logic:present name="showhzy">
						<tr>
							<th align="right">
								生活区:
							</th>
							<td align="left">
								<html:text name="rs" property="shq" styleId="shq" readonly="true" value="${rs.shq}"/>
							</td>
							<th align="right">
							</th>
							<td align="left">
							</td>
						</tr>
					</logic:present>
					<logic:present name="isNBZYJSXY">
					<tr>
						<th align="right">
							<font color="red">*</font>维修内容									
						</th>
						<td align="left" colspan="3">
							<html:select name="rs" property="wxnr" style="width:150px" styleId="wxnr">
								<html:options collection="wxnrList" property="dm" labelProperty="mc" />
							</html:select>							
						</td>				
					</tr>					
					</logic:present>
					<logic:notPresent name="isNBZYJSXY">
					<tr>
						<th align="right">
							<font color="red">*</font>维修内容
							<br>
							<span style="color: red">
							<限100字>	</span>										
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="wxnr" id="wxnr" type="_moz">${rs.wxnr}</textarea>			
						</td>				
					</tr>
					</logic:notPresent>
					<tr>
						<th align="right">
							备注
							<br>
							<span style="color: red">
							<限100字>	</span>								
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="bz" id="bz" type="_moz">${rs.bz}</textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" class="button2" onclick="toSave()" style="width:80px" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</logic:notEmpty>		
			<logic:equal value="true" name="result">
			  <script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="result">
			  <script type="text/javascript">
			    alert('操作失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
		<logic:present name="rs" property="rydm">
		<logic:notEqual value="000" name="rs" property="rydm">
			<script>
			document.getElementById("wxry").style.display = "";
			</script>
		</logic:notEqual>			
		</logic:present>
  </body>
</html>
