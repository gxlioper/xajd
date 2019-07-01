<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsqjs/js/xmsqjs.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				auotSetCanCheck();
			});
		</script>
	</head>
	<body>
		<input type="hidden" name="xh" id="xh" value="${TxhdXmsqJsForm.xh }"/>
	
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
											<td style='width: 40%'>
												活动时间
											</td>
											<td style='width: 35%'>
												活动地点
											</td>
											<td style='width: 5%'>
												剩余名额
											</td>
										</tr>
									</thead>
									<tbody id="wsq">
										<logic:present name="xmsqInfoList">
											<logic:iterate name="xmsqInfoList" id="info" indexId="i">
												<logic:equal value="0" name="info" property="sfsq">
													<tr name="checkxm">
														<td>
															<input name="checkbox" type="checkbox" value="${info.xmdm }"/>
														</td>
														<td>
															${info.xmmc }
														</td>
														<td>
															${info.hdsj }
														</td>
														<td>
															${info.hddd }
														</td>
														<td name="syme">
															${info.syme }
														</td>
													</tr>
												</logic:equal>
											</logic:iterate>
										</logic:present>
									</tbody>
									
									<tbody id="ysq" style="display:none;">
										<logic:present name="xmsqInfoList">
											<logic:iterate name="xmsqInfoList" id="info" indexId="i">
												<logic:equal value="1" name="info" property="sfsq">
													<tr>
														<td>
															<input type="checkbox" value="${info.xmdm }" disabled="disabled"/>
														</td>
														<td>
															${info.xmmc }
														</td>
														<td>
															${info.hdsj }
														</td>
														<td>
															${info.hddd }
														</td>
													</tr>
												</logic:equal>
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
