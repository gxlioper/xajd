<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 作者：qph. （注：拷别人的页面把名字注释去掉是好人！） -->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			jQuery(function(){
				var njid = jQuery("#nj").val();
				var xyid = jQuery("#xydm").val();
				var zyid = jQuery("#zydm").val();

				var njArr = njid=="" ? [] : njid.split("!!@!!");
				var xyArr = xyid=="" ? [] : xyid.split("!!@!!");
				var zyArr = zyid=="" ? [] : zyid.split("!!@!!");

				for (var i = 0,c=njArr.length ; i < c ; i++){
					jQuery("a[name=selectNj][value="+njArr[i]+"]").addClass("current");
				}

				for (var i = 0,c=xyArr.length ; i < c ; i++){
					jQuery("a[name=selectXy][value="+xyArr[i]+"]").addClass("current");
				}

				for (var i = 0,c=zyArr.length ; i < c ; i++){
					jQuery("a[name=selectZy][value="+zyArr[i]+"]").addClass("current");
				}
				

				jQuery("#btn_fh").bind("click",function(){
					document.location.href="general_xsxx_tjcx.do?method=tjcxByZy&nj="+njid+"&xydm="+xyid;
				});


				jQuery(".filter_condition").find("a").bind("click",function(){

					if (jQuery(this).attr("class") == "current"){
						jQuery(this).removeClass("current");
					} else {
						jQuery(this).addClass("current");
					}

					//处理选中项
					var selectNj = jQuery("a[name=selectNj][class=current]");
					var selectXy = jQuery("a[name=selectXy][class=current]");
					var selectZy = jQuery("a[name=selectZy][class=current]");
					var njArr = [];
					var xyArr = [];
					var zyArr = [];

					for (var i = 0 ; i < selectNj.length ; i++){
						njArr.push(selectNj.eq(i).attr("value"));
					}
					for (var i = 0 ; i < selectXy.length ; i++){
						xyArr.push(selectXy.eq(i).attr("value"));
					}
					for (var i = 0 ; i < selectZy.length ; i++){
						zyArr.push(selectZy.eq(i).attr("value"));
					}
					
					document.location.href="general_xsxx_tjcx.do?method=tjcxByBj&nj="+njArr.join("!!@!!")+"&xydm="+xyArr.join("!!@!!")+"&zydm="+zyArr.join("!!@!!");
				})
			});

			function cxXs(obj){
				//处理选中项
				var selectNj = jQuery("a[name=selectNj][class=current]");
				var selectXy = jQuery("a[name=selectXy][class=current]");
				var selectZy = jQuery("a[name=selectZy][class=current]");
				var njArr = [];
				var xyArr = [];
				var zyArr = [];

				for (var i = 0 ; i < selectNj.length ; i++){
					njArr.push(selectNj.eq(i).attr("value"));
				}
				for (var i = 0 ; i < selectXy.length ; i++){
					xyArr.push(selectXy.eq(i).attr("value"));
				}
				for (var i = 0 ; i < selectZy.length ; i++){
					zyArr.push(selectZy.eq(i).attr("value"));
				}
				document.location.href="general_xsxx_tjcx.do?method=tjxsByBjdm&bjdm="+obj+"&nj="+njArr.join("!!@!!")+"&xydm="+xyArr.join("!!@!!")+"&zydm="+zyArr.join("!!@!!");
				}

			function sjDc(){
				var url = "general_xsxx_tjcx.do?method=dcsjByType&type=njxyzybj";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
		</script>
	</head>

	<body>
	<html:form action="/general_xsxx_tjcx" method="post">
		<html:hidden property="nj" name="xsxxTjcxForm" styleId="nj"/>
		<html:hidden property="xydm" name="xsxxTjcxForm" styleId="xydm"/>
		<html:hidden property="zydm" name="xsxxTjcxForm" styleId="zydm"/>
	
	
		<!-- 多功能操作区 -->
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> 导 出 </a>
					</li>
					<li>
						<a id="btn_fh" class="btn_fh"> 返 回 </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="filter_condition after filter_float">
			<h3>
				年级：
			</h3>
			<ul>
				<logic:notEmpty name="njList">
					<logic:iterate id="n" name="njList">
						<li>
							<a href="javascript:void(0);" name="selectNj" value="${n.nj }">${n.nj }</a><span>|</span>
						</li>
					</logic:iterate>
				</logic:notEmpty>
			</ul>
		</div>
		<div class="filter_condition after">
			<h3>
				<bean:message key="lable.xb" />：
			</h3>
			<ul>
				<logic:notEmpty name="xyList">
					<%
						List<HashMap<String,String>> xyList = (List<HashMap<String,String>>)request.getAttribute("xyList");	
					
						for (int i = 0 ; i < xyList.size() ; i++){
							HashMap<String,String> map = xyList.get(i);
					%>
					
					<%
						if (i == 0 || !xyList.get(i-1).get("pyszm").equals(map.get("pyszm"))){
					%>
						<li style="display:inline;">
						<h5>
							<img src="<%=stylePath %>/images/num_<%=map.get("pyszm") %>.gif" alt="<%=map.get("pyszm") %>" />
						</h5>
					<%
						}
						
					%>
						<a href="javascript:void(0);" name="selectXy"  value="<%=map.get("xydm") %>" pyzm="<%=map.get("pyszm") %>">
						   	<%=map.get("xymc") %>
						</a><span>|</span>
						
					<%
						if (i != 0 && i != xyList.size()-1 && !xyList.get(i+1).get("pyszm").equals(map.get("pyszm"))){
					%>	
						</li>
					<%
						}
						}
					%>
					</li>
				</logic:notEmpty>
			</ul>
		</div>
		<div class="filter_condition after">
			<h3>
				专业：
			</h3>
			<ul>
				<logic:notEmpty name="zyList">
					<%
						List<HashMap<String,String>> zyList = (List<HashMap<String,String>>)request.getAttribute("zyList");	
					
						for (int i = 0 ; i < zyList.size() ; i++){
							HashMap<String,String> map = zyList.get(i);
					%>
					
					<%
						if (i == 0 || !zyList.get(i-1).get("pyszm").equals(map.get("pyszm"))){
					%>
						<li style="display:inline;">
						<h5>
							<img src="<%=stylePath %>/images/num_<%=map.get("pyszm") %>.gif" alt="<%=map.get("pyszm") %>" />
						</h5>
					<%
						}
						
					%>
						<a href="javascript:void(0);" name="selectZy"  value="<%=map.get("zydm") %>" pyzm="<%=map.get("pyszm") %>">
						   	<%=map.get("zymc") %>
						</a><span>|</span>
						
					<%
						if (i != 0 && i != zyList.size()-1 && !zyList.get(i+1).get("pyszm").equals(map.get("pyszm"))){
					%>	
						</li>
					<%
						}
						}
					%>
					</li>
				</logic:notEmpty>
			</ul>
		</div>
		<div class="class_infor after">
			<ul>
				<logic:notEmpty name="rsList">
					<logic:iterate id="r" name="rsList">
						<li>
							<h2>
							<a href="#" onclick="cxXs('${r.bjdm}')">${r.bjmc}</a>
							</h2>
							<div class="con">
								<p class="num_all">
									学生数
									<em>${r.allrs }</em>人
								</p>
								<p>
									男生
									<em>${r.man }</em>人&nbsp;&nbsp;&nbsp;占 ${r.manbl }&nbsp;&nbsp;&nbsp;
									女生
									<em>${r.woman }</em>人&nbsp;&nbsp;&nbsp;占  ${r.womanbl }
								</p>
							</div>
						</li>
					</logic:iterate>
				</logic:notEmpty>
			</ul>
		</div>
		</html:form>
	</body>
</html>
