<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>

	<body onload="clin();">	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 申请 - 宿舍异动申请</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/XsgyglDispatch.do?method=ssYdSq" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
             <input type="hidden" id="ssbh" name="ssbh" value="${rs.ssbh}" />
             <logic:equal value="isNotStu" name="isNotStu" >			
			   <div align="center"><font color="red">只有学生用户可以申请!</font> </div>
			</logic:equal>
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>宿舍异动申请</span>
						</th>
					</tr>
				</thead>
				<tbody>					
					<tr>
						<th align="right" style="width: 100">
							<font color="red">*</font>学号：
						</th>
						<td align="left">
							<html:text name='rs' property="xh" readonly="true"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none" >
								选择
							</button>
						</td>
						<th align="right">
							姓名：
						</th>
						<td align="left" >
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>						
					</tr>					
					<tr>
						<th align="right">
							性别：
						</th>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>学年：
						</th>
						<td align="left">
							<html:select name="rs" property="xn" style="width:100px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>											
					</tr>
					<tr>
						<th align="right">
							年级：
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>学期：
						</th>
						<td align="left">
							<html:select name="rs" property="xq" style="width:80px"
								styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>				
					</tr>					
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</th>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>异动后楼栋名称：
						</th>
						<td align="left">
								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
						</td>				
					</tr>
					<tr>
						<th align="right">
							专业：
						</th>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>异动后寝室号：
						</th>
						<td align="left">
							<input type="hidden" name="qshV" value=""/>
							<html:select name="rs" property="qsh" style="width:110px" onchange="GetCwhList()">
								<html:option value=""></html:option>
								<html:options collection="qshList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>									
					</tr>
					<tr>
						<th align="right">
							班级：
						</th>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>异动后床位号：
						</th>
						<td align="left">
							<input type="hidden" name="cwhV" value="" />
							<html:select name="rs" property="cwh" style="width:130px" styleId="cwh" onchange="isOrNo()">
								<html:options collection="cwhList" property="dm" labelProperty="mc" />
							</html:select>
						</td>												
					</tr>
					<tr>
						<th align="right">
							异动前楼栋名称：
						</th>
						<td align="left">
							<html:text name="rs" property="ydqldmc" readonly="true" styleId="ydqldmc" />
						</td>
						<th align="right">
							<font color="red">*</font>申请异动时间：									
						</th>
						<td align="left">
							<html:text name="rs" property="ydsj" readonly="true"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('ydsj','y-mm-dd');"
								style="cursor:hand " />
						</td>							
					</tr>
					<tr>
						<th align="right">
							异动前寝室号：
						</th>
						<td align="left">
							<html:text name="rs" property="ydqqsh" readonly="true" styleId="ydqqsh"/>
						</td>
						<th align="right">
							
						</th>
						<td align="left">
							
						</td>
					</tr>
					<tr>
						<th align="right">
							异动前床位号：
						</th>
						<td align="left">
							<html:text name="rs" property="ydqcwh" readonly="true" styleId="ydqcwh"/>	
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					<tr>
						<th align="right">
							异动前入住时间：
						</th>
						<td align="left">
							<html:text name="rs" property="rzrq" readonly="true" styleId="rzrq"/>	
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					<tr>
						<th align="right">
							异动理由：	
							<br>(限100字内)								
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="76" property="ydly" id="ydly" ></textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
										<button id="buttonSave" 
											onclick="ssydDataSave()"
											style="width: 80px">
											提	交
										</button>
									&nbsp;&nbsp;
									<button id="buttonClose" onclick="spbPrint()"
										style="width: 80px">
										打 印
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>		
		</html:form>		
		<logic:equal value="yes" name="done">
			<script type="text/javascript">
	           alert("申请成功！");
	        </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script type="text/javascript">
	          alert("申请失败！");
	        </script>
		</logic:equal>
  </body>
  <script type="text/javascript">
  function ssydDataSave(){
     if($('ydly').value.length>100){
        alert('字数超出100字！');
        return false;
     }else{
       if(IsNoEmpty('xh-xn-xq-lddm-qsh-cwh-ydsj'))
       {
       refreshForm('/xgxt/XsgyglDispatch.do?method=ssYdSq&doType=save');
       $("buttonSave").disabled=true;
       }
     }
  }
  function isOrNo(){
    var val = $('lddm').value+"-"+$('qsh').value+$("cwh").value;
    getSztzData.getDataEx("ssydsqb","ydhssbh||ydhcwh",val,function(str){
		         if(str!=""){
		            alert("该床位号已被申请！");
		            $("cwh").value="";
		            $("cwh").focus();
			        return false;
		         }
		});
  }
  function clin(){
     if($("ssbh").value==""){
     alert("当前用户还没有入住！");
     return false;
     }
  }
   function spbPrint() {
	document.forms[0].action = "/xgxt/XsgyglDispatch.do?method=sSyDsPb";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
  </script>
</html>
