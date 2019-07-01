<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
        <script language="javascript" src="js/sztzFunction.js"></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getSztzData.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>	
	</head>
	<body onload="if($('yesNo').value=='通过'){$('wxfp1').style.display='';$('wxfp2').style.display='';}">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 审核 - 公寓(宿舍)维修审核 - 单个审核</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/XsgyglDispatch.do?method=gywxSqSh" method="post">		

			<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>单个审核</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<tr style="height:22px;">
					<th align="right" width="15%">
						楼栋名称：
					</th>
					<td align="left" width="80px">
						<bean:write name="rs" property="ldmc"/>
					</td>
					<th align="right" width="20%">
						学年：
					</th>
					<td align="left" width="80px">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						寝室号：
					</th>
					<td align="left">
						<bean:write name="rs" property="qsh"/>
					</td>
					<th align="right">
						报修时间：
					</th>
					<td align="left">
						<bean:write name="rs" property="bxsj"/>
					</td>
					
				</tr>				
				<tr style="height:22px">
					<th align="right">
						报修人姓名：
					</th>
					<td align="left">
						<bean:write name="rs" property="bxrxm"/>
					</td>
					<th align="right">
						 联系方式：
					</th>
					<td align="left">
						<bean:write name="rs" property="lxfs"/>
					</td>						
				</tr>	
				<tr style="height:22px">								
					<th align="right">
						 审核：
					</th>
					<td align="left">
						<html:select name="rs"  property="yesNo" onchange="FpWx()">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>	
					<th align="right">
					
					</th>
					<td align="left">
						
					</td>		
				</tr>	
				<tr  style="height:22px">
					<th align="right">
						维修内容：
					</th>
					<td align="left"colspan="3" >
						<bean:write name="rs" property="wxnr"/>
					</td>	
									
				</tr>
				<tr>
						<th align="right">
							学校审核说明：<BR>
							(小于300字) 							                   
						</th>
						<td align="left" colspan="3">
						<logic:equal name="userType" value="xy">	
							<html:textarea name="rs" property="xxshyy" styleId="xxshyy" rows="6"							
								cols="76" style="width:95% " disabled="true"></html:textarea>
						</logic:equal>
						<logic:equal name="userType" value="xx">	
							<html:textarea name="rs" property="xxshyy" styleId="xxshyy" rows="6"							
								cols="76" style="width:95% "></html:textarea>
						</logic:equal>						
						</td>
					</tr>
				<logic:present name="isCSMZZYJSXY"><!-- 长沙民政职业技术<bean:message key="lable.xsgzyxpzxy" /> -->
				<tr  style="height:22px">
					<th align="right">
						申请备注：
					</th>
					<td align="left"colspan="3" >
						<bean:write name="rs" property="sqbz"/>
					</td>										
				</tr>	
				</logic:present>			
				<tr id="wxfp1" style="display:none;height:22px"   >
					<th align="right">
						预维修时间：
					</th>
					<td align="left" >
                     <html:text name="rs" property="wxsj"  onblur="dateFormatChg(this);"
								onclick="return showCalendar('wxsj','y-mm-dd');" style="cursor:hand " />
					</td>
					<th align="right">
						预派遣维修人员：
					</th>
					<td align="left">
					   <html:select name="rs" property="rydm" style="width:150px" styleId="rydm">
								<html:option value=""></html:option>
								<html:options collection="ryList" property="rydm" labelProperty="rymc" />
						</html:select>
					</td>				
				</tr>
				<tr  id="wxfp2" style="display:none;height:22px">
					<th align="right">
						备注：
					</th>
					<td align="left"colspan="3" >
						<html:textarea name="rs" property="bz" rows="3" cols="50"></html:textarea>
					</td>
					
				</tr>	
				</tbody>	
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:equal value="yes" name="writeAble">
									<button id="buttonSave" 
										onclick="toSave();return false;"
										style="width: 80px">
										保	存
									</button>
								</logic:equal>
								&nbsp;&nbsp;
								<logic:equal value="no" name="writeAble">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>	
     		</table>
			<logic:equal value="yes" name="done">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("操作失败!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		</html:form>
	</body>
<script type="text/javascript">
  function FpWx(){
    var id1 = document.getElementById("wxfp1");
    var id2 = document.getElementById("wxfp2");
    if($("yesNo").value=="通过"){
     id1.style.display="";
     id2.style.display="";
    }else{
     id1.style.display="none";    
     id2.style.display="none";
    }
  }
  
  function toSave(){
       if($("xxshyy")){
           if($("xxshyy").value.length>300){
             alert("学校审核说明字数大于300字！");
             return false;
          }         
       }
       var pkValue="";
       pkValue = $("pkValue").value;
		getSztzData.getInfoEx("gywxglb","ssbh||bxsj",pkValue,"wxsj<>'未维修' ",function(str){
		         if(str){		         	
		            alert("该宿舍已维修，不能再进行审核操作！");		          
			        return false;
		         }else{
                    refreshForm('/xgxt/XsgyglDispatch.do?method=gywxSqSh&doType=save');
                    $("buttonSave").disabled='true';
		         }
    	});	       

    }
 </script>
</html>
