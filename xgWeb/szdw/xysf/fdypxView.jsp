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
	<script type="text/javascript" src="js/szdw/jskcap.js"></script>
	<script type="text/javascript">
		
		function tipsAndSave(){
			BatAlert.showTips("正在保存，请稍后！");
			saveData('xysf_fdypx.do?method=fdypxView&doType=save','zgh');
		}
	</script>
</head>
<body>
	
	<html:form action="xysf_dyjsgl.do" method="post">

	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>
					<logic:equal value="view" name="doType">辅导员培训信息管理 - 培训信息单个查看</logic:equal>
					<logic:equal value="modi" name="doType">辅导员培训信息管理 - 培训信息修改</logic:equal>
				</a>
			</p>
		</div>
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		<input type="hidden" name="save_id" value="${param.pkValue }"/>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>
							培训信息
						</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>职工号
					</th>
					<td align="left" width="30%">
						<html:text property="save_zgh" styleId="zgh" readonly="true" value="${rs.zgh}"/>
					</td>
					
				<th width="16%">
					<div align="right">
						姓名
					</div>
				</th>
				<td width="34%">
					${rs.xm }
				</td>
			</tr>
		
			<tr>
				<th>
					<div align="right">
						性别
					</div>
				</th>
				<td>
					${rs.xb }
				</td>
				<th>
					<div align="right">
						职务
					</div>
				</th>
				<td>
					${rs.zwmc }
				</td>
			</tr>
			
			<tr>
				<th>
					<div align="right">
						负责部门
					</div>
				</th>
				<td>
					${rs.bmmc }
				</td>
				
				<th>
					<div align="right">
						 学历
					</div>
				</th>
				<td>
					${rs.xl }
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						培训名称
					</div>
				</th>
				<td>
					<input type="text" name="save_pxmc" maxlength="50" value="${rs.pxmc }"/>
				</td>
				<th>
					<div align="right">
						 培训地点
					</div>
				</th>
				<td>
					<input type="text" name="save_pxdd" maxlength="100"  value="${rs.pxdd }"/>
				</td>
			</tr>
			<tr>
				<th>
					<div align="right">
						培训时间
					</div>
				</th>
				<td>
					<input type="text" onclick="time(this.id)" readonly="readonly" onblur="dateFormatChg(this)"  id="pxsj" name="save_pxsj" value="${rs.pxsj }"/>
				</td>
				<th>
					
				</th>
				<td>
					
				</td>
			</tr>
			<tr align="left" style="height:22px">
				<th align="right">
					培训内容
					<br />
					<font color="red">(限制在50字内)</font>
				</th>
				<td colspan="7">
					<html:textarea property='save_pxnr' style="word-break:break-all;width:99%" value="${rs.pxnr}"
						rows='4' onblur="checkLen(this,50)"/>
				</td>
			</tr>
			<tr align="left" style="height:22px">
				<th align="right">
					备注
					<br />
					<font color="red">(限制在200字内)</font>
				</th>
				<td colspan="7">
					<html:textarea property='save_bz' style="word-break:break-all;width:99%" value="${rs.bz}"
						rows='4' onblur="checkLen(this,200)"/>
				</td>
			</tr>
			</tbody>
			
				<tfoot>
			      <tr>
			        <td colspan="4">
			        <logic:notEqual value="view" name="doType">
			        <div class="bz">"<span class="red">*</span>"为必填项</div>
			       </logic:notEqual>
			          <div class="btn">
			          <logic:equal value="modi" name="doType">
			          		<button type="button" name="提交" onclick="tipsAndSave()">保存</button>
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
