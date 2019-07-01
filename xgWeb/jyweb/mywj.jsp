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
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
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
		  var pkValue = curr_row.getElementsByTagName("input").value;
		  
		  obj.href = "mywj.do?method=mywj&doType=del&jytype=jyweb&pkValue="+pkValue+"&titName="+titName;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "mywj.do?method=mywj&jytype=jyweb";
            document.forms[0].submit();
		}


        function loadchange(){
        
	       var tab = document.getElementById("titName").value;
	       document.getElementById(tab+"l").className = "xxk_on_l";
	       document.getElementById(tab+"m").className = "xxk_on_m";
	       document.getElementById(tab+"r").className = "xxk_on_r";
        }

        function changePage(defaultId){//切换页面          
                          
	       var title = defaultId.id.substr(0,defaultId.id.length-1);        
	       var titleName,anotherName,anotherName2;                         
	    if (title == "save"){                                             
		 titleName = "save";                                               
		 document.getElementById("titName").value = "save";				        
		 document.getElementById(titleName+"l").className = "xxk_on_l";   
		 document.getElementById(titleName+"m").className = "xxk_on_m";   
		 document.getElementById(titleName+"r").className = "xxk_on_r";   
		 anotherName = "see";                                             
		 document.getElementById(anotherName+"l").className = "xxk_off_l";
		 document.getElementById(anotherName+"m").className = "xxk_off_m";
		 document.getElementById(anotherName+"r").className = "xxk_off_r";
		 anotherName2 = "toudi";                                            
		 document.getElementById(anotherName+"l").className = "xxk_off_l";
		 document.getElementById(anotherName+"m").className = "xxk_off_m";
		 document.getElementById(anotherName+"r").className = "xxk_off_r";
	    } else if (title == "see") {                                     
		 titleName = "see";                                               
		 document.getElementById("titName").value = "see";		            
		 document.getElementById(titleName+"l").className = "xxk_on_l";   
		 document.getElementById(titleName+"m").className = "xxk_on_m";   
		 document.getElementById(titleName+"r").className = "xxk_on_r";   
		 anotherName = "save";                                             
		 document.getElementById(anotherName+"l").className = "xxk_off_l";
		 document.getElementById(anotherName+"m").className = "xxk_off_m";
		 document.getElementById(anotherName+"r").className = "xxk_off_r";
		 anotherName2 = "toudi";                                            
		 document.getElementById(anotherName+"l").className = "xxk_off_l";
		 document.getElementById(anotherName+"m").className = "xxk_off_m";
		 document.getElementById(anotherName+"r").className = "xxk_off_r";
	    } else if (title == "toudi") {                                     
		 titleName = "toudi";                                               
		 document.getElementById("titName").value = "toudi";		            
		 document.getElementById(titleName+"l").className = "xxk_on_l";   
		 document.getElementById(titleName+"m").className = "xxk_on_m";   
		 document.getElementById(titleName+"r").className = "xxk_on_r";   
		 anotherName = "save";                                             
		 document.getElementById(anotherName+"l").className = "xxk_off_l";
		 document.getElementById(anotherName+"m").className = "xxk_off_m";
		 document.getElementById(anotherName+"r").className = "xxk_off_r";
		 anotherName2 = "see";                                            
		 document.getElementById(anotherName+"l").className = "xxk_off_l";
		 document.getElementById(anotherName+"m").className = "xxk_off_m";
		 document.getElementById(anotherName+"r").className = "xxk_off_r";
	    }	  	      
	     document.forms[0].action = "mywj.do?method=mywj&jytype=jyweb";                                                    
		 document.forms[0].submit();                                      
      }                                                                  
       

		</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body onload="loadchange();buttonstylechange();">
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/mywj" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />" />
			<input type="hidden" id="url" name="url" value="/mywj.do?method=mywj" />
			<input type="hidden" name="webtype" value="mywj" />	
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="stucontrl.jsp"></jsp:include>

						<div class="rdxw">
							<h1></h1>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
								<h3>
									当前位置：<a href="index.do">首页</a>选择 我的文件夹
								</h3>
							<div class="xxk">
								<logic:notEmpty name="pages">
									<logic:iterate id="card" name="pages" scope="request">
										<ul>
											<li id="<bean:write name='card' property='en'/>l"
												class="xxk_off_l"></li>
											<li id="<bean:write name='card' property='en'/>m"
												onclick="changePage(this)" class="xxk_off_m">
												&nbsp;
												<bean:write name='card' property='cn' />
												&nbsp;
											</li>
											<li id="<bean:write name='card' property='en'/>r"
												class="xxk_off_r"></li>
										</ul>
									</logic:iterate>
								</logic:notEmpty>
							</div>
							共
							<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
							</b>&nbsp;</FONT>条相关记录
							<table width="98%" align="center" class="tbborder">
								<tr align="center" class="btys" height="25">
									<td>
										单位名称
									</td>
									<td>
										招聘职位
									</td>
									<td>
										发布时间
									</td>
									<td>
										操作
									</td>
								</tr>
								<logic:iterate name="rs" id="v">
									<tr height="25" onmouseover="rowOnClick2(this)" align="center">
									
										<td>
									<input type="hidden" value="<bean:write name='v' property="rid"/>" />
											<a
												href="viewzpinfo.do?method=jyzpinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="v" property="pkvalue"/>"
												target="_blank"><bean:write name="v" property="gsmc" />
											</a>
										</td>
										<td>
											<bean:write name="v" property="zpzw" />
										</td>
										<td>
											<bean:write name="v" property="fbsj" />
										</td>
										<td>
											<a href="mywj.do?method=mywj&doType=del&jytype=jyweb&pkValue=<bean:write name="v" property="rid" />&titName=<bean:write name="titName" scope="request" />"  >删除</a>
										</td>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="98%" align="center" class="tbborder">
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
							</TABLE>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("记录删除成功！");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("记录删除失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" ></button>
		</html:form>
	</body>
</html>
