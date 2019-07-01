<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="pjpyycsfwh.do" method="post">
		<input type="hidden" name="act" value="save"/>
		<script>
			function checkData(){
				var pskhcjbl = document.getElementById('pskhcjbl').value;
				var xykhcjbl = document.getElementById('xykhcjbl').value;
				var jdkhcjbl = document.getElementById('jdkhcjbl').value;
				if(parseInt(pskhcjbl) >100 ||parseInt(xykhcjbl) >100 ||parseInt(jdkhcjbl) >100 ){
					alert('各种比例均不能大于100！');
					return false;
				}
				refreshForm('/xgxt/pjpy_ycsf_setzhszcpbl.do');
			}
			
		</script>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 综合素质测评比例设置</a>
			</p>
		</div>

			
			<div class="tab">
			<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>综合素质测评比例设置</span>
					</th>
				</tr>
			</thead>
			<tbody>
					<!-- 厦门理工 -->
					<logic:present name="showXmlgxy">
					<tr height="35">
						<th align="right" width="50%">
							${rs.pskhcjbl }
						</th>
						<td width="50%">
							<html:text property="pskhcjbl" styleId="pskhcjbl" style="width: 60%" onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)'></html:text>%
						</td>
					</tr>
					<tr height="35">
						<th align="right">
							${rs.xykhcjbl }
						</th>
						<td>
							<html:text property="xykhcjbl" styleId="xykhcjbl" style="width: 60%" onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)'></html:text>%
						</td>
					</tr>
					<tr height="35">
						<th align="right">
							${rs.jdkhcjbl }
						</th>
						<td>
							<html:text property="jdkhcjbl" styleId="jdkhcjbl" style="width: 60%" onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)'></html:text>%
						</td>
					</tr>
					</logic:present>
					<!-- END -->
					<logic:equal value="11078" name="xxdm">
							<tr height="35">
								<th align="right" width="50%">
									${rs.pskhcjbl }
								</th>
								<td width="50%">
									<html:text property="xyfbl" styleId="pskhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
							<tr height="35">
								<th align="right">
									${rs.xykhcjbl }
								</th>
								<td>
									<html:text property="bxfbl" styleId="xykhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
							<tr height="35">
								<th align="right">
									${rs.jdkhcjbl }
								</th>
								<td>
									<html:text property="xwbxfbl" styleId="jdkhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
							<tr height="35">
								<th align="right">
									${rs.zhbxfbl }
								</th>
								<td>
									<html:text property="tcbxfbl" styleId="jdkhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
					</logic:equal>
					<logic:notEqual value="11078" name="xxdm">
					<!-- 常州信息 -->
						<logic:equal value="12317" name="xxdm">
							<tr height="35">
						<th align="right" width="50%">
							${rs.pskhcjbl }
						</th>
						<td width="50%">
							<html:text property="pskhcjbl" styleId="pskhcjbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
						</td>
					</tr>
					<tr height="35">
						<th align="right">
							${rs.xykhcjbl }
						</th>
						<td>
							<html:text property="xykhcjbl" styleId="xykhcjbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
						</td>
					</tr>
					<tr height="35">
						<th align="right">
							${rs.jdkhcjbl }
						</th>
						<td>
							<html:text property="jdkhcjbl" styleId="jdkhcjbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<fieldset>
							<legend>
								德育成绩项目比例
							</legend>
							<table width="100%"  class="tbstyle" align="center">
							<tr height="35">
