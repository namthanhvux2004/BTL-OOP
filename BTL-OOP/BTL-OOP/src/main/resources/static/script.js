function tai_lai_trang(){
    location.reload();
}

function goToHomePage() {
    window.location.href = './home.html';
}

function goToLib() {
    window.location.href = 'thuvien.html';
}

const listBtn = document.querySelector('.js-btn_list')
const modal = document.querySelector('.modal') //luu class modal vao bien modal
const ClosetBtn = document.querySelector('.js-btn-close')
function showList() {
    modal.classList.add('open')
}

function hideList() {
    modal.classList.remove('open')
}

listBtn.addEventListener('click', showList)
ClosetBtn.addEventListener('click', hideList)