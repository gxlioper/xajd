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
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function saveHdsh(shlx){
		showTips('处理数据中，请等待......');
		var pk = $("pk").value;	
		var url = '/xgxt/zgddShgzHdgl.do?method=hdglSh&shlx='+shlx;
		refreshForm(url);
		$("buttonTg").disabled=true;
		$("buttonWtg").disabled=true;
	}
	
	function onShow(){
		var hddm = $("hddm").value;
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		
		dwr.engine.setAsync(false);
		getHdglDAO.getZdyZdz(hddm,xh,xn,xq,function (data){
			var hdnr1 = new Array();
			for(var i=0;i<data.length;i++){
				var zdz="";
				if(data[i].zdz !=""&&data[i].zdz!=null){
					zdz=data[i].zdz;
				}
				if (data[i].zdlx == "003"){
				
					var hdnr2 = new Array();
					
					hdnr2[0]=function(da){
						    return "<div align='right'>"+data[i].zdm+"：</div>";
						    }
						    
					hdnr2[1]=function(da){
						    return "<textarea type='text' style='width:100%' id='"
						    +data[i].zd
						    +"' name='"
						    +data[i].zd
						    +"'rows='5' onblur='chLeng(this,500)' disabled='true'>"
						    +zdz
						    +"</textarea>";	    	
						    }
					
					DWRUtil.addRows("hdnrarea",[''],hdnr2,{escapeHtml:false});
					
				}else{
					if(hdnr1.length == 0){
						if (data[i].zdlx == "002"){
							hdnr1[0]= document.createElement("div");
							hdnr1[0].align="right"
							hdnr1[0].innerHTML=data[i].zdm+"：";
							
							hdnr1[1]= document.createElement("input");
						    hdnr1[1].style.cursor="hand;";
							hdnr1[1].name=data[i].zd;
							hdnr1[1].id=data[i].zd;
							hdnr1[1].onclick=function(){gettime(this.id)};
							hdnr1[1].onblur=function(){dateFormatChg(this)}
							hdnr1[1].value=zdz;
							hdnr1[1].readOnly="ture";
						}else{
							hdnr1[0]= document.createElement("div");
							hdnr1[0].align="right"
							hdnr1[0].innerHTML=data[i].zdm+"：";
							
							hdnr1[1]= document.createElement("input");
							hdnr1[1].maxLength="20";
							hdnr1[1].name=data[i].zd;
							hdnr1[1].id=data[i].zd;
							hdnr1[1].value=zdz;
							hdnr1[1].readOnly="ture";
						}
					continue;
					}else{
						if (data[i].zdlx == "002"){
						
							hdnr1[2]= document.createElement("div");
							hdnr1[2].align="right"
							hdnr1[2].innerHTML=data[i].zdm+"：";
							
							hdnr1[3]= document.createElement("input");
							hdnr1[3].style.cursor="hand;";
							hdnr1[3].name=data[i].zd;
							hdnr1[3].id=data[i].zd;
							hdnr1[3].onclick=function(){gettime(this.id)};
							hdnr1[3].onblur=function(){dateFormatChg(this)}
							hdnr1[3].value=zdz;
							hdnr1[3].readOnly="ture";
						}else{
							hdnr1[2]= document.createElement("div");
							hdnr1[2].align="right"
							hdnr1[2].innerHTML=data[i].zdm+"：";
							
							hdnr1[3]= document.createElement("input");
							hdnr1[3].maxLength="20";
							hdnr1[3].name=data[i].zd;
							hdnr1[3].id=data[i].zd;		
							hdnr1[3].value=zdz;	
							hdnr1[3].readOnly="ture";
						}
						DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
						hdnr1 = new Array();
					}
				}
			}
			if(hdnr1.length != 0){
				hdnr1[2]="";
				hdnr1[3]="";
				DWRUtil.addRows("hdnrtext",['dd'],hdnr1);
			}
		});
		dwr.engine.setAsync(true);
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="onShow()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getHdglDAO.js'></script>
		<html:form action="/zgddShgzHdgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" />
					</a>
				</p>
			</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk"/>" />
				<input type="hidden" id="xn" name="xn"
					value="<bean:write name="rs" property="xn"/>" />
				<input type="hidden" id="xq" name="xq"
					value="<bean:write name="rs" property="xq"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>活动申请</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<div class="buttontool" id="btndiv">
											<logic:equal name="doType" value="view">
												<button class="" onclick="Close();return false;" id="buttonClose">
													关 闭
												</button>
											</logic:equal>
											<logic:notEqual name="doType" value="view">
												<logic:equal name="needXy" value="yes">
													<button class="" onclick="saveHdsh('tg');" id="buttonTg">
														审核通过
													</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="" onclick="saveHdsh('wtg');" id="buttonWtg">
														审核不通过
													</button>
												</logic:equal>
												<logic:equal name="needXy" value="no">
													<button class="" onclick="Close();return false;" id="buttonClose">
														关 闭
													</button>
												</logic:equal>
											</logic:notEqual>
										</div>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<td align="right" style="width:15%">
									学号
								</td>
								<td align="left" style="width:35%">
									<bean:write name="rs" property="xh" />
									<input type="hidden" id="xh" name="xh"
										value="<bean:write name="rs" property="xh"/>" />
								</td>
								<td align="right" style="width:15%">
									姓名
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									性别
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									民族
								</td>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
							</tr>

							<tr>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
								<td align="right">
									专业
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									班级
								</td>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
								<td align="right">
									活动名称
								</td>
								<td align="left">
									<bean:write name="rs" property="hdmc" />
									<input type="hidden" id="hddm" name="hddm"
										value="<bean:write name="rs" property="hddm"/>" />
								</td>
							</tr>
							<tr>
								<td align="right">
									学年
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
								<td align="right">
									学期
								</td>
								<td align="left">
									<bean:write name="rs" property="xqmc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									备注
								</td>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bz" disabled="true" rows="5"
										style="width:100%" />
								</td>
							</tr>
					<tr>
					<td colspan="4">
					<table width="100%" class="formlist" border="0">
						<tbody width="100%" class="" id="hdnrtext">
								<td style="width:15%"></td>
								<td style="width:35%"></td>
								<td style="width:15%"></td>
								<td></td>
							</tr>
						</tbody>
					</table>
					<table width="100%" class="formlist" border="0">
						<tbody width="100%" class="" id="hdnrarea">
							<tr>
								<td style="width:15%"></td>
								<td style="width:85%"></td>
							</tr>
						</tbody>
					</table>
					</td>
					</tr>
					<logic:equal name="doType" value="view">
					
								<logic:equal name="needXy" value="yes">
									<tr>
										<td style="width:15%" align="right">
											<bean:message key="lable.xsgzyxpzxy" />
											意见
										</td>
										<td style="width:85%" colspan="3">
											<html:textarea name="rs" property="xyyj" disabled="true"
												rows="5" style="width:100%" />
										</td>
									</tr>
								</logic:equal>
								<tr>
									<td style="width:15%" align="right">
										学校意见
									</td>
									<td style="width:85%" colspan="3">
										<html:textarea name="rs" property="xxyj" disabled="true"
											rows="5" style="width:100%" />
									</td>
								</tr>
							
					</logic:equal>
					<logic:notEqual name="doType" value="view">
						
								<logic:equal name="userType" value="xy">
									<logic:equal name="needXy" value="yes">
										<tr>
											<td style="width:15%" align="right">
												<bean:message key="lable.xsgzyxpzxy" />
												意见
											</td>
											<td style="width:85%" colspan="3">
												<html:textarea name="rs" property="xyyj" rows="5"
													style="width:100%" />
											</td>
										</tr>
									</logic:equal>
									<logic:equal name="needXy" value="no">
										<tr>
											<td style="width:15%" align="right">
												学校意见
											</td>
											<td style="width:85%" colspan="3">
												<html:textarea name="rs" property="xxyj" rows="5"
													style="width:100%" />
											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<logic:equal name="needXy" value="yes">
										<tr>
											<td style="width:15%" align="right">
												<bean:message key="lable.xsgzyxpzxy" />
												意见
											</td>
											<td style="width:85%" colspan="3">
												<html:textarea name="rs" property="xyyj" disabled="true"
													rows="5" style="width:100%" />
											</td>
										</tr>
									</logic:equal>
									<tr>
										<td style="width:15%" align="right">
											学校意见
										</td>
										<td style="width:85%" colspan="3">
											<html:textarea name="rs" property="xxyj" rows="5"
												style="width:100%" />
										</td>
									</tr>
								</logic:notEqual>
							</tbody>
						</table>
						
					</logic:notEqual>
					</tbody>
						</table>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("审核成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("审核失败！");
				    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
