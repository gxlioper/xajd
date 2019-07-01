<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function zcwjupdateok(){
	    var url ="/xgxt/zcwjupdate.do?act=update";
		document.forms[0].action = url;
		document.forms[0].submit();   
    }	
    
    function updateokturn(){
        var url2 ="/xgxt/zcwjfb.do";
        document.forms[0].action = url2;
		document.forms[0].submit();
    }
    
    function zpxxreturnmesssave(){   
		 document.forms[0].action = "/xgxt/zpxxReturnMess.do?doType=save";
		 document.forms[0].submit();
	}
    
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 就业招聘 - 单位意见反馈</a>
			</p>
		</div>

		<html:form action="/savezcwj.do" method="post">
			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单位意见反馈</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<th width="16%">
								单位名称
							</th>
							<td width="30%">
								<logic:equal name="updategsmc" value="no">
									<html:text name="rs" property="gsmc" style="width:260px"
										readonly="true" />
								</logic:equal>
								<logic:equal name="updategsmc" value="ok">
									<html:text name="rs" property="gsmc" style="width:260px" />
								</logic:equal>
							</td>


							<th width="16%">
								单位性质
							</th>
							<td width="30%">
								<html:select name="rs" property="dwxz" styleId="dwxz"
									style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="dwxzdm2List" property="dwxz"
										labelProperty="dwxz" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>调查条目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table>
									<tbody>
						<tr>
							<td>
								1、贵单位对本校开设之专业课程的满意度
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								2、贵单位认为我校所授之内容符合业界实际需求
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								3、我校毕业生所学技能和工作实际衔接
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								4、我校毕业生表现符合贵公司的期许
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								5、我校毕业生的表达与沟通能力
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								6、我校毕业生的电脑运用能力
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								7、我校毕业生的创意及思考能力
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								8、我校毕业生的外语会话能力
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								9、我校毕业生的独立思考与分析能力
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="非常满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="一般"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="非常不满意"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								10、贵单位对我校毕业生总体满意度
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="非常满意" checked="checked" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="满意" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="一般" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="不满意" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="非常不满意" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="red">*默认值为非常满意</font>
							</td>
						</tr>
					</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>详细信息反馈</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								意见反馈标题
							</th>
							<td colspan="3">
								<html:text name="rs" property="yjfkbt" style="width:100%" />
							</td>
						</tr>
						<tr>
							<th>
								意见反馈内容
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="yjfknr" style="width:100%"
									rows="26" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="zpxxreturnmesssave();">
										提 交
									</button>
									<button type="reset">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="ok">
				<script>
                      alert("提交成功!");
                    </script>
			</logic:equal>
			<logic:equal name="insert" value="no">
				<script>
                      alert("提交失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>

</html>
