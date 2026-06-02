const id = document.getElementById("id");
const mail = document.getElementById("mail");
const mailConfirm = document.getElementById("mailConfirm");
const pw = document.getElementById("pw");
const confirm = document.getElementById("confirm");
const lname = document.getElementById("lname");
const fname = document.getElementById("fname");
const address = document.getElementById("address");
const messageMail1 = document.getElementById("messageMail1");
const messageMail2 = document.getElementById("messageMail2");
const message1 = document.getElementById("message1");
const message2 = document.getElementById("message2");
const submitBtn = document.getElementById("submitBtn");
const regex = /^[A-Za-z\d]{8,25}$/;
const mailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


// 入力制限(a-z,A-Z,0-9,~25)PW用
function sanitizePW(input) {
	input.value = input.value.replace(/\s/g, '').replace(/[^a-zA-Z0-9]/g, '');
	if (input.value.length > 25) {
		input.value = input.value.slice(0, 25);
	}
}

// 入力制限(a-z,A-Z,0-9,~10)ID用
function sanitizeID(input) {
	input.value = input.value.replace(/\s/g, '').replace(/[^a-zA-Z0-9]/g, '');
	if (input.value.length > 10) {
		input.value = input.value.slice(0, 10);
	}
}

// 入力制限（メール用）
function sanitizeMail(input) {
  // 空白除去
  input.value = input.value.toLowerCase().replace(/\s/g, '').replace(/[^a-zA-Z0-9@._+\-]/g, '');
}

//入力制限（記号禁止）
function sanitizeOther(input) {
  input.value = input.value.replace(/[^\p{L}\p{N}\s]/gu, '');
}

id.addEventListener("input",()=>{
	sanitizeID(id);
})

mail.addEventListener("input", () => {
  sanitizeMail(mail);
  checkEmail();
});

mailConfirm.addEventListener("input", () => {
  sanitizeMail(mailConfirm);
  checkEmail();
});


pw.addEventListener("input", () => {
	sanitizePW(pw);
	checkPW();
});

confirm.addEventListener("input", () => {
	sanitizePW(confirm);
	checkPW();
});

lname.addEventListener("input", () => {
	sanitizeOther(lname);
});

fname.addEventListener("input", () => {
	sanitizeOther(fname);
});

address.addEventListener("input", () => {
	sanitizeOther(address);
});

function checkPW() {

	// パスワード長チェック

	if (!regex.test(pw.value)) {
		message1.textContent = "英字と数字を含めて8〜25文字で入力してください";
		message1.style.color = "red";
	} else {
		message1.textContent = "OK";
		message1.style.color = "green";
	}

	//確認チェック
	if (confirm.value === "") {
		message2.textContent = "";
		submitBtn.disabled = true;
		return;
	}

	if (pw.value === confirm.value) {
		message2.textContent = "一致しています";
		message2.style.color = "green";
		submitBtn.disabled = false;
	} else {
		message2.textContent = "一致していません";
		message2.style.color = "red";
		submitBtn.disabled = true;
	}

}


function checkEmail() {

  // 形式チェック
  if (!mailRegex.test(mail.value)) {
    messageMail1.textContent = "正しいメールアドレスを入力してください";
    messageMail1.style.color = "red";
  } else {
    messageMail1.textContent = "OK";
    messageMail1.style.color = "green";
  }

  // 確認未入力
  if (mailConfirm.value === "") {
    messageMail2.textContent = "";
    return;
  }

  // 一致チェック
  if (mail.value === mailConfirm.value) {
    if (mailRegex.test(mail.value)) {
      messageMail2.textContent = "一致しています";
      messageMail2.style.color = "green";
    }
  } else {
    messageMail2.textContent = "一致していません";
    messageMail2.style.color = "red";
  }
}



// 初期状態は押せないように
submitBtn.disabled = true;


id.addEventListener("input", check);
pw.addEventListener("input", check);
confirm.addEventListener("input", check);
mail.addEventListener("input", check);
mailConfirm.addEventListener("input", check);
