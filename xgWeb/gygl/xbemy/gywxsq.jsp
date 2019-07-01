<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
			<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	
	<body onload="clin();">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a> 公寓管理 - 申请 - 公寓维修申请</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/XsgyglDispatch.do?method=gywxSq" method="post">
			<input type="hidden" id="url" name="url"
				value="/XsgyglDispatch.do?method=gywxSq" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="disableEle" name="disableEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
			<input type="hidden" id="fzbmV" name="fzbmV" />
			<logic:equal value="isNotStu" name="isNotStu">
				<div align="center">
					<font color="red">只有学生用户可以申请!</font>
				</div>
			</logic:equal>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="ssbh" name="ssbh" value="${rs.ssbh}" />
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>公寓维修申请</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th align="right">
							<font color="red">*</font>楼栋名称：
						</th>
						<td align="left">
							<html:text name="rs" property="ldmc" readonly="true"></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>学年：
						</th>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>寝室号：
						</th>
						<td align="left">
							<html:text name="rs" property="qsh" readonly="true"></html:text>
							<input type="hidden" id="ssbh" name="ssbh" value="${rs.ssbh}" />
						</td>


						<th align="right">
							<font color="red">*</font>学期：
						</th>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>报修时间：
						</th>
						<td align="left">
							<html:text name="rs" property="bxsj" readonly="true"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('bxsj','y-mm-dd');"
								style="cursor:hand " />
						</td>
						<th align="right">
							报修人：
						</th>
						<td align="left">
							<html:text name="rs" property="bxr" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						
						<th align="right">
							姓名：
						</th>
						<td align="left">
							<html:text name="rs" property="xm" readonly="true"></html:text>
						</td>
						<th align="right">
						    联系方式：
						</th>
						<td align="left">
						    <html:text name="rs" property="lxfs" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>维修内容：<br>
							<限100字内>
						</th>
						<logic:present name="isCSMZZYJSXY">
							<td align="left">
								<html:select name="rs" property="wxnr" style="width:150px"
									styleId="wxnr" onchange="getGyWxNrFzBmList()">
									<html:option value=""></html:option>
									<html:options collection="wxnrList" property="nrdm"
										labelProperty="nrmc" />
								</html:select>
							</td>

							<th align="right">
								负责部门：
							</th>
							<td align="left">
								<html:select name="rs" property="fzbm" style="width:150px"
									styleId="fzbm">
									<html:options collection="fzbmList" property="fzbmdm"
										labelProperty="fzbmmc" />
								</html:select>
							</td>
						</logic:present>
						<logic:notPresent name="isCSMZZYJSXY">
							<td align="left" colspan="3">
								<textarea rows="5" cols="80" name="wxnr"></textarea>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<th align="right">
							备注：<br>
							<限100字内>
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="80" name="bz" ></textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
										<button id="buttonSave" 
											onclick="to_save()"
											style="width: 80px">
											提 交
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>

		</html:form>
		<logic:equal value="yes" name="done">
			<script type="text/javascript">
	     alert("申请成功！");
	    </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script type="text/javascript">
	     alert("申请失败！");
	    </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
     function clin(){
        if($("ssbh").value==""){
          alert("当前用户还没有入住！");
          return false;
        }
     }
     function to_save(){
        if(IsNoEmpty('xn-xq-ldmc-qsh-bxsj-wxnr')){
          if($("wxnr")){
             if($("wxnr").value.length>100){
                alert("维修内容字数不能大于100！")
                return false;
             }
          }
          if($("bz")){
             if($("bz").value.length>100){
                alert("备注字数不能大于100！")
                return false;
             }
          }
          refreshForm('/xgxt/XsgyglDispatch.do?method=gywxSq&doType=save');
          $("buttonSave").disabled=true;
        }
     }
  </script>
</html>
