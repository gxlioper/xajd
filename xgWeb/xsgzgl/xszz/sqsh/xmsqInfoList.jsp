<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#titleTr td").last().css("display","none")
				checkCondition();
				
				//项目说明<br/>转换为换行显示在页面上
				jQuery("#xmList>table>tbody>tr").each(function(){
					var title = jQuery(this).attr("title");
					title = title.replaceAll("<br/>","\n");
					jQuery(this).attr("title",title);

			    });
			});
			
		</script>
	</head>
	<body>
		<input type="hidden" name="xh" id="xh" value="${xszzSqshForm.xh }"/>
	
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectSqxm(this,'wsq');"><span>未申请</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectSqxm(this,'ysq');"><span>已申请</span></a></li>
	      </ul>
	    </div>
		<div class="tab"  >
			<table class="formlist">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="selectXm();">
									确定
								</button>
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td>
							<div class="con_overlfow" style="width:100%;height:240px;overflow-x:hidden;overflow-y:auto;vertical-align: top;" id="xmList">
								<table class="dateline" width="100%">
									<thead>
										<tr id="titleTr">
											<td width="2%"></td>
											<td style='width: 20%'>
												项目名称
											</td>
											<td style='width: 10%'>
												金额
											</td>
											<td style='width: 20%'>
												项目评定周期
											</td>
											<td id="xztj" style='width: 50%'>
												限制条件
											</td>
											<td id="sqsj" style='width: 50%'>
												申请时间
											</td>
										</tr>
									</thead>
									<tbody id="wsq">
										<logic:present name="xmsqInfoList">
											<logic:iterate name="xmsqInfoList" id="info" indexId="i">
												<tr title="${info.xmsm }">
													<td>
														<input type="checkbox" value="${info.xmdm }" onclick="checkJdsz(this);"/>
													</td>
													<td>${info.xmmc }</td>
													<td><input type="hidden" name="jesfxssq" value="${info.jesfxssq }"/>${info.je }<logic:equal name="info" property="jesfxssq" value="1"><font color='red'>(调)</font></logic:equal></td>
													<td>${info.pdzq }</td>
													<td></td>
													<td></td>
												</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
									
									<tbody id="ysq" style="display:none;">
										<logic:present name="wsqList">
											<logic:iterate name="wsqList" id="info" indexId="i">
												<tr title="${info.xmsm }">
													<td>
														<input type="checkbox" value="${info.xmdm }" disabled="disabled"/>
													</td>
													<td>${info.xmmc }</td>
													<td>${info.je }<logic:equal name="info" property="jesfxssq" value="1"><font color='red'>(调)</font></logic:equal></td>
													<td>${info.pdzq }</td>
													<td>${info.sqsj }</td>
												</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
									
								</table>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
