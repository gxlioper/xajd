<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script>
		function save(){
			refreshForm('gwfb.do?method=gwfbsh&act=save');
		}
	</script>
</head>
<body>
	<html:form action="/gwfb.do?method=xwgwfb" method="post">
		<input type="hidden" id="path" name="path" value="${path}"/>
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" class="formlist" id="rsT">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>招聘公司</th>
					<td>
						${rs.yrdwmc}
					</td>
					<th>岗位名称</th>
					<td>
						${rs.gwdm}
						<input type="hidden" id="gwdm" name="save_gwdm" value="${rs.gwdm}"/>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						${rs.xn}
					</td>
					<th>年度</th>
					<td>
						${rs.nd}
					</td>
				</tr>
				<tr>
					<th>工作开始日期</th>
					<td>
						${rs.gzksrq}
					</td>
					<th>工作结束日期</th>
					<td>
						${rs.gzjsrq}
					</td>
				</tr>
				<tr>
					<th>招聘人数</th>
					<td height="22" align="left">
						${rs.xyrs}
						<input type="hidden" name="save_sqsyrs" id="sqsyrs" value="${rs.xyrs}"/>
					</td>
					<th>联系电话</th>
					<td>
						${rs.lxdh}
					</td>	
				</tr>
				<tr>
					<th>计酬方式</th>
					<td>
						${rs.jcfs}
					</td>
					<th>报酬标准</th>
					<td>
						${rs.jybcbz}
					</td>
				</tr>
				<tr>
					<th>面试时间</th>
					<td>
						${rs.mssj}
					</td>
					<th>面试地点</th>
					<td>
						${rs.msdd}
					</td>
				</tr>
				<tr>
					<th>工作时间</th>
					<td>
						${rs.gzsj}
					</td>							
					<th>工作地点</th>
					<td>
						${rs.gzdd}
					</td>						
				</tr>
				<tr>
					<th>工作内容</th>
					<td colspan="3">
						${rs.gznr}
					</td>
				</tr>		
				<tr>
					<th>招聘要求</th>
					<td colspan="3">
						${rs.gwtsyq}
					</td>
				</tr>								
				<tr>
					<th>备注</th>
					<td colspan="3">
						${rs.bz}
					</td>
				</tr>	
				<tr>					
					<th>审核结果</th>
					<td colspan="3">
						<html:select name="rs" property="save_shjg" styleId="shjg">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学生处意见</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_xgbyj" cols="60" rows="3" styleId="xscyj" onblur="chLeng(this,'150')"></html:textarea>
					</td>
				</tr>			
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
							<logic:equal value="yes" name="writeAble">
							<button type="button"  id="buttonSave" onclick="save();return false;">
								保存
							</button>
							</logic:equal>
							<button type="button"  id="buttonClose" onclick="Close();return false;">
								关闭
							</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>

	<logic:present name="result">
		<intput type="hidden" id="message" value="${message}"/>
		<script>
			alert(document.getElementById('message').value);
			if(window.dialogArguments){
		 		window.dialogArguments.document.getElementById('search_go').onclick();
		 	}
			Close();
		</script>
	</logic:present>
</body>
</html>
