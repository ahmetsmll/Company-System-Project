fetch("http://localhost:8080/departments")
    .then(response => response.json())
    .then(departments => {
        const list = document.getElementById("departmentList");
        list.innerHTML = "";

        departments.forEach(dept => {
            const li = document.createElement("li");
           

			const nameSpan = document.createElement("span");
			           nameSpan.textContent = dept.name;
					   
			const showBtn = document.createElement("button");
			showBtn.textContent = "Employees";
			showBtn.onclick = () => toggleEmployees(dept.id, li);
			
            const deleteBtn = document.createElement("button");
            deleteBtn.textContent = "Delete";
            deleteBtn.onclick = () => deleteDepartment(dept.id);

			li.appendChild(nameSpan);
			li.appendChild(showBtn);
            li.appendChild(deleteBtn);
			
            list.appendChild(li);
		
        });
    })
    .catch(error => {
        console.error("Error:", error);
    });
	function addDepartment() {
	    const name = document.getElementById("departmentName").value;

	    if (!name) {
	        alert("Department name cannot be empty!");
	        return;
	    }

	    fetch("http://localhost:8080/departments/save", {
	        method: "POST",
	        headers: {
	            "Content-Type": "application/json"
	        },
	        body: JSON.stringify({ name: name })
	    })
	    .then(() => {
	        document.getElementById("departmentName").value = "";
	        loadDepartments();
	    })
	    .catch(error => console.error("Department add error:", error));
	}
	
	function deleteDepartment(id) {
	    fetch(`http://localhost:8080/departments/delete/${id}`, {
	        method: "DELETE"
	    })
	    .then(() => {
	        location.reload(); 
	    })
	    .catch(error => {
	        console.error("Delete error:", error);
	    });
	}

	function toggleEmployees(departmentId, departmentLi) {

	    
	    const existing = departmentLi.querySelector("ul");
	    if (existing) {
	        existing.remove();
	        return;
	    }
	
			
	    fetch(`http://localhost:8080/employees/department/${departmentId}`)
	        .then(response => response.json())
	        .then(employees => {
	            const empUl = document.createElement("ul");

	            employees.forEach(emp => {
	                const empLi = document.createElement("li");
	                empLi.textContent = `${emp.name} - ${emp.salary} ₺`;
	                empUl.appendChild(empLi);
	            });

	            departmentLi.appendChild(empUl);
	        })
	        .catch(error => console.error("Department employee list error:", error));
	}
	
	function toggleNoDepartmentEmployees() {
	    const list = document.getElementById("noDepartmentEmployeeList");

	 
	    if (list.style.display === "block") {
	        list.style.display = "none";
	        list.innerHTML = "";
	        return;
	    }

	   
	    fetch("http://localhost:8080/employees/nullDepartment")
	        .then(response => response.json())
	        .then(employees => {
	            list.innerHTML = "";
	            list.style.display = "block";

	            if (employees.length === 0) {
	                const li = document.createElement("li");
	                li.textContent = "Everyone has a department";
	                list.appendChild(li);
	                return;
	            }

	            employees.forEach(emp => {
	                const li = document.createElement("li");
	                li.textContent = `${emp.name} - ${emp.salary} ₺`;
	                list.appendChild(li);
	            });
	        })
	        .catch(error => console.error("Null department error:", error));
	}

