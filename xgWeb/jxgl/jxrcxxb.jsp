<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<html:form action="/ArmyCalendarSave" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>军训管理 - 信息维护 - 军训日程安排</a>
				</p>
			</div>

			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<div class="tab">
				  <table width="100%"  border="0" class="formlist">
					 <thead>
			    	<tr>
			        	<th colspan="4"><span>军训日程安排</span></th>
			        </tr>
			    	</thead>
			    	 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				           <div class="btn">
				           <logic:equal name="doType" value="add">
								<button type="button" 
									onclick="if(pdsjks()){Savedata('nj-nd-kssj-jssj-apnr-kssjH-kssjM-kssjS-jssjH-jssjM-jssjS','ArmyCalendarSave.do?type=add')}">
										保 存
								</button>
								</logic:equal>
								<logic:equal name="doType" value="modi">
								<button type="button"  id="buttonSave"
									onclick="if(pdsjks()){Savedata('nj-nd-kssj-jssj-apnr-kssjH-kssjM-kssjS-jssjH-jssjM-jssjS','ArmyCalendarSave.do?type=modi')}">
										保 存
								</button>
								</logic:equal>
								<button type="button"  onclick="Close();return false;" >
									关 闭
								</button>
				          </div></td>
				      </tr>
				    </tfoot>
			    	
					<tbody>
					<tr>
						<td align="right">
							<font color="red">*</font>年级：
						</td>
						<td align="left">
							<html:select name="rs" property="nj"  style="width:120px" styleId="nj">
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>年度：
						</td>
						<td align="left">
							<html:select name="rs" property="nd"  style="width:120px" styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>开始时间：
						</td>
						<td align="left">
							<html:text name="rs" property="kssj" onclick="return showCalendar('kssj','y-mm-dd');" readonly="true" size="10" styleId="kssj"></html:text>
							&nbsp;<html:text name="rs" property="kssjH" size="2"  styleId="kssjH" maxlength="2"></html:text>:
							<html:text name="rs" property="kssjM" size="2"  styleId="kssjM" maxlength="2"></html:text>:
							<html:text name="rs" property="kssjS" size="2" styleId="kssjS" maxlength="2"></html:text>
						</td>
						<td align="right">
							<font color="red">*</font>结束时间：
						</td>
						<td align="left">
						 	<html:text name="rs" property="jssj" onclick="return showCalendar('jssj','y-mm-dd');" readonly="true" size="10" styleId="jssj"></html:text>
							&nbsp;<html:text name="rs" property="jssjH" size="2" styleId="jssjH" maxlength="2"></html:text>:
							<html:text name="rs" property="jssjM" size="2"   styleId="jssjM" maxlength="2"></html:text>:
							<html:text name="rs" property="jssjS" size="2"  styleId="jssjS" maxlength="2"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							日程摘要：
						</td>
						<td colspan="3" align="left">
							<html:text name="rs" property="rczy" style="width:90%" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>军训安排：
						</td>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="apnr" style="width:90%"
								rows="3" styleId="apnr" onblur="chLeng(this,100);"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							具体内容：
						</td>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="jtrr" style="width:90%"
								rows="７" styleId="apnr" onblur="chLeng(this,400);"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3" align="left">
							<html:textarea name="rs" property="bz" style="width:90%" rows="3"
							onblur="chLeng(this,50);"></html:textarea>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败!");
			</script>
		</logic:equal>
		<logic:equal value="exist" name="result">
			<script>
				alert("该日期已经安排日程!");
			</script>
		</logic:equal>
	</body>
</html>
