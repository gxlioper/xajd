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
		<title><bean:message key="lable.title" />
		</title>
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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDyInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript">
      function dataSave(){
<%--         var strv=$("strVal").value;--%>
<%--         var xh = $("xh").value;--%>
<%--         var xn = $("xn").value;--%>
<%--         var act  = $("act").value;--%>
<%--         if(act!="view"&&act!="modi"){--%>
<%--             var exist = "false";--%>
<%--             dwr.engine.setAsync(false); --%>
<%--             getSztzData.getInfoEx("zhszbxfb","xh||xn",xh+xn," fs is not null",function(bol){--%>
<%--                if(bol){--%>
<%--                   exist="true";--%>
<%--                }             --%>
<%--             });--%>
<%--             dwr.engine.setAsync(true); --%>
<%--             if(exist=="true"){--%>
<%--                alert("������ѧ����ַ��걨�Ѿ������ʦ��֣������ٽ����걨���޸ģ�");--%>
<%--                return false;--%>
<%--             }--%>
<%--         }--%>
<%--         var len = document.getElementById("bxrow").rows.length;--%>
<%--         var xh ="";--%>
<%--         var lb ="";--%>
<%--         var dm ="";--%>
<%--         var nr ="";--%>
<%--         if($("xh")){--%>
<%--             xh = $("xh").value; --%>
<%--             if(xh==""){--%>
<%--                alert("ѧ�Ų���Ϊ�գ�");--%>
<%--                return false;--%>
<%--             }--%>
<%--         } --%>
<%--         var num = 1; --%>
<%--         for(var j=1;j<len;j++){--%>
<%--            lb = document.getElementById("bxrow").rows[j].cells[1].getElementsByTagName('select')[0].value;        --%>
<%--            dm = document.getElementById("bxrow").rows[j].cells[2].getElementsByTagName('select')[0].value;  --%>
<%--            nr = document.getElementById("bxrow").rows[j].cells[3].getElementsByTagName('textarea')[0].value;--%>
<%--            --%>
<%--            if(lb==""||lb=='null'){--%>
<%--                alert("��"+num+"�С��������Ϊ�գ�");--%>
<%--                return false;--%>
<%--            }       --%>
<%--            if(dm==""||dm=='null'){--%>
<%--               alert("��"+num+"�С����֡�Ϊ�գ�");--%>
<%--               return false;     --%>
<%--            }                        --%>
<%--            if(nr.length>1000){--%>
<%--               alert("��"+num+"�С��������ݡ���������(��1000��)");--%>
<%--               return false;--%>
<%--            }  --%>
<%--            num++;                                          --%>
<%--       }--%>
<%--       for(j=1;j<len;j++){--%>
<%--           var lbV1 = document.getElementById("bxrow").rows[j].cells[1].getElementsByTagName('select')[0].value;        --%>
<%--           var dmV1 = document.getElementById("bxrow").rows[j].cells[2].getElementsByTagName('select')[0].value;           --%>
<%--           var lbV2 = "";--%>
<%--           var dmV2 = "";--%>
<%--           for(i=1;i<len;i++){--%>
<%--                 lbV2 = document.getElementById("bxrow").rows[i].cells[1].getElementsByTagName('select')[0].value;        --%>
<%--                 dmV2 = document.getElementById("bxrow").rows[i].cells[2].getElementsByTagName('select')[0].value; --%>
<%--                 if((lbV1==lbV2)--%>
<%--                         &&(dmV1==dmV2)--%>
<%--                         &&(j!=i)){--%>
<%--                    alert("��"+j+"�С�������𡯺͡����֡�ֵ\n\n���"+i+"�С�������𡯺͡����֡�ֵ����ͬ��\n\n�����ύ��ֵͬ����˶Ժ����ύ��");--%>
<%--                    return false;--%>
<%--                 }--%>
<%--           }--%>
<%--         }--%>
         refreshForm('/xgxt/gzdxPjpy.do?method=zhBxPf&doType=save')
      }
    </script>
	<body >
		<html:form action="/gzdxPjpy" method="post">
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="xn"/>" />	
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act"/>" />	
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue"/>" />			
			<div class="title">
				<div class="title_img" id="title_m">
                     ��ǰ����λ�ã��������� - �۲���Ϣά�� - �ۺϱ����걨����
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							�ۺϱ�������
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right">
						ѧ�ţ�
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="xh" styleId="xh" readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</td>
					<td height="22" align="right">
						ѧ�꣺
					</td>
					<td height="22" align="left">
						${xn }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						������
					</td>
					<td height="22" align="left">
						${rs.xm }
					</td>
					<td height="22" align="right">
						�Ա�
					</td>
					<td height="22" align="left">
						${rs.xb }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�꼶��
					</td>
					<td height="22" align="left">
						${rs.nj }
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td height="22" align="left">
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						רҵ��
					</td>
					<td height="22" align="left">
						${rs.zymc }
					</td>
					<td height="22" align="right">
						�༶��
					</td>
					<td height="22" align="left">
						${rs.bjmc}
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<font color="blue">�ۺϱ�������</font>
				</legend>
				<table width="100%" class="tbstyle">
					<tbody>
						<tr>
							<td>
								<table width="100%" class="tbstyle" id="tab">
									<tbody width="100%" class="tbstyle" id="bxrow">									
										<tr>
										   <td>
												���
											</td>
											<td>
												�������
											</td>
											<td>
												����
											</td>
											<td>
												��������
											</td>
											<td>
												�Ӽ�
											</td>
											<td>
												����
											</td>
										</tr>
									</tbody>
									<logic:iterate id="rs" name="rsVal">
									  <tr>
										    <td>
												<bean:write name="rs" property="r"/>
											</td>
											<td>
											    <bean:write name="rs" property="lbmc"/>	
											    <input type="hidden" name="pllb" value="<bean:write name="rs" property="lb"/>">
											</td>
											<td>
												 <bean:write name="rs" property="mc"/>	
												 <input type="hidden" name="pldm" value="<bean:write name="rs" property="dm"/>">
											</td>
											<td>
												<bean:write name="rs" property="nr" />
											</td>
											<td>
												<html:select name="rs" property="fslb">
													<html:option value=""></html:option>
													<html:option value="+">+</html:option>
													<html:option value="-">-</html:option>
												</html:select>
											</td>
											<td>
												<html:text name="rs" property="fs" style="width:50px" maxlength="3"  onkeypress='return sztzNumInputValue(this,5,event)' onblur='ckinpdata(this)'/>
											</td>
										</tr>
									</logic:iterate>	
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			<br />
			<logic:notEqual value="view" name="act">
			<div class="buttontool" id="button" align="center">
				<button class="button2" onclick="dataSave()"
					style="width:80px" id="buttonSave" >
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			</logic:notEqual>
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
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>

</html>
