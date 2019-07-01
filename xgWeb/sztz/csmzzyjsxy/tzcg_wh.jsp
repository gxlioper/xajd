<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
    <base target="_self">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body onload="getXzx();onLoadXsxx()">
		<script language="javascript" src="js/sztz/csmzzyjsxy/tzcg_wh.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/sztzLzzy.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<html:form action="/csmz_sztz.do?method=tzcg_wh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�${title}
				</div>
			</div>			
			<logic:equal value="1" name="sqsjTag">
			   <br />
				<br />
				<p align="center">
					<font color="red">�����趨ʱ�䷶Χ��,�ݲ������걨!</font>
				</p>
			</logic:equal>   			
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
				<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
				<input type="hidden" id="url" name="url" value="/csmz_sztz.do?method=tzcg_sb" />
				<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
				<input type="hidden" id="userName" name="userName" value="${userName }"/>
				<input type="hidden" id="userType" name="userType" value="${userType }"/>
				<input type="hidden" id="userDep" name="userDep" value="${userDep }"/>
				<input type="hidden" name="cols" value=""/>
				<fieldset>
					<legend>
						��д�걨��
					</legend>
					<table width="100%"  id="rsT" class="tbstyle">
						<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b>��д�걨��</b>
								</td>
							</tr>
						</thead>
						<tr>
								<td align="left" width="50%" colspan="2"  align="center" >
									ѧ�꣺
									<html:select property="xn" styleId="xn" onchange="getHdxm()">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									ѧ�ڣ�
									<html:select property="xq" styleId="xq" onchange="getHdxm()">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<td align="center" width="25%">
									<font color="red">*</font>ѡ���
									<html:select property="xzx" styleId="xzx" onchange="getXzx()">
										<html:option value="xy"><bean:message key="lable.xb" /></html:option>
										<html:option value="zy">רҵ</html:option>
										<html:option value="bj">�༶</html:option>
										<html:option value="xh">ѧ��</html:option>
										<html:option value="xm">����</html:option>
									</html:select>
								</td>
								<td align="center">
									 <input type="checkbox" name="fanxuan" onclick="fxxs()" styleId="titList"/>��ѡ	
								</td>
						<tr>
							<td align="left" width="18%" colspan="2">
							    ������Ŀ��
							    	<html:select property="kmmc" styleId="kmmc" onchange="getHdxm()">
							    		<html:option value=""></html:option>
							    		<html:options collection="kmList" property="kmdm" labelProperty="kmm" />
							    	</html:select>
							    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    ��Ŀ����
							    <html:select property="xmjb" styleId="xmjb" onchange="getHdxm()">
							    			<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
							    	</html:select>
						    </td>
						    <td  colspan="2" align="center">
						    	<!-- ѧԺ���ؿ� -->
						        <div id="xymc">
						        <bean:message key="lable.xb" />��
						        <logic:equal name="userType" value="xy">
						        <html:select property="query_xydm" disabled="true" styleId="query_xydm" >
						        	<html:option value=""></html:option>
						        	<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						        </html:select>
						        <html:hidden property="xydm" styleId="xy" value="${userDep}"/>
						        </logic:equal>
						        
						        <logic:notEqual name="userType" value="xy">
						        <html:select property="xydm" onchange="initZyList();initBjList();getStuXxByXy()" 
						        	 styleId="xy">
						        	<html:option value=""></html:option>
						        	<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						        </html:select>	
						        </logic:notEqual>
						         </div>
						        <!-- רҵ���ؿ� -->
						        <div id="zymc" style="display : none">
						        רҵ��
						        <html:select property="zydm" style="display : none;" onchange="initBjList();getStuXxByZy()" 
						        	styleId="zy">
						        	<html:option value="">---��ѡ��---</html:option>
						        	<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						        </html:select>	
						        </div>
						        <div id="bjmc" style="display : none">
						        �༶��
						        <html:select property="bjdm" style="display : none;" onchange="getStuXxByBj()" 
						        	 styleId="bj">
						        	<html:option value="">---��ѡ��---</html:option>
						        	<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						        </html:select>	
						        </div>
						       
						        <html:text property="xh" style="display : none"/>
						        
						        <input type="button" value="��ѯ" style="display : none" name="xhbutton" id="xhbutton" onclick="getStuXxByXh()"/>
						      	<html:text property="xm" style="display : none"/> 
						      	<input type="button" value="��ѯ" style="display : none" name="xmbutton" id="xmbutton" onclick="getStuXxByXm()"/>			       
						    </td>
						</tr>
						<tr style="height:22px">
							<td align="right" width="18%">
								<font color="red">*</font>�(��Ŀ)��
							</td>
							<td align="left">
								 <html:select property="xmmc" onchange="getTzxmXx()" style="width:150px" 
								 	styleId="xmmc">
						        	<html:options collection="hdxmList" property="dm" labelProperty="mc" />
						        </html:select>	
							</td>
							<td align="center" colspan="2" rowspan="8" >
							<select name="titList" style="width:300px;cursor:hand;" size="20" name="powerSub"
									id="titList"  ondblclick="chkOnRow(this)">
										
									</select>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								ָ����ʦ��
							</td>
							<td align="left">
								${rs.zzdw}
							</td>		
						</tr>
						<tr style="height:22px">
							<td align="right">
								������Ŀ��
							</td>
							<td align="left">							
								${rs.kmmc}
							</td>							
						</tr>
						<tr style="height:22px">
							<td align="right">
								��Ŀ����ϵ(����)��
							</td>
							<td align="left">
								${rs.bmmc}
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�(��Ŀ)���ͣ�
							</td>
							<td align="left">
								${rs.xmjb}
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������(�걨��)��
							</td>
							<td align="left">
								${rs.xmsbr}
							</td>							
						</tr>						
						<tr align="left">
							<td align="right">
								�μ���ݣ�
							</td>
							<td align="left">
								<html:select  property="cyjs" style="width:90px;background-color:#FFFFFF"
								styleId="cyjs">
								<html:option value="����">����</html:option>
								<html:options collection="cjsf" property="en"
									labelProperty="cn" />
							    </html:select>
							</td>						
						</tr>
						<tr align="left">				
							<td align="right">
								<font color="red">*</font>�񽱽��
							</td>
							<td align="left">
								<html:select property="jxlb" styleId="jxlb">
								<html:option value=""></html:option>
								<html:options collection="jxjbList" property="jxid"
									labelProperty="jxm" />
								</html:select>
							</td>
						</tr>		
						<tr align="left">
							<td align="right">
								��Ŀ������
							</td>
							<td colspan="3">							    
								${rs.xmms }			
							</td>
						</tr>							
						<tr align="left">
							<td align="right">
								�ɹ�������
								<br>(��300����)
							</td>
							<td colspan="3">
								 <html:textarea  property='cgms' styleId="cgms"  
								    rows='8' cols="80"/>
							</td>
						</tr>				
					</table>
					<logic:equal name="writeAble" value="yes">
					<div class="buttontool" align="center">
						<button class="button2" id="buttonSave"
							onclick="saveInfo()">
							�� �� �� ��
						</button>
					</div>
					</logic:equal>
				</fieldset>
			<logic:equal value="true" name="result">
			  <script type="text/javascript">
			      alert('�걨�ɹ���');
			  </script>
			</logic:equal>
		</html:form>
	</body>
</html>
