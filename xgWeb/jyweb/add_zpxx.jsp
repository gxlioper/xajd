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
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/jygl.js"></script>
		<script type="text/javascript">
		function addzpxx(){
		   var email = document.getElementById("email").value;   
           var zpzw = document.getElementById("zpzw").value;   
           var gsmc = document.getElementById("gsmc").value;  
           var day = document.getElementById("day").value;
           var hour = document.getElementById("hour").value;
           var min = document.getElementById("min").value; 
     
          if(zpzw==""){
           alert("招聘职位不能为空！");
           return false;
          }
          if(gsmc==""){
           alert("公司名称不能为空！");
           return false;
          }      
          if((email != null) && (email != "") && (!isEmail(email))){
           alert("电子邮箱不合法！");
           return false;
          }
          if((day==""&&(hour!=""||min!=""))||(day!=""&&hour==""&&min!="")||(day!=""&&hour!=""&&min=="")){
           alert("面试时间输入错误！");
           return false;
          }
	       document.forms[0].action = "addzpxx.do?method=addzpxx&jytype=jyweb&doType=save";
	       document.forms[0].submit();
		}
		
		
		 //exclude left and right space; 
	    function trim(s){
 		 return rtrim(ltrim(s)); 
	    }
	     //exclude left space; 
	    function ltrim(s){
 		 return s.replace( /^\s*/, ""); 
	    } 
	      //exclude right space; 
	    function rtrim(s){ 
 		 return s.replace( /\s*$/, ""); 
	    }
    
        function isEmail(s){
	     s = trim(s); 
 	     var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	     return p.test(s);
        }
        
        function updateinfo(){
		var url ="updatezpxxinfo.do?method=updateZpxxInfo&jytype=jyweb&pkValue=";
		
		var pkValue ="";	 
		  pkValue = curr_row.getElementsByTagName("input")[0].value;
		  url += pkValue;
		  showTopWin(url, 650, 500);
		  
		}
        function refreshtheweb()
		{
			document.forms[0].action = "adminoperation.do?method=adminoperation&act=zpxx&jytype=jyweb";
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
		<html:form action="/addzpxx" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="webtype" value="fbzp" />
			<input type="hidden" name="viewzydm" value="" id="viewzydm"/>
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="dwcontrl.jsp"></jsp:include>
						<div class="rdxw">
							<h1></h1>
						</div>
						<div class="yqlj">
							<h1></h1>
							<span></span>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
								<h3>
									当前位置：
									<a href="index.do">首页</a>选择
									招聘信息发布&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关记录&nbsp;&nbsp;<font color="red">提示：双击一行空白处可以修改内容。</font>
								</h3>
							<table width="98%" align="center" class="tbborder">
								<tr height="25" class="btys">
									<td>
										<font color="red">单位名称</font>
									</td>
									<td>
										<font color="red">招聘岗位</font>
									</td>
									<td>
										<font color="red">发布时间</font>
									</td>
									<td>
										<font color="red">审核结果</font>
									</td>
									<td>
										<font color="red">操作</font>
									</td>
								</tr>
								<logic:iterate name="list" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)" ondblclick="updateinfo()">
										<td>
									    <input type="hidden" name="pk" value="<bean:write name="s" property="rowid" />"  />
											<a href="viewzpinfo.do?method=jyzpinfo&doType=view&jytype=jyweb&pkValue=<bean:write name="s" property="rowid" />" target="_blank"><bean:write name="s" property="gsmc" />
											</a>
										</td>
										<td>
											<bean:write name="s" property="zpzw" />
										</td>
										<td>
											<bean:write name="s" property="fbsj" />
										</td>
										<td>
											<bean:write name="s" property="xxsh" />
										</td>
										<td>
											<a onclick="updateinfo(this)" href="#">修改</a>&nbsp;|&nbsp;
											<a href="addzpxx.do?method=addzpxx&doType=del&jytype=jyweb&pkValue=<bean:write name="s" property="rowid" /> ">删除
											</a>
										</td>
									</tr>
								</logic:iterate>
							</table>
							<br>
							<div id="admingl2" style="display:">
								<button onclick="setdisplay()">
									发布招聘信息
								</button>
							</div>
							<div id="admingl" style="display: none">
								<button onclick="setdisplay()">
									关闭窗体
								</button>
								<table width="98%" align="center" class="tbborder">
									<tr height="28">
										<td align="right" width="15%">
											<font color="red">*</font>招聘职位：
										</td>
										<td width="35%">
											<html:text name="rs" property="zpzw" maxlength="25"
												style="width:100%" />
										</td>
										<td align="right" width="15%">
											<font color="red">*</font>公司名称：
										</td>
										<td width="35%">
											<html:text name="rs" property="gsmc" readonly="true"
												style="width:100%" />
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											电子邮箱：
										</td>
										<td>
											<html:text name="rs" property="email" maxlength="30"
												style="width:100%" />
										</td>
										<td align="right">
											联系电话：
										</td>
										<td>
											<html:text name="rs" property="lxdh" maxlength="15"
												style="width:100%" />
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											联系人：
										</td>
										<td align="left">
											<html:text name="rs" property="lxr" maxlength="30"
												style="width:100%" />
										</td>
										<td align="right">
											移动电话：
										</td>
										<td>
											<html:text name="rs" property="yddh" maxlength="50"
												style="width:100%" />
										</td>

									</tr>
									<tr height="28">
										<td align="right">
											网址：
										</td>
										<td>
											<html:text name="rs" property="gswz" maxlength="50"
												style="width:100%" />
										</td>
										<td align="right">
											传真：
										</td>
										<td>
											<html:text name="rs" property="cz" maxlength="50"
												style="width:100%" />
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											工作地点：
										</td>
										<td>
											<html:text name="rs" property="gzdd" maxlength="25"
												style="width:100%" />
										</td>
										<td align="right">
											招聘人数：
										</td>
										<td>
											<html:text name="rs" property="zprs" maxlength="3"
												style="width:100%" />
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											行业分类：
										</td>
										<td>
											<html:select name="rs" property="hyfl" styleId="hyfl"
												style="width:100%">
												<html:option value=""></html:option>
												<html:options collection="hyflList" property="hyfl"
													labelProperty="hyfl" />
											</html:select>
										</td>
										<td align="right">
											外语要求：
										</td>
										<td>
											<html:text name="rs" property="wyyq" maxlength="10"
												style="width:100%" />
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											试用期薪水：
										</td>
										<td>
											<div align="left">
												<html:select name="rs" property="syxs">
													<html:option value=""></html:option>
													<html:option value="面议">面议</html:option>
													<html:option value="1000以下">1000以下</html:option>
													<html:option value="1000-1500">1000-1500</html:option>
													<html:option value="1500-2500">1500-2500</html:option>
													<html:option value="2500-3500">2500-3500</html:option>
													<html:option value="3500-5000">3500-5000</html:option>
													<html:option value="5000-7000">5000-7000</html:option>
													<html:option value="7000-10000">7000-10000</html:option>
													<html:option value="10000以上">10000以上</html:option>
												</html:select>
											</div>
										</td>
										<td align="right">
											转正后薪水：
										</td>
										<td>
											<div align="left">
												<html:select name="rs" property="zzxs">
													<html:option value=""></html:option>
													<html:option value="面议">面议</html:option>
													<html:option value="1000以下">1000以下</html:option>
													<html:option value="1000-1500">1000-1500</html:option>
													<html:option value="1500-2500">1500-2500</html:option>
													<html:option value="2500-3500">2500-3500</html:option>
													<html:option value="3500-5000">3500-5000</html:option>
													<html:option value="5000-7000">5000-7000</html:option>
													<html:option value="7000-10000">7000-10000</html:option>
													<html:option value="10000以上">10000以上</html:option>
												</html:select>
											</div>
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											性别要求：
										</td>
										<td>
											<div align="left">
												<html:select name="rs" property="xb" style="width:70px">
													<html:option value=""></html:option>
													<html:option value="男">男</html:option>
													<html:option value="女">女</html:option>
												</html:select>
												单位性质：
												<html:select name="rs" property="dwxz" style="width:60px">
												    <html:option value=""></html:option>
													<html:option value="国有">国有</html:option>
													<html:option value="集体">集体</html:option>
													<html:option value="民营">民营</html:option>
													<html:option value="私营">私营</html:option>
													<html:option value="股份">股份</html:option>
													<html:option value="合资">合资</html:option>
													<html:option value="独资">独资</html:option>
												</html:select>
											</div>
										</td>
										<td align="right">
											面试时间：
										</td>
										<td>
											<html:text name="rs" style="cursor:hand; width:70px;"
												styleId="day" property="day"
												onclick="return showCalendar('day','y-mm-dd');"
												readonly="true" />
											<html:select name="rs" property="hour">
												<html:option value=""></html:option>
												<html:option value="06">06</html:option>
												<html:option value="07">07</html:option>
												<html:option value="08">08</html:option>
												<html:option value="09">09</html:option>
												<html:option value="10">10</html:option>
												<html:option value="11">11</html:option>
												<html:option value="12">12</html:option>
												<html:option value="13">13</html:option>
												<html:option value="14">14</html:option>
												<html:option value="15">15</html:option>
												<html:option value="16">16</html:option>
												<html:option value="17">17</html:option>
												<html:option value="18">18</html:option>
												<html:option value="19">19</html:option>
												<html:option value="20">20</html:option>
												<html:option value="21">21</html:option>
												<html:option value="22">22</html:option>
											</html:select>
											时
											<html:select name="rs" property="min">
												<html:option value=""></html:option>
												<html:option value="00">00</html:option>
												<html:option value="10">10</html:option>
												<html:option value="20">20</html:option>
												<html:option value="30">30</html:option>
												<html:option value="40">40</html:option>
												<html:option value="50">50</html:option>
											</html:select>
											分
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											学历要求：
										</td>
										<td>
											<div align="left">
												<html:select name="rs" property="xlyq" style="width:90px">
													<html:option value=""></html:option>
													<html:option value="专科">专科</html:option>
													<html:option value="专科以上">专科以上</html:option>
													<html:option value="本科">本科</html:option>
													<html:option value="本科以上">本科以上</html:option>
													<html:option value="硕士">硕士</html:option>
													<html:option value="硕士以上">硕士以上</html:option>
													<html:option value="博士">博士</html:option>
													<html:option value="博士以上">博士以上</html:option>
												</html:select>
											</div>
										</td>
										<td align="right">
											面试地点：
										</td>
										<td>
											<html:text name="rs" property="msdd" maxlength="30"
												style="width:100%" />
										</td>
									</tr>
									<tr height="28">
										<td align="right">
											面试携带：
										</td>
										<td>
											<html:text name="rs" property="msxd" style="width:100%" />
										</td>
										<td>
											单位地址：
										</td>
										<td>
											<html:text name="rs" property="dwdz" maxlength="30"
												style="width:100%" />
										</td>

									</tr>
									<logic:equal value="10856" name="xxdm">
									<tr height="28">
										<td align="right">
											所需专业：
										</td>
										<td align="left">
											<div style="overflow: auto;height:220px">
												<table class="tbstyle" id="zyinfo">
												<thead>
													<td><input type="checkBox" title="全选" onclick="selectall(this);"/></td>
													<td>专业名称</td>
												</thead>
												
	 											<logic:iterate id="s" name="zyList">
	 											<tr>
	 												<td><input type="checkBox" value="<bean:write name="s" property="zydm"/>" onclick="viewzy()"/></td>
	 												<td><bean:write name="s" property="zymc"/></td>
												</tr>
	 											</logic:iterate>
	 											</table>
	 										</div>										
										</td>
										<td colspan="2">
											<html:textarea name="rs" property="zy" readonly="true" styleId="sxzy" cols="35" rows="15">专业不限</html:textarea>
										</td>
									</tr>
									</logic:equal>
									<tr>
										<td align="right">
											岗位职责：
										</td>
										<td colspan="3">
											<html:textarea name="rs" property="gwzz" rows="8"
												style="width:100%" />
										</td>
									</tr>
									<tr>
										<td align="right">
											职位要求：
										</td>
										<td colspan="3">
											<html:textarea name="rs" property="zwyq" rows="8"
												style="width:100%" />
										</td>
									</tr>
									<tr>
										<td align="right">
											公司简介：
										</td>
										<td colspan="3">
											<html:textarea name="rs" property="gsjj" rows="12"
												style="width:100%" />
										</td>
									</tr>
								</table>
								<div align="center">
									<button onclick="addzpxx();" style="width:80px">
										提 交
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" style="width:80px">
										重 置
									</button>
								</div>
							</div>

						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<button onclick="refreshtheweb()" id="search_go" style="display: none">
			</button>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("提交成功！");
               </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("提交失败！");
               </script>
			</logic:equal>
		</logic:notEmpty>
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
	</body>
</html>
