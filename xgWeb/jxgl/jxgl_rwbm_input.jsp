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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function savetheinfo(){   
	var xh = document.getElementById("xh").value;
	var jtzz = document.getElementById("jtzz").value;

	
	if(xh==""){
	alert("学号不能为空！");
	return false;
	}
	if(jtzz!=""&&jtzz.length>30){
	alert("联系地址长度过大，请简略！");
	return false;
	}
	
	
	  if(confirm("你确认检查无误？")){
	        BatAlert.showTips('正在提交，请等待...');
		 	document.forms[0].action = "jxglrwbm.do?doType=save";
		 	document.forms[0].submit();
		 	return true;
		 	}else{
		 	return false;
		 	}
	}
	
	function returntobegin(){   
		 	document.forms[0].action = "/xgxt/savegrjl.do";
		 	document.forms[0].submit();
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
    
    
    function updategrzp(xh){
            showTopWin("jxglzpsc.do?xh="+xh, 500, 280);
    }
    
    
    
    
    
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/jxglrwbm" method="post" enctype="multipart/form-data">
			<%@ include file="/jxgl/hiddenValue.jsp"%>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理 - 网上征兵 - 入伍报名登记
				</div>
			</div>
			<logic:notEmpty name="readonly">
				<logic:equal name="readonly" value="readonly">
					<p align="center">
						本页面应由学生操作，管理员权限为只读！
					</p>
				</logic:equal>
			</logic:notEmpty>
			<logic:empty name="readonly">
			<table width="100%" class="tbstyle" id="rwbm">
				<thead>
					<tr height="25">
						<td colspan="7" align="right">
							学号：
							<html:text name="rs" property="xh" readonly="true" />
						</td>
					</tr>
				</thead>
				<tr height="30">
					<td width="15%" align="right">
						姓名：
					</td>
					<td width="15%">
						<html:text name="rs" property="xm" />
					</td>
					<td width="14%" align="right">
						性别：
					</td>
					<td width="15%">
						<html:text name="rs" property="xb" />
					</td>
					<td width="15%" align="right">
						民族：
					</td>
					<td width="15%">
						<html:select name="rs" property="mz" styleId="mz"
							style="width:100%">
							<html:options collection="mzList" property="mzmc"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<td rowspan="5" width="5%" align="center" title="一寸照">
						<logic:equal value="浙江商业职业技术学院" name="xxmc" scope="session">
								<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
						</logic:equal>
						<logic:notEqual value="浙江商业职业技术学院" name="xxmc" scope="session">
							<logic:equal value="12872" name="xxdm">
								<logic:present name="showsfzh">
									<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
								</logic:present>
								<logic:present name="showxh">
									<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
								</logic:present>
							</logic:equal>
							<logic:notEqual value="12872" name="xxdm">
								<%--浙江机电职业技术学院--%>
								<logic:equal value="12861" name="xxdm">
									<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=<bean:write name="rs" property="xh" />"
										border="0" align="absmiddle" style="width:140;height:160" />
								</logic:equal>
								<%--end浙江机电职业技术学院--%>

								<%--非浙江机电职业技术学院--%>
								<logic:notEqual value="12861" name="xxdm">
									<%--非江苏信息职业技术学院--%>
									<logic:notEqual value="13108" name="xxdm">
										<img
											src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
											border="0" align="absmiddle" style="width:140;height:160" />
									</logic:notEqual>
									<%--江苏信息职业技术学院--%>
									<logic:equal value="13108" name="xxdm">
											<img border="0" style="height:133px;width:100px;"
												src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg">
									</logic:equal>
								</logic:notEqual>
								<%--end非浙江机电职业技术学院--%>
							</logic:notEqual>
						</logic:notEqual>
					</td>
				</tr>
				<!-- 非贵州大学 -->
				<logic:notEqual name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						籍贯：
					</td>
					<td>
						<html:text name="rs" property="jg" />
					</td>
					<td align="right">
						出生年月：
					</td>
					<td>
						<html:text name="rs" property="csny" />
					</td>
					<td align="right">
						党（团）：
					</td>
					<td>
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmm"
								labelProperty="zzmm" />
						</html:select>

					</td>
				</tr>
				<tr height="30">
					<td align="right">
						入校时间：
					</td>
					<td>
						<html:text name="rs" property="rxsj" />
					</td>
					<td align="right">
						专业（系）：
					</td>
					<td>
						<html:select name="rs" property="zymc" styleId="zymc"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zymc"
								labelProperty="zymc" />
						</html:select>
					</td>
					<td align="right">
						家庭出身：
					</td>
					<td>
						<html:text name="rs" property="jtcs" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- 贵州大学 -->
				<logic:equal name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						籍贯：
					</td>
					<td>
						<html:text name="rs" property="jg" />
					</td>
					<td align="right">
						出生年月：
					</td>
					<td>
						<html:text name="rs" property="csny" />
					</td>
					<td align="right">
						家庭出身：
					</td>
					<td>
						<html:text name="rs" property="jtcs" />
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						年级：
					</td>
					<td>
						<html:select name="rs" property="nj" style="" onchange="initZyList()">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						<html:select name="rs" property="xydm" style="" styleId="xy" onchange="initZyList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						专业（系）：
					</td>
					<td>
						<html:select name="rs" property="zydm" style="" styleId="zy" onchange="">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						入校时间：
					</td>
					<td>
						<html:text name="rs" property="rxsj" />
					</td>
					<td align="right">
						党（团）：
					</td>
					<td>
						<html:select name="rs" property="zzmm" styleId="zzmm"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmm"
								labelProperty="zzmm" />
						</html:select>

					</td>
					<td align="right">
						入党(团)时间：
					</td>
					<td>
						<html:text name="rs" property="rdsj" />
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						户口所在地：
					</td>
					<td colspan="5">
						<html:select name="rs" property="hkshen" styleId="hkshen" onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="hkshi" styleId="hkshi"
							onchange="loadXian('hkshi','hkxian')" disabled="true">
							<html:options collection="hkshiList" property="shidm" labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="hkxian" styleId="hkxian" disabled="true">
							<html:options collection="hkxianList" property="xiandm" labelProperty="xianmc" />
						</html:select>
					</td>
					<td align="center" rowspan="4">
						<button type="button" class="button2" onclick="updategrzp(<bean:write name="rs" property="xh"/>)">
							上传照片
						</button>
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						入学前的户籍所在地：
					</td>
					<td colspan="5">
						<html:select name="rs" property="syshen" styleId="syshen" onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
						</html:select>
						<html:select name="rs" property="syshi" styleId="syshi"
							onchange="loadXian('hkshi','hkxian')" disabled="true">
							<html:options collection="syshiList" property="shidm" labelProperty="shimc" />
						</html:select>
						<html:select name="rs" property="syxian" styleId="syxian" disabled="true">
							<html:options collection="syxianList" property="xiandm" labelProperty="xianmc" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
				<!-- end -->
				<tr height="30">
					<td align="right">
						有何特长：
					</td>
					<td>
						<html:text name="rs" property="tc" />
					</td>
					<td align="right">
						学生证号：
					</td>
					<td>
						<html:text name="rs" property="xszh" />
					</td>
					<td align="right">
						身份证号：
					</td>
					<td>
						<html:text name="rs" property="sfzh" />
					</td>
				</tr>
				<!-- 贵州大学 -->
				<logic:equal name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						家庭住址：
					</td>
					<td colspan="3">
						<html:text name="rs" property="jtzz" style="width:100%" />
					</td>
					<td align="right">
						联系电话：
					</td>
					<td>
						<html:text name="rs" property="lxdh" />
					</td>
				</tr>
				</logic:equal>
				<!-- 非贵州大学 -->
				<logic:notEqual name="xxdm" value="10657">
				<tr height="30">
					<td align="right">
						家庭住址：
					</td>
					<td colspan="3">
						<html:text name="rs" property="jtzz" style="width:100%" />
					</td>
					<td align="right">
						联系电话：
					</td>
					<td>
						<html:text name="rs" property="lxdh" />
				</td>			
				</tr>			
				<tr height="30">
					<td align="center">
						是否受过
						<br>
						军事训练
					</td>
					<td align="center">
						<html:select name="rs" property="sfjsxl" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<td align="center">
						受训内容
						<br>
						及时间
					</td>
					<td colspan="2">
						<html:textarea name="rs" property="sxnr" style="width:100%"
							rows="4" />
					</td>
					<td align="right">
						奖惩情况：
					</td>
					<td>
						<html:textarea name="rs" property="jcqk" style="width:100px"
							rows="4" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- 贵州大学 -->
				<logic:equal name="xxdm" value="10657">
				<tr>
					<td align="center">
						是否受过
						<br>
						军事训练
					</td>
					<td align="left">
						<html:select name="rs" property="sfjsxl" style="width:80px">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
					<td align="center" rowspan="2">
						受训内容
						<br>
						及时间
					</td>
					<td colspan="2" rowspan="2">
						<html:textarea name="rs" property="sxnr" style="width:100%"
							rows="4" />
					</td>
					<td align="right" rowspan="2">
						奖惩情况：
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="jcqk" style="width:100px"
							rows="4" />
					</td>
				</tr>
				<tr>
					<td align="center">
						登记类型
					</td>
					<td align="left">
						<html:select property="lx" style="" onchange="">
							<html:options collection="djlxList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>	
				</logic:equal>
				<tr height="30">
					<td align="center">
						在校表现：
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="zxbx" rows="5"
							style="width:100%" />
					</td>
				</tr>
				<!-- 非贵州大学 -->
				<logic:notEqual name="xxdm" value="10657">
				<tr height="30">
					<td align="center">
						家庭主要
						<br>
						成员
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtzycy" rows="5"
							style="width:100%" />
					</td>
				</tr>
				</logic:notEqual>
				<!-- end -->
				<!-- 贵州大学 -->
				<logic:equal name="xxdm" value="10657">			
					<tr height="30">
						<td align="center" rowspan="4">
							家庭主要
							<br>
							成员
						</td>
						<td>
							与本人关系
						</td>
						<td>
							姓名
						</td>
						<td colspan="3">
							工作单位
						</td>
						<td>
							联系电话
						</td>
					</tr>
					<tr>
						<td>
							${rs.jtcy1_gx }&nbsp;
						</td>
						<td>
							${rs.jtcy1_xm }&nbsp;
						</td>
						<td colspan="3">
							${rs.jtcy1_gzdz }&nbsp;
						</td>
						<td>
							${rs.jtcy1_lxdh1 }&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							${rs.jtcy2_gx }&nbsp;
						</td>
						<td>
							${rs.jtcy2_xm }&nbsp;
						</td>
						<td colspan="3">
							${rs.jtcy2_gzdz }&nbsp;
						</td>
						<td>
							${rs.jtcy2_lxdh1 }&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							${rs.jtcy3_gx }&nbsp;
						</td>
						<td>
							${rs.jtcy3_xm }&nbsp;
						</td>
						<td colspan="3">
							${rs.jtcy3_gzdz }&nbsp;
						</td>
						<td>
							${rs.jtcy3_lxdh1 }&nbsp;
						</td>
					</tr>
				</logic:equal>	
				<tr height="30">
					<td align="center">
						本人简历
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="grjl" rows="15"
							style="width:100%" />
					</td>
				</tr>
				<tr height="30">
					<td rowspan="2" align="center">
						身高
						<br>
						（厘米）
					</td>
					<td rowspan="2">
						<html:text name="rs" property="sg" />
					</td>
					<td rowspan="2" align="center">
						体重
						<br>
						（千克）
					</td>
					<td rowspan="2">
						<html:text name="rs" property="tz" />
					</td>
					<td rowspan="2" align="center">
						视力
					</td>
					<td align="right">
						右眼：
					</td>
					<td>
						<html:text name="rs" property="slright" style="width:100px" />
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						左眼：
					</td>
					<td>
						<html:text name="rs" property="slleft" style="width:100px" />
					</td>
				</tr>
				<tr height="30">
					<td align="center" colspan="2">
						家庭及个人病史情况
					</td>
					<td colspan="5">
						<html:textarea name="rs" property="jtjgrbs" rows="6"
							style="width:100%" />
					</td>
				</tr>
				<!-- begin 骆嘉伟 2009/3/30 -->
				<logic:equal value="11647" name="xxdm" scope = "session">
				<tr height="30">
					<td align="center" colspan="2">
						家庭意见
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="jtyj" rows="6"
							style="width:100%" />
					</td>
				</tr>
				</logic:equal>
				<!-- end 骆嘉伟 2009/3/30 -->
			</table>
			<div class="buttontool" align="center">
	
						<button type="button" class="button2" onclick="savetheinfo();" style="width:80px" >提交</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" type="reset" style="width:80px">关闭</button>
			</div>
			</logic:empty>

			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
                        alert("提交成功！");
                   </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
                        alert("提交失败！请检查是否重复提交");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
