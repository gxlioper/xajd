<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xszz/jtcy.js"></script>
	<script language="javascript" src="js/xszz/xszzInit.js"></script>
	<script language="javascript" src="js/xszz/xszzComm.js"></script>
	<script language="javascript" src="js/xszz/xszzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script language="javascript" src="js/xszz/xszzOptionList.js"></script>	
	<script language="javascript">
	
	//项目特殊处理
	function xmTsCl(){
	
		var xmb = $("xmb").value;
	
		if(xmb == "gjzxdkb"){
	
			if($("xnje")){
				$("xnje").readOnly = true;
			}
		}
	}
	
	//设置学年金额
	function setXnje(){
	
		var xmb = $("xmb").value;
		var xmzzje = $("xmzzje").value;
		
		if(xmb == "gjzxdkb"){
		
			var xz = "1";
			
			if($("xz")){
				xz = $("xz").value;
			}
			alert(xz);
		}
	}
	
	function showXscjXx(){
		showTopWin('/xgxt/xszzXszj.do?method=xscjManage&xh='+$("xh").value,400,300);
	}
	</script>
	</head>

	<body onload="onShow_xmsh();xszzInit();">
		<!-- 标题 -->
		<!-- 标题 end-->
		<html:form action="/commXszz" method="post" enctype="multipart/form-data">
			<html:hidden property="iskns" styleId="iskns"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" id="xh" value="${xmInfo.xh }"/>
			<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
			<input type="hidden" name="mrxm" id="mrxm" value="${xmInfo.mrxm }"/>
			<!-- 关系 -->
			<html:select property="gx" styleClass="select" style="display : none" styleId="gx">
				<html:options collection="gxList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- 隐藏域 end-->
			<!-- 项目基本情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
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
						${xmInfo.xmmc } 申请 
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
									<logic:equal name="xmnr" property="mk" value="xmxgInfo">
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
					<!-- 综测合素质测评 end-->
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
					<logic:equal name="xmnr" property="mk" value="shInfo">
						<%@ include file="sqsh/shInfo.jsp"%>
					</logic:equal>
				</logic:iterate>				
			</logic:notEmpty>
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tr>
					<td align="center">
						<div class="btn">
							<!-- 非查看 -->
							<logic:notEqual name="doType" value="view">
								<button type="button" id="buttonSave" onclick="xmsh('tg')" style="width: 80px">
									通过
								</button>
								&nbsp;&nbsp;
								<button type="button" id="buttonSave" onclick="xmsh('btg')" style="width: 80px">
									不通过
								</button>
								&nbsp;&nbsp;
							</logic:notEqual>
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
			</table>
			<!-- 提示信息 -->
			<%@ include file="other/tsxx.jsp"%>
		</html:form>
	</body>
</html>