<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				onShow("zxdk_query");
			});
		</script>
		<style type="text/css">
			.hiddenfont{width:155px;height:15px;display:block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
		</style>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id" styleId="id"/>
		
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款申请</span>
							</th>
						</tr>
					</thead>
				<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				</table>
				<logic:notEqual value="10511" name="xxdm">
					<div class="tab"  id="content" ></div>
				</logic:notEqual>
				<logic:equal value="10511" name="xxdm">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款信息<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;单位（元）</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">贷款期数</th>
							<td width="34%" id='dkqxtd'>
							  ${zxdkXyddkForm.dkqx}
							</td>
							<th width="16%">贷款期限（月）</th>
							<td width="34%">
								 ${zxdkXyddkForm.jhr1}
							</td>
						</tr>
						<tr  id = "tableshow" >
							<td colspan="4"  width="100%">
								<table id="table" width="100%">
									<tr width="100%">
										<th style="text-align:center" width="15%" >学年</th>
										<th style="text-align:center" >住宿费贷款额</th>
										<th style="text-align:center" >学费贷款额</th>
										<th style="text-align:center" >生活费贷款额</th>
										<th style="text-align:center" >年住宿费应缴额</th>
										<th style="text-align:center" >年学费应缴额</th>
									</tr>
									<logic:iterate id="i" name="nddkList">
									<tr class ='showtr'>
										<td>
											${i.xn}
										</td>
										 <td>
										 	${i.nzsfdk}
										 </td>
										 <td>
										 	${i.nxfdk}
										 </td>
										 <td>
										 	${i.nshfdk}
										 </td>
										 <td>
										 	${i.nzsfyje}
										 </td>
										 <td>
										 	${i.nxfyje}
										  </td>
										</tr>
									</logic:iterate>
									
								</table>
							</td>
						</tr>
						<tr>
							<th width="16%">住宿费贷款总额</th>
							<td width="34%">
								 ${zxdkXyddkForm.zsfdks}
							</td>
							<th width="16%">学费贷款总额</th>
							<td width="34%">
								 ${zxdkXyddkForm.xfdks}
							</td>
						</tr>
						<tr>
							<th width="16%">生活费贷款总额</th>
							<td width="34%">
								${zxdkXyddkForm.shfdks}
							</td>
							<th width="16%">贷款总金额</th>
							<td width="34%">
								${zxdkXyddkForm.dkje}
							</td>
						</tr>
						<tr>
							<th width="16%">申请理由</th>
							<td width="84%" colspan="3">
								${zxdkXyddkForm.sqly}
							</td>
						</tr>
					</tbody>
				</table>
				</logic:equal>
					<table width="100%" border="0" class="formlist" style="position: relative;top: -6px;">
					<logic:notEqual value="10511" name="xxdm">
						<logic:equal value="12688" name="xxdm">
							<thead>
								<tr>
									<th colspan="4">
										<span>贷款其他信息</span>
									</th>
								</tr>
							</thead>
							<tbody>
						</logic:equal>
					    <tr >
							<th width="15%">合同编号</th>
							<td width="35%">
								${zxdkXyddkForm.htbh }
							</td>
							<th width="15%">申请时间</th>
							<td width="35%">
								${zxdkXyddkForm.sqsj }
							</td>
						</tr>
						<tr>
							<th width="15%">贷款银行</th>
							<td width="35%">
								${zxdkXyddkForm.yhmc }
							</td>
							<th width="15%">银行电话</th>
							<td width="35%">
								${zxdkXyddkForm.lxdh }
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr>						
								<th>
									贷款办理日期
								</th>
								<td>
									${zxdkXyddkForm.bldkrq}
								</td>
								<th>
									合同签订地点
								</th>
								<td>
									${zxdkXyddkForm.htdd}
								</td>
							</tr>
							<tr>
								<th>
									学院经办人
								</th>
								<td>
									${zxdkXyddkForm.xyjbr}
								</td>
							</tr>
						</logic:equal>
					</logic:notEqual>
					<logic:equal value="10511" name="xxdm">
					    <tr >
							<th width="16%">合同编号</th>
							<td width="34%">
								${zxdkXyddkForm.htbh }
							</td>
							<th width="16%">申请时间</th>
							<td width="34%">
								${zxdkXyddkForm.sqsj }
							</td>
						</tr>
						<tr >
							<th width="16%">贷款银行</th>
							<td width="34%">
								${zxdkXyddkForm.yhmc }
							</td>
							<th width="16%">银行电话</th>
							<td width="34%">
								${zxdkXyddkForm.lxdh }
							</td>
						</tr>
					</logic:equal>	
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zxdkXyddkForm.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					<logic:equal value="12688" name="xxdm">
						</tbody>
					</logic:equal>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>放贷记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">放贷学年</th>
										<th style="text-align: center;">凭证号</th>
										<th style="text-align: center;">放贷时间</th>
										<th style="text-align: center;">放款卡号</th>
										<th style="text-align: center;">放款金额(元)</th>
									</tr>
									<logic:iterate id="f" name="fdxxList">
										<tr>
											<td>${f.dkxn }</td>
											<td>${f.pzh }</td>
											<td>${f.fksj }</td>
											<td>${f.fkkh }</td>
											<td>${f.fkje }</td>
										</tr>
									</logic:iterate>
									<logic:empty name="fdxxList">
										<tr>
											<td colspan="5">
												无放贷记录！
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>续贷信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">续贷学年</th>
										<th style="text-align: center;">续贷金额(元)</th>
										<th style="text-align: center;">续贷理由</th>
									</tr>
									<logic:iterate id="x" name="xdsqList">
										<tr>
											<td>${x.htbh }</td>
											<td>${x.xdxn }
												<input type="hidden" name="ysqxn" value="${x.xdxn }"/>
											</td>
											<td>${x.xdje }</td>
											<td>
											 <label class="hiddenfont" title="${x.xdly }">${x.xdly }</label>
											</td>
										</tr>
									</logic:iterate>
									<logic:empty name="xdsqList">
										<tr>
											<td colspan="4">
												无续贷申请记录！
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
					<logic:present name="wyxx">
						<thead>
							<tr>
								<th colspan="4">
									<span>违约信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>违约详情</th>
								<td colspan="3">
									${wyxx.wyxq }
								</td>
							</tr>
							<tr>
								<th>备注</th>
								<td colspan="3">
									${wyxx.bz }
								</td>
							</tr>
						</tbody>
					</logic:present>
					
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
	
</html>