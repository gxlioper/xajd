<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="showLzRq();loadView();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 层长信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjjjzy_Gygl" method="post">
			<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			
			<!-- 层长信息 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>层长信息</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>楼栋：
					</th>
					<td height="22" align="left">
					<input type="hidden" name="lddm" value="${rs.lddm}">
						<html:text name="rs" property="ldmc" disabled="true" />
					</td>
					<th height="22" align="right">
						<font color="red">*</font>楼层：
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="lc" disabled="true" />
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>层长：
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="cz" disabled="true" />
					</td>
					<th height="22" align="right">
						<font color="red">*</font>任职日期：
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="rzrq" styleId="rzrq"
							disabled="true" />
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						姓名：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<th height="22" align="right">
						联系方式：
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="lxdh" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						性别：
					</th>
					<td height="22" align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<th height="22" align="right">
						层长邮箱：
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="dzyx" maxlength="25"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						住址：
					</th>
					<td height="22" align="left">
						<html:text name="rs" name="rs" property="zz" maxlength="50"></html:text>
					</td>
					<th height="22" align="right">
						是否在职：
					</th>
					<td height="22" align="left">
						<html:select name="rs" property="sfzz" style="width:120px"
							styleId="sfzz" onchange="showLzRq()">
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr style="display: none" id="showlz">
					<th height="22" align="right">
						离职日期：
					</th>
					<td height="22" align="left">
						<html:text name='rs' property="lzrq" styleId="lzrq"
							style="cursor:hand;" onblur="dateFormatChg(this)"
							style="cursor:hand;"
							onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
							readonly="true" />
					</td>
					<td height="22" align="right">

					</td>
					<td height="22" align="left">

					</td>
				</tr>

				<tr align="left">
					<th align="right">
						备注：<br>
								(限200字内)
					</th>
					<td colspan="4">
						<html:textarea name="rs" property='bz' style="width:400px" rows='5' />
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button id="buttonSave" onclick="czModiSave()"
										style="width: 80px">
										保	存
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败,或系统中已存在与带\"*\"号项目相同的记录，请检查输入的数据后再提交！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function czModiSave(mustFill){	          
	          var rzrq="";
	          var lzrq="";
	          var sfzz = "";
	          var cz = $("cz").value;
	          if($("sfzz")){
	             sfzz = $("sfzz").value;
	          }
	          if($("rzrq")){
	             rzrq = $("rzrq").value;
	          }
	          if($("lzrq")){
	             lzrq = $("lzrq").value;
	          }
	          if(sfzz=="否"){
	               if(lzrq==""){
	                  alert("离职日期不能为空！");
	                  return false;
	               }   
	               if(rzrq>lzrq){
	                  alert("离职日期早于任职日期！");
	                  return false;
	               }
	          }
	          if($("bz").value.length>200){
	              alert("备注字数不能超过200字！");
	              return false;
	           }       	           
	          var url = "/xgxt/zjjjzy_Gygl.do?method=czModi&doType=save";
	          url +="&pkValue=";
	          url +=$("pkValue").value;
	           if(sfzz=="是"){
	             var pkValue=$("lddm").value+$("lc").value+cz;
	             gyglShareData.getLcqszZzpd("czxxb","lddm||lc||cz",pkValue,"lddm||lc||cz||rzrq",$("pkValue").value,function(str){
		             if(str){		         	
		                refreshForm(url);	           		          			        
		             }else{                     
                        alert("该生目前正任职该楼层层长，不能重复任职！");                
		             }
    	         });
    	       }else{
	             refreshForm(url);   
	           }
 	     }
	     function showLzRq(){
	          var sfzz = $("sfzz").value;
	          if(sfzz=="否"){
	             $("showlz").style.display="";
	          }else{
	             $("showlz").style.display="none";
	          }
	     }
	</script>
</html>
