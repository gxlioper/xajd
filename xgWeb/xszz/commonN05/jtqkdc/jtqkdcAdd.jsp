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
		<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		<html:form action="/jtqkdc.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 家庭情况调查 - 家庭情况信息 - 增加
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/jtqkdc.do?method=jtqkdcAdd" />
				<fieldset>
					<legend>
						学生家庭情况维护
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									学生家庭情况信息维护
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="readonly"
									       styleId="xh" 
									       onkeypress="autoFillStuInfo(event.keyCode,this);" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="button2" 
									id="buttonFindStu">
									选择
								</button>
							</td>
							<td align="right">
								入学前户口：
							</td>
							<td align="left">
								<html:select property="save_rxqhkxz" styleId="rxqhkxz">
									<html:option value=""></html:option>
									<html:option value="城镇">城镇</html:option>
									<html:option value="农村">农村</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<td align="right">
								家庭人口数：
							</td>
							<td align="left">
								<html:text name="rs" property="save_jtrks" styleId="jtrks" maxlength="2" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>(人)
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<td align="right">
								毕业学校：
							</td>
							<td align="left">
								<html:text name="rs" property="save_byxx" styleId="byxx" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" disabled="true" />
							</td>
							<td align="right">
								孤残：
							</td>
							<td align="left">
								<html:radio property="save_sfgc" name='rs' value="是">是</html:radio>
								<html:radio property="save_sfgc" name='rs' value="否">否</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
							<td align="right">
								单亲：
							</td>
							<td align="left">
								<html:radio property="save_sfdq" name='rs' value="是">是</html:radio>
								<html:radio property="save_sfdq" name='rs' value="否">否</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
							<td align="right">
								双亲：
							</td>
							<td align="left">
								<html:radio property="save_sfsq" name='rs' value="是">是</html:radio>
								<html:radio property="save_sfsq" name='rs' value="否">否</html:radio>
							</td>								
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
							<td align="right">
								离异：
							</td>
							<td align="left">
								<html:radio property="save_sfly" name='rs' value="是">是</html:radio>
								<html:radio property="save_sfly" name='rs' value="否">否</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right">
								学制：
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>
							<td align="right">
								烈士子女：
							</td>
							<td align="left">
								<html:radio property="save_sflszn" name='rs' value="是">是</html:radio>
								<html:radio property="save_sflszn" name='rs' value="否">否</html:radio>
							</td>							
						</tr>
						<tr>
							<td align="right">
								电子邮箱：
							</td>
							<td align="left">
								<html:text name='rs' property="dzyx" styleId="dzyx" disabled="true" />
							</td>
							<td align="right">
								家庭收入来源：
							</td>
							<td align="left">
								<html:text name='rs' property="save_jtsrly" styleId="jtsrly" maxlength="50"/>
							</td>							
						</tr>
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<td align="right">
								家庭人均年收入：
							</td>
							<td align="left">
								<html:text name='rs' property="save_jtrjnsr" styleId="jtrjnsr" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="10"/>(元)
							</td>						    
						</tr>
						<tr>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
							<td align="right">
								家庭年收入：
							</td>
							<td align="left">
								<html:text name='rs' property="save_jtnsr" styleId="jtnsr" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="10"/>(元)
							</td>							
						</tr>
						<tr>
							<td align="right">
								本学年已获资助情况：
							</td>
							<td align="left">
								<html:text name="rs" property="save_yhzzqk" styleId="yhzzqk" maxlength="300"/>
							</td>
							<td align="right">
								人均月收入：
							</td>
							<td align="left">
								<html:text name="rs" property="save_jtrjysr" styleId="jtrjysr" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="6"/>
							</td>	
						</tr>
						<tr>
                           <td align="right">
								家庭遭受自然灾害情况：
							</td>
							<td align="left">
								<html:text name="rs" property="save_zszrzhqk" styleId="zszrzhqk" maxlength="300"/>
							</td>	
							<td align="right">
								家庭遭受突发意外事件：
							</td>
							<td align="left">
								<html:text name="rs" property="save_zstfywsj" styleId="zstfywsj" maxlength="300"/>
							</td>
                        </tr>
						<tr>
							<td align="right">
								家庭成员因残疾、<br/>
								年迈而劳动能力弱情况：
							</td>
							<td align="left">
								<html:text name="rs" property="save_ldnlrqk" styleId="ldnlrqk" maxlength="300"/>
							</td>
							<td align="right">
								家庭成员失业情况：
							</td>
							<td align="left">
								<html:text name="rs" property="save_jtcysyqk" styleId="jtcysyqk" maxlength="300"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								家庭欠债情况：
							</td>
							<td align="left">
								<html:text name="rs" property="save_jtqzqk" styleId="jtqzqk" maxlength="300"/>
							</td>
							<td align="right">
								其他情况：
							</td>
							<td align="left">
								<html:text name="rs" property="save_jtqtqk" styleId="jtqtqk" maxlength="300"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="width:100%">
								<table style="width:100%" class="tbstyle">
									<thead>
										<tr>
											<td>
												姓名
											</td>
											<td>
												出生日期
											</td>
											<td>
												与学生关系
											</td>
											<td>
												工作（学习）单位
											</td>
											<td>
												职业
											</td>
											<td>
												月收入（元）
											</td>
											<td>
												年收入（元）
											</td>
											<td>
												健康状况
											</td>
										</tr>
									</thead>
									<tr>
											<td>
												<div> &nbsp;${rs.jtcy1_xm}</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy1_nl}</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy1_gx}</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy1_xm">
												<html:text name="rs" property="save_jtcy1_gzdwmc" styleId="jtcy1_gzdwmc" style="width:80px" maxlength="100"/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy1_zy}</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy1_xm">
												<html:text name="rs" property="save_jtcy1_ysr" styleId="jtcy1_ysr" style="width:80px" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy1_xm">
												<html:text name="rs" property="save_jtcy1_nsr" styleId="jtcy1_nsr" style="width:80px" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy1_xm">
												<html:text name="rs" property="save_jtcy1_jkzk" styleId="jtcy1_jkzk" style="width:80px" maxlength="100"/>
												</logic:notEmpty>
												</div>
											</td>
									</tr>
									<tr>
											<td>
												<div> &nbsp;${rs.jtcy2_xm}</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy2_nl}</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy2_gx}</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy2_xm">
												<html:text name="rs" property="save_jtcy2_gzdwmc" styleId="jtcy2_gzdwmc" style="width:80px" maxlength="100"/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy2_zy}</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy2_xm">
												<html:text name="rs" property="save_jtcy2_ysr" styleId="jtcy2_ysr" style="width:80px" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy2_xm">
												<html:text name="rs" property="save_jtcy2_nsr" styleId="jtcy2_nsr" style="width:80px" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy2_xm">
												<html:text name="rs" property="save_jtcy2_jkzk" styleId="jtcy2_jkzk" style="width:80px" maxlength="100"/>
												</logic:notEmpty>
												</div>
											</td>
									</tr>
									<tr>
											<td>
												<div> &nbsp;${rs.jtcy3_xm}</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy3_nl}</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy3_gx}</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy3_xm">
												<html:text name="rs" property="save_jtcy3_gzdwmc" styleId="jtcy3_gzdwmc" style="width:80px" maxlength="100"/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;${rs.jtcy3_zy}</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy3_xm">
												<html:text name="rs" property="save_jtcy3_ysr" styleId="jtcy3_ysr" maxlength="10" style="width:80px" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy3_xm">
												<html:text name="rs" property="save_jtcy3_nsr" styleId="jtcy3_nsr" maxlength="10" style="width:80px" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
												</logic:notEmpty>
												</div>
											</td>
											<td>
												<div> &nbsp;
												<logic:notEmpty name="rs" property="jtcy3_xm">
												<html:text name="rs" property="save_jtcy3_jkzk" styleId="jtcy3_jkzk" maxlength="100" style="width:80px"/>
												</logic:notEmpty>
												</div>
											</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="right">
								民政部门详细通讯地址：
							</td>
							<td align="left" colspan="3">
								<html:text name="rs" property="save_mzbtxdz" styleId="mzbtxdz" maxlength="100" style="width:100%"/>
							</td>							
						</tr>
						<tr>
							<td align="right">
								民政部门邮政编码：
							</td>
							<td align="left">
								<html:text name='rs' property="save_mzbyb" styleId="mzbyb" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="6"/>
							</td>
							<td align="right">
								民政部门联系电话：
							</td>
							<td align="left">
								<html:text name="rs" property="save_mzblxdh" styleId="mzblxdh" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
							</td>
						</tr>						
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="saveData('jtqkdc.do?method=jtqkdcAdd&type=save','xh')"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
