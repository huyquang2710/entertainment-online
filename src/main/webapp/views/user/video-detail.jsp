<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${ video.title }</title>
    <%@include file="/common/head.jsp"%>
</head>
<body>
    <!-- Page Loader -->
   <%@include file="/common/header.jsp"%>
   
    <div class="container-fluid tm-container-content tm-mt-60">
        <div class="row mb-4">
            <h2 class="col-12 tm-text-primary">${ video.title }</h2>
        </div>	
        <div class="row tm-mb-90">            
            <div class="col-xl-8 col-lg-7 col-md-6 col-sm-12" style="min-height: 500px!important">
            	
            	 <iframe id="tm-video" src="http://www.youtube.com/embed/${ video.href }" ></iframe>

            </div>
            <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">
                <div class="tm-bg-gray tm-video-details">
                    <p class="mb-4">${ video.description }</p>
                    <input type="hidden" id="videoIdHdn" value="${ video.href }" >
                    <c:if test="${not empty sessionScope.currentUser }">
                    <div class="text-center mb-5">
                        <button id="likeOrUnlikeBtn" class="btn btn-primary tm-btn-big">
							<c:choose>
								<c:when test="${ flagLikedBtn == true }">
									Unlike
								</c:when>
								<c:otherwise>
									Like
								</c:otherwise>
							</c:choose>
						</button>
                    </div>   
                    <div class="text-center mb-5">
                        <a href="#" class="btn btn-primary tm-btn-big">Share</a>
                    </div>    
                    </c:if>
                                                   
                </div>
            </div>
        </div>
        
        <!-- video lien quan. sau nay phat trien -->
<!--         <div class="row mb-4">
            <h2 class="col-12 tm-text-primary">
                Related Videos
            </h2>
        </div>
        <div class="row mb-3 tm-gallery">
            <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
                <figure class="effect-ming tm-video-item">
                    <img src="img/img-01.jpg" alt="Image" class="img-fluid">
                    <figcaption class="d-flex align-items-center justify-content-center">
                        <h2>Hangers</h2>
                        <a href="#">View more</a>
                    </figcaption>                    
                </figure>
                <div class="d-flex justify-content-between tm-text-gray">
                    <span class="tm-text-gray-light">12 Oct 2020</span>
                    <span>12,460 views</span>
                </div>
            </div>    
        </div> row -->
        
    </div> <!-- container-fluid, tm-container-content -->

	<!-- footer -->
	<%@include file="/common/footer.jsp"%>
	<script>
		$('#likeOrUnlikeBtn').click(function() {
			var videoId = $('#videoIdHdn').val();
			$.ajax({
				url: 'video?action=like&id=' + videoId
			}).then(function(data) {
				var text = $('#likeOrUnlikeBtn').text();
				if(text.indexOf('Like') != -1) {
					$('#likeOrUnlikeBtn').text('Unlike');
				} else {
					$('#likeOrUnlikeBtn').text('Like');
				}
			}).fail(function(error) {
				alert('Co loi xay ra');
			});
		});
				
	</script>
</body>
</html>