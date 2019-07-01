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
		<script type="text/javascript">
			function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function tipsAndSave(){
			BatAlert.showTips("正在保存，请稍后！");
			saveData('xysf_dyjsgl.do?method=dyjsViewAndModi&doType=save','zgh');
		}
	</script>
</head>
<body>
	
	<html:form action="xysf_dyjsgl.do" method="post">
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>
					<logic:equal value="view" name="operation">德育讲师团管理 - 讲师单个查看</logic:equal>
					<logic:equal value="modi" name="operation">德育讲师团管理 - 讲师单个修改</logic:equal>
				</a>
			</p>
		</div>
		
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		
		<div class="tab"/>
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>教师信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>职工号
					</th>
					<td align="left" width="30%">
						<html:text property="save_zgh" styleId="zgh" readonly="readonly" value="${rs.zgh}"/>
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
						民族
					</div>
				</th>
				<td>
					${rs.mzmc }
				</td>
				
				<th>
					<div align="right">
						出生日期
					</div>
				</th>
				<td>
					${rs.csrq }
				</td>
			</tr>
	
			<tr align="left" style="height:22px">
							<th align="right">
								备注
								<br />
								<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_bz' style="width:99%" value="${rs.bz}"
									rows='8' onblur="checkLen(this,400)"/>
							</td>
			</tr>
			</tbody>
			
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			     	   <logic:equal value="modi" name="operation">
			          		<button type="button" name="提交" 
			          		onclick="tipsAndSave()">保存</button>
			         	</logic:equal>
			            <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
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
