/**
 * IDとメールアドレスをフロントでチェックする機能
 */

const idInput = document.getElementById("id");
const idMsg = document.getElementById("idM");

const mailInput = document.getElementById("mail");
const mailMsg = document.getElementById("mailM");

function setError(el, msg) {
	el.textContent = msg;
	el.style.color = "red";
}

function clearError(el) {
	el.textContent = "";
	el.style.color = "";
}

async function checkDuplicate() {

	const id = idInput.value;
	const mail = mailInput.value;

	const res = await fetch(
		`${contextPath}/duplicationCheck.action?id=${encodeURIComponent(id)}&mail=${encodeURIComponent(mail)}`
	);

	const data = await res.json();

	if ("idDuplicate" in state) {
		state.idDuplicate = !data.idExists;
	}

	if ("mailDuplicate" in state) {
		state.mailDuplicate = !data.mailExists;
	}

	// 結果反映
	if (data.idExists) {
		setError(idMsg, "IDが重複しています");
	} else {
		clearError(idMsg);
	}

	if (data.mailExists) {
		setError(mailMsg, "メールが重複しています");
	} else {
		clearError(mailMsg);
	}

	updateSubmitState();
}
// 入力時にチェック
idInput.addEventListener("blur", checkDuplicate);
mailInput.addEventListener("blur", checkDuplicate);
