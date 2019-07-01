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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<base target="_self">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<html:form action="/csmz_sztz.do?method=xxwh_tzcgcj" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>素质拓展 - 信息维护 - 素质拓展成绩</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("保存成功！");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url"
					value="/csmz_sztz.do?method=xxwh_tzcgcj" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>添加素质拓展成绩</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<logic:notEqual value="view" name="doType">
										<div class="bz">
											"
											<span class="red">*</span>"为必填项
										</div>
									</logic:notEqual>
									<div class="btn">
										<logic:notEqual value="view" name="doType">
											<button name="提交" id="buttonSave"
												onclick="if(IsNoEmpty('xh-xmmc-jxlb')){refreshForm('/xgxt/csmz_sztz.do?method=xxwh_tzcgcj&act=save');this.disabled=true}">
												提 交
											</button>
										</logic:notEqual>
										<button name="关闭" onclick="Close();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th align="right" width="12%">
									<font color="red">*</font>学号
								</th>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"
										onkeypress="if(event.keyCode == 13) return false;" />
									<logic:notEqual value="modi" name="doType">
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											id="buttonFindStu" class="btn_01">
											选择
										</button>
									</logic:notEqual>
								</td>
								<th align="right" width="18%">
									<font color="red">*</font>拓展活动(项目)
								</th>
								<td align="left">
									<input type="hidden" name="xmdm"
										value="<bean:write name="xmdm" scope="request"/>">
									<html:text name='rs' property="xmmc" styleId="xmmc"
										readonly="true" />
									<button onclick="tzxmInfoTo()" class="btn_01">
										选择
									</button>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									姓名
								</th>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
								<th align="right">
									学年
								</th>
								<td align="left">
									<bean:write name='rs' property="xn" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									性别
								</th>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<th align="right">
									学期
								</th>
								<td align="left">
									<bean:write name='rs' property="xq" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									年级
								</th>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
								<th align="right">
									所属科目
								</th>
								<td align="left">
									<bean:write name='rs' property="kmmc" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<th align="right">
									项目所属
									<bean:message key="lable.xsgzyxpzxy" />
									(部门)
								</th>
								<td>
									<bean:write name='rs' property="bmmc" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									专业
								</th>
								<td align="left">
									<bean:write name='rs' property="zymc" />
								</td>
								<th align="right">
									活动(项目)级别
								</th>
								<td align="left">
									<bean:write name='rs' property="xmjb" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									班级
								</th>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<th align="right">
									<logic:equal value="12104" name="xxdm">
							       指导老师
							    </logic:equal>
									<logic:notEqual value="12104" name="xxdm">
							       主办单位
							    </logic:notEqual>
								</th>
								<td align="left">
									<bean:write name='rs' property="zzdw" />
								</td>
							</tr>
							<tr align="left">
								<th align="right">
									参与角色
								</th>
								<td align="left">
									<html:select name="rs" property="cyjs"
										style="width:90px;background-color:#FFFFFF" styleId="cyjs">
										<html:option value="——">——</html:option>
										<html:options collection="cjsfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th align="right">
									主办时间
								</th>
								<td align="left">
									<bean:write name='rs' property="zbsj" />
								</td>
							</tr>
							<tr align="left">
								<th align="right">
									<font color="red">*</font>获奖奖项
								</th>
								<td align="left">
									<html:select name="rs" property="jxlb"
										style="width:90px;background-color:#FFFFFF" styleId="jxlb"
										onchange="refreshForm('/xgxt/csmz_sztz.do?method=xxwh_tzcgcj')">
										<html:option value=""></html:option>
										<html:options collection="jxjbList" property="jxid"
											labelProperty="jxm" />
									</html:select>
								</td>
								<th align="right">
									${fsclin}
								</th>
								<td align="left">
									<bean:write name='rs' property="xf" />
								</td>
							</tr>
							<logic:present name="showdybz">
								<tr align="left">
									<th align="right">

									</th>
									<td align="left">

									</td>
									<th align="right">
										是否打印
									</th>
									<td align="left">
										<html:select name="rs" property="sfdy"
											style="width:90px;background-color:#FFFFFF" styleId="sfdy"
											onchange="numChekPrint()">
											<html:option value="否">否</html:option>
											<html:option value="是">是</html:option>
										</html:select>
									</td>
								</tr>
							</logic:present>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:equal value="yes" name="done">
				<script type="text/javascript">
			      alert('保存成功！');
			      	Close();
	             dialogArgumentsQueryChick();
			  </script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script type="text/javascript">
			      alert('保存失败！');
			      	Close();
	             dialogArgumentsQueryChick();
			  </script>
			</logic:equal>
		</html:form>
	</body>
	<script type="text/javascript">
function tzxmInfoTo(){
    if($('xh').value==""){
        alert("学号不能为空！");
        return false;
    }else{
        var url = "/xgxt/csmz_sztz.do?method=tzxm_xxcx&xh="+$('xh').value+"&url="+$('url').value;        
        showTopWin(url,750,550);             
    }
}
function numChekPrint(){
     var sfdy = "";
     var xh = "";
     var xmid = "";
     sfdy = ($("sfdy"))?$("sfdy").value:"";
     xh = $("xh").value;
     xmid = $("xmdm").value;
     if(sfdy=="是"){
        getSztzData.getTzxmDyCout(xh,xmid,function(str){
              if(parseInt(str)>=8){
                 alert("该成果所在学年、科目中，该生打印成果记录数已达到8条，不能再选择打印\n\n可先在\"(计划项目)成果认证 - 打印成果调整\"功能中进行调整后，再提交！")
                 $("buttonSave").disabled=true;
              }
        });
     }else{
        if($("buttonSave")){
           if($("buttonSave").disabled==true){
              $("buttonSave").disabled = false;
           }
        }
     }


}
</script>
</html>
