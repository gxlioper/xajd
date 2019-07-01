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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
				
		
		function viewMorezpinfo(doType){	  	   
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  var url ="viewzpinfo.do?method=jyzpinfo&doType=view&jytype=jyweb&pkValue="+pkValue; 
		 if (doType == "view"){
		   showTopWin(url, 700, 650);
		  }
		}
		
		function newpage(obj){
		    var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzpinfo.do?method=jyzpinfo&doType=view&jytype=jyweb&pkValue="+pkValue;
		}
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "viewallzpinfo.do?method=alljyzpinfo&doType=del&jytype=jyweb&pkValue="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb&act=query";
            document.forms[0].submit();
		}
		function query()
		{
			document.forms[0].action = "viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb&act=query";
            document.forms[0].submit();
		}
		function qingkong()
		{
			document.forms[0].action = "viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb&doType3=qingkong";
            document.forms[0].submit();
		}
		
		function   document.onclick(){   
           showSel(false)   
        }   
        function   showSel(flag){   
        document.all.hyfl.style.display=(flag?"block":"none")   
        window.event.cancelBubble=true;   
       }   
        function   selText(obj){   
        obj.value=(document.all.hyfl.options[document.all.hyfl.selectedIndex].text)   
       //showSel(false)   
       }   
		</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/viewallzpinfo" method="post">
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<div class="default_box">
							<h3>
								<em>����ְλ</em>
							</h3>
							<table width="98%" align="center">

								<tr>
									<td width="70">
										��λ���ƣ�
									</td>
									<td>
										<html:text styleClass="text_normal" name="rs1" property="dwmc"
											style="width:90px" />
									</td>
								</tr>
								<tr>
									<td>
										��Ƹְλ��
									</td>
									<td>
										<html:text styleClass="text_normal" name="rs1" property="zpzw"
											style="width:90px" />
									</td>
								</tr>
								<tr>
									<td>
										�����ص㣺
									</td>
									<td>
										<html:text styleClass="text_normal" name="rs1" property="gzdd"
											style="width:90px" />
									</td>
								</tr>
								<tr>
									<td>
										��ҵ���ࣺ
									</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>

													<html:text styleClass="text_normal" name="rs1"
														property="iData" style="width:68px" readonly="true" />
													<%--<input name=iData size="13"
														style="margin:0;border-right-width:0px;height:20;" />
													--%>
													<input type=button onclick=showSel(true) value="6"
														style="margin:0;cursor:hand;font-family:Webdings;width:18px;height:18px;vertical-align:baseline;padding:1px;line-height:5px" />
												</td>
											</tr>
											<tr>
												<td>
													<html:select name="rs1" property="hyfl" size="10"
														style="position:absolute;display:none"
														onchange="selText(iData)">
														<html:option value=""></html:option>
														<html:options collection="hyflList" property="hyfl"
															labelProperty="hyfl" />
													</html:select>
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<tr>
									<td>
										����Ҫ��
									</td>
									<td>
										<html:text styleClass="text_normal" name="rs1" property="wyyq"
											style="width:90px" />
									</td>
								</tr>
								<tr>
									<td>
										ѧ��Ҫ��
									</td>
									<td>
										<html:select name="rs1" property="xlyq" style="width:90px">
											<html:option value=""></html:option>
													<html:option value="ר��">ר��</html:option>
													<html:option value="ר������">ר������</html:option>
													<html:option value="����">����</html:option>
													<html:option value="��������">��������</html:option>
													<html:option value="˶ʿ">˶ʿ</html:option>
													<html:option value="˶ʿ����">˶ʿ����</html:option>
													<html:option value="��ʿ">��ʿ</html:option>
													<html:option value="��ʿ����">��ʿ����</html:option>

										</html:select>

									</td>
								</tr>
								<tr>
									<td>
										�Ա�Ҫ��
									</td>
									<td>
										<html:select name="rs1" property="xb" style="width:90px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="Ů">Ů</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										нˮҪ��
									</td>
									<td>
										<html:select name="rs1" property="zzxs" style="width:90px">
											<html:option value=""></html:option>
											<html:option value="����">����</html:option>
											<html:option value="1000����">1000����</html:option>
											<html:option value="1000-1500">1000-1500</html:option>
											<html:option value="1500-2500">1500-2500</html:option>
											<html:option value="2500-3500">2500-3500</html:option>
											<html:option value="3500-5000">3500-5000</html:option>
											<html:option value="5000-7000">5000-7000</html:option>
											<html:option value="7000-10000">7000-10000</html:option>
											<html:option value="10000����">10000����</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										����ʱ�䣺
									</td>
									<td>
										<html:select name="rs1" property="xjsj" style="width:90px">
											<html:option value=""></html:option>
											<html:option value="-1">����</html:option>
											<html:option value="-2">������</html:option>
											<html:option value="-7">һ����</html:option>
											<html:option value="-15">������</html:option>
											<html:option value="-30">һ����</html:option>
											<html:option value="-90">������</html:option>
											<html:option value="-180">������</html:option>
											<html:option value="-365">һ����</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										��Ϣ��Դ��
									</td>
									<td>
										<html:select name="rs1" property="xxly" style="width:90px">
											<html:option value=""></html:option>
											<html:option value="ѧУ">ѧУ����</html:option>
											<html:option value="��λ">��λ����</html:option>
										</html:select>
									</td>
								</tr>
								<tr align="center">
									<td colspan="2">
										<button onClick="query();">
											�� ��
										</button>
										&nbsp;&nbsp;
										<button onClick="qingkong();">
											�� ��
										</button>
									</td>
								</tr>
							</table>
						</div>

					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
							<h1>
								<h3>
									��ǰλ�ã�
									<a href="index.do">��ҳ</a>ѡ�� ��Ƹ��Ϣ ��
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>����ؼ�¼
								</h3>
							</h1>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<thead>
								<tr class="btys">
									<td width="150" align="center">
										
											��λ���� 
									</td>
									<td align="center">
											��Ƹ��λ 
									</td>
									<td align="center">
											 ��Դ
									</td>
									<td width="100" align="center">
											�������� 
									</td>
									<td align="center">
											����� 
									</td>
									<logic:equal name="usertype" value="admin" scope="session">
										<td width="100" align="center">
												����
										</td>
									</logic:equal>

								</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td>
												<a class="tubiao" target="_blank" onclick="newpage(this)"
													href="#" title="<bean:write name="v" />"> <bean:write name="v" /> </a>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td>
												<font color="red">Ƹ:</font>
												<a target="_blank" onclick="newpage(this)" href="#" title="<bean:write name="v" />"><bean:write
														name="v" /> </a>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<td align="center">
												<font color="#444444"><bean:write name="v" /></font>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="5" length="1">
											<td align="center">
												<font color="#444444"><bean:write name="v" /></font>
											</td>
										</logic:iterate>
										<td align="center">
											<logic:equal name="usertype" value="admin" scope="session">
												<a onclick="delone(this)" href="">ɾ��
												</a>
											</logic:equal>
										</td>
									</tr>
								</logic:iterate>
								<logic:present name="rs">
									<script language="javascript">
										changeView('tb1',0,12,'yes','yes');
										changeView('tb1',1,12,'yes','yes');
									</script>
								</logic:present>
							</table>
							<table width="98%" align="center" class="tbborder">
								<tr>
									<td>
										<div class="pagination">
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
										</div>
									</td>
								</tr>
							</table>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("��¼ɾ���ɹ���");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("��¼ɾ��ʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" />
		</html:form>
	</body>
</html>
