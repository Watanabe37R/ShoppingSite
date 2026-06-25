/**
 * 
 */
const id = document.getElementById("id");
const idM = document.getElementById("idM");
const nameInput = document.getElementById("name");
const nameM = document.getElementById("nameM");
const submitBtn = document.getElementById("submitBtn");
// =====================
// 正規表現
// =====================
const idRegex = /^[a-zA-Z0-9]{1,10}$/;
const nameRegex = /^.{1,32}$/;
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
	console.log("動いてるよ");
	console.log("submitBtn:", submitBtn);


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
			console.log("state:", state);
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
				if (touched) setError(msg, "IDは1〜10文字の半角英数字で入力してください");
				state.id = false;
			} else {
				clearError(msg);
				state.id = true;
			}
		}, { input: id, message: idM });
	}

	//Nameチェック
	if (config.nameInput) {
		initField("nameInput", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.nameInput = false;
			} else if (!nameRegex.test(v) || !noDangerousChars.test(v)) {
				if (touched) setError(msg, "1〜32文字で入力してください。一部使用できない記号があります。");
				state.nameInput = false;
			} else {
				clearError(msg);
				state.nameInput = true;
			}
		}, { input: nameInput, message: nameM });
	}
}

function setupValidationByMode(mode) {
	if (mode === "create") {
		setupValidation({
			id: true,
			nameInput: true
		});

	} else if (mode === "edit") {
		setupValidation({
			nameInput: true
		});
	}
}
