<%@ page language="java" contentType="text/html; charset=GBK"%>
				<div id="stuImg" class="open_img" style="margin-left:0px;margin-right:5px;margin-top: 0px;height: 130px">
					<img style="width:100px;height:130px" id="xszp"
						src="xsxx_xsgl.do?method=showPhoto&xh=${xh}"
						border="0">
				</div>
				<div id ="zpscbtn" align="center">
					<button  type="button"  onclick="showZpscDiv();" >
					上传照片
					</button>
				</div>	
				<div id="stuGkImg" class="open_img" style="margin-left:0px;margin-right:5px;margin-top: 5px;height: 130px">
					<img style="width:100px;height:130px" id="xsgkzp"
						src="xsxx_xsgl.do?method=showGkPhoto&xh=${xh}"
						border="0">
				</div>
				<div id ="gkzpscbtn" align="center">
					<button  type="button"  onclick="showgkZpscDiv();" >
					高考照片
					</button>
				</div>				
				
				
											<!-- 上传照片 -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">注：请上传jpg，gif，png，bmp 格式的文件，限 1 M 。</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>	
			
											<!-- 上传高考照片 -->
			<div id="addGkPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuGkPic" name="stuGkPic" style="width:90%"
										   onchange='uploadStuGkPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">注：请上传jpg，gif，png，bmp 格式的文件，限 1 M 。</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>	