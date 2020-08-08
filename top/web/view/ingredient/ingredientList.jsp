<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/azzara.min.css">

<script>
	var dbIng;
	$(document).ready(function() {
		$('#add-ingredient').DataTable({
			"pageLength" : 12,

		});

	});

	function ing(ingID) {
		console.log(ingID);
		var data = {
			'ingID' : ingID
		};
		$.ajax({
			//url : "adjust.top?ingID=" + ingID,
			url : "adjust.top",
			data : data,
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				console.log(data)

				$(".modal-body #ingID").val(data.ingID);
				$(".modal-body #ingCategory").val(data.ingCategory);
				$(".modal-body #ingName").val(data.ingName);
				$(".modal-body #ingPrice").val(data.ingPrice);
				$(".modal-body #ingUnit").val(data.ingUnit);
				$(".modal-body #ingBrand").val(data.ingBrand);
				$(".modal-body #ingType").val(data.ingType);
				$(".modal-body #ingWeight").val(data.ingWeight);
				$(".modal-body #ingRegDate").val(data.ingRegDate);
				$(".modal-body #ingLink").val(data.ingLink);
				$(".modal-body #ingLinkCount").val(data.ingLinkCount);
				$(".modal-body #ingImgName").val(data.ingImgName);

				console.log(data.ingID + "?⑥닔?덉뿉");

			}

		});
	};

	function del(ingID) {

		var ds = encodeURI(ingID);
		location.replace('ingdel.top?' + 'ingID=' + encodeURI(ds, "UTF-8"));

	}
	function up(ingID) {
		alert(ingID);

		var ds = encodeURI(ingID);
		location.replace('uupdate.top?' + 'ingID=' + encodeURI(ds, "UTF-8"));

	}

	function insert() {
		location.href = "insert.top";
	}
