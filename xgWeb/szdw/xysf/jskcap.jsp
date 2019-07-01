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
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src='js/szdw/jskcap.js'></script>
	<script type="text/javascript">
	function autoFillTeaInfo(cod) {
		if (cod == 13) {
			var url = $('url').value;
			document.forms[0].action = url;
			document.forms[0].submit();
		}
	}
	jQuery(function(){
		onShow();
	})
	
</script>
</head>
<body >
	<html:form action="xysf_dyjsgl.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>德育讲师团管理 - 德育讲师课程安排</a>
			</p>
		</div>
		
		<input type="hidden" id="doType" value="" />
		<input type="hidden" name="pkValue" value="${param.pkValue }" />
		
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr style="height: 22px">
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
					<input type="hidden" name="zgh" value="${rs.zgh }"/>
					${rs.zgh }
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
		</div>
		<br/><br/>
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
				 	<th colspan="6"><span>讲师授课安排</span></th>
				 </tr>
				</thead>
				<thead style="height: 10px">
					<tr>
						<td align="center" nowrap="nowrap" style='width: 10%'>
							选定删除行
						</td>
						<td align="center" nowrap="nowrap" style='width: 17%'>
							授课时间
						</td>
						<td align="center" nowrap="nowrap" style='width: 20%'>
							授课地点
						</td>
						<td align="center" nowrap="nowrap" style='width: 18%'>
							主题
						</td>
						<td align="center" nowrap="nowrap" style='width: 15%'>
							活动规模
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
			          	<button type="button" name="提交" onclick="save('xysf_dyjsgl.do?method=dyjskcap&doType=save');">提 交</button>
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
	if (window.dialogArguments
			&& window.dialogArguments.document.all("search_go")) {
		window.dialogArguments.document.getElementById('search_go').click();
	}
</script>
	</logic:present>
</body>
</html>
