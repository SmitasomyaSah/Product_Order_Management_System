import React, { useState,useEffect } from 'react'

function practice() {
  const [name,setName] = useState("");
  const [email,setEmail] = useState("");
  const [department,setDepartment] = useState("");
  const [employee,setEmployee] = useState([]);

  useEffect(()=>{
  loadEmployees();
  },[]);

  const loadEmployees=()=>{
    axios.get("http://localhost:8080/employees")
    .then(res=>setEmployee(res.data));
  };

  const validate = () => {
    let newErrors = {};

    if (!name.trim()) {
      newErrors.name = "Name is required";
    }

    if (!email.trim()) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      newErrors.email = "Invalid email";
    }

    if (!department.trim()) {
      newErrors.department = "Department is required";
    }

    setErrors(newErrors);

    return Object.keys(newErrors).length === 0;
  };

  const saveEmployee=()=>{
    if(!validate()) return;
    const emp = {name,email,department};
    axios.post("http://localhost:8080/employees",emp)
    .then(()=>{
        loadEmployees();
        setName("");
        setEmail("");
        setDepartment("");
    }); 
  };

  const deleteEmployee=()=>{
    axios.delete("http://localhost:8080/employees/${id}")
    .then(()=>loadEmployees());
  };

  return (
    <div>
    <h1>Employee Management System</h1>
    <input placeholder='name'
           value={name}
           onChange={(e)=>setName(e.target.value)}
    />
    <input placeholder='email'
           value={email}
           onChange={(e)=>setEmail(e.target.value)}
    />
    <input placeholder='department'
           value={department}
           onChange={(e)=>setDepartment(e.target.value)}
    />
    <button onClick={saveEmployee}>Save</button>
    <hr/>
    {employee.map(emp=>(
        <div key={emp.id}>
          {emp.name} - {emp.email} - {emp.department}  

          <button onClick={()=>deleteEmployee(emp.id)}>
            Delete
          </button>
            
        </div>
    ))}
    </div>
  );
}

export default practice;
