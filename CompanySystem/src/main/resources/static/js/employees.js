loadAllEmployees();

function loadAllEmployees() {
    fetch("http://localhost:8080/employees")
        .then(response => response.json())
        .then(data => renderEmployeeList(data))
        .catch(error => console.error("Employee loading  error:", error));
}

fetch("http://localhost:8080/employees")
    .then(response => response.json())
    .then(employees => {
        const list = document.getElementById("employeeList");
        list.innerHTML = "";

        employees.forEach(emp => {
            const li = document.createElement("li");

            const deptName = emp.department
                ? emp.department.name
                : "No Department";

            li.textContent = `${emp.name} - ${emp.salary} ₺ (${deptName}) `;

           
            const updateBtn = document.createElement("button");
            updateBtn.textContent = "Update";
            updateBtn.onclick = () => fillEmployeeForm(emp);

          
            const deleteBtn = document.createElement("button");
            deleteBtn.textContent = "Delete";
            deleteBtn.onclick = () => deleteEmployee(emp.id);

            li.appendChild(updateBtn);
            li.appendChild(deleteBtn);
            list.appendChild(li);
        });
    })
    .catch(error => console.error("Employee list error:", error));



function saveEmployee() {
    const id = document.getElementById("employeeId").value;
    const name = document.getElementById("employeeName").value;
    const salary = document.getElementById("employeeSalary").value;
    const departmentId = document.getElementById("employeeDepartmentSelect").value;

    if (!name || !salary) {
        alert("Name ve Salary cannot be empty!");
        return;
    }

   
    if (id) {
        fetch(`http://localhost:8080/employees/update/${id}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                salary: salary
            })
        })
        .then(() => {
            if (departmentId) {
                return fetch(`http://localhost:8080/employees/${id}/changeDepartment/${departmentId}`, {
                    method: "PUT"
                });
            }
        })
        .then(() => {
            resetForm();
            location.reload();
        })
        .catch(error => console.error("Update + Department error:", error));
    }

    
    else {
        fetch("http://localhost:8080/employees/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                salary: salary
            })
        })
        .then(() => fetch("http://localhost:8080/employees")) 
        .then(response => response.json())
        .then(employees => {
            if (departmentId) {
                const lastEmployee = employees[employees.length - 1];
                return fetch(`http://localhost:8080/employees/${lastEmployee.id}/changeDepartment/${departmentId}`, {
                    method: "PUT"
                });
            }
        })
        .then(() => {
            resetForm();
            location.reload();
        })
        .catch(error => console.error("Add + Department error:", error));
    }
}



function fillEmployeeForm(emp) {
    document.getElementById("employeeId").value = emp.id;
    document.getElementById("employeeName").value = emp.name;
    document.getElementById("employeeSalary").value = emp.salary;
	document.getElementById("employeeForm").dataset.updateId = id;
	document.getElementById("employeeForm") .scrollIntoView({ behavior: "smooth" });
				   

    if (emp.department) {
        document.getElementById("employeeDepartmentSelect").value = emp.department.id;
    } else {
        document.getElementById("employeeDepartmentSelect").value = "";
    }

    document.getElementById("formTitle").textContent = "Employee Update";
}

 


function resetForm() {
    document.getElementById("employeeId").value = "";
    document.getElementById("employeeName").value = "";
    document.getElementById("employeeSalary").value = "";
	document.getElementById("employeeDepartmentSelect").value = "";

    document.getElementById("formTitle").textContent = "New Employee Save";
}



function deleteEmployee(id) {
    fetch(`http://localhost:8080/employees/delete/${id}`, {
        method: "DELETE"
    })
    .then(() => location.reload())
    .catch(error => console.error("Delete error:", error));
}

fetch("http://localhost:8080/departments")
    .then(response => response.json())
    .then(departments => {
        const select = document.getElementById("employeeDepartmentSelect");

        departments.forEach(dep => {
            const option = document.createElement("option");
            option.value = dep.id;
            option.textContent = dep.name;
            select.appendChild(option);
        });
    })
    .catch(error => console.error("Department dropdown error:", error));

	function renderEmployeeList(employees) {
	    const list = document.getElementById("employeeList");
	    list.innerHTML = "";

	    employees.forEach(emp => {
	        const li = document.createElement("li");

	        const deptName = emp.department
	            ? emp.department.name
	            : "No Department";

	        li.textContent = `${emp.name} - ${emp.salary} ₺  (${deptName}) `;

	        const updateBtn = document.createElement("button");
	        updateBtn.textContent = "Update";
	        updateBtn.onclick = () => fillEmployeeForm(emp);

	        const deleteBtn = document.createElement("button");
	        deleteBtn.textContent = "Delete";
	        deleteBtn.onclick = () => deleteEmployee(emp.id);

	        li.appendChild(updateBtn);
	        li.appendChild(deleteBtn);
	        list.appendChild(li);
	    });
	}
	function filterBySalary() {
	    const salary = document.getElementById("salaryFilterInput").value;

	    if (!salary) {
	        alert("Please enter the salary!");
	        return;
	    }

	    fetch(`http://localhost:8080/employees/salaryFilter/${salary}`)
	        .then(response => response.json())
	        .then(data => renderEmployeeList(data))
	        .catch(error => console.error("Salary filter error:", error));
	}

