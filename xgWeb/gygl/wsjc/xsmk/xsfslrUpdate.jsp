<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/commanFunction.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getGyglWsjcDAO.js"></script>
	<script type="text/javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/gygl/xsfslrUpdate.js" defer="defer"></script>
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
<body >
	<html:form action="/commWsjc" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		
		<input type="hidden" name="xh" value="${rs.xh }"/>
		<input type="hidden" name="xn" value="${xn }"/>
		<input type="hidden" name="xq" value="${xq }"/>
		<input type="hidden" name="nd" value="${nd }"/>
		<input type="hidden" name="lrr" value="${userName }"/>
		<input type="hidden" name="lrsj" value="${lrsj }"/>
		<input type="hidden" name="jczq" value="${jczq }"/>
		
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>学生寝室卫生分信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					学号
				</th>
				<td align="left" width="30%">
				  	${rs.xh }
				</td>
				<th width="20%">
					姓名
				</th>
				<td width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>学年</th>
				<td>${xn }</td>
				<th>学期</th>
				<td>${xqmc }</td>
			</tr>
			<tr>
				<th>年度</th>
				<td>${nd }</td>
				<logic:equal name="jczq" value="周">
					<th>检查周次</th>
					<td><input type="text" readonly="readonly" name="jczc" value="${jczc }" /></td>
				</logic:equal>
				<logic:equal name="jczq" value="日">
					<th>检查日期</th>
					<td><input type="text" readonly="readonly" name="jcsj" value="${jcsj }" /></td>
				</logic:equal>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xb" />
				</th>
				<td>
					${rs.xymc }
				</td>
				
				<th>
					专业
				</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			
			<tr>
				<th>
					班级
				</th>
				<td>
					${rs.bjmc }
				</td>
				
				<th>
					楼栋
				</th>
				<td>
					${rs.ldmc}
				</td>
				
			</tr>
			<tr>
				<th>
					寝室号
				</th>
				<td>
					${rs.qsh }
				</td>
				<th>
					床位号
				</th>
				<td>
					${rs.cwh }
				</td>
			</tr>
			</tbody>
			
		</table>
		<br/>
		<div align="right">
			<p>
				<input type="button" value="+"
					onclick="trAdd('flag1','add','numAdd1','rzqk');"
					style="width: 20px" />
				<input type="button" value="-" onclick="trDel('flag1', 'delRow1');" style="width: 20px" />
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
						<td align="center" nowrap="nowrap" style='width: 10%'>
							性质
						</td>
						<td align="center" nowrap="nowrap" style='width: 70%'>
							原因
						</td>
						<td align="center" nowrap="nowrap" style='width: 10%'>
							分数
						</td>
					</tr>
				</thead>
				<tbody align="center" id="flag1">
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="6">
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button name="保存" onclick="save('/xgxt/commWsjc.do?method=xsfslrUpdate&doType=save');">保存</button>
			          	</logic:notEqual>
			          		<button onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		
		<div style="display: none">
			<select id="add_select">
				<logic:iterate id="map" name="addXmList">
					<option value="${map.xmdm}">${map.xmmc}</option>
				</logic:iterate>
			</select>
			<select id="reduce_select">
				<logic:iterate id="map" name="reduceXmList">
					<option value="${map.xmdm}">${map.xmmc}</option>
				</logic:iterate>
			</select>
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
