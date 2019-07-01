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
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
		function viewMorezpinfo(doType){	  	   
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  var url ="viewzpinfo.do?method=jyzpinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb"; 
		 if (doType == "view"){
		   showTopWin(url, 700, 650);
		  }
		}
		
		function newpage(obj){
		    var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzpinfo.do?method=jyzpinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		function newpage_news(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewnewsinfo.do?method=newsinfo&newsId="+pkValue+"&jytype=jyweb";
		}
		
		function newpage_zczp(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzczpinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_ggl(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewgglinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_syjs(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewsyjsinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_qzxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "qzxxinfo.do?method=qzxxinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		
		function newpage_tpxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewtpxxinfo.do?method=tpxxinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		function userlogin(){
           var usertype =document.getElementById("userty").value;
           var yhm = document.getElementById("yhm").value;
           var mm = document.getElementById("mm").value;
           var yzm = document.getElementById("yzm").value;
           
           if(yhm==""){
           alert("�������û�����");
           return false;
           }
           
           if(mm==""){
           alert("���������룡");
           return false;
           }
           
           if(yzm==""){
           alert("��������֤�룡");
           return false;
           }
           
           if(yzm.length<4){
            alert("��֤���λ������ȷ��");
           return false;
           }
           
		   document.forms[0].action = "index.do?method=jyindex&doType=login&usertype="+usertype+"&jytype=jyweb";
		   document.forms[0].submit();
		}	
			
		function dowhat(){
		   document.forms[0].action = "adminoperation.do?method=adminoperation&jytype=jyweb";
		   document.forms[0].submit();
		}
		
		function wjdc(){
		   document.forms[0].action = "index.do?method=jyindex&doType2=wjdc&jytype=jyweb";
		   document.forms[0].submit();
		}
		
		function wjdcresult(){
		    url = "wjdcresult.do?method=wjdcresult&jytype=jyweb";
		    showTopWin(url, 450, 270);
		    
		}
		function findarticle(){
		    document.forms[0].action = "findarticle.do?method=findarticle&doType=find&jytype=jyweb";
		    document.forms[0].submit();
		}
		
		function admingl(){
		 	document.forms[0].action = "adminoperation.do?method=adminoperation&act=zxdt&jytype=jyweb";
		 	document.forms[0].submit();
        }
        function dwgl(){
            document.forms[0].action = "adminoperation.do?method=adminoperation&act=zpxx&jytype=jyweb";
		 	document.forms[0].submit();
        }
         function stugl(){
            document.forms[0].action = "adminoperation.do?method=adminoperation&act=grjldj&jytype=jyweb";
		 	document.forms[0].submit();
        }
        
	</script>



	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<form action="jyzpinfo.do" method="post">
			<logic:equal name="xxdm" value="10856" scope="session">
				<logic:equal name="yhm" value="" scope="session">
					<jsp:include flush="true" page="head_false.jsp"></jsp:include>
					<input type="hidden" name="lock" value="closed" />
				</logic:equal>
				<logic:notEqual name="yhm" value="" scope="session">
					<jsp:include flush="true" page="head.jsp"></jsp:include>
					<input type="hidden" name="lock" value="open" />
				</logic:notEqual>
			</logic:equal>
			<logic:notEqual name="xxdm" value="10856" scope="session">
				<jsp:include flush="true" page="head.jsp"></jsp:include>
				<input type="hidden" name="lock" value="open" />
			</logic:notEqual>
			<div class="mainframe">
				<div class="leftframe">
					<div class="yhdl">
						<h3>
							�û���¼
						</h3>
						<logic:equal name="who" value="anonymous">
							<dl>
								<dt>
									�û�����
								</dt>
								<dd>
									<input type="text" style="width:80px;" size="10"
										class="inputtext" name="yhm" maxlength="15" />
								</dd>
								<dt>
									��&nbsp;&nbsp;&nbsp;&nbsp;�룺
								</dt>
								<dd>
									<input type="password" style="width:80px;" class="inputtext"
										maxlength="16" name="mm" />
								</dd>
								<dt>
									��֤�룺
								</dt>
								<dd>
									<input name="yzm" type="text" class="inputtext"
										style="width:80px" maxlength="4" />
									<img src="yzm.jsp" border="0" align="absmiddle" />
								</dd>
								<dt>
									<select name="userty"
										onkeypress="if(window.event.keyCode==13) userlogin();">
										<option selected="selected" value="xs">
											ѧ��
										</option>
										<option value="dw">
											��λ
										</option>
										<option value="admin">
											����Ա
										</option>
									</select>
								</dt>
								<dd>
									<button onClick="userlogin();">
										��&nbsp;¼
									</button>
								</dd>
							</dl>
							<span> <a
								href="dwregister.do?method=dwregister&jytype=jyweb">&nbsp;��λע��</a>
								<a href="findweb.do?method=dwyhmmzh&jytype=jyweb">&nbsp;�������룿</a>
							</span>
						</logic:equal>
						<!-- ����Ա��½��Ĳ����� -->

						<logic:equal name="who" value="admin">
							<dl>
								<dt>
									�û����ͣ�
								</dt>
								<dd>
									������Ա��
								</dd>
								<dt>
									�û�����
								</dt>
								<dd>
									<bean:write name="xm" />
								</dd>
								<dt>
									��Ϣ����
								</dt>
								<dd>
									<button onclick="admingl()" style="width:100px">
										�����̨������
									</button>
								</dd>
							</dl>
							<span><a
								href="changepassword.do?method=changepassword&jytype=jyweb"><font
									color="red">�޸�����</font> </a> <a
								href="index.do?method=jyindex&doType=left&jytype=jyweb"><font
									color="red">ע��</font> </a> </span>
						</logic:equal>
						<!-- ѧ����½��Ĳ����� -->
						<logic:equal name="who" value="xs">
							<dl>
								<dt>
									�û����ͣ�
								</dt>
								<dd>
									��ѧ����
								</dd>
								<dt>
									�û�����
								</dt>
								<dd>
									<bean:write name="xm" />
								</dd>
								<dt>
									��Ϣ����
								</dt>
								<dd>
									<button onclick="stugl()" style="width:100px">
										����������桷��
									</button>
								</dd>
							</dl>
							<span><a
								href="viewalldwhfinfo.do?method=alldwhfinfo&jytype=jyweb">����<font
									color="red"><bean:write name="howmanydw" scope="session" />
								</font>�ҵ�λ�ظ���</a> </span>
							<span> <a
								href="changepassword.do?method=changepassword&jytype=jyweb"><font
									color="red">�޸�����</font> </a>| <a
								href="index.do?method=jyindex&doType=left&jytype=jyweb"><font
									color="red">ע��</font> </a> </span>
						</logic:equal>
						<!-- ��λ��½��Ĳ����� -->

						<logic:equal name="who" value="dw">
							<dl>
								<dt>
									�û����ͣ�
								</dt>
								<dd>
									����λ��
								</dd>
								<dt>
									�û�����
								</dt>
								<dd>
									<bean:write name="dwmc" />
								</dd>
								<dt>
									��Ϣ����
								</dt>

								<dd>
									<button onclick="dwgl()" style="width:100px">
										����������桷��
									</button>
								</dd>
							</dl>
							<span><a
								href="dwviewallgrjlinfo.do?method=dwviewallgrjlinfo&jytype=jyweb">����<font
									color="red"><bean:write name="howmany" scope="session" />
								</font>λѧ��Ͷ����</a> </span>
							<span> <a
								href="changepassword.do?method=changepassword&jytype=jyweb"><font
									color="red">�޸�����</font> </a> <a
								href="index.do?method=jyindex&doType=left&jytype=jyweb"><font
									color="red">ע��</font> </a> </span>
						</logic:equal>
						���ǵ�&nbsp;
						<font color="red" size="8"><b><bean:write name="lll" />
						</b> </font>&nbsp;λ������
					</div>

					<div class="wzss">
						<h3>
							��������
						</h3>
						<table width="95%" align="center">
							<tr>
								<td>
									�ؼ��֣�
								</td>
								<td>
									<input type="text" name="gjz" style="width:100%;"
										class="inputtext"
										onkeypress="if(window.event.keyCode==13){ findarticle(); } " />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<html:select name="find" property="find">
										<html:option value="zxdt">���¶�̬</html:option>
										<html:option value="tpxx">ͼƬ��Ϣ</html:option>
										<html:option value="zcfg">���߷���</html:option>
										<html:option value="ggl">������</html:option>
										<html:option value="zph">��Ƹ��</html:option>
										<html:option value="syjs">��Դ����</html:option>
										<html:option value="xzzx">��������</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<button onClick="findarticle();" class="btn_search"
										id="searchbutton">
									</button>
								</td>
							</tr>
						</table>
					</div>
					<div class="tpxw">
						<h3>
							ͼƬ����
						</h3>
						<ul>
							<br/>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<logic:iterate name="tpxx" id="s4">
										<li id="tpref">
											<a title="<bean:write name="s4" property="bttitle" />"
												href="#"><img
													src="<bean:write name="s4" property="picpath1" />"
													style="width:95px;height:65px" /> <span><bean:write
														name="s4" property="bt" /> </span> </a>
										</li>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<logic:iterate name="tpxx" id="s4">
										<li id="tpref">
											<a title="<bean:write name="s4" property="bttitle" />"
												href="viewtpxxinfo.do?method=tpxxinfo&doType=view&pkValue=<bean:write name="s4" property="rowid" />"
												target="_blank"><img
													src="<bean:write name="s4" property="picpath1" />"
													style="width:95px;height:65px" /> <span><bean:write
														name="s4" property="bt" /> </span> </a>
										</li>
									</logic:iterate>
								</logic:notEqual>
							</logic:equal>

							<logic:notEqual name="xxdm" value="10856" scope="session">
								<logic:iterate name="tpxx" id="s4">
									<li id="tpref">
										<a title="<bean:write name="s4" property="bttitle" />"
											href="viewtpxxinfo.do?method=tpxxinfo&doType=view&pkValue=<bean:write name="s4" property="rowid" />"
											target="_blank"><img
												src="<bean:write name="s4" property="picpath1" />"
												style="width:95px;height:65px" /> <span><bean:write
													name="s4" property="bt" /> </span> </a>
									</li>
								</logic:iterate>
							</logic:notEqual>
						</ul>
						<h6>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<a href="#"></a>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<a href="viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb"
										target="_blank"></a>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856" scope="session">
								<a href="viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb"
									target="_blank"></a>
							</logic:notEqual>
						</h6>
					</div>
					<div class="yqlj">
						<h3>
							��������
						</h3>
						<ul>
							<logic:iterate name="yqlj" id="s8">
								<li>
									<a href="<bean:write name="s8" property="wz" />"
										target="_blank"><bean:write name="s8" property="mc" /> </a>
								</li>
							</logic:iterate>
						</ul>
						<h6>
							<a href="#" onclick="showTopWin('yqljlist.do?method=yqljList',300,500)"></a>
						</h6>
					</div>
				</div>
				<div class="midframe">
					<div class="piclinksearch">
						<ul>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<li>
										<a href="#" class="rc"></a>
									</li>
									<li>
										<a href="#" class="qz"></a>
									</li>
									<li>
										<a href="#" class="xz"></a>
									</li>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<li>
										<a href="viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb"
											class="rc"></a>
									</li>
									<li>
										<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb"
											class="qz"></a>
									</li>
									<li>
										<a href="viewallwjxzinfo.do?method=allwjxzsinfo&jytype=jyweb"
											class="xz"></a>
									</li>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856" scope="session">
								<li>
									<a href="viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb"
										class="rc"></a>
								</li>
								<li>
									<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb"
										class="qz"></a>
								</li>
								<li>
									<a href="viewallwjxzinfo.do?method=allwjxzsinfo&jytype=jyweb"
										class="xz"></a>
								</li>

							</logic:notEqual>

						</ul>
					</div>
					<div class="zxdt">
						<h3>
							���¶�̬
						</h3>
						<ul>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<logic:iterate name="zxdt" id="s1">
										<li>
											<a title="<bean:write name="s1" property="allnewstitle" />"
												href="#"><bean:write name="s1" property="newstitle" />
												<logic:equal name="s1" property="newmark" value="new">
													<img style="width:30px" src="images/newmark.gif" />
												</logic:equal> </a>
										</li>

										<h5>
											<font color="#444444">[<bean:write name="s1"
													property="pubtime" />]</font>
										</h5>

									</logic:iterate>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<logic:iterate name="zxdt" id="s1">
										<li>
											<a title="<bean:write name="s1" property="allnewstitle" />"
												target="_blank"
												href="viewnewsinfo.do?method=newsinfo&jytype=jyweb&newsid=<bean:write name="s1" property="newsid" />&rownum=<bean:write name="s1" property="r" />"><bean:write
													name="s1" property="newstitle" /> <logic:equal name="s1"
													property="newmark" value="new">
													<img style="width:30px" src="images/newmark.gif" />
												</logic:equal> </a>
										</li>

										<h5>
											<font color="#444444">[<bean:write name="s1"
													property="pubtime" />]</font>
										</h5>

									</logic:iterate>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856" scope="session">
								<logic:iterate name="zxdt" id="s1">
									<li>
										<a title="<bean:write name="s1" property="allnewstitle" />"
											target="_blank"
											href="viewnewsinfo.do?method=newsinfo&jytype=jyweb&newsid=<bean:write name="s1" property="newsid" />&rownum=<bean:write name="s1" property="r" />"><bean:write
												name="s1" property="newstitle" /> <logic:equal name="s1"
												property="newmark" value="new">
												<img style="width:30px" src="images/newmark.gif" />
											</logic:equal> </a>
									</li>

									<h5>
										<font color="#444444">[<bean:write name="s1"
												property="pubtime" />]</font>
									</h5>

								</logic:iterate>
							</logic:notEqual>
						</ul>
						<h6>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<a href="#"></a>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<a href="viewallnewsinfo.do?method=allnewsinfo&jytype=jyweb"
										target="_blank"></a>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856" scope="session">
								<a href="viewallnewsinfo.do?method=allnewsinfo&jytype=jyweb"
									target="_blank"></a>
							</logic:notEqual>
						</h6>
					</div>
					<div class="zpxx">
						<h3>
							<ul>
								<li class=current id="li_1" onmouseover="chgCode(this,1)">
									<logic:equal name="xxdm" value="10856" scope="session">
										<logic:equal name="yhm" value="" scope="session">
											<a href="#" id="zpxx"><span>��Ƹ��Ϣ</span> </a>
										</logic:equal>
										<logic:notEqual name="yhm" value="" scope="session">
											<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb" id="zpxx" 
												target="_blank"><span>��Ƹ��Ϣ</span> </a>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10856" scope="session">
										<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb" id="zpxx"
											target="_blank"><span>��Ƹ��Ϣ</span> </a>
									</logic:notEqual>
								</li>
								<li id="li_2" onmouseover="chgCode(this,2);">
									<logic:equal name="xxdm" value="10856" scope="session">
										<logic:equal name="yhm" value="" scope="session">
											<a href="#"> <span>��ְ��Ϣ</span> </a>
										</logic:equal>
										<logic:notEqual name="yhm" value="" scope="session">
											<a href="viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb"
												target="_blank"><span>��ְ��Ϣ</span> </a>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10856" scope="session">
										<a href="viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb"
											target="_blank"><span>��ְ��Ϣ</span> </a>
									</logic:notEqual>
								</li>
							</ul>
							<h6>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<a href="#" id="path"></a>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb" id="path"
										target="_blank"></a>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856" scope="session">
								<a href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb" id="path"
									target="_blank"></a>
							</logic:notEqual>
							</h6>
						</h3>
						<h4>
							<ul id="list_1" class="zpxxlist">
								<logic:equal name="xxdm" value="10856" scope="session">
									<logic:equal name="yhm" value="" scope="session">
										<logic:iterate name="rs" id="s">
											<li>
												<a
													title="<bean:write name="s" property="allgsmc" />    ����˴Σ�<bean:write name="s" property="readtimes" />"
													href="#"><bean:write name="s" property="gsmc" /> </a>
												<h4>
													<font color="red">Ƹ��</font>
													<bean:write name="s" property="zpzw" />
												</h4>
												<h5>
													<font color="#444444">[<bean:write name="s"
															property="fbsj" />] </font>
												</h5>
											</li>
										</logic:iterate>
									</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<logic:iterate name="rs" id="s">
										<li>
											<a
												title="<bean:write name="s" property="allgsmc" />    ����˴Σ�<bean:write name="s" property="readtimes" />"
												href="viewzpinfo.do?method=jyzpinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="s" property="rid"/>"
												target="_blank"><bean:write name="s" property="gsmc" />
											</a>
											<h4>
												<font color="red">Ƹ��</font>
												<bean:write name="s" property="zpzw" />
											</h4>
											<h5>
												<font color="#444444">[<bean:write name="s"
														property="fbsj" /> ]</font>
											</h5>
										</li>
									</logic:iterate>
								</logic:notEqual>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10856" scope="session">
									<logic:iterate name="rs" id="s">
										<li>
											<a
												title="<bean:write name="s" property="allgsmc" />    ����˴Σ�<bean:write name="s" property="readtimes" />"
												href="viewzpinfo.do?method=jyzpinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="s" property="rid"/>" target="_blank">
												<bean:write name="s" property="gsmc" /> </a>
											<h4>
												<font color="red">Ƹ��</font>
												<bean:write name="s" property="zpzw" />
											</h4>
											<h5>
												<font color="#444444">[<bean:write name="s"
														property="fbsj" />] </font>
											</h5>
										</li>
									</logic:iterate>
								</logic:notEqual>
							</ul>
							<ul class="qzxxlist" id="list_2" style="display:none">
								<logic:equal name="xxdm" value="10856" scope="session">
									<logic:equal name="yhm" value="" scope="session">
										<logic:iterate name="qzxx" id="s6">
											<li>
												<a
													title="<bean:message key="lable.xsgzyxpzxy" />��<bean:write name="s6" property="xymc" />  רҵ��<bean:write name="s6" property="zymc" />"
													href="#"> <bean:message key="lable.xsgzyxpzxy" />��<bean:write name="s6" property="xymc" />
													רҵ��<bean:write name="s6" property="zymc" /> </a>
												<h4>
													<font color="red">��ְ��</font>
													<b><bean:write name="s6" property="qzyx" /> </b>
												</h4>
												<h5>
													<span><font color="#444444">[<bean:write
																name="s6" property="fbsj" />]</font> </span>
												</h5>
											</li>
										</logic:iterate>
									</logic:equal>
									<logic:notEqual name="yhm" value="" scope="session">
										<logic:iterate name="qzxx" id="s6">
											<li>
												<a
													title="<bean:message key="lable.xsgzyxpzxy" />��<bean:write name="s6" property="xymc" />  רҵ��<bean:write name="s6" property="zymc" />"
													target="_blank"
													href="qzxxinfo.do?method=qzxxinfo&doType=view&jytype=jyweb&pkValue=<bean:write name="s6" property="xh" />">
													<bean:message key="lable.xsgzyxpzxy" />��<bean:write name="s6" property="xymc" /> רҵ��<bean:write
														name="s6" property="zymc" /> </a>
												<h4>
													<font color="red">��ְ��</font>
													<b><bean:write name="s6" property="qzyx" /> </b>
												</h4>
												<h5>
													<span><font color="#444444">[<bean:write
																name="s6" property="fbsj" /> ]</font> </span>
												</h5>
											</li>
										</logic:iterate>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10856" scope="session">
									<logic:iterate name="qzxx" id="s6">
										<li>
											<a
												title="<bean:message key="lable.xsgzyxpzxy" />��<bean:write name="s6" property="xymc" />  רҵ��<bean:write name="s6" property="zymc" />"
												target="_blank"
												href="qzxxinfo.do?method=qzxxinfo&doType=view&jytype=jyweb&pkValue=<bean:write name="s6" property="xh" />">
												<bean:message key="lable.xsgzyxpzxy" />��<bean:write name="s6" property="xymc" /> רҵ��<bean:write
													name="s6" property="zymc" /> </a>
											<h4>
												<font color="red">��ְ��</font>
												<b><bean:write name="s6" property="qzyx" /> </b>
											</h4>
											<h5>
												<span><font color="#444444">[<bean:write
															name="s6" property="fbsj" /> ]</font> </span>
											</h5>
										</li>
									</logic:iterate>
								</logic:notEqual>
							</ul>
						</h4>
						<input type="hidden" id="preNode" name="preNode" value="1" />
						<script type='text/javascript'>
                            window.onload=new function() {};
                            function chgCode(obj,tid){
                                document.getElementById("li_"+document.getElementById("preNode").value).className="";obj.className="current"; 
                                document.getElementById("list_"+document.getElementById("preNode").value).style.display='none';
                                document.getElementById("list_"+tid).style.display='';
                                document.getElementById("preNode").value = tid;
                                var zpxxpath = document.getElementById("zpxx").href;
                                if(zpxxpath.indexOf('#')>0){
                                	document.getElementById('path').href='#';
                                }else{
                                	if(tid==1){
                                		document.getElementById('path').href='viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb';
                                	}else{
                                		document.getElementById('path').href='viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb';
                                	}
                                }
                            }
                         </script>
					</div>

					<div class="xgzx">
						<h3>
							�����Ѷ
						</h3>
						<ul>
							<logic:equal name="xxdm" value="10856" scope="session">
								<logic:equal name="yhm" value="" scope="session">
									<logic:iterate name="xgzx" id="s7">
										<li>
											<a title="<bean:write name="s7" property="wjbttitle" />"
												href="#"> [&nbsp;<font color="red">
												<bean:write name="s7" property="wjlx" /> </font>&nbsp;]&nbsp;
														<bean:write name="s7" property="wjbt" /> 
												<logic:equal name="s7" property="newmark" value="new">
													<img style="width:30px" src="images/newmark.gif" />
												</logic:equal> 
											</a>
											<h5>
												<font color="#444444">[<bean:write name="s7"
														property="fbsj" />] </font>
											</h5>
										</li>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual name="yhm" value="" scope="session">
									<logic:iterate name="xgzx" id="s7">
										<li>
											<a title="<bean:write name="s7" property="wjbttitle" />"
												href="qitainfo.do?method=qitainfo&rowid=<bean:write name="s7" property="rowid" />"
												target="_blank"> [&nbsp;<font color="red"><bean:write
														name="s7" property="wjlx" /> </font>&nbsp;]&nbsp;<bean:write
													name="s7" property="wjbt" /> <logic:equal name="s7"
													property="newmark" value="new">
													<img style="width:30px" src="images/newmark.gif" />
												</logic:equal> </a>
											<h5>
												<font color="#444444">[<bean:write name="s7"
														property="fbsj" />] </font>
											</h5>
										</li>
									</logic:iterate>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10856" scope="session">
								<logic:iterate name="xgzx" id="s7">
									<li>
										<a title="<bean:write name="s7" property="wjbttitle" />"
											href="qitainfo.do?method=qitainfo&rowid=<bean:write name="s7" property="rowid" />"
											target="_blank"> [&nbsp;<font color="red"><bean:write
													name="s7" property="wjlx" /> </font>&nbsp;]&nbsp;<bean:write
												name="s7" property="wjbt" /> 
											<logic:equal name="s7" property="newmark" value="new">
												<img style="width:30px" src="images/newmark.gif" />
											</logic:equal>
									  </a>
										<h5>
											<font color="#444444">
											[<bean:write name="s7" property="fbsj" /> ]</font>
										</h5>
									</li>
								</logic:iterate>
							</logic:notEqual>

						</ul>
					</div>

					<%--<div class="tpxx" style="display:none;">
						<h3>
							
						</h3>
						<ul>
							<logic:iterate name="tpxx" id="s7">
								<li onmouseover="rowOnClick2(this)">
									<a href="" onclick="newpage_tpxx(this)" target="_blank"> <logic:iterate
											id="v7" name="s7" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v7' />" />
										</logic:iterate> <logic:iterate id="v7" name="s7" offset="2" length="1">
											<img src="<bean:write name="v7" />" />
										</logic:iterate> <logic:iterate id="v7" name="s7" offset="1" length="1">
											<span><bean:write name='v7' /> </span>
										</logic:iterate> </a>
								</li>
							</logic:iterate>
						</ul>
						<h6>
							<a href="viewalltpxxinfo.do?method=alltpxxinfo&jytype=jyweb"
								target="_blank"></a>
						</h6>
					</div>

				--%>
				</div>
				<div class="rightframe">
					<div class="ggl">
						<h3></h3>
						<div id=demo style="overflow:hidden; height:200px"
							onmouseover=ii=1 onmouseout=ii=0>
							<div id=demo1>
								<table border="0" height="200px" width="90%">
									<logic:equal name="xxdm" value="10856" scope="session">
										<logic:equal name="yhm" value="" scope="session">
											<logic:iterate name="ggl" id="s3">
												<tr onmouseover="rowOnClick2(this)">
													<td>
														<font color="black">��</font><a
															title="<bean:write name="s3" property="wjbttitle" />"
															href="#"> <bean:write name="s3" property="wjbt" /> <logic:equal
																name="s3" property="newmark" value="new">
																<img style="width:30px" src="images/newmark.gif" />
															</logic:equal> </a>
													</td>
												</tr>
											</logic:iterate>
										</logic:equal>
										<logic:notEqual name="yhm" value="" scope="session">
											<logic:iterate name="ggl" id="s3">
												<tr onmouseover="rowOnClick2(this)">
													<td>
														<font color="black">��</font><a
															title="<bean:write name="s3" property="wjbttitle" />"
															target="_blank"
															href="viewgglinfo.do?method=qitainfo&jytype=jyweb&rowid=<bean:write name="s3" property="rowid" />">
															<bean:write name="s3" property="wjbt" /> <logic:equal
																name="s3" property="newmark" value="new">
																<img style="width:30px" src="images/newmark.gif" />
															</logic:equal> </a>
													</td>
												</tr>
											</logic:iterate>
										</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10856" scope="session">
										<logic:iterate name="ggl" id="s3">
											<tr onmouseover="rowOnClick2(this)">
												<td>
													<font color="black">��</font><a
														title="<bean:write name="s3" property="wjbttitle" />"
														target="_blank"
														href="viewgglinfo.do?method=qitainfo&jytype=jyweb&rowid=<bean:write name="s3" property="rowid" />">
														<bean:write name="s3" property="wjbt" /> <logic:equal
															name="s3" property="newmark" value="new">
															<img style="width:30px" src="images/newmark.gif" />
														</logic:equal> </a>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEqual>
								</table>
							</div>
							<div id=demo2></div>
						</div>
					</div>
					<div class="zczp" style="display:none;">
						<h3>
							ר����Ƹ
						</h3>
						<table border="0" width="100%">
							<logic:iterate name="zczp" id="s2">
								<tr onmouseover="rowOnClick2(this)">
									<logic:iterate id="v2" name="s2" offset="0" length="1">
										<input type="hidden" value="<bean:write name='v2' />" />
									</logic:iterate>
									<logic:iterate id="v2" name="s2" offset="1" length="1">
										<td>
											<font color="black">��</font><a target="_blank"
												onclick="newpage_zczp(this)" href=""> <bean:write
													name="v2" /> </a>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<h6>
							<a href="viewallzczpinfo.do?method=allzczpinfo&jytype=jyweb"
								target="_blank">More.. </a>
						</h6>
					</div>

					<div class="wjdc">
						<h3>
							�ʾ����
						</h3>
						<logic:notEmpty name="wjdcbt">
							<h4>
								<bean:write name="wjdcbt" />
							</h4>
							<ul>
								<logic:iterate name="wjdc" id="w">
									<li>
										<logic:iterate id="w2" name="w">
											<input type="radio" name="choice"
												value="<bean:write name='w2' />" />
											<bean:write name='w2' />
										</logic:iterate>
									</li>
								</logic:iterate>
							</ul>
							<br>
							<h6>
								<button onclick="wjdc();" id="tpbutton">
									ͶƱ
								</button>
								<button onclick="wjdcresult();" id="ckbutton">
									�鿴���
								</button>
							</h6>
						</logic:notEmpty>
					</div>
					<div class="yqlogo">
						<h3>
							��������
						</h3>
						<ul>
							<li>
								<a href="#" target="_blank"><img
										src="jyweb/images/listlogo_1.gif" /> </a>
							</li>
							<li>
								<a href="http://www.zjrc.com" target="_blank"><img
										src="jyweb/images/listlogo_2.gif" /> </a>
							</li>
							<li>
								<a href="http://www.chinahr.com" target="_blank"><img
										src="jyweb/images/listlogo_3.gif" /> </a>
							</li>
							<li>
								<a href="http://www.hhrc.com.cn" target="_blank"><img
										src="jyweb/images/listlogo_4.gif" /> </a>
							</li>
							<li>
								<a href="http://www.51job.com" target="_blank"><img
										src="jyweb/images/listlogo_5.gif" /> </a>
							</li>
						</ul>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</form>

		<logic:notEmpty name="login">
			<logic:equal name="login" value="no">
				<script>
                      alert("�û������������");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="yz">
			<logic:equal name="yz" value="no">
				<script>
                      alert("��֤�����");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="wjdcok">
			<logic:equal name="wjdcok" value="wjdcok">
				<script>
                      alert("�ʾ��ύ�ɹ���");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="wjdcno">
			<logic:equal name="wjdcno" value="wjdcno">
				<script>
                      alert("�ʾ��ύʧ�ܣ�");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="wjdcnull">
			<logic:equal name="wjdcnull" value="wjdcnull">
				<script>
                      alert("�ʾ��ύʧ�ܣ�ѡ���Ϊ��!");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:equal name="wjdccf" value="wjdccf">
				<script>
                      alert("�ʾ��ύʧ�ܣ����Ѿ��ύ����!");
                </script>
		</logic:equal>

	</body>
	<SCRIPT type="text/javascript">
      var ii=0;
      var demo = document.all("demo");
      var demo1 = document.all("demo1");
      var demo2 = document.all("demo2");
      t=demo.scrollTop;
      demo2.innerHTML=demo1.innerHTML;
      function qswhMarquee(){
      if (ii==1)return;
      if(demo2.offsetTop-demo.scrollTop<=0)
        demo.scrollTop-=demo1.offsetHeight
      else{
      demo.scrollTop++;
 
 //     if (demo.scrollTop % (Math.floor(demo1.offsetHeight / 5 )) ==0){
 //      ii=1;
 //    setTimeout("ii=0",600);
 //      }
 //    ������ͣ��600���� 
      }  
     }
     setInterval(qswhMarquee,60);//�ٶ�60  �����1
    </SCRIPT>

	<SCRIPT>
	       var buttonArray = document.getElementsByTagName("BUTTON");
           var hrefArray   = document.getElementsByTagName("a");
           var searchbutton = document.getElementById("searchbutton"); //������ť
           var tpbutton = document.getElementById("tpbutton"); //ͶƱ��ť
           var ckbutton = document.getElementById("ckbutton"); //�鿴�����ť
       
           var lock = $("lock").value;
           
           if(lock=="closed"){
            for(var index=0;index <hrefArray.length;index++){
        	    hrefArray[index].disabled=false;
                }
                searchbutton.disabled=true;
                tpbutton.disabled=true;
                ckbutton.disabled=true;
           }
           
       function lock(){
           var buttonArray = document.getElementsByTagName("BUTTON");
           var hrefArray   = document.getElementsByTagName("a");
       
           //alert(hrefArray.length);

           var lock = $("lock").value;
           if(lock=="closed"){

                for(var index=0;index <hrefArray.length;index++){
        	       hrefArray[index].disabled=false;
                }
           }
        }
	</script>
</html>
