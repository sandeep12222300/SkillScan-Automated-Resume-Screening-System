document.getElementById("resumeForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const formData = new FormData(this);

    fetch("/api/resume/upload", {
        method: "POST",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            const resultBox = document.getElementById("resultBox");
            resultBox.style.display = "block";
            resultBox.innerHTML = `
            <strong>Candidate:</strong> ${data.candidateName}<br>
            <strong>Match Percentage:</strong> ${data.matchPercentage.toFixed(2)}%
        `;
        })
        .catch(error => {
            alert("Error uploading resume");
        });
});
