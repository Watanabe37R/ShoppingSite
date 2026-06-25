/*
 * ユーザ上表入力に対してフロント側のバリデーション
 */
const id = document.getElementById("id");
const mail = document.getElementById("mail");
const mailConfirm = document.getElementById("mailConfirm");
const pw = document.getElementById("pw");
const pwOld = document.getElementById("pwOld");
const pwConfirm = document.getElementById("pwConfirm");
const lname = document.getElementById("lname");
const fname = document.getElementById("fname");
const address = document.getElementById("address");
const idM = document.getElementById("idM");
const mailM = document.getElementById("mailM");
const mailConfirmM = document.getElementById("mailConfirmM");
const pwM = document.getElementById("pwM");
const pwOldM = document.getElementById("pwOldM");
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
//エラー定義
function setError(el, msg) {
	if (!el) return;
	el.textContent = msg;
	el.style.color = "red";
}

function clearError(el) {
	if (!el) return;
	el.textContent = "";
	el.style.color = "";
}

function setupValidation(config) {
	const state = {};
	const touched = {};


	function updateSubmitState() {
		const allValid = Object.values(state).every(v => v === true);
		submitBtn.disabled = !allValid;
	}

	function initField(key, checkFn, elements) {
		const input = elements.input;
		const message = elements.message;

		state[key] = false;

		if (!input) return;

		const run = () => {
			checkFn(input, message, state, touched[key]);
			updateSubmitState();
		};

		// 入力されたらフラグON
		input.addEventListener("input", () => {
			touched[key] = true;
			run();
		});

		// フォーカス外れたらチェック
		input.addEventListener("blur", () => {
			touched[key] = true;
			run();
		});

		run(); // 初期チェック
	}

	// =========================
	// 各フィールド定義
	// =========================
	//IDチェック
	if (config.id) {
		initField("id", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.id = false;
			} else if (!idRegex.test(v)) {
				if (touched) setError(msg, "ログインIDは1〜10文字の半角英数字で入力してください");
				state.id = false;
			} else {
				clearError(msg);
				state.id = true;
			}
		}, { input: id, message: idM });
	}
	//メールチェック
	if (config.mail) {
		initField("mail", (el, msg, state, touched) => {
			const v = el.value.trim();

			if (v === "") {
				clearError(msg);
				state.mail = false;
			} else if (!mailRegex.test(v)) {
				if (touched) setError(msg, "メール形式が不正です。128文字以内で半角英数字等で入力してください");
				state.mail = false;
			} else {
				clearError(msg);
				state.mail = true;
			}
		}, { input: mail, message: mailM });
	}
	//メール一致チェック
	if (config.mailConfirm && mail && mailConfirm) {
		state.mailConfirm = false;

		const run = () => {
			const touchedFlag = touched.mail || touched.mailConfirm;
			const v = mail.value.trim();
			const cv = mailConfirm.value.trim();

			if (!mailRegex.test(v)) {
				if (touchedFlag) setError(mailM, "メール形式が不正です。128文字以内で半角英数字等で入力してください");
				state.mailConfirm = false;
			} else {
				clearError(mailM);
			}

			if (cv === "") {
				clearError(mailConfirmM);
				state.mailConfirm = false;
			} else if (v !== cv) {
				if (touchedFlag) setError(mailConfirmM, "一致していません");
				state.mailConfirm = false;
			} else {
				clearError(mailConfirmM);
				state.mailConfirm = true;
			}

			updateSubmitState();
		};

		mail.addEventListener("input", () => {
		  touched.mail = true;
		  run();
		});

		mailConfirm.addEventListener("input", () => {
		  touched.mailConfirm = true;
		  run();
		});

		run();
	}
	//PWチェック
	if (config.pw) {
		initField("pw", (el, msg, state, touched) => {
			const v = el.value.trim();

			if (!pwRegex.test(v)) {
				if (touched) setError(msg, "パスワードは、8〜32文字の半角英数字で入力してください");
				state.pw = false;
			} else {
				clearError(msg);
				state.pw = true;
			}
		}, { input: pw, message: pwM });
	}
	//PW一致チェック
	if (config.pwConfirm && pw && pwConfirm) {
		state.pwConfirm = false;

		const run = () => {
			const touchedPWFlag = touched.pw || touched.pwConfirm;
			const v = pw.value.trim();
			const cv = pwConfirm.value.trim();

			if (!pwRegex.test(v)) {
				if (touchedPWFlag) setError(pwM, "パスワードは、8〜32文字の半角英数字で入力してください");
				state.pwConfirm = false;
			} else {
				clearError(pwM);
			}

			if (cv === "") {
				clearError(pwConfirmM);
				state.pwConfirm = false;
			} else if (v !== cv) {
				if (touchedPWFlag) setError(pwConfirmM, "一致していません");
				state.pwConfirm = false;
			} else {
				clearError(pwConfirmM);
				state.pwConfirm = true;
			}

			updateSubmitState();
		};
		
		pw.addEventListener("input", () => {
		  touched.pw = true;
		  run();
		});

		pwConfirm.addEventListener("input", () => {
		  touched.pwConfirm = true;
		  run();
		});
	
		run();
	}
	//旧PWチェック
	if (config.pwOld) {
		initField("pwOld", (el, msg, state, touched) => {
			const v = el.value.trim();

			if (v === "") {
				if (touched) setError(msg, "現在のパスワードを入力してください");
				state.pwOld = false;

			} else if (!pwRegex.test(v)) {
				if (touched) setError(msg, "パスワードは、8〜32文字の半角英数字で入力してください");
				state.pwOld = false;

			} else {
				clearError(msg);
				state.pwOld = true;
			}

		}, { input: pwOld, message: pwOldM });
	}
	//名前チェック
	if (config.name) {
		initField("lname", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (!nameRegex.test(v)) {
				if (touched) setError(msg, "苗字は32文字以内で入力してください。なお、使用できない記号があります。");
				state.lname = false;
			} else {
				clearError(msg);
				state.lname = true;
			}
		}, { input: lname, message: lnameM });

		initField("fname", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (!nameRegex.test(v)) {
				if (touched) setError(msg, "名前は32文字以内で入力してください。なお、使用できない記号があります。");
				state.fname = false;
			} else { 
				clearError(msg);
				state.fname = true;
			}
		}, { input: fname, message: fnameM });
	}
	//住所チェック
	if (config.address) {
		initField("address", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (!addressRegex.test(v)||!noDangerousChars.test(v)) {
				if (touched) setError(msg, "住所は128文字以内で入力してください。なお、使用できない記号があります。");
				state.address = false;
			} else {
				clearError(msg);
				state.address = true;
			}
		}, { input: address, message: addressM });
	}

	//重複チェック
	if (config.duplicate) {

		if (config.id) state.idDuplicate = false;
		if (config.mail || config.mailConfirm) state.mailDuplicate = false;

		const checkDuplicate = async () => {
			console.log("dup呼ばれた"); 
			const idVal = id ? id.value : "";
			const mailVal = mail ? mail.value : "";

			const res = await fetch(
				`${contextPath}/duplicationCheck.action?id=${encodeURIComponent(idVal)}&mail=${encodeURIComponent(mailVal)}`
			);

			const data = await res.json();

			// ID
			if ("idDuplicate" in state) {
				state.idDuplicate = !data.idExists;

				if (data.idExists) {
					if (touched.id) setError(idM, "IDが重複しています。別のIDをお試しください。");
				} else {
					clearError(idM);
				}
			}

			// mail
			if ("mailDuplicate" in state) {
				state.mailDuplicate = !data.mailExists;

				if (data.mailExists) {
					if (touched.mail) setError(mailM, "メールが重複しています。別のメールアドレスをお試しください。");
				} else {
					clearError(mailM);
				}
			}

			updateSubmitState();
		};

		if (id) {
			id.addEventListener("blur", () => {
				touched.id = true;
				checkDuplicate();
			});
		}
		if (mail) {
		  mail.addEventListener("blur", () => {
		    touched.mail = true;
		    checkDuplicate();
		  });
		}
	}
}



