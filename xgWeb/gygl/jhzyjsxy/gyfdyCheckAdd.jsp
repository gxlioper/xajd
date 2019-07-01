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
	<head>
		<base target="_self" />
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="zpfSum()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>

		<html:form action="/jhzy_gygl" method="post">
			<input type="hidden" id="xn" name="xn" value="${xn }" />
			<input type="hidden" id="xq" name="xq" value="${xq }" />
			<input type="hidden" id="act" name="act" value="${act }" />			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ԣ����Ա���� - ��Ԣ����Ա���� - ά��
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							��Ԣ����Ա����
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>��Ԣ����Ա��
					</td>
					<td height="22" align="left">
					<logic:empty name="act">
						<html:select property="zgh" styleId="zgh" onchange="refreshForm('/xgxt/jhzy_gygl.do?method=gyfdyCheckAdd')">
							<html:option value=""></html:option>
							<html:options collection="gyFdyList" property="yhm"
								labelProperty="xm" />
						</html:select>
					</logic:empty>
					<logic:notEmpty name="act">
					     ${rs.xm}
					     <input type="hidden" id="zgh" name="zgh" value="${rs.zgh}" />	
					</logic:notEmpty>	
					</td>
					<td height="22" align="right">
						ѧ�꣺
					</td>
					<td height="22" align="left">
						<bean:write name="xn"/>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>�·ݣ�
					</td>
					<td height="22" align="left">
						<logic:empty name="act">
						<html:select property="yf" styleId="yf" onchange="refreshForm('/xgxt/jhzy_gygl.do?method=gyfdyCheckAdd')" >
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf"
								labelProperty="yf" />
						</html:select>
						</logic:empty>
						<logic:notEmpty name="act">
					     ${rs.yf}
					     <input type="hidden" id="yf" name="yf" value="${rs.yf}" />	
					</logic:notEmpty>
					</td>
					<td height="22" align="right">
						ѧ�ڣ�
					</td>
					<td height="22" align="left">
						<bean:write name="xqmc"/>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						����Աְ���ţ�
					</td>
					<td height="22" align="left">
						${rsGyfdy.yhm }
					</td>
					<td height="22" align="right">
					    ���ڲ��ţ�
					</td>
					<td height="22" align="left">
						${rsGyfdy.bmmc }
					<input type="hidden" name="xydm" value="${rsGyfdy.xydm}">
					</td>
				</tr>
						<tr>
					<td height="22" align="right">
						��ϵ�绰��
					</td>
					<td height="22" align="left">
						${rsGyfdy.lxdh }
					</td>
					<td height="22" align="right">
					   
					</td>
					<td height="22" align="left">
					
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<fieldset>
							<legend>
								��Ԣ����Ա���˼ӷ���
							</legend>
							<table width="100%" class="tbstyle">
								<tr>
									<td height="22" align="right">
										�Ͻ����ϼ�ʱ�ԣ�
									</td>
									<td height="22" align="left">
                                        <html:text name="rs" property="sjcljsx" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">
										��ʱ�μӻ��飺
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="ascjhy" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
								</tr>
								<tr>
									<td height="22" align="right">
										���õĸ������
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="bzgxgz" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">
										��Ҫ����ס��Ԣ��
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="ayqrzgy" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
								</tr>
								<tr>
									<td height="22" align="right">
										ÿ���ٿ�һ�����᣺
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="mzzkyclh" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">
										ÿ��������飺
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="mywsjc" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,20);zpfSum();"></html:text>
									</td>
								</tr>
								<tr>
									<td height="22" align="right">
										��Ϣ���ͼ�ʱ��
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="xxbsjs" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">
										̸����¼��
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="thjl" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
								</tr>
								<tr>
									<td height="22" align="right">
										��Ԣ������
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="gyhdqk" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);numLimit(this,10);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">

									</td>
									<td height="22" align="left">

									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<fieldset>
							<legend>
								��Ԣ����Ա���˼Ӽ�����
							</legend>
							<table width="100%" class="tbstyle">								
								<tr>
									<td height="22" align="right">
										ѧ�����
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="xshd" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">
										�򱨣�
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="jb" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);zpfSum();"></html:text>
									</td>
								</tr>
								<tr>
									<td height="22" align="right">
										����Ա��׼�֣�
									</td>
									<td height="22" align="left">
                                       <html:text name="rs" property="fdyjzf" style="width:80px" onkeypress='return sztzNumInputValue(this,5,event)' onblur="chkInput(this,event);zpfSum();"></html:text>
									</td>
									<td height="22" align="right">
										������ѧ������������
									</td>
									<td height="22" align="left">
                                       <html:text name="rsWsjcf" readonly="true" property="sfzxsqsws" style="width:80px" onblur="zpfSum();"></html:text>
									</td>
								</tr>
								<tr>
									<td height="22" align="right">
										
									</td>
									<td height="22" align="left">
									</td>
									<td height="22" align="right">
										ѧ�����Ҵ��ʵ�����
									</td>
									<td height="22" align="left">
                                       <html:text name="rsWsjcf" readonly="true" property="xsqsdgldq" style="width:80px" onblur="zpfSum();"></html:text>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						���������֣�
					</td>
					<td height="22" align="left" colspan="3">
						 <html:text name="rs" readonly="true" property="zpf" style="width:80px" ></html:text>
					</td>				
				</tr>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
				<logic:notEqual value="view" name="act">
				<button class="button2" onclick="dataSave('zgh-yf')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ��,��ϵͳ���Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function dataSave(mustFill){	           
	          var eles = mustFill.split("-");
	          for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			           alert("�뽫��\"*\"�ŵ���Ŀ����������");
			           return false;
		           }		
	          }	                      
              refreshForm("/xgxt/jhzy_gygl.do?method=gyfdyCheckAdd&doType=Save");
              $("buttonSave").disabled=true;
	     }
	     function numLimit(obj,num){
	         if(obj.value>num){
	         	alert("����������ܴ���"+num+"!")
                obj.value="0";
                return false;	         
	         }
	     }
	    function zpfSum(){
	      var  zpf=0;
	      var  mywsjc=$("mywsjc").value;
	      mywsjc = (isNaN(parseInt(mywsjc)))?0:parseFloat(mywsjc);
          var  xxbsjs=$("xxbsjs").value;
          xxbsjs = (isNaN(parseInt(xxbsjs)))?0:parseFloat(xxbsjs);
          var  thjl=$("thjl").value;
          thjl = (isNaN(parseInt(thjl)))?0:parseFloat(thjl);
          var  gyhdqk=$("gyhdqk").value;
          gyhdqk = (isNaN(parseInt(gyhdqk)))?0:parseFloat(gyhdqk);
          var  sfzxsqsws=$("sfzxsqsws").value;
          sfzxsqsws = (isNaN(parseInt(sfzxsqsws)))?0:parseFloat(sfzxsqsws);
          var  xsqsdgldq=$("xsqsdgldq").value;
          xsqsdgldq = (isNaN(parseInt(xsqsdgldq)))?0:parseFloat(xsqsdgldq);
          var  xshd=$("xshd").value;
          xshd = (isNaN(parseInt(xshd)))?0:parseFloat(xshd);
          var  jb=$("jb").value;
          jb = (isNaN(parseInt(jb)))?0:parseFloat(jb);
          var  fdyjzf=$("fdyjzf").value; 
          fdyjzf = (isNaN(parseInt(fdyjzf)))?0:parseFloat(fdyjzf);       
          var  sjcljsx=$("sjcljsx").value;
          sjcljsx = (isNaN(parseInt(sjcljsx)))?0:parseFloat(sjcljsx);   
          var  ascjhy=$("ascjhy").value;
          ascjhy = (isNaN(parseInt(ascjhy)))?0:parseFloat(ascjhy);  
          var  bzgxgz=$("bzgxgz").value;
          bzgxgz = (isNaN(parseInt(bzgxgz)))?0:parseFloat(bzgxgz);  
          var  ayqrzgy=$("ayqrzgy").value;
          ayqrzgy = (isNaN(parseInt(ayqrzgy)))?0:parseFloat(ayqrzgy);  
          var  mzzkyclh=$("mzzkyclh").value;
          mzzkyclh = (isNaN(parseInt(mzzkyclh)))?0:parseFloat(mzzkyclh);  
          zpf = mywsjc+xxbsjs+thjl+gyhdqk+sfzxsqsws+xsqsdgldq+xshd+jb+fdyjzf+sjcljsx+ascjhy+bzgxgz+ayqrzgy+mzzkyclh;
          $("zpf").value=zpf;
	    }
	</script>
</html>
