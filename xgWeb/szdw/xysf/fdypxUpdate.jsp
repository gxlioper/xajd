<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type='text/javascript' src='js/szdw/fdypxUpdate.js'></script>
	<script type="text/javascript">
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function tipsAndSave(){
			var zgh = $('zgh').value;
			if(zgh != ""){			
				BatAlert.showTips("正在保存，请稍后！");
				saveData('xysf_dyjsgl.do?method=addDyjs&doType=save','zgh');
			}else{
				alert("请将带\"*\"的项目输入完整！");
			}
		}
		
		jQuery(function(){
			onShow();
		})
	</script>
</head>
<body>
	<html:form action="xysf_fdypx.do" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		
		<input type="hidden" id="doType" value=""/>
		<input type="hidden" id="url" name="url"
			value="xysf_fdypx.do?method=fdypxUpdate" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>辅导员信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>职工号
					</th>
					<td align="left" width="30%">
					   <logic:equal value="true" name="isSzdw">
					   	<html:text property="zgh" styleId="zgh" readonly="true" value="${rs.zgh}"></html:text>
					   </logic:equal>
					   <logic:equal value="false" name="isSzdw">
							<html:text property="zgh" styleId="zgh" readonly="true"
								onchange="checkXhExists($('getStuInfo').value);"
								onkeypress="autoFillTeaInfo(event.keyCode)" />
							<button type="button" onclick="showTopWin('/xgxt/xysf_dyjsgl.do?method=getTeaInfo',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
					   </logic:equal>
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
			</tbody>
			
		</table>
		<br/>
		<div align="right">
			<p>
				<input  value="+"
					onclick="trAdd('flag1','add','numAdd1','rzqk');"
					style="width: 20px" />
				<input  value="-" onclick="trDel('flag1', 'delRow1');" style="width: 20px" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;
				<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
					onblur="trAdd('flag1','madd','numAdd1','rzqk')"/>
				&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;
				<input type="text" name="numDel" id="numDel1" style="width: 20px"
					onblur="trDelAll('flag1','numDel1')"/>
				&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
			</div>
			<table align="center" style="width: 100%" id="rsT"
				class="formlist">
				<!-- 打印时第一行不显示- -->
				<thead>
				 <tr>
				 	<th colspan="6"><span>辅导员培训信息</span></th>
				 </tr>
				</thead>
				<thead style="height: 10px">
					<tr>
						<td align="center" nowrap="nowrap" style='width: 10%'>
							选定删除行
						</td>
						<td align="center" nowrap="nowrap" style='width: 17%'>
							培训名称
						</td>
						<td align="center" nowrap="nowrap" style='width: 20%'>
							培训地点
						</td>
						<td align="center" nowrap="nowrap" style='width: 17%'>
							培训时间
						</td>
						<td align="center" nowrap="nowrap" style='width: 18%'>
							培训内容
						</td>
						<td align="center" nowrap="nowrap" style='width: 20%'>
							备注<font color="red">(限制在200字内)</font>
						</td>
					</tr>
				</thead>
				<tbody align="center" id="flag1">
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<logic:equal value="yes" name="writeAble">
			          		<button type="button" name="提交" onclick="save('xysf_fdypx.do?method=fdypxSave&doType=save');">提 交</button>
			          	</logic:equal>
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
