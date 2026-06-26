/**
 * 
 */
const title = document.getElementById("title");
const titleM = document.getElementById("titleM");
const content = document.getElementById("content");
const contentM = document.getElementById("contentM");
const submitBtn = document.getElementById("submitBtn");
// =====================
// 正規表現
// =====================
const titleRegex = /^.{1,128}$/;
const contentRegex = /^.{1,1000}$/;
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
	if (config.title) {
		initField("title", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.title = false;
			} else if (!titleRegex.test(v) || !noDangerousChars.test(v)) {
				if (touched) setError(msg, "1〜128文字で入力してください。一部使用できない記号があります。");
				state.title = false;
			} else {
				clearError(msg);
				state.title = true;
			}
		}, { input: title, message: titleM });
	}

	//Nameチェック
	if (config.content) {
		initField("content", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.content = false;
			} else if (!contentRegex.test(v) || !noDangerousChars.test(v)) {
				if (touched) setError(msg, "1〜1000文字で入力してください。一部使用できない記号があります。");
				state.content = false;
			} else {
				clearError(msg);
				state.content = true;
			}
		}, { input: content, message: contentM });
	}
}

function setupValidationByMode() {
	setupValidation({
		title: true,
		content: true
	});
}