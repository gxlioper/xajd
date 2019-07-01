<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function xlxhhd_modi(){
		var zt=document.all["zt"].value;
			if ( zt==""){
				alert ("请填写主题！");
				document.all["zt"].focus();
				return false;
			}
			var hdxs=document.all["hdxs"].value;
			if ( hdxs==""){
				alert ("请选择活动形式!");
				document.all["hdxs"].focus();
				return false;
			}else if(hdxs=="013"){
				var qthdxs=document.all["qthdxs"].value;
				if(qthdxs==""){
					alert ("请填写其他活动形式!");
					document.all["qthdxs"].focus();
					return false;
				}
			}
			
			var dd=document.all["dd"].value;
			if ( dd==""){
				alert ("请填写活动地点！");
				document.all["dd"].focus();
				return false;
			}
			
			var rq=document.all["rq"].value;
			if ( rq==""){
				alert ("请选择活动日期！");
				document.all["rq"].focus();
				return false;
			}
			
			var zcr=document.all["zcr"].value;
			if ( zcr==""){
				alert ("请填写主持人！");
				document.all["zcr"].focus();
				return false;
			}
			
			var xs=document.all["xs"].value;
			if ( xs==""){
				alert ("请填写学生！");
				document.all["xs"].focus();
				return false;
			}
			
			var cyxs=document.all["cyxs"].value;
			if ( cyxs==""){
				alert ("请填写参与学生！");
				document.all["cyxs"].focus();
				return false;
			}
			
			var rs=document.all["rs"].value;
			if ( rs==""){
				alert ("请填写参与学生人数！");
				document.all["rs"].focus();
				return false;
			}
			
			
			var hdjl=document.all["hdjl"].value;
			if ( hdjl==""){
				alert ("请填写活动记录！");
				document.all["hdjl"].focus();
				return false;
			}
			if (hdjl.length >150){
				alert ("活动记录最多只能填写150个汉字！");
				document.all["hdjl"].focus();
				return false;
			}
			
			var hdxg=document.all["hdxg"].value;
			if ( hdxg==""){
				alert ("请填写活动效果！");
				document.all["hdxg"].focus();
				return false;
			}
			if ( hdxg.length>150){
				alert ("活动效果最多只能填写150个汉字！");
				document.all["hdxg"].focus();
				return false;
			}
			
			document.all["modi_flag"].value="yes";
			underDealWith();
			refreshForm('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_modi');
	}
	function check_qthdxs(){
		var hdxs=document.all["hdxs"].value;
		if(hdxs=="013"){
			document.getElementById('qthdxs').readOnly=false;
		}else{
			document.all["qthdxs"].value="";
			document.getElementById('qthdxs').readOnly=true;
		}
	}
	</script>
	</head>
	<body >
	
		<html:form action="/xljk_xlxhhd" method="post">
		<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
		<input type="hidden" id="xlxhhd_xnid" name="xlxhhd_xnid" value="<bean:write name="xlxhhd_xnid" scope="request"/>" />
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="8"><span>协会活动记录</span></th>
        			</tr>
   				 </thead>
			
				 <tbody>
					<tr>
						<th colspan="2">
							<font color="red">*</font>主 题
						</th>
						<td colspan="6" >
							<html:text  style="width:98%" property="zt" styleId="zt" />
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<font color="red">*</font>活动形式
						</th>
						<td colspan="2">
							<html:select  property="hdxs" style="width:145px" styleId="hdxs"
								onchange="check_qthdxs()">
								<html:option value=""></html:option>
								<html:options collection="hdxsList" property="DMH"
										labelProperty="DMMC" />
							</html:select>
						</td>
						<th colspan="2">
							其他形式
						</th>
						<td colspan="2" >
							<html:text property="qthdxs" styleId="qthdxs" readonly="true" />
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<font color="red">*</font>地点
						</th>
						<td  colspan="2">
							<html:text  property="dd" styleId="dd" />
						</td>
						<th colspan="2">
							<font color="red">*</font>活动日期
						</th>
						<td colspan="2">
							<html:text style="cursor:hand;" styleId="dateF"
									property="rq"
									onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="true" />
						</td>
					</tr>
					<tr>
						<th  colspan="2">
							开始时间
						</th>
						<td colspan="2">
							<html:text  property="kssj" styleId="kssj" />
						</td>
						<th colspan="2" nowrap="nowrap">
							结束时间
						</th>
						<td colspan="2">
							<html:text  property="jssj" styleId="jssj" />
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<font color="red">*</font>主持人
						</th>
						<td colspan="2">
							<html:text  property="zcr" styleId="zy" />
						</td>
						<th colspan="2">
							<font color="red">*</font>学生
						</th>
						<td colspan="2">
							<html:text  property="xs"  />
						</td>
					</tr>
					<tr style="height:22px">
						<th colspan="2">
							<font color="red">*</font>参与学生
						</th>
						<td colspan="6">
							<html:text  style="width:98%" property="cyxs" styleId="cyxs" />
						</td>
					</tr>
					<tr>
						<th colspan="2" nowrap="nowrap">
							<font color="red">*</font>参与学生人数
						</th>
						<td colspan="6">
							<html:text  property="rs" styleId="rs" />
						</td>
					</tr>
					<tr>
						<th colspan="2"><font color="red">*</font>
							活动记录
						</th>
                    	<td colspan="6" >
                    		<html:textarea rows="5" style="word-break:break-all;width:99%" property="hdjl" styleId="a" />
                    	</td>
					</tr>			
				   <tr>
				  		<th colspan="2">
				  			<font color="red">*</font>
				  			活动效果
				  		</th>
                    	<td colspan="6">
                    		<html:textarea rows="5"  style="word-break:break-all;width:99%" property="hdxg" styleId="a" />
                    	</td>
				  </tr>
				  </tbody>
					 <tfoot>
				      <tr>
				        <td colspan="8"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				     
							<button  onclick="xlxhhd_modi()" id="buttonUpdate">
								修 改
							</button>
							<button  onclick="Close();return false;" 
								 id="buttonClose" >
								关 闭
							</button>
										           
				          </div>
				          </td>
				      </tr>
				    </tfoot>
				</table>
				
				<div id="tmpdiv"></div>	
<%--				<div class="buttontool" align="center" style="width:100%">--%>
<%--					<button class="button2"  onclick="xlxhhd_modi()" style="width:80px" id="buttonUpdate">--%>
<%--						修 改--%>
<%--					</button>--%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--					<button class="button2"  onclick="Close();return false;" style="width:80px" id="buttonClose">--%>
<%--						关 闭--%>
<%--					</button>--%>
<%--				</div>--%>
			<logic:notEmpty name="message">
					<logic:equal name="message" value="update_success">
						<script>
						alert("更新成功!");
						window.dialogArguments.document.getElementById("search_go1").click();
						Close();
						</script>
					</logic:equal>
					<logic:equal name="message" value="update_fail">
						<script>
						alert("更新失败!");
						document.getElementById("tmpdiv").innerHTML = "";
						</script>
					</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
