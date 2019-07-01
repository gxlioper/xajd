<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- ���ߣ�qph. ��ע�������˵�ҳ�������ע��ȥ���Ǻ��ˣ��� -->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			jQuery(function(){
				
				var xyid = jQuery("#xydm").val();
				var zyid = jQuery("#zydm").val();

				
				var xyArr = xyid=="" ? [] : xyid.split("!!@!!");
				var zyArr = zyid=="" ? [] : zyid.split("!!@!!");

			
				for (var i = 0,c=xyArr.length ; i < c ; i++){
					jQuery("a[name=selectXy][value="+xyArr[i]+"]").addClass("current");
				}

				for (var i = 0,c=zyArr.length ; i < c ; i++){
					jQuery("a[name=selectZy][value="+zyArr[i]+"]").addClass("current");
				}
				

				jQuery("#btn_fh").bind("click",function(){
					document.location.href="general_xsxx_tjcx.do?method=tjcxByXyZy&xydm="+xyid;
				});


				jQuery(".filter_condition").find("a").bind("click",function(){

					if (jQuery(this).attr("class") == "current"){
						jQuery(this).removeClass("current");
					} else {
						jQuery(this).addClass("current");
					}

					//����ѡ����
				
					var selectXy = jQuery("a[name=selectXy][class=current]");
					var selectZy = jQuery("a[name=selectZy][class=current]");
					var xyArr = [];
					var zyArr = [];

					for (var i = 0 ; i < selectXy.length ; i++){
						xyArr.push(selectXy.eq(i).attr("value"));
					}
					for (var i = 0 ; i < selectZy.length ; i++){
						zyArr.push(selectZy.eq(i).attr("value"));
					}
					
					document.location.href="general_xsxx_tjcx.do?method=tjcxByXyZyBj&xydm="+xyArr.join("!!@!!")+"&zydm="+zyArr.join("!!@!!");
				})
			});

			function cxXs(obj){
				//����ѡ����
				
				var selectXy = jQuery("a[name=selectXy][class=current]");
				var selectZy = jQuery("a[name=selectZy][class=current]");
				var xyArr = [];
				var zyArr = [];

				for (var i = 0 ; i < selectXy.length ; i++){
					xyArr.push(selectXy.eq(i).attr("value"));
				}
				for (var i = 0 ; i < selectZy.length ; i++){
					zyArr.push(selectZy.eq(i).attr("value"));
				}
				document.location.href="general_xsxx_tjcx.do?method=tjxsByXyZyBjXs&bjdm="+obj+"&xydm="+xyArr.join("!!@!!")+"&zydm="+zyArr.join("!!@!!");
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
		<html:hidden property="xydm" name="xsxxTjcxForm" styleId="xydm"/>
		<html:hidden property="zydm" name="xsxxTjcxForm" styleId="zydm"/>
	
	
		<!-- �๦�ܲ����� -->
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> �� �� </a>
					</li>
					<li>
						<a id="btn_fh" class="btn_fh"> �� �� </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="filter_condition after">
			<h3>
				<bean:message key="lable.xb" />��
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
				רҵ��
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
									ѧ����
									<em>${r.allrs }</em>��
								</p>
								<p>
									����
									<em>${r.man }</em>��&nbsp;&nbsp;&nbsp;ռ ${r.manbl }&nbsp;&nbsp;&nbsp;
									Ů��
									<em>${r.woman }</em>��&nbsp;&nbsp;&nbsp;ռ  ${r.womanbl }
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
