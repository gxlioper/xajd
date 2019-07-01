<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			<script language="javascript" src="js/xszz/jtcy.js"></script>	
			<script language="javascript" src="js/xszz/xszzFunction.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
			<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
			<script language="javascript" src="js/check.js"></script>
			<script language="javascript" src="js/xszz/xszzInit.js"></script>
			<script language="javascript" src="js/xszz/xszzComm.js"></script>
			<script language="javascript" src="js/xszz/xszzOptionList.js"></script>
			<script language="javascript">
			//打印报表
			function printBb(){
				
				var ie = 'ie';
				if(ie == "5.6"){
					confirmInfo("您的IE版本未IE6，版本过低，可能导致打印样式问题，建议使用IE8，请问是否要在官网进行升级（取消可以继续用本浏览器打印）",askInfo);
				}else{
					url = "/xgxt/commPrint.do?method=printJsp";
					
					document.forms[0].action = url;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
					
					var xmdm = $("xmdm").value;
				}
			}
			
			//询问信息
			function askInfo(tag){
				if(tag == "ok"){
					document.forms[0].action = "http://www.microsoft.com/downloads/zh-cn/details.aspx?FamilyID=341c2ad5-8c3d-4347-8c03-08cdecd8852b";
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
					
					var xmdm = $("xmdm").value;
				}else{
					document.forms[0].action = "commPrint.do?method=printJsp";
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";
					
					var xmdm = $("xmdm").value;
				}
			}
		
			function showFileUpload(zd){
				var url =  "/xgxt/commXszz.do?method=fileUpload";
					url+= "&zd="+zd;
				showTopWin(url,'400','200');
			}
			
			function showXscjXx(){
				showTopWin('/xgxt/xszzXszj.do?method=xscjManage&xh='+$("xh").value,400,300);
			}
			</script>
	</head>
	<body onload="onShow_xmsq();xszzInit();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz" method="post" enctype="multipart/form-data">
			<!-- 隐藏域 -->
			<html:hidden property="iskns" styleId="iskns"/>
			<input type="hidden" value="${knsdl }" id="knsdl"/>
			<input type="hidden" value="${jtqkdc }" id="jtqkdc"/>
			<input type="hidden" value="${xmInfo.ysq }" id="ysq" name="ysq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
			
			<input type="hidden" name="url" id="url" value="/commXszz.do?method=xmsqUpdate" />
			
			<input type="hidden" name="save_sqsj" id="sqsj" value="${xmInfo.sqsj }"/>
			<input type="hidden" name="save_xmdm" id="xmdm" value="${xmInfo.xmdm }"/>
			<input type="hidden" name="save_xh" id="xh" value="${xmInfo.xh }"/>
			<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
			<input type="hidden" name="mrxm" id="mrxm" value="${xmInfo.mrxm }"/>
			<input type="hidden" name="url" id="url" value="/xgxt/commXszz.do?method=xmsqUpdate"/>
			<!-- 关系 -->
			<html:select property="gx" styleClass="select" style="display : none" styleId="gx">
				<html:options collection="gxList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- 隐藏域 end-->
			<!-- 项目基本情况 -->
			<div class="tab" >
			<table class="formlist" width="99%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5"><b>
						<logic:equal name="xmInfo" property="sqzq" value="学年">
							${xmInfo.xn }学年
							<input type="hidden" name="save_xn" value="${xmInfo.xn }"/>
						</logic:equal>
						<logic:equal name="xmInfo" property="sqzq" value="年度">
							${xmInfo.nd }年度
							<input type="hidden" name="save_nd" value="${xmInfo.nd }"/>
						</logic:equal>
						<logic:equal name="xmInfo" property="sqzq" value="学期">
							${xmInfo.xn }学年
							${xmInfo.xqmc }学期
							<input type="hidden" name="save_xq" value="${xmInfo.xq }"/>
							<input type="hidden" name="save_xn" value="${xmInfo.xn }"/>
						</logic:equal>
						${xmInfo.xmmc } 申请</b>
						</font>
					</td>
				</tr>
			</table>
			
					
			<logic:notEmpty name="xmnrList">
				
				<logic:iterate name="xmnrList" id="xmnr">
					<!-- 学生基本信息 -->
					<logic:equal name="xmnr" property="mk" value="view_xsjbxx">
						<%@ include file="sqsh/xsInfo.jsp"%>
					</logic:equal>
					<!-- 学生基本信息 end-->
					
					<!-- 困难生认定作为独立模块，并且困难生认定包含了家庭情况调查项目 -->
					<logic:equal name="xmInfo" property="xmb" value="xszz_knsb">
						<logic:equal value="yes" property="iskns" name="xszzTyForm">
							<logic:equal value="true" name="knsdl">
								<logic:equal value="true" name="jtqkdc">
									<logic:equal name="xmnr" property="mk" value="">
										<%@ include file="sqsh/jtcyInfo.jsp"%>
									</logic:equal>
								</logic:equal>
							</logic:equal>
						</logic:equal>
					</logic:equal>
					
					
					<!-- 家庭情况 -->
					<logic:equal name="xmnr" property="mk" value="jtqkdcb">
						<%@ include file="sqsh/jtqkInfo.jsp"%>
					</logic:equal>
					<!-- 家庭情况 end-->					
					<!-- 综测合素质测评 -->
					<logic:equal name="xmnr" property="mk" value="zcf">
						<%@ include file="sqsh/zcf.jsp"%>
					</logic:equal>		
					<!-- 其他信息 -->
					<logic:equal name="xmnr" property="mk" value="xsqtxxb">
						<%@ include file="sqsh/qtInfo.jsp"%>
					</logic:equal>	
					<!-- 其他信息 end-->	
					<!-- 困难生信息 -->
					<logic:equal name="xmnr" property="mk" value="xszz_knsb">
						<%@ include file="sqsh/knInfo.jsp"%>
					</logic:equal>	
					<!-- 困难生信息 end-->
					<!-- 项目情况 -->
					<logic:equal name="xmnr" property="mk" value="xmxgInfo">
						<%@ include file="sqsh/wsInfo.jsp"%>
					</logic:equal>
					<!-- 项目情况 end-->
					
				</logic:iterate>	
			</logic:notEmpty>
			<logic:equal name="xspjpy" value="true">
			<%@ include file="sqsh/pjpyInfo.jsp"%>
			</logic:equal>
			</div>
			<table  class="formlist" width="99%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz"><span class="red">注：若打印样式有问题，请您使用IE8进行尝试</span></div>
							<div class="btn">
							<!-- 非查看 -->
							<logic:notEqual name="doType" value="view">
								<button type="button" id="buttonSave" onclick="saveXmsq()" style="width: 80px">
									保	存
								</button>
								&nbsp;&nbsp;
							</logic:notEqual>
							<button type="button" id="printBtn" onclick="printBb();" style="width: 80px">
								打印
							</button>
							&nbsp;&nbsp;
							<button type="button" id="buttonSave" onclick="window.close();return false;" style="width: 80px">
								关	闭
							</button> 
							<logic:equal name="xxdm" value="10491">
							&nbsp;&nbsp;
							<button type="button" id="buttonSave" onclick="showXscjXx()" style="width: 80px">
								成  绩
							</button>
							</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="other/tsxx.jsp"%>
		</html:form>
	</body>
</html>