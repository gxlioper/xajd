<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<script language="javascript">
       	  function hiddenField() {
		     i = document.getElementsByTagName("select").length;
		    for (j = 0; j < i; j++) {
			  document.getElementsByTagName("select")[j].style.visibility = "hidden";
		    } 
	      }
	      
	      function getWfpGw(){
	      	if (null == $('xq').value || ""==$('xq').value){
	      		alert("��ѡ��У��");
	      		return false;
	      	}
	      
	      	if (null == $('cs').value || ""==$('cs').value){
	      		if(confirm('��ѡ���Ų�ѯЧ�ʻ��ܵ�Ӱ�죬��ȷ��Ҫ��������\n ��ʾ����ѡ��¥�������ܼ��ص���ţ�')){
	      			initCwFpSsCwXxList();
	      		}
	      	} else {
		      	initCwFpSsCwXxList();
	      	}
	      
	      }
	      
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
	</head>
	<body onload="xyDisabled('xy');">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������� - ��λ����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/bed_distribute" method="post">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<html:hidden name="commanForm" property="count" styleId="count" />
			<html:hidden name="commanForm" property="boy" styleId="boy" />
			<html:hidden name="commanForm" property="girl" styleId="girl" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" value="<bean:write name="oldMappingItems" scope="request"/>"/>
			<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm" scope="request"/>"/>
			<input type="hidden" name="mappingItems" value="" />       	
			<input type="hidden" name="bjV" id="bjV" />				
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />			
            <div id="items" name="items" style="display:none; position: absolute;color: blue; " >
				<span>&nbsp;&nbsp;&nbsp;&nbsp;������ѧ�ţ������س���</span>
			</div>
			<logic:notEmpty name="errINFO">
			<script language="javascript">
			     alert('<bean:write name="errINFO"/>');
			</script>
			</logic:notEmpty>
            <div id="showDiv" style="display:none" align="center">
             <iframe  src='javascript:false' border=0px  style='position: absolute;visibility: inherit;top: 0px;left: 0px;width: 350px;height: 200px;z-index: -999;filter: Alpha(Opacity=0)'></iframe>          
					<table class="formlist" border="0" align="center" style="width: 100%">
						<thead>
							<tr>
								<th colspan="4">
									<span>��סʱ������</span>
								</th>
							</tr>
						</thead>
						<tbody>		
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>��סʱ�䣺
								</td>
								<td align='left'>
								<html:text property="zsrq" styleId="zsrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zsrq','y-mm-dd','aa');" readonly="true"/>
								</td>
							</tr>
						<tbody>		
						<tfoot>
								<tr>
									<td colspan='2'>
										<div class="btn">
										<button type="button" id="kfbtnSave" onclick='addCwColum()'>
											�ύ
										</button>
										&nbsp;&nbsp;
										<button type="button" onclick='hiddenMessage(true,true)'>
											�ر�
										</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
				</div>			
				
				<div style="width:790px; margin:0 auto; background:#F3F5F8;border:1px solid #B5D0E5;border-top:0;">
			    <div class="searchbox">
			        <p class="search_con">
                        <label style="color:#0357A0;font-weight:bold;">��λ����</label>
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
                        <td><font color="red">*</font>�꼶</td>
                        <td>
                        	<html:select property="nj"  onfocus="beforSubmit()"
								onchange=" CwFp_Nj()" styleId="nj">
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
                        </td>
                        <th><font color="red">*</font><bean:message key="lable.xsgzyxpzxy" /></th>
                        <td>
                        	<html:select property="xydm"  styleId="xy" onfocus="beforSubmit()"
								onchange=" CwFp_Xy()" style="width: 150px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>	
                        </td>
                        </tr>
                        <tr>
                        <th>�༶</th>
                        <td>
                        	<html:select property="bjdm"  styleId="bj" onfocus="beforSubmit()"
								onchange=" CwFp_Bj()" style="width: 150px">                                 
                                    <html:options collection="bjList" property="dm" labelProperty="mc"/>
							</html:select>
                        </td>
                         <logic:notEqual name="xxdm" value="11553">
                         	<th></th>
                         	<td></td>
                         </logic:notEqual>
                        
                        <logic:equal name="xxdm" value="11553">
							<th>
								ѧ��
							</th>
							<td>
							<html:text property="ksh" styleId="ksh" 
								style="ime-mode:disabled"
								onfocus="beforSubmit();"  
								onblur="hiddenItems();"
								onkeypress="getStuInfo();return onlyNum(this,20);"/>
								&nbsp;&nbsp;
								<br/>
							<font color="blue">¼����Ҫ����������䴲λ��ѧ�ţ���ɺ󰴻س���</font>
							</td>
						</logic:equal>	
                      </tr>
                    </tbody>
          			</table>
                </td>
            	<td>
            		<table width="100%" cellpadding="0" cellspacing="0" class="tb01" border="0"> 
                        <tbody>
                          <tr>
                           <th><font color="red">*</font>У �� ��</th>
                            <td>
                            	<html:select property="xqdm" styleId="xq" 
									onfocus="beforSubmit()" onchange="CwFp_Xq()">									
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
                            </td>
                            <th>�Ա��޶�</th>
                            <td>
                            	<html:select property="xb" styleId="xb" onfocus="beforSubmit()"
									onchange="if($('xq').value==''){}else{ CwFp_Xb()}">
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
                            	<html:select property="lddm"  styleId="ld" onfocus="beforSubmit()"
									onchange="CwFp_Ld()">	
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>
                            </td>
                            <th>�㡡��</th>
                            <td>
	                        	<html:select property="cs" styleId="cs"  onfocus="beforSubmit()">
									<html:options collection="csList" property="dm"
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
                   	δ��������(��)��<span id="allbody" style="width: 70px"><bean:write name="total" scope="request" /></span>&nbsp;&nbsp;&nbsp;
                   	�У�<span id="allboy" style="color:#0058A4;"><bean:write name="boy" scope="request" /></span>&nbsp;&nbsp;&nbsp;
                   	Ů��<span id="allgirl" style="color:#0058A4;"><bean:write name="girl" scope="request" /></span>
                   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   	�ѷ�������(��)��<span id="totalBed" style="width: 70px"><bean:write name="totalBed"scope="request" /></span>&nbsp;&nbsp;&nbsp;
                   	�У�<span id="boyBed" style="color:#0058A4;"><bean:write name="boyBed" scope="request" /></span>&nbsp;&nbsp;&nbsp;
                   	Ů��<span id="girlBed" style="color:#0058A4;"><bean:write name="girlBed"scope="request" /></span>&nbsp;&nbsp;&nbsp;
                   	</div>
					<div class="btn">
	                   <button type="button" onclick="allNotEmpThenGo('/xgxt/bed_distribute.do?doType=query')">��ѯ</button>&nbsp;&nbsp;
					</div>
                   </td>
                 </tr>
              </table>
        </div>
		<div>
        <table width="100%" border="0" class="fieldlist">
      <tr>
        <td width="25%">
       	  <table width="100%" style="border:1px solid #B5CEE2;">
             <tr>
                <td style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
	                <label style="font-weight:bold;color:#0157A2;">δ���䴲λ</label><br/>
	                <span>���ұ��/��λ��/��λ��</span>
                </td>
            </tr>
            <tr>
                <th >
                	<div class="listcon" id="wfpDiv">
                		<html:select property="oracleItem" style="width:100%;" size="27" 
                			multiple="multiple" styleId="oracleList" onmouseover="">
							<html:options collection="ssxxList" labelProperty="mc"
									property="dm" />
						</html:select>
                	</div>
                </th>
       		</tr>
        </table>
        </td>
        <td width="25%">
       	  <table width="100%" style="border:1px solid #B5CEE2;">
             <tr>
                <td style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
	                <label style="font-weight:bold;color:#0157A2;">δ����ѧ��</label><br/>
	                <span>ѧ��/����/�Ա�</span>
                </td>
            </tr>
            <tr>
                <th >
                	<div class="listcon">
                		<html:select property="xh" style="width:100%;" size="27"
								styleId="xh" multiple="multiple" onmouseover="">
								<logic:notEmpty name="xsList">
									<html:options collection="xsList" labelProperty="mc" 
										property="dm" />
								</logic:notEmpty>
							</html:select>
                	</div>
                </th>
       		</tr>
        </table>
        </td>
        <td style="padding:0" align="center">
        	<div class="btn_choose">
        		<button type="button" class="right" onclick="addCwBatchColum()"></button><br />
        		<button type="button" class="left" onclick="delCwBatchColum();"></button><br />
        	</div>
        </td>
        <td valign="top" width="42%">
	          <table width="100%" style="border:1px solid #B5CEE2;">
	             <tr>
	                <td style="background:#EAF4FE; padding:7px;border-bottom:1px solid #D6E7EF">
	                	<label style="font-weight:bold;color:#0157A2;">�ѷ������</label><br/>
	               		<span>ѧ��/����/�Ա�/���ұ��/��λ��/���䴲λ��/��סʱ��</span>
	               	</td>
	            </tr>
	            <tr>
	                <th >
	                	<div class="listcon">
	                		<html:select property="sql" size="27" style="width:100%" 
	                			ondblclick="" styleId="sql"  multiple="multiple" onmouseover="">
								<html:options collection="yfpCwList" property="dm"
										labelProperty="mc" />
								</html:select>	
	                	</div>
	                </th>
	        	</tr>
	        </table>
       	</td>
          </tr>
          <tr>
				<td align="center" colspan="4">
					<div class="btn">
					<button type="button" name="button1"														
						onclick="if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѷ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){bedDistSave()}" >ȷ ��</button>
					<button type="button" onclick="refreshForm('/xgxt/bed_distribute.do')" >�� ��</button>
					</div>
				</td>
			</tr>
	  		</table>
	        </div>
	        </div>		
			<div id="tmpdivone" ></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:present name="back">
				<script>
					alert("${back}");					
				</script>
			</logic:present>
			<logic:equal name="doFlag" value="ok">
				<script>