</script>
<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<div class="d-flex align-items-center">
					<h4 class="card-title">?щ즺</h4>
					<button class="btn btn-primary btn-round ml-auto"
						data-toggle="modal" data-target="#addRowModal">
						<i class="fa fa-plus"></i> Add Row
					</button>
				</div>
			</div>
			<!--**************************MODAL insert******************************************-->
			<div class="modal fade" id="addRowModal" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header no-bd">
							<h5 class="modal-title">
								<span class="fw-mediumbold"> ?ы뭹</span> <span class="fw-light">
									?깅줉</span>
							</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p class="small">愿由ы븷 ??곸쓣 ?깅줉?섏꽭??</p>
							<form action="insert.top" method="post" accept-charset="utf-8">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group form-group-default">
											<label>?앹옄??/label> <input name="ingID" type="text"
												class="form-control" placeholder="ID" required>
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>移댄뀒怨좊━</label> <input name="ingCategory" type="text"
												class="form-control" placeholder="Category" required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>?대쫫</label> <input name="ingName" type="text"
												class="form-control" placeholder="Name" required>
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>媛寃?/label> <input name="ingPrice"
												type="number" class="form-control" placeholder="0" required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>?⑥쐞</label> <input name="ingUnit" type="text"
												class="form-control" placeholder="Unit" required>
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>釉뚮옖??/label> <input name="ingBrand"
												type="text" class="form-control" placeholder="Brand"
												required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>醫낅쪟</label> <input name="ingType" type="text"
												class="form-control" placeholder="Type" required>
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>臾닿쾶</label> <input id="ingWeight" name="ingWeight"
												type="number" placeholder="0" class="form-control">
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>留곹겕</label> <input name="ingLink" type="text"
												class="form-control" placeholder="Link" required>
										</div>
									</div>

									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>?대?吏</label> <input name="ingImgName" type="text"
												class="form-control" placeholder="Name" required>
										</div>
									</div>

								</div>
								<div class="modal-footer no-bd">
									<button id="addRowButton1" class="btn btn-primary"
										type="submit">Add</button>
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Close</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- ********************MODAL end******************************************* -->
			<!--**************************MODAL******************************************-->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header no-bd">
							<h5 class="modal-title">
								<span class="fw-mediumbold"> ?섏젙</span> <span class="fw-light">
									?섍린 </span>
							</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p class="small">?щ즺 ?뺣낫瑜??섏젙?섏꽭??/p>
							<form action="up.top" method="post" accept-charset="utf-8">
								<div class="row">
									<div class="col-sm-12">
										<div class="form-group form-group-default">
											<label>?앹옄??/label> <input name="ingID" id="ingID"
												type="text" class="form-control" readonly>
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>移댄뀒怨좊━</label> <input name="ingCategory"
												id="ingCategory" type="text" class="form-control" readonly>

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>?대쫫</label> <input id="ingName" name="ingName"
												type="text" class="form-control">
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>媛寃?/label> <input id="ingPrice"
												name="ingPrice" type="number" class="form-control">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>?⑥쐞</label> <input id="ingUnit" name="ingUnit"
												type="text" class="form-control">
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>釉뚮옖??/label> <input id="ingBrand"
												name="ingBrand" type="text" class="form-control">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>醫낅쪟</label> <input id="ingType" name="ingType"
												type="text" class="form-control">
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>臾닿쾶</label> <input id="ingWeight" name="ingWeight"
												type="number" class="form-control">
										</div>
									</div>

									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>留곹겕</label> <input id="ingLink" name="ingLink"
												type="text" class="form-control">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group form-group-default">
											<label>議고쉶??/label> <input id="ingLinkCount"
												name="ingLinkCount" type="number" class="form-control"
												readonly>
										</div>
									</div>
									<div class="col-md-6 pr-0">
										<div class="form-group form-group-default">
											<label>?대?吏</label> <input id="ingImgName" name="ingImgName"
												type="text" class="form-control">
										</div>
									</div>

								</div>
								<div class="modal-footer no-bd">
									<button id="addRowButton" class="btn btn-primary" type="submit">Add</button>
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Close</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- ********************MODAL end******************************************* -->
			<div class="tab-pane" id="modalTable">
				<div class="card-body">
					<div class="responsive">
						<table id="add-ingredient"
							class="table table-head-bg-default mt-4 table-striped table-hover">
							<thead id="tr">
								<tr>
									<th>?앹옄??/th>
									<th>移댄뀒 怨좊━</th>
									<th>?대쫫</th>
									<th>媛寃?/th>
									<th>?⑥쐞</th>
									<th>釉뚮옖??/th>
									<th>醫낅쪟</th>
									<th>臾닿쾶</th>
									<th>?깅줉 ?좎쭨</th>
									<th>援щℓ留곹겕</th>
									<th>議고쉶??/th>
									<th>?대?吏</th>
									<th style="width: 10%"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="i" items="${ingredientListData }">
									<tr>
										<td>${i.ingID }</td>
										<td>${i.ingCategory }</td>
										<td>${i.ingName }</td>
										<td>${i.ingPrice }</td>
										<td>${i.ingUnit }</td>
										<td>${i.ingBrand }</td>
										<td>${i.ingType }</td>
										<td>${i.ingWeight }</td>
										<td>${i.ingRegDate }</td>
										<td><a href="${i.ingLink }">${i.ingName } 援щℓ?섍린</a></td>
										<td>${i.ingLinkCount }</td>
										<td>${i.ingImgName }</td>
										<td>
											<div class="form-button-action">
												<button type="button" data-toggle="modal" title="?섏젙"
													data-target="#myModal" onclick="ing('${i.ingID}');"
													class="btn btn-link btn-primary btn-lg"
													data-original-title="Edit Task">
													<i class="far fa-edit"></i>
												</button>
												<button onclick="del('${i.ingID}');" type="button"
													data-toggle="tooltip" title="??젣"
													class="btn btn-link btn-danger"
													data-original-title="Remove">
													<i class="fa fa-times"></i>
												</button>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>