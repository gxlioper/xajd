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
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
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
						              	�� ѯ
						              </button>
						              &nbsp;&nbsp;&nbsp;&nbsp;
						              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
						              	�� ��
						              </button>
						            </div>
						          </td>
						        </tr>
						      </tfoot>

							<tbody>							
								<tr>
									<th align="left">
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
									 ������Ŀ
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
										��Ŀ����
									</th>
									<td>
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;">
											
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>
									<th>
										��Ŀ����
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
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 	��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������;</font>
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
	if(confirm("ȷ��ѡ�����Ŀ��?")){
		var xmdm = replaceChar(curr_row.getElementsByTagName('input')[0].value," ","");
		var xh = window.dialogArguments.document.forms[0].xh.value;
		var pk = xh+xmdm;
		var url = document.forms[0].url.value ;
		var str = "";
<%--   	 	getSztzData.getInfoEx("csmz_tzcgb","xh||xmid",pk," ( fdysh='ͨ��' or xysh='ͨ��' or xxsh='ͨ��') ",function(data){--%>
<%--		         if(data){--%>
<%--		            alert("������ѧ�ꡢѧ�ڸ���չ��Ŀ�ɹ����걨����ͨ����˻����������,�����ٽ��в�����");--%>
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