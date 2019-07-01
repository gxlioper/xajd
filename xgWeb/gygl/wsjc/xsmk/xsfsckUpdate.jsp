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
	</script>
</head>
<body>
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
						<span>学生信息卫生分信息</span><p class="floatright normal">学年：${xn }&nbsp;&nbsp;&nbsp;&nbsp;　学期：${xqmc }　&nbsp;&nbsp;</p>
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
			<thead>
				<tr>
					<th colspan="4"><span>具体分数项目</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
		      <td colspan="4" class="tdbox">
				<table width="100%" class="dateline">
	              <thead>
	                  <tr>
	                  	<logic:equal value="周" name="jczq">
							<td>检查周次</td>
						</logic:equal>
						<logic:notEqual value="周" name="jczq">
							<td>检查时间</td>
						</logic:notEqual>
						<td>原因</td>
						<td>性质</td>
						<td>分数</td>
						<td>录入人</td>
					</tr>
				  </thead>
				  <tbody>
				  	<logic:iterate id="fs" name="xsfsInfo">
				  		<tr>
					  		<logic:equal value="周" name="jczq">
								<td>第${fs.jczc }周</td>
							</logic:equal>
							<logic:notEqual value="周" name="jczq">
								<td>${fs.jcsj }</td>
							</logic:notEqual>
				  			<td>${fs.xmmc }</td>
				  			<td>${fs.xmxz }</td>
				  			<td>${fs.fs }</td>
				  			<td>${fs.lrr }</td>
				  		</tr>
				  	</logic:iterate>
				  </tbody>
				</table>
			  </td>
		     </tr>
			</tbody>
			<tfoot>
			  <tr>
		        <td colspan="4">
		          <div class="btn">
					  <button name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		
		</div>
	</html:form>
</body>
</html>
