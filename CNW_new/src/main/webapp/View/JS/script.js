let slideIndex = 0;
showSlides();

function showSlides() {
    let i;
    const slidesContainer = document.getElementById('slide-container');
    
    const imagePaths = ["./View/image_slide/image1.jpg", "./View/image_slide/image2.png", "./View/image_slide/image3.jpg", "./View/image_slide/image4.jpg", "./View/image_slide/image5.jpg", "./View/image_slide/image6.jpg"];
    for (i = 0; i < imagePaths.length; i++) {
        const slide = document.createElement('div');
        slide.className = 'mySlides fade'; // Thêm class fade
        const img = document.createElement('img');
        img.src = imagePaths[i];
        slide.appendChild(img);
        slidesContainer.appendChild(slide);
    }

    const allSlides = document.getElementsByClassName("mySlides");

    for (i = 0; i < allSlides.length; i++) {
        allSlides[i].style.display = "none";
    }

    slideIndex++;
    if (slideIndex > allSlides.length) {
        slideIndex = 1;
    }
    
    for (i = 0; i < allSlides.length; i++) {
		allSlides[i].classList.remove("fade"); // Loại bỏ class fade từ tất cả các sildes
	}

    allSlides[slideIndex - 1].style.display = "block";
    //allSlides[slideIndex - 1].classList.add("fade"); // Thêm class fade cho silde hiện tại

    setTimeout(showSlides, 3000); // Chuyển slide sau 3 giây
}

// Tạo sự kiện click tạo Form Booking
// const bookingBtn = document.querySelector('#booking-form');
const bookingClose = document.querySelector('#booking-form-close');
/*
bookingBtn.addEventListener("click", function() {
	document.querySelector('.booking-form').style.display = "flex";
});*/

bookingClose.addEventListener("click", function() {
	document.querySelector('.booking-form').style.display = "none";
});