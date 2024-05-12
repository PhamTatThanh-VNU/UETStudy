(function($) {

	"use strict";

	$(window).stellar({
    responsive: true,
    parallaxBackgrounds: true,
    parallaxElements: true,
    horizontalScrolling: false,
    hideDistantElements: false,
    scrollProperty: 'scroll'
  });


	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	// loader
	var loader = function() {
		setTimeout(function() { 
			if($('#ftco-loader').length > 0) {
				$('#ftco-loader').removeClass('show');
			}
		}, 1);
	};
	loader();

	// Scrollax
   $.Scrollax();

	var carousel = function() {
		$('.carousel-testimony').owlCarousel({
			center: false,
			loop: true,
			items:1,
			margin: 30,
			stagePadding: 0,
			nav: false,
			navText: ['<span class="ion-ios-arrow-back">', '<span class="ion-ios-arrow-forward">'],
			responsive:{
				0:{
					items: 1
				},
				600:{
					items: 2
				},
				1000:{
					items: 4
				}
			}
		});

	};
	carousel();

	$('nav .dropdown').hover(function(){
		var $this = $(this);
		// 	 timer;
		// clearTimeout(timer);
		$this.addClass('show');
		$this.find('> a').attr('aria-expanded', true);
		// $this.find('.dropdown-menu').addClass('animated-fast fadeInUp show');
		$this.find('.dropdown-menu').addClass('show');
	}, function(){
		var $this = $(this);
			// timer;
		// timer = setTimeout(function(){
			$this.removeClass('show');
			$this.find('> a').attr('aria-expanded', false);
			// $this.find('.dropdown-menu').removeClass('animated-fast fadeInUp show');
			$this.find('.dropdown-menu').removeClass('show');
		// }, 100);
	});


	$('#dropdown04').on('show.bs.dropdown', function () {
	  console.log('show');
	});

	// scroll
	var scrollWindow = function() {
		$(window).scroll(function(){
			var $w = $(this),
					st = $w.scrollTop(),
					navbar = $('.ftco_navbar'),
					sd = $('.js-scroll-wrap');

			if (st > 150) {
				if ( !navbar.hasClass('scrolled') ) {
					navbar.addClass('scrolled');	
				}
			} 
			if (st < 150) {
				if ( navbar.hasClass('scrolled') ) {
					navbar.removeClass('scrolled sleep');
				}
			} 
			if ( st > 350 ) {
				if ( !navbar.hasClass('awake') ) {
					navbar.addClass('awake');	
				}
				
				if(sd.length > 0) {
					sd.addClass('sleep');
				}
			}
			if ( st < 350 ) {
				if ( navbar.hasClass('awake') ) {
					navbar.removeClass('awake');
					navbar.addClass('sleep');
				}
				if(sd.length > 0) {
					sd.removeClass('sleep');
				}
			}
		});
	};
	scrollWindow();

	var isMobile = {
		Android: function() {
			return navigator.userAgent.match(/Android/i);
		},
			BlackBerry: function() {
			return navigator.userAgent.match(/BlackBerry/i);
		},
			iOS: function() {
			return navigator.userAgent.match(/iPhone|iPad|iPod/i);
		},
			Opera: function() {
			return navigator.userAgent.match(/Opera Mini/i);
		},
			Windows: function() {
			return navigator.userAgent.match(/IEMobile/i);
		},
			any: function() {
			return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
		}
	};

	var counter = function() {
		
		$('#section-counter, .hero-wrap, .ftco-counter').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('ftco-animated') ) {

				var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
				$('.number').each(function(){
					var $this = $(this),
						num = $this.data('number');
						console.log(num);
					$this.animateNumber(
					  {
					    number: num,
					    numberStep: comma_separator_number_step
					  }, 7000
					);
				});
				
			}

		} , { offset: '95%' } );

	}
	counter();


	var contentWayPoint = function() {
		var i = 0;
		$('.ftco-animate').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('ftco-animated') ) {
				
				i++;

				$(this.element).addClass('item-animate');
				setTimeout(function(){

					$('body .ftco-animate.item-animate').each(function(k){
						var el = $(this);
						setTimeout( function () {
							var effect = el.data('animate-effect');
							if ( effect === 'fadeIn') {
								el.addClass('fadeIn ftco-animated');
							} else if ( effect === 'fadeInLeft') {
								el.addClass('fadeInLeft ftco-animated');
							} else if ( effect === 'fadeInRight') {
								el.addClass('fadeInRight ftco-animated');
							} else {
								el.addClass('fadeInUp ftco-animated');
							}
							el.removeClass('item-animate');
						},  k * 50, 'easeInOutExpo' );
					});
					
				}, 100);
				
			}

		} , { offset: '95%' } );
	};
	contentWayPoint();


	// magnific popup
	$('.image-popup').magnificPopup({
    type: 'image',
    closeOnContentClick: true,
    closeBtnInside: false,
    fixedContentPos: true,
    mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
     gallery: {
      enabled: true,
      navigateByImgClick: true,
      preload: [0,1] // Will preload 0 - before current, and 1 after the current image
    },
    image: {
      verticalFit: true
    },
    zoom: {
      enabled: true,
      duration: 300 // don't foget to change the duration also in CSS
    }
  });

  $('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
    disableOn: 700,
    type: 'iframe',
    mainClass: 'mfp-fade',
    removalDelay: 160,
    preloader: false,

    fixedContentPos: false
  });


  $('.checkin_date, .checkout_date').datepicker({
	  'format': 'm/d/yyyy',
	  'autoclose': true
	});




})(jQuery);
//Date format
function formatDate(dateString) {
	let date = new Date(dateString);
	let year = date.getFullYear();
	let month = ('0' + (date.getMonth() + 1)).slice(-2); // Thêm '0' vào trước số tháng nếu cần
	let day = ('0' + date.getDate()).slice(-2); // Thêm '0' vào trước số ngày nếu cần
	let hours = ('0' + date.getHours()).slice(-2); // Thêm '0' vào trước số giờ nếu cần
	let minutes = ('0' + date.getMinutes()).slice(-2); // Thêm '0' vào trước số phút nếu cần
	let seconds = ('0' + date.getSeconds()).slice(-2); // Thêm '0' vào trước số giây nếu cần
	return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
$(document).ready(function() {
	// Chọn tất cả các phần tử có lớp hoặc thuộc tính dữ liệu 'date-created'
	$('.date-created').each(function() {
		// Lấy giá trị của thuộc tính 'th:text' và chuyển đổi định dạng thời gian
		let dateString = $(this).text();
		let formattedDate = formatDate(dateString);
		// Gán lại giá trị đã chuyển đổi cho thuộc tính 'th:text'
		$(this).text(formattedDate);
	});
});
//Filter
$(document).ready(function getKnowledgeBlock(){
	$("#department").change(function(){
		let idDepartment = $(this).val();
		$.ajax({
			url: '/findByDepartment',
			type: 'GET',
			data: { idDepartment: idDepartment },
			success: function(response) {
				let knowledgeBlockSelect = $('#knowledgeBlock');
				knowledgeBlockSelect.empty();
				$('<option>').text("Chon khoi kien thuc").appendTo(knowledgeBlockSelect);
				$.each(response, function(i, knowledgeBlock){
					$('<option>').val(knowledgeBlock.blockId).text(knowledgeBlock.blockName).appendTo(knowledgeBlockSelect);
				});
			}
		});
	});
});

$(document).ready(function getSubject(){
	$("#knowledgeBlock").change(function(){
		let knowledgeId = $(this).val();
		$.ajax({
			url: '/filterSubject',
			type: 'GET',
			data: { knowledgeId: knowledgeId },
			success: function(response){
				let SubjectSelect = $('#subject');
				SubjectSelect.empty();
				$('<option>').text("Chon Mon Hoc").appendTo(SubjectSelect);
				$.each(response, function(i, subject){
					$('<option>').val(subject.subjectId).text(subject.subjectName).appendTo(SubjectSelect);
				});
			}
		});
	});
});

$(document).ready(function() {
	$('#findDocumentForm').submit(function(event) {
		event.preventDefault();
		let subjectId = $('#subject').val();
		// Gửi yêu cầu AJAX để lấy tài liệu dựa trên subjectId
		$.ajax({
			url: '/findDocument',
			type: 'GET',
			data: { subjectId: subjectId },
			success: function(response) {
				// Xóa tất cả các phần tử hiện tại
				$('#document').empty();

				// Duyệt qua từng tài liệu trong response
				$.each(response, function(index, document) {
					console.log(document.dateCreated)
					let newElement = `
					<div class="col-md-6 d-flex align-items-stretch ftco-animate fadeInUp ftco-animated">
						<div class="project-wrap">
							<div class="text p-4">
								<a href="/uploads/files/${document.documentFile}" target="_blank">
									<h3>${document.documentTitle}</h3>
								</a>
								<p>
									<a href="/uploads/files/${document.documentFile}" target="_blank">${document.documentContent}</a>
								</p>
								<ul class="d-flex justify-content-between">
									<li><span class="flaticon-shower"></span><span>${formatDate(document.dateCreated)}</span></li>
								</ul>
							</div>
						</div>
					</div>`;
					// Thêm phần tử mới vào DOM
					$('#document').append(newElement);
				});
			},
			error: function(xhr, status, error) {
				// Xử lý lỗi
				console.error(error);
			}
		});
	});
});

$(document).ready(function() {
	$('#searchDocument').keydown(function(event) {
		// Ấn nút Enter (keyCode = 13)
		if (event.keyCode === 13) {
			event.preventDefault();
			let keyword = $('#searchDocument').val().trim();
			// Gửi yêu cầu AJAX để tìm kiếm tài liệu theo tên
			$.ajax({
				url: '/findDocumentByName',
				type: 'GET',
				data: { keyword: keyword },
				success: function(response) {
					// Xóa tất cả các phần tử hiện tại
					$('#document').empty();
					// Duyệt qua từng tài liệu trong response và thêm vào DOM
					$.each(response, function(index, document) {
						if(document.approved) {
							let newElement = `
						<div class="col-md-6 d-flex align-items-stretch ftco-animate fadeInUp ftco-animated">
							<div class="project-wrap" id="approved">
								<div class="text p-4">
									<a href="/uploads/files/${document.documentFile}" target="_blank">
										<h3>${document.documentTitle}</h3>
									</a>
									<p>
										<a href="/uploads/files/${document.documentFile}" target="_blank">${document.documentContent}</a>
									</p>
									<ul class="d-flex justify-content-between">
										<li><span class="flaticon-shower"></span><span>${formatDate(document.dateCreated)}</span></li>
									</ul>
								</div>
							</div>
						</div>`;
							$('#document').append(newElement);
						}
					});
				},
				error: function(xhr, status, error) {
					// Xử lý lỗi
					console.error(error);
				}
			});
		}
	});
});
$(document).ready(function(){
	$('#subjectSearch').autocomplete({
		source: function(request, response) {
			$.ajax({
				type: 'GET',
				url: '/allSubject',
				success: function(subjects){
					let filteredSubjects = subjects.filter(function(subject){
						return subject.subjectName.toLowerCase().includes(request.term.toLowerCase());
					});
					response(filteredSubjects.map(function(subject) {
						return { label: subject.subjectName, value: subject.subjectId };
					}));
				},
			});
		},
		select: function(event, ui) {
			// ui.item.value chứa subjectId
			// ui.item.label chứa subjectName
			$('#subjectSearch').val(ui.item.label);
			// Lưu subjectId vào một ô nhập liệu ẩn hoặc biến toàn cục để sử dụng sau này
			$('#subjectId').val(ui.item.value);
			return false;
		}
	});
});
document.querySelector('form').addEventListener('submit', function(event) {
	var fileInput = document.getElementById('file');
	var maxSizeInBytes = 1024 * 1024 * 10; // 1MB

	// Kiểm tra kích thước của tệp tin
	if (fileInput.files.length > 0 && fileInput.files[0].size > maxSizeInBytes) {
		var errorFeedback = document.getElementById('FeedbackErr');
		errorFeedback.innerHTML = "The allowed file size is 10 MB. The file you are trying to upload is too large.";
		errorFeedback.style.display = 'block'; // Hiển thị thông báo lỗi
		event.preventDefault(); // Ngăn chặn form được gửi đi
	}
});

$(document).ready(function() {
	$('table #updateButton').on('click', function (event) {
		event.preventDefault();
		let href = $(this).attr('href');
		$.get(href, function (department) {
			$('#idDepartment').val(department.departmentId);
			$('#departmentNameUpdate').val(department.departmentName);
		});
		$('#editModal').modal();
	});
});
$(document).ready(function() {
	$('table #updateKnowledgeBlock').on('click', function (event) {
		event.preventDefault();
		let href = $(this).attr('href');
		$.get(href, function (knowledgeBlock) {
			$('#knowledgeId').val(knowledgeBlock.blockId);
			$('#knowledgeNameUpdate').val(knowledgeBlock.blockName);
			$('#knowledge-departmentUpdate').val(knowledgeBlock.department.departmentId);
		});
		$('#editKnowledgeBlock').modal();
	});
});

$(document).ready(function() {
	$('table #updateSubjectButton').on('click', function (event) {
		event.preventDefault();
		let href = $(this).attr('href');
		$.get(href, function (subject) {
			console.log(subject)
			$('#subjectId').val(subject.subjectId);
			$('#subjectNameUpdate').val(subject.subjectName);
			$('#subject-knowledgeUpdate').val(subject.knowledgeBlock.blockId);
		});
		$('#editSubject').modal();
	});
});

const urlParams = new URLSearchParams(window.location.search);
const errorMessage = urlParams.get('error');

// Kiểm tra xem có thông báo lỗi không và hiển thị nó
if (errorMessage && errorMessage.trim() !== "") {
	document.getElementById("FeedbackDeleteErr").innerText = errorMessage;
	document.getElementById("FeedbackDeleteErr").style.display = "block";
}

function checkPasswordMatch(fieldConfirmPassword) {
	if (fieldConfirmPassword.value != $("#password-field").val()) {
		fieldConfirmPassword.setCustomValidity("Passwords do not match!");
	} else {
		fieldConfirmPassword.setCustomValidity("");
	}
}

function toggleNote() {
	var note1 = document.getElementById('note-1');
	var note2 = document.getElementById('note-2');

	note1.style.display = 'none';
	note2.style.display = 'block';
}

function hideNote() {
	var note1 = document.getElementById('note-1');
	var note2 = document.getElementById('note-2');

	note2.style.display = 'none';
	note1.style.display = 'block';
}

function closeForm(event) {
	event.preventDefault(); // Ngăn chặn gửi form
	hideNote(); // Ẩn note
}
