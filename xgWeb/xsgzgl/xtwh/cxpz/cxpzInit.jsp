<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" defer="defer">
			
			jQuery(function(){
				loadColList();
			});
			
			
			function loadColList(){
				var gnbz = jQuery("#gnbz").val();
				jQuery("#demoPage").load("xtwh_cxpz.do?method=ylxg",{gnbz:gnbz});		
				jQuery(".rightframe04").load("xtwh_cxpz.do?method=getColList&gnbz="+gnbz);
			}
			
			function reloadCxpz(gnbz){
				document.location.href="xtwh_cxpz.do?method=cxpzInit&gnbz="+gnbz;
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xtwh_cxpz">
			<html:hidden property="gnbz" styleId="gnbz" />
		
			<div id="demoPage" style="height: 65px;">
			</div>
		
			<div class="leftframe04" style="width:20%; ">
				<div class="menulist">
					<!--层start-->
					<div class="menutitle">
						<h3>
							<span class="title"> 查询功能列表 </span>
						</h3>
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height: 420px; overflow-x: hidden; overflow-y: auto;">
							<ul id="gnbzList">
								<logic:present name="cxgnList">
									<logic:iterate id="cxgn" name="cxgnList" >
										<li>
											<a href="javascript:void(0);" onclick="reloadCxpz('${cxgn.gnbz}');">
												${cxgn.gnmc}
											</a>
										</li>
									</logic:iterate>
								</logic:present>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="rightframe04" style="width:80%;overflow-x:auto;">
	
			</div>
		</html:form>
	</body>
</html>
