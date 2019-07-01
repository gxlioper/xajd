<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="xnxqNosee()">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		
    <html:form action="/csmz_sztz.do?method=tzxm_xxcx" method="post">
			<input type="hidden" id="url" name="url" value="<bean:write name="url" scope="request"/>" />
			<input type="hidden" id="xh" name="xh" value="<bean:write name="xh" scope="request"/>" />
			<input type="hidden" id="xnxqNos" name="xnxqNos" value="<bean:write name="xnxqNos" scope="request"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>

			<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
						      <tfoot>
						        <tr>
						          <td colspan="6">
						            <div class="btn">
						            	<input type="hidden" name="go" value="a" />
						              <button class="btn_cx" id="search_go" 
						              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=tzxm_xxcx');">
						              	查 询
						              </button>
						              &nbsp;&nbsp;&nbsp;&nbsp;
						              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
						              	重 置
						              </button>
						            </div>
						          </td>
						        </tr>
						      </tfoot>

							<tbody>							
								<tr>
									<th align="left">
										学年
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
									 所属科目
									 </th>
									 <td>
										<html:select property="kmdm" style="width:150px"
											styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>										
									</td>
								</tr>
								<tr>
									<th align="left">																				
										项目级别
									</th>
									<td>
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;">
											
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>
									<th>
										项目名称
									</th>
									<td>
										<html:text property="xmmc">
										</html:text>
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>							
						</tbody>
					</table>
					</div>
				</div>
				<div class="formbox">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 	记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：单击表头可以排序;</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
				    <logic:notEmpty name="rs">					
							  <table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>	
								<tbody>		
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="sendTzxmInfo();">
											<td align="center">
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td align="center">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>						 
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
									<script type="text/javascript">
														$('choose').className="hide";
												</script>
				</logic:notEmpty>
			</div>
	</html:form>	
    </body>
<script type="text/javascript">
function sendTzxmInfo() {	
	if(confirm("确定选择该项目吗?")){
		var xmdm = replaceChar(curr_row.getElementsByTagName('input')[0].value," ","");
		var xh = window.dialogArguments.document.forms[0].xh.value;
		var pk = xh+xmdm;
		var url = document.forms[0].url.value ;
		var str = "";
<%--   	 	getSztzData.getInfoEx("csmz_tzcgb","xh||xmid",pk," ( fdysh='通过' or xysh='通过' or xxsh='通过') ",function(data){--%>
<%--		         if(data){--%>
<%--		            alert("该生该学年、学期该拓展项目成果已申报，并通过审核或正在审核中,不能再进行操作！");--%>
<%--			        return false;--%>
<%--		         }else{--%>
		         	var vel = window.dialogArguments.document.forms[0].xmmc;
					vel.focus();
					vel.value = replaceChar(curr_row.cells[2].innerText," ","");				
					if(url.indexOf("tzcg_sb")!=-1){
					    str = "/xgxt/csmz_sztz.do?method=tzcg_sb&from=" +url+"&xh="+xh+"&xmdm="+xmdm+"&cxdo=true";
					}else if(url.indexOf("xxwh_tzcgcj")!=-1){		
					    str = "/xgxt/csmz_sztz.do?method=xxwh_tzcgcj&from=" +url+"&xh="+xh+"&xmdm="+xmdm+"&cxdo=true";		    				   
    				}
		            window.dialogArguments.document.forms[0].action = str;
	                window.dialogArguments.document.forms[0].submit(); 	
	                window.close();	        		            	
<%--		         }		        --%>
<%--		         }); 		--%>
	}else{
		return false;
	}
}
function xnxqNosee(){
<!--     var xnxqNos = $("xnxqNos").value;-->
<!--     if(xnxqNos=="true"){-->
<!--         $("xn").disabled=true;-->
<!--         $("xq").disabled=true;-->
<!--     }-->
}
</script>

</html>	