<%--				DOCUMENT.FORMS[0].ACTION = "/XGXT/BED_DISTRIBUTE.DO";--%>
<%--				document.forms[0].submit();--%>
				alert("�����ɹ�!");				
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
				//document.forms[0].action = "/xgxt/bed_distribute.do";
				//document.forms[0].submit();
				alert("����ʧ��!");				
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script language="javascript">	
function showItems(){
	var items = document.getElementById("items");
	if($("ksh").value==""){
		items.style.left = (screen.availwidth)/2.2;
		items.style.top = ((screen.availheight)/9);
		items.style.display = "block";
	}
}
function hiddenItems(){
    var items = document.getElementById("items");
    items.style.display = "none";
}

function getStuInfo(){
	
	dwr.engine.setAsync(false);
	
	if(event.keyCode == "13"){
		
		var xh = $("ksh").value;
		var xq = $("xq").value;
		var ldxb = $("xb").value;
		var lddm = $("ld").value;
		var cs = $("cs").value;
			
		if(xh != ""){
			
			var tableName = "view_xszsxx";
			var colList = ["ldmc","cs","qsh","cwh"];
			var pk = "xh";
			var pkValue = xh;
			var query = "";
			
			var flag = true ;
			getOtherData.getTableInfo(tableName,colList,pk,pkValue,query,function(data){
				if(data != null){
					var msg = "�����Ѱ���ס��,��������Ϊ"+data[0]+data[1]+"��"+data[2]+"��"+data[3]+"�Ŵ�";
					alert(msg);
					flag = false;
				}
			});
				
			tableName = "view_xsjbxx";
			colList = ["xm","xb","nj","xydm","bjdm"];
		
			if(flag){
				getOtherData.getTableInfo(tableName,colList,pk,pkValue,query,function(data){
					if(data != null){
						var xm = data[0];
						var xb = data[1];
						var nj = data[2];
						var xydm = data[3];
						var bjdm = data[4];
						
						$("nj").value = nj;
						$("xy").value = xydm;
						CwFp_Nj();
						CwFp_Xy();
						
						$("bj").value = bjdm;
						CwFp_Bj();
						
						CwFp_Xq();
						if(xq != ""){
							$("xq").value = xq;
						}
						
						if(ldxb != ""){
							$("xb").value = ldxb;
						}
						CwFp_Xb();
							
						if(lddm != ""){
							$("ld").value = lddm;
						}
						CwFp_Ld();
						
						if(cs != ""){
							$("cs").value = cs;
						}
						CwFp_Cs();
						
						var optionText = xh + "/" + xm + "/" + xb;
						
						var id = "xh";
						var options = [{dm:xh,mc:optionText}];
						DWRUtil.removeAllOptions(id);
						DWRUtil.addOptions(id,options,"dm","mc");
				
					}else{
						alert("��ѧ��������");
						return false;
					}
				});
			}
		}else{
			CwFp_Xq();
			if(xq != ""){
				$("xq").value = xq;
			}
			
			if(ldxb != ""){
				$("xb").value = ldxb;
			}
			CwFp_Xb();
					
			if(lddm != ""){
				$("ld").value = lddm;
			}
			CwFp_Ld();
				
			if(cs != ""){
				$("cs").value = cs;
			}
			CwFp_Cs();
		}
				
	}
	dwr.engine.setAsync(true);
}
</script>
</html>