<%--								<td align="right" width="25%">--%>
<%--									德育基础分--%>
<%--								</td>--%>
<%--								<td width="25%">--%>
<%--									<html:text property="dyjcf" styleId="dyjcf" style="width: 60px" onkeyup="checkInputData(this)"></html:text>分--%>
<%--								</td>--%>
								<td align="right" width="25%">
									德育基础分比例
								</td>
								<td width="25%">
									<html:text property="dyjcfbl" styleId="dyjcfbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
								<td align="right">
									宿舍生活规范分比例
								</td>
								<td>
									<html:text property="dyssgffbl" styleId="dyssgffbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
							<tr height="35">
								
								<td align="right" width="25%">
									德育附加分比例
								</td>
								<td width="25%">
									<html:text property="dyfjfbl" styleId="dyfjfbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
								
								<td align="right" width="25%">
									
								</td>
								<td width="25%">
								
								</td>
							</tr>
							</table>
						</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<fieldset>
							<legend>
								智育成绩项目比例
							</legend>
							<table width="100%"  class="" align="center">
							<tr height="35">
								<th align="right" width="25%">
									考试科目平均成绩比例
								</th>
								<td width="25%">
									<html:text property="zykskmcjbl" styleId="zykskmcjbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
								<th align="right" width="25%">
									考查科目平均成绩比例
								</th>
								<td width="25%">
									<html:text property="zykckmcjbl" styleId="zykckmcjbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
							<tr height="35">
								<th align="right">
									智育附加分比例
								</th>
								<td>
									<html:text property="zyfjfbl" styleId="zyfjfbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
								<td align="right">
									
								</td>
								<td>
									
								</td>
							</tr>
							</table>
						</fieldset>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<fieldset>
							<legend>
								体育成绩项目比例
							</legend>
							<table width="100%"  class="" align="center">
							<tr height="35">
								<th align="right" width="25%">
									体育课成绩比例
								</th>
								<td width="25%">
									<html:text property="tycjbl" styleId="tycjbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
								<th align="right" width="25%">
									课外体育锻炼成绩比例
								</th>
								<td width="25%">
									<html:text property="tykwtydlbl" styleId="tykwtydlbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
							</tr>
							<tr height="35">
								<th align="right">
									体育附加分比例
								</th>
								<td>
									<html:text property="tyfjfbl" styleId="tyfjfbl" style="width: 60px" onkeyup="checkInputData(this)"></html:text>%
								</td>
								<td align="right">
									
								</td>
								<td>
									
								</td>
							</tr>
							</table>
						</fieldset>
						</td>
					</tr>
					</logic:equal>
				<logic:notEqual value="12317" name="xxdm">	
				<logic:notPresent name="showXmlgxy">
					<tr height="35">
						<th align="right" width="50%">
							${rs.pskhcjbl }
						</th>
						<td width="50%">
							<html:text property="pskhcjbl" styleId="pskhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
						</td>
					</tr>
					<tr height="35">
						<th align="right">
							${rs.xykhcjbl }
						</th>
						<td>
							<html:text property="xykhcjbl" styleId="xykhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
						</td>
					</tr>
					<tr height="35">
						<th align="right">
							${rs.jdkhcjbl }
						</th>
						<td>
							<html:text property="jdkhcjbl" styleId="jdkhcjbl" style="width: 60%" onkeyup="checkInputData(this)"></html:text>%
						</td>
					</tr>
					</logic:notPresent>
					</logic:notEqual>
					</logic:notEqual>
					
				</tbody>
				<tfoot>
				<tr height="35">
					<td align="center" colspan="2">
					 <div class="btn">
						<button class="" onclick="checkData();">
							保 存
						</button>
						&nbsp;&nbsp;
<%--						<button class="button2" onclick="refreshForm('pjpy_ycsf_setzhszcpbl.do')"--%>
<%--							style="width: 60px">--%>
<%--							取 消--%>
<%--						</button>--%>
<%--						&nbsp;&nbsp;--%>
<%--						<button class="button2" onclick="showTopWin('pjpy_gzdx_cfxzsz.do',400,300)">--%>
<%--							处分限制设置--%>
<%--						</button>--%>
</div>
					</td>
				</tr>
				</tfoot>
			</table>
			</div>
		<logic:equal value="yes" name="result">
			<script>
				alert('保存成功!');
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert('保存失败!');
			</script>
		</logic:equal>
		</html:form>
	</body>
