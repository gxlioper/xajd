<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
      function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
      function setTBGbed(){
          totalBed.innerText="0";
          boyBed.innerText="0";
          girlBed.innerText="0";
      }
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript">
    function fzLd(ele){ 
       var userType = document.getElementById("userType").value;
	   var xxdm = document.getElementById("xxdm").value;
	   if(xxdm=="13429"){//�ϲ���ѧ��ѧ����<bean:message key="lable.xsgzyxpzxy" />      
	      if (userType!="admin") {
		     var tmp = ele.split("-");
		     for (i = 0; i < tmp.length; i++) {
			   document.getElementById(tmp[i]).disabled = true;
		   }
		  }
	  }   
    }
    
    function choiceDisabled(ele) {
			var tmp = ele.split("-");
			for (i = 0; i < tmp.length; i++) {
				if (document.getElementById(tmp[i])) {
					document.getElementById(tmp[i]).disabled = true;
				}
			}
		}
    </script>
	</head>

	<body onload="fzLd('xq-xb-ld')">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������� - ���Ữ��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/dorm_distribute" method="post">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="<bean:write name="oldMappingItems" scope="request"/>"/>
			<input type="hidden" name="cws" id="cws" />
			<input type="hidden" name="addordel" id="addordel" />
            <input  style="display: none" id="acwsfp" onclick="AcwsAddOrDel()"/>
			<logic:notEmpty name="errINFO">
			<script language="javascript">
			     alert('<bean:write name="errINFO"/>');
			</script>
			</logic:notEmpty>
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>	
				
			<div style="width:790px; margin:0 auto; background:#F3F5F8;border:1px solid #B5D0E5;border-top:0;">
			    <div class="searchbox">
			        <p class="search_con">
                        <label style="color:#0357A0;font-weight:bold;">���ַ�ʽ</label>
                         <html:select property="fpfs" styleId="fpfs"  onfocus="beforSSFPSubmit()" style="width:160px">
							<html:option value="ass">����������</html:option>
							<html:option value="acw">����λ��</html:option>
						</html:select>
                       <span style="color:red; padding-left:20px;">��ʾ������סshift�����������������ƶ��ɽ��ж�ѡ��ͬʱ��ctrl���ɵ���ȥ����ѡ�е����</span> 
                    </p>
			    </div>	
					<div class="searchtab">
         <table width="100%" style="border-left:0; border-right:0;"> 
              <tr>
                <td width="50%" style="border-right:1px solid #ccc;">
                	<table width="100%"  cellpadding="0" cellspacing="0" class="tb02"> 
                    <tbody>
                      <tr>
                        <td width="14%">�꼶</td>
                        <td width="36%">
                        	<html:select property="nj" styleId="nj"  onfocus="beforSSFPSubmit()" onchange="ssfpTj();">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
							</html:select>
                        </td>
                        <th width="14%"><bean:message key="lable.xsgzyxpzxy" /></th>
                        <td width="36%">
                        	<html:select property="xydm" styleId="xy" onfocus="beforSSFPSubmit()" onchange="ssfpTj();" style="width:180px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
									
									<logic:equal value="xy" name="userType">
										<input type="hidden" name="xydm" value="${userDep }"/>
										<script type="text/javascript">
											choiceDisabled('xy');
										</script>
									</logic:equal>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="4">
                        	δ�����������ˣ���<span id="allbody" style="width: 70px"><bean:write name="total"scope="request" /></span>&nbsp;&nbsp;&nbsp;
                        	�У�<span id="allboy" style="color:#0058A4;"><bean:write name="boy" scope="request" /></span>&nbsp;&nbsp;&nbsp;
                        	Ů��<span id="allgirl" style="color:#0058A4;"><bean:write name="girl"scope="request" /></span>
                        </td>
                      </tr>
                    </tbody>
          			</table>
                </td>
            	<td>
                	<table width="100%" cellpadding="0" cellspacing="0" class="tb01" border="0"> 
                        <tbody>
                          <tr>
                           <th>У �� ��</th>
                            <td>
                            	<html:select property="xqdm"  styleId="xq" onfocus="beforSSFPSubmit()"
									onchange="ssFp_Xq()">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
                            </td>
                            <th>�Ա��޶�</th>
                            <td>
                            	<html:select property="xb" styleId="xb"  onfocus="beforSSFPSubmit()"
									onchange="if($('xq').value==''){}else{initSsFpLdList();}">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
									<html:option value="���">���</html:option>
								</html:select>
                            </td>
                          </tr>
                          <tr>
                            <th>¥ �� ��</th>
                            <td>
                            	<html:select property="lddm" styleId="ld"  onfocus="beforSSFPSubmit()"
									onchange="initSsFpFpCsList();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>
                            </td>
                            <th>�㡡��</th>
                            <td>
	                        	<html:select property="cs" styleId="cs"
									onfocus="beforSSFPSubmit()">										
									<html:options collection="lcList" property="dm"
									labelProperty="mc" />
								</html:select> 
                            </td>
                          </tr>
                        </tbody>
                      </table>
                </td>
              </tr>
              <tr>
                 <td colspan="2">
	                 <div class="bz">
	                 	�Ѿ����ִ�λ�����ˣ���<span id="totalBed" style="width: 70px"><bean:write name="totalBed"scope="request" /></span>&nbsp;&nbsp;&nbsp;
	                 	�У�<span id="boyBed" style="color:#0058A4;"><bean:write name="boyBed"scope="request" /></span>&nbsp;&nbsp;&nbsp;
	                 	Ů��<span id="girlBed" style="color:#0058A4;"><bean:write name="girlBed"scope="request" /></span>&nbsp;&nbsp;&nbsp;
	                 	��ϣ�<span id="bgBed" style="color:#0058A4;"><bean:write name="bgBed"scope="request" /></span>
	                 </div>
	                 <div class="btn">
	                 	<button type="button" onclick="allNotEmpThenGo('/xgxt/dorm_distribute.do')">��ѯ</button>
	                 </div>
                 </td>
               </tr>
            </table>
        </div>
				
		<div>
        <table width="100%" border="0" class="fieldlist">
      <tr>
        <td valign="top" width="47%">
       	 	 <table width="100%" style="border:1px solid #B5CEE2;">
	             <tr>
	                <td style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
	                	<label style="font-weight:bold;color:#0157A2;">�ѻ�������</label>
	               		<span>�꼶/<bean:message key="lable.xsgzyxpzxy" />/¥��/��/���Һ�/�ܴ�λ��/���ִ�λ��</span>
	               	</td>
	            </tr>
	            <tr>
	                <th >
	                	<div class="listcon" id="yfpDiv">
	                		<html:select property="sql" style="width:100%;" size="27" styleId="sql" multiple="multiple" onmouseover="">
								<html:options collection="ssfpList" labelProperty="mc" property="dm" />
							</html:select>
	                	</div>
	                </th>
	        	</tr>
	        </table>
        </td>
        <td style="padding:0" align="center">
        	<div class="btn_choose">
        		<button type="button" class="right" onclick="delBatchColum()"></button><br />
        		<button type="button" class="left" onclick="addBatchColum()"></button><br />
        	</div>
        </td>
        <td valign="top" width="48%">
         <table width="100%" style="border:1px solid #B5CEE2;">
             <tr>
                <td style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
	                <label style="font-weight:bold;color:#0157A2;">δ��������</label>
	                <span>¥�� / �� / ���Һ� / �ܴ�λ�� / ʣ�ലλ��</span>
                </td>
            </tr>
            <tr>
                <th >
                	<div class="listcon" id="wfpDiv">
                		<html:select property="oracleItem" style="width:100%;"
									size="27" styleId="oracleList" multiple="multiple" onmouseover="">
							<html:options collection="ssxxList" labelProperty="mc" property="dm" />
						</html:select>
                	</div>
                </th>
       		</tr>
        </table>
        
       	</td>
          </tr>
          <tr>
										<td align="center" colspan="3">
											<div class="btn">
											<button type="button"  name="button1"
												style=""
												onclick="if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѻ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){dormDistSave()}">ȷ ��</button>
											<button type="button"  name="button2"
												style=""
												onclick="refreshForm('/xgxt/dorm_distribute.do')">�� ��</button>
											</div>
										</td>
									</tr>
	  		</table>
	        </div>
	        </div>		
			<div id="tmpdiv"></div>
			<div id="tmpdivone"></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>			
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
					alert("�����ɹ�!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
		<script type="text/javascript" defer>
			oldCondiSqlVConn();
		</script>
	</body>
</html>
