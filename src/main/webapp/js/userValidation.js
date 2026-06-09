/*
 * ユーザ上表入力に対してフロント側のバリデーション
 */
const id = document.getElementById("id");
const mail = document.getElementById("mail");
const mailConfirm = document.getElementById("mailConfirm");
const pw = document.getElementById("pw");
const pwConfirm = document.getElementById("pwConfirm");
const lname = document.getElementById("lname");
const fname = document.getElementById("fname");
const address = document.getElementById("address");
const idM = document.getElementById("idM");
const mailM = document.getElementById("mailM");
const mailConfirmM = document.getElementById("mailConfirmM");
const pwM = document.getElementById("pwM");
const pwConfirmM = document.getElementById("pwConfirmM");
const lnameM = document.getElementById("lnameM");
const fnameM = document.getElementById("fnameM");
const addressM = document.getElementById("addressM");
const submitBtn = document.getElementById("submitBtn");

// =====================
// 正規表現
// =====================
const idRegex = /^[a-zA-Z0-9]{1,10}$/;
const mailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const noFullWidth = /^[\x00-\x7F]+$/;
const pwRegex = /^[a-zA-Z0-9]{8,32}$/;
const nameRegex = /^[\p{L}\p{M}\p{N}\sー々・]{1,32}$/u;
const addressRegex = /^.{1,128}$/;
const noDangerousChars = /^[^<>"'&]+$/;

// =====================
// 共通関数
// =====================
function setError(el, msg) {
	el.textContent = msg;
	el.style.color = "red";
}

function clearError(el) {
	el.textContent = "";
}

const state = {
	id: !id,
	mail: !mail,
	mailConfirm: !mailConfirm,
	pw: !pw,
	pwConfirm: !pwConfirm,
	lname: !lname,
	fname: !fname,
	address: !address
};

// =====================
// 個別チェック
// =====================

// ログインID
function checkId() {
	const value = id.value.trim();

	if (value === "") {
		clearError(idM);
		state.id = false;
	} else if (!idRegex.test(value)) {
		setError(idM, "ログインIDは1〜10文字の英数字で入力してください");
		state.id = false;
	} else {
		clearError(idM);
		state.id = true;
	}
}

// メール
function checkMail() {
	const value = mail.value.trim();
	if (value === "") {
		clearError(mailM);
		state.mail = false;
	} else if (!noDangerousChars.test(value)) {
		setError(mailM, "使用できない文字が含まれています");
		state.mail = false;
	} else if (!noFullWidth.test(value)) {
		setError(mailM, "メールアドレスは半角で入力してください");
		state.mail = false;
	} else if (!mailRegex.test(value)) {
		setError(mailM, "正しいメール形式で入力してください");
		state.mail = false;
	} else {
		clearError(mailM);
		state.mail = true;
	}


	// 確認チェック
	const confirmValue = mailConfirm.value.trim();

	if (confirmValue === "") {
		clearError(mailConfirmM);
		state.mailConfirm = false;
	} else if (value !== confirmValue) {
		setError(mailConfirmM, "一致していません");
		state.mailConfirm = false;
	} else {
		clearError(mailConfirmM);
		state.mailConfirm = true;
	}
}

// パスワード
function checkPW() {
	const value = pw.value.trim();

	if (value === "") {
		clearError(pwM);
		state.pw = false;
	} else if (!pwRegex.test(value)) {
		setError(pwM, "パスワードは8〜32文字の英数字で入力してください");
		state.pw = false;
	} else {
		clearError(pwM);
		state.pw = true;
	}

	// 確認チェック
	// confirmがない場合はスキップ
	if (!pwConfirm) return;

	const confirmValue = pwConfirm.value.trim();

	if (confirmValue === "") {
		clearError(pwConfirmM);
		state.pwConfirm = false;
	} else if (value !== confirmValue) {
		setError(pwConfirmM, "一致していません");
		state.pwConfirm = false;
	} else {
		clearError(pwConfirmM);
		state.pwConfirm = true;
	}
}

// 名前系
function checkName() {
	const lnameVal = lname.value.trim();
	const fnameVal = fname.value.trim();

	if (lnameVal === "") {
		clearError(lnameM);
		state.lname = false;
	} else if (!noDangerousChars.test(lnameVal)) {
		setError(lnameM, "使用できない文字が含まれています");
		state.lname = false;
	} else if (!nameRegex.test(lnameVal)) {
		setError(lnameM, "苗字は記号以外で1〜32文字で入力してください");
		state.lname = false;
	} else {
		clearError(lnameM);
		state.lname = true;
	}

	if (fnameVal === "") {
		clearError(fnameM);
		state.fname = false;
	} else if (!noDangerousChars.test(fnameVal)) {
		setError(fnameM, "使用できない文字が含まれています");
		state.fname = false;
	} else if (!nameRegex.test(fnameVal)) {
		setError(fnameM, "名前は記号以外で1〜32文字で入力してください");
		state.fname = false;
	} else {
		clearError(fnameM);
		state.fname = true;
	}
}

// 住所
function checkAddress() {
	const value = address.value.trim();

	if (value === "") {
		clearError(addressM);
		state.address = false;
	} else if (!noDangerousChars.test(value)) {
		setError(addressM, "使用できない文字が含まれています");
		state.address = false;
	} else if (!addressRegex.test(value)) {
		setError(addressM, "住所は記号以外で1〜128文字で入力してください");
		state.address = false;
	} else {
		clearError(addressM);
		state.address = true;
	}
}

// =====================
// 全体チェック（ボタン制御）
// =====================
function updateSubmitState() {
	const allValid = Object.values(state).every(v => v === true);
	submitBtn.disabled = !allValid;
	console.log(state);
}


// =====================
// イベント登録
// =====================
if (id) {
	id.addEventListener("input", () => {
		checkId();
		updateSubmitState();
	});
}

if (mail) {
	mail.addEventListener("input", () => {
		checkMail();
		updateSubmitState();
	});
}

if (mailConfirm) {
	mailConfirm.addEventListener("input", () => {
		checkMail();
		updateSubmitState();
	});
}

if (pw) {
	pw.addEventListener("input", () => {
		checkPW();
		updateSubmitState();
	});
}

if (pwConfirm) {
	pwConfirm.addEventListener("input", () => {
		checkPW();
		updateSubmitState();
	});
}

if (lname) {
	lname.addEventListener("input", () => {
		checkName();
		updateSubmitState();
	});
}

if (fname) {
	fname.addEventListener("input", () => {
		checkName();
		updateSubmitState();
	});
}

if (address) {
	address.addEventListener("input", () => {
		checkAddress();
		updateSubmitState();
	});
}

// 初期状態
submitBtn.disabled = true;
