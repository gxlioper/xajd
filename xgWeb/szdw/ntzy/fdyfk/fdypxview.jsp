<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	<html:form action="ntzy_fdyfk.do" method="post">
	
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>
			<logic:equal value="view" name="operation">当前位置：思政队伍 - 辅导员培训信息单个查询</logic:equal>
			<logic:equal value="modi" name="operation">当前位置：思政队伍 - 辅导员培训信息单个修改</logic:equal>
			</a>
		</p>
	</div>

	<input type="hidden" name="pkValue" value="${param.pkValue }" />
	<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>教师信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						职工号
					</th>
					<td align="left" width="30%">
						<input type="hidden" name="save_zgh" value="${rs.zgh }"/>
						${rs.zgh }
					</td>
					
				<th width="16%">
					<div align="right">
						姓名
					</div>
				</th>
				<td width="34%" align="left">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						性别
					</div>
				</th>
				<td align="left">
					${rs.xb }
				</td>
				
				<th>
					<div align="right">
						出生日期
					</div>
				</th>
				<td align="left">
					${rs.csrq }
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						民族
					</div>
				</th>
				<td align="left">
					${rs.mzmc }
				</td>
				
				<th>
					<div align="right">
						籍贯
					</div>
				</th>
				<td align="left">
					${rs.sfmc }
				</td>
			</tr>
			
			<tr>
				<th>
					<div align="right">
						所在部门
					</div>
				</th>
				<td align="left">
					${rs.bmmc }
				</td>
				
				<th>
					<div align="right">
						 学历
					</div>
				</th>
				<td align="left">
					${rs.xl }
				</td>
				
			</tr>
			
			<tr>
				<th>
					<div align="right">
						职务
					</div>
				</th>
				<td>
					${rs.zwmc }
				</td>
				
				<th>
					<div align="right">
						所属工作组
					</div>
				</th>
				<td>
					${rs.fdyzmc }
				</td>
			</tr>
			</tbody>
			</table>
			<br/>
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4">
						<span>培训信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th align="right" width="20%">
						<font color="red">*</font>培训范围
					</th>
					<td align="left" width="30%">
						<html:select property="save_pxfw" styleId="pxfw" value="${rs.pxfw}">
							<html:option value="">--请选择--</html:option>
							<html:options collection="pxfwList" property="xmmc" labelProperty="xmmc"/>
						</html:select>
					</td>

					<th width="16%">
						<div align="right">
							<font color="red">*</font>培训类型
						</div>
					</th>
					<td width="34%" align="left">
						<html:select property="save_pxlx" styleId="pxlx" value="${rs.pxlx }">
							<html:option value="">--请选择--</html:option>
							<html:options collection="pxlxList" property="xmmc" labelProperty="xmmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="20%">
						<font color="red">*</font>开始时间
					</th>
					<td align="left" width="30%">
						<html:text property="save_kssj" styleId="kssj" readonly="true" value="${rs.kssj}"
						onclick="showCalendar('kssj','y年mm月dd日');"></html:text>
					</td>
					<th width="16%">
						<div align="right">
							<font color="red">*</font>结束时间
						</div>
					</th>
					<td width="34%" align="left">
						<html:text property="save_jssj" styleId="jssj" readonly="true" value="${rs.jssj}"
						onclick="showCalendar('jssj','y年mm月dd日');"></html:text>
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						培训项目
					</th>
					<td>
						<html:text property="save_pxxm"  value="${rs.pxxm }"/>
					</td>
					
					<th align="right">
						学校审核
					</th>
					<td>
						${rs.xxsh}
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						具体内容
						<br />
						<font color="red">(限制在400字内)</font>
					</th>
					<td colspan="3">
						<html:textarea property='save_jtnr' style="width:99%" rows='6' value="${rs.jtnr}"
							onblur="checkLen(this,400)"/>
					</td>
				</tr>
				<tr align="left" style="height: 22px">
					<th align="right">
						备注
						<br />
						<font color="red">(限制在800字内)</font>
					</th>
					<td colspan="3">
						<html:textarea property='save_bz' style="width:99%" rows='6' value="${rs.bz}"
							onblur="checkLen(this,800)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			        <logic:equal value="modi" name="operation">
			          		<button type="button" onclick="saveDataShowTips('ntzy_fdyfk.do?method=fdypxview&doType=save','','正在处理数据，请稍候！');">
								保存
							</button>
			         	</logic:equal>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
