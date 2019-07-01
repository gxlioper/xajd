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
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
		
		
		function delgrjl(){
		    if(confirm("确定要删除个人简历吗？")){
		       document.forms[0].action = "grjldj.do?method=grjldj&act=del&jytype=jyweb";
	           document.forms[0].submit();	
	        }else{
	         return false;
	        }
		}
		
		
		
		
		function addgrjl(){
          var xsxh = document.getElementById("xsxh").value;
	      var lxdh = document.getElementById("lxdh").value;
	      var yzbm = document.getElementById("yzbm").value;
	      var email = document.getElementById("email").value;
	      var lxdz =document.getElementById("lxdz").value;
	      
	      var hjqk = document.getElementById("hjqk").value;
	      var xxqk = document.getElementById("xxqk").value;
	      var xjysjl = document.getElementById("xjysjl").value;
	      var shsjqk = document.getElementById("shsjqk").value;
	      var gzjl = document.getElementById("gzjl").value;
	      var grtc = document.getElementById("grtc").value;
	      var zwtj = document.getElementById("zwtj").value;
	      var id = document.getElementById("id").value;
	      
	      if(hjqk.length>2000){
		   alert("获奖情况不能大于2000字节!");
		   return false;
		  }
	      if(xxqk.length>2000){
		   alert("学习情况不能大于2000字节!");
		   return false;
		  }
	      if(xjysjl.length>2000){
		   alert("校级以上奖励不能大于2000字节!");
		   return false;
		  }
	      if(shsjqk.length>2000){
		   alert("社会实践情况不能大于2000字节!");
		   return false;
		  }
	      if(gzjl.length>2000){
		   alert("工作经历不能大于2000字节!");
		   return false;
		  }
	      if(grtc.length>2000){
		   alert("个人特长不能大于2000字节!");
		   return false;
		  }
	      if(zwtj.length>2000){
		   alert("自我推荐不能大于2000字节!");
		   return false;
		  }
	      
	      
	      
	      
	      if(xsxh==""){
	      alert("学号不能为空！");
	      return false;
	      }
	      if(lxdz!=""&&lxdz.length>25){
	      alert("联系地址长度过大，请简略！");
	      return false;
	      }
	      
	      if(lxdh.length<7&&lxdh!=""){
	      alert("电话号码长度不合要求！");
	      return false;
	      }
	      if(lxdh.length>13&&lxdh!=""){
	      alert("电话号码长度不合要求！");
	      return false;
	      }
	      if(yzbm.length!=6&&yzbm!=""){
	      alert("邮政编码长度应为6位");
	      return false;
	      }
	      if(!isNumber(yzbm)&&yzbm!=""){
	      alert("邮政编码应为数字！");
	      return false;
	      }
	      if(!isEmail(email)&&email!=""){
	      alert("电子邮箱不合法！");
	      return false;
	      }	
	      if(lxdz==""&&lxdh==""&&email==""){
	      alert("请至少填写一个联系方式！");
	      return false;		   
		  }
		  if(id!=""){
		       if(checkSfzh(id)){
		       document.forms[0].action = "grjldj.do?method=grjldj&doType=save&jytype=jyweb";
	           document.forms[0].submit();
	           }
	      }else{
	           document.forms[0].action = "grjldj.do?method=grjldj&doType=save&jytype=jyweb";
	           document.forms[0].submit();
	      }
		}
		
       function isNumber(s){
	    var p = /^(-|\+)?\d+$/;
	    return p.test(s); 
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
		
		
		
		function checkSfzh(sfzh) {
        sfzh=sfzh.toUpperCase();
	    var OldID;
	    if(sfzh.length == 15){
		OldID = sfzh;
		return true;
	    }else if(sfzh.length == 18){
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	    }else{
		alert("请输入正确的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	    }
	    var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	    var A = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	    var i, j, S;
	    var NewID, ID, strNF;
	    NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	    S = 0;
	    for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	    }
	    S = S % 11;
	    if(sfzh != NewID + A[S]){
		alert("请输入正确的身份证号码！","",function(){
			obj.select();
			obj.focus();
		});
		  return false;
	    }
	   return true;
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
		<html:form action="/grjldj" method="post">
			<html:hidden name="rs" property="pkValue" />
            <input type="hidden" name="webtype" value="grjldj" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="stucontrl.jsp"></jsp:include>
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
									<a href="index.do">首页</a>选择 个人简历登记及修改
								</h3>
							<h1>
								个人简历登记
							</h1>
							<logic:equal name="youjl" value="youjl">
								<font color="red">提示：你已登记了个人简历，以下操作可以对简历内容进行修改！</font>
							</logic:equal>
							<logic:equal name="nojl" value="nojl">
								<font color="red">提示：你还未登记个人简历，请将以下内容补充完整并提交！</font>
							</logic:equal>
							<table width="95%" align="center" class="tbborder">
								<thead>
									<tr>
										<td colspan="9" align="left">
											<div align="left">
												身份证号：
												<html:text name="rs" property="id" style="width:112px" />
												<html:checkbox name="rs" property="idsee" value="no" />
												(保密) &nbsp;&nbsp;&nbsp;&nbsp;入学年份:
												<html:text name="rs" property="rxnf" style="width:40px" />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<font color="red">*</font>学号：
												<html:text property="xsxh" name="rs" styleId="xsxh"
													readonly="true" style="width:100px" />
												<br>
												<html:checkbox name="rs" property="hidden" value="yes" />
												只对投递单位公开
											</div>
										</td>
									</tr>
								</thead>
								<tr>
									<td rowspan="3" align="center" width="30">
										<b>个<br>人<br>资<br>料</b>
									</td>
									<td align="right" width="70">
										姓名：
									</td>
									<td width="150">
										<html:text name="rs" property="name" style="width:100%"
											readonly="true" />
									</td>
									<td align="right" width="70">
										性别：
									</td>
									<td width="150" colspan="2">
										<html:text name="rs" property="xb" style="width:100%"
											readonly="true" />
									</td>
									<td align="right" width="70">
										出生年月：
									</td>
									<td width="150" colspan="2">
										<html:text name="rs" property="csny" style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="right">
										民族：
									</td>
									<td width="150">
										<html:select name="rs" property="mz" style="width:100%">
											<html:options collection="mzList" property="mzmc"
												labelProperty="mzmc" />
										</html:select>
									</td>
									<td align="right">
										学历：
									</td>
									<td colspan="2">
										<html:text name="rs" property="xl" style="width:100%" />
									</td>
									<td align="right">
										政治面貌：
									</td>
									<td colspan="2">
										<html:select name="rs" property="zzmm" style="width:150px">
											<html:option value="无党派民主人士">无党派民主人士</html:option>
											<html:options collection="zzmmList" property="zzmm"
												labelProperty="zzmm" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="right">
										专业名称：
									</td>
									<td>
										<html:text name="rs" property="zymc" style="width:100%"
											readonly="true" />
									</td>
									<td align="right">
										辅修专业：
									</td>
									<td colspan="2">
										<html:text name="rs" property="fxzymc" style="width:100%" />
									</td>
									<td align="right">
										生源地区：
									</td>
									<td colspan="2">
										<html:text name="rs" property="sydq" style="width:100%" />
									</td>
								</tr>
								<tr>
									<td rowspan="2" align="center">
										<b>联<br>系<br>方<br>法</b>
									</td>
									<td align="right">
										联系地址：
									</td>
									<td colspan="3">
										<html:textarea name="rs" property="lxdz" rows="2"
											style="width:100%" />
									</td>
									<td align="right">
										联系电话：
									</td>
									<td colspan="3">
										<html:textarea name="rs" property="lxdh" rows="2"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="right">
										邮政编码：
									</td>
									<td colspan="3">
										<html:text name="rs" property="yzbm" style="width:100%"
											maxlength="6" />
									</td>
									<td align="right">
										电子邮箱：
									</td>
									<td colspan="3">
										<html:text name="rs" property="email" style="width:100%"
											maxlength="25" />
									</td>
								</tr>
								<tr>
									<td rowspan="5" align="center">
										<b>学<br>生<br>综<br>合<br>情<br>况</b>
									</td>
									<td align="center">
										获奖情况
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="hjqk" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										学习情况
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="xxqk" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										校级以
										<br>
										上奖励
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="xjysjl" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										社会实
										<br>
										践情况
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="shsjqk" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										工作经历
									</td>
									<td colspan="4">
										<html:textarea name="rs" property="gzjl" rows="8"
											style="width:100%" />
									</td>
									<td align="center">
										个人特长
									</td>
									<td colspan="2">
										<html:textarea name="rs" property="grtc" rows="8"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										<b>自<br>我<br>推<br>荐</b>
									</td>
									<td colspan="8">
										<html:textarea name="rs" property="zwtj" rows="25"
											style="width:100%" />
									</td>
								</tr>
							</table>
							<div align="center">
								<button onclick="addgrjl();">
									提交/修改
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset">
									重置
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button onclick="delgrjl();">
									删除
								</button>
							</div>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
























			<div>
				<h3>
				</h3>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("简历登记成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("简历登记失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("简历修改成功！");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("简历修改失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("简历删除成功！");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("简历删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
